/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayjob.business.mail.InviaMailTrasmissioneNotifiche;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayjob.utilities.XmlUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.epay.epayservices.model.RevocaPerWso2;
import it.csi.sportello2epaywso.CorpoRichiesteDiRevocaType;
import it.csi.sportello2epaywso.DatiSingolaRevocaType;
import it.csi.sportello2epaywso.PersonaFisicaType;
import it.csi.sportello2epaywso.PersonaGiuridicaType;
import it.csi.sportello2epaywso.ResponseType;
import it.csi.sportello2epaywso.ResultType;
import it.csi.sportello2epaywso.RichiestaDiRevocaType;
import it.csi.sportello2epaywso.RichiestaDiRevocaType.ListaDatiSingolaRevoca;
import it.csi.sportello2epaywso.SoggettoType;
import it.csi.sportello2epaywso.Sportello2EPaywso;
import it.csi.sportello2epaywso.Sportello2EPaywso_Service;
import it.csi.sportello2epaywso.TestataRichiesteDiRevocaType;
import it.csi.sportello2epaywso.TrasmettiRichiesteDiRevocaRequest;


public class TrasmettiRichiesteDiRevoca extends RtXml {

    private static final String CONFIG_PROPERTIES = "config.properties";

    private LogUtil log;

    private JobFacade jobFacade;

    private MailFacade mailFacade;

    private String url;

