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
 * CpInformativePspAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpInformativePspAction extends BaseAction implements Preparable {

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDatainformativePsp(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp value) {
		getSession().put("appDatainformativePsp", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp getAppDatainformativePsp() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp) (getSession()
				.get("appDatainformativePsp"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDataselettoreInformativePsp(java.lang.String value) {
		getSession().put("appDataselettoreInformativePsp", value);
	}

	public java.lang.String getAppDataselettoreInformativePsp() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreInformativePsp"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDatalistaInformativePsp(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp> value) {
		getSession().put("appDatalistaInformativePsp", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp> getAppDatalistaInformativePsp() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp>) (getSession()
				.get("appDatalistaInformativePsp"));
	}

	public void setAppDatafiltroRicercaInformativePsp(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp value) {
		getSession().put("appDatafiltroRicercaInformativePsp", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp getAppDatafiltroRicercaInformativePsp() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp) (getSession()
				.get("appDatafiltroRicercaInformativePsp"));
	}

	public void setAppDatalistaTipoversamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento> value) {
		getSession().put("appDatalistaTipoversamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento> getAppDatalistaTipoversamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento>) (getSession()
				.get("appDatalistaTipoversamento"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel.class;
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
			log.debug("[CpInformativePspAction::handleBtRicerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpInformativePspAction::handleBtRicerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpInformativePspAction::handleBtRicerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpInformativePspAction::handleBtRicerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpInformativePspAction::handleBtRicerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btVai]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtVai_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btVai", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpInformativePspAction::handleBtVai_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpInformativePspAction::handleBtVai_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpInformativePspAction::handleBtVai_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpInformativePspAction::handleBtVai_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpInformativePspAction::handleBtVai_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbtipoversamento.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbtipoversamento_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaTipoversamento");

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
	 * al data-binding relativo al dataset DATASET del widget tbRisultati.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbRisultati_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaInformativePsp");

		if (isTableClearStatus("cpInformativePsp_tbRisultati")) {
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
		/*PROTECTED REGION ID(R-292948991) ENABLED START*/
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
				log.error("[CpInformativePspAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpInformativePspAction::isWidgetVisible] errore durante verifica->hide");
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
				"mnuView", "btRicerca", "txdatapubblicazione",
				"txdatainserimento", "txragionesociale", "cbtipoversamento"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{
				"tbRisultati", "btVai"};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "btRicerca", "txdatapubblicazione",
				"txdatainserimento", "txragionesociale", "cbtipoversamento"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{
				"tbRisultati", "btVai"};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpInformativePsp", "INIZIALE",
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
				"txdatapubblicazione", "txdatainserimento", "txragionesociale",
				"cbtipoversamento", "btRicerca", "tbRisultati", "btVai",
				"mnuView"};

		String[] act_onRefresh_resricercaActionstep_0_off = new String[]{};

		String[] act_onRefresh_resricercaActionstep_0_show = new String[]{
				"txdatapubblicazione", "txdatainserimento", "txragionesociale",
				"cbtipoversamento", "btRicerca", "tbRisultati", "btVai",
				"mnuView"};

		String[] act_onRefresh_resricercaActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resricercaActionstep_0 = new ScreenStateCommand(
				"cpInformativePsp", "RICERCA",
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpInformativePspAction", "readOne()",
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

		new DummyUISecConstraint("cpInformativePsp", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txdatapubblicazione
		UISecConstraint txdatapubblicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "txdatapubblicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatapubblicazione_constraints = new UISecConstraint[]{txdatapubblicazione_defaultVisib_ctr};
		UISecConstraint txdatapubblicazione_ctr = new ComplexUISecConstraint(
				txdatapubblicazione_constraints);
		allConstraints.put("txdatapubblicazione", txdatapubblicazione_ctr);

		// constraint fittizio per txdatainserimento
		UISecConstraint txdatainserimento_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "txdatainserimento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatainserimento_constraints = new UISecConstraint[]{txdatainserimento_defaultVisib_ctr};
		UISecConstraint txdatainserimento_ctr = new ComplexUISecConstraint(
				txdatainserimento_constraints);
		allConstraints.put("txdatainserimento", txdatainserimento_ctr);

		// constraint fittizio per txragionesociale
		UISecConstraint txragionesociale_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "txragionesociale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txragionesociale_constraints = new UISecConstraint[]{txragionesociale_defaultVisib_ctr};
		UISecConstraint txragionesociale_ctr = new ComplexUISecConstraint(
				txragionesociale_constraints);
		allConstraints.put("txragionesociale", txragionesociale_ctr);

		// constraint fittizio per cbtipoversamento
		UISecConstraint cbtipoversamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "cbtipoversamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbtipoversamento_constraints = new UISecConstraint[]{cbtipoversamento_defaultVisib_ctr};
		UISecConstraint cbtipoversamento_ctr = new ComplexUISecConstraint(
				cbtipoversamento_constraints);
		allConstraints.put("cbtipoversamento", cbtipoversamento_ctr);

		// constraint fittizio per btRicerca
		UISecConstraint btRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "btRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btRicerca_constraints = new UISecConstraint[]{btRicerca_defaultVisib_ctr};
		UISecConstraint btRicerca_ctr = new ComplexUISecConstraint(
				btRicerca_constraints);
		allConstraints.put("btRicerca", btRicerca_ctr);

		// constraint fittizio per tbRisultati
		UISecConstraint tbRisultati_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "tbRisultati",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRisultati_constraints = new UISecConstraint[]{tbRisultati_defaultVisib_ctr};
		UISecConstraint tbRisultati_ctr = new ComplexUISecConstraint(
				tbRisultati_constraints);
		allConstraints.put("tbRisultati", tbRisultati_ctr);

		// constraint fittizio per btVai
		UISecConstraint btVai_defaultVisib_ctr =

		new DummyUISecConstraint("cpInformativePsp", "btVai",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btVai_constraints = new UISecConstraint[]{btVai_defaultVisib_ctr};
		UISecConstraint btVai_ctr = new ComplexUISecConstraint(
				btVai_constraints);
		allConstraints.put("btVai", btVai_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txdatapubblicazione
		UISecConstraint txdatapubblicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "txdatapubblicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatapubblicazione_constraints = new UISecConstraint[]{txdatapubblicazione_defaultOnoff_ctr};
		UISecConstraint txdatapubblicazione_ctr = new ComplexUISecConstraint(
				txdatapubblicazione_constraints);
		allConstraints.put("txdatapubblicazione", txdatapubblicazione_ctr);

		// constraint fittizio per txdatainserimento
		UISecConstraint txdatainserimento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "txdatainserimento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatainserimento_constraints = new UISecConstraint[]{txdatainserimento_defaultOnoff_ctr};
		UISecConstraint txdatainserimento_ctr = new ComplexUISecConstraint(
				txdatainserimento_constraints);
		allConstraints.put("txdatainserimento", txdatainserimento_ctr);

		// constraint fittizio per txragionesociale
		UISecConstraint txragionesociale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "txragionesociale",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txragionesociale_constraints = new UISecConstraint[]{txragionesociale_defaultOnoff_ctr};
		UISecConstraint txragionesociale_ctr = new ComplexUISecConstraint(
				txragionesociale_constraints);
		allConstraints.put("txragionesociale", txragionesociale_ctr);

		// constraint fittizio per cbtipoversamento
		UISecConstraint cbtipoversamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "cbtipoversamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbtipoversamento_constraints = new UISecConstraint[]{cbtipoversamento_defaultOnoff_ctr};
		UISecConstraint cbtipoversamento_ctr = new ComplexUISecConstraint(
				cbtipoversamento_constraints);
		allConstraints.put("cbtipoversamento", cbtipoversamento_ctr);

		// constraint fittizio per btRicerca
		UISecConstraint btRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "btRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btRicerca_constraints = new UISecConstraint[]{btRicerca_defaultOnoff_ctr};
		UISecConstraint btRicerca_ctr = new ComplexUISecConstraint(
				btRicerca_constraints);
		allConstraints.put("btRicerca", btRicerca_ctr);

		// constraint fittizio per tbRisultati
		UISecConstraint tbRisultati_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "tbRisultati",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRisultati_constraints = new UISecConstraint[]{tbRisultati_defaultOnoff_ctr};
		UISecConstraint tbRisultati_ctr = new ComplexUISecConstraint(
				tbRisultati_constraints);
		allConstraints.put("tbRisultati", tbRisultati_ctr);

		// constraint fittizio per btVai
		UISecConstraint btVai_defaultOnoff_ctr =

		new DummyUISecConstraint("cpInformativePsp", "btVai",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btVai_constraints = new UISecConstraint[]{btVai_defaultOnoff_ctr};
		UISecConstraint btVai_ctr = new ComplexUISecConstraint(
				btVai_constraints);
		allConstraints.put("btVai", btVai_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpInformativePspAction::dumpmodel] START");

		log.debug("[CpInformativePspAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpInformativePspAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpInformativePspAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpInformativePspAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpInformativePspAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpInformativePspAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpInformativePspAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpInformativePsp");
		log.debug("[CpInformativePspAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpInformativePspAction::dumpmodel] [c] sessione");
		log.debug("[CpInformativePspAction::dumpmodel] " + session);
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
		// contiene i comandi del widget btVai per ogni Ev.H.
		HashMap<String, ICommand> cmds4btVaiByEvh = new HashMap<String, ICommand>();

		cmds4btVaiByEvh.put("CLICKED", initCommandBtVai_CLICKED());
		cmdsByWidget.put("btVai", cmds4btVaiByEvh);

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
		// ExecCommand begin
		String[] resultNames4ricercaInformativa = new String[]{
				"RICERCA_NORESULT", "RICERCA_RESULT"};

		ICommand[] actionsForResults4ricercaInformativa = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_on = new String[]{
				"mnuView", "btRicerca", "txdatapubblicazione",
				"txdatainserimento", "txragionesociale", "cbtipoversamento"};

		String[] act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_off = new String[]{
				"tbRisultati", "btVai"};

		String[] act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_show = new String[]{
				"mnuView", "btRicerca", "txdatapubblicazione",
				"txdatainserimento", "txragionesociale", "cbtipoversamento"};

		String[] act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_hide = new String[]{
				"tbRisultati", "btVai"};

		ScreenStateCommand act_actions_clicked_btRicerca_resricerca_noresultActionstep_0 = new ScreenStateCommand(
				"cpInformativePsp",
				"INIZIALE",
				act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_on,
				act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_off,
				act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_show,
				act_actions_clicked_btRicerca_resricerca_noresultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btRicerca_resricerca_noresultAction_0_steps = new ICommand[]{act_actions_clicked_btRicerca_resricerca_noresultActionstep_0};
		SequenceCommand act_actions_clicked_btRicerca_resricerca_noresultAction_0 = new SequenceCommand(
				act_actions_clicked_btRicerca_resricerca_noresultAction_0_steps);
		// SequenceCommand end
		actionsForResults4ricercaInformativa[0] = act_actions_clicked_btRicerca_resricerca_noresultAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btRicerca_resricerca_resultActionstep_0_on = new String[]{
				"txdatapubblicazione", "txdatainserimento", "txragionesociale",
				"cbtipoversamento", "btRicerca", "tbRisultati", "btVai",
				"mnuView"};

		String[] act_actions_clicked_btRicerca_resricerca_resultActionstep_0_off = new String[]{};

		String[] act_actions_clicked_btRicerca_resricerca_resultActionstep_0_show = new String[]{
				"txdatapubblicazione", "txdatainserimento", "txragionesociale",
				"cbtipoversamento", "btRicerca", "tbRisultati", "btVai",
				"mnuView"};

		String[] act_actions_clicked_btRicerca_resricerca_resultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btRicerca_resricerca_resultActionstep_0 = new ScreenStateCommand(
				"cpInformativePsp",
				"RICERCA",
				act_actions_clicked_btRicerca_resricerca_resultActionstep_0_on,
				act_actions_clicked_btRicerca_resricerca_resultActionstep_0_off,
				act_actions_clicked_btRicerca_resricerca_resultActionstep_0_show,
				act_actions_clicked_btRicerca_resricerca_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btRicerca_resricerca_resultAction_1_steps = new ICommand[]{act_actions_clicked_btRicerca_resricerca_resultActionstep_0};
		SequenceCommand act_actions_clicked_btRicerca_resricerca_resultAction_1 = new SequenceCommand(
				act_actions_clicked_btRicerca_resricerca_resultAction_1_steps);
		// SequenceCommand end
		actionsForResults4ricercaInformativa[1] = act_actions_clicked_btRicerca_resricerca_resultAction_1;

		ExecCommand act_actions_clicked_btRicerca_1 = new ExecCommand(
				resultNames4ricercaInformativa,
				actionsForResults4ricercaInformativa) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.ricercaInformativa(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpInformativePspAction", "readOne()",
							"chiamata verso BackEnd", "ricercaInformativa");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [ricercaInformativa]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btRicerca_1;
	}

	private ICommand initCommandBtVai_CLICKED() {
		// ExecCommand begin
		String[] resultNames4vaiAlDettaglio = new String[]{"DETTAGLIO_OK",
				"DETTAGLIO_KO"};

		ICommand[] actionsForResults4vaiAlDettaglio = new ICommand[2];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btVai_resdettaglio_okAction_0 = new JumpCommand(
				"cpDettaglioInformativaPsp", null, false);
		/// Jump Command end
		actionsForResults4vaiAlDettaglio[0] = act_actions_clicked_btVai_resdettaglio_okAction_0;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btVai_resdettaglio_koAction_1_on = new String[]{
				"txdatapubblicazione", "txdatainserimento", "txragionesociale",
				"cbtipoversamento", "btRicerca", "tbRisultati", "btVai",
				"mnuView"};

		String[] act_actions_clicked_btVai_resdettaglio_koAction_1_off = new String[]{};

		String[] act_actions_clicked_btVai_resdettaglio_koAction_1_show = new String[]{
				"txdatapubblicazione", "txdatainserimento", "txragionesociale",
				"cbtipoversamento", "btRicerca", "tbRisultati", "btVai",
				"mnuView"};

		String[] act_actions_clicked_btVai_resdettaglio_koAction_1_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btVai_resdettaglio_koAction_1 = new ScreenStateCommand(
				"cpInformativePsp", "RICERCA",
				act_actions_clicked_btVai_resdettaglio_koAction_1_on,
				act_actions_clicked_btVai_resdettaglio_koAction_1_off,
				act_actions_clicked_btVai_resdettaglio_koAction_1_show,
				act_actions_clicked_btVai_resdettaglio_koAction_1_hide);
		//Screen State Command end
		actionsForResults4vaiAlDettaglio[1] = act_actions_clicked_btVai_resdettaglio_koAction_1;

		ExecCommand act_actions_clicked_btVai_1 = new ExecCommand(
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpInformativePspAction", "readOne()",
							"chiamata verso BackEnd", "vaiAlDettaglio");
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
		return act_actions_clicked_btVai_1;
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
