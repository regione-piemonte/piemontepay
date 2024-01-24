/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.BOZZA;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.ERRORE_IN_FASE_DI_INVIO;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.IN_CORSO_DI_REDAZIONE;
import static it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum.LISTE_DI_CARICO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaLightDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;
import it.csi.epay.epaypaweb.dto.common.EliminaPosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.SalvaTestataFlussoPosizioniDebitorieRequestDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndPosizioneDebitoriaLightListRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
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
	@Result(name = INPUT, location = "pages/visualizza-listadicarico.jsp"),
	@Result(name = "REFRESH", type = "chain", location = "entry-visualizza-listadicarico"), // necessario init=true per reinizializzare la pagina
	@Result(name = "DETTAGLIO_POSIZIONE_DEBITORIA", type = "chain", location = "entry-dettaglio-posizionedebitoria"),
	@Result(name = "NUOVA_POSIZIONE_DEBITORIA", type = "chain", location = "entry-creaormodifica-posizionedebitoria"),
	@Result(name = "BACK_TO_HOME", type = "chain", location = "show-main-menu"),
	@Result(name = "BACK_TO_RICERCA", type = "chain", location = "entry-ricerca-listedicarico"),
	@Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" })
	//@formatter:on
})
public class VisualizzaListadicaricoAction extends VisualizzaFlussoBaseAction {
	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = VisualizzaListadicaricoAction.class.getSimpleName ();

	// campi in input per inserimento e modifica della testata della lista di carico non in comune con quelli presenti nella super classe
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private BigDecimal importoTotalePosizioniDebitorie;
	//
	// campi usati per la validazione
	private String strDataInizioValidita;
	private String strDataFineValidita;

	private Long idPosizioneDebitoria;
	private DataTableResponse<PosizioneDebitoriaLightDto> dataTableResponse;
	private List<CodiceVersamentoDto> codiciVersamentoListeDiCarico;

	private static final String SI = "SI";

	private static final String NO = "NO";

	private static final String MULTIBENEFICIARIO_STRING = "multibeneficiario";

