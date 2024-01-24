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

/*PROTECTED REGION ID(R-2058613527) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.util.PropertiesUtil;
/*PROTECTED REGION END*/

public class CPBECpGestioneApplicazioni {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_APPLICAZIONE_CODE = "appDataapplicazione";

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_SELETTOREIDAPPLICAZIONE_CODE = "appDataselettoreIdApplicazione";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneApplicazioni";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String NUOVAAPPLICAZIONE_OUTCOME_CODE__OK = "Ok";
		final String NUOVAAPPLICAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1548523912) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			Applicazione newAplicazione = new Applicazione();
			theModel.setAppDataapplicazione(newAplicazione);
			// impostazione del result code
			result.setResultCode(NUOVAAPPLICAZIONE_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::nuovaApplicazione] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAAPPLICAZIONE_OUTCOME_CODE__OK = "Ok";
		final String MODIFICAAPPLICAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1138016463) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idApplicazione = theModel
					.getAppDataselettoreIdApplicazione();
			if (idApplicazione == null || idApplicazione.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0007"));;
				result.setResultCode(MODIFICAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			} else {
				result.setResultCode(MODIFICAAPPLICAZIONE_OUTCOME_CODE__OK);
			}
			Applicazione aplicazioneSelezionata = getGestoreMDPBackOffice()
					.getApplicazioneById(user, idApplicazione);
			theModel.setAppDataapplicazione(aplicazioneSelezionata);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaApplicazione] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CANCELLAAPPLICAZIONE_OUTCOME_CODE__OK = "Ok";
		final String CANCELLAAPPLICAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-168589112) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idApplicazione = theModel
					.getAppDataselettoreIdApplicazione();

			String in = getGestoreMDPBackOffice()
					.deleteApplicationConfiguration(user, idApplicazione);
			if (!in.equals("")) {
				result.getGlobalErrors().add(in);
				result.setResultCode(CANCELLAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			//ricarico la tabella
			ArrayList<Applicazione> aApp = new ArrayList<Applicazione>();
			aApp = getGestoreMDPBackOffice().getListaApplicazioniByUser(user);
			theModel.setAppDataapplicazioni(aApp);
			// impostazione del result code 
			result.setResultCode(CANCELLAAPPLICAZIONE_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cancellaApplicazione] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE = "Ok_Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS = "Ok_No_Results";
		final String REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-401214615) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			ArrayList<Applicazione> aApp = new ArrayList<Applicazione>();
			aApp = getGestoreMDPBackOffice().getListaApplicazioniByUser(user);
			theModel.setAppDataapplicazioni(aApp);
			if (theModel.getAppDataapplicazioni().size() == 0) {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS);
			}
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

	public static void resetClearStatus_widg_tListaApplicazioni(
			java.util.Map session) {
		session.put("cpGestioneApplicazioni_tListaApplicazioni_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1436300395) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPBackOffice gestoreMDPBackOffice = null;

	public GestoreMDPBackOffice getGestoreMDPBackOffice() {
		return gestoreMDPBackOffice;
	}

	public void setGestoreMDPRPTRTBackOffice(
			GestoreMDPBackOffice gestoreMDPBackOffice) {
		this.gestoreMDPBackOffice = gestoreMDPBackOffice;
	}

	/*PROTECTED REGION END*/
}
