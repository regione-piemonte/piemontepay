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

/*PROTECTED REGION ID(R-83242397) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;
/*PROTECTED REGION END*/

public class CPBECpRicercaEnti {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_ENTI_CODE = "appDataenti";

	public final static String APPDATA_RICERCAENTI_CODE = "appDataricercaEnti";

	public final static String APPDATA_LISTAENTI_CODE = "appDatalistaEnti";

	public final static String APPDATA_SELETTOREIDENTE_CODE = "appDataselettoreIdEnte";

	public final static String APPDATA_LISTASTATIATTIVAZIONEENTI_CODE = "appDatalistaStatiAttivazioneEnti";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpRicercaEnti";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cercaEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CERCAENTE_OUTCOME_CODE__RESULTS = "RESULTS";
		final String CERCAENTE_OUTCOME_CODE__NO_RESULTS = "NO_RESULTS";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R756547448) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			theModel.setAppDataselettoreIdEnte(null);
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<Enti> lista = new ArrayList<Enti>();
			lista = getGestoreMDPNODONAZIONALEBackOffice().getEntiByParam(user,
					theModel.getAppDataricercaEnti().getEnteId(),
					theModel.getAppDataricercaEnti().getPartitaIva(),
					theModel.getAppDataricercaEnti().getDescrizione(),
					theModel.getAppDataricercaEnti().getAttivo());

			theModel.setAppDatalistaEnti(lista);

			log.info("lunghezza lista " + lista.size());
			if (lista.size() > 0) {
				// impostazione del result code 
				result.setResultCode(CERCAENTE_OUTCOME_CODE__RESULTS);
			} else {
				result.setResultCode(CERCAENTE_OUTCOME_CODE__NO_RESULTS);
			}
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cercaEnte] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults clean(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CLEAN_OUTCOME_CODE__CLEAN = "clean";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-351704457) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			theModel.setAppDataricercaEnti(new Enti());
			theModel.setAppDatalistaEnti(new ArrayList<Enti>());

			// impostazione del result code 
			result.setResultCode(CLEAN_OUTCOME_CODE__CLEAN);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::clean] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovoEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String NUOVOENTE_OUTCOME_CODE__INSERISCI = "inserisci";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R465081669) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			theModel.setAppDataselettoreIdEnte(null);

			// impostazione del result code 
			result.setResultCode(NUOVOENTE_OUTCOME_CODE__INSERISCI);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::nuovoEnte] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAENTE_OUTCOME_CODE__MODIFICA_OK = "modifica_ok";
		final String MODIFICAENTE_OUTCOME_CODE__MODIFICA_KO = "modifica_ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R688735670) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();

			if (theModel.getAppDataselettoreIdEnte() == null) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0001"));
				result.setResultCode(MODIFICAENTE_OUTCOME_CODE__MODIFICA_KO);
				result.setModel(theModel);
				return result;
			}

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<Enti> ente = getGestoreMDPNODONAZIONALEBackOffice()
					.getEntiByParam(user, theModel.getAppDataselettoreIdEnte(),
							null, null, null);
			theModel.setAppDataenti(ente.get(0));
			// impostazione del result code 
			log.error("ente attivo :" + ente.get(0).getAttivo());

			// impostazione del result code 
			result.setResultCode(MODIFICAENTE_OUTCOME_CODE__MODIFICA_OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaEnte] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINAENTE_OUTCOME_CODE__CANCELLA_OK = "cancella_ok";
		final String ELIMINAENTE_OUTCOME_CODE__CANCELLA_KO = "cancella_ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1438900885) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			UserInfoExt user = theModel.getAppDatauserInfoExt();

			PropertiesUtil pu = new PropertiesUtil();
			if (theModel.getAppDataselettoreIdEnte() == null) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0000"));
				result.setResultCode(ELIMINAENTE_OUTCOME_CODE__CANCELLA_KO);
				result.setModel(theModel);
				return result;
			}

			getGestoreMDPNODONAZIONALEBackOffice().updateEnte(user,
					theModel.getAppDataselettoreIdEnte(), "NOUPDATE",
					"NOUPDATE", "0");

			ArrayList<Enti> lista = getGestoreMDPNODONAZIONALEBackOffice()
					.getEntiByParam(user,
							theModel.getAppDataricercaEnti().getEnteId(),
							theModel.getAppDataricercaEnti().getPartitaIva(),
							theModel.getAppDataricercaEnti().getDescrizione(),
							theModel.getAppDataricercaEnti().getAttivo());

			theModel.setAppDatalistaEnti(lista);

			result.setResultCode(ELIMINAENTE_OUTCOME_CODE__CANCELLA_OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::eliminaEnte] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__RICERCA = "Ricerca";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-920396369) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RICERCA);
			}

			ArrayList<StatoAttivazioneEnti> lista = new ArrayList<StatoAttivazioneEnti>();

			StatoAttivazioneEnti kv = new StatoAttivazioneEnti();
			kv.setAttivo("");
			kv.setStato("Tutti gli Stati");
			lista.add(kv);

			StatoAttivazioneEnti kv1 = new StatoAttivazioneEnti();
			kv1.setAttivo("1");
			kv1.setStato("Attivo");
			lista.add(kv1);

			StatoAttivazioneEnti kv0 = new StatoAttivazioneEnti();
			kv0.setAttivo("0");
			kv0.setStato("Disattivo");
			lista.add(kv0);

			theModel.setAppDatalistaStatiAttivazioneEnti(lista);

			log.error("[BackEndFacade::refreshPanel] lista " + lista.size());

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

	public static void resetClearStatus_widg_tRicerca(java.util.Map session) {
		session.put("cpRicercaEnti_tRicerca_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1736700459) ENABLED START*/
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
