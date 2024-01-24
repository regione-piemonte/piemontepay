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
 * CpFlussoSingoloPagamentoAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpFlussoSingoloPagamentoAction extends BaseAction
		implements
			Preparable {

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataflussoSingoloPagamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento value) {
		getSession().put("appDataflussoSingoloPagamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento getAppDataflussoSingoloPagamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento) (getSession()
				.get("appDataflussoSingoloPagamento"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatalistaFlussoSingoloPagamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento> value) {
		getSession().put("appDatalistaFlussoSingoloPagamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento> getAppDatalistaFlussoSingoloPagamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento>) (getSession()
				.get("appDatalistaFlussoSingoloPagamento"));
	}

	public void setAppDataricercaFlussoSingoloPagamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoSingoloPagamento value) {
		getSession().put("appDataricercaFlussoSingoloPagamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoSingoloPagamento getAppDataricercaFlussoSingoloPagamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoSingoloPagamento) (getSession()
				.get("appDataricercaFlussoSingoloPagamento"));
	}

	public void setAppDataselettoreFlussoSingoloPagamento(java.lang.String value) {
		getSession().put("appDataselettoreFlussoSingoloPagamento", value);
	}

	public java.lang.String getAppDataselettoreFlussoSingoloPagamento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreFlussoSingoloPagamento"));
	}

	public void setAppDataselettoreFlussoRiversamento(java.lang.String value) {
		getSession().put("appDataselettoreFlussoRiversamento", value);
	}

	public java.lang.String getAppDataselettoreFlussoRiversamento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreFlussoRiversamento"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataapplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataapplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataapplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataapplicazioni"));
	}

	public void setAppDataflussoRiversamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento value) {
		getSession().put("appDataflussoRiversamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento getAppDataflussoRiversamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento) (getSession()
				.get("appDataflussoRiversamento"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btricerca]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtricerca_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btricerca", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpFlussoSingoloPagamentoAction::handleBtricerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoSingoloPagamentoAction::handleBtricerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoSingoloPagamentoAction::handleBtricerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoSingoloPagamentoAction::handleBtricerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoSingoloPagamentoAction::handleBtricerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [goToFlusso]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleGoToFlusso_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("goToFlusso", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpFlussoSingoloPagamentoAction::handleGoToFlusso_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoSingoloPagamentoAction::handleGoToFlusso_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoSingoloPagamentoAction::handleGoToFlusso_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoSingoloPagamentoAction::handleGoToFlusso_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoSingoloPagamentoAction::handleGoToFlusso_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbListaApplication.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbListaApplication_DATASET()
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
	 * al data-binding relativo al dataset DATASET del widget tbRicerca.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbRicerca_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaFlussoSingoloPagamento");

		if (isTableClearStatus("cpFlussoSingoloPagamento_tbRicerca")) {
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
		/*PROTECTED REGION ID(R1070509423) ENABLED START*/
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
				log.error("[CpFlussoSingoloPagamentoAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpFlussoSingoloPagamentoAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"RESULT", "INIZIALE"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resresultAction_0_on = new String[]{"mnuView",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca", "tbRicerca", "goToFlusso"};

		String[] act_onRefresh_resresultAction_0_off = new String[]{"txidentificativoistitutoricevente"};

		String[] act_onRefresh_resresultAction_0_show = new String[]{"mnuView",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca", "tbRicerca", "goToFlusso"};

		String[] act_onRefresh_resresultAction_0_hide = new String[]{"txidentificativoistitutoricevente"};

		ScreenStateCommand act_onRefresh_resresultAction_0 = new ScreenStateCommand(
				"cpFlussoSingoloPagamento", "RESULT",
				act_onRefresh_resresultAction_0_on,
				act_onRefresh_resresultAction_0_off,
				act_onRefresh_resresultAction_0_show,
				act_onRefresh_resresultAction_0_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[0] = act_onRefresh_resresultAction_0;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeAction_1_on = new String[]{"mnuView",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca"};

		String[] act_onRefresh_resinizialeAction_1_off = new String[]{
				"txidentificativoistitutoricevente", "tbRicerca", "goToFlusso"};

		String[] act_onRefresh_resinizialeAction_1_show = new String[]{
				"mnuView", "txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca"};

		String[] act_onRefresh_resinizialeAction_1_hide = new String[]{
				"txidentificativoistitutoricevente", "tbRicerca", "goToFlusso"};

		ScreenStateCommand act_onRefresh_resinizialeAction_1 = new ScreenStateCommand(
				"cpFlussoSingoloPagamento", "INIZIALE",
				act_onRefresh_resinizialeAction_1_on,
				act_onRefresh_resinizialeAction_1_off,
				act_onRefresh_resinizialeAction_1_show,
				act_onRefresh_resinizialeAction_1_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[1] = act_onRefresh_resinizialeAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoSingoloPagamentoAction",
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

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txDataRegolamentoDa
		UISecConstraint txDataRegolamentoDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"txDataRegolamentoDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoDa_constraints = new UISecConstraint[]{txDataRegolamentoDa_defaultVisib_ctr};
		UISecConstraint txDataRegolamentoDa_ctr = new ComplexUISecConstraint(
				txDataRegolamentoDa_constraints);
		allConstraints.put("txDataRegolamentoDa", txDataRegolamentoDa_ctr);

		// constraint fittizio per txDataRegolamentoA
		UISecConstraint txDataRegolamentoA_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"txDataRegolamentoA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoA_constraints = new UISecConstraint[]{txDataRegolamentoA_defaultVisib_ctr};
		UISecConstraint txDataRegolamentoA_ctr = new ComplexUISecConstraint(
				txDataRegolamentoA_constraints);
		allConstraints.put("txDataRegolamentoA", txDataRegolamentoA_ctr);

		// constraint fittizio per cbListaApplication
		UISecConstraint cbListaApplication_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"cbListaApplication",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaApplication_constraints = new UISecConstraint[]{cbListaApplication_defaultVisib_ctr};
		UISecConstraint cbListaApplication_ctr = new ComplexUISecConstraint(
				cbListaApplication_constraints);
		allConstraints.put("cbListaApplication", cbListaApplication_ctr);

		// constraint fittizio per txidentificativoistitutoricevente
		UISecConstraint txidentificativoistitutoricevente_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"txidentificativoistitutoricevente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoistitutoricevente_constraints = new UISecConstraint[]{txidentificativoistitutoricevente_defaultVisib_ctr};
		UISecConstraint txidentificativoistitutoricevente_ctr = new ComplexUISecConstraint(
				txidentificativoistitutoricevente_constraints);
		allConstraints.put("txidentificativoistitutoricevente",
				txidentificativoistitutoricevente_ctr);

		// constraint fittizio per btricerca
		UISecConstraint btricerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "btricerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btricerca_constraints = new UISecConstraint[]{btricerca_defaultVisib_ctr};
		UISecConstraint btricerca_ctr = new ComplexUISecConstraint(
				btricerca_constraints);
		allConstraints.put("btricerca", btricerca_ctr);

		// constraint fittizio per tbRicerca
		UISecConstraint tbRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "tbRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRicerca_constraints = new UISecConstraint[]{tbRicerca_defaultVisib_ctr};
		UISecConstraint tbRicerca_ctr = new ComplexUISecConstraint(
				tbRicerca_constraints);
		allConstraints.put("tbRicerca", tbRicerca_ctr);

		// constraint fittizio per goToFlusso
		UISecConstraint goToFlusso_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "goToFlusso",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] goToFlusso_constraints = new UISecConstraint[]{goToFlusso_defaultVisib_ctr};
		UISecConstraint goToFlusso_ctr = new ComplexUISecConstraint(
				goToFlusso_constraints);
		allConstraints.put("goToFlusso", goToFlusso_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txDataRegolamentoDa
		UISecConstraint txDataRegolamentoDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"txDataRegolamentoDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoDa_constraints = new UISecConstraint[]{txDataRegolamentoDa_defaultOnoff_ctr};
		UISecConstraint txDataRegolamentoDa_ctr = new ComplexUISecConstraint(
				txDataRegolamentoDa_constraints);
		allConstraints.put("txDataRegolamentoDa", txDataRegolamentoDa_ctr);

		// constraint fittizio per txDataRegolamentoA
		UISecConstraint txDataRegolamentoA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"txDataRegolamentoA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoA_constraints = new UISecConstraint[]{txDataRegolamentoA_defaultOnoff_ctr};
		UISecConstraint txDataRegolamentoA_ctr = new ComplexUISecConstraint(
				txDataRegolamentoA_constraints);
		allConstraints.put("txDataRegolamentoA", txDataRegolamentoA_ctr);

		// constraint fittizio per cbListaApplication
		UISecConstraint cbListaApplication_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"cbListaApplication",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaApplication_constraints = new UISecConstraint[]{cbListaApplication_defaultOnoff_ctr};
		UISecConstraint cbListaApplication_ctr = new ComplexUISecConstraint(
				cbListaApplication_constraints);
		allConstraints.put("cbListaApplication", cbListaApplication_ctr);

		// constraint fittizio per txidentificativoistitutoricevente
		UISecConstraint txidentificativoistitutoricevente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento",
				"txidentificativoistitutoricevente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoistitutoricevente_constraints = new UISecConstraint[]{txidentificativoistitutoricevente_defaultOnoff_ctr};
		UISecConstraint txidentificativoistitutoricevente_ctr = new ComplexUISecConstraint(
				txidentificativoistitutoricevente_constraints);
		allConstraints.put("txidentificativoistitutoricevente",
				txidentificativoistitutoricevente_ctr);

		// constraint fittizio per btricerca
		UISecConstraint btricerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "btricerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btricerca_constraints = new UISecConstraint[]{btricerca_defaultOnoff_ctr};
		UISecConstraint btricerca_ctr = new ComplexUISecConstraint(
				btricerca_constraints);
		allConstraints.put("btricerca", btricerca_ctr);

		// constraint fittizio per tbRicerca
		UISecConstraint tbRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "tbRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRicerca_constraints = new UISecConstraint[]{tbRicerca_defaultOnoff_ctr};
		UISecConstraint tbRicerca_ctr = new ComplexUISecConstraint(
				tbRicerca_constraints);
		allConstraints.put("tbRicerca", tbRicerca_ctr);

		// constraint fittizio per goToFlusso
		UISecConstraint goToFlusso_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoSingoloPagamento", "goToFlusso",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] goToFlusso_constraints = new UISecConstraint[]{goToFlusso_defaultOnoff_ctr};
		UISecConstraint goToFlusso_ctr = new ComplexUISecConstraint(
				goToFlusso_constraints);
		allConstraints.put("goToFlusso", goToFlusso_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] START");

		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpFlussoSingoloPagamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpFlussoSingoloPagamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpFlussoSingoloPagamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpFlussoSingoloPagamento");
		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] [c] sessione");
		log.debug("[CpFlussoSingoloPagamentoAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btricerca per ogni Ev.H.
		HashMap<String, ICommand> cmds4btricercaByEvh = new HashMap<String, ICommand>();

		cmds4btricercaByEvh.put("CLICKED", initCommandBtricerca_CLICKED());
		cmdsByWidget.put("btricerca", cmds4btricercaByEvh);
		// contiene i comandi del widget goToFlusso per ogni Ev.H.
		HashMap<String, ICommand> cmds4goToFlussoByEvh = new HashMap<String, ICommand>();

		cmds4goToFlussoByEvh.put("CLICKED", initCommandGoToFlusso_CLICKED());
		cmdsByWidget.put("goToFlusso", cmds4goToFlussoByEvh);

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

	private ICommand initCommandBtricerca_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4ricercaSingliFlussi = new String[]{"result",
				"no_result"};

		ICommand[] actionsForResults4ricercaSingliFlussi = new ICommand[2];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btricercastep_resresultAction_0_on = new String[]{
				"mnuView", "txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca", "tbRicerca", "goToFlusso"};

		String[] act_actions_clicked_btricercastep_resresultAction_0_off = new String[]{"txidentificativoistitutoricevente"};

		String[] act_actions_clicked_btricercastep_resresultAction_0_show = new String[]{
				"mnuView", "txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca", "tbRicerca", "goToFlusso"};

		String[] act_actions_clicked_btricercastep_resresultAction_0_hide = new String[]{"txidentificativoistitutoricevente"};

		ScreenStateCommand act_actions_clicked_btricercastep_resresultAction_0 = new ScreenStateCommand(
				"cpFlussoSingoloPagamento", "RESULT",
				act_actions_clicked_btricercastep_resresultAction_0_on,
				act_actions_clicked_btricercastep_resresultAction_0_off,
				act_actions_clicked_btricercastep_resresultAction_0_show,
				act_actions_clicked_btricercastep_resresultAction_0_hide);
		//Screen State Command end
		actionsForResults4ricercaSingliFlussi[0] = act_actions_clicked_btricercastep_resresultAction_0;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btricercastep_resno_resultAction_1_on = new String[]{
				"mnuView", "txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca"};

		String[] act_actions_clicked_btricercastep_resno_resultAction_1_off = new String[]{
				"txidentificativoistitutoricevente", "tbRicerca", "goToFlusso"};

		String[] act_actions_clicked_btricercastep_resno_resultAction_1_show = new String[]{
				"mnuView", "txDataRegolamentoDa", "txDataRegolamentoA",
				"cbListaApplication", "btricerca"};

		String[] act_actions_clicked_btricercastep_resno_resultAction_1_hide = new String[]{
				"txidentificativoistitutoricevente", "tbRicerca", "goToFlusso"};

		ScreenStateCommand act_actions_clicked_btricercastep_resno_resultAction_1 = new ScreenStateCommand(
				"cpFlussoSingoloPagamento", "INIZIALE",
				act_actions_clicked_btricercastep_resno_resultAction_1_on,
				act_actions_clicked_btricercastep_resno_resultAction_1_off,
				act_actions_clicked_btricercastep_resno_resultAction_1_show,
				act_actions_clicked_btricercastep_resno_resultAction_1_hide);
		//Screen State Command end
		actionsForResults4ricercaSingliFlussi[1] = act_actions_clicked_btricercastep_resno_resultAction_1;

		ExecCommand act_actions_clicked_btricercastep_0 = new ExecCommand(
				resultNames4ricercaSingliFlussi,
				actionsForResults4ricercaSingliFlussi) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.ricercaSingliFlussi(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoSingoloPagamentoAction",
							"readOne()", "chiamata verso BackEnd",
							"ricercaSingliFlussi");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [ricercaSingliFlussi]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btricerca_1_steps = new ICommand[]{act_actions_clicked_btricercastep_0};
		SequenceCommand act_actions_clicked_btricerca_1 = new SequenceCommand(
				act_actions_clicked_btricerca_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btricerca_1;
	}

	private ICommand initCommandGoToFlusso_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4goToTestata = new String[]{"vaiTestata",
				"NO_SELECTION"};

		ICommand[] actionsForResults4goToTestata = new ICommand[2];
		/// Jump Command begin
		JumpCommand act_actions_clicked_goToFlussostep_resvaitestataAction_0 = new JumpCommand(
				"cpDettaglioFlussoRiversamento", null, false);
		/// Jump Command end
		actionsForResults4goToTestata[0] = act_actions_clicked_goToFlussostep_resvaitestataAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_goToFlussostep_resno_selectionAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4goToTestata[1] = act_actions_clicked_goToFlussostep_resno_selectionAction_1;

		ExecCommand act_actions_clicked_goToFlussostep_0 = new ExecCommand(
				resultNames4goToTestata, actionsForResults4goToTestata) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.goToTestata(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoSingoloPagamentoAction",
							"readOne()", "chiamata verso BackEnd",
							"goToTestata");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [goToTestata]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_goToFlusso_1_steps = new ICommand[]{act_actions_clicked_goToFlussostep_0};
		SequenceCommand act_actions_clicked_goToFlusso_1 = new SequenceCommand(
				act_actions_clicked_goToFlusso_1_steps);
		// SequenceCommand end
		return act_actions_clicked_goToFlusso_1;
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
