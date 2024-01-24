/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpnodospcservices.business.pagrt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.ejb.CreateException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.soap.util.mime.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import it.csi.mdp.adapters.business.CoreUtilities;
import it.csi.mdp.adapters.business.PersistenzaDatiNodoUtility;
import it.csi.mdp.clientmod3.ChiaveValore;
import it.csi.mdp.clientmod3.EsitoRiceviRT;
import it.csi.mdp.clientmod3.ParametriRiceviRT;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.DatiSingolaRevoca;
import it.csi.mdp.core.business.dto.DatiSingoloEsito;
import it.csi.mdp.core.business.dto.DettaglioFruitore;
import it.csi.mdp.core.business.dto.ER;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.StatiRPTEnum;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.Payment;
import it.csi.mdp.core.business.paymentmanager.PaymentHome;
import it.csi.mdp.core.interfacecxf.MissingParameterException;
import it.csi.mdp.dosignclient.DosignException_Exception;
import it.csi.mdp.dosignclient.DosignSignatureValidation;
import it.csi.mdp.dosignclient.VerifyDto;
import it.csi.mdp.dosignclient.VerifyReportDto;
import it.csi.mdp.generatedvo.pagamenti.CtRicevutaTelematica;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaRispostaRevoca;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.NodoInviaRispostaRevocaRisposta;
import it.csi.mdp.mdpnodospcclient.integration.service.pagtelematicirpt.PagamentiTelematiciRPT;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.EsitoPaaInviaRT;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.FaultBean;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaRT;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaRTRisposta;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PagamentiTelematiciRT;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.TipoInviaEsitoStornoRisposta;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.TipoInviaRichiestaRevocaRisposta;
import it.csi.mdp.mdpnodospcservices.util.Constants;
import it.csi.mdp.mdpnodospcservices.util.FaultBeanEnum;
import it.csi.mdp.mdpnodospcservices.util.LoggerUtil;
import it.csi.mdp.mdpnodospcservices.util.Utils;
import it.csi.mdp.utility.CostantiNodoSpc;
import it.csi.util.performance.StopWatch;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.CtDatiEsitoRevoca;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.CtDatiSingolaRevoca;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.CtDatiSingoloEsitoRevoca;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.CtEsitoRevoca;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.CtIstitutoAttestante;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.CtRichiestaRevoca;
import it.gov.digitpa.schemas._2011.pagamenti.revoche.ObjectFactory;


@WebService ( serviceName = "PagamentiTelematiciRT", portName = "PPTPort",
                endpointInterface = "it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PagamentiTelematiciRT" )
public class PagamentiTelematiciRTImpl implements PagamentiTelematiciRT {
	
	@Autowired
    private Environment environment;

    @Override
	@SOAPBinding ( parameterStyle = ParameterStyle.BARE )
    @WebResult ( name = "paaInviaRTRisposta", targetNamespace = "http://ws.pagamenti.telematici.gov/", partName = "bodyrisposta" )
    @WebMethod ( action = "paaInviaRT" )
    public PaaInviaRTRisposta paaInviaRT (
        @WebParam ( partName = "bodyrichiesta", name = "paaInviaRT", targetNamespace = "http://ws.pagamenti.telematici.gov/" ) PaaInviaRT bodyrichiesta,
        @WebParam ( partName = "header", name = "intestazionePPT", targetNamespace = "http://ws.pagamenti.telematici.gov/ppthead",
                        header = true ) it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.IntestazionePPT header ) {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        LoggerUtil.begin ();

        LoggerUtil.begin ( "ANALISI-RICERCA" );

        PaaInviaRTRisposta risposta = new PaaInviaRTRisposta ();
        EsitoPaaInviaRT esito = new EsitoPaaInviaRT ();
        esito.setEsito ( "KO" );

        Timestamp commonInsertDate = new Timestamp ( new Date ().getTime () );

        String faultString = "";

        RPT richiestaTrovata = null;
        CtRicevutaTelematica rt = null;
        Payment p = null;
        Map<String, String> mappaAcf = null;
				String idPsp = null;

        /*
         * Ricezione della RT da parte del nodo dei pagamenti
         */
        try {

            p = reperisciPaymentInterface ();

            Unmarshaller unmarshaller = creaUnMarshallerRT ();

            byte [] rtData = bodyrichiesta.getRt ();

            if ( !StringUtils.isEmpty ( bodyrichiesta.getTipoFirma () ) && !"0".equals ( bodyrichiesta.getTipoFirma () ) ) {
                rtData = estraiRTFirmata ( rtData );
            }

            rt = (CtRicevutaTelematica) unmarshaller.unmarshal ( new ByteArrayInputStream ( rtData ) );

						if ( null != rt ) {
							if ( null != rt.getIstitutoAttestante () ) {
								if ( null != rt.getIstitutoAttestante ().getIdentificativoUnivocoAttestante () ) {
									idPsp = rt.getIstitutoAttestante ().getIdentificativoUnivocoAttestante ().getCodiceIdentificativoUnivoco ();
								}
							}
						}

            LoggerUtil.dumpObject ( "Unmarshalling avvenuto con successo", rt );

            LoggerUtil.begin ( "ANALISI-RICERCA-IUV-" + header.getIdentificativoUnivocoVersamento () );

	        assert rt != null;
	        List<RPT> elencoRpt = reperisciRPTPerID ( rt, p, header.getIdentificativoUnivocoVersamento () );

            if ( elencoRpt == null || elencoRpt.size () == 0 ) {
                faultString = FaultBeanEnum.PAA_RPT_SCONOSCIUTA.getFaultString () + " TRANSAZIONE = " + rt.getRiferimentoMessaggioRichiesta ();
                risposta.setPaaInviaRTRisposta ( componiEsitoFallimento ( esito, FaultBeanEnum.PAA_RPT_SCONOSCIUTA.name (),
                    FaultBeanEnum.PAA_RPT_SCONOSCIUTA.getFaultString (),
                    FaultBeanEnum.PAA_RPT_SCONOSCIUTA.getFaultString () + " " + rt.getRiferimentoMessaggioRichiesta (), header.getIdentificativoDominio () ) );
                return risposta;
            }

            richiestaTrovata = elencoRpt.get ( 0 );

            DettaglioFruitore df = p.recuperaDatiFruitorePerApplicationId ( richiestaTrovata.getApplicationId () );
            mappaAcf = CoreUtilities.costruisciMappaApplicationCustomFields ( df.getElencoAcf () );
            if ( !header.getIdentificativoDominio ().equals ( richiestaTrovata.getIdentificativoDominio () ) ) {
                faultString = FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getFaultString () + " " + header.getIdentificativoDominio () + " TRANSAZIONE = "
                    + rt.getRiferimentoMessaggioRichiesta ();
                risposta.setPaaInviaRTRisposta ( componiEsitoFallimento ( esito, FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.name (),
                    FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getFaultString (),
                    FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getFaultString () + " " + header.getIdentificativoDominio (), header.getIdentificativoDominio () ) );
                watcher.stop ();
                watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "paaInviaRT()", "[PagamentiTelematiciRTImpl]::[paaInviaRT]",
                    "esito :" + risposta.getPaaInviaRTRisposta ().getEsito () );
                return risposta;
            }

            if ( !header.getIdentificativoIntermediarioPA ().equals ( richiestaTrovata.getIdentificativoIntermediarioPa () ) ) {
                faultString = FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.getFaultString () + " " + header.getIdentificativoIntermediarioPA () + " TRANSAZIONE = "
                    + rt.getRiferimentoMessaggioRichiesta ();
                risposta.setPaaInviaRTRisposta ( componiEsitoFallimento ( esito, FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.name (),
                    FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.getFaultString (),
                    FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.getFaultString () + " " + header.getIdentificativoIntermediarioPA (),
                    header.getIdentificativoDominio () ) );
                watcher.stop ();
                watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "paaInviaRT()", "[PagamentiTelematiciRTImpl]::[paaInviaRT]",
                    "esito :" + risposta.getPaaInviaRTRisposta ().getEsito () );
                return risposta;
            }

