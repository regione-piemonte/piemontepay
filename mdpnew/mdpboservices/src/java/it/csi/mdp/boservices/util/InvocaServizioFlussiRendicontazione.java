/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.util;

//import it.csi.mdp.generatedvo.flussoriversamento.CtDatiSingoliPagamenti;
//import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
//import it.csi.mdp.mdpetl.dto.FlussoRendicontazione;
//import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
//import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
//import it.csi.mdp.mdpetl.integration.util.dao.FlussoNextvalDAO;
//import it.csi.mdp.mdpetl.integration.util.dao.InserisciFlussoRiversamentoDAO;
//import it.csi.mdp.mdpetl.integration.util.dao.InserisciFlussoSingoloPagamentoDAO;
//import it.csi.mdp.mdpetl.integration.util.dao.VerificaFlussoGiaPresenteDAO;
//import it.csi.mdp.mdpetl.util.LogUtil;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediElencoFlussiRendicontazione;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediElencoFlussiRendicontazioneRisposta;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediFlussoRendicontazione;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediFlussoRendicontazioneRisposta;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
//import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoIdRendicontazione;

import it.csi.mdp.generatedvo.flussoriversamento.CtDatiSingoliPagamenti;
import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediElencoFlussiRendicontazione;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediElencoFlussiRendicontazioneRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediFlussoRendicontazione;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediFlussoRendicontazioneRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoIdRendicontazione;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

//import org.apache.commons.io.IOUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class InvocaServizioFlussiRendicontazione {

	private static final Logger log = Logger.getLogger(Constants.APPLICATION_CODE);

	/**
	 * Recepimento del flusso per un singolo PSP
	 * 
	 * @param endPointUrl
	 * @param azione
	 * @param identificativoDominio
	 * @param identificativoIntermediarioPA
	 * @param identificativoStazioneIntermediarioPA
	 * @param password
	 * @param idPsp
	 * @throws Exception
	 */
	public List<CtFlussoRiversamento> elaborazioneFlussiPerPSP(String endPointUrl, String azione, String identificativoDominio, String identificativoIntermediarioPA, String identificativoStazioneIntermediarioPA,
			String password, String idPsp) throws Exception {


		log.debug("elaborazioneFlussiPerPSP Elaborazione flusso per PSP " + idPsp);
		log.debug("elaborazioneFlussiPerPSP Elaborazione azione " + azione);

		
		JaxWsProxyFactoryBean factory = inizializzaServizio(endPointUrl, azione);
		PagamentiTelematiciRPT pagamentiTelematiciService = (PagamentiTelematiciRPT) factory.create();

		log.debug("elaborazioneFlussiPerPSP identificativoDominio " + identificativoDominio);
		log.debug("elaborazioneFlussiPerPSP identificativoIntermediarioPA " + identificativoIntermediarioPA);
		log.debug("elaborazioneFlussiPerPSP identificativoStazioneIntermediarioPA " + identificativoStazioneIntermediarioPA);
		log.debug("elaborazioneFlussiPerPSP password " + password);
		log.debug("elaborazioneFlussiPerPSP Elaborazione flusso per PSP " + idPsp);
		
		NodoChiediElencoFlussiRendicontazione bodyrichiesta = componiRichiestaElencoFlussi(identificativoDominio, identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, password, idPsp);

		NodoChiediElencoFlussiRendicontazioneRisposta risposta = pagamentiTelematiciService.nodoChiediElencoFlussiRendicontazione(bodyrichiesta);

		if (risposta.getFault() != null) {
			log.error("ERRORE INVOCAZIONE SERVIZIO nodoChiediElencoFlussiRendicontazione \nFAULT CODE: " + risposta.getFault().getFaultCode() + "\nFAULT STRING: "
					+ risposta.getFault().getFaultString() + "\nDESCRIPTION " + risposta.getFault().getDescription());
			//System.exit(-1);
		}
		List<CtFlussoRiversamento> listaFlussi = new ArrayList<CtFlussoRiversamento>();
		
		for (TipoIdRendicontazione singoloElenco : risposta.getElencoFlussiRendicontazione().getIdRendicontazione()) {
			NodoChiediFlussoRendicontazione richiestaSingoloFlusso = compiniRichiestaElenco(identificativoDominio, identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, password,idPsp, singoloElenco);

			NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso = pagamentiTelematiciService.nodoChiediFlussoRendicontazione(richiestaSingoloFlusso);

			if (rispostaSingoloFlusso.getFault() != null) {
				log.error("ERRORE INVOCAZIONE SERVIZIO nodoChiediFlussoRendicontazione \nFAULT CODE: " + risposta.getFault().getFaultCode() + "\nFAULT STRING: "
						+ risposta.getFault().getFaultString() + "\nDESCRIPTION " + risposta.getFault().getDescription());
				//System.exit(-1);
			}

			
			CtFlussoRiversamento flussoRiversamento = unmarshallFlusso(rispostaSingoloFlusso);

			
			listaFlussi.add(flussoRiversamento);
			// testata del flusso 
//			FlussoRendicontazione flussoDaSalvare = salvaFlussoDB(idPsp, rispostaSingoloFlusso, flussoRiversamento);
//			
//			if (flussoDaSalvare != null)
//			for (CtDatiSingoliPagamenti datiSingoloPagamento : flussoRiversamento.getDatiSingoliPagamenti()) {
//				salvaSingoloPagamentoFlussoDB(flussoDaSalvare, datiSingoloPagamento);
//			}
		}

		log.info("elaborazioneFlussiPerPSP STOP");
		return listaFlussi;
	}

	/**
	 * Salva sul DB i dati del singolo pagamento del flusso
	 * 
	 * @param flussoDaSalvare
	 * @param datiSingoloPagamento
	 * @throws NamingException
	 * @throws Exception
	 * @throws SerialException
	 * @throws SQLException
	 */
