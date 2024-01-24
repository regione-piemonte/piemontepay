/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action.mdpbackoffice;

import java.util.*;

import java.lang.reflect.InvocationTargetException;
import java.beans.IntrospectionException;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.*;
import com.opensymphony.xwork2.conversion.annotations.*;
import com.opensymphony.xwork2.ActionContext;

import it.csi.mdp.mdpboweb.dto.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.security.*;

import it.csi.mdp.mdpboweb.business.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.action.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor.MethodProtection;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor.FatClientOnly;
import it.csi.mdp.mdpboweb.presentation.uiutils.*;
import flexjson.JSON;

/**
 * CpGestioneTransazioniAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneTransazioniAction extends BaseAction
		implements
			Preparable {

	public void setAppDataapplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataapplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataapplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataapplicazioni"));
	}

	public void setAppDatatransazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione> value) {
		getSession().put("appDatatransazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione> getAppDatatransazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione>) (getSession()
				.get("appDatatransazioni"));
	}

	public void setAppDataricercaTransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione value) {
		getSession().put("appDataricercaTransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione getAppDataricercaTransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione) (getSession()
				.get("appDataricercaTransazione"));
	}

	public void setAppDatalastWhereClause(java.lang.String value) {
		getSession().put("appDatalastWhereClause", value);
	}

	public java.lang.String getAppDatalastWhereClause() {
		return (java.lang.String) (getSession().get("appDatalastWhereClause"));
	}

	public void setAppDataselettoreIdTransazione(java.lang.String value) {
		getSession().put("appDataselettoreIdTransazione", value);
	}

	public java.lang.String getAppDataselettoreIdTransazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdTransazione"));
	}

	public void setAppDataselettoreOperazione(java.lang.String value) {
		getSession().put("appDataselettoreOperazione", value);
	}

	public java.lang.String getAppDataselettoreOperazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreOperazione"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDatastatiTransazione(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> value) {
		getSession().put("appDatastatiTransazione", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> getAppDatastatiTransazione() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione>) (getSession()
				.get("appDatastatiTransazione"));
	}

	public void setAppDatatransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione value) {
		getSession().put("appDatatransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione getAppDatatransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione) (getSession()
				.get("appDatatransazione"));
	}

	public void setAppDataselettoreIdTipologiaCommissione(java.lang.String value) {
		getSession().put("appDataselettoreIdTipologiaCommissione", value);
	}

	public java.lang.String getAppDataselettoreIdTipologiaCommissione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdTipologiaCommissione"));
	}

	public void setAppDatatipologiaCommissioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione> value) {
		getSession().put("appDatatipologiaCommissioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione> getAppDatatipologiaCommissioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione>) (getSession()
				.get("appDatatipologiaCommissioni"));
	}

	public void setAppDatapaginazioneTrans(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.PaginazioneTrans value) {
		getSession().put("appDatapaginazioneTrans", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.PaginazioneTrans getAppDatapaginazioneTrans() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.PaginazioneTrans) (getSession()
				.get("appDatapaginazioneTrans"));
	}

	public void setAppDatareportCercaTransazioni(java.lang.String value) {
		getSession().put("appDatareportCercaTransazioni", value);
	}

	public java.lang.String getAppDatareportCercaTransazioni() {
		return (java.lang.String) (getSession()
				.get("appDatareportCercaTransazioni"));
	}

	public void setAppDatasizeBlocco(java.lang.Integer value) {
		getSession().put("appDatasizeBlocco", value);
	}

	public java.lang.Integer getAppDatasizeBlocco() {
		return (java.lang.Integer) (getSession().get("appDatasizeBlocco"));
	}

	public void setAppDatastatiTransazionexCambio(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> value) {
		getSession().put("appDatastatiTransazionexCambio", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> getAppDatastatiTransazionexCambio() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione>) (getSession()
				.get("appDatastatiTransazionexCambio"));
	}

	public void setAppDatacercaErrore(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore value) {
		getSession().put("appDatacercaErrore", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore getAppDatacercaErrore() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore) (getSession()
				.get("appDatacercaErrore"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	/////////////////////////////////////////
	/// property comuni a tutte le action
	/////////////////////////////////////////

	// riga selezionata in una eventuale tabella
	// NOTA: l'id della cella selezionata e' data da idRiga + idColonna
	private String _idRiga = "";

	public void setIdRiga(String val) {
		_idRiga = val;
	}

	public String getIdRiga() {
		return _idRiga;
	}

	// colonna selezionata in una eventuale tabella
	// NOTA: l'id della cella selezionata e' data da idRiga + idColonna
	private String _idColonna = "";

	public void setIdColonna(String val) {
		_idColonna = val;
	}

	public String getIdColonna() {
		return _idColonna;
	}

	/// parametri per cambio TAB

	private String selectedTabKey;

	public void setSelectedTabKey(String value) {
		selectedTabKey = value;
	}

	public String getSelectedTabKey() {
		return selectedTabKey;
	}

	private String selectedTabValue;

	public void setSelectedTabValue(String value) {
		selectedTabValue = value;
	}

	public String getSelectedTabValue() {
		return selectedTabValue;
	}

	/**
	 * @generated
	 */
	private List<TableFilter> filter;

	/**
	 * @generated
	 */
	public List<TableFilter> getFilter() {
		return filter;
	}

	/**
	 * @generated
	 */
	public void setFilter(List<TableFilter> filter) {
		this.filter = filter;
	}

	/**
	 * @generated
	 */
	private TableStatus tableStatus;

	/**
	 * @generated
	 */
	public TableStatus getTableStatus() {
		return tableStatus;
	}

	/**
	 * @generated
	 */
	public void setTableStatus(TableStatus tableStatus) {
		this.tableStatus = tableStatus;
	}

	/**
	 * classe model associata
	 */
	public Class modelClass() {
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel.class;
	}

	/**
	 * I singoli eventi sui singoli widget sono gestiti dai metodi specifici
	 * @return SUCCESS.
	 */
	@SkipValidation
	public String execute() throws CommandExecutionException {
		// esegue eventuali comandi di clear appdata
		ICommand clearCmd = (ICommand) session
				.get(PENDING_CLEAR_COMMAND_ATTRIBUTE);
		if (clearCmd != null) {
			clearCmd.doCommand(this);
			session.remove(PENDING_CLEAR_COMMAND_ATTRIBUTE);
		}
		return SUCCESS;
	}

	//////////////////////////////////////////////////////////////////////////////////
	/// metodi specifici per la gestione del singolo tipo di evento sul singolo widget
	/// contenuto nel contentPanel
	/// metodo: handle<nomeWidget>_<NOME_EVENTO>
	/// es: handletreeVoci_CLICKED
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCerca]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCerca_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnCerca", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneTransazioniAction::handleBtnCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnPrevPage]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnPrevPage_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnPrevPage", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneTransazioniAction::handleBtnPrevPage_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnPrevPage_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnPrevPage_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnPrevPage_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnPrevPage_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnNextPage]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnNextPage_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnNextPage", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneTransazioniAction::handleBtnNextPage_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnNextPage_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnNextPage_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnNextPage_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnNextPage_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnDettaglio]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnDettaglio_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnDettaglio", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneTransazioniAction::handleBtnDettaglio_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnDettaglio_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnDettaglio_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneTransazioniAction::handleBtnDettaglio_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneTransazioniAction::handleBtnDettaglio_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	//////////////////////////////////////////////////////////////////////////////////
	/// metodo di data providing sull'intero ContentPanel
	/// metodo: provide_CPDATA
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	/// metodi di data providing sui widget dotati di multi data binding
	/// contenuti nel contentPanel
	/// metodo: provide<nomeWidget>_<tipologia dati>
	/// es: provideCbComuni_DATASET
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbApplicazione.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbApplicazione_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataapplicazioni");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbStato.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbStato_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatastatiTransazione");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget tRicerca.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTRicerca_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatatransazioni");

		if (isTableClearStatus("cpGestioneTransazioni_tRicerca")) {
			tableStatus.setClearStatus(true);
		}
		TableDataArranger arranger = new TableDataArranger();
		if (dataToProvide != null) {
			dataToProvide = arranger.arrange((List) dataToProvide, tableStatus,
					filter);
		}

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R-1045881082) ENABLED START*/
		/* Inserire la validazione */
		/*PROTECTED REGION END*/
	}

	protected Map<String, UISecConstraint> allVisibilityConstraints = null;
	protected Map<String, UISecConstraint> allOnOffConstraints = null;

	/**
	 * Metodo di preparazione della schermata/action
	 */
	public void prepare() throws CommandExecutionException {
		super.prepare();

		// cancellazione eventuale degli errori di conversione non desiderati
		clearConversionErrorsIfSkipValidation();

		// caricamento struttura di constraints
		if (allVisibilityConstraints == null)
			allVisibilityConstraints = getPageVisibilityUIConstraints();
		if (allOnOffConstraints == null)
			allOnOffConstraints = getPageONOFFUIConstraints();

		// comandi eseguiti ad ogni refresh solo se non sono in modalita' fatclient
		ActionContext ctx = ActionContext.getContext();
		String methodName = ctx.getActionInvocation().getProxy().getMethod();

		if (methodName.startsWith("execute")
				|| (!methodName.startsWith("exec") && !methodName
						.startsWith("provide"))) {
			// comandi eseguiti ad ogni refresh solo per i metodi non fatclient
			ICommand cmd = initOnRefreshCommand();
			cmd.doCommand(this);
		}

	}

	// ridefinizione dei metodi di verifica visibilita'/validazione
	// per supportare i security check

	public boolean isWidgetDisabled(String cpName, String widgShortName) {
		UISecConstraint ctr = allOnOffConstraints.get(widgShortName);
		if (ctr != null) {
			try {
				return !ctr.verifyConstraint(session,
						UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
						getSpringSecurityHelper());
			} catch (BEException ex) {
				log.error("[CpGestioneTransazioniAction::isWidgetDisabled] errore durante verifica->disable");
				return true; // forzo la DISABILITAZIONE
			}
		} else
			return super.isWidgetDisabled(cpName, widgShortName);
	}

	public boolean isWidgetVisible(String cpName, String widgShortName) {
		UISecConstraint ctr = allVisibilityConstraints.get(widgShortName);
		if (ctr != null) {
			try {
				return ctr.verifyConstraint(session,
						UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
						getSpringSecurityHelper());
			} catch (BEException ex) {
				log.error("[CpGestioneTransazioniAction::isWidgetVisible] errore durante verifica->hide");
				return false; // forzo l'invisibilita'
			}
		} else
			return super.isWidgetVisible(cpName, widgShortName);
	}

	/**
	 * inizializza la struttura dei command da eseguire al refresh del pannello
	 */
	private ICommand initOnRefreshCommand() {
		// ExecCommand begin
		String[] resultNames4refreshPanel = new String[]{"Ok_Iniziale",
				"Ok_No_Results", "Ok_Some_Results"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[3];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_inizialeActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca"};

		String[] act_onRefresh_resok_inizialeActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "tRicerca",
				"btnDettaglio"};

		String[] act_onRefresh_resok_inizialeActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca"};

		String[] act_onRefresh_resok_inizialeActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "tRicerca",
				"btnDettaglio"};

		ScreenStateCommand act_onRefresh_resok_inizialeActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "INIZIALE",
				act_onRefresh_resok_inizialeActionstep_0_on,
				act_onRefresh_resok_inizialeActionstep_0_off,
				act_onRefresh_resok_inizialeActionstep_0_show,
				act_onRefresh_resok_inizialeActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_inizialeAction_0_steps = new ICommand[]{act_onRefresh_resok_inizialeActionstep_0};
		SequenceCommand act_onRefresh_resok_inizialeAction_0 = new SequenceCommand(
				act_onRefresh_resok_inizialeAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resok_inizialeAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_no_resultsActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_onRefresh_resok_no_resultsActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		String[] act_onRefresh_resok_no_resultsActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_onRefresh_resok_no_resultsActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		ScreenStateCommand act_onRefresh_resok_no_resultsActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "NO_RESULTS",
				act_onRefresh_resok_no_resultsActionstep_0_on,
				act_onRefresh_resok_no_resultsActionstep_0_off,
				act_onRefresh_resok_no_resultsActionstep_0_show,
				act_onRefresh_resok_no_resultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_no_resultsAction_1_steps = new ICommand[]{act_onRefresh_resok_no_resultsActionstep_0};
		SequenceCommand act_onRefresh_resok_no_resultsAction_1 = new SequenceCommand(
				act_onRefresh_resok_no_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resok_no_resultsAction_1;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_onRefresh_resok_some_resultsActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_onRefresh_resok_some_resultsAction_2_steps = new ICommand[]{act_onRefresh_resok_some_resultsActionstep_0};
		SequenceCommand act_onRefresh_resok_some_resultsAction_2 = new SequenceCommand(
				act_onRefresh_resok_some_resultsAction_2_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[2] = act_onRefresh_resok_some_resultsAction_2;

		ExecCommand act_onRefresh_1 = new ExecCommand(resultNames4refreshPanel,
				actionsForResults4refreshPanel) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.refreshPanel(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneTransazioniAction",
							"readOne()", "chiamata verso BackEnd",
							"refreshPanel");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [refreshPanel]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_onRefresh_1;
	}

	protected Map<String, UISecConstraint> getPageVisibilityUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "idTransazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultVisib_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per cbApplicazione
		UISecConstraint cbApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "cbApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbApplicazione_constraints = new UISecConstraint[]{cbApplicazione_defaultVisib_ctr};
		UISecConstraint cbApplicazione_ctr = new ComplexUISecConstraint(
				cbApplicazione_constraints);
		allConstraints.put("cbApplicazione", cbApplicazione_ctr);

		// constraint fittizio per cbStato
		UISecConstraint cbStato_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "cbStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbStato_constraints = new UISecConstraint[]{cbStato_defaultVisib_ctr};
		UISecConstraint cbStato_ctr = new ComplexUISecConstraint(
				cbStato_constraints);
		allConstraints.put("cbStato", cbStato_ctr);

		// constraint fittizio per calDataInizio
		UISecConstraint calDataInizio_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "calDataInizio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] calDataInizio_constraints = new UISecConstraint[]{calDataInizio_defaultVisib_ctr};
		UISecConstraint calDataInizio_ctr = new ComplexUISecConstraint(
				calDataInizio_constraints);
		allConstraints.put("calDataInizio", calDataInizio_ctr);

		// constraint fittizio per calDataFine
		UISecConstraint calDataFine_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "calDataFine",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] calDataFine_constraints = new UISecConstraint[]{calDataFine_defaultVisib_ctr};
		UISecConstraint calDataFine_ctr = new ComplexUISecConstraint(
				calDataFine_constraints);
		allConstraints.put("calDataFine", calDataFine_ctr);

		// constraint fittizio per btnCerca
		UISecConstraint btnCerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnCerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCerca_constraints = new UISecConstraint[]{btnCerca_defaultVisib_ctr};
		UISecConstraint btnCerca_ctr = new ComplexUISecConstraint(
				btnCerca_constraints);
		allConstraints.put("btnCerca", btnCerca_ctr);

		// constraint fittizio per ptReportRicerca
		UISecConstraint ptReportRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "ptReportRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptReportRicerca_constraints = new UISecConstraint[]{ptReportRicerca_defaultVisib_ctr};
		UISecConstraint ptReportRicerca_ctr = new ComplexUISecConstraint(
				ptReportRicerca_constraints);
		allConstraints.put("ptReportRicerca", ptReportRicerca_ctr);

		// constraint fittizio per btnPrevPage
		UISecConstraint btnPrevPage_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnPrevPage",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnPrevPage_constraints = new UISecConstraint[]{btnPrevPage_defaultVisib_ctr};
		UISecConstraint btnPrevPage_ctr = new ComplexUISecConstraint(
				btnPrevPage_constraints);
		allConstraints.put("btnPrevPage", btnPrevPage_ctr);

		// constraint fittizio per btnNextPage
		UISecConstraint btnNextPage_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnNextPage",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNextPage_constraints = new UISecConstraint[]{btnNextPage_defaultVisib_ctr};
		UISecConstraint btnNextPage_ctr = new ComplexUISecConstraint(
				btnNextPage_constraints);
		allConstraints.put("btnNextPage", btnNextPage_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "tRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultVisib_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per btnDettaglio
		UISecConstraint btnDettaglio_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnDettaglio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnDettaglio_constraints = new UISecConstraint[]{btnDettaglio_defaultVisib_ctr};
		UISecConstraint btnDettaglio_ctr = new ComplexUISecConstraint(
				btnDettaglio_constraints);
		allConstraints.put("btnDettaglio", btnDettaglio_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "idTransazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultOnoff_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per cbApplicazione
		UISecConstraint cbApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "cbApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbApplicazione_constraints = new UISecConstraint[]{cbApplicazione_defaultOnoff_ctr};
		UISecConstraint cbApplicazione_ctr = new ComplexUISecConstraint(
				cbApplicazione_constraints);
		allConstraints.put("cbApplicazione", cbApplicazione_ctr);

		// constraint fittizio per cbStato
		UISecConstraint cbStato_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "cbStato",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbStato_constraints = new UISecConstraint[]{cbStato_defaultOnoff_ctr};
		UISecConstraint cbStato_ctr = new ComplexUISecConstraint(
				cbStato_constraints);
		allConstraints.put("cbStato", cbStato_ctr);

		// constraint fittizio per calDataInizio
		UISecConstraint calDataInizio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "calDataInizio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] calDataInizio_constraints = new UISecConstraint[]{calDataInizio_defaultOnoff_ctr};
		UISecConstraint calDataInizio_ctr = new ComplexUISecConstraint(
				calDataInizio_constraints);
		allConstraints.put("calDataInizio", calDataInizio_ctr);

		// constraint fittizio per calDataFine
		UISecConstraint calDataFine_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "calDataFine",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] calDataFine_constraints = new UISecConstraint[]{calDataFine_defaultOnoff_ctr};
		UISecConstraint calDataFine_ctr = new ComplexUISecConstraint(
				calDataFine_constraints);
		allConstraints.put("calDataFine", calDataFine_ctr);

		// constraint fittizio per btnCerca
		UISecConstraint btnCerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnCerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCerca_constraints = new UISecConstraint[]{btnCerca_defaultOnoff_ctr};
		UISecConstraint btnCerca_ctr = new ComplexUISecConstraint(
				btnCerca_constraints);
		allConstraints.put("btnCerca", btnCerca_ctr);

		// constraint fittizio per ptReportRicerca
		UISecConstraint ptReportRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "ptReportRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptReportRicerca_constraints = new UISecConstraint[]{ptReportRicerca_defaultOnoff_ctr};
		UISecConstraint ptReportRicerca_ctr = new ComplexUISecConstraint(
				ptReportRicerca_constraints);
		allConstraints.put("ptReportRicerca", ptReportRicerca_ctr);

		// constraint fittizio per btnPrevPage
		UISecConstraint btnPrevPage_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnPrevPage",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnPrevPage_constraints = new UISecConstraint[]{btnPrevPage_defaultOnoff_ctr};
		UISecConstraint btnPrevPage_ctr = new ComplexUISecConstraint(
				btnPrevPage_constraints);
		allConstraints.put("btnPrevPage", btnPrevPage_ctr);

		// constraint fittizio per btnNextPage
		UISecConstraint btnNextPage_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnNextPage",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNextPage_constraints = new UISecConstraint[]{btnNextPage_defaultOnoff_ctr};
		UISecConstraint btnNextPage_ctr = new ComplexUISecConstraint(
				btnNextPage_constraints);
		allConstraints.put("btnNextPage", btnNextPage_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "tRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultOnoff_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per btnDettaglio
		UISecConstraint btnDettaglio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneTransazioni", "btnDettaglio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnDettaglio_constraints = new UISecConstraint[]{btnDettaglio_defaultOnoff_ctr};
		UISecConstraint btnDettaglio_ctr = new ComplexUISecConstraint(
				btnDettaglio_constraints);
		allConstraints.put("btnDettaglio", btnDettaglio_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneTransazioniAction::dumpmodel] START");

		log.debug("[CpGestioneTransazioniAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneTransazioniAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneTransazioniAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneTransazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneTransazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneTransazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpGestioneTransazioniAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneTransazioni");
		log.debug("[CpGestioneTransazioniAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneTransazioniAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneTransazioniAction::dumpmodel] " + session);
	}

	static final String PENDING_CLEAR_COMMAND_ATTRIBUTE = "_pending_clear_command_";
	/**
	 *	Metodo per la rimozione dalla sessione degli application data a scope
	 *  SAME_PAGE. 
	 */
	public void clearPageScopedAppData(String targetContentPanelName) {
		// nothing to clear...
	}

	/**
	 * inizializza la struttura dei command da eseguire per ciascun event handler 
	 * di ciascun widget
	 */
	private ICommand initCommand(String sourceWidget, String eventType) {
		HashMap<String, HashMap<String, ICommand>> cmdsByWidget = new HashMap<String, HashMap<String, ICommand>>();

		// contiene i comandi del widget btnCerca per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCercaByEvh = new HashMap<String, ICommand>();

		cmds4btnCercaByEvh.put("CLICKED", initCommandBtnCerca_CLICKED());
		cmdsByWidget.put("btnCerca", cmds4btnCercaByEvh);
		// contiene i comandi del widget btnPrevPage per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnPrevPageByEvh = new HashMap<String, ICommand>();

		cmds4btnPrevPageByEvh.put("CLICKED", initCommandBtnPrevPage_CLICKED());
		cmdsByWidget.put("btnPrevPage", cmds4btnPrevPageByEvh);
		// contiene i comandi del widget btnNextPage per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnNextPageByEvh = new HashMap<String, ICommand>();

		cmds4btnNextPageByEvh.put("CLICKED", initCommandBtnNextPage_CLICKED());
		cmdsByWidget.put("btnNextPage", cmds4btnNextPageByEvh);
		// contiene i comandi del widget btnDettaglio per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnDettaglioByEvh = new HashMap<String, ICommand>();

		cmds4btnDettaglioByEvh
				.put("CLICKED", initCommandBtnDettaglio_CLICKED());
		cmdsByWidget.put("btnDettaglio", cmds4btnDettaglioByEvh);

		ICommand ris = cmdsByWidget.get(sourceWidget).get(eventType);
		if (ris != null)
			return ris;
		else
			return new ICommand() { // NOP..
				public String doCommand(BaseAction strutsAction)
						throws CommandExecutionException {
					return null;
				}
			};
	}

	private ICommand initCommandBtnCerca_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cerca = new String[]{"Ok_No_Results",
				"Ok_Some_Results", "Ok_Prev_Next", "Ok_Prev", "Ok_Next", "Ko"};

		ICommand[] actionsForResults4cerca = new ICommand[6];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_no_resultsActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "NO_RESULTS",
				act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_on,
				act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_off,
				act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_show,
				act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_no_resultsAction_0_steps = new ICommand[]{act_actions_clicked_btnCerca_resok_no_resultsActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_resok_no_resultsAction_0 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_no_resultsAction_0_steps);
		// SequenceCommand end
		actionsForResults4cerca[0] = act_actions_clicked_btnCerca_resok_no_resultsAction_0;
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_appdataToBeRemovedFromSession = new String[]{"selettoreIdTransazione"};

		ClearAppDataCommand act_actions_clicked_btnCerca_resok_some_resultsActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "btnDettaglio", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"btnDettaglio", "idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_some_resultsActionstep_1 = new ScreenStateCommand(
				"cpGestioneTransazioni",
				"SOME_RESULTS",
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_on,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_off,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_show,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_some_resultsAction_1_steps = new ICommand[]{
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1};
		SequenceCommand act_actions_clicked_btnCerca_resok_some_resultsAction_1 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_some_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4cerca[1] = act_actions_clicked_btnCerca_resok_some_resultsAction_1;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"btnCerca", "btnPrevPage", "tRicerca", "btnDettaglio",
				"btnNextPage", "calDataInizio", "calDataFine",
				"ptReportRicerca"};

		String[] act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_off = new String[]{};

		String[] act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"btnCerca", "btnPrevPage", "tRicerca", "btnDettaglio",
				"btnNextPage", "calDataInizio", "calDataFine",
				"ptReportRicerca"};

		String[] act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_prev_nextActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_PREV_NEXT",
				act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_on,
				act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_off,
				act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_show,
				act_actions_clicked_btnCerca_resok_prev_nextActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_prev_nextAction_2_steps = new ICommand[]{act_actions_clicked_btnCerca_resok_prev_nextActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_resok_prev_nextAction_2 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_prev_nextAction_2_steps);
		// SequenceCommand end
		actionsForResults4cerca[2] = act_actions_clicked_btnCerca_resok_prev_nextAction_2;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_prevActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "calDataFine",
				"calDataInizio", "cbStato", "btnCerca", "btnPrevPage",
				"tRicerca", "ptReportRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnCerca_resok_prevActionstep_0_off = new String[]{"btnNextPage"};

		String[] act_actions_clicked_btnCerca_resok_prevActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "calDataFine",
				"calDataInizio", "cbStato", "btnCerca", "btnPrevPage",
				"tRicerca", "ptReportRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnCerca_resok_prevActionstep_0_hide = new String[]{"btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_prevActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_PREV",
				act_actions_clicked_btnCerca_resok_prevActionstep_0_on,
				act_actions_clicked_btnCerca_resok_prevActionstep_0_off,
				act_actions_clicked_btnCerca_resok_prevActionstep_0_show,
				act_actions_clicked_btnCerca_resok_prevActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_prevAction_3_steps = new ICommand[]{act_actions_clicked_btnCerca_resok_prevActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_resok_prevAction_3 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_prevAction_3_steps);
		// SequenceCommand end
		actionsForResults4cerca[3] = act_actions_clicked_btnCerca_resok_prevAction_3;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_nextActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca", "ptReportRicerca",
				"btnNextPage", "tRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnCerca_resok_nextActionstep_0_off = new String[]{"btnPrevPage"};

		String[] act_actions_clicked_btnCerca_resok_nextActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca", "ptReportRicerca",
				"btnNextPage", "tRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnCerca_resok_nextActionstep_0_hide = new String[]{"btnPrevPage"};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_nextActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_NEXT",
				act_actions_clicked_btnCerca_resok_nextActionstep_0_on,
				act_actions_clicked_btnCerca_resok_nextActionstep_0_off,
				act_actions_clicked_btnCerca_resok_nextActionstep_0_show,
				act_actions_clicked_btnCerca_resok_nextActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_nextAction_4_steps = new ICommand[]{act_actions_clicked_btnCerca_resok_nextActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_resok_nextAction_4 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_nextAction_4_steps);
		// SequenceCommand end
		actionsForResults4cerca[4] = act_actions_clicked_btnCerca_resok_nextAction_4;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCerca_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCerca_reskoAction_5_steps = new ICommand[]{act_actions_clicked_btnCerca_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_reskoAction_5 = new SequenceCommand(
				act_actions_clicked_btnCerca_reskoAction_5_steps);
		// SequenceCommand end
		actionsForResults4cerca[5] = act_actions_clicked_btnCerca_reskoAction_5;

		ExecCommand act_actions_clicked_btnCerca_1 = new ExecCommand(
				resultNames4cerca, actionsForResults4cerca) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cerca((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneTransazioniAction",
							"readOne()", "chiamata verso BackEnd", "cerca");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cerca]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCerca_1;
	}

	private ICommand initCommandBtnPrevPage_CLICKED() {
		// ExecCommand begin
		String[] resultNames4previousPage = new String[]{"Ok", "Ok_Prev_Next",
				"Ok_Prev", "Ok_Next", "Ok_No_Result", "Ko"};

		ICommand[] actionsForResults4previousPage = new ICommand[6];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnPrevPage_resokActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "btnDettaglio", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnPrevPage_resokActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		String[] act_actions_clicked_btnPrevPage_resokActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"btnDettaglio", "idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnPrevPage_resokActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnPrevPage_resokActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS",
				act_actions_clicked_btnPrevPage_resokActionstep_0_on,
				act_actions_clicked_btnPrevPage_resokActionstep_0_off,
				act_actions_clicked_btnPrevPage_resokActionstep_0_show,
				act_actions_clicked_btnPrevPage_resokActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnPrevPage_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnPrevPage_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnPrevPage_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnPrevPage_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4previousPage[0] = act_actions_clicked_btnPrevPage_resokAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"btnCerca", "btnPrevPage", "tRicerca", "btnDettaglio",
				"btnNextPage", "calDataInizio", "calDataFine",
				"ptReportRicerca"};

		String[] act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_off = new String[]{};

		String[] act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"btnCerca", "btnPrevPage", "tRicerca", "btnDettaglio",
				"btnNextPage", "calDataInizio", "calDataFine",
				"ptReportRicerca"};

		String[] act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni",
				"SOME_RESULTS_PREV_NEXT",
				act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_on,
				act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_off,
				act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_show,
				act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnPrevPage_resok_prev_nextAction_1_steps = new ICommand[]{act_actions_clicked_btnPrevPage_resok_prev_nextActionstep_0};
		SequenceCommand act_actions_clicked_btnPrevPage_resok_prev_nextAction_1 = new SequenceCommand(
				act_actions_clicked_btnPrevPage_resok_prev_nextAction_1_steps);
		// SequenceCommand end
		actionsForResults4previousPage[1] = act_actions_clicked_btnPrevPage_resok_prev_nextAction_1;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnPrevPage_resok_prevActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "calDataFine",
				"calDataInizio", "cbStato", "btnCerca", "btnPrevPage",
				"tRicerca", "ptReportRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnPrevPage_resok_prevActionstep_0_off = new String[]{"btnNextPage"};

		String[] act_actions_clicked_btnPrevPage_resok_prevActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "calDataFine",
				"calDataInizio", "cbStato", "btnCerca", "btnPrevPage",
				"tRicerca", "ptReportRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnPrevPage_resok_prevActionstep_0_hide = new String[]{"btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnPrevPage_resok_prevActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_PREV",
				act_actions_clicked_btnPrevPage_resok_prevActionstep_0_on,
				act_actions_clicked_btnPrevPage_resok_prevActionstep_0_off,
				act_actions_clicked_btnPrevPage_resok_prevActionstep_0_show,
				act_actions_clicked_btnPrevPage_resok_prevActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnPrevPage_resok_prevAction_2_steps = new ICommand[]{act_actions_clicked_btnPrevPage_resok_prevActionstep_0};
		SequenceCommand act_actions_clicked_btnPrevPage_resok_prevAction_2 = new SequenceCommand(
				act_actions_clicked_btnPrevPage_resok_prevAction_2_steps);
		// SequenceCommand end
		actionsForResults4previousPage[2] = act_actions_clicked_btnPrevPage_resok_prevAction_2;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnPrevPage_resok_nextActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca", "ptReportRicerca",
				"btnNextPage", "tRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnPrevPage_resok_nextActionstep_0_off = new String[]{"btnPrevPage"};

		String[] act_actions_clicked_btnPrevPage_resok_nextActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca", "ptReportRicerca",
				"btnNextPage", "tRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnPrevPage_resok_nextActionstep_0_hide = new String[]{"btnPrevPage"};

		ScreenStateCommand act_actions_clicked_btnPrevPage_resok_nextActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_NEXT",
				act_actions_clicked_btnPrevPage_resok_nextActionstep_0_on,
				act_actions_clicked_btnPrevPage_resok_nextActionstep_0_off,
				act_actions_clicked_btnPrevPage_resok_nextActionstep_0_show,
				act_actions_clicked_btnPrevPage_resok_nextActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnPrevPage_resok_nextAction_3_steps = new ICommand[]{act_actions_clicked_btnPrevPage_resok_nextActionstep_0};
		SequenceCommand act_actions_clicked_btnPrevPage_resok_nextAction_3 = new SequenceCommand(
				act_actions_clicked_btnPrevPage_resok_nextAction_3_steps);
		// SequenceCommand end
		actionsForResults4previousPage[3] = act_actions_clicked_btnPrevPage_resok_nextAction_3;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		String[] act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		ScreenStateCommand act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni",
				"NO_RESULTS",
				act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_on,
				act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_off,
				act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_show,
				act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnPrevPage_resok_no_resultAction_4_steps = new ICommand[]{act_actions_clicked_btnPrevPage_resok_no_resultActionstep_0};
		SequenceCommand act_actions_clicked_btnPrevPage_resok_no_resultAction_4 = new SequenceCommand(
				act_actions_clicked_btnPrevPage_resok_no_resultAction_4_steps);
		// SequenceCommand end
		actionsForResults4previousPage[4] = act_actions_clicked_btnPrevPage_resok_no_resultAction_4;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnPrevPage_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnPrevPage_reskoAction_5_steps = new ICommand[]{act_actions_clicked_btnPrevPage_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnPrevPage_reskoAction_5 = new SequenceCommand(
				act_actions_clicked_btnPrevPage_reskoAction_5_steps);
		// SequenceCommand end
		actionsForResults4previousPage[5] = act_actions_clicked_btnPrevPage_reskoAction_5;

		ExecCommand act_actions_clicked_btnPrevPage_1 = new ExecCommand(
				resultNames4previousPage, actionsForResults4previousPage) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.previousPage(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneTransazioniAction",
							"readOne()", "chiamata verso BackEnd",
							"previousPage");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [previousPage]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnPrevPage_1;
	}

	private ICommand initCommandBtnNextPage_CLICKED() {
		// ExecCommand begin
		String[] resultNames4nextPage = new String[]{"Ok", "Ok_Prev_Next",
				"Ok_Prev", "Ok_Next", "Ok_No_Result", "Ko"};

		ICommand[] actionsForResults4nextPage = new ICommand[6];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnNextPage_resokActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "btnDettaglio", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnNextPage_resokActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		String[] act_actions_clicked_btnNextPage_resokActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"btnDettaglio", "idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnNextPage_resokActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnNextPage_resokActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS",
				act_actions_clicked_btnNextPage_resokActionstep_0_on,
				act_actions_clicked_btnNextPage_resokActionstep_0_off,
				act_actions_clicked_btnNextPage_resokActionstep_0_show,
				act_actions_clicked_btnNextPage_resokActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnNextPage_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnNextPage_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnNextPage_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnNextPage_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4nextPage[0] = act_actions_clicked_btnNextPage_resokAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"btnCerca", "btnPrevPage", "tRicerca", "btnDettaglio",
				"btnNextPage", "calDataInizio", "calDataFine",
				"ptReportRicerca"};

		String[] act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_off = new String[]{};

		String[] act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"btnCerca", "btnPrevPage", "tRicerca", "btnDettaglio",
				"btnNextPage", "calDataInizio", "calDataFine",
				"ptReportRicerca"};

		String[] act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni",
				"SOME_RESULTS_PREV_NEXT",
				act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_on,
				act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_off,
				act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_show,
				act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnNextPage_resok_prev_nextAction_1_steps = new ICommand[]{act_actions_clicked_btnNextPage_resok_prev_nextActionstep_0};
		SequenceCommand act_actions_clicked_btnNextPage_resok_prev_nextAction_1 = new SequenceCommand(
				act_actions_clicked_btnNextPage_resok_prev_nextAction_1_steps);
		// SequenceCommand end
		actionsForResults4nextPage[1] = act_actions_clicked_btnNextPage_resok_prev_nextAction_1;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnNextPage_resok_prevActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "calDataFine",
				"calDataInizio", "cbStato", "btnCerca", "btnPrevPage",
				"tRicerca", "ptReportRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnNextPage_resok_prevActionstep_0_off = new String[]{"btnNextPage"};

		String[] act_actions_clicked_btnNextPage_resok_prevActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "calDataFine",
				"calDataInizio", "cbStato", "btnCerca", "btnPrevPage",
				"tRicerca", "ptReportRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnNextPage_resok_prevActionstep_0_hide = new String[]{"btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnNextPage_resok_prevActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_PREV",
				act_actions_clicked_btnNextPage_resok_prevActionstep_0_on,
				act_actions_clicked_btnNextPage_resok_prevActionstep_0_off,
				act_actions_clicked_btnNextPage_resok_prevActionstep_0_show,
				act_actions_clicked_btnNextPage_resok_prevActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnNextPage_resok_prevAction_2_steps = new ICommand[]{act_actions_clicked_btnNextPage_resok_prevActionstep_0};
		SequenceCommand act_actions_clicked_btnNextPage_resok_prevAction_2 = new SequenceCommand(
				act_actions_clicked_btnNextPage_resok_prevAction_2_steps);
		// SequenceCommand end
		actionsForResults4nextPage[2] = act_actions_clicked_btnNextPage_resok_prevAction_2;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnNextPage_resok_nextActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca", "ptReportRicerca",
				"btnNextPage", "tRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnNextPage_resok_nextActionstep_0_off = new String[]{"btnPrevPage"};

		String[] act_actions_clicked_btnNextPage_resok_nextActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbStato",
				"calDataInizio", "calDataFine", "btnCerca", "ptReportRicerca",
				"btnNextPage", "tRicerca", "btnDettaglio"};

		String[] act_actions_clicked_btnNextPage_resok_nextActionstep_0_hide = new String[]{"btnPrevPage"};

		ScreenStateCommand act_actions_clicked_btnNextPage_resok_nextActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS_NEXT",
				act_actions_clicked_btnNextPage_resok_nextActionstep_0_on,
				act_actions_clicked_btnNextPage_resok_nextActionstep_0_off,
				act_actions_clicked_btnNextPage_resok_nextActionstep_0_show,
				act_actions_clicked_btnNextPage_resok_nextActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnNextPage_resok_nextAction_3_steps = new ICommand[]{act_actions_clicked_btnNextPage_resok_nextActionstep_0};
		SequenceCommand act_actions_clicked_btnNextPage_resok_nextAction_3 = new SequenceCommand(
				act_actions_clicked_btnNextPage_resok_nextAction_3_steps);
		// SequenceCommand end
		actionsForResults4nextPage[3] = act_actions_clicked_btnNextPage_resok_nextAction_3;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		String[] act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage", "btnDettaglio"};

		ScreenStateCommand act_actions_clicked_btnNextPage_resok_no_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni",
				"NO_RESULTS",
				act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_on,
				act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_off,
				act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_show,
				act_actions_clicked_btnNextPage_resok_no_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnNextPage_resok_no_resultAction_4_steps = new ICommand[]{act_actions_clicked_btnNextPage_resok_no_resultActionstep_0};
		SequenceCommand act_actions_clicked_btnNextPage_resok_no_resultAction_4 = new SequenceCommand(
				act_actions_clicked_btnNextPage_resok_no_resultAction_4_steps);
		// SequenceCommand end
		actionsForResults4nextPage[4] = act_actions_clicked_btnNextPage_resok_no_resultAction_4;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnNextPage_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnNextPage_reskoAction_5_steps = new ICommand[]{act_actions_clicked_btnNextPage_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnNextPage_reskoAction_5 = new SequenceCommand(
				act_actions_clicked_btnNextPage_reskoAction_5_steps);
		// SequenceCommand end
		actionsForResults4nextPage[5] = act_actions_clicked_btnNextPage_reskoAction_5;

		ExecCommand act_actions_clicked_btnNextPage_1 = new ExecCommand(
				resultNames4nextPage, actionsForResults4nextPage) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.nextPage(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneTransazioniAction",
							"readOne()", "chiamata verso BackEnd", "nextPage");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [nextPage]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnNextPage_1;
	}

	private ICommand initCommandBtnDettaglio_CLICKED() {
		// ExecCommand begin
		String[] resultNames4dettaglioTransazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4dettaglioTransazione = new ICommand[2];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnDettaglio_resokActionstep_0 = new JumpCommand(
				"cpDettaglioTransazione", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnDettaglio_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnDettaglio_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnDettaglio_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnDettaglio_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4dettaglioTransazione[0] = act_actions_clicked_btnDettaglio_resokAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnDettaglio_reskoActionstep_0_on = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"idTransazione", "btnDettaglio", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnDettaglio_reskoActionstep_0_off = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		String[] act_actions_clicked_btnDettaglio_reskoActionstep_0_show = new String[]{
				"mnuView", "cbApplicazione", "cbStato", "btnCerca", "tRicerca",
				"btnDettaglio", "idTransazione", "calDataInizio", "calDataFine"};

		String[] act_actions_clicked_btnDettaglio_reskoActionstep_0_hide = new String[]{
				"ptReportRicerca", "btnPrevPage", "btnNextPage"};

		ScreenStateCommand act_actions_clicked_btnDettaglio_reskoActionstep_0 = new ScreenStateCommand(
				"cpGestioneTransazioni", "SOME_RESULTS",
				act_actions_clicked_btnDettaglio_reskoActionstep_0_on,
				act_actions_clicked_btnDettaglio_reskoActionstep_0_off,
				act_actions_clicked_btnDettaglio_reskoActionstep_0_show,
				act_actions_clicked_btnDettaglio_reskoActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnDettaglio_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnDettaglio_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnDettaglio_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnDettaglio_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4dettaglioTransazione[1] = act_actions_clicked_btnDettaglio_reskoAction_1;

		ExecCommand act_actions_clicked_btnDettaglio_1 = new ExecCommand(
				resultNames4dettaglioTransazione,
				actionsForResults4dettaglioTransazione) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.dettaglioTransazione(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneTransazioniAction",
							"readOne()", "chiamata verso BackEnd",
							"dettaglioTransazione");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [dettaglioTransazione]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnDettaglio_1;
	}

	@SkipValidation
	public String handleChangeTab() {
		if (this.hasActionErrors() || this.hasFieldErrors() || this.hasErrors())
			return INPUT;
		else {
			session.put(selectedTabKey, selectedTabValue);
			return SUCCESS;
		}
	}

}
