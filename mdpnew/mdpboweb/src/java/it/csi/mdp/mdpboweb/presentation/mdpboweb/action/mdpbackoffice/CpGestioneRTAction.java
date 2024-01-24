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
 * CpGestioneRTAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneRTAction extends BaseAction implements Preparable {

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

	public void setAppDatalistaRT(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche> value) {
		getSession().put("appDatalistaRT", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche> getAppDatalistaRT() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche>) (getSession()
				.get("appDatalistaRT"));
	}

	public void setAppDataricercaRT(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche value) {
		getSession().put("appDataricercaRT", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche getAppDataricercaRT() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.RicevuteTelematiche) (getSession()
				.get("appDataricercaRT"));
	}

	public void setAppDataselettoreIdRT(java.lang.String value) {
		getSession().put("appDataselettoreIdRT", value);
	}

	public java.lang.String getAppDataselettoreIdRT() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRT"));
	}

	public void setAppDatacodiciEsitoPagamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento value) {
		getSession().put("appDatacodiciEsitoPagamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento getAppDatacodiciEsitoPagamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento) (getSession()
				.get("appDatacodiciEsitoPagamento"));
	}

	public void setAppDatalistaCodiciEsitoPagamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento> value) {
		getSession().put("appDatalistaCodiciEsitoPagamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento> getAppDatalistaCodiciEsitoPagamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.CodiciEsitoPagamento>) (getSession()
				.get("appDatalistaCodiciEsitoPagamento"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [cerca]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleCerca_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("cerca", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRTAction::handleCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRTAction::handleCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRTAction::handleCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRTAction::handleCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRTAction::handleCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [exportRT]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleExportRT_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("exportRT", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRTAction::handleExportRT_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRTAction::handleExportRT_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRTAction::handleExportRT_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRTAction::handleExportRT_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRTAction::handleExportRT_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [esportRPTassociata]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleEsportRPTassociata_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("esportRPTassociata", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRTAction::handleEsportRPTassociata_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRTAction::handleEsportRPTassociata_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRTAction::handleEsportRPTassociata_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRTAction::handleEsportRPTassociata_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRTAction::handleEsportRPTassociata_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbListaStatiRpt.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbListaStatiRpt_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaCodiciEsitoPagamento");

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
		Object dataToProvide = ctx.getValueStack().findValue("appDatalistaRT");

		if (isTableClearStatus("cpGestioneRT_tRicerca")) {
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
		/*PROTECTED REGION ID(R725534772) ENABLED START*/
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
				log.error("[CpGestioneRTAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneRTAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Iniziale", "Ricerca"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeActionstep_0_on = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "insertDateDa", "cbListaStatiRpt", "cerca",
				"lastUpdateA", "insertDateA"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{
				"idMsgRichiesta", "tRicerca", "exportRT", "esportRPTassociata"};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "insertDateDa", "cbListaStatiRpt", "cerca",
				"lastUpdateA", "insertDateA"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{
				"idMsgRichiesta", "tRicerca", "exportRT", "esportRPTassociata"};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpGestioneRT", "VIEW_INIZIALE",
				act_onRefresh_resinizialeActionstep_0_on,
				act_onRefresh_resinizialeActionstep_0_off,
				act_onRefresh_resinizialeActionstep_0_show,
				act_onRefresh_resinizialeActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resinizialeAction_0_steps = new ICommand[]{act_onRefresh_resinizialeActionstep_0};
		SequenceCommand act_onRefresh_resinizialeAction_0 = new SequenceCommand(
				act_onRefresh_resinizialeAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resinizialeAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resricercaActionstep_0_on = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "cbListaStatiRpt", "cerca", "tRicerca",
				"exportRT", "esportRPTassociata", "lastUpdateA", "insertDateA"};

		String[] act_onRefresh_resricercaActionstep_0_off = new String[]{
				"insertDateDa", "idMsgRichiesta"};

		String[] act_onRefresh_resricercaActionstep_0_show = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "insertDateDa", "cbListaStatiRpt", "cerca",
				"tRicerca", "exportRT", "esportRPTassociata", "lastUpdateA",
				"insertDateA"};

		String[] act_onRefresh_resricercaActionstep_0_hide = new String[]{"idMsgRichiesta"};

		ScreenStateCommand act_onRefresh_resricercaActionstep_0 = new ScreenStateCommand(
				"cpGestioneRT", "VIEW_RICERCA_OK",
				act_onRefresh_resricercaActionstep_0_on,
				act_onRefresh_resricercaActionstep_0_off,
				act_onRefresh_resricercaActionstep_0_show,
				act_onRefresh_resricercaActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resricercaAction_1_steps = new ICommand[]{act_onRefresh_resricercaActionstep_0};
		SequenceCommand act_onRefresh_resricercaAction_1 = new SequenceCommand(
				act_onRefresh_resricercaAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resricercaAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRTAction", "readOne()",
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

		new DummyUISecConstraint("cpGestioneRT", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "iuv",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultVisib_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "idTransazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultVisib_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per idApplicazione
		UISecConstraint idApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "idApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idApplicazione_constraints = new UISecConstraint[]{idApplicazione_defaultVisib_ctr};
		UISecConstraint idApplicazione_ctr = new ComplexUISecConstraint(
				idApplicazione_constraints);
		allConstraints.put("idApplicazione", idApplicazione_ctr);

		// constraint fittizio per lastUpdateDa
		UISecConstraint lastUpdateDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "lastUpdateDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateDa_constraints = new UISecConstraint[]{lastUpdateDa_defaultVisib_ctr};
		UISecConstraint lastUpdateDa_ctr = new ComplexUISecConstraint(
				lastUpdateDa_constraints);
		allConstraints.put("lastUpdateDa", lastUpdateDa_ctr);

		// constraint fittizio per lastUpdateA
		UISecConstraint lastUpdateA_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "lastUpdateA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateA_constraints = new UISecConstraint[]{lastUpdateA_defaultVisib_ctr};
		UISecConstraint lastUpdateA_ctr = new ComplexUISecConstraint(
				lastUpdateA_constraints);
		allConstraints.put("lastUpdateA", lastUpdateA_ctr);

		// constraint fittizio per insertDateDa
		UISecConstraint insertDateDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "insertDateDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateDa_constraints = new UISecConstraint[]{insertDateDa_defaultVisib_ctr};
		UISecConstraint insertDateDa_ctr = new ComplexUISecConstraint(
				insertDateDa_constraints);
		allConstraints.put("insertDateDa", insertDateDa_ctr);

		// constraint fittizio per insertDateA
		UISecConstraint insertDateA_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "insertDateA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateA_constraints = new UISecConstraint[]{insertDateA_defaultVisib_ctr};
		UISecConstraint insertDateA_ctr = new ComplexUISecConstraint(
				insertDateA_constraints);
		allConstraints.put("insertDateA", insertDateA_ctr);

		// constraint fittizio per cbListaStatiRpt
		UISecConstraint cbListaStatiRpt_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "cbListaStatiRpt",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaStatiRpt_constraints = new UISecConstraint[]{cbListaStatiRpt_defaultVisib_ctr};
		UISecConstraint cbListaStatiRpt_ctr = new ComplexUISecConstraint(
				cbListaStatiRpt_constraints);
		allConstraints.put("cbListaStatiRpt", cbListaStatiRpt_ctr);

		// constraint fittizio per idMsgRichiesta
		UISecConstraint idMsgRichiesta_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "idMsgRichiesta",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idMsgRichiesta_constraints = new UISecConstraint[]{idMsgRichiesta_defaultVisib_ctr};
		UISecConstraint idMsgRichiesta_ctr = new ComplexUISecConstraint(
				idMsgRichiesta_constraints);
		allConstraints.put("idMsgRichiesta", idMsgRichiesta_ctr);

		// constraint fittizio per cerca
		UISecConstraint cerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "cerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cerca_constraints = new UISecConstraint[]{cerca_defaultVisib_ctr};
		UISecConstraint cerca_ctr = new ComplexUISecConstraint(
				cerca_constraints);
		allConstraints.put("cerca", cerca_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "tRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultVisib_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per exportRT
		UISecConstraint exportRT_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "exportRT",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] exportRT_constraints = new UISecConstraint[]{exportRT_defaultVisib_ctr};
		UISecConstraint exportRT_ctr = new ComplexUISecConstraint(
				exportRT_constraints);
		allConstraints.put("exportRT", exportRT_ctr);

		// constraint fittizio per esportRPTassociata
		UISecConstraint esportRPTassociata_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRT", "esportRPTassociata",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] esportRPTassociata_constraints = new UISecConstraint[]{esportRPTassociata_defaultVisib_ctr};
		UISecConstraint esportRPTassociata_ctr = new ComplexUISecConstraint(
				esportRPTassociata_constraints);
		allConstraints.put("esportRPTassociata", esportRPTassociata_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "iuv",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultOnoff_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "idTransazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultOnoff_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per idApplicazione
		UISecConstraint idApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "idApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idApplicazione_constraints = new UISecConstraint[]{idApplicazione_defaultOnoff_ctr};
		UISecConstraint idApplicazione_ctr = new ComplexUISecConstraint(
				idApplicazione_constraints);
		allConstraints.put("idApplicazione", idApplicazione_ctr);

		// constraint fittizio per lastUpdateDa
		UISecConstraint lastUpdateDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "lastUpdateDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateDa_constraints = new UISecConstraint[]{lastUpdateDa_defaultOnoff_ctr};
		UISecConstraint lastUpdateDa_ctr = new ComplexUISecConstraint(
				lastUpdateDa_constraints);
		allConstraints.put("lastUpdateDa", lastUpdateDa_ctr);

		// constraint fittizio per lastUpdateA
		UISecConstraint lastUpdateA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "lastUpdateA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateA_constraints = new UISecConstraint[]{lastUpdateA_defaultOnoff_ctr};
		UISecConstraint lastUpdateA_ctr = new ComplexUISecConstraint(
				lastUpdateA_constraints);
		allConstraints.put("lastUpdateA", lastUpdateA_ctr);

		// constraint fittizio per insertDateDa
		UISecConstraint insertDateDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "insertDateDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateDa_constraints = new UISecConstraint[]{insertDateDa_defaultOnoff_ctr};
		UISecConstraint insertDateDa_ctr = new ComplexUISecConstraint(
				insertDateDa_constraints);
		allConstraints.put("insertDateDa", insertDateDa_ctr);

		// constraint fittizio per insertDateA
		UISecConstraint insertDateA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "insertDateA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateA_constraints = new UISecConstraint[]{insertDateA_defaultOnoff_ctr};
		UISecConstraint insertDateA_ctr = new ComplexUISecConstraint(
				insertDateA_constraints);
		allConstraints.put("insertDateA", insertDateA_ctr);

		// constraint fittizio per cbListaStatiRpt
		UISecConstraint cbListaStatiRpt_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "cbListaStatiRpt",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaStatiRpt_constraints = new UISecConstraint[]{cbListaStatiRpt_defaultOnoff_ctr};
		UISecConstraint cbListaStatiRpt_ctr = new ComplexUISecConstraint(
				cbListaStatiRpt_constraints);
		allConstraints.put("cbListaStatiRpt", cbListaStatiRpt_ctr);

		// constraint fittizio per idMsgRichiesta
		UISecConstraint idMsgRichiesta_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "idMsgRichiesta",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idMsgRichiesta_constraints = new UISecConstraint[]{idMsgRichiesta_defaultOnoff_ctr};
		UISecConstraint idMsgRichiesta_ctr = new ComplexUISecConstraint(
				idMsgRichiesta_constraints);
		allConstraints.put("idMsgRichiesta", idMsgRichiesta_ctr);

		// constraint fittizio per cerca
		UISecConstraint cerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "cerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cerca_constraints = new UISecConstraint[]{cerca_defaultOnoff_ctr};
		UISecConstraint cerca_ctr = new ComplexUISecConstraint(
				cerca_constraints);
		allConstraints.put("cerca", cerca_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "tRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultOnoff_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per exportRT
		UISecConstraint exportRT_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "exportRT",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] exportRT_constraints = new UISecConstraint[]{exportRT_defaultOnoff_ctr};
		UISecConstraint exportRT_ctr = new ComplexUISecConstraint(
				exportRT_constraints);
		allConstraints.put("exportRT", exportRT_ctr);

		// constraint fittizio per esportRPTassociata
		UISecConstraint esportRPTassociata_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRT", "esportRPTassociata",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] esportRPTassociata_constraints = new UISecConstraint[]{esportRPTassociata_defaultOnoff_ctr};
		UISecConstraint esportRPTassociata_ctr = new ComplexUISecConstraint(
				esportRPTassociata_constraints);
		allConstraints.put("esportRPTassociata", esportRPTassociata_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneRTAction::dumpmodel] START");

		log.debug("[CpGestioneRTAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneRTAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneRTAction::dumpmodel] " + pd.getName()
							+ ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneRTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneRTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneRTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpGestioneRTAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneRT");
		log.debug("[CpGestioneRTAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneRTAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneRTAction::dumpmodel] " + session);
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

		// contiene i comandi del widget cerca per ogni Ev.H.
		HashMap<String, ICommand> cmds4cercaByEvh = new HashMap<String, ICommand>();

		cmds4cercaByEvh.put("CLICKED", initCommandCerca_CLICKED());
		cmdsByWidget.put("cerca", cmds4cercaByEvh);
		// contiene i comandi del widget exportRT per ogni Ev.H.
		HashMap<String, ICommand> cmds4exportRTByEvh = new HashMap<String, ICommand>();

		cmds4exportRTByEvh.put("CLICKED", initCommandExportRT_CLICKED());
		cmdsByWidget.put("exportRT", cmds4exportRTByEvh);
		// contiene i comandi del widget esportRPTassociata per ogni Ev.H.
		HashMap<String, ICommand> cmds4esportRPTassociataByEvh = new HashMap<String, ICommand>();

		cmds4esportRPTassociataByEvh.put("CLICKED",
				initCommandEsportRPTassociata_CLICKED());
		cmdsByWidget.put("esportRPTassociata", cmds4esportRPTassociataByEvh);

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

	private ICommand initCommandCerca_CLICKED() {
		// ExecCommand begin
		String[] resultNames4ricercaRT = new String[]{"NO_RESULT", "RESULT"};

		ICommand[] actionsForResults4ricercaRT = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resno_resultActionstep_0_on = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "lastUpdateA", "insertDateDa", "insertDateA",
				"cbListaStatiRpt", "idMsgRichiesta", "cerca", "tRicerca"};

		String[] act_actions_clicked_cerca_resno_resultActionstep_0_off = new String[]{
				"exportRT", "esportRPTassociata"};

		String[] act_actions_clicked_cerca_resno_resultActionstep_0_show = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "lastUpdateA", "insertDateDa", "insertDateA",
				"cbListaStatiRpt", "idMsgRichiesta", "cerca", "tRicerca"};

		String[] act_actions_clicked_cerca_resno_resultActionstep_0_hide = new String[]{
				"exportRT", "esportRPTassociata"};

		ScreenStateCommand act_actions_clicked_cerca_resno_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneRT", "NO_RESULT",
				act_actions_clicked_cerca_resno_resultActionstep_0_on,
				act_actions_clicked_cerca_resno_resultActionstep_0_off,
				act_actions_clicked_cerca_resno_resultActionstep_0_show,
				act_actions_clicked_cerca_resno_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resno_resultAction_0_steps = new ICommand[]{act_actions_clicked_cerca_resno_resultActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resno_resultAction_0 = new SequenceCommand(
				act_actions_clicked_cerca_resno_resultAction_0_steps);
		// SequenceCommand end
		actionsForResults4ricercaRT[0] = act_actions_clicked_cerca_resno_resultAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resresultActionstep_0_on = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "cbListaStatiRpt", "cerca", "tRicerca",
				"exportRT", "esportRPTassociata", "lastUpdateA", "insertDateA"};

		String[] act_actions_clicked_cerca_resresultActionstep_0_off = new String[]{
				"insertDateDa", "idMsgRichiesta"};

		String[] act_actions_clicked_cerca_resresultActionstep_0_show = new String[]{
				"mnuView", "iuv", "idTransazione", "idApplicazione",
				"lastUpdateDa", "insertDateDa", "cbListaStatiRpt", "cerca",
				"tRicerca", "exportRT", "esportRPTassociata", "lastUpdateA",
				"insertDateA"};

		String[] act_actions_clicked_cerca_resresultActionstep_0_hide = new String[]{"idMsgRichiesta"};

		ScreenStateCommand act_actions_clicked_cerca_resresultActionstep_0 = new ScreenStateCommand(
				"cpGestioneRT", "VIEW_RICERCA_OK",
				act_actions_clicked_cerca_resresultActionstep_0_on,
				act_actions_clicked_cerca_resresultActionstep_0_off,
				act_actions_clicked_cerca_resresultActionstep_0_show,
				act_actions_clicked_cerca_resresultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resresultAction_1_steps = new ICommand[]{act_actions_clicked_cerca_resresultActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resresultAction_1 = new SequenceCommand(
				act_actions_clicked_cerca_resresultAction_1_steps);
		// SequenceCommand end
		actionsForResults4ricercaRT[1] = act_actions_clicked_cerca_resresultAction_1;

		ExecCommand act_actions_clicked_cerca_1 = new ExecCommand(
				resultNames4ricercaRT, actionsForResults4ricercaRT) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.ricercaRT(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRTAction", "readOne()",
							"chiamata verso BackEnd", "ricercaRT");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [ricercaRT]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_cerca_1;
	}

	private ICommand initCommandExportRT_CLICKED() {
		// ExecCommand begin
		String[] resultNames4esportaRtXml = new String[]{"ESPORTA",
				"ESPORTA_KO"};

		ICommand[] actionsForResults4esportaRtXml = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_exportRT_resesportaAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esportaRtXml[0] = act_actions_clicked_exportRT_resesportaAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_exportRT_resesporta_koAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esportaRtXml[1] = act_actions_clicked_exportRT_resesporta_koAction_1;

		ExecCommand act_actions_clicked_exportRT_1 = new ExecCommand(
				resultNames4esportaRtXml, actionsForResults4esportaRtXml) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.esportaRtXml(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRTAction", "readOne()",
							"chiamata verso BackEnd", "esportaRtXml");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [esportaRtXml]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_exportRT_1;
	}

	private ICommand initCommandEsportRPTassociata_CLICKED() {
		// ExecCommand begin
		String[] resultNames4esportaRPTassociata = new String[]{"OK"};

		ICommand[] actionsForResults4esportaRPTassociata = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_esportRPTassociata_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esportaRPTassociata[0] = act_actions_clicked_esportRPTassociata_resokAction_0;

		ExecCommand act_actions_clicked_esportRPTassociata_1 = new ExecCommand(
				resultNames4esportaRPTassociata,
				actionsForResults4esportaRPTassociata) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.esportaRPTassociata(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRTAction", "readOne()",
							"chiamata verso BackEnd", "esportaRPTassociata");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [esportaRPTassociata]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_esportRPTassociata_1;
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
