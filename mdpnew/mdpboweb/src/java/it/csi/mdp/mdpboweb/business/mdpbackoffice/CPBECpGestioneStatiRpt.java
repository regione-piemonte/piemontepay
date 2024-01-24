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

/*PROTECTED REGION ID(R-1075816793) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

/*PROTECTED REGION END*/

public class CPBECpGestioneStatiRpt {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LISTASINGOLOSTATORPT_CODE = "appDatalistaSingoloStatoRpt";

	public final static String APPDATA_LISTASINGOLOSTATOVERSAMENTO_CODE = "appDatalistaSingoloStatoVersamento";

	public final static String APPDATA_SELETTOREIDRPT_CODE = "appDataselettoreIdRPT";

	public final static String APPDATA_SINGOLOSTATOVERSAMENTO_CODE = "appDatasingoloStatoVersamento";

	public final static String APPDATA_STATORPTRISPOSTA_CODE = "appDatastatoRPTRisposta";

	public final static String APPDATA_SINGOLOSTATORPT_CODE = "appDatasingoloStatoRPT";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneStatiRpt";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String INDIETRO_OUTCOME_CODE__INDIETRO = "INDIETRO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1975636898) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

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

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__ = "";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1728235243) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");

				// setto la lista esitiPagamento
				UserInfoExt user = theModel.getAppDatauserInfoExt();
				String id = theModel.getAppDataselettoreIdRPT();

				ArrayList<RichiestaPagamentoTelematico> lrpt = getGestoreMDPNODONAZIONALEBackOffice()
						.getRPTByParam(user, Integer.parseInt(id), null, null,
								null, null, null, null, null, null, null);

				RichiestaPagamentoTelematico rpt = lrpt.get(0);

				//TODO da eliminare quando sara' presente in tabella
				//tring applicationId="TEST_TEST";
				//				String iuv="RF08150920003TEST00000002";
				//				String codiceContestoPagamento="n/a";

				rpt.setCodiceContestoPagamento("n/a");

				StatoRPTRisposta sRpt = getGestoreMDPNODONAZIONALEBackOffice()
						.getStatiRPT(user, rpt.getApplicationId(),
								rpt.getIuv(), rpt.getCodiceContestoPagamento());

				theModel.setAppDatastatoRPTRisposta(sRpt);
				theModel.setAppDatalistaSingoloStatoRpt(sRpt
						.getListaSingoloStatoRPT());
				theModel.setAppDatalistaSingoloStatoVersamento(sRpt
						.getListaSingoloStatoVersamento());
			}
			// impostazione del result code 
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__);
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

	public static void resetClearStatus_widg_tbListaSingoloStatoRpt(
			java.util.Map session) {
		session.put("cpGestioneStatiRpt_tbListaSingoloStatoRpt_clearStatus",
				Boolean.TRUE);
	}

	public static void resetClearStatus_widg_tbListaSingoloStatoVersamento(
			java.util.Map session) {
		session.put(
				"cpGestioneStatiRpt_tbListaSingoloStatoVersamento_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-2004423827) ENABLED START*/
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
