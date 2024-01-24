/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.application;

import com.thoughtworks.xstream.XStream;
import it.csi.mdp.clientmod3.EsitoRiceviEsito;
import it.csi.mdp.clientmod3.ParametriRiceviEsito;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.integration.util.dao.*;
import it.csi.mdp.mdpetl.integration.util.dao.flussosingolopagamento.UpdateFlussoSingoloPagamentoDAO;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.UtilDate;
import it.csi.mdp.utility.CostantiNodoSpc;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */

public class InvioEsitoPagCodNove extends InvioEsitoPag {

	private static final LogUtil log = new LogUtil ( InvioEsitoPagCodNove.class );

	private final SecureRandom random = new SecureRandom ();

	public void elaboraInvioEsito ( byte [] key ) throws Exception {
		String methodName = "elaboraInvioEsito";
		log.startMethod ( methodName );

		String codApplicazioniPagCod9 = new EstraiConfigurazioneDAO ( Constants.COD_APPLICAZIONI_PAG_COD_NOVE ).executeQuery ().getValue ();
		log.info ( methodName, "codApplicazioniPagCod9 : " + codApplicazioniPagCod9 );
		System.out.println ( "codApplicazioniPagCod9 : " + codApplicazioniPagCod9 );
		String codiceTipologiaPagCod9 = new EstraiConfigurazioneDAO ( Constants.CODICE_TIPOLOGIA_PAG_COD_NOVE ).executeQuery ().getValue ();
		log.info ( methodName, "codiceTipologiaPagCod9 : " + codiceTipologiaPagCod9 );
		System.out.println ( "codiceTipologiaPagCod9 : " + codiceTipologiaPagCod9 );
		String limiteInferioreDataEsitoCod9 = new EstraiConfigurazioneDAO ( Constants.LIMITE_INFERIORE_DATA_ESITO_COD_NOVE ).executeQuery ().getValue ();
		log.info ( methodName, "limiteInferioreDataEsitoCod9 : " + limiteInferioreDataEsitoCod9 );
		System.out.println ( "limiteInferioreDataEsitoCod9 : " + limiteInferioreDataEsitoCod9 );
		String limiteLetturaPagCod9 = new EstraiConfigurazioneDAO ( Constants.LIMITE_LETTURA_PAG_COD_NOVE ).executeQuery ().getValue ();
		log.info ( methodName, "limiteLetturaPagCod9 : " + limiteLetturaPagCod9 );
		System.out.println ( "limiteLetturaPagCod9 : " + limiteLetturaPagCod9 );

		List<FlussoSingoloPagamento> elencoPagamenti
			= estraiPagamenti ( codApplicazioniPagCod9, codiceTipologiaPagCod9, limiteInferioreDataEsitoCod9, limiteLetturaPagCod9 );

		if ( null != elencoPagamenti && !elencoPagamenti.isEmpty () ) {

			log.info ( methodName, "Trovati N: " + elencoPagamenti.size () + " Pagamenti cod 9 da reinviare" );
			System.out.println ( "Trovati N: " + elencoPagamenti.size () + " Pagamenti cod 9 da reinviare" );
			for ( FlussoSingoloPagamento flussoSingoloPagamento: elencoPagamenti ) {
				log.info ( methodName, "Elaborazione in corso di IUV: " + flussoSingoloPagamento.getIuv () + " IdRPT: " + flussoSingoloPagamento.getIdRPT ()
					+ " IdFlusso: " + flussoSingoloPagamento.getIdFlusso () );
				System.out.println ( "Elaborazione in corso di IUV: " + flussoSingoloPagamento.getIuv () + " IdRPT: " + flussoSingoloPagamento.getIdRPT ()
					+ " IdFlusso: " + flussoSingoloPagamento.getIdFlusso () );
				try {
					// AO aggiornato nome metodo inviaEsitoAlFruitore anzichè inviaRTAlFruitore)
					if (Constants.ESITO_OK.equals(inviaEsitoAlFruitore(key, flussoSingoloPagamento))) {
						// AO aggiornamento diverso in base a flussoSingoloPagamento.getIdRPT ()
						if (flussoSingoloPagamento.getIdRPT() != null && flussoSingoloPagamento.getTipoRPT().startsWith("RPT_")) {
							new AggiornaStatoRPTInviataDAO(
									flussoSingoloPagamento.getIdRPT()).executeUpdate();
						} else if (flussoSingoloPagamento.getIdRPT() != null && flussoSingoloPagamento.getTipoRPT().startsWith("RECEIPT_")) {
							new AggiornaStatoReceiptInviataDAO(
									flussoSingoloPagamento.getIdRPT()).executeUpdate();
						}
						// AO in ogni caso aggiornare lo stato del flusso con OK
						new AggiornaStatoFlussoSingoloPagamentoDAO(
								flussoSingoloPagamento.getIdFlusso(), flussoSingoloPagamento.getIuv()).executeUpdate();
					}
				} catch ( Exception e ) {
					log.error ( methodName, "Errore durante l'invio RT al fruitore per singolo pagamento", e );
					System.out.println ( "Errore durante l'invio RT al fruitore per singolo pagamento." );
				} finally {
					Thread.sleep ( 1000 );
				}
			}
		} else {
			log.info ( methodName, "Nessun pagamento cod 9 da reinviare trovato." );
			System.out.println ( "Nessun pagamento cod 9 da reinviare trovato." );
		}
	}

