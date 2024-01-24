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
 * CpFlussoRiversamentoAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpFlussoRiversamentoAction extends BaseAction
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

	public void setAppDataflussoRiversamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento value) {
		getSession().put("appDataflussoRiversamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento getAppDataflussoRiversamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento) (getSession()
				.get("appDataflussoRiversamento"));
	}

	public void setAppDataflussoSingoloPagamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento value) {
		getSession().put("appDataflussoSingoloPagamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento getAppDataflussoSingoloPagamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento) (getSession()
				.get("appDataflussoSingoloPagamento"));
	}

	public void setAppDatalistaFlussoRiversamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> value) {
		getSession().put("appDatalistaFlussoRiversamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> getAppDatalistaFlussoRiversamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento>) (getSession()
				.get("appDatalistaFlussoRiversamento"));
	}

	public void setAppDatalistaFlussoSingoloPagamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento> value) {
		getSession().put("appDatalistaFlussoSingoloPagamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento> getAppDatalistaFlussoSingoloPagamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento>) (getSession()
				.get("appDatalistaFlussoSingoloPagamento"));
	}

	public void setAppDataricercaFlussoRiversamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoRiversamento value) {
		getSession().put("appDataricercaFlussoRiversamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoRiversamento getAppDataricercaFlussoRiversamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoRiversamento) (getSession()
				.get("appDataricercaFlussoRiversamento"));
	}

	public void setAppDataricercaFlussoSingoloPagamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoSingoloPagamento value) {
		getSession().put("appDataricercaFlussoSingoloPagamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoSingoloPagamento getAppDataricercaFlussoSingoloPagamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.RicercaFlussoSingoloPagamento) (getSession()
				.get("appDataricercaFlussoSingoloPagamento"));
	}

	public void setAppDataselettoreFlussoRiversamento(java.lang.String value) {
		getSession().put("appDataselettoreFlussoRiversamento", value);
	}

	public java.lang.String getAppDataselettoreFlussoRiversamento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreFlussoRiversamento"));
	}

	public void setAppDataselettoreFlussoSingoloPagamento(java.lang.String value) {
		getSession().put("appDataselettoreFlussoSingoloPagamento", value);
	}

	public java.lang.String getAppDataselettoreFlussoSingoloPagamento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreFlussoSingoloPagamento"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btRicerca]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtRicerca_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btRicerca", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpFlussoRiversamentoAction::handleBtRicerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoRiversamentoAction::handleBtRicerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoRiversamentoAction::handleBtRicerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoRiversamentoAction::handleBtRicerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoRiversamentoAction::handleBtRicerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btVerifica]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtVerifica_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btVerifica", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpFlussoRiversamentoAction::handleBtVerifica_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoRiversamentoAction::handleBtVerifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoRiversamentoAction::handleBtVerifica_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoRiversamentoAction::handleBtVerifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoRiversamentoAction::handleBtVerifica_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btGoTodett]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtGoTodett_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btGoTodett", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpFlussoRiversamentoAction::handleBtGoTodett_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoRiversamentoAction::handleBtGoTodett_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoRiversamentoAction::handleBtGoTodett_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpFlussoRiversamentoAction::handleBtGoTodett_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpFlussoRiversamentoAction::handleBtGoTodett_CLICKED] returning default result [SUCCESS]");
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
				"appDatalistaFlussoRiversamento");

		if (isTableClearStatus("cpFlussoRiversamento_tbRicerca")) {
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
		/*PROTECTED REGION ID(R1152460763) ENABLED START*/
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
				log.error("[CpFlussoRiversamentoAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpFlussoRiversamentoAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"INIZIALE", "RESULT"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeActionstep_0_on = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "btVerifica"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{
				"tbRicerca", "btGoTodett"};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "btVerifica"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{
				"tbRicerca", "btGoTodett"};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpFlussoRiversamento", "INIZIALE",
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
		String[] act_onRefresh_resresultActionstep_0_on = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "tbRicerca", "btGoTodett", "btVerifica"};

		String[] act_onRefresh_resresultActionstep_0_off = new String[]{};

		String[] act_onRefresh_resresultActionstep_0_show = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "tbRicerca", "btGoTodett", "btVerifica"};

		String[] act_onRefresh_resresultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resresultActionstep_0 = new ScreenStateCommand(
				"cpFlussoRiversamento", "RESULT",
				act_onRefresh_resresultActionstep_0_on,
				act_onRefresh_resresultActionstep_0_off,
				act_onRefresh_resresultActionstep_0_show,
				act_onRefresh_resresultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resresultAction_1_steps = new ICommand[]{act_onRefresh_resresultActionstep_0};
		SequenceCommand act_onRefresh_resresultAction_1 = new SequenceCommand(
				act_onRefresh_resresultAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resresultAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoRiversamentoAction",
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

		new DummyUISecConstraint("cpFlussoRiversamento", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txIdentificativoUnivocoRegolamento
		UISecConstraint txIdentificativoUnivocoRegolamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento",
				"txIdentificativoUnivocoRegolamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdentificativoUnivocoRegolamento_constraints = new UISecConstraint[]{txIdentificativoUnivocoRegolamento_defaultVisib_ctr};
		UISecConstraint txIdentificativoUnivocoRegolamento_ctr = new ComplexUISecConstraint(
				txIdentificativoUnivocoRegolamento_constraints);
		allConstraints.put("txIdentificativoUnivocoRegolamento",
				txIdentificativoUnivocoRegolamento_ctr);

		// constraint fittizio per txDataRegolamentoDa
		UISecConstraint txDataRegolamentoDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "txDataRegolamentoDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoDa_constraints = new UISecConstraint[]{txDataRegolamentoDa_defaultVisib_ctr};
		UISecConstraint txDataRegolamentoDa_ctr = new ComplexUISecConstraint(
				txDataRegolamentoDa_constraints);
		allConstraints.put("txDataRegolamentoDa", txDataRegolamentoDa_ctr);

		// constraint fittizio per txDataRegolamentoA
		UISecConstraint txDataRegolamentoA_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "txDataRegolamentoA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoA_constraints = new UISecConstraint[]{txDataRegolamentoA_defaultVisib_ctr};
		UISecConstraint txDataRegolamentoA_ctr = new ComplexUISecConstraint(
				txDataRegolamentoA_constraints);
		allConstraints.put("txDataRegolamentoA", txDataRegolamentoA_ctr);

		// constraint fittizio per txdenominazionemittente
		UISecConstraint txdenominazionemittente_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento",
				"txdenominazionemittente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdenominazionemittente_constraints = new UISecConstraint[]{txdenominazionemittente_defaultVisib_ctr};
		UISecConstraint txdenominazionemittente_ctr = new ComplexUISecConstraint(
				txdenominazionemittente_constraints);
		allConstraints.put("txdenominazionemittente",
				txdenominazionemittente_ctr);

		// constraint fittizio per txidentificativoistitutoricevente
		UISecConstraint txidentificativoistitutoricevente_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento",
				"txidentificativoistitutoricevente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoistitutoricevente_constraints = new UISecConstraint[]{txidentificativoistitutoricevente_defaultVisib_ctr};
		UISecConstraint txidentificativoistitutoricevente_ctr = new ComplexUISecConstraint(
				txidentificativoistitutoricevente_constraints);
		allConstraints.put("txidentificativoistitutoricevente",
				txidentificativoistitutoricevente_ctr);

		// constraint fittizio per btRicerca
		UISecConstraint btRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "btRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btRicerca_constraints = new UISecConstraint[]{btRicerca_defaultVisib_ctr};
		UISecConstraint btRicerca_ctr = new ComplexUISecConstraint(
				btRicerca_constraints);
		allConstraints.put("btRicerca", btRicerca_ctr);

		// constraint fittizio per btVerifica
		UISecConstraint btVerifica_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "btVerifica",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btVerifica_constraints = new UISecConstraint[]{btVerifica_defaultVisib_ctr};
		UISecConstraint btVerifica_ctr = new ComplexUISecConstraint(
				btVerifica_constraints);
		allConstraints.put("btVerifica", btVerifica_ctr);

		// constraint fittizio per tbRicerca
		UISecConstraint tbRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "tbRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRicerca_constraints = new UISecConstraint[]{tbRicerca_defaultVisib_ctr};
		UISecConstraint tbRicerca_ctr = new ComplexUISecConstraint(
				tbRicerca_constraints);
		allConstraints.put("tbRicerca", tbRicerca_ctr);

		// constraint fittizio per btGoTodett
		UISecConstraint btGoTodett_defaultVisib_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "btGoTodett",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btGoTodett_constraints = new UISecConstraint[]{btGoTodett_defaultVisib_ctr};
		UISecConstraint btGoTodett_ctr = new ComplexUISecConstraint(
				btGoTodett_constraints);
		allConstraints.put("btGoTodett", btGoTodett_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txIdentificativoUnivocoRegolamento
		UISecConstraint txIdentificativoUnivocoRegolamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento",
				"txIdentificativoUnivocoRegolamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdentificativoUnivocoRegolamento_constraints = new UISecConstraint[]{txIdentificativoUnivocoRegolamento_defaultOnoff_ctr};
		UISecConstraint txIdentificativoUnivocoRegolamento_ctr = new ComplexUISecConstraint(
				txIdentificativoUnivocoRegolamento_constraints);
		allConstraints.put("txIdentificativoUnivocoRegolamento",
				txIdentificativoUnivocoRegolamento_ctr);

		// constraint fittizio per txDataRegolamentoDa
		UISecConstraint txDataRegolamentoDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "txDataRegolamentoDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoDa_constraints = new UISecConstraint[]{txDataRegolamentoDa_defaultOnoff_ctr};
		UISecConstraint txDataRegolamentoDa_ctr = new ComplexUISecConstraint(
				txDataRegolamentoDa_constraints);
		allConstraints.put("txDataRegolamentoDa", txDataRegolamentoDa_ctr);

		// constraint fittizio per txDataRegolamentoA
		UISecConstraint txDataRegolamentoA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "txDataRegolamentoA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataRegolamentoA_constraints = new UISecConstraint[]{txDataRegolamentoA_defaultOnoff_ctr};
		UISecConstraint txDataRegolamentoA_ctr = new ComplexUISecConstraint(
				txDataRegolamentoA_constraints);
		allConstraints.put("txDataRegolamentoA", txDataRegolamentoA_ctr);

		// constraint fittizio per txdenominazionemittente
		UISecConstraint txdenominazionemittente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento",
				"txdenominazionemittente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdenominazionemittente_constraints = new UISecConstraint[]{txdenominazionemittente_defaultOnoff_ctr};
		UISecConstraint txdenominazionemittente_ctr = new ComplexUISecConstraint(
				txdenominazionemittente_constraints);
		allConstraints.put("txdenominazionemittente",
				txdenominazionemittente_ctr);

		// constraint fittizio per txidentificativoistitutoricevente
		UISecConstraint txidentificativoistitutoricevente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento",
				"txidentificativoistitutoricevente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoistitutoricevente_constraints = new UISecConstraint[]{txidentificativoistitutoricevente_defaultOnoff_ctr};
		UISecConstraint txidentificativoistitutoricevente_ctr = new ComplexUISecConstraint(
				txidentificativoistitutoricevente_constraints);
		allConstraints.put("txidentificativoistitutoricevente",
				txidentificativoistitutoricevente_ctr);

		// constraint fittizio per btRicerca
		UISecConstraint btRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "btRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btRicerca_constraints = new UISecConstraint[]{btRicerca_defaultOnoff_ctr};
		UISecConstraint btRicerca_ctr = new ComplexUISecConstraint(
				btRicerca_constraints);
		allConstraints.put("btRicerca", btRicerca_ctr);

		// constraint fittizio per btVerifica
		UISecConstraint btVerifica_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "btVerifica",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btVerifica_constraints = new UISecConstraint[]{btVerifica_defaultOnoff_ctr};
		UISecConstraint btVerifica_ctr = new ComplexUISecConstraint(
				btVerifica_constraints);
		allConstraints.put("btVerifica", btVerifica_ctr);

		// constraint fittizio per tbRicerca
		UISecConstraint tbRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "tbRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRicerca_constraints = new UISecConstraint[]{tbRicerca_defaultOnoff_ctr};
		UISecConstraint tbRicerca_ctr = new ComplexUISecConstraint(
				tbRicerca_constraints);
		allConstraints.put("tbRicerca", tbRicerca_ctr);

		// constraint fittizio per btGoTodett
		UISecConstraint btGoTodett_defaultOnoff_ctr =

		new DummyUISecConstraint("cpFlussoRiversamento", "btGoTodett",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btGoTodett_constraints = new UISecConstraint[]{btGoTodett_defaultOnoff_ctr};
		UISecConstraint btGoTodett_ctr = new ComplexUISecConstraint(
				btGoTodett_constraints);
		allConstraints.put("btGoTodett", btGoTodett_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpFlussoRiversamentoAction::dumpmodel] START");

		log.debug("[CpFlussoRiversamentoAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpFlussoRiversamentoAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpFlussoRiversamentoAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpFlussoRiversamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpFlussoRiversamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpFlussoRiversamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpFlussoRiversamentoAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpFlussoRiversamento");
		log.debug("[CpFlussoRiversamentoAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpFlussoRiversamentoAction::dumpmodel] [c] sessione");
		log.debug("[CpFlussoRiversamentoAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btRicerca per ogni Ev.H.
		HashMap<String, ICommand> cmds4btRicercaByEvh = new HashMap<String, ICommand>();

		cmds4btRicercaByEvh.put("CLICKED", initCommandBtRicerca_CLICKED());
		cmdsByWidget.put("btRicerca", cmds4btRicercaByEvh);
		// contiene i comandi del widget btVerifica per ogni Ev.H.
		HashMap<String, ICommand> cmds4btVerificaByEvh = new HashMap<String, ICommand>();

		cmds4btVerificaByEvh.put("CLICKED", initCommandBtVerifica_CLICKED());
		cmdsByWidget.put("btVerifica", cmds4btVerificaByEvh);
		// contiene i comandi del widget btGoTodett per ogni Ev.H.
		HashMap<String, ICommand> cmds4btGoTodettByEvh = new HashMap<String, ICommand>();

		cmds4btGoTodettByEvh.put("CLICKED", initCommandBtGoTodett_CLICKED());
		cmdsByWidget.put("btGoTodett", cmds4btGoTodettByEvh);

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

	private ICommand initCommandBtRicerca_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4ricerca = new String[]{"no_result", "result"};

		ICommand[] actionsForResults4ricerca = new ICommand[2];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btRicercastep_resno_resultAction_0_on = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "tbRicerca", "btGoTodett", "btVerifica"};

		String[] act_actions_clicked_btRicercastep_resno_resultAction_0_off = new String[]{};

		String[] act_actions_clicked_btRicercastep_resno_resultAction_0_show = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "tbRicerca", "btGoTodett", "btVerifica"};

		String[] act_actions_clicked_btRicercastep_resno_resultAction_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btRicercastep_resno_resultAction_0 = new ScreenStateCommand(
				"cpFlussoRiversamento", "RESULT",
				act_actions_clicked_btRicercastep_resno_resultAction_0_on,
				act_actions_clicked_btRicercastep_resno_resultAction_0_off,
				act_actions_clicked_btRicercastep_resno_resultAction_0_show,
				act_actions_clicked_btRicercastep_resno_resultAction_0_hide);
		//Screen State Command end
		actionsForResults4ricerca[0] = act_actions_clicked_btRicercastep_resno_resultAction_0;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btRicercastep_resresultAction_1_on = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "tbRicerca", "btGoTodett", "btVerifica"};

		String[] act_actions_clicked_btRicercastep_resresultAction_1_off = new String[]{};

		String[] act_actions_clicked_btRicercastep_resresultAction_1_show = new String[]{
				"mnuView", "txIdentificativoUnivocoRegolamento",
				"txDataRegolamentoDa", "txDataRegolamentoA",
				"txdenominazionemittente", "txidentificativoistitutoricevente",
				"btRicerca", "tbRicerca", "btGoTodett", "btVerifica"};

		String[] act_actions_clicked_btRicercastep_resresultAction_1_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btRicercastep_resresultAction_1 = new ScreenStateCommand(
				"cpFlussoRiversamento", "RESULT",
				act_actions_clicked_btRicercastep_resresultAction_1_on,
				act_actions_clicked_btRicercastep_resresultAction_1_off,
				act_actions_clicked_btRicercastep_resresultAction_1_show,
				act_actions_clicked_btRicercastep_resresultAction_1_hide);
		//Screen State Command end
		actionsForResults4ricerca[1] = act_actions_clicked_btRicercastep_resresultAction_1;

		ExecCommand act_actions_clicked_btRicercastep_0 = new ExecCommand(
				resultNames4ricerca, actionsForResults4ricerca) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.ricerca(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoRiversamentoAction",
							"readOne()", "chiamata verso BackEnd", "ricerca");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [ricerca]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btRicerca_1_steps = new ICommand[]{act_actions_clicked_btRicercastep_0};
		SequenceCommand act_actions_clicked_btRicerca_1 = new SequenceCommand(
				act_actions_clicked_btRicerca_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btRicerca_1;
	}

	private ICommand initCommandBtVerifica_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4verificaFlussi = new String[]{"ok"};

		ICommand[] actionsForResults4verificaFlussi = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btVerificastep_resokAction_0 = new JumpCommand(
				"cpVerificaFlussi", null, false);
		/// Jump Command end
		actionsForResults4verificaFlussi[0] = act_actions_clicked_btVerificastep_resokAction_0;

		ExecCommand act_actions_clicked_btVerificastep_0 = new ExecCommand(
				resultNames4verificaFlussi, actionsForResults4verificaFlussi) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.verificaFlussi(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoRiversamentoAction",
							"readOne()", "chiamata verso BackEnd",
							"verificaFlussi");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [verificaFlussi]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btVerifica_1_steps = new ICommand[]{act_actions_clicked_btVerificastep_0};
		SequenceCommand act_actions_clicked_btVerifica_1 = new SequenceCommand(
				act_actions_clicked_btVerifica_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btVerifica_1;
	}

	private ICommand initCommandBtGoTodett_CLICKED() {
		// ExecCommand begin
		String[] resultNames4vaiAlDettaglio = new String[]{"OK", "KO"};

		ICommand[] actionsForResults4vaiAlDettaglio = new ICommand[2];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btGoTodett_resokAction_0 = new JumpCommand(
				"cpDettaglioFlussoRiversamento", null, false);
		/// Jump Command end
		actionsForResults4vaiAlDettaglio[0] = act_actions_clicked_btGoTodett_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btGoTodett_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4vaiAlDettaglio[1] = act_actions_clicked_btGoTodett_reskoAction_1;

		ExecCommand act_actions_clicked_btGoTodett_1 = new ExecCommand(
				resultNames4vaiAlDettaglio, actionsForResults4vaiAlDettaglio) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.vaiAlDettaglio(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpFlussoRiversamentoAction",
							"readOne()", "chiamata verso BackEnd",
							"vaiAlDettaglio");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [vaiAlDettaglio]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btGoTodett_1;
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
