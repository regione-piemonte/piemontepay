/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

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
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.validatorModel.DatiPersonaliComplexValidator;
import it.csi.epay.epayweb.model.enumeration.Wizzard;
import it.csi.epay.epayweb.utilities.ReCAPTCHA;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/accessoLibero/pagaSenzaIuv")
public class AccessoLiberoPagamentoSpontaneoController extends _Controller{

    private final static String VIEWNAMEPAGA1 = "accesso_libero/pagamento1_riferimenti_senza_iuv";
    private final static String VIEWNAMEPAGA2 = "accesso_libero/pagamento2_dati_personali";
    private final static String VIEWNAMEPAGA3 = "accesso_libero/pagamento3_riepilogo";
    private final static String VIEWNAMEPAGA5 = "accesso_libero/pagamento5_conclusione";
    private final static String PAYMENTURL = "/payment/start";
    private final static String ANNULLA_OP = "annullaOp";

    private final static String COVID19_REGIONE_PIEMONTE_CF = "80087670016";
    private final static String COVID19_COD_VERSAMENTO = "PN00";

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
    public String pagaSenzaIuv(HttpSession session) {
        final String methodName = "pagaSenzaIuv";
        log.debugStart(methodName);

        removeAttribute(session);

        log.debugEnd(methodName);
        return "redirect:riferimenti";
    }

    @RequestMapping ( value = "/riferimenti", method = RequestMethod.GET )
    public String riferimentiGet ( HttpSession session, Model model ) {
        final String methodName = "riferimentiGet";
        log.debugStart ( methodName );

        setAttributeIntoModel ( Riferimento.class, model, session );

        model.addAttribute("commonData", initCommonData("riferimenti", 1, Boolean.FALSE, Boolean.TRUE, "Non disponi di uno IUV (Identificativo Univoco Versamento), ma devi fare un pagamento verso la Pubblica Amministrazione? Puoi farlo indicando i dati richiesti in questa pagina e, a seguire, i tuoi dati personali."));

        log.debugEnd(methodName);
        return VIEWNAMEPAGA1;
    }

    @RequestMapping(value = "/riferimenti/tasse", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TipoPagamentoLight>  riferimentiTasse(@RequestParam Long idEnte) {
        final String methodName = "riferimentiTasse";
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
                riferimento.setCodiceVersamento ( tipoPagamento.getCodiceVersamento () );
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
                riferimento.setCompilazioneNote(tipoPagamento.getCompilazioneNote());
                riferimento.setPagamentoDesc(tipoPagamento.getDescrizionePortale());
            } catch (NoDataException e) {
                throw new RuntimeException("Tipo pagamento non trovato (" + riferimento.getPagamentoId() + ")");
            }
            riferimento.setDataOperazione(new Date());
            saveAttributeIntoSession(riferimento, session);
        }

        /* DEV/CSI_PAG-2418 - BEGIN */
        // con i dati del codice fiscale e del codice veramento, verifica se esistono i dati specifici riscossione,
        // se no avvisa subito l'utente che non si puo procedere con il pagamento, e questo PRIMA dell'inserimento
        // dei dati anagrafici/importo (step 2 del processo). Il controllo viene fatto nuovamente anche allo step 4
        // del processo (sul Prosegui del Riepilogo) dato che nel frattempo potrebbero essere cambiate le condizioni.
        //
		String errorOnGetDatiSpecificiRiscossione = getErrorOnGetDatiSpecificiRiscossione ( pagamentoFacade, riferimento );
		if ( errorOnGetDatiSpecificiRiscossione != null ) {
			return redirect ( "riferimenti", redirectAttributes, riferimento, bindingResult, getForErrorOnGetDSR ( errorOnGetDatiSpecificiRiscossione ) );
		}
        /* DEV/CSI_PAG-2418 - END */

        if ("prosegui".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:datiPersonali";
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoSpontaneoController.riferimentiPost.");
    }

