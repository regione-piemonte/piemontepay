/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliSenzaIuvPrivateGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.DatiPersonaliComplexValidator;
import it.csi.epay.epayweb.model.enumeration.Wizzard;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/private/pagaSenzaIuv")
public class PrivatePagamentoSpontaneoController extends _Controller{

    private final static String VIEWNAMEPAGA1 = "private/pagamento1_riferimenti_senza_iuv";
    private final static String VIEWNAMEPAGA2 = "private/pagamento2_dati_personali";
    private final static String VIEWNAMEPAGA3 = "private/pagamento3_riepilogo";
    private final static String VIEWNAMEPAGA5 = "private/pagamento5_conclusione";
    private final static String PAYMENTURL = "/payment/start";

    private final static String ANNULLA_OP = "annullaOp";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private EnteFacade enteFacade;

    @Autowired
    private DatiPersonaliComplexValidator datiPersonaliComplexValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(true));
        binder.registerCustomEditor(String.class, "confirmEmail", new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String pagaSenzaIuv(HttpSession session) {
        String methodName = "pagaSenzaIuv";
        log.debugStart(methodName);
        removeAttribute(session);

        log.debugEnd(methodName);
        return "redirect:riferimenti";
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.GET)
    public String riferimentiGet(HttpSession session, Model model) {
        String methodName = "riferimentiGet";
        log.debugStart(methodName);
        setAttributeIntoModel(Riferimento.class, model, session);

        model.addAttribute("commonData", initCommonData("riferimenti", 1, Boolean.FALSE, Boolean.TRUE, "Non disponi di uno IUV (Identificativo Univoco Versamento), ma devi fare un pagamento verso la Pubblica Amministrazione? Puoi farlo indicando i dati richiesti in questa pagina e, a seguire, i tuoi dati personali.", session));
        log.debugEnd(methodName);
        return VIEWNAMEPAGA1;
    }

    @RequestMapping(value = "/riferimenti/tasse", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TipoPagamentoLight>  riferimentiTasse(@RequestParam Long idEnte) {
        String methodName = "riferimentiTasse";
        log.debugStart(methodName);
        Ente ente = new Ente();
        ente.setIdEnte(idEnte);
        List<TipoPagamentoLight> listaTasse = pagamentoFacade.elencoTipoPagamentoPerEnte(ente);
        log.debugEnd(methodName);
        return listaTasse;
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.POST)
    public String riferimentiPost(HttpSession session,
        @Validated({RiferimentoSenzaIuvGroup.class, Default.class}) @ModelAttribute Riferimento riferimento,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes) {
        String methodName = "riferimentiPost";
        log.debugStart(methodName);

        log.debug(methodName, "ENTE: " + riferimento.getEnteId());
        log.debug(methodName, "PAGAMENTO: " + riferimento.getPagamentoId());

        //riferimentoComplexValidator.validateConIuv(riferimento, bindingResult);
        if (bindingResult.hasErrors()) {
            return redirect("riferimenti", redirectAttributes, riferimento, bindingResult);
        }

        Riferimento riferimentoOld = getAttributeFromSession(Riferimento.class, session);
        if (riferimentoOld != null &&
                        (riferimento.getEnteId() == riferimentoOld.getEnteId() ||
                        riferimento.getPagamentoId() == riferimentoOld.getPagamentoId())) {
        } else {
            removeAttributeFromSession(DatiPersonali.class, session);
            Ente ente = enteFacade.ricerca(riferimento.getEnteId());
            riferimento.setEnteDesc(ente.getNome());
            riferimento.setCodiceFiscaleEnte ( ente.getCodiceFiscale () );
            try {
                TipoPagamento tipoPagamento = pagamentoFacade.ricercaTipoPagamento(riferimento.getPagamentoId());
                riferimento.setCodiceVersamento ( tipoPagamento.getCodiceVersamento () );
                if (tipoPagamento != null && tipoPagamento.getTipologiaPagamento() != null) {
                    if ("MABL".equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice() )
                    		|| "PABL".equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice() ) ){
                    	//FIXME RDI-34
                    	Messaggi messaggi = new Messaggi ();
                        messaggi.addMessaggio (
                            new Messaggio ( LevelMessage.DANGER, "Pagamento con marca da bollo. Non e' consentito il pagamento dallo Sportello PiemontePay, utilizzare l'apposito servizio" ) );
                        log.debugEnd ( methodName );
                        return redirect("riferimenti", redirectAttributes, messaggi);
                    }
                }

                
                riferimento.setCompilazioneNote(tipoPagamento.getCompilazioneNote());
                riferimento.setPagamentoDesc(tipoPagamento.getDescrizionePortale());
                
            } catch (NoDataException e) {
                throw new RuntimeException("Tipo pagamento non trovato (" + riferimento.getPagamentoId() + ")");
            }
            riferimento.setDataOperazione(new Date());
            saveAttributeIntoSession(riferimento, session);
        }

        log.debugEnd(methodName);
        if ("prosegui".equals(action)) {
            return "redirect:datiPersonali";
        } 
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoSpontaneoController.riferimentiPost.");
    }

    @RequestMapping(value = "/datiPersonali", method = RequestMethod.GET)
    public String datiPersonaliGet(HttpSession session, Model model) {
        String methodName = "datiPersonaliGet";
        log.debugStart(methodName);
        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        model.addAttribute("commonData", initCommonData("datiPersonali", 2, Boolean.TRUE, Boolean.TRUE, null, session));
        log.debugEnd(methodName);
        return VIEWNAMEPAGA2;
    }

    @RequestMapping(value = "/datiPersonali", method = RequestMethod.POST)
    public String datiPersonaliPost(HttpSession session,
        @Validated({DatiPersonaliSenzaIuvPrivateGroup.class, Default.class}) @ModelAttribute DatiPersonali datiPersonali, 
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes) {
        String methodName = "datiPersonaliPost";
        log.debugStart(methodName);

        if ("indietro".equals(action)) {
            return "redirect:riferimenti";
        }

        mergeModel(datiPersonali, getAttributeFromSession(DatiPersonali.class, session));

        datiPersonaliComplexValidator.validate(datiPersonali, bindingResult, Boolean.TRUE);
        if (bindingResult.hasErrors()) {
            return redirect("datiPersonali", redirectAttributes, datiPersonali, bindingResult);
        }

        saveAttributeIntoSession(datiPersonali, session);

        log.debugEnd(methodName);
        if ("prosegui".equals(action)) {
            return "redirect:riepilogo";
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoSpontaneoController.datiPersonaliPost.");
    }

    @RequestMapping(value = "/riepilogo", method = RequestMethod.GET)
    public String riepilogoGet(HttpSession session, Model model) {
        String methodName = "riepilogoGet";
        log.debugStart(methodName);
        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        Messaggi messaggi = (Messaggi) model.asMap ().get ( "messaggi" );
        Boolean prosegui = messaggi == null || messaggi.isEmpty () || ! ( messaggi.hasWarning () || messaggi.hasDanger () );
        if ( null != messaggi && messaggi.hasDanger () ) {
            session.setAttribute ( ANNULLA_OP, true );
        }

        model.addAttribute ( "commonData", initCommonData ( "riepilogo", 3, Boolean.TRUE, prosegui, null, session ) );
        log.debugEnd(methodName);
        return VIEWNAMEPAGA3;
    }

    @RequestMapping(value = "/riepilogo", method = RequestMethod.POST)
    public String riepilogoPost(HttpSession session, @RequestParam String action) {
        String methodName = "riepilogoPost";
        log.debugStart(methodName);

        if ("indietro".equals(action)) {
            if ( session.getAttribute ( ANNULLA_OP ) != null ? (Boolean) session.getAttribute ( ANNULLA_OP ) : false ) {
                log.debugEnd ( methodName );
                return "redirect:/private/pagaSenzaIuv";
            }
            return "redirect:datiPersonali";
        }
        if ("prosegui".equals(action)) {
            saveAttributeIntoSession(Wizzard.PAGA_AUTH_NOIUV, session);
            log.debugEnd(methodName);
            return "forward:" + PAYMENTURL;

        } 
        log.debugEnd(methodName);
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoSpontaneoController.riepilogoPost.");
    }

    @RequestMapping(value = "/conclusione", method = RequestMethod.GET)
    public String conclusione(HttpSession session, Model model) {
        String methodName = "conclusione";
        log.debugStart(methodName);

        setAttributeIntoModel(Riferimento.class, model, session);
        Riferimento riferimento = getAttributeFromSession(Riferimento.class, session);
        Pagamento pagamento = getAttributeFromSession(Pagamento.class, session);
        riferimento.setIuv(pagamento.getIuv());
        model.addAttribute(riferimento);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        model.addAttribute("commonData", initCommonData(null, 5, Boolean.FALSE, Boolean.FALSE, null, session));
        log.debugEnd(methodName);
        return VIEWNAMEPAGA5;
    }
}
