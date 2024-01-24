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
 * CpDettaglioTransazioneAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioTransazioneAction extends BaseAction
		implements
			Preparable {

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

	public void setAppDatatransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione value) {
		getSession().put("appDatatransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione getAppDatatransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione) (getSession()
				.get("appDatatransazione"));
	}

	public void setAppDatastatiTransazione(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> value) {
		getSession().put("appDatastatiTransazione", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> getAppDatastatiTransazione() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione>) (getSession()
				.get("appDatastatiTransazione"));
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDatastatiTransazionexCambio(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> value) {
		getSession().put("appDatastatiTransazionexCambio", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> getAppDatastatiTransazionexCambio() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione>) (getSession()
				.get("appDatastatiTransazionexCambio"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnCambiaStato]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCambiaStato_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCambiaStato", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioTransazioneAction::handleBtnCambiaStato_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtnCambiaStato_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtnCambiaStato_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtnCambiaStato_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtnCambiaStato_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnIndietro]
	 */
	@SkipValidation
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnIndietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioTransazioneAction::handleBtnIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtnIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtnIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnVerificaStato]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnVerificaStato_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnVerificaStato", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioTransazioneAction::handleBtnVerificaStato_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtnVerificaStato_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtnVerificaStato_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtnVerificaStato_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtnVerificaStato_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btStorno]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtStorno_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btStorno", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioTransazioneAction::handleBtStorno_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtStorno_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtStorno_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtStorno_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtStorno_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btconferma]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtconferma_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btconferma", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioTransazioneAction::handleBtconferma_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtconferma_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtconferma_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioTransazioneAction::handleBtconferma_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioTransazioneAction::handleBtconferma_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbStato.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbStato_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatastatiTransazionexCambio");

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
		/*PROTECTED REGION ID(R-1304635777) ENABLED START*/
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
				log.error("[CpDettaglioTransazioneAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioTransazioneAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Ok_Con_Cambio_Stato",
				"Ok_Senza_Cambio_Stato"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_con_cambio_statoActionstep_0_on = new String[]{
				"mnuView", "ptIdTransazione", "ptDataInizio", "ptDataFine",
				"ptGatewayPagamento", "ptModalitaPagamento",
				"ptImportoTransazione", "ptStatoAttuale", "btnIndietro",
				"btnVerificaStato", "cbStato", "btnCambiaStato",
				"ptIdApplicazione", "ptbasketId", "tpmerchantId",
				"ptpaymentid", "ptpayurl", "ptpgresultcode", "startDate",
				"tpcodStato", "oldstate", "btStorno", "btconferma"};

		String[] act_onRefresh_resok_con_cambio_statoActionstep_0_off = new String[]{};

		String[] act_onRefresh_resok_con_cambio_statoActionstep_0_show = new String[]{
				"mnuView", "ptIdTransazione", "ptDataInizio", "ptDataFine",
				"ptGatewayPagamento", "ptModalitaPagamento",
				"ptImportoTransazione", "ptStatoAttuale", "btnIndietro",
				"btnVerificaStato", "cbStato", "btnCambiaStato",
				"ptIdApplicazione", "ptbasketId", "tpmerchantId",
				"ptpaymentid", "ptpayurl", "ptpgresultcode", "startDate",
				"tpcodStato", "oldstate", "btStorno", "btconferma"};

		String[] act_onRefresh_resok_con_cambio_statoActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resok_con_cambio_statoActionstep_0 = new ScreenStateCommand(
				"cpDettaglioTransazione", "VIEW_CON_CAMBIO_STATO",
				act_onRefresh_resok_con_cambio_statoActionstep_0_on,
				act_onRefresh_resok_con_cambio_statoActionstep_0_off,
				act_onRefresh_resok_con_cambio_statoActionstep_0_show,
				act_onRefresh_resok_con_cambio_statoActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_con_cambio_statoAction_0_steps = new ICommand[]{act_onRefresh_resok_con_cambio_statoActionstep_0};
		SequenceCommand act_onRefresh_resok_con_cambio_statoAction_0 = new SequenceCommand(
				act_onRefresh_resok_con_cambio_statoAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resok_con_cambio_statoAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_senza_cambio_statoActionstep_0_on = new String[]{
				"mnuView", "ptIdTransazione", "ptDataInizio", "ptDataFine",
				"ptGatewayPagamento", "ptModalitaPagamento",
				"ptImportoTransazione", "ptStatoAttuale", "btnIndietro",
				"btnVerificaStato", "ptIdApplicazione", "ptbasketId",
				"tpmerchantId", "ptpaymentid", "ptpayurl", "ptpgresultcode",
				"startDate", "tpcodStato", "oldstate"};

		String[] act_onRefresh_resok_senza_cambio_statoActionstep_0_off = new String[]{
				"cbStato", "btnCambiaStato", "btStorno", "btconferma"};

		String[] act_onRefresh_resok_senza_cambio_statoActionstep_0_show = new String[]{
				"mnuView", "ptIdTransazione", "ptDataInizio", "ptDataFine",
				"ptGatewayPagamento", "ptModalitaPagamento",
				"ptImportoTransazione", "ptStatoAttuale", "btnIndietro",
				"btnVerificaStato", "ptIdApplicazione", "ptbasketId",
				"tpmerchantId", "ptpaymentid", "ptpayurl", "ptpgresultcode",
				"startDate", "tpcodStato", "oldstate"};

		String[] act_onRefresh_resok_senza_cambio_statoActionstep_0_hide = new String[]{
				"cbStato", "btnCambiaStato", "btStorno", "btconferma"};

		ScreenStateCommand act_onRefresh_resok_senza_cambio_statoActionstep_0 = new ScreenStateCommand(
				"cpDettaglioTransazione", "VIEW_SENZA_CAMBIO_STATO",
				act_onRefresh_resok_senza_cambio_statoActionstep_0_on,
				act_onRefresh_resok_senza_cambio_statoActionstep_0_off,
				act_onRefresh_resok_senza_cambio_statoActionstep_0_show,
				act_onRefresh_resok_senza_cambio_statoActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_senza_cambio_statoAction_1_steps = new ICommand[]{act_onRefresh_resok_senza_cambio_statoActionstep_0};
		SequenceCommand act_onRefresh_resok_senza_cambio_statoAction_1 = new SequenceCommand(
				act_onRefresh_resok_senza_cambio_statoAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resok_senza_cambio_statoAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioTransazioneAction",
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

		new DummyUISecConstraint("cpDettaglioTransazione", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptIdTransazione
		UISecConstraint ptIdTransazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptIdTransazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdTransazione_constraints = new UISecConstraint[]{ptIdTransazione_defaultVisib_ctr};
		UISecConstraint ptIdTransazione_ctr = new ComplexUISecConstraint(
				ptIdTransazione_constraints);
		allConstraints.put("ptIdTransazione", ptIdTransazione_ctr);

		// constraint fittizio per ptIdApplicazione
		UISecConstraint ptIdApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptIdApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdApplicazione_constraints = new UISecConstraint[]{ptIdApplicazione_defaultVisib_ctr};
		UISecConstraint ptIdApplicazione_ctr = new ComplexUISecConstraint(
				ptIdApplicazione_constraints);
		allConstraints.put("ptIdApplicazione", ptIdApplicazione_ctr);

		// constraint fittizio per ptDataInizio
		UISecConstraint ptDataInizio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptDataInizio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptDataInizio_constraints = new UISecConstraint[]{ptDataInizio_defaultVisib_ctr};
		UISecConstraint ptDataInizio_ctr = new ComplexUISecConstraint(
				ptDataInizio_constraints);
		allConstraints.put("ptDataInizio", ptDataInizio_ctr);

		// constraint fittizio per ptDataFine
		UISecConstraint ptDataFine_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptDataFine",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptDataFine_constraints = new UISecConstraint[]{ptDataFine_defaultVisib_ctr};
		UISecConstraint ptDataFine_ctr = new ComplexUISecConstraint(
				ptDataFine_constraints);
		allConstraints.put("ptDataFine", ptDataFine_ctr);

		// constraint fittizio per ptGatewayPagamento
		UISecConstraint ptGatewayPagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione",
				"ptGatewayPagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptGatewayPagamento_constraints = new UISecConstraint[]{ptGatewayPagamento_defaultVisib_ctr};
		UISecConstraint ptGatewayPagamento_ctr = new ComplexUISecConstraint(
				ptGatewayPagamento_constraints);
		allConstraints.put("ptGatewayPagamento", ptGatewayPagamento_ctr);

		// constraint fittizio per ptModalitaPagamento
		UISecConstraint ptModalitaPagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione",
				"ptModalitaPagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptModalitaPagamento_constraints = new UISecConstraint[]{ptModalitaPagamento_defaultVisib_ctr};
		UISecConstraint ptModalitaPagamento_ctr = new ComplexUISecConstraint(
				ptModalitaPagamento_constraints);
		allConstraints.put("ptModalitaPagamento", ptModalitaPagamento_ctr);

		// constraint fittizio per ptImportoTransazione
		UISecConstraint ptImportoTransazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione",
				"ptImportoTransazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptImportoTransazione_constraints = new UISecConstraint[]{ptImportoTransazione_defaultVisib_ctr};
		UISecConstraint ptImportoTransazione_ctr = new ComplexUISecConstraint(
				ptImportoTransazione_constraints);
		allConstraints.put("ptImportoTransazione", ptImportoTransazione_ctr);

		// constraint fittizio per ptStatoAttuale
		UISecConstraint ptStatoAttuale_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptStatoAttuale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptStatoAttuale_constraints = new UISecConstraint[]{ptStatoAttuale_defaultVisib_ctr};
		UISecConstraint ptStatoAttuale_ctr = new ComplexUISecConstraint(
				ptStatoAttuale_constraints);
		allConstraints.put("ptStatoAttuale", ptStatoAttuale_ctr);

		// constraint fittizio per ptbasketId
		UISecConstraint ptbasketId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptbasketId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptbasketId_constraints = new UISecConstraint[]{ptbasketId_defaultVisib_ctr};
		UISecConstraint ptbasketId_ctr = new ComplexUISecConstraint(
				ptbasketId_constraints);
		allConstraints.put("ptbasketId", ptbasketId_ctr);

		// constraint fittizio per tpmerchantId
		UISecConstraint tpmerchantId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "tpmerchantId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tpmerchantId_constraints = new UISecConstraint[]{tpmerchantId_defaultVisib_ctr};
		UISecConstraint tpmerchantId_ctr = new ComplexUISecConstraint(
				tpmerchantId_constraints);
		allConstraints.put("tpmerchantId", tpmerchantId_ctr);

		// constraint fittizio per ptpaymentid
		UISecConstraint ptpaymentid_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptpaymentid",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptpaymentid_constraints = new UISecConstraint[]{ptpaymentid_defaultVisib_ctr};
		UISecConstraint ptpaymentid_ctr = new ComplexUISecConstraint(
				ptpaymentid_constraints);
		allConstraints.put("ptpaymentid", ptpaymentid_ctr);

		// constraint fittizio per ptpayurl
		UISecConstraint ptpayurl_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptpayurl",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptpayurl_constraints = new UISecConstraint[]{ptpayurl_defaultVisib_ctr};
		UISecConstraint ptpayurl_ctr = new ComplexUISecConstraint(
				ptpayurl_constraints);
		allConstraints.put("ptpayurl", ptpayurl_ctr);

		// constraint fittizio per ptpgresultcode
		UISecConstraint ptpgresultcode_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptpgresultcode",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptpgresultcode_constraints = new UISecConstraint[]{ptpgresultcode_defaultVisib_ctr};
		UISecConstraint ptpgresultcode_ctr = new ComplexUISecConstraint(
				ptpgresultcode_constraints);
		allConstraints.put("ptpgresultcode", ptpgresultcode_ctr);

		// constraint fittizio per startDate
		UISecConstraint startDate_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "startDate",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] startDate_constraints = new UISecConstraint[]{startDate_defaultVisib_ctr};
		UISecConstraint startDate_ctr = new ComplexUISecConstraint(
				startDate_constraints);
		allConstraints.put("startDate", startDate_ctr);

		// constraint fittizio per tpcodStato
		UISecConstraint tpcodStato_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "tpcodStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tpcodStato_constraints = new UISecConstraint[]{tpcodStato_defaultVisib_ctr};
		UISecConstraint tpcodStato_ctr = new ComplexUISecConstraint(
				tpcodStato_constraints);
		allConstraints.put("tpcodStato", tpcodStato_ctr);

		// constraint fittizio per oldstate
		UISecConstraint oldstate_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "oldstate",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] oldstate_constraints = new UISecConstraint[]{oldstate_defaultVisib_ctr};
		UISecConstraint oldstate_ctr = new ComplexUISecConstraint(
				oldstate_constraints);
		allConstraints.put("oldstate", oldstate_ctr);
		// constraints per cbStato
		UISecConstraint cbStato_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "cbStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] cbStato_constraints = new UISecConstraint[]{cbStato_0_ctr};
		UISecConstraint cbStato_ctr = new ComplexUISecConstraint(
				cbStato_constraints);
		allConstraints.put("cbStato", cbStato_ctr);
		// constraints per btnCambiaStato
		UISecConstraint btnCambiaStato_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "btnCambiaStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] btnCambiaStato_constraints = new UISecConstraint[]{btnCambiaStato_0_ctr};
		UISecConstraint btnCambiaStato_ctr = new ComplexUISecConstraint(
				btnCambiaStato_constraints);
		allConstraints.put("btnCambiaStato", btnCambiaStato_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "btnIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultVisib_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnVerificaStato
		UISecConstraint btnVerificaStato_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "btnVerificaStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnVerificaStato_constraints = new UISecConstraint[]{btnVerificaStato_defaultVisib_ctr};
		UISecConstraint btnVerificaStato_ctr = new ComplexUISecConstraint(
				btnVerificaStato_constraints);
		allConstraints.put("btnVerificaStato", btnVerificaStato_ctr);
		// constraints per btStorno
		UISecConstraint btStorno_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "btStorno",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] btStorno_constraints = new UISecConstraint[]{btStorno_0_ctr};
		UISecConstraint btStorno_ctr = new ComplexUISecConstraint(
				btStorno_constraints);
		allConstraints.put("btStorno", btStorno_ctr);
		// constraints per btconferma
		UISecConstraint btconferma_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "btconferma",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] btconferma_constraints = new UISecConstraint[]{btconferma_0_ctr};
		UISecConstraint btconferma_ctr = new ComplexUISecConstraint(
				btconferma_constraints);
		allConstraints.put("btconferma", btconferma_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptIdTransazione
		UISecConstraint ptIdTransazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptIdTransazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdTransazione_constraints = new UISecConstraint[]{ptIdTransazione_defaultOnoff_ctr};
		UISecConstraint ptIdTransazione_ctr = new ComplexUISecConstraint(
				ptIdTransazione_constraints);
		allConstraints.put("ptIdTransazione", ptIdTransazione_ctr);

		// constraint fittizio per ptIdApplicazione
		UISecConstraint ptIdApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptIdApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdApplicazione_constraints = new UISecConstraint[]{ptIdApplicazione_defaultOnoff_ctr};
		UISecConstraint ptIdApplicazione_ctr = new ComplexUISecConstraint(
				ptIdApplicazione_constraints);
		allConstraints.put("ptIdApplicazione", ptIdApplicazione_ctr);

		// constraint fittizio per ptDataInizio
		UISecConstraint ptDataInizio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptDataInizio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptDataInizio_constraints = new UISecConstraint[]{ptDataInizio_defaultOnoff_ctr};
		UISecConstraint ptDataInizio_ctr = new ComplexUISecConstraint(
				ptDataInizio_constraints);
		allConstraints.put("ptDataInizio", ptDataInizio_ctr);

		// constraint fittizio per ptDataFine
		UISecConstraint ptDataFine_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptDataFine",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptDataFine_constraints = new UISecConstraint[]{ptDataFine_defaultOnoff_ctr};
		UISecConstraint ptDataFine_ctr = new ComplexUISecConstraint(
				ptDataFine_constraints);
		allConstraints.put("ptDataFine", ptDataFine_ctr);

		// constraint fittizio per ptGatewayPagamento
		UISecConstraint ptGatewayPagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione",
				"ptGatewayPagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptGatewayPagamento_constraints = new UISecConstraint[]{ptGatewayPagamento_defaultOnoff_ctr};
		UISecConstraint ptGatewayPagamento_ctr = new ComplexUISecConstraint(
				ptGatewayPagamento_constraints);
		allConstraints.put("ptGatewayPagamento", ptGatewayPagamento_ctr);

		// constraint fittizio per ptModalitaPagamento
		UISecConstraint ptModalitaPagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione",
				"ptModalitaPagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptModalitaPagamento_constraints = new UISecConstraint[]{ptModalitaPagamento_defaultOnoff_ctr};
		UISecConstraint ptModalitaPagamento_ctr = new ComplexUISecConstraint(
				ptModalitaPagamento_constraints);
		allConstraints.put("ptModalitaPagamento", ptModalitaPagamento_ctr);

		// constraint fittizio per ptImportoTransazione
		UISecConstraint ptImportoTransazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione",
				"ptImportoTransazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptImportoTransazione_constraints = new UISecConstraint[]{ptImportoTransazione_defaultOnoff_ctr};
		UISecConstraint ptImportoTransazione_ctr = new ComplexUISecConstraint(
				ptImportoTransazione_constraints);
		allConstraints.put("ptImportoTransazione", ptImportoTransazione_ctr);

		// constraint fittizio per ptStatoAttuale
		UISecConstraint ptStatoAttuale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptStatoAttuale",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptStatoAttuale_constraints = new UISecConstraint[]{ptStatoAttuale_defaultOnoff_ctr};
		UISecConstraint ptStatoAttuale_ctr = new ComplexUISecConstraint(
				ptStatoAttuale_constraints);
		allConstraints.put("ptStatoAttuale", ptStatoAttuale_ctr);

		// constraint fittizio per ptbasketId
		UISecConstraint ptbasketId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptbasketId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptbasketId_constraints = new UISecConstraint[]{ptbasketId_defaultOnoff_ctr};
		UISecConstraint ptbasketId_ctr = new ComplexUISecConstraint(
				ptbasketId_constraints);
		allConstraints.put("ptbasketId", ptbasketId_ctr);

		// constraint fittizio per tpmerchantId
		UISecConstraint tpmerchantId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "tpmerchantId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tpmerchantId_constraints = new UISecConstraint[]{tpmerchantId_defaultOnoff_ctr};
		UISecConstraint tpmerchantId_ctr = new ComplexUISecConstraint(
				tpmerchantId_constraints);
		allConstraints.put("tpmerchantId", tpmerchantId_ctr);

		// constraint fittizio per ptpaymentid
		UISecConstraint ptpaymentid_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptpaymentid",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptpaymentid_constraints = new UISecConstraint[]{ptpaymentid_defaultOnoff_ctr};
		UISecConstraint ptpaymentid_ctr = new ComplexUISecConstraint(
				ptpaymentid_constraints);
		allConstraints.put("ptpaymentid", ptpaymentid_ctr);

		// constraint fittizio per ptpayurl
		UISecConstraint ptpayurl_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptpayurl",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptpayurl_constraints = new UISecConstraint[]{ptpayurl_defaultOnoff_ctr};
		UISecConstraint ptpayurl_ctr = new ComplexUISecConstraint(
				ptpayurl_constraints);
		allConstraints.put("ptpayurl", ptpayurl_ctr);

		// constraint fittizio per ptpgresultcode
		UISecConstraint ptpgresultcode_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "ptpgresultcode",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptpgresultcode_constraints = new UISecConstraint[]{ptpgresultcode_defaultOnoff_ctr};
		UISecConstraint ptpgresultcode_ctr = new ComplexUISecConstraint(
				ptpgresultcode_constraints);
		allConstraints.put("ptpgresultcode", ptpgresultcode_ctr);

		// constraint fittizio per startDate
		UISecConstraint startDate_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "startDate",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] startDate_constraints = new UISecConstraint[]{startDate_defaultOnoff_ctr};
		UISecConstraint startDate_ctr = new ComplexUISecConstraint(
				startDate_constraints);
		allConstraints.put("startDate", startDate_ctr);

		// constraint fittizio per tpcodStato
		UISecConstraint tpcodStato_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "tpcodStato",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tpcodStato_constraints = new UISecConstraint[]{tpcodStato_defaultOnoff_ctr};
		UISecConstraint tpcodStato_ctr = new ComplexUISecConstraint(
				tpcodStato_constraints);
		allConstraints.put("tpcodStato", tpcodStato_ctr);

		// constraint fittizio per oldstate
		UISecConstraint oldstate_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "oldstate",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] oldstate_constraints = new UISecConstraint[]{oldstate_defaultOnoff_ctr};
		UISecConstraint oldstate_ctr = new ComplexUISecConstraint(
				oldstate_constraints);
		allConstraints.put("oldstate", oldstate_ctr);
		// constraints per cbStato
		UISecConstraint cbStato_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "cbStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] cbStato_constraints = new UISecConstraint[]{};
		UISecConstraint cbStato_ctr = new ComplexUISecConstraint(
				cbStato_constraints);
		allConstraints.put("cbStato", cbStato_ctr);
		// constraints per btnCambiaStato
		UISecConstraint btnCambiaStato_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "btnCambiaStato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] btnCambiaStato_constraints = new UISecConstraint[]{};
		UISecConstraint btnCambiaStato_ctr = new ComplexUISecConstraint(
				btnCambiaStato_constraints);
		allConstraints.put("btnCambiaStato", btnCambiaStato_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "btnIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultOnoff_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnVerificaStato
		UISecConstraint btnVerificaStato_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioTransazione", "btnVerificaStato",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnVerificaStato_constraints = new UISecConstraint[]{btnVerificaStato_defaultOnoff_ctr};
		UISecConstraint btnVerificaStato_ctr = new ComplexUISecConstraint(
				btnVerificaStato_constraints);
		allConstraints.put("btnVerificaStato", btnVerificaStato_ctr);
		// constraints per btStorno
		UISecConstraint btStorno_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "btStorno",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] btStorno_constraints = new UISecConstraint[]{};
		UISecConstraint btStorno_ctr = new ComplexUISecConstraint(
				btStorno_constraints);
		allConstraints.put("btStorno", btStorno_ctr);
		// constraints per btconferma
		UISecConstraint btconferma_0_ctr =

		new UCBasedUISecConstraint("cpDettaglioTransazione", "btconferma",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] btconferma_constraints = new UISecConstraint[]{};
		UISecConstraint btconferma_ctr = new ComplexUISecConstraint(
				btconferma_constraints);
		allConstraints.put("btconferma", btconferma_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioTransazioneAction::dumpmodel] START");

		log.debug("[CpDettaglioTransazioneAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioTransazioneAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioTransazioneAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioTransazioneAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioTransazioneAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioTransazioneAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioTransazioneAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioTransazione");
		log.debug("[CpDettaglioTransazioneAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpDettaglioTransazioneAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioTransazioneAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnCambiaStato per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCambiaStatoByEvh = new HashMap<String, ICommand>();

		cmds4btnCambiaStatoByEvh.put("CLICKED",
				initCommandBtnCambiaStato_CLICKED());
		cmdsByWidget.put("btnCambiaStato", cmds4btnCambiaStatoByEvh);
		// contiene i comandi del widget btnIndietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnIndietroByEvh = new HashMap<String, ICommand>();

		cmds4btnIndietroByEvh.put("CLICKED", initCommandBtnIndietro_CLICKED());
		cmdsByWidget.put("btnIndietro", cmds4btnIndietroByEvh);
		// contiene i comandi del widget btnVerificaStato per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnVerificaStatoByEvh = new HashMap<String, ICommand>();

		cmds4btnVerificaStatoByEvh.put("CLICKED",
				initCommandBtnVerificaStato_CLICKED());
		cmdsByWidget.put("btnVerificaStato", cmds4btnVerificaStatoByEvh);
		// contiene i comandi del widget btStorno per ogni Ev.H.
		HashMap<String, ICommand> cmds4btStornoByEvh = new HashMap<String, ICommand>();

		cmds4btStornoByEvh.put("CLICKED", initCommandBtStorno_CLICKED());
		cmdsByWidget.put("btStorno", cmds4btStornoByEvh);
		// contiene i comandi del widget btconferma per ogni Ev.H.
		HashMap<String, ICommand> cmds4btconfermaByEvh = new HashMap<String, ICommand>();

		cmds4btconfermaByEvh.put("CLICKED", initCommandBtconferma_CLICKED());
		cmdsByWidget.put("btconferma", cmds4btconfermaByEvh);

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

	private ICommand initCommandBtnCambiaStato_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cambiaStatoTransazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4cambiaStatoTransazione = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCambiaStato_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCambiaStato_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnCambiaStato_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnCambiaStato_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnCambiaStato_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4cambiaStatoTransazione[0] = act_actions_clicked_btnCambiaStato_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCambiaStato_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCambiaStato_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnCambiaStato_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnCambiaStato_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnCambiaStato_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4cambiaStatoTransazione[1] = act_actions_clicked_btnCambiaStato_reskoAction_1;

		ExecCommand act_actions_clicked_btnCambiaStato_1 = new ExecCommand(
				resultNames4cambiaStatoTransazione,
				actionsForResults4cambiaStatoTransazione) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cambiaStatoTransazione(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioTransazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"cambiaStatoTransazione");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cambiaStatoTransazione]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCambiaStato_1;
	}

	private ICommand initCommandBtnIndietro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4indietro = new String[]{"Ok"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnIndietro_resokActionstep_0 = new JumpCommand(
				"cpGestioneTransazioni", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnIndietro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnIndietro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnIndietro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnIndietro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4indietro[0] = act_actions_clicked_btnIndietro_resokAction_0;

		ExecCommand act_actions_clicked_btnIndietro_1 = new ExecCommand(
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioTransazioneAction",
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
		return act_actions_clicked_btnIndietro_1;
	}

	private ICommand initCommandBtnVerificaStato_CLICKED() {
		// ExecCommand begin
		String[] resultNames4verificaStato = new String[]{"Ok"};

		ICommand[] actionsForResults4verificaStato = new ICommand[1];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnVerificaStato_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnVerificaStato_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnVerificaStato_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnVerificaStato_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnVerificaStato_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4verificaStato[0] = act_actions_clicked_btnVerificaStato_resokAction_0;

		ExecCommand act_actions_clicked_btnVerificaStato_1 = new ExecCommand(
				resultNames4verificaStato, actionsForResults4verificaStato) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.verificaStato(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioTransazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"verificaStato");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [verificaStato]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnVerificaStato_1;
	}

	private ICommand initCommandBtStorno_CLICKED() {
		// ExecCommand begin
		String[] resultNames4storno = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4storno = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btStorno_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btStorno_resokAction_0_steps = new ICommand[]{act_actions_clicked_btStorno_resokActionstep_0};
		SequenceCommand act_actions_clicked_btStorno_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btStorno_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4storno[0] = act_actions_clicked_btStorno_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btStorno_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btStorno_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btStorno_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btStorno_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btStorno_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4storno[1] = act_actions_clicked_btStorno_reskoAction_1;

		ExecCommand act_actions_clicked_btStorno_1 = new ExecCommand(
				resultNames4storno, actionsForResults4storno) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.storno((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioTransazioneAction",
							"readOne()", "chiamata verso BackEnd", "storno");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [storno]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btStorno_1;
	}

	private ICommand initCommandBtconferma_CLICKED() {
		// ExecCommand begin
		String[] resultNames4conferma = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4conferma = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btconferma_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btconferma_resokAction_0_steps = new ICommand[]{act_actions_clicked_btconferma_resokActionstep_0};
		SequenceCommand act_actions_clicked_btconferma_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btconferma_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4conferma[0] = act_actions_clicked_btconferma_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btconferma_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btconferma_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btconferma_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btconferma_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btconferma_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4conferma[1] = act_actions_clicked_btconferma_reskoAction_1;

		ExecCommand act_actions_clicked_btconferma_1 = new ExecCommand(
				resultNames4conferma, actionsForResults4conferma) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.conferma(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioTransazioneAction",
							"readOne()", "chiamata verso BackEnd", "conferma");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [conferma]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btconferma_1;
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
