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

/*PROTECTED REGION ID(R-935753110) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse;
import java.io.File;
/*PROTECTED REGION END*/

public class CPBECpDettaglioApplicazioneNew {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_APPLICAZIONE_CODE = "appDataapplicazione";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_EXTRAATTRIBUTES_CODE = "appDataextraAttributes";

	public final static String APPDATA_ASSOCIAZIONEGW_MP_CODE = "appDataassociazioneGW_MP";

	public final static String APPDATA_ASSOCIAZIONIGW_MP_CODE = "appDataassociazioniGW_MP";

	public final static String APPDATA_PAYMENTMODES_CODE = "appDatapaymentModes";

	public final static String APPDATA_GATEWAYS_CODE = "appDatagateways";

	public final static String APPDATA_SELETTOREITEMASSOCIAZIONEGW_MP_CODE = "appDataselettoreItemAssociazioneGW_MP";

	public final static String APPDATA_NUOVOEXTRAATTRIBUTE_CODE = "appDatanuovoExtraAttribute";

	public final static String APPDATA_SELETTORECHIAVEATTR_CODE = "appDataselettoreChiaveAttr";

	public final static String APPDATA_GATEWAY_CODE = "appDatagateway";

	public final static String APPDATA_PAYMENTMODE_CODE = "appDatapaymentMode";

	public final static String APPDATA_SELETTOREIDGATEWAY_CODE = "appDataselettoreIdGateway";

	public final static String APPDATA_SELETTOREIDPAYMENTMODE_CODE = "appDataselettoreIdPaymentMode";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioApplicazioneNew";

	public final static String TABSET_TSPAPPLICAZIONE = "tspApplicazione";
	public final static String TAB_TSPAPPLICAZIONE_PDATIGENERALI = CPNAME + "_"
			+ TABSET_TSPAPPLICAZIONE + "_" + "pDatiGenerali";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaApplicazione(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAAPPLICAZIONE_OUTCOME_CODE__OK = "Ok";
		final String SALVAAPPLICAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-245123613) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			log.info("[CPBECpDettaglioApplicazioneNew::salvaApplicazione] BEGIN");
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Applicazione app = theModel.getAppDataapplicazione();
			String email = app.getEmailEsercente();
			//inizio con i controlli
			if (app.getIdApplicazione() == null
					|| app.getIdApplicazione().trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0001"));
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (app.getNomeApplicazione() == null
					|| app.getNomeApplicazione().trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0002"));
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (email != null && !email.trim().equals("")
					&& !Validator.isValidEmail(email)) {
				result.getGlobalErrors().add(
						pu.getMessage("error.formatoEmail.message"));
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				return result;
			}

			// inserisco la nuova applicazione
			String in = getGestoreMDPBackOffice().setApplicazione(user, app,
					false);
			if (!in.equals("")) {
				result.getGlobalErrors().add(in);
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			//recupero l'id appena creata e passo in modifica per aggiungere gli attributi successivi
			String idApplicazione = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			if (idApplicazione == null || idApplicazione.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0007"));;
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			} else {
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
			}
			Applicazione aplicazioneSelezionata = getGestoreMDPBackOffice()
					.getApplicazioneById(user, idApplicazione);
			theModel.setAppDataapplicazione(aplicazioneSelezionata);
			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));
			result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__OK);
			result.setModel(theModel);
			log.info("[CPBECpDettaglioApplicazioneNew::salvaApplicazione] END");
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaApplicazione] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R491563848) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);
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
	/*PROTECTED REGION ID(R-845170230) ENABLED START*/
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
