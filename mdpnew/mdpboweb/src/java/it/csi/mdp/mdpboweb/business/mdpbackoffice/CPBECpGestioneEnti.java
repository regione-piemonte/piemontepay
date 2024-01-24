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

/*PROTECTED REGION ID(R1639472714) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;
/*PROTECTED REGION END*/

public class CPBECpGestioneEnti {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_ENTI_CODE = "appDataenti";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_SELETTOREIDENTE_CODE = "appDataselettoreIdEnte";

	public final static String APPDATA_LISTASTATIATTIVAZIONEENTI_CODE = "appDatalistaStatiAttivazioneEnti";

	public final static String APPDATA_RICERCAENTI_CODE = "appDataricercaEnti";

	public final static String APPDATA_LISTAENTI_CODE = "appDatalistaEnti";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneEnti";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__INDIETRO = "indietro";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-234891429) ENABLED START*/
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

			// impostazione del result code 
			result.setResultCode(INDIETRO_OUTCOME_CODE__INDIETRO);
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

	public ExecResults inserisci(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INSERISCI_OUTCOME_CODE__INSERISCI = "inserisci";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1954817596) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			// impostazione del result code 
			result.setResultCode(INSERISCI_OUTCOME_CODE__INSERISCI);

			log.info("[CPBECpGestioneEnti::] inserisci 1 "
					+ theModel.getAppDataenti().getEnteId());
			log.info("[CPBECpGestioneEnti::] inserisci 2 "
					+ theModel.getAppDataenti().getPartitaIva());
			log.info("[CPBECpGestioneEnti::] inserisci 3 "
					+ theModel.getAppDataenti().getDescrizione());

			if (!controlloEnteId(theModel.getAppDataenti().getEnteId())) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0002"));
				result.setModel(theModel);
				return result;
			}

			String piMsg = controlloPartitaIva(theModel.getAppDataenti()
					.getPartitaIva(), true);
			log.info("[CPBECpGestioneEnti::] inserisci piMsg = " + piMsg);

			if (!piMsg.equalsIgnoreCase("")) {
				result.getGlobalErrors().add(piMsg);
				result.setModel(theModel);
				return result;
			}

			log.info("[CPBECpGestioneEnti::] inserisci theModel.getAppDataenti().getDescrizione() == null = "
					+ (theModel.getAppDataenti().getDescrizione() == null));
			if (theModel.getAppDataenti().getDescrizione() == null
					|| theModel.getAppDataenti().getDescrizione().trim()
							.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0004"));
				result.setModel(theModel);
				return result;
			}

			ArrayList<Enti> lista = getGestoreMDPNODONAZIONALEBackOffice()
					.getEntiByParam(user,
							theModel.getAppDataenti().getEnteId(), null, null,
							null);
			log.info("[CPBECpGestioneEnti::] inserisci " + lista.size());

			if (lista.size() > 0) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0003"));
				result.setModel(theModel);
				return result;
			}

			log.info("[CPBECpGestioneEnti::] inserisci prima di insert");

			getGestoreMDPNODONAZIONALEBackOffice().insertEnte(user,
					theModel.getAppDataenti().getEnteId(),
					theModel.getAppDataenti().getPartitaIva(),
					theModel.getAppDataenti().getDescrizione(),
					theModel.getAppDataenti().getAttivo());

			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));

			// reset del form
			theModel.getAppDataenti().setEnteId("");
			theModel.getAppDataenti().setPartitaIva("");
			theModel.getAppDataenti().setDescrizione("");
			//theModel.getAppDataenti().setAttivo("");

			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::inserisci] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modifica(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICA_OUTCOME_CODE__MODIFICA = "modifica";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1611413847) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			// impostazione del result code 
			result.setResultCode(MODIFICA_OUTCOME_CODE__MODIFICA);

			if (!controlloEnteId(theModel.getAppDataenti().getEnteId())) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0002"));
				result.setModel(theModel);
				return result;
			}

			String piMsg = controlloPartitaIva(theModel.getAppDataenti()
					.getPartitaIva(), true);
			if (!piMsg.equalsIgnoreCase("")) {
				result.getGlobalErrors().add(piMsg);
				result.setModel(theModel);
				return result;
			}

			if (theModel.getAppDataenti().getDescrizione() == null
					|| theModel.getAppDataenti().getDescrizione().trim()
							.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("ENTI-E-0004"));
				result.setModel(theModel);
				return result;
			}

			log.error("theModel.getAppDataenti().getEnteId()() "
					+ theModel.getAppDataenti().getEnteId());
			log.error("theModel.getAppDataenti().getPartitaIva() "
					+ theModel.getAppDataenti().getPartitaIva());
			log.error("theModel.getAppDataenti().getDescrizione() "
					+ theModel.getAppDataenti().getDescrizione());
			log.error("theModel.getAppDataenti().getAttivo() "
					+ theModel.getAppDataenti().getAttivo());

			getGestoreMDPNODONAZIONALEBackOffice().updateEnte(user,
					theModel.getAppDataenti().getEnteId(),
					theModel.getAppDataenti().getPartitaIva(),
					theModel.getAppDataenti().getDescrizione(),
					theModel.getAppDataenti().getAttivo());

			result.getGlobalMessages().add(pu.getMessage("modificaOk.message"));

			ArrayList<Enti> ente = getGestoreMDPNODONAZIONALEBackOffice()
					.getEntiByParam(user, theModel.getAppDataselettoreIdEnte(),
							null, null, null);

			theModel.setAppDataenti(ente.get(0));

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

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__MODIFICA = "Modifica";
		final String REFRESHPANEL_OUTCOME_CODE__INSERISCI = "Inserisci";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1875684200) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");

				ArrayList<StatoAttivazioneEnti> lista = new ArrayList<StatoAttivazioneEnti>();
				StatoAttivazioneEnti kv1 = new StatoAttivazioneEnti();
				kv1.setAttivo("1");
				kv1.setStato("Attivo");
				lista.add(kv1);

				StatoAttivazioneEnti kv0 = new StatoAttivazioneEnti();
				kv0.setAttivo("0");
				kv0.setStato("Disattivo");
				lista.add(kv0);

				theModel.setAppDatalistaStatiAttivazioneEnti(lista);
				if (theModel.getAppDataselettoreIdEnte() == null) {
					theModel.setAppDataenti(new Enti());
				}
			}

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			if (theModel.getAppDataselettoreIdEnte() == null) {
				// impostazione del result code 
				//theModel.setAppDataenti(new Enti());
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INSERISCI);
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__MODIFICA);
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

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-981623190) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice = null;

	public GestoreMDPNODONAZIONALEBackOffice getGestoreMDPNODONAZIONALEBackOffice() {
		return gestoreMDPNODONAZIONALEBackOffice;
	}

	public void setGestoreMDPNODONAZIONALEBackOffice(
			GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice) {
		this.gestoreMDPNODONAZIONALEBackOffice = gestoreMDPNODONAZIONALEBackOffice;
	}

	private boolean controlloEnteId(String enteId) {
		return Validator.isValidMask(enteId, "[0-9A-Z]{4}");
	}

	private String controlloPartitaIva(String partitaIva, boolean nullisError) {
		log.info("partitaIva " + partitaIva);
		return Validator.controllaPIVA(partitaIva, nullisError);
	}
	/*PROTECTED REGION END*/
}
