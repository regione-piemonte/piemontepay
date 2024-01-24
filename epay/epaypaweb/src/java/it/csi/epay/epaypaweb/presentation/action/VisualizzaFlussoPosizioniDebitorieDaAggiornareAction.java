/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.BOZZA;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.ERRORE_IN_FASE_DI_INVIO;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.IN_CORSO_DI_REDAZIONE;
import static it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum.AGGIORNAMENTO_POSIZIONI_DEBITORIE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareLightDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;
import it.csi.epay.epaypaweb.dto.common.EliminaPosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.annotation.Authorizations;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import it.csi.epay.epaypaweb.presentation.dto.StatoCursore;

import it.csi.epay.epaypaweb.util.Util;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/visualizza-flusso-posizionidebitoriedaaggiornare.jsp"),
	@Result(name = "REFRESH", type = "chain", location = "entry-visualizza-flusso-posizionidebitoriedaaggiornare"), // necessario init=true per reinizializzare la pagina
	@Result(name = "DETTAGLIO_POSIZIONE_DEBITORIA_DA_AGGIORNARE", type = "chain", location = "entry-dettaglio-posizionedebitoriadaaggiornare"),
	@Result(name = "NUOVA_POSIZIONE_DEBITORIA_DA_AGGIORNARE", type = "chain", location = "entry-creaormodifica-posizionedebitoriadaaggiornare"),
	@Result(name = "BACK_TO_HOME", type = "chain", location = "show-main-menu"),
	@Result(name = "BACK_TO_RICERCA", type = "chain", location = "entry-ricerca-listeaggiornamentoposizionidebitorie"),
	@Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" })
//@formatter:on
})
public class VisualizzaFlussoPosizioniDebitorieDaAggiornareAction extends VisualizzaFlussoBaseAction {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = VisualizzaFlussoPosizioniDebitorieDaAggiornareAction.class.getSimpleName ();

	// N.B. i campi in input per inserimento e modifica della testata del flusso delle posizioni debitorie da aggiornare sono tutti presenti nella super classe

	private Long idPosizioneDebitoriaDaAggiornare;
	private DataTableResponse<PosizioneDebitoriaDaAggiornareLightDto> dataTableResponse;

	private List<CodiceVersamentoDto> codiciVersamentoListeDiCarico;

	private static final String ID_FLUSSO = "idFlusso";

	private static final String OPERATIVITA_CONST = "operativita";

	private static final String ORIGINE_HOME_PER_INSERIMENTO = "origineHomePerInserimento";

	private static final String ID_MESSAGGIO = "idMessaggio";

	private static final String NUMERO_ELEMENTI = "numeroElementi";

	private static final String ERRORE_IMPREVISTO = "Errore imprevisto";

	private static final String NUOVA_POSIZIONE_DEBITORIA_DA_AGGIORNARE = "NUOVA_POSIZIONE_DEBITORIA_DA_AGGIORNARE";

	private static final String REFRESH = "REFRESH";

	private static final String SUCCESS = "success";

	private static final String WARNING = "warning";

	private static final String ERROR = "error";

	private static final String SI = "SI";

	private static final String NO = "NO";

	private String multibeneficiario = NO;

	private String strImportoPrincipale;

