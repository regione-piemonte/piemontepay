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

/*PROTECTED REGION ID(R540382448) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;

/*PROTECTED REGION END*/

public class CPBECpDettaglioUtenti {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_UTENTE_CODE = "appDatautente";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_RUOLI_CODE = "appDataruoli";

	public final static String APPDATA_RUOLO_CODE = "appDataruolo";

	public final static String APPDATA_SELETTOREIDRUOLO_CODE = "appDataselettoreIdRuolo";

	public final static String APPDATA_GRUPPO_CODE = "appDatagruppo";

	public final static String APPDATA_GRUPPI_CODE = "appDatagruppi";

	public final static String APPDATA_SELETTOREIDGRUPPO_CODE = "appDataselettoreIdGruppo";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioUtenti";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAUTENTE_OUTCOME_CODE__OK = "Ok";
		final String SALVAUTENTE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-159684789) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Utente utente = theModel.getAppDatautente();
			ArrayList<UtenteGruppo> lisug = new ArrayList<UtenteGruppo>();
			// nel momento in cui dovesse un utente avere piu' gruppi il valore va preso 
			// dalla tabella riepilogativa e non piu dalla combo
			String codfisc = utente.getCodFisc();
			String email = utente.getEmail();
			String firstname = utente.getDescrUtente();
			String lastname = "";
			String pwdservizio = utente.getPwdservizio().trim();
			String idGruppo = theModel.getAppDataselettoreIdGruppo();
			String idUtente = utente.getIdUtente();
			if (email != null && !email.trim().equals("")
					&& !Validator.isValidEmail(email)) {
				result.getGlobalErrors().add(
						pu.getMessage("error.formatoEmail.message"));
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (!Validator.isValidRequired(codfisc)) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0003"));
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (!Validator.isValidRequired(firstname)) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0002"));
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			if (!Validator.isValidRequired(pwdservizio)) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0004"));
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (!Validator.isValidRequired(idGruppo)) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0005"));
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			/*
			 if (!Validator.isValidCodfisc(codfisc)) {
			 result.getGlobalErrors().add(pu.getMessage("UT-E-0006"));
			 result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
			 result.setModel(theModel);
			 }
			 */
			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				return result;
			}

			UtenteGruppo ug = new UtenteGruppo();
			ug.setIdgroup(idGruppo);
			ug.setIdUtente(idUtente);
			lisug.add(ug);

			String in = getGestoreMDPBackOffice().setMdpUsers(user, idUtente,
					codfisc, email, firstname, lastname, pwdservizio, lisug);
			if (!in.equals("")) {
				result.getGlobalErrors().add(in);
				result.setResultCode(SALVAUTENTE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			theModel.setAppDataisPostBack("N");
			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));
			// impostazione del result code
			result.setResultCode(SALVAUTENTE_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaUtente] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__NUOVO = "NUOVO";
		final String REFRESHPANEL_OUTCOME_CODE__MODIFICA = "MODIFICA";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R658698498) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String isPostBack = theModel.getAppDataisPostBack();

			//if (isPostBack == null || isPostBack.equals("")|| isPostBack.equals("N")) {
			// carico la lista dei gruppi
			if (theModel.getAppDatagruppi() == null) {
				theModel.setAppDatagruppi(getGestoreMDPBackOffice()
						.getMdpBckGroups(user));
			}
			// impostazione del result code
			//result.setResultCode(REFRESHPANEL_OUTCOME_CODE__NUOVO);
			//theModel.setAppDataisPostBack("S");
			//}else{
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__MODIFICA);
			//}
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

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R214252030) ENABLED START*/
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
