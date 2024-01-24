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
 * CpGestioneGiornaleEventiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneGiornaleEventiAction extends BaseAction
		implements
			Preparable {

	private it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi _appDataricercaGiornaleEventi = null;

	public void setAppDataricercaGiornaleEventi(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi value) {
		_appDataricercaGiornaleEventi = value;
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi getAppDataricercaGiornaleEventi() {
		return _appDataricercaGiornaleEventi;
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDataselettoreIdGiornaleEvento(java.lang.String value) {
		getSession().put("appDataselettoreIdGiornaleEvento", value);
	}

	public java.lang.String getAppDataselettoreIdGiornaleEvento() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGiornaleEvento"));
	}

	public void setAppDatalistaGiornaliEventi(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi> value) {
		getSession().put("appDatalistaGiornaliEventi", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi> getAppDatalistaGiornaliEventi() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.GiornaleEventi>) (getSession()
				.get("appDatalistaGiornaliEventi"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel.class;
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
			log.debug("[CpGestioneGiornaleEventiAction::handleCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGiornaleEventiAction::handleCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGiornaleEventiAction::handleCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGiornaleEventiAction::handleCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGiornaleEventiAction::handleCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btPulisci]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtPulisci_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btPulisci", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGiornaleEventiAction::handleBtPulisci_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGiornaleEventiAction::handleBtPulisci_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGiornaleEventiAction::handleBtPulisci_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGiornaleEventiAction::handleBtPulisci_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGiornaleEventiAction::handleBtPulisci_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [bpDettaglio]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBpDettaglio_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("bpDettaglio", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGiornaleEventiAction::handleBpDettaglio_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGiornaleEventiAction::handleBpDettaglio_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGiornaleEventiAction::handleBpDettaglio_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGiornaleEventiAction::handleBpDettaglio_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGiornaleEventiAction::handleBpDettaglio_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tRicerca.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTRicerca_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaGiornaliEventi");

		if (isTableClearStatus("cpGestioneGiornaleEventi_tRicerca")) {
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
		/*PROTECTED REGION ID(R1404064118) ENABLED START*/
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
				log.error("[CpGestioneGiornaleEventiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneGiornaleEventiAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Iniziale", "RESULT"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeActionstep_0_on = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca"};

		String[] act_onRefresh_resinizialeActionstep_0_off = new String[]{
				"btPulisci", "tRicerca", "bpDettaglio"};

		String[] act_onRefresh_resinizialeActionstep_0_show = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"btPulisci"};

		String[] act_onRefresh_resinizialeActionstep_0_hide = new String[]{
				"tRicerca", "bpDettaglio"};

		ScreenStateCommand act_onRefresh_resinizialeActionstep_0 = new ScreenStateCommand(
				"cpGestioneGiornaleEventi", "VIEW_INIZIALE",
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
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "bpDettaglio", "btPulisci"};

		String[] act_onRefresh_resresultActionstep_0_off = new String[]{};

		String[] act_onRefresh_resresultActionstep_0_show = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "bpDettaglio", "btPulisci"};

		String[] act_onRefresh_resresultActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resresultActionstep_0 = new ScreenStateCommand(
				"cpGestioneGiornaleEventi", "VIEW_RICERCA_OK",
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGiornaleEventiAction",
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

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "iuv",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultVisib_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per dataEvento
		UISecConstraint dataEvento_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "dataEvento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] dataEvento_constraints = new UISecConstraint[]{dataEvento_defaultVisib_ctr};
		UISecConstraint dataEvento_ctr = new ComplexUISecConstraint(
				dataEvento_constraints);
		allConstraints.put("dataEvento", dataEvento_ctr);

		// constraint fittizio per identificativoDominio
		UISecConstraint identificativoDominio_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi",
				"identificativoDominio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoDominio_constraints = new UISecConstraint[]{identificativoDominio_defaultVisib_ctr};
		UISecConstraint identificativoDominio_ctr = new ComplexUISecConstraint(
				identificativoDominio_constraints);
		allConstraints.put("identificativoDominio", identificativoDominio_ctr);

		// constraint fittizio per identificativoFruitore
		UISecConstraint identificativoFruitore_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi",
				"identificativoFruitore",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoFruitore_constraints = new UISecConstraint[]{identificativoFruitore_defaultVisib_ctr};
		UISecConstraint identificativoFruitore_ctr = new ComplexUISecConstraint(
				identificativoFruitore_constraints);
		allConstraints
				.put("identificativoFruitore", identificativoFruitore_ctr);

		// constraint fittizio per codicePagamento
		UISecConstraint codicePagamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "codicePagamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] codicePagamento_constraints = new UISecConstraint[]{codicePagamento_defaultVisib_ctr};
		UISecConstraint codicePagamento_ctr = new ComplexUISecConstraint(
				codicePagamento_constraints);
		allConstraints.put("codicePagamento", codicePagamento_ctr);

		// constraint fittizio per cerca
		UISecConstraint cerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "cerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cerca_constraints = new UISecConstraint[]{cerca_defaultVisib_ctr};
		UISecConstraint cerca_ctr = new ComplexUISecConstraint(
				cerca_constraints);
		allConstraints.put("cerca", cerca_ctr);

		// constraint fittizio per btPulisci
		UISecConstraint btPulisci_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "btPulisci",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btPulisci_constraints = new UISecConstraint[]{btPulisci_defaultVisib_ctr};
		UISecConstraint btPulisci_ctr = new ComplexUISecConstraint(
				btPulisci_constraints);
		allConstraints.put("btPulisci", btPulisci_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "tRicerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultVisib_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per bpDettaglio
		UISecConstraint bpDettaglio_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "bpDettaglio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] bpDettaglio_constraints = new UISecConstraint[]{bpDettaglio_defaultVisib_ctr};
		UISecConstraint bpDettaglio_ctr = new ComplexUISecConstraint(
				bpDettaglio_constraints);
		allConstraints.put("bpDettaglio", bpDettaglio_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per iuv
		UISecConstraint iuv_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "iuv",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] iuv_constraints = new UISecConstraint[]{iuv_defaultOnoff_ctr};
		UISecConstraint iuv_ctr = new ComplexUISecConstraint(iuv_constraints);
		allConstraints.put("iuv", iuv_ctr);

		// constraint fittizio per dataEvento
		UISecConstraint dataEvento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "dataEvento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] dataEvento_constraints = new UISecConstraint[]{dataEvento_defaultOnoff_ctr};
		UISecConstraint dataEvento_ctr = new ComplexUISecConstraint(
				dataEvento_constraints);
		allConstraints.put("dataEvento", dataEvento_ctr);

		// constraint fittizio per identificativoDominio
		UISecConstraint identificativoDominio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi",
				"identificativoDominio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoDominio_constraints = new UISecConstraint[]{identificativoDominio_defaultOnoff_ctr};
		UISecConstraint identificativoDominio_ctr = new ComplexUISecConstraint(
				identificativoDominio_constraints);
		allConstraints.put("identificativoDominio", identificativoDominio_ctr);

		// constraint fittizio per identificativoFruitore
		UISecConstraint identificativoFruitore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi",
				"identificativoFruitore",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] identificativoFruitore_constraints = new UISecConstraint[]{identificativoFruitore_defaultOnoff_ctr};
		UISecConstraint identificativoFruitore_ctr = new ComplexUISecConstraint(
				identificativoFruitore_constraints);
		allConstraints
				.put("identificativoFruitore", identificativoFruitore_ctr);

		// constraint fittizio per codicePagamento
		UISecConstraint codicePagamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "codicePagamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] codicePagamento_constraints = new UISecConstraint[]{codicePagamento_defaultOnoff_ctr};
		UISecConstraint codicePagamento_ctr = new ComplexUISecConstraint(
				codicePagamento_constraints);
		allConstraints.put("codicePagamento", codicePagamento_ctr);

		// constraint fittizio per cerca
		UISecConstraint cerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "cerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cerca_constraints = new UISecConstraint[]{cerca_defaultOnoff_ctr};
		UISecConstraint cerca_ctr = new ComplexUISecConstraint(
				cerca_constraints);
		allConstraints.put("cerca", cerca_ctr);

		// constraint fittizio per btPulisci
		UISecConstraint btPulisci_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "btPulisci",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btPulisci_constraints = new UISecConstraint[]{btPulisci_defaultOnoff_ctr};
		UISecConstraint btPulisci_ctr = new ComplexUISecConstraint(
				btPulisci_constraints);
		allConstraints.put("btPulisci", btPulisci_ctr);

		// constraint fittizio per tRicerca
		UISecConstraint tRicerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "tRicerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tRicerca_constraints = new UISecConstraint[]{tRicerca_defaultOnoff_ctr};
		UISecConstraint tRicerca_ctr = new ComplexUISecConstraint(
				tRicerca_constraints);
		allConstraints.put("tRicerca", tRicerca_ctr);

		// constraint fittizio per bpDettaglio
		UISecConstraint bpDettaglio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGiornaleEventi", "bpDettaglio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] bpDettaglio_constraints = new UISecConstraint[]{bpDettaglio_defaultOnoff_ctr};
		UISecConstraint bpDettaglio_ctr = new ComplexUISecConstraint(
				bpDettaglio_constraints);
		allConstraints.put("bpDettaglio", bpDettaglio_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] START");

		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneGiornaleEventiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneGiornaleEventiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneGiornaleEventiAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneGiornaleEventi");
		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneGiornaleEventiAction::dumpmodel] " + session);
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
		// contiene i comandi del widget btPulisci per ogni Ev.H.
		HashMap<String, ICommand> cmds4btPulisciByEvh = new HashMap<String, ICommand>();

		cmds4btPulisciByEvh.put("CLICKED", initCommandBtPulisci_CLICKED());
		cmdsByWidget.put("btPulisci", cmds4btPulisciByEvh);
		// contiene i comandi del widget bpDettaglio per ogni Ev.H.
		HashMap<String, ICommand> cmds4bpDettaglioByEvh = new HashMap<String, ICommand>();

		cmds4bpDettaglioByEvh.put("CLICKED", initCommandBpDettaglio_CLICKED());
		cmdsByWidget.put("bpDettaglio", cmds4bpDettaglioByEvh);

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
		String[] resultNames4cerca = new String[]{"NO_RESULT", "RESULTS",
				"VIEW_RICERCA_NO_RESULT"};

		ICommand[] actionsForResults4cerca = new ICommand[3];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resno_resultActionstep_0_on = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca"};

		String[] act_actions_clicked_cerca_resno_resultActionstep_0_off = new String[]{
				"btPulisci", "tRicerca", "bpDettaglio"};

		String[] act_actions_clicked_cerca_resno_resultActionstep_0_show = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"btPulisci"};

		String[] act_actions_clicked_cerca_resno_resultActionstep_0_hide = new String[]{
				"tRicerca", "bpDettaglio"};

		ScreenStateCommand act_actions_clicked_cerca_resno_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneGiornaleEventi", "VIEW_INIZIALE",
				act_actions_clicked_cerca_resno_resultActionstep_0_on,
				act_actions_clicked_cerca_resno_resultActionstep_0_off,
				act_actions_clicked_cerca_resno_resultActionstep_0_show,
				act_actions_clicked_cerca_resno_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resno_resultAction_0_steps = new ICommand[]{act_actions_clicked_cerca_resno_resultActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resno_resultAction_0 = new SequenceCommand(
				act_actions_clicked_cerca_resno_resultAction_0_steps);
		// SequenceCommand end
		actionsForResults4cerca[0] = act_actions_clicked_cerca_resno_resultAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resresultsActionstep_0_on = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "bpDettaglio", "btPulisci"};

		String[] act_actions_clicked_cerca_resresultsActionstep_0_off = new String[]{};

		String[] act_actions_clicked_cerca_resresultsActionstep_0_show = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "bpDettaglio", "btPulisci"};

		String[] act_actions_clicked_cerca_resresultsActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_cerca_resresultsActionstep_0 = new ScreenStateCommand(
				"cpGestioneGiornaleEventi", "VIEW_RICERCA_OK",
				act_actions_clicked_cerca_resresultsActionstep_0_on,
				act_actions_clicked_cerca_resresultsActionstep_0_off,
				act_actions_clicked_cerca_resresultsActionstep_0_show,
				act_actions_clicked_cerca_resresultsActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resresultsAction_1_steps = new ICommand[]{act_actions_clicked_cerca_resresultsActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resresultsAction_1 = new SequenceCommand(
				act_actions_clicked_cerca_resresultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4cerca[1] = act_actions_clicked_cerca_resresultsAction_1;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_on = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "btPulisci"};

		String[] act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_off = new String[]{"bpDettaglio"};

		String[] act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_show = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "btPulisci"};

		String[] act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_hide = new String[]{"bpDettaglio"};

		ScreenStateCommand act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0 = new ScreenStateCommand(
				"cpGestioneGiornaleEventi",
				"VIEW_RICERCA_NO_RESULT",
				act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_on,
				act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_off,
				act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_show,
				act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_cerca_resview_ricerca_no_resultAction_2_steps = new ICommand[]{act_actions_clicked_cerca_resview_ricerca_no_resultActionstep_0};
		SequenceCommand act_actions_clicked_cerca_resview_ricerca_no_resultAction_2 = new SequenceCommand(
				act_actions_clicked_cerca_resview_ricerca_no_resultAction_2_steps);
		// SequenceCommand end
		actionsForResults4cerca[2] = act_actions_clicked_cerca_resview_ricerca_no_resultAction_2;

		ExecCommand act_actions_clicked_cerca_1 = new ExecCommand(
				resultNames4cerca, actionsForResults4cerca) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cerca((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGiornaleEventiAction",
							"readOne()", "chiamata verso BackEnd", "cerca");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cerca]:"
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

	private ICommand initCommandBtPulisci_CLICKED() {
		// ExecCommand begin
		String[] resultNames4clear = new String[]{"PULISCI"};

		ICommand[] actionsForResults4clear = new ICommand[1];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btPulisci_respulisciActionstep_0_appdataToBeRemovedFromSession = new String[]{
				"ricercaGiornaleEventi", "listaGiornaliEventi"};

		ClearAppDataCommand act_actions_clicked_btPulisci_respulisciActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btPulisci_respulisciActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end

		ICommand[] act_actions_clicked_btPulisci_respulisciAction_0_steps = new ICommand[]{act_actions_clicked_btPulisci_respulisciActionstep_0};
		SequenceCommand act_actions_clicked_btPulisci_respulisciAction_0 = new SequenceCommand(
				act_actions_clicked_btPulisci_respulisciAction_0_steps);
		// SequenceCommand end
		actionsForResults4clear[0] = act_actions_clicked_btPulisci_respulisciAction_0;

		ExecCommand act_actions_clicked_btPulisci_1 = new ExecCommand(
				resultNames4clear, actionsForResults4clear) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.clear((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGiornaleEventiAction",
							"readOne()", "chiamata verso BackEnd", "clear");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [clear]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btPulisci_1;
	}

	private ICommand initCommandBpDettaglio_CLICKED() {
		// ExecCommand begin
		String[] resultNames4vaiAlDettaglio = new String[]{"OK", "KO"};

		ICommand[] actionsForResults4vaiAlDettaglio = new ICommand[2];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_bpDettaglio_resokActionstep_0 = new JumpCommand(
				"cpDettaglioGiornaleEventi", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_bpDettaglio_resokAction_0_steps = new ICommand[]{act_actions_clicked_bpDettaglio_resokActionstep_0};
		SequenceCommand act_actions_clicked_bpDettaglio_resokAction_0 = new SequenceCommand(
				act_actions_clicked_bpDettaglio_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4vaiAlDettaglio[0] = act_actions_clicked_bpDettaglio_resokAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_bpDettaglio_reskoActionstep_0_on = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "bpDettaglio", "btPulisci"};

		String[] act_actions_clicked_bpDettaglio_reskoActionstep_0_off = new String[]{};

		String[] act_actions_clicked_bpDettaglio_reskoActionstep_0_show = new String[]{
				"mnuView", "iuv", "dataEvento", "identificativoDominio",
				"identificativoFruitore", "codicePagamento", "cerca",
				"tRicerca", "bpDettaglio", "btPulisci"};

		String[] act_actions_clicked_bpDettaglio_reskoActionstep_0_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_bpDettaglio_reskoActionstep_0 = new ScreenStateCommand(
				"cpGestioneGiornaleEventi", "VIEW_RICERCA_OK",
				act_actions_clicked_bpDettaglio_reskoActionstep_0_on,
				act_actions_clicked_bpDettaglio_reskoActionstep_0_off,
				act_actions_clicked_bpDettaglio_reskoActionstep_0_show,
				act_actions_clicked_bpDettaglio_reskoActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_bpDettaglio_reskoAction_1_steps = new ICommand[]{act_actions_clicked_bpDettaglio_reskoActionstep_0};
		SequenceCommand act_actions_clicked_bpDettaglio_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_bpDettaglio_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4vaiAlDettaglio[1] = act_actions_clicked_bpDettaglio_reskoAction_1;

		ExecCommand act_actions_clicked_bpDettaglio_1 = new ExecCommand(
				resultNames4vaiAlDettaglio, actionsForResults4vaiAlDettaglio) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.vaiAlDettaglio(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGiornaleEventiAction",
							"readOne()", "chiamata verso BackEnd",
							"vaiAlDettaglio");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [vaiAlDettaglio]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_bpDettaglio_1;
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
