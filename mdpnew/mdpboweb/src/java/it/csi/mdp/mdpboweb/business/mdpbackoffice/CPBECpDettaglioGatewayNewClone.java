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

/*PROTECTED REGION ID(R-697001362) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;

/*PROTECTED REGION END*/

public class CPBECpDettaglioGatewayNewClone {

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

	public final static String APPDATA_EXTRAATTRIBUTES_CODE = "appDataextraAttributes";

	public final static String APPDATA_SELETTORECHIAVEATTR_CODE = "appDataselettoreChiaveAttr";

	public final static String APPDATA_NUOVOEXTRAATTRIBUTE_CODE = "appDatanuovoExtraAttribute";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioGatewayNewClone";

	public final static String TABSET_TSTGATEWAY = "tstGateway";
	public final static String TAB_TSTGATEWAY_PDATIGENERALIGATEWAY = CPNAME
			+ "_" + TABSET_TSTGATEWAY + "_" + "pDatiGeneraliGateway";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults valorizzaDettGateway(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VALORIZZADETTGATEWAY_OUTCOME_CODE__POST_SELEZIONE = "POST_SELEZIONE";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R2003131046) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String selettoreClone = theModel.getAppDataselettoreIdGateway2();
			Gateway clone = getGestoreMDPBackOffice().getGatewayById(user,
					selettoreClone);

			ArrayList<ExtraAttribute> listaEa = getGestoreMDPBackOffice()
					.getGatewayCustomFields(user, clone.getIdGateway());
			theModel.setAppDataextraAttributesNuovoGateway(listaEa);

			ArrayList<Lingua> listaLingue = getGestoreMDPBackOffice()
					.getLanguagesByGatewayId(user, clone.getIdGateway());
			theModel.setAppDatalingue(listaLingue);
			theModel.setAppDatalingueClone(listaLingue);

			ArrayList<Divisa> listaDivisa = getGestoreMDPBackOffice()
					.getMdpCurrencyByGatewayId(user, clone.getIdGateway());

			theModel.setAppDatadivise(listaDivisa);
			theModel.setAppDatadiviseClone(listaDivisa);

			clone.setIdGateway("");
			theModel.setAppDatagateway(clone);

