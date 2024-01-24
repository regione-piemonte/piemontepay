/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliStampaAvvisoSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.DatiPersonaliComplexValidator;
import it.csi.epay.epayweb.utilities.ReCAPTCHA;
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

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/accessoLibero/stampaAvviso")
public class AccessoLiberoStampaAvvisoController extends _Controller{

    private final static String VIEWNAMEPAGA1 = "accesso_libero/pagamento1_riferimenti_senza_iuv";
    private final static String VIEWNAMEPAGA2 = "accesso_libero/pagamento2_dati_personali";
    private final static String VIEWNAMEPAGA3 = "accesso_libero/pagamento3_riepilogo";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private EnteFacade enteFacade;

    @Autowired
    private DatiPersonaliComplexValidator datiPersonaliComplexValidator;

    @Autowired
    private ReCAPTCHA reCAPTCHA;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(true));
        binder.registerCustomEditor(String.class, "confirmEmail", new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String stampaAvviso(HttpSession session) {
        final String methodName = "stampaAvviso";
        log.debugStart(methodName);

        removeAttribute(session);

        log.debugEnd(methodName);
        return "redirect:riferimenti";
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.GET)
    public String riferimentiGet(HttpSession session, Model model) {
        final String methodName = "riferimentiGet";
        log.debugStart(methodName);

        setAttributeIntoModel(Riferimento.class, model, session);

        model.addAttribute("commonData", initCommonData("riferimenti", 1, Boolean.FALSE, Boolean.TRUE, "Compila i dati richiesti: verr&agrave; creato un \"Avviso di pagamento\" (con relativo IUV) che potrai stampare (formato PDF).<br>Potrai quindi effettuare un pagamento online (con lo IUV) oppure, portando con te la stampa, pagare nei centri autorizzati.", session));

        log.debugEnd(methodName);
        return VIEWNAMEPAGA1;
    }

    @RequestMapping(value = "/riferimenti", method = RequestMethod.POST)
    public String riferimentiPost(HttpSession session,
        @Validated({RiferimentoSenzaIuvGroup.class, Default.class}) @ModelAttribute Riferimento riferimento,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes) {
        final String methodName = "riferimentiPost";
        log.debugStart(methodName);
        log.debug(methodName, "ENTE: " + riferimento.getEnteId());
        log.debug(methodName, "PAGAMENTO: " + riferimento.getPagamentoId());

        //riferimentoComplexValidator.validateConIuv(riferimento, bindingResult);
        if (bindingResult.hasErrors()) {
            log.debugEnd(methodName);
            return redirect("riferimenti", redirectAttributes, riferimento, bindingResult);
        }

        Riferimento riferimentoOld = getAttributeFromSession(Riferimento.class, session);
        if (!riferimento.equalIdEnteAndIdPagamento(riferimentoOld)) {
            removeAttributeFromSession(DatiPersonali.class, session);
            Ente ente = enteFacade.ricerca(riferimento.getEnteId());
            riferimento.setEnteDesc(ente.getNome());
            riferimento.setCodiceFiscaleEnte ( ente.getCodiceFiscale () );
            try {
                TipoPagamento tipoPagamento = pagamentoFacade.ricercaTipoPagamento(riferimento.getPagamentoId());
                if (tipoPagamento.getTipologiaPagamento () != null) {
                    if ("MABL".equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice() )
                    		|| "PABL".equalsIgnoreCase ( tipoPagamento.getTipologiaPagamento ().getCodice() ) ){
                    	//FIXME RDI-34
                    	Messaggi messaggi = new Messaggi ();
                        messaggi.addMessaggio (
                            new Messaggio ( LevelMessage.DANGER, "Pagamento con marca da bollo. Non e' consentito il pagamento dallo Sportello PiemontePay, utilizzare l'apposito servizio" ) );
                        log.debugEnd ( methodName );
                        return redirect("riferimenti", redirectAttributes, riferimento, bindingResult);
                    }
                }
                riferimento.setCodiceVersamento ( tipoPagamento.getCodiceVersamento () );
                riferimento.setCompilazioneNote(tipoPagamento.getCompilazioneNote());
                riferimento.setPagamentoDesc(tipoPagamento.getDescrizionePortale());
            } catch (NoDataException e) {
                throw new RuntimeException("Tipo pagamento non trovato (" + riferimento.getPagamentoId() + ")");
            }
            riferimento.setDataOperazione(new Date());
            saveAttributeIntoSession(riferimento, session);
        }

		/* DEV/CSI_PAG-2418-bis - BEGIN - per testarla in locale modificare costanti in it.csi.epay.epayservices.utilities.Constants */
		String errorOnGetDatiSpecificiRiscossione = getErrorOnGetDatiSpecificiRiscossione ( pagamentoFacade, riferimento );
		if ( errorOnGetDatiSpecificiRiscossione != null ) {
			return redirect ( "riferimenti", redirectAttributes, riferimento, bindingResult, getForErrorOnGetDSR ( errorOnGetDatiSpecificiRiscossione ) );
		}
		/* DEV/CSI_PAG-2418-bis - END */

        if ("prosegui".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:datiPersonali";
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoStampaAvvisoController.riferimentiPost.");
    }

    @RequestMapping(value = "/datiPersonali", method = RequestMethod.GET)
    public String datiPersonaliGet(HttpSession session, Model model, @RequestParam ( required = false ) String donatorinosolidale) {
        final String methodName = "datiPersonaliGet";
        log.debugStart(methodName);
        String initCommonDescrizione = null;
        boolean indietro = true;
        if (null != donatorinosolidale) {
            log.debug ( methodName, "DONAZIONE COTO" );
            Riferimento riferimentoDonazione = new Riferimento ();
//            removeAttributeFromSession ( DatiPersonali.class, session );
//            removeAttributeFromSession ( Riferimento.class, session );
            removeAttribute(session);

            Ente ente;
            try {
                ente = enteFacade.ricerca ( COVID19_TO_CF );
            } catch ( Exception e ) {
                throw new RuntimeException ( "Ente non trovato (" + COVID19_TO_CF + ")" );
            }
            if ( null != ente ) {
                riferimentoDonazione.setEnteId ( ente.getIdEnte () );
                riferimentoDonazione.setEnteDesc ( ente.getNome () );
                
                try {
                    List<TipoPagamentoLight> listTipoPagamento = pagamentoFacade.elencoTipoPagamentoPerEnte ( ente );
                    boolean tpPagfound = false;
                    for ( TipoPagamentoLight tipoPagamento: listTipoPagamento ) {
                        if ( StringUtils.equals ( COVID19_TO_COD_VERSAMENTO, tipoPagamento.getCodiceVersamento () ) ) {
                            tpPagfound = true;
                            riferimentoDonazione.setPagamentoId(tipoPagamento.getIdTipoPagamento());
                            riferimentoDonazione.setCompilazioneNote ( tipoPagamento.getCompilazioneNote () );
                            riferimentoDonazione.setPagamentoDesc ( tipoPagamento.getDescrizionePortale () );
                            riferimentoDonazione.setDataOperazione ( new Date () );
                            riferimentoDonazione.setCodiceFiscaleEnte ( COVID19_TO_CF );
                            riferimentoDonazione.setCodiceVersamento ( COVID19_TO_COD_VERSAMENTO );
                            saveAttributeIntoSession ( riferimentoDonazione, session );
                            break;
                        }
                    }

                    if ( !tpPagfound ) {
                        throw new RuntimeException (
                            "Tipo pagamento::Codice Versamento (" + COVID19_TO_COD_VERSAMENTO + ") non trovato per Ente (" + ente.getNome () + ")" );
                    }
                    initCommonDescrizione = "Donazione";
                    indietro = false;
                } catch ( IllegalArgumentException e ) {
                    throw new RuntimeException ( "Nessun Tipo pagamento trovato per Ente (" + ente.getNome () + ")" );
                }
            } else {
                throw new RuntimeException ( "Ente non trovato (" + COVID19_TO_CF + ")" );
            }
            model.addAttribute ( "commonData", initCommonData ( "datiPersonali", 2, indietro, Boolean.TRUE, initCommonDescrizione ) );
        } else {
          model.addAttribute("commonData", initCommonData("datiPersonali", 2, Boolean.TRUE, Boolean.TRUE, null, session));
        }
        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        log.debugEnd(methodName);
        return VIEWNAMEPAGA2;
    }

    @RequestMapping(value = "/datiPersonali", method = RequestMethod.POST)
    public String datiPersonaliPost(HttpSession session,
        @Validated({DatiPersonaliStampaAvvisoSenzaIuvGroup.class, Default.class}) @ModelAttribute DatiPersonali datiPersonali,
//        @Validated({DatiPersonaliSenzaIuvGroup.class, Default.class}) @ModelAttribute DatiPersonali datiPersonali,
        BindingResult bindingResult, @RequestParam String action, RedirectAttributes redirectAttributes,
        @RequestParam ( value = "g-recaptcha-response", required = false ) String gRecaptchaResponse ) {
        final String methodName = "datiPersonaliPost";
        log.debugStart(methodName);
        
//        if(datiPersonali.getCodiceFiscale().equalsIgnoreCase("AAAAAA00A00A000I")) {
//        	String codiceFiscale = calcolaCodiceFiscaleStraniero();
//        	if(StringUtils.isBlank(codiceFiscale)) {
//        		Messaggi messaggi = new Messaggi();
//                messaggi.addMessaggio(new Messaggio(LevelMessage.INFO, "Problemi con il codiceFiscale!"));
//                log.debugEnd(methodName);
//                return redirect("datiPersonali", redirectAttributes, datiPersonali, bindingResult, messaggi);
//         
//        	} else {
//            	datiPersonali.setCodiceFiscale(codiceFiscale);
//        	}
//        }

        if ("indietro".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:riferimenti";
        }

        mergeModel(datiPersonali, getAttributeFromSession(DatiPersonali.class, session));

        if ( !reCAPTCHA.verify ( gRecaptchaResponse ) ) {
            Messaggi messaggi = new Messaggi();
            messaggi.addMessaggio(new Messaggio(LevelMessage.INFO, "Prima di tutto, verificate di non essere un robot!"));
            log.debugEnd(methodName);
            return redirect("datiPersonali", redirectAttributes, datiPersonali, bindingResult, messaggi);
        }

        datiPersonaliComplexValidator.validate(datiPersonali, bindingResult, Boolean.TRUE);
        if (bindingResult.hasErrors()) {
            log.debugEnd(methodName);
            return redirect("datiPersonali", redirectAttributes, datiPersonali, bindingResult);
        }

        saveAttributeIntoSession(datiPersonali, session);

        if ("prosegui".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:riepilogo";
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoStampaAvvisoController.datiPersonaliPost.");
    }

    @RequestMapping(value = "/riepilogo", method = RequestMethod.GET)
    public String riepilogoGet(HttpSession session, Model model) {
        final String methodName = "riepilogoGet";
        log.debugStart(methodName);

        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        model.addAttribute("commonData", initCommonData("riepilogo", 3, Boolean.TRUE, Boolean.FALSE, null, session));

        log.debugEnd(methodName);
        return VIEWNAMEPAGA3;
    }

    @RequestMapping(value = "/riepilogo", method = RequestMethod.POST)
    public String riepilogoPost(HttpSession session, @RequestParam String action) {
        final String methodName = "riepilogoPost";
        log.debugStart(methodName);

        if ("indietro".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:datiPersonali";
        }

        throw new RuntimeException("Azione non gestita per AccessoLiberoStampaAvvisoController.riepilogoPost.");
    }

    @RequestMapping(value = "/salvaestampa")
    public String salvaestampa(HttpSession session) {
        final String methodName = "salvaestampa";
        log.debugStart(methodName);

        log.debugEnd(methodName);
        return "forward:/payment/salvaPagamento?stampa=true";
    }
}
