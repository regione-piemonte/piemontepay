/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.clientmod3.EsitoRiceviEsito;
import it.csi.mdp.clientmod3.ParametriRiceviEsito;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaFlussoSingoloPagamentoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaMDPGetPaymentDAO;
import it.csi.mdp.mdpetl.integration.util.dao.AggiornaStatoRPTInviataDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiConfigurazioneDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiFlussoSingoloPagamentoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiFlussoSingoloPagamentoSenzaReceiptInStatoInviatoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ParametriNodoSpcDAO;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.UtilDate;
import it.csi.mdp.utility.CostantiNodoSpc;

public class InvioEsitoPagCodZeroSenzaRT extends InvioEsitoPag {

	private static final LogUtil log = new LogUtil ( InvioEsitoPagCodZeroSenzaRT.class );

	private final SecureRandom random = new SecureRandom ();

	public void elaboraInvioEsito ( byte [] key ) throws Exception {
		String methodName = "elaboraInvioEsito";
		log.startMethod ( methodName );

		String limiteInferioreDataEsitoSingoloPagamento = new EstraiConfigurazioneDAO ( Constants.LIMITE_INFERIORE_DATA_ESITO_SINGOLO_PAGAMENTO ).executeQuery ().getValue ();
		log.info ( methodName, "limiteInferioreDataEsitoSingoloPagamento : " + limiteInferioreDataEsitoSingoloPagamento );
		System.out.println ( "limiteInferioreDataEsitoSingoloPagamento : " + limiteInferioreDataEsitoSingoloPagamento );
		String limiteLetturaPagCodZeroSenzaRT = new EstraiConfigurazioneDAO ( Constants.LIMITE_LETTURA_PAG_COD_ZERO_SENZA_RT ).executeQuery ().getValue ();
		log.info ( methodName, "limiteLetturaPagCodZeroSenzaRT : " + limiteLetturaPagCodZeroSenzaRT );
		System.out.println ( "limiteLetturaPagCodZeroSenzaRT : " + limiteLetturaPagCodZeroSenzaRT );
		String limiteApplicationPagCodZeroSenzaRT = new EstraiConfigurazioneDAO ( Constants.LIMITE_APPLICATION_PAG_COD_ZERO_SENZA_RT ).executeQuery ().getValue ();
		log.info ( methodName, "limiteApplicationPagCodZeroSenzaRT : " + limiteApplicationPagCodZeroSenzaRT );
		System.out.println ( "limiteApplicationPagCodZeroSenzaRT : " + limiteApplicationPagCodZeroSenzaRT );
		String codiceTiplogiaPagCodZeroSenzaRT = new EstraiConfigurazioneDAO ( Constants.COD_TIPOLOGIA_PAG_PAG_COD_ZERO_SENZA_RT ).executeQuery ().getValue ();
		log.info ( methodName, "codiceTiplogiaPagCodZeroSenzaRT : " + codiceTiplogiaPagCodZeroSenzaRT );
		System.out.println ( "codiceTiplogiaPagCodZeroSenzaRT : " + codiceTiplogiaPagCodZeroSenzaRT );
		if ( StringUtils.isEmpty ( codiceTiplogiaPagCodZeroSenzaRT ) ) {
			codiceTiplogiaPagCodZeroSenzaRT = "0";
		}
		String limiteApplicazionePagCodZeroSenzaRT = new EstraiConfigurazioneDAO ( Constants.LIMITE_APPLICATION_PAG_COD_ZERO_SENZA_RT ).executeQuery ().getValue ();
		log.info ( methodName, "limiteApplicazionePagCodZeroSenzaRT : " + limiteApplicazionePagCodZeroSenzaRT );
		System.out.println ( "limiteApplicazionePagCodZeroSenzaRT : " + limiteApplicazionePagCodZeroSenzaRT );

		List<FlussoSingoloPagamento> elencoPagamenti = estraiPagamenti ( limiteInferioreDataEsitoSingoloPagamento, limiteLetturaPagCodZeroSenzaRT, limiteApplicationPagCodZeroSenzaRT, codiceTiplogiaPagCodZeroSenzaRT);
		List<FlussoSingoloPagamento> elPagamRicEsito = estraiPagRicEsi ( limiteInferioreDataEsitoSingoloPagamento, limiteLetturaPagCodZeroSenzaRT, limiteApplicazionePagCodZeroSenzaRT );
		elencoPagamenti.addAll ( elPagamRicEsito );

		if ( !elencoPagamenti.isEmpty () ) {
			log.info ( methodName, "Trovati N: " + elencoPagamenti.size () + " Pagamenti da inviare" );
			System.out.println ( "Trovati N: " + elencoPagamenti.size () + " Pagamenti da inviare" );
			for ( FlussoSingoloPagamento flussoSingoloPagamento : elencoPagamenti ) {
				log.info ( methodName, "Elaborazione in corso di IUV: " + flussoSingoloPagamento.getIuv () + " IdRPT: " + flussoSingoloPagamento.getIdRPT ()
								+ " IdFlusso: " + flussoSingoloPagamento.getIdFlusso () + " IdGetpayment: " + flussoSingoloPagamento.getIdGetpayment () );
				System.out.println ( "Elaborazione in corso di IUV: " + flussoSingoloPagamento.getIuv () + " IdRPT: " + flussoSingoloPagamento.getIdRPT ()
								+ " IdFlusso: " + flussoSingoloPagamento.getIdFlusso () + " IdGetpayment: " + flussoSingoloPagamento.getIdGetpayment ());
				try {
					if ( Constants.ESITO_OK.equals ( inviaRTAlFruitore ( key, flussoSingoloPagamento ) ) ) {
                        if ( null != flussoSingoloPagamento.getIdRPT () && flussoSingoloPagamento.getIdRPT ().compareTo ( 0 )!= 0) {
                            new AggiornaStatoRPTInviataDAO ( flussoSingoloPagamento.getIdRPT () ).executeUpdate ();
                        }
                        if ( null != flussoSingoloPagamento.getIdGetpayment ()  && flussoSingoloPagamento.getIdGetpayment ().compareTo ( 0 )!= 0) {
                            new AggiornaMDPGetPaymentDAO ( flussoSingoloPagamento.getIdGetpayment () ).executeUpdate ();
                        }
						new AggiornaFlussoSingoloPagamentoDAO ( flussoSingoloPagamento.getId () ).executeUpdate ();
					}
				} catch ( Exception e ) {
					log.error ( methodName, "Errore durante l'invio RT al fruitore per singolo pagamento", e );
				} finally {
					Thread.sleep ( 1000 );
				}
			}
		} else {
			log.info ( methodName, "Nessun pagamento da inviare trovato." );
			System.out.println ( "Nessun pagamento da inviare trovato." );
		}
    }

