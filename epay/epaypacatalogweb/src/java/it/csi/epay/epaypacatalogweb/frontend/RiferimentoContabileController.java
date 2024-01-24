/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.CodiceVersamentoManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.RiferimentoContabileManager;
import it.csi.epay.epaypacatalogweb.business.manager.TassonomiaManager;
import it.csi.epay.epaypacatalogweb.business.manager.VoceEntrataManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.frontend.util.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.OpzioneComboVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.OpzioniCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.ppay.MacrotipoPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.TematicaPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ChiudiRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ModificaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RicercaRiferimentoContabileFiltroVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileStoricoVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.validators.GestioneRiferimentoContabileValidator;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping ( value = "/riferimenti-contabili" )
public class RiferimentoContabileController extends AuthenticatedParentController {

	private static final String PAGINA_MODIFICA = "/riferimento-contabile/modifica/index";

	private static final String PAGINA_VISUALIZZA = "/riferimento-contabile/visualizza/index";

	private static final String PAGINA_RICERCA = "/riferimento-contabile/ricerca/index";

	private static final String PAGINA_CHIUDI = "/riferimento-contabile/chiusura/index";
	private static final String PAGINA_DUPLICA = "/riferimenti-contabili/duplica/";

	private static final String URL_RICERCA = "/riferimenti-contabili/ricerca";

	private static final String MODEL_MODIFICA_RIFERIMENTO_CONTABILE = "modifica_riferimento_contabile";

	private static final String MODEL_DESCRIZIONE_DATO_SPECIFICO_RISCOSSIONE = "model_descrizione_dato_specifico_riscossione";

	private static final String MODEL_CHIUDI_RIFERIMENTO_CONTABILE = "chiudi_riferimento_contabile";

	private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_riferimenti_contabili";

	private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

	private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

	private static final String MODEL_ID_RIFERIMENTO_CONTABILE = "id_riferimento_contabile";

	private static final String SCENARIO_NUOVO = "nuovo";

	private static final String SCENARIO_NUOVO_DA_CODICE_VERSAMENTO = "nuovoDaCodiceVersamento";

	private static final String SCENARIO_MODIFICA = "modifica";

	private static final String SCENARIO_CHIUSURA = "chiusura";

	private static final String SCENARIO_DUPLICA = "duplica";

	private static final String SCENARIO_ALT = "scenario_alt";

	private static final String MODEL_CODICE_VERSAMENTO_DEFAULT = "codice_versamento_default";

	private static final String MODEL_TIPOLOGIA_DATO_SPECIFICO_RISCOSSIONE = "tipologia_dato_specifico_riscossione";

	private static final String TIPOLOGIA_DATO_SPECIFICO_RISCOSSIONE_ENTE = "Ente";

	private static final String MESSAGGIO_SUGGEST_RIFC_DDSR = "MESSAGGIO_SUGGEST_RIFC_DDSR";

	private static final String CODICE_MESSAGGIO_SUGGEST_RIFC_DDSR = "SUGGEST_RIFC_DDSR";

	public static final String MODEL_RIFERIMENTO_CONTABILE_SECONDARIO = "modelRiferimentoContabileSecondario";


	@Autowired
	private RiferimentoContabileManager riferimentoContabileManager;

	@Autowired
	private DecodificheManager decodificheManager;

	@Autowired
	private AuthorizationManager authorizationManager;

	@Autowired
	private EsportazioneManager esportazioneManager;

	@Autowired
	private VoceEntrataManager voceEntrataManager;

	@Autowired
	private CodiceVersamentoManager codiceVersamentoManager;

	@Autowired
	private TassonomiaManager tassonomiaManager;

	private static final String MEX_SUGGEST_CODICE_TRIBUTO = "MEX_SUGGEST_CODICE_TRIBUTO";

	private static final String CODICE_MEX_SUGGEST_CODICE_TRIBUTO = "SUGGEST_CODICE_TRIBUTO";

	@InitBinder
	protected void initBinder ( WebDataBinder binder ) {
		binder.registerCustomEditor ( String.class, new StringTrimmerEditor ( true ) );

		binder.setValidator ( new GestioneRiferimentoContabileValidator () );

		SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
		dateFormat.setLenient ( false );
		binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
	}

