/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import it.csi.mdp.adapters.business.CoreUtilities;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.mdp.generatedvo.pagamenti.CtRichiestaPagamentoTelematico;
import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaRPTInviataDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRichiestePagamentoDaInviareDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciGiornaleEventiDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ParametriNodoSpcDAO;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.IntestazionePPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaRPTRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.utility.CostantiNodoSpc;
import it.csi.mdp.utility.DatiInterscambioNodoUtility;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.thoughtworks.xstream.XStream;

public class InvioDifferitoRPT {

	static LogUtil log = new LogUtil(InvioDifferitoRPT.class);

	public void elaborazioneReinvio(byte[] key) throws Exception {

		String methodName = "elaborazioneReinvio";
		log.startMethod(methodName);
		
		
		List<DatiRichiesta> elencoRichiesteDaInviare = new EstraiRichiestePagamentoDaInviareDAO().executeQuery();
		String applicationIdCorrente = null;
		Map<String,String> mappaAppCustomFieldsCorrente = null;
		XStream xs = new XStream();
		log.info(methodName, xs.toXML(elencoRichiesteDaInviare));
		for (DatiRichiesta rptDaInviare : elencoRichiesteDaInviare) {
			log.info(methodName, "ELABORAZIONE RPT CON ID " + rptDaInviare.getId());
			if (applicationIdCorrente == null || !rptDaInviare.getApplicationId().equals(applicationIdCorrente)) {
				applicationIdCorrente = rptDaInviare.getApplicationId();
				mappaAppCustomFieldsCorrente = new ParametriNodoSpcDAO(key).getMappaApplicationCustomFields(applicationIdCorrente);
			}
			NodoInviaRPTRisposta esito = null;
			try {
				log.info(methodName, xs.toXML(mappaAppCustomFieldsCorrente));
				esito = inviaRPTAlNodo(rptDaInviare, mappaAppCustomFieldsCorrente);
			} finally {
				regirstraEventoGiornale(rptDaInviare, esito, mappaAppCustomFieldsCorrente);
			}
			
			if (Constants.ESITO_OK.equals(esito)) {
				new AggiornaRPTInviataDAO(rptDaInviare.getId()).executeUpdate();
			}
		}
		log.stopMethod(methodName);
	}

	

	private void regirstraEventoGiornale(DatiRichiesta rptDaInviare,
			NodoInviaRPTRisposta esito,
			Map<String, String> mappaAppCustomFieldsCorrente) {
		
		try {
			LoggerUtil.debug(rptDaInviare.getRptXml());
			JAXBContext jContext = JAXBContext.newInstance(CtRichiestaPagamentoTelematico.class);
			Unmarshaller unmarshaller = jContext.createUnmarshaller();
			unmarshaller.setEventHandler(new PassaErroriDiValidazioneEventHandler());
			SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Source so = new StreamSource(this.getClass().getResourceAsStream("/PagInf_RPT_RT_6_2_0.xsd"));
			Schema s = sf.newSchema(so);
			unmarshaller.setSchema(s);
			CtRichiestaPagamentoTelematico rpt = (CtRichiestaPagamentoTelematico)unmarshaller.unmarshal(new ByteArrayInputStream(rptDaInviare.getRptXml().getBytes()));
			
			GiornaleEvento gde = new GiornaleEvento();
			gde.setDataoraevento(new Timestamp(System.currentTimeMillis()));
			gde.setIdentificativodominio(rptDaInviare.getIdentificativoDominio());
			gde.setIuv(rptDaInviare.getIuv());
			gde.setCodiceContesto(rptDaInviare.getCodiceContestoPagamento());
			gde.setIdPsp(rptDaInviare.getIdPsp());
			gde.setTipoversamento(rpt.getDatiVersamento().getTipoVersamento().value());
			gde.setComponente("mdpetl");
			gde.setCategoriaevento("BATCH");
			gde.setTipoevento("nodoInviaRPT");
			gde.setSottotipoevento("reinvioRPTBatch");
			gde.setIdentificativofruitore(rptDaInviare.getApplicationId());
			gde.setIdentificativoerogatore(rptDaInviare.getIdentificativoIntermediarioPa());
			gde.setIdentificativostazioneintermediariopa(rptDaInviare.getIdentificativoStazioneIntermediarioPa());
			gde.setIdIntPsp(rptDaInviare.getIdIntermPsp());
			if (esito != null && esito.getFault() != null)  {
				gde.setParametrispecificiinterfaccia(esito.getFault().getFaultCode() + " - " + esito.getFault().getDescription());
			}
			gde.setEsito(esito.getEsito());
			gde.setApplicationId(rptDaInviare.getApplicationId());
			gde.setTransactionId(rptDaInviare.getTransactionId());
			
			new InserisciGiornaleEventiDAO(gde).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
//			LoggerUtil.error("IMPOSSIBILE REGISTRARE EVENTO GIORNALE", e);
		}
	}



	private NodoInviaRPTRisposta inviaRPTAlNodo(DatiRichiesta rptDaInviare,
			Map<String, String> mappaAppCustomFields) {

		Map<String, String> mappaTea = new HashMap<String, String>();
		mappaTea.put(CostantiNodoSpc.TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO, rptDaInviare.getIuv());
		//crea intestazione SOAP del WS
		IntestazionePPT intestazionePPT = DatiInterscambioNodoUtility.creaIntestazionePPTWs(mappaAppCustomFields, mappaTea, "n/a");
		
		//crea body SOAP del WS
		NodoInviaRPT nodoInviaRPT = DatiInterscambioNodoUtility.creaNodoInviaRPTWs(mappaAppCustomFields, rptDaInviare.getIdCanale(), rptDaInviare.getIdIntermPsp(), rptDaInviare.getIdPsp(),  "");

		nodoInviaRPT.setRpt(rptDaInviare.getRptXml().getBytes());
		
		JaxWsProxyFactoryBean factory = CoreUtilities.inizializzaServizio(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO), "nodoInviaRPT");
		
		PagamentiTelematiciRPT pagamentiTelematiciService = (PagamentiTelematiciRPT)factory.create();

		NodoInviaRPTRisposta risposta = pagamentiTelematiciService.nodoInviaRPT(nodoInviaRPT, intestazionePPT);
		
		return risposta;
	}
	
	private class PassaErroriDiValidazioneEventHandler implements ValidationEventHandler {

		public boolean handleEvent(ValidationEvent arg0) {
			return true;
		}
		
	}
}