    public TrasmettiRichiesteDiRevoca ( JobFacade jobFacade, MailFacade mailFacade ) throws IllegalArgumentException, NoDataException {
        super ();
        final String methodName = "TrasmettiRichiesteDiRevoca";
        log = new LogUtil ( this.getClass () );
        this.jobFacade = jobFacade;
        this.mailFacade = mailFacade;

        try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
            Properties properties = new Properties ();
            properties.load ( inputStream );
            url = properties.getProperty ( "sportello2epaywso.wsdl" );
            log.debug ( methodName, "url wso : " + url );
        } catch ( IOException e ) {
            log.debug ( methodName, "errore lettura parametri. " + e.getMessage () );
            throw new RuntimeException ( "Errore lettura parametri: " + e.getMessage () );
        }
    }

    public void execute () throws Exception {
        final String methodName = "execute";
        log.infoStart ( methodName );

        try {
            List<Ente> enti = jobFacade.ricercaEntiPerInvioRichiesteDiRevoca ();

            for ( Ente ente: enti ) {
                log.debug ( methodName, "elaborazione ente " + ente.getNome () );
                try {
                    List<RevocaPerWso2> richiesteDiRevoca = jobFacade.ricercaRichiesteRevocaPerEnte ( ente );
                    if ( richiesteDiRevoca != null && !richiesteDiRevoca.isEmpty () ) {
                        logRichieste ( richiesteDiRevoca );
                    }

                    for ( RevocaPerWso2 richiestaDiRevoca: richiesteDiRevoca ) {
                        log.debug ( methodName, "elaborazione richiesta di revoca " +
                            ( richiestaDiRevoca.getRr () != null ? richiestaDiRevoca.getRr ().getIdRr () : null ) );

                        requestSoap ( ente, richiestaDiRevoca );

                        log.debug ( methodName, "marco come inviata la richiesta di revoca " +
                            ( richiestaDiRevoca.getRr () != null ? richiestaDiRevoca.getRr ().getIdRr () : null ) );
                        jobFacade.aggiornaRevocaInviataWso2 ( richiestaDiRevoca );
                    }

                } catch ( NoDataException nde ) {
                    log.debug ( methodName, "aggiorno registro elaborazioni NoData" );
                    inserisciRegistroElaborazioniNoData ( jobFacade, ente, null );
                    log.debug ( methodName, "nessun tipo pagamento da elaborare" );
                }
            }

        } catch ( NoDataException nde ) {
            log.debug ( methodName, "aggiorno registro elaborazioni NoData" );
            inserisciRegistroElaborazioniNoData ( jobFacade, null, null );
            log.debug ( methodName, "nessun ente da elaborare" );
        }

        log.infoEnd ( methodName );
    }

    private void logRichieste ( List<RevocaPerWso2> richiesteDiRevoca ) {
        if ( log.isDebugEnabled () ) {
            log.debug ( "LOG REVOCA", "######################################################" );
            try {

                if ( richiesteDiRevoca == null || richiesteDiRevoca.isEmpty () ) {
                    log.debug ( "LOG REVOCA", "# LISTA VUOTA #" );
                } else {
                    for ( RevocaPerWso2 revoca: richiesteDiRevoca ) {

                        log.debug ( "ID MESSAGGIO", revoca.getRr ().getIdentificativoMessaggioRevoca () );
                        log.debug ( "REVOCA", XmlUtil.obj2Xml ( revoca.getRr () ) );

                    }
                }
            } catch ( Exception e ) {
                log.error ( "logRichieste", "Errore nel log delle richieste: " + e.getClass ().getSimpleName () + " - " + e.getMessage () );
            }
            log.debug ( "LOG REVOCA", "######################################################" );
        }
    }

    private void requestSoap ( Ente ente, RevocaPerWso2 richiestaDiRevoca ) throws XPathExpressionException {
        final String methodName = "requestSoap";

        if ( StringUtils.isBlank ( richiestaDiRevoca.getRr ().getCodiceIdentificativoUnivocoAttestante () ) ) {
            throw new RuntimeException ( "Il campo RR.CodiceIdentificativoUnivocoAttestante e' obbligatorio" );
        }
        if ( richiestaDiRevoca.getDati () == null || richiestaDiRevoca.getDati ().size () < 1 ) {
            throw new RuntimeException ( "Il campo RR.Dati e' obbligatorio e deve contenere almeno un elemento" );
        }

        log.debug ( methodName, "aggiorno registro elaborazioni inizio" );
        RegistroElaborazioni registroElaborazioni = inserisciRegistroElaborazioniInizio ( jobFacade, ente, richiestaDiRevoca );

        CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca elenco = new CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca ();
        List<RichiestaDiRevocaType> listaRevoche = elenco.getRichiestaDiRevoca ();

        log.debug ( methodName, "elaborazione richiestaDiRevoca " + richiestaDiRevoca.getRr ().getIdentificativoMessaggioRevoca () );

        RichiestaDiRevocaType rr = new RichiestaDiRevocaType ();

        //---------------------------------------------------------------------------------//
        //CORPO
        //---------------------------------------------------------------------------------//

        XMLGregorianCalendar dataOraMessaggio = timestamp2xmlGregorianCalandar ( richiestaDiRevoca.getRr ().getDataOraMessaggioRevoca () );

        SoggettoType soggettoAttestante = new SoggettoType (); //TODO - VERIFICARE FUNZIONAMENTO
        soggettoAttestante.setIdentificativoUnivocoFiscale ( richiestaDiRevoca.getRr ().getCodiceIdentificativoUnivocoAttestante () );

        if ( richiestaDiRevoca.getRr ().getCodiceIdentificativoUnivocoAttestante ().toLowerCase ().matches ( "[a-z]{6}[a-z0-9]*" ) ) {
            PersonaFisicaType value = new PersonaFisicaType ();
            value.setNome ( richiestaDiRevoca.getRr ().getDenominazioneIstitutoAttestante () );
            value.setCognome ( richiestaDiRevoca.getRr ().getDenominazioneIstitutoAttestante () );
            soggettoAttestante.setPersonaFisica ( value );
        } else {
            PersonaGiuridicaType value = new PersonaGiuridicaType ();
            value.setRagioneSociale ( richiestaDiRevoca.getRr ().getDenominazioneIstitutoAttestante () );
            soggettoAttestante.setPersonaGiuridica ( value );
        }

        ListaDatiSingolaRevoca listaDatiSingolaRevoca = new ListaDatiSingolaRevoca ();
        for ( DatiSingolaRevoca dato: richiestaDiRevoca.getDati () ) {
            DatiSingolaRevocaType datiDTO = new DatiSingolaRevocaType ();
            datiDTO.setCausale ( dato.getCausaleRevoca () );
            datiDTO.setDatiAggiuntivi ( dato.getDatiAggiuntiviRevoca () );
            datiDTO.setIUR ( dato.getIur () );
            datiDTO.setSingoloImportoRevocato ( dato.getSingoloImportoRevocato () );
            listaDatiSingolaRevoca.getDatiSingolaRevoca ().add ( datiDTO );
        }

        rr.setApplicationId ( richiestaDiRevoca.getRr ().getApplicationId () );
        rr.setCodiceContestoPagamento ( richiestaDiRevoca.getRr ().getCodiceContestoPagamento () );
        rr.setDataOraMessaggioRevoca ( dataOraMessaggio );
        rr.setListaDatiSingolaRevoca ( listaDatiSingolaRevoca );
        rr.setIdentificativoDominio ( richiestaDiRevoca.getRr ().getIdDominio () );
        rr.setIdentificativoMessaggioRevoca ( richiestaDiRevoca.getRr ().getIdentificativoMessaggioRevoca () );
        rr.setImportoPagato ( richiestaDiRevoca.getRr ().getImportoTotaleRevocato () );
        rr.setIstitutoAttestante ( soggettoAttestante );
        rr.setIUV ( richiestaDiRevoca.getRr ().getIuv () );
        int tipoRevoca = 0;

        try {
            tipoRevoca = Integer.parseInt ( richiestaDiRevoca.getRr ().getTipoRevoca () );
        } catch ( NumberFormatException e ) {
        }

        rr.setTipoRevoca ( tipoRevoca );
        rr.setXML ( richiestaDiRevoca.getRr ().getRrXml () );

        rr.setXML ( new byte [] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } );

        listaRevoche.add ( rr );

        //---------------------------------------------------------------------------------//
        //TESTATA
        //---------------------------------------------------------------------------------//

        TestataRichiesteDiRevocaType testata = new TestataRichiesteDiRevocaType ();
        testata.setIdMessaggio ( registroElaborazioni.getIdMessaggio () );
        testata.setCFEnteCreditore ( ente.getCodiceFiscale () );
        testata.setCodiceVersamento ( richiestaDiRevoca.getRr ().getCodiceContestoPagamento () ); //TODO-SI TRATTA DEL CV?
        testata.setNumeroPagamenti ( registroElaborazioni.getNumPagamenti () );
        testata.setImportoTotalePagamenti ( registroElaborazioni.getImportoTotPagamenti () );

        CorpoRichiesteDiRevocaType corpo = new CorpoRichiesteDiRevocaType ();
        corpo.setElencoRichiesteDiRevoca ( elenco );

        TrasmettiRichiesteDiRevocaRequest request = new TrasmettiRichiesteDiRevocaRequest ();
        request.setTestata ( testata );
        request.setCorpoRichiesteDiRevoca ( corpo );

        if ( log.isDebugEnabled () ) {
            log.debug ( methodName, "REQUEST PER SERVICE: " + XmlUtil.obj2Xml ( request ) );
        }

        ResultType result = callService ( request );
        //        ResultType result = new ResultType ();
        //        result.setCodice ( "000" );
        //        result.setMessaggio ( "(MOCKED) OK" );

        if ( log.isDebugEnabled () ) {
            log.debug ( methodName, "RESULT DA SERVICE: " + XmlUtil.obj2Xml ( result ) );
        }

        log.debug ( methodName, "aggiorno registro elaborazioni fine" );
        aggiornaRegistroElaborazioniFine ( jobFacade, registroElaborazioni, result );
    }

    private static RegistroElaborazioni inserisciRegistroElaborazioniInizio ( JobFacade jobFacade, Ente ente, RevocaPerWso2 richiestaDiRevoca ) {
        Date data = new Date ();
        RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni ();
        registroElaborazioni.setDataInizio ( new Timestamp ( data.getTime () ) );
        registroElaborazioni.setOperazione ( "TrasmettiRichiesteDiRevoca" );
        registroElaborazioni.setEsito ( "elaborazione" );
        registroElaborazioni.setIdEnte ( ente.getIdEnte () );
        registroElaborazioni.setIdTipoPagamento ( null );
        registroElaborazioni.setPagamentiSpontanei ( false );
        registroElaborazioni.setIdMessaggio ( getIdMessaggioRandom () );
        registroElaborazioni.setNumPagamenti ( richiestaDiRevoca.getDati ().size () );
        registroElaborazioni.setImportoTotPagamenti ( richiestaDiRevoca.getRr ().getImportoTotaleRevocato () );
        registroElaborazioni.setPagamenti ( new ArrayList<Pagamento> () );

        Long id = jobFacade.inserisciRegistroElaborazioni ( registroElaborazioni );

        registroElaborazioni.setId ( id );
        return registroElaborazioni;
    }

    private void aggiornaRegistroElaborazioniFine ( JobFacade jobFacade, RegistroElaborazioni registroElaborazioni, ResultType result ) {
        Date data = new Date ();
        registroElaborazioni.setDataFine ( new Timestamp ( data.getTime () ) );
        registroElaborazioni.setMessageFault ( result.getCodice () + " - " + result.getMessaggio () );
        boolean hasResult;

        if ( StringUtils.isEmpty ( result.getCodice () ) ) {
            hasResult = false;
            String messaggio = result.getMessaggio ();
            if ( StringUtils.isEmpty ( messaggio ) ) {
                messaggio = "Il server non ha inviato nessuna risposta.";
            }
            registroElaborazioni.setMessageFault ( "NO_RESPONSE - " + messaggio );
            registroElaborazioni.setEsito ( "errore" );
            jobFacade.aggiornaRegistroElaborazioni ( registroElaborazioni, false );

        } else if ( Integer.parseInt ( result.getCodice () ) < 100 ) {
            hasResult = true;
            registroElaborazioni.setEsito ( "successo" );
            jobFacade.aggiornaRegistroElaborazioni ( registroElaborazioni, true );

        } else {
            hasResult = true;
            registroElaborazioni.setEsito ( "errore" );
            jobFacade.aggiornaRegistroElaborazioni ( registroElaborazioni, false );
        }

        if ( hasResult && Integer.parseInt ( result.getCodice () ) != 0 ) {
            InviaMailTrasmissioneNotifiche inviaMail = new InviaMailTrasmissioneNotifiche ( mailFacade );
            if ( Integer.parseInt ( result.getCodice () ) < 100 ) {
                inviaMail.inviaMailResponceWarning ( registroElaborazioni, result );
            } else {
                inviaMail.inviaMailResponceError ( registroElaborazioni, result );
            }
        }
    }

    private static void inserisciRegistroElaborazioniNoData ( JobFacade jobFacade, Ente ente, RevocaPerWso2 richiestaDiRevoca ) {
        Date data = new Date ();

        RegistroElaborazioni registroElaborazioni = new RegistroElaborazioni ();
        registroElaborazioni.setDataInizio ( new Timestamp ( data.getTime () ) );
        registroElaborazioni.setDataFine ( new Timestamp ( data.getTime () ) );
        registroElaborazioni.setOperazione ( "TrasmettiRichiesteDiRevoca" );
        registroElaborazioni.setEsito ( "noData" );

        if ( ente == null ) {
            registroElaborazioni.setMessageFault ( "Nessun ente abilitato all'invio delle richieste di revoca" );
        } else {
            registroElaborazioni.setIdEnte ( ente.getIdEnte () );
            if ( richiestaDiRevoca == null ) {
                registroElaborazioni.setMessageFault ( "Nessuna richiesta di revoca abilitata all'invio per l'ente " + ente.getNome () );
            }
        }
        jobFacade.inserisciRegistroElaborazioni ( registroElaborazioni );
    }

    private static String getIdMessaggioRandom () {
        return "TRR".concat ( UUID.randomUUID ().toString ().replace ( "-", "" ) );
    }

    private ResultType callService ( TrasmettiRichiesteDiRevocaRequest trasmettiRichiesteDiRevocaRequest ) {
        URL urlService = null;
        try {
            urlService = new URL ( url );
        } catch ( MalformedURLException e ) {
            System.exit ( -3 );
        }
        Sportello2EPaywso_Service service = new Sportello2EPaywso_Service ( urlService );
        Sportello2EPaywso sportello2EPaywso = service.getSportello2EPaywsoSOAP ();
        ResponseType responseType = sportello2EPaywso.trasmettiRichiesteDiRevoca ( trasmettiRichiesteDiRevocaRequest );
        return responseType.getResult ();
    }

    private static XMLGregorianCalendar date2xmlGregorianCalandar ( Date date ) {
        if ( date == null ) {
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar ();
        gc.setTime ( date );
        try {
            return DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gc );
        } catch ( DatatypeConfigurationException e ) {
            e.printStackTrace ();
        }
        return null;
    }

    private static XMLGregorianCalendar string2xmlGregorianCalandar ( String date ) {
        if ( date == null ) {
            return null;
        }
        String [] split = date.split ( "-" );
        GregorianCalendar gc = new GregorianCalendar ();
        gc.set ( GregorianCalendar.YEAR, Integer.parseInt ( split [0] ) );
        gc.set ( GregorianCalendar.MONTH, Integer.parseInt ( split [1] ) - 1 );
        gc.set ( GregorianCalendar.DAY_OF_MONTH, Integer.parseInt ( split [2] ) );
        try {
            return DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gc );
        } catch ( DatatypeConfigurationException e ) {
            e.printStackTrace ();
        }
        return null;
    }

    private static XMLGregorianCalendar timestamp2xmlGregorianCalandar ( Timestamp timestamp ) {
        if ( timestamp == null ) {
            return null;
        }
        try {
            Date date = new Date ( timestamp.getTime () );
            GregorianCalendar gc = new GregorianCalendar ();
            gc.setTime ( date );
            return DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gc );
        } catch ( DatatypeConfigurationException e ) {
            return null;
        }
    }
}
