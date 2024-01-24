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

/*PROTECTED REGION ID(R-1117855914) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
/*PROTECTED REGION END*/

public class CPBECpHomePage {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_GOTOLOGIN_CODE = "appDatagotologin";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpHomePage";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpHomePageModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK = "Ok";
		final String REFRESHPANEL_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1359956700) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			new MdpboProperties().getDefwsMdpboProperties("logout.url");

			if (theModel.getAppDatauserInfoExt() == null) {
				UserInfo user = theModel.getAppDatacurrentUser();
				UserInfoExt userExt = new UserInfoExt();
				userExt.setCodFisc(user.getCodFisc());
				userExt.setCodRuolo(user.getCodRuolo());
				userExt.setCognome(user.getCognome());
				userExt.setEnte(user.getEnte());
				userExt.setIdIride(user.getIdIride());
				userExt.setNome(user.getNome());
				userExt.setRuolo(user.getRuolo());

				try {
					//TODO DA GESTIRE
					//theModel.setAppDatagotologin("https://tst-secure.ruparpiemonte.it/Shibboleth.sso/Logout");
					//log.info("logout --> " + props.getProperty("logout.url"));
					theModel.setAppDatagotologin(new MdpboProperties()
							.getDefwsMdpboProperties("logout.url"));
					userExt.setPwBck(getGestoreMDPBackOffice().getPwUtente(
							user.getCodFisc()));
				} catch (Exception e) {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__KO);
					result.setModel(theModel);
					return result;
				}
				/*			 
							userExt.setPwBck(getGestoreMDPBackOffice().getPwUtente(
									user.getCodFisc()));
				 */
				theModel.setAppDatauserInfoExt(userExt);
			}
			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			/****
			SecurityHelper sh = new SecurityHelper();
			String listaUseCaseCurrentUser = "Use Case dell'utente :";
			Map map = theModel.getSession();
			if (sh.verifyCurrentUserForUC(map,"MDPNEW_GEST")) {
				listaUseCaseCurrentUser += " MDPNEW_GEST ";
				System.out.println("**************** Use Case APPLICAZIONI_GEST OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"APPLICAZIONI_GEST")) {
				listaUseCaseCurrentUser += " APPLICAZIONI_GEST ";
				System.out.println("**************** Use Case APPLICAZIONI_GEST OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"APPLICAZIONI_GEST_FULL")) {
				listaUseCaseCurrentUser += " APPLICAZIONI_GEST_FULL ";
				System.out.println("**************** Use Case APPLICAZIONI_GEST_FULL OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"APPLICAZIONI_GEST_FULL")) {
				listaUseCaseCurrentUser += " PROVIDER_GEST ";
				System.out.println("**************** Use Case APPLICAZIONI_GEST_FULL OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"APPLICAZIONI_GEST_FULL")) {
				listaUseCaseCurrentUser += " PAGAMENTI_RW ";
				System.out.println("**************** Use Case APPLICAZIONI_GEST_FULL OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"UTENTI_GEST")) {
				listaUseCaseCurrentUser += " UTENTI_GEST ";
				System.out.println("**************** Use Case UTENTI_GEST OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"STORICOERRORI")) {
				listaUseCaseCurrentUser += " STORICOERRORI ";
				System.out.println("**************** Use Case STORICOERRORI OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"AUDIT")) {
				listaUseCaseCurrentUser += " AUDIT ";
				System.out.println("**************** Use Case AUDIT OK!****************" );
			}
			if (sh.verifyCurrentUserForUC(map,"PAGAMENTI")) {
				listaUseCaseCurrentUser += " PAGAMENTI ";
				System.out.println("**************** Use Case PAGAMENTI OK!****************" );
			}
			result.getGlobalMessages().add(listaUseCaseCurrentUser);
			 ******/
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);
			// sh.verifyCurrentUserForUC(getCurrentUser(session, "UTENTI"));
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
	/*PROTECTED REGION ID(R2001974110) ENABLED START*/
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
