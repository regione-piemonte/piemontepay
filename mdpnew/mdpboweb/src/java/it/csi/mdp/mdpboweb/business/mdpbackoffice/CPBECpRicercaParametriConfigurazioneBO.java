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

/*PROTECTED REGION ID(R-317632794) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
//import it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub;
import it.csi.mdp.mdpboweb.util.PropertiesUtil;
import org.apache.axis.AxisFault;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
//import it.csi.mdp.mdpboweb.business.ws.AuthException;
/*PROTECTED REGION END*/

public class CPBECpRicercaParametriConfigurazioneBO {

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
	public final static String CPNAME = "cpRicercaParametriConfigurazioneBO";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovo(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String NUOVO_OUTCOME_CODE__OK = "Ok";
		final String NUOVO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R2031888002) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			//PropertiesUtil pu = new PropertiesUtil();
			//UserInfoExt user = theModel.getAppDatauserInfoExt();
			ParametroConfigurazioneBO newPc = new ParametroConfigurazioneBO();
			newPc.setIdParametro("");
			newPc.setValore("");
			newPc.setDescrParametro("");
			theModel.setAppDataparametroConfigurazioneBO(newPc);
			result.setResultCode(NUOVO_OUTCOME_CODE__OK);
			theModel.setAppDataisPostBack(null);
			theModel.setAppDataselettoreOperazione("INSERT_PARAMCONF");
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::nuovo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modifica(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICA_OUTCOME_CODE__OK = "Ok";
		final String MODIFICA_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1998311795) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			PropertiesUtil pu = new PropertiesUtil();
			//UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<ParametroConfigurazioneBO> aParConf = new ArrayList<ParametroConfigurazioneBO>();
			//Map sess = theModel.getSession();
			String idParametro = theModel.getAppDataselettoreParametroConf();
			if (idParametro != null && !idParametro.equals("")) {
				ParametroConfigurazioneBO pc = new ParametroConfigurazioneBO();
				for (int k = 0; k < theModel
						.getAppDataparametriConfigurazioneBO().size(); k++) {
					ParametroConfigurazioneBO wsPC = theModel
							.getAppDataparametriConfigurazioneBO().get(k);
					if (wsPC.getIdParametro().equals(idParametro)) {
						pc.setIdParametro(wsPC.getIdParametro());
						pc.setValore(wsPC.getValore());
						pc.setDescrParametro(wsPC.getDescrParametro());
						break;
					}
				}
				result.setResultCode(MODIFICA_OUTCOME_CODE__KO);
				if (pc != null) {
					aParConf.add(pc);
					result.setResultCode(MODIFICA_OUTCOME_CODE__OK);
					theModel.setAppDataselettoreOperazione("UPDATE_PARAMCONF");
					theModel.setAppDataparametroConfigurazioneBO(pc);
					result.setModel(theModel);
					return result;
				}
			} else {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0100"));
				result.setResultCode(MODIFICA_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			theModel.setAppDataisPostBack(null);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modifica] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults elimina(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINA_OUTCOME_CODE__OK = "Ok";
		final String ELIMINA_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-684857582) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			log.info("[CBERicercaParametriConf::BEGIN]");
			PropertiesUtil pu = new PropertiesUtil();
			ArrayList<ParametroConfigurazioneBO> aParConf = new ArrayList<ParametroConfigurazioneBO>();
			String idParametro = theModel.getAppDataselettoreParametroConf();
			if (idParametro != null && !idParametro.equals("")) {
				ParametroConfigurazioneBO pc = new ParametroConfigurazioneBO();
				for (int k = 0; k < theModel
						.getAppDataparametriConfigurazioneBO().size(); k++) {
					ParametroConfigurazioneBO wsPC = theModel
							.getAppDataparametriConfigurazioneBO().get(k);
					if (wsPC.getIdParametro().equals(idParametro)) {
						pc.setIdParametro(wsPC.getIdParametro());
						pc.setValore(wsPC.getValore());
						pc.setDescrParametro(wsPC.getDescrParametro());
						break;
					}
				}
				result.setResultCode(ELIMINA_OUTCOME_CODE__KO);
				if (pc != null) {
					aParConf.add(pc);
					result.setResultCode(ELIMINA_OUTCOME_CODE__OK);
					theModel.setAppDataselettoreOperazione("DELETE_PARAMCONF");
					theModel.setAppDataparametroConfigurazioneBO(pc);
					result.setModel(theModel);
					return result;
				}
			} else {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0108"));
				result.setResultCode(ELIMINA_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			theModel.setAppDataisPostBack(null);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::elimina] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS = "Ok_No_Results";
		final String REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R415138124) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				//ArrayList<ParametroConfigurazioneBO> aParConf = new ArrayList<ParametroConfigurazioneBO>();
				ArrayList<ParametroConfigurazioneBO> aParConfFromWS = new ArrayList<ParametroConfigurazioneBO>();
				try {
					aParConfFromWS = getGestoreMDPBackOffice()
							.getListaParametriConfigurazione(user);

					theModel.setAppDataparametriConfigurazioneBO(aParConfFromWS);
					theModel.setAppDataisPostBack("S");

				} catch (AxisFault e) {
					String faultString = e.getFaultString();
					String message = pu.getMessage("TR-E-0101") + " AxisFault "
							+ pu.getMessage("TR-E-0102") + " GetBoConfig()"
							+ pu.getMessage("TR-E-0103") + faultString
							+ pu.getMessage("TR-E-0104");
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
					result.getGlobalErrors().add(message);
					result.setModel(theModel);
					return result;
				} catch (MalformedURLException e) {
					String faultString = e.getMessage();
					String message = pu.getMessage("TR-E-0101")
							+ " MalformedURLException"
							+ pu.getMessage("TR-E-0102") + " GetBoConfig()"
							+ pu.getMessage("TR-E-0103") + faultString
							+ pu.getMessage("TR-E-0104");

					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
					result.getGlobalErrors().add(message);
					result.setModel(theModel);
					return result;
				} catch (RemoteException e) {
					String faultString = e.getMessage();
					String message = pu.getMessage("TR-E-0101")
							+ " RemoteException" + pu.getMessage("TR-E-0102")
							+ " GetBoConfig()" + pu.getMessage("TR-E-0103")
							+ faultString + pu.getMessage("TR-E-0104");
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
					result.getGlobalErrors().add(message);
					result.setModel(theModel);
					return result;
				} catch (Exception e) {
					String faultString = e.getMessage();
					String message = pu.getMessage("TR-E-0101") + " Exception"
							+ pu.getMessage("TR-E-0102") + " GetBoConfig()"
							+ pu.getMessage("TR-E-0103") + faultString
							+ pu.getMessage("TR-E-0104");
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
					result.getGlobalErrors().add(message);
					result.setModel(theModel);
					return result;
				}

				theModel.setAppDataselettoreOperazione(null);
				theModel.setAppDataisPostBack(null);

				// impostazione del result code
				if (theModel.getAppDataparametriConfigurazioneBO().size() == 0) {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
				} else {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS);
				}
			} else {
				if (theModel.getAppDataparametriConfigurazioneBO().size() == 0) {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_NO_RESULTS);
				} else {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS);
				}
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

	public static void resetClearStatus_widg_tdParametriConf(
			java.util.Map session) {
		session.put(
				"cpRicercaParametriConfigurazioneBO_tdParametriConf_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R599199950) ENABLED START*/
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
