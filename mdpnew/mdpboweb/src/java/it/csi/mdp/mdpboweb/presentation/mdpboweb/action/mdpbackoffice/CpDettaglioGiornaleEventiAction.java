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
 * CpDettaglioGiornaleEventiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioGiornaleEventiAction extends BaseAction
		implements
			Preparable {

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatagiornaleEventi(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi value) {
		getSession().put("appDatagiornaleEventi", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi getAppDatagiornaleEventi() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi) (getSession()
				.get("appDatagiornaleEventi"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataselettoreIdGiornaleEvento(java.lang.String value) {
		getSession().put("appDataselettoreIdGiornaleEvento", value);
	}

	public java.lang.String getAppDataselettoreIdGiornaleEvento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGiornaleEvento"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGiornaleEventiModel.class;
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
			log.debug("[CpDettaglioGiornaleEventiAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGiornaleEventiAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGiornaleEventiAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGiornaleEventiAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGiornaleEventiAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
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
		/*PROTECTED REGION ID(R-1068645791) ENABLED START*/
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
				log.error("[CpDettaglioGiornaleEventiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioGiornaleEventiAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"OK"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resokActionstep_0_on = new String[]{"mnuView",
				"ptInsertDate", "btIndietro", "ptlastUpdate",
				"ptDataoraevento", "ptIdentificativoDominio", "ptIuv",
				"ptCodicecontesto", "ptIdPsp", "ptTipoVersamento",
				"ptComponente", "ptCategoriaEvento", "ptTipoEvento",
				"ptSottotipoevento", "ptIdentificativoFruitore",
				"ptIdentificativoErogatore",
				"ptIdentificativostazioneintermediariopa", "ptIdIntPsp",
				"ptCanalepagamento", "ptParametrispecificiinterfaccia",
				"ptesito", "ptApplicationId"};

		String[] act_onRefresh_resokActionstep_0_off = new String[]{};

		String[] act_onRefresh_resokActionstep_0_show = new String[]{"mnuView",
				"ptInsertDate", "btIndietro", "ptlastUpdate",
				"ptDataoraevento", "ptIdentificativoDominio", "ptIuv",
				"ptCodicecontesto", "ptIdPsp", "ptTipoVersamento",
				"ptComponente", "ptCategoriaEvento", "ptTipoEvento",
				"ptSottotipoevento", "ptIdentificativoFruitore",
				"ptIdentificativoErogatore",
				"ptIdentificativostazioneintermediariopa", "ptIdIntPsp",
				"ptCanalepagamento", "ptParametrispecificiinterfaccia",
				"ptesito", "ptApplicationId"};

		String[] act_onRefresh_resokActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resokActionstep_0 = new ScreenStateCommand(
				"cpDettaglioGiornaleEventi", "INIZIALE",
				act_onRefresh_resokActionstep_0_on,
				act_onRefresh_resokActionstep_0_off,
				act_onRefresh_resokActionstep_0_show,
				act_onRefresh_resokActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resokAction_0_steps = new ICommand[]{act_onRefresh_resokActionstep_0};
		SequenceCommand act_onRefresh_resokAction_0 = new SequenceCommand(
				act_onRefresh_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resokAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGiornaleEventiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGiornaleEventiAction",
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

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptInsertDate
		UISecConstraint ptInsertDate_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptInsertDate",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptInsertDate_constraints = new UISecConstraint[]{ptInsertDate_defaultVisib_ctr};
		UISecConstraint ptInsertDate_ctr = new ComplexUISecConstraint(
				ptInsertDate_constraints);
		allConstraints.put("ptInsertDate", ptInsertDate_ctr);

		// constraint fittizio per ptlastUpdate
		UISecConstraint ptlastUpdate_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptlastUpdate",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptlastUpdate_constraints = new UISecConstraint[]{ptlastUpdate_defaultVisib_ctr};
		UISecConstraint ptlastUpdate_ctr = new ComplexUISecConstraint(
				ptlastUpdate_constraints);
		allConstraints.put("ptlastUpdate", ptlastUpdate_ctr);

		// constraint fittizio per ptDataoraevento
		UISecConstraint ptDataoraevento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptDataoraevento", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptDataoraevento_constraints = new UISecConstraint[]{ptDataoraevento_defaultVisib_ctr};
		UISecConstraint ptDataoraevento_ctr = new ComplexUISecConstraint(
				ptDataoraevento_constraints);
		allConstraints.put("ptDataoraevento", ptDataoraevento_ctr);

		// constraint fittizio per ptIdentificativoDominio
		UISecConstraint ptIdentificativoDominio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativoDominio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativoDominio_constraints = new UISecConstraint[]{ptIdentificativoDominio_defaultVisib_ctr};
		UISecConstraint ptIdentificativoDominio_ctr = new ComplexUISecConstraint(
				ptIdentificativoDominio_constraints);
		allConstraints.put("ptIdentificativoDominio",
				ptIdentificativoDominio_ctr);

		// constraint fittizio per ptIuv
		UISecConstraint ptIuv_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptIuv",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIuv_constraints = new UISecConstraint[]{ptIuv_defaultVisib_ctr};
		UISecConstraint ptIuv_ctr = new ComplexUISecConstraint(
				ptIuv_constraints);
		allConstraints.put("ptIuv", ptIuv_ctr);

		// constraint fittizio per ptCodicecontesto
		UISecConstraint ptCodicecontesto_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptCodicecontesto", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptCodicecontesto_constraints = new UISecConstraint[]{ptCodicecontesto_defaultVisib_ctr};
		UISecConstraint ptCodicecontesto_ctr = new ComplexUISecConstraint(
				ptCodicecontesto_constraints);
		allConstraints.put("ptCodicecontesto", ptCodicecontesto_ctr);

		// constraint fittizio per ptIdPsp
		UISecConstraint ptIdPsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptIdPsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdPsp_constraints = new UISecConstraint[]{ptIdPsp_defaultVisib_ctr};
		UISecConstraint ptIdPsp_ctr = new ComplexUISecConstraint(
				ptIdPsp_constraints);
		allConstraints.put("ptIdPsp", ptIdPsp_ctr);

		// constraint fittizio per ptTipoVersamento
		UISecConstraint ptTipoVersamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptTipoVersamento", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptTipoVersamento_constraints = new UISecConstraint[]{ptTipoVersamento_defaultVisib_ctr};
		UISecConstraint ptTipoVersamento_ctr = new ComplexUISecConstraint(
				ptTipoVersamento_constraints);
		allConstraints.put("ptTipoVersamento", ptTipoVersamento_ctr);

		// constraint fittizio per ptComponente
		UISecConstraint ptComponente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptComponente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptComponente_constraints = new UISecConstraint[]{ptComponente_defaultVisib_ctr};
		UISecConstraint ptComponente_ctr = new ComplexUISecConstraint(
				ptComponente_constraints);
		allConstraints.put("ptComponente", ptComponente_ctr);

		// constraint fittizio per ptCategoriaEvento
		UISecConstraint ptCategoriaEvento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptCategoriaEvento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptCategoriaEvento_constraints = new UISecConstraint[]{ptCategoriaEvento_defaultVisib_ctr};
		UISecConstraint ptCategoriaEvento_ctr = new ComplexUISecConstraint(
				ptCategoriaEvento_constraints);
		allConstraints.put("ptCategoriaEvento", ptCategoriaEvento_ctr);

		// constraint fittizio per ptTipoEvento
		UISecConstraint ptTipoEvento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptTipoEvento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptTipoEvento_constraints = new UISecConstraint[]{ptTipoEvento_defaultVisib_ctr};
		UISecConstraint ptTipoEvento_ctr = new ComplexUISecConstraint(
				ptTipoEvento_constraints);
		allConstraints.put("ptTipoEvento", ptTipoEvento_ctr);

		// constraint fittizio per ptSottotipoevento
		UISecConstraint ptSottotipoevento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptSottotipoevento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptSottotipoevento_constraints = new UISecConstraint[]{ptSottotipoevento_defaultVisib_ctr};
		UISecConstraint ptSottotipoevento_ctr = new ComplexUISecConstraint(
				ptSottotipoevento_constraints);
		allConstraints.put("ptSottotipoevento", ptSottotipoevento_ctr);

		// constraint fittizio per ptIdentificativoFruitore
		UISecConstraint ptIdentificativoFruitore_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativoFruitore",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativoFruitore_constraints = new UISecConstraint[]{ptIdentificativoFruitore_defaultVisib_ctr};
		UISecConstraint ptIdentificativoFruitore_ctr = new ComplexUISecConstraint(
				ptIdentificativoFruitore_constraints);
		allConstraints.put("ptIdentificativoFruitore",
				ptIdentificativoFruitore_ctr);

		// constraint fittizio per ptIdentificativoErogatore
		UISecConstraint ptIdentificativoErogatore_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativoErogatore",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativoErogatore_constraints = new UISecConstraint[]{ptIdentificativoErogatore_defaultVisib_ctr};
		UISecConstraint ptIdentificativoErogatore_ctr = new ComplexUISecConstraint(
				ptIdentificativoErogatore_constraints);
		allConstraints.put("ptIdentificativoErogatore",
				ptIdentificativoErogatore_ctr);

		// constraint fittizio per ptIdentificativostazioneintermediariopa
		UISecConstraint ptIdentificativostazioneintermediariopa_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativostazioneintermediariopa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativostazioneintermediariopa_constraints = new UISecConstraint[]{ptIdentificativostazioneintermediariopa_defaultVisib_ctr};
		UISecConstraint ptIdentificativostazioneintermediariopa_ctr = new ComplexUISecConstraint(
				ptIdentificativostazioneintermediariopa_constraints);
		allConstraints.put("ptIdentificativostazioneintermediariopa",
				ptIdentificativostazioneintermediariopa_ctr);

		// constraint fittizio per ptIdIntPsp
		UISecConstraint ptIdIntPsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptIdIntPsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdIntPsp_constraints = new UISecConstraint[]{ptIdIntPsp_defaultVisib_ctr};
		UISecConstraint ptIdIntPsp_ctr = new ComplexUISecConstraint(
				ptIdIntPsp_constraints);
		allConstraints.put("ptIdIntPsp", ptIdIntPsp_ctr);

		// constraint fittizio per ptCanalepagamento
		UISecConstraint ptCanalepagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptCanalepagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptCanalepagamento_constraints = new UISecConstraint[]{ptCanalepagamento_defaultVisib_ctr};
		UISecConstraint ptCanalepagamento_ctr = new ComplexUISecConstraint(
				ptCanalepagamento_constraints);
		allConstraints.put("ptCanalepagamento", ptCanalepagamento_ctr);

		// constraint fittizio per ptParametrispecificiinterfaccia
		UISecConstraint ptParametrispecificiinterfaccia_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptParametrispecificiinterfaccia",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptParametrispecificiinterfaccia_constraints = new UISecConstraint[]{ptParametrispecificiinterfaccia_defaultVisib_ctr};
		UISecConstraint ptParametrispecificiinterfaccia_ctr = new ComplexUISecConstraint(
				ptParametrispecificiinterfaccia_constraints);
		allConstraints.put("ptParametrispecificiinterfaccia",
				ptParametrispecificiinterfaccia_ctr);

		// constraint fittizio per ptesito
		UISecConstraint ptesito_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptesito",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptesito_constraints = new UISecConstraint[]{ptesito_defaultVisib_ctr};
		UISecConstraint ptesito_ctr = new ComplexUISecConstraint(
				ptesito_constraints);
		allConstraints.put("ptesito", ptesito_ctr);

		// constraint fittizio per ptApplicationId
		UISecConstraint ptApplicationId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptApplicationId", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptApplicationId_constraints = new UISecConstraint[]{ptApplicationId_defaultVisib_ctr};
		UISecConstraint ptApplicationId_ctr = new ComplexUISecConstraint(
				ptApplicationId_constraints);
		allConstraints.put("ptApplicationId", ptApplicationId_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "btIndietro",
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

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptInsertDate
		UISecConstraint ptInsertDate_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptInsertDate",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptInsertDate_constraints = new UISecConstraint[]{ptInsertDate_defaultOnoff_ctr};
		UISecConstraint ptInsertDate_ctr = new ComplexUISecConstraint(
				ptInsertDate_constraints);
		allConstraints.put("ptInsertDate", ptInsertDate_ctr);

		// constraint fittizio per ptlastUpdate
		UISecConstraint ptlastUpdate_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptlastUpdate",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptlastUpdate_constraints = new UISecConstraint[]{ptlastUpdate_defaultOnoff_ctr};
		UISecConstraint ptlastUpdate_ctr = new ComplexUISecConstraint(
				ptlastUpdate_constraints);
		allConstraints.put("ptlastUpdate", ptlastUpdate_ctr);

		// constraint fittizio per ptDataoraevento
		UISecConstraint ptDataoraevento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptDataoraevento", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptDataoraevento_constraints = new UISecConstraint[]{ptDataoraevento_defaultOnoff_ctr};
		UISecConstraint ptDataoraevento_ctr = new ComplexUISecConstraint(
				ptDataoraevento_constraints);
		allConstraints.put("ptDataoraevento", ptDataoraevento_ctr);

		// constraint fittizio per ptIdentificativoDominio
		UISecConstraint ptIdentificativoDominio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativoDominio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativoDominio_constraints = new UISecConstraint[]{ptIdentificativoDominio_defaultOnoff_ctr};
		UISecConstraint ptIdentificativoDominio_ctr = new ComplexUISecConstraint(
				ptIdentificativoDominio_constraints);
		allConstraints.put("ptIdentificativoDominio",
				ptIdentificativoDominio_ctr);

		// constraint fittizio per ptIuv
		UISecConstraint ptIuv_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptIuv",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIuv_constraints = new UISecConstraint[]{ptIuv_defaultOnoff_ctr};
		UISecConstraint ptIuv_ctr = new ComplexUISecConstraint(
				ptIuv_constraints);
		allConstraints.put("ptIuv", ptIuv_ctr);

		// constraint fittizio per ptCodicecontesto
		UISecConstraint ptCodicecontesto_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptCodicecontesto", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptCodicecontesto_constraints = new UISecConstraint[]{ptCodicecontesto_defaultOnoff_ctr};
		UISecConstraint ptCodicecontesto_ctr = new ComplexUISecConstraint(
				ptCodicecontesto_constraints);
		allConstraints.put("ptCodicecontesto", ptCodicecontesto_ctr);

		// constraint fittizio per ptIdPsp
		UISecConstraint ptIdPsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptIdPsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdPsp_constraints = new UISecConstraint[]{ptIdPsp_defaultOnoff_ctr};
		UISecConstraint ptIdPsp_ctr = new ComplexUISecConstraint(
				ptIdPsp_constraints);
		allConstraints.put("ptIdPsp", ptIdPsp_ctr);

		// constraint fittizio per ptTipoVersamento
		UISecConstraint ptTipoVersamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptTipoVersamento", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptTipoVersamento_constraints = new UISecConstraint[]{ptTipoVersamento_defaultOnoff_ctr};
		UISecConstraint ptTipoVersamento_ctr = new ComplexUISecConstraint(
				ptTipoVersamento_constraints);
		allConstraints.put("ptTipoVersamento", ptTipoVersamento_ctr);

		// constraint fittizio per ptComponente
		UISecConstraint ptComponente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptComponente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptComponente_constraints = new UISecConstraint[]{ptComponente_defaultOnoff_ctr};
		UISecConstraint ptComponente_ctr = new ComplexUISecConstraint(
				ptComponente_constraints);
		allConstraints.put("ptComponente", ptComponente_ctr);

		// constraint fittizio per ptCategoriaEvento
		UISecConstraint ptCategoriaEvento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptCategoriaEvento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptCategoriaEvento_constraints = new UISecConstraint[]{ptCategoriaEvento_defaultOnoff_ctr};
		UISecConstraint ptCategoriaEvento_ctr = new ComplexUISecConstraint(
				ptCategoriaEvento_constraints);
		allConstraints.put("ptCategoriaEvento", ptCategoriaEvento_ctr);

		// constraint fittizio per ptTipoEvento
		UISecConstraint ptTipoEvento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptTipoEvento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptTipoEvento_constraints = new UISecConstraint[]{ptTipoEvento_defaultOnoff_ctr};
		UISecConstraint ptTipoEvento_ctr = new ComplexUISecConstraint(
				ptTipoEvento_constraints);
		allConstraints.put("ptTipoEvento", ptTipoEvento_ctr);

		// constraint fittizio per ptSottotipoevento
		UISecConstraint ptSottotipoevento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptSottotipoevento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptSottotipoevento_constraints = new UISecConstraint[]{ptSottotipoevento_defaultOnoff_ctr};
		UISecConstraint ptSottotipoevento_ctr = new ComplexUISecConstraint(
				ptSottotipoevento_constraints);
		allConstraints.put("ptSottotipoevento", ptSottotipoevento_ctr);

		// constraint fittizio per ptIdentificativoFruitore
		UISecConstraint ptIdentificativoFruitore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativoFruitore",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativoFruitore_constraints = new UISecConstraint[]{ptIdentificativoFruitore_defaultOnoff_ctr};
		UISecConstraint ptIdentificativoFruitore_ctr = new ComplexUISecConstraint(
				ptIdentificativoFruitore_constraints);
		allConstraints.put("ptIdentificativoFruitore",
				ptIdentificativoFruitore_ctr);

		// constraint fittizio per ptIdentificativoErogatore
		UISecConstraint ptIdentificativoErogatore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativoErogatore",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativoErogatore_constraints = new UISecConstraint[]{ptIdentificativoErogatore_defaultOnoff_ctr};
		UISecConstraint ptIdentificativoErogatore_ctr = new ComplexUISecConstraint(
				ptIdentificativoErogatore_constraints);
		allConstraints.put("ptIdentificativoErogatore",
				ptIdentificativoErogatore_ctr);

		// constraint fittizio per ptIdentificativostazioneintermediariopa
		UISecConstraint ptIdentificativostazioneintermediariopa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptIdentificativostazioneintermediariopa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdentificativostazioneintermediariopa_constraints = new UISecConstraint[]{ptIdentificativostazioneintermediariopa_defaultOnoff_ctr};
		UISecConstraint ptIdentificativostazioneintermediariopa_ctr = new ComplexUISecConstraint(
				ptIdentificativostazioneintermediariopa_constraints);
		allConstraints.put("ptIdentificativostazioneintermediariopa",
				ptIdentificativostazioneintermediariopa_ctr);

		// constraint fittizio per ptIdIntPsp
		UISecConstraint ptIdIntPsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptIdIntPsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptIdIntPsp_constraints = new UISecConstraint[]{ptIdIntPsp_defaultOnoff_ctr};
		UISecConstraint ptIdIntPsp_ctr = new ComplexUISecConstraint(
				ptIdIntPsp_constraints);
		allConstraints.put("ptIdIntPsp", ptIdIntPsp_ctr);

		// constraint fittizio per ptCanalepagamento
		UISecConstraint ptCanalepagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptCanalepagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptCanalepagamento_constraints = new UISecConstraint[]{ptCanalepagamento_defaultOnoff_ctr};
		UISecConstraint ptCanalepagamento_ctr = new ComplexUISecConstraint(
				ptCanalepagamento_constraints);
		allConstraints.put("ptCanalepagamento", ptCanalepagamento_ctr);

		// constraint fittizio per ptParametrispecificiinterfaccia
		UISecConstraint ptParametrispecificiinterfaccia_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptParametrispecificiinterfaccia",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptParametrispecificiinterfaccia_constraints = new UISecConstraint[]{ptParametrispecificiinterfaccia_defaultOnoff_ctr};
		UISecConstraint ptParametrispecificiinterfaccia_ctr = new ComplexUISecConstraint(
				ptParametrispecificiinterfaccia_constraints);
		allConstraints.put("ptParametrispecificiinterfaccia",
				ptParametrispecificiinterfaccia_ctr);

		// constraint fittizio per ptesito
		UISecConstraint ptesito_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "ptesito",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptesito_constraints = new UISecConstraint[]{ptesito_defaultOnoff_ctr};
		UISecConstraint ptesito_ctr = new ComplexUISecConstraint(
				ptesito_constraints);
		allConstraints.put("ptesito", ptesito_ctr);

		// constraint fittizio per ptApplicationId
		UISecConstraint ptApplicationId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi",
				"ptApplicationId", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptApplicationId_constraints = new UISecConstraint[]{ptApplicationId_defaultOnoff_ctr};
		UISecConstraint ptApplicationId_ctr = new ComplexUISecConstraint(
				ptApplicationId_constraints);
		allConstraints.put("ptApplicationId", ptApplicationId_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGiornaleEventi", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] START");

		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioGiornaleEventiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioGiornaleEventiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioGiornaleEventiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioGiornaleEventi");
		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioGiornaleEventiAction::dumpmodel] " + session);
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
		String[] resultNames4indietro = new String[]{"OK"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btIndietro_resokActionstep_0 = new JumpCommand(
				"cpGestioneGiornaleEventi", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btIndietro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btIndietro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btIndietro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btIndietro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4indietro[0] = act_actions_clicked_btIndietro_resokAction_0;

		ExecCommand act_actions_clicked_btIndietro_1 = new ExecCommand(
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGiornaleEventiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGiornaleEventiAction",
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
