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
 * CpGestioneGruppiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneGruppiAction extends BaseAction implements Preparable {

	public void setAppDatagruppo(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo value) {
		getSession().put("appDatagruppo", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo getAppDatagruppo() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo) (getSession()
				.get("appDatagruppo"));
	}

	public void setAppDatagruppi(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo> value) {
		getSession().put("appDatagruppi", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo> getAppDatagruppi() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo>) (getSession()
				.get("appDatagruppi"));
	}

	public void setAppDatagruppiApplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione> value) {
		getSession().put("appDatagruppiApplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione> getAppDatagruppiApplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione>) (getSession()
				.get("appDatagruppiApplicazioni"));
	}

	public void setAppDatagruppiruoli(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo> value) {
		getSession().put("appDatagruppiruoli", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo> getAppDatagruppiruoli() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo>) (getSession()
				.get("appDatagruppiruoli"));
	}

	public void setAppDatagruppoApplicazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione value) {
		getSession().put("appDatagruppoApplicazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione getAppDatagruppoApplicazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoApplicazione) (getSession()
				.get("appDatagruppoApplicazione"));
	}

	public void setAppDatagrupporuolo(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo value) {
		getSession().put("appDatagrupporuolo", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo getAppDatagrupporuolo() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.GruppoRuolo) (getSession()
				.get("appDatagrupporuolo"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
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

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataselettoreGruppo(java.lang.String value) {
		getSession().put("appDataselettoreGruppo", value);
	}

	public java.lang.String getAppDataselettoreGruppo() {
		return (java.lang.String) (getSession().get("appDataselettoreGruppo"));
	}

	public void setAppDataselettoreIdRuolo(java.lang.String value) {
		getSession().put("appDataselettoreIdRuolo", value);
	}

	public java.lang.String getAppDataselettoreIdRuolo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRuolo"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btNuovoGruppo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtNuovoGruppo_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btNuovoGruppo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGruppiAction::handleBtNuovoGruppo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGruppiAction::handleBtNuovoGruppo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGruppiAction::handleBtNuovoGruppo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGruppiAction::handleBtNuovoGruppo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGruppiAction::handleBtNuovoGruppo_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btModificaGruppo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtModificaGruppo_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btModificaGruppo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGruppiAction::handleBtModificaGruppo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGruppiAction::handleBtModificaGruppo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGruppiAction::handleBtModificaGruppo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGruppiAction::handleBtModificaGruppo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGruppiAction::handleBtModificaGruppo_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btCancellaGruppo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtCancellaGruppo_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btCancellaGruppo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGruppiAction::handleBtCancellaGruppo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGruppiAction::handleBtCancellaGruppo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGruppiAction::handleBtCancellaGruppo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGruppiAction::handleBtCancellaGruppo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGruppiAction::handleBtCancellaGruppo_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tListaGruppi.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTListaGruppi_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatagruppi");

		if (isTableClearStatus("cpGestioneGruppi_tListaGruppi")) {
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
		/*PROTECTED REGION ID(R1358717943) ENABLED START*/
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
				log.error("[CpGestioneGruppiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneGruppiAction::isWidgetVisible] errore durante verifica->hide");
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
				"Some_Result"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resno_resultActionstep_0_on = new String[]{
				"mnuView", "tListaGruppi", "btNuovoGruppo", "btModificaGruppo",
				"btCancellaGruppo"};

		String[] act_onRefresh_resno_resultActionstep_0_off = new String[]{};

		String[] act_onRefresh_resno_resultActionstep_0_show = new String[]{
				"mnuView", "tListaGruppi", "btNuovoGruppo", "btModificaGruppo",
				"btCancellaGruppo"};

		String[] act_onRefresh_resno_resultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resno_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneGruppi", "SOME_RESULT",
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
		String[] act_onRefresh_ressome_resultActionstep_0_on = new String[]{
				"mnuView", "tListaGruppi", "btNuovoGruppo", "btModificaGruppo",
				"btCancellaGruppo"};

		String[] act_onRefresh_ressome_resultActionstep_0_off = new String[]{};

		String[] act_onRefresh_ressome_resultActionstep_0_show = new String[]{
				"mnuView", "tListaGruppi", "btNuovoGruppo", "btModificaGruppo",
				"btCancellaGruppo"};

		String[] act_onRefresh_ressome_resultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_ressome_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneGruppi", "SOME_RESULT",
				act_onRefresh_ressome_resultActionstep_0_on,
				act_onRefresh_ressome_resultActionstep_0_off,
				act_onRefresh_ressome_resultActionstep_0_show,
				act_onRefresh_ressome_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_ressome_resultAction_1_steps = new ICommand[]{act_onRefresh_ressome_resultActionstep_0};
		SequenceCommand act_onRefresh_ressome_resultAction_1 = new SequenceCommand(
				act_onRefresh_ressome_resultAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_ressome_resultAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGruppiAction", "readOne()",
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

		new DummyUISecConstraint("cpGestioneGruppi", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tListaGruppi
		UISecConstraint tListaGruppi_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "tListaGruppi",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tListaGruppi_constraints = new UISecConstraint[]{tListaGruppi_defaultVisib_ctr};
		UISecConstraint tListaGruppi_ctr = new ComplexUISecConstraint(
				tListaGruppi_constraints);
		allConstraints.put("tListaGruppi", tListaGruppi_ctr);

		// constraint fittizio per btNuovoGruppo
		UISecConstraint btNuovoGruppo_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "btNuovoGruppo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btNuovoGruppo_constraints = new UISecConstraint[]{btNuovoGruppo_defaultVisib_ctr};
		UISecConstraint btNuovoGruppo_ctr = new ComplexUISecConstraint(
				btNuovoGruppo_constraints);
		allConstraints.put("btNuovoGruppo", btNuovoGruppo_ctr);

		// constraint fittizio per btModificaGruppo
		UISecConstraint btModificaGruppo_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "btModificaGruppo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btModificaGruppo_constraints = new UISecConstraint[]{btModificaGruppo_defaultVisib_ctr};
		UISecConstraint btModificaGruppo_ctr = new ComplexUISecConstraint(
				btModificaGruppo_constraints);
		allConstraints.put("btModificaGruppo", btModificaGruppo_ctr);

		// constraint fittizio per btCancellaGruppo
		UISecConstraint btCancellaGruppo_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "btCancellaGruppo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btCancellaGruppo_constraints = new UISecConstraint[]{btCancellaGruppo_defaultVisib_ctr};
		UISecConstraint btCancellaGruppo_ctr = new ComplexUISecConstraint(
				btCancellaGruppo_constraints);
		allConstraints.put("btCancellaGruppo", btCancellaGruppo_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tListaGruppi
		UISecConstraint tListaGruppi_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "tListaGruppi",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tListaGruppi_constraints = new UISecConstraint[]{tListaGruppi_defaultOnoff_ctr};
		UISecConstraint tListaGruppi_ctr = new ComplexUISecConstraint(
				tListaGruppi_constraints);
		allConstraints.put("tListaGruppi", tListaGruppi_ctr);

		// constraint fittizio per btNuovoGruppo
		UISecConstraint btNuovoGruppo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "btNuovoGruppo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btNuovoGruppo_constraints = new UISecConstraint[]{btNuovoGruppo_defaultOnoff_ctr};
		UISecConstraint btNuovoGruppo_ctr = new ComplexUISecConstraint(
				btNuovoGruppo_constraints);
		allConstraints.put("btNuovoGruppo", btNuovoGruppo_ctr);

		// constraint fittizio per btModificaGruppo
		UISecConstraint btModificaGruppo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "btModificaGruppo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btModificaGruppo_constraints = new UISecConstraint[]{btModificaGruppo_defaultOnoff_ctr};
		UISecConstraint btModificaGruppo_ctr = new ComplexUISecConstraint(
				btModificaGruppo_constraints);
		allConstraints.put("btModificaGruppo", btModificaGruppo_ctr);

		// constraint fittizio per btCancellaGruppo
		UISecConstraint btCancellaGruppo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGruppi", "btCancellaGruppo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btCancellaGruppo_constraints = new UISecConstraint[]{btCancellaGruppo_defaultOnoff_ctr};
		UISecConstraint btCancellaGruppo_ctr = new ComplexUISecConstraint(
				btCancellaGruppo_constraints);
		allConstraints.put("btCancellaGruppo", btCancellaGruppo_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneGruppiAction::dumpmodel] START");

		log.debug("[CpGestioneGruppiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneGruppiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneGruppiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneGruppiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneGruppiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneGruppiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpGestioneGruppiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneGruppi");
		log.debug("[CpGestioneGruppiAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneGruppiAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneGruppiAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btNuovoGruppo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btNuovoGruppoByEvh = new HashMap<String, ICommand>();

		cmds4btNuovoGruppoByEvh.put("CLICKED",
				initCommandBtNuovoGruppo_CLICKED());
		cmdsByWidget.put("btNuovoGruppo", cmds4btNuovoGruppoByEvh);
		// contiene i comandi del widget btModificaGruppo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btModificaGruppoByEvh = new HashMap<String, ICommand>();

		cmds4btModificaGruppoByEvh.put("CLICKED",
				initCommandBtModificaGruppo_CLICKED());
		cmdsByWidget.put("btModificaGruppo", cmds4btModificaGruppoByEvh);
		// contiene i comandi del widget btCancellaGruppo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btCancellaGruppoByEvh = new HashMap<String, ICommand>();

		cmds4btCancellaGruppoByEvh.put("CLICKED",
				initCommandBtCancellaGruppo_CLICKED());
		cmdsByWidget.put("btCancellaGruppo", cmds4btCancellaGruppoByEvh);

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

	private ICommand initCommandBtNuovoGruppo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4gotoNuovoGruppo = new String[]{"Ok"};

		ICommand[] actionsForResults4gotoNuovoGruppo = new ICommand[1];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btNuovoGruppo_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{
				"gruppi", "gruppiApplicazioni", "gruppiruoli", "gruppo",
				"gruppoApplicazione", "grupporuolo", "isPostBack",
				"utentiDelGruppo"};

		ClearAppDataCommand act_actions_clicked_btNuovoGruppo_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btNuovoGruppo_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btNuovoGruppo_resokActionstep_1 = new JumpCommand(
				"cpDettaglioGruppo", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btNuovoGruppo_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btNuovoGruppo_resokActionstep_0,
				act_actions_clicked_btNuovoGruppo_resokActionstep_1};
		SequenceCommand act_actions_clicked_btNuovoGruppo_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btNuovoGruppo_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4gotoNuovoGruppo[0] = act_actions_clicked_btNuovoGruppo_resokAction_0;

		ExecCommand act_actions_clicked_btNuovoGruppo_1 = new ExecCommand(
				resultNames4gotoNuovoGruppo, actionsForResults4gotoNuovoGruppo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.gotoNuovoGruppo(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGruppiAction", "readOne()",
							"chiamata verso BackEnd", "gotoNuovoGruppo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [gotoNuovoGruppo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btNuovoGruppo_1;
	}

	private ICommand initCommandBtModificaGruppo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4gotoModificaGruppo = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4gotoModificaGruppo = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btModificaGruppo_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btModificaGruppo_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btModificaGruppo_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btModificaGruppo_resokActionstep_1 = new JumpCommand(
				"cpDettaglioGruppo", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btModificaGruppo_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btModificaGruppo_resokActionstep_0,
				act_actions_clicked_btModificaGruppo_resokActionstep_1};
		SequenceCommand act_actions_clicked_btModificaGruppo_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btModificaGruppo_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4gotoModificaGruppo[0] = act_actions_clicked_btModificaGruppo_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btModificaGruppo_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4gotoModificaGruppo[1] = act_actions_clicked_btModificaGruppo_reskoAction_1;

		ExecCommand act_actions_clicked_btModificaGruppo_1 = new ExecCommand(
				resultNames4gotoModificaGruppo,
				actionsForResults4gotoModificaGruppo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.gotoModificaGruppo(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGruppiAction", "readOne()",
							"chiamata verso BackEnd", "gotoModificaGruppo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [gotoModificaGruppo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btModificaGruppo_1;
	}

	private ICommand initCommandBtCancellaGruppo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cancellaGruppo = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4cancellaGruppo = new ICommand[2];
		/// Clear AppData Command begin
		String[] act_actions_clicked_btCancellaGruppo_resokAction_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btCancellaGruppo_resokAction_0 = new ClearAppDataCommand(
				act_actions_clicked_btCancellaGruppo_resokAction_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		actionsForResults4cancellaGruppo[0] = act_actions_clicked_btCancellaGruppo_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btCancellaGruppo_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4cancellaGruppo[1] = act_actions_clicked_btCancellaGruppo_reskoAction_1;

		ExecCommand act_actions_clicked_btCancellaGruppo_1 = new ExecCommand(
				resultNames4cancellaGruppo, actionsForResults4cancellaGruppo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cancellaGruppo(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGruppiAction", "readOne()",
							"chiamata verso BackEnd", "cancellaGruppo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cancellaGruppo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btCancellaGruppo_1;
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
