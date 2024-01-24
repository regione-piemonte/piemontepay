/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.UtentiManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.utente.ModificaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.validators.GestioneUtenteValidator;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import it.csi.epay.epaypacatalogweb.security.UserDetails;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;

@Controller
@RequestMapping ( value = "/utenti" )
public class UtenteController extends AuthenticatedParentController {

    private static final String PAGINA_NUOVO = "/utente/nuovo/index";

    private static final String PAGINA_MODIFICA = "/utente/modifica/index";

    private static final String PAGINA_VISUALIZZA = "/utente/visualizza/index";

    private static final String PAGINA_RICERCA = "/utente/ricerca/index";

    private static final String URL_RICERCA = "/utenti/ricerca";

    private static final String MODEL_MODIFICA_UTENTE = "modifica_utente";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_utenti";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

    private static final String MODEL_ID_UTENTE = "id_utente";

    private static final String SCENARIO_NUOVO = "nuovo";

    private static final String SCENARIO_MODIFICA = "modifica";

    @Autowired
    private UtentiManager utentiManager;

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @InitBinder
    protected void initBinder ( WebDataBinder binder ) {
        binder.registerCustomEditor ( String.class, new StringTrimmerEditor ( true ) );

        binder.setValidator ( new GestioneUtenteValidator () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }

    @Override
    public void loadComboboxes ( Model model ) {
        // NOP
    }

    public void loadComboboxesModifica ( Model model ) {
        // NOP
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( "/pulisci" )
    public String clearRicerca ( Model model, HttpServletRequest request ) {
        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( "/ricerca" )
    public String viewRicerca (
        Model model,
        HttpServletRequest request ) {
        List<RisultatoRicercaUtenteVO> list;

        authorizationManager.require ( UtenteVO.class, EntityAction.LIST );

        RicercaUtenteFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaUtenteFiltroVO.class );

        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaUtenteFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            try {
                list = utentiManager.ricerca ( filtro );
                postProcess ( list );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, list );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( list.size () < 1 ) );
            } catch ( OperationFailedException e ) {
                model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
            }
        }

