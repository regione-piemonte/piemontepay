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

/*PROTECTED REGION ID(R33938004) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.util.PropertiesUtil;
/*PROTECTED REGION END*/

public class CPBECpDettaglioParametroConfigurazioneBO {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_PARAMETRICONFIGURAZIONEBO_CODE = "appDataparametriConfigurazioneBO";

	public final static String APPDATA_PARAMETROCONFIGURAZIONEBO_CODE = "appDataparametroConfigurazioneBO";

	public final static String APPDATA_SELETTOREPARAMETROCONF_CODE = "appDataselettoreParametroConf";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioParametroConfigurazioneBO";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		final String INDIETRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-241525743) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code
			result.setResultCode("Ok");
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::indietro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salva(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVA_OUTCOME_CODE__OK = "Ok";
		final String SALVA_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-2123336408) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			ParametroConfigurazioneBO inpPC = theModel
					.getAppDataparametroConfigurazioneBO();
			String chiave = inpPC.getIdParametro();
			String valore = inpPC.getValore();
			String descrizione = inpPC.getDescrParametro();
			// CONTROLLI PRIMA DEL SALVATAGGIO
			if (chiave == null || chiave.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0105"));
				result.setResultCode(SALVA_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			if (chiave.contains(" ")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0107"));
				result.setResultCode(SALVA_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (valore == null || valore.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0106"));
				result.setResultCode(SALVA_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			// OK ---> Si procede con il salvataggio 
			try {
				getGestoreMDPBackOffice().salvaParametroConfigurazioneBO(user,
						inpPC);

			} catch (Exception e) {
				String faultString = e.getMessage();
				String message = pu.getMessage("TR-E-0101") + " Exception"
						+ pu.getMessage("TR-E-0102") + " setBoConfig()"
						+ pu.getMessage("TR-E-0103") + faultString
						+ pu.getMessage("TR-E-0104");

				result.setResultCode(SALVA_OUTCOME_CODE__KO);
				result.getGlobalErrors().add(message);
				result.setModel(theModel);
				return result;
			}

			/// FINE LOGICA BUSINESS. SI ESCE!
			result.setResultCode(SALVA_OUTCOME_CODE__OK);
			theModel.setAppDataisPostBack(null);
			theModel.setAppDataselettoreOperazione("");

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salva] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_INSERT = "Ok_Insert";
		final String REFRESHPANEL_OUTCOME_CODE__OK_MODIFICA = "Ok_Modifica";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-429251298) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			//UserInfoExt user = theModel.getAppDatauserInfoExt();
			//PropertiesUtil pu = new PropertiesUtil();
			String isPostBack = theModel.getAppDataisPostBack();
			String seleOpe = theModel.getAppDataselettoreOperazione();
			if (seleOpe.equals("INSERT_PARAMCONF")) {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INSERT);
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_MODIFICA);
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

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1973153248) ENABLED START*/
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
