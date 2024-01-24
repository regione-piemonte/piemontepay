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
 * CpDettaglioInformativaPspAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioInformativaPspAction extends BaseAction
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

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatainformativePsp(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp value) {
		getSession().put("appDatainformativePsp", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp getAppDatainformativePsp() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp) (getSession()
				.get("appDatainformativePsp"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioInformativaPspModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btBck]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtBck_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btBck", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioInformativaPspAction::handleBtBck_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioInformativaPspAction::handleBtBck_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioInformativaPspAction::handleBtBck_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioInformativaPspAction::handleBtBck_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioInformativaPspAction::handleBtBck_CLICKED] returning default result [SUCCESS]");
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
		/*PROTECTED REGION ID(R-1338436948) ENABLED START*/
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
				log.error("[CpDettaglioInformativaPspAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioInformativaPspAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Iniziale"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeActionstep_0_on = new String[]{
				"mnuView", "txidinformativapsp", "txidentificativoflusso",
				"txidentificativopsp", "txragionesociale",
				"txdatapubblicazione", "txdatainiziovalidita",
				"txurlinformazionipsp", "txstornopagamento",
				"txidentificativointermediario", "txidentificativocanale",
				"txtipoversamento", "txmodellopagamento", "txpriorita",
				"txdisponibilitaservizio", "txdescrizioneservizio",
				"txcondizionieconomichemassime", "txurlinformazionicanale",
				"datainserimento", "txordinamento", "txstatoinserimento",
				"txorigine", "btBck"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "txidinformativapsp", "txidentificativoflusso",
				"txidentificativopsp", "txragionesociale",
				"txdatapubblicazione", "txdatainiziovalidita",
				"txurlinformazionipsp", "txstornopagamento",
				"txidentificativointermediario", "txidentificativocanale",
				"txtipoversamento", "txmodellopagamento", "txpriorita",
				"txdisponibilitaservizio", "txdescrizioneservizio",
				"txcondizionieconomichemassime", "txurlinformazionicanale",
				"datainserimento", "txordinamento", "txstatoinserimento",
				"txorigine", "btBck"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpDettaglioInformativaPsp", "INIZIALE",
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioInformativaPspModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioInformativaPspAction",
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

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txidinformativapsp
		UISecConstraint txidinformativapsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidinformativapsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidinformativapsp_constraints = new UISecConstraint[]{txidinformativapsp_defaultVisib_ctr};
		UISecConstraint txidinformativapsp_ctr = new ComplexUISecConstraint(
				txidinformativapsp_constraints);
		allConstraints.put("txidinformativapsp", txidinformativapsp_ctr);

		// constraint fittizio per txidentificativoflusso
		UISecConstraint txidentificativoflusso_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativoflusso",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoflusso_constraints = new UISecConstraint[]{txidentificativoflusso_defaultVisib_ctr};
		UISecConstraint txidentificativoflusso_ctr = new ComplexUISecConstraint(
				txidentificativoflusso_constraints);
		allConstraints
				.put("txidentificativoflusso", txidentificativoflusso_ctr);

		// constraint fittizio per txidentificativopsp
		UISecConstraint txidentificativopsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativopsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativopsp_constraints = new UISecConstraint[]{txidentificativopsp_defaultVisib_ctr};
		UISecConstraint txidentificativopsp_ctr = new ComplexUISecConstraint(
				txidentificativopsp_constraints);
		allConstraints.put("txidentificativopsp", txidentificativopsp_ctr);

		// constraint fittizio per txragionesociale
		UISecConstraint txragionesociale_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txragionesociale", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txragionesociale_constraints = new UISecConstraint[]{txragionesociale_defaultVisib_ctr};
		UISecConstraint txragionesociale_ctr = new ComplexUISecConstraint(
				txragionesociale_constraints);
		allConstraints.put("txragionesociale", txragionesociale_ctr);

		// constraint fittizio per txdatapubblicazione
		UISecConstraint txdatapubblicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdatapubblicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatapubblicazione_constraints = new UISecConstraint[]{txdatapubblicazione_defaultVisib_ctr};
		UISecConstraint txdatapubblicazione_ctr = new ComplexUISecConstraint(
				txdatapubblicazione_constraints);
		allConstraints.put("txdatapubblicazione", txdatapubblicazione_ctr);

		// constraint fittizio per txdatainiziovalidita
		UISecConstraint txdatainiziovalidita_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdatainiziovalidita",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatainiziovalidita_constraints = new UISecConstraint[]{txdatainiziovalidita_defaultVisib_ctr};
		UISecConstraint txdatainiziovalidita_ctr = new ComplexUISecConstraint(
				txdatainiziovalidita_constraints);
		allConstraints.put("txdatainiziovalidita", txdatainiziovalidita_ctr);

		// constraint fittizio per txurlinformazionipsp
		UISecConstraint txurlinformazionipsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txurlinformazionipsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txurlinformazionipsp_constraints = new UISecConstraint[]{txurlinformazionipsp_defaultVisib_ctr};
		UISecConstraint txurlinformazionipsp_ctr = new ComplexUISecConstraint(
				txurlinformazionipsp_constraints);
		allConstraints.put("txurlinformazionipsp", txurlinformazionipsp_ctr);

		// constraint fittizio per txstornopagamento
		UISecConstraint txstornopagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txstornopagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txstornopagamento_constraints = new UISecConstraint[]{txstornopagamento_defaultVisib_ctr};
		UISecConstraint txstornopagamento_ctr = new ComplexUISecConstraint(
				txstornopagamento_constraints);
		allConstraints.put("txstornopagamento", txstornopagamento_ctr);

		// constraint fittizio per txidentificativointermediario
		UISecConstraint txidentificativointermediario_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativointermediario",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativointermediario_constraints = new UISecConstraint[]{txidentificativointermediario_defaultVisib_ctr};
		UISecConstraint txidentificativointermediario_ctr = new ComplexUISecConstraint(
				txidentificativointermediario_constraints);
		allConstraints.put("txidentificativointermediario",
				txidentificativointermediario_ctr);

		// constraint fittizio per txidentificativocanale
		UISecConstraint txidentificativocanale_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativocanale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativocanale_constraints = new UISecConstraint[]{txidentificativocanale_defaultVisib_ctr};
		UISecConstraint txidentificativocanale_ctr = new ComplexUISecConstraint(
				txidentificativocanale_constraints);
		allConstraints
				.put("txidentificativocanale", txidentificativocanale_ctr);

		// constraint fittizio per txtipoversamento
		UISecConstraint txtipoversamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txtipoversamento", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txtipoversamento_constraints = new UISecConstraint[]{txtipoversamento_defaultVisib_ctr};
		UISecConstraint txtipoversamento_ctr = new ComplexUISecConstraint(
				txtipoversamento_constraints);
		allConstraints.put("txtipoversamento", txtipoversamento_ctr);

		// constraint fittizio per txmodellopagamento
		UISecConstraint txmodellopagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txmodellopagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txmodellopagamento_constraints = new UISecConstraint[]{txmodellopagamento_defaultVisib_ctr};
		UISecConstraint txmodellopagamento_ctr = new ComplexUISecConstraint(
				txmodellopagamento_constraints);
		allConstraints.put("txmodellopagamento", txmodellopagamento_ctr);

		// constraint fittizio per txpriorita
		UISecConstraint txpriorita_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "txpriorita",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txpriorita_constraints = new UISecConstraint[]{txpriorita_defaultVisib_ctr};
		UISecConstraint txpriorita_ctr = new ComplexUISecConstraint(
				txpriorita_constraints);
		allConstraints.put("txpriorita", txpriorita_ctr);

		// constraint fittizio per txdisponibilitaservizio
		UISecConstraint txdisponibilitaservizio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdisponibilitaservizio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdisponibilitaservizio_constraints = new UISecConstraint[]{txdisponibilitaservizio_defaultVisib_ctr};
		UISecConstraint txdisponibilitaservizio_ctr = new ComplexUISecConstraint(
				txdisponibilitaservizio_constraints);
		allConstraints.put("txdisponibilitaservizio",
				txdisponibilitaservizio_ctr);

		// constraint fittizio per txdescrizioneservizio
		UISecConstraint txdescrizioneservizio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdescrizioneservizio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdescrizioneservizio_constraints = new UISecConstraint[]{txdescrizioneservizio_defaultVisib_ctr};
		UISecConstraint txdescrizioneservizio_ctr = new ComplexUISecConstraint(
				txdescrizioneservizio_constraints);
		allConstraints.put("txdescrizioneservizio", txdescrizioneservizio_ctr);

		// constraint fittizio per txcondizionieconomichemassime
		UISecConstraint txcondizionieconomichemassime_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txcondizionieconomichemassime",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txcondizionieconomichemassime_constraints = new UISecConstraint[]{txcondizionieconomichemassime_defaultVisib_ctr};
		UISecConstraint txcondizionieconomichemassime_ctr = new ComplexUISecConstraint(
				txcondizionieconomichemassime_constraints);
		allConstraints.put("txcondizionieconomichemassime",
				txcondizionieconomichemassime_ctr);

		// constraint fittizio per txurlinformazionicanale
		UISecConstraint txurlinformazionicanale_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txurlinformazionicanale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txurlinformazionicanale_constraints = new UISecConstraint[]{txurlinformazionicanale_defaultVisib_ctr};
		UISecConstraint txurlinformazionicanale_ctr = new ComplexUISecConstraint(
				txurlinformazionicanale_constraints);
		allConstraints.put("txurlinformazionicanale",
				txurlinformazionicanale_ctr);

		// constraint fittizio per datainserimento
		UISecConstraint datainserimento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"datainserimento", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] datainserimento_constraints = new UISecConstraint[]{datainserimento_defaultVisib_ctr};
		UISecConstraint datainserimento_ctr = new ComplexUISecConstraint(
				datainserimento_constraints);
		allConstraints.put("datainserimento", datainserimento_ctr);

		// constraint fittizio per txordinamento
		UISecConstraint txordinamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "txordinamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txordinamento_constraints = new UISecConstraint[]{txordinamento_defaultVisib_ctr};
		UISecConstraint txordinamento_ctr = new ComplexUISecConstraint(
				txordinamento_constraints);
		allConstraints.put("txordinamento", txordinamento_ctr);

		// constraint fittizio per txstatoinserimento
		UISecConstraint txstatoinserimento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txstatoinserimento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txstatoinserimento_constraints = new UISecConstraint[]{txstatoinserimento_defaultVisib_ctr};
		UISecConstraint txstatoinserimento_ctr = new ComplexUISecConstraint(
				txstatoinserimento_constraints);
		allConstraints.put("txstatoinserimento", txstatoinserimento_ctr);

		// constraint fittizio per txorigine
		UISecConstraint txorigine_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "txorigine",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txorigine_constraints = new UISecConstraint[]{txorigine_defaultVisib_ctr};
		UISecConstraint txorigine_ctr = new ComplexUISecConstraint(
				txorigine_constraints);
		allConstraints.put("txorigine", txorigine_ctr);

		// constraint fittizio per btBck
		UISecConstraint btBck_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "btBck",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btBck_constraints = new UISecConstraint[]{btBck_defaultVisib_ctr};
		UISecConstraint btBck_ctr = new ComplexUISecConstraint(
				btBck_constraints);
		allConstraints.put("btBck", btBck_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txidinformativapsp
		UISecConstraint txidinformativapsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidinformativapsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidinformativapsp_constraints = new UISecConstraint[]{txidinformativapsp_defaultOnoff_ctr};
		UISecConstraint txidinformativapsp_ctr = new ComplexUISecConstraint(
				txidinformativapsp_constraints);
		allConstraints.put("txidinformativapsp", txidinformativapsp_ctr);

		// constraint fittizio per txidentificativoflusso
		UISecConstraint txidentificativoflusso_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativoflusso",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativoflusso_constraints = new UISecConstraint[]{txidentificativoflusso_defaultOnoff_ctr};
		UISecConstraint txidentificativoflusso_ctr = new ComplexUISecConstraint(
				txidentificativoflusso_constraints);
		allConstraints
				.put("txidentificativoflusso", txidentificativoflusso_ctr);

		// constraint fittizio per txidentificativopsp
		UISecConstraint txidentificativopsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativopsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativopsp_constraints = new UISecConstraint[]{txidentificativopsp_defaultOnoff_ctr};
		UISecConstraint txidentificativopsp_ctr = new ComplexUISecConstraint(
				txidentificativopsp_constraints);
		allConstraints.put("txidentificativopsp", txidentificativopsp_ctr);

		// constraint fittizio per txragionesociale
		UISecConstraint txragionesociale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txragionesociale", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txragionesociale_constraints = new UISecConstraint[]{txragionesociale_defaultOnoff_ctr};
		UISecConstraint txragionesociale_ctr = new ComplexUISecConstraint(
				txragionesociale_constraints);
		allConstraints.put("txragionesociale", txragionesociale_ctr);

		// constraint fittizio per txdatapubblicazione
		UISecConstraint txdatapubblicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdatapubblicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatapubblicazione_constraints = new UISecConstraint[]{txdatapubblicazione_defaultOnoff_ctr};
		UISecConstraint txdatapubblicazione_ctr = new ComplexUISecConstraint(
				txdatapubblicazione_constraints);
		allConstraints.put("txdatapubblicazione", txdatapubblicazione_ctr);

		// constraint fittizio per txdatainiziovalidita
		UISecConstraint txdatainiziovalidita_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdatainiziovalidita",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdatainiziovalidita_constraints = new UISecConstraint[]{txdatainiziovalidita_defaultOnoff_ctr};
		UISecConstraint txdatainiziovalidita_ctr = new ComplexUISecConstraint(
				txdatainiziovalidita_constraints);
		allConstraints.put("txdatainiziovalidita", txdatainiziovalidita_ctr);

		// constraint fittizio per txurlinformazionipsp
		UISecConstraint txurlinformazionipsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txurlinformazionipsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txurlinformazionipsp_constraints = new UISecConstraint[]{txurlinformazionipsp_defaultOnoff_ctr};
		UISecConstraint txurlinformazionipsp_ctr = new ComplexUISecConstraint(
				txurlinformazionipsp_constraints);
		allConstraints.put("txurlinformazionipsp", txurlinformazionipsp_ctr);

		// constraint fittizio per txstornopagamento
		UISecConstraint txstornopagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txstornopagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txstornopagamento_constraints = new UISecConstraint[]{txstornopagamento_defaultOnoff_ctr};
		UISecConstraint txstornopagamento_ctr = new ComplexUISecConstraint(
				txstornopagamento_constraints);
		allConstraints.put("txstornopagamento", txstornopagamento_ctr);

		// constraint fittizio per txidentificativointermediario
		UISecConstraint txidentificativointermediario_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativointermediario",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativointermediario_constraints = new UISecConstraint[]{txidentificativointermediario_defaultOnoff_ctr};
		UISecConstraint txidentificativointermediario_ctr = new ComplexUISecConstraint(
				txidentificativointermediario_constraints);
		allConstraints.put("txidentificativointermediario",
				txidentificativointermediario_ctr);

		// constraint fittizio per txidentificativocanale
		UISecConstraint txidentificativocanale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txidentificativocanale",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidentificativocanale_constraints = new UISecConstraint[]{txidentificativocanale_defaultOnoff_ctr};
		UISecConstraint txidentificativocanale_ctr = new ComplexUISecConstraint(
				txidentificativocanale_constraints);
		allConstraints
				.put("txidentificativocanale", txidentificativocanale_ctr);

		// constraint fittizio per txtipoversamento
		UISecConstraint txtipoversamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txtipoversamento", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] txtipoversamento_constraints = new UISecConstraint[]{txtipoversamento_defaultOnoff_ctr};
		UISecConstraint txtipoversamento_ctr = new ComplexUISecConstraint(
				txtipoversamento_constraints);
		allConstraints.put("txtipoversamento", txtipoversamento_ctr);

		// constraint fittizio per txmodellopagamento
		UISecConstraint txmodellopagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txmodellopagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txmodellopagamento_constraints = new UISecConstraint[]{txmodellopagamento_defaultOnoff_ctr};
		UISecConstraint txmodellopagamento_ctr = new ComplexUISecConstraint(
				txmodellopagamento_constraints);
		allConstraints.put("txmodellopagamento", txmodellopagamento_ctr);

		// constraint fittizio per txpriorita
		UISecConstraint txpriorita_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "txpriorita",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txpriorita_constraints = new UISecConstraint[]{txpriorita_defaultOnoff_ctr};
		UISecConstraint txpriorita_ctr = new ComplexUISecConstraint(
				txpriorita_constraints);
		allConstraints.put("txpriorita", txpriorita_ctr);

		// constraint fittizio per txdisponibilitaservizio
		UISecConstraint txdisponibilitaservizio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdisponibilitaservizio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdisponibilitaservizio_constraints = new UISecConstraint[]{txdisponibilitaservizio_defaultOnoff_ctr};
		UISecConstraint txdisponibilitaservizio_ctr = new ComplexUISecConstraint(
				txdisponibilitaservizio_constraints);
		allConstraints.put("txdisponibilitaservizio",
				txdisponibilitaservizio_ctr);

		// constraint fittizio per txdescrizioneservizio
		UISecConstraint txdescrizioneservizio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txdescrizioneservizio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txdescrizioneservizio_constraints = new UISecConstraint[]{txdescrizioneservizio_defaultOnoff_ctr};
		UISecConstraint txdescrizioneservizio_ctr = new ComplexUISecConstraint(
				txdescrizioneservizio_constraints);
		allConstraints.put("txdescrizioneservizio", txdescrizioneservizio_ctr);

		// constraint fittizio per txcondizionieconomichemassime
		UISecConstraint txcondizionieconomichemassime_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txcondizionieconomichemassime",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txcondizionieconomichemassime_constraints = new UISecConstraint[]{txcondizionieconomichemassime_defaultOnoff_ctr};
		UISecConstraint txcondizionieconomichemassime_ctr = new ComplexUISecConstraint(
				txcondizionieconomichemassime_constraints);
		allConstraints.put("txcondizionieconomichemassime",
				txcondizionieconomichemassime_ctr);

		// constraint fittizio per txurlinformazionicanale
		UISecConstraint txurlinformazionicanale_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txurlinformazionicanale",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txurlinformazionicanale_constraints = new UISecConstraint[]{txurlinformazionicanale_defaultOnoff_ctr};
		UISecConstraint txurlinformazionicanale_ctr = new ComplexUISecConstraint(
				txurlinformazionicanale_constraints);
		allConstraints.put("txurlinformazionicanale",
				txurlinformazionicanale_ctr);

		// constraint fittizio per datainserimento
		UISecConstraint datainserimento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"datainserimento", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] datainserimento_constraints = new UISecConstraint[]{datainserimento_defaultOnoff_ctr};
		UISecConstraint datainserimento_ctr = new ComplexUISecConstraint(
				datainserimento_constraints);
		allConstraints.put("datainserimento", datainserimento_ctr);

		// constraint fittizio per txordinamento
		UISecConstraint txordinamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "txordinamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txordinamento_constraints = new UISecConstraint[]{txordinamento_defaultOnoff_ctr};
		UISecConstraint txordinamento_ctr = new ComplexUISecConstraint(
				txordinamento_constraints);
		allConstraints.put("txordinamento", txordinamento_ctr);

		// constraint fittizio per txstatoinserimento
		UISecConstraint txstatoinserimento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp",
				"txstatoinserimento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txstatoinserimento_constraints = new UISecConstraint[]{txstatoinserimento_defaultOnoff_ctr};
		UISecConstraint txstatoinserimento_ctr = new ComplexUISecConstraint(
				txstatoinserimento_constraints);
		allConstraints.put("txstatoinserimento", txstatoinserimento_ctr);

		// constraint fittizio per txorigine
		UISecConstraint txorigine_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "txorigine",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txorigine_constraints = new UISecConstraint[]{txorigine_defaultOnoff_ctr};
		UISecConstraint txorigine_ctr = new ComplexUISecConstraint(
				txorigine_constraints);
		allConstraints.put("txorigine", txorigine_ctr);

		// constraint fittizio per btBck
		UISecConstraint btBck_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioInformativaPsp", "btBck",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btBck_constraints = new UISecConstraint[]{btBck_defaultOnoff_ctr};
		UISecConstraint btBck_ctr = new ComplexUISecConstraint(
				btBck_constraints);
		allConstraints.put("btBck", btBck_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] START");

		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioInformativaPspAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioInformativaPspAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioInformativaPspAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioInformativaPspAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioInformativaPsp");
		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioInformativaPspAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btBck per ogni Ev.H.
		HashMap<String, ICommand> cmds4btBckByEvh = new HashMap<String, ICommand>();

		cmds4btBckByEvh.put("CLICKED", initCommandBtBck_CLICKED());
		cmdsByWidget.put("btBck", cmds4btBckByEvh);

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

	private ICommand initCommandBtBck_CLICKED() {
		// ExecCommand begin
		String[] resultNames4goBack = new String[]{"OK"};

		ICommand[] actionsForResults4goBack = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btBck_resokAction_0 = new JumpCommand(
				"cpInformativePsp", null, false);
		/// Jump Command end
		actionsForResults4goBack[0] = act_actions_clicked_btBck_resokAction_0;

		ExecCommand act_actions_clicked_btBck_1 = new ExecCommand(
				resultNames4goBack, actionsForResults4goBack) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.goBack((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioInformativaPspModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioInformativaPspAction",
							"readOne()", "chiamata verso BackEnd", "goBack");
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
		return act_actions_clicked_btBck_1;
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
