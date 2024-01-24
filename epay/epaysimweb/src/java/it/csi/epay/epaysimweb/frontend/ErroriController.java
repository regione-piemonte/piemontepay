/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.csi.epay.epaysimweb.business.manager.ErroriManager;
import it.csi.epay.epaysimweb.business.manager.EsportazioneManager;
import it.csi.epay.epaysimweb.common.Messages;
import it.csi.epay.epaysimweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysimweb.model.errori.ErroreVO;
import it.csi.epay.epaysimweb.model.errori.ErroreVOValidator;
import it.csi.epay.epaysimweb.model.errori.RicercaErroreFiltroVO;
import it.csi.epay.epaysimweb.util.DateUtils;


@Controller
@RequestMapping ( value = "/errori" )
public class ErroriController extends AuthenticatedParentController {

    private static final String URL_RICERCA = "/errori/ricerca";

    private final static String URL_VISUALIZZA = "/{idErrore}/visualizza";

    private final static String VIEW_DETTAGLIO = "errori/dettaglio";

    private static final String MODEL_ERRORE_ERRORE = "errore_errore";

    private static final String MODEL_ERRORE = "dettaglio_errore";

    private static final String MODEL_DISABLE_INPUT_PROVVISORIO = "disable_input";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_errori";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";
    
    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private ErroriManager erroriManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @Override
    public void loadComboboxes ( Model model ) {
        // Nothing here...
    }
    
