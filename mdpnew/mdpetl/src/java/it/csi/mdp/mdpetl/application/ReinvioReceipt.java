/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.clientmod3.ChiaveValore;
import it.csi.mdp.clientmod3.EsitoRiceviEsito;
import it.csi.mdp.clientmod3.ParametriRiceviEsito;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.generatedvo.pagamentimod3.CtMapEntry;
import it.csi.mdp.generatedvo.pagamentimod3.PaSendRTReq;
import it.csi.mdp.mdpetl.dto.DatiReceiptNonInviata;
import it.csi.mdp.mdpetl.dto.LoggingReceipt;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaCodaInvioReceiptInvioFallitoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaDataInizioTentativiCodaInvioReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaDataTentativiCodaInvioReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaStatoInvioFruitoreReceipt;
import it.csi.mdp.mdpetl.integration.util.dao.DeleteReceiptCodaInviateDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiConfigurazioneDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRicevuteNonInviateDataPrioritariaReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRicevuteNonInviateReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciLoggingReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ModificaReceiptInvioFallitoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ModificaReceiptInvioRiuscitoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ParametriNodoSpcDAO;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;
import it.csi.mdp.mdpetl.util.UtilDate;
import it.csi.mdp.utility.CostantiNodoSpc;


public class ReinvioReceipt {

	private static final LogUtil log = new LogUtil ( ReinvioReceipt.class );

	private final SimpleDateFormat sdf = new SimpleDateFormat ( "ddMMyyyy-hh:mm:ss:ms" );

