/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.RiferimentoPagamento;
import it.csi.epay.epayweb.frontend.models.RiferimentoSenzaCodiceFiscale;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliConIuvPrivateGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoConIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.DatiPersonaliComplexValidator;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.RiferimentoComplexValidator;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.RiferimentoSenzaCodiceFiscaleComplexValidator;
import it.csi.epay.epayweb.model.enumeration.Wizzard;


@Controller
@Scope ( WebApplicationContext.SCOPE_SESSION )
@RequestMapping ( "/private/pagaAltroRiferimentoConIuv" )
public class PrivatePagamentoAltroRiferimentoConIuvController extends _Controller {

    private final static String VIEWNAMEPAGA1 = "private/pagamento1_altri_riferimenti_con_iuv";

    private final static String VIEWNAMEPAGA1CF = "private/pagamento1b_codice_fiscale";

    private final static String VIEWNAMEPAGA1RP = "private/pagamento1b_riferimenti_pagamento";

    private final static String VIEWNAMEPAGA2 = "private/pagamento2_altri_riferimenti_dati_personali";

    private final static String VIEWNAMEPAGA3 = "private/pagamento3_riepilogo";

    private final static String VIEWNAMEPAGA5 = "private/pagamento5_conclusione";

    private final static String PAYMENTURL = "/payment/start";

    private final static String ANNULLA_OP = "annullaOp";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private DatiPersonaliComplexValidator datiPersonaliComplexValidator;

    @Autowired
    private RiferimentoSenzaCodiceFiscaleComplexValidator riferimentoSenzaCodiceFiscaleComplexValidator;

    @Autowired
    private RiferimentoComplexValidator riferimentoComplexValidator;

    @InitBinder
    protected void initBinder ( WebDataBinder binder ) {
        binder.registerCustomEditor ( String.class, "email", new StringTrimmerEditor ( true ) );
        binder.registerCustomEditor ( String.class, "confirmEmail", new StringTrimmerEditor ( true ) );
    }

    @RequestMapping ( value = "/", method = RequestMethod.GET )
    public String pagaAltroRiferimentoConIuv ( HttpSession session ) {
        String methodName = "pagaAltroRiferimentoConIuv";
        log.debugStart ( methodName );

        removeAttribute ( session );

        log.debugEnd ( methodName );
        return "redirect:riferimenti";
    }

    @RequestMapping ( value = "/riferimenti", method = RequestMethod.GET )
    public String riferimentiGet ( HttpSession session, Model model ) {
        String methodName = "riferimentiGet";
        log.debugStart ( methodName );

        IdentitaShibboleth identitaShibboleth = (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" );
        identitaShibboleth.setCodiceFiscale ( "RSSMRA00A01L219U" );

        setAttributeIntoModel ( RiferimentoSenzaCodiceFiscale.class, model, session );

        model.addAttribute ( "commonData", initCommonData ( "riferimenti", 1, Boolean.FALSE, Boolean.TRUE,
            "Indica lo <strong>IUV</strong> (codice Identificativo Univoco di Versamento) presente nell'Avviso di pagamento che hai ricevuto e procedi con il pagamento che devi effettuare a favore della Pubblica Amministrazione.",
            session ) );

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
        List<RiferimentoPagamento> riferimentoPagamentos = new ArrayList<RiferimentoPagamento> ();
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
        throw new RuntimeException ( "Azione non gestita per PrivatePagamentoAltroRiferimentoConIuvController.riferimentiPost." );
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
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes ) {
        final String methodName = "codiceFiscalePost";
        log.debugStart ( methodName );
        log.debug ( methodName, "CF o PI: " + riferimento.getCodiceFiscale () );
        log.debug ( methodName, "IUV: " + riferimento.getIuv () );

        if ( "indietro".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:riferimenti";
        }

        mergeModel ( riferimento, getAttributeFromSession ( Riferimento.class, session ) );

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

        Riferimento riferimentoOld = getAttributeFromSession ( Riferimento.class, session );
        if ( !riferimento.equalCodiceFiscale ( riferimentoOld ) ) {
            removeAttributeFromSession ( DatiPersonali.class, session );
        }

        saveAttributeIntoSession ( riferimento, session );

        if ( "prosegui".equals ( action ) ) {
            log.debugEnd ( methodName );
            return "redirect:datiPersonali";
        }
        throw new RuntimeException ( "Azione non gestita per PrivatePagamentoAltroRiferimentoConIuvController.riferimentiPost." );
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
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes ) {
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
        throw new RuntimeException ( "Azione non gestita per PrivatePagamentoAltroRiferimentoConIuvController.riferimentiPagamentoPost." );

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
        @Validated ( { DatiPersonaliConIuvPrivateGroup.class, Default.class } ) @ModelAttribute DatiPersonali datiPersonali,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes ) {
        String methodName = "datiPersonaliPost";
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
        throw new RuntimeException ( "Azione non gestita per PrivatePagamentoAltroRiferimentoConIuvController.datiPersonaliPost." );
    }

    @RequestMapping ( value = "/riepilogo", method = RequestMethod.GET )
    public String riepilogoGet ( HttpSession session, Model model ) {
        String methodName = "riepilogoGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        setAttributeIntoModel ( DatiPersonali.class, model, session );

        Messaggi messaggi = (Messaggi) model.asMap ().get ( "messaggi" );
        Boolean prosegui = messaggi == null || messaggi.isEmpty () || ! ( messaggi.hasWarning () || messaggi.hasDanger () );
        if ( null != messaggi && messaggi.hasDanger () ) {
            session.setAttribute ( ANNULLA_OP, true );
        }

        model.addAttribute ( "commonData", initCommonData ( "riepilogo", 5, Boolean.TRUE, prosegui, null, session ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA3;
    }

    @RequestMapping ( value = "/riepilogo", method = RequestMethod.POST )
    public String riepilogoPost ( HttpSession session, @RequestParam String action ) {
        String methodName = "riepilogoPost";
        log.debugStart ( methodName );

        if ( "indietro".equals ( action ) ) {
            if ( session.getAttribute ( ANNULLA_OP ) != null ? (Boolean) session.getAttribute ( ANNULLA_OP ) : false ) {
                log.debugEnd ( methodName );
                return "redirect:/private/pagaAltroRiferimentoConIuv";
            }
            log.debugEnd ( methodName );
            return redirect ( "datiPersonali" );
        }
        if ( "prosegui".equals ( action ) ) {
            saveAttributeIntoSession ( Wizzard.PAGA_AUTH_CONIUV_RF, session );
            log.debugEnd ( methodName );
            return "forward:" + PAYMENTURL;
        }
        throw new RuntimeException ( "Azione non gestita per PrivatePagamentoAltroRiferimentoConIuvController.riepilogoPost." );
    }

    @RequestMapping ( value = "/conclusione", method = RequestMethod.GET )
    public String conclusione ( HttpSession session, Model model ) {
        String methodName = "conclusione";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );
        Riferimento riferimento = getAttributeFromSession ( Riferimento.class, session );
        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        riferimento.setIuv ( pagamento.getIuv () );
        model.addAttribute ( riferimento );
        setAttributeIntoModel ( DatiPersonali.class, model, session );

        model.addAttribute ( "commonData", initCommonData ( null, 6, Boolean.FALSE, Boolean.FALSE, null, session ) );

        log.debugEnd ( methodName );
        return VIEWNAMEPAGA5;
    }
}
