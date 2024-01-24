/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epaymodricweb.business.manager.EsportazioneManager;
import it.csi.epay.epaymodricweb.business.manager.ProvvisorioManager;
import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.Messages;
import it.csi.epay.epaymodricweb.common.exceptions.FileUploadException;
import it.csi.epay.epaymodricweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsCancellaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoProvvisorio;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.provvisorio.DTOProvvisorioBuilder;
import it.csi.epay.epaymodricweb.model.provvisorio.FileVO;
import it.csi.epay.epaymodricweb.model.provvisorio.ProvvisorioVO;
import it.csi.epay.epaymodricweb.model.provvisorio.ProvvisorioVOBuilder;
import it.csi.epay.epaymodricweb.model.provvisorio.ProvvisorioVOValidator;
import it.csi.epay.epaymodricweb.model.provvisorio.RicercaProvvisorioFiltroVO;
import it.csi.epay.epaymodricweb.util.CostantiErrori;
import it.csi.epay.epaymodricweb.util.DateUtils;


@Controller
public class ProvvisorioController extends AuthenticatedParentController {

    public final static String URL_BASE = "/provvisori";

    public final static String URL_RICERCA = URL_BASE + "/ricerca";

    public final static String URL_PULISCI = URL_BASE + "/pulisci";

    public final static String URL_ESPORTA_MODELLO_TEMPLATE_CSV = URL_BASE + "/esportamodellocsv";

    public final static String URL_ESPORTA_MODELLO_TEMPLATE_XLS = URL_BASE + "/esportamodelloxls";

    public final static String URL_ESPORTA_CSV = URL_BASE + "/esportacsv";

    public final static String URL_ESPORTA_XLS = URL_BASE + "/esportaxls";

    public final static String URL_IMPORTA_CSV = URL_BASE + "/importacsv";

    public final static String URL_IMPORTA_XLS = URL_BASE + "/importaxls";

    public final static String URL_VISUALIZZA_PROVVISORIO = URL_BASE + "/{idProvvisorio}/visualizza";

    public final static String URL_MODIFICA_PROVVISORIO = URL_BASE + "/{idProvvisorio}/modifica";

    public final static String URL_CANCELLA_PROVVISORIO = URL_BASE + "/{idProvvisorio}/cancella";

    public final static String URL_INSERISCI_PROVVISORIO = URL_BASE + "/inserisci";

    public final static String URL_SALVA_PROVVISORIO = URL_BASE + "/salva";

    public final static String URL_SALVA_FILE_PROVVISORI = URL_BASE + "/upload";

    public final static String VIEW_RICERCA = "provvisorio/ricerca";

    public final static String VIEW_DETTAGLIO_PROVVISORIO = "provvisorio/dettaglio";

    public final static String VIEW_UPLOAD_PROVVISORIO = "provvisorio/upload";

    private static final String MODEL_FILE_PROVVISORI = "provvisori_file";

    private static final String MODEL_PROVVISORIO_ERRORE = "provvisorio_errore";

    private static final String MODEL_PROVVISORIO = "dettaglio_provvisorio";

    private static final String MODEL_DISABLE_INPUT_PROVVISORIO = "disable_input";//true, false

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_provvisori";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

    private static final String MODEL_UPLOAD_TIPO_FILE = "upload_tipo_file";

    @Autowired
    private EsportazioneManager esportazioneManager;

    @Autowired
    private ProvvisorioManager provvisorioManager;