//	private void salvaSingoloPagamentoFlussoDB(FlussoRendicontazione flussoDaSalvare, CtDatiSingoliPagamenti datiSingoloPagamento) throws NamingException, Exception, SerialException, SQLException {
//		FlussoSingoloPagamento singoloPagamentoDB = new FlussoSingoloPagamento();
//		singoloPagamentoDB.setIdFlusso(flussoDaSalvare.getId());
//		singoloPagamentoDB.setCodiceEsitoSingoloPagamento(datiSingoloPagamento.getCodiceEsitoSingoloPagamento());
//		singoloPagamentoDB.setDataEsitoSingoloPagamento(new Timestamp(datiSingoloPagamento.getDataEsitoSingoloPagamento().toGregorianCalendar().getTimeInMillis()));
//		singoloPagamentoDB.setIdentificativoUnivocoRiscossione(datiSingoloPagamento.getIdentificativoUnivocoRiscossione());
//		singoloPagamentoDB.setIuv(datiSingoloPagamento.getIdentificativoUnivocoVersamento());
//		singoloPagamentoDB.setSingoloImportoPagato(datiSingoloPagamento.getSingoloImportoPagato());
//		singoloPagamentoDB.setApplicationId(new EstraiApplicationIdDaIuvDAO(datiSingoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery());
//
//		new InserisciFlussoSingoloPagamentoDAO(singoloPagamentoDB).executeUpdate();
//	}

	/**
	 * Salva sul DB i dati del flusso elaborato
	 * 
	 * @param idPsp
	 * @param rispostaSingoloFlusso
	 * @param flussoRiversamento
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws Exception
	 * @throws SerialException
	 */
