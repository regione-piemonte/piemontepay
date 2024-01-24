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

/*PROTECTED REGION ID(R-745592117) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
/*PROTECTED REGION END*/

public class CPBECpGestioneUtenti {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_UTENTE_CODE = "appDatautente";

	public final static String APPDATA_UTENTI_CODE = "appDatautenti";

	public final static String APPDATA_SELETTOREUTENTE_CODE = "appDataselettoreUtente";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_SELETTOREIDRUOLO_CODE = "appDataselettoreIdRuolo";

	public final static String APPDATA_RUOLI_CODE = "appDataruoli";

	public final static String APPDATA_RUOLO_CODE = "appDataruolo";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_SELETTOREIDGRUPPO_CODE = "appDataselettoreIdGruppo";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneUtenti";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goToNuovoUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String GOTONUOVOUTENTE_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-558226223) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			//UserInfoExt user = theModel.getAppDatauserInfoExt();
			theModel.setAppDatautente(new Utente());
			// impostazione del result code
			result.setResultCode(GOTONUOVOUTENTE_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::goToNuovoUtente] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAUTENTE_OUTCOME_CODE__OK = "OK";
		final String MODIFICAUTENTE_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1512896911) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			String idUtenteSelezionato = theModel.getAppDataselettoreUtente();

			if (idUtenteSelezionato == null
					|| idUtenteSelezionato.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0001"));
				result.setResultCode(MODIFICAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			Utente utente = getGestoreMDPBackOffice().getMdpUsersById(user,
					idUtenteSelezionato);
			theModel.setAppDatautente(utente);
			log.info("seleziono il selettore del gruppo  "
					+ utente.getIdGruppo());
			theModel.setAppDataselettoreIdGruppo(utente.getIdGruppo());

			// impostazione del result code 
			result.setResultCode(MODIFICAUTENTE_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaUtente] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CANCELLAUTENTE_OUTCOME_CODE__OK = "Ok";
		final String CANCELLAUTENTE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R488337480) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			String idUtenteSelezionato = theModel.getAppDataselettoreUtente();
			String rif = getGestoreMDPBackOffice().deleteMdpBckUser(user,
					idUtenteSelezionato);
			if (!rif.equals("")) {
				result.getGlobalErrors().add(rif);
				result.setResultCode(CANCELLAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			ArrayList<Utente> listaUtenti = theModel.getAppDatautenti();
			for (int r = 0; r < listaUtenti.size(); r++) {
				Utente sel = listaUtenti.get(r);
				if (sel.getIdUtente().equals(idUtenteSelezionato)) {
					listaUtenti.remove(r);
				}
			}
			theModel.setAppDatautenti(listaUtenti);
			result.getGlobalMessages().add(
					pu.getMessage("cancellazioneOk.message"));
			// impostazione del result code 
			result.setResultCode(CANCELLAUTENTE_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cancellaUtente] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__NO_RESULT = "No_Result";
		final String REFRESHPANEL_OUTCOME_CODE__SOMERESULT = "SomeResult";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R209892423) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				ArrayList<Utente> listaUtenti = getGestoreMDPBackOffice()
						.getListaUtentiByUser(user);
				theModel.setAppDatautenti(listaUtenti);
				// impostazione del result code
				if (listaUtenti.size() > 0) {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__SOMERESULT);
				} else {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__NO_RESULT);
				}
				theModel.setAppDataisPostBack("S");
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__SOMERESULT);
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

	public static void resetClearStatus_widg_tListaUtenti(java.util.Map session) {
		session.put("cpGestioneUtenti_tListaUtenti_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R458317193) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
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
