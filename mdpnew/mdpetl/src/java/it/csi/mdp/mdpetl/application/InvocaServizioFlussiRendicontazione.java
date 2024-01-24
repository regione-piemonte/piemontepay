/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.clientmod3.EsitoRiceviEsito;
import it.csi.mdp.clientmod3.ParametriRiceviEsito;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.generatedvo.flussoriversamento.CtDatiSingoliPagamenti;
import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
import it.csi.mdp.mdpetl.dto.FlussoRendicontazione;
import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.integration.util.dao.CountFlussoRiversamentoPerIdentificativoFlussoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaRPTDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiDataOraFlussoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiTransactionIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.FlussoNextvalDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciFlussoRiversamentoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciFlussoSingoloPagamentoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ParametriNodoSpcDAO;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.SSLUtils;
import it.csi.mdp.mdpetl.util.mail.MailData;
import it.csi.mdp.mdpetl.util.mail.MailUtil;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediElencoFlussiRendicontazione;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediElencoFlussiRendicontazioneRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediFlussoRendicontazione;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoChiediFlussoRendicontazioneRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.TipoIdRendicontazione;
import it.csi.mdp.utility.CostantiNodoSpc;

public class InvocaServizioFlussiRendicontazione {
	
	private static final Integer STATO_FLUSSO_DA_INVIARE = new Integer ( 1 );


    private static final Integer STATO_FLUSSO_IN_SOVRASCITTURA = new Integer ( 3 );


    private SecureRandom random = new SecureRandom();


	static LogUtil log = new LogUtil(InvocaServizioFlussiRendicontazione.class);

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
	public boolean elaborazioneFlussiPerPSP(String endPointUrl, String azione, String identificativoDominio, String identificativoIntermediarioPA, String identificativoStazioneIntermediarioPA,
			String password, String idPsp, byte[] key, int numeroRetry, boolean gadEnabled) throws Exception {
		
		String methodName = "elaborazioneFlussiPerPSP";
		log.startMethod(methodName);
		
		boolean errore = false;

//		log.debug(methodName, "Elaborazione flusso per PSP " + idPsp);
		
//		NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlussoProva= new NodoChiediFlussoRendicontazioneRisposta ();
//		InvocaServizioFlussiRendicontazioneMock mock= new InvocaServizioFlussiRendicontazioneMock();
//		CtFlussoRiversamento flussoRiversamentoProva = mock.getCtFlussoRiversamentoPerTest ();
//		 FlussoRendicontazione flussoDaSalvareprova = salvaFlussoDB(idPsp, rispostaSingoloFlussoProva, flussoRiversamentoProva);

		JaxWsProxyFactoryBean factory = inizializzaServizio(endPointUrl, azione);
		PagamentiTelematiciRPT pagamentiTelematiciService = (PagamentiTelematiciRPT) factory.create();

		NodoChiediElencoFlussiRendicontazione bodyrichiesta = componiRichiestaElencoFlussi(identificativoDominio, identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, password, idPsp);
		
		if(gadEnabled){
			SSLUtils.setTLSContext(pagamentiTelematiciService);
		    log.info(methodName, "********************   setTSLContex effettuato  ***************************");
		}
			
			
		
		//Retry 5 tentativi max
		NodoChiediElencoFlussiRendicontazioneRisposta risposta = tentativoElencoFlussiRendicontazione(methodName, pagamentiTelematiciService, bodyrichiesta, numeroRetry);
		
		if (risposta == null) {//Se ancora null tutti i tentativi sono falliti
			log.fatal(methodName, "TUTTI I TENTATIVI DI INVOCARE nodoChiediElencoFlussiRendicontazione SONO FALLITI");
			System.exit(-1);
		}
		
		if (risposta.getFault() != null) {
			log.fatal(methodName, "ERRORE INVOCAZIONE SERVIZIO nodoChiediElencoFlussiRendicontazione \nFAULT CODE: " + risposta.getFault().getFaultCode() + "\nFAULT STRING: "
					+ risposta.getFault().getFaultString() + "\nDESCRIPTION " + risposta.getFault().getDescription());
			//System.exit(-1);
		} else {

		    for (TipoIdRendicontazione singoloElenco : risposta.getElencoFlussiRendicontazione().getIdRendicontazione()) {


		        try {

		            Integer result= new CountFlussoRiversamentoPerIdentificativoFlussoDAO(singoloElenco.getIdentificativoFlusso ())
		                            .executeQuery();


		            if (result <1)
		            {
		                log.debug ( methodName, "avvio processo per "+singoloElenco.getIdentificativoFlusso () );  


		                NodoChiediFlussoRendicontazione richiestaSingoloFlusso = compiniRichiestaElenco(identificativoDominio, identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, password,
		                    idPsp, singoloElenco);

		                NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso = tentativiSingoloFlusso(methodName, pagamentiTelematiciService, richiestaSingoloFlusso);

		                if (risposta == null) {//Se ancora null tutti i tentativi sono falliti
		                    log.fatal(methodName, "TUTTI I TENTATIVI DI INVOCARE nodoChiediFlussoRendicontazione SONO FALLITI");
		                    System.exit(-1);
		                }

		                if (rispostaSingoloFlusso.getFault() != null) {
		                    log.fatal(methodName, "ERRORE INVOCAZIONE SERVIZIO nodoChiediFlussoRendicontazione \nFAULT CODE: " + risposta.getFault().getFaultCode() + "\nFAULT STRING: "
		                                    + risposta.getFault().getFaultString() + "\nDESCRIPTION " + risposta.getFault().getDescription());
		                    continue;
		                }

		                // testata del flusso 
		                CtFlussoRiversamento flussoRiversamento = unmarshallFlusso(rispostaSingoloFlusso);

		                // testata del flusso 
		                FlussoRendicontazione flussoDaSalvare = salvaFlussoDB(idPsp, rispostaSingoloFlusso, flussoRiversamento);

		                if (flussoDaSalvare != null)
		                    for (CtDatiSingoliPagamenti datiSingoloPagamento : flussoRiversamento.getDatiSingoliPagamenti()) {
		                        errore = errore | salvaSingoloPagamentoFlussoDB(flussoDaSalvare, datiSingoloPagamento, key, flussoRiversamento.getIstitutoMittente().getDenominazioneMittente());
		                    }


		            }
		            else
		            {
		                log.debug ( methodName, " identificativo flusso "+singoloElenco.getIdentificativoFlusso ()+" presente in banca dati " ); 
		            }

		        } catch (Exception e) {
		            log.error("elaborazioneFlussiPerPSP", "Errore a livello singolo flusso", e);
		            errore = true;
		        }
		    }
		}

		log.stopMethod(methodName);
		return errore;
	}