    //    private String calcolaCodiceFiscale() {
    //        final String methodName = "calcolaCodiceFiscale";
    //
    //        String codiceFiscaleTemp = "AAAAAA00A00A000I";
    //        String codiceFiscale = "";
    //
    //    	AnagraficaTemp anagrafica;
    //		try {
    //			anagrafica = anagraficaFacade.getAnagrafica();
    //
    //			if (anagrafica != null) {
    //
    //				Pattern pattern = Pattern.compile("([a-zA-Z]{6})([0-9]{2})([a-zA-Z]{1})([0-9]{2})([a-zA-Z]{1})([0-9]{3})([a-zA-Z]{1})");
    //				Matcher matcher = pattern.matcher(anagrafica.getCodiceFiscale());
    //
    //			    if (matcher.matches()) {
    //			    	String anno = matcher.group(2);
    //			    	String giorno =matcher.group(4);
    //			    	String codice = matcher.group(6);
    //					if(!StringUtils.isNumeric(matcher.group(6))) {
    //						codice = "000";
    //	        		} else {
    //	        			if(Integer.valueOf(matcher.group(6))<999) {
    //	        				codice = new DecimalFormat("000").format(Integer.valueOf(matcher.group(6))+1);
    //		        		} else {
    //		        			codice = "000";
    //
    //		        			if(!StringUtils.isNumeric(matcher.group(4))) {
    //		        				giorno = "00";
    //		            		} else {
    //		            			if(Integer.valueOf(matcher.group(4))<99) {
    //		            				giorno = new DecimalFormat("00").format(Integer.valueOf(matcher.group(4))+1);
    //		    	        		} else {
    //		    	        			giorno = "00";
    //
    //		    	        			if(!StringUtils.isNumeric(matcher.group(2))) {
    //		    	        				anno = "00";
    //		    	            		} else {
    //		    	            			if(Integer.valueOf(matcher.group(2))<99) {
    //		    	            				anno = new DecimalFormat("00").format(Integer.valueOf(matcher.group(2))+1);
    //		    	    	        		} else {
    //		    	    	        			anno = "00";
    //		    	    	        		}
    //		    	            		}
    //		    	        		}
    //		            		}
    //		        		}
    //	        		}
    //					codiceFiscale =  matcher.group(1) + anno + matcher.group(3) + giorno
    //							+ matcher.group(5) + codice + matcher.group(7) ;
    //			    } else {
    //			    	codiceFiscale = codiceFiscaleTemp;
    //        		}
    //
    //			    anagrafica.setCodiceFiscale(codiceFiscale);
    //			    anagrafica.setIdAnagrafica(anagrafica.getIdAnagrafica());
    //				anagraficaFacade.inserisciAnagrafica(anagrafica);
    //        	}
    //			else {
    //				codiceFiscale = codiceFiscaleTemp;
    //
    //			    anagrafica = new AnagraficaTemp();
    //			    anagrafica.setCodiceFiscale(codiceFiscale);
    //			    anagrafica.setIdAnagrafica(1L);
    //				anagraficaFacade.inserisciAnagrafica(anagrafica);
    //			}
    //
    //		} catch (IllegalArgumentException | NoDataException e) {
    //			log.error(methodName, e.getMessage());
    //			return null;
    //		}
    //
    //		return codiceFiscale;
    //    }

    @RequestMapping(value = "/datiPersonali", method = RequestMethod.GET)
    public String datiPersonaliGet ( HttpSession session, Model model, @RequestParam ( required = false ) String donazione, @RequestParam ( required = false ) String donatorinosolidale ) {
        final String methodName = "datiPersonaliGet";
        log.debugStart(methodName);
        String initCommonDescrizione = null;
        boolean indietro = true;

        if ( null != donazione) {
            log.debug ( methodName, "DONAZIONE COVID19" );
            Riferimento riferimentoDonazione = new Riferimento ();
            //            removeAttributeFromSession ( DatiPersonali.class, session );
            //            removeAttributeFromSession ( Riferimento.class, session );
            removeAttribute(session);

            Ente ente;
            try {
                ente = enteFacade.ricerca ( COVID19_REGIONE_PIEMONTE_CF );
            } catch ( Exception e ) {
                throw new RuntimeException ( "Ente non trovato (" + COVID19_REGIONE_PIEMONTE_CF + ")" );
            }
            if ( null != ente ) {
                riferimentoDonazione.setEnteId ( ente.getIdEnte () );
                riferimentoDonazione.setEnteDesc ( ente.getNome () );

                try {
                    List<TipoPagamentoLight> listTipoPagamento = pagamentoFacade.elencoTipoPagamentoPerEnte ( ente );
                    boolean tpPagfound = false;
                    for ( TipoPagamentoLight tipoPagamento: listTipoPagamento ) {
                        if ( StringUtils.equals ( COVID19_COD_VERSAMENTO, tipoPagamento.getCodiceVersamento () ) ) {
                            tpPagfound = true;
                            riferimentoDonazione.setPagamentoId(tipoPagamento.getIdTipoPagamento());
                            riferimentoDonazione.setCompilazioneNote ( tipoPagamento.getCompilazioneNote () );
                            riferimentoDonazione.setPagamentoDesc ( tipoPagamento.getDescrizionePortale () );
                            riferimentoDonazione.setDataOperazione ( new Date () );
                            riferimentoDonazione.setCodiceFiscaleEnte ( COVID19_REGIONE_PIEMONTE_CF );
                            riferimentoDonazione.setCodiceVersamento ( COVID19_COD_VERSAMENTO );
                            saveAttributeIntoSession ( riferimentoDonazione, session );
                            break;
                        }
                    }

                    if ( !tpPagfound ) {
                        throw new RuntimeException (
                            "Tipo pagamento::Codice Versamento (" + COVID19_COD_VERSAMENTO + ") non trovato per Ente (" + ente.getNome () + ")" );
                    }
                    initCommonDescrizione = "Donazione";
                    indietro = false;
                } catch ( IllegalArgumentException e ) {
                    throw new RuntimeException ( "Nessun Tipo pagamento trovato per Ente (" + ente.getNome () + ")" );
                }
            } else {
                throw new RuntimeException ( "Ente non trovato (" + COVID19_REGIONE_PIEMONTE_CF + ")" );
            }

        } else if (null != donatorinosolidale) {
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
        }

        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);
        DatiPersonali datiPersonali = (DatiPersonali) model.asMap().get("datiPersonali");
        if (null!= datiPersonali && datiPersonali.isDonazione())
        {
            initCommonDescrizione = "Donazione";
            indietro = false;
        }