	private String strImportoSecondario;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Action("entry-visualizza-flusso-posizionidebitoriedaaggiornare")
	@Authorizations({ @Authorization(cdu = "DET_AGPD"), @Authorization(cdu = "INS_AGPD") })
	@SkipValidation
	@SuppressWarnings("unchecked")
	public String entryVisualizzaFlussoPosizioniDebitorieDaAggiornare() {
		String methodName = "entryVisualizzaFlussoPosizioniDebitorieDaAggiornare";
		

		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
			codiciVersamentoListeDiCarico = new LinkedList<> ();
			for ( CodiceVersamentoDto cov: listaSenzaFiltriMultibeneficiario ) {
				if ( cov.getFlagMbSecondario () == null || ( cov.getFlagMbSecondario () != null && !cov.getFlagMbSecondario () ) ) {
					codiciVersamentoListeDiCarico.add ( cov );
				}
			}

			ActionScope actionScope = getSessionContext().getActionScope();

			if (isInit()) {
				// ramo percorso se si arriva dalla ricerca
				actionScope.putValue ( ID_FLUSSO, idFlusso );
				actionScope.putValue ( OPERATIVITA_CONST, operativita );
				actionScope.putValue ( ORIGINE_HOME_PER_INSERIMENTO, origineHomePerInserimento );

				// operativita
				switch (operativita) {
				case INSERISCI:
					flussoSelezionato = new FlussoLightDto();
					//
					idMessaggio = "";
					idCodVersamento = null;
					numeroElementi = 0;
					break;

				case MODIFICA:
					flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);
					//
					idMessaggio = flussoSelezionato.getIdMessaggio();
					idCodVersamento = super.getIdCodVersamento(flussoSelezionato.getCodVersamento());
					numeroElementi = flussoSelezionato.getNumeroElementi();
					//
					salvaTestataInActionScope();
					//
					sortingDir = "asc";
					sortingIdx = 1; // posizione debitoria esterna
					break;

				case VISUALIZZA:
					flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);
					//
					sortingDir = "asc";
					sortingIdx = 1; // posizione debitoria esterna
					break;
				}