        loadComboboxes ( model );
        return PAGINA_RICERCA;
    }

    private void postProcess ( List<RisultatoRicercaUtenteVO> lista ) {
        UserDetails utenteCorrente = SecurityUtils.getUser ();
        for ( RisultatoRicercaUtenteVO risultato: lista ) {
            if ( risultato.getId ().equals ( utenteCorrente.getUtente ().getId () ) ) {
                risultato.setModificaConsentita ( false );
            } else {
                risultato.setModificaConsentita ( true );
            }

        }

    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
    public String doRicerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaUtenteFiltroVO filtro, BindingResult bindingResult,
        Model model, HttpServletRequest request ) {

        if ( bindingResult.hasErrors () ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );

            loadComboboxes ( model );
            return PAGINA_RICERCA;
        } else {
            request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );
        }

        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.ELIMINA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/elimina/{id}", method = RequestMethod.GET )
    public String elimina ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes ) {

        authorizationManager.require ( UtenteVO.class, id, EntityAction.DELETE );

        try {
            utentiManager.elimina ( id );
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
        } catch ( RuntimeException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
        } catch ( OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
        } catch ( Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
        }

        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( "/esporta-excel" )
    public void esportaXlsx ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        authorizationManager.require ( UtenteVO.class, EntityAction.LIST );

        List<RisultatoRicercaUtenteVO> list;

        RicercaUtenteFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaUtenteFiltroVO.class );

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                list = utentiManager.ricerca ( filtro );
            } catch ( OperationFailedException e ) {
                throw new RuntimeException ( e );
            }
            esportazioneManager.esportaUtentiXlsx ( response, list );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( "/esporta-csv" )
    public void esportaCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        authorizationManager.require ( UtenteVO.class, EntityAction.LIST );

        List<RisultatoRicercaUtenteVO> list;

        RicercaUtenteFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaUtenteFiltroVO.class );

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                list = utentiManager.ricerca ( filtro );
            } catch ( OperationFailedException e ) {
                throw new RuntimeException ( e );
            }
            esportazioneManager.esportaUtentiCsv ( response, list );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/modifica/{id}", method = RequestMethod.GET )
    public String viewModificaUtente ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request ) {

        authorizationManager.require ( UtenteVO.class, id, EntityAction.WRITE );

        try {
            popolaModelModifica ( model, id, null, SCENARIO_MODIFICA );
            return PAGINA_MODIFICA;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/visualizza/{id}", method = RequestMethod.GET )
    public String viewVisualizzaUtente ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request ) {

        authorizationManager.require ( UtenteVO.class, id, EntityAction.WRITE );

        try {
            popolaModelModifica ( model, id, null, SCENARIO_MODIFICA );
            return PAGINA_VISUALIZZA;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/nuovo", method = RequestMethod.GET )
    public String viewNuovoUtente ( Model model, HttpServletRequest request ) {

        try {
            popolaModelModifica ( model, null, null, SCENARIO_NUOVO );
            return PAGINA_NUOVO;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/modifica/{id}", method = RequestMethod.POST )
    public String doModificaUtente (
        @PathVariable ( "id" ) Long id,
        @Valid @ModelAttribute ( MODEL_MODIFICA_UTENTE ) ModificaUtenteVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        authorizationManager.require ( UtenteVO.class, id, EntityAction.WRITE );

        if ( bindingResult.hasErrors () ) {
            popolaModelModifica ( model, id, userInput, SCENARIO_MODIFICA );
            // model.addAllAttributes ( bindingResult.getModel () );
            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_utente";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            return PAGINA_MODIFICA;
        } else {
            try {
                if ( checkUtenteExists ( userInput ) ) {
                    model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.USER_EXISTS ) );
                    popolaModelModifica ( model, id, null, SCENARIO_MODIFICA );
                    return PAGINA_MODIFICA;
                }
                utentiManager.aggiorna ( userInput );
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
            } catch ( RuntimeException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( OperationFailedException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( Exception e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
            }

            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_UTENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/nuovo", method = RequestMethod.POST )
    public String doNuovoUtente (
        @Valid @ModelAttribute ( MODEL_MODIFICA_UTENTE ) ModificaUtenteVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        Long id = null;

        // authorizationManager.require ( UtenteVO.class, id, EntityAction.WRITE );

        if ( bindingResult.hasErrors () ) {
            popolaModelModifica ( model, id, userInput, SCENARIO_NUOVO );
            //model.addAllAttributes ( bindingResult.getModel () );
            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_utente";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            return PAGINA_NUOVO;
        } else {
            try {
                if ( checkUtenteExists ( userInput ) ) {
                    model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.USER_EXISTS ) );
                    return PAGINA_NUOVO;
                }

                utentiManager.inserisci ( userInput );
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );

            } catch ( RuntimeException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( OperationFailedException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( Exception e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
            }

            return "redirect:" + URL_RICERCA;
        }
    }

    private boolean checkUtenteExists ( ModificaUtenteVO userInput ) throws OperationFailedException, ParseException {
        RicercaUtenteFiltroVO utente = new RicercaUtenteFiltroVO ();
        utente.setCodiceFiscale ( userInput.getCodiceFiscale () );
        List<RisultatoRicercaUtenteVO> listUtenti = utentiManager.ricerca ( utente );
        if ( null != listUtenti && listUtenti.size () > 0 ) {
            DateFormat sm = new SimpleDateFormat ( "yyyy/MM/dd" );
            Date currentDate = sm.parse ( sm.format ( new Date () ) );
            for ( RisultatoRicercaUtenteVO utenteExists: listUtenti ) {
                if ( ( null == utenteExists.getDataFineValidita ()
                                || ( null != utenteExists.getDataFineValidita ()
                                && utenteExists.getDataFineValidita ().compareTo ( currentDate ) >= 0 ) )
                                &&
                                StringUtils.equalsIgnoreCase ( utenteExists.getCodiceFiscale (), userInput.getCodiceFiscale () )
                                && !utenteExists.getId ().equals ( userInput.getId () )
                                ) {
                    return true;
                }
            }
        }
        return false;
    }

    private void popolaModelModifica ( Model model, Long id, ModificaUtenteVO modelCorrente, String scenario ) throws OperationFailedException {

        UtenteVO modelMerged;

        if ( id == null ) {
            if ( modelCorrente != null ) {
                modelMerged = utentiManager.merge ( null, modelCorrente );
            } else {
                modelMerged = utentiManager.istanzia ();
            }
        } else {
            if ( modelCorrente == null ) {
                modelMerged = utentiManager.get ( id );
            } else {
                modelMerged = utentiManager.merge ( id, modelCorrente );
            }
        }

        model.addAttribute ( MODEL_MODIFICA_UTENTE, modelMerged );
        model.addAttribute ( MODEL_SCENARIO, scenario );
        model.addAttribute ( MODEL_ID_UTENTE, id );
        loadComboboxesModifica ( model );
    }

}
