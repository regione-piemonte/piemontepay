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

/*PROTECTED REGION ID(R285529291) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;

/*PROTECTED REGION END*/

public class CPBECpDettaglioGateway {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_GATEWAY_CODE = "appDatagateway";

	public final static String APPDATA_GATEWAYS_CODE = "appDatagateways";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_SELETTOREIDGATEWAY_CODE = "appDataselettoreIdGateway";

	public final static String APPDATA_AZIONEGATEWAY_CODE = "appDataAzioneGateway";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_SELETTOREITEMASSOCIAZIONEGW_MP_CODE = "appDataselettoreItemAssociazioneGW_MP";

	public final static String APPDATA_PAYMENTMODE_CODE = "appDatapaymentMode";

	public final static String APPDATA_PAYMENTMODES_CODE = "appDatapaymentModes";

	public final static String APPDATA_SELETTOREIDGATEWAY2_CODE = "appDataselettoreIdGateway2";

	public final static String APPDATA_SELETTOREIDLINGUA_CODE = "appDataselettoreIdLingua";

	public final static String APPDATA_ASSOCIAZIONEGW_MP_CODE = "appDataassociazioneGW_MP";

	public final static String APPDATA_ASSOCIAZIONIGW_MP_CODE = "appDataassociazioniGW_MP";

	public final static String APPDATA_SELETTOREIDDIVISA_CODE = "appDataselettoreIdDivisa";

	public final static String APPDATA_LINGUE_CODE = "appDatalingue";

	public final static String APPDATA_DIVISE_CODE = "appDatadivise";

	public final static String APPDATA_DIVISECLONE_CODE = "appDatadiviseClone";

	public final static String APPDATA_LINGUECLONE_CODE = "appDatalingueClone";

	public final static String APPDATA_EXTRAATTRIBUTESNUOVOGATEWAY_CODE = "appDataextraAttributesNuovoGateway";

	public final static String APPDATA_SELETTORECHIAVEATTR_CODE = "appDataselettoreChiaveAttr";

	public final static String APPDATA_NUOVOEXTRAATTRIBUTE_CODE = "appDatanuovoExtraAttribute";

	public final static String APPDATA_EXTRAATTRIBUTES_CODE = "appDataextraAttributes";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioGateway";

