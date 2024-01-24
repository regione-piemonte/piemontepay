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
 * CpGestioneStatiRptAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneStatiRptAction extends BaseAction implements Preparable {

	// Table tbListaSingoloStatoRpt

	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> _widg_tbListaSingoloStatoRpt;

	public void setWidg_tbListaSingoloStatoRpt(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> value) {
		_widg_tbListaSingoloStatoRpt = value;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> getWidg_tbListaSingoloStatoRpt() {
		return _widg_tbListaSingoloStatoRpt;
	}

	// Table tbListaSingoloStatoVersamento

	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> _widg_tbListaSingoloStatoVersamento;

	public void setWidg_tbListaSingoloStatoVersamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> value) {
		_widg_tbListaSingoloStatoVersamento = value;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> getWidg_tbListaSingoloStatoVersamento() {
		return _widg_tbListaSingoloStatoVersamento;
	}

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

	public void setAppDatalistaSingoloStatoRpt(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> value) {
		getSession().put("appDatalistaSingoloStatoRpt", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> getAppDatalistaSingoloStatoRpt() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT>) (getSession()
				.get("appDatalistaSingoloStatoRpt"));
	}

	public void setAppDatalistaSingoloStatoVersamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> value) {
		getSession().put("appDatalistaSingoloStatoVersamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> getAppDatalistaSingoloStatoVersamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento>) (getSession()
				.get("appDatalistaSingoloStatoVersamento"));
	}

	public void setAppDataselettoreIdRPT(java.lang.String value) {
		getSession().put("appDataselettoreIdRPT", value);
	}

	public java.lang.String getAppDataselettoreIdRPT() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRPT"));
	}

	public void setAppDatasingoloStatoVersamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento value) {
		getSession().put("appDatasingoloStatoVersamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento getAppDatasingoloStatoVersamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento) (getSession()
				.get("appDatasingoloStatoVersamento"));
	}

	public void setAppDatastatoRPTRisposta(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta value) {
		getSession().put("appDatastatoRPTRisposta", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta getAppDatastatoRPTRisposta() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta) (getSession()
				.get("appDatastatoRPTRisposta"));
	}

	public void setAppDatasingoloStatoRPT(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT value) {
		getSession().put("appDatasingoloStatoRPT", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT getAppDatasingoloStatoRPT() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT) (getSession()
				.get("appDatasingoloStatoRPT"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btIndietro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btIndietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneStatiRptAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneStatiRptAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneStatiRptAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneStatiRptAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneStatiRptAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tbListaSingoloStatoRpt.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaSingoloStatoRpt_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaSingoloStatoRpt");

		if (isTableClearStatus("cpGestioneStatiRpt_tbListaSingoloStatoRpt")) {
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
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget tbListaSingoloStatoVersamento.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaSingoloStatoVersamento_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaSingoloStatoVersamento");

		if (isTableClearStatus("cpGestioneStatiRpt_tbListaSingoloStatoVersamento")) {
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
		/*PROTECTED REGION ID(R-137332203) ENABLED START*/
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
				log.error("[CpGestioneStatiRptAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneStatiRptAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{""};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resnullAction_0_on = new String[]{"wiewMenu",
				"esito", "statoAttuale", "urlPagamento",
				"tbListaSingoloStatoRpt", "tbListaSingoloStatoVersamento",
				"btIndietro"};

		String[] act_onRefresh_resnullAction_0_off = new String[]{};

		String[] act_onRefresh_resnullAction_0_show = new String[]{"wiewMenu",
				"esito", "statoAttuale", "urlPagamento",
				"tbListaSingoloStatoRpt", "tbListaSingoloStatoVersamento",
				"btIndietro"};

		String[] act_onRefresh_resnullAction_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resnullAction_0 = new ScreenStateCommand(
				"cpGestioneStatiRpt", "INIZIALE",
				act_onRefresh_resnullAction_0_on,
				act_onRefresh_resnullAction_0_off,
				act_onRefresh_resnullAction_0_show,
				act_onRefresh_resnullAction_0_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[0] = act_onRefresh_resnullAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneStatiRptAction",
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

		// constraint fittizio per wiewMenu
		UISecConstraint wiewMenu_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "wiewMenu",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] wiewMenu_constraints = new UISecConstraint[]{wiewMenu_defaultVisib_ctr};
		UISecConstraint wiewMenu_ctr = new ComplexUISecConstraint(
				wiewMenu_constraints);
		allConstraints.put("wiewMenu", wiewMenu_ctr);

		// constraint fittizio per esito
		UISecConstraint esito_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "esito",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] esito_constraints = new UISecConstraint[]{esito_defaultVisib_ctr};
		UISecConstraint esito_ctr = new ComplexUISecConstraint(
				esito_constraints);
		allConstraints.put("esito", esito_ctr);

		// constraint fittizio per statoAttuale
		UISecConstraint statoAttuale_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "statoAttuale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] statoAttuale_constraints = new UISecConstraint[]{statoAttuale_defaultVisib_ctr};
		UISecConstraint statoAttuale_ctr = new ComplexUISecConstraint(
				statoAttuale_constraints);
		allConstraints.put("statoAttuale", statoAttuale_ctr);

		// constraint fittizio per urlPagamento
		UISecConstraint urlPagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "urlPagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] urlPagamento_constraints = new UISecConstraint[]{urlPagamento_defaultVisib_ctr};
		UISecConstraint urlPagamento_ctr = new ComplexUISecConstraint(
				urlPagamento_constraints);
		allConstraints.put("urlPagamento", urlPagamento_ctr);

		// constraint fittizio per tbListaSingoloStatoRpt
		UISecConstraint tbListaSingoloStatoRpt_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt",
				"tbListaSingoloStatoRpt",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaSingoloStatoRpt_constraints = new UISecConstraint[]{tbListaSingoloStatoRpt_defaultVisib_ctr};
		UISecConstraint tbListaSingoloStatoRpt_ctr = new ComplexUISecConstraint(
				tbListaSingoloStatoRpt_constraints);
		allConstraints
				.put("tbListaSingoloStatoRpt", tbListaSingoloStatoRpt_ctr);

		// constraint fittizio per tbListaSingoloStatoVersamento
		UISecConstraint tbListaSingoloStatoVersamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt",
				"tbListaSingoloStatoVersamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaSingoloStatoVersamento_constraints = new UISecConstraint[]{tbListaSingoloStatoVersamento_defaultVisib_ctr};
		UISecConstraint tbListaSingoloStatoVersamento_ctr = new ComplexUISecConstraint(
				tbListaSingoloStatoVersamento_constraints);
		allConstraints.put("tbListaSingoloStatoVersamento",
				tbListaSingoloStatoVersamento_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "btIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultVisib_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per wiewMenu
		UISecConstraint wiewMenu_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "wiewMenu",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] wiewMenu_constraints = new UISecConstraint[]{wiewMenu_defaultOnoff_ctr};
		UISecConstraint wiewMenu_ctr = new ComplexUISecConstraint(
				wiewMenu_constraints);
		allConstraints.put("wiewMenu", wiewMenu_ctr);

		// constraint fittizio per esito
		UISecConstraint esito_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "esito",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] esito_constraints = new UISecConstraint[]{esito_defaultOnoff_ctr};
		UISecConstraint esito_ctr = new ComplexUISecConstraint(
				esito_constraints);
		allConstraints.put("esito", esito_ctr);

		// constraint fittizio per statoAttuale
		UISecConstraint statoAttuale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "statoAttuale",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] statoAttuale_constraints = new UISecConstraint[]{statoAttuale_defaultOnoff_ctr};
		UISecConstraint statoAttuale_ctr = new ComplexUISecConstraint(
				statoAttuale_constraints);
		allConstraints.put("statoAttuale", statoAttuale_ctr);

		// constraint fittizio per urlPagamento
		UISecConstraint urlPagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "urlPagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] urlPagamento_constraints = new UISecConstraint[]{urlPagamento_defaultOnoff_ctr};
		UISecConstraint urlPagamento_ctr = new ComplexUISecConstraint(
				urlPagamento_constraints);
		allConstraints.put("urlPagamento", urlPagamento_ctr);

		// constraint fittizio per tbListaSingoloStatoRpt
		UISecConstraint tbListaSingoloStatoRpt_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt",
				"tbListaSingoloStatoRpt",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaSingoloStatoRpt_constraints = new UISecConstraint[]{tbListaSingoloStatoRpt_defaultOnoff_ctr};
		UISecConstraint tbListaSingoloStatoRpt_ctr = new ComplexUISecConstraint(
				tbListaSingoloStatoRpt_constraints);
		allConstraints
				.put("tbListaSingoloStatoRpt", tbListaSingoloStatoRpt_ctr);

		// constraint fittizio per tbListaSingoloStatoVersamento
		UISecConstraint tbListaSingoloStatoVersamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt",
				"tbListaSingoloStatoVersamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaSingoloStatoVersamento_constraints = new UISecConstraint[]{tbListaSingoloStatoVersamento_defaultOnoff_ctr};
		UISecConstraint tbListaSingoloStatoVersamento_ctr = new ComplexUISecConstraint(
				tbListaSingoloStatoVersamento_constraints);
		allConstraints.put("tbListaSingoloStatoVersamento",
				tbListaSingoloStatoVersamento_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneStatiRpt", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneStatiRptAction::dumpmodel] START");

		log.debug("[CpGestioneStatiRptAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneStatiRptAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneStatiRptAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneStatiRptAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneStatiRptAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneStatiRptAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpGestioneStatiRptAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneStatiRpt");
		log.debug("[CpGestioneStatiRptAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneStatiRptAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneStatiRptAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btIndietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btIndietroByEvh = new HashMap<String, ICommand>();

		cmds4btIndietroByEvh.put("CLICKED", initCommandBtIndietro_CLICKED());
		cmdsByWidget.put("btIndietro", cmds4btIndietroByEvh);

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

	private ICommand initCommandBtIndietro_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4indietro = new String[]{"INDIETRO"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btIndietrostep_resindietroAction_0 = new JumpCommand(
				"cpGestioneRPT", null, false);
		/// Jump Command end
		actionsForResults4indietro[0] = act_actions_clicked_btIndietrostep_resindietroAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneStatiRptAction",
							"readOne()", "chiamata verso BackEnd", "indietro");
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
