/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliConIuvPrivateGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.DatiPersonaliComplexValidator;
import it.csi.epay.epayweb.model.enumeration.Wizzard;
import it.csi.epay.epayweb.utilities.CifraturaIdPagamento;
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

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/private/pagaConIuv")
public class PrivatePagamentoConIuvController extends _Controller {

    private final static String VIEWNAMEPAGA1 = "private/pagamento1_riferimenti_con_iuv";
    private final static String VIEWNAMEPAGA2 = "private/pagamento2_dati_personali";
    private final static String VIEWNAMEPAGA3 = "private/pagamento3_riepilogo";
    private final static String VIEWNAMEPAGA5 = "private/pagamento5_conclusione";
    private final static String PAYMENTURL = "/payment/start";

    private final static String ANNULLA_OP = "annullaOp";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private DatiPersonaliComplexValidator datiPersonaliComplexValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(true));
        binder.registerCustomEditor(String.class, "confirmEmail", new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String pagaConIuv(HttpSession session) {
        String methodName = "pagaConIuv";
        log.debugStart(methodName);

        removeAttribute(session);

        log.debugEnd(methodName);
        return "redirect:riferimenti";
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.GET)
    public String riferimentiGet(HttpSession session, Model model) {
        String methodName = "riferimentiGet";
        log.debugStart(methodName);

        IdentitaShibboleth identitaShibboleth = (IdentitaShibboleth)session.getAttribute("identitaShibboleth");
        List<Pagamento> elencoPagamenti = pagamentoFacade.ricercaPagamentiAttiviNonSpontaneiPerCF(identitaShibboleth.getCodiceFiscale());
        log.debug(methodName, toXml(elencoPagamenti));
        elencoPagamenti = elencoPagamenti.stream().filter(filtraPagamentiNonMarcaBollo()).collect(Collectors.toCollection ( new Supplier<LinkedList<Pagamento>> () {
        	@Override
            public LinkedList<Pagamento> get () {
               return new LinkedList<> ();
            }
        }));
        //Codifica dell'idpagamento per evitare un accesso diretto all'avviso
        for(Pagamento pagamento:elencoPagamenti) {
            Long idPagamento = pagamento.getIdPagamento ();
            String idPagamentoCifrato = CifraturaIdPagamento.codifica ( idPagamento);
            
            pagamento.setIdPagamentoCifrato ( idPagamentoCifrato );
        }
        
        model.addAttribute("elencoPagamenti", elencoPagamenti);
        model.addAttribute("commonData", initCommonData("riferimenti", 1, Boolean.FALSE, Boolean.TRUE, "Indica lo <strong>IUV</strong> (codice Identificativo Univoco di Versamento) presente nell'Avviso di pagamento che hai ricevuto e procedi con il pagamento che devi effettuare a favore della Pubblica Amministrazione.", session));

        log.debugEnd(methodName);
        return VIEWNAMEPAGA1;
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.POST)
    public String riferimentiPost(HttpSession session, @RequestParam String idPagamentoCifrato, RedirectAttributes redirectAttributes) {
        String methodName = "riferimentiPost";
        log.debugStart(methodName);

        Pagamento pagamento = null;
        try {
            long idPagamento = CifraturaIdPagamento.decodifica ( idPagamentoCifrato );
            
            log.debug(methodName, "id pagamento cifrato : " + idPagamentoCifrato);
            log.debug(methodName, "id pagamento plain : " + idPagamento);
            
            pagamento = pagamentoFacade.ricerca(idPagamento);
        } catch (Exception e) {
            throw new RuntimeException("Pagamento non trovato (id=" + idPagamentoCifrato + ")");
        }

        if(((IdentitaShibboleth)session.getAttribute("identitaShibboleth")) != null) {
            if(((IdentitaShibboleth)session.getAttribute("identitaShibboleth")).getCodiceFiscale() != null) {
                if (!pagamento.getPagatore().getCodiceFiscale().equals(
                    ((IdentitaShibboleth)session.getAttribute("identitaShibboleth")).getCodiceFiscale())) {
                    Messaggi messaggi = new Messaggi();         
                    messaggi.addMessaggio(new Messaggio(LevelMessage.DANGER, "Pagamento non trovato!"));
                    log.debug ("riferimentiPost" ,"Pagamento non trovato (id=" + pagamento.getIdPagamento() + ") per CF Pagatore : " + pagamento.getPagatore().getCodiceFiscale() + " ATTESO " +  ((IdentitaShibboleth)session.getAttribute("identitaShibboleth")).getCodiceFiscale());
                    return redirect("riferimenti", redirectAttributes, messaggi);
                }
            }
        }
        
        if (pagamento != null && pagamento.getTipoPagamento() != null && pagamento.getTipoPagamento().getTipologiaPagamento() != null) {
            if ("MABL".equalsIgnoreCase ( pagamento.getTipoPagamento().getTipologiaPagamento ().getCodice() )
            		|| "PABL".equalsIgnoreCase ( pagamento.getTipoPagamento().getTipologiaPagamento ().getCodice() ) ){
            	//RDI-34
            	Messaggi messaggi = new Messaggi ();
                messaggi.addMessaggio (
                    new Messaggio ( LevelMessage.DANGER, "Pagamento con marca da bollo. Non e' consentito il pagamento dallo Sportello PiemontePay, utilizzare l'apposito servizio" ) );
                log.debugEnd ( methodName );
                return redirect("riferimenti", redirectAttributes, messaggi);
            }
        }

        
        Riferimento riferimento = new Riferimento();
        riferimento.setEnteId(pagamento.getEnte().getIdEnte());
        riferimento.setEnteDesc(pagamento.getEnte().getNome());
        riferimento.setPagamentoId(pagamento.getTipoPagamento().getIdTipoPagamento());
        riferimento.setPagamentoDesc(pagamento.getTipoPagamento().getDescrizionePortale());
        riferimento.setCompilazioneNote(pagamento.getTipoPagamento().getCompilazioneNote());
        riferimento.setDataOperazione(new Date());
        
        //TAG_PPU - 2019 - RDI 004 - RDI 005
        riferimento.setAnnoAccertamento ( pagamento.getAnnoAccertamento () );
        riferimento.setNumeroAccertamento ( pagamento.getNumeroAccertamento () );
        //TAG_PPU - 2019 - RDI 004 - RDI 005

        saveAttributeIntoSession(riferimento, session);
        saveAttributeIntoSession(pagamento, session);

        log.debugEnd(methodName);
        return "redirect:datiPersonali";
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
        @Validated({DatiPersonaliConIuvPrivateGroup.class, Default.class}) @ModelAttribute DatiPersonali datiPersonali, 
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes) {
        String methodName = "datiPersonaliPost";
        log.debugStart(methodName);

        if ("indietro".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:riferimenti";
        }

        mergeModel(datiPersonali, getAttributeFromSession(DatiPersonali.class, session));


        datiPersonaliComplexValidator.validate(datiPersonali, bindingResult, Boolean.FALSE);
        if (bindingResult.hasErrors()) {
            log.debugEnd(methodName);
            return redirect("datiPersonali", redirectAttributes, datiPersonali, bindingResult);
        }

        saveAttributeIntoSession(datiPersonali, session);

        if ("prosegui".equals(action)) {
            Pagamento pagamento = getAttributeFromSession(Pagamento.class, session);
            try {
                Anagrafica pagatore = pagamento.getPagatore();
                pagatore.setEmail(datiPersonali.getEmail());
                pagamentoFacade.aggiornaPagatore(pagamento.getIdPagamento(), pagatore);
                pagamentoFacade.aggiornaNote(pagamento.getIdPagamento(), datiPersonali.getNote());
                pagamentoFacade.aggiornaConsensoPrivacy(pagamento.getIdPagamento(), datiPersonali.getFlagPrivacy());
            } catch (IllegalArgumentException | NoDataException e) {
                throw new RuntimeException("Aggiornamento pagamento fallito (id=" + pagamento.getIdPagamento() + ")" );
            }
            log.debugEnd(methodName);
            return redirect("riepilogo");
        }
        throw new RuntimeException("Azione non gestita per PrivatePagamentoConIuvController.datiPersonaliPost.");
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
                return "redirect:/private/pagaConIuv";
            }
            log.debugEnd(methodName);
            return redirect("datiPersonali");
        }
        if ("prosegui".equals(action)) {
            saveAttributeIntoSession(Wizzard.PAGA_AUTH_CONIUV, session);
            log.debugEnd(methodName);
            return "forward:" + PAYMENTURL;
        } 
        throw new RuntimeException("Azione non gestita per PrivatePagamentoConIuvController.riepilogoPost.");
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
