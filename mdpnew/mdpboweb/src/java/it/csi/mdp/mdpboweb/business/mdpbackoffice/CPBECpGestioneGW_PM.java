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

/*PROTECTED REGION ID(R-1175104184) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
/*PROTECTED REGION END*/

public class CPBECpGestioneGW_PM {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_SELETTOREIDPAYMENTMODE_CODE = "appDataselettoreIdPaymentMode";

	public final static String APPDATA_SELETTOREIDGATEWAY_CODE = "appDataselettoreIdGateway";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_ASSOCIAZIONEGW_MP_CODE = "appDataassociazioneGW_MP";

	public final static String APPDATA_ASSOCIAZIONIGW_MP_CODE = "appDataassociazioniGW_MP";

	public final static String APPDATA_GATEWAY_CODE = "appDatagateway";

	public final static String APPDATA_GATEWAYS_CODE = "appDatagateways";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_NUOVAASSOCIAZIONEGW_MP_CODE = "appDatanuovaAssociazioneGW_MP";

	public final static String APPDATA_PAYMENTMODE_CODE = "appDatapaymentMode";

	public final static String APPDATA_PAYMENTMODES_CODE = "appDatapaymentModes";

	public final static String APPDATA_SELETTOREITEMASSOCIAZIONEGW_MP_CODE = "appDataselettoreItemAssociazioneGW_MP";

	public final static String APPDATA_AZIONEGATEWAY_CODE = "appDataAzioneGateway";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_SELETTOREIDGATEWAY2_CODE = "appDataselettoreIdGateway2";

	public final static String APPDATA_LINGUE_CODE = "appDatalingue";

	public final static String APPDATA_LINGUECLONE_CODE = "appDatalingueClone";

	public final static String APPDATA_DIVISE_CODE = "appDatadivise";

	public final static String APPDATA_DIVISECLONE_CODE = "appDatadiviseClone";

	public final static String APPDATA_EXTRAATTRIBUTESNUOVOGATEWAY_CODE = "appDataextraAttributesNuovoGateway";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneGW_PM";