	@Override
	public void loadComboboxes ( Model model ) {
		List<OpzioniCodiceVersamentoVO> cv = decodificheManager.getListaOpzioniCodiceVersamento ( false );
		model.addAttribute ( MODEL_CODICI_VERSAMENTO, cv );
		model.addAttribute ( MODEL_CODICI_VERSAMENTO + MODEL_AS_JSON_POSTFIX, serializeForJavascript ( cv ) );

		List<TematicaPPayVO> tematiche = decodificheManager.getListaOpzioniTematicheVociEntrata ();
		List<MacrotipoPPayVO> macrotipi = decodificheManager.getListaOpzioniMacrotipiVociEntrata ();
		model.addAttribute ( MODEL_TEMATICHE, OpzioneComboVO.getOpzioni ( tematiche ) );
		model.addAttribute ( MODEL_MACROTIPI, OpzioneComboVO.getOpzioni ( macrotipi ) );

		List<VoceEntrataPPayVO> vociEntrata = voceEntrataManager.getOpzioniVoceEntrata ();
		model.addAttribute ( MODEL_VOCI_ENTRATA, vociEntrata );
		model.addAttribute ( MODEL_VOCI_ENTRATA + MODEL_AS_JSON_POSTFIX, serializeForJavascript ( vociEntrata ) );
		model.addAttribute ( MEX_SUGGEST_CODICE_TRIBUTO, "Da compilare qualora il pagamento fosse riferito al tributo TARI-TEFA"/*decodificheManager.getTestoMessaggio ( CODICE_MEX_SUGGEST_CODICE_TRIBUTO )*/ );

	}

	public void loadComboboxesModifica ( Model model ) throws OperationFailedException {
		List<OpzioniCodiceVersamentoVO> cv = decodificheManager.getListaOpzioniCodiceVersamento ( false );

		List<GenericVO> tipologiaDatoSpecificoRiscossioneList = decodificheManager.getListaOpzioniTipologiaDatoSpecificoRiscossioneRiferimentoContabile ();
		String idTipologiaDatoSpecificoRiscossione = "";
		for ( GenericVO tipologiaDatoSpecificoRiscossione: tipologiaDatoSpecificoRiscossioneList ) {
			if ( tipologiaDatoSpecificoRiscossione.getDescrizione ().equals ( TIPOLOGIA_DATO_SPECIFICO_RISCOSSIONE_ENTE ) ) {
				idTipologiaDatoSpecificoRiscossione = tipologiaDatoSpecificoRiscossione.getCodice ();
			}
		}

		model.addAttribute ( MODEL_CODICI_VERSAMENTO, cv );
		model.addAttribute ( MODEL_CODICI_VERSAMENTO + MODEL_AS_JSON_POSTFIX, serializeForJavascript ( cv ) );
		model.addAttribute ( MODEL_TIPOLOGIE_DATI_SINGOLA_RISCOSSIONE, tipologiaDatoSpecificoRiscossioneList );
		model.addAttribute ( MODEL_TIPOLOGIA_DATO_SPECIFICO_RISCOSSIONE, idTipologiaDatoSpecificoRiscossione );
		model.addAttribute ( MESSAGGIO_SUGGEST_RIFC_DDSR, decodificheManager.getTestoMessaggio ( CODICE_MESSAGGIO_SUGGEST_RIFC_DDSR ) );
		List<TassonomiaVO> ts = getDatoSpecificoRiscossioneComboBox ();
		model.addAttribute ( MODEL_DESCRIZIONE_DATO_SPECIFICO_RISCOSSIONE, ts );
		model.addAttribute ( MODEL_DESCRIZIONE_DATO_SPECIFICO_RISCOSSIONE + MODEL_AS_JSON_POSTFIX, serializeForJavascript ( ts ) );
		model.addAttribute ( MEX_SUGGEST_CODICE_TRIBUTO, "La compilazione del campo e' obbligatoria per pagamento riferito al tributo TARI-TEFA"/*decodificheManager.getTestoMessaggio ( CODICE_MEX_SUGGEST_CODICE_TRIBUTO )*/ );
	}

