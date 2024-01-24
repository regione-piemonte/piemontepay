/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.CodiceVersamentoManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.EnteManager;
import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.RiferimentoContabileManager;
import it.csi.epay.epaypacatalogweb.business.manager.VoceEntrataManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.frontend.util.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.OpzioneComboVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoCollegatoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RicercaCodiceVersamentoFiltroVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RisultatoRicercaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.validators.GestioneCodiceVersamentoValidator;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.ppay.MacrotipoPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.TematicaPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import it.csi.epay.epaypacatalogweb.security.UserDetails;
import it.csi.epay.epaypacatalogweb.security.UserDetailsInfoTematica;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;


@Controller
@RequestMapping ( value = "/codici-versamento" )
public class CodiceVersamentoController extends AuthenticatedParentController {

    private static final String ALERT_NO_OPZIONI_MULTIBENEFICIARIO = "alert_no_opzioni_multibeneficiario";
    
//    private static final String ALERT_NO_OPZIONI_ENTE_SECONDARIO = "alert_no_opzioni_ente_secondario";

	private static final String PAGINA_MODIFICA = "/codice-versamento/modifica/index";

    private static final String PAGINA_NUOVO = "/codice-versamento/nuovo/index";

    private static final String PAGINA_VISUALIZZA = "/codice-versamento/visualizza/index";

    private static final String PAGINA_RICERCA = "/codice-versamento/ricerca/index";

    private static final String URL_RICERCA = "/codici-versamento/ricerca";

    private static final String URL_MODIFICA = "/codici-versamento/modifica";

    private static final String MODEL_MODIFICA_CODICE_VERSAMENTO = "modifica_codice_versamento";

    private static final String MODEL_CODICE_VERSAMENTO_PADRE = "codice_versamento_padre";

    private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_codici_versamento";

    private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

    private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

    private static final String PAGINA_MODIFICA_COLLEGATO = "/codice-versamento/collegato/index";

    private static final String PAGINA_VISUALIZZA_COLLEGATO = "/codice-versamento/visualizza-collegato/index";

    private static final String MODEL_MODIFICA_CODICE_VERSAMENTO_COLLEGATO = "modifica_codice_versamento_collegato";

    private static final String MODEL_ID_CODICE_VERSAMENTO = "id_codice_versamento";

    private static final String MODEL_CODICE_VOCE_ENTRATA = "codice_voce_entrata";

    private static final String MODEL_VISIBILITA_TOTALE = "visibilita_totale";

    private static final String SCENARIO_NUOVO = "nuovo";

    private static final String SCENARIO_MODIFICA = "modifica";

    private static final String MODALITA_VISUALIZZAZIONE = "modalita_visualizzazione";

    private static final String NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO = "pagina_origine_codice_versamento";

    private static final String NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO_PARAMETER = "source";

    private static final String NAVIGAZIONE_ORIGINE_DEFAULT = "default";

    private static final String NAVIGAZIONE_ORIGINE_MODIFICA = "modifica";

    private static final String PAGINA_MODALE_VE_RISULTATI = "/codice-versamento/modifica/modale-voci-entrata/tabella_risultati";

    private static final String MEX_COD_VERS_TIPO_PAGA_SPONT = "MEX_COD_VERS_TIPO_PAGA_SPONT";

    private static final String CODICE_MEX_COD_VERS_TIPO_PAGA_SPONT = "SUGGEST_COD_VERS_TIPO_PAGA_SPONT";

    private static final String MEX_SUGGEST_MOD_INTEGRAZIONE = "MEX_SUGGEST_MOD_INTEGRAZIONE";
    
    private static final String CODICE_MEX_SUGGEST_MOD_INTEGRAZIONE = "SUGGEST_MOD_INTEGRAZIONE";
    
    private static final String MEX_SUGGEST_TIPO_PAGAMENTO = "MEX_COD_SUGGEST_TIPO_PAGAMENTO";

    private static final String CODICE_MEX_SUGGEST_TIPO_PAGAMENTO = "SUGGEST_TIPO_PAGAMENTO";
    
    private static final String MEX_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO = "MEX_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO";

    private static final String CODICE_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_1 = "SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_1";
    
    private static final String CODICE_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_2 = "SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_2";
    
    private static final String MODEL_ENTE_SECONDARIO = "modelEnteSecondario";
    
    private static final String MODEL_COV_DA_ASSOCIARE = "modelCovDaAssociare";
    
    private static final String MODEL_MODALITA_ASSOCIAZIONE_MB = "modelModalitAssociazioneMb";
    
    
    
