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

/*PROTECTED REGION ID(R1964013897) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreCoreMdpBackOffice;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse;
/*PROTECTED REGION END*/

public class CPBECpGestioneGruppi {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_GRUPPO_CODE = "appDatagruppo";

	public final static String APPDATA_GRUPPI_CODE = "appDatagruppi";

	public final static String APPDATA_GRUPPIAPPLICAZIONI_CODE = "appDatagruppiApplicazioni";

	public final static String APPDATA_GRUPPIRUOLI_CODE = "appDatagruppiruoli";

	public final static String APPDATA_GRUPPOAPPLICAZIONE_CODE = "appDatagruppoApplicazione";

	public final static String APPDATA_GRUPPORUOLO_CODE = "appDatagrupporuolo";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_RUOLI_CODE = "appDataruoli";

	public final static String APPDATA_RUOLO_CODE = "appDataruolo";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_SELETTOREGRUPPO_CODE = "appDataselettoreGruppo";

	public final static String APPDATA_SELETTOREIDRUOLO_CODE = "appDataselettoreIdRuolo";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneGruppi";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults gotoNuovoGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String GOTONUOVOGRUPPO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R897690851) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:			
			theModel.setAppDatagruppo(new Gruppo());
			// impostazione del result code
			result.setResultCode(GOTONUOVOGRUPPO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::gotoNuovoGruppo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults gotoModificaGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String GOTOMODIFICAGRUPPO_OUTCOME_CODE__OK = "Ok";
		final String GOTOMODIFICAGRUPPO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1429771102) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String gruppoSelezionato = theModel.getAppDataselettoreGruppo();
			if (gruppoSelezionato == null
					|| gruppoSelezionato.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GR-E-0001"));
				result.setResultCode(GOTOMODIFICAGRUPPO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			theModel.setAppDataselettoreGruppo(gruppoSelezionato);
			theModel.setAppDatagruppo(getGestoreMDPBackOffice()
					.getMdpBckGroupsById(user, gruppoSelezionato));
			//theModel.setAppDataselettoreIdRuolo();

			result.setResultCode(GOTOMODIFICAGRUPPO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::gotoModificaGruppo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CANCELLAGRUPPO_OUTCOME_CODE__OK = "Ok";
		final String CANCELLAGRUPPO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1203877698) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			String idGruppoSelezionato = theModel.getAppDataselettoreGruppo();
			String in = getGestoreMDPBackOffice().deleteMdpBckGroup(user,
					idGruppoSelezionato);
			if (!in.equals("")) {
				result.getGlobalErrors().add(in);
				result.setResultCode(CANCELLAGRUPPO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			ArrayList<Gruppo> listagrp = theModel.getAppDatagruppi();
			for (int r = 0; r < listagrp.size(); r++) {

				if (listagrp.get(r).getIdgroup().equals(idGruppoSelezionato)) {
					listagrp.remove(r);
				}
			}
			theModel.setAppDatagruppi(listagrp);
			result.getGlobalMessages().add(
					pu.getMessage("cancellazioneOk.message"));
			result.setResultCode(CANCELLAGRUPPO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cancellaGruppo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__NO_RESULT = "No_Result";
		final String REFRESHPANEL_OUTCOME_CODE__SOME_RESULT = "Some_Result";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R171182857) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			theModel.setAppDatagruppi(getGestoreMDPBackOffice()
					.getMdpBckGroups(user));
			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__SOME_RESULT);
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

	public static void resetClearStatus_widg_tListaGruppi(java.util.Map session) {
		session.put("cpGestioneGruppi_tListaGruppi_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-1579248053) ENABLED START*/
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