	public void elaborazioneReinvio ( byte[] key ) throws Exception {
		String methodName = "elaborazioneReinvioReceipt";
		log.startMethod ( methodName );
		Set<String> iuvInviati = new HashSet<String> ();
		String applicationIdCorrente = null;
		Map<String, String> mappaAppCustomFieldsCorrente = null;

		long sleepReinvio = Long.parseLong ( new EstraiConfigurazioneDAO ( Constants.SLEEP_REINVIO_RECEIPT ).executeQuery ().getValue () );
		log.info ( methodName, "sleepReinvio : " + sleepReinvio );
		System.out.println ( "sleepReinvio : " + sleepReinvio );

		String limiteLetturaRecordReinvioReceipt = new EstraiConfigurazioneDAO ( Constants.LIMITE_LETTURA_RECORD_REINVIO_RECEIPT ).executeQuery ().getValue ();
		log.info ( methodName, "limiteLetturaRecordReinvioReceipt : " + limiteLetturaRecordReinvioReceipt );
		System.out.println ( "limiteLetturaRecordReinvioReceipt : " + limiteLetturaRecordReinvioReceipt );

		Integer limiteNumGiorniReinvioReceipt = new Integer ( new EstraiConfigurazioneDAO ( Constants.LIMITE_NUM_GIORNI_REINVIO_RECEIPT ).executeQuery ().getValue () );
		log.info ( methodName, "limiteNumGiorniReinvioReceipt : " + limiteNumGiorniReinvioReceipt );
		System.out.println ( "limiteNumGiorniReinvioReceipt : " + limiteNumGiorniReinvioReceipt );

		String dataPrioritariaReinvio = new EstraiConfigurazioneDAO ( Constants.DATA_PRIORITARIA_REINVIO_RECEIPT ).executeQuery ().getValue ();
		log.info ( methodName, "dataPrioritariaReinvio : " + dataPrioritariaReinvio );
		System.out.println ( "dataPrioritariaReinvio : " + dataPrioritariaReinvio );

		String applicazionePrioritariaReinvio = new EstraiConfigurazioneDAO ( Constants.APPLICAZIONE_PRIORITARIA_REINVIO_RECEIPT ).executeQuery ().getValue ();
		log.info ( methodName, "applicazionePrioritariaReinvio : " + applicazionePrioritariaReinvio );
		System.out.println ( "applicazionePrioritariaReinvio : " + applicazionePrioritariaReinvio );

		List<DatiReceiptNonInviata> elencoRicevuteNonInviate = ottieniEstraiRicevuteNonInviateReceiptDAO ( limiteLetturaRecordReinvioReceipt, limiteNumGiorniReinvioReceipt,
										dataPrioritariaReinvio, applicazionePrioritariaReinvio, key );

		XStream xs = new XStream ();
//		Unmarshaller unmarshaller = inizializzaUnMarshallerReceipt ();
		
		
		log.info ( methodName, "Numero di Receipt da inviare: " + ( null != elencoRicevuteNonInviate ? elencoRicevuteNonInviate.size () : "0" ) );
		System.out.println ( "Numero di Receipt da inviare: " + ( null != elencoRicevuteNonInviate ? elencoRicevuteNonInviate.size () : "0" ) );

		if ( null != elencoRicevuteNonInviate ) {
			for ( DatiReceiptNonInviata ricevuta : elencoRicevuteNonInviate ) {
				try {
					log.info ( methodName, "Trasmissione in corso della Receipt con IUV: " + ricevuta.getCreditorReferenceId () );
					System.out.println ( "Trasmissione in corso della Receipt con IUV: " + ricevuta.getCreditorReferenceId () );
					log.info ( methodName, xs.toXML ( ricevuta ) );
					if ( !ricevuta.getApplicationId ().equals ( applicationIdCorrente ) ) {
						applicationIdCorrente = ricevuta.getApplicationId ();
						mappaAppCustomFieldsCorrente = new ParametriNodoSpcDAO ( key ).getMappaApplicationCustomFieldsEnabled ( applicationIdCorrente );
					}

					Timestamp dataTentativi = new Timestamp ( System.currentTimeMillis () );
					ricevuta.getCoda ().setDataTentativi ( dataTentativi );
					new AggiornaDataTentativiCodaInvioReceiptDAO ( ricevuta.getCoda ().getDataTentativi (), ricevuta.getCreditorReferenceId () ).executeUpdate ();

					if ( null == ricevuta.getCoda ().getDataInizioTentativi () ) {
						ricevuta.getCoda ().setDataInizioTentativi ( dataTentativi );
						new AggiornaDataInizioTentativiCodaInvioReceiptDAO (
										ricevuta.getCoda ().getDataInizioTentativi (),
										ricevuta.getCreditorReferenceId () ).executeUpdate ();
					}
					//sperando vada bene, o si necessita EsitoRiceviReceipt
					EsitoRiceviEsito esito = spedisciReceiptAlFruitore ( ricevuta, mappaAppCustomFieldsCorrente );
//					EsitoRiceviEsito esito = new EsitoRiceviEsito ();
//					esito.setEsito ( "OK" );

					if ( Constants.ESITO_OK.equals ( esito.getEsito () ) ) {
						iuvInviati.add ( ricevuta.getCreditorReferenceId () );
						new ModificaReceiptInvioRiuscitoDAO ( ricevuta.getId () ).executeUpdate ();
//						Al momento non esiste la tabella, sara' da inserire 
						loggaInvioEsito ( Constants.ESITO_OK, "Reinvio Receipt", ricevuta.getCreditorReferenceId () );
					} else {
						log.warn ( methodName, "Invio fallito per la Receipt con IUV: " + ricevuta.getCreditorReferenceId () );
						System.out.println ( "Invio fallito per la Receipt con IUV: " + ricevuta.getCreditorReferenceId () );
						new ModificaReceiptInvioFallitoDAO ( ricevuta.getId () , ( StringUtils.isEmpty ( esito.getMessaggioErrore () ) ) ? "Errore Generico" : esito.getMessaggioErrore ()).executeUpdate ();
						Integer numeroGiorniKo = calcolaNumGiorniTentativiKo ( dataPrioritariaReinvio, applicazionePrioritariaReinvio, ricevuta );
						new AggiornaCodaInvioReceiptInvioFallitoDAO ( ricevuta.getCoda ().getContatoreTentativi () + 1,
										numeroGiorniKo,
										( StringUtils.isEmpty ( esito.getMessaggioErrore () ) ) ? "Errore Generico" : esito.getMessaggioErrore (),
										ricevuta.getCreditorReferenceId ()
						).executeUpdate ();
						if ( numeroGiorniKo > limiteNumGiorniReinvioReceipt ) {
							log.info ( methodName, "limite massimo giorni (" + limiteNumGiorniReinvioReceipt + ") raggiunto, reinvio non possible per iuv: " + ricevuta.getCreditorReferenceId () );
							System.out.println ( "limite massimo giorni (" + limiteNumGiorniReinvioReceipt + ") raggiunto, reinvio non possible per iuv: " + ricevuta.getCreditorReferenceId () );
							new AggiornaStatoInvioFruitoreReceipt ( "3T", "'" + ricevuta.getCreditorReferenceId () + "'" ).executeUpdate ();
						}
//                      Al momento non esiste la tabella, sara' da inserire 
						loggaInvioEsito ( Constants.ESITO_KO, !( "Reinvio Receipt: " + null ).equals ( esito.getMessaggioErrore () ) ? "Errore Generico" : esito.getMessaggioErrore (),
										ricevuta.getCreditorReferenceId  () );
					}
				} catch ( Exception e ) {
					log.error ( methodName, "Errore in fase di invio della Receipt con IUV: " + ricevuta.getCreditorReferenceId (), e );
					e.printStackTrace ();
				} finally {
					log.info ( methodName, "Fine trasmissione della Receipt con IUV: " + ricevuta.getCreditorReferenceId () );
					System.out.println ( "Fine trasmissione della Receipt con IUV: " + ricevuta.getCreditorReferenceId () );
					Thread.sleep ( sleepReinvio );
				}
			}
		}

		log.info ( methodName, "IUV DA CANCELLARE:" + xs.toXML ( iuvInviati ) );
		System.out.println ( "IUV DA CANCELLARE:" + xs.toXML ( iuvInviati ) );
		StringBuilder sb = new StringBuilder ();
		for ( String s : iuvInviati ) {
			sb.append ( "'" ).append ( s ).append ( "'" ).append ( "," );
		}
		String levaUltimaVisrgola = sb.toString ();
		if ( levaUltimaVisrgola.length () != 0 ) {
			levaUltimaVisrgola = levaUltimaVisrgola.substring ( 0, levaUltimaVisrgola.length () - 1 );

			new DeleteReceiptCodaInviateDAO ( levaUltimaVisrgola ).executeUpdate ();
		}
		//inserimentoInCodaReceiptStatoInvioFruitoreNull ( methodName, xs );TODO ASK
		// modifica dello stato della Receipt per gli iuv  che sono stati inseriti nella tabella delle code
		log.stopMethod ( methodName );
	}