    private List<FlussoSingoloPagamento> estraiPagamenti ( String dataLimite, String limit, String limiteApplication, String codiceTiplogiaPagCodZeroSenzaRT )
					throws Exception {
        if ( StringUtils.isNotBlank ( limiteApplication ) ) {
			Set<String> idapp = new HashSet<String> ( Arrays.asList ( limiteApplication.split ( "," ) ) );
            limiteApplication = ottieniListaInsertUpdateIn ( idapp );
        }
        return new EstraiFlussoSingoloPagamentoDAO ( dataLimite, limit, limiteApplication, codiceTiplogiaPagCodZeroSenzaRT).executeQuery ();
    }

	private List<FlussoSingoloPagamento> estraiPagRicEsi ( String limiteInferioreDataEsitoSingoloPagamento, String limiteLetturaPagCodZeroSenzaRT,
					String limiteApplicazionePagCodZeroSenzaRT ) throws Exception {
		if ( StringUtils.isNotBlank ( limiteApplicazionePagCodZeroSenzaRT ) ) {
			Set<String> idapp = new HashSet<String> ( Arrays.asList ( limiteApplicazionePagCodZeroSenzaRT.split ( "," ) ) );
			limiteApplicazionePagCodZeroSenzaRT = ottieniListaInsertUpdateIn ( idapp );
		}
		return new EstraiFlussoSingoloPagamentoSenzaReceiptInStatoInviatoDAO ( limiteInferioreDataEsitoSingoloPagamento, limiteLetturaPagCodZeroSenzaRT,
						limiteApplicazionePagCodZeroSenzaRT ).executeQuery ();
	}

    private String inviaRTAlFruitore ( byte [] key, FlussoSingoloPagamento flusso ) throws Exception {
        String ret = Constants.ESITO_KO;
        String timestamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyyy-hh:mm:ss:ms");
        timestamp = sdf.format ( new Date () );
        String applicationId = flusso.getApplicationId ();
        if ( null == flusso.getApplicationId () ) {
            IuvOttico iuvOttico = new EstraiApplicationIdDaIuvDAO ( flusso.getIuv () ).executeQuery ();
            applicationId = iuvOttico.getApplicationId ();
        }
        Map<String, String> mappaAppCustomFields = null;
        XStream xs = new XStream ();
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean ();
        ParametriRiceviEsito parametriRiceviRT = new ParametriRiceviEsito ();
        String stringa35Random = new BigInteger ( 160, random ).toString ( 32 );
        if ( stringa35Random.length () > 32 ) {
            stringa35Random = stringa35Random.substring ( 32 );
        }
        stringa35Random = "MDP" + stringa35Random;
        if ( applicationId == null ) {
            log.warn ( "inviaRTAlFruitore",
                "Impossibile inviare il Flusso per IUV " + flusso.getIuv () + " Al fruitore" );
            System.out.println ( "Impossibile inviare il Flusso per IUV " + flusso.getIuv () + " Al fruitore" );
            return ret;
        } else {
            mappaAppCustomFields = new ParametriNodoSpcDAO ( key ).getMappaApplicationCustomFieldsEnabled ( applicationId );
            String mapStr = xs.toXML ( mappaAppCustomFields );
            log.info ( "inviaRTAlFruitore", mapStr );
            System.out.println ( mapStr );
            parametriRiceviRT.setMac ( generaMacVersamento ( mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE ), applicationId,
                flusso.getIuv (), timestamp, stringa35Random ) );
            factory.setAddress ( mappaAppCustomFields.get ( CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE ) );
        }