            if ( !header.getIdentificativoStazioneIntermediarioPA ().equals ( richiestaTrovata.getIdentificativoStazioneIntermediarioPa () ) ) {
                faultString = FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.getFaultString () + " " + header.getIdentificativoStazioneIntermediarioPA ()
                    + " TRANSAZIONE = " + rt.getRiferimentoMessaggioRichiesta ();
                risposta.setPaaInviaRTRisposta (
                    componiEsitoFallimento ( esito, FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.name (), FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.getFaultString (),
                        FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.getFaultString () + " " + header.getIdentificativoStazioneIntermediarioPA (),
                        header.getIdentificativoDominio () ) );
                watcher.stop ();
                watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "paaInviaRT()", "[PagamentiTelematiciRTImpl]::[paaInviaRT]",
                    "esito :" + risposta.getPaaInviaRTRisposta ().getEsito () );
                return risposta;
            }
            RT ricevutaDB = p.recuperaRichiestaPerIdTransazione ( rt.getRiferimentoMessaggioRichiesta () );
            if ( ricevutaDB != null ) {
                CtRicevutaTelematica rtTrovataDB = (CtRicevutaTelematica) unmarshaller.unmarshal ( new ByteArrayInputStream ( ricevutaDB.getRtData () ) );
                if ( header.getCodiceContestoPagamento ().equals ( rtTrovataDB.getDatiPagamento ().getCodiceContestoPagamento () )
                    && header.getIdentificativoUnivocoVersamento ().equals ( rtTrovataDB.getDatiPagamento ().getIdentificativoUnivocoVersamento () ) ) {
                    faultString = FaultBeanEnum.PAA_RT_DUPLICATA.getFaultString () + " CODICE CONTESTO PAGAMENTO = " + header.getCodiceContestoPagamento ();
                    risposta.setPaaInviaRTRisposta (
                        componiEsitoFallimento ( esito, FaultBeanEnum.PAA_RT_DUPLICATA.name (), FaultBeanEnum.PAA_RT_DUPLICATA.getFaultString (),
                            FaultBeanEnum.PAA_RT_DUPLICATA.getFaultString () + " Per transazione identificata: " + rt.getRiferimentoMessaggioRichiesta (),
                            header.getIdentificativoDominio () ) );
                    watcher.stop ();
                    watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "paaInviaRT()", "[PagamentiTelematiciRTImpl]::[paaInviaRT]",
                        "esito :" + risposta.getPaaInviaRTRisposta ().getEsito () );
                    return risposta;
                }
            }

            esito.setEsito ( "OK" );
            risposta.setPaaInviaRTRisposta ( esito );

            LoggerUtil.begin ( "ANALISI-COMPOSIZIONE-ESITO" );

        } catch ( UnmarshalException e ) {
            LoggerUtil.error ( "ERRORE ", e );
            faultString = testoEccezioneCausa ( e );
            risposta.setPaaInviaRTRisposta ( componiEsitoFallimento ( esito, FaultBeanEnum.PAA_SINTASSI_XSD.name (),
                FaultBeanEnum.PAA_SINTASSI_XSD.getFaultString (), faultString, header.getIdentificativoDominio () ) );
            Utils.inviaEmailErrore ( header.getIdentificativoUnivocoVersamento (), header.getCodiceContestoPagamento (), p, null, e, "paaInviaRT" );
        } catch ( JAXBException e ) {
            LoggerUtil.error ( "ERRORE ", e );
            faultString = testoEccezioneCausa ( e );
            risposta.setPaaInviaRTRisposta ( componiEsitoFallimento ( esito, FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.name (),
                FaultBeanEnum.PAA_SINTASSI_EXTRAXSD.getFaultString (), faultString, header.getIdentificativoDominio () ) );
            Utils.inviaEmailErrore ( header.getIdentificativoUnivocoVersamento (), header.getCodiceContestoPagamento (), p, null, e, "paaInviaRT" );
        } catch ( Exception e ) {
            LoggerUtil.error ( "ERRORE ", e );
            faultString = Utils.concatenaMessaggiCausa ( e ).toString ();
            risposta.setPaaInviaRTRisposta ( componiEsitoFallimento ( esito, FaultBeanEnum.PAA_SYSTEM_ERROR.name (),
                FaultBeanEnum.PAA_SYSTEM_ERROR.getFaultString (), faultString, header.getIdentificativoDominio () ) );
            Utils.inviaEmailErrore ( header.getIdentificativoUnivocoVersamento (), header.getCodiceContestoPagamento (), p, null, e, "paaInviaRT" );
        } finally {

            LoggerUtil.dumpObject ( "RISPOSTA RISPEDITA", risposta );

            if ( richiestaTrovata != null ) {

                if ( "Y".equalsIgnoreCase ( mappaAcf.get ( "flagRiceviRT" ) ) ) {
                    LoggerUtil.begin ( "ANALISI-REGISTRAZIONE-RT" );
                }

                registraRT ( bodyrichiesta.getRt (), richiestaTrovata.getApplicationId (), richiestaTrovata.getTransactionId (), bodyrichiesta.getTipoFirma (),
                    rt, esito.getEsito (), null, commonInsertDate );
                aggiornaRPT ( richiestaTrovata, esito.getEsito () );
                registraEventoGiornale ( richiestaTrovata.getApplicationId (), richiestaTrovata.getTransactionId (),
                    richiestaTrovata.getIdentificativoDominio (), esito.getEsito (), "paaInviaRT", header.getIdentificativoUnivocoVersamento (), "N/A",
                    header.getIdentificativoStazioneIntermediarioPA (), header.getIdentificativoDominio (), StringUtils.isEmpty ( idPsp ) ? richiestaTrovata.getIdPsp () : idPsp,
                    richiestaTrovata.getIdIntermPsp (), faultString, rt );
                aggiornaStatoTransazione ( p, richiestaTrovata.getTransactionId (), rt.getDatiPagamento ().getCodiceEsitoPagamento (), esito.getEsito (), header.getIdentificativoUnivocoVersamento() );
                
                if ( "KO".equalsIgnoreCase ( esito.getEsito () ) ) {
                    inviaMailErrore ( p, "Errore durante la ricezione della Ricevuta Telematica per lo iuv " + header.getIdentificativoUnivocoVersamento ()
                        + " \r\nTesto dell'errore: " + esito.getFault ().getDescription () );
                }
                //controllo se codice errore PAA_RT_DUPLICATA non invio al gestionale
                //In un primo momento preferiamo inviare sempre la RT ai gestionali
                if ( "KO".equalsIgnoreCase ( esito.getEsito () ) && esito.getFault () != null
                    && FaultBeanEnum.PAA_RT_DUPLICATA.name ().equals ( esito.getFault ().getFaultCode () ) ) {
                    LoggerUtil.info ( FaultBeanEnum.PAA_RT_DUPLICATA.name () + " - " + FaultBeanEnum.PAA_RT_DUPLICATA.getDescription ()
                        + " La RT non verra inviata al gestionale esterno." );
                } else {
                    try {
                        Singleton instance = Singleton.getInstance ();

                        instance.addThread ( new InviaRTThread ( bodyrichiesta, rt, richiestaTrovata, esito, mappaAcf, p, commonInsertDate ) );
                    } catch ( InterruptedException e ) {
                        LoggerUtil.error ( "Errore in fase di communicazione con il nodo e/o fruitore", e );
                    }
                }
            } else {
                registraRT ( bodyrichiesta.getRt (), " - ", " - ", bodyrichiesta.getTipoFirma (), rt, esito.getEsito (), "KO", commonInsertDate );
				idPsp = "N/A";
				if ( null != rt.getIstitutoAttestante () ) {
					if ( null != rt.getIstitutoAttestante ().getIdentificativoUnivocoAttestante () ) {
						if ( null != rt.getIstitutoAttestante ().getIdentificativoUnivocoAttestante ().getCodiceIdentificativoUnivoco () )
							idPsp = rt.getIstitutoAttestante ().getIdentificativoUnivocoAttestante ().getCodiceIdentificativoUnivoco ();
					}
				}
				registraEventoGiornale ( "N/A", "N/A", "N/A", esito.getEsito (), "paaInviaRT", header.getIdentificativoUnivocoVersamento (), "N/A",
                    header.getIdentificativoStazioneIntermediarioPA (), header.getIdentificativoDominio (), idPsp,
                    "N/A", faultString, rt );

                inviaMailErrore ( p, "Richiesta Telematica di Pagamento (RPT) per IUV = " + header.getIdentificativoUnivocoVersamento () + " Non trovata" );
            }

        }

        LoggerUtil.end ( "ANALISI" );

        LoggerUtil.end ();

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "paaInviaRT()", "[PagamentiTelematiciRTImpl]::[paaInviaRT]", "(valore input omesso)" );

        return risposta;
    }
    
    class InviaRRToFruitoreThread implements Callable<String> {

        private RR rrDTO;

        private Integer lastKey;

        /**
         * @param rrDTO
         */
        public InviaRRToFruitoreThread ( RR rrDTO, Integer lastKey ) {
            super ();
            this.rrDTO = rrDTO;
            this.lastKey = lastKey;
        }

        /**
         * 
         */
        @SuppressWarnings ( "unused" )
        private InviaRRToFruitoreThread () {
            super ();
        }

        @Override
		public String call () throws Exception {
        	LoggerUtil.debug ( "START invoking call InviaRRToFruitoreThread" );
        	String endpoint = null;
        	String ret = null;

        	try {
        		
            List<Applicationcustomfields> customFieldsList
                = PersistenzaDatiNodoUtility.recuperaCustomFieldDecoded ( rrDTO.getApplicationId (), "endpointServiziNodo" );

            for ( Applicationcustomfields item: customFieldsList ) {
                if ( item.getFieldname ().equalsIgnoreCase ( "endpointServiziNodo" ) ) {
                    endpoint = item.getFieldvalue ();
                }
            }
            

            /*
             * Sending data to Fruitore
             */
            if ( endpoint != null && endpoint.length () > 0 && !endpoint.toLowerCase ().endsWith ( "?wsdl" ) ) {
                endpoint += "?wsdl";
            }
            
            LoggerUtil.debug ( "chiamata riceviEsito per RR su gestionale esterno: " + endpoint);
            
            LoggerUtil.debug("Valorizzazione parametri di input: BEGIN");
            
            it.csi.mdp.clientmod3.ParametriRiceviEsito parametriRiceviEsito = new it.csi.mdp.clientmod3.ParametriRiceviEsito();
            
            String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            parametriRiceviEsito.setApplicationId(rrDTO.getApplicationId());
            parametriRiceviEsito.setTransactionId("na");
            parametriRiceviEsito.setIdMsgRicevuta("na");
            parametriRiceviEsito.setDataOraMsgRicevuta(stringToXMLGregorianCalendar(now));
            parametriRiceviEsito.setTipoFirma("na");
            parametriRiceviEsito.setIuv(rrDTO.getIuv());
            parametriRiceviEsito.setCodEsitoPagamento("na");
            parametriRiceviEsito.setDescEsitoPagamento("na");
            parametriRiceviEsito.setIdMsgRichiesta(rrDTO.getIdentificativoMessaggioRevoca());
            parametriRiceviEsito.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            parametriRiceviEsito.setMac("na");
            parametriRiceviEsito.setIdentificativoUnivocoRiscossione("na");
            parametriRiceviEsito.setImportoPagato(new BigDecimal(0));
            parametriRiceviEsito.setRtPresente(true);
            parametriRiceviEsito.setIdentificativoPSP("na");
            parametriRiceviEsito.setDenominazionePSP(rrDTO.getDenominazioneIstitutoAttestante());

            rrDTO.setId(lastKey);
            List<it.csi.mdp.clientmod3.ChiaveValore> parameters = null;

            parameters = createRRParametersList(rrDTO);

            ChiaveValore rrFlag = new ChiaveValore();
            rrFlag.setChiave("isRR");
            rrFlag.setValore("true");
            parameters.add(rrFlag);

            ChiaveValore rrXml = new ChiaveValore();
            rrXml.setChiave("xml");
    		if(rrDTO.getXmlRR() != null) {
    		    //L'xml va ricodificato per la spedizione, sportello lo attende codificato
    	        rrXml.setValore(Base64.encodeBase64String ( rrDTO.getXmlRR ()));
    		}
        	parameters.add(rrXml);

            int index = 1;
            for (DatiSingolaRevoca datiSingolaRevoca : rrDTO.getListaDatiSingolaRevoca()) {
                ChiaveValore datiSingolaRevocaEntry = new ChiaveValore();
                datiSingolaRevocaEntry.setChiave("causale_revoca" + index);
                datiSingolaRevocaEntry.setValore(datiSingolaRevoca.getCausaleRevoca());
                parameters.add(datiSingolaRevocaEntry);
                
                datiSingolaRevocaEntry = new ChiaveValore();
                datiSingolaRevocaEntry.setChiave("dati_aggiuntivi_revoca" + index);
                datiSingolaRevocaEntry.setValore(datiSingolaRevoca.getDatiAggiuntiviRevoca());
                parameters.add(datiSingolaRevocaEntry);

                datiSingolaRevocaEntry = new ChiaveValore();
                datiSingolaRevocaEntry.setChiave("iur" + index);
                datiSingolaRevocaEntry.setValore(datiSingolaRevoca.getIur());
                parameters.add(datiSingolaRevocaEntry);

                datiSingolaRevocaEntry = new ChiaveValore();
                datiSingolaRevocaEntry.setChiave("singolo_importo_revocato" + index);
                datiSingolaRevocaEntry.setValore(datiSingolaRevoca.getSingoloImportoRevocato().toString());

                parameters.add(datiSingolaRevocaEntry);
                index++;
            }
            parametriRiceviEsito.setElencoParametriAggiuntivi(parameters);
            
            //parametriRiceviEsito.getElencoParametriAggiuntivi().addAll(parameters);
            
            LoggerUtil.debug("Valorizzazione parametri di input: END");
            
            /*
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean ();
            // factory.setProperties ( props );
            factory.getInInterceptors ().add ( new LoggingInInterceptor () );
            factory.getOutInterceptors ().add ( new LoggingOutInterceptor () );
            factory.setServiceClass ( Serviziorissrvspc.class );
            factory.setAddress ( endpoint );
            
            Serviziorissrvspc inviaRichiestaRevoca = (Serviziorissrvspc) factory.create ();
            */
            Serviziorissrvspc inviaRichiestaRevoca = (Serviziorissrvspc)Utils.getProxyAPIService(Serviziorissrvspc.class, endpoint,null);
            
            LoggerUtil.debug("inviaRichiestaRevoca.riceviEsito: INVOKING ...");
            ret = inviaRichiestaRevoca.riceviEsito(parametriRiceviEsito).getEsito();
            LoggerUtil.debug("inviaRichiestaRevoca.riceviEsito: INVOKED");
            
            //EpayMdpWsInterface port = instanceClient();

            // EPayClient ePayClient = new EPayClient ( endpoint );
            //String ret = ePayClient.sendRRToFruitore ( rrDTO, lastKey );
            LoggerUtil.debug ( "esito RR ricevuto da gestionale esterno: <"+ret+">" );
            
            
        	} catch (Exception e) {
        		LoggerUtil.error(e.getMessage(),e);
        		throw e;
        	}
        	finally {
        		LoggerUtil.debug ( "END invoking call InviaRRToFruitoreThread" );
			}
        	return ret;
        }
        
        private XMLGregorianCalendar stringToXMLGregorianCalendar(String s) throws ParseException, DatatypeConfigurationException {
            XMLGregorianCalendar result = null;
            Date date;
            SimpleDateFormat simpleDateFormat;
            GregorianCalendar gregorianCalendar;

            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(s);
            gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            return result;
        }
        
        private List<ChiaveValore> createRRParametersList(RR rr)
                throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            List<ChiaveValore> parameters = new ArrayList<ChiaveValore>();
            for (Field field : rr.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(XStreamAlias.class)) {
                    String methodName = "get" + field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1);
                    Object value = rr.getClass().getMethod(methodName).invoke(rr);
                    ChiaveValore param = new ChiaveValore();
                    param.setChiave(field.getAnnotation(XStreamAlias.class).value());
                        
                    if(value != null) {
                        if (value.getClass().getSimpleName().equals("Timestamp")) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String convertedValue = dateFormat.format(value);
                            param.setValore(convertedValue);
                        }
                        if (value.getClass().getSimpleName().equals("Double")) {
                            String convertedValue = ((Double) value).toString();
                            param.setValore(convertedValue);
                        }       
                        if (value.getClass().getSimpleName().equals("String")) {
                            param.setValore((String)value);
                        }
                        if (value.getClass().getSimpleName().equals("Integer")) {
                            param.setValore(((Integer)value).toString());
                        }
                    }

                    parameters.add(param);
                }
            }
            return parameters;
        }
    }
    
    class InviaRRToNodoThread implements Callable<String> {

        /**
         * @param rrDTO
         */
        public InviaRRToNodoThread ( RR rr, ER er, CountDownLatch countDownLatchBlocker ) {
            super ();
            this.rr = rr;
            this.er = er;
            this.countDownLatchBlocker = countDownLatchBlocker;
        }

        /**
         * 
         */
        @SuppressWarnings ( "unused" )
        private InviaRRToNodoThread () {
            super ();
        }

        private RR rr;
        private ER er;
        private CountDownLatch countDownLatchBlocker;

        @Override
		public String call () throws Exception {
        	LoggerUtil.debug ( "START invoking call InviaRRToNodoThread" );
        	LoggerUtil.debug ( "STOP running call InviaRRToNodoThread" );
        	countDownLatchBlocker.await();
        	LoggerUtil.debug ( "RESTART running call InviaRRToNodoThread" );
            nodoInviaRispostaRevoca ( rr, er );
            LoggerUtil.debug ( "END invoking call InviaRRToNodoThread" );
            return "OK";
        }
    }

    class InviaRTThread implements Callable<String> {

        private PaaInviaRT bodyrichiesta;

        private CtRicevutaTelematica rt;

        private RPT richiestaTrovata;

        private EsitoPaaInviaRT esito;

        private Map<String, String> mappaAcf;

        private Payment p;

        private Timestamp commonInsertDate;

        public InviaRTThread ( PaaInviaRT bodyrichiesta, CtRicevutaTelematica rt, RPT richiestaTrovata, EsitoPaaInviaRT esito, Map<String, String> mappaAcf,
            Payment p, Timestamp commonInsertDate ) {
            this.bodyrichiesta = bodyrichiesta;
            this.esito = esito;
            this.mappaAcf = mappaAcf;
            this.p = p;
            this.richiestaTrovata = richiestaTrovata;
            this.rt = rt;
            this.commonInsertDate = commonInsertDate;
        }

        //@Override
        @Override
		public String call () {

            StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
            watcher.start ();

            String statoInvioFruitore = spedisciRTAlFruitore ( rt, richiestaTrovata,
                esito, bodyrichiesta.getRt (),
                bodyrichiesta.getTipoFirma (),
                p, mappaAcf );

            LoggerUtil.info ( "ESITO INVIO FRUITORE:" + statoInvioFruitore );

            aggiornaRT ( bodyrichiesta.getRt (), richiestaTrovata.getApplicationId (), richiestaTrovata.getTransactionId (), bodyrichiesta.getTipoFirma (), rt,
                esito.getEsito (), statoInvioFruitore, commonInsertDate );

            watcher.stop ();
            watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "inviaRTThread()", "[PagamentiTelematiciRTImpl]::[inviaRTThread]", "(valore input omesso)" );

            return statoInvioFruitore;
        }

        private void aggiornaRT ( byte [] rt, String applicationId, String transactionId, String tipoFirma, CtRicevutaTelematica rtOgg, String esito,
            String stato, Timestamp commonInsertDate ) {

            StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
            watcher.start ();

            LoggerUtil.debug ( "[PagamentiTelematiciRTImpl::aggiornaRT] - INIT" );

            try {
                int codiceEsito = 2;

                LoggerUtil.info ( "rt non null:" + ( rtOgg != null ) );

                if ( rtOgg != null ) {
                    codiceEsito = Integer.valueOf ( rtOgg.getDatiPagamento ().getCodiceEsitoPagamento () ) + 1;
                    LoggerUtil.info ( "Esito:" + codiceEsito );
                }

                LoggerUtil.info ( "Chiamata alla persistenza" );

                //ADD "X" + transactionId per ri-utilizare le RT
                PersistenzaDatiNodoUtility.aggiornaRT ( rt, applicationId, transactionId, tipoFirma, rtOgg, codiceEsito, stato, "WEBSERVICE",
                    commonInsertDate );
            } catch ( Exception e ) {
                LoggerUtil.error ( "IMPOSSIBILE AGGIORNARE LA RICEVUTA DI PAGAMENTO TELEMATICA", e );
            } finally {
                // fine misurazione
                LoggerUtil.debug ( "[PagamentiTelematiciRTImpl::aggiornaRT] - END" );

                watcher.stop ();
                watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "aggiornaRT()", "[PersistenzaDatiNodoUtility]::[aggiornaRT]", "(valore input omesso)" );
            }
        }

        private String spedisciRTAlFruitore ( CtRicevutaTelematica rt,
            RPT richiestaTrovata, EsitoPaaInviaRT esito, byte [] rtData, String tipoFirma, Payment p, Map<String, String> mappaAcf ) {

            StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
            watcher.start ();

            LoggerUtil.debug ( "[PagamentiTelematiciRTImpl::spedisciRTAlFruitore] - INIT" );

            try {
                Map<String, Object> props = new HashMap<String, Object> ();
                props.put ( "mtom-enabled", Boolean.TRUE );

                /*
                JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean ();
                factory.setProperties ( props );
                factory.getInInterceptors ().add ( new LoggingInInterceptor () );
                factory.getOutInterceptors ().add ( new LoggingOutInterceptor () );
                factory.setServiceClass ( Serviziorissrvspc.class );
                factory.setAddress ( mappaAcf.get ( "endpointServiziNodo" ) );
                Serviziorissrvspc iRiceviRT = (Serviziorissrvspc) factory.create ();
                */
                Serviziorissrvspc iRiceviRT = (Serviziorissrvspc)Utils.getProxyAPIService(Serviziorissrvspc.class, mappaAcf.get ( "endpointServiziNodo" ),props);
                

                ParametriRiceviRT ricevuta = new ParametriRiceviRT ();
                ricevuta.setApplicationId ( richiestaTrovata.getApplicationId () );
                ricevuta.setCodEsitoPagamento ( rt.getDatiPagamento ().getCodiceEsitoPagamento () );
                ricevuta.setDataOraMsgRicevuta ( rt.getDataOraMessaggioRicevuta () );
                ricevuta.setDescEsitoPagamento ( CostantiNodoSpc.mappaCodiciEsitoPagamento.get ( rt.getDatiPagamento ().getCodiceEsitoPagamento () ) );
                ricevuta.setIdMsgRicevuta ( rt.getIdentificativoMessaggioRicevuta () );
                ricevuta.setIdMsgRichiesta ( richiestaTrovata.getIdMsgRichiesta () );
                ricevuta.setIuv ( rt.getDatiPagamento ().getIdentificativoUnivocoVersamento () );
                String timestamp = null;
                SimpleDateFormat sdf = new SimpleDateFormat ( "ddmmyyyy-hh:mm:ss:ms" );
                timestamp = sdf.format ( new Date () );
                ricevuta.setMac ( generaMac ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE ), richiestaTrovata.getIuv (),
                    richiestaTrovata.getApplicationId (), rt.getIdentificativoMessaggioRicevuta (), timestamp ) );
                ByteArrayDataSource rawData = new ByteArrayDataSource ( rtData, "application/octet-stream" );
                ricevuta.setRtData ( new DataHandler ( rawData ) );
                ricevuta.setTimestamp ( timestamp );
                ricevuta.setTipoFirma ( tipoFirma );
                ricevuta.setTransactionId ( richiestaTrovata.getTransactionId () );
                EsitoRiceviRT esitoRT = iRiceviRT.riceviRT ( ricevuta );

                LoggerUtil.dumpObject ( "COSA MI HA RISPOISTO?", esitoRT );
                return esitoRT.getEsito ();
            } catch ( Throwable t ) {
                LoggerUtil.error ( "ERRORE DURANTE L'INVIO DELLA RT AL FRUITORE: ", t );
                return "KO";
            } finally {
                // fine misurazione               
                LoggerUtil.debug ( "[PagamentiTelematiciRTImpl::spedisciRTAlFruitore] - END" );

                watcher.stop ();
                watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "spedisciRTAlFruitore()", "invocazione servizio [Serviziorissrvspc]::[riceviRT]",
                    "(valore input omesso)" );
            }
        }

    }

    private void inviaMailErrore ( Payment p, String testo ) {
        try {
            p.errorMail ( "", null, testo );
        } catch ( Exception e ) {
            LoggerUtil.error ( "Impossibile inviare l'email di errore con il testo " + testo, e );
        }
    }

    private byte [] estraiRTFirmata ( byte [] rtData ) throws DosignException_Exception, IOException {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        JaxWsProxyFactoryBean fact = new JaxWsProxyFactoryBean ();
        fact.getInInterceptors ().add ( new LoggingInInterceptor () );
        fact.getOutInterceptors ().add ( new LoggingOutInterceptor () );
        fact.setServiceClass ( DosignSignatureValidation.class );
        Properties propsEnv = new Properties ();
        propsEnv.load ( this.getClass ().getResourceAsStream ( "/META-INF/env.properties" ) );
        LoggerUtil.debug ( "PROPERTIES: " + propsEnv.getProperty ( "endpointDosign" ) );
        fact.setAddress ( propsEnv.getProperty ( "endpointDosign" ) );
        Map<String, Object> props = new HashMap<String, Object> ();
        props.put ( "mtom-enabled", Boolean.TRUE );
        fact.setProperties ( props );
        DosignSignatureValidation service = (DosignSignatureValidation) fact.create ();

        VerifyDto verifyDto = new VerifyDto ();
        verifyDto.setEnvelopeArray ( rtData );
        verifyDto.setExtractData ( true );
        verifyDto.setProfileType ( 1 );
        verifyDto.setVerificationScope ( 1 );
        verifyDto.setVerificationType ( 1 );

        VerifyReportDto response = service.verify ( verifyDto );

        //FIXME JIRA MDPNEW-31: errori di sbustamento response.getError();
        response.getError (); //int
        response.getErrorMsg (); //String, ha qualche formato?
        response.getHexErrorCode (); //String, consiste in qualcosa di particolare?
        response.getVerifyInfo ().get ( 0 ).getError ();//??
        response.getVerifyInfo ().get ( 0 ).getInvalidSignCount ();//??
        //FIXME fine codice di esempio
        rtData = response.getVerifyInfo ().get ( 0 ).getData ();

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "estraiRTFirmata()", "[PagamentiTelematiciRTImpl]::[estraiRTFirmata]", "(valore input omesso)" );

        return rtData;
    }

    private void registraRT ( byte [] rt, String applicationId,
        String transactionId, String tipoFirma, CtRicevutaTelematica rtOgg, String esito, String stato, Timestamp commonInsertDate ) {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        try {
            int codiceEsito = 2;
            if ( rtOgg != null ) {
                codiceEsito = Integer.valueOf ( rtOgg.getDatiPagamento ().getCodiceEsitoPagamento () ) + 1;
            }
            PersistenzaDatiNodoUtility.registraRT ( rt, applicationId, transactionId, tipoFirma, rtOgg, codiceEsito, stato, "WEBSERVICE", commonInsertDate );
        } catch ( Exception e ) {
            LoggerUtil.error ( "IMPOSSIBILE REGISTRARE LA RICEVUTA DI PAGAMENTO TELEMATICA", e );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "registraRT()", "[PagamentiTelematiciRTImpl]::[registraRT]", "(valore input omesso)" );
        }
    }

    private String testoEccezioneCausa ( Exception e ) {
        String faultString = "";
        if ( StringUtils.isEmpty ( e.getMessage () ) ) {
            if ( e.getCause () != null ) {
                faultString = e.getCause ().getMessage ();
            }
        } else {
            faultString = e.getMessage ();
        }
        return faultString;
    }

    private List<RPT> reperisciRPTPerID ( CtRicevutaTelematica rt, Payment p, String iuv )
                    throws RemoteException, DaoException, NamingException,
                    CreateException, MissingParameterException {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        RPT filtro = new RPT ();
        filtro.setIdMsgRichiesta ( rt.getRiferimentoMessaggioRichiesta () );
        filtro.setIuv ( iuv );
		if ( null != rt.getDominio () ) {
			filtro.setIdentificativoDominio ( rt.getDominio ().getIdentificativoDominio () );
			LoggerUtil.info ( "added filtro: rt.getDominio ().getIdentificativoDominio (): " + rt.getDominio ().getIdentificativoDominio ());
		}
		List<RPT> elencoRpt = p.recuperaRichiestaPagamentoConFiltro ( filtro );
        LoggerUtil.debug ( "Elenco RPT per id richiesta " + rt.getRiferimentoMessaggioRichiesta () );
        LoggerUtil.dumpObject ( "ELENCO", elencoRpt );

        watcher.stop ();
        watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "reperisciRPTPerID()", "[PagamentiTelematiciRTImpl]::[reperisciRPTPerID]", "(valore input omesso)" );

        return elencoRpt;
    }

    private Unmarshaller creaUnMarshallerRT ()
                    throws JAXBException, SAXException {
        JAXBContext jContext = JAXBContext.newInstance ( CtRicevutaTelematica.class );
        Unmarshaller unmarshaller = jContext.createUnmarshaller ();
        SchemaFactory sf = SchemaFactory.newInstance ( "http://www.w3.org/2001/XMLSchema" );
        Source so = new StreamSource ( this.getClass ().getResourceAsStream ( "/META-INF/PagInf_RPT_RT_6_2_0.xsd" ) );
        Schema s = sf.newSchema ( so );
        unmarshaller.setSchema ( s );
        return unmarshaller;
    }

    /**
     * Registra lo stato della transazione
     * 
     * @param p
     * @param t
     * @param codiceEsitoPagamento
     */
    private void aggiornaStatoTransazione ( Payment p, String t, String codiceEsitoPagamento, String esito, String iuv ) {
        if ( p == null ) {
            return;
        }
        LoggerUtil.info ( "REGISTRAZIONE SATO TRANSAZIONE PER ESITO " + codiceEsitoPagamento );
        try {
            p.setStatoTransazione ( t, "0".equals ( codiceEsitoPagamento ) ? 4 : 5, codiceEsitoPagamento, iuv );

        } catch ( RemoteException e ) {
            LoggerUtil.error ( "ERRORE DURANTE LA REGISTRAZIONE DELLO STATO TRANSAZIONE PER IL NODO ", e );
        } catch ( DaoException e ) {
            LoggerUtil.error ( "ERRORE DURANTE LA REGISTRAZIONE DELLO STATO TRANSAZIONE PER IL NODO ", e );
        } catch ( MissingParameterException e ) {
            LoggerUtil.error ( "ERRORE DURANTE LA REGISTRAZIONE DELLO STATO TRANSAZIONE PER IL NODO ", e );
        }
    }

    @Override
	@WebResult ( name = "paaInviaRichiestaRevocaRisposta", targetNamespace = "" )
    @RequestWrapper ( localName = "paaInviaRichiestaRevoca", targetNamespace = "http://ws.pagamenti.telematici.gov/",
                    className = "it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaRichiestaRevoca" )
    @ResponseWrapper ( localName = "paaInviaRichiestaRevocaRisposta", targetNamespace = "http://ws.pagamenti.telematici.gov/",
                    className = "it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaRichiestaRevocaRisposta" )
    @WebMethod ( action = "paaInviaRichiestaRevoca" )
    public TipoInviaRichiestaRevocaRisposta paaInviaRichiestaRevoca (
        @WebParam ( name = "identificativoDominio", targetNamespace = "" ) java.lang.String identificativoDominio,
        @WebParam ( name = "identificativoUnivocoVersamento", targetNamespace = "" ) java.lang.String identificativoUnivocoVersamento,
        @WebParam ( name = "codiceContestoPagamento", targetNamespace = "" ) java.lang.String codiceContestoPagamento,
        @WebParam ( name = "rr", targetNamespace = "" ) byte [] rr ) {
        
    	LoggerUtil.debug ( "START invoking paaInviaRichiestaRevoca" );
    	FaultBean _returnFault = null;
        TipoInviaRichiestaRevocaRisposta _return = new TipoInviaRichiestaRevocaRisposta ();
        _return.setEsito ( "KO" );

        RR rrDto = null;
        Integer lastKey = null;
        ER erDto = null;
        try {
            if ( StringUtils.isBlank ( identificativoDominio ) || StringUtils.isBlank ( identificativoUnivocoVersamento )
                || StringUtils.isBlank ( codiceContestoPagamento ) || ArrayUtils.isEmpty ( rr ) ) {
                throw new IllegalArgumentException ( "Parametri di input mancanti" );
            }

            // FIXED CSI_PAG-333 MDPNEW-95 BEGIN
            Assert.isTrue ( validateXMLSchema ( rr ), "Non è stato possibile validare il messaggio di input." );
            // FIXED CSI_PAG-333 MDPNEW-95 END
            /*
             * Mapping xml to DTO
             */
            CtRichiestaRevoca ctRichiestaRevoca = unmarshallER ( rr );
            rrDto = ctRichiestaRevocaToRR ( ctRichiestaRevoca );
            LoggerUtil.debug("mapping XML-> DTO richiesta revoca OK");

            Assert.isTrue ( identificativoDominio.equals ( rrDto.getIdDominio () ),
                "Identificativo Dominio[] non corrisponde con quanto presente nella revoca[" + rrDto.getIdDominio () + "]" );
            Assert.isTrue ( identificativoUnivocoVersamento.equals ( rrDto.getIuv () ),
                "IUV[" + identificativoUnivocoVersamento + "] non corrisponde con quanto presente nella revoca[" + rrDto.getIuv () + "]" );
            Assert.isTrue ( codiceContestoPagamento.equals ( rrDto.getCodiceContestoPagamento () ),
                "codice Contesto Pagamento[" + codiceContestoPagamento + "] non corrisponde con quanto presente nella revoca["
                    + rrDto.getCodiceContestoPagamento () + "]" );

            /*
             * Map params
             */
            rrDto.setXmlRR ( rr );

            /*
             * Save RR and DatiSingolaRevoca
             */
            lastKey = PersistenzaDatiNodoUtility.registraRR ( rrDto );
            LoggerUtil.debug ( "Last ID " + lastKey );
            Assert.notNull ( lastKey, "Errore in fase di salvataggio della RR con IUV: " + identificativoUnivocoVersamento );

            /*
             * Saving ER and DatiSingoloEsito
             */
            erDto = createER ( rrDto );
            erDto.setXmlEr ( erToXml ( creaEsitoRevoca ( ctRichiestaRevoca ) ).getBytes ( "UTF-8" ) );
            PersistenzaDatiNodoUtility.registraER ( erDto );

            /*
             * Updating RT data with id_rr
             */
            PersistenzaDatiNodoUtility.updateIdRrByIuv ( rrDto.getIuv (), lastKey );
        } catch ( Exception e ) {
            _returnFault = new FaultBean ();
            _returnFault.setFaultCode ( "KO" );
            LoggerUtil.error ( "Unable to complete paaInviaRichiestaRevoca ", e );
        }
        _return.setFault ( _returnFault );

        CountDownLatch countDownLatchBlocker = new CountDownLatch(1);
        if ( _returnFault == null ) {
            _return.setEsito ( "OK" );
            // rispondere OK da qui in poi eseguire in async
            try {
                LoggerUtil.debug ( "BEGIN THREADS INVIO RR ER" );
                Singleton instance = Singleton.getInstance ();
                /*
                 * Sending response to Nodo
                 */
                
                instance.addThread ( new InviaRRToFruitoreThread ( rrDto, lastKey ) );
                instance.submit ( new InviaRRToNodoThread ( rrDto, erDto, countDownLatchBlocker) );
                //instance.addThread ( new InviaRRToNodoThread ( rrDto, erDto, callingThreadBlocker) );
            } catch ( InterruptedException e ) {
                LoggerUtil.error ( "Errore in fase di communicazione con il nodo e/o fruitore", e );
                /**
                 * Errori considerati non bloccanti eccezione non rilanciata appostamente.
                 */
            } finally {
                LoggerUtil.debug ( "END THREADS INVIO RR ER" );
            }
        }

        countDownLatchBlocker.countDown();
        LoggerUtil.debug ( "END invoking paaInviaRichiestaRevoca" );
        return _return;
    }
    
    private boolean validateXMLSchema ( byte [] xml ) {
        try {
            SchemaFactory sf = SchemaFactory.newInstance ( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Source so = new StreamSource ( this.getClass ().getResourceAsStream ( "/META-INF/RR_ER_1_0_0.xsd" ) );
            Schema schema = sf.newSchema ( so );
            Validator validator = schema.newValidator ();
            LoggerUtil.info ( "Validator istanziato" );
            validator.validate ( new StreamSource ( new StringReader ( new String ( xml ) ) ) );
            LoggerUtil.info ( "validator.validate eseguito" );
        } catch ( Exception e ) {
            LoggerUtil.error ( "Errore in fase di validazione del messaggio: ", e );
            return false;
        }
        return true;
    }

    @Override
	@WebResult ( name = "paaInviaEsitoStornoRisposta", targetNamespace = "" )
    @RequestWrapper ( localName = "paaInviaEsitoStorno", targetNamespace = "http://ws.pagamenti.telematici.gov/",
                    className = "it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaEsitoStorno" )
    @ResponseWrapper ( localName = "paaInviaEsitoStornoRisposta", targetNamespace = "http://ws.pagamenti.telematici.gov/",
                    className = "it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaEsitoStornoRisposta" )
    @WebMethod ( action = "paaInviaEsitoStorno" )
    public TipoInviaEsitoStornoRisposta paaInviaEsitoStorno (
			@WebParam(name = "identificativoIntermediarioPA", targetNamespace = "") String identificativoIntermediarioPA,
			@WebParam(name = "identificativoStazioneIntermediarioPA", targetNamespace = "") String identificativoStazioneIntermediarioPA,
			@WebParam(name = "identificativoDominio", targetNamespace = "") String identificativoDominio,
			@WebParam(name = "identificativoUnivocoVersamento", targetNamespace = "") String identificativoUnivocoVersamento,
			@WebParam(name = "codiceContestoPagamento", targetNamespace = "") String codiceContestoPagamento,
			@WebParam(name = "er", targetNamespace = "") byte[] er) {
	    LoggerUtil.debug ( "paaInviaEsitoStorno" );
	    LoggerUtil.debug ( "paaInviaEsitoStorno" );
		return null;
	}

    /**
     * Reperisce l'interfaccia al servizio core che fornisce l'accesso al DB
     * 
     * @return
     * @throws NamingException
     * @throws CreateException
     * @throws RemoteException
     */
    private Payment reperisciPaymentInterface () throws NamingException, CreateException, RemoteException {
        InitialContext ctx = new InitialContext ();
        //Object o = ctx.lookup ( "mdppaymentsrv/ejb/PaymentManager" );
        //Object o = ctx.lookup ( "java:app/mdpcorecontext-business/PaymentManager!it.csi.mdp.core.business.paymentmanager.PaymentLocal" );
        Object o = ctx.lookup (Constants.PAYMENT_MANAGER_JNDI_PATH);
        PaymentHome home = (PaymentHome) o;
        Payment p = home.create ();
        return p;
    }

    /**
     * Aggiorna lo stato della RPT in base all'esito della RT ricevuta
     * 
     * @param richiestaTrovata
     * @param esito
     */
    private void aggiornaRPT ( RPT richiestaTrovata, String esito ) {

        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        richiestaTrovata.setIdStatiRpt ( "OK".equals ( esito ) ? StatiRPTEnum.RT_VERIFICATA.id () : StatiRPTEnum.RT_ERRATA.id () );

        try {
            Payment p = reperisciPaymentInterface ();
            p.aggiornaRPT ( richiestaTrovata );
        } catch ( Exception e ) {
            LoggerUtil.error ( "IMPOSSIBILE REGISTRARE LA RICHIESTA DI PAGAMENTO TELEMATICA", e );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "aggiornaRPT()", "[PagamentiTelematiciRTImpl]::[aggiornaRPT]", "(valore input omesso)" );
        }
    }

    /**
     * 
     * @param applicationId
     * @param transactionId
     * @param gatewayId
     * @param esito
     * @param tipoEvento
     * @param iuv
     * @param tipoVersamento
     * @param identificativoStazioneIntermediarioPA
     * @param identificativoDominio
     * @param idPsp
     * @param idIntermediarioPsp
     * @param parametriSpecificiInterfaccia
     * @param rt
     * @throws RemoteException
     */
    private void registraEventoGiornale ( String applicationId, String transactionId, String gatewayId, String esito, String tipoEvento, String iuv,
        String tipoVersamento, String identificativoStazioneIntermediarioPA, String identificativoDominio, String idPsp, String idIntermediarioPsp,
        String parametriSpecificiInterfaccia, CtRicevutaTelematica rt ) {
        StopWatch watcher = new StopWatch ( Constants.APP_CODE_PERF );
        watcher.start ();

        try {
            String codiceContestoPagamento = "n/a";
            if ( rt != null && rt.getDatiPagamento () != null && rt.getDatiPagamento ().getCodiceContestoPagamento () != null ) {
                codiceContestoPagamento = rt.getDatiPagamento ().getCodiceContestoPagamento ();
            }
            PersistenzaDatiNodoUtility.registraEventoGiornale ( applicationId, transactionId, gatewayId, esito, tipoEvento, iuv, tipoVersamento,
                identificativoStazioneIntermediarioPA, identificativoDominio, idPsp, idIntermediarioPsp, parametriSpecificiInterfaccia, "mdpnodospcservices",
                codiceContestoPagamento );
        } catch ( Exception e ) {
            LoggerUtil.error ( "IMPOSSIBILE REGISTRARE EVENTO GIORNALE", e );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "PagamentiTelematiciRTImpl", "registraEventoGiornale()", "[PagamentiTelematiciRTImpl]::[registraEventoGiornale]",
                "(valore input omesso)" );
        }
    }

    private EsitoPaaInviaRT componiEsitoFallimento ( EsitoPaaInviaRT esito, String faultCode, String faultString, String faultDescription, String idDominio ) {
        it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.FaultBean fault
            = new it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.FaultBean ();
        fault.setFaultCode ( faultCode );
        fault.setFaultString ( faultString );
        fault.setDescription ( faultDescription );
        fault.setId ( idDominio );
        esito.setFault ( fault );

        return esito;
    }

    private String generaMac ( String passphrase, String iuv, String applicationId, String idMsgRicevuta, String timestamp ) {

        String sToDigest = passphrase + "%%%%" + applicationId + iuv + idMsgRicevuta + timestamp + "%%%%" + passphrase;
        byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
        String mac = Base64.encodeBase64String ( bMac );
        mac = mac.substring ( 0, 35 );
        return mac;
    }
	
    private void nodoInviaRispostaRevoca(RR rr, ER er ) throws RemoteException, NamingException, CreateException, DaoException, MissingParameterException, UnsupportedEncodingException {
        LoggerUtil.debug ( "Start invoking nodoInviaRispostaRevoca" );
        
        if (null == rr.getApplicationId ()) {
        	throw new MissingParameterException("Application id mancante");
        }
        
        String endpoint = "";
        List<Applicationcustomfields> customFieldsList
            = PersistenzaDatiNodoUtility.recuperaCustomFieldDecoded ( rr.getApplicationId (), CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO );
        
        for(Applicationcustomfields item:customFieldsList) {
            if(item.getFieldname ().equalsIgnoreCase ( CostantiNodoSpc.APP_PARAM_PORTA_DI_DOMINIO )) {
                endpoint = item.getFieldvalue ();
            }
        }
        
        LoggerUtil.debug ( "endpoint: " + endpoint );
        JaxWsProxyFactoryBean factory = CoreUtilities.inizializzaServizio(endpoint, "nodoInviaRispostaRevoca");
        PagamentiTelematiciRPT pagamentiTelematiciService = null;
        try {
            pagamentiTelematiciService = (PagamentiTelematiciRPT)factory.create();
        } catch ( Exception e ) {
            LoggerUtil.error ( "errore in fase di creazione del servizio pagamentiTelematiciService: ", e );
        }
        LoggerUtil.debug ( "Creato PagamentiTelematiciRPT!" );
        NodoInviaRispostaRevoca nodoInviaRispostaRevoca = new NodoInviaRispostaRevoca();
        nodoInviaRispostaRevoca.setCodiceContestoPagamento(rr.getCodiceContestoPagamento());
        nodoInviaRispostaRevoca.setEr( er.getXmlEr () );
        nodoInviaRispostaRevoca.setIdentificativoDominio(rr.getIdDominio());
        
        List<Applicationcustomfields> passwordDominioNodoSpcCustomFieldsList = PersistenzaDatiNodoUtility.recuperaCustomFieldDecoded(rr.getApplicationId (), CostantiNodoSpc.APP_PARAM_PASSWORD_DOMINIO_NODO_SPC);
        LoggerUtil.debug ( "Recuperato: " + CostantiNodoSpc.APP_PARAM_PASSWORD_DOMINIO_NODO_SPC );
        String passwordNodo = "";
        for(Applicationcustomfields item:passwordDominioNodoSpcCustomFieldsList) {
            if(item.getFieldname ().equalsIgnoreCase ( CostantiNodoSpc.APP_PARAM_PASSWORD_DOMINIO_NODO_SPC )) {
            	passwordNodo = item.getFieldvalue ();
                break;
            }
        }
        nodoInviaRispostaRevoca.setPassword(passwordNodo);
        
        List<Applicationcustomfields> identificativoStazioneIntermediarioPACustomFieldsList = PersistenzaDatiNodoUtility.recuperaCustomFieldDecoded(rr.getApplicationId (), CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA);
        LoggerUtil.debug ( "Recuperato: " + CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA );
        String identificativoStazioneIntermediarioPA = "";
        for(Applicationcustomfields item:identificativoStazioneIntermediarioPACustomFieldsList) {
            if(item.getFieldname ().equalsIgnoreCase ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA ) ) {
                identificativoStazioneIntermediarioPA = item.getFieldvalue ();
                break;
            }
        }
        nodoInviaRispostaRevoca.setIdentificativoStazioneIntermediarioPA(identificativoStazioneIntermediarioPA);
        
        List<Applicationcustomfields> identificativointermediarioPACustomFieldsList = PersistenzaDatiNodoUtility.recuperaCustomFieldDecoded(rr.getApplicationId (), CostantiNodoSpc.APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA);
        
        String identificativointermediarioPA = "";
        for(Applicationcustomfields item:identificativointermediarioPACustomFieldsList) {
            if(item.getFieldname ().equalsIgnoreCase ( CostantiNodoSpc.APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA )) {
                identificativointermediarioPA = item.getFieldvalue ();
                break;
            }
        }
        nodoInviaRispostaRevoca.setIdentificativoIntermediarioPA(identificativointermediarioPA);
        nodoInviaRispostaRevoca.setIdentificativoUnivocoVersamento(rr.getIuv());
        NodoInviaRispostaRevocaRisposta response = null;
        try {
            response = pagamentiTelematiciService.nodoInviaRispostaRevoca(nodoInviaRispostaRevoca);
        } catch ( Exception e ) {
            LoggerUtil.error ( "errore in fase di invocazione del servizio nodoInviaRispostaRevoca: ", e );
        }
        
        LoggerUtil.debug ( "Esito: " + ( null != response ? response.getEsito () : "" ) + " Err_msg:"
            + ( ( null != response && null != response.getFault () ) ? response.getFault ().getFaultString () : "" ) );
        
        LoggerUtil.debug ( "End invoking nodoInviaRispostaRevoca" );
    }

	
	
	private ER createER(RR rr) {
	    LoggerUtil.debug ( "Start invoking createER" );
	    ER er = new ER();
	    er.setIdDominio(rr.getIdDominio());
	    er.setApplicationId(rr.getApplicationId());
	    er.setIdentificativoMessaggioEsito(rr.getIdentificativoMessaggioRevoca());
	    er.setDataOraMessaggioEsito(rr.getDataOraMessaggioRevoca());
	    er.setRiferimentoMessaggioRevoca(rr.getIdentificativoMessaggioRevoca());
	    er.setRiferimentoDataRevoca(rr.getDataOraMessaggioRevoca());
	    er.setCodiceIdentificativoUnivocoAttestante(rr.getCodiceIdentificativoUnivocoAttestante());
	    er.setDenominazioneIstitutoAttestante(rr.getDenominazioneIstitutoAttestante());
	    er.setImportoTotaleRevocato(rr.getImportoTotaleRevocato());
	    er.setIuv(rr.getIuv());
	    er.setCodiceContestoPagamento(rr.getCodiceContestoPagamento());
	    
	    List<DatiSingoloEsito> listaDatiSingoloEsito = new ArrayList<DatiSingoloEsito>();
	    
	    for(DatiSingolaRevoca singolaRevoca:rr.getListaDatiSingolaRevoca()) {
	        DatiSingoloEsito datiSingoloEsito = new DatiSingoloEsito();
	        datiSingoloEsito.setSingoloImportoRevocato(singolaRevoca.getSingoloImportoRevocato());
	        datiSingoloEsito.setIur(singolaRevoca.getIur());
	        datiSingoloEsito.setCausaleEsito(singolaRevoca.getCausaleRevoca());
	        datiSingoloEsito.setDatiAggiuntiviEsito(singolaRevoca.getDatiAggiuntiviRevoca());
	        listaDatiSingoloEsito.add(datiSingoloEsito);
	    }
	    er.setListaDatiSingoloEsito(listaDatiSingoloEsito);
	    
	    return er;
	}

	private String getApplicationFromIuv(String iuv) {
	    String appId = "";
	    try {
	        appId = PersistenzaDatiNodoUtility.recuperaApplicationIdFromIuv(iuv);
	        
	        if (StringUtils.isEmpty ( appId )) {
	            throw new Exception("IMPOSSIBILE RECUPERARE L'APPLICATIONID DALLO IUV");
	        }
	    } catch (Exception e) {
            LoggerUtil.error("IMPOSSIBILE RECUPERARE L'APPLICATIONID DALLO IUV: <"+iuv+">", e);
        }
	    
	    return appId;
	}
	
	/*
	 * Metodo di deserializzazione stream XML della richiesta di revoca nel DTO JAXB di mapping
	 */
	private CtRichiestaRevoca unmarshallER(byte[] xmlRevoca) {
	       // FIXED CSI_PAG-333 MDPNEW-95 BEGIN
        JAXBContext jaxbContext;
        CtRichiestaRevoca ctRichiestaRevoca = null;
        try {
            jaxbContext = JAXBContext.newInstance ( "it.gov.digitpa.schemas._2011.pagamenti.revoche" );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller ();
            @SuppressWarnings ( "unchecked" )
            JAXBElement<CtRichiestaRevoca> element = (JAXBElement<CtRichiestaRevoca>) jaxbUnmarshaller.unmarshal ( new ByteArrayInputStream ( xmlRevoca ) );
            ctRichiestaRevoca = element.getValue ();
        } catch ( JAXBException e ) {
            LoggerUtil.error ( "Errore in fase di parsificazione del messaggio: ", e );
        }
        return ctRichiestaRevoca;
        // FIXED CSI_PAG-333 MDPNEW-95 END
	}
	
	private RR ctRichiestaRevocaToRR( CtRichiestaRevoca ctRichiestaRevoca)  throws ParseException{
		
	    RR rr = new RR();
	    
	    String iuv = ctRichiestaRevoca.getDatiRevoca ().getIdentificativoUnivocoVersamento ();
	    Assert.hasText(iuv,"IUV non presente");
	    String applidationId = getApplicationFromIuv(iuv);
        Assert.hasText ( applidationId, "Applicazione non trovata per IUV: <" + applidationId+">" );
        rr.setApplicationId ( applidationId );
	    
	    String IdDominio = ctRichiestaRevoca.getDominio ().getIdentificativoDominio ();
	    Assert.hasText ( IdDominio, "Identificativo dominio/Ente non presente");
	    rr.setIdDominio ( IdDominio );
        
        // identificatio e ora del messaggio
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date parsedDate = dateFormat.parse(ctRichiestaRevoca.getDataOraMessaggioRevoca().toString());
        Timestamp timestamp = new Timestamp(parsedDate.getTime());    
        rr.setDataOraMessaggioRevoca(timestamp);
        rr.setIdentificativoMessaggioRevoca(ctRichiestaRevoca.getIdentificativoMessaggioRevoca());
        
        // blocco dati attestante
	    CtIstitutoAttestante ia=  ctRichiestaRevoca.getIstitutoAttestante();
	    Assert.isTrue((ia!=null),"Istituto Attestante non presente");
	    String ium = ia.getIdentificativoUnivocoMittente().getCodiceIdentificativoUnivoco();
	    Assert.hasText(ium,"Identificativo Univoco Mittente non presente");
        rr.setCodiceIdentificativoUnivocoAttestante(ium);
        String dm = ia.getDenominazioneMittente();
        Assert.hasText(dm,"Denominazione Mittente non presente");
        rr.setDenominazioneIstitutoAttestante(dm);
      
        /* blocco dati revoca */        
        rr.setImportoTotaleRevocato(ctRichiestaRevoca.getDatiRevoca().getImportoTotaleRevocato().doubleValue());
        rr.setIuv(iuv);
        if(ctRichiestaRevoca.getDatiRevoca().getTipoRevoca() != null)
        	rr.setTipoRevoca(Double.parseDouble(ctRichiestaRevoca.getDatiRevoca().getTipoRevoca()));//MA CHI E' IL PIRLA CHE CAVOLO HA CREATO UN TIPO A DOUBLE SU MDP!!!!
        
        rr.setCodiceContestoPagamento ( ctRichiestaRevoca.getDatiRevoca ().getCodiceContestoPagamento () );
        
        List<DatiSingolaRevoca> listaSingoleRevoche = new ArrayList<DatiSingolaRevoca>();
        
        for(CtDatiSingolaRevoca ctDatiSingolaRevoca: ctRichiestaRevoca.getDatiRevoca().getDatiSingolaRevoca()) {
            DatiSingolaRevoca datiSingolaRevoca = new DatiSingolaRevoca();
            datiSingolaRevoca.setCausaleRevoca(ctDatiSingolaRevoca.getCausaleRevoca());
            datiSingolaRevoca.setDatiAggiuntiviRevoca(ctDatiSingolaRevoca.getDatiAggiuntiviRevoca());
            datiSingolaRevoca.setIur(ctDatiSingolaRevoca.getIdentificativoUnivocoRiscossione());
            datiSingolaRevoca.setSingoloImportoRevocato(ctDatiSingolaRevoca.getSingoloImportoRevocato().doubleValue());
            listaSingoleRevoche.add(datiSingolaRevoca);
        }
        rr.setListaDatiSingolaRevoca(listaSingoleRevoche);
        
        return rr;
	}
	
    /**
     * Metodo privato che crea un oggetto revoca per inviarlo al nodo e salvarlo.
     * 
     * @param er oggetto interno a mdp
     * @param ctRichiestaRevoca
     * @return CtEsitoRevoca esito revoca creata.
     */
    private CtEsitoRevoca creaEsitoRevoca ( CtRichiestaRevoca ctRichiestaRevoca ) {
        CtEsitoRevoca retER = new CtEsitoRevoca ();
        retER.setVersioneOggetto ( ctRichiestaRevoca.getVersioneOggetto () );
        retER.setDominio ( ctRichiestaRevoca.getDominio () );
        retER.setIdentificativoMessaggioEsito ( "ER_" + ctRichiestaRevoca.getIdentificativoMessaggioRevoca () ); //:TODO
        try {
            retER.setDataOraMessaggioEsito (
                DatatypeFactory.newInstance ().newXMLGregorianCalendar ( new SimpleDateFormat ( "yyyy-MM-dd'T'hh:mm:ss" ).format ( new Date () ) ) );
            retER.setRiferimentoDataRevoca ( DatatypeFactory.newInstance().newXMLGregorianCalendarDate(ctRichiestaRevoca.getDataOraMessaggioRevoca ().getYear(), ctRichiestaRevoca.getDataOraMessaggioRevoca ().getMonth (),
                ctRichiestaRevoca.getDataOraMessaggioRevoca ().getDay (), DatatypeConstants.FIELD_UNDEFINED) );
        } catch ( DatatypeConfigurationException e ) {
            LoggerUtil.warn ( "Errore in fase di creazione della data corrente del messaggio di esito della revoca: "
                + ctRichiestaRevoca.getIdentificativoMessaggioRevoca () );
        }
        retER.setRiferimentoMessaggioRevoca ( ctRichiestaRevoca.getIdentificativoMessaggioRevoca () );
        
        retER.setIstitutoAttestante ( ctRichiestaRevoca.getIstitutoAttestante () );
        retER.setSoggettoVersante ( ctRichiestaRevoca.getSoggettoVersante () );
        retER.setSoggettoPagatore ( ctRichiestaRevoca.getSoggettoPagatore () );
        // creazione dati esito revoca
        CtDatiEsitoRevoca ctDatiEsitoRevoca = new CtDatiEsitoRevoca ();
        ctDatiEsitoRevoca.setCodiceContestoPagamento ( ctRichiestaRevoca.getDatiRevoca ().getCodiceContestoPagamento () );
        ctDatiEsitoRevoca.setIdentificativoUnivocoVersamento ( ctRichiestaRevoca.getDatiRevoca ().getIdentificativoUnivocoVersamento () );
        ctDatiEsitoRevoca.setImportoTotaleRevocato ( ctRichiestaRevoca.getDatiRevoca ().getImportoTotaleRevocato () );
        for ( CtDatiSingolaRevoca singolaRevoca: ctRichiestaRevoca.getDatiRevoca ().getDatiSingolaRevoca () ) {
            CtDatiSingoloEsitoRevoca esitoRevoca = new CtDatiSingoloEsitoRevoca ();
            esitoRevoca.setCausaleEsito ( singolaRevoca.getCausaleRevoca () );
            esitoRevoca.setDatiAggiuntiviEsito ( singolaRevoca.getDatiAggiuntiviRevoca () );
            esitoRevoca.setIdentificativoUnivocoRiscossione ( singolaRevoca.getIdentificativoUnivocoRiscossione () );
            esitoRevoca.setSingoloImportoRevocato ( singolaRevoca.getSingoloImportoRevocato () );
            ctDatiEsitoRevoca.getDatiSingolaRevoca ().add ( esitoRevoca );
        }

        retER.setDatiRevoca ( ctDatiEsitoRevoca );
        return retER;
    }
	
	private String erToXml ( CtEsitoRevoca er ) {
	    JAXBContext jaxbContext;
	    StringWriter ret = new StringWriter ();
	    try {
	        jaxbContext = JAXBContext.newInstance ("it.gov.digitpa.schemas._2011.pagamenti.revoche");
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller ();
	        jaxbMarshaller.setProperty ( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE ); // To format XML
	        jaxbMarshaller.marshal ( new ObjectFactory ().createER ( er ) , ret );
	        return ret.toString ();
	    } catch ( JAXBException e ) {
	        LoggerUtil.error ( "Errore in fase di creazione del messaggio: ", e );
	        return "";
	    }
	}
}


