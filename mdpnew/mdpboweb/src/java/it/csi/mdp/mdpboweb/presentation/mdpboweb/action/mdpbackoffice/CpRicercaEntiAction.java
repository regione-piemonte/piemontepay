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
 * CpRicercaEntiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpRicercaEntiAction extends BaseAction implements Preparable {

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

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataenti(it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti value) {
		getSession().put("appDataenti", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti getAppDataenti() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti) (getSession()
				.get("appDataenti"));
	}

	public void setAppDataricercaEnti(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti value) {
		getSession().put("appDataricercaEnti", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti getAppDataricercaEnti() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti) (getSession()
				.get("appDataricercaEnti"));
	}

	public void setAppDatalistaEnti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti> value) {
		getSession().put("appDatalistaEnti", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti> getAppDatalistaEnti() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti>) (getSession()
				.get("appDatalistaEnti"));
	}

	public void setAppDataselettoreIdEnte(java.lang.String value) {
		getSession().put("appDataselettoreIdEnte", value);
	}

	public java.lang.String getAppDataselettoreIdEnte() {
		return (java.lang.String) (getSession().get("appDataselettoreIdEnte"));
	}

	public void setAppDatalistaStatiAttivazioneEnti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoAttivazioneEnti> value) {
		getSession().put("appDatalistaStatiAttivazioneEnti", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoAttivazioneEnti> getAppDatalistaStatiAttivazioneEnti() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoAttivazioneEnti>) (getSession()
				.get("appDatalistaStatiAttivazioneEnti"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel.class;
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
			log.debug("[CpRicercaEntiAction::handleCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btClean]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtClean_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btClean", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpRicercaEntiAction::handleBtClean_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleBtClean_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleBtClean_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleBtClean_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleBtClean_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [newEnte]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleNewEnte_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("newEnte", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpRicercaEntiAction::handleNewEnte_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleNewEnte_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleNewEnte_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleNewEnte_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleNewEnte_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [updEnte]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleUpdEnte_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("updEnte", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpRicercaEntiAction::handleUpdEnte_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleUpdEnte_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleUpdEnte_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleUpdEnte_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleUpdEnte_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [delEnte]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleDelEnte_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("delEnte", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpRicercaEntiAction::handleDelEnte_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleDelEnte_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleDelEnte_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaEntiAction::handleDelEnte_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaEntiAction::handleDelEnte_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget attivo.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideAttivo_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaStatiAttivazioneEnti");

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
		Object dataToProvide = ctx.getValueStack()
				.findValue("appDatalistaEnti");

		if (isTableClearStatus("cpRicercaEnti_tRicerca")) {
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
		/*PROTECTED REGION ID(R-1034964905) ENABLED START*/
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
				log.error("[CpRicercaEntiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpRicercaEntiAction::isWidgetVisible] errore durante verifica->hide");
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
				"mnuView", "txdescrizione", "txPartitaIva", "cerca", "newEnte",
				"attivo", "btClean", "enteId"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{
				"tRicerca", "updEnte", "delEnte"};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "txdescrizione", "txPartitaIva", "cerca", "newEnte",
				"attivo", "btClean", "enteId"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{
				"tRicerca", "updEnte", "delEnte"};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpRicercaEnti", "INIZIALE",
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
				"mnuView", "txdescrizione", "txPartitaIva", "enteId", "cerca",
				"tRicerca", "newEnte", "updEnte", "delEnte", "attivo",
				"btClean"};

		String[] act_onRefresh_resricercaActionstep_0_off = new String[]{};

		String[] act_onRefresh_resricercaActionstep_0_show = new String[]{
				"mnuView", "txdescrizione", "txPartitaIva", "enteId", "cerca",
				"tRicerca", "newEnte", "updEnte", "delEnte", "attivo",
				"btClean"};

		String[] act_onRefresh_resricercaActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resricercaActionstep_0 = new ScreenStateCommand(
				"cpRicercaEnti", "RICERCA_OK",
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpRicercaEntiAction", "readOne()",
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

		new DummyUISecConstraint("cpRicercaEnti", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txdescrizione
		UISecConstraint txdescrizione_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "txdescrizione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdescrizione_constraints = new UISecConstraint[]{txdescrizione_defaultVisib_ctr};
		UISecConstraint txdescrizione_ctr = new ComplexUISecConstraint(
				txdescrizione_constraints);
		allConstraints.put("txdescrizione", txdescrizione_ctr);

		// constraint fittizio per txPartitaIva
		UISecConstraint txPartitaIva_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "txPartitaIva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txPartitaIva_constraints = new UISecConstraint[]{txPartitaIva_defaultVisib_ctr};
		UISecConstraint txPartitaIva_ctr = new ComplexUISecConstraint(
				txPartitaIva_constraints);
		allConstraints.put("txPartitaIva", txPartitaIva_ctr);

		// constraint fittizio per enteId
		UISecConstraint enteId_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "enteId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] enteId_constraints = new UISecConstraint[]{enteId_defaultVisib_ctr};
		UISecConstraint enteId_ctr = new ComplexUISecConstraint(
				enteId_constraints);
		allConstraints.put("enteId", enteId_ctr);

		// constraint fittizio per attivo
		UISecConstraint attivo_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "attivo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] attivo_constraints = new UISecConstraint[]{attivo_defaultVisib_ctr};
		UISecConstraint attivo_ctr = new ComplexUISecConstraint(
				attivo_constraints);
		allConstraints.put("attivo", attivo_ctr);

		// constraint fittizio per cerca
		UISecConstraint cerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "cerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cerca_constraints = new UISecConstraint[]{cerca_defaultVisib_ctr};
		UISecConstraint cerca_ctr = new ComplexUISecConstraint(
				cerca_constraints);
		allConstraints.put("cerca", cerca_ctr);

		// constraint fittizio per btClean
		UISecConstraint btClean_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "btClean",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btClean_constraints = new UISecConstraint[]{btClean_defaultVisib_ctr};
		UISecConstraint btClean_ctr = new ComplexUISecConstraint(
				btClean_constraints);
		allConstraints.put("btClean", btClean_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "tRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultVisib_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per newEnte
		UISecConstraint newEnte_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "newEnte",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] newEnte_constraints = new UISecConstraint[]{newEnte_defaultVisib_ctr};
		UISecConstraint newEnte_ctr = new ComplexUISecConstraint(
				newEnte_constraints);
		allConstraints.put("newEnte", newEnte_ctr);

		// constraint fittizio per updEnte
		UISecConstraint updEnte_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "updEnte",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] updEnte_constraints = new UISecConstraint[]{updEnte_defaultVisib_ctr};
		UISecConstraint updEnte_ctr = new ComplexUISecConstraint(
				updEnte_constraints);
		allConstraints.put("updEnte", updEnte_ctr);

		// constraint fittizio per delEnte
		UISecConstraint delEnte_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "delEnte",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] delEnte_constraints = new UISecConstraint[]{delEnte_defaultVisib_ctr};
		UISecConstraint delEnte_ctr = new ComplexUISecConstraint(
				delEnte_constraints);
		allConstraints.put("delEnte", delEnte_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txdescrizione
		UISecConstraint txdescrizione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "txdescrizione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdescrizione_constraints = new UISecConstraint[]{txdescrizione_defaultOnoff_ctr};
		UISecConstraint txdescrizione_ctr = new ComplexUISecConstraint(
				txdescrizione_constraints);
		allConstraints.put("txdescrizione", txdescrizione_ctr);

		// constraint fittizio per txPartitaIva
		UISecConstraint txPartitaIva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "txPartitaIva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txPartitaIva_constraints = new UISecConstraint[]{txPartitaIva_defaultOnoff_ctr};
		UISecConstraint txPartitaIva_ctr = new ComplexUISecConstraint(
				txPartitaIva_constraints);
		allConstraints.put("txPartitaIva", txPartitaIva_ctr);

		// constraint fittizio per enteId
		UISecConstraint enteId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "enteId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] enteId_constraints = new UISecConstraint[]{enteId_defaultOnoff_ctr};
		UISecConstraint enteId_ctr = new ComplexUISecConstraint(
				enteId_constraints);
		allConstraints.put("enteId", enteId_ctr);

		// constraint fittizio per attivo
		UISecConstraint attivo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "attivo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] attivo_constraints = new UISecConstraint[]{attivo_defaultOnoff_ctr};
		UISecConstraint attivo_ctr = new ComplexUISecConstraint(
				attivo_constraints);
		allConstraints.put("attivo", attivo_ctr);

		// constraint fittizio per cerca
		UISecConstraint cerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "cerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cerca_constraints = new UISecConstraint[]{cerca_defaultOnoff_ctr};
		UISecConstraint cerca_ctr = new ComplexUISecConstraint(
				cerca_constraints);
		allConstraints.put("cerca", cerca_ctr);

		// constraint fittizio per btClean
		UISecConstraint btClean_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "btClean",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btClean_constraints = new UISecConstraint[]{btClean_defaultOnoff_ctr};
		UISecConstraint btClean_ctr = new ComplexUISecConstraint(
				btClean_constraints);
		allConstraints.put("btClean", btClean_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "tRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultOnoff_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per newEnte
		UISecConstraint newEnte_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "newEnte",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] newEnte_constraints = new UISecConstraint[]{newEnte_defaultOnoff_ctr};
		UISecConstraint newEnte_ctr = new ComplexUISecConstraint(
				newEnte_constraints);
		allConstraints.put("newEnte", newEnte_ctr);

		// constraint fittizio per updEnte
		UISecConstraint updEnte_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "updEnte",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] updEnte_constraints = new UISecConstraint[]{updEnte_defaultOnoff_ctr};
		UISecConstraint updEnte_ctr = new ComplexUISecConstraint(
				updEnte_constraints);
		allConstraints.put("updEnte", updEnte_ctr);

		// constraint fittizio per delEnte
		UISecConstraint delEnte_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaEnti", "delEnte",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] delEnte_constraints = new UISecConstraint[]{delEnte_defaultOnoff_ctr};
		UISecConstraint delEnte_ctr = new ComplexUISecConstraint(
				delEnte_constraints);
		allConstraints.put("delEnte", delEnte_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpRicercaEntiAction::dumpmodel] START");

		log.debug("[CpRicercaEntiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpRicercaEntiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpRicercaEntiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpRicercaEntiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpRicercaEntiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpRicercaEntiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpRicercaEntiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpRicercaEnti");
		log.debug("[CpRicercaEntiAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpRicercaEntiAction::dumpmodel] [c] sessione");
		log.debug("[CpRicercaEntiAction::dumpmodel] " + session);
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
		// contiene i comandi del widget btClean per ogni Ev.H.
		HashMap<String, ICommand> cmds4btCleanByEvh = new HashMap<String, ICommand>();

		cmds4btCleanByEvh.put("CLICKED", initCommandBtClean_CLICKED());
		cmdsByWidget.put("btClean", cmds4btCleanByEvh);
		// contiene i comandi del widget newEnte per ogni Ev.H.
		HashMap<String, ICommand> cmds4newEnteByEvh = new HashMap<String, ICommand>();

		cmds4newEnteByEvh.put("CLICKED", initCommandNewEnte_CLICKED());
		cmdsByWidget.put("newEnte", cmds4newEnteByEvh);
		// contiene i comandi del widget updEnte per ogni Ev.H.
		HashMap<String, ICommand> cmds4updEnteByEvh = new HashMap<String, ICommand>();

		cmds4updEnteByEvh.put("CLICKED", initCommandUpdEnte_CLICKED());
		cmdsByWidget.put("updEnte", cmds4updEnteByEvh);
		// contiene i comandi del widget delEnte per ogni Ev.H.
		HashMap<String, ICommand> cmds4delEnteByEvh = new HashMap<String, ICommand>();

		cmds4delEnteByEvh.put("CLICKED", initCommandDelEnte_CLICKED());
		cmdsByWidget.put("delEnte", cmds4delEnteByEvh);

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
		String[] resultNames4cercaEnte = new String[]{"RESULTS", "NO_RESULTS"};

		ICommand[] actionsForResults4cercaEnte = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resresultsActionstep_0_on = new String[]{
				"mnuView", "txdescrizione", "txPartitaIva", "enteId", "cerca",
				"tRicerca", "newEnte", "updEnte", "delEnte", "attivo",
				"btClean"};

		String[] act_actions_clicked_cerca_resresultsActionstep_0_off = new String[]{};

		String[] act_actions_clicked_cerca_resresultsActionstep_0_show = new String[]{
				"mnuView", "txdescrizione", "txPartitaIva", "enteId", "cerca",
				"tRicerca", "newEnte", "updEnte", "delEnte", "attivo",
				"btClean"};

		String[] act_actions_clicked_cerca_resresultsActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_cerca_resresultsActionstep_0 = new ScreenStateCommand(
				"cpRicercaEnti", "RICERCA_OK",
				act_actions_clicked_cerca_resresultsActionstep_0_on,
				act_actions_clicked_cerca_resresultsActionstep_0_off,
				act_actions_clicked_cerca_resresultsActionstep_0_show,
				act_actions_clicked_cerca_resresultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resresultsAction_0_steps = new ICommand[]{act_actions_clicked_cerca_resresultsActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resresultsAction_0 = new SequenceCommand(
				act_actions_clicked_cerca_resresultsAction_0_steps);
		// SequenceCommand end
		actionsForResults4cercaEnte[0] = act_actions_clicked_cerca_resresultsAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resno_resultsActionstep_0_on = new String[]{
				"mnuView", "txdescrizione", "txPartitaIva", "enteId", "attivo",
				"cerca", "btClean", "tRicerca", "newEnte"};

		String[] act_actions_clicked_cerca_resno_resultsActionstep_0_off = new String[]{
				"updEnte", "delEnte"};

		String[] act_actions_clicked_cerca_resno_resultsActionstep_0_show = new String[]{
				"mnuView", "txdescrizione", "txPartitaIva", "enteId", "attivo",
				"cerca", "btClean", "tRicerca", "newEnte"};

		String[] act_actions_clicked_cerca_resno_resultsActionstep_0_hide = new String[]{
				"updEnte", "delEnte"};

		ScreenStateCommand act_actions_clicked_cerca_resno_resultsActionstep_0 = new ScreenStateCommand(
				"cpRicercaEnti", "RICERCA_NO_RESULT",
				act_actions_clicked_cerca_resno_resultsActionstep_0_on,
				act_actions_clicked_cerca_resno_resultsActionstep_0_off,
				act_actions_clicked_cerca_resno_resultsActionstep_0_show,
				act_actions_clicked_cerca_resno_resultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resno_resultsAction_1_steps = new ICommand[]{act_actions_clicked_cerca_resno_resultsActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resno_resultsAction_1 = new SequenceCommand(
				act_actions_clicked_cerca_resno_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4cercaEnte[1] = act_actions_clicked_cerca_resno_resultsAction_1;

		ExecCommand act_actions_clicked_cerca_1 = new ExecCommand(
				resultNames4cercaEnte, actionsForResults4cercaEnte) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cercaEnte(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpRicercaEntiAction", "readOne()",
							"chiamata verso BackEnd", "cercaEnte");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cercaEnte]:"
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

	private ICommand initCommandBtClean_CLICKED() {
		// ExecCommand begin
		String[] resultNames4clean = new String[]{"clean"};

		ICommand[] actionsForResults4clean = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btClean_rescleanAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4clean[0] = act_actions_clicked_btClean_rescleanAction_0;

		ExecCommand act_actions_clicked_btClean_1 = new ExecCommand(
				resultNames4clean, actionsForResults4clean) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.clean((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpRicercaEntiAction", "readOne()",
							"chiamata verso BackEnd", "clean");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [clean]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btClean_1;
	}

	private ICommand initCommandNewEnte_CLICKED() {
		// ExecCommand begin
		String[] resultNames4nuovoEnte = new String[]{"inserisci"};

		ICommand[] actionsForResults4nuovoEnte = new ICommand[1];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_newEnte_resinserisciActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_newEnte_resinserisciActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_newEnte_resinserisciActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_newEnte_resinserisciActionstep_1 = new JumpCommand(
				"cpGestioneEnti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_newEnte_resinserisciAction_0_steps = new ICommand[]{
				act_actions_clicked_newEnte_resinserisciActionstep_0,
				act_actions_clicked_newEnte_resinserisciActionstep_1};
		SequenceCommand act_actions_clicked_newEnte_resinserisciAction_0 = new SequenceCommand(
				act_actions_clicked_newEnte_resinserisciAction_0_steps);
		// SequenceCommand end
		actionsForResults4nuovoEnte[0] = act_actions_clicked_newEnte_resinserisciAction_0;

		ExecCommand act_actions_clicked_newEnte_1 = new ExecCommand(
				resultNames4nuovoEnte, actionsForResults4nuovoEnte) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.nuovoEnte(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpRicercaEntiAction", "readOne()",
							"chiamata verso BackEnd", "nuovoEnte");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [nuovoEnte]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_newEnte_1;
	}

	private ICommand initCommandUpdEnte_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaEnte = new String[]{"modifica_ok",
				"modifica_ko"};

		ICommand[] actionsForResults4modificaEnte = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_updEnte_resmodifica_okActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_updEnte_resmodifica_okActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_updEnte_resmodifica_okActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_updEnte_resmodifica_okActionstep_1 = new JumpCommand(
				"cpGestioneEnti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_updEnte_resmodifica_okAction_0_steps = new ICommand[]{
				act_actions_clicked_updEnte_resmodifica_okActionstep_0,
				act_actions_clicked_updEnte_resmodifica_okActionstep_1};
		SequenceCommand act_actions_clicked_updEnte_resmodifica_okAction_0 = new SequenceCommand(
				act_actions_clicked_updEnte_resmodifica_okAction_0_steps);
		// SequenceCommand end
		actionsForResults4modificaEnte[0] = act_actions_clicked_updEnte_resmodifica_okAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_updEnte_resmodifica_koAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4modificaEnte[1] = act_actions_clicked_updEnte_resmodifica_koAction_1;

		ExecCommand act_actions_clicked_updEnte_1 = new ExecCommand(
				resultNames4modificaEnte, actionsForResults4modificaEnte) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaEnte(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpRicercaEntiAction", "readOne()",
							"chiamata verso BackEnd", "modificaEnte");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaEnte]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_updEnte_1;
	}

	private ICommand initCommandDelEnte_CLICKED() {
		// ExecCommand begin
		String[] resultNames4eliminaEnte = new String[]{"cancella_ok",
				"cancella_ko"};

		ICommand[] actionsForResults4eliminaEnte = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_delEnte_rescancella_okAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4eliminaEnte[0] = act_actions_clicked_delEnte_rescancella_okAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_delEnte_rescancella_koAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4eliminaEnte[1] = act_actions_clicked_delEnte_rescancella_koAction_1;

		ExecCommand act_actions_clicked_delEnte_1 = new ExecCommand(
				resultNames4eliminaEnte, actionsForResults4eliminaEnte) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.eliminaEnte(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpRicercaEntiAction", "readOne()",
							"chiamata verso BackEnd", "eliminaEnte");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [eliminaEnte]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_delEnte_1;
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