	private int calcolaNumGiorniTentativiKo ( String dataPrioritariaReinvio, String applicazionePrioritariaReinvio, DatiReceiptNonInviata ricevuta ) {
		return ( StringUtils.isEmpty ( dataPrioritariaReinvio ) && StringUtils.isEmpty ( applicazionePrioritariaReinvio ) ) ?
						differenzaGiorni ( ricevuta ) + 1 :
						ricevuta.getCoda ().getNumGiorniTentativiKo ();
	}

	private int differenzaGiorni ( DatiReceiptNonInviata ricevuta ) {
		Date dataInizioTentativi = eliminaOreMinutiSecondi ( ricevuta.getCoda ().getDataInizioTentativi () );
		Date dataTentativi = eliminaOreMinutiSecondi ( ricevuta.getCoda ().getDataTentativi () );
		long differenza = dataTentativi.getTime () - dataInizioTentativi.getTime ();
		return new Long ( differenza / 1000 / 60 / 60 / 24 ).intValue ();
	}

	private Date eliminaOreMinutiSecondi ( Timestamp data ) {
		Date dataProva = new Date ( data.getTime () );
		String formattata = UtilDate.formatDate ( dataProva, "yyyy-MM-dd" );
		return UtilDate.parseDate ( formattata, "yyyy-MM-dd" );
	}

	private List<DatiReceiptNonInviata> ottieniEstraiRicevuteNonInviateReceiptDAO ( String limiteLetturaRecordReinvioReceipt, Integer limiteNumGiorniReinvioReceipt,
					String dataPrioritariaReinvio, String applicazionePrioritariaReinvio, byte[] sKey )
					throws Exception {

		if ( !StringUtils.isEmpty ( applicazionePrioritariaReinvio ) ) {
			Set<String> idapp = new HashSet<String> ( Arrays.asList ( applicazionePrioritariaReinvio.split ( "," ) ) );
			applicazionePrioritariaReinvio = ottieniListaInsertUpdateIn ( idapp );
			if ( !StringUtils.isEmpty ( dataPrioritariaReinvio ) ) {
				return new EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO ( limiteLetturaRecordReinvioReceipt, dataPrioritariaReinvio,
								applicazionePrioritariaReinvio, limiteNumGiorniReinvioReceipt ).executeQuery ();
			} else {
				return new EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO ( limiteLetturaRecordReinvioReceipt, applicazionePrioritariaReinvio,
								limiteNumGiorniReinvioReceipt ).executeQuery ();
			}
		} else if ( !StringUtils.isEmpty ( dataPrioritariaReinvio ) ) {
			return new EstraiRicevuteNonInviateDataPrioritariaReceiptDAO ( limiteLetturaRecordReinvioReceipt, dataPrioritariaReinvio, limiteNumGiorniReinvioReceipt,
							sKey ).executeQuery ();
		} else {
			return new EstraiRicevuteNonInviateReceiptDAO ( limiteLetturaRecordReinvioReceipt, limiteNumGiorniReinvioReceipt, sKey ).executeQuery ();
		}
	}

