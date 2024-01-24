/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.csi.epay.epaymodricweb.business.manager.DecodificheManager;
import it.csi.epay.epaymodricweb.business.manager.EsportazioneManager;
import it.csi.epay.epaymodricweb.business.manager.FlussoManager;
import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.Messages;
import it.csi.epay.epaymodricweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.frontend.util.DataTableResponse;
import it.csi.epay.epaymodricweb.integration.mapper.FlussoMapper;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteEsportazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.GenericVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiSintesiVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoOrigineVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoValidator;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;
import it.csi.epay.epaymodricweb.util.CostantiErrori;


@Controller
public class FlussoController extends AuthenticatedParentController {

    public final static String URL_BASE = "/flussi";

    public final static String URL_RICERCA = URL_BASE + "/ricerca";
    public final static String URL_RICERCA_JSON = URL_BASE + "/ricercaJSON";
    public final static String URL_PULISCI = URL_BASE + "/pulisci";
    public final static String URL_SINTESI = URL_BASE + "/{idOrigine}/sintesi";
    public final static String URL_DETTAGLI = URL_SINTESI + "/{idSintesi}/dettagli";
    public final static String URL_DETTAGLIO = URL_DETTAGLI + "/{idDettaglio}";
    public final static String URL_ESPORTA_CSV = URL_BASE + "/esporta-csv";
    public final static String URL_ESPORTA_EXCEL = URL_BASE + "/esporta-excel";
    public final static String URL_FORZA_ELABORAZIONE = URL_BASE + "/forza-elaborazione";
    
//    public final static String URL_PRENOTA_CSV = URL_BASE + "/prenota-csv";
//    public final static String URL_PRENOTA_EXCEL = URL_BASE + "/prenota-excel";
    
//    public final static String URL_REPORT_PRENOTA_CSV =   "/report/entry-prenota-report-ricerca-flussi-csv";
//    public final static String URL_REPORT_PRENOTA_EXCEL =   "/report/entry-prenota-report-ricerca-flussi-excel";
    
    public final static String URL_REPORT_PRENOTA =   "/report/entry-prenota-report-ricerca-flussi";
    
    public final static String VIEW_RICERCA = "flusso/ricerca";
    public final static String VIEW_SINTESI_FLUSSO = "flusso/sintesi";
    public final static String VIEW_DETTAGLI_FLUSSO = "flusso/dettagli";
    public final static String VIEW_DETTAGLIO_FLUSSO = "flusso/dettaglio";

    public static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_flussi";
    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";
    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";
    private static final String MODEL_OPZIONI_STATO_FLUSSO = "opzioni_stato_flusso";
    private static final String MODEL_OPZIONI_CODICI_VERSAMENTO = "opzioni_codici_versamento";
    private static final String MODEL_LIMITE_ESPORTAZIONE_SUPERATO = "limite_esportazione_superato";
    private static final String MODEL_LIMITE_REPORT_SUPERATO = "limite_report_superato";
    private static final String MODEL_NUM_MAX_FLUSSI = "num_max_fussi";
    
    private static final String MODEL_PAGINA_PRENOTAZIONE_REPORT = "pagina_prenotazione_report";
    private static final String MODEL_SINTESI_FLUSSO = "sintesi_flusso";
    private static final String MODEL_SINTESI_FLUSSO_ERRORE = "sintesi_flusso_errore";
    private static final String MODEL_DETTAGLIO_FLUSSO = "dettaglio_flusso";
    private static final String MODEL_DETTAGLIO_FLUSSO_ERRORE = "dettaglio_flusso_errore";
    private static final String MODEL_ID_FLUSSO_ORIGINE = "flusso_origine_id";
    private static final String MODEL_ID_FLUSSO_SINTESI = "flusso_sintesi_id";

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private FlussoManager flussoManager;