        factory.getInInterceptors ().add ( new LoggingInInterceptor () );
        factory.getOutInterceptors ().add ( new LoggingOutInterceptor () );
        factory.setServiceClass ( Serviziorissrvspc.class );

        Serviziorissrvspc iPagNodo = (Serviziorissrvspc) factory.create ();

        parametriRiceviRT.setApplicationId ( applicationId );
        parametriRiceviRT.setCodEsitoPagamento ( "9" );
        if(null != flusso.getDataEsitoSingoloPagamento ()) {
            parametriRiceviRT.setDataEsitoSingoloPagamento ( UtilDate.convertiTimestampInXmlGregorianCalendar ( flusso.getDataEsitoSingoloPagamento () ) );
            parametriRiceviRT.setDataOraMsgRicevuta ( UtilDate.convertiTimestampInXmlGregorianCalendar ( flusso.getDataEsitoSingoloPagamento () ) );
        }
        parametriRiceviRT.setDescEsitoPagamento ( "Pagamento eseguito in assenza di RT" );
        parametriRiceviRT.setIuv ( flusso.getIuv () );

        parametriRiceviRT.setIdMsgRicevuta ( stringa35Random );
        parametriRiceviRT.setTimestamp ( timestamp );
        parametriRiceviRT.setIdentificativoUnivocoRiscossione ( flusso.getIdentificativoUnivocoRiscossione () );
        parametriRiceviRT.setImportoPagato ( flusso.getSingoloImportoPagato () );
        parametriRiceviRT.setRtPresente ( false );
        parametriRiceviRT.setIdentificativoPSP ( flusso.getIdentificativoPsp () );
        parametriRiceviRT.setDenominazionePSP ( flusso.getDenominazioneMittente () );
        parametriRiceviRT.setTransactionId ( flusso.getTransactionId () );
        EsitoRiceviEsito esito = null;
        try {
            System.out.println ( "Inizio invio Esito" );
            log.info ( "inviaRTAlFruitore", "Inizio invio Esito" );
            System.out.println ( "Request riceviEsito: " + xs.toXML ( parametriRiceviRT ) );
            log.info ( "inviaRTAlFruitore", "Request riceviEsito: " + xs.toXML ( parametriRiceviRT ) );
            esito = iPagNodo.riceviEsito ( parametriRiceviRT );
            System.out.println ( "Fine invio Esito" );
            System.out.println ( "Responde riceviEsito: " + xs.toXML ( esito ) );
            log.info ( "inviaRTAlFruitore", "Fine invio Esito" );
            log.info ( "inviaRTAlFruitore", "Response riceviEsito: " + xs.toXML ( esito ) );
            ret = esito != null && ( Constants.ESITO_OK.equals ( esito.getEsito () )
                || ( Constants.ESITO_KO.equals ( esito.getEsito () ) && Constants.PAA_PAGAMENTO_DUPLICATO.equals ( esito.getCodiceErrore () ) ) ) ? Constants.ESITO_OK : Constants.ESITO_KO;
        } catch ( Throwable e ) {
            ret = Constants.ESITO_KO;
            log.error ( "inviaRTAlFruitore", "Errore in fase di invio dell'esito! ", e);
            e.printStackTrace();
        }

        loggaInvioEsito ( flusso, ret, null != esito? "Rispota del gestionale: " + esito.getMessaggioErrore (): "Errore in fase di invio dell'esito!");
        return ret;
    }

	private String generaMacVersamento ( String passphrase, String applicationId, String identificativoUnivocoVersamento, String timestamp,
		String stringa35Random ) {
		String sToDigest = passphrase + "%%%%" + applicationId + identificativoUnivocoVersamento + stringa35Random + timestamp + "%%%%" + passphrase;
		log.debug ( "CALOCLO MAC PER CHIEDI DATI PAGAMENTO: ", sToDigest );
		byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
		String mac = Base64.encodeBase64String ( bMac );
		mac = mac.substring ( 0, 35 );
		return mac;
	}

	private String ottieniListaInsertUpdateIn ( Set<String> application ) {
		StringBuilder sb = new StringBuilder ();
		for ( String s: application ) {
			sb.append ( "'" ).append ( s ).append ( "'" ).append ( "," );
		}
		String levaUltimaVirgola = sb.toString ();
		if ( levaUltimaVirgola.length () != 0 ) {
			levaUltimaVirgola = levaUltimaVirgola.substring ( 0, levaUltimaVirgola.length () - 1 );
		}
		return levaUltimaVirgola;
	}
}
