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
 * CpDettaglioFlussoRiversamentoAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioFlussoRiversamentoAction extends BaseAction
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

	public void setAppDataselettoreFlussoRiversamento(java.lang.String value) {
		getSession().put("appDataselettoreFlussoRiversamento", value);
	}

	public java.lang.String getAppDataselettoreFlussoRiversamento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreFlussoRiversamento"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel.class;
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
			log.debug("[CpDettaglioFlussoRiversamentoAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [goTosp]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleGoTosp_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("goTosp", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioFlussoRiversamentoAction::handleGoTosp_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleGoTosp_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleGoTosp_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleGoTosp_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleGoTosp_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [esportaFlusso]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleEsportaFlusso_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("esportaFlusso", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioFlussoRiversamentoAction::handleEsportaFlusso_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleEsportaFlusso_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleEsportaFlusso_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleEsportaFlusso_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioFlussoRiversamentoAction::handleEsportaFlusso_CLICKED] returning default result [SUCCESS]");
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
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R1875992074) ENABLED START*/
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
				log.error("[CpDettaglioFlussoRiversamentoAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioFlussoRiversamentoAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"INIZIALE"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeAction_0_on = new String[]{"mnuView",
				"txidentificativopsp", "txidentificativoflusso",
				"txversioneoggetto", "txidentificativounivocoregolamento",
				"identificativoistitutomittente",
				"txidentificativoistitutoricevente", "txnumerototalepagamenti",
				"tximportototalepagamenti", "txdataoraflusso",
				"txdataregolamento", "txdatainserimento", "txdatamodifica",
				"txdenominazionemittente", "txdenominazionericevente",
				"btIndietro", "goTosp", "esportaFlusso"};

		String[] act_onRefresh_resinizialeAction_0_off = new String[]{};

		String[] act_onRefresh_resinizialeAction_0_show = new String[]{
				"mnuView", "txidentificativopsp", "txidentificativoflusso",
				"txversioneoggetto", "txidentificativounivocoregolamento",
				"identificativoistitutomittente",
				"txidentificativoistitutoricevente", "txnumerototalepagamenti",
				"tximportototalepagamenti", "txdataoraflusso",
				"txdataregolamento", "txdatainserimento", "txdatamodifica",
				"txdenominazionemittente", "txdenominazionericevente",
				"btIndietro", "goTosp", "esportaFlusso"};

		String[] act_onRefresh_resinizialeAction_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resinizialeAction_0 = new ScreenStateCommand(
				"cpDettaglioFlussoRiversamento", "INIZIALE",
				act_onRefresh_resinizialeAction_0_on,
				act_onRefresh_resinizialeAction_0_off,
				act_onRefresh_resinizialeAction_0_show,
				act_onRefresh_resinizialeAction_0_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[0] = act_onRefresh_resinizialeAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioFlussoRiversamentoAction",
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

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txidentificativopsp
		UISecConstraint txidentificativopsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativopsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativopsp_constraints = new UISecConstraint[]{txidentificativopsp_defaultVisib_ctr};
		UISecConstraint txidentificativopsp_ctr = new ComplexUISecConstraint(
				txidentificativopsp_constraints);
		allConstraints.put("txidentificativopsp", txidentificativopsp_ctr);

		// constraint fittizio per txidentificativoflusso
		UISecConstraint txidentificativoflusso_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativoflusso",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoflusso_constraints = new UISecConstraint[]{txidentificativoflusso_defaultVisib_ctr};
		UISecConstraint txidentificativoflusso_ctr = new ComplexUISecConstraint(
				txidentificativoflusso_constraints);
		allConstraints
				.put("txidentificativoflusso", txidentificativoflusso_ctr);

		// constraint fittizio per txversioneoggetto
		UISecConstraint txversioneoggetto_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txversioneoggetto",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txversioneoggetto_constraints = new UISecConstraint[]{txversioneoggetto_defaultVisib_ctr};
		UISecConstraint txversioneoggetto_ctr = new ComplexUISecConstraint(
				txversioneoggetto_constraints);
		allConstraints.put("txversioneoggetto", txversioneoggetto_ctr);

		// constraint fittizio per txidentificativounivocoregolamento
		UISecConstraint txidentificativounivocoregolamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativounivocoregolamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativounivocoregolamento_constraints = new UISecConstraint[]{txidentificativounivocoregolamento_defaultVisib_ctr};
		UISecConstraint txidentificativounivocoregolamento_ctr = new ComplexUISecConstraint(
				txidentificativounivocoregolamento_constraints);
		allConstraints.put("txidentificativounivocoregolamento",
				txidentificativounivocoregolamento_ctr);

		// constraint fittizio per identificativoistitutomittente
		UISecConstraint identificativoistitutomittente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"identificativoistitutomittente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoistitutomittente_constraints = new UISecConstraint[]{identificativoistitutomittente_defaultVisib_ctr};
		UISecConstraint identificativoistitutomittente_ctr = new ComplexUISecConstraint(
				identificativoistitutomittente_constraints);
		allConstraints.put("identificativoistitutomittente",
				identificativoistitutomittente_ctr);

		// constraint fittizio per txidentificativoistitutoricevente
		UISecConstraint txidentificativoistitutoricevente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativoistitutoricevente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoistitutoricevente_constraints = new UISecConstraint[]{txidentificativoistitutoricevente_defaultVisib_ctr};
		UISecConstraint txidentificativoistitutoricevente_ctr = new ComplexUISecConstraint(
				txidentificativoistitutoricevente_constraints);
		allConstraints.put("txidentificativoistitutoricevente",
				txidentificativoistitutoricevente_ctr);

		// constraint fittizio per txnumerototalepagamenti
		UISecConstraint txnumerototalepagamenti_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txnumerototalepagamenti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txnumerototalepagamenti_constraints = new UISecConstraint[]{txnumerototalepagamenti_defaultVisib_ctr};
		UISecConstraint txnumerototalepagamenti_ctr = new ComplexUISecConstraint(
				txnumerototalepagamenti_constraints);
		allConstraints.put("txnumerototalepagamenti",
				txnumerototalepagamenti_ctr);

		// constraint fittizio per tximportototalepagamenti
		UISecConstraint tximportototalepagamenti_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"tximportototalepagamenti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tximportototalepagamenti_constraints = new UISecConstraint[]{tximportototalepagamenti_defaultVisib_ctr};
		UISecConstraint tximportototalepagamenti_ctr = new ComplexUISecConstraint(
				tximportototalepagamenti_constraints);
		allConstraints.put("tximportototalepagamenti",
				tximportototalepagamenti_ctr);

		// constraint fittizio per txdataoraflusso
		UISecConstraint txdataoraflusso_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdataoraflusso", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txdataoraflusso_constraints = new UISecConstraint[]{txdataoraflusso_defaultVisib_ctr};
		UISecConstraint txdataoraflusso_ctr = new ComplexUISecConstraint(
				txdataoraflusso_constraints);
		allConstraints.put("txdataoraflusso", txdataoraflusso_ctr);

		// constraint fittizio per txdataregolamento
		UISecConstraint txdataregolamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdataregolamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdataregolamento_constraints = new UISecConstraint[]{txdataregolamento_defaultVisib_ctr};
		UISecConstraint txdataregolamento_ctr = new ComplexUISecConstraint(
				txdataregolamento_constraints);
		allConstraints.put("txdataregolamento", txdataregolamento_ctr);

		// constraint fittizio per txdatainserimento
		UISecConstraint txdatainserimento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdatainserimento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatainserimento_constraints = new UISecConstraint[]{txdatainserimento_defaultVisib_ctr};
		UISecConstraint txdatainserimento_ctr = new ComplexUISecConstraint(
				txdatainserimento_constraints);
		allConstraints.put("txdatainserimento", txdatainserimento_ctr);

		// constraint fittizio per txdatamodifica
		UISecConstraint txdatamodifica_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdatamodifica", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txdatamodifica_constraints = new UISecConstraint[]{txdatamodifica_defaultVisib_ctr};
		UISecConstraint txdatamodifica_ctr = new ComplexUISecConstraint(
				txdatamodifica_constraints);
		allConstraints.put("txdatamodifica", txdatamodifica_ctr);

		// constraint fittizio per txdenominazionemittente
		UISecConstraint txdenominazionemittente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdenominazionemittente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdenominazionemittente_constraints = new UISecConstraint[]{txdenominazionemittente_defaultVisib_ctr};
		UISecConstraint txdenominazionemittente_ctr = new ComplexUISecConstraint(
				txdenominazionemittente_constraints);
		allConstraints.put("txdenominazionemittente",
				txdenominazionemittente_ctr);

		// constraint fittizio per txdenominazionericevente
		UISecConstraint txdenominazionericevente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdenominazionericevente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdenominazionericevente_constraints = new UISecConstraint[]{txdenominazionericevente_defaultVisib_ctr};
		UISecConstraint txdenominazionericevente_ctr = new ComplexUISecConstraint(
				txdenominazionericevente_constraints);
		allConstraints.put("txdenominazionericevente",
				txdenominazionericevente_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento", "btIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultVisib_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);

		// constraint fittizio per goTosp
		UISecConstraint goTosp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento", "goTosp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] goTosp_constraints = new UISecConstraint[]{goTosp_defaultVisib_ctr};
		UISecConstraint goTosp_ctr = new ComplexUISecConstraint(
				goTosp_constraints);
		allConstraints.put("goTosp", goTosp_ctr);

		// constraint fittizio per esportaFlusso
		UISecConstraint esportaFlusso_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"esportaFlusso", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] esportaFlusso_constraints = new UISecConstraint[]{esportaFlusso_defaultVisib_ctr};
		UISecConstraint esportaFlusso_ctr = new ComplexUISecConstraint(
				esportaFlusso_constraints);
		allConstraints.put("esportaFlusso", esportaFlusso_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txidentificativopsp
		UISecConstraint txidentificativopsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativopsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativopsp_constraints = new UISecConstraint[]{txidentificativopsp_defaultOnoff_ctr};
		UISecConstraint txidentificativopsp_ctr = new ComplexUISecConstraint(
				txidentificativopsp_constraints);
		allConstraints.put("txidentificativopsp", txidentificativopsp_ctr);

		// constraint fittizio per txidentificativoflusso
		UISecConstraint txidentificativoflusso_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativoflusso",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoflusso_constraints = new UISecConstraint[]{txidentificativoflusso_defaultOnoff_ctr};
		UISecConstraint txidentificativoflusso_ctr = new ComplexUISecConstraint(
				txidentificativoflusso_constraints);
		allConstraints
				.put("txidentificativoflusso", txidentificativoflusso_ctr);

		// constraint fittizio per txversioneoggetto
		UISecConstraint txversioneoggetto_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txversioneoggetto",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txversioneoggetto_constraints = new UISecConstraint[]{txversioneoggetto_defaultOnoff_ctr};
		UISecConstraint txversioneoggetto_ctr = new ComplexUISecConstraint(
				txversioneoggetto_constraints);
		allConstraints.put("txversioneoggetto", txversioneoggetto_ctr);

		// constraint fittizio per txidentificativounivocoregolamento
		UISecConstraint txidentificativounivocoregolamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativounivocoregolamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativounivocoregolamento_constraints = new UISecConstraint[]{txidentificativounivocoregolamento_defaultOnoff_ctr};
		UISecConstraint txidentificativounivocoregolamento_ctr = new ComplexUISecConstraint(
				txidentificativounivocoregolamento_constraints);
		allConstraints.put("txidentificativounivocoregolamento",
				txidentificativounivocoregolamento_ctr);

		// constraint fittizio per identificativoistitutomittente
		UISecConstraint identificativoistitutomittente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"identificativoistitutomittente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoistitutomittente_constraints = new UISecConstraint[]{identificativoistitutomittente_defaultOnoff_ctr};
		UISecConstraint identificativoistitutomittente_ctr = new ComplexUISecConstraint(
				identificativoistitutomittente_constraints);
		allConstraints.put("identificativoistitutomittente",
				identificativoistitutomittente_ctr);

		// constraint fittizio per txidentificativoistitutoricevente
		UISecConstraint txidentificativoistitutoricevente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txidentificativoistitutoricevente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoistitutoricevente_constraints = new UISecConstraint[]{txidentificativoistitutoricevente_defaultOnoff_ctr};
		UISecConstraint txidentificativoistitutoricevente_ctr = new ComplexUISecConstraint(
				txidentificativoistitutoricevente_constraints);
		allConstraints.put("txidentificativoistitutoricevente",
				txidentificativoistitutoricevente_ctr);

		// constraint fittizio per txnumerototalepagamenti
		UISecConstraint txnumerototalepagamenti_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txnumerototalepagamenti",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txnumerototalepagamenti_constraints = new UISecConstraint[]{txnumerototalepagamenti_defaultOnoff_ctr};
		UISecConstraint txnumerototalepagamenti_ctr = new ComplexUISecConstraint(
				txnumerototalepagamenti_constraints);
		allConstraints.put("txnumerototalepagamenti",
				txnumerototalepagamenti_ctr);

		// constraint fittizio per tximportototalepagamenti
		UISecConstraint tximportototalepagamenti_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"tximportototalepagamenti",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tximportototalepagamenti_constraints = new UISecConstraint[]{tximportototalepagamenti_defaultOnoff_ctr};
		UISecConstraint tximportototalepagamenti_ctr = new ComplexUISecConstraint(
				tximportototalepagamenti_constraints);
		allConstraints.put("tximportototalepagamenti",
				tximportototalepagamenti_ctr);

		// constraint fittizio per txdataoraflusso
		UISecConstraint txdataoraflusso_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdataoraflusso", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txdataoraflusso_constraints = new UISecConstraint[]{txdataoraflusso_defaultOnoff_ctr};
		UISecConstraint txdataoraflusso_ctr = new ComplexUISecConstraint(
				txdataoraflusso_constraints);
		allConstraints.put("txdataoraflusso", txdataoraflusso_ctr);

		// constraint fittizio per txdataregolamento
		UISecConstraint txdataregolamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdataregolamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdataregolamento_constraints = new UISecConstraint[]{txdataregolamento_defaultOnoff_ctr};
		UISecConstraint txdataregolamento_ctr = new ComplexUISecConstraint(
				txdataregolamento_constraints);
		allConstraints.put("txdataregolamento", txdataregolamento_ctr);

		// constraint fittizio per txdatainserimento
		UISecConstraint txdatainserimento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdatainserimento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatainserimento_constraints = new UISecConstraint[]{txdatainserimento_defaultOnoff_ctr};
		UISecConstraint txdatainserimento_ctr = new ComplexUISecConstraint(
				txdatainserimento_constraints);
		allConstraints.put("txdatainserimento", txdatainserimento_ctr);

		// constraint fittizio per txdatamodifica
		UISecConstraint txdatamodifica_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdatamodifica", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txdatamodifica_constraints = new UISecConstraint[]{txdatamodifica_defaultOnoff_ctr};
		UISecConstraint txdatamodifica_ctr = new ComplexUISecConstraint(
				txdatamodifica_constraints);
		allConstraints.put("txdatamodifica", txdatamodifica_ctr);

		// constraint fittizio per txdenominazionemittente
		UISecConstraint txdenominazionemittente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdenominazionemittente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdenominazionemittente_constraints = new UISecConstraint[]{txdenominazionemittente_defaultOnoff_ctr};
		UISecConstraint txdenominazionemittente_ctr = new ComplexUISecConstraint(
				txdenominazionemittente_constraints);
		allConstraints.put("txdenominazionemittente",
				txdenominazionemittente_ctr);

		// constraint fittizio per txdenominazionericevente
		UISecConstraint txdenominazionericevente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"txdenominazionericevente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdenominazionericevente_constraints = new UISecConstraint[]{txdenominazionericevente_defaultOnoff_ctr};
		UISecConstraint txdenominazionericevente_ctr = new ComplexUISecConstraint(
				txdenominazionericevente_constraints);
		allConstraints.put("txdenominazionericevente",
				txdenominazionericevente_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);

		// constraint fittizio per goTosp
		UISecConstraint goTosp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento", "goTosp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] goTosp_constraints = new UISecConstraint[]{goTosp_defaultOnoff_ctr};
		UISecConstraint goTosp_ctr = new ComplexUISecConstraint(
				goTosp_constraints);
		allConstraints.put("goTosp", goTosp_ctr);

		// constraint fittizio per esportaFlusso
		UISecConstraint esportaFlusso_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioFlussoRiversamento",
				"esportaFlusso", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] esportaFlusso_constraints = new UISecConstraint[]{esportaFlusso_defaultOnoff_ctr};
		UISecConstraint esportaFlusso_ctr = new ComplexUISecConstraint(
				esportaFlusso_constraints);
		allConstraints.put("esportaFlusso", esportaFlusso_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] START");

		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioFlussoRiversamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioFlussoRiversamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioFlussoRiversamentoAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioFlussoRiversamento");
		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioFlussoRiversamentoAction::dumpmodel] " + session);
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
		// contiene i comandi del widget goTosp per ogni Ev.H.
		HashMap<String, ICommand> cmds4goTospByEvh = new HashMap<String, ICommand>();

		cmds4goTospByEvh.put("CLICKED", initCommandGoTosp_CLICKED());
		cmdsByWidget.put("goTosp", cmds4goTospByEvh);
		// contiene i comandi del widget esportaFlusso per ogni Ev.H.
		HashMap<String, ICommand> cmds4esportaFlussoByEvh = new HashMap<String, ICommand>();

		cmds4esportaFlussoByEvh.put("CLICKED",
				initCommandEsportaFlusso_CLICKED());
		cmdsByWidget.put("esportaFlusso", cmds4esportaFlussoByEvh);

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
		/// Jump Command begin
		JumpCommand act_actions_clicked_btIndietrostep_0 = new JumpCommand(
				"cpFlussoRiversamento", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btIndietro_1_steps = new ICommand[]{act_actions_clicked_btIndietrostep_0};
		SequenceCommand act_actions_clicked_btIndietro_1 = new SequenceCommand(
				act_actions_clicked_btIndietro_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btIndietro_1;
	}

	private ICommand initCommandGoTosp_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_goTospstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_goTospstep_0 = new ClearAppDataCommand(
				act_actions_clicked_goTospstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_goTospstep_1 = new JumpCommand(
				"cpFlussoSingoloPagamento", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_goTosp_1_steps = new ICommand[]{
				act_actions_clicked_goTospstep_0,
				act_actions_clicked_goTospstep_1};
		SequenceCommand act_actions_clicked_goTosp_1 = new SequenceCommand(
				act_actions_clicked_goTosp_1_steps);
		// SequenceCommand end
		return act_actions_clicked_goTosp_1;
	}

	private ICommand initCommandEsportaFlusso_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4esporta = new String[]{"ESPORTA"};

		ICommand[] actionsForResults4esporta = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_esportaFlussostep_resesportaAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4esporta[0] = act_actions_clicked_esportaFlussostep_resesportaAction_0;

		ExecCommand act_actions_clicked_esportaFlussostep_0 = new ExecCommand(
				resultNames4esporta, actionsForResults4esporta) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.esporta(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioFlussoRiversamentoAction",
							"readOne()", "chiamata verso BackEnd", "esporta");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [esporta]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_esportaFlusso_1_steps = new ICommand[]{act_actions_clicked_esportaFlussostep_0};
		SequenceCommand act_actions_clicked_esportaFlusso_1 = new SequenceCommand(
				act_actions_clicked_esportaFlusso_1_steps);
		// SequenceCommand end
		return act_actions_clicked_esportaFlusso_1;
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
