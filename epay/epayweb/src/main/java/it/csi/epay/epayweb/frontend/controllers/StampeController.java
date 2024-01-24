/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;
import it.csi.epay.epayweb.frontend.models.Ricevuta;
import it.csi.epay.epayweb.integration.epaybeapi.StampaAvvisoPagamento;
import it.csi.epay.epayweb.integration.epaybeapi.StampaAvvisoPagamentoRequest;
import it.csi.epay.epayweb.integration.epaybeapi.StampaAvvisoPagamentoResponse;
import it.csi.epay.epayweb.utilities.CifraturaIdPagamento;
import it.csi.epay.epayweb.utilities.XmlUtil;


@Controller
@Scope ( WebApplicationContext.SCOPE_SESSION )
@RequestMapping ( "/stampe" )
public class StampeController extends _Controller {

    @Value ( "${url.myApp}" )
    private String urlMyApp;

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private StampaAvvisoPagamento stampaAvvisoPagamento;

    @RequestMapping ( value = "/ricevutaPdf*", method = RequestMethod.GET, produces = "application/pdf" )
    public void ricevutaPdf ( HttpSession session, HttpServletResponse res ) {
        //String methodName = "ricevutaPdf";
        System.out.println ( "Call to StampeController.ricevutaPdf" );

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        try {
            Rt rt = pagamentoFacade.ricercaRt ( pagamento.getIdPagamento (), StatoPagamento.findById ( pagamento.getIdStatoCorrente () ) );
            Ricevuta ricevutaPdf = new Ricevuta ( rt.getRicevutaPdf () );
            String filename = "ricevuta_" + rt.getIdMsgRicevuta () + ".pdf";
            download ( filename, ricevutaPdf.getData (), res );
        } catch ( Exception e ) {
            PrintWriter out = null;
            try {
                out = res.getWriter ();
                out.println ( "Effettuare il pagamento prima" );
                out.close ();
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
            }
            throw new RuntimeException ( "Pagamento non trovato (id=" + pagamento.getIdPagamento () + ")" );
        }
    }

    @RequestMapping ( value = "/ricevutaPdf", method = RequestMethod.POST, produces = "application/pdf" )
    public void ricevutaPdfPost ( HttpServletResponse res,
        @RequestParam String codiceFiscale,
        @RequestParam String iuv,
        @RequestParam Integer idStatoPagamento ) {
        String methodName = "ricevutaPdfPost";
        log.debugStart ( methodName );

        try {
            List<Pagamento> pagamenti = pagamentoFacade.ricercaPagamentiAllPerCF ( codiceFiscale );

            for ( Pagamento pagamento: pagamenti ) {
                System.out.println ( XmlUtil.obj2Xml ( pagamento ) );
                System.out.println ();
                if ( pagamento.getIdStatoCorrente ().equals ( idStatoPagamento ) ) {
                    StatoPagamento stato = StatoPagamento.findById ( idStatoPagamento );
                    RegistroVersamenti versamento = pagamentoFacade.ricercaUltimaRegistrazioneVersamento ( pagamento.getIdPagamento (), stato );
                    if ( iuv.equals ( versamento.getIuv () ) || iuv.equals ( pagamento.getIuv () ) ) {
                        Rt rt = pagamentoFacade.ricercaRt ( pagamento.getIdPagamento (), stato );
                        Ricevuta ricevutaPdf = new Ricevuta ( rt.getRicevutaPdf () );
                        String filename = "ricevuta_" + rt.getIdMsgRicevuta () + ".pdf";
                        download ( filename, ricevutaPdf.getData (), res );
                        return;
                    }
                }
            }
        } catch ( Exception e ) {
            log.error ( methodName, ExceptionUtils.getStackTrace ( e ) );
        }

        PrintWriter out = null;
        try {
            out = res.getWriter ();
            out.println ( "Effettuare il pagamento prima" );
            out.close ();
        } catch ( IOException ioe ) {
            ioe.printStackTrace ();
        }

        log.debugEnd ( methodName );
        throw new RuntimeException ( "Pagamento non trovato ()" );
    }

    @RequestMapping ( value = "/ricevutaXml*", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE )
    public void ricevutaXml ( HttpSession session, HttpServletResponse res ) {
        //String methodName = "ricevutaXml";
        System.out.println ( "Call to StampeController.ricevutaXml" );

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        try {
            Rt rt = pagamentoFacade.ricercaRt ( pagamento.getIdPagamento (), StatoPagamento.findById ( pagamento.getIdStatoCorrente () ) );
            Ricevuta ricevutaXml = new Ricevuta ( rt.getRtXml () );
            String filename = "ricevuta_" + rt.getIdMsgRicevuta () + ".xml";
            download ( filename, ricevutaXml.getData (), res );
        } catch ( Exception e ) {
            PrintWriter out = null;
            try {
                out = res.getWriter ();
                out.println ( "Effettuare il pagamento prima" );
                out.close ();
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
            }
            throw new RuntimeException ( "Pagamento non trovato (id=" + pagamento.getIdPagamento () + ")" );
        }
    }

