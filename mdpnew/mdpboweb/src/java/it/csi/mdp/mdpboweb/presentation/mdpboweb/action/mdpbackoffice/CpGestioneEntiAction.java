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
 * CpGestioneEntiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneEntiAction extends BaseAction implements Preparable {

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

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
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

	/////////////////////////////////////////
	/// property comuni a tutte le action
	/////////////////////////////////////////

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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [indietro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("indietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneEntiAction::handleIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneEntiAction::handleIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneEntiAction::handleIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneEntiAction::handleIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneEntiAction::handleIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [inserisci]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleInserisci_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("inserisci", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneEntiAction::handleInserisci_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneEntiAction::handleInserisci_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneEntiAction::handleInserisci_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneEntiAction::handleInserisci_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneEntiAction::handleInserisci_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [modifica]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleModifica_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("modifica", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneEntiAction::handleModifica_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneEntiAction::handleModifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneEntiAction::handleModifica_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneEntiAction::handleModifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneEntiAction::handleModifica_CLICKED] returning default result [SUCCESS]");
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
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R-2140610504) ENABLED START*/
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
				log.error("[CpGestioneEntiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneEntiAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Modifica",
				"Inserisci"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resmodificaActionstep_0_on = new String[]{
				"mnuView", "partitaIva", "descrizione", "ptEnteId", "indietro",
				"modifica", "attivo"};

		String[] act_onRefresh_resmodificaActionstep_0_off = new String[]{
				"txEnteId", "inserisci"};

		String[] act_onRefresh_resmodificaActionstep_0_show = new String[]{
				"mnuView", "partitaIva", "descrizione", "ptEnteId", "indietro",
				"modifica", "attivo"};

		String[] act_onRefresh_resmodificaActionstep_0_hide = new String[]{
				"txEnteId", "inserisci"};

		ScreenStateCommand act_onRefresh_resmodificaActionstep_0 = new ScreenStateCommand(
				"cpGestioneEnti", "MODIFICA",
				act_onRefresh_resmodificaActionstep_0_on,
				act_onRefresh_resmodificaActionstep_0_off,
				act_onRefresh_resmodificaActionstep_0_show,
				act_onRefresh_resmodificaActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resmodificaAction_0_steps = new ICommand[]{act_onRefresh_resmodificaActionstep_0};
		SequenceCommand act_onRefresh_resmodificaAction_0 = new SequenceCommand(
				act_onRefresh_resmodificaAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resmodificaAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinserisciActionstep_0_on = new String[]{
				"mnuView", "partitaIva", "descrizione", "indietro",
				"inserisci", "txEnteId", "attivo"};

		String[] act_onRefresh_resinserisciActionstep_0_off = new String[]{
				"ptEnteId", "modifica"};

		String[] act_onRefresh_resinserisciActionstep_0_show = new String[]{
				"mnuView", "partitaIva", "descrizione", "indietro",
				"inserisci", "txEnteId", "attivo"};

		String[] act_onRefresh_resinserisciActionstep_0_hide = new String[]{
				"ptEnteId", "modifica"};

		ScreenStateCommand act_onRefresh_resinserisciActionstep_0 = new ScreenStateCommand(
				"cpGestioneEnti", "INSERISCI",
				act_onRefresh_resinserisciActionstep_0_on,
				act_onRefresh_resinserisciActionstep_0_off,
				act_onRefresh_resinserisciActionstep_0_show,
				act_onRefresh_resinserisciActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resinserisciAction_1_steps = new ICommand[]{act_onRefresh_resinserisciActionstep_0};
		SequenceCommand act_onRefresh_resinserisciAction_1 = new SequenceCommand(
				act_onRefresh_resinserisciAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resinserisciAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneEntiAction", "readOne()",
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

		new DummyUISecConstraint("cpGestioneEnti", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per partitaIva
		UISecConstraint partitaIva_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "partitaIva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] partitaIva_constraints = new UISecConstraint[]{partitaIva_defaultVisib_ctr};
		UISecConstraint partitaIva_ctr = new ComplexUISecConstraint(
				partitaIva_constraints);
		allConstraints.put("partitaIva", partitaIva_ctr);

		// constraint fittizio per descrizione
		UISecConstraint descrizione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "descrizione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] descrizione_constraints = new UISecConstraint[]{descrizione_defaultVisib_ctr};
		UISecConstraint descrizione_ctr = new ComplexUISecConstraint(
				descrizione_constraints);
		allConstraints.put("descrizione", descrizione_ctr);

		// constraint fittizio per ptEnteId
		UISecConstraint ptEnteId_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "ptEnteId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptEnteId_constraints = new UISecConstraint[]{ptEnteId_defaultVisib_ctr};
		UISecConstraint ptEnteId_ctr = new ComplexUISecConstraint(
				ptEnteId_constraints);
		allConstraints.put("ptEnteId", ptEnteId_ctr);

		// constraint fittizio per txEnteId
		UISecConstraint txEnteId_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "txEnteId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txEnteId_constraints = new UISecConstraint[]{txEnteId_defaultVisib_ctr};
		UISecConstraint txEnteId_ctr = new ComplexUISecConstraint(
				txEnteId_constraints);
		allConstraints.put("txEnteId", txEnteId_ctr);

		// constraint fittizio per attivo
		UISecConstraint attivo_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "attivo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] attivo_constraints = new UISecConstraint[]{attivo_defaultVisib_ctr};
		UISecConstraint attivo_ctr = new ComplexUISecConstraint(
				attivo_constraints);
		allConstraints.put("attivo", attivo_ctr);

		// constraint fittizio per indietro
		UISecConstraint indietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "indietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] indietro_constraints = new UISecConstraint[]{indietro_defaultVisib_ctr};
		UISecConstraint indietro_ctr = new ComplexUISecConstraint(
				indietro_constraints);
		allConstraints.put("indietro", indietro_ctr);

		// constraint fittizio per inserisci
		UISecConstraint inserisci_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "inserisci",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] inserisci_constraints = new UISecConstraint[]{inserisci_defaultVisib_ctr};
		UISecConstraint inserisci_ctr = new ComplexUISecConstraint(
				inserisci_constraints);
		allConstraints.put("inserisci", inserisci_ctr);

		// constraint fittizio per modifica
		UISecConstraint modifica_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "modifica",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] modifica_constraints = new UISecConstraint[]{modifica_defaultVisib_ctr};
		UISecConstraint modifica_ctr = new ComplexUISecConstraint(
				modifica_constraints);
		allConstraints.put("modifica", modifica_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per partitaIva
		UISecConstraint partitaIva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "partitaIva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] partitaIva_constraints = new UISecConstraint[]{partitaIva_defaultOnoff_ctr};
		UISecConstraint partitaIva_ctr = new ComplexUISecConstraint(
				partitaIva_constraints);
		allConstraints.put("partitaIva", partitaIva_ctr);

		// constraint fittizio per descrizione
		UISecConstraint descrizione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "descrizione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] descrizione_constraints = new UISecConstraint[]{descrizione_defaultOnoff_ctr};
		UISecConstraint descrizione_ctr = new ComplexUISecConstraint(
				descrizione_constraints);
		allConstraints.put("descrizione", descrizione_ctr);

		// constraint fittizio per ptEnteId
		UISecConstraint ptEnteId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "ptEnteId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptEnteId_constraints = new UISecConstraint[]{ptEnteId_defaultOnoff_ctr};
		UISecConstraint ptEnteId_ctr = new ComplexUISecConstraint(
				ptEnteId_constraints);
		allConstraints.put("ptEnteId", ptEnteId_ctr);

		// constraint fittizio per txEnteId
		UISecConstraint txEnteId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "txEnteId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txEnteId_constraints = new UISecConstraint[]{txEnteId_defaultOnoff_ctr};
		UISecConstraint txEnteId_ctr = new ComplexUISecConstraint(
				txEnteId_constraints);
		allConstraints.put("txEnteId", txEnteId_ctr);

		// constraint fittizio per attivo
		UISecConstraint attivo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "attivo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] attivo_constraints = new UISecConstraint[]{attivo_defaultOnoff_ctr};
		UISecConstraint attivo_ctr = new ComplexUISecConstraint(
				attivo_constraints);
		allConstraints.put("attivo", attivo_ctr);

		// constraint fittizio per indietro
		UISecConstraint indietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "indietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] indietro_constraints = new UISecConstraint[]{indietro_defaultOnoff_ctr};
		UISecConstraint indietro_ctr = new ComplexUISecConstraint(
				indietro_constraints);
		allConstraints.put("indietro", indietro_ctr);

		// constraint fittizio per inserisci
		UISecConstraint inserisci_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "inserisci",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] inserisci_constraints = new UISecConstraint[]{inserisci_defaultOnoff_ctr};
		UISecConstraint inserisci_ctr = new ComplexUISecConstraint(
				inserisci_constraints);
		allConstraints.put("inserisci", inserisci_ctr);

		// constraint fittizio per modifica
		UISecConstraint modifica_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneEnti", "modifica",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] modifica_constraints = new UISecConstraint[]{modifica_defaultOnoff_ctr};
		UISecConstraint modifica_ctr = new ComplexUISecConstraint(
				modifica_constraints);
		allConstraints.put("modifica", modifica_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneEntiAction::dumpmodel] START");

		log.debug("[CpGestioneEntiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneEntiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneEntiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneEntiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneEntiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneEntiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpGestioneEntiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneEnti");
		log.debug("[CpGestioneEntiAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneEntiAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneEntiAction::dumpmodel] " + session);
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

		// contiene i comandi del widget indietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4indietroByEvh = new HashMap<String, ICommand>();

		cmds4indietroByEvh.put("CLICKED", initCommandIndietro_CLICKED());
		cmdsByWidget.put("indietro", cmds4indietroByEvh);
		// contiene i comandi del widget inserisci per ogni Ev.H.
		HashMap<String, ICommand> cmds4inserisciByEvh = new HashMap<String, ICommand>();

		cmds4inserisciByEvh.put("CLICKED", initCommandInserisci_CLICKED());
		cmdsByWidget.put("inserisci", cmds4inserisciByEvh);
		// contiene i comandi del widget modifica per ogni Ev.H.
		HashMap<String, ICommand> cmds4modificaByEvh = new HashMap<String, ICommand>();

		cmds4modificaByEvh.put("CLICKED", initCommandModifica_CLICKED());
		cmdsByWidget.put("modifica", cmds4modificaByEvh);

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

	private ICommand initCommandIndietro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4indietro = new String[]{"indietro"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_indietro_resindietroAction_0 = new JumpCommand(
				"cpRicercaEnti", null, false);
		/// Jump Command end
		actionsForResults4indietro[0] = act_actions_clicked_indietro_resindietroAction_0;

		ExecCommand act_actions_clicked_indietro_1 = new ExecCommand(
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneEntiAction", "readOne()",
							"chiamata verso BackEnd", "indietro");
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
		return act_actions_clicked_indietro_1;
	}

	private ICommand initCommandInserisci_CLICKED() {
		// ExecCommand begin
		String[] resultNames4inserisci = new String[]{"inserisci"};

		ICommand[] actionsForResults4inserisci = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_inserisci_resinserisciAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4inserisci[0] = act_actions_clicked_inserisci_resinserisciAction_0;

		ExecCommand act_actions_clicked_inserisci_1 = new ExecCommand(
				resultNames4inserisci, actionsForResults4inserisci) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.inserisci(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneEntiAction", "readOne()",
							"chiamata verso BackEnd", "inserisci");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [inserisci]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_inserisci_1;
	}

	private ICommand initCommandModifica_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modifica = new String[]{"modifica"};

		ICommand[] actionsForResults4modifica = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_modifica_resmodificaAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4modifica[0] = act_actions_clicked_modifica_resmodificaAction_0;

		ExecCommand act_actions_clicked_modifica_1 = new ExecCommand(
				resultNames4modifica, actionsForResults4modifica) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modifica(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneEntiAction", "readOne()",
							"chiamata verso BackEnd", "modifica");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modifica]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_modifica_1;
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
