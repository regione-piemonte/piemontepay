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
 * CpStatisticheTranzazioniAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpStatisticheTranzazioniAction extends BaseAction
		implements
			Preparable {

	// Table tbRisultati

	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione> _widg_tbRisultati;

	public void setWidg_tbRisultati(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione> value) {
		_widg_tbRisultati = value;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione> getWidg_tbRisultati() {
		return _widg_tbRisultati;
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

	public void setAppDatalistaStatisticaApplicazioneTransazione(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione> value) {
		getSession()
				.put("appDatalistaStatisticaApplicazioneTransazione", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione> getAppDatalistaStatisticaApplicazioneTransazione() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione>) (getSession()
				.get("appDatalistaStatisticaApplicazioneTransazione"));
	}

	public void setAppDatastatisticaApplicazioneTransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione value) {
		getSession().put("appDatastatisticaApplicazioneTransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione getAppDatastatisticaApplicazioneTransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.StatisticaApplicazioneTransazione) (getSession()
				.get("appDatastatisticaApplicazioneTransazione"));
	}

	private it.csi.mdp.mdpboweb.dto.nsbackoffice.FiltroRicercaStatisticaApplicazioneTransazione _appDatafiltroRicercaStatisticaApplicazioneTransazione = null;

	public void setAppDatafiltroRicercaStatisticaApplicazioneTransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FiltroRicercaStatisticaApplicazioneTransazione value) {
		_appDatafiltroRicercaStatisticaApplicazioneTransazione = value;
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.FiltroRicercaStatisticaApplicazioneTransazione getAppDatafiltroRicercaStatisticaApplicazioneTransazione() {
		return _appDatafiltroRicercaStatisticaApplicazioneTransazione;
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel.class;
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
			log.debug("[CpStatisticheTranzazioniAction::handleBtCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpStatisticheTranzazioniAction::handleBtCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStatisticheTranzazioniAction::handleBtCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpStatisticheTranzazioniAction::handleBtCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStatisticheTranzazioniAction::handleBtCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btexport]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtexport_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btexport", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpStatisticheTranzazioniAction::handleBtexport_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpStatisticheTranzazioniAction::handleBtexport_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStatisticheTranzazioniAction::handleBtexport_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpStatisticheTranzazioniAction::handleBtexport_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpStatisticheTranzazioniAction::handleBtexport_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tbRisultati.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbRisultati_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaStatisticaApplicazioneTransazione");

		if (isTableClearStatus("cpStatisticheTranzazioni_tbRisultati")) {
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
		/*PROTECTED REGION ID(R1490427094) ENABLED START*/
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
				log.error("[CpStatisticheTranzazioniAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpStatisticheTranzazioniAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"VIEW_INIZIALE",
				"VIEW_RICERCA"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resview_inizialeActionstep_0_on = new String[]{
				"mnuView", "txApplicationId", "txDataDa", "txDataA", "btCerca"};

		String[] act_onRefresh_resview_inizialeActionstep_0_off = new String[]{
				"tbRisultati", "btexport"};

		String[] act_onRefresh_resview_inizialeActionstep_0_show = new String[]{
				"mnuView", "txApplicationId", "txDataDa", "txDataA", "btCerca"};

		String[] act_onRefresh_resview_inizialeActionstep_0_hide = new String[]{
				"tbRisultati", "btexport"};

		ScreenStateCommand act_onRefresh_resview_inizialeActionstep_0 = new ScreenStateCommand(
				"cpStatisticheTranzazioni", "INIZIALE",
				act_onRefresh_resview_inizialeActionstep_0_on,
				act_onRefresh_resview_inizialeActionstep_0_off,
				act_onRefresh_resview_inizialeActionstep_0_show,
				act_onRefresh_resview_inizialeActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resview_inizialeAction_0_steps = new ICommand[]{act_onRefresh_resview_inizialeActionstep_0};
		SequenceCommand act_onRefresh_resview_inizialeAction_0 = new SequenceCommand(
				act_onRefresh_resview_inizialeAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resview_inizialeAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resview_ricercaActionstep_0_on = new String[]{
				"mnuView", "txApplicationId", "txDataDa", "txDataA", "btCerca",
				"tbRisultati", "btexport"};

		String[] act_onRefresh_resview_ricercaActionstep_0_off = new String[]{};

		String[] act_onRefresh_resview_ricercaActionstep_0_show = new String[]{
				"mnuView", "txApplicationId", "txDataDa", "txDataA", "btCerca",
				"tbRisultati", "btexport"};

		String[] act_onRefresh_resview_ricercaActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resview_ricercaActionstep_0 = new ScreenStateCommand(
				"cpStatisticheTranzazioni", "RICERCA_OK",
				act_onRefresh_resview_ricercaActionstep_0_on,
				act_onRefresh_resview_ricercaActionstep_0_off,
				act_onRefresh_resview_ricercaActionstep_0_show,
				act_onRefresh_resview_ricercaActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resview_ricercaAction_1_steps = new ICommand[]{act_onRefresh_resview_ricercaActionstep_0};
		SequenceCommand act_onRefresh_resview_ricercaAction_1 = new SequenceCommand(
				act_onRefresh_resview_ricercaAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resview_ricercaAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpStatisticheTranzazioniAction",
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

		new DummyUISecConstraint("cpStatisticheTranzazioni", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txApplicationId
		UISecConstraint txApplicationId_defaultVisib_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "txApplicationId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txApplicationId_constraints = new UISecConstraint[]{txApplicationId_defaultVisib_ctr};
		UISecConstraint txApplicationId_ctr = new ComplexUISecConstraint(
				txApplicationId_constraints);
		allConstraints.put("txApplicationId", txApplicationId_ctr);

		// constraint fittizio per txDataDa
		UISecConstraint txDataDa_defaultVisib_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "txDataDa",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataDa_constraints = new UISecConstraint[]{txDataDa_defaultVisib_ctr};
		UISecConstraint txDataDa_ctr = new ComplexUISecConstraint(
				txDataDa_constraints);
		allConstraints.put("txDataDa", txDataDa_ctr);

		// constraint fittizio per txDataA
		UISecConstraint txDataA_defaultVisib_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "txDataA",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataA_constraints = new UISecConstraint[]{txDataA_defaultVisib_ctr};
		UISecConstraint txDataA_ctr = new ComplexUISecConstraint(
				txDataA_constraints);
		allConstraints.put("txDataA", txDataA_ctr);

		// constraint fittizio per btCerca
		UISecConstraint btCerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "btCerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btCerca_constraints = new UISecConstraint[]{btCerca_defaultVisib_ctr};
		UISecConstraint btCerca_ctr = new ComplexUISecConstraint(
				btCerca_constraints);
		allConstraints.put("btCerca", btCerca_ctr);

		// constraint fittizio per tbRisultati
		UISecConstraint tbRisultati_defaultVisib_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "tbRisultati",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRisultati_constraints = new UISecConstraint[]{tbRisultati_defaultVisib_ctr};
		UISecConstraint tbRisultati_ctr = new ComplexUISecConstraint(
				tbRisultati_constraints);
		allConstraints.put("tbRisultati", tbRisultati_ctr);

		// constraint fittizio per btexport
		UISecConstraint btexport_defaultVisib_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "btexport",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btexport_constraints = new UISecConstraint[]{btexport_defaultVisib_ctr};
		UISecConstraint btexport_ctr = new ComplexUISecConstraint(
				btexport_constraints);
		allConstraints.put("btexport", btexport_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per txApplicationId
		UISecConstraint txApplicationId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "txApplicationId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txApplicationId_constraints = new UISecConstraint[]{txApplicationId_defaultOnoff_ctr};
		UISecConstraint txApplicationId_ctr = new ComplexUISecConstraint(
				txApplicationId_constraints);
		allConstraints.put("txApplicationId", txApplicationId_ctr);

		// constraint fittizio per txDataDa
		UISecConstraint txDataDa_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "txDataDa",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataDa_constraints = new UISecConstraint[]{txDataDa_defaultOnoff_ctr};
		UISecConstraint txDataDa_ctr = new ComplexUISecConstraint(
				txDataDa_constraints);
		allConstraints.put("txDataDa", txDataDa_ctr);

		// constraint fittizio per txDataA
		UISecConstraint txDataA_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "txDataA",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDataA_constraints = new UISecConstraint[]{txDataA_defaultOnoff_ctr};
		UISecConstraint txDataA_ctr = new ComplexUISecConstraint(
				txDataA_constraints);
		allConstraints.put("txDataA", txDataA_ctr);

		// constraint fittizio per btCerca
		UISecConstraint btCerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "btCerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btCerca_constraints = new UISecConstraint[]{btCerca_defaultOnoff_ctr};
		UISecConstraint btCerca_ctr = new ComplexUISecConstraint(
				btCerca_constraints);
		allConstraints.put("btCerca", btCerca_ctr);

		// constraint fittizio per tbRisultati
		UISecConstraint tbRisultati_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "tbRisultati",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRisultati_constraints = new UISecConstraint[]{tbRisultati_defaultOnoff_ctr};
		UISecConstraint tbRisultati_ctr = new ComplexUISecConstraint(
				tbRisultati_constraints);
		allConstraints.put("tbRisultati", tbRisultati_ctr);

		// constraint fittizio per btexport
		UISecConstraint btexport_defaultOnoff_ctr =

		new DummyUISecConstraint("cpStatisticheTranzazioni", "btexport",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btexport_constraints = new UISecConstraint[]{btexport_defaultOnoff_ctr};
		UISecConstraint btexport_ctr = new ComplexUISecConstraint(
				btexport_constraints);
		allConstraints.put("btexport", btexport_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] START");

		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpStatisticheTranzazioniAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpStatisticheTranzazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpStatisticheTranzazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpStatisticheTranzazioniAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpStatisticheTranzazioni");
		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] [c] sessione");
		log.debug("[CpStatisticheTranzazioniAction::dumpmodel] " + session);
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
		// contiene i comandi del widget btexport per ogni Ev.H.
		HashMap<String, ICommand> cmds4btexportByEvh = new HashMap<String, ICommand>();

		cmds4btexportByEvh.put("CLICKED", initCommandBtexport_CLICKED());
		cmdsByWidget.put("btexport", cmds4btexportByEvh);

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
		// SequenceCommand begin
		// ExecCommand begin
		String[] resultNames4ricerca = new String[]{"OK"};

		ICommand[] actionsForResults4ricerca = new ICommand[1];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btCercastep_resokAction_0_on = new String[]{
				"mnuView", "txApplicationId", "txDataDa", "txDataA", "btCerca",
				"tbRisultati", "btexport"};

		String[] act_actions_clicked_btCercastep_resokAction_0_off = new String[]{};

		String[] act_actions_clicked_btCercastep_resokAction_0_show = new String[]{
				"mnuView", "txApplicationId", "txDataDa", "txDataA", "btCerca",
				"tbRisultati", "btexport"};

		String[] act_actions_clicked_btCercastep_resokAction_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btCercastep_resokAction_0 = new ScreenStateCommand(
				"cpStatisticheTranzazioni", "RICERCA_OK",
				act_actions_clicked_btCercastep_resokAction_0_on,
				act_actions_clicked_btCercastep_resokAction_0_off,
				act_actions_clicked_btCercastep_resokAction_0_show,
				act_actions_clicked_btCercastep_resokAction_0_hide);
		//Screen State Command end
		actionsForResults4ricerca[0] = act_actions_clicked_btCercastep_resokAction_0;

		ExecCommand act_actions_clicked_btCercastep_0 = new ExecCommand(
				resultNames4ricerca, actionsForResults4ricerca) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.ricerca(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpStatisticheTranzazioniAction",
							"readOne()", "chiamata verso BackEnd", "ricerca");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [ricerca]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end

		ICommand[] act_actions_clicked_btCerca_1_steps = new ICommand[]{act_actions_clicked_btCercastep_0};
		SequenceCommand act_actions_clicked_btCerca_1 = new SequenceCommand(
				act_actions_clicked_btCerca_1_steps);
		// SequenceCommand end
		return act_actions_clicked_btCerca_1;
	}

	private ICommand initCommandBtexport_CLICKED() {
		// ExecCommand begin
		String[] resultNames4exportCsv = new String[]{"EXPORT"};

		ICommand[] actionsForResults4exportCsv = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btexport_resexportAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4exportCsv[0] = act_actions_clicked_btexport_resexportAction_0;

		ExecCommand act_actions_clicked_btexport_1 = new ExecCommand(
				resultNames4exportCsv, actionsForResults4exportCsv) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.exportCsv(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpStatisticheTranzazioniAction",
							"readOne()", "chiamata verso BackEnd", "exportCsv");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [exportCsv]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btexport_1;
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