    @RequestMapping ( value = "/ricevutaXml", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE )
    public void ricevutaXmlPost ( HttpServletResponse res,
        @RequestParam String codiceFiscale,
        @RequestParam String iuv,
        @RequestParam Integer idStatoPagamento ) {
        String methodName = "ricevutaXmlPost";
        log.debugStart ( methodName );

        try {
            List<Pagamento> pagamenti = pagamentoFacade.ricercaPagamentiAllPerCF ( codiceFiscale );

            for ( Pagamento pagamento: pagamenti ) {
                if ( pagamento.getIdStatoCorrente ().equals ( idStatoPagamento ) ) {
                    StatoPagamento stato = StatoPagamento.findById ( idStatoPagamento );
                    RegistroVersamenti versamento = pagamentoFacade.ricercaUltimaRegistrazioneVersamento ( pagamento.getIdPagamento (), stato );
                    if ( iuv.equals ( versamento.getIuv () ) || iuv.equals ( pagamento.getIuv () ) ) {
                        Rt rt = pagamentoFacade.ricercaRt ( pagamento.getIdPagamento (), stato );
                        Ricevuta ricevutaXml = new Ricevuta ( rt.getRtXml () );
                        String filename = "ricevuta_" + rt.getIdMsgRicevuta () + ".xml";
                        download ( filename, ricevutaXml.getData (), res );
                        return;
                    }
                }
            }
        } catch ( Exception e ) {
            log.error ( methodName, ExceptionUtils.getStackTrace ( e ) );
        }

        PrintWriter out = null;
        try {
            out = res.getWriter ();
            out.println ( "Effettuare il pagamento prima" );
            out.close ();
        } catch ( IOException ioe ) {
            ioe.printStackTrace ();
        }

        log.debugEnd ( methodName );
        throw new RuntimeException ( "Pagamento non trovato ()" );
    }

    @RequestMapping ( value = "/quietanzaPdf*", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE )
    public void quietanzaPdf ( HttpSession session, HttpServletResponse res ) {
        //String methodName = "ricevutaXml";
        System.out.println ( "Call to StampeController.quietanzaPdf" );

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        try {
            EsitiRicevuti esito = pagamentoFacade.ricercaEsitiRicevutiByIUV ( pagamento.getIuv () );
            String filename = "quietanza_" + esito.getIdQuietanzaEsito () + ".pdf";
            download ( filename, esito.getRicevutaPdf (), res );
        } catch ( Exception e ) {
            PrintWriter out = null;
            try {
                out = res.getWriter ();
                out.println ( "Effettuare il pagamento prima" );
                out.close ();
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
            }
            throw new RuntimeException ( "Pagamento non trovato (id=" + pagamento.getIdPagamento () + ")" );
        }
    }

    @RequestMapping ( value = "/quietanzaPdf", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE )
    public void quietanzaPdfPost ( HttpServletResponse res,
        @RequestParam String codiceFiscale,
        @RequestParam String iuv,
        @RequestParam Integer idStatoPagamento ) {
        String methodName = "quietanzaPdfPost";
        log.debugStart ( methodName );

        try {
            List<Pagamento> pagamenti = pagamentoFacade.ricercaPagamentiAllPerCF ( codiceFiscale );

            //            for ( Pagamento pagamento: pagamenti ) {
            //                if ( pagamento.getIdStatoCorrente ().equals ( idStatoPagamento ) ) {
            //                    StatoPagamento stato = StatoPagamento.findById ( idStatoPagamento );
            //                    RegistroVersamenti versamento = pagamentoFacade.ricercaUltimaRegistrazioneVersamento ( pagamento.getIdPagamento (), stato );
            //                    if ( iuv.equals ( versamento.getIuv () ) || iuv.equals ( pagamento.getIuv () ) ) {
            //                        Rt rt = pagamentoFacade.ricercaRt ( pagamento.getIdPagamento (), stato );
            //                        Ricevuta ricevutaXml = new Ricevuta ( rt.getRtXml () );
            //                        String filename = "ricevuta_" + rt.getIdMsgRicevuta () + ".xml";
            //                        download ( filename, ricevutaXml.getData (), res );
            //                        return;
            //                    }
            //                }
            //            }
        } catch ( Exception e ) {
            log.error ( methodName, ExceptionUtils.getStackTrace ( e ) );
        }

        PrintWriter out = null;
        try {
            out = res.getWriter ();
            out.println ( "Effettuare il pagamento prima" );
            out.close ();
        } catch ( IOException ioe ) {
            ioe.printStackTrace ();
        }

        log.debugEnd ( methodName );
        throw new RuntimeException ( "Pagamento non trovato ()" );
    }

