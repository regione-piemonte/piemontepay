/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.AutorizzazioneFunzioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.UtentiManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.OpzioneComboVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteCduCategoriaCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteCduCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioniAssociazioneUtenteCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.validators.GestioneUtenteValidator;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import it.csi.epay.epaypacatalogweb.vo.CategoriaFunzioneVO;
import it.csi.epay.epaypacatalogweb.vo.FunzioneVO;


@Controller
@RequestMapping ( value = "/autorizzazione-funzioni" )
public class AutorizzazioneFunzioneController extends AuthenticatedParentController {

    private static final String PAGINA_RICERCA = "/autorizzazione-funzione/ricerca/index";

    private static final String PAGINA_MODIFICA = "/autorizzazione-funzione/modifica/index";

    private static final String URL_RICERCA = "/autorizzazione-funzioni/ricerca";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_utenti_af";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati_af";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota_af";

    private static final String MODEL_OPZIONI_CATEGORIE_CDU = "lista_opzioni_categoria_cdu";

    private static final String MODEL_OPZIONI_CDU = "lista_opzioni_cdu";

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private UtentiManager utentiManager;

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @Autowired
    private AutorizzazioneFunzioneManager autorizzazioneFunzioneManager;

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
        OpzioniAssociazioneUtenteCduVO alberoOpzioniCdu = decodificheManager.getListaOpzioniAssociazioneUtenteCdu ();
        List<OpzioneComboVO> opzioniCategoriaCdu = new ArrayList<> ();
        List<OpzioneComboVO> opzioniCdu = new ArrayList<> ();

        for ( OpzioneAssociazioneUtenteCduCategoriaCduVO categoria: alberoOpzioniCdu.getCategorie () ) {
            opzioniCategoriaCdu.add ( new OpzioneComboVO ( categoria.getId (), categoria.getCodice (), categoria.getDescrizione () ) );
            if ( categoria.getCdu () != null ) {
                for ( OpzioneAssociazioneUtenteCduCduVO cdu: categoria.getCdu () ) {
                    opzioniCdu.add ( new OpzioneComboVO ( cdu.getId (), cdu.getCodice (),
                        categoria.getDescrizione () + " - " + cdu.getDescrizione () ) );
                }
            }
        }

        model.addAttribute ( MODEL_OPZIONI_CATEGORIE_CDU, opzioniCategoriaCdu );
        model.addAttribute ( MODEL_OPZIONI_CDU, opzioniCdu );

        model.addAttribute ( MODEL_OPZIONI_CATEGORIE_CDU + "_js", serializeForJavascript ( opzioniCategoriaCdu ) );
        model.addAttribute ( MODEL_OPZIONI_CDU + "_js", serializeForJavascript ( opzioniCdu ) );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( "/pulisci" )
    public String clearRicerca ( Model model, HttpServletRequest request ) {
        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "modifica/{id}", method = RequestMethod.GET )
    public String viewModifica ( Locale locale, Model model, @PathVariable ( "id" ) Long id,
        RedirectAttributes redirectAttributes ) {

        UtenteVO user;
        try {
            user = utentiManager.get ( id );
        } catch ( RuntimeException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        } catch ( OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        } catch ( Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, Messages.UNKNOWN_ERROR ) );
            return "redirect:" + URL_RICERCA;
        }

        List<FunzioneVO> functions = autorizzazioneFunzioneManager.getCduPossibili ();
        Set<CategoriaFunzioneVO> categorie = new LinkedHashSet<> ();
        for ( FunzioneVO funzione: functions ) {
            categorie.add ( new CategoriaFunzioneVO ( funzione.getCodiceCategoria (), funzione.getDescrizioneCategoria () ) );
        }

        model.addAttribute ( "selectedUser", user );
        model.addAttribute ( "categorie", categorie );
        model.addAttribute ( "unactiveFunctionList", autorizzazioneFunzioneManager.getCduNonAssegnati ( user, functions ) );
        model.addAttribute ( "activeFunctionsList", autorizzazioneFunzioneManager.getCduAssegnati ( user, functions ) );

        return PAGINA_MODIFICA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.AUTORIZZA_FUNZIONE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/salva", method = RequestMethod.POST )
    public String doModifica (
        @RequestParam ( value = "active", required = false ) String [] activeFunIds,
        @RequestParam ( "selectedUserId" ) Long selectedUserId,
        RedirectAttributes redirectAttributes ) {

        try {
            autorizzazioneFunzioneManager.salvaAssociazioneCdu ( selectedUserId, activeFunIds );
        } catch ( RuntimeException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        } catch ( OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        } catch ( Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_LOADING_PAGE, Messages.UNKNOWN_ERROR ) );
            return "redirect:" + URL_RICERCA;
        }

        redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
        return "redirect:" + URL_RICERCA;

    }
}