	private String multibeneficiario = NO;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Action("entry-visualizza-listadicarico")
	@Authorizations({ @Authorization(cdu = "DET_LDC"), @Authorization(cdu = "INS_LDC") })
	@SkipValidation
	@SuppressWarnings("unchecked")
	public String entryVisualizzaListadicarico() {
		String methodName = "entryVisualizzaListadicarico";
		

		List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
		codiciVersamentoListeDiCarico = new LinkedList<> ();
		for ( CodiceVersamentoDto cov: listaSenzaFiltriMultibeneficiario ) {
			if ( cov.getFlagMbSecondario () == null || ( cov.getFlagMbSecondario () != null && !cov.getFlagMbSecondario () ) ) {
				codiciVersamentoListeDiCarico.add ( cov );
			}
		}

		String result;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			ActionScope actionScope = getSessionContext().getActionScope();
			if (isInit()) {
				// ramo percorso se si arriva dalla ricerca
				actionScope.putValue("idFlusso", idFlusso);
				actionScope.putValue("operativita", operativita);
				actionScope.putValue("origineHomePerInserimento", origineHomePerInserimento);

				// operativita
				switch (operativita) {
				case INSERISCI:
					flussoSelezionato = new FlussoLightDto();
					//
					idMessaggio = "";
					idCodVersamento = null;
					dataInizioValidita = null;
					dataFineValidita = null;
					strDataInizioValidita = Util.date2strdate(dataInizioValidita);
					strDataFineValidita = Util.date2strdate(dataFineValidita);
					numeroElementi = 0;
					importoTotalePosizioniDebitorie = new BigDecimal(0);
					break;

				case MODIFICA:
					FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto = gestioneFlussiBusiness.getFlussoPosizioniDebitorie(idFlusso);
					flussoSelezionato = flussoCompleto.getFlusso();
					//
					setMultibeneficarioFlag ( actionScope );
					//
					idMessaggio = flussoSelezionato.getIdMessaggio();
					idCodVersamento = super.getIdCodVersamento(flussoSelezionato.getCodVersamento());
					dataInizioValidita = flussoCompleto.getItemList().get(0).getDataInizioValidita();
					dataFineValidita = flussoCompleto.getItemList().get(0).getDataFineValidita();
					strDataInizioValidita = Util.date2strdate(dataInizioValidita);
					strDataFineValidita = Util.date2strdate(dataFineValidita);
					numeroElementi = flussoSelezionato.getNumeroElementi();
					importoTotalePosizioniDebitorie = flussoSelezionato.getImportoTotale();
					//
					salvaTestataInActionScope();
					//
					sortingDir = "asc";
					sortingIdx = 3; // data scadenza
					break;

				case VISUALIZZA:
					flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);
					setMultibeneficarioFlag ( actionScope );
					//
					sortingDir = "asc";
					sortingIdx = 3; // data scadenza
					break;
				}

				// resetta il cursore
				getSessionContext().resetStatoCursore();

			} else {
				// ramo percorso al ritorno dal dettaglio o dalla pagina di inserimento/modifica
				FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto = null;
				if (idFlusso == null)
					idFlusso = actionScope.getValue("idFlusso");
				if (idFlusso != null) {
					flussoCompleto = gestioneFlussiBusiness.getFlussoPosizioniDebitorie(idFlusso);
					flussoSelezionato = flussoCompleto.getFlusso();
				}

				operativita = actionScope.getValue("operativita");
				origineHomePerInserimento = actionScope.getValue("origineHomePerInserimento");

				// riprina valori nei campi
				switch (operativita) {
				case INSERISCI:
					sortingDir = "asc";
					sortingIdx = 3; // data scadenza
					restartFrom = 0;
					sortingIdx = 0;
					pageLength = null;
					// no break

				case MODIFICA:
					if (idFlusso != null) {
						idMessaggio = flussoSelezionato.getIdMessaggio();
						idCodVersamento = super.getIdCodVersamento(flussoSelezionato.getCodVersamento());
						numeroElementi = flussoSelezionato.getNumeroElementi();
						importoTotalePosizioniDebitorie = flussoSelezionato.getImportoTotale();
						dataInizioValidita = flussoCompleto.getItemList().get(0).getDataInizioValidita();
						dataFineValidita = flussoCompleto.getItemList().get(0).getDataFineValidita();
						strDataInizioValidita = Util.date2strdate(dataInizioValidita);
						strDataFineValidita = Util.date2strdate(dataFineValidita);

					} else {
						// ramo percorso solo al ritorno dall'inserimento, senza salvataggio, della prima posizione debitoria
						idMessaggio = actionScope.getValue("idMessaggio");
						idCodVersamento = actionScope.getValue("idCodVersamento");
						numeroElementi = actionScope.getValue("numeroElementi");
						importoTotalePosizioniDebitorie = actionScope.getValue("importoTotalePosizioniDebitorie");
						strDataInizioValidita = Util.date2strdate(actionScope.getValue("dataInizioValidita"));
						strDataFineValidita = Util.date2strdate(actionScope.getValue("dataFineValidita"));
						dataInizioValidita = sdf.parse(strDataInizioValidita);
						dataFineValidita = sdf.parse(strDataFineValidita);
					}
					//
					salvaTestataInActionScope();
					break;

				case VISUALIZZA:
					break;
				}

				// riposiziona la tabella nella pagina che contiene l'ultimo dettaglio visitato con scorrimento
				StatoCursore<ColumnNamePosizioneDebitoriaEnum> statoCursore = (StatoCursore<ColumnNamePosizioneDebitoriaEnum>) getSessionContext().getStatoCursore();
				if (statoCursore != null) {
					restartFrom = statoCursore.getNumeroRighePerPagina() * (statoCursore.getNumeroPagina() - 1);
					sortingDir = statoCursore.getCriteriOrdinamento().get(0).getSortTypeEnum() == SortTypeEnum.ASC ? "asc" : "desc";
					switch (statoCursore.getCriteriOrdinamento().get(0).getColumnNameEnum()) {
					default:
					case IUV:
						sortingIdx = 0;
						break;
					case IMPORTO_TOTALE:
						sortingIdx = 1;
						break;
					case DESCRIZIONE_CAUSALE_VERSAMENTO:
						sortingIdx = 2;
						break;
					case DATA_SCADENZA:
						sortingIdx = 3;
						break;
					case ID_FISCALE_DEBITORE:
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

		} catch (BusinessException | ParseException e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action("salva-testata-listadicarico")
	@Authorization(cdu = "INS_LDC")
	public String salvaTestataListadicarico() {
		String methodName = "salvaTestataListadicarico";
		
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
			flusso.setTipoFlusso(LISTE_DI_CARICO);
			flusso.setStatoFlusso(BOZZA);
			flusso.setUtenteInserimento(utente.getCod());
			flusso.setUtenteUltimaVariazione(utente.getCod());
			flusso.setCodFiscaleEnte(ente.getCodFiscale());
			flusso.setCodVersamento(codVersamento);
			flusso.setNumeroElementi(numeroElementi);
			flusso.setImportoTotale(importoTotalePosizioniDebitorie);
			flusso.setPagamentiSpontanei(false);

			// risalva
			salvaTestataInActionScope();

			SalvaTestataFlussoPosizioniDebitorieRequestDto salvaTestataFlussoPosizioniDebitorieRequestDto = new SalvaTestataFlussoPosizioniDebitorieRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),flusso,dataInizioValidita,dataFineValidita);
			esitoElaborazione = (gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorie(salvaTestataFlussoPosizioniDebitorieRequestDto)) ? "success" : "warning";
			idMessaggio = gestioneFlussiBusiness.getFlussoLight(idFlusso).getIdMessaggio();
			messaggioEsitoElaborazione = getText("message." + esitoElaborazione + ".salvatestata.flusso", new String[] { idMessaggio });
			//
			ActionScope actionScope = getSessionContext().getActionScope();
			actionScope.putValue("idMessaggio", idMessaggio);

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			esitoElaborazione = "error";
			messaggioEsitoElaborazione = getText("message.error.salvatestata.flusso", new String[] { idMessaggio, e.getMessage() });

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return "REFRESH";
	}

	@Action("dettaglio-posizionedebitoria")
	@Authorization(cdu = "DET_LDC")
	@SkipValidation
	public String dettaglioPosizioneDebitoriaAction() {
		String methodName = "dettaglioPosizioneDebitoriaAction";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		salvaTestataInActionScope();

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "DETTAGLIO_POSIZIONE_DEBITORIA";
	}

	@Action("nuova-posizionedebitoria")
	@Authorization(cdu = "INS_LDC")
	public String nuovaPosizioneDebitoria() {
		return nuovaOrModificaPosizioneDebitoria("nuovaPosizioneDebitoria");
	}

	@Action("modifica-posizionedebitoria")
	@Authorization(cdu = "INS_LDC")
	public String modificaPosizioneDebitoriaAction() {
		return nuovaOrModificaPosizioneDebitoria("modificaPosizioneDebitoriaAction");
	}

	//@formatter:off
	private String nuovaOrModificaPosizioneDebitoria(String methodName) {
		

		String result;

		// salva testata in modo persistente se sono cambiati i valori
		FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// stabilisce se risalvare la testata se i suoi valori sono cambiati rispetto a quelli salvati
			if (idFlusso != null) {
				flussoCompleto = gestioneFlussiBusiness.getFlussoPosizioniDebitorie(idFlusso);
				String codVersamentoSaved = flussoCompleto.getFlusso().getCodVersamento();
				Date dataIniValiditaSaved = flussoCompleto.getItemList().get(0).getDataInizioValidita();
				Date dataFinValiditaSaved = flussoCompleto.getItemList().get(0).getDataFineValidita();
				//
				if (   idCodVersamento   .compareTo(super.getIdCodVersamento(codVersamentoSaved)) != 0
								|| dataInizioValidita.compareTo(dataIniValiditaSaved) != 0
								|| dataFineValidita  .compareTo(dataFinValiditaSaved) != 0) {
					salvaTestataListadicarico();
					if (esitoElaborazione.equals("success"))
						result = "NUOVA_POSIZIONE_DEBITORIA";
					else
						result = "REFRESH";

				} else {
					salvaTestataInActionScope();
					result = "NUOVA_POSIZIONE_DEBITORIA";
				}

			} else {
				salvaTestataInActionScope();
				result = "NUOVA_POSIZIONE_DEBITORIA";
			}

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			esitoElaborazione = "error";
			messaggioEsitoElaborazione = getText("message.error.salvatestata.flusso", new String[] { idMessaggio, e.getMessage() });
			result = "REFRESH";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}
	//@formatter:on

	@Action("elimina-posizionedebitoria")
	@Authorization(cdu = "ELM_LDC")
	@SkipValidation
	public String eliminaPosizioneDebitoriaAction() {
		String methodName = "eliminaPosizioneDebitoriaAction";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// riottiene dalla sessione alcuni dati necessari
			ActionScope actionScope = getSessionContext().getActionScope();
			idFlusso = actionScope.getValue("idFlusso");
			operativita = actionScope.getValue("operativita");
			origineHomePerInserimento = actionScope.getValue("origineHomePerInserimento");

			// logica dell'elimina
			EliminaPosizioneDebitoriaRequestDto eliminaPosizioneDebitoriaRequestDto = new EliminaPosizioneDebitoriaRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idFlusso,idPosizioneDebitoria);
			if (gestioneFlussiBusiness.eliminaPosizioneDebitoria(eliminaPosizioneDebitoriaRequestDto)) {
				esitoElaborazione = "success";

				// verifica se il flusso esiste ancora o se risulta anch'esso eliminato
				if (gestioneFlussiBusiness.existsFlusso(idFlusso)) {
					messaggioEsitoElaborazione = getText("message.success.elimina.posizionedebitoria");
					operativita = TipoOperativitaEnum.MODIFICA;

				} else {
					messaggioEsitoElaborazione = getText("message.success.elimina.flusso", new String[] { idMessaggio });
					operativita = TipoOperativitaEnum.INSERISCI;
					idFlusso = null;
				}

			} else {
				esitoElaborazione = "warning";
				messaggioEsitoElaborazione = getText("message.warning.elimina.posizionedebitoria");
			}

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			esitoElaborazione = "error";
			messaggioEsitoElaborazione = getText("message.error.elimina.posizionedebitoria", new String[] { e.getMessage() });

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return "REFRESH";
	}