    private List<TassonomiaVO> getDatoSpecificoRiscossioneComboBox () throws OperationFailedException {
        List<TassonomiaVO> ts = tassonomiaManager.getElencoTassonomiaPerCodiceTipoEnteCreditore ();
        if (CollectionUtils.isEmpty ( ts ))
        {
            throw new OperationFailedException ("Impossibile reperire la tassonomia");
        }
		
		Comparator<TassonomiaVO > comparator = new Comparator<TassonomiaVO>() {
            @Override
            public int compare(TassonomiaVO o1, TassonomiaVO o2) {
                return o1.getTipoServizio ().compareTo(o2.getTipoServizio ());
            }
        };
        
        if ( !CollectionUtils.isEmpty ( ts ) ) {
            ts.sort ( comparator );
        }
        return ts;
    }

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( "/pulisci" )
	public String clearRicerca ( Model model, HttpServletRequest request ) {
		request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
		model.addAttribute ( MODEL_FILTRO_RICERCA, null );
		return "redirect:" + URL_RICERCA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( "/ricerca" )
	public String viewRicerca (
		Model model,
		HttpServletRequest request ) {
		List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> list;

		authorizationManager.require ( RiferimentoContabileVO.class, EntityAction.LIST );

		RicercaRiferimentoContabileFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaRiferimentoContabileFiltroVO.class );

		if ( filtro == null ) {
			model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaRiferimentoContabileFiltroVO () );
			model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
		} else {
			model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
			try {
				list = riferimentoContabileManager.ricerca ( filtro );

				// flatten list
				List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> flatList = new ArrayList<> ();
				for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO item: list ) {
					flatList.add ( item );
					if ( item.getCodiciVersamentoCollegati () != null ) {
						flatList.addAll ( item.getCodiciVersamentoCollegati () );
						item.getCodiciVersamentoCollegati ().clear ();
					}
				}

				for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO gruppo: flatList ) {

					List<RisultatoRicercaRiferimentoContabileVO> allRiferimentiGruppoIncludingStorico = new ArrayList<> ();

					for ( RisultatoRicercaRiferimentoContabileVO rc: gruppo.getRiferimentiContabili () ) {
						allRiferimentiGruppoIncludingStorico.add ( rc );
						if ( rc.getStorico () != null && !rc.getStorico ().isEmpty () ) {
							for ( RisultatoRicercaRiferimentoContabileStoricoVO voceStorico: rc.getStorico () ) {
								if ( filtro.getAncheRiferimentiNonInVita () != null && filtro.getAncheRiferimentiNonInVita () ) {
									// filtro include anche i riferimenti non in vita
									allRiferimentiGruppoIncludingStorico.add ( voceStorico );
								} else {
									// il filtro esclude i riferimenti non in vita
									if ( voceStorico.getInVita () ) {
										allRiferimentiGruppoIncludingStorico.add ( voceStorico );
									}
								}
							}
						}
					}

					gruppo.setRiferimentiContabili ( allRiferimentiGruppoIncludingStorico );
				}

