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
import javax.servlet.http.HttpSession;
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
import org.springframework.xml.transform.StringSource;

import it.csi.epay.epaysimweb.business.manager.EsportazioneManager;
import it.csi.epay.epaysimweb.business.manager.ProvvisoriManager;
import it.csi.epay.epaysimweb.common.Messages;
import it.csi.epay.epaysimweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.generated.FlussoGiornaleDiCassa;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoOutputWsProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoProvvisorio;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.GiornaleDiCassaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.InserisciProvvisorioOutputResponse;
import it.csi.epay.epaysimweb.model.provvisori.InserisciProvvisorioVO;
import it.csi.epay.epaysimweb.model.provvisori.ProvvisorioVO;
import it.csi.epay.epaysimweb.model.provvisori.ProvvisorioVOBuilder;
import it.csi.epay.epaysimweb.model.provvisori.ProvvisorioVOValidator;
import it.csi.epay.epaysimweb.model.provvisori.RicercaProvvisorioFiltroVO;
import it.csi.epay.epaysimweb.util.DateUtils;
import it.csi.epay.epaysimweb.util.SecurityUtils;


@Controller
@RequestMapping ( value = "/provvisori" )
public class ProvvisoriController extends AuthenticatedParentController {

    //inserisci
    private static final String PAGINA_INSERISCI = "/inserisci";

    private static final String URL_INSERISCI = "/provvisori/inserisci";

    //model inserisci
    private static final String MODEL_INSERISCI = "inserisci_provvisorio";
    //    private static final String MODEL_RISULTATO_INSERISCI = "risultato_inserisci_flusso";

    public final static String URL_BASE = "/provvisori";

    private static final String URL_RICERCA = "/provvisori/ricerca";

    private final static String URL_VISUALIZZA_PROVVISORIO = "/{idProvvisorio}/visualizza";

    private final static String VIEW_DETTAGLIO_PROVVISORIO = "provvisori/dettaglio";

    private static final String MODEL_PROVVISORIO_ERRORE = "provvisorio_errore";

    private static final String MODEL_PROVVISORIO = "dettaglio_provvisorio";

    private static final String MODEL_DISABLE_INPUT_PROVVISORIO = "disable_input";//true, false

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_provvisori";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";
    
    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private ProvvisoriManager provvisoriManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @Override
    public void loadComboboxes ( Model model ) {
        // Nothing here...
    }
    
    @RequestMapping ( value = "/inserisci" )
    public String viewInserisciProvvisorio ( Model model, HttpServletRequest request, HttpSession session ) {
        return URL_INSERISCI;
    }

