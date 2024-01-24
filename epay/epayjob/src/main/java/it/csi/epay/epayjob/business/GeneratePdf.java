/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import it.csi.epay.epayjob.model.RtData;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Rt;
import org.xml.sax.SAXException;

import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

public class GeneratePdf extends RtXml{
	
	private final LogUtil log;
	private final JobFacade jobFacade;
	
	public GeneratePdf(JobFacade jobFacade) {
		super();
		log = new LogUtil(this.getClass());
		this.jobFacade = jobFacade;
	}
	
	public RtData execute() throws NamingException {
		final String methodName = "execute";
		
		log.info(methodName, "Executed  at " + new Date());
		RtData rtData = new RtData();
		
		for (Rt rt : jobFacade.elencoRtSenzaRicevutaPdf()) {
			log.debug(methodName, "Elaborazione Rt n " + rt.getIdRt());
			try {
				setDoc(jobFacade.readRtXml(rt.getIdRt()));
				
				String idTransazione = getNode(XmlField.msgRichiesta);
				String cfEnte = getNode(XmlField.cfEnte);
				String nomeEnte = getNode(XmlField.nomeEnte);
				String nomePSP = getNode(XmlField.nomePSP);
				String dataOra = convertDate(getNode(XmlField.dataOra));
				
				String dataEsitoSingoloPagamento = getNodeOrNull() != null? convertDate(getNodeOrNull()):"";
				String cfPagatore = getNode(XmlField.cfPagatore);
				String nomePagatore = getNode(XmlField.nomePagatore);
				BigDecimal importo = new BigDecimal(getNode(XmlField.importo));
				String iuv = getNode(XmlField.iuv);
				String codiceEsitoPagamento = getNode(XmlField.codiceEsitoPagamento);
				String iur = getNodes(XmlField.iur);
				String esitoSingoloPagamento = getNodes(XmlField.esitoSingoloPagamento);;
				
				Ente ente = jobFacade.getEnteByCF(cfEnte);
				Pagamento pagamento = jobFacade.getPagamento(iuv);
									
				Map<ParamNameXPdf, Object> param = new EnumMap<ParamNameXPdf, Object>(ParamNameXPdf.class);
				param.put(ParamNameXPdf.A1_LOGO_ENTE, ente.getLogo());
				param.put(ParamNameXPdf.A2_LOGO_PAGOPA, jobFacade.getLogoEnte(0L));
				param.put(ParamNameXPdf.B2_DESCRIZIONE_TASSA, pagamento.getTipoPagamento().getDescrizionePortale());
				param.put(ParamNameXPdf.B3_STATO_TRANSAZIONE, codiceEsitoPagamento.equals("0")  ? "Pagamento eseguito" : codiceEsitoPagamento.equals("2")  ? "Pagamento eseguito parzialmente" : "Pagamento NON eseguito");
				param.put(ParamNameXPdf.C1_DESCRIZIONE_ENTE, nomeEnte);
				param.put(ParamNameXPdf.C2_CF_ENTE, cfEnte);
				param.put(ParamNameXPdf.C3_IMPORTO, importo);
				/* Pagamento model emette IllegalArgumentException che viene ora gestita - BEGIN */
				String codiceAvviso;
				try {
					codiceAvviso = pagamento.getCodiceAvviso ();
				} catch ( IllegalArgumentException e ) {
					codiceAvviso = null;
				}
				param.put ( ParamNameXPdf.C4_CODICE_AVVISO, codiceAvviso );
				/* Pagamento model emette IllegalArgumentException che viene ora gestita - END */
				param.put(ParamNameXPdf.C5_IUV, iuv); 
				param.put(ParamNameXPdf.D1_NOME, nomePagatore);
				param.put(ParamNameXPdf.D2_CF, cfPagatore);
				param.put(ParamNameXPdf.E1_ID_TRANSAZIONE, idTransazione);
				param.put(ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE, nomePSP);
				param.put(ParamNameXPdf.E3_DATA_ORA, dataOra);
				param.put(ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO, dataEsitoSingoloPagamento);
				param.put(ParamNameXPdf.E4_IDENTIFICATIVO_UNIVOCO_RISCOSSIONE, iur);
				param.put(ParamNameXPdf.E5_ESITO_SINGOLO_PAGAMENTO, esitoSingoloPagamento);
				param.put(ParamNameXPdf.C6_NOTE_PAGAMENTO, pagamento.getNote());
				param.put(ParamNameXPdf.C6_CAUSALE, pagamento.getCausale());

				byte[] rt_pdf = jobFacade.creaRicevutaPdf(param);
				rt.setRicevutaPdf(rt_pdf);
				jobFacade.saveRtPdf(rt.getIdRt(), rt_pdf);
				
				rtData.setParam(param);
				rtData.setRt(rt);
				rtData.setPagamento(pagamento);
			} catch (IOException | SAXException | ParserConfigurationException | XPathExpressionException | NoDataException e) {
				e.printStackTrace();
			}
		}
		return rtData;
	}
	
	private static String convertDate(String dateIn) {
		SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateOut = "";
	    try {
	    	Date date = sdfIn.parse(dateIn);
	    	dateOut = sdfOut.format(date);
	    } catch (ParseException e) {
	    	System.out.println("Exception " + e);
	    }
	    return dateOut;
	}
}
