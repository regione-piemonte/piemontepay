/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.ws.security.util.Base64;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.core.util.LoggerUtil;
import it.csi.mdp.generatedvo.flussoriversamento.CtDatiSingoliPagamenti;
import it.csi.mdp.generatedvo.flussoriversamento.CtFlussoRiversamento;
import it.csi.mdp.generatedvo.pagamenti.CtRichiestaPagamentoTelematico;
import it.csi.mdp.generatedvo.pagamenti.StTipoIdentificativoUnivocoPersFG;
import it.csi.mdp.generatedvo.pagamentimod3.PaGetPaymentRes;
import it.csi.mdp.generatedvo.pagamentimod3.PaSendRTReq;
import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.dto.DatiRichiestaGetPayment;
import it.csi.mdp.mdpetl.dto.DatiRichiestaReceipt;
import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.dto.LoggingFlusso;
import it.csi.mdp.mdpetl.dto.LoggingFlussoEsteso;
import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiApplicationIdDaIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiFlussiRiversamento;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiGetPaymentPerIuvDominioDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiMultiversamentoPerIuvAndTransactionIdDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiReceiptPerIuvDominioDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiReceiptPerIuvSconosciutoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiRichiestePagamentoPerIuvDAO;
import it.csi.mdp.mdpetl.integration.util.dao.FiltroFlussiFlagInvioEnum;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciLoggingFlussoDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ModificaStatoInvioFlussiDAO;
import it.csi.mdp.mdpetl.integration.util.dao.StatoInvioFlussoEnum;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.CategoriaIUV;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.DatiSingoloPagamentoType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PagamentoIntermediatoType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PagamentoIntermediatoType.DatiSingoliPagamenti;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PersonaFisicaType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.PersonaGiuridicaType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.ResponseType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.ServiziRendicontazioneExt;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.SoggettoType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TipoRicevuta;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TrasmettiFlussoRendicontazioneExtRequestType;
import it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati;


public class InoltroFlussiRendicontazioneEstesi {

    static LogUtil log = new LogUtil ( InoltroFlussiRendicontazioneEstesi.class );

