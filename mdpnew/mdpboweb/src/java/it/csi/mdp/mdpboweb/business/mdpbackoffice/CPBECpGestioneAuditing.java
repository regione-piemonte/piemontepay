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

/*PROTECTED REGION ID(R-1435404063) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.util.PropertiesUtil;

/*PROTECTED REGION END*/

public class CPBECpGestioneAuditing {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_APPFORAUDIT_CODE = "appDataappForAudit";

	public final static String APPDATA_APPFORAUDITFILTERED_CODE = "appDataappForAuditFiltered";

	public final static String APPDATA_APPLICAZIONE_CODE = "appDataapplicazione";

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_ASSOCIAZIONEGW_MP_CODE = "appDataassociazioneGW_MP";

	public final static String APPDATA_ASSOCIAZIONIGW_MP_CODE = "appDataassociazioniGW_MP";

	public final static String APPDATA_AUDIT_CODE = "appDataaudit";

	public final static String APPDATA_AUDITIES_CODE = "appDataaudities";

	public final static String APPDATA_AZIONE_CODE = "appDataazione";

	public final static String APPDATA_AZIONI_CODE = "appDataazioni";

	public final static String APPDATA_AZIONIFORAUDIT_CODE = "appDataazioniForAudit";

	public final static String APPDATA_AZIONIFORAUDITFILTERED_CODE = "appDataazioniForAuditFiltered";

	public final static String APPDATA_CHKABILITAASSGW_MP_CODE = "appDatachkAbilitaAssGW_MP";

	public final static String APPDATA_EXTRAATTRIBUTES_CODE = "appDataextraAttributes";

	public final static String APPDATA_FILTEREDAPPLICATION_CODE = "appDatafilteredApplication";

	public final static String APPDATA_FILTEREDAZIONI_CODE = "appDatafilteredAzioni";

	public final static String APPDATA_GATEWAY_CODE = "appDatagateway";

	public final static String APPDATA_GATEWAYS_CODE = "appDatagateways";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_NUOVAASSOCIAZIONEGW_MP_CODE = "appDatanuovaAssociazioneGW_MP";

	public final static String APPDATA_NUOVOEXTRAATTRIBUTE_CODE = "appDatanuovoExtraAttribute";

	public final static String APPDATA_PAYMENTMODE_CODE = "appDatapaymentMode";

	public final static String APPDATA_PAYMENTMODES_CODE = "appDatapaymentModes";

	public final static String APPDATA_RICERCAAUDIT_CODE = "appDataricercaAudit";

	public final static String APPDATA_RICERCATRANSAZIONE_CODE = "appDataricercaTransazione";

	public final static String APPDATA_SELETTOREAPP1_CODE = "appDataselettoreApp1";

	public final static String APPDATA_SELETTOREAPP2_CODE = "appDataselettoreApp2";

	public final static String APPDATA_SELETTOREIDTRANSAZIONE_CODE = "appDataselettoreIdTransazione";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_SELETTORECHIAVEATTR_CODE = "appDataselettoreChiaveAttr";

	public final static String APPDATA_SELETTOREIDAPPLICAZIONE_CODE = "appDataselettoreIdApplicazione";

	public final static String APPDATA_SELETTOREIDGATEWAY_CODE = "appDataselettoreIdGateway";

	public final static String APPDATA_SELETTOREIDPAYMENTMODE_CODE = "appDataselettoreIdPaymentMode";

	public final static String APPDATA_SELETTOREAZIONE1_CODE = "appDataselettoreAzione1";

	public final static String APPDATA_SELETTOREITEMASSOCIAZIONEGW_MP_CODE = "appDataselettoreItemAssociazioneGW_MP";

	public final static String APPDATA_SELETTOREAZIONE2_CODE = "appDataselettoreAzione2";

	public final static String APPDATA_STATITRANSAZIONE_CODE = "appDatastatiTransazione";

	public final static String APPDATA_SELETTOREUTENTE1_CODE = "appDataselettoreUtente1";

	public final static String APPDATA_TRANSAZIONE_CODE = "appDatatransazione";

	public final static String APPDATA_STATOTRANSAZIONE_CODE = "appDatastatoTransazione";

	public final static String APPDATA_SELETTOREUTENTE2_CODE = "appDataselettoreUtente2";