    @PreAuthorize ( "hasRole('INSERISCI_PROVVISORI')" )
    @RequestMapping ( value = "/inserisci", method = RequestMethod.POST )
    public String ricercaGiornaleDiCassa ( @Valid @ModelAttribute ( "inserisci_provvisorio" ) InserisciProvvisorioVO flusso,
        BindingResult bindingResult, Model model, HttpServletRequest request ) throws IOException {

        if ( bindingResult.hasErrors () ) {
            model.addAttribute ( MODEL_INSERISCI, flusso );
            return PAGINA_INSERISCI;
        } else {
            if ( flusso.getXmlFlussoProvvisorio () != null && flusso.getXmlFlussoProvvisorio ().getSize () > 0 ) {
                String xmlFlusso = new String ( flusso.getXmlFlussoProvvisorio ().getBytes () );
                try {
                    FlussoGiornaleDiCassa flussoGiornale = unmarshal ( xmlFlusso );
                    if ( flussoGiornale.getIdentificativoFlussoBT () != null || !flussoGiornale.getIdentificativoFlussoBT ().isEmpty () ) {
                        GiornaleDiCassaOutputDTO giornaleDiCassa = provvisoriManager.ricercaGiornaleDiCassa ( flussoGiornale );
                        if ( null != giornaleDiCassa.getCodiceEsito () && giornaleDiCassa.getCodiceEsito ().equals ( "OK" ) ) {
                            request.getSession ().setAttribute ( MODEL_INSERISCI, xmlFlusso );
                            if ( giornaleDiCassa.getGiornaleDiCassaDTO ().getIdentificativoFlussoBT () != null ) {
                                model.addAttribute ( "msgPopup",
                                    String.format ( Messages.POPUP_UPDATE_GIORNALE, giornaleDiCassa.getGiornaleDiCassaDTO ().getIdentificativoFlussoBT () ) );

                            } else {
                                InserisciProvvisorioOutputResponse output
                                    = inserisciProvvisorio ( flussoGiornale, SecurityUtils.getUser ().getUsername (), xmlFlusso );
                                if ( null != output.getCodiceEsito () && output.getCodiceEsito ().equals ( "OK" ) ) {
                                    String message
                                        = String.format ( Messages.LOAD_SUCCESSFULLY,
                                            null != flussoGiornale ? flussoGiornale.getIdentificativoFlussoBT () : "" );
                                    model.addAttribute ( "msgInfo", message );
                                } else {
                                    String message
                                        = String.format ( Messages.ELAB_PROVVISORIO_UNSUCCESS,
                                            null != flussoGiornale ? flussoGiornale.getIdentificativoFlussoBT () : "" );
                                    model.addAttribute ( "msgError", message );
                                    model.addAttribute ( "outputList", output.getInserisciProvvisorioOutputDTOList () );
                                }
                            }
                        } else {
                            String message
                                = String.format ( Messages.ELAB_PROVVISORIO_UNSUCCESS,
                                    null != flussoGiornale ? flussoGiornale.getIdentificativoFlussoBT () : "" );
                            model.addAttribute ( "msgError", message );
                        }
                    } else {
                        bindingResult.rejectValue ( "xmlFlussoProvvisorio", "xmlFlussoProvvisorio.validitaGenerica", Messages.ELAB_PROVVISORIO_UNSUCCESS );

                    }
                } catch ( Exception e ) {
                    bindingResult.rejectValue ( "xmlFlussoProvvisorio", "xmlFlussoProvvisorio.validitaGenerica", Messages.ELAB_PROVVISORIO_UNSUCCESS );
                }
            } else {
                bindingResult.rejectValue ( "xmlFlussoProvvisorio", "xmlFlussoProvvisorio.validitaGenerica", Messages.ELAB_PROVVISORIO_UNSUCCESS );
            }
        }
        return URL_INSERISCI;
    }

    private InserisciProvvisorioOutputResponse inserisciProvvisorio ( FlussoGiornaleDiCassa flussoGiornale, String username, String xmlFlusso )
                    throws OperationFailedException {
        return provvisoriManager.salvaFlussoProvvisori ( flussoGiornale, SecurityUtils.getUser ().getUsername (), xmlFlusso );
    };

    @PreAuthorize ( "hasRole('INSERISCI_PROVVISORI')" )
    @RequestMapping ( value = "/inserisci-provvisorio", method = RequestMethod.GET )
    public String inserisciProvvisorio ( Model model, HttpServletRequest request ) throws IOException {

        String xmlFlusso = this.getFromSession ( request, MODEL_INSERISCI, String.class );

        FlussoGiornaleDiCassa flussoGiornale = unmarshal ( xmlFlusso );
        InserisciProvvisorioOutputResponse output;
        try {
            output = inserisciProvvisorio ( flussoGiornale, SecurityUtils.getUser ().getUsername (), xmlFlusso );
            if ( null != output.getCodiceEsito () && output.getCodiceEsito ().equals ( "OK" ) ) {
                String message
                    = String.format ( Messages.LOAD_SUCCESSFULLY, null != flussoGiornale ? flussoGiornale.getIdentificativoFlussoBT () : "" );
                model.addAttribute ( "msgInfo", message );
            } else {
                String message
                    = String.format ( Messages.ELAB_PROVVISORIO_UNSUCCESS, null != flussoGiornale ? flussoGiornale.getIdentificativoFlussoBT () : "" );
                model.addAttribute ( "msgError", message );
                model.addAttribute ( "outputList", output.getInserisciProvvisorioOutputDTOList () );
            }
        } catch ( OperationFailedException e ) {
            String message
                = String.format ( Messages.ELAB_PROVVISORIO_UNSUCCESS, null != flussoGiornale ? flussoGiornale.getIdentificativoFlussoBT () : "" );
            model.addAttribute ( "msgError", message );
        }
        return URL_INSERISCI;
    }

