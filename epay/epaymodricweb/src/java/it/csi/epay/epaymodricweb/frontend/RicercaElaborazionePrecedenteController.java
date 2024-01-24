/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.csi.epay.epaymodricweb.business.manager.RicercaElaborazionePrecedenteManager;
import it.csi.epay.epaymodricweb.business.manager.RilanciaElaborazioneManager;
import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.Messages;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.elaborazione.ElaborazioneVO;
import it.csi.epay.epaymodricweb.model.elaborazione.RicercaElaborazionePrecedenteFiltroVO;
import it.csi.epay.epaymodricweb.model.elaborazione.RicercaElaborazionePrecedenteValidator;
import it.csi.epay.epaymodricweb.model.flusso.FlussoOrigineVO;


/*
 *
 * NGueye
 *
 *
 */

@Controller
public class RicercaElaborazionePrecedenteController extends AuthenticatedParentController {

    public final static String URL_BASE = "/elaborazione";

    public final static String URL_RICERCA = URL_BASE + "/ricercaElaborazionePrecedente";

    public final static String URL_DETTAGLIO = URL_BASE + "/{idElaborazione}/dettaglio";

    public final static String URL_RILANCIA_ELABORAZIONE = URL_BASE + "/rilanciaElaborazione";

    public final static String URL_PULISCI = URL_BASE + "/pulisci";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_elaborazione_precedente";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_FLUSSI_ASSOCIATI_A_ELABORAZIONE = "flussi_associati_a_elaborazione";

    private static final String MODEL_ELABORAZIONE_SELEZIONATA = "elaborazione_selezionata";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

    private static final String MODEL_RICERCA_ELABORAZIONE_ERRORE = "ricerca_elaborazione_errore";

    private static final String MODEL_OPZIONI_STATI_ELABORAZIONI = "opzioni_stati_elaborazione";

    public final static String VIEW_RICERCA = "elaborazione/ricercaElaborazionePrecedente";

    public final static String VIEW_DETTAGLIO = "elaborazione/dettaglio/index";

    @Autowired
    RilanciaElaborazioneManager rilanciaElaborazioneManager;

    @Autowired
    RicercaElaborazionePrecedenteManager ricercaElaborazionePrecedenteManager;

    @InitBinder
    public void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new RicercaElaborazionePrecedenteValidator () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_ELABORAZIONE + "')" )
    @RequestMapping ( value = URL_RICERCA, method = RequestMethod.GET )
    public String viewRicerca ( Model model, HttpServletRequest request ) throws OperationFailedException {

        List<ElaborazioneVO> list;
        RicercaElaborazionePrecedenteFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaElaborazionePrecedenteFiltroVO.class );
        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaElaborazionePrecedenteFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {

            try {
                model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
                list = ricercaElaborazionePrecedenteManager.ricercaElaborazionePrecedente ( filtro );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, list );
                request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, list );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( list.size () < 1 ) );
            } catch ( Exception e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
            }
        }
        loadComboboxes ( model );
        return VIEW_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_ELABORAZIONE + "')" )
    @RequestMapping ( value = URL_RICERCA, method = RequestMethod.POST )
    public String ricerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaElaborazionePrecedenteFiltroVO filtro,
        BindingResult bindingResult, Model model, HttpServletRequest request ) throws ServletException {

        if ( bindingResult.hasErrors () ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );

            return VIEW_RICERCA;
        } else {
            request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );


            List<ElaborazioneVO> list;
            try {
                list = ricercaElaborazionePrecedenteManager.ricercaElaborazionePrecedente ( filtro );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, list );
                request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, list );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( list.size () < 1 ) );
            } catch ( Exception e ) {
                model.addAttribute ( MODEL_RICERCA_ELABORAZIONE_ERRORE,
                    String.format ( Messages.ERRORE_DURANTE_RICERCA_ELABORAZIONE, e.getCause ().getMessage () ) );

            }
        }
        loadComboboxes ( model );
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_ELABORAZIONE + "')" )
    @RequestMapping ( URL_PULISCI )
    public String clearRicerca ( Model model, HttpServletRequest request ) {

        request.getSession ().removeAttribute ( MODEL_RISULTATI_RICERCA );
        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );

        model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );

        loadComboboxes ( model );

        return "redirect:" + URL_RICERCA;
    }

    //Recupera la lista di id passata da ajax (script-ricerca-elab-precedente.jsp)
    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_ELABORAZIONE + "')" )
    @RequestMapping ( value = URL_RILANCIA_ELABORAZIONE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody String checkIdSelectedList ( @RequestBody Long id )
                    throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        String result = "";
        //Chiama rilanciaElaborazione che al suo turno chiama inserisciElaborazione
        rilanciaElaborazioneManager.rilanciaElaborazione ( id );
        return result;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_ELABORAZIONE + "')" )
    @SuppressWarnings ( "unchecked" )
    @RequestMapping ( value = URL_DETTAGLIO )
    public String viewElaborazione ( @PathVariable ( "idElaborazione" ) Long idElaborazione, Model model, HttpServletRequest request,
        HttpServletResponse response ) {

        if (null != request.getSession ().getAttribute ( MODEL_RISULTATI_RICERCA )) {
            List<ElaborazioneVO> list = (List<ElaborazioneVO>) request.getSession ().getAttribute ( MODEL_RISULTATI_RICERCA );
            ElaborazioneVO elaborazioneSelezionata = null;
            if (list.size () > 0) {
                for (ElaborazioneVO elaborazione : list) {
                    if (elaborazione.getId ().equals ( idElaborazione )) {
                        elaborazioneSelezionata = elaborazione;
                        break;
                    }
                }
                if (null != elaborazioneSelezionata) {
                    model.addAttribute ( MODEL_ELABORAZIONE_SELEZIONATA, elaborazioneSelezionata );
                    try {
                        List<FlussoOrigineVO> flussiOrigine
                        = ricercaElaborazionePrecedenteManager.ricercaFlussiOrigineAssociatiAElaborazione ( elaborazioneSelezionata.getListaFlussi () );
                        model.addAttribute ( MODEL_FLUSSI_ASSOCIATI_A_ELABORAZIONE, flussiOrigine );
                    } catch ( OperationFailedException e ) {
                        return "redirect:" + URL_RICERCA;
                    }
                }
            }
        } else {
            return "redirect:" + URL_RICERCA;
        }

        return VIEW_DETTAGLIO;

    }

    @Override
    public void loadComboboxes ( Model model ) {
        try {
            model.addAttribute ( MODEL_OPZIONI_STATI_ELABORAZIONI, ricercaElaborazionePrecedenteManager.elencaStatiElaborazione () );
        } catch ( OperationFailedException e ) {
            e.printStackTrace ();
        }
    }

}
