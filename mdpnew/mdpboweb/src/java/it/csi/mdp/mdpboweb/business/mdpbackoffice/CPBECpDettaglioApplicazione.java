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

/*PROTECTED REGION ID(R-1325789230) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

import java.io.File;

/*PROTECTED REGION END*/

public class CPBECpDettaglioApplicazione {

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

	public final static String APPDATA_DIVISA_CODE = "appDatadivisa";

	public final static String APPDATA_DIVISE_CODE = "appDatadivise";

	public final static String APPDATA_SELETTOREIDTIPOLOGIACOMMISSIONE_CODE = "appDataselettoreIdTipologiaCommissione";

	public final static String APPDATA_TIPOLOGIACOMMISSIONI_CODE = "appDatatipologiaCommissioni";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_ENTI_CODE = "appDataenti";

	public final static String APPDATA_LISTAENTI_CODE = "appDatalistaEnti";

	public final static String APPDATA_SELETTOREIDENTE_CODE = "appDataselettoreIdEnte";

	public final static String APPDATA_IBANENTEAPPLICATION_CODE = "appDataibanEnteApplication";

	public final static String APPDATA_LISTAIBANENTEAPPLICATION_CODE = "appDatalistaIbanEnteApplication";

	public final static String APPDATA_SELETTOREIBANENTEAPPLICATION_CODE = "appDataselettoreIbanEnteApplication";

	public final static String APPDATA_LISTATIPOVERSAMENTO_CODE = "appDatalistaTipoversamento";

	public final static String APPDATA_LISTAINFORMATIVEPSP_CODE = "appDatalistaInformativePsp";

	public final static String APPDATA_LISTAATTIVAZIONE_CODE = "appDatalistaAttivazione";

	public final static String APPDATA_KEYVALUE_CODE = "appDatakeyValue";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioApplicazione";