//	private FlussoRendicontazione salvaFlussoDB(String idPsp, NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso, CtFlussoRiversamento flussoRiversamento) throws IOException, SQLException,
//			NamingException, Exception, SerialException {
//		
//		int count = new VerificaFlussoGiaPresenteDAO(flussoRiversamento.getIdentificativoFlusso(), idPsp).executeQuery();
//		
//		if (count == 0) {
//			
//			FlussoRendicontazione flussoDaSalvare = new FlussoRendicontazione();
//			flussoDaSalvare.setIdentificativoPSP(idPsp);
//			flussoDaSalvare.setDataOraFlusso(new Timestamp(flussoRiversamento.getDataOraFlusso().toGregorianCalendar().getTimeInMillis()));
//			flussoDaSalvare.setDataRegolamento(new Timestamp(flussoRiversamento.getDataRegolamento().toGregorianCalendar().getTimeInMillis()));
//			flussoDaSalvare.setIdentificativoFlusso(flussoRiversamento.getIdentificativoFlusso());
//			flussoDaSalvare.setIdentificativoIstitutoMittente(flussoRiversamento.getIstitutoMittente().getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco());
//			flussoDaSalvare.setDenominazioneMittente(flussoRiversamento.getIstitutoMittente().getDenominazioneMittente());
//			flussoDaSalvare.setIdentificativoIstitutoRicevente(flussoRiversamento.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco());
//			flussoDaSalvare.setDenominazioneRicevente(flussoRiversamento.getIstitutoRicevente().getDenominazioneRicevente());
//			flussoDaSalvare.setIdentificativoUnivocoRegolamento(flussoRiversamento.getIdentificativoUnivocoRegolamento());
//			flussoDaSalvare.setImportoTotalePagamenti(flussoRiversamento.getImportoTotalePagamenti());
//			flussoDaSalvare.setNumeroTotalePagamenti(flussoRiversamento.getNumeroTotalePagamenti().intValue());
//			flussoDaSalvare.setVersioneOggetto(flussoRiversamento.getVersioneOggetto());
//	
//			flussoDaSalvare.setXml(IOUtils.toString(rispostaSingoloFlusso.getXmlRendicontazione().getInputStream()));
//	
//			flussoDaSalvare.setId(new FlussoNextvalDAO().executeQuery());
//
//			new InserisciFlussoRiversamentoDAO(flussoDaSalvare).executeUpdate();
//			return flussoDaSalvare;
//		}
//		log.info("elaborazioneFlussiPerPSP", "IL FLUSSO ESISTE GIA', salto " + flussoRiversamento.getIdentificativoFlusso() + " " + idPsp);
//		System.out.println("IL FLUSSO ESISTE GIA', salto " + flussoRiversamento.getIdentificativoFlusso() + " " + idPsp);
//		return null;
//	}

	/**
	 * Esegue l'unmarshall dell'xml rappresentate il flusso di rendicontazione
	 * 
	 * @param rispostaSingoloFlusso
	 * @return
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws IOException
	 */
	private CtFlussoRiversamento unmarshallFlusso(NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso) throws JAXBException, SAXException, IOException {
		JAXBContext jContext = JAXBContext.newInstance(CtFlussoRiversamento.class);
		Unmarshaller unmarshaller = jContext.createUnmarshaller();

		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Source so = new StreamSource(this.getClass().getResourceAsStream("/FlussoRiversamento_1_0_2.xsd"));
		Schema s = sf.newSchema(so);
		//System.out.println("IMPOSTO LO SCHEMA");
		unmarshaller.setSchema(s);

		// unmarshaller.setEventHandler(new ValidationEventHandler() {public
		// boolean handleEvent(ValidationEvent event ) {throw new
		// RuntimeException(event.getMessage(),event.getLinkedException());}});

		CtFlussoRiversamento flussoRiversamento = (CtFlussoRiversamento) unmarshaller.unmarshal(rispostaSingoloFlusso.getXmlRendicontazione().getInputStream());
		
		return flussoRiversamento;
	}

	/**
	 * Compone la richiesta SOAP per l'invocazione dell'operazione
	 * nodoChiediFlussoRendicontazione
	 * 
	 * @param identificativoDominio
	 * @param identificativoIntermediarioPA
	 * @param identificativoStazioneIntermediarioPA
	 * @param password
	 * @param idPsp
	 * @param singoloElenco
	 * @return
	 */
	private NodoChiediFlussoRendicontazione compiniRichiestaElenco(String identificativoDominio, String identificativoIntermediarioPA, String identificativoStazioneIntermediarioPA, String password,
			String idPsp, TipoIdRendicontazione singoloElenco) {
		NodoChiediFlussoRendicontazione richiestaSingoloFlusso = new NodoChiediFlussoRendicontazione();
		richiestaSingoloFlusso.setIdentificativoDominio(identificativoDominio);
		richiestaSingoloFlusso.setIdentificativoIntermediarioPA(identificativoIntermediarioPA);
		richiestaSingoloFlusso.setIdentificativoStazioneIntermediarioPA(identificativoStazioneIntermediarioPA);
		richiestaSingoloFlusso.setPassword(password);
		richiestaSingoloFlusso.setIdentificativoPSP(idPsp);
		richiestaSingoloFlusso.setIdentificativoFlusso(singoloElenco.getIdentificativoFlusso());
		return richiestaSingoloFlusso;
	}

	/**
	 * Compone la richiesta SOAP per l'invocazione dell'operazione
	 * nodoChiediElencoFlussiRendicontazione
	 * 
	 * @param identificativoDominio
	 * @param identificativoIntermediarioPA
	 * @param identificativoStazioneIntermediarioPA
	 * @param password
	 * @param idPsp
	 * @return
	 */
	private NodoChiediElencoFlussiRendicontazione componiRichiestaElencoFlussi(String identificativoDominio, String identificativoIntermediarioPA, String identificativoStazioneIntermediarioPA,
			String password, String idPsp) {
		NodoChiediElencoFlussiRendicontazione bodyrichiesta = new NodoChiediElencoFlussiRendicontazione();
		bodyrichiesta.setIdentificativoDominio(identificativoDominio);
		bodyrichiesta.setIdentificativoIntermediarioPA(identificativoIntermediarioPA);
		bodyrichiesta.setIdentificativoStazioneIntermediarioPA(identificativoStazioneIntermediarioPA);
		bodyrichiesta.setPassword(password);
		bodyrichiesta.setIdentificativoPSP(idPsp);
		return bodyrichiesta;
	}

	/**
	 * Inizializza il servizio
	 * 
	 * @param endponitURL
	 * @param azione
	 * @return
	 */
	private JaxWsProxyFactoryBean inizializzaServizio(String endponitURL, String azione) {
		String methodName = "inizializzaServizio";
		log.info(methodName+ " START");

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(PagamentiTelematiciRPT.class);
		
		if(!endponitURL.substring(endponitURL.length()-1, endponitURL.length()).equals("/")){
			endponitURL= endponitURL +"/";
		}
		factory.setAddress(endponitURL + azione);

		
		
		log.info("factory.getAddress() " + factory.getAddress());
		log.info(methodName + " STOP");
		return factory;
	}


	
	public static void main(String[] args){
		new InvocaServizioFlussiRendicontazione().inizializzaServizio("endponitURL/","azione");
	}

}
