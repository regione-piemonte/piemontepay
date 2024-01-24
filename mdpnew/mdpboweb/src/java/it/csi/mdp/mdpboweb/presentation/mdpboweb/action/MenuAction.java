/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.*;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.validator.annotations.*;

import it.csi.mdp.mdpboweb.dto.*;
import it.csi.mdp.mdpboweb.business.*;
import it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor.FatClientOnly;

/**
 * MenuAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
public class MenuAction extends BaseAction {

	/**
	 * classe model associata
	 */
	public Class modelClass() {
		return it.csi.mdp.mdpboweb.dto.GlobalMenuModel.class;
	}

	/**
	 *
	 * @return Il risultato delle azioni, SUCCESS altrimenti.
	 */
	@SkipValidation
	public String execute() throws CommandExecutionException {
		return SUCCESS;
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuApplicazioni() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuApplicazioni", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuApplicazioni] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuApplicazioni");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuApplicazioni] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuApplicazioni");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuGWModApp() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuGWModApp", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGWModApp] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuGWModApp");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGWModApp] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuGWModApp");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuEnti() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuEnti", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuEnti] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuEnti");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuEnti] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuEnti");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuTransazioni() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuTransazioni", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuTransazioni] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuTransazioni");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuTransazioni] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuTransazioni");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuStatistiche() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuStatistiche", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuStatistiche] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuStatistiche");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuStatistiche] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuStatistiche");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuAuditing() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuAuditing", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuAuditing] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuAuditing");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuAuditing] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuAuditing");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuGestConfig() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuGestConfig", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGestConfig] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuGestConfig");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGestConfig] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuGestConfig");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuGestioneGruppi() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuGestioneGruppi", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGestioneGruppi] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuGestioneGruppi");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGestioneGruppi] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuGestioneGruppi");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuGestioneUtenti() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuGestioneUtenti", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGestioneUtenti] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuGestioneUtenti");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGestioneUtenti] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuGestioneUtenti");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuStoricoErrori() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuStoricoErrori", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuStoricoErrori] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuStoricoErrori");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuStoricoErrori] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuStoricoErrori");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuGiornaleEventi() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuGiornaleEventi", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGiornaleEventi] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuGiornaleEventi");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuGiornaleEventi] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuGiornaleEventi");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuRichiestaPagamentoTelematico()
			throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuRichiestaPagamentoTelematico",
				"CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuRichiestaPagamentoTelematico] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuRichiestaPagamentoTelematico");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuRichiestaPagamentoTelematico] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuRichiestaPagamentoTelematico");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuRicevutaTelematica() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuRicevutaTelematica", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuRicevutaTelematica] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuRicevutaTelematica");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuRicevutaTelematica] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuRicevutaTelematica");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuInformativaPsp() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuInformativaPsp", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuInformativaPsp] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuInformativaPsp");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuInformativaPsp] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuInformativaPsp");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuFlussoRiversamento() throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuFlussoRiversamento", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuFlussoRiversamento] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuFlussoRiversamento");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuFlussoRiversamento] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuFlussoRiversamento");
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @return SUCCESS result.
	 */
	public String goToMnuSingoloFlussoPagamento()
			throws CommandExecutionException {

		// gestione degli eventi di tipo CLICKED
		ICommand action = initCommand("mnuSingoloFlussoPagamento", "CLICKED");
		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuSingoloFlussoPagamento] returning result ["
						+ result + "]");
			}
			setActiveMenu("mnuSingoloFlussoPagamento");
			return result;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("[MenuAction::goToMnuSingoloFlussoPagamento] returning default result [SUCCESS]");
			}
			setActiveMenu("mnuSingoloFlussoPagamento");
			return SUCCESS;
		}
	}

	public static final String ACTIVE_MENU_ATTRNAME = "active_menu";

	public void setActiveMenu(String menuName) {
		session.put(ACTIVE_MENU_ATTRNAME, "menu_items_" + menuName);
	}

	/**
	 * inizializza la struttura dei command da eseguire per ciascun event handler 
	 * di ciascun widget
	 */
	private ICommand initCommand(String sourceWidget, String eventType) {
		HashMap<String, HashMap<String, ICommand>> cmdsByWidget = new HashMap<String, HashMap<String, ICommand>>();

		// contiene i comandi del menu mnuApplicazioni per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuApplicazioniByEvh = new HashMap<String, ICommand>();

		cmds4mnuApplicazioniByEvh.put("CLICKED",
				initCommandMnuApplicazioni_CLICKED());
		cmdsByWidget.put("mnuApplicazioni", cmds4mnuApplicazioniByEvh);
		// contiene i comandi del menu mnuGWModApp per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuGWModAppByEvh = new HashMap<String, ICommand>();

		cmds4mnuGWModAppByEvh.put("CLICKED", initCommandMnuGWModApp_CLICKED());
		cmdsByWidget.put("mnuGWModApp", cmds4mnuGWModAppByEvh);
		// contiene i comandi del menu mnuEnti per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuEntiByEvh = new HashMap<String, ICommand>();

		cmds4mnuEntiByEvh.put("CLICKED", initCommandMnuEnti_CLICKED());
		cmdsByWidget.put("mnuEnti", cmds4mnuEntiByEvh);
		// contiene i comandi del menu mnuTransazioni per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuTransazioniByEvh = new HashMap<String, ICommand>();

		cmds4mnuTransazioniByEvh.put("CLICKED",
				initCommandMnuTransazioni_CLICKED());
		cmdsByWidget.put("mnuTransazioni", cmds4mnuTransazioniByEvh);
		// contiene i comandi del menu mnuStatistiche per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuStatisticheByEvh = new HashMap<String, ICommand>();

		cmds4mnuStatisticheByEvh.put("CLICKED",
				initCommandMnuStatistiche_CLICKED());
		cmdsByWidget.put("mnuStatistiche", cmds4mnuStatisticheByEvh);
		// contiene i comandi del menu mnuAuditing per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuAuditingByEvh = new HashMap<String, ICommand>();

		cmds4mnuAuditingByEvh.put("CLICKED", initCommandMnuAuditing_CLICKED());
		cmdsByWidget.put("mnuAuditing", cmds4mnuAuditingByEvh);
		// contiene i comandi del menu mnuGestConfig per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuGestConfigByEvh = new HashMap<String, ICommand>();

		cmds4mnuGestConfigByEvh.put("CLICKED",
				initCommandMnuGestConfig_CLICKED());
		cmdsByWidget.put("mnuGestConfig", cmds4mnuGestConfigByEvh);
		// contiene i comandi del menu mnuGestioneGruppi per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuGestioneGruppiByEvh = new HashMap<String, ICommand>();

		cmds4mnuGestioneGruppiByEvh.put("CLICKED",
				initCommandMnuGestioneGruppi_CLICKED());
		cmdsByWidget.put("mnuGestioneGruppi", cmds4mnuGestioneGruppiByEvh);
		// contiene i comandi del menu mnuGestioneUtenti per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuGestioneUtentiByEvh = new HashMap<String, ICommand>();

		cmds4mnuGestioneUtentiByEvh.put("CLICKED",
				initCommandMnuGestioneUtenti_CLICKED());
		cmdsByWidget.put("mnuGestioneUtenti", cmds4mnuGestioneUtentiByEvh);
		// contiene i comandi del menu mnuStoricoErrori per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuStoricoErroriByEvh = new HashMap<String, ICommand>();

		cmds4mnuStoricoErroriByEvh.put("CLICKED",
				initCommandMnuStoricoErrori_CLICKED());
		cmdsByWidget.put("mnuStoricoErrori", cmds4mnuStoricoErroriByEvh);
		// contiene i comandi del menu mnuGiornaleEventi per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuGiornaleEventiByEvh = new HashMap<String, ICommand>();

		cmds4mnuGiornaleEventiByEvh.put("CLICKED",
				initCommandMnuGiornaleEventi_CLICKED());
		cmdsByWidget.put("mnuGiornaleEventi", cmds4mnuGiornaleEventiByEvh);
		// contiene i comandi del menu mnuRichiestaPagamentoTelematico per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuRichiestaPagamentoTelematicoByEvh = new HashMap<String, ICommand>();

		cmds4mnuRichiestaPagamentoTelematicoByEvh.put("CLICKED",
				initCommandMnuRichiestaPagamentoTelematico_CLICKED());
		cmdsByWidget.put("mnuRichiestaPagamentoTelematico",
				cmds4mnuRichiestaPagamentoTelematicoByEvh);
		// contiene i comandi del menu mnuRicevutaTelematica per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuRicevutaTelematicaByEvh = new HashMap<String, ICommand>();

		cmds4mnuRicevutaTelematicaByEvh.put("CLICKED",
				initCommandMnuRicevutaTelematica_CLICKED());
		cmdsByWidget.put("mnuRicevutaTelematica",
				cmds4mnuRicevutaTelematicaByEvh);
		// contiene i comandi del menu mnuInformativaPsp per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuInformativaPspByEvh = new HashMap<String, ICommand>();

		cmds4mnuInformativaPspByEvh.put("CLICKED",
				initCommandMnuInformativaPsp_CLICKED());
		cmdsByWidget.put("mnuInformativaPsp", cmds4mnuInformativaPspByEvh);
		// contiene i comandi del menu mnuFlussoRiversamento per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuFlussoRiversamentoByEvh = new HashMap<String, ICommand>();

		cmds4mnuFlussoRiversamentoByEvh.put("CLICKED",
				initCommandMnuFlussoRiversamento_CLICKED());
		cmdsByWidget.put("mnuFlussoRiversamento",
				cmds4mnuFlussoRiversamentoByEvh);
		// contiene i comandi del menu mnuSingoloFlussoPagamento per ogni Ev.H.
		HashMap<String, ICommand> cmds4mnuSingoloFlussoPagamentoByEvh = new HashMap<String, ICommand>();

		cmds4mnuSingoloFlussoPagamentoByEvh.put("CLICKED",
				initCommandMnuSingoloFlussoPagamento_CLICKED());
		cmdsByWidget.put("mnuSingoloFlussoPagamento",
				cmds4mnuSingoloFlussoPagamentoByEvh);

		return cmdsByWidget.get(sourceWidget).get(eventType);
	}

	private ICommand initCommandMnuApplicazioni_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuApplicazionistep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "selettoreOperazione", "selettoreIdApplicazione",
				"selettoreIdGateway", "selettoreIdPaymentMode",
				"selettoreChiaveAttr", "extraAttributes",
				"nuovoExtraAttribute", "enti", "ibanEnteApplication",
				"keyValue", "listaEnti", "listaIbanEnteApplication",
				"selettoreIbanEnteApplication", "selettoreIdEnte"};

		ClearAppDataCommand act_actions_clicked_mnuApplicazionistep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuApplicazionistep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuApplicazionistep_1 = new JumpCommand(
				"cpGestioneApplicazioni", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuApplicazioni_1_steps = new ICommand[]{
				act_actions_clicked_mnuApplicazionistep_0,
				act_actions_clicked_mnuApplicazionistep_1};
		SequenceCommand act_actions_clicked_mnuApplicazioni_1 = new SequenceCommand(
				act_actions_clicked_mnuApplicazioni_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuApplicazioni_1;
	}
	private ICommand initCommandMnuGWModApp_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuGWModAppstep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "selettoreChiaveAttr", "extraAttributes",
				"nuovoExtraAttribute", "selettoreIdGateway",
				"selettoreIdGateway2", "divise", "diviseClone", "lingue",
				"lingueClone", "extraAttributesNuovoGateway"};

		ClearAppDataCommand act_actions_clicked_mnuGWModAppstep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuGWModAppstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuGWModAppstep_1 = new JumpCommand(
				"cpGestioneGW_PM", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuGWModApp_1_steps = new ICommand[]{
				act_actions_clicked_mnuGWModAppstep_0,
				act_actions_clicked_mnuGWModAppstep_1};
		SequenceCommand act_actions_clicked_mnuGWModApp_1 = new SequenceCommand(
				act_actions_clicked_mnuGWModApp_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuGWModApp_1;
	}
	private ICommand initCommandMnuEnti_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuEntistep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "enti", "listaEnti", "ricercaEnti",
				"selettoreIdEnte"};

		ClearAppDataCommand act_actions_clicked_mnuEntistep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuEntistep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuEntistep_1 = new JumpCommand(
				"cpRicercaEnti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuEnti_1_steps = new ICommand[]{
				act_actions_clicked_mnuEntistep_0,
				act_actions_clicked_mnuEntistep_1};
		SequenceCommand act_actions_clicked_mnuEnti_1 = new SequenceCommand(
				act_actions_clicked_mnuEnti_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuEnti_1;
	}
	private ICommand initCommandMnuTransazioni_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuTransazionistep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "selettoreOperazione",
				"filtroRicercaStatisticaApplicazioneTransazione"};

		ClearAppDataCommand act_actions_clicked_mnuTransazionistep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuTransazionistep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuTransazionistep_1 = new JumpCommand(
				"cpGestioneTransazioni", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuTransazioni_1_steps = new ICommand[]{
				act_actions_clicked_mnuTransazionistep_0,
				act_actions_clicked_mnuTransazionistep_1};
		SequenceCommand act_actions_clicked_mnuTransazioni_1 = new SequenceCommand(
				act_actions_clicked_mnuTransazioni_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuTransazioni_1;
	}
	private ICommand initCommandMnuStatistiche_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuStatistichestep_0_appdataToBeRemovedFromSession = new String[]{
				"statisticaApplicazioneTransazione",
				"listaStatisticaApplicazioneTransazione"};

		ClearAppDataCommand act_actions_clicked_mnuStatistichestep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuStatistichestep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuStatistichestep_1 = new JumpCommand(
				"cpStatisticheTranzazioni", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuStatistiche_1_steps = new ICommand[]{
				act_actions_clicked_mnuStatistichestep_0,
				act_actions_clicked_mnuStatistichestep_1};
		SequenceCommand act_actions_clicked_mnuStatistiche_1 = new SequenceCommand(
				act_actions_clicked_mnuStatistiche_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuStatistiche_1;
	}
	private ICommand initCommandMnuAuditing_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuAuditingstep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "audit", "audities", "azione", "azioniForAudit",
				"azioniForAuditFiltered", "azioni", "ricercaAudit",
				"utentiForAudit", "utentiForAuditFiltered", "applicazioni",
				"applicazione", "filteredApplication", "appForAudit",
				"appForAuditFiltered", "appForGroup", "appForGroupFiltered",
				"associazioneGW_MP", "associazioniGW_MP", "AzioneGateway",
				"cercaErrore", "chkAbilitaAssGW_MP", "codiciEsitoPagamento",
				"divisa", "divise", "diviseClone", "enti", "errore", "errori",
				"extraAttributes", "extraAttributesNuovoGateway",
				"filteredApplicationGroup", "filteredAzioni", "filteredUtenti",
				"filtroRicercaInformativePsp",
				"filtroRicercaStatisticaApplicazioneTransazione",
				"flussoRiversamento", "flussoSingoloPagamento", "gateway",
				"gateways", "giornaleEventi", "gruppi", "gruppiApplicazioni",
				"gruppiruoli", "gruppo", "gruppoApplicazione", "grupporuolo",
				"informativePsp", "lastWhereClause", "lingua", "lingue",
				"lingueClone", "listaCodiciEsitoPagamento", "listaEnti",
				"listaFlussoRiversamento", "listaFlussoSingoloPagamento",
				"listaGiornaliEventi", "listaInformativePsp", "listaRPT",
				"listaRT", "listaSingoloStatoRpt",
				"listaSingoloStatoVersamento", "listaStatiAttivazioneEnti",
				"listaStatiRpt", "listaStatisticaApplicazioneTransazione",
				"listaTipoversamento", "nuovaAssociazioneGW_MP",
				"nuovoExtraAttribute", "paginazioneTrans",
				"parametriConfigurazioneBO", "parametroConfigurazioneBO",
				"paymentMode", "paymentModes", "reportCercaTransazioni",
				"ricercaEnti", "ricercaFlussoRiversamento",
				"ricercaFlussoSingoloPagamento", "ricercaGiornaleEventi",
				"ricercaRPT", "ricercaRT", "ricercaTransazione",
				"richiestaPagamentoTelematico", "ruoli", "ruolo",
				"selettoreApp1", "selettoreApp2", "selettoreAppGroup",
				"selettoreAzione1", "selettoreAzione2", "selettoreChiaveAttr",
				"selettoreFlussoRiversamento",
				"selettoreFlussoSingoloPagamento", "selettoreGruppo",
				"selettoreIdApplicazione", "selettoreIdDivisa",
				"selettoreIdEnte", "selettoreIdErrore", "selettoreIdGateway",
				"selettoreIdGateway2", "selettoreIdGiornaleEvento",
				"selettoreIdGruppo", "selettoreIdLingua",
				"selettoreIdPaymentMode", "selettoreIdRPT", "selettoreIdRT",
				"selettoreIdRuolo", "selettoreIdTipologiaCommissione",
				"selettoreIdTransazione", "selettoreInformativePsp",
				"selettoreItemAssociazioneGW_MP", "selettoreOperazione",
				"selettoreParametroConf", "selettoreUtente",
				"selettoreUtente1", "selettoreUtente2", "singoloStatoRPT",
				"singoloStatoVersamento", "statiRpt",
				"statisticaApplicazioneTransazione", "statiTransazione",
				"statiTransazionexCambio", "statoRPTRisposta",
				"statoTransazione", "tipologiaCommissioni", "transazione",
				"transazioni"};

		ClearAppDataCommand act_actions_clicked_mnuAuditingstep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuAuditingstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuAuditingstep_1 = new JumpCommand(
				"cpGestioneAuditing", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuAuditing_1_steps = new ICommand[]{
				act_actions_clicked_mnuAuditingstep_0,
				act_actions_clicked_mnuAuditingstep_1};
		SequenceCommand act_actions_clicked_mnuAuditing_1 = new SequenceCommand(
				act_actions_clicked_mnuAuditing_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuAuditing_1;
	}
	private ICommand initCommandMnuGestConfig_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuGestConfigstep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "selettoreIdTipologiaCommissione",
				"tipologiaCommissioni", "lingua", "selettoreParametroConf"};

		ClearAppDataCommand act_actions_clicked_mnuGestConfigstep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuGestConfigstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuGestConfigstep_1 = new JumpCommand(
				"cpRicercaParametriConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuGestConfig_1_steps = new ICommand[]{
				act_actions_clicked_mnuGestConfigstep_0,
				act_actions_clicked_mnuGestConfigstep_1};
		SequenceCommand act_actions_clicked_mnuGestConfig_1 = new SequenceCommand(
				act_actions_clicked_mnuGestConfig_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuGestConfig_1;
	}
	private ICommand initCommandMnuGestioneGruppi_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuGestioneGruppistep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "selettoreIdRuolo", "gruppo"};

		ClearAppDataCommand act_actions_clicked_mnuGestioneGruppistep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuGestioneGruppistep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuGestioneGruppistep_1 = new JumpCommand(
				"cpGestioneGruppi", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuGestioneGruppi_1_steps = new ICommand[]{
				act_actions_clicked_mnuGestioneGruppistep_0,
				act_actions_clicked_mnuGestioneGruppistep_1};
		SequenceCommand act_actions_clicked_mnuGestioneGruppi_1 = new SequenceCommand(
				act_actions_clicked_mnuGestioneGruppi_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuGestioneGruppi_1;
	}
	private ICommand initCommandMnuGestioneUtenti_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuGestioneUtentistep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_mnuGestioneUtentistep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuGestioneUtentistep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuGestioneUtentistep_1 = new JumpCommand(
				"cpGestioneUtenti", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuGestioneUtenti_1_steps = new ICommand[]{
				act_actions_clicked_mnuGestioneUtentistep_0,
				act_actions_clicked_mnuGestioneUtentistep_1};
		SequenceCommand act_actions_clicked_mnuGestioneUtenti_1 = new SequenceCommand(
				act_actions_clicked_mnuGestioneUtenti_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuGestioneUtenti_1;
	}
	private ICommand initCommandMnuStoricoErrori_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuStoricoErroristep_0_appdataToBeRemovedFromSession = new String[]{
				"cercaErrore", "errore", "errori", "selettoreIdErrore",
				"isPostBack"};

		ClearAppDataCommand act_actions_clicked_mnuStoricoErroristep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuStoricoErroristep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuStoricoErroristep_1 = new JumpCommand(
				"cpStoricoErrori", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuStoricoErrori_1_steps = new ICommand[]{
				act_actions_clicked_mnuStoricoErroristep_0,
				act_actions_clicked_mnuStoricoErroristep_1};
		SequenceCommand act_actions_clicked_mnuStoricoErrori_1 = new SequenceCommand(
				act_actions_clicked_mnuStoricoErrori_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuStoricoErrori_1;
	}
	private ICommand initCommandMnuGiornaleEventi_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuGiornaleEventistep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "giornaleEventi", "listaGiornaliEventi",
				"ricercaGiornaleEventi"};

		ClearAppDataCommand act_actions_clicked_mnuGiornaleEventistep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuGiornaleEventistep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuGiornaleEventistep_1 = new JumpCommand(
				"cpGestioneGiornaleEventi", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuGiornaleEventi_1_steps = new ICommand[]{
				act_actions_clicked_mnuGiornaleEventistep_0,
				act_actions_clicked_mnuGiornaleEventistep_1};
		SequenceCommand act_actions_clicked_mnuGiornaleEventi_1 = new SequenceCommand(
				act_actions_clicked_mnuGiornaleEventi_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuGiornaleEventi_1;
	}
	private ICommand initCommandMnuRichiestaPagamentoTelematico_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuRichiestaPagamentoTelematicostep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "listaRPT", "ricercaRPT", "selettoreIdRPT"};

		ClearAppDataCommand act_actions_clicked_mnuRichiestaPagamentoTelematicostep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuRichiestaPagamentoTelematicostep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuRichiestaPagamentoTelematicostep_1 = new JumpCommand(
				"cpGestioneRPT", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuRichiestaPagamentoTelematico_1_steps = new ICommand[]{
				act_actions_clicked_mnuRichiestaPagamentoTelematicostep_0,
				act_actions_clicked_mnuRichiestaPagamentoTelematicostep_1};
		SequenceCommand act_actions_clicked_mnuRichiestaPagamentoTelematico_1 = new SequenceCommand(
				act_actions_clicked_mnuRichiestaPagamentoTelematico_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuRichiestaPagamentoTelematico_1;
	}
	private ICommand initCommandMnuRicevutaTelematica_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuRicevutaTelematicastep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "ricercaRT", "listaRT", "selettoreIdRT"};

		ClearAppDataCommand act_actions_clicked_mnuRicevutaTelematicastep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuRicevutaTelematicastep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuRicevutaTelematicastep_1 = new JumpCommand(
				"cpGestioneRT", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuRicevutaTelematica_1_steps = new ICommand[]{
				act_actions_clicked_mnuRicevutaTelematicastep_0,
				act_actions_clicked_mnuRicevutaTelematicastep_1};
		SequenceCommand act_actions_clicked_mnuRicevutaTelematica_1 = new SequenceCommand(
				act_actions_clicked_mnuRicevutaTelematica_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuRicevutaTelematica_1;
	}
	private ICommand initCommandMnuInformativaPsp_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuInformativaPspstep_0_appdataToBeRemovedFromSession = new String[]{
				"filtroRicercaInformativePsp", "informativePsp",
				"listaInformativePsp", "selettoreInformativePsp", "isPostBack"};

		ClearAppDataCommand act_actions_clicked_mnuInformativaPspstep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuInformativaPspstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuInformativaPspstep_1 = new JumpCommand(
				"cpInformativePsp", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuInformativaPsp_1_steps = new ICommand[]{
				act_actions_clicked_mnuInformativaPspstep_0,
				act_actions_clicked_mnuInformativaPspstep_1};
		SequenceCommand act_actions_clicked_mnuInformativaPsp_1 = new SequenceCommand(
				act_actions_clicked_mnuInformativaPsp_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuInformativaPsp_1;
	}
	private ICommand initCommandMnuFlussoRiversamento_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuFlussoRiversamentostep_0_appdataToBeRemovedFromSession = new String[]{
				"flussoRiversamento", "flussoSingoloPagamento", "isPostBack",
				"ricercaFlussoSingoloPagamento", "ricercaFlussoRiversamento",
				"selettoreFlussoSingoloPagamento",
				"selettoreFlussoRiversamento", "listaFlussoSingoloPagamento",
				"listaFlussoRiversamento"};

		ClearAppDataCommand act_actions_clicked_mnuFlussoRiversamentostep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuFlussoRiversamentostep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuFlussoRiversamentostep_1 = new JumpCommand(
				"cpFlussoRiversamento", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuFlussoRiversamento_1_steps = new ICommand[]{
				act_actions_clicked_mnuFlussoRiversamentostep_0,
				act_actions_clicked_mnuFlussoRiversamentostep_1};
		SequenceCommand act_actions_clicked_mnuFlussoRiversamento_1 = new SequenceCommand(
				act_actions_clicked_mnuFlussoRiversamento_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuFlussoRiversamento_1;
	}
	private ICommand initCommandMnuSingoloFlussoPagamento_CLICKED() {
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_mnuSingoloFlussoPagamentostep_0_appdataToBeRemovedFromSession = new String[]{
				"flussoRiversamento", "flussoSingoloPagamento", "isPostBack",
				"listaFlussoSingoloPagamento", "listaFlussoRiversamento",
				"ricercaFlussoRiversamento", "ricercaFlussoSingoloPagamento",
				"selettoreFlussoRiversamento",
				"selettoreFlussoSingoloPagamento"};

		ClearAppDataCommand act_actions_clicked_mnuSingoloFlussoPagamentostep_0 = new ClearAppDataCommand(
				act_actions_clicked_mnuSingoloFlussoPagamentostep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_mnuSingoloFlussoPagamentostep_1 = new JumpCommand(
				"cpFlussoSingoloPagamento", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_mnuSingoloFlussoPagamento_1_steps = new ICommand[]{
				act_actions_clicked_mnuSingoloFlussoPagamentostep_0,
				act_actions_clicked_mnuSingoloFlussoPagamentostep_1};
		SequenceCommand act_actions_clicked_mnuSingoloFlussoPagamento_1 = new SequenceCommand(
				act_actions_clicked_mnuSingoloFlussoPagamento_1_steps);
		// SequenceCommand end
		return act_actions_clicked_mnuSingoloFlussoPagamento_1;
	}

	/**
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R-963475072) ENABLED START*/
		/* Inserire la validazione */
		/*PROTECTED REGION END*/
	}

	/**
	 *	Metodo per la rimozione dalla sessione degli application data a scope
	 *  SAME_PAGE. 
	 */
	public void clearPageScopedAppData(String targetContentPanelName) {
		// non sapendo quale content panel si sta abbandonando vengono rimossi tutti
		// gli appdata a scope PAGE

	}
}
