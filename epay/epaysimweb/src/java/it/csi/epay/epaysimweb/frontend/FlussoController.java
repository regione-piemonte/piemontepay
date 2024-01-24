/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.csi.epay.epaysimweb.business.manager.EsportazioneManager;
import it.csi.epay.epaysimweb.business.manager.FlussiManager;
import it.csi.epay.epaysimweb.common.Messages;
import it.csi.epay.epaysimweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysimweb.model.flussi.EsportazioneFlussoVO;
import it.csi.epay.epaysimweb.model.flussi.FlussiDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussiSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoOrigineVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoValidator;
import it.csi.epay.epaysimweb.model.flussi.RicercaFlussoFiltroVO;


@Controller
public class FlussoController extends AuthenticatedParentController {

    public final static String URL_BASE = "/flussi";

    public final static String URL_RICERCA = URL_BASE + "/ricerca";

    public final static String URL_PULISCI = URL_BASE + "/pulisci";

    public final static String URL_SINTESI = "/flusso/{idOrigine}/sintesi";

    public final static String URL_DETTAGLI = URL_SINTESI + "/{idSintesi}/dettagli";

    public final static String URL_DETTAGLIO = URL_DETTAGLI + "/{idDettaglio}";

    public final static String URL_ESPORTA_CSV = URL_BASE + "/esporta-csv";

    public final static String URL_ESPORTA_EXCEL = URL_BASE + "/esporta-excel";

    public final static String URL_UPDATE_STATO_FLUSSO_ELABORATO = "/flusso/{idOrigine}/updateFlussiElaborato";

    public final static String URL_UPDATE_STATO_FLUSSO_ERRORE = "/flusso/{idOrigine}/updateFlussiErrore";

    public final static String VIEW_RICERCA = "flusso/ricerca";

    public final static String VIEW_SINTESI_FLUSSO = "flusso/sintesi";

    public final static String VIEW_DETTAGLI_FLUSSO = "flusso/dettagli";

    public final static String VIEW_DETTAGLIO_FLUSSO = "flusso/dettaglio";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_flussi";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

    private static final String MODEL_SINTESI_FLUSSO = "sintesi_flusso";

    private static final String MODEL_SINTESI_FLUSSO_ERRORE = "sintesi_flusso_errore";

    private static final String MODEL_DETTAGLIO_FLUSSO = "dettaglio_flusso";

    private static final String MODEL_DETTAGLIO_FLUSSO_ERRORE = "dettaglio_flusso_errore";

    private static final String MODEL_ID_FLUSSO_ORIGINE = "flusso_origine_id";

    private static final String MODEL_ID_FLUSSO_SINTESI = "flusso_sintesi_id";