    private static final String URL_INSERISCI_RIFERIMENTO_CONTABILE = "/riferimenti-contabili/nuovoDaCodiceVersamento/";

    private static final String SEPARATOR = "@@@@";

    @Autowired
    private CodiceVersamentoManager codiceVersamentoManager;

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private EsportazioneManager esportazioneManager;

    @Autowired
    private VoceEntrataManager voceEntrataManager;
    
    @Autowired
    private EnteManager enteManager;
    
    @Autowired
    private RiferimentoContabileManager riferimentoContabileManager;

    @InitBinder
    protected void initBinder ( WebDataBinder binder ) {
        binder.registerCustomEditor ( String.class, new StringTrimmerEditor ( true ) );

        binder.setValidator ( new GestioneCodiceVersamentoValidator () );
        
        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );

        HttpServletRequest request = getCurrentRequest ();

        String sourceParam = request.getParameter ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO_PARAMETER );
        if ( sourceParam != null && !sourceParam.isEmpty () ) {
            request.getSession ().setAttribute ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO, sourceParam );
        }
    }
    

    @Override
    public void loadComboboxes ( Model model ) {
        List<TematicaPPayVO> tematiche = decodificheManager.getListaOpzioniTematicheVociEntrata ();
        List<MacrotipoPPayVO> macrotipi = decodificheManager.getListaOpzioniMacrotipiVociEntrata ();
        List<VoceEntrataPPayVO> vociEntrata = voceEntrataManager.getOpzioniVoceEntrata ();
        List<GenericVO> modalitaIntegrazione = decodificheManager.getListaOpzioniModalitaIntegrazione ();

        model.addAttribute ( MODEL_TEMATICHE, OpzioneComboVO.getOpzioni ( tematiche ) );
        model.addAttribute ( MODEL_MACROTIPI, OpzioneComboVO.getOpzioni ( macrotipi ) );
        model.addAttribute ( MODEL_VOCI_ENTRATA, vociEntrata );
        model.addAttribute ( MODEL_VOCI_ENTRATA + "_JS", serializeForJavascript ( vociEntrata ) );

        //FIX-INTEGRAZIONE
        model.addAttribute ( "listaOpzioniModalitaIntegrazione", modalitaIntegrazione );

        //ENG:RDI-02 2019
        model.addAttribute ( MEX_SUGGEST_TIPO_PAGAMENTO, decodificheManager.getTestoMessaggio ( CODICE_MEX_SUGGEST_TIPO_PAGAMENTO ) );
    }

    public void loadComboboxesModifica ( Model model, Long idEnteSecondario , String modalitaAssociazioneMb) {
        List<TematicaPPayVO> tematiche = decodificheManager.getListaOpzioniTematicheVociEntrataClean ();
        List<MacrotipoPPayVO> macrotipi = decodificheManager.getListaOpzioniMacrotipiVociEntrata ();
        List<GenericVO> tipiPagamento = decodificheManager.getListaOpzioniTipoPagamento ();
        List<VoceEntrataPPayVO> vociEntrata = voceEntrataManager.getOpzioniVoceEntrata ();
        List<GenericVO> modalitaIntegrazione = decodificheManager.getListaOpzioniModalitaIntegrazione ();
        List<EnteVO> entiSecondari = enteManager.getEntiConRiferimentoContabileSecondario();

        model.addAttribute ( MODEL_TEMATICHE, OpzioneComboVO.getOpzioni ( tematiche ) );
        model.addAttribute ( MODEL_MACROTIPI, OpzioneComboVO.getOpzioni ( macrotipi ) );
        model.addAttribute ( MODEL_TIPI_PAGAMENTO, tipiPagamento );
        model.addAttribute ( MODEL_ENTE_SECONDARIO, entiSecondari );
        if (null!= idEnteSecondario)
        {
					model.addAttribute ( MODEL_COV_DA_ASSOCIARE, decodificheManager.ricercaCodiceVersamentoRifContabileSecondario(idEnteSecondario));
        }
//       
        model.addAttribute ( MODEL_VOCI_ENTRATA, vociEntrata );
        model.addAttribute ( MODEL_VOCI_ENTRATA + "_JS", serializeForJavascript ( vociEntrata ) );

        //FIX-INTEGRAZIONE
        model.addAttribute ( "listaOpzioniModalitaIntegrazione", modalitaIntegrazione);

        //ENG:RDI-046
        model.addAttribute ( MEX_COD_VERS_TIPO_PAGA_SPONT, decodificheManager.getTestoMessaggio ( CODICE_MEX_COD_VERS_TIPO_PAGA_SPONT ) );

        //ENG:RDI-02 2019
        model.addAttribute ( MEX_SUGGEST_TIPO_PAGAMENTO, decodificheManager.getTestoMessaggio ( CODICE_MEX_SUGGEST_TIPO_PAGAMENTO ) );

        //ENG:RDI-02 2019
        model.addAttribute ( MEX_SUGGEST_MOD_INTEGRAZIONE, decodificheManager.getTestoMessaggio ( CODICE_MEX_SUGGEST_MOD_INTEGRAZIONE ) );
        
      //ENG:RDI-45 2021
        model.addAttribute ( MEX_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO, 
        		decodificheManager.getTestoMessaggio ( CODICE_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_1 ) +"\r\n"
        		+ decodificheManager.getTestoMessaggio ( CODICE_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_2 )
        		);
        
        model.addAttribute ( MODEL_MODALITA_ASSOCIAZIONE_MB, creaAssociazioneModalitaMb(modalitaAssociazioneMb));
    }

	

	public void loadComboboxesModificaCollegato ( Model model, Long idEnteSecondario , String modalitaAssociazioneMb ) {
		List<EnteVO> entiSecondari = enteManager.getEntiConRiferimentoContabileSecondario();
		
		 //ENG:RDI-45 2021
		 model.addAttribute ( MODEL_ENTE_SECONDARIO, entiSecondari );
	        if (null!= idEnteSecondario)
	        {
					model.addAttribute ( MODEL_COV_DA_ASSOCIARE, decodificheManager.ricercaCodiceVersamentoRifContabileSecondario(idEnteSecondario));
	        }
	     
	        model.addAttribute ( MEX_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO, 
	        		decodificheManager.getTestoMessaggio ( CODICE_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_1 ) +"\r\n"
	        		+ decodificheManager.getTestoMessaggio ( CODICE_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO_2 )
	        		);
	        
	        model.addAttribute ( MODEL_MODALITA_ASSOCIAZIONE_MB, creaAssociazioneModalitaMb(modalitaAssociazioneMb));
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( "/pulisci" )
    public String clearRicerca ( Model model, HttpServletRequest request ) {
        request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
        model.addAttribute ( MODEL_FILTRO_RICERCA, null );
        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( "/ricerca" )
    public String viewRicerca (
        Model model,
        HttpServletRequest request ) {
        List<RisultatoRicercaCodiceVersamentoVO> list;

        authorizationManager.require ( CodiceVersamentoVO.class, EntityAction.LIST );

        RicercaCodiceVersamentoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaCodiceVersamentoFiltroVO.class );

        if ( filtro == null ) {
            model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaCodiceVersamentoFiltroVO () );
            model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
        } else {
            model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
            try {
                UserDetails user = SecurityUtils.getUser ();

                list = codiceVersamentoManager.ricercaCodiciVersamento ( filtro );

                for ( RisultatoRicercaCodiceVersamentoVO elemento: list ) {
                    elemento.setInserimentoConsentito ( false );

                    for ( UserDetailsInfoTematica tematicaVisibile: user.getTematiche () ) {
                        if ( tematicaVisibile.getCodice ().equals ( elemento.getCodiceTematica () ) ) {
                            if ( tematicaVisibile.getFlagVisibilitaTotale () != null && tematicaVisibile.getFlagVisibilitaTotale () ) {
                                elemento.setInserimentoConsentito ( true );
                            }
                            break;
                        }
                    }

                    if(elemento.getTematicaEsclusa() != null && elemento.getTematicaEsclusa()) {
                        elemento.setInserimentoConsentito ( false );
                    }
                }

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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
    public String doRicerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaCodiceVersamentoFiltroVO filtro, BindingResult bindingResult,
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

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.ELIMINA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/elimina/{id}", method = RequestMethod.GET )
    public String elimina ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes ) {

        authorizationManager.require ( CodiceVersamentoVO.class, id, EntityAction.DELETE );

        try {
            codiceVersamentoManager.eliminaCodiceVersamento ( id );
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
        } catch ( RuntimeException | OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
        } catch ( Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
        }

        return "redirect:" + URL_RICERCA;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( "/esporta-excel" )
    public void esportaXlsx ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        authorizationManager.require ( CodiceVersamentoVO.class, EntityAction.LIST );

        List<RisultatoRicercaCodiceVersamentoVO> list;

        RicercaCodiceVersamentoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaCodiceVersamentoFiltroVO.class );

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                list = codiceVersamentoManager.ricercaCodiciVersamento ( filtro );
            } catch ( OperationFailedException e ) {
                throw new RuntimeException ( e );
            }
            esportazioneManager.esportaCodiciVersamentoXlsx ( response, list );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( "/esporta-csv" )
    public void esportaCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

        authorizationManager.require ( CodiceVersamentoVO.class, EntityAction.LIST );

        List<RisultatoRicercaCodiceVersamentoVO> list;

        RicercaCodiceVersamentoFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaCodiceVersamentoFiltroVO.class );

        if ( filtro == null ) {
            throw new NotAllowedException ();
        } else {
            try {
                list = codiceVersamentoManager.ricercaCodiciVersamento ( filtro );
            } catch ( OperationFailedException e ) {
                throw new RuntimeException ( e );
            }
            esportazioneManager.esportaCodiciVersamentoCsv ( response, list );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/modifica/{id}", method = RequestMethod.GET )
    public String viewModificaCodiceVersamento ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request,
        RedirectAttributes redirectAttributes ) {

        authorizationManager.require ( CodiceVersamentoVO.class, id, EntityAction.WRITE );

        try {
            popolaModelModifica ( model, id, null, SCENARIO_MODIFICA, null );
            return PAGINA_MODIFICA;
        } catch ( OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/visualizza/{id}", method = RequestMethod.GET )
    public String viewVisualizzaCodiceVersamento ( @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request,
        RedirectAttributes redirectAttributes ) {

        authorizationManager.require ( CodiceVersamentoVO.class, id, EntityAction.WRITE );

        try {
            popolaModelModifica ( model, id, null, SCENARIO_MODIFICA, null );
            model.addAttribute ( MODALITA_VISUALIZZAZIONE, true );
            return PAGINA_VISUALIZZA;
        } catch ( OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/nuovo", method = RequestMethod.GET )
    public String viewNuovoCodiceVersamento ( Model model, HttpServletRequest request ) {

        try {
            popolaModelModifica ( model, null, null, SCENARIO_NUOVO, null );
            //ENG:RDI-046
            return PAGINA_NUOVO;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/inserisci/{codice}", method = RequestMethod.GET )
    public String viewInserisciCodiceVersamento ( @PathVariable ( "codice" ) String codiceVoceEntrata,
        Model model, HttpServletRequest request ) {

        try {
            popolaModelModifica ( model, null, null, SCENARIO_NUOVO, codiceVoceEntrata );
            //ENG:RDI-046
            return PAGINA_NUOVO;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/modifica/{id}", method = RequestMethod.POST )
    public String doModificaCodiceVersamento (
        @PathVariable ( "id" ) Long id,
        @Valid @ModelAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO ) ModificaCodiceVersamentoVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        authorizationManager.require ( CodiceVersamentoVO.class, id, EntityAction.WRITE );

        boolean rimaniSuModifica;

        if ( bindingResult.hasErrors () ) {
            rimaniSuModifica = true;
        } else {
            try {
                
                if (!StringUtils.isEmpty ( userInput.getModalitaAssociazioneMultibeneficiario ()  )
                                && TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals ( userInput.getModalitaAssociazioneMultibeneficiario () )
                                && 
                                riferimentoContabileManager.verificaNumeroRiferimentiContabiliInVitaPerCov( userInput.getId ())>1)
                {
                    popolaModelModifica ( model, id, userInput, SCENARIO_MODIFICA, null );
                    model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format (
                        Messages.ERROR_SAVING_CHANGES, Messages.RIFERIMENTO_CONTABILE_IN_VITA ) );
                    return PAGINA_MODIFICA;
                }

                
                AggiornaCodiceVersamentoOutput serviceOutput = codiceVersamentoManager.aggiornaVersamento ( userInput );

				if ( serviceOutput.getMessaggi () != null && serviceOutput.getMessaggi ().size () > 0 ) {
					redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO,
									concatenaMessaggi ( serviceOutput.getMessaggi () ) );
				} else {
					String messaggio = Messages.SAVED_SUCCESSFULLY;
					messaggio += SEPARATOR + Messages.ALERT_MODAL_INS_COD_VER_2;
					redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, messaggio );
				}
//                	 
                rimaniSuModifica = false;

            } catch ( RuntimeException | OperationFailedException e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
                rimaniSuModifica = true;
            } catch ( Exception e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
                rimaniSuModifica = true;
            }
//            rimaniSuModifica = true;
        }

        if ( rimaniSuModifica ) {
            popolaModelModifica ( model, id, userInput, SCENARIO_MODIFICA, null );

            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_codice_versamento";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            return PAGINA_MODIFICA;
        } else {
            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/nuovo", method = RequestMethod.POST )
    public String doNuovoCodiceVersamento (
        @Valid @ModelAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO ) ModificaCodiceVersamentoVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        Long id = null;

        boolean rimaniSuModifica;

	    if ( bindingResult.hasErrors () ) {
            rimaniSuModifica = true;
        } else {
            try {
                InserisciCodiceVersamentoOutput serviceOutput = codiceVersamentoManager.inserisciCodiceVersamento ( userInput );
                rimaniSuModifica = false;
                if (!Boolean.TRUE.equals ( userInput.getFlagElementiMultibeneficiario() ))
                {
                	if ( serviceOutput.getMessaggi () != null && serviceOutput.getMessaggi ().size () > 0 ) {
                        redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO,
                            concatenaMessaggi ( serviceOutput.getMessaggi () ) );
                    } else {
                        redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
                    }
                }
                else {
                	return "redirect:" + "/riferimenti-contabili/nuovoDaCodiceVersamento/serviceOutput.getRisultatoInserimento().getId()";
                }

            } catch ( RuntimeException | OperationFailedException e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
                rimaniSuModifica = true;
            } catch ( Exception e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
                rimaniSuModifica = true;
            }
        }

        if ( rimaniSuModifica ) {
            popolaModelModifica ( model, id, userInput, SCENARIO_NUOVO, null );

            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_codice_versamento";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            //ENG:RDI-046
            return PAGINA_NUOVO;
        } else {
            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/inserisci", method = RequestMethod.POST )
    public String doInserisciCodiceVersamento (
        @Valid @ModelAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO ) ModificaCodiceVersamentoVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        Long id = null;

        String codiceVoceEntrata = userInput.getCodiceVoceEntrata ();

        boolean rimaniSuModifica;
//        boolean alertMultibeneficiario;

        if ( bindingResult.hasErrors () ) {
        	if (null != bindingResult.getFieldError("modalitaAssociazioneMultibeneficiario"))
        	{
        		 model.addAttribute ( ALERT_NO_OPZIONI_MULTIBENEFICIARIO,
                         true );
        	}
        	
            rimaniSuModifica = true;
        } else {
            try {
                userInput.setCodiceVoceEntrata ( codiceVoceEntrata );

                InserisciCodiceVersamentoOutput serviceOutput = codiceVersamentoManager.inserisciCodiceVersamento ( userInput );
                //EPAY-99
                if (!Boolean.TRUE.equals ( userInput.getFlagElementiMultibeneficiario() ))
                {
                	 String messaggio;
                     if ( serviceOutput.getMessaggi () != null && serviceOutput.getMessaggi ().size () > 0 ) {
                         messaggio = concatenaMessaggi ( serviceOutput.getMessaggi () );
                     } else {
                         messaggio = Messages.SAVED_SUCCESSFULLY;
                     }
                     messaggio += SEPARATOR + Messages.ALERT_MODAL_INS_COD_VER_2;
                     redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, messaggio);

                     rimaniSuModifica = false;
                }
                else {
                	return "redirect:" +URL_INSERISCI_RIFERIMENTO_CONTABILE+serviceOutput.getRisultatoInserimento().getId();
                }

            } catch ( RuntimeException | OperationFailedException e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
                rimaniSuModifica = true;
            } catch ( Exception e ) {
                model.addAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
                rimaniSuModifica = true;
            }
        }

        if ( rimaniSuModifica ) {
            popolaModelModifica ( model, id, userInput, SCENARIO_NUOVO, codiceVoceEntrata );

            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_codice_versamento";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            //ENG:RDI-046
            return PAGINA_NUOVO;
        } else {
            return "redirect:" + URL_RICERCA;
        }
    }

    private void popolaModelModifica ( Model model, Long id, ModificaCodiceVersamentoVO modelCorrente, String scenario, String codiceVoceEntrata )
                    throws OperationFailedException {

        CodiceVersamentoVO modelMerged;

        boolean nuovoSenzaCodice = ( id == null && modelCorrente == null && codiceVoceEntrata == null );

        if ( id == null ) {
            if ( codiceVoceEntrata == null ) {
                // sto inserendo un elemento senza dati di partenza
                if ( modelCorrente != null ) {
                    modelMerged = codiceVersamentoManager.mergeInputUtente ( null, modelCorrente );
                } else {
                    modelMerged = codiceVersamentoManager.istanziaNuovo ();
                }
            } else {
                // sto inserendo sapendo il codice voce entrata
                if ( modelCorrente != null ) {
                    modelMerged = codiceVersamentoManager.mergeInputUtente ( null, modelCorrente, codiceVoceEntrata );
                } else {
                    modelMerged = codiceVersamentoManager.istanziaNuovo ( codiceVoceEntrata );
                }
            }
        } else {
            // sto modificando un elemento gia' esistente
            if ( modelCorrente == null ) {
                modelMerged = codiceVersamentoManager.getCodiceVersamento ( id );
            } else {
                modelMerged = codiceVersamentoManager.mergeInputUtente ( id, modelCorrente );
            }
        }

        UserDetails user = SecurityUtils.getUser ();

        if ( nuovoSenzaCodice ) {
            model.addAttribute ( MODEL_VISIBILITA_TOTALE, null );
            model.addAttribute ( MODEL_CODICE_VOCE_ENTRATA, null );
        } else {
            model.addAttribute ( MODEL_VISIBILITA_TOTALE,
                modelMerged.getCodiceTematica () != null && user.haVisibilitaTotaleSuTematica ( modelMerged.getCodiceTematica () ) );
            model.addAttribute ( MODEL_CODICE_VOCE_ENTRATA, codiceVoceEntrata );
        }

        model.addAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO, modelMerged );
        model.addAttribute ( MODEL_SCENARIO, scenario );
        model.addAttribute ( MODEL_ID_CODICE_VERSAMENTO, id );
        loadComboboxesModifica ( model, modelMerged.getEnteSecondarioAssociazioneMultibeneficiario(), modelMerged.getModalitaAssociazioneMultibeneficiarioOld () );

        model.addAttribute ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO, getCurrentSession ().getAttribute ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO ) );
        
       
    }

    private  List<GenericVO> creaAssociazioneModalitaMb ( String modalitaAssociazioneMultibeneficiarioOld ) {
        
        List<GenericVO> modalita = new LinkedList<GenericVO>();
        
        if (StringUtils.isEmpty ( modalitaAssociazioneMultibeneficiarioOld ))
        {
            GenericVO modalita1= new GenericVO (new Long(TipoAssociazioneMultibeneficiario.SECONDARIO.getId ()), "", TipoAssociazioneMultibeneficiario.SECONDARIO.getCode () );
            modalita.add ( modalita1 );
            
            GenericVO modalita2= new GenericVO (new Long(TipoAssociazioneMultibeneficiario.PRIMARIO.getId ()), "", TipoAssociazioneMultibeneficiario.PRIMARIO.getCode () );
            modalita.add ( modalita2 );
            
        }
        else if(TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals ( modalitaAssociazioneMultibeneficiarioOld ))
        {
            GenericVO modalita1= new GenericVO (new Long(TipoAssociazioneMultibeneficiario.SECONDARIO.getId ()), "", TipoAssociazioneMultibeneficiario.SECONDARIO.getCode () );
            modalita.add ( modalita1 );
            
        }
        else if(TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals ( modalitaAssociazioneMultibeneficiarioOld ))
        {
            GenericVO modalita1= new GenericVO (new Long(TipoAssociazioneMultibeneficiario.PRIMARIO.getId ()), "", TipoAssociazioneMultibeneficiario.PRIMARIO.getCode () );
            modalita.add ( modalita1 );
            
        }
        return modalita;
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.ELIMINA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/{idCodiceVersamento}/codici-versamento-collegati/elimina/{id}", method = RequestMethod.GET )
    public String eliminaCollegato (
        @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        @PathVariable ( "id" ) Long id,
        Model model, HttpServletRequest request,
        RedirectAttributes redirectAttributes ) {

        authorizationManager.require ( CodiceVersamentoVO.class, idCodiceVersamento, EntityAction.DELETE );

        try {
            codiceVersamentoManager.eliminaCodiceVersamentoCollegato ( idCodiceVersamento, id );
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
        } catch ( RuntimeException | OperationFailedException e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
        } catch ( Exception e ) {
            redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
        }

        if ( getOrigine ().equals ( NAVIGAZIONE_ORIGINE_MODIFICA ) ) {
            return "redirect:" + URL_MODIFICA + "/" + idCodiceVersamento;
        } else {
            return "redirect:" + URL_RICERCA;
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/{idCodiceVersamento}/codici-versamento-collegati/modifica/{id}", method = RequestMethod.GET )
    public String viewModificaCodiceVersamentoCollegato (
        @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request ) {

        authorizationManager.require ( CodiceVersamentoVO.class, idCodiceVersamento, EntityAction.WRITE );

        try {
            popolaModelModificaCollegato ( model, idCodiceVersamento, id, null, SCENARIO_MODIFICA );
            return PAGINA_MODIFICA_COLLEGATO;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/{idCodiceVersamento}/codici-versamento-collegati/visualizza/{id}", method = RequestMethod.GET )
    public String viewVisualizzaCodiceVersamentoCollegato (
        @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        @PathVariable ( "id" ) Long id, Model model, HttpServletRequest request ) {

        authorizationManager.require ( CodiceVersamentoVO.class, idCodiceVersamento, EntityAction.WRITE );

        try {
            popolaModelModificaCollegato ( model, idCodiceVersamento, id, null, SCENARIO_MODIFICA );
            model.addAttribute ( MODALITA_VISUALIZZAZIONE, true );
            return PAGINA_VISUALIZZA_COLLEGATO;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/{idCodiceVersamento}/codici-versamento-collegati/nuovo", method = RequestMethod.GET )
    public String viewNuovoCodiceVersamentoCollegato (
        @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        Model model, HttpServletRequest request ) {

        try {
            popolaModelModificaCollegato ( model, idCodiceVersamento, null, null, SCENARIO_NUOVO );
            return PAGINA_MODIFICA_COLLEGATO;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/{idCodiceVersamento}/codici-versamento-collegati/modifica/{id}", method = RequestMethod.POST )
    public String doModificaCodiceVersamentoCollegato (
        @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        @PathVariable ( "id" ) Long id,
        @Valid @ModelAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO_COLLEGATO ) ModificaCodiceVersamentoCollegatoVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        authorizationManager.require ( CodiceVersamentoVO.class, idCodiceVersamento, EntityAction.WRITE );

        if ( bindingResult.hasErrors () ) {
            popolaModelModificaCollegato ( model, idCodiceVersamento, id, userInput, SCENARIO_MODIFICA );
            // model.addAllAttributes ( bindingResult.getModel () );
            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_codice_versamento_collegato";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            return PAGINA_MODIFICA_COLLEGATO;
        } else {
            try {
                codiceVersamentoManager.aggiornaCodiceVersamentoCollegato ( idCodiceVersamento, userInput );
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
            } catch ( RuntimeException | OperationFailedException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( Exception e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
            }

            if ( getOrigine ().equals ( NAVIGAZIONE_ORIGINE_MODIFICA ) ) {
                return "redirect:" + URL_MODIFICA + "/" + idCodiceVersamento;
            } else {
                return "redirect:" + URL_RICERCA;
            }
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( value = "/{idCodiceVersamento}/codici-versamento-collegati/nuovo", method = RequestMethod.POST )
    public String doNuovoCodiceVersamentoCollegato (
        @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        @Valid @ModelAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO_COLLEGATO ) ModificaCodiceVersamentoCollegatoVO userInput,
        BindingResult bindingResult,
        Model model,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes ) throws OperationFailedException {

        Long id = null;

        authorizationManager.require ( CodiceVersamentoVO.class, idCodiceVersamento, EntityAction.WRITE );

        if ( bindingResult.hasErrors () ) {
            popolaModelModificaCollegato ( model, idCodiceVersamento, id, userInput, SCENARIO_NUOVO );
            // model.addAllAttributes ( bindingResult.getModel () );
            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_codice_versamento_collegato";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            return PAGINA_MODIFICA_COLLEGATO;
        } else {
            try {
                InserisciCodiceVersamentoOutput serviceOutput= codiceVersamentoManager.inserisciCodiceVersamentoCollegato ( idCodiceVersamento, userInput );
                if (!Boolean.TRUE.equals ( userInput.getFlagElementiMultibeneficiario() )) {
                    String messaggio = Messages.SAVED_SUCCESSFULLY;
                    messaggio += SEPARATOR + Messages.ALERT_MODAL_INS_COD_VER_2;
                    redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, messaggio );
                } else {
                    return "redirect:" +URL_INSERISCI_RIFERIMENTO_CONTABILE+serviceOutput.getRisultatoInserimento().getId();
                }
            } catch ( RuntimeException | OperationFailedException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( Exception e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
            }

            if ( getOrigine ().equals ( NAVIGAZIONE_ORIGINE_MODIFICA ) ) {
                return "redirect:" + URL_MODIFICA + "/" + idCodiceVersamento;
            } else {
                return "redirect:" + URL_RICERCA;
            }
        }
    }

    private void popolaModelModificaCollegato ( Model model, Long idCodiceVersamento, Long id, ModificaCodiceVersamentoCollegatoVO modelCorrente,
        String scenario ) throws OperationFailedException {

        CodiceVersamentoVO modelMerged;

        if ( id == null ) {
            if ( modelCorrente != null ) {
                modelMerged = codiceVersamentoManager.mergeInputUtenteCollegato ( idCodiceVersamento, null, modelCorrente );
            } else {
                modelMerged = codiceVersamentoManager.istanziaNuovoCollegato ( idCodiceVersamento );
            }
        } else {
            if ( modelCorrente == null ) {
                modelMerged = codiceVersamentoManager.getCodiceVersamentoCollegato ( idCodiceVersamento, id );
            } else {
                modelMerged = codiceVersamentoManager.mergeInputUtenteCollegato ( idCodiceVersamento, id, modelCorrente );
            }
        }

        model.addAttribute ( MODEL_ID_CODICE_VERSAMENTO, idCodiceVersamento );
        model.addAttribute ( MODEL_CODICE_VERSAMENTO_PADRE, modelMerged.getCodiceVersamentoPadre () );
        model.addAttribute ( MODEL_MODIFICA_CODICE_VERSAMENTO_COLLEGATO, modelMerged );
        model.addAttribute ( MODEL_SCENARIO, scenario );
        loadComboboxesModificaCollegato ( model, modelMerged.getEnteSecondarioAssociazioneMultibeneficiario(), modelMerged.getModalitaAssociazioneMultibeneficiarioOld () );

        model.addAttribute ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO, getCurrentSession ().getAttribute ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO ) );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( "/{idCodiceVersamento}/codici-versamento-collegati/esporta-excel" )
    public void esportaCollegatiXlsx ( @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        HttpServletResponse response, HttpServletRequest request ) throws IOException, OperationFailedException {

        authorizationManager.require ( CodiceVersamentoVO.class, EntityAction.LIST );

        CodiceVersamentoVO current = codiceVersamentoManager.getCodiceVersamento ( idCodiceVersamento );

        esportazioneManager.esportaCodiciVersamentoCollegatiXlsx ( response, current, current.getCodiciVersamentoCollegati () );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO + "')" )
    @RequestMapping ( "/{idCodiceVersamento}/codici-versamento-collegati/esporta-csv" )
    public void esportaCollegatiCsv ( @PathVariable ( "idCodiceVersamento" ) Long idCodiceVersamento,
        HttpServletResponse response, HttpServletRequest request ) throws IOException, OperationFailedException {

        authorizationManager.require ( CodiceVersamentoVO.class, EntityAction.LIST );

        CodiceVersamentoVO current = codiceVersamentoManager.getCodiceVersamento ( idCodiceVersamento );

        esportazioneManager.esportaCodiciVersamentoCollegatiCsv ( response, current, current.getCodiciVersamentoCollegati () );
    }

    private String getOrigine () {
        String raw = (String) getCurrentSession ().getAttribute ( NAVIGAZIONE_ORIGINE_CODICE_VERSAMENTO );
        if ( raw == null || raw.isEmpty () ) {
            return NAVIGAZIONE_ORIGINE_DEFAULT;
        } else {
            return raw;
        }
    }

    @RequestMapping ( value = "/json/ricerca-voci-entrata-disponibili", method = RequestMethod.GET )
    public @ResponseBody List<VoceEntrataPPayVO> ricercaVociEntrataDisponibiliJson (
        @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaVoceEntrataPPayFiltroVO filtro,
        BindingResult bindingResult ) {

        if ( bindingResult.hasErrors () ) {
            throw new RuntimeException ( "Parametri non validi" );
        }

        try {
            return voceEntrataManager.ricercaVoceEntrataDisponibile ( filtro );
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( "Errore nella ricerca", e );
        }

    }

    @RequestMapping ( value = "/ajax/ricerca-voci-entrata-disponibili", method = RequestMethod.GET )
    public String ricercaVociEntrataDisponibiliAjax ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaVoceEntrataPPayFiltroVO filtro,
        BindingResult bindingResult, Model model ) {

        if ( bindingResult.hasErrors () ) {
            throw new RuntimeException ( "Parametri non validi" );
        }

        try {
            List<VoceEntrataPPayVO> risultati = voceEntrataManager.ricercaVoceEntrataDisponibile ( filtro );

            model.addAttribute ( "lista_risultati_ve", risultati );
            model.addAttribute ( "lista_risultati_ve_vuota", risultati == null || risultati.size () < 1 );

            return PAGINA_MODALE_VE_RISULTATI;

        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( "Errore nella ricerca", e );
        }

    }

    @RequestMapping ( value = "/ajax/ricerca-codici-versamento-da-associare", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GenericVO> ricercaCodiciVersamentoDaAssociare (@RequestParam Long idEnte) {

       
        try {
        	return decodificheManager.ricercaCodiceVersamentoRifContabileSecondario(idEnte);
        } catch ( Exception e ) {
            throw new RuntimeException ( "Errore nella ricerca", e );
        }

    }

}
