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

/*PROTECTED REGION ID(R-243854554) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
/*PROTECTED REGION END*/

public class CPBECpDettaglioPaymentMode {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_PAYMENTMODES_CODE = "appDatapaymentModes";

	public final static String APPDATA_PAYMENTMODE_CODE = "appDatapaymentMode";

	public final static String APPDATA_SELETTOREIDPAYMENTMODE_CODE = "appDataselettoreIdPaymentMode";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioPaymentMode";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioPaymentModeModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1771123455) ENABLED START*/
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

	public ExecResults salvaModPagamento(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioPaymentModeModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String SALVAMODPAGAMENTO_OUTCOME_CODE__OK = "Ok";
		final String SALVAMODPAGAMENTO_OUTCOME_CODE__KO = "Ko";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1335909398) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String idPaymentMod = theModel.getAppDatapaymentMode()
					.getIdPayment();
			String descrPaymentMode = theModel.getAppDatapaymentMode()
					.getDescrPayment();
			if (descrPaymentMode == null || descrPaymentMode.trim().equals("")) {
				result.getGlobalErrors().add(pu.getMessage("PM-E-0001"));
				result.setResultCode(SALVAMODPAGAMENTO_OUTCOME_CODE__KO);
				result.setModel(theModel);
			}
			if (result.getGlobalErrors().size() > 0) {
				result.setResultCode(SALVAMODPAGAMENTO_OUTCOME_CODE__KO);
				return result;
			}

			try {
				getGestoreMDPBackOffice().setPaymentMode(user, idPaymentMod,
						descrPaymentMode);
				//ricarico la lista delle modalita pagamento
				ArrayList<PaymentMode> aPm = new ArrayList<PaymentMode>();
				aPm = getGestoreMDPBackOffice().getListaPaymentModeByUser(user);
				theModel.setAppDatapaymentModes(aPm);

			} catch (Exception e) {
				log.error(" [GestoreMDPBackOffice::salvaGateway]", e);
				result.getGlobalErrors().add(pu.getMessage("AP-E-0000"));
				result.setResultCode(SALVAMODPAGAMENTO_OUTCOME_CODE__KO);
				result.setModel(theModel);
				return result;
			}
			result.getGlobalMessages().add(
					pu.getMessage("salvataggioOk.message"));
			// impostazione del result code
			result.setResultCode(SALVAMODPAGAMENTO_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::salvaModPagamento] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioPaymentModeModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__OK = "Ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1036020468) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__OK);
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

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-127457010) ENABLED START*/
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