class Singleton {

    private static volatile Singleton instance = null;

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool ( 80 );

    private static int poolSize = 5;    
    private static int poolTout = 5;
        
    private Singleton () {
        Properties propsEnv = new Properties ();

        try {
            propsEnv.load ( this.getClass ().getResourceAsStream ( "/META-INF/env.properties" ) );

            LoggerUtil.debug ( "THREAD - PROPERTIES: " + propsEnv.getProperty ( "poolSizeRT" ) );
            LoggerUtil.debug ( "THREAD - PROPERTIES: " + propsEnv.getProperty ( "poolTimeoutRT" ) );

            String size = propsEnv.getProperty ( "poolSizeRT" );
            String tout = propsEnv.getProperty ( "poolTimeoutRT" );

        
            poolSize = Integer.parseInt ( size );
            poolTout = Integer.parseInt ( tout );
            
        } catch ( Exception e ) {
            LoggerUtil.error ( "Errore in fase di gestione del singleton", e );
        }

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool ( poolSize );
    }
    
    public void submit(Callable<String> task) {
    	executor.submit(task);
    }
    
    public void addThread ( Callable<String> thread ) throws InterruptedException {

        java.util.Collection<Callable<String>> list = new java.util.LinkedList<Callable<String>> ();

        list.add ( thread );

        executor.invokeAll ( list, poolTout, TimeUnit.SECONDS );

        LoggerUtil.debug ( "THREAD - SIZE POOL :" + executor.getPoolSize () );
        LoggerUtil.debug ( "THREAD - SIZE CODA :" + executor.getQueue ().size () );
        LoggerUtil.debug ( "THREAD - ATTIVI :" + executor.getActiveCount ());
        LoggerUtil.debug ( "THREAD - COMPLETATI :" + executor.getCompletedTaskCount ());
        LoggerUtil.debug ( "THREAD - MASSIMI :" + executor.getMaximumPoolSize ());
    }

    public static Singleton getInstance () {
        if ( instance == null ) {
            synchronized ( Singleton.class ) {
                if ( instance == null ) {
                    instance = new Singleton ();
                }
            }
        }
        return instance;
    }
    
}
