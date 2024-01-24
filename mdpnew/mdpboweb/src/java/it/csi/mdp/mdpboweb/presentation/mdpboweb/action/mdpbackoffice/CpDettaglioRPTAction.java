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
 * CpDettaglioRPTAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioRPTAction extends BaseAction implements Preparable {

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

	public void setAppDataselettoreIdRPT(java.lang.String value) {
		getSession().put("appDataselettoreIdRPT", value);
	}

	public java.lang.String getAppDataselettoreIdRPT() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRPT"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDatarichiestaPagamentoTelematico(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico value) {
		getSession().put("appDatarichiestaPagamentoTelematico", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico getAppDatarichiestaPagamentoTelematico() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.RichiestaPagamentoTelematico) (getSession()
				.get("appDatarichiestaPagamentoTelematico"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioRPTModel.class;
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
			log.debug("[CpDettaglioRPTAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioRPTAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioRPTAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioRPTAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioRPTAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
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
		/*PROTECTED REGION ID(R-1117409977) ENABLED START*/
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
				log.error("[CpDettaglioRPTAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioRPTAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"iniziale"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeActionstep_0_on = new String[]{
				"mnuView", "id", "applicationId", "transactionId",
				"insertDate", "lastUpdate", "authSoggetto", "dataMsgRichiesta",
				"idCanale", "idIntermPsp", "idMsgRichiesta", "idPsp",
				"identificativoDominio", "identificativoIntermediarioPa",
				"identificativoStazioneIntermediarioPa", "iuv", "idStatiRpt",
				"descStatiRpt", "btIndietro"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "id", "applicationId", "transactionId",
				"insertDate", "lastUpdate", "authSoggetto", "dataMsgRichiesta",
				"idCanale", "idIntermPsp", "idMsgRichiesta", "idPsp",
				"identificativoDominio", "identificativoIntermediarioPa",
				"identificativoStazioneIntermediarioPa", "iuv", "idStatiRpt",
				"descStatiRpt", "btIndietro"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpDettaglioRPT", "INIZIALE",
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioRPTAction", "readOne()",
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

		new DummyUISecConstraint("cpDettaglioRPT", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per id
		UISecConstraint id_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "id",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] id_constraints = new UISecConstraint[]{id_defaultVisib_ctr};
		UISecConstraint id_ctr = new ComplexUISecConstraint(id_constraints);
		allConstraints.put("id", id_ctr);

		// constraint fittizio per applicationId
		UISecConstraint applicationId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "applicationId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] applicationId_constraints = new UISecConstraint[]{applicationId_defaultVisib_ctr};
		UISecConstraint applicationId_ctr = new ComplexUISecConstraint(
				applicationId_constraints);
		allConstraints.put("applicationId", applicationId_ctr);

		// constraint fittizio per transactionId
		UISecConstraint transactionId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "transactionId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] transactionId_constraints = new UISecConstraint[]{transactionId_defaultVisib_ctr};
		UISecConstraint transactionId_ctr = new ComplexUISecConstraint(
				transactionId_constraints);
		allConstraints.put("transactionId", transactionId_ctr);

		// constraint fittizio per insertDate
		UISecConstraint insertDate_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "insertDate",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDate_constraints = new UISecConstraint[]{insertDate_defaultVisib_ctr};
		UISecConstraint insertDate_ctr = new ComplexUISecConstraint(
				insertDate_constraints);
		allConstraints.put("insertDate", insertDate_ctr);

		// constraint fittizio per lastUpdate
		UISecConstraint lastUpdate_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "lastUpdate",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdate_constraints = new UISecConstraint[]{lastUpdate_defaultVisib_ctr};
		UISecConstraint lastUpdate_ctr = new ComplexUISecConstraint(
				lastUpdate_constraints);
		allConstraints.put("lastUpdate", lastUpdate_ctr);

		// constraint fittizio per authSoggetto
		UISecConstraint authSoggetto_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "authSoggetto",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] authSoggetto_constraints = new UISecConstraint[]{authSoggetto_defaultVisib_ctr};
		UISecConstraint authSoggetto_ctr = new ComplexUISecConstraint(
				authSoggetto_constraints);
		allConstraints.put("authSoggetto", authSoggetto_ctr);

		// constraint fittizio per dataMsgRichiesta
		UISecConstraint dataMsgRichiesta_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "dataMsgRichiesta",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] dataMsgRichiesta_constraints = new UISecConstraint[]{dataMsgRichiesta_defaultVisib_ctr};
		UISecConstraint dataMsgRichiesta_ctr = new ComplexUISecConstraint(
				dataMsgRichiesta_constraints);
		allConstraints.put("dataMsgRichiesta", dataMsgRichiesta_ctr);

		// constraint fittizio per idCanale
		UISecConstraint idCanale_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idCanale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idCanale_constraints = new UISecConstraint[]{idCanale_defaultVisib_ctr};
		UISecConstraint idCanale_ctr = new ComplexUISecConstraint(
				idCanale_constraints);
		allConstraints.put("idCanale", idCanale_ctr);

		// constraint fittizio per idIntermPsp
		UISecConstraint idIntermPsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idIntermPsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idIntermPsp_constraints = new UISecConstraint[]{idIntermPsp_defaultVisib_ctr};
		UISecConstraint idIntermPsp_ctr = new ComplexUISecConstraint(
				idIntermPsp_constraints);
		allConstraints.put("idIntermPsp", idIntermPsp_ctr);

		// constraint fittizio per idMsgRichiesta
		UISecConstraint idMsgRichiesta_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idMsgRichiesta",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idMsgRichiesta_constraints = new UISecConstraint[]{idMsgRichiesta_defaultVisib_ctr};
		UISecConstraint idMsgRichiesta_ctr = new ComplexUISecConstraint(
				idMsgRichiesta_constraints);
		allConstraints.put("idMsgRichiesta", idMsgRichiesta_ctr);

		// constraint fittizio per idPsp
		UISecConstraint idPsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idPsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idPsp_constraints = new UISecConstraint[]{idPsp_defaultVisib_ctr};
		UISecConstraint idPsp_ctr = new ComplexUISecConstraint(
				idPsp_constraints);
		allConstraints.put("idPsp", idPsp_ctr);

		// constraint fittizio per identificativoDominio
		UISecConstraint identificativoDominio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "identificativoDominio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoDominio_constraints = new UISecConstraint[]{identificativoDominio_defaultVisib_ctr};
		UISecConstraint identificativoDominio_ctr = new ComplexUISecConstraint(
				identificativoDominio_constraints);
		allConstraints.put("identificativoDominio", identificativoDominio_ctr);

		// constraint fittizio per identificativoIntermediarioPa
		UISecConstraint identificativoIntermediarioPa_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT",
				"identificativoIntermediarioPa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoIntermediarioPa_constraints = new UISecConstraint[]{identificativoIntermediarioPa_defaultVisib_ctr};
		UISecConstraint identificativoIntermediarioPa_ctr = new ComplexUISecConstraint(
				identificativoIntermediarioPa_constraints);
		allConstraints.put("identificativoIntermediarioPa",
				identificativoIntermediarioPa_ctr);

		// constraint fittizio per identificativoStazioneIntermediarioPa
		UISecConstraint identificativoStazioneIntermediarioPa_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT",
				"identificativoStazioneIntermediarioPa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoStazioneIntermediarioPa_constraints = new UISecConstraint[]{identificativoStazioneIntermediarioPa_defaultVisib_ctr};
		UISecConstraint identificativoStazioneIntermediarioPa_ctr = new ComplexUISecConstraint(
				identificativoStazioneIntermediarioPa_constraints);
		allConstraints.put("identificativoStazioneIntermediarioPa",
				identificativoStazioneIntermediarioPa_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "iuv",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultVisib_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per idStatiRpt
		UISecConstraint idStatiRpt_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idStatiRpt",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idStatiRpt_constraints = new UISecConstraint[]{idStatiRpt_defaultVisib_ctr};
		UISecConstraint idStatiRpt_ctr = new ComplexUISecConstraint(
				idStatiRpt_constraints);
		allConstraints.put("idStatiRpt", idStatiRpt_ctr);

		// constraint fittizio per descStatiRpt
		UISecConstraint descStatiRpt_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "descStatiRpt",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] descStatiRpt_constraints = new UISecConstraint[]{descStatiRpt_defaultVisib_ctr};
		UISecConstraint descStatiRpt_ctr = new ComplexUISecConstraint(
				descStatiRpt_constraints);
		allConstraints.put("descStatiRpt", descStatiRpt_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "btIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultVisib_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per id
		UISecConstraint id_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "id",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] id_constraints = new UISecConstraint[]{id_defaultOnoff_ctr};
		UISecConstraint id_ctr = new ComplexUISecConstraint(id_constraints);
		allConstraints.put("id", id_ctr);

		// constraint fittizio per applicationId
		UISecConstraint applicationId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "applicationId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] applicationId_constraints = new UISecConstraint[]{applicationId_defaultOnoff_ctr};
		UISecConstraint applicationId_ctr = new ComplexUISecConstraint(
				applicationId_constraints);
		allConstraints.put("applicationId", applicationId_ctr);

		// constraint fittizio per transactionId
		UISecConstraint transactionId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "transactionId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] transactionId_constraints = new UISecConstraint[]{transactionId_defaultOnoff_ctr};
		UISecConstraint transactionId_ctr = new ComplexUISecConstraint(
				transactionId_constraints);
		allConstraints.put("transactionId", transactionId_ctr);

		// constraint fittizio per insertDate
		UISecConstraint insertDate_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "insertDate",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] insertDate_constraints = new UISecConstraint[]{insertDate_defaultOnoff_ctr};
		UISecConstraint insertDate_ctr = new ComplexUISecConstraint(
				insertDate_constraints);
		allConstraints.put("insertDate", insertDate_ctr);

		// constraint fittizio per lastUpdate
		UISecConstraint lastUpdate_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "lastUpdate",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] lastUpdate_constraints = new UISecConstraint[]{lastUpdate_defaultOnoff_ctr};
		UISecConstraint lastUpdate_ctr = new ComplexUISecConstraint(
				lastUpdate_constraints);
		allConstraints.put("lastUpdate", lastUpdate_ctr);

		// constraint fittizio per authSoggetto
		UISecConstraint authSoggetto_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "authSoggetto",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] authSoggetto_constraints = new UISecConstraint[]{authSoggetto_defaultOnoff_ctr};
		UISecConstraint authSoggetto_ctr = new ComplexUISecConstraint(
				authSoggetto_constraints);
		allConstraints.put("authSoggetto", authSoggetto_ctr);

		// constraint fittizio per dataMsgRichiesta
		UISecConstraint dataMsgRichiesta_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "dataMsgRichiesta",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] dataMsgRichiesta_constraints = new UISecConstraint[]{dataMsgRichiesta_defaultOnoff_ctr};
		UISecConstraint dataMsgRichiesta_ctr = new ComplexUISecConstraint(
				dataMsgRichiesta_constraints);
		allConstraints.put("dataMsgRichiesta", dataMsgRichiesta_ctr);

		// constraint fittizio per idCanale
		UISecConstraint idCanale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idCanale",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idCanale_constraints = new UISecConstraint[]{idCanale_defaultOnoff_ctr};
		UISecConstraint idCanale_ctr = new ComplexUISecConstraint(
				idCanale_constraints);
		allConstraints.put("idCanale", idCanale_ctr);

		// constraint fittizio per idIntermPsp
		UISecConstraint idIntermPsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idIntermPsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idIntermPsp_constraints = new UISecConstraint[]{idIntermPsp_defaultOnoff_ctr};
		UISecConstraint idIntermPsp_ctr = new ComplexUISecConstraint(
				idIntermPsp_constraints);
		allConstraints.put("idIntermPsp", idIntermPsp_ctr);

		// constraint fittizio per idMsgRichiesta
		UISecConstraint idMsgRichiesta_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idMsgRichiesta",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idMsgRichiesta_constraints = new UISecConstraint[]{idMsgRichiesta_defaultOnoff_ctr};
		UISecConstraint idMsgRichiesta_ctr = new ComplexUISecConstraint(
				idMsgRichiesta_constraints);
		allConstraints.put("idMsgRichiesta", idMsgRichiesta_ctr);

		// constraint fittizio per idPsp
		UISecConstraint idPsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idPsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idPsp_constraints = new UISecConstraint[]{idPsp_defaultOnoff_ctr};
		UISecConstraint idPsp_ctr = new ComplexUISecConstraint(
				idPsp_constraints);
		allConstraints.put("idPsp", idPsp_ctr);

		// constraint fittizio per identificativoDominio
		UISecConstraint identificativoDominio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "identificativoDominio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoDominio_constraints = new UISecConstraint[]{identificativoDominio_defaultOnoff_ctr};
		UISecConstraint identificativoDominio_ctr = new ComplexUISecConstraint(
				identificativoDominio_constraints);
		allConstraints.put("identificativoDominio", identificativoDominio_ctr);

		// constraint fittizio per identificativoIntermediarioPa
		UISecConstraint identificativoIntermediarioPa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT",
				"identificativoIntermediarioPa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoIntermediarioPa_constraints = new UISecConstraint[]{identificativoIntermediarioPa_defaultOnoff_ctr};
		UISecConstraint identificativoIntermediarioPa_ctr = new ComplexUISecConstraint(
				identificativoIntermediarioPa_constraints);
		allConstraints.put("identificativoIntermediarioPa",
				identificativoIntermediarioPa_ctr);

		// constraint fittizio per identificativoStazioneIntermediarioPa
		UISecConstraint identificativoStazioneIntermediarioPa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT",
				"identificativoStazioneIntermediarioPa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoStazioneIntermediarioPa_constraints = new UISecConstraint[]{identificativoStazioneIntermediarioPa_defaultOnoff_ctr};
		UISecConstraint identificativoStazioneIntermediarioPa_ctr = new ComplexUISecConstraint(
				identificativoStazioneIntermediarioPa_constraints);
		allConstraints.put("identificativoStazioneIntermediarioPa",
				identificativoStazioneIntermediarioPa_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "iuv",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultOnoff_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per idStatiRpt
		UISecConstraint idStatiRpt_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "idStatiRpt",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idStatiRpt_constraints = new UISecConstraint[]{idStatiRpt_defaultOnoff_ctr};
		UISecConstraint idStatiRpt_ctr = new ComplexUISecConstraint(
				idStatiRpt_constraints);
		allConstraints.put("idStatiRpt", idStatiRpt_ctr);

		// constraint fittizio per descStatiRpt
		UISecConstraint descStatiRpt_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "descStatiRpt",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] descStatiRpt_constraints = new UISecConstraint[]{descStatiRpt_defaultOnoff_ctr};
		UISecConstraint descStatiRpt_ctr = new ComplexUISecConstraint(
				descStatiRpt_constraints);
		allConstraints.put("descStatiRpt", descStatiRpt_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioRPT", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioRPTAction::dumpmodel] START");

		log.debug("[CpDettaglioRPTAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioRPTAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioRPTAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioRPTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioRPTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioRPTAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpDettaglioRPTAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioRPT");
		log.debug("[CpDettaglioRPTAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpDettaglioRPTAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioRPTAction::dumpmodel] " + session);
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
		// ExecCommand begin
		String[] resultNames4goBack = new String[]{"OK"};

		ICommand[] actionsForResults4goBack = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btIndietro_resokAction_0 = new JumpCommand(
				"cpGestioneRPT", null, false);
		/// Jump Command end
		actionsForResults4goBack[0] = act_actions_clicked_btIndietro_resokAction_0;

		ExecCommand act_actions_clicked_btIndietro_1 = new ExecCommand(
				resultNames4goBack, actionsForResults4goBack) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.goBack((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioRPTModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioRPTAction", "readOne()",
							"chiamata verso BackEnd", "goBack");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [goBack]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
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
