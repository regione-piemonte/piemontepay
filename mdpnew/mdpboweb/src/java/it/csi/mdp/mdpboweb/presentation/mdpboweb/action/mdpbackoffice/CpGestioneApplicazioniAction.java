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
 * CpGestioneApplicazioniAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneApplicazioniAction extends BaseAction
		implements
			Preparable {

	public void setAppDataapplicazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione value) {
		getSession().put("appDataapplicazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione getAppDataapplicazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione) (getSession()
				.get("appDataapplicazione"));
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

	public void setAppDatalastWhereClause(java.lang.String value) {
		getSession().put("appDatalastWhereClause", value);
	}

	public java.lang.String getAppDatalastWhereClause() {
		return (java.lang.String) (getSession().get("appDatalastWhereClause"));
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataselettoreIdApplicazione(java.lang.String value) {
		getSession().put("appDataselettoreIdApplicazione", value);
	}

	public java.lang.String getAppDataselettoreIdApplicazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdApplicazione"));
	}

	public void setAppDataselettoreOperazione(java.lang.String value) {
		getSession().put("appDataselettoreOperazione", value);
	}

	public java.lang.String getAppDataselettoreOperazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreOperazione"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnNuovaApplicazione]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnNuovaApplicazione_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnNuovaApplicazione", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneApplicazioniAction::handleBtnNuovaApplicazione_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneApplicazioniAction::handleBtnNuovaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneApplicazioniAction::handleBtnNuovaApplicazione_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneApplicazioniAction::handleBtnNuovaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneApplicazioniAction::handleBtnNuovaApplicazione_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnModificaApplicazione]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnModificaApplicazione_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnModificaApplicazione", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneApplicazioniAction::handleBtnModificaApplicazione_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneApplicazioniAction::handleBtnModificaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneApplicazioniAction::handleBtnModificaApplicazione_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneApplicazioniAction::handleBtnModificaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneApplicazioniAction::handleBtnModificaApplicazione_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCancellaApplicazione]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCancellaApplicazione_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCancellaApplicazione", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneApplicazioniAction::handleBtnCancellaApplicazione_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneApplicazioniAction::handleBtnCancellaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneApplicazioniAction::handleBtnCancellaApplicazione_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneApplicazioniAction::handleBtnCancellaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneApplicazioniAction::handleBtnCancellaApplicazione_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tListaApplicazioni.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTListaApplicazioni_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataapplicazioni");

		if (isTableClearStatus("cpGestioneApplicazioni_tListaApplicazioni")) {
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
		/*PROTECTED REGION ID(R47124951) ENABLED START*/
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
				log.error("[CpGestioneApplicazioniAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneApplicazioniAction::isWidgetVisible] errore durante verifica->hide");
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
				"mnuView", "tListaApplicazioni", "btnNuovaApplicazione"};

		String[] act_onRefresh_resok_inizialeActionstep_0_off = new String[]{
				"btnModificaApplicazione", "btnCancellaApplicazione"};

		String[] act_onRefresh_resok_inizialeActionstep_0_show = new String[]{
				"mnuView", "tListaApplicazioni", "btnNuovaApplicazione"};

		String[] act_onRefresh_resok_inizialeActionstep_0_hide = new String[]{
				"btnModificaApplicazione", "btnCancellaApplicazione"};

		ScreenStateCommand act_onRefresh_resok_inizialeActionstep_0 = new ScreenStateCommand(
				"cpGestioneApplicazioni", "NO_RESULTS",
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
				"mnuView", "tListaApplicazioni", "btnNuovaApplicazione"};

		String[] act_onRefresh_resok_no_resultsActionstep_0_off = new String[]{
				"btnModificaApplicazione", "btnCancellaApplicazione"};

		String[] act_onRefresh_resok_no_resultsActionstep_0_show = new String[]{
				"mnuView", "tListaApplicazioni", "btnNuovaApplicazione"};

		String[] act_onRefresh_resok_no_resultsActionstep_0_hide = new String[]{
				"btnModificaApplicazione", "btnCancellaApplicazione"};

		ScreenStateCommand act_onRefresh_resok_no_resultsActionstep_0 = new ScreenStateCommand(
				"cpGestioneApplicazioni", "NO_RESULTS",
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
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_some_resultsActionstep_0_on = new String[]{
				"mnuView", "tListaApplicazioni", "btnModificaApplicazione",
				"btnNuovaApplicazione", "btnCancellaApplicazione"};

		String[] act_onRefresh_resok_some_resultsActionstep_0_off = new String[]{};

		String[] act_onRefresh_resok_some_resultsActionstep_0_show = new String[]{
				"mnuView", "tListaApplicazioni", "btnModificaApplicazione",
				"btnNuovaApplicazione", "btnCancellaApplicazione"};

		String[] act_onRefresh_resok_some_resultsActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resok_some_resultsActionstep_0 = new ScreenStateCommand(
				"cpGestioneApplicazioni", "SOME_RESULTS",
				act_onRefresh_resok_some_resultsActionstep_0_on,
				act_onRefresh_resok_some_resultsActionstep_0_off,
				act_onRefresh_resok_some_resultsActionstep_0_show,
				act_onRefresh_resok_some_resultsActionstep_0_hide);
		//Screen State Command end

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneApplicazioniAction",
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

		new DummyUISecConstraint("cpGestioneApplicazioni", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tListaApplicazioni
		UISecConstraint tListaApplicazioni_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"tListaApplicazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tListaApplicazioni_constraints = new UISecConstraint[]{tListaApplicazioni_defaultVisib_ctr};
		UISecConstraint tListaApplicazioni_ctr = new ComplexUISecConstraint(
				tListaApplicazioni_constraints);
		allConstraints.put("tListaApplicazioni", tListaApplicazioni_ctr);

		// constraint fittizio per btnNuovaApplicazione
		UISecConstraint btnNuovaApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"btnNuovaApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNuovaApplicazione_constraints = new UISecConstraint[]{btnNuovaApplicazione_defaultVisib_ctr};
		UISecConstraint btnNuovaApplicazione_ctr = new ComplexUISecConstraint(
				btnNuovaApplicazione_constraints);
		allConstraints.put("btnNuovaApplicazione", btnNuovaApplicazione_ctr);

		// constraint fittizio per btnModificaApplicazione
		UISecConstraint btnModificaApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"btnModificaApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnModificaApplicazione_constraints = new UISecConstraint[]{btnModificaApplicazione_defaultVisib_ctr};
		UISecConstraint btnModificaApplicazione_ctr = new ComplexUISecConstraint(
				btnModificaApplicazione_constraints);
		allConstraints.put("btnModificaApplicazione",
				btnModificaApplicazione_ctr);

		// constraint fittizio per btnCancellaApplicazione
		UISecConstraint btnCancellaApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"btnCancellaApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCancellaApplicazione_constraints = new UISecConstraint[]{btnCancellaApplicazione_defaultVisib_ctr};
		UISecConstraint btnCancellaApplicazione_ctr = new ComplexUISecConstraint(
				btnCancellaApplicazione_constraints);
		allConstraints.put("btnCancellaApplicazione",
				btnCancellaApplicazione_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tListaApplicazioni
		UISecConstraint tListaApplicazioni_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"tListaApplicazioni",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tListaApplicazioni_constraints = new UISecConstraint[]{tListaApplicazioni_defaultOnoff_ctr};
		UISecConstraint tListaApplicazioni_ctr = new ComplexUISecConstraint(
				tListaApplicazioni_constraints);
		allConstraints.put("tListaApplicazioni", tListaApplicazioni_ctr);

		// constraint fittizio per btnNuovaApplicazione
		UISecConstraint btnNuovaApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"btnNuovaApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNuovaApplicazione_constraints = new UISecConstraint[]{btnNuovaApplicazione_defaultOnoff_ctr};
		UISecConstraint btnNuovaApplicazione_ctr = new ComplexUISecConstraint(
				btnNuovaApplicazione_constraints);
		allConstraints.put("btnNuovaApplicazione", btnNuovaApplicazione_ctr);

		// constraint fittizio per btnModificaApplicazione
		UISecConstraint btnModificaApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"btnModificaApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnModificaApplicazione_constraints = new UISecConstraint[]{btnModificaApplicazione_defaultOnoff_ctr};
		UISecConstraint btnModificaApplicazione_ctr = new ComplexUISecConstraint(
				btnModificaApplicazione_constraints);
		allConstraints.put("btnModificaApplicazione",
				btnModificaApplicazione_ctr);

		// constraint fittizio per btnCancellaApplicazione
		UISecConstraint btnCancellaApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneApplicazioni",
				"btnCancellaApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCancellaApplicazione_constraints = new UISecConstraint[]{btnCancellaApplicazione_defaultOnoff_ctr};
		UISecConstraint btnCancellaApplicazione_ctr = new ComplexUISecConstraint(
				btnCancellaApplicazione_constraints);
		allConstraints.put("btnCancellaApplicazione",
				btnCancellaApplicazione_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneApplicazioniAction::dumpmodel] START");

		log.debug("[CpGestioneApplicazioniAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneApplicazioniAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneApplicazioniAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneApplicazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneApplicazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneApplicazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpGestioneApplicazioniAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneApplicazioni");
		log.debug("[CpGestioneApplicazioniAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneApplicazioniAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneApplicazioniAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnNuovaApplicazione per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnNuovaApplicazioneByEvh = new HashMap<String, ICommand>();

		cmds4btnNuovaApplicazioneByEvh.put("CLICKED",
				initCommandBtnNuovaApplicazione_CLICKED());
		cmdsByWidget
				.put("btnNuovaApplicazione", cmds4btnNuovaApplicazioneByEvh);
		// contiene i comandi del widget btnModificaApplicazione per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnModificaApplicazioneByEvh = new HashMap<String, ICommand>();

		cmds4btnModificaApplicazioneByEvh.put("CLICKED",
				initCommandBtnModificaApplicazione_CLICKED());
		cmdsByWidget.put("btnModificaApplicazione",
				cmds4btnModificaApplicazioneByEvh);
		// contiene i comandi del widget btnCancellaApplicazione per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCancellaApplicazioneByEvh = new HashMap<String, ICommand>();

		cmds4btnCancellaApplicazioneByEvh.put("CLICKED",
				initCommandBtnCancellaApplicazione_CLICKED());
		cmdsByWidget.put("btnCancellaApplicazione",
				cmds4btnCancellaApplicazioneByEvh);

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

	private ICommand initCommandBtnNuovaApplicazione_CLICKED() {
		// ExecCommand begin
		String[] resultNames4nuovaApplicazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4nuovaApplicazione = new ICommand[2];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnNuovaApplicazione_resokActionstep_0 = new JumpCommand(
				"cpDettaglioApplicazioneNew", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnNuovaApplicazione_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnNuovaApplicazione_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnNuovaApplicazione_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnNuovaApplicazione_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4nuovaApplicazione[0] = act_actions_clicked_btnNuovaApplicazione_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnNuovaApplicazione_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnNuovaApplicazione_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnNuovaApplicazione_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnNuovaApplicazione_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnNuovaApplicazione_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4nuovaApplicazione[1] = act_actions_clicked_btnNuovaApplicazione_reskoAction_1;

		ExecCommand act_actions_clicked_btnNuovaApplicazione_1 = new ExecCommand(
				resultNames4nuovaApplicazione,
				actionsForResults4nuovaApplicazione) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.nuovaApplicazione(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneApplicazioniAction",
							"readOne()", "chiamata verso BackEnd",
							"nuovaApplicazione");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [nuovaApplicazione]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnNuovaApplicazione_1;
	}

	private ICommand initCommandBtnModificaApplicazione_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaApplicazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4modificaApplicazione = new ICommand[2];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnModificaApplicazione_resokActionstep_0 = new JumpCommand(
				"cpDettaglioApplicazione", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnModificaApplicazione_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnModificaApplicazione_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnModificaApplicazione_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnModificaApplicazione_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4modificaApplicazione[0] = act_actions_clicked_btnModificaApplicazione_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnModificaApplicazione_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnModificaApplicazione_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnModificaApplicazione_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnModificaApplicazione_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnModificaApplicazione_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4modificaApplicazione[1] = act_actions_clicked_btnModificaApplicazione_reskoAction_1;

		ExecCommand act_actions_clicked_btnModificaApplicazione_1 = new ExecCommand(
				resultNames4modificaApplicazione,
				actionsForResults4modificaApplicazione) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaApplicazione(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneApplicazioniAction",
							"readOne()", "chiamata verso BackEnd",
							"modificaApplicazione");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaApplicazione]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnModificaApplicazione_1;
	}

	private ICommand initCommandBtnCancellaApplicazione_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cancellaApplicazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4cancellaApplicazione = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCancellaApplicazione_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4cancellaApplicazione[0] = act_actions_clicked_btnCancellaApplicazione_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCancellaApplicazione_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4cancellaApplicazione[1] = act_actions_clicked_btnCancellaApplicazione_reskoAction_1;

		ExecCommand act_actions_clicked_btnCancellaApplicazione_1 = new ExecCommand(
				resultNames4cancellaApplicazione,
				actionsForResults4cancellaApplicazione) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cancellaApplicazione(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneApplicazioniAction",
							"readOne()", "chiamata verso BackEnd",
							"cancellaApplicazione");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cancellaApplicazione]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCancellaApplicazione_1;
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
