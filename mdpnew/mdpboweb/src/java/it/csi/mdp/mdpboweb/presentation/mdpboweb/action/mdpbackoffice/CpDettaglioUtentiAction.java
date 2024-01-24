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
 * CpDettaglioUtentiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioUtentiAction extends BaseAction implements Preparable {

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDatautente(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente value) {
		getSession().put("appDatautente", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente getAppDatautente() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente) (getSession()
				.get("appDatautente"));
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

	public void setAppDataselettoreIdRuolo(java.lang.String value) {
		getSession().put("appDataselettoreIdRuolo", value);
	}

	public java.lang.String getAppDataselettoreIdRuolo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRuolo"));
	}

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

	public void setAppDataselettoreIdGruppo(java.lang.String value) {
		getSession().put("appDataselettoreIdGruppo", value);
	}

	public java.lang.String getAppDataselettoreIdGruppo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdGruppo"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel.class;
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
			log.debug("[CpDettaglioUtentiAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioUtentiAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioUtentiAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioUtentiAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioUtentiAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btSalva]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtSalva_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btSalva", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioUtentiAction::handleBtSalva_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioUtentiAction::handleBtSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioUtentiAction::handleBtSalva_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioUtentiAction::handleBtSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioUtentiAction::handleBtSalva_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget cbGruppo.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbGruppo_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatagruppi");

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
		/*PROTECTED REGION ID(R-1090412188) ENABLED START*/
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
				log.error("[CpDettaglioUtentiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioUtentiAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"NUOVO", "MODIFICA"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resnuovoActionstep_0_on = new String[]{
				"mnuView", "tfdescrUtente", "tfCodFisc", "tfEmail",
				"Pwdservizio", "btIndietro", "btSalva", "cbGruppo",
				"txidUtente"};

		String[] act_onRefresh_resnuovoActionstep_0_off = new String[]{};

		String[] act_onRefresh_resnuovoActionstep_0_show = new String[]{
				"mnuView", "tfdescrUtente", "tfCodFisc", "tfEmail",
				"Pwdservizio", "btIndietro", "btSalva", "cbGruppo",
				"txidUtente"};

		String[] act_onRefresh_resnuovoActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resnuovoActionstep_0 = new ScreenStateCommand(
				"cpDettaglioUtenti", "NUOVO",
				act_onRefresh_resnuovoActionstep_0_on,
				act_onRefresh_resnuovoActionstep_0_off,
				act_onRefresh_resnuovoActionstep_0_show,
				act_onRefresh_resnuovoActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resnuovoAction_0_steps = new ICommand[]{act_onRefresh_resnuovoActionstep_0};
		SequenceCommand act_onRefresh_resnuovoAction_0 = new SequenceCommand(
				act_onRefresh_resnuovoAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resnuovoAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resmodificaActionstep_0_on = new String[]{
				"mnuView", "tfdescrUtente", "txidUtente", "tfCodFisc",
				"tfEmail", "Pwdservizio", "btIndietro", "btSalva", "cbGruppo"};

		String[] act_onRefresh_resmodificaActionstep_0_off = new String[]{};

		String[] act_onRefresh_resmodificaActionstep_0_show = new String[]{
				"mnuView", "tfdescrUtente", "txidUtente", "tfCodFisc",
				"tfEmail", "Pwdservizio", "btIndietro", "btSalva", "cbGruppo"};

		String[] act_onRefresh_resmodificaActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resmodificaActionstep_0 = new ScreenStateCommand(
				"cpDettaglioUtenti", "MODIFICA",
				act_onRefresh_resmodificaActionstep_0_on,
				act_onRefresh_resmodificaActionstep_0_off,
				act_onRefresh_resmodificaActionstep_0_show,
				act_onRefresh_resmodificaActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resmodificaAction_1_steps = new ICommand[]{act_onRefresh_resmodificaActionstep_0};
		SequenceCommand act_onRefresh_resmodificaAction_1 = new SequenceCommand(
				act_onRefresh_resmodificaAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resmodificaAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioUtentiAction", "readOne()",
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

		new DummyUISecConstraint("cpDettaglioUtenti", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txidUtente
		UISecConstraint txidUtente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "txidUtente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidUtente_constraints = new UISecConstraint[]{txidUtente_defaultVisib_ctr};
		UISecConstraint txidUtente_ctr = new ComplexUISecConstraint(
				txidUtente_constraints);
		allConstraints.put("txidUtente", txidUtente_ctr);

		// constraint fittizio per tfdescrUtente
		UISecConstraint tfdescrUtente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "tfdescrUtente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfdescrUtente_constraints = new UISecConstraint[]{tfdescrUtente_defaultVisib_ctr};
		UISecConstraint tfdescrUtente_ctr = new ComplexUISecConstraint(
				tfdescrUtente_constraints);
		allConstraints.put("tfdescrUtente", tfdescrUtente_ctr);

		// constraint fittizio per tfCodFisc
		UISecConstraint tfCodFisc_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "tfCodFisc",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfCodFisc_constraints = new UISecConstraint[]{tfCodFisc_defaultVisib_ctr};
		UISecConstraint tfCodFisc_ctr = new ComplexUISecConstraint(
				tfCodFisc_constraints);
		allConstraints.put("tfCodFisc", tfCodFisc_ctr);

		// constraint fittizio per tfEmail
		UISecConstraint tfEmail_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "tfEmail",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfEmail_constraints = new UISecConstraint[]{tfEmail_defaultVisib_ctr};
		UISecConstraint tfEmail_ctr = new ComplexUISecConstraint(
				tfEmail_constraints);
		allConstraints.put("tfEmail", tfEmail_ctr);

		// constraint fittizio per Pwdservizio
		UISecConstraint Pwdservizio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "Pwdservizio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] Pwdservizio_constraints = new UISecConstraint[]{Pwdservizio_defaultVisib_ctr};
		UISecConstraint Pwdservizio_ctr = new ComplexUISecConstraint(
				Pwdservizio_constraints);
		allConstraints.put("Pwdservizio", Pwdservizio_ctr);

		// constraint fittizio per cbGruppo
		UISecConstraint cbGruppo_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "cbGruppo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbGruppo_constraints = new UISecConstraint[]{cbGruppo_defaultVisib_ctr};
		UISecConstraint cbGruppo_ctr = new ComplexUISecConstraint(
				cbGruppo_constraints);
		allConstraints.put("cbGruppo", cbGruppo_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "btIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultVisib_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);

		// constraint fittizio per btSalva
		UISecConstraint btSalva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "btSalva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btSalva_constraints = new UISecConstraint[]{btSalva_defaultVisib_ctr};
		UISecConstraint btSalva_ctr = new ComplexUISecConstraint(
				btSalva_constraints);
		allConstraints.put("btSalva", btSalva_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txidUtente
		UISecConstraint txidUtente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "txidUtente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txidUtente_constraints = new UISecConstraint[]{txidUtente_defaultOnoff_ctr};
		UISecConstraint txidUtente_ctr = new ComplexUISecConstraint(
				txidUtente_constraints);
		allConstraints.put("txidUtente", txidUtente_ctr);

		// constraint fittizio per tfdescrUtente
		UISecConstraint tfdescrUtente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "tfdescrUtente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfdescrUtente_constraints = new UISecConstraint[]{tfdescrUtente_defaultOnoff_ctr};
		UISecConstraint tfdescrUtente_ctr = new ComplexUISecConstraint(
				tfdescrUtente_constraints);
		allConstraints.put("tfdescrUtente", tfdescrUtente_ctr);

		// constraint fittizio per tfCodFisc
		UISecConstraint tfCodFisc_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "tfCodFisc",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfCodFisc_constraints = new UISecConstraint[]{tfCodFisc_defaultOnoff_ctr};
		UISecConstraint tfCodFisc_ctr = new ComplexUISecConstraint(
				tfCodFisc_constraints);
		allConstraints.put("tfCodFisc", tfCodFisc_ctr);

		// constraint fittizio per tfEmail
		UISecConstraint tfEmail_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "tfEmail",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfEmail_constraints = new UISecConstraint[]{tfEmail_defaultOnoff_ctr};
		UISecConstraint tfEmail_ctr = new ComplexUISecConstraint(
				tfEmail_constraints);
		allConstraints.put("tfEmail", tfEmail_ctr);

		// constraint fittizio per Pwdservizio
		UISecConstraint Pwdservizio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "Pwdservizio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] Pwdservizio_constraints = new UISecConstraint[]{Pwdservizio_defaultOnoff_ctr};
		UISecConstraint Pwdservizio_ctr = new ComplexUISecConstraint(
				Pwdservizio_constraints);
		allConstraints.put("Pwdservizio", Pwdservizio_ctr);

		// constraint fittizio per cbGruppo
		UISecConstraint cbGruppo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "cbGruppo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbGruppo_constraints = new UISecConstraint[]{cbGruppo_defaultOnoff_ctr};
		UISecConstraint cbGruppo_ctr = new ComplexUISecConstraint(
				cbGruppo_constraints);
		allConstraints.put("cbGruppo", cbGruppo_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);

		// constraint fittizio per btSalva
		UISecConstraint btSalva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioUtenti", "btSalva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btSalva_constraints = new UISecConstraint[]{btSalva_defaultOnoff_ctr};
		UISecConstraint btSalva_ctr = new ComplexUISecConstraint(
				btSalva_constraints);
		allConstraints.put("btSalva", btSalva_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioUtentiAction::dumpmodel] START");

		log.debug("[CpDettaglioUtentiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioUtentiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioUtentiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioUtentiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioUtentiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioUtentiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioUtentiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioUtenti");
		log.debug("[CpDettaglioUtentiAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpDettaglioUtentiAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioUtentiAction::dumpmodel] " + session);
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
		// contiene i comandi del widget btSalva per ogni Ev.H.
		HashMap<String, ICommand> cmds4btSalvaByEvh = new HashMap<String, ICommand>();

		cmds4btSalvaByEvh.put("CLICKED", initCommandBtSalva_CLICKED());
		cmdsByWidget.put("btSalva", cmds4btSalvaByEvh);

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
				"cpGestioneUtenti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btIndietro_1_steps = new ICommand[]{act_actions_clicked_btIndietrostep_0};
		SequenceCommand act_actions_clicked_btIndietro_1 = new SequenceCommand(
				act_actions_clicked_btIndietro_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btIndietro_1;
	}

	private ICommand initCommandBtSalva_CLICKED() {
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4salvaUtente = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salvaUtente = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btSalvastep_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btSalvastep_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btSalvastep_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btSalvastep_resokActionstep_1 = new JumpCommand(
				"cpGestioneUtenti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btSalvastep_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btSalvastep_resokActionstep_0,
				act_actions_clicked_btSalvastep_resokActionstep_1};
		SequenceCommand act_actions_clicked_btSalvastep_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btSalvastep_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4salvaUtente[0] = act_actions_clicked_btSalvastep_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btSalvastep_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4salvaUtente[1] = act_actions_clicked_btSalvastep_reskoAction_1;

		ExecCommand act_actions_clicked_btSalvastep_0 = new ExecCommand(
				resultNames4salvaUtente, actionsForResults4salvaUtente) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salvaUtente(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioUtentiAction", "readOne()",
							"chiamata verso BackEnd", "salvaUtente");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salvaUtente]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btSalva_1_steps = new ICommand[]{act_actions_clicked_btSalvastep_0};
		SequenceCommand act_actions_clicked_btSalva_1 = new SequenceCommand(
				act_actions_clicked_btSalva_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btSalva_1;
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