	public final static String TABSET_TSPGW_PM = "tspGW_PM";
	public final static String TAB_TSPGW_PM_PGATEWAYS = CPNAME + "_"
			+ TABSET_TSPGW_PM + "_" + "pGateways";
	public final static String TAB_TSPGW_PM_PMODALITAPAGAMENTO = CPNAME + "_"
			+ TABSET_TSPGW_PM + "_" + "pModalitaPagamento";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovoGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String NUOVOGATEWAY_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R958107408) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			Gateway gateway = new Gateway();
			theModel.setAppDatagateway(gateway);
			theModel.setAppDataAzioneGateway("NUOVO");
			// impostazione del result code
			result.setResultCode(NUOVOGATEWAY_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::nuovoGateway] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults clonaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CLONAGATEWAY_OUTCOME_CODE__OK = "Ok";
		final String CLONAGATEWAY_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1181380568) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			String idGatewaySel = theModel.getAppDataselettoreIdGateway();
			PropertiesUtil pu = new PropertiesUtil();
			if (idGatewaySel == null || idGatewaySel.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0009"));
				result.setResultCode(CLONAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			theModel.setAppDataselettoreIdGateway2(idGatewaySel);

			UserInfoExt user = theModel.getAppDatauserInfoExt();

			Gateway clone = getGestoreMDPBackOffice().getGatewayById(user,
					idGatewaySel);

			// resetto ID 
			clone.setIdGatewayxClone(clone.getIdGateway());
			clone.setIdGateway("");
			theModel.setAppDatagateway(clone);

			// impostazione del result code 
			result.setResultCode(CLONAGATEWAY_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::clonaGateway] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAGATEWAY_OUTCOME_CODE__OK = "Ok";
		final String MODIFICAGATEWAY_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1203101911) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idGatewaySel = theModel.getAppDataselettoreIdGateway();
			PropertiesUtil pu = new PropertiesUtil();
			if (idGatewaySel == null || idGatewaySel.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0004"));
				result.setResultCode(MODIFICAGATEWAY_OUTCOME_CODE__OK);
				result.setModel(theModel);
			}
			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(MODIFICAGATEWAY_OUTCOME_CODE__KO);
				return result;
			}
			Gateway gateway = new Gateway();
			gateway = getGestoreMDPBackOffice().getGatewayById(user,
					idGatewaySel);
			theModel.setAppDatagateway(gateway);
			// impostazione del result code
			result.setResultCode(MODIFICAGATEWAY_OUTCOME_CODE__OK);
			theModel.setAppDataAzioneGateway("MODIFICA");
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaGateway] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CANCELLAGATEWAY_OUTCOME_CODE__OK = "Ok";
		final String CANCELLAGATEWAY_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1395294096) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idGatewaySel = theModel.getAppDataselettoreIdGateway();
			if (idGatewaySel == null || idGatewaySel.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0006"));
				result.setResultCode(CANCELLAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(CANCELLAGATEWAY_OUTCOME_CODE__KO);
				return result;
			}
			String in = getGestoreMDPBackOffice().deleteGatewayConfiguration(
					user, idGatewaySel);
			if (!in.equals("")) {
				result.getGlobalErrors().add(in);
				result.setResultCode(CANCELLAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			//estraggo la lista dei Gateway per quell'utente AGGIORNATA
			ArrayList<Gateway> aGat = new ArrayList<Gateway>();
			aGat = getGestoreMDPBackOffice().getListaGatewayByUser(user);
			theModel.setAppDatagateways(aGat);
			// impostazione del result code 
			result.setResultCode(CANCELLAGATEWAY_OUTCOME_CODE__OK);
			result.getGlobalMessages().add(
					pu.getMessage("cancellazioneOk.message"));
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cancellaGateway] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovoPaymentMode(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String NUOVOPAYMENTMODE_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1279553451) ENABLED START*/
			PaymentMode paymentMode = new PaymentMode();
			theModel.setAppDatapaymentMode(paymentMode);
			// impostazione del result code
			result.setResultCode(NUOVOPAYMENTMODE_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::nuovoPaymentMode] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaPaymentMode(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String MODIFICAPAYMENTMODE_OUTCOME_CODE__OK = "Ok";
		final String MODIFICAPAYMENTMODE_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1930120004) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idPaymentMode = theModel.getAppDataselettoreIdPaymentMode();
			PropertiesUtil pu = new PropertiesUtil();
			if (idPaymentMode == null || idPaymentMode.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("PM-E-0002"));
				result.setResultCode(MODIFICAPAYMENTMODE_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			if (result.getGlobalErrors().size() > 0) {
				return result;
			}
			PaymentMode paymentMode = new PaymentMode();
			paymentMode = getGestoreMDPBackOffice().getPaymentModeById(user,
					idPaymentMode);
			theModel.setAppDatapaymentMode(paymentMode);
			// impostazione del result code
			result.setResultCode(MODIFICAPAYMENTMODE_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::modificaPaymentMode] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK = "Ok";
		final String REFRESHPANEL_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R826018218) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				UserInfoExt user = theModel.getAppDatauserInfoExt();
				//estraggo la lista dei Gateway per quell'utente
				ArrayList<Gateway> aGat = new ArrayList<Gateway>();
				aGat = getGestoreMDPBackOffice().getListaGatewayByUser(user);
				theModel.setAppDatagateways(aGat);
				//estraggo la lista della modalita di pagamento per quell'utente
				ArrayList<PaymentMode> aPm = new ArrayList<PaymentMode>();
				aPm = getGestoreMDPBackOffice().getListaPaymentModeByUser(user);
				theModel.setAppDatapaymentModes(aPm);
				theModel.setAppDataisPostBack("S");
			}
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

	public static void resetClearStatus_widg_tbListaGateways(
			java.util.Map session) {
		session.put("cpGestioneGW_PM_tbListaGateways_clearStatus", Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tbListaPaymentModes(
			java.util.Map session) {
		session.put("cpGestioneGW_PM_tbListaPaymentModes_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R1914436070) ENABLED START*/
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