    @InitBinder
    public void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new ProvvisorioVOValidator () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }

    public void loadComboboxesFromService ( Model model ) throws OperationFailedException {
        //nulla da fare
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( URL_PULISCI )
    public String clearRicerca ( Model model, HttpServletRequest request ) {

        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( URL_ESPORTA_MODELLO_TEMPLATE_XLS )
    public void esportaModelloXls ( HttpServletResponse response, HttpServletRequest request ) throws IOException {
        esportazioneManager.esportaModelloTemplateProvvisoriXsl ( response );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( URL_ESPORTA_MODELLO_TEMPLATE_CSV )
    public void esportaModelloCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {
        esportazioneManager.esportaModelloTemplateProvvisoriCsv ( response );
    }

    //Import export provvisori

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_ESPORTA_CSV )
    public void esportaTabellaProvvisoriCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        DtoOutputWsProvvisori items = getProvvisoriDaEsportare ( request );

        if ( items != null && items.getProvvisori ().size () > 0 ) {
            esportazioneManager.esportaTemplateProvvisoriCsv ( response, items );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_ESPORTA_XLS )
    public void esportaTabellaProvvisoriXls ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        DtoOutputWsProvvisori items = getProvvisoriDaEsportare ( request );

        if ( items != null && items.getProvvisori ().size () > 0 ) {
            esportazioneManager.esportaTemplateProvvisoriXls ( response, items );
        }
    }

    private DtoOutputWsProvvisori getProvvisoriDaEsportare ( HttpServletRequest request ) {
        RicercaProvvisorioFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaProvvisorioFiltroVO.class );

        DtoOutputWsProvvisori flussiDaEsportare = new DtoOutputWsProvvisori ();

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                GregorianCalendar from = null;
                GregorianCalendar to = null;
                XMLGregorianCalendar fromX = null;
                XMLGregorianCalendar toX = null;

                if ( filtro.getDataElaborazioneDa () != null ) {
                    from = new GregorianCalendar ();
                    from.setTime ( filtro.getDataElaborazioneDa () );
                    fromX = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( from );
                }
                if ( filtro.getDataElaborazioneA () != null ) {
                    to = new GregorianCalendar ();
                    to.setTime ( filtro.getDataElaborazioneA () );
                    toX = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( to );
                }

                DtoInputWsRicercaProvvisori filtroRicerca = new DtoInputWsRicercaProvvisori ();
                filtroRicerca.setDataA ( fromX );
                filtroRicerca.setDataDa ( toX );
                filtroRicerca.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );
                filtroRicerca.setIdStatoFlusso ( filtro.getStatoFlusso () );

                flussiDaEsportare = provvisorioManager.ricercaProvvisori ( filtroRicerca );
            } catch ( Exception e ) {
                throw new RuntimeException ( e );
            }
        }

        return flussiDaEsportare;
    }

    // Fine esportazione tabella provvisori
    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_IMPORTA_CSV, method = RequestMethod.GET )
    public String importaTabellaProvvisoriCsv ( Model model,
        HttpServletRequest request ) throws IOException {

        model.addAttribute ( MODEL_FILE_PROVVISORI, new FileVO ( Constants.ESTENSIONE_FILE_CSV ) );
        model.addAttribute ( MODEL_UPLOAD_TIPO_FILE, Constants.ESTENSIONE_FILE_CSV );
        return VIEW_UPLOAD_PROVVISORIO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_IMPORTA_XLS, method = RequestMethod.GET )
    public String importaTabellaProvvisoriXls ( Model model,
        HttpServletRequest request ) throws IOException {

        model.addAttribute ( MODEL_FILE_PROVVISORI, new FileVO ( Constants.ESTENSIONE_FILE_XLS ) );
        model.addAttribute ( MODEL_UPLOAD_TIPO_FILE, Constants.ESTENSIONE_FILE_XLS );
        return VIEW_UPLOAD_PROVVISORIO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "') or hasRole(' "+ Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_RICERCA, method = RequestMethod.GET )
    public String viewRicerca (
        Model model,
        HttpServletRequest request ) throws OperationFailedException {

        DtoOutputWsProvvisori outputWsEsito = null;

        RicercaProvvisorioFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaProvvisorioFiltroVO.class );

        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaProvvisorioFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
            request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            try {
                //TODO
                DtoInputWsRicercaProvvisori listaProvvisoriDaCercare = new DtoInputWsRicercaProvvisori ();
                try {
                    listaProvvisoriDaCercare.setDataA (
                        ( null != filtro.getDataElaborazioneA () ) ? DateUtils.getXmlGregorianCalendarDate ( filtro.getDataElaborazioneA () )
                                        : null );
                } catch ( Exception e ) {
                    e.printStackTrace ();
                }
                try {
                    listaProvvisoriDaCercare.setDataDa (
                        ( null != filtro.getDataElaborazioneDa () ) ? DateUtils.getXmlGregorianCalendarDate ( filtro.getDataElaborazioneDa () )
                                        : null );
                } catch ( Exception e ) {
                    e.printStackTrace ();
                }
                listaProvvisoriDaCercare.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );
                listaProvvisoriDaCercare.setIdStatoFlusso ( StringUtils.hasText ( filtro.getStatoFlusso () ) ? filtro.getStatoFlusso () : null );
                outputWsEsito = provvisorioManager.ricercaProvvisori ( listaProvvisoriDaCercare );
                List<ProvvisorioVO> provvisori = mapProvvisorioVOFromDTO ( outputWsEsito );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, provvisori );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( provvisori.size () < 1 ) );
            } catch ( UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e ) {
                model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
            }
        }

        try {
            loadComboboxesFromService ( model );
        } catch ( OperationFailedException e ) {
            model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                String.format ( "Si e' verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
        }

        return VIEW_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_MODIFICA_PROVVISORIO, method = RequestMethod.GET )
    public String viewModificaProvvisorio (
        @PathVariable ( "idProvvisorio" ) Long idProvvisorio,
        Model model,
        HttpServletRequest request ) {
        try {
            DtoInputWsRicercaProvvisori listaProvvisoriDaCercare = new DtoInputWsRicercaProvvisori ();
            listaProvvisoriDaCercare.setId ( idProvvisorio );
            DtoOutputWsProvvisori data = provvisorioManager.ricercaProvvisori ( listaProvvisoriDaCercare );
            if ( null != data ) {
                if ( !CollectionUtils.isEmpty ( data.getProvvisori () ) ) {
                    if ( data.getProvvisori ().size () > 1 ) {
                        model.addAttribute ( MODEL_PROVVISORIO, null );
                        model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Trovati piu' provvisori per l'id: " + idProvvisorio );
                    } else {
                        if ( CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( data.getEsito ().getCodiceErrore () ) ) {
                            List<ProvvisorioVO> provvisori = mapProvvisorioVOFromDTO ( data );
                            model.addAttribute ( MODEL_PROVVISORIO, provvisori.get ( 0 ) );
                            model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "false" );
                        } else {
                            model.addAttribute ( MODEL_PROVVISORIO, null );
                            model.addAttribute ( MODEL_PROVVISORIO_ERRORE, data.getEsito ().getCodiceErrore () + data.getEsito ().getDescrizione () );
                        }
                    }
                } else {
                    model.addAttribute ( MODEL_PROVVISORIO, null );
                    model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Nessun provvisorio trovato per l'id: " + idProvvisorio );
                }
            } else {
                model.addAttribute ( MODEL_PROVVISORIO, null );
                model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Nessun provvisorio trovato per l'id: " + idProvvisorio );
            }
        } catch ( UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e ) {
            model.addAttribute ( MODEL_PROVVISORIO_ERRORE, e.getMessage () );
        }
        return VIEW_DETTAGLIO_PROVVISORIO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_CANCELLA_PROVVISORIO, method = RequestMethod.GET )
    public String viewCancellaProvvisorio (
        @PathVariable ( "idProvvisorio" ) Long idProvvisorio,
        Model model,
        HttpServletRequest request ) {
        try {
            DtoInputWsCancellaProvvisori dtoCancella = new DtoInputWsCancellaProvvisori ();
            DtoProvvisorio prov = new DtoProvvisorio ();
            prov.setId ( idProvvisorio );
            dtoCancella.getDtoProvvisorioList ().add ( prov );
            DtoOutputWsEsito data = provvisorioManager.cancellaProvvisori ( dtoCancella );
            model.addAttribute ( MODEL_PROVVISORIO, null );
            if ( null != data ) {
                if ( CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( data.getCodiceErrore () ) ) {
                    model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "false" );
                } else {
                    model.addAttribute ( MODEL_PROVVISORIO_ERRORE, data.getCodiceErrore () + data.getDescrizione () );
                }
            } else {
                model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Errore in fase di cancellazione del provvisorio con id: " + idProvvisorio );
            }
        } catch ( UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e ) {
            model.addAttribute ( MODEL_PROVVISORIO_ERRORE, e.getMessage () );
        }
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_VISUALIZZA_PROVVISORIO, method = RequestMethod.GET )
    public String viewVisualizzaProvvisorio (
        @PathVariable ( "idProvvisorio" ) Long idProvvisorio,
        Model model,
        HttpServletRequest request ) {
        try {
            DtoInputWsRicercaProvvisori listaProvvisoriDaCercare = new DtoInputWsRicercaProvvisori ();
            listaProvvisoriDaCercare.setId ( idProvvisorio );
            DtoOutputWsProvvisori data = provvisorioManager.ricercaProvvisori ( listaProvvisoriDaCercare );
            if ( null != data ) {
                if ( !CollectionUtils.isEmpty ( data.getProvvisori () ) ) {
                    if ( data.getProvvisori ().size () > 1 ) {
                        model.addAttribute ( MODEL_PROVVISORIO, null );
                        model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Trovati piu' provvisori per l'id: " + idProvvisorio );
                    } else {
                        if ( CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( data.getEsito ().getCodiceErrore () ) ) {
                            List<ProvvisorioVO> provvisori = mapProvvisorioVOFromDTO ( data );
                            model.addAttribute ( MODEL_PROVVISORIO, provvisori.get ( 0 ) );
                            model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "true" );
                        } else {
                            model.addAttribute ( MODEL_PROVVISORIO_ERRORE, data.getEsito ().getCodiceErrore () + data.getEsito ().getDescrizione () );
                        }
                    }
                } else {
                    model.addAttribute ( MODEL_PROVVISORIO, null );
                    model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Nessun provvisorio trovato per l'id: " + idProvvisorio );
                }
            } else {
                model.addAttribute ( MODEL_PROVVISORIO, null );
                model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Nessun provvisorio trovato per l'id: " + idProvvisorio );
            }
        } catch ( UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e ) {
            model.addAttribute ( MODEL_PROVVISORIO_ERRORE, e.getMessage () );
        }

        return VIEW_DETTAGLIO_PROVVISORIO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_INSERISCI_PROVVISORIO, method = RequestMethod.GET )
    public String viewInserisciProvvisorio (
        Model model,
        HttpServletRequest request ) {
        model.addAttribute ( MODEL_PROVVISORIO, new ProvvisorioVO () );
        model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "false" );
        return VIEW_DETTAGLIO_PROVVISORIO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_SALVA_PROVVISORIO, method = RequestMethod.POST )
    public String doSalvaProvvisorio (
        @Valid @ModelAttribute ( MODEL_PROVVISORIO ) ProvvisorioVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {
        if ( bindingResult.hasErrors () ) {
            String validationResultsKey = "org.springframework.validation.BindingResult.salvataggio_provvisorio";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "false" );
            return VIEW_DETTAGLIO_PROVVISORIO;
        } else {
            DtoInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori = new DtoInputWsAggiornaProvvisori ();
            dtoInputWsAggiornaProvvisori.getDtoProvvisorioList ().add ( mapDtoProvvisorioFromVO ( userInput ) );
            try {
                dtoInputWsAggiornaProvvisori.setNonBatch ( true );
                DtoOutputWsEsito resp = provvisorioManager.aggiornaProvvisori ( dtoInputWsAggiornaProvvisori );
                model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "false" );
                if ( !CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( resp.getCodiceErrore () ) ) {
                    redirectAttributes.addFlashAttribute ( "msgError", String.format ( Messages.ERROR_SAVING_CHANGES, resp.getDescrizione () ) );
                } else {
                    redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
                }
            } catch ( UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e ) {
                e.printStackTrace ();
                redirectAttributes.addFlashAttribute ( "msgError",
                    String.format ( Messages.ERROR_SAVING_CHANGES, null != e.getMessage () ? e.getMessage () : userInput.getIdentificativoFlusso () ) );
            }
            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_SALVA_FILE_PROVVISORI, method = RequestMethod.POST )
    public String doUploadProvvisori (
        @Valid @ModelAttribute ( MODEL_FILE_PROVVISORI ) FileVO uploadedFile,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {
        if ( bindingResult.hasErrors () ) {
            String validationResultsKey = "org.springframework.validation.BindingResult.salvataggio_provvisorio";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
        } else {
            try {
                DtoOutputWsEsito result = new DtoOutputWsEsito ();
                try {
                    result = provvisorioManager.uploadProvvisori ( uploadedFile );
                } catch ( FileUploadException e ) {
                    Collections.sort ( e.getErrori () );
                    model.addAttribute ( "nomeFile", uploadedFile.getFileContent ().getOriginalFilename () );
                    model.addAttribute ( "listaErrori", e.getErrori () );
                }
                if ( !CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( result.getCodiceErrore () ) ) {
                    model.addAttribute ( "msgError", String.format ( Messages.ERROR_SAVING_CHANGES, result.getDescrizione () ) );
                } else {
                    model.addAttribute ( "msgInfo", Messages.SAVED_SUCCESSFULLY );
                }
            } catch ( UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e ) {
                e.printStackTrace ();
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( "Si e' verificato un errore durante il caricamento del documento: %s", e.getMessage () ) );
            }
        }
        return VIEW_UPLOAD_PROVVISORIO;
    }

    private List<ProvvisorioVO> mapProvvisorioVOFromDTO ( DtoOutputWsProvvisori dtoOutputWsProvvisori ) {
        List<ProvvisorioVO> provvisori = new ArrayList<> ();
        if ( dtoOutputWsProvvisori != null && !CollectionUtils.isEmpty ( dtoOutputWsProvvisori.getProvvisori () ) ) {
            for ( DtoProvvisorio dtoProvvisorio: dtoOutputWsProvvisori.getProvvisori () ) {
                provvisori.add ( new ProvvisorioVOBuilder ()
                    .withId ( dtoProvvisorio.getId () )
                    .withAnnoProvvisorio ( dtoProvvisorio.getAnnoProvvisorio () )
                    .withCausale ( dtoProvvisorio.getCausale () )
                    .withDescrizione ( dtoProvvisorio.getDescrizione () )
                    .withIdentificativoFlusso ( dtoProvvisorio.getIdentificativoFlusso () )
                    .withStato ( dtoProvvisorio.getStato () )
                    .withDataMovimento (
                        ( null != dtoProvvisorio.getDataMovimento () ) ? dtoProvvisorio.getDataMovimento ().toGregorianCalendar ().getTime () : null )
                    .withAnnoEsercizio ( dtoProvvisorio.getAnnoEsercizio () )
                    .withDataFine ( ( null != dtoProvvisorio.getDataFine () ) ? dtoProvvisorio.getDataFine ().toGregorianCalendar ().getTime () : null )
                    .withIdEnte ( dtoProvvisorio.getIdEnte () )
                    .withImportoProvvisorio ( dtoProvvisorio.getImportoProvvisorio () )
                    .withNumeroProvvisorio ( dtoProvvisorio.getNumeroProvvisorio () )
                    .build () );
            }
        }
        return provvisori;
    }

    private DtoProvvisorio mapDtoProvvisorioFromVO ( ProvvisorioVO prov ) {
        return ( new DTOProvvisorioBuilder ()
                        .withId ( prov.getId () )
                        .withAnnoProvvisorio ( prov.getAnnoProvvisorio () )
                        .withCausale ( prov.getCausale () )
                        .withDescrizione ( prov.getDescrizione () )
                        .withIdentificativoFlusso ( prov.getIdentificativoFlusso () )
                        .withStato ( prov.getStato () )
                        .withDataMovimento ( prov.getDataMovimento () )
                        .withAnnoEsercizio ( prov.getAnnoEsercizio () )
                        .withDataFine ( prov.getDataFine () )
                        .withImportoProvvisorio ( prov.getImportoProvvisorio () )
                        .withNumeroProvvisorio ( prov.getNumeroProvvisorio () )
                        .build () );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_PROVVISORIO + "')" )
    @RequestMapping ( value = URL_RICERCA, method = RequestMethod.POST )
    public String ricerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaProvvisorioFiltroVO filtro, BindingResult bindingResult,
        Model model, HttpServletRequest request ) {

        if ( bindingResult.hasErrors () ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
            try {
                loadComboboxesFromService ( model );
            } catch ( OperationFailedException e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( "Si e' verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
            }

            return VIEW_RICERCA;
        } else {
            request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );
        }

        return "redirect:" + URL_RICERCA;
    }

}