	public final static String TABSET_TSTGATEWAY = "tstGateway";
	public final static String TAB_TSTGATEWAY_PDATIGENERALIGATEWAY = CPNAME
			+ "_" + TABSET_TSTGATEWAY + "_" + "pDatiGeneraliGateway";
	public final static String TAB_TSTGATEWAY_PGATEWAYPAYMENTMODE = CPNAME
			+ "_" + TABSET_TSTGATEWAY + "_" + "pGatewayPaymentMode";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults aggiungiAttributo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String AGGIUNGIATTRIBUTO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R529609797) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String nome = theModel.getAppDatanuovoExtraAttribute().getNome();

			if (nome == null || nome.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0008"));
				result.setResultCode(AGGIUNGIATTRIBUTO_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			ArrayList<ExtraAttribute> listaSel = new ArrayList<ExtraAttribute>();
			if (theModel.getAppDataextraAttributes() != null) {
				listaSel = theModel.getAppDataextraAttributes();
			}

			Gateway val = theModel.getAppDatagateway();
			String idGateway = val.getIdGateway();

			ExtraAttribute elementoSel = new ExtraAttribute();
			elementoSel.setChiave("" + (listaSel.size() + 1));
			elementoSel.setDescrizione(theModel.getAppDatanuovoExtraAttribute()
					.getDescrizione());
			elementoSel.setGatewayId(idGateway);
			elementoSel.setNome(theModel.getAppDatanuovoExtraAttribute()
					.getNome());
			listaSel.add(elementoSel);
			theModel.setAppDataextraAttributes(listaSel);
			// impostazione del result code
			result.setResultCode(AGGIUNGIATTRIBUTO_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::aggiungiAttributo] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-665174598) ENABLED START*/
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

	public ExecResults salvaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAGATEWAY_OUTCOME_CODE__OK = "Ok";
		final String SALVAGATEWAY_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R516798069) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Gateway val = theModel.getAppDatagateway();

			String idGateway = val.getIdGateway();
			String descrGateway = val.getDescrGateway();
			String providerGateway = val.getProviderGateway();
			String serviceJNDIName = val.getServiceJNDIName();

			if (descrGateway == null || descrGateway.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0001"));
				result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			if (providerGateway == null || providerGateway.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0002"));
				result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			if (serviceJNDIName == null || serviceJNDIName.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0003"));
				result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__KO);
				return result;
			}

			try {
				idGateway = getGestoreMDPBackOffice().setGateway(user,
						idGateway, descrGateway, providerGateway,
						serviceJNDIName);

			} catch (Exception e) {
				log.error(" [GestoreMDPBackOffice::salvaGateway]", e);
				result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
				result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			//inserisco gli Extra Attribute
			getGestoreMDPBackOffice().setGatewayCustomFields(user,
					theModel.getAppDataextraAttributes());

			// aggiungo l'elemento nuovo alla lista
			ArrayList<Gateway> aGat = new ArrayList<Gateway>();
			//aGat = getGestoreMDPBackOffice().getListaGatewayByUser(user);
			aGat = theModel.getAppDatagateways();
			aGat.add(val);
			theModel.setAppDatagateways(aGat);
			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));
			// impostazione del result code
			result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaGateway] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults CaricaAssociazioneSelezionataGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CARICAASSOCIAZIONESELEZIONATAGW_MP_OUTCOME_CODE__OK = "Ok";
		final String CARICAASSOCIAZIONESELEZIONATAGW_MP_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1111920362) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String chiave = theModel.getAppDataselettoreItemAssociazioneGW_MP();
			PropertiesUtil pu = new PropertiesUtil();
			if (theModel.getAppDataselettoreItemAssociazioneGW_MP() == null) {
				result.getGlobalErrors().add(pu.getMessage("PM-E-0004"));
				result.setResultCode(CARICAASSOCIAZIONESELEZIONATAGW_MP_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}

			AssociazioneGW_PM associazione = getGestoreMDPBackOffice()
					.getGatewayDetailSelezionato(user, chiave);
			theModel.setAppDataassociazioneGW_MP(associazione);
			// impostazione del result code
			result.setResultCode(CARICAASSOCIAZIONESELEZIONATAGW_MP_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::CaricaAssociazioneSelezionataGW_MP] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaAssociazioneGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK = "Ok";
		final String SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-2136125839) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String gatewayId = theModel.getAppDatagateway().getIdGateway();
			String paymentmodeId = theModel.getAppDataassociazioneGW_MP()
					.getIdPayment();
			boolean enabled = theModel.getAppDataassociazioneGW_MP()
					.getAbilitazione();

			String defaultpaymenturl = theModel.getAppDataassociazioneGW_MP()
					.getDefaultPaymentUrl();
			/*
			if(defaultpaymenturl!=null && !defaultpaymenturl.equals("")){
				if(!Validator.isValidUrl(defaultpaymenturl)){
					result.getGlobalErrors().add(pu.getMessage("PM-E-"));
					result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}
			}
			 */

			String errorurl = theModel.getAppDataassociazioneGW_MP()
					.getCancelUrl();
			/*
			if(errorurl!=null && !errorurl.equals("")){
				if(!Validator.isValidUrl(errorurl)){
					result.getGlobalErrors().add(pu.getMessage("PM-E-"));
					result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}
			}
			 */

			String receipturl = theModel.getAppDataassociazioneGW_MP()
					.getReceiptUrl();
			/*
			if(receipturl!=null && !receipturl.equals("")){
				if(!Validator.isValidUrl(receipturl)){
					result.getGlobalErrors().add(pu.getMessage("PM-E-"));
					result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}
			}
			 */

			String returnurlmdp = theModel.getAppDataassociazioneGW_MP()
					.getReturnUrl();
			/*
			if(returnurlmdp!=null && !returnurlmdp.equals("")){
				if(!Validator.isValidUrl(returnurlmdp)){
					result.getGlobalErrors().add(pu.getMessage("PM-E-"));
					result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
					result.setModel(theModel);
				}
			}
			 */
			String backofficeurl = null;
			String contextpg = theModel.getAppDataassociazioneGW_MP()
					.getContextpg();
			String httpport = theModel.getAppDataassociazioneGW_MP()
					.getHttpport();
			boolean httpportNull = false;
			String mdpgatewaypage = theModel.getAppDataassociazioneGW_MP()
					.getMdpgatewaypage();
			String sendmailonresponse = null;

			boolean verificaesito = theModel.getAppDataassociazioneGW_MP()
					.getVerificaesito();

			if (httpport != null && !httpport.trim().equals("")
					&& !NumberUtil.isNumber(httpport.trim())) {
				result.getGlobalErrors().add(pu.getMessage("PM-E-0003"));
				result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (paymentmodeId == null || paymentmodeId.equals("")) {
				result.getGlobalErrors().add(pu.getMessage("PM-E-0005"));
				result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}

			if (result.getGlobalErrors().size() > 0) {
				return result;
			}
			getGestoreMDPBackOffice()
					.setGatewayDetail(user, backofficeurl, contextpg,
							defaultpaymenturl, enabled, errorurl, gatewayId,
							httpport, httpportNull, mdpgatewaypage,
							paymentmodeId, receipturl, returnurlmdp,
							sendmailonresponse, verificaesito);

			//Aggiorno  le associazioni gateway payment per quell'utente
			theModel.setAppDataassociazioniGW_MP(getGestoreMDPBackOffice()
					.getGatewayDetailById(user, gatewayId));

			//ripulisco il form
			//theModel.setAppDataassociazioneGW_MP(new AssociazioneGW_PM());

			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));
			theModel.setAppDataisPostBack("N");
			// impostazione del result code
			result.setResultCode(SALVAASSOCIAZIONEGW_MP_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".
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

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__MODIFICA = "MODIFICA";
		final String REFRESHPANEL_OUTCOME_CODE__RESULT = "RESULT";
		final String REFRESHPANEL_OUTCOME_CODE__NORESULT = "NORESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1002359367) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				UserInfoExt user = theModel.getAppDatauserInfoExt();
				Gateway val = theModel.getAppDatagateway();
				String idGateway = val.getIdGateway();
				ArrayList<Divisa> listaDivise = new ArrayList<Divisa>();
				ArrayList<Lingua> listaLingue = new ArrayList<Lingua>();
				if (idGateway != null && !idGateway.equals("")) {
					listaDivise = getGestoreMDPBackOffice()
							.getMdpCurrencyByGatewayId(user, idGateway);
					listaLingue = getGestoreMDPBackOffice()
							.getLanguagesByGatewayId(user, idGateway);

					ArrayList<ExtraAttribute> listaEa = getGestoreMDPBackOffice()
							.getGatewayCustomFields(user, idGateway);
					theModel.setAppDataextraAttributes(listaEa);
				}

				theModel.setAppDatadivise(listaDivise);
				theModel.setAppDatalingue(listaLingue);
				//estraggo le associazioni gateway payment per quell'utente
				ArrayList<AssociazioneGW_PM> aGwPm = new ArrayList<AssociazioneGW_PM>();
				aGwPm = getGestoreMDPBackOffice().getGatewayDetailById(user,
						idGateway);
				theModel.setAppDataassociazioniGW_MP(aGwPm);

				ArrayList<PaymentMode> listPM = getGestoreMDPBackOffice()
						.getListaPaymentModeByUser(user);

				Collections.sort(listPM, Comparators.PM_COMPARATOR);
				theModel.setAppDatapaymentModes(listPM);
				theModel.setAppDataisPostBack("S");
			}
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__MODIFICA);
			ArrayList<ExtraAttribute> listaExtra = theModel
					.getAppDataextraAttributes();
			if (listaExtra != null && listaExtra.size() > 0) {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RESULT);
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__NORESULT);
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

	public static void resetClearStatus_widg_tbListaAttributi(
			java.util.Map session) {
		session.put("cpDettaglioGateway_tbListaAttributi_clearStatus",
				Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tLingue(java.util.Map session) {
		session.put("cpDettaglioGateway_tLingue_clearStatus", Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tDivise(java.util.Map session) {
		session.put("cpDettaglioGateway_tDivise_clearStatus", Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tbListaAssociazioni(
			java.util.Map session) {
		session.put("cpDettaglioGateway_tbListaAssociazioni_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R775555785) ENABLED START*/
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
