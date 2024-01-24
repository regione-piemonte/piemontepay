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

/*PROTECTED REGION ID(R228118982) ENABLED START*/
import it.csi.mdp.mdpboweb.util.PropertiesUtil;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
/*PROTECTED REGION END*/

public class CPBECpStoricoErrori {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_ERRORI_CODE = "appDataerrori";

	public final static String APPDATA_ERRORE_CODE = "appDataerrore";

	public final static String APPDATA_GATEWAYS_CODE = "appDatagateways";

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_SELETTOREIDERRORE_CODE = "appDataselettoreIdErrore";

	public final static String APPDATA_CERCAERRORE_CODE = "appDatacercaErrore";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpStoricoErrori";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CERCA_OUTCOME_CODE__OK_NO_RESULTS = "Ok_No_Results";
		final String CERCA_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		final String CERCA_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1583237867) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<Errore> aErrs = new ArrayList<Errore>();
			try {
				aErrs = getGestoreMDPBackOffice().getListaErroriByFiltro(user,
						theModel.getAppDatacercaErrore());
			} catch (Exception e) {
				String faultString = e.getMessage();
				String message = pu.getMessage("TR-E-0101") + " Exception"
						+ pu.getMessage("TR-E-0102") + " getErrorList()"
						+ pu.getMessage("TR-E-0103") + faultString
						+ pu.getMessage("TR-E-0104");

				result.setResultCode(CERCA_OUTCOME_CODE__KO);
				result.getGlobalErrors().add(message);
				result.setModel(theModel);
				return result;
			}
			theModel.setAppDataerrori(aErrs);
			Errore err = theModel.getAppDatacercaErrore();
			err.setTestoErroreCompleto("");
			theModel.setAppDatacercaErrore(err);
			String resultCode = null;
			if (aErrs == null) {
				resultCode = (CERCA_OUTCOME_CODE__OK_NO_RESULTS);
			} else {
				if (aErrs.size() == 0) {
					resultCode = (CERCA_OUTCOME_CODE__OK_NO_RESULTS);
				} else {
					resultCode = (CERCA_OUTCOME_CODE__OK_SOME_RESULTS);
				}
			}
			theModel.setAppDataselettoreIdErrore("0");
			result.setResultCode(resultCode);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cerca] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults showError(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SHOWERROR_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-871280808) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String idErrore = theModel.getAppDataselettoreIdErrore();
			if (idErrore.equals("0")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0111"));
				result.setResultCode(SHOWERROR_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			} else {
				for (int k = 0; k < theModel.getAppDataerrori().size(); k++) {
					int tmIdErrore = Integer.parseInt(theModel
							.getAppDataerrori().get(k).getIdErrore());
					if (idErrore.equals(String.valueOf(tmIdErrore))) {
						String testoErr = new String();
						testoErr = theModel.getAppDataerrori().get(k)
								.getTestoErroreCompleto();
						Errore err = theModel.getAppDatacercaErrore();
						err.setTestoErroreCompleto(testoErr);
						theModel.setAppDatacercaErrore(err);
						break;
					}
				}
			}
			// impostazione del result code
			result.setResultCode(SHOWERROR_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::showError] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE = "Ok_Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		final String REFRESHPANEL_OUTCOME_CODE__NO_RESULTS = "No_results";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R524448364) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				ArrayList<Applicazione> aApp = new ArrayList<Applicazione>();
				ArrayList<Applicazione> aAppFromWS = new ArrayList<Applicazione>();
				// INIZIALIZZA DIMENSIONE BLOCCHI 100
				// theModel.setAppDatasizeBlocco(100);
				// Inizializza paginazione
				// PaginazioneTrans Pt = new PaginazioneTrans();
				// Pt.setDimensionePagina(theModel.getAppDatasizeBlocco());
				// Pt.setNumeroPagina(1);
				// Pt.setTotale(0);
				// theModel.setAppDatapaginazioneTrans(Pt);
				aAppFromWS = getGestoreMDPBackOffice()
						.getListaApplicazioniByUser(user);
				Collections.sort(aAppFromWS,
						Comparators.APPLICAZIONE_COMPARATOR);

				for (Applicazione applicazione : aAppFromWS) {
					log.info(applicazione.getIdApplicazione());
				}

				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione("SCEGLI_APPLICAZIONE");
				fakeApp.setNomeApplicazione("Nessun filtro sulla applicazione");
				aApp.add(fakeApp);

				for (int y = 0; y < aAppFromWS.size(); y++) {
					Applicazione app = aAppFromWS.get(y);
					aApp.add(app);
				}
				theModel.setAppDataapplicazioni(aApp);
				Errore err = theModel.getAppDatacercaErrore();
				if (err == null) {
					err = new Errore();
				}
				err.setIdApplicazione("SCEGLI_APPLICAZIONE");
				// Gateways	
				ArrayList<Gateway> aGT = new ArrayList<Gateway>();
				ArrayList<Gateway> aGTFromWS = new ArrayList<Gateway>();
				Gateway fakeGT = new Gateway();
				fakeGT.setIdGateway("SCEGLI_GATEWAY");
				fakeGT.setDescrGateway("Nessun filtro sul Gateway");
				aGT.add(fakeGT);
				aGTFromWS = getGestoreMDPBackOffice().getListaGatewayByUser(
						user);

				Collections.sort(aGTFromWS, Comparators.GATEWAY_COMPARATOR);

				for (int y = 0; y < aGTFromWS.size(); y++) {
					Gateway gt = aGTFromWS.get(y);
					aGT.add(gt);
				}
				theModel.setAppDatagateways(aGT);
				err.setIdGateway("SCEGLI_GATEWAY");
				theModel.setAppDatacercaErrore(err);
				theModel.setAppDataisPostBack("S");
				theModel.setAppDataselettoreIdErrore("0");
				theModel.setAppDataerrori(null);
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE);
			} else {
				if (theModel.getAppDataerrori() != null
						&& theModel.getAppDataerrori().size() > 0) {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_SOME_RESULTS);

				} else {
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__NO_RESULTS);
				}
			}
			// impostazione del result code
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

	public static void resetClearStatus_widg_tRicerca(java.util.Map session) {
		session.put("cpStoricoErrori_tRicerca_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-1875884888) ENABLED START*/
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
