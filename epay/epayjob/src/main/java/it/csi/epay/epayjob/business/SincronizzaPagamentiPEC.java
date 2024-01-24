/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import it.csi.epay.epayjob.model.JobContext;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.PagamentoInvioPEC;
import it.csi.epay.epayservices.model.StatistichePagamenti;
import it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiPEC_Service;
import it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiRequest;
import it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiRequest.Pagamento;
import it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiResponse;
import it.csi.epaypa.sincronizzapagamentipec.TestResourcesRequest;

public class SincronizzaPagamentiPEC extends RtXml {

    private static final String CODICE_PEC_OK = "000";

    private static final String CONFIG_PROPERTIES = "config.properties";

    private static final int PAGE_SIZE = 100;

    private static final int TIME_LIMIT_SECONDS = 21600; // 6h

    private JobContext context;
    private Properties properties;
    private JobFacade jobFacade;

    public SincronizzaPagamentiPEC ( JobContext context, JobFacade jobFacade ) throws IllegalArgumentException, NoDataException {
        super();
        this.context = context;

        final String methodName = "constructor";
        try {
            context.infoStart ( methodName );

            this.jobFacade = jobFacade;

            try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
                properties = new Properties ();
                properties.load ( inputStream );
            } catch ( IOException e ) {
                context.debug ( methodName, "errore lettura parametri. " + e.getMessage () );
                throw new RuntimeException ( "Errore lettura parametri: " + e.getMessage () );
            }

            if ( properties.isEmpty () ) {
                context.debug ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
            }
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nel metodo " + methodName, t );
        } finally {
            context.infoEnd ( methodName );
        }
    }

    public void execute () throws Exception {
        final String methodName = "execute";
        context.infoStart ( methodName );
        try {
            doExecute ();
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nell'esecuzione del Job", t );
            throw t;
        } finally {
            context.infoEnd ( methodName );
        }
    }

    private void doExecute () throws Exception {
        final String methodName = "doExecute";
        Date startTime = new Date ();
        context.info ( methodName, "avvio elaborazione: " + startTime );

        it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiPEC facade = getPECFacade ();

        StatistichePagamenti statistiche = jobFacade.getStatistichePagamenti ();
        context.info ( methodName, "numero pagamenti: " + statistiche.getNumeroRecord () );
        context.info ( methodName, "ID massimo: " + statistiche.getIdMassimo () );

        // calcolo paginazione
//        Deque<Long []> pagine = paginaListaID ( statistiche, PAGE_SIZE );
//        Deque<Long []> pagine = new LinkedList<> ();
//        pagine.add(new Long[] {new Long(29271), new Long(47913)});

        context.info ( methodName, "elaborazione delle pagine avviata" );
        List<PagamentoInvioPEC> pagamenti;

//        Iterator<Long []> headFirstIterator = pagine.iterator ();
        boolean timedOut = false;
        long numeroElaborati = 0L;
        int numeroPagina= 1;
        while ( numeroElaborati< statistiche.getNumeroRecord () && !timedOut ) 
        {
            List<Long> idPagamenti = new LinkedList<Long> ();
//        while ( headFirstIterator.hasNext () && !timedOut ) {
            long elapsedMs = ( new Date ().getTime () ) - startTime.getTime ();

            context.info ( methodName, "timing elaborazione: trascorsi " + ( elapsedMs / 1000.0 ) + " secondi" );
            if ( elapsedMs >= ( TIME_LIMIT_SECONDS * 1000 ) ) {
                context.warn ( methodName, "raggiunto il limite temporale massimo di " + TIME_LIMIT_SECONDS + " secondi. L'elaborazione verra' interrotta." );
                timedOut = true;
            } else {

//                Long [] page = headFirstIterator.next ();
                context.info ( methodName, "avvio elaborazione pagine numero " +numeroPagina);

                pagamenti = jobFacade.ricercaPagamentoPerInvioPEC ( new Long(PAGE_SIZE));
//                List<PagamentoInvioPEC> nuovo = new LinkedList<PagamentoInvioPEC>();
//                 for (PagamentoInvioPEC pag: pagamenti)
//                 {
//                	 if (pag.getIuvNumeroAvviso().equals("202120564886119"))
//                	 {
//                		 nuovo.add(pag);
//                	 }
//                	 
//                 }
//                 pagamenti= nuovo;

                context.info ( methodName, "trovati " + pagamenti.size () + " pagamenti da inviare al PEC" );
                numeroElaborati += pagamenti.size ();

                if ( !pagamenti.isEmpty () ) {
                    SincronizzaPagamentiRequest parameters = new SincronizzaPagamentiRequest ();

                    for ( PagamentoInvioPEC pagamento: pagamenti ) {
                        parameters.getPagamento ().add ( mapPagamentoToPEC ( pagamento ) );
                        idPagamenti.add ( pagamento.getIdPagamento () );
                    }

                    SincronizzaPagamentiResponse result = sincronizzaPagamentiSOAPCall ( facade, parameters );

                    if ( result.getEsito ().getResult ().getCodice ().equals ( CODICE_PEC_OK ) ) {
                        // successo
                        context.info ( methodName, "invio della pagina riuscito, marco come inviati" );
                        jobFacade.marcaInviatiAlPEC ( idPagamenti );
                        context.info ( methodName, "mark della pagina riuscito" );

                    } else {
                        String errorMsg = "Sincronizzazione fallita: " + result.getEsito ().getResult ().getCodice () + " - " +
                                        result.getEsito ().getResult ().getMessaggio ();
                        context.error ( methodName, errorMsg );
                        // throw new RuntimeException ( errorMsg );
                    }
                }

                context.info ( methodName,
                    "terminata elaborazione pagina numero " + numeroPagina+ " - elaborati finora " + numeroElaborati + " record" );
                numeroPagina++;
            }
        }

        if ( timedOut ) {
            String msg = "Elaborazione interrotta per timeout. Sono stati elaborati " + numeroElaborati + " su " + statistiche.getNumeroRecord () + " record.";
            context.warn ( methodName, msg );
            context.addProblem ( msg );
        } else {
            context.info ( methodName, "Elaborazione delle pagine terminata con successo. Tutti i " + numeroElaborati + " record sono stati elaborati." );
        }

        long elapsedMs = ( new Date ().getTime () ) - startTime.getTime ();
        context.info ( methodName, "L'elaborazione ha impiegato " + ( elapsedMs / 1000.0 ) + " secondi" );
    }

    private SincronizzaPagamentiResponse sincronizzaPagamentiSOAPCall ( it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiPEC facade,
        SincronizzaPagamentiRequest parameters ) {
        final String methodName = "sincronizzaPagamentiSOAPCall";
        context.infoStart ( methodName );
        try {
            // log.debug ( methodName, "parametri chiamata SOAP:" + XmlUtil.obj2Xml ( parameters ) );
            return facade.sincronizzaPagamenti ( parameters );
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nell'esecuzione della chiamata SOAP sincronizzaPagamenti", t );
            throw t;
        } finally {
            context.infoEnd ( methodName );
        }
    }

    private Deque<Long []> paginaListaID ( StatistichePagamenti statistiche, int pageSize ) {
        String methodName = "paginaListaID";
        context.infoStart ( methodName );

        Deque<Long []> pagine = new LinkedList<> ();
        Long idFinale = statistiche.getIdMassimo () + 1;
        Long idIniziale = idFinale - pageSize;

        context.info ( methodName, "calcolo delle pagine di elaborazione avviato" );

        while ( idFinale > 0 ) {
            idIniziale = idFinale - pageSize;
            if ( idIniziale <= 0 ) {
                idIniziale = 1L;
            }

            if ( idIniziale >= idFinale ) {
                // elaborazione terminata
                break;
            }

            Long [] page = new Long [] { idIniziale, idFinale };
            pagine.add ( page );
            context.info ( methodName, "calcolata pagina " + page [0] + " - " + page [1] );

            idFinale = idIniziale;
        }

        context.info ( methodName, "calcolo delle pagine di elaborazione terminato" );
        context.infoEnd ( methodName );
        return pagine;
    }

    private Pagamento mapPagamentoToPEC ( PagamentoInvioPEC pagamento ) {
        Pagamento dto = new Pagamento ();
        dto.setIdPagamento ( pagamento.getIdPagamento () );
        try {
            dto.setDataPagamento ( pagamento.getDataPagamento () == null ? null
                            : dateToXMLGregorianCalendar ( new java.util.Date ( pagamento.getDataPagamento ().getTime () ) ) );
        } catch ( DatatypeConfigurationException e ) {
            throw new RuntimeException ( "Errore nel parsing del campo Data Pagamento", e );
        }
        dto.setIdStato ( pagamento.getIdStato () );
        dto.setDescStato ( pagamento.getDescStato () );
        dto.setImportoPagato ( pagamento.getImportoPagato () );
        dto.setIuvNumeroAvviso ( pagamento.getIuvNumeroAvviso () );
        dto.setPagamentoSpontaneo ( pagamento.getPagamentoSpontaneo () );
        dto.setCodVersamento ( pagamento.getCodVersamento () );
        dto.setDescrCodVersamento ( pagamento.getDescrCodVersamento () );
        dto.setCodFiscaleEnte ( pagamento.getCodFiscaleEnte () );
        dto.setNotePagamento( pagamento.getNotePagamento() );
        dto.setNome( pagamento.getNome() );
        dto.setCognome( pagamento.getCognome() );
        dto.setRagioneSociale( pagamento.getRagioneSociale() );
        dto.setCodiceFiscalePagatore( pagamento.getCodiceFiscalePagatore() );
        dto.setFlagPersonaFisica( pagamento.isFlagPersonaFisica() );
        dto.setCausale ( pagamento.getCausale () );
        try {
            dto.setDataScadenza ( dateToXMLGregorianCalendar ( pagamento.getDataScadenza () ) );
        } catch ( DatatypeConfigurationException e ) {
            throw new RuntimeException ( "Errore nel parsing del campo Data Scadenza", e );
        }
        return dto;
    }

    private it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiPEC getPECFacade () {
        final String methodName = "getPECFacade";
        context.infoStart ( methodName );
        try {
            URL urlService = null;

            String url = properties.getProperty ( "epaypawebbusiness.sincronizzapagamentipec.wsdl" );
            context.debug ( methodName, "endpoint: " + url );
            try {
                urlService = new URL ( url );
            } catch ( MalformedURLException e ) {
                throw new RuntimeException ( "Errore nel parsing della URL del servizio del PEC", e );
            }

            SincronizzaPagamentiPEC_Service service = new SincronizzaPagamentiPEC_Service ( urlService );

            it.csi.epaypa.sincronizzapagamentipec.SincronizzaPagamentiPEC facade = service.getSincronizzaPagamentiPEC ();

            ( (BindingProvider) facade ).getRequestContext ().put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url );

            context.info ( methodName, "verifica della disponibilita' del servizio del PEC avviato" );
            TestResourcesRequest trt = new TestResourcesRequest ();
            facade.testResources ( trt );
            context.info ( methodName, "verifica della disponibilita' del servizio del PEC terminato" );

            return facade;
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nella creazione del servizio del PEC", t );
            throw t;
        } finally {
            context.infoEnd ( methodName );
        }
    }

    public static XMLGregorianCalendar dateToXMLGregorianCalendar ( Date date ) throws DatatypeConfigurationException {
        if ( null == date ) {
            return null;
        } else {
            GregorianCalendar c = new GregorianCalendar ();
            c.setTime ( date );
            XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
            return xmlDate;
        }
    }

}