    @Autowired
    private EsportazioneManager esportazioneManager;
  
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new FlussoValidator());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public void loadComboboxesFromService ( Model model ) throws OperationFailedException {
        model.addAttribute ( MODEL_OPZIONI_STATO_FLUSSO, decodificheManager.getListaOpzioniStatoFlusso () );
        model.addAttribute ( MODEL_OPZIONI_CODICI_VERSAMENTO, decodificheManager.getListaOpzioniCodiciVersamento () );
//      model.addAttribute ( MODEL_OPZIONI_CODICI_VERSAMENTO, SecurityUtils.getUser ().getCodiciVersamento ());
//      List<UserDetailsCodiciVersamento> detailsCodiciVersamentoProvvisorDaNonCommittareAssolutamente = new LinkedList<UserDetailsCodiciVersamento>();
//      UserDetailsCodiciVersamento det1= new UserDetailsCodiciVersamento(new Long(2409),"codice versamento uno" , "BD00");
//      UserDetailsCodiciVersamento det2= new UserDetailsCodiciVersamento(new Long(2), "codice versamento due", "TF10");
//      UserDetailsCodiciVersamento det3= new UserDetailsCodiciVersamento(new Long(3), "codice versamento tre", "T002");
//      
//      detailsCodiciVersamentoProvvisorDaNonCommittareAssolutamente.add(det1);
//      detailsCodiciVersamentoProvvisorDaNonCommittareAssolutamente.add(det2);
//      detailsCodiciVersamentoProvvisorDaNonCommittareAssolutamente.add(det3);
//      model.addAttribute ( MODEL_OPZIONI_CODICI_VERSAMENTO, detailsCodiciVersamentoProvvisorDaNonCommittareAssolutamente);

//        model.addAttribute ( MODEL_OPZIONI_CODICI_VERSAMENTO, SecurityUtils.getUser ().getCodiciVersamento ());
        
    }

    @RequestMapping(URL_PULISCI)
    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    public String clearRicerca(Model model, HttpServletRequest request) {

        request.getSession().removeAttribute(MODEL_FILTRO_RICERCA);
        model.addAttribute(MODEL_FILTRO_RICERCA, null);
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping(value = URL_RICERCA, method=RequestMethod.GET)
    public String viewRicerca(
        Model model,
        HttpServletRequest request
                    ) {
    	
        RicercaFlussoFiltroVO filtro = this.getFromSession(request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class);
        
       
        if (filtro == null ||  Boolean.parseBoolean ( request.getParameter("init"))) {
            model.addAttribute(MODEL_FILTRO_RICERCA, new RicercaFlussoFiltroVO());
            model.addAttribute(MODEL_RISULTATI_RICERCA, null);
            request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {
            model.addAttribute(MODEL_FILTRO_RICERCA, filtro);
            model.addAttribute(MODEL_RISULTATI_RICERCA, null);
            request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, null );    
        }

        try {
            loadComboboxesFromService(model);
        } catch (OperationFailedException e) {
            model.addAttribute(MODEL_MESSAGGIO_ERRORE,
                String.format ( "Si  verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
        }

        return VIEW_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping ( value = URL_RICERCA_JSON, method = RequestMethod.POST, produces = "application/json" )
    public @ResponseBody DataTableResponse<FlussoOrigineVO> ricercaJSON ( @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaFlussoFiltroVO filtro, HttpServletRequest request )
                    throws OperationFailedException {
        DataTableResponse<FlussoOrigineVO> data = new DataTableResponse<> ();
        String sortingCol = request.getParameter("sortingCol");
        String sortingDir = request.getParameter("sortingDir");
        DtoOutputWsFlussoOrigine result = flussoManager.ricercaFlussoOrigine ( filtro, sortingCol, sortingDir );

        List<FlussoOrigineVO> outputList = new LinkedList<> ();
        for ( DtoFlussoOrigine dto: result.getFlussiOrigine () ) {
            try {
                outputList.add ( FlussoMapper.parseFlussoOrigineVO ( dto ) );
            } catch ( ParseException e ) {
            }
        }
        
        data.setData ( outputList );
        data.setRecordsTotal ( result.getTotalElements () );
        data.setRecordsFiltered ( result.getTotalElements () );
        data.setDraw ( filtro.getDraw () );

        return data;
    }
    
    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping(value = URL_RICERCA, method = RequestMethod.POST)
    public String ricerca(@Valid @ModelAttribute(MODEL_FILTRO_RICERCA) RicercaFlussoFiltroVO filtro, BindingResult bindingResult,
    		Model model, HttpServletRequest request) {
    	List<FlussoOrigineVO> list =  new ArrayList<> ();

    	if ( bindingResult.hasErrors () ) {
    		model.addAttribute(MODEL_FILTRO_RICERCA, filtro);
    		model.addAttribute(MODEL_RISULTATI_RICERCA, null);

//    		try {
//    			loadComboboxesFromService(model);
//    		} catch (OperationFailedException e) {
//    			model.addAttribute(MODEL_MESSAGGIO_ERRORE,
//    					String.format ( "Si e' verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
//    		}

//    		return VIEW_RICERCA;
    	} else {
    		request.getSession().setAttribute(MODEL_FILTRO_RICERCA, filtro);

    		try {
//    			list = flussoManager.ricercaFlussoOrigine(filtro);
    		    list.add ( new FlussoOrigineVO () );
    			DtoOutputWsRicercaLimiteEsportazione limiteEsportazione = flussoManager.ricercaLimiteEsportazione();
    			String limite = limiteEsportazione.getNumMaxRecordsReport();
    			model.addAttribute(CostantiErrori.NUM_MAX_RECORDS_EXPORT, limite);
    			model.addAttribute(MODEL_RISULTATI_RICERCA, list);
    			model.addAttribute(MODEL_NESSUN_RISULTATO_RICERCA, (list.size() < 1));
    		} 

    		catch (EpaymodricException_Exception | Exception_Exception | UnrecoverableException_Exception e) {
    			model.addAttribute(MODEL_RISULTATI_RICERCA, null);
    			model.addAttribute(MODEL_MESSAGGIO_ERRORE, String.format(Messages.ERROR_DURING_SEARCH, e.getMessage()));
    		} 
    	}
    	
    	try {
			loadComboboxesFromService(model);
		} catch (OperationFailedException e) {
			model.addAttribute(MODEL_MESSAGGIO_ERRORE,
					String.format ( "Si e' verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
		}
    	return VIEW_RICERCA;

//    	return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping(value = URL_SINTESI, method=RequestMethod.GET)
    public String viewSintesi(
        @PathVariable("idOrigine") Long idFlussoOrigine,
        Model model,
        HttpServletRequest request
                    ) {

        FlussiSintesiVO data;
        try {
            data = flussoManager.ricercaFlussiSintesi(idFlussoOrigine);

            model.addAttribute(MODEL_SINTESI_FLUSSO, data);
        } catch (OperationFailedException e) {
            model.addAttribute(MODEL_SINTESI_FLUSSO_ERRORE, e.getMessage());
        }

        return VIEW_SINTESI_FLUSSO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping(value = URL_DETTAGLI, method=RequestMethod.GET)
    public String viewDettagli(
        @PathVariable("idOrigine") Long idFlussoOrigine,
        @PathVariable("idSintesi") Long idFlussoSintesi,
        Model model,
        HttpServletRequest request
                    ) {
        FlussiDettaglioVO data;

        try {
            data = flussoManager.ricercaFlussiDettaglio(idFlussoSintesi);

            model.addAttribute(MODEL_DETTAGLIO_FLUSSO, data);
            model.addAttribute(MODEL_ID_FLUSSO_ORIGINE, idFlussoOrigine);
        } catch (OperationFailedException e) {
            model.addAttribute(MODEL_DETTAGLIO_FLUSSO_ERRORE, e.getMessage());
        }

        return VIEW_DETTAGLI_FLUSSO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping(value = URL_DETTAGLIO, method=RequestMethod.GET)
    public String viewDettaglio(
        @PathVariable("idOrigine") Long idFlussoOrigine,
        @PathVariable("idSintesi") Long idFlussoSintesi,
        @PathVariable("idDettaglio") Long idFlussoDettaglio,
        Model model,
        HttpServletRequest request
                    ) {
        FlussoDettaglioVO data;

        try {
            data = flussoManager.ricercaFlussoDettaglio(idFlussoSintesi, idFlussoDettaglio);

            model.addAttribute(MODEL_DETTAGLIO_FLUSSO, data);
            model.addAttribute(MODEL_ID_FLUSSO_ORIGINE, idFlussoOrigine);
            model.addAttribute(MODEL_ID_FLUSSO_SINTESI, idFlussoSintesi);
        } catch (OperationFailedException e) {
            model.addAttribute(MODEL_DETTAGLIO_FLUSSO_ERRORE, e.getMessage());
        }

        return VIEW_DETTAGLIO_FLUSSO;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping ( URL_ESPORTA_EXCEL )
    public String esportaXlsx ( HttpServletResponse response, HttpServletRequest request, Model model ) throws IOException {

        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                esportazioneManager.esportaListaFlussiXls ( response, filtro );
            } catch ( OperationFailedException e ) {
            	if (CostantiErrori.NUM_MAX_RECORDS_EXPORT.equals(e.getMessage()))
            	{
            		 model.addAttribute ( MODEL_LIMITE_ESPORTAZIONE_SUPERATO, true );
                     model.addAttribute(MODEL_PAGINA_PRENOTAZIONE_REPORT, ".." + ReportController.URL_PRENOTAZIONE + "?salvaprenotazione=true");
            	}
            	else if(!StringUtils.isEmpty(e.getMessage()) && 
            			e.getMessage().equals(CostantiErrori.NUM_MAX_RECORDS_REPORT))
            	{
            		 model.addAttribute ( MODEL_LIMITE_REPORT_SUPERATO, true );
            		 try {
            			 DtoOutputWsRicercaLimiteElaborazioneReport limiteEsportazione = flussoManager.ricercaLimiteElaborazioneReport();
            			 
            			 model.addAttribute(MODEL_NUM_MAX_FLUSSI,limiteEsportazione.getNumMaxRecordsReport());
            			 
					} catch (Exception e2) {
						 model.addAttribute(MODEL_DETTAGLIO_FLUSSO_ERRORE, e2.getMessage());
					}
            	}
            	else
            	{
            		 model.addAttribute(MODEL_DETTAGLIO_FLUSSO_ERRORE, e.getMessage());
            	}
            	
               
                return viewRicerca ( model, request );
            }
        }
        return null;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping ( URL_ESPORTA_CSV )
    public String esportaCsv ( HttpServletResponse response, HttpServletRequest request, Model model ) throws IOException {

        RicercaFlussoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                esportazioneManager.esportaListaFlussiCsv ( response, filtro );
            } catch ( OperationFailedException e ) {
            	if (CostantiErrori.NUM_MAX_RECORDS_EXPORT.equals(e.getMessage()))
            	{
            		 model.addAttribute ( MODEL_LIMITE_ESPORTAZIONE_SUPERATO, true );
                     model.addAttribute(MODEL_PAGINA_PRENOTAZIONE_REPORT, ".." + ReportController.URL_PRENOTAZIONE + "?salvaprenotazione=true");
            	}
            	else if(!StringUtils.isEmpty(e.getMessage()) && 
            			e.getMessage().equals(CostantiErrori.NUM_MAX_RECORDS_REPORT))
            	{
            		 model.addAttribute ( MODEL_LIMITE_REPORT_SUPERATO, true );
            		 try {
            			 DtoOutputWsRicercaLimiteElaborazioneReport limiteEsportazione = flussoManager.ricercaLimiteElaborazioneReport();
            			 
            			 model.addAttribute(MODEL_NUM_MAX_FLUSSI,limiteEsportazione.getNumMaxRecordsReport());
            			 
					} catch (Exception e2) {
						 model.addAttribute(MODEL_DETTAGLIO_FLUSSO_ERRORE, e2.getMessage());
					}
            	}
            	else
            	{
            		 model.addAttribute(MODEL_DETTAGLIO_FLUSSO_ERRORE, e.getMessage());
            	}
            	
               
                return viewRicerca ( model, request );
            }
        }
        return null;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    @RequestMapping ( value = URL_FORZA_ELABORAZIONE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public GenericVO forzaElaborazione ( @RequestParam ( value = "identificativiFlussoDaRielaborare[]" ) String [] identificativiFlussoDaRielaborare,
        HttpServletResponse response,
        HttpServletRequest request )
                        throws IOException {
        GenericVO esito = new GenericVO ();
        try {
            esito = flussoManager.inserisciFlussiDaRielaborare ( Arrays.asList ( identificativiFlussoDaRielaborare ) );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
        return esito;
    }


}
