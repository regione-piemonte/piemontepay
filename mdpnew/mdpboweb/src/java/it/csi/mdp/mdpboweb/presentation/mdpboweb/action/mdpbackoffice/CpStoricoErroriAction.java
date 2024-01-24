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
 * CpStoricoErroriAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpStoricoErroriAction extends BaseAction implements Preparable {

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDataerrori(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore> value) {
		getSession().put("appDataerrori", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore> getAppDataerrori() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore>) (getSession()
				.get("appDataerrori"));
	}

	public void setAppDataerrore(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore value) {
		getSession().put("appDataerrore", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore getAppDataerrore() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Errore) (getSession()
				.get("appDataerrore"));
	}

	public void setAppDatagateways(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway> value) {
		getSession().put("appDatagateways", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway> getAppDatagateways() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway>) (getSession()
				.get("appDatagateways"));
	}

	public void setAppDataapplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataapplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataapplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataapplicazioni"));
	}

	public void setAppDataselettoreIdErrore(java.lang.String value) {
		getSession().put("appDataselettoreIdErrore", value);
	}

	public java.lang.String getAppDataselettoreIdErrore() {
		return (java.lang.String) (getSession().get("appDataselettoreIdErrore"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel.class;
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
			log.debug("[CpStoricoErroriAction::handleBtnCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpStoricoErroriAction::handleBtnCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStoricoErroriAction::handleBtnCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpStoricoErroriAction::handleBtnCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStoricoErroriAction::handleBtnCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnShowError]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnShowError_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnShowError", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpStoricoErroriAction::handleBtnShowError_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpStoricoErroriAction::handleBtnShowError_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStoricoErroriAction::handleBtnShowError_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpStoricoErroriAction::handleBtnShowError_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStoricoErroriAction::handleBtnShowError_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbGateway.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbGateway_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatagateways");

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
		Object dataToProvide = ctx.getValueStack().findValue("appDataerrori");

		if (isTableClearStatus("cpStoricoErrori_tRicerca")) {
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
		/*PROTECTED REGION ID(R-1036833030) ENABLED START*/
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
				log.error("[CpStoricoErroriAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpStoricoErroriAction::isWidgetVisible] errore durante verifica->hide");
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
				"Ok_Some_Results", "No_results"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[3];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_inizialeActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca"};

		String[] act_onRefresh_resok_inizialeActionstep_0_off = new String[]{
				"tRicerca", "btnShowError", "ptTestoErrore"};

		String[] act_onRefresh_resok_inizialeActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca"};

		String[] act_onRefresh_resok_inizialeActionstep_0_hide = new String[]{
				"tRicerca", "btnShowError", "ptTestoErrore"};

		ScreenStateCommand act_onRefresh_resok_inizialeActionstep_0 = new ScreenStateCommand(
				"cpStoricoErrori", "INIZIALE",
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
		String[] act_onRefresh_resok_some_resultsActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca",
				"btnShowError", "ptTestoErrore"};

		String[] act_onRefresh_resok_some_resultsActionstep_0_off = new String[]{};

		String[] act_onRefresh_resok_some_resultsActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca",
				"btnShowError", "ptTestoErrore"};

		String[] act_onRefresh_resok_some_resultsActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resok_some_resultsActionstep_0 = new ScreenStateCommand(
				"cpStoricoErrori", "SOME_RESULTS",
				act_onRefresh_resok_some_resultsActionstep_0_on,
				act_onRefresh_resok_some_resultsActionstep_0_off,
				act_onRefresh_resok_some_resultsActionstep_0_show,
				act_onRefresh_resok_some_resultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_some_resultsAction_1_steps = new ICommand[]{act_onRefresh_resok_some_resultsActionstep_0};
		SequenceCommand act_onRefresh_resok_some_resultsAction_1 = new SequenceCommand(
				act_onRefresh_resok_some_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resok_some_resultsAction_1;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resno_resultsActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_onRefresh_resno_resultsActionstep_0_off = new String[]{
				"btnShowError", "ptTestoErrore"};

		String[] act_onRefresh_resno_resultsActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_onRefresh_resno_resultsActionstep_0_hide = new String[]{
				"btnShowError", "ptTestoErrore"};

		ScreenStateCommand act_onRefresh_resno_resultsActionstep_0 = new ScreenStateCommand(
				"cpStoricoErrori", "NO_RESULTS",
				act_onRefresh_resno_resultsActionstep_0_on,
				act_onRefresh_resno_resultsActionstep_0_off,
				act_onRefresh_resno_resultsActionstep_0_show,
				act_onRefresh_resno_resultsActionstep_0_hide);
		//Screen State Command end
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resno_resultsActionstepstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_onRefresh_resno_resultsActionstepstep_0_off = new String[]{
				"btnShowError", "ptTestoErrore"};

		String[] act_onRefresh_resno_resultsActionstepstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_onRefresh_resno_resultsActionstepstep_0_hide = new String[]{
				"btnShowError", "ptTestoErrore"};

		ScreenStateCommand act_onRefresh_resno_resultsActionstepstep_0 = new ScreenStateCommand(
				"cpStoricoErrori", "NO_RESULTS",
				act_onRefresh_resno_resultsActionstepstep_0_on,
				act_onRefresh_resno_resultsActionstepstep_0_off,
				act_onRefresh_resno_resultsActionstepstep_0_show,
				act_onRefresh_resno_resultsActionstepstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resno_resultsActionstep_1_steps = new ICommand[]{act_onRefresh_resno_resultsActionstepstep_0};
		SequenceCommand act_onRefresh_resno_resultsActionstep_1 = new SequenceCommand(
				act_onRefresh_resno_resultsActionstep_1_steps);
		// SequenceCommand end

		ICommand[] act_onRefresh_resno_resultsAction_2_steps = new ICommand[]{
				act_onRefresh_resno_resultsActionstep_0,
				act_onRefresh_resno_resultsActionstep_1};
		SequenceCommand act_onRefresh_resno_resultsAction_2 = new SequenceCommand(
				act_onRefresh_resno_resultsAction_2_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[2] = act_onRefresh_resno_resultsAction_2;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpStoricoErroriAction", "readOne()",
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

		new DummyUISecConstraint("cpStoricoErrori", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "idTransazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultVisib_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per cbApplicazione
		UISecConstraint cbApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbApplicazione_constraints = new UISecConstraint[]{cbApplicazione_defaultVisib_ctr};
		UISecConstraint cbApplicazione_ctr = new ComplexUISecConstraint(
				cbApplicazione_constraints);
		allConstraints.put("cbApplicazione", cbApplicazione_ctr);

		// constraint fittizio per cbGateway
		UISecConstraint cbGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbGateway_constraints = new UISecConstraint[]{cbGateway_defaultVisib_ctr};
		UISecConstraint cbGateway_ctr = new ComplexUISecConstraint(
				cbGateway_constraints);
		allConstraints.put("cbGateway", cbGateway_ctr);

		// constraint fittizio per cbDallaData
		UISecConstraint cbDallaData_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbDallaData",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbDallaData_constraints = new UISecConstraint[]{cbDallaData_defaultVisib_ctr};
		UISecConstraint cbDallaData_ctr = new ComplexUISecConstraint(
				cbDallaData_constraints);
		allConstraints.put("cbDallaData", cbDallaData_ctr);

		// constraint fittizio per cbAllaData
		UISecConstraint cbAllaData_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbAllaData",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAllaData_constraints = new UISecConstraint[]{cbAllaData_defaultVisib_ctr};
		UISecConstraint cbAllaData_ctr = new ComplexUISecConstraint(
				cbAllaData_constraints);
		allConstraints.put("cbAllaData", cbAllaData_ctr);

		// constraint fittizio per btnCerca
		UISecConstraint btnCerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "btnCerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCerca_constraints = new UISecConstraint[]{btnCerca_defaultVisib_ctr};
		UISecConstraint btnCerca_ctr = new ComplexUISecConstraint(
				btnCerca_constraints);
		allConstraints.put("btnCerca", btnCerca_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "tRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultVisib_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per btnShowError
		UISecConstraint btnShowError_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "btnShowError",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnShowError_constraints = new UISecConstraint[]{btnShowError_defaultVisib_ctr};
		UISecConstraint btnShowError_ctr = new ComplexUISecConstraint(
				btnShowError_constraints);
		allConstraints.put("btnShowError", btnShowError_ctr);

		// constraint fittizio per ptTestoErrore
		UISecConstraint ptTestoErrore_defaultVisib_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "ptTestoErrore",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptTestoErrore_constraints = new UISecConstraint[]{ptTestoErrore_defaultVisib_ctr};
		UISecConstraint ptTestoErrore_ctr = new ComplexUISecConstraint(
				ptTestoErrore_constraints);
		allConstraints.put("ptTestoErrore", ptTestoErrore_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "idTransazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultOnoff_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per cbApplicazione
		UISecConstraint cbApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbApplicazione_constraints = new UISecConstraint[]{cbApplicazione_defaultOnoff_ctr};
		UISecConstraint cbApplicazione_ctr = new ComplexUISecConstraint(
				cbApplicazione_constraints);
		allConstraints.put("cbApplicazione", cbApplicazione_ctr);

		// constraint fittizio per cbGateway
		UISecConstraint cbGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbGateway_constraints = new UISecConstraint[]{cbGateway_defaultOnoff_ctr};
		UISecConstraint cbGateway_ctr = new ComplexUISecConstraint(
				cbGateway_constraints);
		allConstraints.put("cbGateway", cbGateway_ctr);

		// constraint fittizio per cbDallaData
		UISecConstraint cbDallaData_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbDallaData",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbDallaData_constraints = new UISecConstraint[]{cbDallaData_defaultOnoff_ctr};
		UISecConstraint cbDallaData_ctr = new ComplexUISecConstraint(
				cbDallaData_constraints);
		allConstraints.put("cbDallaData", cbDallaData_ctr);

		// constraint fittizio per cbAllaData
		UISecConstraint cbAllaData_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "cbAllaData",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAllaData_constraints = new UISecConstraint[]{cbAllaData_defaultOnoff_ctr};
		UISecConstraint cbAllaData_ctr = new ComplexUISecConstraint(
				cbAllaData_constraints);
		allConstraints.put("cbAllaData", cbAllaData_ctr);

		// constraint fittizio per btnCerca
		UISecConstraint btnCerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "btnCerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCerca_constraints = new UISecConstraint[]{btnCerca_defaultOnoff_ctr};
		UISecConstraint btnCerca_ctr = new ComplexUISecConstraint(
				btnCerca_constraints);
		allConstraints.put("btnCerca", btnCerca_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "tRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultOnoff_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per btnShowError
		UISecConstraint btnShowError_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "btnShowError",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnShowError_constraints = new UISecConstraint[]{btnShowError_defaultOnoff_ctr};
		UISecConstraint btnShowError_ctr = new ComplexUISecConstraint(
				btnShowError_constraints);
		allConstraints.put("btnShowError", btnShowError_ctr);

		// constraint fittizio per ptTestoErrore
		UISecConstraint ptTestoErrore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStoricoErrori", "ptTestoErrore",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptTestoErrore_constraints = new UISecConstraint[]{ptTestoErrore_defaultOnoff_ctr};
		UISecConstraint ptTestoErrore_ctr = new ComplexUISecConstraint(
				ptTestoErrore_constraints);
		allConstraints.put("ptTestoErrore", ptTestoErrore_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpStoricoErroriAction::dumpmodel] START");

		log.debug("[CpStoricoErroriAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpStoricoErroriAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpStoricoErroriAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpStoricoErroriAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpStoricoErroriAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpStoricoErroriAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpStoricoErroriAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpStoricoErrori");
		log.debug("[CpStoricoErroriAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpStoricoErroriAction::dumpmodel] [c] sessione");
		log.debug("[CpStoricoErroriAction::dumpmodel] " + session);
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
		// contiene i comandi del widget btnShowError per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnShowErrorByEvh = new HashMap<String, ICommand>();

		cmds4btnShowErrorByEvh
				.put("CLICKED", initCommandBtnShowError_CLICKED());
		cmdsByWidget.put("btnShowError", cmds4btnShowErrorByEvh);

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
				"Ok_Some_Results", "Ko"};

		ICommand[] actionsForResults4cerca = new ICommand[3];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_off = new String[]{
				"btnShowError", "ptTestoErrore"};

		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_actions_clicked_btnCerca_resok_no_resultsActionstep_0_hide = new String[]{
				"btnShowError", "ptTestoErrore"};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_no_resultsActionstep_0 = new ScreenStateCommand(
				"cpStoricoErrori", "NO_RESULTS",
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
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca",
				"btnShowError", "ptTestoErrore"};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_off = new String[]{};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca",
				"btnShowError", "ptTestoErrore"};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_some_resultsActionstep_0 = new ScreenStateCommand(
				"cpStoricoErrori",
				"SOME_RESULTS",
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_on,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_off,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_show,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_some_resultsAction_1_steps = new ICommand[]{act_actions_clicked_btnCerca_resok_some_resultsActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_resok_some_resultsAction_1 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_some_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4cerca[1] = act_actions_clicked_btnCerca_resok_some_resultsAction_1;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_reskoActionstep_0_on = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_actions_clicked_btnCerca_reskoActionstep_0_off = new String[]{
				"btnShowError", "ptTestoErrore"};

		String[] act_actions_clicked_btnCerca_reskoActionstep_0_show = new String[]{
				"mnuView", "idTransazione", "cbApplicazione", "cbGateway",
				"cbDallaData", "cbAllaData", "btnCerca", "tRicerca"};

		String[] act_actions_clicked_btnCerca_reskoActionstep_0_hide = new String[]{
				"btnShowError", "ptTestoErrore"};

		ScreenStateCommand act_actions_clicked_btnCerca_reskoActionstep_0 = new ScreenStateCommand(
				"cpStoricoErrori", "NO_RESULTS",
				act_actions_clicked_btnCerca_reskoActionstep_0_on,
				act_actions_clicked_btnCerca_reskoActionstep_0_off,
				act_actions_clicked_btnCerca_reskoActionstep_0_show,
				act_actions_clicked_btnCerca_reskoActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_reskoAction_2_steps = new ICommand[]{act_actions_clicked_btnCerca_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_reskoAction_2 = new SequenceCommand(
				act_actions_clicked_btnCerca_reskoAction_2_steps);
		// SequenceCommand end
		actionsForResults4cerca[2] = act_actions_clicked_btnCerca_reskoAction_2;

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
							.cerca((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpStoricoErroriAction", "readOne()",
							"chiamata verso BackEnd", "cerca");
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

	private ICommand initCommandBtnShowError_CLICKED() {
		// ExecCommand begin
		String[] resultNames4showError = new String[]{"Ok"};

		ICommand[] actionsForResults4showError = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnShowError_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4showError[0] = act_actions_clicked_btnShowError_resokAction_0;

		ExecCommand act_actions_clicked_btnShowError_1 = new ExecCommand(
				resultNames4showError, actionsForResults4showError) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.showError(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpStoricoErroriAction", "readOne()",
							"chiamata verso BackEnd", "showError");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [showError]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnShowError_1;
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