				model.addAttribute ( MODEL_RISULTATI_RICERCA, flatList );
				model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( list.size () < 1 ) );
			} catch ( OperationFailedException e ) {
				model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
				model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
			}
		}

		loadComboboxes ( model );
		return PAGINA_RICERCA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
	public String doRicerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaRiferimentoContabileFiltroVO filtro, BindingResult bindingResult,
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

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.ELIMINA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/elimina/{id}", method = RequestMethod.GET )
	public String elimina ( @PathVariable ( "id" ) Long id, RedirectAttributes redirectAttributes ) {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.DELETE );

		try {
			riferimentoContabileManager.elimina ( id );
			redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
		} catch ( RuntimeException | OperationFailedException e ) {
			redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
		} catch ( Exception e ) {
			redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
		}

		return "redirect:" + URL_RICERCA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( "/esporta-excel" )
	public void esportaXlsx ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

		authorizationManager.require ( RiferimentoContabileVO.class, EntityAction.LIST );

		List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> list;

		RicercaRiferimentoContabileFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaRiferimentoContabileFiltroVO.class );

		if ( filtro == null ) {
			throw new NotAllowedException ();
		} else {
			try {
				list = riferimentoContabileManager.ricerca ( filtro );
			} catch ( OperationFailedException e ) {
				throw new RuntimeException ( e );
			}
			esportazioneManager.esportaRiferimentiContabiliXlsx ( response, list );
		}
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( "/esporta-csv" )
	public void esportaCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

		authorizationManager.require ( RiferimentoContabileVO.class, EntityAction.LIST );

		List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> list;

		RicercaRiferimentoContabileFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaRiferimentoContabileFiltroVO.class );

		if ( filtro == null ) {
			throw new NotAllowedException ();
		} else {
			try {
				list = riferimentoContabileManager.ricerca ( filtro );
			} catch ( OperationFailedException e ) {
				throw new RuntimeException ( e );
			}
			esportazioneManager.esportaRiferimentiContabiliCsv ( response, list );
		}
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/modifica/{id}", method = RequestMethod.GET )
	public String viewModificaRiferimentoContabile ( @PathVariable ( "id" ) Long id, Model model ) throws OperationFailedException {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.WRITE );

		popolaModelModifica ( model, id, null, SCENARIO_MODIFICA, null );
		return PAGINA_MODIFICA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/visualizza/{id}", method = RequestMethod.GET )
	public String viewVisualizzaRiferimentoContabile ( @PathVariable ( "id" ) Long id, Model model )
					throws OperationFailedException {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.WRITE );

		popolaModelModifica ( model, id, null, SCENARIO_MODIFICA, null );
		return PAGINA_VISUALIZZA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/visualizza-storico/{idPadre}/{id}", method = RequestMethod.GET )
	public String viewVisualizzaStoricoRiferimentoContabile ( @PathVariable ( "idPadre" ) Long idPadre, @PathVariable ( "id" ) Long id, Model model )
						throws OperationFailedException {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.WRITE );

		popolaModelVisualizzaStorico ( model, idPadre, id );
		return PAGINA_VISUALIZZA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/chiudi/{id}", method = RequestMethod.GET )
	public String viewChiudiRiferimentoContabile ( @PathVariable ( "id" ) Long id, Model model )
					throws OperationFailedException {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.WRITE );

		popolaModelChiusura ( model, id, null );
		return PAGINA_CHIUDI;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/nuovo", method = RequestMethod.GET )
	public String viewNuovoRiferimentoContabile ( Model model, HttpServletRequest request ) throws OperationFailedException {

		popolaModelModifica ( model, null, null, SCENARIO_NUOVO, null );

		String startingCode = request.getParameter ( "codiceVersamento" );
		if ( null != ( request.getSession ().getAttribute ( "idCodiceVersamento" ) ) ) {
			startingCode = request.getSession ().getAttribute ( "idCodiceVersamento" ).toString ();
		}

		if ( !StringUtils.isEmpty ( startingCode ) ) {
			model.addAttribute ( MODEL_CODICE_VERSAMENTO_DEFAULT, startingCode );
			popolaModelRiferimentiContabiliSecondari ( model, SCENARIO_NUOVO, new Long ( startingCode ) );
		}

		return PAGINA_MODIFICA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/nuovoDaCodiceVersamento/{idCov}", method = RequestMethod.GET )
	public String viewModificaRiferimentoContabileDaCodiceVersamento ( @PathVariable ( "idCov" ) Long idCov, Model model )
					throws OperationFailedException {
		popolaModelModifica ( model, null, null, SCENARIO_NUOVO_DA_CODICE_VERSAMENTO, idCov );
		return PAGINA_MODIFICA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/modifica/{id}", method = RequestMethod.POST )
	public String doModificaRiferimentoContabile (
		@PathVariable ( "id" ) Long id,
		@Valid @ModelAttribute ( MODEL_MODIFICA_RIFERIMENTO_CONTABILE ) ModificaRiferimentoContabileVO userInput,
		BindingResult bindingResult,
		Model model, RedirectAttributes redirectAttributes ) throws OperationFailedException {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.WRITE );

		if ( bindingResult.hasErrors () ) {
			popolaModelModifica ( model, id, userInput, SCENARIO_MODIFICA, null );
			String validationResultsKey = "org.springframework.validation.BindingResult.modifica_riferimento_contabile";
			model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
			return PAGINA_MODIFICA;
		} else {
			try {
			    
			  //ENG:RDI-046
                String messaggioErrore = riferimentoContabileManager.verificaPresenzaRiferimentiContabili ( 
                    userInput.getIdCodiceVersamento (), userInput.getAnnoEsercizio (),
                    userInput.getAnnoAccertamento () == null, userInput.getDataInizioValidita (), userInput.getDataFineValidita (), id );
                if ( !StringUtils.isEmpty ( messaggioErrore ) ) {
                    popolaModelModifica ( model, id, userInput, SCENARIO_MODIFICA, null );
                    model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format (
                        Messages.ERROR_SAVING_CHANGES, messaggioErrore ) );
                    return PAGINA_MODIFICA;
                }
			    
			    
				riferimentoContabileManager.aggiorna ( userInput );
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
			} catch ( RuntimeException | OperationFailedException e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
					String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
			} catch ( Exception e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
					String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
			}

			return "redirect:" + URL_RICERCA;
		}
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/duplica/{id}", method = RequestMethod.GET )
	public String viewDuplicaRiferimentoContabile (
		@PathVariable ( "id" ) Long id,
		Model model,
		HttpServletRequest request ) throws OperationFailedException {

		popolaModelModifica ( model, id, null, SCENARIO_DUPLICA, null );
		RiferimentoContabileVO riferimentoContabileVO = riferimentoContabileManager.get ( id );

		String startingCode = request.getParameter ( "codiceVersamento" );
		if ( !StringUtils.isEmpty ( startingCode ) ) {
			model.addAttribute ( MODEL_CODICE_VERSAMENTO_DEFAULT, startingCode );
			model.addAttribute ( "riferimentoContabileVO", riferimentoContabileVO );
		}
		loadComboboxesModifica ( model );
		return PAGINA_MODIFICA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/nuovoDaCodiceVersamento/{idCov}", method = RequestMethod.POST )
	public String doNuovoRiferimentoContabileDaCodiceVersamento (
		@PathVariable ( "idCov" ) Long idCov,
		@Valid @ModelAttribute ( MODEL_MODIFICA_RIFERIMENTO_CONTABILE ) ModificaRiferimentoContabileVO userInput,
		BindingResult bindingResult,
		Model model, RedirectAttributes redirectAttributes ) throws OperationFailedException {

		Long id = null;

		if ( bindingResult.hasErrors () ) {
			popolaModelModifica ( model, id, userInput, SCENARIO_NUOVO_DA_CODICE_VERSAMENTO, idCov );
			String validationResultsKey = "org.springframework.validation.BindingResult.modifica_riferimento_contabile";
			model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
			return PAGINA_MODIFICA;
		} else {

			try {
			    
			    String messaggioErrore = riferimentoContabileManager.verificaPresenzaRiferimentiContabili ( //TODO questa chiamata fa saltare l'inserimento rif con
                    userInput.getIdCodiceVersamento (), userInput.getAnnoEsercizio (),
                    userInput.getAnnoAccertamento () == null , userInput.getDataInizioValidita (), userInput.getDataFineValidita (), null );
                if ( !StringUtils.isEmpty ( messaggioErrore ) ) {
                    popolaModelModifica ( model, id, userInput, SCENARIO_NUOVO_DA_CODICE_VERSAMENTO, null );
                    model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format (
                        Messages.ERROR_SAVING_CHANGES, messaggioErrore ) );
                    return PAGINA_MODIFICA;
                }
			    
				riferimentoContabileManager.inserisci ( userInput );
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
			} catch ( RuntimeException e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
					String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
			}

			return "redirect:" + URL_RICERCA;
		}
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/nuovo", method = RequestMethod.POST )
	public String doNuovoRiferimentoContabile (
					@Valid @ModelAttribute ( MODEL_MODIFICA_RIFERIMENTO_CONTABILE ) ModificaRiferimentoContabileVO userInput,
					BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes ) throws OperationFailedException {
		if ( bindingResult.hasErrors () ) {
			popolaModelModifica ( model, null, userInput, SCENARIO_NUOVO, null );
			String validationResultsKey = "org.springframework.validation.BindingResult.modifica_riferimento_contabile";
			model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
			return PAGINA_MODIFICA;
		} else {

			try {
				//ENG:RDI-046
				String messaggioErrore = riferimentoContabileManager.verificaPresenzaRiferimentiContabili ( //TODO questa chiamata fa saltare l'inserimento rif con
								userInput.getIdCodiceVersamento (), userInput.getAnnoEsercizio (),
								userInput.getAnnoAccertamento () == null, userInput.getDataInizioValidita (), userInput.getDataFineValidita (), null );
				if ( !StringUtils.isEmpty ( messaggioErrore ) ) {
					popolaModelModifica ( model, null, userInput, SCENARIO_NUOVO, null );
					model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format (
									Messages.ERROR_SAVING_CHANGES, messaggioErrore ) );
					return PAGINA_MODIFICA;
				}

				riferimentoContabileManager.inserisci ( userInput );
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
			} catch ( RuntimeException | OperationFailedException e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
								String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
			} catch ( Exception e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
								String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
			}

			return "redirect:" + URL_RICERCA;
		}
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/duplica/{id}", method = RequestMethod.POST )
	public String doDuplicaRiferimentoContabile (
	                @PathVariable ( "id" ) Long id,
					@Valid @ModelAttribute ( MODEL_MODIFICA_RIFERIMENTO_CONTABILE ) ModificaRiferimentoContabileVO userInput,
					BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request )
					throws OperationFailedException {
		request.getSession ().setAttribute ( "duplicato", userInput );
		
		if ( bindingResult.hasErrors () ) {
            popolaModelModifica ( model, id, userInput, SCENARIO_DUPLICA, null );
            String validationResultsKey = "org.springframework.validation.BindingResult.modifica_riferimento_contabile";
            model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
            return PAGINA_MODIFICA;
        } else {
            try {
                String messaggioErrore =
                                riferimentoContabileManager.verificaPresenzaRiferimentiContabili ( //TODO questa chiamata fa saltare l'inserimento rif con
                                                userInput.getIdCodiceVersamento (), userInput.getAnnoEsercizio (),
                                                userInput.getAnnoAccertamento () == null, userInput.getDataInizioValidita (),
												userInput.getDataFineValidita (), null );
                if ( !StringUtils.isEmpty ( messaggioErrore ) ) {
                    popolaModelModifica ( model, id, userInput, SCENARIO_DUPLICA, null );
                    model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format (
                                    Messages.ERROR_SAVING_CHANGES, messaggioErrore ) );
                    return PAGINA_MODIFICA;
                }
                userInput.setId ( null );
                riferimentoContabileManager.inserisci ( userInput );
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
            } catch ( RuntimeException | OperationFailedException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                                String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
			} catch ( Exception e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                                String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
			}
            
            return "redirect:" + URL_RICERCA;
        }
		
	}

	

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_RIFERIMENTO_CONTABILE + "')" )
	@RequestMapping ( value = "/chiudi/{id}", method = RequestMethod.POST )
	public String doChiudiRiferimentoContabile (
		@PathVariable ( "id" ) Long id,
		@Valid @ModelAttribute ( MODEL_CHIUDI_RIFERIMENTO_CONTABILE ) ChiudiRiferimentoContabileVO userInput,
		BindingResult bindingResult,
		Model model, RedirectAttributes redirectAttributes ) throws OperationFailedException {

		authorizationManager.require ( RiferimentoContabileVO.class, id, EntityAction.WRITE );

		if ( bindingResult.hasErrors () ) {
			popolaModelChiusura ( model, id, userInput );
			String validationResultsKey = "org.springframework.validation.BindingResult.chiudi_riferimento_contabile";
			model.addAttribute ( validationResultsKey, bindingResult.getModel ().get ( validationResultsKey ) );
			return PAGINA_CHIUDI;
		} else {
			try {
				riferimentoContabileManager.chiudi ( userInput );
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
			} catch ( RuntimeException | OperationFailedException e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
					String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
			} catch ( Exception e ) {
				redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
					String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
			}

			return "redirect:" + URL_RICERCA;
		}
	}

	private void popolaModelVisualizzaStorico ( Model model, Long idPadre, Long id ) throws OperationFailedException {

		RiferimentoContabileVO modelMerged;
		modelMerged = riferimentoContabileManager.getStorico ( idPadre, id );

		model.addAttribute ( MODEL_MODIFICA_RIFERIMENTO_CONTABILE, modelMerged );
		model.addAttribute ( MODEL_SCENARIO, SCENARIO_MODIFICA );
		model.addAttribute ( MODEL_ID_RIFERIMENTO_CONTABILE, id );

		loadComboboxesModifica ( model );
	}

	private void popolaModelModifica ( Model model, Long id, ModificaRiferimentoContabileVO modelCorrente, String scenario, Long idCov )
					throws OperationFailedException {

		RiferimentoContabileVO modelMerged;

		if ( id == null ) { // nuovo
			if ( modelCorrente != null ) {
				modelMerged = riferimentoContabileManager.merge ( null, modelCorrente, SCENARIO_DUPLICA.equals ( scenario ) );
			} else {
				modelMerged = riferimentoContabileManager.istanzia ();
			}
		} else { // modifica o duplica
			if ( modelCorrente == null ) {
				modelMerged = riferimentoContabileManager.get ( id );
			} else {
				modelMerged = riferimentoContabileManager.merge ( id, modelCorrente, SCENARIO_DUPLICA.equals ( scenario ) );
			}
		}

		model.addAttribute ( MODEL_MODIFICA_RIFERIMENTO_CONTABILE, modelMerged );
		if ( SCENARIO_DUPLICA.equals ( scenario )) {
		    model.addAttribute ( MODEL_SCENARIO, SCENARIO_NUOVO );
		    model.addAttribute ( SCENARIO_ALT, SCENARIO_DUPLICA );
		} else {
		    model.addAttribute ( MODEL_SCENARIO, scenario );
		}

		model.addAttribute ( MODEL_ID_RIFERIMENTO_CONTABILE, id );
		if ( null != idCov ) {
			model.addAttribute ( MODEL_CODICE_VERSAMENTO_DEFAULT, idCov );
		} else {
			idCov = modelMerged.getIdCodiceVersamento ();
		}
		if ( null != idCov ) {
			popolaModelRiferimentiContabiliSecondari ( model, scenario, idCov );
		}

		loadComboboxesModifica ( model );
	}

    private void popolaModelRiferimentiContabiliSecondari ( Model model, String scenario, Long idCov ) throws OperationFailedException {
        CodiceVersamentoVO cov = codiceVersamentoManager.getCodiceVersamento ( idCov );
        if ( TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals ( cov.getModalitaAssociazioneMultibeneficiario () ) ) {
        	if ( null == cov.getCovAssociatoAssociazioneMultibeneficiario () ) {
        	    if (SCENARIO_NUOVO_DA_CODICE_VERSAMENTO.equals ( scenario ) ) {
        	        throw new OperationFailedException ( "Mancanza codici versamento associati" );
        	    }
        	} else {
        	    List<GenericVO> riferimentiSecondati
                = riferimentoContabileManager.ricercaRiferimentiContabiliSecondariPerCov ( cov.getCovAssociatoAssociazioneMultibeneficiario () );
                model.addAttribute ( MODEL_RIFERIMENTO_CONTABILE_SECONDARIO, riferimentiSecondati );
        	}
        }
    }

	private void popolaModelChiusura ( Model model, Long id, ChiudiRiferimentoContabileVO modelCorrente ) throws OperationFailedException {

		RiferimentoContabileVO modelMerged = riferimentoContabileManager.get ( id );

		if ( modelCorrente != null ) {
			modelMerged.setDataFineValidita ( modelCorrente.getDataFineValidita () );
		}

		model.addAttribute ( MODEL_CHIUDI_RIFERIMENTO_CONTABILE, modelMerged );
		model.addAttribute ( MODEL_SCENARIO, SCENARIO_CHIUSURA );
		model.addAttribute ( MODEL_ID_RIFERIMENTO_CONTABILE, id );
		loadComboboxesModifica ( model );
	}
	
	 @RequestMapping ( value = "/ajax/ricerca-riferimenti-contabili-secondari-da-associare", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody List<GenericVO> ricercaRiferimentiContabiliSecondariDaAssociare (@RequestParam Long idRifContabileSecondario) {
	        try {
	            return riferimentoContabileManager.ricercaRiferimentiContabiliSecondariPerCov(idRifContabileSecondario);
	        } catch ( Exception e ) {
	            throw new RuntimeException ( "Errore nella ricerca", e );
	        }
	    }
}
