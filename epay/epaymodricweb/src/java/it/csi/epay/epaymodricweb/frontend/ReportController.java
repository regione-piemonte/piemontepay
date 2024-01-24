/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.csi.epay.epaymodricweb.business.manager.DecodificheManager;
import it.csi.epay.epaymodricweb.business.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.Messages;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;
import it.csi.epay.epaymodricweb.model.report.PrenotazioneRicercaReportFormVO;
import it.csi.epay.epaymodricweb.model.report.ReportValidator;
import it.csi.epay.epaymodricweb.model.report.RisultatiRicercaReportVO;
import it.csi.epay.epaymodricweb.util.SecurityUtils;

@Controller
public class ReportController extends AuthenticatedParentController{
	
	public static final String URL_BASE = "/report";
	public static final String URL_PRENOTAZIONE = URL_BASE + "/prenota-report-ricerca-flussi";
//	public static final String URL_RICERCA_PRENOTAZIONE = URL_BASE + "/ricerca-report-ricerca-flussi";
	public static final String MODEL_PRENOTAZIONE_REPORT = "filtro_prenotazione_report"; // errato da correggere
	public static final String MODEL_RISULTATI_RICERCA = "lista_risultati";
    private static final String MODEL_OPZIONI_STATO_FLUSSO = "opzioni_stato_flusso";
    public final static String URL_PULISCI = URL_BASE + "/pulisci";
    private static final String MODEL_OPZIONI_CODICI_VERSAMENTO = "opzioni_codici_versamento";
    private static final String URL_DOWNLOAD_REPORT = URL_BASE + "/{idExport}/download";
//    public final static String URL_ENTRY_PRENOTAZIONE = URL_BASE + "/entry-prenota-report-ricerca-flussi-csv";
//    public final static String URL_ENTRY_PRENOTAZIONE_EXCEL = URL_BASE + "/entry-prenota-report-ricerca-flussi-excel";
    
    private static final String CONTENT_DISPOSITION_KEY = "Content-Disposition";
    private static final String CONTENT_DISPOSITION_VALUE = "attachment; filename=";
    private static final String CONTENT_TRANSFER_ENCODING_KEY = "Content-Transfer-Encoding";
    private static final String CONTENT_TRANSFER_ENCODING_VALUE = "binary";
    private static final String CONTENT_ENCODING_KEY = "Content-Encoding";
    private static final String CONTENT_ENCODING_VALUE = "none";
    private static final String PRAGMA_KEY = "Pragma";
    private static final String PRAGMA_VALUE = "public";
    private static final String CACHE_CONTROL_KEY = "Cache-Control";
    private static final String CACHE_CONTROL_VALUE = "max-age=0";
    private static final String FORCE_DOWNLOAD = "application/force-download";
    
    public static final String MODEL_TIPO_REPORT_CONTABILE = "tipo_report_contabile";
    public static final String MODEL_TIPO_REPORT_FLUSSI_COMPLETI = "tipo_report_flussi_completi";

    
	@Autowired
	PrenotazioneReportManager prenotazioneReportManager;
	
	 @Autowired
	 private DecodificheManager decodificheManager;
	 
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.setValidator(new ReportValidator());

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    }

	
	//@PreAuthorize ( "hasRole('" + Constants.USE_CASES.PRENOTAZIONE_EXPORT + "')" )
	@SuppressWarnings("unused")
	@RequestMapping(value=URL_PRENOTAZIONE, method=RequestMethod.GET)
    public String prenotazioneExport ( HttpServletRequest request, Model model ) {

        PrenotazioneRicercaReportFormVO filtro = (PrenotazioneRicercaReportFormVO) request.getSession ().getAttribute ( MODEL_PRENOTAZIONE_REPORT );

        if ( Boolean.parseBoolean ( request.getParameter ( "salvaprenotazione" ) ) ) {
            filtro = new PrenotazioneRicercaReportFormVO ();
            RicercaFlussoFiltroVO filtroRicerca = this.getFromSession ( request, FlussoController.MODEL_FILTRO_RICERCA, RicercaFlussoFiltroVO.class );
            filtro.setIdCodVersamento ( filtroRicerca.getIdCodVersamento () );
            filtro.setDataElaborazioneA ( filtroRicerca.getDataElaborazioneA () );
            filtro.setDataElaborazioneDa ( filtroRicerca.getDataElaborazioneDa () );
            filtro.setDataRicezioneA ( filtroRicerca.getDataRicezioneA () );
            filtro.setDataRicezioneDa ( filtroRicerca.getDataRicezioneDa () );
            filtro.setIdentificativoFlusso ( filtroRicerca.getIdentificativoFlusso () );
            filtro.setIuv ( filtroRicerca.getIuv () );
            filtro.setStatoFlusso ( filtroRicerca.getStatoFlusso () );
            filtro.setPsp ( filtroRicerca.getPsp () );
            filtro.setNomeReport ( UUID.randomUUID ().toString () );
        }

        if ( filtro == null ||  Boolean.parseBoolean ( request.getParameter("init"))) {
            filtro = new PrenotazioneRicercaReportFormVO ();
        }

        model.addAttribute ( MODEL_PRENOTAZIONE_REPORT, filtro );
        model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, null );
        request.getSession ().setAttribute ( Constants.FILTRO_TIPO_FILE, Constants.TIPO_FILE_XLS );

        List<RisultatiRicercaReportVO> reports = null;
        try {
            reports = prenotazioneReportManager.ricercaReport ();
            model.addAttribute ( MODEL_RISULTATI_RICERCA, reports );
        } catch ( OperationFailedException e ) {
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
            model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
        }
        model.addAttribute ( "reports", reports );

        try {

            loadComboboxesFromService ( model );
        } catch ( OperationFailedException e ) {
            model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                String.format ( "Si  verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
        }

        return URL_PRENOTAZIONE;
    }
	