    @RequestMapping ( value = "/avvisoPagamento", method = RequestMethod.GET, produces = "application/pdf" )
    public void avvisoPagamento ( HttpSession session, HttpServletResponse res ) {
        String methodName = "avvisoPagamento";
        log.debugStart ( methodName );

        try {
            Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
            byte [] avvisoPdf = null;

            StampaAvvisoPagamentoRequest request = createAvvisoPagamento ( pagamento );

            StampaAvvisoPagamentoResponse avvisoPagamento = stampaAvvisoPagamento.getAvvisoPagamento ( request );

            int codEsito = Integer.parseInt ( avvisoPagamento.getCodEsito () );
            if ( codEsito != 0 ) {
                throw new Exception ( codEsito + ": " + avvisoPagamento.getDesEsito () );
            }
            avvisoPdf = avvisoPagamento.getAvvisoPagamentoData ();

            String filename = "avviso_" + pagamento.getIuv () + ".pdf";
            download ( filename, avvisoPdf, res );

        } catch ( Exception e ) {
            log.error ( methodName, "" );
            throw new RuntimeException ( e );
        } finally {
            log.debugEnd ( methodName );
        }
    }

    @RequestMapping ( value = "/avvisoPagamento/{id}", method = RequestMethod.GET, produces = "application/pdf" )
    public void avvisoPagamentoPerId ( HttpSession session, @PathVariable ( "id" ) String idPagamentoCifrato, HttpServletResponse res ) {
        //String methodName = "ricevutaXml";
        System.out.println ( "Call to StampeController.avvisoPagamento" );

        try {
            long idPagamento = CifraturaIdPagamento.decodifica ( idPagamentoCifrato );

            Pagamento pagamento = pagamentoFacade.ricerca ( idPagamento );

            if ( ( (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" ) ) != null ) {
                if ( ( (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" ) ).getCodiceFiscale () != null ) {
                    if ( !pagamento.getPagatore ().getCodiceFiscale ().equals (
                        ( (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" ) ).getCodiceFiscale () ) ) {
                        throw new Exception (
                            "Pagamento non trovato (id=" + pagamento.getIdPagamento () + ") per CF Pagatore : " + pagamento.getPagatore ().getCodiceFiscale ()
                            + " ATTESO " + ( (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" ) ).getCodiceFiscale () );
                    }
                }
            }

            byte [] avvisoPdf = null;

            StampaAvvisoPagamentoRequest request = createAvvisoPagamento ( pagamento );

            StampaAvvisoPagamentoResponse avvisoPagamento = stampaAvvisoPagamento.getAvvisoPagamento ( request );

            int codEsito = Integer.parseInt ( avvisoPagamento.getCodEsito () );
            if ( codEsito != 0 ) {
                throw new Exception ( codEsito + ": " + avvisoPagamento.getDesEsito () );
            }
            avvisoPdf = avvisoPagamento.getAvvisoPagamentoData ();

            String filename = "avviso_" + pagamento.getIuv () + ".pdf";
            download ( filename, avvisoPdf, res );
        } catch ( Exception e ) {
            PrintWriter out = null;
            try {
                out = res.getWriter ();
                out.println ( "Errore nella generazione del pdf" );
                out.close ();
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
            }
            throw new RuntimeException ( e );
        }
    }

    private void download ( String filename, byte [] data, HttpServletResponse res ) throws Exception {
        // Initialize response.
        //res.reset();
        //res.setContentType("application/xml"); // Check http://www.iana.org/assignments/media-types for all types.
        res.setHeader ( "Content-disposition", "attachment; filename=\"" + filename + "\"" );
        // Write file to response.
        OutputStream output = res.getOutputStream ();
        output.write ( data );
        output.close ();
    }

    private StampaAvvisoPagamentoRequest createAvvisoPagamento ( Pagamento pagamento ) {
        StampaAvvisoPagamentoRequest richiestaAvvisoPagamentoDto = new StampaAvvisoPagamentoRequest ();
        richiestaAvvisoPagamentoDto.setEnte ( pagamento.getEnte () );
        richiestaAvvisoPagamentoDto.setIuv ( pagamento.getIuv () );
        richiestaAvvisoPagamentoDto.setCodAvviso ( pagamento.getCodiceAvviso () );
        richiestaAvvisoPagamentoDto.setImportoTotale ( pagamento.getImporto () );
        richiestaAvvisoPagamentoDto.setCausale ( pagamento.getCausale () );
        richiestaAvvisoPagamentoDto.setScadenza ( pagamento.getDataScadenza () );
        richiestaAvvisoPagamentoDto.setSoggettoDebitore ( pagamento.getPagatore () );
        richiestaAvvisoPagamentoDto.setIdTipoPagamento ( pagamento.getTipoPagamento () != null ? pagamento.getTipoPagamento ().getIdTipoPagamento () : null );
        richiestaAvvisoPagamentoDto
        .setBollettinoPostale ( pagamento.getTipoPagamento () != null ? pagamento.getTipoPagamento ().getFlagPresenzaBollettinoPostale () : null );

        return richiestaAvvisoPagamentoDto;
    }

}