    @Autowired
    private FlussiManager flussiManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @InitBinder
    public void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new FlussoValidator () );
        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }

    @RequestMapping ( URL_PULISCI )
    public String clearRicerca ( Model model, HttpServletRequest request ) {

        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @RequestMapping ( value = URL_RICERCA, method = RequestMethod.GET )
    public String viewRicerca ( Model model, HttpServletRequest request ) {

        List<FlussoOrigineVO> list;

        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );

        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaFlussoFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            try {
                list = flussiManager.ricercaFlussi ( filtro );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, list );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( null == list || list.size () < 1 ) );
            } catch ( Exception e ) {
                model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
            }
        }
        return VIEW_RICERCA;
    }

    @Override
    public void loadComboboxes ( Model model ) {
        //nessuna operazione.
    }

    @RequestMapping ( value = URL_RICERCA, method = RequestMethod.POST )
    public String ricerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaFlussoFiltroVO filtro, BindingResult bindingResult,
        Model model, HttpServletRequest request ) {

        if ( bindingResult.hasErrors () ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
            return VIEW_RICERCA;
        } else {
            request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );
        }

        return "redirect:" + URL_RICERCA;
    }

    //@RequestMapping ( value = URL_UPDATE_STATO_FLUSSO_ELABORATO, method = RequestMethod.POST )
    //@ResponseBody
    @RequestMapping ( value = URL_UPDATE_STATO_FLUSSO_ELABORATO, method = RequestMethod.GET )
    public String updateStatoFlussiElaborato ( @PathVariable ( "idOrigine" ) Long idFlussoOrigine, HttpServletResponse response, HttpServletRequest request,
        Model model ) {
        updateStatoFlussi ( idFlussoOrigine, response, Boolean.TRUE );
        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );
        model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
        return "redirect:" + URL_RICERCA;
    }

    //@RequestMapping ( value = URL_UPDATE_STATO_FLUSSO_ERRORE, method = RequestMethod.POST )
    //@ResponseBody
    @RequestMapping ( value = URL_UPDATE_STATO_FLUSSO_ERRORE, method = RequestMethod.GET )
    public String updateStatoFlussiErrore ( @PathVariable ( "idOrigine" ) Long idFlussoOrigine, HttpServletResponse response, HttpServletRequest request,
        Model model ) {
        updateStatoFlussi ( idFlussoOrigine, response, Boolean.FALSE );
        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );
        model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
        return "redirect:" + URL_RICERCA;
    }

    /**
     * metodo che chiama il servizio di aggiornamento dei flussi.
     * 
     * @param ids lista di id flusso
     * @param response oggetto per gestire l'errore di ritorno
     * @param nuovoStatoFlusso nuovo stato del flusso
     * @return String con l'esito dell'operazione.
     */
    //private String updateStatoFlussi ( List<String> ids, HttpServletResponse response, Boolean nuovoStatoFlusso ) {
    private String updateStatoFlussi ( Long ids, HttpServletResponse response, Boolean nuovoStatoFlusso ) {
        if ( null != ids ) {
            UpdateEsitoStatoElaborazioneFlussoInputDTO input = new UpdateEsitoStatoElaborazioneFlussoInputDTO ();
            input.getIdentificativiFlussoOrigineList ().add ( ids.toString () );
            input.setNuovoStato ( nuovoStatoFlusso );
            try {
                flussiManager.updateEsitoStatoElaborazioneFlusso ( input );
            } catch ( OperationFailedException e ) {
                e.printStackTrace ();
                response.setStatus ( HttpStatus.BAD_REQUEST.value () );
                return e.getMessage ();
            } catch ( Exception e ) {
                e.printStackTrace ();
                response.setStatus ( HttpStatus.INTERNAL_SERVER_ERROR.value () );
                return "Errore in fase di invocazione del servizio di aggiornamento.";
            }
        }
        return "OK";
    }

    @RequestMapping ( value = URL_SINTESI, method = RequestMethod.GET )
    public String viewSintesi (
        @PathVariable ( "idOrigine" ) Long idFlussoOrigine,
        Model model,
        HttpServletRequest request ) {

        FlussiSintesiVO data = new FlussiSintesiVO ();
        try {
            FlussoOrigineVO flussoOrigineVO = new FlussoOrigineVO ();
            flussoOrigineVO.setId ( idFlussoOrigine );
            data = flussiManager.ricercaFlussoOriginePagoPa ( flussoOrigineVO );

            model.addAttribute ( MODEL_SINTESI_FLUSSO, data );
        } catch ( Exception e ) {
            model.addAttribute ( MODEL_SINTESI_FLUSSO_ERRORE, e.getMessage () );
        }

        return VIEW_SINTESI_FLUSSO;
    }

    @RequestMapping ( value = URL_DETTAGLI, method = RequestMethod.GET )
    public String viewDettagli (
        @PathVariable ( "idOrigine" ) Long idFlussoOrigine,
        @PathVariable ( "idSintesi" ) Long idFlussoSintesi,
        Model model,
        HttpServletRequest request ) {
        FlussiDettaglioVO data;

        try {
            FlussoSintesiVO flussoSintesiVO = new FlussoSintesiVO ();
            flussoSintesiVO.setId ( idFlussoSintesi );
            data = flussiManager.ricercaFlussoSintesiPagoPa ( flussoSintesiVO );

            model.addAttribute ( MODEL_DETTAGLIO_FLUSSO, data );
            model.addAttribute ( MODEL_ID_FLUSSO_ORIGINE, idFlussoOrigine );
        } catch ( Exception e ) {
            model.addAttribute ( MODEL_DETTAGLIO_FLUSSO_ERRORE, e.getMessage () );
        }

        return VIEW_DETTAGLI_FLUSSO;
    }

    @RequestMapping ( value = URL_DETTAGLIO, method = RequestMethod.GET )
    public String viewDettaglio (
        @PathVariable ( "idOrigine" ) Long idFlussoOrigine,
        @PathVariable ( "idSintesi" ) Long idFlussoSintesi,
        @PathVariable ( "idDettaglio" ) Long idFlussoDettaglio,
        Model model,
        HttpServletRequest request ) {
        FlussoDettaglioVO data;

        try {
            FlussoDettaglioVO flussoDettaglioVO = new FlussoDettaglioVO ();
            flussoDettaglioVO.setId ( idFlussoDettaglio );
            data = flussiManager.ricercaFlussoDettaglioPagoPa ( flussoDettaglioVO );

            model.addAttribute ( MODEL_DETTAGLIO_FLUSSO, data );
            model.addAttribute ( MODEL_ID_FLUSSO_ORIGINE, idFlussoOrigine );
            model.addAttribute ( MODEL_ID_FLUSSO_SINTESI, idFlussoSintesi );
        } catch ( Exception e ) {
            model.addAttribute ( MODEL_DETTAGLIO_FLUSSO_ERRORE, e.getMessage () );
        }

        return VIEW_DETTAGLIO_FLUSSO;
    }

    @RequestMapping ( URL_ESPORTA_EXCEL )
    public void esportaXlsx ( HttpServletResponse response, HttpServletRequest request ) throws IOException {
        List<EsportazioneFlussoVO> flussi;
        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );
        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                flussi = flussiManager.ricercaFlussiDaEsportare ( filtro );
            } catch ( OperationFailedException e ) {
                throw new RuntimeException ( e );
            }
            esportazioneManager.esportaListaFlussiXls ( response, flussi );
        }
    }

    @RequestMapping ( URL_ESPORTA_CSV )
    public void esportaCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {
        List<EsportazioneFlussoVO> flussi;
        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );
        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                flussi = flussiManager.ricercaFlussiDaEsportare ( filtro );
            } catch ( OperationFailedException e ) {
                throw new RuntimeException ( e );
            }
            esportazioneManager.esportaListaFlussiCsv ( response, flussi );
        }
    }

}