	public final static String TABSET_TSPAPPLICAZIONE = "tspApplicazione";
	public final static String TAB_TSPAPPLICAZIONE_PDATIGENERALI = CPNAME + "_"
			+ TABSET_TSPAPPLICAZIONE + "_" + "pDatiGenerali";
	public final static String TAB_TSPAPPLICAZIONE_PGATEWAYPAYMENTMODE = CPNAME
			+ "_" + TABSET_TSPAPPLICAZIONE + "_" + "pGatewayPaymentMode";
	public final static String TAB_TSPAPPLICAZIONE_PASSOCIAZIONEENTI = CPNAME
			+ "_" + TABSET_TSPAPPLICAZIONE + "_" + "pAssociazioneEnti";
	public final static String TAB_TSPAPPLICAZIONE_PIBANENTEAPPLICATION = CPNAME
			+ "_" + TABSET_TSPAPPLICAZIONE + "_" + "pIbanEnteApplication";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R121953491) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code
			result.setResultCode(INDIETRO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

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

	public ExecResults salvaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAAPPLICAZIONE_OUTCOME_CODE__OK = "Ok";
		final String SALVAAPPLICAZIONE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1601989451) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			log.info("[CPBECpDettaglioApplicazione::salvaApplicazione] BEGIN");
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			Applicazione app = theModel.getAppDataapplicazione();
			String email = app.getEmailEsercente();
			// inizio con i controlli
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
				return result;
			}

			String in = getGestoreMDPBackOffice().setApplicazione(user, app,
					true);
			if (!in.equals("")) {
				result.getGlobalErrors().add(in);
				result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));

			// impostazione del result code
			result.setResultCode(SALVAAPPLICAZIONE_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			log.info("[CPBECpDettaglioApplicazione::salvaApplicazione] END");
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

	public ExecResults modificaAssociazioneGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1919573329) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String chiave = theModel.getAppDataselettoreItemAssociazioneGW_MP();
			String idApplicazione = theModel.getAppDataapplicazione()
					.getIdApplicazione();

			ArrayList<AssociazioneGW_PM> associazioneGW_PM = theModel
					.getAppDataassociazioniGW_MP();
			AssociazioneGW_PM associazioneGW_PMSel = new AssociazioneGW_PM();

			for (int k = 0; k < associazioneGW_PM.size(); k++) {
				AssociazioneGW_PM ea = associazioneGW_PM.get(k);
				if (ea.getChiave().equals(chiave)) {
					associazioneGW_PMSel = ea;
					break;
				}
			}

			theModel.setAppDataselettoreIdGateway(associazioneGW_PMSel
					.getIdGateway());

			ArrayList<PaymentMode> aPM = new ArrayList<PaymentMode>();
			aPM = getGestoreMDPBackOffice().getPaymentModeByIdGateway(user,
					associazioneGW_PMSel.getIdGateway());

			Collections.sort(aPM, Comparators.PM_COMPARATOR);
			theModel.setAppDatapaymentModes(aPM);

			theModel.setAppDataselettoreIdPaymentMode(associazioneGW_PMSel
					.getIdPayment());

			theModel.getAppDataassociazioneGW_MP().setAbilitazione(
					associazioneGW_PMSel.getAbilitazione());

			theModel.getAppDataassociazioneGW_MP().setPasswordEsercente(
					associazioneGW_PMSel.getPasswordEsercente());

			theModel.getAppDataassociazioneGW_MP().setIdEsercente(
					associazioneGW_PMSel.getIdEsercente());

			theModel.getAppDataassociazioneGW_MP().setMACAvvio(
					associazioneGW_PMSel.getMACAvvio());

			theModel.setAppDataselettoreIdTipologiaCommissione(associazioneGW_PMSel
					.getIdTipologiaComm());

			theModel.getAppDataassociazioneGW_MP().setImpMax(
					associazioneGW_PMSel.getImpMax());

			theModel.getAppDataassociazioneGW_MP().setImpMin(
					associazioneGW_PMSel.getImpMin());

			theModel.getAppDataassociazioneGW_MP().setApplicationurlback(
					associazioneGW_PMSel.getApplicationurlback());

			theModel.getAppDataassociazioneGW_MP().setApplicationurlresponseko(
					associazioneGW_PMSel.getApplicationurlresponseko());

			theModel.getAppDataassociazioneGW_MP().setApplicationurlresponseok(
					associazioneGW_PMSel.getApplicationurlresponseok());

			theModel.getAppDataassociazioneGW_MP().setMail2Buyerko(
					associazioneGW_PMSel.getMail2Buyerko());

			theModel.getAppDataassociazioneGW_MP().setMail2Buyerok(
					associazioneGW_PMSel.getMail2Buyerok());

			theModel.getAppDataassociazioneGW_MP().setMail2Esercko(
					associazioneGW_PMSel.getMail2Esercko());

			theModel.getAppDataassociazioneGW_MP().setMail2Esercok(
					associazioneGW_PMSel.getMail2Esercok());

			theModel.getAppDataassociazioneGW_MP().setMail2Sysko(
					associazioneGW_PMSel.getMail2Sysko());

			theModel.getAppDataassociazioneGW_MP().setMail2Sysok(
					associazioneGW_PMSel.getMail2Sysok());

			theModel.getAppDataassociazioneGW_MP().setAbilitazione(
					associazioneGW_PMSel.getAbilitazione());

			theModel.getAppDataassociazioneGW_MP().setCodIstat(
					associazioneGW_PMSel.getCodIstat());

			theModel.getAppDataassociazioneGW_MP().setSogliaa(
					associazioneGW_PMSel.getSogliaa());

			String idGat = associazioneGW_PMSel.getIdGateway();

			ArrayList<ExtraAttribute> ea = new ArrayList<ExtraAttribute>();
			ea = getGestoreMDPBackOffice().getGatewayCustomFields(user, idGat,
					idApplicazione);
			theModel.setAppDataextraAttributes(ea);

			// impostazione del result code
			result.setResultCode(MODIFICAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaAssociazioneGW_MP] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults caricaComboModalitaPagamento(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CARICACOMBOMODALITAPAGAMENTO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-828784153) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			log.info("[CPBECpDettaglioApplicazione::caricaComboModalitaPagamento] BEGIN");
			String applicationid = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			String idGateway = theModel.getAppDataselettoreIdGateway();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			ArrayList<PaymentMode> aPM = new ArrayList<PaymentMode>();
			aPM = getGestoreMDPBackOffice().getPaymentModeByIdGateway(user,
					idGateway);
			theModel.setAppDatapaymentModes(aPM);
			/* inizializzo la tabella dei custom fields */
			ArrayList<ExtraAttribute> ea = new ArrayList<ExtraAttribute>();
			ea = getGestoreMDPBackOffice().getGatewayCustomFields(user,
					idGateway, applicationid);
			theModel.setAppDataextraAttributes(ea);
			// reset dei campi in pagina
			theModel.setAppDataassociazioneGW_MP(new AssociazioneGW_PM());
			theModel.setAppDatanuovoExtraAttribute(new ExtraAttribute());

			// impostazione del result code
			result.setResultCode(CARICACOMBOMODALITAPAGAMENTO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::caricaComboModalitaPagamento] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults caricaAllegato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CARICAALLEGATO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-633742755) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String applicationid = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			String idGateway = theModel.getAppDataselettoreIdGateway();

			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Applicazione app = theModel.getAppDataapplicazione();
			String filename = theModel.getWidg_txFileFileName();
			String fileType = theModel.getWidg_txFileContentType();
			File file = theModel.getWidg_txFile();

			String valoreUrl = getGestoreMDPBackOffice()
					.uploadMethodForApplicationGateway(user, file,
							app.getIdApplicazione(), filename, fileType,
							idGateway);

			// l'allegato e' settabile se e solo se negli extra attribute e'
			// presente
			// l'EA con nome uguale al nomeChive che estraggo di seguito
			String nomeChiave = new MdpboProperties()
					.getDefwsMdpboProperties("resourcePathAllegato");

			// estraggo la lista degli ExtraAttribute
			boolean settaggio = false;
			ArrayList<ExtraAttribute> listaEAForGareway = new ArrayList<ExtraAttribute>();
			listaEAForGareway = theModel.getAppDataextraAttributes();
			ExtraAttribute eaSel = new ExtraAttribute();
			if (listaEAForGareway != null) {
				for (int k = 0; k < listaEAForGareway.size(); k++) {
					ExtraAttribute ea = listaEAForGareway.get(k);
					if (ea.getNome().equals(nomeChiave)) {
						eaSel.setChiave(ea.getChiave());
						eaSel.setValore(valoreUrl);
						eaSel.setNome(ea.getNome());
						eaSel.setDescrizione(ea.getDescrizione());
						listaEAForGareway.set(k, eaSel);
						settaggio = true;
					}

				}
				theModel.setAppDataextraAttributes(listaEAForGareway);
			}
			// se ho settato l'EA setto tutti gli applicationcustom fields
			// associati a quel gateway per quella applicazione
			if (settaggio) {
				getGestoreMDPBackOffice().setApplicationCustomFields(user,
						applicationid, idGateway,
						theModel.getAppDataextraAttributes());
			} else {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0011"));
				result.setModel(theModel);
			}

			// impostazione del result code
			result.setResultCode(CARICAALLEGATO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::caricaAllegato] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults caricaAttributoSelezionato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CARICAATTRIBUTOSELEZIONATO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R614577465) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			// estraggo l'elemento selezionato
			PropertiesUtil pu = new PropertiesUtil();
			String idExtraAtt = theModel.getAppDataselettoreChiaveAttr();

			if (idExtraAtt == null || idExtraAtt.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0012"));
				result.setResultCode(CARICAATTRIBUTOSELEZIONATO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			ArrayList<ExtraAttribute> listaSel = theModel
					.getAppDataextraAttributes();
			ExtraAttribute eaSel = new ExtraAttribute();

			for (int k = 0; k < listaSel.size(); k++) {
				ExtraAttribute ea = listaSel.get(k);
				if (ea.getChiave().equals(idExtraAtt)) {
					eaSel.setChiave(ea.getChiave());
					eaSel.setValore(ea.getValore());
					eaSel.setNome(ea.getNome());
					eaSel.setDescrizione(ea.getDescrizione());
					break;
				}
			}
			theModel.setAppDatanuovoExtraAttribute(eaSel);
			// impostazione del result code
			result.setResultCode(CARICAATTRIBUTOSELEZIONATO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::caricaAttributoSelezionato] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults completaAttributo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String COMPLETAATTRIBUTO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-166754754) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			ArrayList<ExtraAttribute> listaSel = theModel
					.getAppDataextraAttributes();
			String idChiavsel = theModel.getAppDatanuovoExtraAttribute()
					.getChiave();
			PropertiesUtil pu = new PropertiesUtil();

			if (idChiavsel == null || idChiavsel.equals("")) {

				result.getGlobalErrors().add(pu.getMessage("AP-E-0013"));
				result.setResultCode(COMPLETAATTRIBUTO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			for (int k = 0; k < listaSel.size(); k++) {
				ExtraAttribute ea = listaSel.get(k);
				if (ea.getChiave().equals(idChiavsel)) {
					listaSel.get(k).setValore(
							theModel.getAppDatanuovoExtraAttribute()
									.getValore());
				}
			}
			theModel.setAppDataextraAttributes(listaSel);
			theModel.setAppDatanuovoExtraAttribute(new ExtraAttribute());
			// impostazione del result code
			result.setResultCode(COMPLETAATTRIBUTO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::completaAttributo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaAssociazioneGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK = "Ok";
		final String SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1535315402) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			// Validator val = new Validator();
			String applicationid = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			String idGateway = theModel.getAppDataselettoreIdGateway();
			String idPayment = theModel.getAppDataselettoreIdPaymentMode();
			boolean abilitazione = theModel.getAppDataassociazioneGW_MP()
					.getAbilitazione();
			String codiceistat = theModel.getAppDataassociazioneGW_MP()
					.getCodIstat();
			String idEsercente = theModel.getAppDataassociazioneGW_MP()
					.getIdEsercente();
			String PasswordEsercente = theModel.getAppDataassociazioneGW_MP()
					.getPasswordEsercente();
			String mACAvvio = theModel.getAppDataassociazioneGW_MP()
					.getMACAvvio();
			String tipologiaCommissione = theModel
					.getAppDataselettoreIdTipologiaCommissione().trim();
			String svalCommMin = theModel.getAppDataassociazioneGW_MP()
					.getImpMin();
			String svalCommMax = theModel.getAppDataassociazioneGW_MP()
					.getImpMax();
			String svalSoglia = theModel.getAppDataassociazioneGW_MP()
					.getSogliaa();
			boolean mail2Buyerko = theModel.getAppDataassociazioneGW_MP()
					.getMail2Buyerko();
			boolean mail2Buyerok = theModel.getAppDataassociazioneGW_MP()
					.getMail2Buyerok();
			boolean mail2Esercko = theModel.getAppDataassociazioneGW_MP()
					.getMail2Esercko();
			boolean mail2Esercok = theModel.getAppDataassociazioneGW_MP()
					.getMail2Esercok();
			boolean mail2Sysko = theModel.getAppDataassociazioneGW_MP()
					.getMail2Sysko();
			boolean mail2Sysok = theModel.getAppDataassociazioneGW_MP()
					.getMail2Sysok();
			String applicationurlback = theModel.getAppDataassociazioneGW_MP()
					.getApplicationurlback();
			String applicationurlresponseko = theModel
					.getAppDataassociazioneGW_MP()
					.getApplicationurlresponseko();
			String applicationurlresponseok = theModel
					.getAppDataassociazioneGW_MP()
					.getApplicationurlresponseok();

			if (tipologiaCommissione == null || tipologiaCommissione.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0003"));
				// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}

			if (idEsercente == null || idEsercente.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0004"));
				// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}

			if (idPayment == null || idPayment.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0005"));
				// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}

			if (!Validator.isValidImporto(svalCommMin)) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0006"));
				// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}
			if (!Validator.isValidImporto(svalCommMax)) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0007"));
				// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}

			if (idGateway == null || idGateway.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0008"));
				// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}

			StringUtil.convertNull(svalSoglia, "");
			StringUtil.convertNull(svalCommMin, "");
			StringUtil.convertNull(svalCommMax, "");

			if (tipologiaCommissione.trim().equals("0")) {
				if (!(svalSoglia.trim().equals("")
						&& svalCommMax.trim().equals("") && svalCommMin.trim()
						.equals(""))) {
					result.getGlobalErrors().add(pu.getMessage("AP-E-0010"));
					// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
					result.setModel(theModel);
				}

			} else if (tipologiaCommissione.equals("A")) {
				if (!(svalSoglia.trim().equals("")
						&& svalCommMax.trim().equals("") && !svalCommMin.trim()
						.equals(""))) {
					result.getGlobalErrors().add(pu.getMessage("AP-E-0010"));
					// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}

			} else if (tipologiaCommissione.equals("B")) {
				if (!(svalSoglia.trim().equals("")
						&& svalCommMax.trim().equals("") && !svalCommMin.trim()
						.equals(""))) {
					result.getGlobalErrors().add(pu.getMessage("AP-E-0010"));
					// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}

			} else if (tipologiaCommissione.equals("C")) {
				if (!(!svalSoglia.trim().equals("")
						&& !svalCommMax.trim().equals("") && !svalCommMin
						.trim().equals(""))) {
					result.getGlobalErrors().add(pu.getMessage("AP-E-0010"));
					// result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}
			}

			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
				return result;
			}

			if (!svalCommMin.trim().equals("")) {
				svalCommMin = String.valueOf(NumberUtil
						.importoToDouble(svalCommMin));
			}

			if (!svalCommMax.trim().equals("")) {
				svalCommMax = String.valueOf(NumberUtil
						.importoToDouble(svalCommMax));
			}
			if (!svalSoglia.trim().equals("")) {
				svalSoglia = String.valueOf(NumberUtil
						.importoToDouble(svalSoglia));
			}

			// setto le associazioni gateway payment
			getGestoreMDPBackOffice().setApplicationDetail(user, applicationid,
					idGateway, idPayment, abilitazione, idEsercente,
					PasswordEsercente, mACAvvio, tipologiaCommissione,
					svalCommMin, svalCommMax, svalSoglia, mail2Buyerko,
					mail2Buyerok, mail2Esercko, mail2Esercok, mail2Sysko,
					mail2Sysok, applicationurlback, applicationurlresponseko,
					applicationurlresponseok, codiceistat);

			// setto gli applicatinCustom fields
			try {
				ArrayList<ExtraAttribute> listaExtraAtt = theModel
						.getAppDataextraAttributes();

				getGestoreMDPBackOffice().setApplicationCustomFields(user,
						applicationid, idGateway, listaExtraAtt);

			} catch (Exception e) {
				log.error(
						" [CPBECpDettaglioApplicazione::salvaAssociazioneGW_MP]",
						e);
				result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
				result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			// TODO NON CANCELLARE POTREBBE SERVIRE
			// inizio reset pagina
			// resetto il form
			// theModel.setAppDataassociazioneGW_MP(new AssociazioneGW_PM());
			// resetto lista extra attribute
			// theModel.setAppDataextraAttributes(new
			// ArrayList<ExtraAttribute>());
			// theModel.setAppDataselettoreIdGateway("");
			// theModel.setAppDataselettoreIdPaymentMode("");
			// theModel.setAppDataselettoreIdTipologiaCommissione("");

			try {
				String idApplicazione = theModel.getAppDataapplicazione()
						.getIdApplicazione();
				ArrayList<AssociazioneGW_PM> associazioneGW_PM = new ArrayList<AssociazioneGW_PM>();
				associazioneGW_PM = getGestoreMDPBackOffice()
						.getApplicationConfiguration(user, idApplicazione);

				theModel.setAppDataassociazioniGW_MP(associazioneGW_PM);
			} catch (Exception e) {
				log.error(
						"[CPBECpDettaglioApplicazione::salvaAssociazioneGW_MP]",
						e);
				result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
				result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			// impostazione del result code
			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));

			result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaAssociazioneGW_MP] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults associaApplicazioneEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ASSOCIAAPPLICAZIONEENTE_OUTCOME_CODE__OK = "ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1903604755) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idApplicazione = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			String enteId = theModel.getAppDataenti().getEnteIdSelezionato();
			PropertiesUtil pu = new PropertiesUtil();

			if (enteId == null || enteId.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("AP-E-0014"));
				result.setModel(theModel);
			} else {
				getGestoreMDPNODONAZIONALEBackOffice().delRelEnteApplication(
						user, idApplicazione, null);
				getGestoreMDPNODONAZIONALEBackOffice().insRelEnteApplication(
						user, idApplicazione, enteId);
				ArrayList<Enti> enti = getGestoreMDPNODONAZIONALEBackOffice()
						.getEntiByParam(user, enteId, null, null, null);
				theModel.setAppDataenti(enti.get(0));
				// ANTO probabile intervento
				// IbanEnteApplication iea = new IbanEnteApplication();
				// iea.setApplicationId(theModel.getAppDataapplicazione().getIdApplicazione());
				// iea.setIdEnte(theModel.getAppDataenti().getEnteId());
				// theModel.setAppDataibanEnteApplication(iea);

			}

			// impostazione del result code
			result.setResultCode(ASSOCIAAPPLICAZIONEENTE_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::associaApplicazioneEnte] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults disattivaIban(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String DISATTIVAIBAN_OUTCOME_CODE__OK = "ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-969103576) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();

			String idSelettore = theModel
					.getAppDataselettoreIbanEnteApplication();

			if (idSelettore == null || idSelettore.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("IBAN-E-0005"));
				result.setModel(theModel);
				result.setResultCode(DISATTIVAIBAN_OUTCOME_CODE__OK);
				return result;
			}

			ArrayList<IbanEnteApplication> listaIban = getGestoreMDPBackOffice()
					.getIbanEnteApplicationByParam(user, idSelettore, null,
							null, null, null, null, null, null, null);
			IbanEnteApplication iea = listaIban.get(0);

			getGestoreMDPBackOffice().updateIbanEnteApplication(user,
					iea.getId(), iea.getApplicationId(), iea.getIdEnte(),
					iea.getTipoversamento(), iea.getIdentificativopsp(),
					iea.getIban(), iea.getDataInizioValidita(),
					iea.getDataFineValidita(), "DISATTIVO");

			listaIban = getGestoreMDPBackOffice()
					.getIbanEnteApplicationByParam(user, null,
							iea.getApplicationId(), null,// iea.getIdEnte(),
							null, null, null, null, null, null);

			theModel.setAppDatalistaIbanEnteApplication(listaIban);
			result.getGlobalMessages().add(

			pu.getMessage("salvataggioOk.message"));

			// impostazione del result code
			result.setResultCode(DISATTIVAIBAN_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::disattivaIban] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaIban(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAIBAN_OUTCOME_CODE__OK = "ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1672186309) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			String idSelettore = theModel
					.getAppDataselettoreIbanEnteApplication();

			if (idSelettore == null || idSelettore.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("IBAN-E-0004"));
				result.setModel(theModel);
				result.setResultCode(MODIFICAIBAN_OUTCOME_CODE__OK);
				return result;
			}

			log.debug("idSelettore  " + idSelettore);

			ArrayList<IbanEnteApplication> listaIban = getGestoreMDPBackOffice()
					.getIbanEnteApplicationByParam(user, idSelettore, null,
							null, null, null, null, null, null, null);
			IbanEnteApplication iea = listaIban.get(0);
			log.debug("listaIban size  " + listaIban.size());

			log.debug("par  " + iea.getTipoversamento());
			log.debug("par  " + iea.getIdentificativopsp());
			log.debug("par  " + iea.getIban());
			log.debug("par  " + iea.getAttivo());

			iea.setIbanOld(iea.getIban());

			String datainizioVal = iea.getDataInizioValidita().trim();
			String dataFineVal = iea.getDataFineValidita().trim();

			if (datainizioVal != null && datainizioVal.length() > 1) {
				iea.setDataInizioValidita(datainizioVal.substring(0, 10));
			}
			if (dataFineVal != null && dataFineVal.length() > 1) {
				iea.setDataFineValidita(dataFineVal.substring(0, 10));
			}
			//log.info("par  " + datainizioVal.substring(0, 10));
			//log.info("par  " + dataFineVal.substring(0, 10));

			theModel.setAppDataibanEnteApplication(iea);

			// impostazione del result code
			result.setResultCode(MODIFICAIBAN_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaIban] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaIban(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAIBAN_OUTCOME_CODE__OK = "ok";
		final String SALVAIBAN_OUTCOME_CODE__KO = "ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1653611328) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			PropertiesUtil pu = new PropertiesUtil();
			IbanEnteApplication iea = theModel.getAppDataibanEnteApplication();

			log.info("id    " + iea.getId());
			log.info("par 1 "
					+ theModel.getAppDataapplicazione().getIdApplicazione());
			log.info("par 2 " + theModel.getAppDataenti().getEnteId());
			log.info("par 3 " + iea.getTipoversamento());
			log.info("par 4 " + iea.getIdentificativopsp());
			log.info("par 5 " + iea.getIban());
			log.info("par 6 " + iea.getDataInizioValidita());
			log.info("par 7 " + iea.getDataFineValidita());
			log.info("par 8 " + iea.getAttivo());

			String id = iea.getId();
			String applicationId = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			String idente = theModel.getAppDataenti().getEnteId();
			String tipoversamento = iea.getTipoversamento();
			String identificativopsp = iea.getIdentificativopsp();
			String iban = iea.getIban();
			String ibanOld = iea.getIbanOld();
			String dataInizioValidita = theModel
					.getAppDataibanEnteApplication().getDataInizioValidita();
			String dataFineValidita = iea.getDataFineValidita();
			String attivo = iea.getAttivo();

			// Controlli
			if (idente == null || idente.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("IBAN-E-0001"));
				result.setModel(theModel);
				result.setResultCode(SALVAIBAN_OUTCOME_CODE__KO);
				return result;
			}

			if (iban == null || iban.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("IBAN-E-0002"));
				result.setModel(theModel);
				result.setResultCode(SALVAIBAN_OUTCOME_CODE__KO);
				return result;
			}

			if (dataInizioValidita == null
					|| dataInizioValidita.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("IBAN-E-0003"));
				result.setModel(theModel);
				result.setResultCode(SALVAIBAN_OUTCOME_CODE__KO);
				return result;
			}

			if (tipoversamento.equalsIgnoreCase("Tutti")
					&& identificativopsp.equalsIgnoreCase("Tutti")) {

				result.getGlobalErrors().add(pu.getMessage("IBAN-E-0006"));

				result.setModel(theModel);
				result.setResultCode(SALVAIBAN_OUTCOME_CODE__KO);
				return result;
			}

			ArrayList<IbanEnteApplication> listaIbanAttivi = getGestoreMDPBackOffice()
					.getIbanEnteApplicationByParam(user, null, applicationId,
							idente, tipoversamento, identificativopsp, null,
							null, null, "ATTIVO");

			//applicationID-identificativoPSP-tipoVersamento

			// blocco che determina eventuali sovrapposizioni date di
			// configurazioni attive
			String err = controlloDate(dataInizioValidita, dataFineValidita,
					listaIbanAttivi, iban);

			if (err != null && !err.toString().equals("")) {
				result.getGlobalErrors().add(err);
				result.setModel(theModel);
				result.setResultCode(SALVAIBAN_OUTCOME_CODE__KO);
				return result;
			}

			if (id == null || id.trim().equals("")) {

				getGestoreMDPBackOffice().insertIbanEnteApplication(user,
						applicationId, idente, tipoversamento,
						identificativopsp, iban, dataInizioValidita,
						dataFineValidita, attivo);

			} else {
				log.info("passo dall update");

				// TODO gestire cambio IBAN

				log.info("confronto i 2 iban old e new " + iban + "   old "
						+ ibanOld);
				if (iban == ibanOld) {
					getGestoreMDPBackOffice().updateIbanEnteApplication(user,
							id, applicationId, idente, tipoversamento,
							identificativopsp, iban, dataInizioValidita,
							dataFineValidita, attivo);
				} else {

					log.info("caso update con old e new diversi");

					ArrayList<IbanEnteApplication> listaIban = getGestoreMDPBackOffice()
							.getIbanEnteApplicationByParam(user, id, null,
									null, null, null, null, null, null, null);
					IbanEnteApplication ieaDaDisattivare = listaIban.get(0);

					getGestoreMDPBackOffice()
							.updateIbanEnteApplication(user,
									ieaDaDisattivare.getId(),
									ieaDaDisattivare.getApplicationId(),
									ieaDaDisattivare.getIdEnte(),
									ieaDaDisattivare.getTipoversamento(),
									ieaDaDisattivare.getIdentificativopsp(),
									ieaDaDisattivare.getIban(),
									ieaDaDisattivare.getDataInizioValidita(),
									ieaDaDisattivare.getDataFineValidita(),
									"DISATTIVO");

					log.info("disattivo il record ");

					IbanEnteApplication value = theModel
							.getAppDataibanEnteApplication();
					value.setId(null);
					theModel.setAppDataibanEnteApplication(value);
					log.info("risetto id e richiamo il metodo come se fosse insert ");
					salvaIban(theModel);

				}
			}

			log.info("id applicazione x estrazione iban  "
					+ iea.getApplicationId());
			log.info("id applicazione x estrazione iban  " + applicationId);

			ArrayList<IbanEnteApplication> listaIban = getGestoreMDPBackOffice()
					.getIbanEnteApplicationByParam(user, null, applicationId,
							null, null, null, null, null, null, null);

			theModel.setAppDatalistaIbanEnteApplication(listaIban);
			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));

			//risetto il form
			IbanEnteApplication ieaStart = new IbanEnteApplication();
			iea.setApplicationId(applicationId);
			ieaStart.setIdEnte(theModel.getAppDataenti().getEnteId());
			theModel.setAppDataibanEnteApplication(ieaStart);

			// impostazione del result code
			result.setResultCode(SALVAIBAN_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaIban] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1482541856) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String isPostBack = theModel.getAppDataisPostBack();
			String idApplicazione = theModel.getAppDataapplicazione()
					.getIdApplicazione();
			PropertiesUtil pu = new PropertiesUtil();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {

				// inizializzo il ck
				theModel.setAppDataassociazioneGW_MP(new AssociazioneGW_PM());
				theModel.getAppDataassociazioneGW_MP().setAbilitazione(false);

				// carico la tabella di associazione gateway payment
				try {
					ArrayList<AssociazioneGW_PM> associazioneGW_PM = new ArrayList<AssociazioneGW_PM>();
					associazioneGW_PM = getGestoreMDPBackOffice()
							.getApplicationConfiguration(user, idApplicazione);

					theModel.setAppDataassociazioniGW_MP(associazioneGW_PM);
				} catch (Exception e) {
					log.error("[CPBECpDettaglioApplicazione::refreshPanel]", e);
					result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);
					result.setModel(theModel);
					return result;
				}

				// carico l'array dei Gateway per la combo
				try {
					// TODO da valutare se serve
					ArrayList<Gateway> aGat = new ArrayList<Gateway>();
					Gateway primo = new Gateway();
					primo.setDescrGateway(" ");
					aGat.add(primo);

					aGat.addAll(getGestoreMDPBackOffice()
							.getListaGatewayByUser(user));
					if (aGat.size() > 0) {
						Collections.sort(aGat, Comparators.GATEWAY_COMPARATOR);
					}
					theModel.setAppDatagateways(aGat);
				} catch (Exception e) {
					log.error("[CPBECpDettaglioApplicazione::refreshPanel]", e);
					result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
					result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);
					result.setModel(theModel);
					return result;
				}

				// inizializzo l'array dei PaymentMode per la combo
				ArrayList<PaymentMode> aPM = new ArrayList<PaymentMode>();
				theModel.setAppDatapaymentModes(aPM);

				// estraggo la lista delle tipologie commissioni
				ArrayList<TipologiaCommissione> tc = new ArrayList<TipologiaCommissione>();
				tc = getGestoreMDPBackOffice().getTipologiaCommissione(user);
				theModel.setAppDatatipologiaCommissioni(tc);

				// INIZIO III sezione
				// carico la lista degli enti attivi
				ArrayList<Enti> listaEnti = getGestoreMDPNODONAZIONALEBackOffice()
						.getEntiByParam(user, null, null, null, "1");
				log.info("[CPBECpDettaglioApplicazione::refreshPanel] listaEnti "
						+ listaEnti.size());
				theModel.setAppDatalistaEnti(listaEnti);

				// carico se c'e' l'ente associato all'applicazione
				ArrayList<Enti> enti = getGestoreMDPNODONAZIONALEBackOffice()
						.getEnteByApplicationId(user, idApplicazione);
				if (enti.size() > 0) {
					theModel.setAppDataenti(enti.get(0));
				}
				// FINE III sezione

				// INIZIO IV sezione
				// estraggo la lista del tipo versamento
				ArrayList<TipoVersamento> listatv = new ArrayList<TipoVersamento>();
				listatv = getGestoreMDPNODONAZIONALEBackOffice()
						.getListaTipoversamento(user);

				ArrayList<TipoVersamento> listatvCompleto = new ArrayList<TipoVersamento>();
				TipoVersamento tv = new TipoVersamento();
				tv.setDescrizione("Tutti");
				tv.setId("Tutti");
				listatvCompleto.add(tv);

				for (TipoVersamento tipover : listatv) {
					listatvCompleto.add(tipover);
				}

				theModel.setAppDatalistaTipoversamento(listatvCompleto);

				// estraggo la lista deiPsp
				ArrayList<InformativePsp> listaPsP = new ArrayList<InformativePsp>();
				ArrayList<InformativePsp> listaPsPCompleta = new ArrayList<InformativePsp>();
				// aggiungo la prima riga 
				InformativePsp infPsp = new InformativePsp();
				infPsp.setIdentificativopsp("Tutti");
				listaPsPCompleta.add(infPsp);

				listaPsP = getGestoreMDPNODONAZIONALEBackOffice()
						.getInformativePSPByParam(user, null,// idinformativapsp,
								null,// identificativoFlusso,
								null,// identificativoPSP,
								null,// ragioneSociale,
								null,// dataPubblicazione,
								null,// dataInizioValidita,
								null,// urlInformazioniPSP,
								null,// stornoPagamento,
								null,// identificativoIntermediario,
								null,// identificativoCanale,
								null,// tipoVersamento,
								null,// modelloPagamento,
								null,// priorita,
								null,// disponibilitaServizio,
								null,// descrizioneServizio,
								null,// condizioniEconomicheMassime,
								null,// urlInformazioniCanale,
								null,// datainserimento,
								"NEW",// statoinserimento,
								null,// ordinamento,
								null// origine
						);

				for (InformativePsp psp : listaPsP) {
					listaPsPCompleta.add(psp);
				}

				theModel.setAppDatalistaInformativePsp(listaPsPCompleta);

				// lista degli stati di attivazione
				ArrayList<KeyValue> listaStatiAttivazione = new ArrayList<KeyValue>();
				KeyValue kv1 = new KeyValue();
				kv1.setKey("ATTIVO");
				kv1.setValue("ATTIVO");
				KeyValue kv2 = new KeyValue();
				kv2.setKey("DISATTIVO");
				kv2.setValue("DISATTIVO");
				listaStatiAttivazione.add(kv1);
				listaStatiAttivazione.add(kv2);
				theModel.setAppDatalistaAttivazione(listaStatiAttivazione);

				IbanEnteApplication iea = new IbanEnteApplication();

				log.info("setto idApplicazione " + idApplicazione);
				iea.setApplicationId(idApplicazione);

				if (enti.size() > 0) {
					log.info("setto l'ente " + enti.get(0).getEnteId());
					iea.setIdEnte(enti.get(0).getEnteId());
				}

				theModel.setAppDataibanEnteApplication(iea);

				ArrayList<IbanEnteApplication> listaIban = getGestoreMDPBackOffice()
						.getIbanEnteApplicationByParam(user, null,
								iea.getApplicationId(), null,// iea.getIdEnte(),
								null, null, null, null, null, null);

				theModel.setAppDatalistaIbanEnteApplication(listaIban);
				// FINE IV sezione

				// setto il PostBack
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

	public static void resetClearStatus_widg_tbListaAssociazioni(
			java.util.Map session) {
		session.put("cpDettaglioApplicazione_tbListaAssociazioni_clearStatus",
				Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tbListaAttributi(
			java.util.Map session) {
		session.put("cpDettaglioApplicazione_tbListaAttributi_clearStatus",
				Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tbIbanEnteApplicatio(
			java.util.Map session) {
		session.put("cpDettaglioApplicazione_tbIbanEnteApplicatio_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-162882020) ENABLED START*/
	// // inserire qui le property che si vogliono iniettare in questo bean (es.
	// dao, proxy di pd, ...)
	GestoreMDPBackOffice gestoreMDPBackOffice = null;
	GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice = null;

	public GestoreMDPBackOffice getGestoreMDPBackOffice() {
		return gestoreMDPBackOffice;
	}

	public void setGestoreMDPBackOffice(
			GestoreMDPBackOffice gestoreMDPBackOffice) {
		this.gestoreMDPBackOffice = gestoreMDPBackOffice;
	}

	public GestoreMDPNODONAZIONALEBackOffice getGestoreMDPNODONAZIONALEBackOffice() {
		return gestoreMDPNODONAZIONALEBackOffice;
	}

	public void setGestoreMDPNODONAZIONALEBackOffice(
			GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice) {
		this.gestoreMDPNODONAZIONALEBackOffice = gestoreMDPNODONAZIONALEBackOffice;
	}

	private String controlloDate(String dataInizioValidita,
			String dataFineValidita,
			ArrayList<IbanEnteApplication> listaIbanAttivi, String iban) {

		String err = UtilDate.controlloDate(dataInizioValidita,
				dataFineValidita);

		if (err != null && !err.trim().equals("")) {
			return err;
		}

		for (IbanEnteApplication ibanAttivo : listaIbanAttivi) {
			String dataDa2 = ibanAttivo.getDataInizioValidita();
			String dataA2 = ibanAttivo.getDataFineValidita();
			//esclude in caso di update se stesso
			if (iban != ibanAttivo.getIban()) {
				err = UtilDate.controlloSovrapposizioneDate(dataInizioValidita,
						dataFineValidita, dataDa2, dataA2);
			}

			if (err != null && !err.trim().equals("")) {
				return err;
			}
		}
		return err;
	}

	public static void main(String[] args) {
		String pippo = "01/09/2015 00:00:00";
		System.out.print(pippo.substring(0, 10));
	}
	/*PROTECTED REGION END*/
}