	private List<FlussoSingoloPagamento> estraiPagamenti ( String codApplicazioniPagCod9, String codiceTipologiaPagCod9, String limiteInferioreDataEsitoCod9,
		String limiteLetturaPagCod9 ) throws Exception {
		if ( StringUtils.isNotBlank ( codApplicazioniPagCod9 ) ) {
			Set<String> idapp = new HashSet<String> ( Arrays.asList ( codApplicazioniPagCod9.split ( "," ) ) );
			codApplicazioniPagCod9 = ottieniListaInsertUpdateIn ( idapp );
		}
		return new EstraiFlussoSingoloPagamentoPerCodiciNoveDAO ( codApplicazioniPagCod9, codiceTipologiaPagCod9, limiteInferioreDataEsitoCod9,
			limiteLetturaPagCod9 )
			.executeQuery ();
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

	private String inviaEsitoAlFruitore ( byte [] key, FlussoSingoloPagamento flusso ) throws Exception {
		String ret = Constants.ESITO_KO;
		String timestamp;
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
			log.warn ( "inviaEsitoAlFruitore",
				"Impossibile inviare il Flusso per IUV " + flusso.getIuv () + " Al fruitore" );
			System.out.println ( "Impossibile inviare il Flusso per IUV " + flusso.getIuv () + " Al fruitore" );
			return ret;
		} else {
			mappaAppCustomFields = new ParametriNodoSpcDAO ( key ).getMappaApplicationCustomFieldsEnabled ( applicationId );
			String mapStr = xs.toXML ( mappaAppCustomFields );
			log.info ( "inviaEsitoAlFruitore", mapStr );
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
		if ( null != flusso.getDataEsitoSingoloPagamento () ) {
			parametriRiceviRT.setDataEsitoSingoloPagamento ( UtilDate.convertTimestampToXmlGregorianCalendar ( flusso.getDataEsitoSingoloPagamento () ) );
	         parametriRiceviRT.setDataOraMsgRicevuta ( UtilDate.convertiTimestampInXmlGregorianCalendar ( flusso.getDataEsitoSingoloPagamento () ) );
		}
		parametriRiceviRT.setDescEsitoPagamento ( "Pagamento codice 9" );
		parametriRiceviRT.setIuv ( flusso.getIuv () );

		parametriRiceviRT.setIdMsgRicevuta ( stringa35Random );
		parametriRiceviRT.setTimestamp ( timestamp );
		parametriRiceviRT.setIdentificativoUnivocoRiscossione ( flusso.getIdentificativoUnivocoRiscossione () );
		parametriRiceviRT.setImportoPagato ( flusso.getSingoloImportoPagato () );
		parametriRiceviRT.setRtPresente ( false );
		parametriRiceviRT.setIdentificativoPSP ( flusso.getIdentificativoPsp () );
		parametriRiceviRT.setDenominazionePSP ( flusso.getDenominazioneMittente () );
		parametriRiceviRT.setTransactionId ( flusso.getTransactionId () );
		EsitoRiceviEsito esitoRiceviEsito = null;
		try {
			System.out.println ( "Inizio invio Esito" );
			log.info ( "inviaEsitoAlFruitore", "Inizio invio Esito" );
			System.out.println ( "Request riceviEsito: " + xs.toXML ( parametriRiceviRT ) );
			log.info ( "inviaEsitoAlFruitore", "Request riceviEsito: " + xs.toXML ( parametriRiceviRT ) );
			esitoRiceviEsito = iPagNodo.riceviEsito ( parametriRiceviRT );
			System.out.println ( "Fine invio Esito" );
			System.out.println ( "Responde riceviEsito: " + xs.toXML ( esitoRiceviEsito ) );
			log.info ( "inviaEsitoAlFruitore", "Fine invio Esito" );
			log.info ( "inviaEsitoAlFruitore", "Response riceviEsito: " + xs.toXML ( esitoRiceviEsito ) );
			if ( null != esitoRiceviEsito ) {
				String esito = esitoRiceviEsito != null && ( Constants.ESITO_OK.equals ( esitoRiceviEsito.getEsito () )
				                || ( Constants.ESITO_KO.equals ( esitoRiceviEsito.getEsito () ) && Constants.PAA_PAGAMENTO_DUPLICATO.equals ( esitoRiceviEsito.getCodiceErrore () ) ) ) ? Constants.ESITO_OK : Constants.ESITO_KO;
				String codiceErrore = esitoRiceviEsito.getCodiceErrore ();
				String messaggioErrore = esitoRiceviEsito.getMessaggioErrore ();
				flusso.setEsitoUltimoInvioAFruitore ( esito );
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
				flusso.setMsgUltimoEsitoInvioAFruitore ( StringUtils.substring ( msg, 0, 250 ) );
			}
		} catch ( Throwable e ) {
			log.error ( "inviaEsitoAlFruitore", "Errore durante l'invio dell'esito", e );
			flusso.setEsitoUltimoInvioAFruitore ( "KO" );
			flusso.setMsgUltimoEsitoInvioAFruitore (
							StringUtils.substring ( "Errore durante l'invio dell'esito - " + ExceptionUtils.getStackTrace ( e ), 0, 250 ) );
		}
		flusso.setDataUltimoInvioAFruitore ( new Timestamp ( System.currentTimeMillis () ) );

//		new InserisciFlussoSingoloPagamentoDAO(flusso).executeUpdate(); <-- sbagliato, creava un record di troppo
		new UpdateFlussoSingoloPagamentoDAO ( flusso ).executeUpdate ();
		loggaInvioEsito ( flusso, ret, null != esitoRiceviEsito ? "Rispota del gestionale: " + esitoRiceviEsito.getMessaggioErrore () : "Errore in fase di invio dell'esito!" );
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
}
