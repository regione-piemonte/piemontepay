/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaFacade;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaFacade;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioniCampiRedirectAsyncFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.ConfigurazioniCampiRedirectAsync;
import it.csi.epay.epayservices.model.EsitoChiamataEsterna;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.ChiamataEsterna;
import it.csi.epay.epayweb.frontend.models.ConfigurazioniRedirectAsync;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.RiferimentoPagamento;
import it.csi.epay.epayweb.frontend.models.RiferimentoSenzaCodiceFiscale;
import it.csi.epay.epayweb.frontend.models.validators.groups.ChiamataEsternaGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliConIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoConIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.ChiamataEsternaComplexValidator;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.DatiPersonaliComplexValidator;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.RiferimentoComplexValidator;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.RiferimentoSenzaCodiceFiscaleComplexValidator;
import it.csi.epay.epayweb.model.enumeration.CampiRedirectAsync;
import it.csi.epay.epayweb.model.enumeration.Wizzard;
import it.csi.epay.epayweb.utilities.ReCAPTCHA;


@Controller
@Scope ( WebApplicationContext.SCOPE_SESSION )
@RequestMapping ( "/accessoLibero/pagaConIuv" )
public class AccessoLiberoPagamentoConIuvController extends _Controller {

    private final static String VIEWNAMEPAGA1 = "accesso_libero/pagamento1_riferimenti_con_iuv";

    private final static String VIEWNAMEPAGA1CF = "accesso_libero/pagamento1b_codice_fiscale";

    private final static String VIEWNAMEPAGA1RP = "accesso_libero/pagamento1b_riferimenti_pagamento";

    private final static String VIEWNAMEPAGA2 = "accesso_libero/pagamento2b_dati_personali";

    private final static String VIEWNAMEPAGA3 = "accesso_libero/pagamento3_riepilogo";

    private final static String VIEWNAMEPAGA5 = "accesso_libero/pagamento5_conclusione";

    private final static String VIEWNAMECHIAMATANONVALIDA = "accesso_libero/pagamento_chiamata_esterna_non_valida";

    private final static String VIEWNAMECHIAMATARESPONSE = "accesso_libero/pagamento_chiamata_esterna_non_valida_response";

    private final static String VIEWNAMEPAGACHIAMATAESTERNA = "accesso_libero/pagamento_chiamata_esterna_dati_personali_riepilogo";

    private final static String PAYMENTURL = "/payment/start";

    private final static String URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_ID_PAGAMENTO = "idPagamento";

    private final static String URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_DIGEST = "digest";

    private final static String URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_COD_ESITO = "codEsito";

    private final static int MAX_COL_DESCRIZIONE_ERRORE = 500;

    private final static int MAX_COL_DIGEST = 100;

    private final static int MAX_COL_UUID = 36;

    private final static String ANNULLA_OP = "annullaOp";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private ChiamataEsternaFacade chiamataEsternaFacade;

    @Autowired
    private ChiamataEsternaSincronaFacade chiamataEsternaSincronaFacade;

    @Autowired
    private RiferimentoComplexValidator riferimentoComplexValidator;

    @Autowired
    private DatiPersonaliComplexValidator datiPersonaliComplexValidator;

    @Autowired
    private ChiamataEsternaComplexValidator chiamataEsternaComplexValidator;

    @Autowired
    private RiferimentoSenzaCodiceFiscaleComplexValidator riferimentoSenzaCodiceFiscaleComplexValidator;

    @Autowired
    private ConfigurazioniCampiRedirectAsyncFacade configurazioniCampiRedirectAsyncFacade;

    @Autowired
    private ReCAPTCHA reCAPTCHA;

    @InitBinder
    protected void initBinder ( WebDataBinder binder ) {
        binder.registerCustomEditor ( String.class, "email", new StringTrimmerEditor ( true ) );
        binder.registerCustomEditor ( String.class, "confirmEmail", new StringTrimmerEditor ( true ) );
    }

    @RequestMapping ( value = "/", method = RequestMethod.GET )
    public String pagaConIuv ( HttpSession session ) {
        final String methodName = "pagaConIuv";
        log.debugStart ( methodName );

        removeAttribute ( session );

        log.debugEnd ( methodName );
        return "redirect:riferimenti";
    }

