/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import it.csi.epay.epaypacatalogweb.business.manager.*;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.OpzioneComboVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.OpzioniCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.ppay.TematicaPPayVO;
import it.csi.epay.epaypacatalogweb.model.utente.*;
import it.csi.epay.epaypacatalogweb.model.utente.validators.GestioneUtenteValidator;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping ( value = "/autorizzazione-codici-versamento" )
public class AutorizzazioniCodiciVersamentoController extends AuthenticatedParentController {

    private static final String PAGINA_RICERCA = "/autorizzazione-codici-versamento/ricerca/index";

    private static final String PAGINA_MODIFICA = "/autorizzazione-codici-versamento/modifica/index";

    private static final String URL_RICERCA = "/autorizzazione-codici-versamento/ricerca";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_utenti_acv";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati_acv";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota_acv";

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private UtentiManager utentiManager;

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @Autowired
    private AutorizzazioneCodiceVersamentoManager autorizzazioniCodiciVersamento;

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
        List<OpzioniCodiceVersamentoVO> cv = decodificheManager.getListaOpzioniCodiceVersamento ( false );
        model.addAttribute ( MODEL_CODICI_VERSAMENTO, cv );
        model.addAttribute ( MODEL_CODICI_VERSAMENTO + "_JS", serializeForJavascript ( cv ) );

        List<TematicaPPayVO> tematiche = decodificheManager.getListaOpzioniTematicheVociEntrata ();
        model.addAttribute ( MODEL_TEMATICHE, OpzioneComboVO.getOpzioni ( tematiche ) );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( "/pulisci" )
    public String clearRicerca ( Model model, HttpServletRequest request ) {
        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/elimina/{id}", method = RequestMethod.GET )
    public String elimina ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes ) {

        authorizationManager.require ( UtenteVO.class, id, EntityAction.DELETE );

        try {
            utentiManager.elimina ( id );
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
        } catch (RuntimeException | OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
        } catch (Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
        }

        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "modifica/{id}", method = RequestMethod.GET )
    public String viewModifica (
        Model model,
        @PathVariable ( "id" ) Long id,
        RedirectAttributes redirectAttributes ) {

        UtenteVO utente;
        List<AssociazioneUtenteTematicaVO> tematiche;

        try {

            utente = utentiManager.get ( id );

            tematiche = autorizzazioniCodiciVersamento.getTematichePossibili ();

            autorizzazioniCodiciVersamento.popolaSelezioneTematichePerUtente ( utente, tematiche, null );

        } catch (RuntimeException | OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        } catch (Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, Messages.UNKNOWN_ERROR ) );
            return "redirect:" + URL_RICERCA;
        }

        model.addAttribute ( "selected_user", utente );
        model.addAttribute ( "albero_tematiche", tematiche );

        return PAGINA_MODIFICA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/salva", method = RequestMethod.POST )
    public String doModifica (
        @ModelAttribute ( value = "albero_tematiche" ) ModificaAssociazioniUtenteTematicaVO inputUtente,
        @RequestParam ( "selectedUserId" ) Long selectedUserId,
        RedirectAttributes redirectAttributes ) {

        try {

            autorizzazioniCodiciVersamento.aggiornaSelezioneTematiche ( selectedUserId, inputUtente );

        } catch (RuntimeException | OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        } catch (Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
            return "redirect:" + URL_RICERCA;
        }

        redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );

        return "redirect:" + URL_RICERCA;

    }
}
