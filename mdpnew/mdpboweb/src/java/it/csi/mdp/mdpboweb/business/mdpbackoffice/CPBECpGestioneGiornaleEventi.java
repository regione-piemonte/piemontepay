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

/*PROTECTED REGION ID(R-2088328248) ENABLED START*/
import org.apache.commons.collections.CollectionUtils;

import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

/*PROTECTED REGION END*/

public class CPBECpGestioneGiornaleEventi {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_RICERCAGIORNALEEVENTI_CODE = "appDataricercaGiornaleEventi";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_SELETTOREIDGIORNALEEVENTO_CODE = "appDataselettoreIdGiornaleEvento";

	public final static String APPDATA_LISTAGIORNALIEVENTI_CODE = "appDatalistaGiornaliEventi";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneGiornaleEventi";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CERCA_OUTCOME_CODE__NO_RESULT = "NO_RESULT";
		final String CERCA_OUTCOME_CODE__RESULTS = "RESULTS";
		final String CERCA_OUTCOME_CODE__VIEW_RICERCA_NO_RESULT = "VIEW_RICERCA_NO_RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-395135465) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<GiornaleEventi> lista = getGestoreMDPNODONAZIONALEBackOffice()
					.getGiornaleEventoByFiltro(
							user,
							theModel.getAppDataricercaGiornaleEventi().getIuv(),
							theModel.getAppDataricercaGiornaleEventi()
									.getDataoraevento(),
							theModel.getAppDataricercaGiornaleEventi()
									.getIdentificativodominio(),
							theModel.getAppDataricercaGiornaleEventi()
									.getIdentificativofruitore(),
							theModel.getAppDataricercaGiornaleEventi()
									.getCodiceContesto());
			log.info("lunghezza lista " + lista.size());

			theModel.setAppDatalistaGiornaliEventi(lista);

			// impostazione del result code
			if (lista.size() < 1) {
				result.setResultCode(CERCA_OUTCOME_CODE__VIEW_RICERCA_NO_RESULT);
			} else {
				result.setResultCode(CERCA_OUTCOME_CODE__RESULTS);
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
					"[BackEndFacade::cerca] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults clear(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CLEAR_OUTCOME_CODE__PULISCI = "PULISCI";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1662542808) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			theModel.setAppDataricercaGiornaleEventi(new GiornaleEventi());
			theModel.setAppDatalistaGiornaliEventi(new ArrayList<GiornaleEventi>());
			// impostazione del result code 
			result.setResultCode(CLEAR_OUTCOME_CODE__PULISCI);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::clear] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglio(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VAIALDETTAGLIO_OUTCOME_CODE__OK = "OK";
		final String VAIALDETTAGLIO_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-2125538223) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String id = theModel.getAppDataselettoreIdGiornaleEvento();

			log.info("[CPBECpGestioneGiornaleEventie::vaiAlDettaglio] id selezionato :"
					+ id);
			if (id == null || id.equals("0")) {
				result.getGlobalErrors().add(pu.getMessage("GE-E-0001"));
				result.setResultCode(VAIALDETTAGLIO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			// getGestoreMDPBackOffice().getDettaglioGiornaleEventoById(theModel.getAppDataselettoreIdGiornaleEvento());

			// impostazione del result code
			result.setResultCode(VAIALDETTAGLIO_OUTCOME_CODE__OK);
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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__RESULT = "RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1816177450) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");
				// impostazione del result code
				log.info("[CPBECpGestioneGiornaleEventi::refreshPanel] REFRESHPANEL_OUTCOME_CODE__INIZIALE");
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);
			} else {
				// impostazione del result code
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RESULT);
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

	public static void resetClearStatus_widg_tRicerca(java.util.Map session) {
		session.put("cpGestioneGiornaleEventi_tRicerca_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1928291436) ENABLED START*/
	// // inserire qui le property che si vogliono iniettare in questo bean (es.
	// dao, proxy di pd, ...)

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