	public final static String APPDATA_TRANSAZIONI_CODE = "appDatatransazioni";

	public final static String APPDATA_UTENTE_CODE = "appDatautente";

	public final static String APPDATA_UTENTI_CODE = "appDatautenti";

	public final static String APPDATA_UTENTIFORAUDIT_CODE = "appDatautentiForAudit";

	public final static String APPDATA_UTENTIFORAUDITFILTERED_CODE = "appDatautentiForAuditFiltered";

	public final static String APPDATA_FILTEREDUTENTI_CODE = "appDatafilteredUtenti";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneAuditing";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ADDAPPLICAZIONEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1435035546) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			// id applicazione selezionata
			String idApplicazioneToAdd = theModel.getAppDataselettoreApp1();
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve selezionare una applicazione della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna applicazione disponibile : messaggio "Non si possono aggiungere applicazioni"
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
				theModel.setAppDataappForAuditFiltered(new ArrayList<Applicazione>());
				ArrayList<Applicazione> aAppFiltered = new ArrayList<Applicazione>();
				for (int k = 0; k < aApps.size(); k++) {
					Applicazione app = aApps.get(k);
					aAppFiltered.add(app);
				}
				theModel.setAppDataappForAuditFiltered(aAppFiltered);
				ArrayList<Applicazione> aAppTemp = new ArrayList<Applicazione>();
				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(INCLUDE_NONE);
				fakeApp.setNomeApplicazione("Nessuna applicazione da aggiungere al filtro");
				aAppTemp.add(fakeApp);

				fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(REMOVE_ALL);
				fakeApp.setNomeApplicazione("Rimuovi tutte le applicazioni dal filtro");
				aAppTemp.add(fakeApp);

				theModel.setAppDataappForAudit(aAppTemp);
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
				theModel.setAppDataappForAudit(aAppCompleta);
				ArrayList<Applicazione> aApp2 = new ArrayList<Applicazione>();
				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(INCLUDE_NONE);
				fakeApp.setNomeApplicazione("Nessuna filtro per applicazione");
				aApp2.add(fakeApp);
				theModel.setAppDataappForAuditFiltered(aApp2);
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
				if (theModel.getAppDataappForAuditFiltered().size() == 1) {
					String ss = (String) theModel
							.getAppDataappForAuditFiltered().get(0)
							.getIdApplicazione();
					if (ss.equals(INCLUDE_NONE)) {
						theModel.setAppDataappForAuditFiltered(new ArrayList<Applicazione>());
					}
				}