//	@SuppressWarnings("unused")
//	@RequestMapping(value=URL_RICERCA_PRENOTAZIONE, method=RequestMethod.GET)
//	public String ricercaPrenotazioneExport(HttpServletRequest request, Model model) {
//		
//		PrenotazioneRicercaReportFormVO filtro = (PrenotazioneRicercaReportFormVO) request.getSession().getAttribute(MODEL_PRENOTAZIONE_REPORT);
//		
//		if(filtro == null || filtro.isEmpty()) {
//			model.addAttribute ( MODEL_PRENOTAZIONE_REPORT, new PrenotazioneRicercaReportFormVO());
//			model.addAttribute(MODEL_RISULTATI_RICERCA, null);
//			request.getSession ().setAttribute ( MODEL_RISULTATI_RICERCA, null );
//
//		}else {
//			model.addAttribute(MODEL_PRENOTAZIONE_REPORT, filtro);
//			
//		}
//		List<RisultatiRicercaReportVO> reports = null;
//		try {
//			reports = prenotazioneReportManager.ricercaReport();
//			model.addAttribute(MODEL_RISULTATI_RICERCA, reports);
//		} catch (OperationFailedException e) {
////				e.printStackTrace();
//			model.addAttribute(MODEL_RISULTATI_RICERCA, null);
//			model.addAttribute(MODEL_MESSAGGIO_ERRORE, String.format(Messages.ERROR_DURING_SEARCH, e.getMessage()));
//		}
//		model.addAttribute("reports", reports);
//		
//		try {
//			
//            loadComboboxesFromService(model);
//        } catch (OperationFailedException e) {
//            model.addAttribute(MODEL_MESSAGGIO_ERRORE,
//                String.format ( "Si  verificato un errore durante il caricamento della pagina: %s", e.getMessage () ) );
//        }
//		
//		return URL_PRENOTAZIONE;
//	}
	
	@RequestMapping(value=URL_PRENOTAZIONE, method=RequestMethod.POST)
	public String prenotazioneExportPost(@Valid @ModelAttribute (MODEL_PRENOTAZIONE_REPORT) PrenotazioneRicercaReportFormVO filtro, BindingResult bindingResult,
			Model model, HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(MODEL_PRENOTAZIONE_REPORT, filtro);
			model.addAttribute(MODEL_RISULTATI_RICERCA, null);
			
			return URL_PRENOTAZIONE;
		}
		else {
			request.getSession().setAttribute(MODEL_PRENOTAZIONE_REPORT, filtro);	
//			richiamare metodo prenotazione report, per ora commentato
			try {
//				List<RisultatiRicercaReportVO> reports = null;
				filtro.setTipoFile((String) request.getSession().getAttribute(Constants.FILTRO_TIPO_FILE));
				prenotazioneReportManager.inserisciPrenotazioneReport(filtro);
//				reports = prenotazioneReportManager.ricercaReport();
//				model.addAttribute(MODEL_RISULTATI_RICERCA, reports);
			} catch (OperationFailedException e) {
				 model.addAttribute(MODEL_MESSAGGIO_ERRORE,
		                    String.format ( "Si e' verificato un errore durante la prenotazione del report: %s", e.getMessage () ) );
				 
				 return URL_PRENOTAZIONE;
			}
		}
		
		try {
			List<RisultatiRicercaReportVO> reports;
			 loadComboboxesFromService(model);
			reports = prenotazioneReportManager.ricercaReport();
			model.addAttribute(MODEL_RISULTATI_RICERCA, reports);
		} catch (OperationFailedException e) {
			String.format ( "Si e' verificato un errore durante il caricamento della pagina: %s", e.getMessage () );
			 return URL_PRENOTAZIONE;
		}
		
		return "redirect:" + URL_PRENOTAZIONE;
		
		
	}
	
	@RequestMapping(URL_PULISCI)