    public void inoltraFlussi ( String urlEndpointServizio, byte key[] ) throws Exception {
        LoggerUtil.begin ();

        XStream xs = new XStream ();
        List<String> elencoIuvNonTrovati = null;

        List<FlussoRiversamento> elencoFlussi = reperisciFlussiPerPArametri ();

        LoggerUtil.info ( "ELENCO FLUSSI ESTRATTI" + elencoFlussi.size () );
        System.out.println ( "ELENCO FLUSSI ESTRATTI" + elencoFlussi.size () );

        if ( CollectionUtils.isEmpty ( elencoFlussi ) ) {
            LoggerUtil.info ( "Nessun flusso da Elaborare Fine esecuzione batch" );
            System.out.println ( "Nessun flusso da Elaborare Fine esecuzione batch" );
            return;
        }

        ServiziRendicontazioneExt iServizio = inizializzaServizioESB ( urlEndpointServizio );

        Unmarshaller unmarshallerFlusso = inizializzaUnMarshallerFlusso ();

        int numeroIuvOttici = 0;

        for ( FlussoRiversamento singoloFlusso: elencoFlussi ) {

            // AO --> reinizializzarlo per la logging flusso
            elencoIuvNonTrovati = new ArrayList<String> ();

            LoggingFlusso logFlusso = new LoggingFlussoEsteso ();
            TrasmettiFlussoRendicontazioneExtRequestType trasmettiFlusso = new TrasmettiFlussoRendicontazioneExtRequestType ();

            // creazione testata
            logFlusso.setDataOraInvio ( new Timestamp ( System.currentTimeMillis () ) );
            logFlusso.setIdFlusso ( singoloFlusso.getIdentificativoflusso () );
            logFlusso.setIstitutoMittente ( singoloFlusso.getDenominazionemittente () );

            LoggerUtil.info ( "\nINIZIO ELABORAZIONE FLUSSO NUMERO " + singoloFlusso.getIdentificativoflusso ()
                + "\nISTITUTO " + singoloFlusso.getIdentificativoistitutomittente () );

            System.out.println ( "\nINIZIO ELABORAZIONE FLUSSO NUMERO " + singoloFlusso.getIdentificativoflusso ()
                + "\nISTITUTO " + singoloFlusso.getIdentificativoistitutomittente () );

            try {

                Set<String> distictCodiciVersamento = new HashSet<String> ();

                CtFlussoRiversamento flusso = (CtFlussoRiversamento) unmarshallerFlusso
                    .unmarshal ( new StringReader ( singoloFlusso.getXmlflusso () ) );

                TestataFlussoRendicontazioneExtType testata = datiTestata ( singoloFlusso, flusso );

                trasmettiFlusso.setTestata ( testata );
                logFlusso.setIdMessaggio ( testata.getIdMessaggio () );

                numeroIuvOttici = gestioneSingoliPagamenti ( elencoIuvNonTrovati, numeroIuvOttici, distictCodiciVersamento, flusso, trasmettiFlusso );
                System.out.println ("\nSingolo pagamento Gestito correttamente");
                it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento elencoCodiciVersamento
                    = new it.csi.mdp.mdpnodospcclient.integration.service.flussiesb.DatiAggiuntiviType.ElencoCodiciVersamento ();
                elencoCodiciVersamento.getCodiceVersamento ().addAll ( distictCodiciVersamento );

                trasmettiFlusso.setTestata ( testata );

                trasmettiFlusso.setFlussoRiversamento ( singoloFlusso.getXmlflusso ().getBytes () );

                log.info ( "inoltraFlussi", "Flusso: " + xs.toXML ( trasmettiFlusso ) );
                log.info ( "inoltraFlussi", "NUMERO IUV TOTALI " + numeroIuvOttici + " NUMERO NON TROVATI "
                    + elencoIuvNonTrovati.size () + "\nELENCO NON TROVATI " + xs.toXML ( elencoIuvNonTrovati ) );
                System.out.println ("\nTrasmissione flusso start - NUMERO IUV TOTALI:"+numeroIuvOttici + " NUMERO NON TROVATI "
                        + elencoIuvNonTrovati.size () + "\nELENCO NON TROVATI " + xs.toXML ( elencoIuvNonTrovati ));
                ResponseType res = iServizio.trasmettiFlussoRendicontazioneExt ( trasmettiFlusso );
                System.out.println ("\nTrasmissione flusso end");
                // LF - 13/12/2018 - il servizio di acquisizione del flusso esteso restituisce
                // 000 quando l'operazione è andata a buon fine
                if ( "000".equalsIgnoreCase ( res.getResult ().getCodice () ) ) {
                    // Lorenzo: se l'invio e' avvenuto correttamente aggiorno il relativo stato a INVIATO
                    new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.INVIATO.getCodStato (),
                        FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeUpdate ();
                    logFlusso.setEsito ( Constants.ESITO_OK );
                } else {
                    // Se abbiamo un errore non bloccante allora lo registro come OK e valorizzo la colonna del warning
                    if ( StringUtils.startsWith ( res.getResult ().getCodice (), "0" ) ) {
                        new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.INVIATO.getCodStato (),
                            FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeUpdate ();
                        logFlusso.setEsito ( Constants.ESITO_OK );
                        logFlusso.setWarning ( StringUtils.substring ( res.getResult ().getMessaggio (), 0, 255 ) );
                    } else {
                        // Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo stato a NON_INVIATO
                        new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (),
                            StatoInvioFlussoEnum.NON_INVIATO.getCodStato (), FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO )
                                .executeUpdate ();
                        logFlusso.setEsito ( Constants.ESITO_KO );
                        logFlusso.setWarning ( StringUtils.substring ( res.getResult ().getMessaggio (), 0, 255 ) );
                    }
                }
            } catch ( Exception e ) {
                log.error ( "inoltraFlussi", "ERRORE", e );
                logFlusso.setErrori ( StringUtils.substring ( e.getMessage (), 0, 255 ) );

                // Lorenzo: se l'invio e' avvenuto in modo erroneo aggiorno il relativo stato a NON_INVIATO
//              MDPNEW- 162 in presenza di eccezione per fault non gestito i flussi non devo cambiare 
//              "stato_invio_flusso_esteso" che deve permanere uguale a 1. In questo modo l'invio del flussi puo' essere riprocessato senza  trattamento dati manuale.
//                new ModificaStatoInvioFlussiDAO ( singoloFlusso.getId (), StatoInvioFlussoEnum.NON_INVIATO.getCodStato (),
//                    FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeUpdate ()

            } finally {
                if ( !CollectionUtils.isEmpty ( elencoIuvNonTrovati ) ) {
                    logFlusso.setWarning (
                        StringUtils.substring ( logFlusso.getWarning () + " - " + "IUV NON TROVATI: " + xs.toXML ( elencoIuvNonTrovati ), 0, 255 ) );
                }
                try {
                    new InserisciLoggingFlussoDAO ( logFlusso ).executeUpdate ();
                } catch ( Exception e ) {
                    log.warn ( "inoltraFlussi", "ERRORE in fase di tracciatura", e );
                }
                //Thread.sleep(10000)
            }
        }
    }

    private int gestioneSingoliPagamenti ( List<String> elencoIuvNonTrovati, int numeroIuvOttici,
        Set<String> distictCodiciVersamento, CtFlussoRiversamento flusso,
        TrasmettiFlussoRendicontazioneExtRequestType flussoExt )
                    throws SerialException, SQLException, NamingException, Exception, JAXBException {

        int indice = 0;

        for ( CtDatiSingoliPagamenti singoloPagamento: flusso.getDatiSingoliPagamenti () ) {
            PagamentoIntermediatoType pagamento = new PagamentoIntermediatoType ();

            LoggerUtil.info ( "SINGOLO PAGAMENTO: " + singoloPagamento.getIdentificativoUnivocoVersamento () );

            DatiRichiesta rptDb = null;
            DatiRichiestaReceipt receiptDb = null;
            DatiRichiestaGetPayment getPaymentDb = null;

            CtRichiestaPagamentoTelematico richiesta = null;
            PaGetPaymentRes richiesta1 = null;
            PaSendRTReq richiesta2 = null;

            List<SingoloPagamentoMultiVersamentoDTO> pag = null;

            String datiSpecPrimoElem = null;
            String causalePrimoElem = null;
            SoggettoType pagatore = new SoggettoType ();

            LoggerUtil.info ( "1. Cerco RPT" );
          //#AC20230830 modificata query ricerca per RPT
            rptDb = new EstraiRichiestePagamentoPerIuvDAO ( singoloPagamento.getIdentificativoUnivocoVersamento (),
                flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco () ).executeQuery ();
            if ( rptDb == null ) {
                LoggerUtil.info ( "2. RPT non trovata cerco getPayment" );
                getPaymentDb = new EstraiGetPaymentPerIuvDominioDAO ( singoloPagamento.getIdentificativoUnivocoVersamento (),
                    flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco () ).executeQuery ();

                if ( getPaymentDb == null ) {
                    LoggerUtil.info ( "3. getPayment non trovata cerco receipt" );
                    //#AC20230830 La ricerca per Recipt secondaria deve essere fatta anche per anche per CF Ente 
                    receiptDb = new EstraiReceiptPerIuvDominioDAO ( singoloPagamento.getIdentificativoUnivocoVersamento (),
                            		flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco ()  ).executeQuery ();

                    if ( receiptDb == null ) {
                        LoggerUtil.info ( "4. receipt non trovata cerco se e' un pagamento non intermediato da PPAY" );
                        receiptDb = new EstraiReceiptPerIuvSconosciutoDAO ( singoloPagamento.getIdentificativoUnivocoVersamento () ).executeQuery ();

                        if ( receiptDb == null ) 
                        {
                            LoggerUtil.info ( "5. receipt non trovata pagamento non riconosciuto da PPAY" );
                        
                      
                        if ("9".equals(singoloPagamento.getCodiceEsitoSingoloPagamento())) {
                            
                            if ( flussoExt.getPagamentiIntermediati () == null ) {
                                flussoExt.setPagamentiIntermediati ( new PagamentiIntermediati () );
                            }
                            flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ().add ( pagamento );
                            flussoExt.getTestata ().setImportoTotalePagamentiIntermediati (
                                flussoExt.getTestata ().getImportoTotalePagamentiIntermediati ().add ( singoloPagamento.getSingoloImportoPagato () ) );
                            flussoExt.getTestata ()
                                .setNumeroTotalePagamentiIntermediati ( flussoExt.getTestata ().getNumeroTotalePagamentiIntermediati ().add ( BigInteger.ONE ) );
                            
                        }
                        else
                        {
                            if ( flussoExt.getPagamentiSconosciuti () == null ) {
                                flussoExt.setPagamentiSconosciuti ( new PagamentiIntermediati () );
                            }
                            flussoExt.getPagamentiSconosciuti ().getPagamentoIntermediato ().add ( pagamento );
                            flussoExt.getTestata ().setImportoTotalePagamentiSconosciuti (
                                flussoExt.getTestata ().getImportoTotalePagamentiSconosciuti ().add ( singoloPagamento.getSingoloImportoPagato () ) );
                            flussoExt.getTestata ()
                                .setNumeroTotalePagamentiSconosciuti ( flussoExt.getTestata ().getNumeroTotalePagamentiSconosciuti ().add ( BigInteger.ONE ) );
                            
                        }

                        // AO Non ho trovato RPT / getPayment /receipt ma il pagamento è nel flusso
                        // il controllo sul codice 9 lo estenderei all'esistenza su iuv ottici
                        // se c'è su iuv ottici è nostro anche se non esiste RPT / RT /receipt
                        // MDPNEW-84 INIZIO
                       
                        IuvOttico iuvDto = new EstraiApplicationIdDaIuvDAO ( singoloPagamento.getIdentificativoUnivocoVersamento () ).executeQuery ();
                        if ( iuvDto != null ) {
                            LoggerUtil.info ( "IUV trovato su iuv ottici" );
                        }
                        

                        DatiSingoloPagamentoType datisingoloPagamento = new DatiSingoloPagamentoType ();
                        
                        if (singoloPagamento.getCodiceEsitoSingoloPagamento().equals("9")) {
                            
                            datisingoloPagamento.setTipoRicevuta ( TipoRicevuta.IUV_SENZA_RICEVUTA); //???
                            datisingoloPagamento.setCategoriaIUV ( CategoriaIUV.INTERM_PPAY );
                            datisingoloPagamento.setTransactionId ( "PRD00000000XXX" );
                            
                            if (singoloPagamento.getIdentificativoUnivocoVersamento().length() > 17) {
                                // soluzione workaround per codice_Versamento estratto dagli iuv di modello1
                                // andrebbe fatto su auxDigit 3 ma è un dato di configurazione
                                try {
                                    // se il carattere al tredicesimo posto è un numero si tratta del nuovo formato
                                    Integer.parseInt(singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 14));
                                    datisingoloPagamento.setCodiceVersamento(
                                            singoloPagamento.getIdentificativoUnivocoVersamento().substring(15, 19));
                                } catch (NumberFormatException e) {
                                    datisingoloPagamento.setCodiceVersamento(
                                            singoloPagamento.getIdentificativoUnivocoVersamento().substring(13, 17));
                                }
                                
                            } else {
                                if (iuvDto != null) {
                                    datisingoloPagamento.setCodiceVersamento(iuvDto.getCodiceVersamento());
                                    LoggerUtil.info("dati: " + datisingoloPagamento.getCodiceVersamento());
                                    
//                                    Non viene piu' gestito l'importo 
                                    //INCREMENTO IL TOTALE DELL'IMPORTO E DEL NUMERO DI PAGAMENTI
//                                    importoTotale = importoTotale.add(singoloPagamento.getSingoloImportoPagato());
//                                    numeroTotale = numeroTotale.add(BigInteger.ONE);
                                } else {
                                    elencoIuvNonTrovati.add(singoloPagamento.getIdentificativoUnivocoVersamento());
                                }
                            }
                        }
                        else
                        {
                            datisingoloPagamento.setTipoRicevuta ( TipoRicevuta.SCONOSCIUTO);
                            datisingoloPagamento.setCategoriaIUV ( CategoriaIUV.SCONOSCIUT_PPAY );
                            datisingoloPagamento.setTransactionId ( "----" );
                            datisingoloPagamento.setCodiceVersamento ( "----" );
                           
                        }
                       
                        datisingoloPagamento.setDatiSpecificiRiscossione ( "9/000" );

                        SoggettoType soggettoPagatore = new SoggettoType ();
                        PersonaGiuridicaType personaGiuridica = new PersonaGiuridicaType ();
                        
                        if ("9".equals(singoloPagamento.getCodiceEsitoSingoloPagamento())) {
                            personaGiuridica.setRagioneSociale ( "Assenza RPT" );
                        }
                        else
                        {
                            personaGiuridica.setRagioneSociale ( "----" );
                        }
                       
                        soggettoPagatore.setPersonaGiuridica ( personaGiuridica );
                        soggettoPagatore.setIdentificativoUnivocoFiscale ( "01111111111" );
                        datisingoloPagamento.setAnagraficaPagatore ( soggettoPagatore );
                        datisingoloPagamento.setAnagraficaVersante ( soggettoPagatore );

                       
                        datisingoloPagamento.setIndiceDatiPagamento ( 1 );
                        datisingoloPagamento.setIUR ( singoloPagamento.getIdentificativoUnivocoRiscossione () );
                        datisingoloPagamento.setIUV ( singoloPagamento.getIdentificativoUnivocoVersamento () );
                        datisingoloPagamento.setSingoloImportoPagato ( singoloPagamento.getSingoloImportoPagato () );
                        datisingoloPagamento.setCodiceEsitoPagamento ( singoloPagamento.getCodiceEsitoSingoloPagamento () );
                        datisingoloPagamento.setDataEsitoSingoloPagamento ( singoloPagamento.getDataEsitoSingoloPagamento () );
                        
                        if ("9".equals(singoloPagamento.getCodiceEsitoSingoloPagamento())) {
                            datisingoloPagamento.setDescrizioneCausaleVersamento (
                                "/RFB/" + singoloPagamento.getIdentificativoUnivocoVersamento() + "/"
                                                + singoloPagamento.getSingoloImportoPagato() + "/TXT/Assenza RPT" );
                        }
                        else
                        {
                            datisingoloPagamento.setDescrizioneCausaleVersamento (
                                "/RFB/" + singoloPagamento.getIdentificativoUnivocoVersamento () + "/"
                                    + singoloPagamento.getSingoloImportoPagato () + "/TXT/PagamentoSconosciuto" );
                        }
                        
                        DatiSingoliPagamenti pagamentoIndermediato = new DatiSingoliPagamenti ();
                        pagamentoIndermediato.setDatiSingoloPagamento ( datisingoloPagamento );
                       
                        pagamento.setDatiSingoliPagamenti ( pagamentoIndermediato );
                        
                        // aggiunta distinct codice versamento per modello 3
                        distictCodiciVersamento.add ( datisingoloPagamento.getCodiceVersamento () );
                       
                        //elencoPagamentiIntermediati.add(primoLivello)
                        numeroIuvOttici++;
                        
                        // MDPNEW-84 FINE
                        } 
                        else {
                            LoggerUtil.info ( "5. receipt trovata - pagamento non intermediato da PPAY" );
                            // lo aggiungo dopo aver estratto i dati della receipt; il boolean mi serve per capire se aggiungere a intermediati 
                            // o non intermediati
                            flussoExt.getTestata ().setImportoTotalePagamentiNonIntermediati (
                                flussoExt.getTestata ().getImportoTotalePagamentiNonIntermediati ().add ( singoloPagamento.getSingoloImportoPagato () ) );
                            flussoExt.getTestata ()
                                .setNumeroTotalePagamentiNonIntermediati (
                                    flussoExt.getTestata ().getNumeroTotalePagamentiNonIntermediati ().add ( BigInteger.ONE ) );
                            //:TODO capire come impostare la logica dopo test
                            indice = singoloPagamento.getIndiceDatiSingoloPagamento () != null ? singoloPagamento.getIndiceDatiSingoloPagamento () - 1 : 0;

                            // AO receipt 

                            try {
                                richiesta2
                                    = JAXB.unmarshal ( new ByteArrayInputStream ( Base64.decode ( new String ( receiptDb.getRptXml () ) ) ),
                                        PaSendRTReq.class );
                            } catch ( Exception e ) {
                                e.printStackTrace ();
                            }

                            System.out.println ( "2.3.1 richiesta2:" + richiesta2.getReceipt ().getReceiptId () );

                            // fare una CtReceipt che fa l'unmashall della receipt con il suo schema
                            EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO estraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO
                                = new EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO (
                                    singoloPagamento.getIdentificativoUnivocoVersamento (),
                                    flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco () );
                            pag = estraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO.executeQuery ();
                            System.out.println ( "2.3.2 Estratto Multiversamento " );

                            //      da correggere lolloso
                            //datiVersamento = richiesta2.getDatiVersamento()
                            datiSpecPrimoElem = richiesta2.getReceipt ().getTransferList ().getTransfer ().get ( indice ) != null
                                            ? richiesta2.getReceipt ().getTransferList ().getTransfer ().get ( indice ).getTransferCategory () : null;
                            causalePrimoElem = richiesta2.getReceipt ().getTransferList ().getTransfer ().get ( indice ).getRemittanceInformation ();

                            pagatore.setEMail ( richiesta2.getReceipt ().getDebtor ().getEMail () );
                            pagatore
                                .setIdentificativoUnivocoFiscale (
                                    richiesta2.getReceipt ().getDebtor ().getUniqueIdentifier ().getEntityUniqueIdentifierValue () );
                            if ( StTipoIdentificativoUnivocoPersFG.F
                                .equals ( richiesta2.getReceipt ().getDebtor ().getUniqueIdentifier ().getEntityUniqueIdentifierType () ) ) {
                                PersonaFisicaType pf = new PersonaFisicaType ();
                                pf.setCognome ( richiesta2.getReceipt ().getDebtor ().getFullName () );
                                pagatore.setPersonaFisica ( pf );
                            } else {
                                PersonaGiuridicaType pf = new PersonaGiuridicaType ();
                                pf.setRagioneSociale ( richiesta2.getReceipt ().getDebtor ().getFullName () );
                                pagatore.setPersonaGiuridica ( pf );
                            }
                            if ( null == flussoExt.getPagamentiNonIntermediati () ) {
                                flussoExt.setPagamentiNonIntermediati ( new PagamentiIntermediati () );
                            }
                            flussoExt.getPagamentiNonIntermediati ().getPagamentoIntermediato ()
                                .addAll ( spacchettamentoFlussiIntermediati ( pag, singoloPagamento, pagatore, distictCodiciVersamento,
                                    receiptDb.getTransactionId (), datiSpecPrimoElem, causalePrimoElem, TipoRicevuta.RECEIPT, CategoriaIUV.NON_INTERM_PPAY ) );
                        }

                    } else {
                        LoggerUtil.info ( "6. receipt trovata" );
                        //: lollo1
                        flussoExt.getTestata ().setImportoTotalePagamentiIntermediati (
                            flussoExt.getTestata ().getImportoTotalePagamentiIntermediati ().add ( singoloPagamento.getSingoloImportoPagato () ) );
                        flussoExt.getTestata ()
                            .setNumeroTotalePagamentiIntermediati ( flussoExt.getTestata ().getNumeroTotalePagamentiSconosciuti ().add ( BigInteger.ONE ) );

                        //:TODO capire come impostare la logica dopo test
                        indice = singoloPagamento.getIndiceDatiSingoloPagamento () != null ? singoloPagamento.getIndiceDatiSingoloPagamento () - 1 : 0;

                        // AO receipt 

                        try {
                            richiesta2
                                = JAXB.unmarshal ( new ByteArrayInputStream ( Base64.decode ( new String ( receiptDb.getRptXml () ) ) ), PaSendRTReq.class );
                        } catch ( Exception e ) {
                            e.printStackTrace ();
                        }

                        System.out.println ( "2.3.1 richiesta2:" + richiesta2.getReceipt ().getReceiptId () );

                        // fare una CtReceipt che fa l'unmashall della receipt con il suo schema
                        EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO estraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO
                            = new EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO (
                                singoloPagamento.getIdentificativoUnivocoVersamento (),
                                flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco () );
                        pag = estraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO.executeQuery ();
                        System.out.println ( "2.3.2 Estratto Multiversamento " );

                        //      da correggere lolloso
                        //datiVersamento = richiesta2.getDatiVersamento();
                        datiSpecPrimoElem = richiesta2.getReceipt ().getTransferList ().getTransfer ().get ( indice ) != null
                                        ? richiesta2.getReceipt ().getTransferList ().getTransfer ().get ( indice ).getTransferCategory () : null;
                        causalePrimoElem = richiesta2.getReceipt ().getTransferList ().getTransfer ().get ( indice ).getRemittanceInformation ();

                        pagatore.setEMail ( richiesta2.getReceipt ().getDebtor ().getEMail () );
                        pagatore
                            .setIdentificativoUnivocoFiscale ( richiesta2.getReceipt ().getDebtor ().getUniqueIdentifier ().getEntityUniqueIdentifierValue () );
                        if ( StTipoIdentificativoUnivocoPersFG.F
                            .equals ( richiesta2.getReceipt ().getDebtor ().getUniqueIdentifier ().getEntityUniqueIdentifierType () ) ) {
                            PersonaFisicaType pf = new PersonaFisicaType ();
                            pf.setCognome ( richiesta2.getReceipt ().getDebtor ().getFullName () );
                            pagatore.setPersonaFisica ( pf );
                        } else {
                            PersonaGiuridicaType pf = new PersonaGiuridicaType ();
                            pf.setRagioneSociale ( richiesta2.getReceipt ().getDebtor ().getFullName () );
                            pagatore.setPersonaGiuridica ( pf );
                        }
                        if ( flussoExt.getPagamentiIntermediati () == null ) {
                            flussoExt.setPagamentiIntermediati ( new PagamentiIntermediati () );
                        }
                        flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ()
                            .addAll ( spacchettamentoFlussiIntermediati ( pag, singoloPagamento, pagatore, distictCodiciVersamento,
                                receiptDb.getTransactionId (), datiSpecPrimoElem, causalePrimoElem, TipoRicevuta.RECEIPT, CategoriaIUV.INTERM_PPAY ) );
                    }
                } else {
                    LoggerUtil.info ( "7. getPayment trovata" );
                    //lollo2
                    flussoExt.getTestata ().setImportoTotalePagamentiIntermediati (
                        flussoExt.getTestata ().getImportoTotalePagamentiIntermediati ().add ( singoloPagamento.getSingoloImportoPagato () ) );
                    flussoExt.getTestata ()
                        .setNumeroTotalePagamentiIntermediati ( flussoExt.getTestata ().getNumeroTotalePagamentiIntermediati ().add ( BigInteger.ONE ) );
                    try {
                        //:TODO capire come impostare la logica dopo test
                        indice = singoloPagamento.getIndiceDatiSingoloPagamento () != null ? singoloPagamento.getIndiceDatiSingoloPagamento () - 1 : 0;
                        // AO getPayment 
                        // fare una CtGetPayment che fa l'unmashall della getpayment con il suo schema
                        LoggerUtil.info ( "2.2.1 Inizio Unmarshall getPayment" );
                        richiesta1
                            = JAXB.unmarshal ( new ByteArrayInputStream ( Base64.decode ( new String ( getPaymentDb.getRptXml () ) ) ), PaGetPaymentRes.class );
                        LoggerUtil.info ( "2.2.2 Unmarshall eseguita correttamente." );
                    } catch ( Exception e ) {
                        LoggerUtil.error ( "2.2.3 Errore unmarshall.", e );
                    }
                    EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO estraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO
                        = new EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO ( singoloPagamento.getIdentificativoUnivocoVersamento (),
                            flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco () );
                    pag = estraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO.executeQuery ();

                    //datiVersamento = richiesta1.getDatiVersamento()
                    datiSpecPrimoElem = richiesta1.getData ().getTransferList ().getTransfer ().get ( indice ) != null
                                    ? richiesta1.getData ().getTransferList ().getTransfer ().get ( indice ).getTransferCategory () : null;
                    causalePrimoElem = richiesta1.getData ().getTransferList ().getTransfer ().get ( indice ).getRemittanceInformation ();

                    pagatore.setEMail ( richiesta1.getData ().getDebtor ().getEMail () );
                    pagatore.setIdentificativoUnivocoFiscale ( richiesta1.getData ().getDebtor ().getUniqueIdentifier ().getEntityUniqueIdentifierValue () );
                    if ( StTipoIdentificativoUnivocoPersFG.F
                        .equals ( richiesta1.getData ().getDebtor ().getUniqueIdentifier ().getEntityUniqueIdentifierType () ) ) {
                        PersonaFisicaType pf = new PersonaFisicaType ();
                        pf.setCognome ( richiesta1.getData ().getDebtor ().getFullName () );
                        pagatore.setPersonaFisica ( pf );
                    } else {
                        PersonaGiuridicaType pf = new PersonaGiuridicaType ();
                        pf.setRagioneSociale ( richiesta1.getData ().getDebtor ().getFullName () );
                        pagatore.setPersonaGiuridica ( pf );
                    }
                    if ( flussoExt.getPagamentiIntermediati () == null ) {
                        flussoExt.setPagamentiIntermediati ( new PagamentiIntermediati () );
                    }
                    flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ()
                        .addAll ( spacchettamentoFlussiIntermediati ( pag, singoloPagamento, pagatore, distictCodiciVersamento,
                            getPaymentDb.getTransactionId (), datiSpecPrimoElem, causalePrimoElem, TipoRicevuta.GET_PAYMENT, CategoriaIUV.INTERM_PPAY ) );

                }
            } else {
                LoggerUtil.info ( "8. RPT trovata" );

                //lollo3
                flussoExt.getTestata ().setImportoTotalePagamentiIntermediati (
                    flussoExt.getTestata ().getImportoTotalePagamentiIntermediati ().add ( singoloPagamento.getSingoloImportoPagato () ) );
                flussoExt.getTestata ()
                    .setNumeroTotalePagamentiIntermediati ( flussoExt.getTestata ().getNumeroTotalePagamentiIntermediati ().add ( BigInteger.ONE ) );

                indice = singoloPagamento.getIndiceDatiSingoloPagamento () != null ? singoloPagamento.getIndiceDatiSingoloPagamento () - 1 : 0;
                LoggerUtil.info ( "Esegui unmarshal della RPT" );
                try {
                    richiesta = JAXB.unmarshal ( new ByteArrayInputStream ( rptDb.getRptXml ().getBytes () ), CtRichiestaPagamentoTelematico.class );
                } catch ( Exception e ) {
                    LoggerUtil.error ( "2.2.3 Errore in fase di unmarshall della RPT.", e );
                }

                LoggerUtil.info ( "Cerco elemento_multiversamento" );
                EstraiMultiversamentoPerIuvAndTransactionIdDAO estraiMultiversamentoPerIuvAndTransactionIdDAO
                    = new EstraiMultiversamentoPerIuvAndTransactionIdDAO (
                        singoloPagamento.getIdentificativoUnivocoVersamento (), rptDb.getTransactionId (), rptDb.getApplicationId () );

                pag = estraiMultiversamentoPerIuvAndTransactionIdDAO.executeQuery ();
                datiSpecPrimoElem = richiesta.getDatiVersamento ().getDatiSingoloVersamento ().get ( indice ) != null
                                ? richiesta.getDatiVersamento ().getDatiSingoloVersamento ().get ( indice ).getDatiSpecificiRiscossione () : null;
                causalePrimoElem = richiesta.getDatiVersamento ().getDatiSingoloVersamento ().get ( indice ).getCausaleVersamento ();

                LoggerUtil.info ( "Valorizzo il soggetto." );
                pagatore.setEMail ( richiesta.getSoggettoPagatore ().getEMailPagatore () );
                pagatore
                    .setIdentificativoUnivocoFiscale ( richiesta.getSoggettoPagatore ().getIdentificativoUnivocoPagatore ().getCodiceIdentificativoUnivoco () );
                if ( StTipoIdentificativoUnivocoPersFG.F
                    .equals ( richiesta.getSoggettoPagatore ().getIdentificativoUnivocoPagatore ().getTipoIdentificativoUnivoco () ) ) {
                    PersonaFisicaType pf = new PersonaFisicaType ();
                    pf.setCognome ( richiesta.getSoggettoPagatore ().getAnagraficaPagatore () );
                    pagatore.setPersonaFisica ( pf );
                } else {
                    PersonaGiuridicaType pf = new PersonaGiuridicaType ();
                    pf.setRagioneSociale ( richiesta.getSoggettoPagatore ().getAnagraficaPagatore () );
                    pagatore.setPersonaGiuridica ( pf );
                }
                if ( null == flussoExt.getPagamentiIntermediati () ) {
                    flussoExt.setPagamentiIntermediati ( new PagamentiIntermediati () );
                }
                flussoExt.getPagamentiIntermediati ().getPagamentoIntermediato ()
                    .addAll ( spacchettamentoFlussiIntermediati ( pag, singoloPagamento, pagatore, distictCodiciVersamento, rptDb.getTransactionId (),
                        datiSpecPrimoElem, causalePrimoElem, TipoRicevuta.RPT, CategoriaIUV.INTERM_PPAY ) );

            }

        }

        it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento co
            = new it.csi.mdp.mdpnodospcclient.integration.service.flussiesbext.TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento ();
        co.getCodiceVersamento ().addAll ( distictCodiciVersamento );
        flussoExt.getTestata ().setElencoCodiciVersamento ( co );
        return numeroIuvOttici;

    }

    /**
     * Funzione che spacchetta lo iuv di un singolo pagamento in base ai suoi componenti contabili.
     * 
     * @param pag
     * @param singoloPagamento
     * @param pagatore
     * @param distictCodiciVersamento
     * @param transactionId
     * @param datoSpecificoRiscossione
     * @param causaleVersamento
     * @return lista di pagamenti
     * @throws Exception
     */
    private List<PagamentoIntermediatoType> spacchettamentoFlussiIntermediati ( List<SingoloPagamentoMultiVersamentoDTO> pag,
        CtDatiSingoliPagamenti singoloPagamento, SoggettoType pagatore, Set<String> distictCodiciVersamento, String transactionId,
        String datoSpecificoRiscossione, String causaleVersamento, TipoRicevuta tipoRicevuta, CategoriaIUV categoriaIUV ) throws Exception {

        List<PagamentoIntermediatoType> retList = new LinkedList<PagamentoIntermediatoType> ();

        if ( CollectionUtils.isEmpty ( pag ) ) {
            SingoloPagamentoMultiVersamentoDTO singoloFarlocco = new SingoloPagamentoMultiVersamentoDTO ();
            singoloFarlocco.setPosizione ( singoloPagamento.getIndiceDatiSingoloPagamento () );
            singoloFarlocco.setCausale ( causaleVersamento );
            singoloFarlocco.setImporto ( singoloPagamento.getSingoloImportoPagato () );
            singoloFarlocco.setIuv ( singoloPagamento.getIdentificativoUnivocoVersamento () );
            singoloFarlocco.setDatiSpecificiRiscossione ( datoSpecificoRiscossione );
            pag.add ( singoloFarlocco );
        }

        for ( SingoloPagamentoMultiVersamentoDTO singoloPagamentoMulti: pag ) {
            PagamentoIntermediatoType primoLivello = new PagamentoIntermediatoType ();
            DatiSingoliPagamenti secondoLivello = new DatiSingoliPagamenti ();
            DatiSingoloPagamentoType terzoLivello = new DatiSingoloPagamentoType ();
            secondoLivello.setDatiSingoloPagamento ( terzoLivello );
            retList.add ( primoLivello );
            terzoLivello.setTipoRicevuta ( tipoRicevuta);
            terzoLivello.setCategoriaIUV ( categoriaIUV );
            terzoLivello.setIUR ( singoloPagamento.getIdentificativoUnivocoRiscossione () );
            terzoLivello.setIUV ( singoloPagamento.getIdentificativoUnivocoVersamento () );
            terzoLivello.setTransactionId ( ( it.csi.mdp.mdpetl.util.StringUtils.isEmpty ( transactionId ) ? "----" : transactionId ) );
            terzoLivello.setSingoloImportoPagato ( singoloPagamentoMulti.getImporto () );
            primoLivello.setDatiSingoliPagamenti ( secondoLivello );

            terzoLivello.setAnagraficaPagatore ( pagatore );
            terzoLivello.setAnagraficaVersante ( pagatore );
            terzoLivello.setCodiceEsitoPagamento ( singoloPagamento.getCodiceEsitoSingoloPagamento () );
            terzoLivello.setDataEsitoSingoloPagamento ( singoloPagamento.getDataEsitoSingoloPagamento () );

            terzoLivello.setCodiceVersamento ( determinaCov ( distictCodiciVersamento, singoloPagamento.getIdentificativoUnivocoVersamento () ) );

            //:TODO finire di controllare                
            if ( singoloPagamentoMulti.getDatiSpecificiRiscossione () == null
                || StringUtils.isBlank ( singoloPagamentoMulti.getDatiSpecificiRiscossione () ) ) {
                throw new Exception ( String.format ( "Dati specifici di riscossione per lo IUV %s non presenti",
                    singoloPagamento.getIdentificativoUnivocoVersamento () ) );
            }
            terzoLivello.setDatiSpecificiRiscossione ( singoloPagamentoMulti.getDatiSpecificiRiscossione () );
            terzoLivello.setDescrizioneCausaleVersamento ( singoloPagamentoMulti.getCausale () );
            // AO cambiata query per la posizione
            if ( singoloPagamentoMulti.getPosizione () != null ) {
                terzoLivello.setIndiceDatiPagamento ( singoloPagamentoMulti.getPosizione () );
            } else {
                terzoLivello.setIndiceDatiPagamento ( 1 );
            }

            // LF 03/01/2019 Aggiunta anno e numero accertamento (RDI-005)
            if ( singoloPagamentoMulti.getAnnoAccertamento () != null
                && singoloPagamentoMulti.getAnnoAccertamento () > 0 ) {

                terzoLivello.setAnnoAccertamento ( singoloPagamentoMulti.getAnnoAccertamento () );
            }
            if ( null != singoloPagamentoMulti.getNumeroAccertamento ()
                && singoloPagamentoMulti.getNumeroAccertamento () > 0 ) {
                terzoLivello.setNumeroAccertamento ( singoloPagamentoMulti.getNumeroAccertamento () );
            }
        }
        return retList;
    }

    /**
     * Metodo per determinare il codice versamento ed inserirlo all'interno della lista dei codici versamento.
     * 
     * @param distictCodiciVersamento
     * @param iuv
     * @return codice versamento se trovato altrimenti ----
     */
    private String determinaCov ( Set<String> distictCodiciVersamento, String iuv ) {
        String retCov = "----";
        if ( StringUtils.isNotBlank ( iuv ) ) {
            if ( iuv.length () > 17 ) {
                try {
                    // se il carattere al tredicesimo posto Ã¨ un numero si tratta del nuovo formato
                    Integer.parseInt ( iuv.substring ( 13, 14 ) );
                    retCov = iuv.substring ( 15, 19 );
                } catch ( NumberFormatException e ) {
                    retCov = iuv.substring ( 13, 17 );
                }
            } else {
                try {
                    IuvOttico iuvDto = new EstraiApplicationIdDaIuvDAO ( iuv ).executeQuery ();
                    if ( iuvDto != null ) {
                        retCov = iuvDto.getCodiceVersamento ();

                    }
                } catch ( Exception e ) {
                    LoggerUtil.error ( "Errore in fase di reperimento del codice versamento riferito allo iuv: " + iuv, e );
                }
            }
        }
        distictCodiciVersamento.add ( retCov );
        return retCov;
    }

    private Unmarshaller inizializzaUnMarshallerFlusso () throws JAXBException, SAXException {
        JAXBContext contextFlussoJAXB = JAXBContext.newInstance ( CtFlussoRiversamento.class );
        Unmarshaller unmarshallerFlusso = contextFlussoJAXB.createUnmarshaller ();
        unmarshallerFlusso.setEventHandler ( new ValidationEventHandler () {

            public boolean handleEvent ( ValidationEvent event ) {
                System.out.println ( "ERRORE DI VALIDAZIONE " + event.getMessage () + " " + event.getSeverity () + " "
                    + event.getLinkedException () );
                return true;
            }
        } );
        SchemaFactory sf = SchemaFactory.newInstance ( "http://www.w3.org/2001/XMLSchema" );
        Source so = new StreamSource ( this.getClass ().getResourceAsStream ( "/FlussoRiversamento_1_0_4.xsd" ) );
        Schema s = sf.newSchema ( so );

        unmarshallerFlusso.setSchema ( s );
        return unmarshallerFlusso;
    }

    private ServiziRendicontazioneExt inizializzaServizioESB ( String urlEndpointServizio ) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean ();
        factory.getInInterceptors ().add ( new LoggingInInterceptor () );
        factory.getOutInterceptors ().add ( new LoggingOutInterceptor () );
        factory.setServiceClass ( ServiziRendicontazioneExt.class );
        factory.setAddress ( urlEndpointServizio );
        ServiziRendicontazioneExt iServizio = (ServiziRendicontazioneExt) factory.create ();
        return iServizio;
    }

    private TestataFlussoRendicontazioneExtType datiTestata ( FlussoRiversamento singoloFlusso,
        CtFlussoRiversamento flusso ) {
        TestataFlussoRendicontazioneExtType testata = new TestataFlussoRendicontazioneExtType ();

        testata.setIdMessaggio ( singoloFlusso.getIdentificativoflusso () );

        testata.setIdPSP ( singoloFlusso.getIdentificativoistitutomittente () );

        /* DATI TESTATA */
        testata.setDataOraMessaggio ( flusso.getDataOraFlusso () );
        testata.setDataRegolamento ( flusso.getDataRegolamento () );

        // EPAY-130 - INIZIO
        testata.setDenominazioneEnte ( flusso.getIstitutoRicevente () != null
                        ? sanitizzaInput ( flusso.getIstitutoRicevente ().getDenominazioneRicevente () )
                        : "ND" );
        testata.setDenominazionePSP ( flusso.getIstitutoMittente () != null
                        ? sanitizzaInput ( flusso.getIstitutoMittente ().getDenominazioneMittente () )
                        : "ND" );
        // EPAY-130 - FINE
        testata.setCFEnteCreditore ( flusso.getIstitutoRicevente () != null
                        ? flusso.getIstitutoRicevente ().getIdentificativoUnivocoRicevente ().getCodiceIdentificativoUnivoco ()
                        : "ND" );
        testata.setIdentificativoFlusso ( singoloFlusso.getIdentificativoflusso () );
        testata.setIdentificativoUnivocoRegolamento ( flusso.getIdentificativoUnivocoRegolamento () );

        testata.setNumeroTotalePagamentiFlusso (
            flusso.getNumeroTotalePagamenti () != null ? flusso.getNumeroTotalePagamenti ().toBigInteger ()
                            : BigInteger.ZERO );
        testata.setImportoTotalePagamentiFlusso ( BigDecimal.valueOf ( singoloFlusso.getImportototalepagamenti () ) );
        testata.setNumeroTotalePagamentiIntermediati ( BigInteger.ZERO );
        testata.setImportoTotalePagamentiIntermediati ( BigDecimal.ZERO );
        testata.setNumeroTotalePagamentiNonIntermediati ( BigInteger.ZERO );
        testata.setImportoTotalePagamentiNonIntermediati ( BigDecimal.ZERO );
        testata.setNumeroTotalePagamentiSconosciuti ( BigInteger.ZERO );
        testata.setImportoTotalePagamentiSconosciuti ( BigDecimal.ZERO );
        return testata;
    }

    // EPAY-130 - INIZIO
    private String sanitizzaInput ( String input ) {
        if ( null == input || input.trim ().length () == 0 ) {
            input = "ND";
        }

        return input;
    }

    private List<FlussoRiversamento> reperisciFlussiPerPArametri ()
                    throws SerialException, SQLException, NamingException, Exception {

        return new EstraiFlussiRiversamento ( FiltroFlussiFlagInvioEnum.FLUSSO_ESTESO ).executeQuery ();
    }
}
