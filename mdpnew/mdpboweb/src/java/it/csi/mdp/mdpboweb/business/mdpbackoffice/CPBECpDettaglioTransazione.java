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

/*PROTECTED REGION ID(R884593041) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreCoreMdpBackOffice;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
/*PROTECTED REGION END*/

public class CPBECpDettaglioTransazione {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_TRANSAZIONE_CODE = "appDatatransazione";

	public final static String APPDATA_STATITRANSAZIONE_CODE = "appDatastatiTransazione";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_STATITRANSAZIONEXCAMBIO_CODE = "appDatastatiTransazionexCambio";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioTransazione";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cambiaStatoTransazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__OK = "Ok";
		final String CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1443530692) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			Transazione trans = theModel.getAppDatatransazione();
			long correnteStato = trans.getCodStato();
			long newStato = trans.getCodNuovoStato();
			// CONTROLLI PRIMA DEL SALVATAGGIO
			if (correnteStato != 3) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0113"));
				result.setResultCode(CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			if (!(newStato == 4 || newStato == 5 || newStato == 7 || newStato == 8)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0116"));
				result.setResultCode(CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			try {
				getGestoreMDPBackOffice().aggiornaStatoTransazione(user, trans);
				trans.setCodStato(trans.getCodNuovoStato());
				for (int ik = 0; ik < theModel
						.getAppDatastatiTransazionexCambio().size(); ik++) {
					StatoTransazione sta = theModel
							.getAppDatastatiTransazionexCambio().get(ik);
					if (sta.getCodStato() == trans.getCodStato()) {
						trans.setDescrStato(sta.getDescrStato());
						break;
					}
				}
				result.setResultCode(CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__KO);
				result.getGlobalMessages().add(pu.getMessage("TR-M-0110"));
			} catch (Exception e) {
				String faultString = e.getMessage();
				String message = pu.getMessage("TR-E-0101") + " Exception"
						+ pu.getMessage("TR-E-0102") + " setBoConfig()"
						+ pu.getMessage("TR-E-0103") + faultString
						+ pu.getMessage("TR-E-0104");

				result.setResultCode(CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__KO);
				result.getGlobalErrors().add(message);
				result.setModel(theModel);
				return result;
			}
			/// FINE LOGICA BUSINESS. SI ESCE!
			result.setResultCode(CAMBIASTATOTRANSAZIONE_OUTCOME_CODE__OK);
			theModel.setAppDataisPostBack(null);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cambiaStatoTransazione] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-495290828) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code
			result.setResultCode(INDIETRO_OUTCOME_CODE__OK);
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

	public ExecResults verificaStato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VERIFICASTATO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-272739462) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String transactionId = theModel.getAppDatatransazione()
					.getIdTransazione();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Long codStato = theModel.getAppDatatransazione().getCodStato();

			if (codStato < 3) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0114"));
				result.setModel(theModel);
			} else {
				String descStato = getGestoreCoreMdpBackOffice()
						.getStatoTransazione(user, transactionId);
				//ricarico la pagina
				log.info("//ricarico la pagina");
				theModel.setAppDatatransazione(getGestoreMDPBackOffice()
						.getTransazioneById(user, transactionId));

				result.getGlobalMessages().add(
						pu.getMessage("statoVerificato.message") + "  "
								+ descStato);

			}

			// impostazione del result code
			result.setResultCode(VERIFICASTATO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::verificaStato] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults storno(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String STORNO_OUTCOME_CODE__OK = "Ok";
		final String STORNO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R543234277) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			// CONTROLLI PRIMA DEL SALVATAGGIO
			PropertiesUtil pu = new PropertiesUtil();
			Transazione transLocale = theModel.getAppDatatransazione();
			long correnteStato = transLocale.getCodStato();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String transactionId = transLocale.getIdTransazione();
			long newCodstat = 0;
			String out = "";
			if (correnteStato != 4) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0118"));
				result.setResultCode(STORNO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			Transazione ris = getGestoreMDPBackOffice().refundPayment(user,
					transactionId);

			if (!ris.getErrore().equals("")) {
				result.getGlobalErrors().add(ris.getErrore());
				result.setResultCode(STORNO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			// Ricarico la transazione in con l'eventuale stato cambiato
			transLocale = getGestoreMDPBackOffice().getTransazioneById(user,
					transactionId);

			newCodstat = ris.getCodStato();

			//transLocale = theModel.getAppDatatransazione();
			//TODO  questa porzione di codice e' eliminabile se il servizio tornasse la descrizione
			//da fare successivamente 
			transLocale.setCodStato(newCodstat);
			transLocale.setCodNuovoStato(newCodstat);
			for (int ik = 0; ik < theModel.getAppDatastatiTransazionexCambio()
					.size(); ik++) {
				StatoTransazione sta = theModel
						.getAppDatastatiTransazionexCambio().get(ik);
				if (sta.getCodStato() == newCodstat) {
					transLocale.setDescrStato(sta.getDescrStato());
					break;
				}
			}
			theModel.setAppDatatransazione(transLocale);
			result.getGlobalMessages().add(
					pu.getMessage("stornoEffettuato.message"));

			// impostazione del result code 
			result.setResultCode(STORNO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::storno] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults conferma(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CONFERMA_OUTCOME_CODE__OK = "Ok";
		final String CONFERMA_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1460093445) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			// CONTROLLI PRIMA DEL SALVATAGGIO
			PropertiesUtil pu = new PropertiesUtil();
			Transazione transLocale = theModel.getAppDatatransazione();
			long correnteStato = transLocale.getCodStato();

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String transactionId = transLocale.getIdTransazione();

			if (correnteStato != 8) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0117"));
				result.setResultCode(CONFERMA_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			long newCodstat = 0;

			Transazione ris = getGestoreMDPBackOffice().confirmPayment(user,
					transactionId);

			newCodstat = ris.getCodStato();

			if (!ris.getErrore().equals("")) {
				result.getGlobalErrors().add(ris.getErrore());
				result.setResultCode(CONFERMA_OUTCOME_CODE__KO);
				result.setModel(theModel);

				return result;
			}

			// Ricarico la transazione in con l'eventuale stato cambiato
			transLocale = getGestoreMDPBackOffice().getTransazioneById(user,
					transactionId);

			//transLocale = theModel.getAppDatatransazione();

			//TODO  questa porzione di codice e' eliminabile se il servizio tornasse la descrizione
			//da fare successivamente 
			transLocale.setCodStato(newCodstat);
			transLocale.setCodNuovoStato(newCodstat);
			//log.info("newCodstat " + newCodstat);
			for (int ik = 0; ik < theModel.getAppDatastatiTransazionexCambio()
					.size(); ik++) {
				StatoTransazione sta = theModel
						.getAppDatastatiTransazionexCambio().get(ik);
				if (sta.getCodStato() == newCodstat) {
					transLocale.setDescrStato(sta.getDescrStato());
					break;
				}
			}
			theModel.setAppDatatransazione(transLocale);

			result.getGlobalMessages().add(
					pu.getMessage("confermaEffettuata.message"));
			// impostazione del result code 
			result.setResultCode(CONFERMA_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::conferma] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_CON_CAMBIO_STATO = "Ok_Con_Cambio_Stato";
		final String REFRESHPANEL_OUTCOME_CODE__OK_SENZA_CAMBIO_STATO = "Ok_Senza_Cambio_Stato";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1371157953) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_CON_CAMBIO_STATO);
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
	/*PROTECTED REGION ID(R745819843) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPBackOffice gestoreMDPBackOffice = null;

	public GestoreMDPBackOffice getGestoreMDPBackOffice() {
		return gestoreMDPBackOffice;
	}

	public void setGestoreMDPBackOffice(
			GestoreMDPBackOffice gestoreMDPBackOffice) {
		this.gestoreMDPBackOffice = gestoreMDPBackOffice;
	}

	GestoreCoreMdpBackOffice gestoreCoreMdpBackOffice = null;

	public GestoreCoreMdpBackOffice getGestoreCoreMdpBackOffice() {
		return gestoreCoreMdpBackOffice;
	}

	public void setGestoreCoreMDPBackOffice(
			GestoreCoreMdpBackOffice gestoreCoreMdpBackOffice) {
		this.gestoreCoreMdpBackOffice = gestoreCoreMdpBackOffice;
	}

	/*PROTECTED REGION END*/
}