	private void setMultibeneficarioFlag ( ActionScope actionScope ) {
		Integer idCov = getIdCodVersamento ( flussoSelezionato.getCodVersamento () );
		Boolean flagMbPrimario = getCov ( idCov ).getFlagMbPrimario ();
		if ( null != flagMbPrimario && flagMbPrimario ) {
			actionScope.putValue ( MULTIBENEFICIARIO_STRING, SI );
			multibeneficiario = SI;
		} else {
			actionScope.putValue ( MULTIBENEFICIARIO_STRING, NO );
			multibeneficiario = NO;
		}
	}

	private void salvaTestataInActionScope() {
		ActionScope actionScope = getSessionContext().getActionScope();
		actionScope.putValue("idFlusso", idFlusso);
		actionScope.putValue("idMessaggio", idMessaggio);
		actionScope.putValue("idCodVersamento", idCodVersamento);
		actionScope.putValue("dataInizioValidita", dataInizioValidita);
		actionScope.putValue("dataFineValidita", dataFineValidita);
		actionScope.putValue("numeroElementi", numeroElementi);
		actionScope.putValue("importoTotalePosizioniDebitorie", importoTotalePosizioniDebitorie);
		List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
		for ( CodiceVersamentoDto cov: listaSenzaFiltriMultibeneficiario ) {
			if ( null != idCodVersamento && idCodVersamento.equals ( cov.getId () ) && cov.getFlagMbPrimario () != null && cov.getFlagMbPrimario () ) {
				actionScope.putValue ( MULTIBENEFICIARIO_STRING, SI );
				break;
			}
		}
	}