    @InitBinder
    public void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new ProvvisorioVOValidator () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }
    
    //Import export provvisori

    @RequestMapping ( value = "/esportacsv" )
    public void esportaTabellaProvvisoriCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        DtoOutputWsProvvisori items = getProvvisoriDaEsportare ( request );

        if ( items != null && items.getProvvisori ().size () > 0 ) {
            esportazioneManager.esportaTemplateProvvisoriCsv ( response, items );
        }
    }

    @RequestMapping ( value = "/esportaxls" )
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

                flussiDaEsportare = provvisoriManager.ricercaProvvisori ( filtroRicerca );
            } catch ( Exception e ) {
                throw new RuntimeException ( e );
            }
        }

        return flussiDaEsportare;
    }
    
    @RequestMapping ( value = URL_VISUALIZZA_PROVVISORIO, method = RequestMethod.GET )
    public String viewVisualizzaProvvisorio (
        @PathVariable ( "idProvvisorio" ) Long idProvvisorio,
        Model model,
        HttpServletRequest request ) {
        try {
            DtoInputWsRicercaProvvisori listaProvvisoriDaCercare = new DtoInputWsRicercaProvvisori ();
            listaProvvisoriDaCercare.setId ( idProvvisorio );
            DtoOutputWsProvvisori data = provvisoriManager.ricercaProvvisori ( listaProvvisoriDaCercare );
            if ( null != data ) {
                if ( !CollectionUtils.isEmpty ( data.getProvvisori () ) ) {
                    if ( data.getProvvisori ().size () > 1 ) {
                        model.addAttribute ( MODEL_PROVVISORIO, null );
                        model.addAttribute ( MODEL_PROVVISORIO_ERRORE, "Trovati piu' provvisori per l'id: " + idProvvisorio );
                    } else {
                        if ( "000".equals ( data.getEsito ().getCodiceErrore () ) ) {
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
        } catch ( Exception e ) {
            model.addAttribute ( MODEL_PROVVISORIO_ERRORE, e.getMessage () );
        }

        return VIEW_DETTAGLIO_PROVVISORIO;
    }
    
    @PreAuthorize ( "hasRole('RICERCA_PROVVISORI')" )
    @RequestMapping ( value = "/ricerca", method = RequestMethod.GET )
    public String viewRicerca (
        Model model,
        HttpServletRequest request ) throws OperationFailedException {

        DtoOutputWsProvvisori outputWsEsito = null;

        RicercaProvvisorioFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaProvvisorioFiltroVO.class );

        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaProvvisorioFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
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
                listaProvvisoriDaCercare.setIdStatoFlusso ( filtro.getStatoFlusso () );
                outputWsEsito = provvisoriManager.ricercaProvvisori ( listaProvvisoriDaCercare );
                List<ProvvisorioVO> provvisori = mapProvvisorioVOFromDTO ( outputWsEsito );
                model.addAttribute ( MODEL_RISULTATI_RICERCA, provvisori );
                model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( provvisori.size () < 1 ) );
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
                    .withIdEnte ( dtoProvvisorio.getIdEnte () )
                    .withImportoProvvisorio ( dtoProvvisorio.getImportoProvvisorio () )
                    .withNumeroProvvisorio ( dtoProvvisorio.getNumeroProvvisorio () )
                    .build () );
            }
        }
        return provvisori;
    }

    @RequestMapping ( value = "/pulisci")
    public String clearRicerca ( Model model, HttpServletRequest request ) {

        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
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

            return "/provvisori/ricerca";
        } else {
            request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );
        }

        return "redirect:" + "/provvisori/ricerca";
    }

    
    public FlussoGiornaleDiCassa unmarshal ( String xmlString ) {
        return (FlussoGiornaleDiCassa) marshaller.unmarshal ( new StringSource ( xmlString ) );
    }

}