    @RequestMapping ( value = "/riferimenti", method = RequestMethod.GET )
    public String riferimentiGet ( HttpSession session, Model model ) {
        final String methodName = "riferimentiGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( RiferimentoSenzaCodiceFiscale.class, model, session );
        model.addAttribute ( "commonData", initCommonData ( "riferimenti", 1, Boolean.FALSE, Boolean.TRUE,
            "Indica lo <strong>IUV</strong> (codice Identificativo Univoco di Versamento) presente nell'Avviso di pagamento che hai ricevuto e procedi con il pagamento che devi effettuare a favore della Pubblica Amministrazione." ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA1;
    }

    @RequestMapping ( value = "/riferimenti", method = RequestMethod.POST )
    public String riferimentiPost ( HttpSession session,
        @Validated ( { RiferimentoConIuvGroup.class, Default.class } ) @ModelAttribute RiferimentoSenzaCodiceFiscale riferimentoSenzaCodiceFiscale,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes ) {
        final String methodName = "riferimentiPost";
        log.debugStart ( methodName );
        log.debug ( methodName, "IUV: " + riferimentoSenzaCodiceFiscale.getIuv () );

        riferimentoSenzaCodiceFiscaleComplexValidator.validate ( riferimentoSenzaCodiceFiscale, bindingResult );
        if ( bindingResult.hasErrors () ) {
            return redirect ( "riferimenti", redirectAttributes, riferimentoSenzaCodiceFiscale, bindingResult );
        }

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        List<RiferimentoPagamento> riferimentoPagamentos = new ArrayList<> ();
        for ( PagamentoRiferimenti pagamentoRiferimento: pagamento.getRiferimenti () ) {
            RiferimentoPagamento riferimentoPagamento = new RiferimentoPagamento ();
            riferimentoPagamento.setNome ( pagamentoRiferimento.getNome () );
            riferimentoPagamentos.add ( riferimentoPagamento );
        }
        riferimentoSenzaCodiceFiscale.setRiferimentiPagamento ( riferimentoPagamentos );

        RiferimentoSenzaCodiceFiscale riferimentoSenzaCodiceFiscaleOld = getAttributeFromSession ( RiferimentoSenzaCodiceFiscale.class, session );
        if ( !riferimentoSenzaCodiceFiscale.equalIuv ( riferimentoSenzaCodiceFiscaleOld ) ) {
            removeAttributeFromSession ( Riferimento.class, session );
            removeAttributeFromSession ( DatiPersonali.class, session );
        }

        saveAttributeIntoSession ( riferimentoSenzaCodiceFiscale, session );

        if ( "prosegui".equals ( action ) ) {
            log.debugEnd ( methodName );
            if ( "ANONIMO".equals ( pagamento.getPagatore ().getCodiceFiscale () ) ) {
                return "redirect:riferimentiPagamento";
            } else {
                return "redirect:codiceFiscale";
            }
        }
        throw new RuntimeException ( "Azione non gestita per AccessoLiberoPagamentoConIuvController.riferimentiPost." );
    }

    @RequestMapping ( value = "/codiceFiscale", method = RequestMethod.GET )
    public String codiceFiscaleGet ( HttpSession session, Model model ) {
        final String methodName = "codiceFiscaleGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        model.addAttribute ( "commonData", initCommonData ( "codiceFiscale", 2, Boolean.TRUE, Boolean.TRUE,
            "Indica il <strong>Codice Fiscale </strong> o la <strong>Partita IVA</strong> presente nell'Avviso di pagamento che hai ricevuto e procedi con il pagamento che devi effettuare a favore della Pubblica Amministrazione." ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA1CF;
    }

    @RequestMapping ( value = "/codiceFiscale", method = RequestMethod.POST )
    public String codiceFiscalePost ( HttpSession session,
        @Validated ( { RiferimentoConIuvGroup.class, Default.class } ) @ModelAttribute Riferimento riferimento,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes,
        @RequestParam ( value = "g-recaptcha-response", required = false ) String gRecaptchaResponse ) {
        final String methodName = "codiceFiscalePost";
        log.debugStart ( methodName );
        log.debug ( methodName, "CF o PI: " + riferimento.getCodiceFiscale () );
        log.debug ( methodName, "IUV: " + riferimento.getIuv () );

        if ( "indietro".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:riferimenti";
        }

        mergeModel ( riferimento, getAttributeFromSession ( Riferimento.class, session ) );

        if ( !reCAPTCHA.verify ( gRecaptchaResponse ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio (
                new Messaggio ( LevelMessage.INFO, "Prima di tutto, verificate di non essere un robot!" ) );
            log.debugEnd ( methodName );
            return redirect ( "codiceFiscale", redirectAttributes, riferimento, bindingResult, messaggi );
        }

        riferimentoComplexValidator.checkCodiceFiscale ( riferimento, bindingResult );
        if ( bindingResult.hasErrors () ) {
            return redirect ( "codiceFiscale", redirectAttributes, riferimento, bindingResult );
        }

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        riferimento.setIuv ( pagamento.getIuv () );
        riferimento.setEnteId ( pagamento.getEnte ().getIdEnte () );
        riferimento.setEnteDesc ( pagamento.getEnte ().getNome () );
        riferimento.setPagamentoId ( pagamento.getTipoPagamento ().getIdTipoPagamento () );
        riferimento.setPagamentoDesc ( pagamento.getTipoPagamento ().getDescrizionePortale () );
        riferimento.setCompilazioneNote ( pagamento.getTipoPagamento ().getCompilazioneNote () );
        riferimento.setDataOperazione ( new Date () );
        if ( !CollectionUtils.isEmpty ( pagamento.getComponenti () ) ) { 
            //:TODO sistemare meglio questo comportamento. Possono essere piu' componenti e capire come risolverlo.
            riferimento.setAnnoAccertamento ( pagamento.getComponenti ().get ( 0 ).getAnnoAccertamento () );
            riferimento.setNumeroAccertamento ( StringUtils.isNoneBlank ( pagamento.getComponenti ().get ( 0 ).getNumeroAccertamento () )
                            ? Integer.valueOf ( pagamento.getComponenti ().get ( 0 ).getNumeroAccertamento () ) : null );
        } else {
            // tengo la vecchia gestione per eventuali problemi di retrocompatibilita'
            if ( pagamento.getTipoPagamento () != null ) {
                riferimento.setAnnoAccertamento (
                    pagamento.getTipoPagamento ().getAnnoAccertamento () != null ? pagamento.getTipoPagamento ().getAnnoAccertamento ().intValue () : null );
                try {
                    riferimento.setNumeroAccertamento ( pagamento.getTipoPagamento ().getNumeroAccertamento () != null
                                    ? Integer.parseInt ( pagamento.getTipoPagamento ().getNumeroAccertamento () ) : null );
                } catch ( NumberFormatException e ) {
                    throw new RuntimeException ( "Numero accertamento del tipo pagamento " + riferimento.getPagamentoId () + " non valido" );
                }
            }
        }
        
        Riferimento riferimentoOld = getAttributeFromSession ( Riferimento.class, session );
        if ( !riferimento.equalCodiceFiscale ( riferimentoOld ) ) {
            removeAttributeFromSession ( DatiPersonali.class, session );
        }

        saveAttributeIntoSession ( riferimento, session );

        if ( "prosegui".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:datiPersonali";
        }

        if ( "salvaestampa".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:salvaestampa";
        }
        throw new RuntimeException ( "Azione non gestita per AccessoLiberoPagamentoConIuvController.riferimentiPost." );
    }

    @RequestMapping ( value = "/riferimentiPagamento", method = RequestMethod.GET )
    public String riferimentiPagamentoGet ( HttpSession session, Model model ) {
        final String methodName = "riferimentiPagamentoGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( RiferimentoSenzaCodiceFiscale.class, model, session );

        model.addAttribute ( "commonData", initCommonData ( "riferimentiPagamento", 2, Boolean.TRUE, Boolean.TRUE,
            "Indica i <strong>riferimenti pagamento</strong> associati allo (codice Identificativo Univoco di Versamento) presente nell'Avviso di pagamento che hai ricevuto e procedi con il pagamento che devi effettuare a favore della Pubblica Amministrazione." ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA1RP;
    }

    @RequestMapping ( value = "/riferimentiPagamento", method = RequestMethod.POST )
    public String riferimentiPagamentoPost ( HttpSession session,
        @Validated ( { RiferimentoConIuvGroup.class, Default.class } ) @ModelAttribute RiferimentoSenzaCodiceFiscale riferimentoSenzaCodiceFiscale,
        BindingResult bindingResult,
        @RequestParam String action,
        RedirectAttributes redirectAttributes ) {
        final String methodName = "riferimentiPagamentoPost";
        log.debugStart ( methodName );

        if ( "indietro".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:riferimenti";
        }

        mergeModel ( riferimentoSenzaCodiceFiscale, getAttributeFromSession ( RiferimentoSenzaCodiceFiscale.class, session ) );

        riferimentoSenzaCodiceFiscaleComplexValidator.validateRiferimentiPagamento ( riferimentoSenzaCodiceFiscale, bindingResult );
        if ( bindingResult.hasErrors () ) {
            log.debugEnd ( methodName );
            return redirect ( "riferimentiPagamento", redirectAttributes, riferimentoSenzaCodiceFiscale, bindingResult );
        }

        riferimentoSenzaCodiceFiscaleComplexValidator.checkPagamentoRiferimenti ( riferimentoSenzaCodiceFiscale, bindingResult );
        if ( bindingResult.hasErrors () ) {
            return redirect ( "riferimentiPagamento", redirectAttributes, riferimentoSenzaCodiceFiscale, bindingResult );
        }

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        Riferimento riferimento = new Riferimento ();
        riferimento.setRiferimentiPagamento ( riferimentoSenzaCodiceFiscale.getRiferimentiPagamento () );
        riferimento.setIuv ( pagamento.getIuv () );
        riferimento.setCodiceFiscale ( pagamento.getPagatore ().getCodiceFiscale () );
        riferimento.setEnteId ( pagamento.getEnte ().getIdEnte () );
        riferimento.setEnteDesc ( pagamento.getEnte ().getNome () );
        riferimento.setPagamentoId ( pagamento.getTipoPagamento ().getIdTipoPagamento () );
        riferimento.setPagamentoDesc ( pagamento.getTipoPagamento ().getDescrizionePortale () );
        riferimento.setCompilazioneNote ( pagamento.getTipoPagamento ().getCompilazioneNote () );
        riferimento.setDataOperazione ( new Date () );
        if ( pagamento.getTipoPagamento () != null ) {
            riferimento.setAnnoAccertamento (
                pagamento.getTipoPagamento ().getAnnoAccertamento () != null ? pagamento.getTipoPagamento ().getAnnoAccertamento ().intValue () : null );
            try {
                riferimento.setNumeroAccertamento ( pagamento.getTipoPagamento ().getNumeroAccertamento () != null
                                ? Integer.parseInt ( pagamento.getTipoPagamento ().getNumeroAccertamento () ) : null );
            } catch ( NumberFormatException e ) {
                throw new RuntimeException ( "Numero accertamento del tipo pagamento " + riferimento.getPagamentoId () + " non valido" );
            }
        }

        RiferimentoSenzaCodiceFiscale riferimentoOld = getAttributeFromSession ( RiferimentoSenzaCodiceFiscale.class, session );
        if ( !riferimentoSenzaCodiceFiscale.equalRiferimentiPagamento ( riferimentoOld ) ) {
            removeAttributeFromSession ( DatiPersonali.class, session );
        }
        saveAttributeIntoSession ( riferimento, session );
        saveAttributeIntoSession ( riferimentoSenzaCodiceFiscale, session );

        if ( "prosegui".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:datiPersonali";
        }
        throw new RuntimeException ( "Azione non gestita per AccessoLiberoPagamentoConIuvController.riferimentiPagamentoPost." );

    }

    @RequestMapping ( value = "/datiPersonali", method = RequestMethod.GET )
    public String datiPersonaliGet ( HttpSession session, Model model ) {
        final String methodName = "datiPersonaliGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        setAttributeIntoModel ( DatiPersonali.class, model, session );
        model.addAttribute ( "commonData", initCommonData ( "datiPersonali", 3, Boolean.TRUE, Boolean.TRUE, null ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA2;
    }

    @RequestMapping ( value = "/datiPersonali", method = RequestMethod.POST )
    public String datiPersonaliPost ( HttpSession session,
        @Validated ( { DatiPersonaliConIuvGroup.class, Default.class } ) @ModelAttribute DatiPersonali datiPersonali,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes ) {
        final String methodName = "datiPersonaliPost";
        log.debugStart ( methodName );

        mergeModel ( datiPersonali, getAttributeFromSession ( DatiPersonali.class, session ) );

        if ( "indietro".equals ( action ) ) {
            log.debugEnd ( methodName );
            if ( "ANONIMO".equals ( datiPersonali.getCodiceFiscale () ) ) {
                return "redirect:riferimentiPagamento";
            } else {
                return "redirect:codiceFiscale";
            }
        }

        datiPersonaliComplexValidator.validate ( datiPersonali, bindingResult, Boolean.FALSE );
        if ( bindingResult.hasErrors () ) {
            log.debugEnd ( methodName );
            return redirect ( "datiPersonali", redirectAttributes, datiPersonali, bindingResult );
        }

        saveAttributeIntoSession ( datiPersonali, session );

        if ( "prosegui".equals ( action ) ) {
            Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
            try {
                Anagrafica pagatore = pagamento.getPagatore ();
                pagatore.setEmail ( datiPersonali.getEmail () );
                pagamentoFacade.aggiornaPagatore ( pagamento.getIdPagamento (), pagatore );
                pagamentoFacade.aggiornaNote ( pagamento.getIdPagamento (), datiPersonali.getNote () );
                pagamentoFacade.aggiornaConsensoPrivacy ( pagamento.getIdPagamento (), datiPersonali.getFlagPrivacy () );
            } catch ( IllegalArgumentException | NoDataException e ) {
                throw new RuntimeException ( "Aggiornamento pagamento fallito (id=" + pagamento.getIdPagamento () + ")" );
            }
            log.debugEnd ( methodName );
            return redirect ( "riepilogo" );
        }
        throw new RuntimeException ( "Azione non gestita per AccessoLiberoPagamentoConIuvController.datiPersonaliPost." );
    }

    @RequestMapping ( value = "/riepilogo", method = RequestMethod.GET )
    public String riepilogoGet ( HttpSession session, Model model ) {
        final String methodName = "riepilogoGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        setAttributeIntoModel ( DatiPersonali.class, model, session );

        Messaggi messaggi = (Messaggi) model.asMap ().get ( "messaggi" );
        Boolean prosegui = messaggi == null || messaggi.isEmpty () || ! ( messaggi.hasWarning () || messaggi.hasDanger () );
        if ( null != messaggi && messaggi.hasDanger () ) {
            session.setAttribute ( ANNULLA_OP, true );
        }

        model.addAttribute ( "commonData", initCommonData ( "riepilogo", 4, Boolean.TRUE, prosegui, null ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA3;
    }

    @RequestMapping ( value = "/riepilogo", method = RequestMethod.POST )
    public String riepilogoPost ( HttpSession session, @RequestParam String action ) {
        final String methodName = "riepilogoPost";
        log.debugStart ( methodName );

        if ( "indietro".equals ( action ) ) {
            if ( session.getAttribute ( ANNULLA_OP ) != null ? (Boolean) session.getAttribute ( ANNULLA_OP ) : false ) {
                log.debugEnd ( methodName );
                return "redirect:/accessoLibero/pagaConIuv";
            }
            log.debugEnd ( methodName );
            return "redirect:datiPersonali";
        }
        if ( "prosegui".equals ( action ) ) {
            saveAttributeIntoSession ( Wizzard.PAGA_FREE_CONIUV, session );
            log.debugEnd ( methodName );
            return "forward:" + PAYMENTURL;
        }
        throw new RuntimeException ( "Azione non gestita per AccessoLiberoPagamentoConIuvController.riepilogoPost." );
    }

    @RequestMapping ( value = "/conclusione", method = RequestMethod.GET )
    public String conclusione ( HttpSession session, Model model ) {
        final String methodName = "conclusione";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        Riferimento riferimento = getAttributeFromSession ( Riferimento.class, session );
        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        riferimento.setIuv ( pagamento.getIuv () );
        model.addAttribute ( riferimento );
        setAttributeIntoModel ( DatiPersonali.class, model, session );

        model.addAttribute ( "commonData", initCommonData ( null, 6, Boolean.FALSE, Boolean.FALSE, null ) );

        //LF 21/01/2019 - RDI018/019 INIZIO

        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = getAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );

        if ( null != tracciabilitaChiamanteEsterno && !StringUtils.isBlank ( tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getUrl () ) ) {

            Messaggi messaggi = (Messaggi) model.asMap ().get ( "messaggi" );

            String esito = "";

            if ( null != messaggi && null != messaggi.getFirstMessage () && null != messaggi.getFirstMessage ().getLevel () ) {

                LevelMessage levelMessage = messaggi.getFirstMessage ().getLevel ();

                esito = "&" + URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_COD_ESITO + "=";

                if ( LevelMessage.SUCCESS.equals ( levelMessage ) ) {
                    esito = esito + EsitoChiamataEsterna.OPERAZIONE_COMPLETATA_CON_SUCCESSO;
                } else {
                    esito = esito + EsitoChiamataEsterna.ERRORE_GENERICO;
                }

            }

            String url = tracciabilitaChiamanteEsterno.getChiamanteEsterno ().getUrl () + "?"
                + URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_ID_PAGAMENTO + "="
                + tracciabilitaChiamanteEsterno.getIdentificativoPagamento () + "&"
                + URL_RITORNO_CHIAMANTE_ESTERNO_PARAM_DIGEST + "=" + tracciabilitaChiamanteEsterno.getDigest ()
                + esito;

            model.addAttribute ( "urlChiamanteEsterno", url );
            removeAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );
        }
        //LF 21/01/2019 - RDI018/019 FINE

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA5;
    }

    // LF 17/01/2019 - RDI-018 INIZIO
    @RequestMapping ( value = "/accessoChiamanteEsterno", method = RequestMethod.POST )
    public String accessoChiamanteEsterno ( HttpSession session,
        @Validated ( { ChiamataEsternaGroup.class, Default.class } ) @ModelAttribute ChiamataEsterna chiamataEsterna, BindingResult bindingResult,
        RedirectAttributes redirectAttributes, HttpServletRequest request ) {

        String methodName = "accessoChiamanteEsterno";

        log.debugStart ( methodName );

        if ( bindingResult.hasErrors () ) {
            return redirect ( "chiamataEsternaNonValida", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), stampaErrori ( methodName, bindingResult ) ), bindingResult );
        }

        try {
            Long numeroUUIDTrovati = 0L;
            try {
                numeroUUIDTrovati = chiamataEsternaFacade.ricercaIdentificativoPagamento ( chiamataEsterna.getIdentificativoPagamento () );
            } catch ( NoDataException | IllegalAccessException e ) {
                //Do nothing
            }
            Assert.isTrue ( Long.compare ( 0L, numeroUUIDTrovati ) >= 0,
                String.format ( "UUID %s presente in archivio", chiamataEsterna.getIdentificativoPagamento () ) );
        } catch ( IllegalArgumentException e ) {
            log.debugEnd ( methodName );
            log.error ( methodName, String.format ( "UUID %s presente in archivio",
                chiamataEsterna.getIdentificativoPagamento () ), e );
            return redirect ( "chiamataEsternaNonValida", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), e.getMessage () ), bindingResult );
        }
        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = new TracciabilitaChiamanteEsterno ();
        ChiamanteEsterno ce = null;
        try {
            ce = chiamataEsternaFacade.recuperaChiamanteEsterno ( chiamataEsterna.getCodiceChiamante () );

            if ( null == ce ) {
                throw new NoDataException ( "Chiamante esterno non riconosciuto, non attivo o passphrase errata" );
            }

        } catch ( IllegalAccessException | NoDataException e ) {
            log.debugEnd ( methodName );
            log.error ( methodName, String.format ( "Chiamante esterno con codice %s non riconosciuto",
                chiamataEsterna.getCodiceChiamante () ), e );
            return redirect ( "chiamataEsternaNonValida", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), e.getMessage () ),
                bindingResult );
        }

        tracciabilitaChiamanteEsterno.setChiamanteEsterno ( ce );
        tracciabilitaChiamanteEsterno.setCodiceFiscale ( chiamataEsterna.getCodiceFiscale () );
        tracciabilitaChiamanteEsterno.setDigest ( chiamataEsterna.getDigest () );
        tracciabilitaChiamanteEsterno.setIuv ( chiamataEsterna.getIuv () );
        tracciabilitaChiamanteEsterno.setRemoteHost ( request.getRemoteHost () );
        tracciabilitaChiamanteEsterno.setIdentificativoPagamento ( chiamataEsterna.getIdentificativoPagamento () );

        chiamataEsternaComplexValidator.validateAndVerify ( chiamataEsterna, bindingResult );

        if ( bindingResult.hasErrors () ) {
            log.debugEnd ( methodName );
            String error = stampaErrori ( methodName, bindingResult );
            return redirect ( "chiamataEsternaNonValida", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), error ), bindingResult );
        }

