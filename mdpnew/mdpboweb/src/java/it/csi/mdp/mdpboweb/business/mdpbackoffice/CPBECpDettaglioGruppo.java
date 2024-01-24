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

/*PROTECTED REGION ID(R186783400) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;

/*PROTECTED REGION END*/

public class CPBECpDettaglioGruppo {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_SELETTOREIDRUOLO_CODE = "appDataselettoreIdRuolo";

	public final static String APPDATA_APPFORGROUP_CODE = "appDataappForGroup";

	public final static String APPDATA_APPFORGROUPFILTERED_CODE = "appDataappForGroupFiltered";

	public final static String APPDATA_SELETTOREAPPGROUP_CODE = "appDataselettoreAppGroup";

	public final static String APPDATA_FILTEREDAPPLICATIONGROUP_CODE = "appDatafilteredApplicationGroup";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_GRUPPO_CODE = "appDatagruppo";

	public final static String APPDATA_RUOLI_CODE = "appDataruoli";

	public final static String APPDATA_RUOLO_CODE = "appDataruolo";

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_SELETTOREGRUPPO_CODE = "appDataselettoreGruppo";

	public final static String APPDATA_UTENTIDELGRUPPO_CODE = "appDatautentiDelGruppo";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioGruppo";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults addApplicazioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R397401057) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			// id applicazione selezionata
			String idApplicazioneToAdd = theModel.getAppDataselettoreAppGroup();

			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve
															// selezionare una
															// applicazione
															// della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna
															// applicazione
															// disponibile :
															// messaggio
															// "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto

			if (idApplicazioneToAdd.equals(INCLUDE_SEL)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0009"));
				result.setResultCode(ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (idApplicazioneToAdd.equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0010"));
				result.setResultCode(ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Applicazione> aApps = theModel.getAppDataapplicazioni();

			if (idApplicazioneToAdd.equals(INCLUDE_ALL)) {
				theModel.setAppDataappForGroupFiltered(new ArrayList<Applicazione>());
				ArrayList<Applicazione> aAppFiltered = new ArrayList<Applicazione>();

				for (int k = 0; k < aApps.size(); k++) {
					Applicazione app = aApps.get(k);
					aAppFiltered.add(app);
				}
				theModel.setAppDataappForGroupFiltered(aAppFiltered);

				ArrayList<Applicazione> aAppTemp = new ArrayList<Applicazione>();
				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(INCLUDE_NONE);
				fakeApp.setNomeApplicazione("Nessuna applicazione da aggiungere ");
				aAppTemp.add(fakeApp);

				fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(REMOVE_ALL);
				fakeApp.setNomeApplicazione("Rimuovi tutte le applicazioni ");
				aAppTemp.add(fakeApp);

				theModel.setAppDataappForGroup(aAppTemp);
				result.setResultCode(ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			if (idApplicazioneToAdd.equals(REMOVE_ALL)) {
				ArrayList<Applicazione> aApp = null;
				aApp = theModel.getAppDataapplicazioni();

				ArrayList<Applicazione> aAppCompleta = new ArrayList<Applicazione>();

				if (aApp.size() > 0) {
					Applicazione fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(INCLUDE_SEL);
					fakeApp.setNomeApplicazione("Seleziona l'applicazione da includere");

					aAppCompleta.add(fakeApp);
					aAppCompleta.addAll(aApp);

					fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(INCLUDE_ALL);
					fakeApp.setNomeApplicazione("Includi tutte le applicazioni ");
					aAppCompleta.add(fakeApp);

					fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(REMOVE_ALL);
					fakeApp.setNomeApplicazione("Rimuovi tutte le applicazioni ");
					aAppCompleta.add(fakeApp);

				} else {
					Applicazione fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(INCLUDE_NONE);
					fakeApp.setNomeApplicazione("Nessuna applicazione disponibile");
					aAppCompleta.add(fakeApp);
				}

				theModel.setAppDataappForGroup(aAppCompleta);

				ArrayList<Applicazione> aApp2 = new ArrayList<Applicazione>();
				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(INCLUDE_NONE);
				fakeApp.setNomeApplicazione("Nessuna applicazione legata al gruppo");
				aApp2.add(fakeApp);
				theModel.setAppDataappForGroupFiltered(aApp2);

				result.setResultCode(ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			// Se nessuno dei precedenti
			// allora si e' aggiunta una singola applicazione al filtro
			int bFound = -1;
			Applicazione appToAdd = null;
			for (int k = 0; k < aApps.size(); k++) {
				Applicazione app = aApps.get(k);
				if (app.getIdApplicazione().equals(idApplicazioneToAdd)) {
					bFound = k;
					appToAdd = app;
					break;
				}
			}

			if (appToAdd != null) {
				if (theModel.getAppDataappForGroupFiltered().size() == 1) {
					String ss = (String) theModel
							.getAppDataappForGroupFiltered().get(0)
							.getIdApplicazione();
					if (ss.equals(INCLUDE_NONE)) {
						theModel.setAppDataappForGroupFiltered(new ArrayList<Applicazione>());
					}
				}

				theModel.getAppDataappForGroupFiltered().add(appToAdd);
				for (int n = 0; n < theModel.getAppDataappForGroup().size(); n++) {
					Applicazione app = theModel.getAppDataappForGroup().get(n);
					if (app.getIdApplicazione().equals(idApplicazioneToAdd)) {
						theModel.getAppDataappForGroup().remove(n);
						break;
					}
				}

				result.setResultCode(ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__OK);
			} else {
				result.setResultCode(ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::addApplicazioneFiltro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaApplicazioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R11491857) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve
															// selezionare una
															// applicazione
															// della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna
															// applicazione
															// disponibile :
															// messaggio
															// "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto

			// impostazione del result code
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			ArrayList<String> selApp = theModel
					.getAppDatafilteredApplicationGroup();

			if (selApp == null || selApp.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0007"));
				result.setResultCode(ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (selApp.size() == 1 && selApp.get(0).equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-008"));
				result.setResultCode(ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Applicazione> aApp3 = theModel
					.getAppDataappForGroupFiltered();
			if (aApp3.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("UT-E-0008"));
				result.setResultCode(ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			// Rimuove l'app dal filtro e
			// Riposiziona l'app nella combo dei filtri
			for (int h = 0; h < selApp.size(); h++) {
				String idAppToDelete = selApp.get(h);
				Applicazione appToRemove = null;
				for (int k = 0; k < aApp3.size(); k++) {
					Applicazione app = aApp3.get(k);
					if (app.getIdApplicazione().equals(idAppToDelete)) {
						appToRemove = app;
						aApp3.remove(k);
						if (aApp3.size() == 0) {
							Applicazione fakeApp = new Applicazione();
							fakeApp.setIdApplicazione(INCLUDE_NONE);
							fakeApp.setNomeApplicazione("Nessuna applicazione");
							aApp3.add(fakeApp);
						}
						theModel.setAppDataappForGroupFiltered(aApp3);

						ArrayList<Applicazione> aApp4 = theModel
								.getAppDataappForGroup();
						for (int k2 = 1; k2 < aApp4.size(); k2++) {
							String idApp3 = aApp4.get(k2).getIdApplicazione();
							if (!idApp3.equals(INCLUDE_ALL)
									&& !idApp3.equals(INCLUDE_NONE)
									&& !idApp3.equals(REMOVE_ALL)
									&& !idApp3.equals(INCLUDE_SEL)) {
								if (aApp4
										.get(k2)
										.getNomeApplicazione()
										.compareTo(
												appToRemove
														.getNomeApplicazione()) > 0) {

									aApp4.add(k2, appToRemove);
									break;
								}
							} else {

								aApp4.add(k2, appToRemove);
								break;
							}
						}
						theModel.setAppDataappForGroup(aApp4);
						break;
					}
				}
			}

			result.setResultCode(ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::eliminaApplicazioneFiltro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1922721475) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			theModel.setAppDataisPostBack("S");
			// impostazione del result code
			result.setResultCode(INDIETRO_OUTCOME_CODE__OK);
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

	public ExecResults salvaGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAGRUPPO_OUTCOME_CODE__OK = "Ok";
		final String SALVAGRUPPO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1320727241) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@";
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Gruppo gruppo = theModel.getAppDatagruppo();
			String idRuoloSel = theModel.getAppDataselettoreIdRuolo();

			if (!Validator.isValidRequired(gruppo.getDescription())) {
				result.getGlobalErrors().add(pu.getMessage("GR-E-0002"));
				result.setResultCode(SALVAGRUPPO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (!Validator.isValidRequired(idRuoloSel)) {
				result.getGlobalErrors().add(pu.getMessage("GR-E-0003"));
				result.setResultCode(SALVAGRUPPO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			GruppoRuolo gruppoRuolo = new GruppoRuolo();
			gruppoRuolo.setIdruolo(idRuoloSel);
			if (gruppo.getIdgroup() != null && !gruppo.getIdgroup().equals("")) {
				gruppoRuolo.setIdgroup(gruppo.getIdgroup());
			}
			// lista delle applicazioni selezionate da associare al gruppo in
			// esame
			ArrayList<Applicazione> listaApp = theModel
					.getAppDataappForGroupFiltered();

			// lista delle relazioni applicazioni/gruppo
			ArrayList<GruppoApplicazione> listGruppoApp = new ArrayList<GruppoApplicazione>();
			for (int t = 0; t < listaApp.size(); t++) {
				if (!listaApp.get(t).getIdApplicazione().equals(INCLUDE_NONE)) {
					GruppoApplicazione ga = new GruppoApplicazione();
					ga.setIdapp(listaApp.get(t).getIdApplicazione());
					if (gruppo.getIdgroup() != null
							&& !gruppo.getIdgroup().equals("")) {
						ga.setIdgroup(gruppo.getIdgroup());
					}
					listGruppoApp.add(ga);
				}
			}

			// setto il gruppo
			getGestoreMDPBackOffice().setMdpGroup(user, gruppo, listGruppoApp,
					gruppoRuolo);
			// impostazione del result code
			result.setResultCode(SALVAGRUPPO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaGruppo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R2094705738) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@";
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@";
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@";
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@";
			String isPostBack = theModel.getAppDataisPostBack();
			//PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idGruppo = "";
			if (theModel.getAppDatagruppo() != null) {
				idGruppo = theModel.getAppDatagruppo().getIdgroup();

			}
			// carico la combo dei ruoli se non resente in sessione
			if (theModel.getAppDataruoli() == null
					|| theModel.getAppDataruoli().size() == 0) {
				theModel.setAppDataruoli(getGestoreMDPBackOffice()
						.getMdpBckRoles(user));
			}

			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {

				// setto la prima combo con tutte le applicazioni
				ArrayList<Applicazione> aAppAll = getGestoreMDPBackOffice()
						.getListaApplicazioniByUser(user);

				theModel.setAppDataapplicazioni(aAppAll);
				// istanza della seconda combo applicazioni che conterra'
				// gli elementi associati al gruppo
				ArrayList<Applicazione> aApp2 = new ArrayList<Applicazione>();
				// se non sono in fase di insert idGruppo != ""
				if (idGruppo != null && !idGruppo.equals("")) {
					Gruppo gruppoSel = theModel.getAppDatagruppo();
					// setto la combo sul ruolo corrente
					theModel.setAppDataselettoreIdRuolo(gruppoSel.getIdRuolo());
					// setto la seconda combo con le applicazioni del gruppo
					aApp2 = gruppoSel.getApplicazioni();
					Collections.sort(aAppAll,
							Comparators.APPLICAZIONE_COMPARATOR);
					Collections
							.sort(aApp2, Comparators.APPLICAZIONE_COMPARATOR);

					int i = 0, j = 0;
					while (j < aApp2.size() && i < aAppAll.size()) {
						if (Comparators.APPLICAZIONE_COMPARATOR.compare(
								aAppAll.get(i), aApp2.get(j)) != 0) {
							i++;
						} else {
							aAppAll.remove(i);
							j++;
						}
					}
				}
				ArrayList<Applicazione> aAppCompleta = new ArrayList<Applicazione>();
				// setto la prima combo con tutte le applicazioni
				if (aAppAll.size() > 0) {
					Applicazione fakeApp = new Applicazione();
					// inserisco la prima riga della lista
					fakeApp.setIdApplicazione(INCLUDE_SEL);
					fakeApp.setNomeApplicazione("Seleziona l'applicazione da includere");

					aAppCompleta.add(fakeApp);
					aAppCompleta.addAll(aAppAll);
					// inserisco le ultime righe della lista
					fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(INCLUDE_ALL);
					fakeApp.setNomeApplicazione("Includi tutte le applicazioni nel filtro");
					aAppCompleta.add(fakeApp);

					fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(REMOVE_ALL);
					fakeApp.setNomeApplicazione("Rimuovi tutte le applicazioni dal filtro");
					aAppCompleta.add(fakeApp);

				} else {
					Applicazione fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(INCLUDE_NONE);
					fakeApp.setNomeApplicazione("Nessuna applicazione disponibile");
					aAppCompleta.add(fakeApp);
				}
				theModel.setAppDataappForGroup(aAppCompleta);
				// setto la seconda combo con le applicazioni del gruppo piu'
				// voce iniziale
				if (aApp2.size() == 0) {
					Applicazione fakeApp = new Applicazione();
					fakeApp.setIdApplicazione(INCLUDE_NONE);
					fakeApp.setNomeApplicazione("Nessuna applicazione associata al gruppo");
					aApp2.add(fakeApp);
				}
				theModel.setAppDataappForGroupFiltered(aApp2);

				if (theModel.getAppDatagruppo() != null
						&& theModel.getAppDatagruppo().getUtenti().size() > 0) {
					theModel.setAppDatautentiDelGruppo(theModel
							.getAppDatagruppo().getUtenti());
				}
				// impostazione del result code
				theModel.setAppDataisPostBack("S");
			}
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);

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

	public static void resetClearStatus_widg_tbListaUtenti(java.util.Map session) {
		session.put("cpDettaglioGruppo_tbListaUtenti_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1699790150) ENABLED START*/
	// // inserire qui le property che si vogliono iniettare in questo bean (es.
	// dao, proxy di pd, ...)
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
