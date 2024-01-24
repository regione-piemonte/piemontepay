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
 * CpGestioneUtentiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneUtentiAction extends BaseAction implements Preparable {

	public void setAppDatautente(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente value) {
		getSession().put("appDatautente", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente getAppDatautente() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente) (getSession()
				.get("appDatautente"));
	}

	public void setAppDatautenti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> value) {
		getSession().put("appDatautenti", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getAppDatautenti() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>) (getSession()
				.get("appDatautenti"));
	}

	public void setAppDataselettoreUtente(java.lang.String value) {
		getSession().put("appDataselettoreUtente", value);
	}

	public java.lang.String getAppDataselettoreUtente() {
		return (java.lang.String) (getSession().get("appDataselettoreUtente"));
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

	public void setAppDataselettoreIdRuolo(java.lang.String value) {
		getSession().put("appDataselettoreIdRuolo", value);
	}

	public java.lang.String getAppDataselettoreIdRuolo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRuolo"));
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

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataselettoreIdGruppo(java.lang.String value) {
		getSession().put("appDataselettoreIdGruppo", value);
	}

	public java.lang.String getAppDataselettoreIdGruppo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdGruppo"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btNuovoUtente]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtNuovoUtente_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btNuovoUtente", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneUtentiAction::handleBtNuovoUtente_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneUtentiAction::handleBtNuovoUtente_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneUtentiAction::handleBtNuovoUtente_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneUtentiAction::handleBtNuovoUtente_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneUtentiAction::handleBtNuovoUtente_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btModUtente]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtModUtente_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btModUtente", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneUtentiAction::handleBtModUtente_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneUtentiAction::handleBtModUtente_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneUtentiAction::handleBtModUtente_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneUtentiAction::handleBtModUtente_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneUtentiAction::handleBtModUtente_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btEliminaUtente]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtEliminaUtente_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btEliminaUtente", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneUtentiAction::handleBtEliminaUtente_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneUtentiAction::handleBtEliminaUtente_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneUtentiAction::handleBtEliminaUtente_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneUtentiAction::handleBtEliminaUtente_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneUtentiAction::handleBtEliminaUtente_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tListaUtenti.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTListaUtenti_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatautenti");

		if (isTableClearStatus("cpGestioneUtenti_tListaUtenti")) {
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
		/*PROTECTED REGION ID(R-1350888071) ENABLED START*/
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
				log.error("[CpGestioneUtentiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneUtentiAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"No_Result",
				"SomeResult"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resno_resultActionstep_0_on = new String[]{
				"mnuView", "tListaUtenti"};

		String[] act_onRefresh_resno_resultActionstep_0_off = new String[]{
				"btNuovoUtente", "btModUtente", "btEliminaUtente"};

		String[] act_onRefresh_resno_resultActionstep_0_show = new String[]{
				"mnuView", "tListaUtenti"};

		String[] act_onRefresh_resno_resultActionstep_0_hide = new String[]{
				"btNuovoUtente", "btModUtente", "btEliminaUtente"};

		ScreenStateCommand act_onRefresh_resno_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneUtenti", "NO_RESULTS",
				act_onRefresh_resno_resultActionstep_0_on,
				act_onRefresh_resno_resultActionstep_0_off,
				act_onRefresh_resno_resultActionstep_0_show,
				act_onRefresh_resno_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resno_resultAction_0_steps = new ICommand[]{act_onRefresh_resno_resultActionstep_0};
		SequenceCommand act_onRefresh_resno_resultAction_0 = new SequenceCommand(
				act_onRefresh_resno_resultAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resno_resultAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_ressomeresultActionstep_0_on = new String[]{
				"mnuView", "tListaUtenti", "btNuovoUtente", "btEliminaUtente",
				"btModUtente"};

		String[] act_onRefresh_ressomeresultActionstep_0_off = new String[]{};

		String[] act_onRefresh_ressomeresultActionstep_0_show = new String[]{
				"mnuView", "tListaUtenti", "btNuovoUtente", "btEliminaUtente",
				"btModUtente"};

		String[] act_onRefresh_ressomeresultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_ressomeresultActionstep_0 = new ScreenStateCommand(
				"cpGestioneUtenti", "SOME_RESULTS",
				act_onRefresh_ressomeresultActionstep_0_on,
				act_onRefresh_ressomeresultActionstep_0_off,
				act_onRefresh_ressomeresultActionstep_0_show,
				act_onRefresh_ressomeresultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_ressomeresultAction_1_steps = new ICommand[]{act_onRefresh_ressomeresultActionstep_0};
		SequenceCommand act_onRefresh_ressomeresultAction_1 = new SequenceCommand(
				act_onRefresh_ressomeresultAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_ressomeresultAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneUtentiAction", "readOne()",
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

		new DummyUISecConstraint("cpGestioneUtenti", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tListaUtenti
		UISecConstraint tListaUtenti_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "tListaUtenti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tListaUtenti_constraints = new UISecConstraint[]{tListaUtenti_defaultVisib_ctr};
		UISecConstraint tListaUtenti_ctr = new ComplexUISecConstraint(
				tListaUtenti_constraints);
		allConstraints.put("tListaUtenti", tListaUtenti_ctr);

		// constraint fittizio per btNuovoUtente
		UISecConstraint btNuovoUtente_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "btNuovoUtente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btNuovoUtente_constraints = new UISecConstraint[]{btNuovoUtente_defaultVisib_ctr};
		UISecConstraint btNuovoUtente_ctr = new ComplexUISecConstraint(
				btNuovoUtente_constraints);
		allConstraints.put("btNuovoUtente", btNuovoUtente_ctr);

		// constraint fittizio per btModUtente
		UISecConstraint btModUtente_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "btModUtente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btModUtente_constraints = new UISecConstraint[]{btModUtente_defaultVisib_ctr};
		UISecConstraint btModUtente_ctr = new ComplexUISecConstraint(
				btModUtente_constraints);
		allConstraints.put("btModUtente", btModUtente_ctr);

		// constraint fittizio per btEliminaUtente
		UISecConstraint btEliminaUtente_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "btEliminaUtente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btEliminaUtente_constraints = new UISecConstraint[]{btEliminaUtente_defaultVisib_ctr};
		UISecConstraint btEliminaUtente_ctr = new ComplexUISecConstraint(
				btEliminaUtente_constraints);
		allConstraints.put("btEliminaUtente", btEliminaUtente_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tListaUtenti
		UISecConstraint tListaUtenti_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "tListaUtenti",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tListaUtenti_constraints = new UISecConstraint[]{tListaUtenti_defaultOnoff_ctr};
		UISecConstraint tListaUtenti_ctr = new ComplexUISecConstraint(
				tListaUtenti_constraints);
		allConstraints.put("tListaUtenti", tListaUtenti_ctr);

		// constraint fittizio per btNuovoUtente
		UISecConstraint btNuovoUtente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "btNuovoUtente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btNuovoUtente_constraints = new UISecConstraint[]{btNuovoUtente_defaultOnoff_ctr};
		UISecConstraint btNuovoUtente_ctr = new ComplexUISecConstraint(
				btNuovoUtente_constraints);
		allConstraints.put("btNuovoUtente", btNuovoUtente_ctr);

		// constraint fittizio per btModUtente
		UISecConstraint btModUtente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "btModUtente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btModUtente_constraints = new UISecConstraint[]{btModUtente_defaultOnoff_ctr};
		UISecConstraint btModUtente_ctr = new ComplexUISecConstraint(
				btModUtente_constraints);
		allConstraints.put("btModUtente", btModUtente_ctr);

		// constraint fittizio per btEliminaUtente
		UISecConstraint btEliminaUtente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneUtenti", "btEliminaUtente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btEliminaUtente_constraints = new UISecConstraint[]{btEliminaUtente_defaultOnoff_ctr};
		UISecConstraint btEliminaUtente_ctr = new ComplexUISecConstraint(
				btEliminaUtente_constraints);
		allConstraints.put("btEliminaUtente", btEliminaUtente_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneUtentiAction::dumpmodel] START");

		log.debug("[CpGestioneUtentiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneUtentiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneUtentiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneUtentiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneUtentiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneUtentiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpGestioneUtentiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneUtenti");
		log.debug("[CpGestioneUtentiAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneUtentiAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneUtentiAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btNuovoUtente per ogni Ev.H.
		HashMap<String, ICommand> cmds4btNuovoUtenteByEvh = new HashMap<String, ICommand>();

		cmds4btNuovoUtenteByEvh.put("CLICKED",
				initCommandBtNuovoUtente_CLICKED());
		cmdsByWidget.put("btNuovoUtente", cmds4btNuovoUtenteByEvh);
		// contiene i comandi del widget btModUtente per ogni Ev.H.
		HashMap<String, ICommand> cmds4btModUtenteByEvh = new HashMap<String, ICommand>();

		cmds4btModUtenteByEvh.put("CLICKED", initCommandBtModUtente_CLICKED());
		cmdsByWidget.put("btModUtente", cmds4btModUtenteByEvh);
		// contiene i comandi del widget btEliminaUtente per ogni Ev.H.
		HashMap<String, ICommand> cmds4btEliminaUtenteByEvh = new HashMap<String, ICommand>();

		cmds4btEliminaUtenteByEvh.put("CLICKED",
				initCommandBtEliminaUtente_CLICKED());
		cmdsByWidget.put("btEliminaUtente", cmds4btEliminaUtenteByEvh);

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

	private ICommand initCommandBtNuovoUtente_CLICKED() {
		// ExecCommand begin
		String[] resultNames4goToNuovoUtente = new String[]{"Ok"};

		ICommand[] actionsForResults4goToNuovoUtente = new ICommand[1];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btNuovoUtente_resokActionstep_0 = new JumpCommand(
				"cpDettaglioUtenti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btNuovoUtente_resokAction_0_steps = new ICommand[]{act_actions_clicked_btNuovoUtente_resokActionstep_0};
		SequenceCommand act_actions_clicked_btNuovoUtente_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btNuovoUtente_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4goToNuovoUtente[0] = act_actions_clicked_btNuovoUtente_resokAction_0;

		ExecCommand act_actions_clicked_btNuovoUtente_1 = new ExecCommand(
				resultNames4goToNuovoUtente, actionsForResults4goToNuovoUtente) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.goToNuovoUtente(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneUtentiAction", "readOne()",
							"chiamata verso BackEnd", "goToNuovoUtente");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [goToNuovoUtente]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btNuovoUtente_1;
	}

	private ICommand initCommandBtModUtente_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaUtente = new String[]{"OK", "KO"};

		ICommand[] actionsForResults4modificaUtente = new ICommand[2];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btModUtente_resokActionstep_0 = new JumpCommand(
				"cpDettaglioUtenti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btModUtente_resokAction_0_steps = new ICommand[]{act_actions_clicked_btModUtente_resokActionstep_0};
		SequenceCommand act_actions_clicked_btModUtente_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btModUtente_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4modificaUtente[0] = act_actions_clicked_btModUtente_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btModUtente_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btModUtente_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btModUtente_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btModUtente_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btModUtente_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4modificaUtente[1] = act_actions_clicked_btModUtente_reskoAction_1;

		ExecCommand act_actions_clicked_btModUtente_1 = new ExecCommand(
				resultNames4modificaUtente, actionsForResults4modificaUtente) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaUtente(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneUtentiAction", "readOne()",
							"chiamata verso BackEnd", "modificaUtente");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaUtente]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btModUtente_1;
	}

	private ICommand initCommandBtEliminaUtente_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cancellaUtente = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4cancellaUtente = new ICommand[2];
		/// Clear AppData Command begin
		String[] act_actions_clicked_btEliminaUtente_resokAction_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btEliminaUtente_resokAction_0 = new ClearAppDataCommand(
				act_actions_clicked_btEliminaUtente_resokAction_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		actionsForResults4cancellaUtente[0] = act_actions_clicked_btEliminaUtente_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btEliminaUtente_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4cancellaUtente[1] = act_actions_clicked_btEliminaUtente_reskoAction_1;

		ExecCommand act_actions_clicked_btEliminaUtente_1 = new ExecCommand(
				resultNames4cancellaUtente, actionsForResults4cancellaUtente) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cancellaUtente(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneUtentiAction", "readOne()",
							"chiamata verso BackEnd", "cancellaUtente");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cancellaUtente]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btEliminaUtente_1;
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
