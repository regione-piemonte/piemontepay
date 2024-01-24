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

/*PROTECTED REGION ID(R312346963) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

/*PROTECTED REGION END*/

public class CPBECpInformativePsp {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_INFORMATIVEPSP_CODE = "appDatainformativePsp";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_SELETTOREINFORMATIVEPSP_CODE = "appDataselettoreInformativePsp";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_LISTAINFORMATIVEPSP_CODE = "appDatalistaInformativePsp";

	public final static String APPDATA_FILTRORICERCAINFORMATIVEPSP_CODE = "appDatafiltroRicercaInformativePsp";

	public final static String APPDATA_LISTATIPOVERSAMENTO_CODE = "appDatalistaTipoversamento";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpInformativePsp";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricercaInformativa(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String RICERCAINFORMATIVA_OUTCOME_CODE__RICERCA_NORESULT = "RICERCA_NORESULT";
		final String RICERCAINFORMATIVA_OUTCOME_CODE__RICERCA_RESULT = "RICERCA_RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-265514489) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<InformativePsp> lista = getGestoreMDPNODONAZIONALEBackOffice()
					.getInformativePSPByParam(
							user,
							null,
							null,
							null,
							theModel.getAppDatafiltroRicercaInformativePsp()
									.getRagionesociale(),
							theModel.getAppDatafiltroRicercaInformativePsp()
									.getDatapubblicazione(),
							null,
							null,
							null,
							null,
							null,
							theModel.getAppDatafiltroRicercaInformativePsp()
									.getTipoversamento(),
							null,
							null,
							null,
							null,
							null,
							null,
							theModel.getAppDatafiltroRicercaInformativePsp()
									.getDatainserimento(), null, null, null);

			log.info("lunghezza lista " + lista.size());

			theModel.setAppDatalistaInformativePsp(lista);

			// impostazione del result code
			if (lista.size() < 1) {
				result.setResultCode(RICERCAINFORMATIVA_OUTCOME_CODE__RICERCA_NORESULT);
			} else {
				result.setResultCode(RICERCAINFORMATIVA_OUTCOME_CODE__RICERCA_RESULT);
			}
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;

			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::ricercaInformativa] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglio(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VAIALDETTAGLIO_OUTCOME_CODE__DETTAGLIO_OK = "DETTAGLIO_OK";
		final String VAIALDETTAGLIO_OUTCOME_CODE__DETTAGLIO_KO = "DETTAGLIO_KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1819275098) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			PropertiesUtil pu = new PropertiesUtil();
			String id = theModel.getAppDataselettoreInformativePsp();

			log.info("[CPBECpInformativePsp::vaiAlDettaglio] id selezionato :"
					+ id);
			if (id == null || id.equals("0")) {
				result.getGlobalErrors().add(pu.getMessage("PSP-E-0001"));
				result.setResultCode(VAIALDETTAGLIO_OUTCOME_CODE__DETTAGLIO_KO);
				result.setModel(theModel);
				return result;
			}

			// impostazione del result code
			result.setResultCode(VAIALDETTAGLIO_OUTCOME_CODE__DETTAGLIO_OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".
			// impostazione del result code
			result.setModel(theModel);
			return result;

			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::vaiAlDettaglio] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__RICERCA = "Ricerca";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1669533505) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");

				UserInfoExt user = theModel.getAppDatauserInfoExt();
				ArrayList<TipoVersamento> value = getGestoreMDPNODONAZIONALEBackOffice()
						.getListaTipoversamento(user);

				log.info("[CPBECpGestioneGiornaleEventi::refreshPanel] value "
						+ value.get(0).getId());
				log.info("[CPBECpGestioneGiornaleEventi::refreshPanel] value "
						+ value.get(0).getDescrizione());

				theModel.setAppDatalistaTipoversamento(value);
				// impostazione del result code
				log.info("[CPBECpGestioneGiornaleEventi::refreshPanel] REFRESHPANEL_OUTCOME_CODE__INIZIALE");
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);
			} else {
				// impostazione del result code
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RICERCA);
				log.info("[CPBECpGestioneGiornaleEventi::refreshPanel] REFRESHPANEL_OUTCOME_CODE__RESULT");
			}
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

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

	public static void resetClearStatus_widg_tbRisultati(java.util.Map session) {
		session.put("cpInformativePsp_tbRisultati_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1051431425) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice = null;

	public GestoreMDPNODONAZIONALEBackOffice getGestoreMDPNODONAZIONALEBackOffice() {
		return gestoreMDPNODONAZIONALEBackOffice;
	}

	public void setGestoreMDPNODONAZIONALEBackOffice(
			GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice) {
		this.gestoreMDPNODONAZIONALEBackOffice = gestoreMDPNODONAZIONALEBackOffice;
	}

	/*PROTECTED REGION END*/
}
