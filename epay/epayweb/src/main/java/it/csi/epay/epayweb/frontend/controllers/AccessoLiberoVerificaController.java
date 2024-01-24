/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
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
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoConIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.RiferimentoComplexValidator;
import it.csi.epay.epayweb.utilities.ReCAPTCHA;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/accessoLibero/verifica")
public class AccessoLiberoVerificaController extends _Controller {

    private final static String VIEWVERIFICA1 = "accesso_libero/verifica1_riferimenti";
    private final static String VIEWVERIFICA2 = "accesso_libero/verifica2_verifica";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private RiferimentoComplexValidator riferimentoComplexValidator;

    @Autowired
    private ReCAPTCHA reCAPTCHA;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(true));
        binder.registerCustomEditor(String.class, "confirmEmail", new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String verifica(HttpSession session) {
        final String methodName = "verifica";
        log.debugStart(methodName);

        removeAttribute(session);

        log.debugEnd(methodName);
        return redirect("riferimenti");
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.GET)
    public String riferimentiGet(HttpSession session, Model model) {
        final String methodName = "riferimentiGet";
        log.debugStart(methodName);

        setAttributeIntoModel(Riferimento.class, model, session);
        model.addAttribute("commonData", initCommonData("riferimenti", 1, Boolean.FALSE, Boolean.TRUE, "Indica lo <strong>IUV</strong> (codice Identificativo Univoco di Versamento) che hai utilizzato per il pagamento e verifica che l'operazione sia andata a buon fine."));

        log.debugEnd(methodName);
        return VIEWVERIFICA1;
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.POST)
    public String riferimentiPost(HttpSession session,
        @Validated({RiferimentoConIuvGroup.class, Default.class}) @ModelAttribute Riferimento riferimento,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes,
        @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {
        final String methodName = "riferimentiPost";
        log.debugStart(methodName);
        log.debug(methodName, "CF o PI: " + riferimento.getCodiceFiscale());
        log.debug(methodName, "IUV: " + riferimento.getIuv());

        if ( StringUtils.isEmpty ( gRecaptchaResponse ) || !reCAPTCHA.verify ( gRecaptchaResponse ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.INFO, "Prima di tutto, verificate di non essere un robot!" ) );
            log.debugEnd ( methodName );
            return redirect ( "riferimenti", redirectAttributes, riferimento, bindingResult, messaggi );
        }

        riferimentoComplexValidator.validate(riferimento, bindingResult);
        if (bindingResult.hasErrors()) {
            log.debugEnd(methodName);
            return redirect("riferimenti", redirectAttributes, riferimento, bindingResult);
        }

        Pagamento pagamento = getAttributeFromSession(Pagamento.class, session);
        riferimento.setEnteId(pagamento.getEnte().getIdEnte());
        riferimento.setEnteDesc(pagamento.getEnte().getNome());
        riferimento.setPagamentoId(pagamento.getTipoPagamento().getIdTipoPagamento());
        riferimento.setPagamentoDesc(pagamento.getTipoPagamento().getDescrizionePortale());
        riferimento.setDataOperazione(pagamento.getDataStatoCorrente());

        //TAG_PPU - 2019 - RDI 004 - RDI 005
        riferimento.setAnnoAccertamento ( pagamento.getAnnoAccertamento () );
        riferimento.setNumeroAccertamento ( pagamento.getNumeroAccertamento () );
        //TAG_PPU - 2019 - RDI 004 - RDI 005

        saveAttributeIntoSession(riferimento, session);
        removeAttributeFromSession(DatiPersonali.class, session);

        if ("prosegui".equals(action)) {
            Messaggi messaggi = new Messaggi();
            switch (StatoPagamento.findById(pagamento.getIdStatoCorrente())) {
            case IN_ATTESA:
                messaggi.addMessaggio(new Messaggio(LevelMessage.INFO, "Il pagamento risulta in attesa della ricevuta."));
                break;
            case FALLITO:
                messaggi.addMessaggio(new Messaggio(LevelMessage.DANGER, "Il pagamento risulta fallito."));
                break;
            case ANNULLATO:
                messaggi.addMessaggio(new Messaggio(LevelMessage.DANGER, "Il pagamento risulta annullato."));
                break;
            case SUCCESSO:
                messaggi.addMessaggio(new Messaggio(LevelMessage.SUCCESS, "Il pagamento risulta essere avvenuto correttamente."));
                break;
            case INVALIDATO:
                messaggi.addMessaggio(new Messaggio(LevelMessage.INFO, "Il pagamento risulta annullanto dall'ente pubblico."));
                break;
            default:
                messaggi.addMessaggio(new Messaggio(LevelMessage.WARNING, "Il pagamento risulta ancora da effettuarsi."));
                break;
            }
            log.debugEnd(methodName);
            return redirect("verifica", redirectAttributes, messaggi);
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoConIuvController.riferimentiPost.");
    }

    @RequestMapping(value = "/verifica", method = RequestMethod.GET)
    public String verificaGet(HttpSession session, Model model) {
        final String methodName = "datiPersonaliGet";
        log.debugStart(methodName);

        Pagamento pagamento = getAttributeFromSession(Pagamento.class, session);
        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        try {
            Rt rt = pagamentoFacade.ricercaRt(pagamento.getIdPagamento(), StatoPagamento.findById(pagamento.getIdStatoCorrente()));
            model.addAttribute("pulsantePdf", (rt.getRicevutaPdf() == null || rt.getRicevutaPdf().length == 0) ? "disabled" : "");
            model.addAttribute ( "pulsanteXml", ( rt.getRtXml () == null || rt.getRtXml ().length == 0 ) ? "disabled" : "" );

        } catch (NoDataException e) {
            model.addAttribute("pulsantePdf", "disabled");
            model.addAttribute ( "pulsanteXml", "disabled" );
        }

        try {

            //cercare quietanza se c'e' abilitare tasto
            boolean quietanza = false;

            EsitiRicevuti esito = pagamentoFacade.ricercaEsitiRicevutiByIUV ( pagamento.getIuv () );
            if (null != esito && esito.getIdQuietanzaEsito () != null )
                quietanza = true;
            model.addAttribute ( "pulsanteReceiptPdf", ( !quietanza ) ? "disabled" : "" );
        } catch ( NoDataException e ) {
            model.addAttribute("pulsanteReceiptPdf", "disabled");
        }


        Rt ricevutaTelematica = null;
        try {
            String iuv = pagamento.getIuv();
            ricevutaTelematica = pagamentoFacade.ricercaRtByIuv(iuv);
            if (ricevutaTelematica!=null) {
                log.info(methodName, " ricevutaTelematica IUV: esito" +  ricevutaTelematica.getCodEsitoPagamento());
                log.info(methodName, " ricevutaTelematica IUV: " + ricevutaTelematica.getIuv());
                log.info(methodName, " ricevutaTelematica RR: "  + ricevutaTelematica.getIdRR());
                //ricevutaTelematica.setDescEsitoPagamento("descrizione");

                saveAttributeIntoSession ( ricevutaTelematica, session );
                model.addAttribute("ricevutaTelematica",ricevutaTelematica);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //

        model.addAttribute("commonData", initCommonData("verifica", 2, Boolean.FALSE, Boolean.FALSE, null));

        log.debugEnd(methodName);
        return VIEWVERIFICA2;
    }
}