    @InitBinder
    public void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new ErroreVOValidator () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }
    
    //export errori

    @RequestMapping ( value = "/esportacsv" )
    public void esportaTabellaProvvisoriCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        List<FlussoOrigineErrorePagopaOutputDTO> items = getErroriDaEsportare ( request );

        if ( items != null && items.size () > 0 ) {
            esportazioneManager.esportaTemplateErroriCsv ( response, items );
        }
    }

    @RequestMapping ( value = "/esportaxls" )
    public void esportaTabellaProvvisoriXls ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        List<FlussoOrigineErrorePagopaOutputDTO> items = getErroriDaEsportare ( request );

        if ( items != null && items.size () > 0 ) {
            esportazioneManager.esportaTemplateErroriXls ( response, items );
        }
    }   
    
    private  List<FlussoOrigineErrorePagopaOutputDTO> getErroriDaEsportare ( HttpServletRequest request ) {
        RicercaErroreFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaErroreFiltroVO.class );

        List<FlussoOrigineErrorePagopaOutputDTO> flussiDaEsportare = new ArrayList<FlussoOrigineErrorePagopaOutputDTO> ();

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

                RicercaFlussoErroreInputDTO filtroItem = new RicercaFlussoErroreInputDTO ();
                filtroItem.setDataFlussoDa ( fromX );
                filtroItem.setDataFlussoA ( toX );
                filtroItem.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );

                flussiDaEsportare = erroriManager.ricercaErrori ( filtroItem );
            } catch ( Exception e ) {
                throw new RuntimeException ( e );
            }
        }

        return flussiDaEsportare;
    }
    
    @RequestMapping ( value = URL_VISUALIZZA, method = RequestMethod.GET )
    public String viewVisualizzaErrore (
        @PathVariable ( "idErrore" ) Long idErrore,
        Model model,
        HttpServletRequest request ) {
        try {
            RicercaFlussoErroreInputDTO listaProvvisoriDaCercare = new RicercaFlussoErroreInputDTO ();
            listaProvvisoriDaCercare.setId ( idErrore );
            List<FlussoOrigineErrorePagopaOutputDTO> data = erroriManager.ricercaErrori ( listaProvvisoriDaCercare );
            if ( null != data ) {
                if ( !CollectionUtils.isEmpty ( data) ) {
                    if ( data.size () > 1 ) {
                        model.addAttribute ( MODEL_ERRORE, null );
                        model.addAttribute ( MODEL_ERRORE_ERRORE, "Trovati piu' errori per l'id: " + idErrore );
                    } else {
                        List<ErroreVO> provvisori = mapErroriVOFromDTO ( data );
                        model.addAttribute ( MODEL_ERRORE, provvisori.get ( 0 ) );
                        model.addAttribute ( MODEL_DISABLE_INPUT_PROVVISORIO, "true" );
                    }
                } else {
                    model.addAttribute ( MODEL_ERRORE, null );
                    model.addAttribute ( MODEL_ERRORE_ERRORE, "Nessun errore trovato per l'id: " + idErrore );
                }
            } else {
                model.addAttribute ( MODEL_ERRORE, null );
                model.addAttribute ( MODEL_ERRORE_ERRORE, "Nessun errore trovato per l'id: " + idErrore );
            }
        } catch ( Exception e ) {
            model.addAttribute ( MODEL_ERRORE_ERRORE, e.getMessage () );
        }

        return VIEW_DETTAGLIO;
    }
    
    @PreAuthorize ( "hasRole('RICERCA_ERRORI')" )
    @RequestMapping ( value = "/ricerca", method = RequestMethod.GET )
    public String viewRicerca (
        Model model,
        HttpServletRequest request ) throws OperationFailedException {

        List<FlussoOrigineErrorePagopaOutputDTO> outputWsEsito = null;

        RicercaErroreFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaErroreFiltroVO.class );

        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaErroreFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            try {
                //TODO
                RicercaFlussoErroreInputDTO listaErroriDaCercare = new RicercaFlussoErroreInputDTO ();
                try {
                    listaErroriDaCercare.setDataFlussoA ( 
                        ( null != filtro.getDataElaborazioneA () ) ? DateUtils.getXmlGregorianCalendarDate ( filtro.getDataElaborazioneA () )
                                        : null );
                } catch ( Exception e ) {
                    e.printStackTrace ();
                }
                try {
                    listaErroriDaCercare.setDataFlussoDa ( 
                        ( null != filtro.getDataElaborazioneDa () ) ? DateUtils.getXmlGregorianCalendarDate ( filtro.getDataElaborazioneDa () )
                                        : null );
                } catch ( Exception e ) {
                    e.printStackTrace ();
                }
                listaErroriDaCercare.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );
                outputWsEsito = erroriManager.ricercaErrori ( listaErroriDaCercare );
                List<ErroreVO> errori = mapErroriVOFromDTO ( outputWsEsito );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, errori );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( errori.size () < 1 ) );
            } catch ( OperationFailedException e ) {
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

        return URL_RICERCA;
    }

    public void loadComboboxesFromService ( Model model ) throws OperationFailedException {
        //nulla da fare
    }

    
    private List<ErroreVO> mapErroriVOFromDTO ( List<FlussoOrigineErrorePagopaOutputDTO> data ) {
        List<ErroreVO> errList = new ArrayList<> ();
        if ( data != null && !CollectionUtils.isEmpty ( data ) ) {
            for ( FlussoOrigineErrorePagopaOutputDTO err: data ) {
                ErroreVO e = new ErroreVO();
                e.setCfEnteRicevente ( err.getCfEnteRicevente () );
                e.setCodiceErrore ( err.getCodiceErrore () );
                e.setCodiceVersamento ( err.getCodiceVersamento () );
                e.setDataInserimento (DateUtils.getDate(err.getDataInserimento () ));
                e.setDataModifica ( DateUtils.getDate(err.getDataModifica () ));
                e.setDataoraFlusso ( DateUtils.getDate(err.getDataoraFlusso () ));
                e.setDataRegolamento ( DateUtils.getDate(err.getDataRegolamento () ));
                e.setDescrizioneErrore ( err.getDescrizioneErrore () );
                e.setIbanRiversamentoFlusso ( err.getIbanRiversamentoFlusso () );
                e.setId ( err.getId () );
                e.setIdElaborazione ( err.getIdElaborazione () );
                e.setIdentificativoFlusso ( err.getIdentificativoFlusso () );
                e.setIdentificativoPsp ( err.getIdentificativoPsp () );
                e.setIdMessaggio ( err.getIdMessaggio () );
                e.setIdStatoFlussolusso ( err.getIdStatoFlussolusso () );
                e.setImportoTotalePagamenti (err.getImportoTotalePagamenti ()  );
                e.setImportoTotalePagamentiIntermediati ( err.getImportoTotalePagamentiIntermediati () );
                e.setNumeroTotalePagamenti ( err.getNumeroTotalePagamenti () );
                e.setNumeroTotalePagamentiIntermediati ( err.getNumeroTotalePagamentiIntermediati () );
                e.setIdentificativoUnivocoRegolamento ( err.getIdentificativoUnivocoRegolamento () );
                errList.add(e);
            }
        }
        return errList;
    }

    @RequestMapping ( value = "/pulisci")
    public String clearRicerca ( Model model, HttpServletRequest request ) {

        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
    public String ricerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaErroreFiltroVO filtro, BindingResult bindingResult,
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

            return "/errori/ricerca";
        } else {
            request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );
        }

        return "redirect:" + "/errori/ricerca";
    }

}