	@Action("pubblica-listedicarico")
	@Authorization(cdu = "INS_LDC")
	@SkipValidation
	public String pubblicaListedicarico() {
		String methodName = "pubblicaListedicarico";
		

		String result;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// riottiene dalla sessione alcuni dati necessari
			ActionScope actionScope = getSessionContext().getActionScope();
			idFlusso = actionScope.getValue("idFlusso");
			operativita = actionScope.getValue("operativita");
			origineHomePerInserimento = actionScope.getValue("origineHomePerInserimento");
			flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);

			// determina lo stato del flusso
			FlussoDto testataFlusso = new FlussoDto(idFlusso);
			if (flussoSelezionato.getCodEsito() != null)
				testataFlusso.setStatoFlusso((Integer.parseInt(flussoSelezionato.getCodEsito()) > 99) ? ERRORE_IN_FASE_DI_INVIO : IN_CORSO_DI_REDAZIONE);
			else
				testataFlusso.setStatoFlusso(IN_CORSO_DI_REDAZIONE);

			SalvaTestataFlussoPosizioniDebitorieRequestDto salvaTestataFlussoPosizioniDebitorieRequestDto = new SalvaTestataFlussoPosizioniDebitorieRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),testataFlusso,null,null);
			// esce dallo stato di bozza salvando il nuovo stato e determina dove tornare in vase all'origine della provinienza
			if (gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorie(salvaTestataFlussoPosizioniDebitorieRequestDto)) {
				esitoElaborazione = "success";
				result = origineHomePerInserimento ? "BACK_TO_HOME" : "BACK_TO_RICERCA";
			} else {
				esitoElaborazione = "warning";
				result = "REFRESH";
			}
			messaggioEsitoElaborazione = getText("message." + esitoElaborazione + ".pubblica.flusso", new String[] { flussoSelezionato.getIdMessaggio() });

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			esitoElaborazione = "error";
			messaggioEsitoElaborazione = getText("message.error.pubblica.flusso", new String[] { flussoSelezionato.getIdMessaggio(), e.getMessage() });
			result = "REFRESH";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action("visualizza-listadicarico-json")
	@Authorization(cdu = "DET_LDC")
	@SkipValidation
	public String visualizzaListadicaricoJSON() throws Exception {
		String methodName = "visualizzaListadicaricoJSON";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> sorting = null;
		if (sortingCol != null && sortingDir != null) {
			sorting = new ArrayList<> ();
			CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>();

			if ("IUV".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNamePosizioneDebitoriaEnum.IUV);
			else if ("importoTotale".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNamePosizioneDebitoriaEnum.IMPORTO_TOTALE);
			else if ("dataScadenza".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNamePosizioneDebitoriaEnum.DATA_SCADENZA);
			else if ("desCausaleVersamento".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNamePosizioneDebitoriaEnum.DESCRIZIONE_CAUSALE_VERSAMENTO);
			else if ("soggettoDebitore.idUnivocoFiscale".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNamePosizioneDebitoriaEnum.ID_FISCALE_DEBITORE);

			sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
			sorting.add(sortingItem);
		}

		PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

		try {

			TotalSizeAndPosizioneDebitoriaLightListRequestDto totalSizeAndPosizioneDebitoriaLightListRequestDto = new TotalSizeAndPosizioneDebitoriaLightListRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idFlusso,sorting,pag);

			//@formatter:off
			TotalSizeAndLightListDto<PosizioneDebitoriaLightDto> totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndPosizioneDebitoriaLightList(totalSizeAndPosizioneDebitoriaLightListRequestDto);

			Integer count = totalSizeAndLightList.getTotalSize().intValue();
			List<PosizioneDebitoriaLightDto> lightList = totalSizeAndLightList.getLightList();

			dataTableResponse = new DataTableResponse<> ();
			dataTableResponse.setRecordsTotal(count);
			dataTableResponse.setRecordsFiltered(count);
			dataTableResponse.setDraw(draw);
			dataTableResponse.setData(lightList);

			log.debug("lightList:" + lightList);

			StatoCursore<ColumnNamePosizioneDebitoriaEnum> statoCursore;
			statoCursore = new StatoCursore<> ( idFlusso, collectIds ( lightList ), pag.getNumeroPagina (), pag.getNumeroRighePerPagina (), count, sorting );
			getSessionContext().setStatoCursore(statoCursore);
			//@formatter:on

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "json-table-data";
	}

	@Override
	// prima sono eseguiti controlli sintattici attraverso la validation by annotation, poi si fanno ulteriori controlli sui dati (precedenza date, range, ecc.)
	public void validate() {
		String methodName = "validate";
		

		Set<String> fieldErrorKeys = new TreeSet<String>();
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (hasFieldErrors()) {
				fieldErrorKeys = getFieldErrors().keySet();
			}

			if (!fieldErrorKeys.contains("strDataInizioValidita") && !fieldErrorKeys.contains("strDataFineValidita")) {
				setDataInizioEFineValiditaAndCheckPrecedenza();
				if (hasFieldErrors())
					addActionMessage(getText("message.valorizzare.testata"));
			}
			if (numeroElementi != null && numeroElementi >= 1000) {
				addFieldError("numeroElementi", "");
				addActionMessage(getText("message.non.superare.1000.posizioni"));
			}
			List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
	        codiciVersamentoListeDiCarico = new LinkedList<> ();
	        for ( CodiceVersamentoDto cov: listaSenzaFiltriMultibeneficiario ) {
	            if ( cov.getFlagMbSecondario () == null || ( cov.getFlagMbSecondario () != null && !cov.getFlagMbSecondario () ) ) {
	                codiciVersamentoListeDiCarico.add ( cov );
	            }
	        }


		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	private void setDataInizioEFineValiditaAndCheckPrecedenza() throws ParseException {
		if (!isBlank(strDataInizioValidita) && !isBlank(strDataFineValidita)) {
			dataInizioValidita = sdf.parse(strDataInizioValidita);
			dataFineValidita = sdf.parse(strDataFineValidita);
			if (dataInizioValidita.after(dataFineValidita)) {
				addFieldError("strDataInizioValidita", getText("message.verificare.precedenza.date"));
				addFieldError("strDataFineValidita", getText("message.verificare.precedenza.date"));
			}
		}
	}

	public BigDecimal getImportoTotalePosizioniDebitorie() {
		return importoTotalePosizioniDebitorie;
	}

	public void setImportoTotalePosizioniDebitorie(BigDecimal importoTotalePosizioniDebitorie) {
		this.importoTotalePosizioniDebitorie = importoTotalePosizioniDebitorie;
	}

	public String getStrDataInizioValidita() {
		return strDataInizioValidita;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setStrDataInizioValidita(String strDataInizioValidita) {
		this.strDataInizioValidita = strDataInizioValidita;
	}

	public String getStrDataFineValidita() {
		return strDataFineValidita;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setStrDataFineValidita(String strDataFineValidita) {
		this.strDataFineValidita = strDataFineValidita;
	}

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public DataTableResponse<PosizioneDebitoriaLightDto> getDataTableResponse() {
		return dataTableResponse;
	}

	public void setDataTableResponse(DataTableResponse<PosizioneDebitoriaLightDto> dataTableResponse) {
		this.dataTableResponse = dataTableResponse;
	}

	public List<CodiceVersamentoDto> getCodiciVersamentoListeDiCarico() {
		return codiciVersamentoListeDiCarico;
	}

	public void setCodiciVersamentoListeDiCarico(List<CodiceVersamentoDto> codiciVersamentoListeDiCarico) {
		this.codiciVersamentoListeDiCarico = codiciVersamentoListeDiCarico;
	}

	public GestioneFlussiBusiness getGestioneFlussiBusiness() {
		return gestioneFlussiBusiness;
	}

	public void setGestioneFlussiBusiness(GestioneFlussiBusiness gestioneFlussiBusiness) {
		this.gestioneFlussiBusiness = gestioneFlussiBusiness;
	}

	public String getMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( String multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}
}