				// resetta il cursore
				getSessionContext().resetStatoCursore();

			} else {
				// ramo percorso al ritorno dal dettaglio o dalla pagina di inserimento/modifica
				if ( idFlusso == null ) {
					idFlusso = actionScope.getValue ( ID_FLUSSO );
				}
				if ( idFlusso != null ) {
					flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);
				}

				operativita = actionScope.getValue ( OPERATIVITA_CONST );
				origineHomePerInserimento = actionScope.getValue ( ORIGINE_HOME_PER_INSERIMENTO );

				// riprina valori nei campi
				switch (operativita) {
				case INSERISCI:
					sortingDir = "asc";
					sortingIdx = 1; // posizione debitoria esterna
					restartFrom = 0;
					sortingIdx = 0;
					pageLength = null;
					// no break

				case MODIFICA:
					if (idFlusso != null) {
						idMessaggio = flussoSelezionato.getIdMessaggio();
						idCodVersamento = super.getIdCodVersamento(flussoSelezionato.getCodVersamento());
						numeroElementi = flussoSelezionato.getNumeroElementi();
					} else {
						// ramo percorso solo al ritorno dall'inserimento, senza salvataggio, della prima posizione debitoria da aggiornare
						idMessaggio = actionScope.getValue ( ID_MESSAGGIO );
						idCodVersamento = actionScope.getValue("idCodVersamento");
						numeroElementi = actionScope.getValue ( NUMERO_ELEMENTI );
					}
					//
					salvaTestataInActionScope();
					break;

				case VISUALIZZA:
					break;
				}

				// riposiziona la tabella nella pagina che contiene l'ultimo dettaglio visitato con scorrimento
				StatoCursore<ColumnNameAggPosizioneDebitoriaEnum> statoCursore = (StatoCursore<ColumnNameAggPosizioneDebitoriaEnum>) getSessionContext().getStatoCursore();
				if (statoCursore != null) {
					restartFrom = statoCursore.getNumeroRighePerPagina() * (statoCursore.getNumeroPagina() - 1);
					sortingDir = statoCursore.getCriteriOrdinamento().get(0).getSortTypeEnum() == SortTypeEnum.ASC ? "asc" : "desc";
					switch (statoCursore.getCriteriOrdinamento().get(0).getColumnNameEnum()) {
					default:
					case TIPO_AGG_POSIZIONE_DEBITORIA:
						sortingIdx = 0;
						break;
					case ID_POSIZIONE_DEBITORIA_ESTERNA:
						sortingIdx = 1;
						break;
					case MOTIVAZIONE:
						sortingIdx = 2;
						break;
					case CODICE_AVVISO:
						sortingIdx = 3;
						break;
					case CONCAT_CODICEESITO_DETTAGLIOESITO:
						sortingIdx = 4;
						break;
					}
					pageLength = statoCursore.getNumeroRighePerPagina();
				}
			}

			// espone eventuali messaggi provenienti da una action chiamata
			addActionMessageIfCallbackExists();
			addActionErrorIfCallbackExists();

			result = INPUT;

		} catch (BusinessException e) {
			log.error ( ERRORE_IMPREVISTO, e );
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		if ( null != flussoSelezionato ) {
			Integer idCov = getIdCodVersamento ( flussoSelezionato.getCodVersamento () );
			if ( null != idCov ) {
				Boolean flagMbPrimario = getCov ( idCov ).getFlagMbPrimario ();
				if ( null != flagMbPrimario && flagMbPrimario ) {
					multibeneficiario = SI;
				} else {
					multibeneficiario = NO;
				}
			}
		}

		return result;
	}

	@Action("salva-testata-flusso-posizionidebitoriedaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	public String salvaTestataFlussoPosizioniDebitorieDaAggiornare() {
		String methodName = "salvaTestataFlussoPosizioniDebitorieDaAggiornare";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce il dto
			SessionContext sessionContext = getSessionContext();
			UtenteDto utente = sessionContext.getUtente();
			EnteDto ente = sessionContext.getEnte();
			String codVersamento = super.getCodVersamento(idCodVersamento);
			//
			FlussoDto flusso = new FlussoDto(idFlusso);
			flusso.setIdMessaggio(idMessaggio);
			flusso.setTipoFlusso(AGGIORNAMENTO_POSIZIONI_DEBITORIE);
			flusso.setStatoFlusso(BOZZA);
			flusso.setUtenteInserimento(utente.getCod());
			flusso.setUtenteUltimaVariazione(utente.getCod());
			flusso.setCodFiscaleEnte(ente.getCodFiscale());
			flusso.setCodVersamento(codVersamento);
			flusso.setNumeroElementi(numeroElementi);

			// risalva
			salvaTestataInActionScope();
			esitoElaborazione = ( gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorieDaAggiornare ( flusso ) ) ? SUCCESS : WARNING;
			idMessaggio = gestioneFlussiBusiness.getFlussoLight(idFlusso).getIdMessaggio();
			messaggioEsitoElaborazione = getText("message." + esitoElaborazione + ".salvatestata.flusso", new String[] { idMessaggio });
			//
			ActionScope actionScope = getSessionContext().getActionScope();
			actionScope.putValue ( ID_MESSAGGIO, idMessaggio );

		} catch (BusinessException e) {
			log.error ( ERRORE_IMPREVISTO, e );
			esitoElaborazione = ERROR;
			messaggioEsitoElaborazione = getText("message.error.salvatestata.flusso", new String[] { idMessaggio, e.getMessage() });

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return REFRESH;
	}

	@Action("dettaglio-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "DET_AGPD")
	@SkipValidation
	public String dettaglioPosizioneDebitoriaDaAggiornareAction() {
		String methodName = "dettaglioPosizioneDebitoriaDaAggiornareAction";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		salvaTestataInActionScope();

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "DETTAGLIO_POSIZIONE_DEBITORIA_DA_AGGIORNARE";
	}

	@Action("nuova-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	public String nuovaPosizioneDebitoriaDaAggiornare() {
		return nuovaOrModificaPosizioneDebitoriaDaAggiornare("nuovaPosizioneDebitoriaDaAggiornare");
	}

	@Action("modifica-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	public String modificaPosizioneDebitoriaDaAggiornare() {
		return nuovaOrModificaPosizioneDebitoriaDaAggiornare("modificaPosizioneDebitoriaDaAggiornare");
	}

	private String nuovaOrModificaPosizioneDebitoriaDaAggiornare(String methodName) {
		

		String result;

		// salva testata in modo persistente se sono cambiati i valori
		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompleto;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// stabilisce se risalvare la testata se i suoi valori sono cambiati rispetto a quelli salvati
			if (idFlusso != null) {
				flussoCompleto = gestioneFlussiBusiness.getFlussoPosizioniDebitorieDaAggiornare(idFlusso);
				String codVersamentoSaved = flussoCompleto.getFlusso().getCodVersamento();
				//
				String cov = flussoCompleto.getFlusso ().getCodVersamento ();
				Integer idCov = getIdCodVersamento ( cov );
				Boolean flagMbPrimario = getCov ( idCov ).getFlagMbPrimario ();
				ActionScope actionScope = getSessionContext ().getActionScope ();
				if ( null != flagMbPrimario && flagMbPrimario ) {
					multibeneficiario = SI;
					actionScope.putValue ( "multibeneficiario", SI );
					if ( null != idPosizioneDebitoriaDaAggiornare ) {
						flussoSelezionato = gestioneFlussiBusiness.getFlussoLight ( idFlusso );
						PosizioneDebitoriaDaAggiornareRequestDto posizioneDebitoriaDaAggiornareRequestDto = new PosizioneDebitoriaDaAggiornareRequestDto (
							getIpAddress (), getIdUtente (), getCodiceFiscaleUtente (), getCodiceApplicazione (), idPosizioneDebitoriaDaAggiornare );

						PosizioneDebitoriaDaAggiornareDto aggposdeb
							= gestioneFlussiBusiness.getPosizioneDebitoriaDaAggiornare ( posizioneDebitoriaDaAggiornareRequestDto );

						strImportoPrincipale = Util.bd2str ( aggposdeb.getImportoPrincipale () );
						strImportoSecondario = Util.bd2str ( aggposdeb.getImportoSecondarioAltroEnte () );
					}
				} else {
					multibeneficiario = NO;
					actionScope.putValue ( "multibeneficiario", NO );
				}
				//
				if (idCodVersamento.compareTo(super.getIdCodVersamento(codVersamentoSaved)) != 0) {
					salvaTestataFlussoPosizioniDebitorieDaAggiornare();
					if ( esitoElaborazione.equals ( SUCCESS ) ) {
						result = NUOVA_POSIZIONE_DEBITORIA_DA_AGGIORNARE;
					} else {
						result = REFRESH;
					}

				} else {
					salvaTestataInActionScope();
					result = NUOVA_POSIZIONE_DEBITORIA_DA_AGGIORNARE;
				}

			} else {
				salvaTestataInActionScope();
				result = NUOVA_POSIZIONE_DEBITORIA_DA_AGGIORNARE;
			}

		} catch (BusinessException e) {
			log.error ( ERRORE_IMPREVISTO, e );
			esitoElaborazione = ERROR;
			messaggioEsitoElaborazione = getText("message.error.salvatestata.flusso", new String[] { idMessaggio, e.getMessage() });
			result = REFRESH;

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Action("elimina-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "ELM_AGPD")
	@SkipValidation
	public String eliminaPosizioneDebitoriaDaAggiornareAction() {
		String methodName = "eliminaPosizioneDebitoriaDaAggiornareAction";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// riottiene dalla sessione alcuni dati necessari
			ActionScope actionScope = getSessionContext().getActionScope();
			idFlusso = actionScope.getValue ( ID_FLUSSO );
			operativita = actionScope.getValue ( OPERATIVITA_CONST );
			origineHomePerInserimento = actionScope.getValue ( ORIGINE_HOME_PER_INSERIMENTO );

			// logica dell'elimina
			EliminaPosizioneDebitoriaDaAggiornareRequestDto eliminaPosizioneDebitoriaDaAggiornareRequestDto = new EliminaPosizioneDebitoriaDaAggiornareRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idFlusso,idPosizioneDebitoriaDaAggiornare);
			if (gestioneFlussiBusiness.eliminaPosizioneDebitoriaDaAggiornare(eliminaPosizioneDebitoriaDaAggiornareRequestDto)) {
				esitoElaborazione = SUCCESS;

				// verifica se il flusso esiste ancora o se risulta anch'esso eliminato
				if (gestioneFlussiBusiness.existsFlusso(idFlusso)) {
					messaggioEsitoElaborazione = getText("message.success.elimina.posizionedebitoriadaaggiornare");
					operativita = TipoOperativitaEnum.MODIFICA;

				} else {
					messaggioEsitoElaborazione = getText("message.success.elimina.flusso", new String[] { idMessaggio });
					operativita = TipoOperativitaEnum.INSERISCI;
					idFlusso = null;
				}

			} else {
				esitoElaborazione = WARNING;
				messaggioEsitoElaborazione = getText("message.warning.elimina.posizionedebitoriadaaggiornare");
			}

		} catch (BusinessException e) {
			log.error ( ERRORE_IMPREVISTO, e );
			esitoElaborazione = ERROR;
			messaggioEsitoElaborazione = getText("message.error.elimina.posizionedebitoriadaaggiornare", new String[] { e.getMessage() });

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return REFRESH;
	}

	private void salvaTestataInActionScope() {
		ActionScope actionScope = getSessionContext().getActionScope();
		actionScope.putValue ( ID_FLUSSO, idFlusso );
		actionScope.putValue ( ID_MESSAGGIO, idMessaggio );
		actionScope.putValue("idCodVersamento", idCodVersamento);
		actionScope.putValue ( NUMERO_ELEMENTI, numeroElementi );

		FlussoLightDto flusso = null;
		try {
			flusso = gestioneFlussiBusiness.getFlussoLight ( idFlusso );
		} catch ( BusinessException e ) {
			// Auto-generated catch block
		}
		if ( null != flusso ) {
			String codVersamento = flusso.getCodVersamento ();
			Integer id = getIdCodVersamento ( codVersamento );
			//gestione multibeneficiario
			Boolean flagMbPrimario = getCov ( id ).getFlagMbPrimario ();
			if ( null != flagMbPrimario && flagMbPrimario ) {
				multibeneficiario = SI;
			} else {
				multibeneficiario = NO;
			}
		}
	}

	@Action("pubblica-flusso-posizionidebitoriedaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	@SkipValidation
	public String pubblicaFlussoPosizioniDebitorieDaAggiornare() {
		String methodName = "pubblicaFlussoPosizioniDebitorieDaAggiornare";
		

		String result;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// riottiene dalla sessione alcuni dati necessari
			ActionScope actionScope = getSessionContext().getActionScope();
			idFlusso = actionScope.getValue ( ID_FLUSSO );
			operativita = actionScope.getValue ( OPERATIVITA_CONST );
			origineHomePerInserimento = actionScope.getValue ( ORIGINE_HOME_PER_INSERIMENTO );
			flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);

			// determina lo stato del flusso
			FlussoDto testataFlusso = new FlussoDto(idFlusso);
			if (flussoSelezionato.getCodEsito() != null)
				testataFlusso.setStatoFlusso((Integer.parseInt(flussoSelezionato.getCodEsito()) > 99) ? ERRORE_IN_FASE_DI_INVIO : IN_CORSO_DI_REDAZIONE);
			else
				testataFlusso.setStatoFlusso(IN_CORSO_DI_REDAZIONE);

			// esce dallo stato di bozza salvando il nuovo stato e determina dove tornare in vase all'origine della provinienza
			if (gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorieDaAggiornare(testataFlusso)) {
				esitoElaborazione = SUCCESS;
				result = origineHomePerInserimento ? "BACK_TO_HOME" : "BACK_TO_RICERCA";
			} else {
				esitoElaborazione = WARNING;
				result = REFRESH;
			}
			messaggioEsitoElaborazione = getText("message." + esitoElaborazione + ".pubblica.flusso", new String[] { flussoSelezionato.getIdMessaggio() });

		} catch (BusinessException e) {
			log.error ( ERRORE_IMPREVISTO, e );
			esitoElaborazione = ERROR;
			messaggioEsitoElaborazione = getText("message.error.pubblica.flusso", new String[] { flussoSelezionato.getIdMessaggio(), e.getMessage() });
			result = REFRESH;

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action("visualizza-flusso-posizionidebitoriedaaggiornare-json")
	@Authorization(cdu = "DET_AGPD")
	@SkipValidation
	public String visualizzaFlussoPosizioniDebitorieDaAggiornareJSON() throws Exception {
		String methodName = "visualizzaFlussoPosizioniDebitorieDaAggiornareJSON";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> sorting = null;
		if (sortingCol != null && sortingDir != null) {
			sorting = new ArrayList<> ();
			CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum> sortingItem = new CriterioOrdinamentoDto<> ();

			if ("tipoAggiornamento.descrizione".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameAggPosizioneDebitoriaEnum.TIPO_AGG_POSIZIONE_DEBITORIA);
			else if ("idPosizioneDebitoriaEsterna".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameAggPosizioneDebitoriaEnum.ID_POSIZIONE_DEBITORIA_ESTERNA);
			else if ("motivazione".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameAggPosizioneDebitoriaEnum.MOTIVAZIONE);
			else if ("codAvviso".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameAggPosizioneDebitoriaEnum.CODICE_AVVISO);
			else if ("esito".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameAggPosizioneDebitoriaEnum.CONCAT_CODICEESITO_DETTAGLIOESITO);

			sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
			sorting.add(sortingItem);
		}

		PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

		try {
			//@formatter:off
			TotalSizeAndLightListDto<PosizioneDebitoriaDaAggiornareLightDto> totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndPosizioneDebitoriaDaAggiornareLightList(idFlusso, sorting, pag);

			Integer count = totalSizeAndLightList.getTotalSize().intValue();
			List<PosizioneDebitoriaDaAggiornareLightDto> lightList = totalSizeAndLightList.getLightList();

			dataTableResponse = new DataTableResponse<> ();
			dataTableResponse.setRecordsTotal(count);
			dataTableResponse.setRecordsFiltered(count);
			dataTableResponse.setDraw(draw);
			dataTableResponse.setData(lightList);

			log.debug("lightList:" + lightList);

			StatoCursore<ColumnNameAggPosizioneDebitoriaEnum> statoCursore;
			statoCursore = new StatoCursore<> ( idFlusso, collectIds ( lightList ), pag.getNumeroPagina (), pag.getNumeroRighePerPagina (), count, sorting );
			getSessionContext().setStatoCursore(statoCursore);
			//@formatter:on

		} catch (BusinessException e) {
			log.error ( ERRORE_IMPREVISTO, e );
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "json-table-data";
	}

	@Override
	// prima sono eseguiti controlli sintattici attraverso la validation by annotation, poi si fanno ulteriori controlli sui dati (precedenza date, range, ecc.)
	public void validate() {
		String methodName = "validate";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (numeroElementi != null && numeroElementi >= 1000) {
				addFieldError ( NUMERO_ELEMENTI, "" );
				addActionMessage(getText("message.non.superare.1000.posizioni"));
			}

		} catch (Throwable e) {
			log.error ( ERRORE_IMPREVISTO, e );
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	public Long getIdPosizioneDebitoriaDaAggiornare() {
		return idPosizioneDebitoriaDaAggiornare;
	}

	public void setIdPosizioneDebitoriaDaAggiornare(Long idPosizioneDebitoriaDaAggiornare) {
		this.idPosizioneDebitoriaDaAggiornare = idPosizioneDebitoriaDaAggiornare;
	}

	public DataTableResponse<PosizioneDebitoriaDaAggiornareLightDto> getDataTableResponse() {
		return dataTableResponse;
	}

	public void setDataTableResponse(DataTableResponse<PosizioneDebitoriaDaAggiornareLightDto> dataTableResponse) {
		this.dataTableResponse = dataTableResponse;
	}

	public List<CodiceVersamentoDto> getCodiciVersamentoListeDiCarico () {
		return codiciVersamentoListeDiCarico;
	}

	public void setCodiciVersamentoListeDiCarico ( List<CodiceVersamentoDto> codiciVersamentoListeDiCarico ) {
		this.codiciVersamentoListeDiCarico = codiciVersamentoListeDiCarico;
	}

	public String getMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( String multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}

	public String getStrImportoPrincipale () {
		return strImportoPrincipale;
	}

	public void setStrImportoPrincipale ( String strImportoPrincipale ) {
		this.strImportoPrincipale = strImportoPrincipale;
	}

	public String getStrImportoSecondario () {
		return strImportoSecondario;
	}

	public void setStrImportoSecondario ( String strImportoSecondario ) {
		this.strImportoSecondario = strImportoSecondario;
	}

}