	private String ottieniListaInsertUpdateIn ( Set<String> iuvInviati ) {
		StringBuilder sb = new StringBuilder ();
		for ( String s : iuvInviati ) {
			sb.append ( "'" ).append ( s ).append ( "'" ).append ( "," );
		}
		String levaUltimaVisrgola = sb.toString ();
		if ( levaUltimaVisrgola.length () != 0 ) {
			levaUltimaVisrgola = levaUltimaVisrgola.substring ( 0, levaUltimaVisrgola.length () - 1 );
		}
		return levaUltimaVisrgola;
	}


	private EsitoRiceviEsito spedisciReceiptAlFruitore ( DatiReceiptNonInviata receipt, Map<String, String> mappaAcf ) {

		log.startMethod ( "[ReinvioReceipt::spedisciReceiptAlFruitore]" );

		try {
			Map<String, Object> props = new HashMap<String, Object> ();
			props.put ( "mtom-enabled", Boolean.TRUE );
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean ();
			factory.setProperties ( props );
			factory.getInInterceptors ().add ( new LoggingInInterceptor () );
			factory.getOutInterceptors ().add ( new LoggingOutInterceptor () );
			factory.setServiceClass ( Serviziorissrvspc.class );
			factory.setAddress ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE ) );
			Serviziorissrvspc Serviziorissrvspc = (Serviziorissrvspc) factory.create ();
			
			ParametriRiceviEsito parametriRiceviEsito = new ParametriRiceviEsito ();

	        SimpleDateFormat sdfTimestamp = new SimpleDateFormat ("ddMMyyyy-hh:mm:ss:ms");
	        String timestamp = sdfTimestamp.format ( new Date () );
	        
	        parametriRiceviEsito.setApplicationId ( receipt.getApplicationId () );
	        parametriRiceviEsito.setTransactionId ( receipt.getTransactionId () );
//	        GregorianCalendar gregory = new GregorianCalendar ();
//	        gregory.setTime ( receipt.getDataOraRicezione () );
//	        XMLGregorianCalendar calendar;
//	        try {
//	            calendar = DatatypeFactory.newInstance ()
//	                            .newXMLGregorianCalendar (
//	                                gregory );
//	            parametriRiceviEsito.setDataOraMsgRicevuta ( calendar );
//	        } catch ( DatatypeConfigurationException e ) {
//	            log.warn ( "Errore in fase di conversione della now per il campo DataOraMsgRicevuta. ", e);
//	        }
	        
	        parametriRiceviEsito.setDataOraMsgRicevuta ( UtilDate.convertiTimestampInXmlGregorianCalendar (receipt.getDataOraRicezione ()) );
	        parametriRiceviEsito.setIuv ( receipt.getCreditorReferenceId () );
	        parametriRiceviEsito.setCodEsitoPagamento ( Constants.CODICE_ESITO_PAGAMENTO_MOD_3 );
	        parametriRiceviEsito.setDescEsitoPagamento ( Constants.DESC_PAGAMENTO_MOD_3 );
	        parametriRiceviEsito.setTimestamp ( timestamp );
	        //algoritmo valorizzazione idmsg
	        final SecureRandom random = new SecureRandom ();
	        String stringa35Random = new BigInteger ( 160, random ).toString ( 32 );
	        if ( stringa35Random.length () > 32 ) {
	            stringa35Random = stringa35Random.substring ( 32 );
	        }
	        stringa35Random = "MDP" + stringa35Random;
	        parametriRiceviEsito.setIdMsgRicevuta ( stringa35Random );
	        