//    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
    public String clearRicerca(Model model, HttpServletRequest request) {

        request.getSession().removeAttribute(MODEL_PRENOTAZIONE_REPORT);
        model.addAttribute(MODEL_PRENOTAZIONE_REPORT, null);
        request.getSession ().setAttribute(Constants.FILTRO_TIPO_FILE, Constants.TIPO_FILE_XLS);
        return "redirect:" + URL_PRENOTAZIONE;
    }
	
//	@RequestMapping(URL_ENTRY_PRENOTAZIONE)
////  @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
//  public String entryPrenotazioneCsv(Model model, HttpServletRequest request) {
//		
//		getFilterFieldsFromSession(request);	
//	
//
//      return "redirect:" + URL_RICERCA_PRENOTAZIONE;
//  }
	
//	@RequestMapping(URL_ENTRY_PRENOTAZIONE_EXCEL)
////  @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_FLUSSI + "')" )
//  public String entryPrenotazioneExcel(Model model, HttpServletRequest request) {
//		
//		getFilterFieldsFromSession(request);	
//	
//
//      return "redirect:" + URL_RICERCA_PRENOTAZIONE;
//  }
	
	@RequestMapping(value=URL_DOWNLOAD_REPORT, method=RequestMethod.GET)
	public void downloadReport(Model model, @PathVariable("idExport") Long idExport, HttpServletRequest request, HttpServletResponse response) throws IOException, OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
		
			FileReportVO file = prenotazioneReportManager.aggiornaStato(idExport);
			downloadReportFile(file, request, response);

	}


//	private void getFilterFieldsFromSession(HttpServletRequest request) {
//		PrenotazioneRicercaReportFormVO filtro = new PrenotazioneRicercaReportFormVO();
//
//		filtro.setIdentificativoFlusso((String) request.getSession().getAttribute(Constants.FILTRO_IDENTIFICATIVO_FLUSSO));
//		filtro.setIuv((String) request.getSession().getAttribute(Constants.FILTRO_IUV));
//		filtro.setIdCodVersamento((String) request.getSession().getAttribute(Constants.FILTRO_ID_COD_VERSAMENTO));
//		filtro.setStatoFlusso((String) request.getSession().getAttribute(Constants.FILTRO_STATO_FLUSSO));
//		filtro.setDataElaborazioneA((Date) request.getSession().getAttribute(Constants.FILTRO_DATA_ELABORAZIONE_A));
//		filtro.setDataElaborazioneDa((Date) request.getSession().getAttribute(Constants.FILTRO_DATA_ELABORAZIONE_DA));
//		filtro.setDataRicezioneA((Date) request.getSession().getAttribute(Constants.FILTRO_DATA_RICEZIONE_A));
//		filtro.setDataRicezioneDa((Date) request.getSession().getAttribute(Constants.FILTRO_DATA_RICEZIONE_DA));
//		filtro.setPsp((String) request.getSession().getAttribute(Constants.FILTRO_PSP));
//		filtro.setTipoReport((String) request.getSession().getAttribute(Constants.FILTRO_TIPO_REPORT));
////		filtro.setTipoFile((String) request.getSession().getAttribute(Constants.FILTRO_TIPO_FILE));
//		request.getSession().setAttribute(MODEL_PRENOTAZIONE_REPORT, filtro);
//		
//	}
	
	 public void loadComboboxesFromService(Model model) throws OperationFailedException {
		    model.addAttribute(MODEL_TIPO_REPORT_CONTABILE, Constants.TIPO_REPORT_CONTABILE);
		    model.addAttribute(MODEL_TIPO_REPORT_FLUSSI_COMPLETI, Constants.TIPO_REPORT_FLUSSI_COMPLETI);
	        model.addAttribute(MODEL_OPZIONI_STATO_FLUSSO, decodificheManager.getListaOpzioniStatoFlusso());
	        model.addAttribute ( MODEL_OPZIONI_CODICI_VERSAMENTO, decodificheManager.getListaOpzioniCodiciVersamento());
	 }
	 
	 public void downloadReportFile(FileReportVO file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		 response.setHeader ( CONTENT_DISPOSITION_KEY, CONTENT_DISPOSITION_VALUE + file.getNomeFile());
	     response.setHeader ( CONTENT_TRANSFER_ENCODING_KEY, CONTENT_TRANSFER_ENCODING_VALUE );
	     response.setHeader ( CONTENT_ENCODING_KEY, CONTENT_ENCODING_VALUE );
	     response.setHeader ( PRAGMA_KEY, PRAGMA_VALUE );
	     response.setHeader ( CACHE_CONTROL_KEY, CACHE_CONTROL_VALUE );
	     response.setContentType ( FORCE_DOWNLOAD );
		 
	     response.setContentLength(file.getContenutoFile().length);
	     response.getOutputStream().write(file.getContenutoFile() , 0, file.getContenutoFile().length);

	 }

}