        Riferimento riferimento = new Riferimento ();

        riferimento.setCodiceFiscale ( chiamataEsterna.getCodiceFiscale () );
        riferimento.setIuv ( chiamataEsterna.getIuv () );

        riferimentoComplexValidator.validateAndVerify ( riferimento, bindingResult );

        if ( bindingResult.hasErrors () ) {
            log.debugEnd ( methodName );
            return redirect ( "chiamataEsternaResponse", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), stampaErrori ( methodName, bindingResult ) ), bindingResult );
        }

        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        riferimento.setEnteId ( pagamento.getEnte ().getIdEnte () );
        riferimento.setEnteDesc ( pagamento.getEnte ().getNome () );
        riferimento.setPagamentoId ( pagamento.getTipoPagamento ().getIdTipoPagamento () );
        riferimento.setPagamentoDesc ( pagamento.getTipoPagamento ().getDescrizionePortale () );
        riferimento.setCompilazioneNote ( pagamento.getTipoPagamento ().getCompilazioneNote () );
        riferimento.setDataOperazione ( new Date () );

        //BLOCCO PER RDI-02 - START
        String applicativoChiamante = ce.getCodiceChiamante ();
        Long codiceVersamento = pagamento.getTipoPagamento ().getIdTipoPagamento ();

        if ( !chiamataEsternaSincronaFacade.validaAutorizzazioneChiamanteTipoPagamento ( applicativoChiamante, codiceVersamento ) ) {
            return redirect ( "chiamataEsternaNonValida", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), "Chiamante non autorizzato al tipo pagamento indicato" ),
                bindingResult );
        }
        //BLOCCO PER RDI-02 - STOP

        Riferimento riferimentoOld = getAttributeFromSession ( Riferimento.class, session );
        if ( !riferimento.equalIuv ( riferimentoOld ) ) {
            removeAttributeFromSession ( DatiPersonali.class, session );
        }
        try {
            Long idChiamata = chiamataEsternaFacade.inserisci ( tracciabilitaChiamanteEsterno );
            tracciabilitaChiamanteEsterno.setIdChiamata ( idChiamata );
        } catch ( Exception e ) {
            redirect ( "chiamataEsternaNonValida", redirectAttributes,
                createChiamataEsternaNonValida ( chiamataEsterna, request.getRemoteHost (), e.getMessage () ), bindingResult );
        }

        saveAttributeIntoSession ( riferimento, session );

        saveAttributeIntoSession ( tracciabilitaChiamanteEsterno, session );

        // EPAY-200 (04/2020) controllo la configurazione dei campi della redirect.
        // Seleziono la configurazione dei campi dal database. 
        // Se tutti i campi sono false, quindi non devono essere mostrati, si accede direttamente al WISP
        // Se invece ci sono campi che sono a true, si accede ad una pagina intermedia dello sportello, 
        // dove compilare i campi obbligatori e poi da li' si va al WISP.

        removeAttributeFromSession ( ConfigurazioniRedirectAsync.class, session );

        ConfigurazioniRedirectAsync configurazioniRedirectAsync = new ConfigurazioniRedirectAsync ();

        try {
            List<ConfigurazioniCampiRedirectAsync> configurazioni = configurazioniCampiRedirectAsyncFacade.getConfigurazioneCampi ();

            for ( ConfigurazioniCampiRedirectAsync configurazione: configurazioni ) {

                CampiRedirectAsync config = CampiRedirectAsync.findById ( configurazione.getId () );

                if ( config == null ) {
                    log.warn ( methodName, "Parametro configurativo non riconosciuto" );
                    continue;
                }

                switch ( config ) {
                case EMAIL :
                    configurazioniRedirectAsync.setEmail ( configurazione.getFlgAbilitato () );
                    break;
                case RIPETI_EMAIL :
                    configurazioniRedirectAsync.setRipetiEmail ( configurazione.getFlgAbilitato () );
                    break;
                case VERIFICA_PRIVACY :
                    configurazioniRedirectAsync.setVerificaPrivacy ( configurazione.getFlgAbilitato () );
                    break;
                case VERIFICA_CAPTCHA :
                    configurazioniRedirectAsync.setVerificaCaptcha ( configurazione.getFlgAbilitato () );
                    break;
                default :
                    break;
                }
            }
        } catch ( NoDataException e ) {
            log.warn ( methodName, "Nessun parametro configurativo individuato" );
        }

        if ( Boolean.FALSE.equals ( configurazioniRedirectAsync.getEmail () )
            && Boolean.FALSE.equals ( configurazioniRedirectAsync.getRipetiEmail () )
            && Boolean.FALSE.equals ( configurazioniRedirectAsync.getVerificaPrivacy () )
            && Boolean.FALSE.equals ( configurazioniRedirectAsync.getVerificaCaptcha () ) ) {

            saveAttributeIntoSession ( Wizzard.PAGA_FREE_CONIUV, session );
            log.debugEnd ( methodName );
            return "forward:" + PAYMENTURL;
        } else {
            saveAttributeIntoSession ( configurazioniRedirectAsync, session );
            log.debugEnd ( methodName );
            return "redirect:datiPersonaliERiepilogo";
        }
    }

    @RequestMapping ( value = "/salvaestampa", method = RequestMethod.GET )
    public String salvaestampa ( HttpSession session ) {
        final String methodName = "salvaestampa";
        log.debugStart ( methodName );

        log.debugEnd ( methodName );
        return "forward:/payment/salvaPagamento?stampa=true";
    }

    @RequestMapping ( value = "/chiamataEsternaNonValida", method = RequestMethod.GET )
    public String chiamataEsternaNonValida ( HttpSession session, @ModelAttribute ChiamataEsterna chiamataEsterna,
        BindingResult bindingResult ) {

        String methodName = "chiamataEsternaNonValida";

        log.debugStart ( methodName );

        removeAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );

        log.debugEnd ( methodName );

        return VIEWNAMECHIAMATANONVALIDA;

    }

    @RequestMapping ( value = "/chiamataEsternaResponse", method = RequestMethod.GET )
    public String chiamataEsternaFailResponse ( HttpSession session, @ModelAttribute ChiamataEsterna chiamataEsterna,
        BindingResult bindingResult ) {

        String methodName = "chiamataEsternaFailResponse";

        log.debugStart ( methodName );

        removeAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );

        log.debugEnd ( methodName );

        return VIEWNAMECHIAMATARESPONSE;

    }

    private ChiamataEsternaNonValida createChiamataEsternaNonValida ( ChiamataEsterna chiamataEsterna, String remoteHost, String errorMessage ) {
        ChiamataEsternaNonValida chiamataEsternaNonValida = new ChiamataEsternaNonValida ();

        chiamataEsternaNonValida.setCodiceChiamante ( chiamataEsterna.getCodiceChiamante () );
        chiamataEsternaNonValida.setCodiceFiscale ( chiamataEsterna.getCodiceFiscale () );
        chiamataEsternaNonValida.setDescrizioneErrore ( StringUtils.abbreviate ( errorMessage, MAX_COL_DESCRIZIONE_ERRORE ) );
        chiamataEsternaNonValida.setDigest ( StringUtils.abbreviate ( chiamataEsterna.getDigest (), MAX_COL_DIGEST ) );
        chiamataEsternaNonValida.setIdentificativoPagamento ( StringUtils.abbreviate ( chiamataEsterna.getIdentificativoPagamento (), MAX_COL_UUID ) );
        chiamataEsternaNonValida.setIuv ( chiamataEsterna.getIuv () );
        chiamataEsternaNonValida.setRemoteHost ( remoteHost );
        chiamataEsternaNonValida.setIdChiamata ( chiamataEsternaFacade.inserisciChiamataEsternaNonValida ( chiamataEsternaNonValida ) );
        return chiamataEsternaNonValida;
    }

    private String stampaErrori ( String methodName, BindingResult bindingResult ) {

        log.warn ( methodName, "Chiamata da parte di un sistema esterno non valida. Inizio errori" );
        StringBuilder errors = new StringBuilder ();

        for ( FieldError error: bindingResult.getFieldErrors () ) {
            errors = errors.append ( String.format ( "[%s] Errore sul campo %s, con valore %s. Messaggio = %s\n", methodName, error.getField (),
                error.getRejectedValue (), error.getDefaultMessage () ) );
            log.warn ( methodName, String.format ( "Errore sul campo %s, con valore %s. Messaggio = %s", error.getField (),
                error.getRejectedValue (), error.getDefaultMessage () ) );
        }

        for ( ObjectError error: bindingResult.getGlobalErrors () ) {
            errors = errors.append ( String.format ( "[%s] Errore globale. Messaggio = %s\n", methodName, error.getDefaultMessage () ) );
            log.warn ( methodName, String.format ( "Errore globale. Messaggio = %s", error.getDefaultMessage () ) );

        }
        log.warn ( methodName, "Chiamata da parte di un sistema esterno non valida. Fine errori" );
        return errors.toString ();
    }
    // LF 17/01/2019 - RDI-018 FINE

    //    EPAY-200 (04/2020)
    @RequestMapping ( value = "/datiPersonaliERiepilogo", method = RequestMethod.GET )
    public String datiPersonaliERiepilogo ( HttpSession session, Model model ) {
        final String methodName = "datiPersonaliERiepilogo";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        setAttributeIntoModel ( DatiPersonali.class, model, session );
        setAttributeIntoModel ( ConfigurazioniRedirectAsync.class, model, session );

        model.addAttribute ( "commonData", initCommonData ( "datiPersonaliERiepilogo", 3, Boolean.TRUE, Boolean.TRUE, null ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGACHIAMATAESTERNA;

    }

    @RequestMapping ( value = "/datiPersonaliERiepilogo", method = RequestMethod.POST )
    public String datiPersonaliERiepilogoPost ( HttpSession session,
        @Validated ( { DatiPersonaliConIuvGroup.class, Default.class } ) @ModelAttribute DatiPersonali datiPersonali,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes,
        @RequestParam ( value = "g-recaptcha-response", required = false ) String gRecaptchaResponse ) {
        final String methodName = "datiPersonaliERiepilogoPost";
        log.debugStart ( methodName );

        mergeModel ( datiPersonali, getAttributeFromSession ( DatiPersonali.class, session ) );

        ConfigurazioniRedirectAsync configurazioniRedirectAsync = getAttributeFromSession ( ConfigurazioniRedirectAsync.class, session );

        if ( "indietro".equals ( action ) ) {
            log.debugEnd ( methodName );
            if ( "ANONIMO".equals ( datiPersonali.getCodiceFiscale () ) ) {
                return "redirect:riferimentiPagamento";
            } else {
                return "redirect:codiceFiscale";
            }
        }

//        if ( ( configurazioniRedirectAsync.getVerificaCaptcha () == null || configurazioniRedirectAsync.getVerificaCaptcha () )
//            && !reCAPTCHA.verify ( gRecaptchaResponse ) ) {
//            Messaggi messaggi = new Messaggi ();
//            messaggi.addMessaggio (
//                new Messaggio ( LevelMessage.INFO, "Prima di tutto, verificate di non essere un robot!" ) );
//            log.debugEnd ( methodName );
//            return redirect ( "datiPersonaliERiepilogo", redirectAttributes, datiPersonali, bindingResult, messaggi );
//        }

        datiPersonaliComplexValidator.validate ( datiPersonali, bindingResult, Boolean.FALSE );

        if ( bindingResult.hasErrors () ) {
            boolean emailError = ( ( configurazioniRedirectAsync.getEmail () == null || configurazioniRedirectAsync.getEmail () )
                && ( configurazioniRedirectAsync.getRipetiEmail () == null || configurazioniRedirectAsync.getRipetiEmail () )
                && ( bindingResult.getFieldError ( "email" ) != null || bindingResult.getFieldError ( "confirmEmail" ) != null ) );
            boolean privacyError = ( ( configurazioniRedirectAsync.getVerificaPrivacy () == null || configurazioniRedirectAsync.getVerificaPrivacy () )
                && bindingResult.getFieldError ( "flagPrivacy" ) != null );
            if ( emailError || privacyError ) {
                log.debugEnd ( methodName );
                return redirect ( "datiPersonaliERiepilogo", redirectAttributes, datiPersonali, bindingResult );
            }

        }

        saveAttributeIntoSession ( datiPersonali, session );

        if ( "prosegui".equals ( action ) ) {
            Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
            if ( pagamento == null ) {
                throw new RuntimeException ( "Impossibile recuperare il pagamento" );
            }
            try {

                if ( ( configurazioniRedirectAsync.getEmail () == null || configurazioniRedirectAsync.getEmail () )
                    && ( configurazioniRedirectAsync.getRipetiEmail () == null || configurazioniRedirectAsync.getRipetiEmail () ) ) {
                    Anagrafica pagatore = pagamento.getPagatore ();
                    pagatore.setEmail ( datiPersonali.getEmail () );
                    pagamentoFacade.aggiornaPagatore ( pagamento.getIdPagamento (), pagatore );
                }

                if ( configurazioniRedirectAsync.getVerificaPrivacy () == null || configurazioniRedirectAsync.getVerificaPrivacy () ) {
                    pagamentoFacade.aggiornaConsensoPrivacy ( pagamento.getIdPagamento (), datiPersonali.getFlagPrivacy () );

                }
            } catch ( IllegalArgumentException | NoDataException e ) {
                throw new RuntimeException ( "Aggiornamento pagamento fallito (id=" + pagamento.getIdPagamento () + ")" );
            }
            log.debugEnd ( methodName );
            saveAttributeIntoSession ( Wizzard.PAGA_FREE_CONIUV, session );
            return "forward:" + PAYMENTURL;
        }
        throw new RuntimeException ( "Azione non gestita per AccessoLiberoPagamentoConIuvController.datiPersonaliPost." );
    }
}