	        parametriRiceviEsito.setMac (generaMacRiceviEsito (
	            receipt.getApplicationId (), 
	            mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE ),
	            receipt.getCreditorReferenceId (),
	            timestamp, 
	            stringa35Random )) ;
	        
	        parametriRiceviEsito.setIdentificativoUnivocoRiscossione (receipt.getReceiptId () );
	        parametriRiceviEsito.setImportoPagato ( receipt.getPaymentAmount () );
	        

            PaSendRTReq richiesta2 =  JAXB.unmarshal ( new ByteArrayInputStream (  org.apache.ws.security.util.Base64.decode ( new String ( receipt.getXmlReceip () ) )  ), PaSendRTReq.class );
            
            
           
           List<CtMapEntry> metadata=   richiesta2.getReceipt ().getMetadata ().getMapEntry ();
           
           Map<String, String> map = new HashMap<String, String> ();
           if (!CollectionUtils.isEmpty ( metadata ))
           {
               for (CtMapEntry mapEntry :metadata)
               {
                   map.put ( mapEntry.getKey (), mapEntry.getValue () );
                   
               }  
           }
           
	        // id_modalita_ricezione_esito corrispondente a ‘receipt da pagoPA’ se il campo con chiave = FlagReceipt della ElencoParametriAggiuntivi della riceviEsito ha come Valore = Ture
	        map.put ( Constants.FLAG_RECEIPT, "true" );
	        
	        List<ChiaveValore> listChiaveVal = new ArrayList<ChiaveValore> ();
	        for ( Map.Entry<String, String> entry: map.entrySet () ) {
	            ChiaveValore c = new ChiaveValore ();
	            c.setChiave ( entry.getKey () );
	            c.setValore ( entry.getValue () );
	            listChiaveVal.add ( c );
	        }
	        if ( null != richiesta2.getReceipt ().getPaymentDateTime () ) {
	            ChiaveValore c = new ChiaveValore ();
	            c.setChiave ( "paymentDateTime" );
	            c.setValore ( richiesta2.getReceipt ().getPaymentDateTime ().toString () );
	            listChiaveVal.add ( c );
	        }
	        parametriRiceviEsito.setElencoParametriAggiuntivi ( listChiaveVal );
	        parametriRiceviEsito.setIdentificativoPSP ( receipt.getIdPsp () );
	        parametriRiceviEsito.setDenominazionePSP ( receipt.getPspCompanyName () );
	        
	        if ( null != receipt.getPaymentDateTime() ) {
//	            p.setDataEsitoSingoloPagamento ( paSendRTReq.getReceipt ().getApplicationDate () );
	            parametriRiceviEsito.setDataEsitoSingoloPagamento (  UtilDate.convertiTimestampInXmlGregorianCalendar (receipt.getPaymentDateTime()) );
	        }
	          
			
			
	        
			EsitoRiceviEsito esitoRiceviEsio = Serviziorissrvspc.riceviEsito ( parametriRiceviEsito );
			// Se il fruotire risponde pagamento duplicato o annullato considero la Receipt già inviata.
			if ( !StringUtils.isEmpty ( esitoRiceviEsio.getCodiceErrore () ) && ( Constants.PAA_PAGAMENTO_DUPLICATO.equals ( esitoRiceviEsio.getCodiceErrore () ) ||
							Constants.PAA_PAGAMENTO_ANNULLATO.equals ( esitoRiceviEsio.getCodiceErrore () ) ) ) {
				esitoRiceviEsio = new EsitoRiceviEsito ();
				esitoRiceviEsio.setEsito ( Constants.ESITO_OK );
				return esitoRiceviEsio;
			}
			return esitoRiceviEsio;
		} catch ( Throwable t ) {
			log.error ( "ERRORE DURANTE L'INVIO DELLA RECEIPT AL FRUITORE: ", t );
			t.printStackTrace ();
			EsitoRiceviEsito esito = new EsitoRiceviEsito ();
			esito.setEsito ( Constants.ESITO_KO );
			esito.setCodiceErrore ( "ERRORE_GENERICO" );
			esito.setMessaggioErrore ( t.getMessage () );
			return esito;
		} finally {
			// fine misurazione
			log.stopMethod ( "[ReinvioReceipt::spedisciReceiptAlFruitore]" );
		}
	}

	
	
	 public static String generaMacRiceviEsito ( String applicationId, String passphrase, String identificativoUnivocoVersamento, String timestamp,
	        String idMsgRicevuta ) {
	        String sToDigest = passphrase + "%%%%" + applicationId + identificativoUnivocoVersamento + idMsgRicevuta + timestamp + "%%%%" + passphrase;
	        byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
	        String mac = Base64.encodeBase64String ( bMac );
	        return mac.substring ( 0, 35 );
	        
	    }

	private void loggaInvioEsito ( String esito, String messErrore, String iuv ) {
		LoggingReceipt loggingReceipt = new LoggingReceipt ();
		loggingReceipt.setDataOraInvio ( new Timestamp ( ( new Date () ).getTime () ) );
		loggingReceipt.setErrori ( org.apache.commons.lang.StringUtils.substring ( messErrore, 0, 255 ) );
		loggingReceipt.setEsito ( esito );
		loggingReceipt.setIstitutoMittente ( "-" );
		loggingReceipt.setIuv ( iuv );
		try {
			new InserisciLoggingReceiptDAO ( loggingReceipt ).executeUpdate ();
		} catch ( Exception e ) {
			log.error ( "loggaInvioEsito", "Errore in fase di registrazione dell'esito sulla tabella logging_receipt! ", e );
			e.printStackTrace ();
		}
	}

}
