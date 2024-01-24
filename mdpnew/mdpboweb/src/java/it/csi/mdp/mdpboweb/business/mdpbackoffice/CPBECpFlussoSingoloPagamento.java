/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business.mdpbackoffice;

import java.util.*;

import it.csi.mdp.mdpboweb.dto.*;
import it.csi.mdp.mdpboweb.dto.common.*;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.*;

import org.apache.log4j.*;
import it.csi.mdp.mdpboweb.util.*;
import it.csi.mdp.mdpboweb.business.*;

/*PROTECTED REGION ID(R1873084353) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;
/*PROTECTED REGION END*/

public class CPBECpFlussoSingoloPagamento {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_FLUSSOSINGOLOPAGAMENTO_CODE = "appDataflussoSingoloPagamento";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LISTAFLUSSOSINGOLOPAGAMENTO_CODE = "appDatalistaFlussoSingoloPagamento";

	public final static String APPDATA_RICERCAFLUSSOSINGOLOPAGAMENTO_CODE = "appDataricercaFlussoSingoloPagamento";

	public final static String APPDATA_SELETTOREFLUSSOSINGOLOPAGAMENTO_CODE = "appDataselettoreFlussoSingoloPagamento";

	public final static String APPDATA_SELETTOREFLUSSORIVERSAMENTO_CODE = "appDataselettoreFlussoRiversamento";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_FLUSSORIVERSAMENTO_CODE = "appDataflussoRiversamento";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpFlussoSingoloPagamento";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricercaSingliFlussi(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String RICERCASINGLIFLUSSI_OUTCOME_CODE__RESULT = "result";
		final String RICERCASINGLIFLUSSI_OUTCOME_CODE__NO_RESULT = "no_result";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1927739647) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			log.info("[CPBECpFlussoSingoloPagamento::ricercaSingliFlussi] START");

			UserInfoExt user = theModel.getAppDatauserInfoExt();

			log.info("[CPBECpFlussoSingoloPagamento::ricercaSingliFlussi] 1 "
					+ theModel.getAppDataricercaFlussoSingoloPagamento()
							.getDataregolamentoDA());
			log.info("[CPBECpFlussoSingoloPagamento::ricercaSingliFlussi] 2 "
					+ theModel.getAppDataricercaFlussoSingoloPagamento()
							.getDataregolamentoA());
			log.info("[CPBECpFlussoSingoloPagamento::ricercaSingliFlussi] 3 "
					+ theModel.getAppDataricercaFlussoSingoloPagamento()
							.getApplicationId());

			ArrayList<FlussoSingoloPagamento> lista = getGestoreMDPNODONAZIONALEBackOffice()
					.getFlussoSingoloPagamentoByParam(
							user,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							theModel.getAppDataricercaFlussoSingoloPagamento()
									.getApplicationId(),
							theModel.getAppDataricercaFlussoSingoloPagamento()
									.getDataregolamentoDA(),
							theModel.getAppDataricercaFlussoSingoloPagamento()
									.getDataregolamentoA());

			log.info("[CPBECpFlussoSingoloPagamento::ricercaSingliFlussi] lista "
					+ lista.size());

			theModel.setAppDatalistaFlussoSingoloPagamento(lista);

			// impostazione del result code 
			result.setResultCode(RICERCASINGLIFLUSSI_OUTCOME_CODE__RESULT);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			log.info("[CPBECpFlussoSingoloPagamento::ricercaSingliFlussi] STOP");
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::ricercaSingliFlussi] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goToTestata(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String GOTOTESTATA_OUTCOME_CODE__VAITESTATA = "vaiTestata";
		final String GOTOTESTATA_OUTCOME_CODE__NO_SELECTION = "NO_SELECTION";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1164558821) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			theModel.setAppDataisPostBack(null);

			//log.info("[CPBECpFlussoSingoloPagamento::goToTestata] theModel.getAppDataselettoreFlussoRiversamento() "+ theModel.getAppDataselettoreFlussoRiversamento());
			if (theModel.getAppDataselettoreFlussoRiversamento() == null) {
				//log.info("[CPBECpFlussoSingoloPagamento::goToTestata] theModel.getAppDataselettoreFlussoSingoloPagamento() "+ theModel.getAppDataselettoreFlussoSingoloPagamento());
				if (theModel.getAppDataselettoreFlussoSingoloPagamento() == null) {
					PropertiesUtil pu = new PropertiesUtil();
					result.getGlobalErrors().add(pu.getMessage("SEL-E-0001"));
					//log.info("[CPBECpFlussoSingoloPagamento::goToTestata] passo dalla non selezione ");
					// impostazione del result code 
					result.setResultCode(GOTOTESTATA_OUTCOME_CODE__NO_SELECTION);
					result.setModel(theModel);
					return result;
				} else {
					//log.info("[CPBECpFlussoSingoloPagamento::goToTestata] ci siamooooooo");
					UserInfoExt user = theModel.getAppDatauserInfoExt();
					ArrayList<FlussoSingoloPagamento> lista = getGestoreMDPNODONAZIONALEBackOffice()
							.getFlussoSingoloPagamentoByParam(
									user,
									Integer.parseInt(theModel
											.getAppDataselettoreFlussoSingoloPagamento()),
									null, null, null, null, null, null, null,
									null, null, null, null);

					log.info("[CPBECpFlussoSingoloPagamento::goToTestata] lista size "
							+ lista.size());
					log.info("[CPBECpFlussoSingoloPagamento::goToTestata] lista.get(0).getIdFlusso() "
							+ lista.get(0).getIdFlusso());
					theModel.setAppDataselettoreFlussoRiversamento(String
							.valueOf(lista.get(0).getIdFlusso()));
				}
			}

			// impostazione del result code 
			result.setResultCode(GOTOTESTATA_OUTCOME_CODE__VAITESTATA);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::goToTestata] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__RESULT = "RESULT";
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "INIZIALE";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R49629585) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");
				UserInfoExt user = theModel.getAppDatauserInfoExt();

				//carico la combo
				ArrayList<Applicazione> listApp = getGestoreMDPBackOffice()
						.listApplicationByFlussoApplicazione(user);

				log.info("[CPBECpFlussoSingoloPagamento::refreshPanel] listApp size "
						+ listApp.size());

				theModel.setAppDataapplicazioni(listApp);

				String idFlusso = theModel
						.getAppDataselettoreFlussoRiversamento();

				log.info("[CPBECpFlussoSingoloPagamento::refreshPanel] idFlusso "
						+ idFlusso);

				if (idFlusso != null && !idFlusso.trim().equals("")) {

					ArrayList<FlussoSingoloPagamento> lista = getGestoreMDPNODONAZIONALEBackOffice()
							.getFlussoSingoloPagamentoByParam(user, null,
									Integer.parseInt(idFlusso), null, null,
									null, null, null, null, null, null, null,
									null);
					theModel.setAppDatalistaFlussoSingoloPagamento(lista);

					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RESULT);
				} else {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);
				}
			} else {
				// impostazione del result code
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RESULT);
				log.info("[CPBECpFlussoSingoloPagamento::refreshPanel] REFRESHPANEL_OUTCOME_CODE__RESULT");
			}
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::refreshPanel] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi statici per il reset delle tabelle
	//////////////////////////////////////////////////////////////////////////////

	public static void resetClearStatus_widg_tbRicerca(java.util.Map session) {
		session.put("cpFlussoSingoloPagamento_tbRicerca_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-740271661) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice = null;

	public GestoreMDPNODONAZIONALEBackOffice getGestoreMDPNODONAZIONALEBackOffice() {
		return gestoreMDPNODONAZIONALEBackOffice;
	}

	public void setGestoreMDPNODONAZIONALEBackOffice(
			GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice) {
		this.gestoreMDPNODONAZIONALEBackOffice = gestoreMDPNODONAZIONALEBackOffice;
	}

	GestoreMDPBackOffice gestoreMDPBackOffice = null;

	public GestoreMDPBackOffice getGestoreMDPBackOffice() {
		return gestoreMDPBackOffice;
	}

	public void setGestoreMDPBackOffice(
			GestoreMDPBackOffice gestoreMDPBackOffice) {
		this.gestoreMDPBackOffice = gestoreMDPBackOffice;
	}
	/*PROTECTED REGION END*/
}