        model.addAttribute ( "commonData", initCommonData ( "datiPersonali", 2, indietro, Boolean.TRUE, initCommonDescrizione ) );

        log.debugEnd(methodName);
        return VIEWNAMEPAGA2;
    }

    @RequestMapping(value = "/datiPersonali", method = RequestMethod.POST)
    public String datiPersonaliPost(HttpSession session,
        @Validated({DatiPersonaliSenzaIuvGroup.class, Default.class}) @ModelAttribute DatiPersonali datiPersonali,
        BindingResult bindingResult, @RequestParam String action, @RequestParam String importo, RedirectAttributes redirectAttributes,
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
            removeAttribute(session);
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

        //CSI_PAG-1826 INIZIO
        //BigDecimal importoDaRequest = new BigDecimal ( importo );
        if ( importo.contains ( "." ) ) {
            Messaggi messaggi = new Messaggi ();
            messaggi.addMessaggio ( new Messaggio ( LevelMessage.INFO,
                            "ATTENZIONE: verifica che l'importo risultante sia effettivamente quello che tu hai scelto." ) );
            log.debugEnd ( methodName );
            session.setAttribute ( ANNULLA_OP, false );
            saveAttributeIntoSession ( datiPersonali, session );
            return redirect ( "riepilogo", redirectAttributes, datiPersonali, bindingResult, messaggi );
        }
        //CSI_PAG-1826 FINE
        saveAttributeIntoSession(datiPersonali, session);

        if ("prosegui".equals(action)) {
            log.debugEnd(methodName);
            return "redirect:riepilogo";
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoSpontaneoController.datiPersonaliPost.");
    }

    @RequestMapping(value = "/riepilogo", method = RequestMethod.GET)
    public String riepilogoGet(HttpSession session, Model model) {
        final String methodName = "riepilogoGet";
        log.debugStart(methodName);

        setAttributeIntoModel(Riferimento.class, model, session);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        Messaggi messaggi =  (Messaggi) model.asMap().get("messaggi");
        Boolean prosegui = messaggi == null || messaggi.isEmpty()  ||  !(messaggi.hasWarning() || messaggi.hasDanger());
        if ( null != messaggi && messaggi.hasDanger () ) {
            session.setAttribute ( ANNULLA_OP, true );
        }
        String descrizione= null;
        DatiPersonali datiPersonali = (DatiPersonali) model.asMap().get("datiPersonali");
        if (null!= datiPersonali && datiPersonali.isDonazione())
        {
            descrizione = "Donazione";
        }
        model.addAttribute("commonData", initCommonData("riepilogo", 3, Boolean.TRUE, prosegui, descrizione));

        log.debugEnd(methodName);
        return VIEWNAMEPAGA3;
    }

    @RequestMapping(value = "/riepilogo", method = RequestMethod.POST)
    public String riepilogoPost(HttpSession session, @RequestParam String action) {
        final String methodName = "riepilogoPost";
        log.debugStart(methodName);

        if ("indietro".equals(action)) {
            if ( session.getAttribute ( ANNULLA_OP ) != null ? (Boolean) session.getAttribute ( ANNULLA_OP ) : false ) {
                log.debugEnd ( methodName );
                return "redirect:/accessoLibero/pagaSenzaIuv";
            }
            log.debugEnd(methodName);
            return "redirect:datiPersonali";
        }
        if ("prosegui".equals(action)) {
            saveAttributeIntoSession(Wizzard.PAGA_FREE_NOIUV, session);
            log.debugEnd(methodName);
            return "forward:" + PAYMENTURL;
        }
        throw new RuntimeException("Azione non gestita per AccessoLiberoPagamentoSpontaneoController.riepilogoPost.");
    }

    @RequestMapping(value = "/conclusione", method = RequestMethod.GET)
    public String conclusione(HttpSession session, Model model) {
        final String methodName = "conclusione";
        log.debugStart(methodName);

        setAttributeIntoModel(Riferimento.class, model, session);
        Riferimento riferimento = getAttributeFromSession(Riferimento.class, session);
        Pagamento pagamento = getAttributeFromSession(Pagamento.class, session);
        if ( null != pagamento && StringUtils.isNotBlank ( pagamento.getIuv () ) ) {
            riferimento.setIuv ( pagamento.getIuv () );
        }
        model.addAttribute(riferimento);
        setAttributeIntoModel(DatiPersonali.class, model, session);

        model.addAttribute("commonData", initCommonData(null, 5, Boolean.FALSE, Boolean.FALSE, null));

        log.debugEnd(methodName);
        return VIEWNAMEPAGA5;
    }
}