	private NodoChiediFlussoRendicontazioneRisposta tentativiSingoloFlusso(String methodName, PagamentiTelematiciRPT pagamentiTelematiciService,
			NodoChiediFlussoRendicontazione richiestaSingoloFlusso) {
		
		NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso = null;
		
		for (int i=1;i<=5;i++) {
			try {
				rispostaSingoloFlusso = pagamentiTelematiciService.nodoChiediFlussoRendicontazione(richiestaSingoloFlusso);
				break;//Rompo il retry se e'andato bene
			} catch (Exception e) {
				log.error(methodName, "INVOCAZIONE nodoChiediFlussoRendicontazione NUMERO " + i + " FALLITA, RITENTO ", e);
			}
			try {
				Thread.sleep(3000);//min 3 secondi tra retry
			} catch (InterruptedException e) {
				log.debug(methodName, "Interrotto");
			}
		}
		return rispostaSingoloFlusso;
	}

	private NodoChiediElencoFlussiRendicontazioneRisposta tentativoElencoFlussiRendicontazione(String methodName, PagamentiTelematiciRPT pagamentiTelematiciService,
			NodoChiediElencoFlussiRendicontazione bodyrichiesta, int numeroRetry) {
		
		NodoChiediElencoFlussiRendicontazioneRisposta risposta = null;
		
		for (int i=1;i<=numeroRetry;i++) {
			try {
				risposta = pagamentiTelematiciService.nodoChiediElencoFlussiRendicontazione(bodyrichiesta);
				break;//Rompo il retry se e' andato bene
			} catch (Exception e) {
				log.error(methodName, "INVOCAZIONE nodoChiediElencoFlussiRendicontazione  NUMERO " + i + " FALLITA, RITENTO ", e);
			}
			try {
				Thread.sleep(3000);//min 3 secondi tra retry
			} catch (InterruptedException e) {
				log.debug(methodName, "Interrotto");
				
			}
		}
		return risposta;
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
	private boolean salvaSingoloPagamentoFlussoDB(FlussoRendicontazione flussoDaSalvare,
			CtDatiSingoliPagamenti datiSingoloPagamento, byte[] key, String istitutoMittente)
			throws NamingException, Exception, SerialException, SQLException {
		boolean errore = false;
		try {

			FlussoSingoloPagamento singoloPagamentoDB = new FlussoSingoloPagamento();
			singoloPagamentoDB.setIdFlusso(null != flussoDaSalvare ? flussoDaSalvare.getId(): null);
			singoloPagamentoDB.setCodiceEsitoSingoloPagamento(datiSingoloPagamento.getCodiceEsitoSingoloPagamento());
			singoloPagamentoDB.setDataEsitoSingoloPagamento(new Timestamp(
					datiSingoloPagamento.getDataEsitoSingoloPagamento().toGregorianCalendar().getTimeInMillis()));
			singoloPagamentoDB
					.setIdentificativoUnivocoRiscossione(datiSingoloPagamento.getIdentificativoUnivocoRiscossione());
			singoloPagamentoDB.setIuv(datiSingoloPagamento.getIdentificativoUnivocoVersamento());
			singoloPagamentoDB.setSingoloImportoPagato(datiSingoloPagamento.getSingoloImportoPagato());
			IuvOttico iuvOttico = new EstraiApplicationIdDaIuvDAO(
					datiSingoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery();
			singoloPagamentoDB.setApplicationId(iuvOttico != null ? iuvOttico.getApplicationId()
					: new EstraiApplicationIdDaRPTDAO(datiSingoloPagamento.getIdentificativoUnivocoVersamento())
							.executeQuery());
			singoloPagamentoDB.setIndiceDatiSingoloPagamento(datiSingoloPagamento.getIndiceDatiSingoloPagamento());
			

            if ( "9".equals ( datiSingoloPagamento.getCodiceEsitoSingoloPagamento () ) ) {
                try {
                    EsitoRiceviEsito esitoRiceviEsito = inviaRTAlFruitore ( flussoDaSalvare, datiSingoloPagamento, key, istitutoMittente );
                    // settaggi per il batch 700
                    if ( null != esitoRiceviEsito ) {
                        String esito = esitoRiceviEsito.getEsito ();
                        String codiceErrore = esitoRiceviEsito.getCodiceErrore ();
                        String messaggioErrore = esitoRiceviEsito.getMessaggioErrore ();
                        singoloPagamentoDB.setEsitoUltimoInvioAFruitore ( esito );
                        String msg = "";
                        if ( codiceErrore != null ) {
                            msg = msg.concat ( codiceErrore );
                        }
                        if ( codiceErrore != null && messaggioErrore != null ) {
                            msg = msg.concat ( " - " );
                            msg = msg.concat ( messaggioErrore );
                        } else if ( codiceErrore == null && messaggioErrore != null ) {
                            msg = msg.concat ( messaggioErrore );
                        }
                        singoloPagamentoDB.setMsgUltimoEsitoInvioAFruitore ( StringUtils.substring ( msg, 0, 250 ) );
                    }
                } catch ( Exception e ) {
                    log.error ( "salvaSingoloPagamentoFlussoDB", "Errore durante l'invio RT al fruitore per singolo pagamento con codice esito 9", e );
                    inviaMailFallitoInvioRTAlFruitore ( datiSingoloPagamento, key, ExceptionUtils.getStackTrace ( e ) );

                    singoloPagamentoDB.setEsitoUltimoInvioAFruitore ( "KO" );
                    singoloPagamentoDB.setMsgUltimoEsitoInvioAFruitore (
                        StringUtils.substring (
                            "Errore durante l'invio RT al fruitore per singolo pagamento con codice esito 9 - " + ExceptionUtils.getStackTrace ( e ), 0,
                            250 ) );
                }
                singoloPagamentoDB.setDataUltimoInvioAFruitore ( new Timestamp ( System.currentTimeMillis () ) );
            }
			
			new InserisciFlussoSingoloPagamentoDAO(singoloPagamentoDB).executeUpdate();
			
		} catch (Exception e) {
			log.error("salvaSingoloPagamentoFlussoDB", "Errore a livello di singolo pagamento", e);
			errore = true;
		}
		return errore;
	}
	
	private void inviaMailFallitoInvioRTAlFruitore(CtDatiSingoliPagamenti datiSingoloPagamento, byte[] key, String stackTrace ) {
		NotificaFallitoInvioRTAlFruitoreThread fallitoInvioRTAlFruitore = new NotificaFallitoInvioRTAlFruitoreThread(datiSingoloPagamento, key, stackTrace);
		
		Thread t = new Thread(fallitoInvioRTAlFruitore);
        t.start(); 
	}

	private EsitoRiceviEsito inviaRTAlFruitore ( FlussoRendicontazione flussoDaSalvare, CtDatiSingoliPagamenti datiSingoloPagamento, byte [] key,
		String istitutoMittente )
					throws Exception {
		String timestamp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy-hh:mm:ss:ms");
		timestamp = sdf.format(new Date());
		
		String applicationId = new EstraiApplicationIdDaIuvDAO(datiSingoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery().getApplicationId();
		log.info("inviaRTAlFruitore", "APPLICATION ID!!!!" + applicationId);
		String transactionId = new EstraiTransactionIdDaIuvDAO(datiSingoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery();
		log.info("inviaRTAlFruitore", "TRANSACTION ID!!!!" + transactionId);
		
		if (applicationId == null)  {
			log.warn("inviaRTAlFruitore", "Impossibile inviare il Flusso per IUV " + datiSingoloPagamento.getIdentificativoUnivocoVersamento() + " Al fruitore");
			return null;
		}
		
		Map<String,String> mappaAppCustomFields = new ParametriNodoSpcDAO(key).getMappaApplicationCustomFieldsEnabled(applicationId);

		XStream xs = new XStream();
		log.info("inviaRTAlFruitore", xs.toXML(mappaAppCustomFields));
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(Serviziorissrvspc.class);
		factory.setAddress(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE));
		Serviziorissrvspc iPagNodo = (Serviziorissrvspc)factory.create();
		
		ParametriRiceviEsito parametriRiceviRT = new ParametriRiceviEsito();
		parametriRiceviRT.setApplicationId(applicationId);
		parametriRiceviRT.setCodEsitoPagamento(datiSingoloPagamento.getCodiceEsitoSingoloPagamento());
		parametriRiceviRT.setDataOraMsgRicevuta(datiSingoloPagamento.getDataEsitoSingoloPagamento());
		parametriRiceviRT.setDataEsitoSingoloPagamento(datiSingoloPagamento.getDataEsitoSingoloPagamento());
		parametriRiceviRT.setDescEsitoPagamento("Pagamento eseguito in assenza di RT");
		parametriRiceviRT.setIuv(datiSingoloPagamento.getIdentificativoUnivocoVersamento());
		String stringa35Random =  new BigInteger(160, random).toString(32);
		if (stringa35Random.length() > 32) {
			stringa35Random = stringa35Random.substring(32);
		}
		stringa35Random = "MDP" + stringa35Random;
		parametriRiceviRT.setMac(generaMacVersamento(mappaAppCustomFields.get(CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE),applicationId, datiSingoloPagamento.getIdentificativoUnivocoVersamento(), timestamp, stringa35Random));
		parametriRiceviRT.setIdMsgRicevuta(stringa35Random);
		parametriRiceviRT.setTimestamp(timestamp);
		parametriRiceviRT.setIdentificativoUnivocoRiscossione(datiSingoloPagamento.getIdentificativoUnivocoRiscossione());
		parametriRiceviRT.setImportoPagato(datiSingoloPagamento.getSingoloImportoPagato());
		parametriRiceviRT.setRtPresente(false);
        parametriRiceviRT.setIdentificativoPSP ( null != flussoDaSalvare ? flussoDaSalvare.getIdentificativoPSP () : null );
		parametriRiceviRT.setDenominazionePSP(istitutoMittente);
		
		
		return iPagNodo.riceviEsito ( parametriRiceviRT );
	}

	private String generaMacVersamento(String passphrase, String applicationId, String identificativoUnivocoVersamento, String timestamp, String stringa35Random) {
	    
		
		String sToDigest = passphrase + "%%%%" + applicationId + identificativoUnivocoVersamento + stringa35Random  + timestamp + "%%%%" + passphrase;
		log.debug("CALOCLO MAC PER CHIEDI DATI PAGAMENTO: ", sToDigest);
		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		String mac = Base64.encodeBase64String (bMac);
		mac = mac.substring (0,35);
		return mac;
	}

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
	private FlussoRendicontazione salvaFlussoDB(String idPsp, NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso, CtFlussoRiversamento flussoRiversamento) throws IOException, SQLException,
			NamingException, Exception, SerialException {
		
//		int count = new VerificaFlussoGiaPresenteDAO(flussoRiversamento.getIdentificativoFlusso()).executeQuery();
		List<Timestamp> dateOra = new EstraiDataOraFlussoDAO(flussoRiversamento.getIdentificativoFlusso()).executeQuery();
		
		
		if (CollectionUtils.isEmpty ( dateOra )) {
			
			FlussoRendicontazione flussoDaSalvare = componiFlusso ( rispostaSingoloFlusso, flussoRiversamento, STATO_FLUSSO_DA_INVIARE );

			new InserisciFlussoRiversamentoDAO(flussoDaSalvare).executeUpdate();
			return flussoDaSalvare;
		}
		else
		{
		    if (!verificaSePresenteDataEOra ( flussoRiversamento.getDataOraFlusso(), dateOra ))
		    {
		        FlussoRendicontazione flussoDaSalvare = componiFlusso ( rispostaSingoloFlusso, flussoRiversamento, STATO_FLUSSO_IN_SOVRASCITTURA  );

	            new InserisciFlussoRiversamentoDAO(flussoDaSalvare).executeUpdate();
	            log.info("elaborazioneFlussiPerPSP", "FLUSSO IN SOVRASCRITTURA IL FLUSSO ESISTE GIA', ma con data e ora differenti" + flussoRiversamento.getIdentificativoFlusso() + " " + idPsp);
	            System.out.println("FLUSSO IN SOVRASCRITTURA IL FLUSSO ESISTE GIA', ma con data e ora differenti " + flussoRiversamento.getIdentificativoFlusso() + " " + idPsp);
	            return flussoDaSalvare;
		    }

            
		}
		log.info("elaborazioneFlussiPerPSP", "IL FLUSSO ESISTE GIA', salto " + flussoRiversamento.getIdentificativoFlusso() + " " + idPsp);
		System.out.println("IL FLUSSO ESISTE GIA', salto " + flussoRiversamento.getIdentificativoFlusso() + " " + idPsp);
		return null;
	}

    private boolean verificaSePresenteDataEOra ( XMLGregorianCalendar dataOraFlusso, List<Timestamp> dateOra ) {
        Timestamp dataOraNew =  new Timestamp(dataOraFlusso.toGregorianCalendar().getTimeInMillis());
        for (Timestamp t : dateOra)
        {
            if (dataOraNew.equals ( t ))
            {
                return true;
            }

        }
        return false;
    }

    private FlussoRendicontazione componiFlusso ( NodoChiediFlussoRendicontazioneRisposta rispostaSingoloFlusso, CtFlussoRiversamento flussoRiversamento , Integer statoFlusso)
                    throws IOException, Exception, SerialException, SQLException {
        FlussoRendicontazione flussoDaSalvare = new FlussoRendicontazione();
        flussoDaSalvare.setIdentificativoPSP(flussoRiversamento.getIstitutoMittente().getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco());
        flussoDaSalvare.setDataOraFlusso(new Timestamp(flussoRiversamento.getDataOraFlusso().toGregorianCalendar().getTimeInMillis()));
        flussoDaSalvare.setDataRegolamento(new Timestamp(flussoRiversamento.getDataRegolamento().toGregorianCalendar().getTimeInMillis()));
        flussoDaSalvare.setIdentificativoFlusso(flussoRiversamento.getIdentificativoFlusso());
        flussoDaSalvare.setIdentificativoIstitutoMittente(flussoRiversamento.getIstitutoMittente().getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco());
        flussoDaSalvare.setDenominazioneMittente(flussoRiversamento.getIstitutoMittente().getDenominazioneMittente());
        flussoDaSalvare.setIdentificativoIstitutoRicevente(flussoRiversamento.getIstitutoRicevente().getIdentificativoUnivocoRicevente().getCodiceIdentificativoUnivoco());
        flussoDaSalvare.setDenominazioneRicevente(flussoRiversamento.getIstitutoRicevente().getDenominazioneRicevente());
        flussoDaSalvare.setIdentificativoUnivocoRegolamento(flussoRiversamento.getIdentificativoUnivocoRegolamento());
        flussoDaSalvare.setImportoTotalePagamenti(flussoRiversamento.getImportoTotalePagamenti());
        flussoDaSalvare.setNumeroTotalePagamenti(flussoRiversamento.getNumeroTotalePagamenti().intValue());
        flussoDaSalvare.setVersioneOggetto(flussoRiversamento.getVersioneOggetto());
        flussoDaSalvare.setCodiceBicBancaDiRiversamento(flussoRiversamento.getCodiceBicBancaDiRiversamento());
        flussoDaSalvare.setStatoInvioFlussoBase ( statoFlusso );
        flussoDaSalvare.setStatoInvioFlussoEsteso ( statoFlusso );
        flussoDaSalvare.setXml(IOUtils.toString(rispostaSingoloFlusso.getXmlRendicontazione().getInputStream()));

        flussoDaSalvare.setId(new FlussoNextvalDAO().executeQuery());
        return flussoDaSalvare;
    }

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
		Source so = new StreamSource(this.getClass().getResourceAsStream("/FlussoRiversamento_1_0_4.xsd"));
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
//		richiestaSingoloFlusso.setIdentificativoPSP(idPsp);
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
//		bodyrichiesta.setIdentificativoPSP(idPsp);
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
		log.startMethod(methodName);

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(PagamentiTelematiciRPT.class);
		
		if(!endponitURL.endsWith("/")){
			endponitURL= endponitURL +"/";
		}
		factory.setAddress(endponitURL + azione);

		log.stopMethod(methodName);
		return factory;
	}

//public static void main(String[] args){
//	
//	String endponitURL="pippo";
//	if(!endponitURL.substring(endponitURL.length()-1, endponitURL.length()).equals("/")){
//		endponitURL= endponitURL +"/";
//	}	
//	System.out.println("endponitURL "+ endponitURL);
//}
	
	class NotificaFallitoInvioRTAlFruitoreThread implements Runnable {

		private CtDatiSingoliPagamenti datiSingoloPagamento;
		private String stackTrace;
		private byte[] key;

		NotificaFallitoInvioRTAlFruitoreThread(CtDatiSingoliPagamenti datiSingoloPagamento,byte[] key, String stackTrace) {
			this.datiSingoloPagamento = datiSingoloPagamento;
			this.key=key;
			this.stackTrace = stackTrace;
		}

		private void inviaMail() {
			String methodName = "[NotificaFallitoInvioRTAlFruitoreThread::inviaMail]";
			log.startMethod(methodName);
			try {
				String applicationId = new EstraiApplicationIdDaIuvDAO(
						datiSingoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery().getApplicationId();
				String transactionId = new EstraiTransactionIdDaIuvDAO(
						datiSingoloPagamento.getIdentificativoUnivocoVersamento()).executeQuery();
				
				String endPoint = new ParametriNodoSpcDAO(key).getMappaApplicationCustomFieldsEnabled(applicationId).get(CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE);

				MailData mail = new MailData();
				mail.setTo(MailUtil.getDestinatarioNotificaFallitoInvioRTFruitore());
				mail.setSubject("Errore notifica RT (flusso singolo riversamento codice 9)");
				StringBuilder mailBody = new StringBuilder();
				mailBody.append(
						"Si e' veririficato un errore durante l'invio della RT relativa al flusso singolo pagamento con codice 9\n\n");
				mailBody.append("TRANSACTION ID: '").append(transactionId).append("'\n");
				mailBody.append("APPLICATION ID: '").append(applicationId).append("'\n");
				mailBody.append("IUV: '").append(datiSingoloPagamento.getIdentificativoUnivocoVersamento()).append("'\n");
				mailBody.append("IUR: '").append(datiSingoloPagamento.getIdentificativoUnivocoRiscossione()).append("'\n\n");
				mailBody.append("END POINT: '").append(endPoint).append("'\n\n");
				
				mailBody.append("Dettagli errore: \n");
				mailBody.append(stackTrace);
				mail.setText(mailBody.toString());

				MailUtil.inviaMail(mail);
			} catch (Exception e) {
				log.error(methodName, "Errore durante l'invio della mail ", e);
			}
			log.stopMethod(methodName);
		}


		@Override
		public void run() {
			inviaMail();
		}

	}

}