			// impostazione del result code
			result.setResultCode(VALORIZZADETTGATEWAY_OUTCOME_CODE__POST_SELEZIONE);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::valorizzaDettGateway] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaAttributoSelezionato(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ELIMINAATTRIBUTOSELEZIONATO_OUTCOME_CODE__RESULT = "RESULT";
		final String ELIMINAATTRIBUTOSELEZIONATO_OUTCOME_CODE__NO_RESULT = "NO_RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R563124541) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			ArrayList<ExtraAttribute> listaSel = theModel
					.getAppDataextraAttributes();

			String chiave = theModel.getAppDataselettoreChiaveAttr();
			if (chiave == null || chiave.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0007"));
				if (listaSel != null && listaSel.size() > 0) {
					result.setResultCode(ELIMINAATTRIBUTOSELEZIONATO_OUTCOME_CODE__RESULT);
				} else {
					result.setResultCode(ELIMINAATTRIBUTOSELEZIONATO_OUTCOME_CODE__NO_RESULT);
				}

				result.setModel(theModel);
				return result;
			}

			listaSel.remove(chiave);
			boolean regresso = false;

			//log.info("listaSel.size() " + listaSel.size());

			for (int a = 0; a < listaSel.size(); a++) {
				ExtraAttribute sel = listaSel.get(a);
				//log.info("sel.getChiave() " + sel.getChiave());
				if (sel.getChiave().equals(chiave)) {
					listaSel.get(a).setChiave(
							String.valueOf(Integer.parseInt(listaSel.get(a)
									.getChiave()) - 1));
					listaSel.remove(a);
				}
			}

			for (int a = 0; a < listaSel.size(); a++) {
				listaSel.get(a).setChiave(String.valueOf(a + 1));
			}

			theModel.setAppDataextraAttributes(listaSel);
			if (listaSel != null && listaSel.size() > 0) {
				result.setResultCode(ELIMINAATTRIBUTOSELEZIONATO_OUTCOME_CODE__RESULT);
			} else {
				result.setResultCode(ELIMINAATTRIBUTOSELEZIONATO_OUTCOME_CODE__NO_RESULT);
			}
			// impostazione del result code 
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::eliminaAttributoSelezionato] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults aggiungiAttributo(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String AGGIUNGIATTRIBUTO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R989811688) ENABLED START*/
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
			for (int t = 0; t < listaSel.size(); t++) {
				if (listaSel.get(t).getNome().trim().toLowerCase()
						.equals(elementoSel.getNome().toLowerCase().trim())) {
					result.getGlobalErrors().add(pu.getMessage("GA-E-0010"));
					result.setResultCode(AGGIUNGIATTRIBUTO_OUTCOME_CODE__OK);
					result.setModel(theModel);
					return result;
				}
			}

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

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1408395959) ENABLED START*/
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

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAGATEWAY_OUTCOME_CODE__OK = "Ok";
		final String SALVAGATEWAY_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-2007163662) ENABLED START*/
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

			String selettoreClone = theModel.getAppDataselettoreIdGateway2();
			if (selettoreClone == null || selettoreClone.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("GA-E-0005"));
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

				// setto i custom Fields
				ArrayList<ExtraAttribute> ea = theModel
						.getAppDataextraAttributesNuovoGateway();

				for (int d = 0; d < ea.size(); d++) {
					ea.get(d).setGatewayId(idGateway);
				}
				if (ea.size() > 0) {
					getGestoreMDPBackOffice().setGatewayCustomFields(user, ea);
				}

				// log.info("caricamento lingue prima");
				// caricamento delle lingue associate
				ArrayList<Lingua> lingue = theModel.getAppDatalingueClone();
				if (lingue.size() > 0) {
					for (int t = 0; t < lingue.size(); t++) {
						Lingua linSel = lingue.get(t);
						linSel.setIdGateway(idGateway);
						getGestoreMDPBackOffice().setLanguage(user, linSel);
					}
				}

				// caricamento delle divise associate
				// log.info("caricamento divise prima  " + lingue.size());
				ArrayList<Divisa> divise = theModel.getAppDatadiviseClone();
				// log.info("caricamento divise prima  " + lingue.size());
				if (divise.size() > 0) {
					for (int t = 0; t < divise.size(); t++) {
						Divisa divSel = divise.get(t);
						divSel.setIdGateway(idGateway);
						getGestoreMDPBackOffice().setMdpCurrency(user, divSel);
					}
				}
			} catch (Exception e) {
				log.error(" [GestoreMDPBackOffice::salvaGateway]", e);
				result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
				result.setResultCode(SALVAGATEWAY_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			// riseleziono la lista totale dei gateway
			ArrayList<Gateway> aGat = new ArrayList<Gateway>();
			aGat = getGestoreMDPBackOffice().getListaGatewayByUser(user);
			theModel.setAppDatagateways(aGat);

			val.setIdGateway(idGateway);
			theModel.setAppDatagateway(val);

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

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__NUOVO = "NUOVO";
		final String REFRESHPANEL_OUTCOME_CODE__SIEXTRA = "SIEXTRA";
		final String REFRESHPANEL_OUTCOME_CODE__NOEXTRA = "NOEXTRA";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1521602364) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			Gateway clone = theModel.getAppDatagateway();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				ArrayList<ExtraAttribute> listaEa = getGestoreMDPBackOffice()
						.getGatewayCustomFields(user,
								clone.getIdGatewayxClone());

				theModel.setAppDataextraAttributesNuovoGateway(listaEa);
				theModel.setAppDataextraAttributes(listaEa);

				ArrayList<Lingua> listaLingue = getGestoreMDPBackOffice()
						.getLanguagesByGatewayId(user,
								clone.getIdGatewayxClone());

				theModel.setAppDatalingue(listaLingue);
				theModel.setAppDatalingueClone(listaLingue);

				ArrayList<Divisa> listaDivisa = getGestoreMDPBackOffice()
						.getMdpCurrencyByGatewayId(user,
								clone.getIdGatewayxClone());

				theModel.setAppDatadivise(listaDivisa);
				theModel.setAppDatadiviseClone(listaDivisa);
				theModel.setAppDataisPostBack("S");
			}

			// impostazione del result code
			ArrayList<ExtraAttribute> listaExtra = theModel
					.getAppDataextraAttributes();
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__NUOVO);
			if (listaExtra != null && listaExtra.size() > 0) {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__SIEXTRA);
			} else {
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__NOEXTRA);
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
		session.put("cpDettaglioGatewayNewClone_tbListaAttributi_clearStatus",
				Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tLingue(java.util.Map session) {
		session.put("cpDettaglioGatewayNewClone_tLingue_clearStatus",
				Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tDivise(java.util.Map session) {
		session.put("cpDettaglioGatewayNewClone_tDivise_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-657687738) ENABLED START*/
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
