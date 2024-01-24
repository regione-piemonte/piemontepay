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
 * CpDettaglioGruppoAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioGruppoAction extends BaseAction implements Preparable {

	// Table tbListaUtenti

	private java.util.ArrayList<java.lang.String> _widg_tbListaUtenti;

	public void setWidg_tbListaUtenti(
			java.util.ArrayList<java.lang.String> value) {
		_widg_tbListaUtenti = value;
	}

	public java.util.ArrayList<java.lang.String> getWidg_tbListaUtenti() {
		return _widg_tbListaUtenti;
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataselettoreIdRuolo(java.lang.String value) {
		getSession().put("appDataselettoreIdRuolo", value);
	}

	public java.lang.String getAppDataselettoreIdRuolo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRuolo"));
	}

	public void setAppDataappForGroup(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataappForGroup", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataappForGroup() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataappForGroup"));
	}

	public void setAppDataappForGroupFiltered(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataappForGroupFiltered", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataappForGroupFiltered() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataappForGroupFiltered"));
	}

	public void setAppDataselettoreAppGroup(java.lang.String value) {
		getSession().put("appDataselettoreAppGroup", value);
	}

	public java.lang.String getAppDataselettoreAppGroup() {
		return (java.lang.String) (getSession().get("appDataselettoreAppGroup"));
	}

	public void setAppDatafilteredApplicationGroup(
			java.util.ArrayList<java.lang.String> value) {
		getSession().put("appDatafilteredApplicationGroup", value);
	}

	public java.util.ArrayList<java.lang.String> getAppDatafilteredApplicationGroup() {
		return (java.util.ArrayList<java.lang.String>) (getSession()
				.get("appDatafilteredApplicationGroup"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDatagruppo(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo value) {
		getSession().put("appDatagruppo", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo getAppDatagruppo() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo) (getSession()
				.get("appDatagruppo"));
	}

	public void setAppDataruoli(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo> value) {
		getSession().put("appDataruoli", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo> getAppDataruoli() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo>) (getSession()
				.get("appDataruoli"));
	}

	public void setAppDataruolo(it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo value) {
		getSession().put("appDataruolo", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo getAppDataruolo() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo) (getSession()
				.get("appDataruolo"));
	}

	public void setAppDataapplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataapplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataapplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataapplicazioni"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDataselettoreGruppo(java.lang.String value) {
		getSession().put("appDataselettoreGruppo", value);
	}

	public java.lang.String getAppDataselettoreGruppo() {
		return (java.lang.String) (getSession().get("appDataselettoreGruppo"));
	}

	public void setAppDatautentiDelGruppo(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> value) {
		getSession().put("appDatautentiDelGruppo", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getAppDatautentiDelGruppo() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>) (getSession()
				.get("appDatautentiDelGruppo"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnAddAppFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnAddAppFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnAddAppFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGruppoAction::handleBtnAddAppFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtnAddAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtnAddAppFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtnAddAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtnAddAppFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnEliminAppFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnEliminAppFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnEliminAppFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGruppoAction::handleBtnEliminAppFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtnEliminAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtnEliminAppFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtnEliminAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtnEliminAppFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btIndietro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btIndietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGruppoAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btSalva]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtSalva_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btSalva", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGruppoAction::handleBtSalva_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtSalva_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGruppoAction::handleBtSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGruppoAction::handleBtSalva_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbAppGroupToAdd.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAppGroupToAdd_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataappForGroup");

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
	 * al data-binding relativo al dataset DATASET del widget cbAppGruoupFiltered.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAppGruoupFiltered_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataappForGroupFiltered");

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
	 * al data-binding relativo al dataset DATASET del widget cbListaRuoli.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbListaRuoli_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDataruoli");

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
	 * al data-binding relativo al dataset DATASET del widget tbListaUtenti.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaUtenti_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatautentiDelGruppo");

		if (isTableClearStatus("cpDettaglioGruppo_tbListaUtenti")) {
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
		/*PROTECTED REGION ID(R-1444011236) ENABLED START*/
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
				log.error("[CpDettaglioGruppoAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioGruppoAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Ok"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resokActionstep_0_on = new String[]{"mnuView",
				"txIdGruppo", "txDescrizioneGruppo", "cbAppGroupToAdd",
				"btnAddAppFiltro", "btnEliminAppFiltro", "cbListaRuoli",
				"btIndietro", "btSalva", "cbAppGruoupFiltered", "tbListaUtenti"};

		String[] act_onRefresh_resokActionstep_0_off = new String[]{};

		String[] act_onRefresh_resokActionstep_0_show = new String[]{"mnuView",
				"txIdGruppo", "txDescrizioneGruppo", "cbAppGroupToAdd",
				"btnAddAppFiltro", "btnEliminAppFiltro", "cbListaRuoli",
				"btIndietro", "btSalva", "cbAppGruoupFiltered", "tbListaUtenti"};

		String[] act_onRefresh_resokActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resokActionstep_0 = new ScreenStateCommand(
				"cpDettaglioGruppo", "DETTAGLIO_GRUPPO",
				act_onRefresh_resokActionstep_0_on,
				act_onRefresh_resokActionstep_0_off,
				act_onRefresh_resokActionstep_0_show,
				act_onRefresh_resokActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resokAction_0_steps = new ICommand[]{act_onRefresh_resokActionstep_0};
		SequenceCommand act_onRefresh_resokAction_0 = new SequenceCommand(
				act_onRefresh_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resokAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGruppoAction", "readOne()",
							"chiamata verso BackEnd", "refreshPanel");
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

		new DummyUISecConstraint("cpDettaglioGruppo", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txIdGruppo
		UISecConstraint txIdGruppo_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "txIdGruppo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdGruppo_constraints = new UISecConstraint[]{txIdGruppo_defaultVisib_ctr};
		UISecConstraint txIdGruppo_ctr = new ComplexUISecConstraint(
				txIdGruppo_constraints);
		allConstraints.put("txIdGruppo", txIdGruppo_ctr);

		// constraint fittizio per txDescrizioneGruppo
		UISecConstraint txDescrizioneGruppo_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "txDescrizioneGruppo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDescrizioneGruppo_constraints = new UISecConstraint[]{txDescrizioneGruppo_defaultVisib_ctr};
		UISecConstraint txDescrizioneGruppo_ctr = new ComplexUISecConstraint(
				txDescrizioneGruppo_constraints);
		allConstraints.put("txDescrizioneGruppo", txDescrizioneGruppo_ctr);

		// constraint fittizio per cbAppGroupToAdd
		UISecConstraint cbAppGroupToAdd_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "cbAppGroupToAdd",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppGroupToAdd_constraints = new UISecConstraint[]{cbAppGroupToAdd_defaultVisib_ctr};
		UISecConstraint cbAppGroupToAdd_ctr = new ComplexUISecConstraint(
				cbAppGroupToAdd_constraints);
		allConstraints.put("cbAppGroupToAdd", cbAppGroupToAdd_ctr);

		// constraint fittizio per btnAddAppFiltro
		UISecConstraint btnAddAppFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btnAddAppFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddAppFiltro_constraints = new UISecConstraint[]{btnAddAppFiltro_defaultVisib_ctr};
		UISecConstraint btnAddAppFiltro_ctr = new ComplexUISecConstraint(
				btnAddAppFiltro_constraints);
		allConstraints.put("btnAddAppFiltro", btnAddAppFiltro_ctr);

		// constraint fittizio per cbAppGruoupFiltered
		UISecConstraint cbAppGruoupFiltered_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "cbAppGruoupFiltered",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppGruoupFiltered_constraints = new UISecConstraint[]{cbAppGruoupFiltered_defaultVisib_ctr};
		UISecConstraint cbAppGruoupFiltered_ctr = new ComplexUISecConstraint(
				cbAppGruoupFiltered_constraints);
		allConstraints.put("cbAppGruoupFiltered", cbAppGruoupFiltered_ctr);

		// constraint fittizio per btnEliminAppFiltro
		UISecConstraint btnEliminAppFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btnEliminAppFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminAppFiltro_constraints = new UISecConstraint[]{btnEliminAppFiltro_defaultVisib_ctr};
		UISecConstraint btnEliminAppFiltro_ctr = new ComplexUISecConstraint(
				btnEliminAppFiltro_constraints);
		allConstraints.put("btnEliminAppFiltro", btnEliminAppFiltro_ctr);

		// constraint fittizio per cbListaRuoli
		UISecConstraint cbListaRuoli_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "cbListaRuoli",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaRuoli_constraints = new UISecConstraint[]{cbListaRuoli_defaultVisib_ctr};
		UISecConstraint cbListaRuoli_ctr = new ComplexUISecConstraint(
				cbListaRuoli_constraints);
		allConstraints.put("cbListaRuoli", cbListaRuoli_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultVisib_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);

		// constraint fittizio per btSalva
		UISecConstraint btSalva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btSalva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btSalva_constraints = new UISecConstraint[]{btSalva_defaultVisib_ctr};
		UISecConstraint btSalva_ctr = new ComplexUISecConstraint(
				btSalva_constraints);
		allConstraints.put("btSalva", btSalva_ctr);

		// constraint fittizio per tbListaUtenti
		UISecConstraint tbListaUtenti_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "tbListaUtenti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaUtenti_constraints = new UISecConstraint[]{tbListaUtenti_defaultVisib_ctr};
		UISecConstraint tbListaUtenti_ctr = new ComplexUISecConstraint(
				tbListaUtenti_constraints);
		allConstraints.put("tbListaUtenti", tbListaUtenti_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txIdGruppo
		UISecConstraint txIdGruppo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "txIdGruppo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdGruppo_constraints = new UISecConstraint[]{txIdGruppo_defaultOnoff_ctr};
		UISecConstraint txIdGruppo_ctr = new ComplexUISecConstraint(
				txIdGruppo_constraints);
		allConstraints.put("txIdGruppo", txIdGruppo_ctr);

		// constraint fittizio per txDescrizioneGruppo
		UISecConstraint txDescrizioneGruppo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "txDescrizioneGruppo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDescrizioneGruppo_constraints = new UISecConstraint[]{txDescrizioneGruppo_defaultOnoff_ctr};
		UISecConstraint txDescrizioneGruppo_ctr = new ComplexUISecConstraint(
				txDescrizioneGruppo_constraints);
		allConstraints.put("txDescrizioneGruppo", txDescrizioneGruppo_ctr);

		// constraint fittizio per cbAppGroupToAdd
		UISecConstraint cbAppGroupToAdd_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "cbAppGroupToAdd",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppGroupToAdd_constraints = new UISecConstraint[]{cbAppGroupToAdd_defaultOnoff_ctr};
		UISecConstraint cbAppGroupToAdd_ctr = new ComplexUISecConstraint(
				cbAppGroupToAdd_constraints);
		allConstraints.put("cbAppGroupToAdd", cbAppGroupToAdd_ctr);

		// constraint fittizio per btnAddAppFiltro
		UISecConstraint btnAddAppFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btnAddAppFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddAppFiltro_constraints = new UISecConstraint[]{btnAddAppFiltro_defaultOnoff_ctr};
		UISecConstraint btnAddAppFiltro_ctr = new ComplexUISecConstraint(
				btnAddAppFiltro_constraints);
		allConstraints.put("btnAddAppFiltro", btnAddAppFiltro_ctr);

		// constraint fittizio per cbAppGruoupFiltered
		UISecConstraint cbAppGruoupFiltered_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "cbAppGruoupFiltered",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppGruoupFiltered_constraints = new UISecConstraint[]{cbAppGruoupFiltered_defaultOnoff_ctr};
		UISecConstraint cbAppGruoupFiltered_ctr = new ComplexUISecConstraint(
				cbAppGruoupFiltered_constraints);
		allConstraints.put("cbAppGruoupFiltered", cbAppGruoupFiltered_ctr);

		// constraint fittizio per btnEliminAppFiltro
		UISecConstraint btnEliminAppFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btnEliminAppFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminAppFiltro_constraints = new UISecConstraint[]{btnEliminAppFiltro_defaultOnoff_ctr};
		UISecConstraint btnEliminAppFiltro_ctr = new ComplexUISecConstraint(
				btnEliminAppFiltro_constraints);
		allConstraints.put("btnEliminAppFiltro", btnEliminAppFiltro_ctr);

		// constraint fittizio per cbListaRuoli
		UISecConstraint cbListaRuoli_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "cbListaRuoli",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaRuoli_constraints = new UISecConstraint[]{cbListaRuoli_defaultOnoff_ctr};
		UISecConstraint cbListaRuoli_ctr = new ComplexUISecConstraint(
				cbListaRuoli_constraints);
		allConstraints.put("cbListaRuoli", cbListaRuoli_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);

		// constraint fittizio per btSalva
		UISecConstraint btSalva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "btSalva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btSalva_constraints = new UISecConstraint[]{btSalva_defaultOnoff_ctr};
		UISecConstraint btSalva_ctr = new ComplexUISecConstraint(
				btSalva_constraints);
		allConstraints.put("btSalva", btSalva_ctr);

		// constraint fittizio per tbListaUtenti
		UISecConstraint tbListaUtenti_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGruppo", "tbListaUtenti",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaUtenti_constraints = new UISecConstraint[]{tbListaUtenti_defaultOnoff_ctr};
		UISecConstraint tbListaUtenti_ctr = new ComplexUISecConstraint(
				tbListaUtenti_constraints);
		allConstraints.put("tbListaUtenti", tbListaUtenti_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioGruppoAction::dumpmodel] START");

		log.debug("[CpDettaglioGruppoAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioGruppoAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioGruppoAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioGruppoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioGruppoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioGruppoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioGruppoAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioGruppo");
		log.debug("[CpDettaglioGruppoAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpDettaglioGruppoAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioGruppoAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnAddAppFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnAddAppFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnAddAppFiltroByEvh.put("CLICKED",
				initCommandBtnAddAppFiltro_CLICKED());
		cmdsByWidget.put("btnAddAppFiltro", cmds4btnAddAppFiltroByEvh);
		// contiene i comandi del widget btnEliminAppFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnEliminAppFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnEliminAppFiltroByEvh.put("CLICKED",
				initCommandBtnEliminAppFiltro_CLICKED());
		cmdsByWidget.put("btnEliminAppFiltro", cmds4btnEliminAppFiltroByEvh);
		// contiene i comandi del widget btIndietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btIndietroByEvh = new HashMap<String, ICommand>();

		cmds4btIndietroByEvh.put("CLICKED", initCommandBtIndietro_CLICKED());
		cmdsByWidget.put("btIndietro", cmds4btIndietroByEvh);
		// contiene i comandi del widget btSalva per ogni Ev.H.
		HashMap<String, ICommand> cmds4btSalvaByEvh = new HashMap<String, ICommand>();

		cmds4btSalvaByEvh.put("CLICKED", initCommandBtSalva_CLICKED());
		cmdsByWidget.put("btSalva", cmds4btSalvaByEvh);

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

	private ICommand initCommandBtnAddAppFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4addApplicazioneFiltro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4addApplicazioneFiltro = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddAppFiltro_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddAppFiltro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnAddAppFiltro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnAddAppFiltro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnAddAppFiltro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4addApplicazioneFiltro[0] = act_actions_clicked_btnAddAppFiltro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddAppFiltro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddAppFiltro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnAddAppFiltro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnAddAppFiltro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnAddAppFiltro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4addApplicazioneFiltro[1] = act_actions_clicked_btnAddAppFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnAddAppFiltro_1 = new ExecCommand(
				resultNames4addApplicazioneFiltro,
				actionsForResults4addApplicazioneFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.addApplicazioneFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGruppoAction", "readOne()",
							"chiamata verso BackEnd", "addApplicazioneFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [addApplicazioneFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnAddAppFiltro_1;
	}

	private ICommand initCommandBtnEliminAppFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4eliminaApplicazioneFiltro = new String[]{"Ok",
				"Ko"};

		ICommand[] actionsForResults4eliminaApplicazioneFiltro = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminAppFiltro_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnEliminAppFiltro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnEliminAppFiltro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnEliminAppFiltro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnEliminAppFiltro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4eliminaApplicazioneFiltro[0] = act_actions_clicked_btnEliminAppFiltro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminAppFiltro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnEliminAppFiltro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnEliminAppFiltro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnEliminAppFiltro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnEliminAppFiltro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4eliminaApplicazioneFiltro[1] = act_actions_clicked_btnEliminAppFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnEliminAppFiltro_1 = new ExecCommand(
				resultNames4eliminaApplicazioneFiltro,
				actionsForResults4eliminaApplicazioneFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.eliminaApplicazioneFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGruppoAction", "readOne()",
							"chiamata verso BackEnd",
							"eliminaApplicazioneFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [eliminaApplicazioneFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnEliminAppFiltro_1;
	}

	private ICommand initCommandBtIndietro_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4indietro = new String[]{"Ok"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btIndietrostep_resokAction_0 = new JumpCommand(
				"cpGestioneGruppi", null, false);
		/// Jump Command end
		actionsForResults4indietro[0] = act_actions_clicked_btIndietrostep_resokAction_0;

		ExecCommand act_actions_clicked_btIndietrostep_0 = new ExecCommand(
				resultNames4indietro, actionsForResults4indietro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.indietro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGruppoAction", "readOne()",
							"chiamata verso BackEnd", "indietro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [indietro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btIndietro_1_steps = new ICommand[]{act_actions_clicked_btIndietrostep_0};
		SequenceCommand act_actions_clicked_btIndietro_1 = new SequenceCommand(
				act_actions_clicked_btIndietro_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btIndietro_1;
	}

	private ICommand initCommandBtSalva_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4salvaGruppo = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salvaGruppo = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btSalvastep_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btSalvastep_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btSalvastep_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btSalvastep_resokActionstep_1 = new JumpCommand(
				"cpGestioneGruppi", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btSalvastep_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btSalvastep_resokActionstep_0,
				act_actions_clicked_btSalvastep_resokActionstep_1};
		SequenceCommand act_actions_clicked_btSalvastep_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btSalvastep_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4salvaGruppo[0] = act_actions_clicked_btSalvastep_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btSalvastep_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4salvaGruppo[1] = act_actions_clicked_btSalvastep_reskoAction_1;

		ExecCommand act_actions_clicked_btSalvastep_0 = new ExecCommand(
				resultNames4salvaGruppo, actionsForResults4salvaGruppo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salvaGruppo(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGruppoAction", "readOne()",
							"chiamata verso BackEnd", "salvaGruppo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salvaGruppo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btSalva_1_steps = new ICommand[]{act_actions_clicked_btSalvastep_0};
		SequenceCommand act_actions_clicked_btSalva_1 = new SequenceCommand(
				act_actions_clicked_btSalva_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btSalva_1;
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
