/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import com.thoughtworks.xstream.XStream;
import it.csi.mdp.clientmod3.EsitoRiceviRT;
import it.csi.mdp.clientmod3.ParametriRiceviRT;
import it.csi.mdp.clientmod3.Serviziorissrvspc;
import it.csi.mdp.generatedvo.pagamenti.CtRicevutaTelematica;
import it.csi.mdp.mdpetl.dto.CodaInvioRT;
import it.csi.mdp.mdpetl.dto.DatiRicevutaNonInviata;
import it.csi.mdp.mdpetl.dto.LoggingRT;
import it.csi.mdp.mdpetl.dto.RT;
import it.csi.mdp.mdpetl.integration.util.dao.*;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;
import it.csi.mdp.mdpetl.util.UtilDate;
import it.csi.mdp.utility.CostantiNodoSpc;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.soap.util.mime.ByteArrayDataSource;
import org.xml.sax.SAXException;

import javax.activation.DataHandler;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReinvioRT {

    static LogUtil log = new LogUtil ( ReinvioRT.class );

    private SimpleDateFormat sdf = new SimpleDateFormat ( "ddmmyyyy-hh:mm:ss:ms" );

    public void elaborazioneReinvio ( byte [] key ) throws Exception {

        String methodName = "elaborazioneReinvio";
        log.startMethod ( methodName );
        Set<String> iuvInviati = new HashSet<String> ();
        String applicationIdCorrente = null;
        Map<String, String> mappaAppCustomFieldsCorrente = null;
        
        
        long  sleepReinvio= Long.parseLong(new EstraiConfigurazioneDAO ( Constants.SLEEP_REINVIO_RT ).executeQuery ().getValue ());
        log.info ( methodName, "sleepReinvio : " +sleepReinvio );
        System.out.println("sleepReinvio : " +sleepReinvio );
      
        String limiteLetturaRecordReinvioRt=  new EstraiConfigurazioneDAO ( Constants.LIMITE_LETTURA_RECORD_REINVIO_RT ).executeQuery ().getValue ();
        log.info ( methodName, "limiteLetturaRecordReinvioRt : " +limiteLetturaRecordReinvioRt );
        System.out.println("limiteLetturaRecordReinvioRt : " +limiteLetturaRecordReinvioRt );
		
        Integer  limiteNumGiorniReinvioRt= new Integer( new EstraiConfigurazioneDAO ( Constants.LIMITE_NUM_GIORNI_REINVIO_RT ).executeQuery ().getValue ());
        log.info ( methodName, "limiteNumGiorniReinvioRt : " +limiteNumGiorniReinvioRt );
        System.out.println("limiteNumGiorniReinvioRt : " +limiteNumGiorniReinvioRt );
        
        String dataPrioritariaReinvio=  new EstraiConfigurazioneDAO ( Constants.DATA_PRIORITARIA_REINVIO_RT ).executeQuery ().getValue ();
        log.info ( methodName, "dataPrioritariaReinvio : " +dataPrioritariaReinvio );
        System.out.println("dataPrioritariaReinvio : " +dataPrioritariaReinvio );
        
        String applicazionePrioritariaReinvio=  new EstraiConfigurazioneDAO ( Constants.APPLICAZIONE_PRIORITARIA_REINVIO_RT ).executeQuery ().getValue ();
        log.info ( methodName, "applicazionePrioritariaReinvio : " +applicazionePrioritariaReinvio );
        System.out.println("applicazionePrioritariaReinvio : " +applicazionePrioritariaReinvio );
        
        List<DatiRicevutaNonInviata> elencoRicevuteNonInviate = ottieniEstraiRicevuteNonInviateDAO(limiteLetturaRecordReinvioRt, limiteNumGiorniReinvioRt, 
        																							dataPrioritariaReinvio, applicazionePrioritariaReinvio,key);
      
        XStream xs = new XStream ();
        Unmarshaller unmarshaller = inizializzaUnMarshallerRT ();
        log.info ( methodName, "Numero di RT da inviare: " + ( null != elencoRicevuteNonInviate ? elencoRicevuteNonInviate.size () : "0" ) );
        System.out.println( "Numero di RT da inviare: " + ( null != elencoRicevuteNonInviate ? elencoRicevuteNonInviate.size () : "0" ) );
        // log.info(methodName, xs.toXML(elencoRicevuteNonInviate)); per motivi di performance spostarlo all'interno del cliclo for e stamparlo uno alla volta.
      
      
        
        for ( DatiRicevutaNonInviata ricevuta: elencoRicevuteNonInviate ) {
            try {
                log.info ( methodName, "Trasmissione in corso della RT con IUV: " + ricevuta.getIuv () );
                System.out.println( "Trasmissione in corso della RT con IUV: " + ricevuta.getIuv () );
                log.info ( methodName, xs.toXML ( ricevuta ) );
                if ( applicationIdCorrente == null || !ricevuta.getApplicationId ().equals ( applicationIdCorrente ) ) {
                    applicationIdCorrente = ricevuta.getApplicationId ();
                    mappaAppCustomFieldsCorrente = new ParametriNodoSpcDAO( key ).getMappaApplicationCustomFieldsEnabled ( applicationIdCorrente );
                }
               
                    	 Timestamp dataTentativi= new Timestamp(System.currentTimeMillis());
                     	ricevuta.getCoda().setDataTentativi(dataTentativi);
                     	new AggiornaDataTentativiCodaInvioRtDAO(ricevuta.getCoda().getDataTentativi(), ricevuta.getIuv()).executeUpdate();
                     	
                     	
                     		if (null == ricevuta.getCoda().getDataInizioTentativi())
                     		{
                     			ricevuta.getCoda().setDataInizioTentativi(dataTentativi);
                     			new AggiornaDataInizioTentativiCodaInvioRtDAO(
                     					ricevuta.getCoda().getDataInizioTentativi(), 
                     					ricevuta.getIuv()).executeUpdate();
                     		}
                     		
                     		
                     		
                    		
                     		EsitoRiceviRT esito = spedisciRTAlFruitore ( ricevuta, mappaAppCustomFieldsCorrente, unmarshaller );
//                     		
//                     		EsitoRiceviRT esito = new EsitoRiceviRT();
//                     		esito.setCodiceErrore("KO");
//                     		esito.setMessaggioErrore("errore invio al fornitore");
                             
                             if ( Constants.ESITO_OK.equals ( esito.getEsito () ) ) {
                                 iuvInviati.add ( ricevuta.getIuv () );
                                 new ModificaRTInvioRiuscitoDAO( ricevuta.getId () ).executeUpdate ();
                                 loggaInvioEsito ( Constants.ESITO_OK, "Reinvio RT", "-", ricevuta.getIuv ());
                             } else {
                                 log.warn ( methodName, "Invio fallito per la RT con IUV: " + ricevuta.getIuv () );
                                 System.out.println( "Invio fallito per la RT con IUV: " + ricevuta.getIuv () );
                                 new ModificaRTDataInvioFallitoDAO ( ricevuta.getId () ).executeUpdate ();
//                          	contatore tentativi + ultimo esito fruitore + data ultima modifica
                                 Integer numeroGiorniKo = calcolaNumGiorniTentativiKo(dataPrioritariaReinvio,
											applicazionePrioritariaReinvio, ricevuta);
                                 new AggiornaCodaInvioRtInvioFallitoDAO(
                                		 ricevuta.getCoda().getContatoreTentativi()+1,
                                		 numeroGiorniKo, 
                                        ( StringUtils.isEmpty ( esito.getMessaggioErrore () ) ) ? "Errore Generico" : esito.getMessaggioErrore (),
                                		 ricevuta.getIuv()
                                		 ).executeUpdate();
                                 if (numeroGiorniKo>limiteNumGiorniReinvioRt)
                                 {
                            		 log.info ( methodName, "limite massimo giorni ("+limiteNumGiorniReinvioRt+") raggiunto, reinvio non possible per iuv: " + ricevuta.getIuv () );
                                    System.out.println( "limite massimo giorni ("+limiteNumGiorniReinvioRt+") raggiunto, reinvio non possible per iuv: " + ricevuta.getIuv () );
                                		new AggiornaStatoInvioFruitoreRT( "3T", "'" + ricevuta.getIuv() + "'" ).executeUpdate ();
                                 }
                                 loggaInvioEsito ( Constants.ESITO_KO, "Reinvio RT: " + null != esito.getMessaggioErrore ()? "Errore Generico" : esito.getMessaggioErrore (), "-", ricevuta.getIuv () );
                             }
//                     	}
////               
////                // settare a 0 il contatore dei tentativi KO per quel application id
////                /**
////                 * modificare la tabella rt_coda_invio aggiungendo le colonne: num_tentativo, magari con un massimale e un default con 0 esito ultimo tentativo
////                 * -> OK/KO ultima_risposta fruitore come String. data_ultima_modifica -> nullable da valorizzare in caso di tentivi. se esito OK e la data > di
////                 * un mese si cancella
////                 * 
////                 * aggiornare la RT:
////                 * 1: stato_invio_fruitore = OK
////                 * 3: data_invio_fruitore aggiornare
////                 * 4: last_update
////                 * 5: eliminare lo iuv dalla tabella rt_coda_invio
////                 */
            } catch ( Exception e ) {
                log.error ( methodName, "Errore in fase di invio della RT con IUV: " + ricevuta.getIuv (), e );
                e.printStackTrace ();
            } finally {
                log.info ( methodName, "Fine trasmissione della RT con IUV: " + ricevuta.getIuv () );
                System.out.println( "Fine trasmissione della RT con IUV: " + ricevuta.getIuv () );
                Thread.sleep(sleepReinvio);
            }
        }

        log.info ( methodName, "IUV DA CANCELLARE:" + xs.toXML ( iuvInviati ) );
        System.out.println( "IUV DA CANCELLARE:" + xs.toXML ( iuvInviati ) );
        StringBuilder sb = new StringBuilder ();
        for ( String s: iuvInviati ) {
            sb.append ( "'" ).append ( s ).append ( "'" ).append ( "," );
        }
        String levaUltimaVisrgola = sb.toString ();
        if ( levaUltimaVisrgola.length () != 0 ) {
            levaUltimaVisrgola = levaUltimaVisrgola.substring ( 0, levaUltimaVisrgola.length () - 1 );

            new DeleteRtCodaInviateDAO ( levaUltimaVisrgola ).executeUpdate ();
        }
        
       
       inserimentoInCodaRtStatoInvioFruitoreNull(methodName, iuvInviati, xs);
       
//       modifica dello stato della RT per gli iuv  che sono stati inseriti nella atbella delle code
       
        log.stopMethod ( methodName );
    }

	private int calcolaNumGiorniTentativiKo(String dataPrioritariaReinvio, String applicazionePrioritariaReinvio,
			DatiRicevutaNonInviata ricevuta) {
		return (StringUtils.isEmpty(dataPrioritariaReinvio) && StringUtils.isEmpty(applicazionePrioritariaReinvio) )?new Integer (differenzaGiorni(ricevuta))+1:
			 ricevuta.getCoda().getNumGiorniTentativiKo();
	}

    private void loggaInvioEsito ( String esito, String messErrore, String denominazioneMittente, String iuv ) {
        LoggingRT loggingRT = new LoggingRT ();
        loggingRT.setDataOraInvio ( new Timestamp ( ( new Date () ).getTime () ) );
        loggingRT.setErrori ( org.apache.commons.lang.StringUtils.substring ( messErrore, 0, 255 ) );
        loggingRT.setEsito ( esito );
        loggingRT.setIstitutoMittente ( denominazioneMittente );
        loggingRT.setIuv ( iuv );
        try {
            new InserisciLoggingRTDAO ( loggingRT ).executeUpdate ();
        } catch ( Exception e ) {
            log.error ( "loggaInvioEsito", "Errore in fase di registrazione dell'esito sulla tabella logging_rt! ", e );
            e.printStackTrace ();
        }
    }
    
	private List<DatiRicevutaNonInviata> ottieniEstraiRicevuteNonInviateDAO(String limiteLetturaRecordReinvioRt, Integer limiteNumGiorniReinvioRt,
			String dataPrioritariaReinvio, String applicazionePrioritariaReinvio,  byte[]sKey)
					throws SQLException, NamingException, Exception, SerialException {

		if (!StringUtils.isEmpty(applicazionePrioritariaReinvio))
		{
			Set<String> idapp = new HashSet<String>(Arrays.asList(applicazionePrioritariaReinvio.split(",")));
			applicazionePrioritariaReinvio= ottieniListaInsertUpdateIn(idapp);
			if (!StringUtils.isEmpty(dataPrioritariaReinvio))
			{
				return new EstraiRicevuteNonInviateDataPrioritariaIdApplicationDAO( limiteLetturaRecordReinvioRt, dataPrioritariaReinvio, applicazionePrioritariaReinvio,limiteNumGiorniReinvioRt).executeQuery ();
			} 
			else
			{
				return new EstraiRicevuteNonInviateDataPrioritariaIdApplicationDAO( limiteLetturaRecordReinvioRt,  applicazionePrioritariaReinvio,limiteNumGiorniReinvioRt).executeQuery ();
			}
		}
		else if (!StringUtils.isEmpty(dataPrioritariaReinvio))
		{
			return new EstraiRicevuteNonInviateDataPrioritariaDAO( limiteLetturaRecordReinvioRt, dataPrioritariaReinvio,limiteNumGiorniReinvioRt, sKey).executeQuery ();
		}
		else
		{
			return new EstraiRicevuteNonInviateDAO ( limiteLetturaRecordReinvioRt, limiteNumGiorniReinvioRt, sKey).executeQuery ();
		}
	}

    private void inserimentoInCodaRtStatoInvioFruitoreNull ( String methodName, Set<String> iuvInviati, XStream xs )
                    throws SQLException, NamingException, Exception, SerialException {
        Set<String> iuvInseritiNellaCodaRt = new HashSet<String> ();
        //     selezionare dalla  rt i record che hanno stato_invio_fruitore a null 
        List<RT> rtnotNull = new EstraiRTStatoInvioFuitoreNullDAO ().executeQuery ();
        //       insert nella tabella delle code rt
        for ( RT rtDaInserire: rtnotNull ) {
            try {
                log.info ( methodName, "Inserimento in corso della coda RT con IUV: " + rtDaInserire.getIuv () );
                inserisciCodaInvioRT ( rtDaInserire );
                iuvInseritiNellaCodaRt.add ( rtDaInserire.getIuv () );
            } catch ( SQLException e ) {
                log.warn ( methodName, " Inserimento nella tabella codaInvioRt fallito per la RT con esito invio null e IUV: " + rtDaInserire.getIuv (), e );
                System.out.println ( "Inserimento nella tabella codaInvioRt fallito per la RT con esito invio null e IU: " + rtDaInserire.getIuv () );
            } catch ( Exception e ) {
                log.warn ( methodName, " Inserimento nella tabella codaInvioRt fallito per la RT con esito invio null e IUV: " + rtDaInserire.getIuv (), e );
                System.out.println ( "Inserimento nella tabella codaInvioRt fallito per la RT con esito invio null e IU: " + rtDaInserire.getIuv () );
            }
        }

        log.info ( methodName, "IUV INSERITI NELLA CODA RT DA AGGIORNARE SU RT: " + xs.toXML ( iuvInseritiNellaCodaRt ) );
        System.out.println ( "IUV INSERITI NELLA CODA RT DA AGGIORNARE SU RT: " + xs.toXML ( iuvInseritiNellaCodaRt ) );
        if ( !iuvInseritiNellaCodaRt.isEmpty () ) {
            aggiornaStatoInvioFruitoreRT ( Constants.ESITO_KO, iuvInseritiNellaCodaRt );
        }
    }

	private boolean stessoGiorno(DatiRicevutaNonInviata ricevuta) {
//		Calendar cal1 = Calendar.getInstance();
		
//		cal1.setTimeInMillis(ricevuta.getDataInizioTentativi().getTime());
		
		return differenzaGiorni(ricevuta)<ricevuta.getCoda().getNumGiorniTentativiKo()+1;
	}
	
	private int  differenzaGiorni(DatiRicevutaNonInviata ricevuta) {
		
		 Date dataInizioTentativi= eliminaOreMinutiSecondi(ricevuta.getCoda().getDataInizioTentativi());
		 Date dataTentativi =  eliminaOreMinutiSecondi(ricevuta.getCoda().getDataTentativi());
		 
		 
		Long differenza = dataTentativi.getTime() -
				dataInizioTentativi.getTime();
		
		return new Long (differenza /1000 / 60 / 60/24).intValue();
	}

	private Date eliminaOreMinutiSecondi(Timestamp data) {
		Date dataProva= new Date(data.getTime());
		 String formattata=UtilDate.formatDate(dataProva,"yyyy-MM-dd" );
		 return UtilDate.parseDate(formattata, "yyyy-MM-dd");
	}

    private EsitoRiceviRT spedisciRTAlFruitore ( DatiRicevutaNonInviata rt, Map<String, String> mappaAcf, Unmarshaller unmarshaller ) {

        log.startMethod ( "[PagamentiTelematiciRTImpl::spedisciRTAlFruitore]" );

        try {
            Map<String, Object> props = new HashMap<String, Object> ();
            props.put ( "mtom-enabled", Boolean.TRUE );

            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean ();
            factory.setProperties ( props );
            factory.getInInterceptors ().add ( new LoggingInInterceptor () );
            factory.getOutInterceptors ().add ( new LoggingOutInterceptor () );
            factory.setServiceClass ( Serviziorissrvspc.class );
            factory.setAddress ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_ENDPONTI_SERVIZI_FRUITORE ) );
            Serviziorissrvspc iRiceviRT = (Serviziorissrvspc) factory.create ();

            ParametriRiceviRT ricevuta = new ParametriRiceviRT ();
            ricevuta.setApplicationId ( rt.getApplicationId () );
            CtRicevutaTelematica ricevutaTelematica
                = (CtRicevutaTelematica) unmarshaller.unmarshal ( new ByteArrayInputStream ( rt.getRtData () ) );
           
            ricevuta.setCodEsitoPagamento ( ricevutaTelematica.getDatiPagamento ().getCodiceEsitoPagamento () );
            ricevuta.setDataOraMsgRicevuta ( UtilDate.convertiTimestampInXmlGregorianCalendar ( rt.getDataMsgRicevuta () ) );
            ricevuta.setDescEsitoPagamento ( CostantiNodoSpc.mappaCodiciEsitoPagamento.get ( ricevutaTelematica.getDatiPagamento ().getCodiceEsitoPagamento () ) );
            ricevuta.setIdMsgRicevuta ( rt.getIdMsgRicevuta () );
            ricevuta.setIdMsgRichiesta ( rt.getIdMsgRichiesta () );
            ricevuta.setIuv ( rt.getIuv () );
            String timestamp = null;
            timestamp = sdf.format ( new Date () );
            ricevuta.setMac ( generaMac ( mappaAcf.get ( CostantiNodoSpc.APP_PARAM_PASSPHRASE_FRUITORE ), rt.getIuv (),
                ricevuta.getApplicationId (), rt.getIdMsgRicevuta (), timestamp ) );
            ByteArrayDataSource rawData = new ByteArrayDataSource ( rt.getRtData (), "application/octet-stream" );
            ricevuta.setRtData ( new DataHandler ( rawData ) );
            ricevuta.setTimestamp ( timestamp );
            ricevuta.setTipoFirma ( rt.getTipoFirma () );
            ricevuta.setTransactionId ( rt.getTransactionId () );
            EsitoRiceviRT esitoRT = iRiceviRT.riceviRT ( ricevuta );
            
            // Se il fruotire risponde pagamento duplicato o annullato considero la RT già inviata.
            if ( !StringUtils.isEmpty ( esitoRT.getCodiceErrore () ) && ( Constants.PAA_PAGAMENTO_DUPLICATO.equals ( esitoRT.getCodiceErrore () ) ||
                Constants.PAA_PAGAMENTO_ANNULLATO.equals ( esitoRT.getCodiceErrore () ) ) ) {
//                return "OK";
            	esitoRT = new EsitoRiceviRT();
            	esitoRT.setEsito(Constants.ESITO_OK);
                return  esitoRT;
            }
            return esitoRT;
        } catch ( Throwable t ) {
            log.error ( "ERRORE DURANTE L'INVIO DELLA RT AL FRUITORE: ", t );
            t.printStackTrace ();
            EsitoRiceviRT esitoRT = new EsitoRiceviRT();
        	esitoRT.setEsito(Constants.ESITO_KO);
        	esitoRT.setCodiceErrore("ERRORE_GENERICO");
        	esitoRT.setMessaggioErrore ( t.getMessage());
            return  esitoRT;
        } finally {
            // fine misurazione               
            log.stopMethod ( "[PagamentiTelematiciRTImpl::spedisciRTAlFruitore]" );
        }
    }

    private XMLGregorianCalendar convertiTimestampInXmlDate ( DatiRicevutaNonInviata ricevuta ) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar ();
        c.setTime ( ricevuta.getDataMsgRicevuta () );
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        xmlDate.setTime ( DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
            DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED );
        xmlDate.setTimezone ( DatatypeConstants.FIELD_UNDEFINED );
        return xmlDate;
    }

    private String generaMac ( String passphrase, String iuv, String applicationId, String idMsgRicevuta, String timestamp ) {

        String sToDigest = passphrase + "%%%%" + applicationId + iuv + idMsgRicevuta + timestamp + "%%%%" + passphrase;
        byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
        String mac = Base64.encodeBase64String ( bMac );
        mac = mac.substring ( 0, 35 );
        return mac;
    }

    private Unmarshaller inizializzaUnMarshallerRT () throws JAXBException,
                    SAXException {
        JAXBContext contextFlussoJAXB = JAXBContext.newInstance ( CtRicevutaTelematica.class );
        Unmarshaller unmarshallerFlusso = contextFlussoJAXB.createUnmarshaller ();
        unmarshallerFlusso.setEventHandler (
            new ValidationEventHandler () {

                public boolean handleEvent ( ValidationEvent event ) {
                    System.out.println ( "ERRORE DI VALIDAZIONE " + event.getMessage () + " " + event.getSeverity () + " " + event.getLinkedException () );
                    return true;
                }
            } );
        SchemaFactory sf = SchemaFactory.newInstance ( "http://www.w3.org/2001/XMLSchema" );
        Source so = new StreamSource ( this.getClass ().getResourceAsStream ( "/PagInf_RPT_RT_6_2_0.xsd" ) );
        Schema s = sf.newSchema ( so );

        unmarshallerFlusso.setSchema ( s );
        return unmarshallerFlusso;
    }
    
    private void inserisciCodaInvioRT(RT rt) throws SerialException, NamingException, SQLException, Exception {
		
			CodaInvioRT coda= new CodaInvioRT();
			coda.setIuv(rt.getIuv());
			coda.setTransactionId(rt.getTransactionId());
			coda.setApplicationId(rt.getApplicationId());
			coda.setContatoreTentativi(0);
			coda.setNumGiorniTentativiKo(0);
			coda.setUltimoEsitoFruitore("");
			coda.setDataInizioTentativi(null);
			coda.setDataTentativi(null);
			coda.setDataUltimaModifica(null);
			
			new InserisciRtCodaInvioDAO(coda).executeUpdate();
	}
    
    private void aggiornaStatoInvioFruitoreRT ( String statoInvioFruitore, Set<String> iuvInseritiNellaCodaRt )
    		throws SerialException, NamingException, SQLException, Exception {
    	String listaIuv= ottieniListaInsertUpdateIn(iuvInseritiNellaCodaRt);
    	if (!StringUtils.isEmpty(listaIuv))
    	{
    		new AggiornaStatoInvioFruitoreRT( statoInvioFruitore, listaIuv  ).executeUpdate ();
    	}
    	
}
    
    private String ottieniListaInsertUpdateIn(Set<String> iuvInviati)
			throws NamingException, Exception, SerialException, SQLException {
		StringBuilder sb = new StringBuilder ();
        for ( String s: iuvInviati ) {
            sb.append ( "'" ).append ( s ).append ( "'" ).append ( "," );
        }
        String levaUltimaVisrgola = sb.toString ();
        if ( levaUltimaVisrgola.length () != 0 ) {
            levaUltimaVisrgola = levaUltimaVisrgola.substring ( 0, levaUltimaVisrgola.length () - 1 );
        }
        return levaUltimaVisrgola;
	}
}
