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
 * CpGestioneRPTAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneRPTAction extends BaseAction implements Preparable {

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

	public void setAppDatalistaStatiRpt(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt> value) {
		getSession().put("appDatalistaStatiRpt", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt> getAppDatalistaStatiRpt() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt>) (getSession()
				.get("appDatalistaStatiRpt"));
	}

	public void setAppDataricercaRPT(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico value) {
		getSession().put("appDataricercaRPT", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico getAppDataricercaRPT() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico) (getSession()
				.get("appDataricercaRPT"));
	}

	public void setAppDataselettoreIdRPT(java.lang.String value) {
		getSession().put("appDataselettoreIdRPT", value);
	}

	public java.lang.String getAppDataselettoreIdRPT() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRPT"));
	}

	public void setAppDatastatiRpt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt value) {
		getSession().put("appDatastatiRpt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt getAppDatastatiRpt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.StatiRpt) (getSession()
				.get("appDatastatiRpt"));
	}

	public void setAppDatalistaRPT(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico> value) {
		getSession().put("appDatalistaRPT", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico> getAppDatalistaRPT() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico>) (getSession()
				.get("appDatalistaRPT"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btCerca]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtCerca_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btCerca", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRPTAction::handleBtCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleBtCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleBtCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleBtCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleBtCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [vaiAlDettaglio]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleVaiAlDettaglio_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("vaiAlDettaglio", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRPTAction::handleVaiAlDettaglio_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleVaiAlDettaglio_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleVaiAlDettaglio_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleVaiAlDettaglio_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleVaiAlDettaglio_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btxportRpt]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtxportRpt_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btxportRpt", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRPTAction::handleBtxportRpt_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleBtxportRpt_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleBtxportRpt_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleBtxportRpt_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleBtxportRpt_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [EsportaRTassociata]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleEsportaRTassociata_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("EsportaRTassociata", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRPTAction::handleEsportaRTassociata_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleEsportaRTassociata_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleEsportaRTassociata_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleEsportaRTassociata_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleEsportaRTassociata_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btDettStatRPT]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtDettStatRPT_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btDettStatRPT", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneRPTAction::handleBtDettStatRPT_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleBtDettStatRPT_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleBtDettStatRPT_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneRPTAction::handleBtDettStatRPT_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneRPTAction::handleBtDettStatRPT_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbStatiRpt.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbStatiRpt_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaStatiRpt");

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
		Object dataToProvide = ctx.getValueStack().findValue("appDatalistaRPT");

		if (isTableClearStatus("cpGestioneRPT_tbRicerca")) {
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
		/*PROTECTED REGION ID(R-1301956270) ENABLED START*/
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
				log.error("[CpGestioneRPTAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneRPTAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Iniziale", "Result"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeActionstep_0_on = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "idMsgRichiesta",
				"btCerca", "lastUpdateA", "insertDateA"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "btDettStatRPT"};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "idMsgRichiesta",
				"btCerca", "lastUpdateA", "insertDateA"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "btDettStatRPT"};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpGestioneRPT", "VIEW_INIZIALE",
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
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "btCerca",
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "idMsgRichiesta", "lastUpdateA",
				"insertDateA", "btDettStatRPT"};

		String[] act_onRefresh_resresultActionstep_0_off = new String[]{};

		String[] act_onRefresh_resresultActionstep_0_show = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "btCerca",
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "idMsgRichiesta", "lastUpdateA",
				"insertDateA", "btDettStatRPT"};

		String[] act_onRefresh_resresultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resresultActionstep_0 = new ScreenStateCommand(
				"cpGestioneRPT", "VIEW_RICERCA_OK",
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRPTAction", "readOne()",
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

		new DummyUISecConstraint("cpGestioneRPT", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "iuv",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultVisib_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per idTransaction
		UISecConstraint idTransaction_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "idTransaction",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransaction_constraints = new UISecConstraint[]{idTransaction_defaultVisib_ctr};
		UISecConstraint idTransaction_ctr = new ComplexUISecConstraint(
				idTransaction_constraints);
		allConstraints.put("idTransaction", idTransaction_ctr);

		// constraint fittizio per idApplication
		UISecConstraint idApplication_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "idApplication",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idApplication_constraints = new UISecConstraint[]{idApplication_defaultVisib_ctr};
		UISecConstraint idApplication_ctr = new ComplexUISecConstraint(
				idApplication_constraints);
		allConstraints.put("idApplication", idApplication_ctr);

		// constraint fittizio per lastUpdateDa
		UISecConstraint lastUpdateDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "lastUpdateDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateDa_constraints = new UISecConstraint[]{lastUpdateDa_defaultVisib_ctr};
		UISecConstraint lastUpdateDa_ctr = new ComplexUISecConstraint(
				lastUpdateDa_constraints);
		allConstraints.put("lastUpdateDa", lastUpdateDa_ctr);

		// constraint fittizio per lastUpdateA
		UISecConstraint lastUpdateA_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "lastUpdateA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateA_constraints = new UISecConstraint[]{lastUpdateA_defaultVisib_ctr};
		UISecConstraint lastUpdateA_ctr = new ComplexUISecConstraint(
				lastUpdateA_constraints);
		allConstraints.put("lastUpdateA", lastUpdateA_ctr);

		// constraint fittizio per insertDateDa
		UISecConstraint insertDateDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "insertDateDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateDa_constraints = new UISecConstraint[]{insertDateDa_defaultVisib_ctr};
		UISecConstraint insertDateDa_ctr = new ComplexUISecConstraint(
				insertDateDa_constraints);
		allConstraints.put("insertDateDa", insertDateDa_ctr);

		// constraint fittizio per insertDateA
		UISecConstraint insertDateA_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "insertDateA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateA_constraints = new UISecConstraint[]{insertDateA_defaultVisib_ctr};
		UISecConstraint insertDateA_ctr = new ComplexUISecConstraint(
				insertDateA_constraints);
		allConstraints.put("insertDateA", insertDateA_ctr);

		// constraint fittizio per cbStatiRpt
		UISecConstraint cbStatiRpt_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "cbStatiRpt",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbStatiRpt_constraints = new UISecConstraint[]{cbStatiRpt_defaultVisib_ctr};
		UISecConstraint cbStatiRpt_ctr = new ComplexUISecConstraint(
				cbStatiRpt_constraints);
		allConstraints.put("cbStatiRpt", cbStatiRpt_ctr);

		// constraint fittizio per idMsgRichiesta
		UISecConstraint idMsgRichiesta_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "idMsgRichiesta",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idMsgRichiesta_constraints = new UISecConstraint[]{idMsgRichiesta_defaultVisib_ctr};
		UISecConstraint idMsgRichiesta_ctr = new ComplexUISecConstraint(
				idMsgRichiesta_constraints);
		allConstraints.put("idMsgRichiesta", idMsgRichiesta_ctr);

		// constraint fittizio per btCerca
		UISecConstraint btCerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "btCerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btCerca_constraints = new UISecConstraint[]{btCerca_defaultVisib_ctr};
		UISecConstraint btCerca_ctr = new ComplexUISecConstraint(
				btCerca_constraints);
		allConstraints.put("btCerca", btCerca_ctr);

		// constraint fittizio per tbRicerca
		UISecConstraint tbRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "tbRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRicerca_constraints = new UISecConstraint[]{tbRicerca_defaultVisib_ctr};
		UISecConstraint tbRicerca_ctr = new ComplexUISecConstraint(
				tbRicerca_constraints);
		allConstraints.put("tbRicerca", tbRicerca_ctr);

		// constraint fittizio per vaiAlDettaglio
		UISecConstraint vaiAlDettaglio_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "vaiAlDettaglio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] vaiAlDettaglio_constraints = new UISecConstraint[]{vaiAlDettaglio_defaultVisib_ctr};
		UISecConstraint vaiAlDettaglio_ctr = new ComplexUISecConstraint(
				vaiAlDettaglio_constraints);
		allConstraints.put("vaiAlDettaglio", vaiAlDettaglio_ctr);

		// constraint fittizio per btxportRpt
		UISecConstraint btxportRpt_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "btxportRpt",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btxportRpt_constraints = new UISecConstraint[]{btxportRpt_defaultVisib_ctr};
		UISecConstraint btxportRpt_ctr = new ComplexUISecConstraint(
				btxportRpt_constraints);
		allConstraints.put("btxportRpt", btxportRpt_ctr);

		// constraint fittizio per EsportaRTassociata
		UISecConstraint EsportaRTassociata_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "EsportaRTassociata",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] EsportaRTassociata_constraints = new UISecConstraint[]{EsportaRTassociata_defaultVisib_ctr};
		UISecConstraint EsportaRTassociata_ctr = new ComplexUISecConstraint(
				EsportaRTassociata_constraints);
		allConstraints.put("EsportaRTassociata", EsportaRTassociata_ctr);

		// constraint fittizio per btDettStatRPT
		UISecConstraint btDettStatRPT_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "btDettStatRPT",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btDettStatRPT_constraints = new UISecConstraint[]{btDettStatRPT_defaultVisib_ctr};
		UISecConstraint btDettStatRPT_ctr = new ComplexUISecConstraint(
				btDettStatRPT_constraints);
		allConstraints.put("btDettStatRPT", btDettStatRPT_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "iuv",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultOnoff_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per idTransaction
		UISecConstraint idTransaction_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "idTransaction",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransaction_constraints = new UISecConstraint[]{idTransaction_defaultOnoff_ctr};
		UISecConstraint idTransaction_ctr = new ComplexUISecConstraint(
				idTransaction_constraints);
		allConstraints.put("idTransaction", idTransaction_ctr);

		// constraint fittizio per idApplication
		UISecConstraint idApplication_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "idApplication",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idApplication_constraints = new UISecConstraint[]{idApplication_defaultOnoff_ctr};
		UISecConstraint idApplication_ctr = new ComplexUISecConstraint(
				idApplication_constraints);
		allConstraints.put("idApplication", idApplication_ctr);

		// constraint fittizio per lastUpdateDa
		UISecConstraint lastUpdateDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "lastUpdateDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateDa_constraints = new UISecConstraint[]{lastUpdateDa_defaultOnoff_ctr};
		UISecConstraint lastUpdateDa_ctr = new ComplexUISecConstraint(
				lastUpdateDa_constraints);
		allConstraints.put("lastUpdateDa", lastUpdateDa_ctr);

		// constraint fittizio per lastUpdateA
		UISecConstraint lastUpdateA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "lastUpdateA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdateA_constraints = new UISecConstraint[]{lastUpdateA_defaultOnoff_ctr};
		UISecConstraint lastUpdateA_ctr = new ComplexUISecConstraint(
				lastUpdateA_constraints);
		allConstraints.put("lastUpdateA", lastUpdateA_ctr);

		// constraint fittizio per insertDateDa
		UISecConstraint insertDateDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "insertDateDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateDa_constraints = new UISecConstraint[]{insertDateDa_defaultOnoff_ctr};
		UISecConstraint insertDateDa_ctr = new ComplexUISecConstraint(
				insertDateDa_constraints);
		allConstraints.put("insertDateDa", insertDateDa_ctr);

		// constraint fittizio per insertDateA
		UISecConstraint insertDateA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "insertDateA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDateA_constraints = new UISecConstraint[]{insertDateA_defaultOnoff_ctr};
		UISecConstraint insertDateA_ctr = new ComplexUISecConstraint(
				insertDateA_constraints);
		allConstraints.put("insertDateA", insertDateA_ctr);

		// constraint fittizio per cbStatiRpt
		UISecConstraint cbStatiRpt_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "cbStatiRpt",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbStatiRpt_constraints = new UISecConstraint[]{cbStatiRpt_defaultOnoff_ctr};
		UISecConstraint cbStatiRpt_ctr = new ComplexUISecConstraint(
				cbStatiRpt_constraints);
		allConstraints.put("cbStatiRpt", cbStatiRpt_ctr);

		// constraint fittizio per idMsgRichiesta
		UISecConstraint idMsgRichiesta_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "idMsgRichiesta",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idMsgRichiesta_constraints = new UISecConstraint[]{idMsgRichiesta_defaultOnoff_ctr};
		UISecConstraint idMsgRichiesta_ctr = new ComplexUISecConstraint(
				idMsgRichiesta_constraints);
		allConstraints.put("idMsgRichiesta", idMsgRichiesta_ctr);

		// constraint fittizio per btCerca
		UISecConstraint btCerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "btCerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btCerca_constraints = new UISecConstraint[]{btCerca_defaultOnoff_ctr};
		UISecConstraint btCerca_ctr = new ComplexUISecConstraint(
				btCerca_constraints);
		allConstraints.put("btCerca", btCerca_ctr);

		// constraint fittizio per tbRicerca
		UISecConstraint tbRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "tbRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRicerca_constraints = new UISecConstraint[]{tbRicerca_defaultOnoff_ctr};
		UISecConstraint tbRicerca_ctr = new ComplexUISecConstraint(
				tbRicerca_constraints);
		allConstraints.put("tbRicerca", tbRicerca_ctr);

		// constraint fittizio per vaiAlDettaglio
		UISecConstraint vaiAlDettaglio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "vaiAlDettaglio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] vaiAlDettaglio_constraints = new UISecConstraint[]{vaiAlDettaglio_defaultOnoff_ctr};
		UISecConstraint vaiAlDettaglio_ctr = new ComplexUISecConstraint(
				vaiAlDettaglio_constraints);
		allConstraints.put("vaiAlDettaglio", vaiAlDettaglio_ctr);

		// constraint fittizio per btxportRpt
		UISecConstraint btxportRpt_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "btxportRpt",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btxportRpt_constraints = new UISecConstraint[]{btxportRpt_defaultOnoff_ctr};
		UISecConstraint btxportRpt_ctr = new ComplexUISecConstraint(
				btxportRpt_constraints);
		allConstraints.put("btxportRpt", btxportRpt_ctr);

		// constraint fittizio per EsportaRTassociata
		UISecConstraint EsportaRTassociata_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "EsportaRTassociata",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] EsportaRTassociata_constraints = new UISecConstraint[]{EsportaRTassociata_defaultOnoff_ctr};
		UISecConstraint EsportaRTassociata_ctr = new ComplexUISecConstraint(
				EsportaRTassociata_constraints);
		allConstraints.put("EsportaRTassociata", EsportaRTassociata_ctr);

		// constraint fittizio per btDettStatRPT
		UISecConstraint btDettStatRPT_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneRPT", "btDettStatRPT",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btDettStatRPT_constraints = new UISecConstraint[]{btDettStatRPT_defaultOnoff_ctr};
		UISecConstraint btDettStatRPT_ctr = new ComplexUISecConstraint(
				btDettStatRPT_constraints);
		allConstraints.put("btDettStatRPT", btDettStatRPT_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneRPTAction::dumpmodel] START");

		log.debug("[CpGestioneRPTAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneRPTAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneRPTAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneRPTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneRPTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneRPTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpGestioneRPTAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneRPT");
		log.debug("[CpGestioneRPTAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneRPTAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneRPTAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btCerca per ogni Ev.H.
		HashMap<String, ICommand> cmds4btCercaByEvh = new HashMap<String, ICommand>();

		cmds4btCercaByEvh.put("CLICKED", initCommandBtCerca_CLICKED());
		cmdsByWidget.put("btCerca", cmds4btCercaByEvh);
		// contiene i comandi del widget vaiAlDettaglio per ogni Ev.H.
		HashMap<String, ICommand> cmds4vaiAlDettaglioByEvh = new HashMap<String, ICommand>();

		cmds4vaiAlDettaglioByEvh.put("CLICKED",
				initCommandVaiAlDettaglio_CLICKED());
		cmdsByWidget.put("vaiAlDettaglio", cmds4vaiAlDettaglioByEvh);
		// contiene i comandi del widget btxportRpt per ogni Ev.H.
		HashMap<String, ICommand> cmds4btxportRptByEvh = new HashMap<String, ICommand>();

		cmds4btxportRptByEvh.put("CLICKED", initCommandBtxportRpt_CLICKED());
		cmdsByWidget.put("btxportRpt", cmds4btxportRptByEvh);
		// contiene i comandi del widget EsportaRTassociata per ogni Ev.H.
		HashMap<String, ICommand> cmds4EsportaRTassociataByEvh = new HashMap<String, ICommand>();

		cmds4EsportaRTassociataByEvh.put("CLICKED",
				initCommandEsportaRTassociata_CLICKED());
		cmdsByWidget.put("EsportaRTassociata", cmds4EsportaRTassociataByEvh);
		// contiene i comandi del widget btDettStatRPT per ogni Ev.H.
		HashMap<String, ICommand> cmds4btDettStatRPTByEvh = new HashMap<String, ICommand>();

		cmds4btDettStatRPTByEvh.put("CLICKED",
				initCommandBtDettStatRPT_CLICKED());
		cmdsByWidget.put("btDettStatRPT", cmds4btDettStatRPTByEvh);

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

	private ICommand initCommandBtCerca_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cercaRTI = new String[]{"NO_RESULT", "RESULT"};

		ICommand[] actionsForResults4cercaRTI = new ICommand[2];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btCerca_resno_resultAction_0_on = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "idMsgRichiesta",
				"btCerca", "lastUpdateA", "insertDateA"};

		String[] act_actions_clicked_btCerca_resno_resultAction_0_off = new String[]{
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "btDettStatRPT"};

		String[] act_actions_clicked_btCerca_resno_resultAction_0_show = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "idMsgRichiesta",
				"btCerca", "lastUpdateA", "insertDateA"};

		String[] act_actions_clicked_btCerca_resno_resultAction_0_hide = new String[]{
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "btDettStatRPT"};

		ScreenStateCommand act_actions_clicked_btCerca_resno_resultAction_0 = new ScreenStateCommand(
				"cpGestioneRPT", "VIEW_INIZIALE",
				act_actions_clicked_btCerca_resno_resultAction_0_on,
				act_actions_clicked_btCerca_resno_resultAction_0_off,
				act_actions_clicked_btCerca_resno_resultAction_0_show,
				act_actions_clicked_btCerca_resno_resultAction_0_hide);
		//Screen State Command end
		actionsForResults4cercaRTI[0] = act_actions_clicked_btCerca_resno_resultAction_0;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btCerca_resresultAction_1_on = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "btCerca",
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "idMsgRichiesta", "lastUpdateA",
				"insertDateA", "btDettStatRPT"};

		String[] act_actions_clicked_btCerca_resresultAction_1_off = new String[]{};

		String[] act_actions_clicked_btCerca_resresultAction_1_show = new String[]{
				"mnuView", "iuv", "idTransaction", "idApplication",
				"lastUpdateDa", "insertDateDa", "cbStatiRpt", "btCerca",
				"tbRicerca", "vaiAlDettaglio", "btxportRpt",
				"EsportaRTassociata", "idMsgRichiesta", "lastUpdateA",
				"insertDateA", "btDettStatRPT"};

		String[] act_actions_clicked_btCerca_resresultAction_1_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btCerca_resresultAction_1 = new ScreenStateCommand(
				"cpGestioneRPT", "VIEW_RICERCA_OK",
				act_actions_clicked_btCerca_resresultAction_1_on,
				act_actions_clicked_btCerca_resresultAction_1_off,
				act_actions_clicked_btCerca_resresultAction_1_show,
				act_actions_clicked_btCerca_resresultAction_1_hide);
		//Screen State Command end
		actionsForResults4cercaRTI[1] = act_actions_clicked_btCerca_resresultAction_1;

		ExecCommand act_actions_clicked_btCerca_1 = new ExecCommand(
				resultNames4cercaRTI, actionsForResults4cercaRTI) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cercaRTI(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRPTAction", "readOne()",
							"chiamata verso BackEnd", "cercaRTI");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cercaRTI]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btCerca_1;
	}

	private ICommand initCommandVaiAlDettaglio_CLICKED() {
		// ExecCommand begin
		String[] resultNames4vaiAlDettaglioRTI = new String[]{"OK", "KO"};

		ICommand[] actionsForResults4vaiAlDettaglioRTI = new ICommand[2];
		/// Jump Command begin
		JumpCommand act_actions_clicked_vaiAlDettaglio_resokAction_0 = new JumpCommand(
				"cpDettaglioRPT", null, false);
		/// Jump Command end
		actionsForResults4vaiAlDettaglioRTI[0] = act_actions_clicked_vaiAlDettaglio_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_vaiAlDettaglio_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4vaiAlDettaglioRTI[1] = act_actions_clicked_vaiAlDettaglio_reskoAction_1;

		ExecCommand act_actions_clicked_vaiAlDettaglio_1 = new ExecCommand(
				resultNames4vaiAlDettaglioRTI,
				actionsForResults4vaiAlDettaglioRTI) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.vaiAlDettaglioRTI(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRPTAction", "readOne()",
							"chiamata verso BackEnd", "vaiAlDettaglioRTI");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [vaiAlDettaglioRTI]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_vaiAlDettaglio_1;
	}

	private ICommand initCommandBtxportRpt_CLICKED() {
		// ExecCommand begin
		String[] resultNames4esportaRptXml = new String[]{"KO", "OK"};

		ICommand[] actionsForResults4esportaRptXml = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btxportRpt_reskoAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esportaRptXml[0] = act_actions_clicked_btxportRpt_reskoAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btxportRpt_resokAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esportaRptXml[1] = act_actions_clicked_btxportRpt_resokAction_1;

		ExecCommand act_actions_clicked_btxportRpt_1 = new ExecCommand(
				resultNames4esportaRptXml, actionsForResults4esportaRptXml) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.esportaRptXml(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRPTAction", "readOne()",
							"chiamata verso BackEnd", "esportaRptXml");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [esportaRptXml]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btxportRpt_1;
	}

	private ICommand initCommandEsportaRTassociata_CLICKED() {
		// ExecCommand begin
		String[] resultNames4esportaRTAssociata = new String[]{"OK"};

		ICommand[] actionsForResults4esportaRTAssociata = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_EsportaRTassociata_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esportaRTAssociata[0] = act_actions_clicked_EsportaRTassociata_resokAction_0;

		ExecCommand act_actions_clicked_EsportaRTassociata_1 = new ExecCommand(
				resultNames4esportaRTAssociata,
				actionsForResults4esportaRTAssociata) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.esportaRTAssociata(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRPTAction", "readOne()",
							"chiamata verso BackEnd", "esportaRTAssociata");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [esportaRTAssociata]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_EsportaRTassociata_1;
	}

	private ICommand initCommandBtDettStatRPT_CLICKED() {
		// ExecCommand begin
		String[] resultNames4dettStatiRPT = new String[]{"DETT"};

		ICommand[] actionsForResults4dettStatiRPT = new ICommand[1];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btDettStatRPT_resdettActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btDettStatRPT_resdettActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btDettStatRPT_resdettActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btDettStatRPT_resdettActionstep_1 = new JumpCommand(
				"cpGestioneStatiRpt", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btDettStatRPT_resdettAction_0_steps = new ICommand[]{
				act_actions_clicked_btDettStatRPT_resdettActionstep_0,
				act_actions_clicked_btDettStatRPT_resdettActionstep_1};
		SequenceCommand act_actions_clicked_btDettStatRPT_resdettAction_0 = new SequenceCommand(
				act_actions_clicked_btDettStatRPT_resdettAction_0_steps);
		// SequenceCommand end
		actionsForResults4dettStatiRPT[0] = act_actions_clicked_btDettStatRPT_resdettAction_0;

		ExecCommand act_actions_clicked_btDettStatRPT_1 = new ExecCommand(
				resultNames4dettStatiRPT, actionsForResults4dettStatiRPT) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.dettStatiRPT(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneRPTAction", "readOne()",
							"chiamata verso BackEnd", "dettStatiRPT");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [dettStatiRPT]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btDettStatRPT_1;
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