				theModel.getAppDataappForAuditFiltered().add(appToAdd);
				for (int n = 0; n < theModel.getAppDataappForAudit().size(); n++) {
					Applicazione app = theModel.getAppDataappForAudit().get(n);
					if (app.getIdApplicazione().equals(idApplicazioneToAdd)) {
						theModel.getAppDataappForAudit().remove(n);
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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1329193290) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:	
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve selezionare una applicazione della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna applicazione disponibile : messaggio "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto
			// impostazione del result code
			PropertiesUtil pu = new PropertiesUtil();
			//UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<String> selApp = theModel.getAppDatafilteredApplication();

			if (selApp == null || selApp.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0008"));
				result.setResultCode(ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (selApp.size() == 1 && selApp.get(0).equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0011"));
				result.setResultCode(ELIMINAAPPLICAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Applicazione> aApp3 = theModel
					.getAppDataappForAuditFiltered();
			if (aApp3.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0011"));
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
							fakeApp.setNomeApplicazione("Nessuna filtro per applicazione");
							aApp3.add(fakeApp);
						}
						theModel.setAppDataappForAuditFiltered(aApp3);
						ArrayList<Applicazione> aApp4 = theModel
								.getAppDataappForAudit();
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
						theModel.setAppDataappForAudit(aApp4);
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

	public ExecResults addAzioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ADDAZIONEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ADDAZIONEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-714189185) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String idAzioneToAdd = theModel.getAppDataselettoreAzione1();
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve selezionare una applicazione della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna applicazione disponibile : messaggio "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto

			if (idAzioneToAdd.equals(INCLUDE_SEL)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0012"));;
				result.setResultCode(ADDAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (idAzioneToAdd.equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0013"));
				result.setResultCode(ADDAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Azione> aAzis = theModel.getAppDataazioni();

			if (idAzioneToAdd.equals(INCLUDE_ALL)) {
				theModel.setAppDataazioniForAuditFiltered(new ArrayList<Azione>());
				ArrayList<Azione> aAziFiltered = new ArrayList<Azione>();
				for (int k = 0; k < aAzis.size(); k++) {
					Azione azi = aAzis.get(k);
					aAziFiltered.add(azi);
				}
				theModel.setAppDataazioniForAuditFiltered(aAziFiltered);

				ArrayList<Azione> aAziTemp = new ArrayList<Azione>();
				Azione fakeAzi = new Azione();
				fakeAzi.setIdAzione(INCLUDE_NONE);
				fakeAzi.setDescrAzione("Nessuna azione da aggiungere al filtro");
				aAziTemp.add(fakeAzi);

				fakeAzi = new Azione();
				fakeAzi.setIdAzione(REMOVE_ALL);
				fakeAzi.setDescrAzione("Rimuovi tutte le azioni dal filtro");
				aAziTemp.add(fakeAzi);
				theModel.setAppDataazioniForAudit(aAziTemp);
				result.setResultCode(ADDAZIONEFILTRO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			if (idAzioneToAdd.equals(REMOVE_ALL)) {
				ArrayList<Azione> aAzi = null;
				aAzi = theModel.getAppDataazioni();
				ArrayList<Azione> aAziCompleta = new ArrayList<Azione>();
				if (aAzi.size() > 0) {
					Azione fakeAzi = new Azione();
					fakeAzi.setIdAzione(INCLUDE_SEL);
					fakeAzi.setDescrAzione("Seleziona l'azione da includere");
					aAziCompleta.add(fakeAzi);
					aAziCompleta.addAll(aAzi);

					fakeAzi = new Azione();
					fakeAzi.setIdAzione(INCLUDE_ALL);
					fakeAzi.setDescrAzione("Includi tutte le azioni nel filtro");
					aAziCompleta.add(fakeAzi);

					fakeAzi = new Azione();
					fakeAzi.setIdAzione(REMOVE_ALL);
					fakeAzi.setDescrAzione("Rimuovi tutte le azioni dal filtro");
					aAziCompleta.add(fakeAzi);

				} else {
					Azione fakeAzi = new Azione();
					fakeAzi.setIdAzione(INCLUDE_NONE);
					fakeAzi.setDescrAzione("Nessuna azione disponibile");
					aAziCompleta.add(fakeAzi);
				}

				theModel.setAppDataazioniForAudit(aAziCompleta);

				ArrayList<Azione> aAzi2 = new ArrayList<Azione>();
				Azione fakeAzi = new Azione();
				fakeAzi.setIdAzione(INCLUDE_NONE);
				fakeAzi.setDescrAzione("Nessun filtro per azione");
				aAzi2.add(fakeAzi);
				theModel.setAppDataazioniForAuditFiltered(aAzi2);
				result.setResultCode(ADDAZIONEFILTRO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			// Se nessuno dei precedenti 
			// allora si e' aggiunta una singola azione al filtro
			Azione aziToAdd = null;
			for (int k = 0; k < aAzis.size(); k++) {
				Azione azi = aAzis.get(k);
				if (azi.getIdAzione().equals(idAzioneToAdd)) {
					aziToAdd = azi;
					break;
				}
			}
			if (aziToAdd != null) {
				if (theModel.getAppDataazioniForAuditFiltered().size() == 1) {
					String ss = (String) theModel
							.getAppDataazioniForAuditFiltered().get(0)
							.getIdAzione();
					if (ss.equals(INCLUDE_NONE)) {
						theModel.setAppDataazioniForAuditFiltered(new ArrayList<Azione>());
					}
				}

				theModel.getAppDataazioniForAuditFiltered().add(aziToAdd);
				for (int n = 0; n < theModel.getAppDataazioniForAudit().size(); n++) {
					Azione app = theModel.getAppDataazioniForAudit().get(n);
					if (app.getIdAzione().equals(idAzioneToAdd)) {
						theModel.getAppDataazioniForAudit().remove(n);
						break;
					}
				}

				result.setResultCode(ADDAZIONEFILTRO_OUTCOME_CODE__OK);
			} else {
				result.setResultCode(ADDAZIONEFILTRO_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::addAzioneFiltro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaAzioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINAAZIONEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ELIMINAAZIONEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1057510447) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve selezionare una applicazione della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna applicazione disponibile : messaggio "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto
			// impostazione del result code
			PropertiesUtil pu = new PropertiesUtil();
			//UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<String> selAzi = theModel.getAppDatafilteredAzioni();
			if (selAzi == null || selAzi.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0018"));
				result.setResultCode(ELIMINAAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (selAzi.size() == 1 && selAzi.get(0).equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0014"));
				result.setResultCode(ELIMINAAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Azione> aAzi3 = theModel
					.getAppDataazioniForAuditFiltered();
			if (aAzi3.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0014"));
				result.setResultCode(ELIMINAAZIONEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			// Rimuove l'azione dal filtro e
			// Riposiziona l'azione nella combo dei filtri
			for (int h = 0; h < selAzi.size(); h++) {
				String idAziToDelete = selAzi.get(h);
				Azione aziToRemove = null;
				for (int k = 0; k < aAzi3.size(); k++) {
					Azione azi = aAzi3.get(k);
					if (azi.getIdAzione().equals(idAziToDelete)) {
						aziToRemove = azi;
						aAzi3.remove(k);
						if (aAzi3.size() == 0) {
							Azione fakeApp = new Azione();
							fakeApp.setIdAzione(INCLUDE_NONE);
							fakeApp.setDescrAzione("Nessuna filtro per azione");
							aAzi3.add(fakeApp);
						}
						theModel.setAppDataazioniForAuditFiltered(aAzi3);
						ArrayList<Azione> aAzi4 = theModel
								.getAppDataazioniForAudit();
						for (int k2 = 1; k2 < aAzi4.size(); k2++) {
							String idAzi3 = aAzi4.get(k2).getIdAzione();
							if (!idAzi3.equals(INCLUDE_ALL)
									&& !idAzi3.equals(INCLUDE_NONE)
									&& !idAzi3.equals(REMOVE_ALL)
									&& !idAzi3.equals(INCLUDE_SEL)) {
								if (aAzi4
										.get(k2)
										.getDescrAzione()
										.compareTo(aziToRemove.getDescrAzione()) > 0) {
									aAzi4.add(k2, aziToRemove);
									break;
								}
							} else {
								aAzi4.add(k2, aziToRemove);
								break;
							}
						}
						theModel.setAppDataazioniForAudit(aAzi4);
						break;
					}
				}
			}
			result.setResultCode(ELIMINAAZIONEFILTRO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::eliminaAzioneFiltro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults addUtenteFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ADDUTENTEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ADDUTENTEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R655306684) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String idUtenteToAdd = theModel.getAppDataselettoreUtente1();
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve selezionare una applicazione della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna applicazione disponibile : messaggio "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto

			if (idUtenteToAdd.equals(INCLUDE_SEL)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0015"));
				result.setResultCode(ADDUTENTEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (idUtenteToAdd.equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0016"));
				result.setResultCode(ADDUTENTEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Utente> aUtenti = theModel.getAppDatautenti();

			if (idUtenteToAdd.equals(INCLUDE_ALL)) {
				theModel.setAppDatautentiForAuditFiltered(new ArrayList<Utente>());
				ArrayList<Utente> aUtentiFiltered = new ArrayList<Utente>();
				for (int k = 0; k < aUtenti.size(); k++) {
					Utente ute = aUtenti.get(k);
					aUtentiFiltered.add(ute);
				}
				theModel.setAppDatautentiForAuditFiltered(aUtentiFiltered);

				ArrayList<Utente> aUteTemp = new ArrayList<Utente>();
				Utente fakeUte = new Utente();
				fakeUte.setIdUtente(INCLUDE_NONE);
				fakeUte.setDescrUtente("Nessun utente da aggiungere al filtro");
				aUteTemp.add(fakeUte);

				fakeUte = new Utente();
				fakeUte.setIdUtente(REMOVE_ALL);
				fakeUte.setDescrUtente("Rimuovi tutti gli utenti dal filtro");
				aUteTemp.add(fakeUte);

				theModel.setAppDatautentiForAudit(aUteTemp);

				result.setResultCode(ADDUTENTEFILTRO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			if (idUtenteToAdd.equals(REMOVE_ALL)) {
				ArrayList<Utente> aUte = null;
				aUte = theModel.getAppDatautenti();

				ArrayList<Utente> aUteCompleta = new ArrayList<Utente>();

				if (aUte.size() > 0) {
					Utente fakeUte = new Utente();
					fakeUte.setIdUtente(INCLUDE_SEL);
					fakeUte.setDescrUtente("Seleziona l'utente da includere");

					aUteCompleta.add(fakeUte);
					aUteCompleta.addAll(aUte);

					fakeUte = new Utente();
					fakeUte.setIdUtente(INCLUDE_ALL);
					fakeUte.setDescrUtente("Includi tutti gli utenti nel filtro");
					aUteCompleta.add(fakeUte);

					fakeUte = new Utente();
					fakeUte.setIdUtente(REMOVE_ALL);
					fakeUte.setDescrUtente("Rimuovi tutti gli utenti dal filtro");
					aUteCompleta.add(fakeUte);

				} else {
					Utente fakeUte = new Utente();
					fakeUte.setIdUtente(INCLUDE_NONE);
					fakeUte.setDescrUtente("Nessun utente disponibile");
					aUteCompleta.add(fakeUte);
				}

				theModel.setAppDatautentiForAudit(aUteCompleta);

				ArrayList<Utente> aUte2 = new ArrayList<Utente>();
				Utente fakeUte = new Utente();
				fakeUte.setIdUtente(INCLUDE_NONE);
				fakeUte.setDescrUtente("Nessun filtro per azione");
				aUte2.add(fakeUte);
				theModel.setAppDatautentiForAuditFiltered(aUte2);
				result.setResultCode(ADDUTENTEFILTRO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			// Se nessuno dei precedenti 
			// allora si e' aggiunta una singola applicazione al filtro

			Utente uteToAdd = null;
			for (int k = 0; k < aUtenti.size(); k++) {
				Utente ute = aUtenti.get(k);
				if (ute.getIdUtente().equals(idUtenteToAdd)) {

					uteToAdd = ute;
					break;
				}
			}
			if (uteToAdd != null) {
				if (theModel.getAppDatautentiForAuditFiltered().size() == 1) {
					String ss = (String) theModel
							.getAppDatautentiForAuditFiltered().get(0)
							.getIdUtente();
					if (ss.equals(INCLUDE_NONE)) {
						theModel.setAppDatautentiForAuditFiltered(new ArrayList<Utente>());
					}
				}
				theModel.getAppDatautentiForAuditFiltered().add(uteToAdd);
				for (int n = 0; n < theModel.getAppDatautentiForAudit().size(); n++) {
					Utente ute = theModel.getAppDatautentiForAudit().get(n);
					if (ute.getIdUtente().equals(idUtenteToAdd)) {
						theModel.getAppDatautentiForAudit().remove(n);
						break;
					}
				}
				result.setResultCode(ADDUTENTEFILTRO_OUTCOME_CODE__OK);
			} else {
				result.setResultCode(ADDUTENTEFILTRO_OUTCOME_CODE__KO);
			}
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::addUtenteFiltro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaUtenteFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINAUTENTEFILTRO_OUTCOME_CODE__OK = "Ok";
		final String ELIMINAUTENTEFILTRO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1867960980) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@"; // Includi tutto
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@"; // Segnala che si deve selezionare una applicazione della combo
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@"; // Nessuna applicazione disponibile : messaggio "Non si possono aggiungere applicazioni"
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@"; // Rimuove tutto
			// impostazione del result code
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			ArrayList<String> selUte = theModel.getAppDatafilteredUtenti();

			if (selUte == null || selUte.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0019"));
				result.setResultCode(ELIMINAUTENTEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			if (selUte.size() == 1 && selUte.get(0).equals(INCLUDE_NONE)) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0017"));
				result.setResultCode(ELIMINAUTENTEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			ArrayList<Utente> aUte3 = theModel
					.getAppDatautentiForAuditFiltered();
			if (selUte.size() == 0) {
				result.getGlobalErrors().add(pu.getMessage("TR-E-0017"));;
				result.setResultCode(ELIMINAUTENTEFILTRO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			// Rimuove utente dal filtro e
			// Riposiziona l'app nella combo dei filtri
			for (int h = 0; h < selUte.size(); h++) {
				String idUteToDelete = selUte.get(h);
				Utente uteToRemove = null;
				for (int k = 0; k < aUte3.size(); k++) {
					Utente app = aUte3.get(k);
					if (app.getIdUtente().equals(idUteToDelete)) {
						uteToRemove = app;
						aUte3.remove(k);
						if (aUte3.size() == 0) {
							Utente fakeApp = new Utente();
							fakeApp.setIdUtente(INCLUDE_NONE);
							fakeApp.setDescrUtente("Nessun filtro per utente");
							aUte3.add(fakeApp);
						}
						theModel.setAppDatautentiForAuditFiltered(aUte3);

						ArrayList<Utente> aAUte4 = theModel
								.getAppDatautentiForAudit();
						for (int k2 = 1; k2 < aAUte4.size(); k2++) {
							String idute3 = aAUte4.get(k2).getIdUtente();
							if (!idute3.equals(INCLUDE_ALL)
									&& !idute3.equals(INCLUDE_NONE)
									&& !idute3.equals(REMOVE_ALL)
									&& !idute3.equals(INCLUDE_SEL)) {
								if (aAUte4
										.get(k2)
										.getDescrUtente()
										.compareTo(uteToRemove.getDescrUtente()) > 0) {

									aAUte4.add(k2, uteToRemove);
									break;
								}
							} else {

								aAUte4.add(k2, uteToRemove);
								break;
							}
						}

						theModel.setAppDatautentiForAudit(aAUte4);

						break;
					}
				}
			}
			result.setResultCode(ELIMINAUTENTEFILTRO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::eliminaUtenteFiltro] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CERCA_OUTCOME_CODE__OK_NO_RESULTS = "Ok_No_Results";
		final String CERCA_OUTCOME_CODE__OK_SOME_RESULTS = "Ok_Some_Results";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1162565296) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			log.info("[CPBECpGestioneAuditing::cerca]   BEGIN");
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String cercaDataInizio = theModel.getAppDataricercaAudit()
					.getCercaDataInizio();
			String cercaDataFine = theModel.getAppDataricercaAudit()
					.getCercaDataFine();
			String cercaIdTransazione = theModel.getAppDataricercaAudit()
					.getIdTransazione();
			ArrayList<Audit> aAudit = new ArrayList<Audit>();
			aAudit = getGestoreMDPBackOffice().getListaAuditByFiltro(user,
					cercaDataInizio, cercaDataFine, cercaIdTransazione,
					theModel.getAppDataappForAuditFiltered(),
					theModel.getAppDataazioniForAuditFiltered(),
					theModel.getAppDatautentiForAuditFiltered());
			result.setResultCode(CERCA_OUTCOME_CODE__OK_SOME_RESULTS);
			theModel.setAppDataaudities(aAudit);
			result.setModel(theModel);
			log.info("[CPBECpGestioneAuditing::cerca]   END");
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

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE = "Ok_Iniziale";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1040702577) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			final String INCLUDE_ALL = "@@@@@@ALL@@@@@@";
			final String INCLUDE_SEL = "@@@@@@SEL@@@@@@";
			final String INCLUDE_NONE = "@@@@@@NONE@@@@@@";
			final String REMOVE_ALL = "@@@@@@REMOVE@@@@@@";
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE);
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				// INIZIALIZZA FILTRO APPLICAZIONE
				ArrayList<Applicazione> aApp = new ArrayList<Applicazione>();
				aApp = getGestoreMDPBackOffice().getListaApplicazioniByUser(
						user);
				Collections.sort(aApp, Comparators.APPLICAZIONE_COMPARATOR);

				theModel.setAppDataapplicazioni(aApp);
				ArrayList<String> newAL = new ArrayList<String>();
				theModel.setAppDatafilteredApplication(newAL);
				ArrayList<Applicazione> aAppCompleta = new ArrayList<Applicazione>();

				if (aApp.size() > 0) {
					Applicazione fakeApp = new Applicazione();
					//inserisco la prima riga della lista
					fakeApp.setIdApplicazione(INCLUDE_SEL);
					fakeApp.setNomeApplicazione("Seleziona l'applicazione da includere");
					aAppCompleta.add(fakeApp);
					aAppCompleta.addAll(aApp);
					//inserisco le ultime righe della lista
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
				theModel.setAppDataappForAudit(aAppCompleta);
				ArrayList<Applicazione> aApp2 = new ArrayList<Applicazione>();
				Applicazione fakeApp = new Applicazione();
				fakeApp.setIdApplicazione(INCLUDE_NONE);
				fakeApp.setNomeApplicazione("Nessun filtro per applicazione");
				aApp2.add(fakeApp);
				theModel.setAppDataappForAuditFiltered(aApp2);
				// INIZIALIZZA FILTRO AZIONI
				ArrayList<Azione> aAzi = new ArrayList<Azione>();
				aAzi = getGestoreMDPBackOffice().getListaAzioniByUser(user);
				//ordino le azioni
				Collections.sort(aAzi, Comparators.AZIONE_COMPARATOR);
				theModel.setAppDataazioni(aAzi);

				ArrayList<Azione> aAziCompleta = new ArrayList<Azione>();

				if (aAzi.size() > 0) {
					Azione fakeAzi = new Azione();
					fakeAzi.setIdAzione(INCLUDE_SEL);
					fakeAzi.setDescrAzione("Seleziona l'azione da includere");

					aAziCompleta.add(fakeAzi);
					aAziCompleta.addAll(aAzi);

					fakeAzi = new Azione();
					fakeAzi.setIdAzione(INCLUDE_ALL);
					fakeAzi.setDescrAzione("Includi tutte le azioni nel filtro");
					aAziCompleta.add(fakeAzi);

					fakeAzi = new Azione();
					fakeAzi.setIdAzione(REMOVE_ALL);
					fakeAzi.setDescrAzione("Rimuovi tutte le azioni dal filtro");
					aAziCompleta.add(fakeAzi);

				} else {
					Azione fakeAzi = new Azione();
					fakeAzi.setIdAzione(INCLUDE_NONE);
					fakeAzi.setDescrAzione("Nessuna azione disponibile");
					aAziCompleta.add(fakeAzi);
				}

				theModel.setAppDataazioniForAudit(aAziCompleta);
				ArrayList<Azione> aAzi2 = new ArrayList<Azione>();
				Azione azi = new Azione();
				azi.setIdAzione(INCLUDE_NONE);
				azi.setDescrAzione("Nessun filtro per azione");
				aAzi2.add(azi);
				theModel.setAppDataazioniForAuditFiltered(aAzi2);
				// INIZIALIZZA FILTRO UTENTI
				ArrayList<Utente> aUte = new ArrayList<Utente>();
				aUte = getGestoreMDPBackOffice().getListaUtentiByUser(user);
				Collections.sort(aUte, Comparators.USER_COMPARATOR);
				theModel.setAppDatautenti(aUte);

				ArrayList<Utente> aUteCompleta = new ArrayList<Utente>();
				if (aUte.size() > 0) {
					Utente fakeUte = new Utente();
					fakeUte.setIdUtente(INCLUDE_SEL);
					fakeUte.setDescrUtente("Seleziona l'utente da includere");

					aUteCompleta.add(fakeUte);
					aUteCompleta.addAll(aUte);

					fakeUte = new Utente();
					fakeUte.setIdUtente(INCLUDE_ALL);
					fakeUte.setDescrUtente("Includi tutti gli utenti nel filtro");
					aUteCompleta.add(fakeUte);

					fakeUte = new Utente();
					fakeUte.setIdUtente(REMOVE_ALL);
					fakeUte.setDescrUtente("Rimuovi tutti gli utenti dal filtro");
					aUteCompleta.add(fakeUte);

				} else {
					Utente fakeUte = new Utente();
					fakeUte.setIdUtente(INCLUDE_NONE);
					fakeUte.setDescrUtente("Nessun utente disponibile");

					aUteCompleta.add(fakeUte);
				}
				theModel.setAppDatautentiForAudit(aUteCompleta);
				ArrayList<Utente> aUte2 = new ArrayList<Utente>();
				Utente ute = new Utente();
				ute.setIdUtente(INCLUDE_NONE);
				ute.setDescrUtente("Nessun filtro per Utente");
				aUte2.add(ute);
				theModel.setAppDatautentiForAuditFiltered(aUte2);
				theModel.setAppDataisPostBack("S");
			}
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK_INIZIALE);
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

	public static void resetClearStatus_widg_tAuditItems(java.util.Map session) {
		session.put("cpGestioneAuditing_tAuditItems_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1474619123) ENABLED START*/
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
