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

/*PROTECTED REGION ID(R-1595409491) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;
/*PROTECTED REGION END*/

public class CPBECpFlussoRiversamento {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_FLUSSORIVERSAMENTO_CODE = "appDataflussoRiversamento";

	public final static String APPDATA_FLUSSOSINGOLOPAGAMENTO_CODE = "appDataflussoSingoloPagamento";

	public final static String APPDATA_LISTAFLUSSORIVERSAMENTO_CODE = "appDatalistaFlussoRiversamento";

	public final static String APPDATA_LISTAFLUSSOSINGOLOPAGAMENTO_CODE = "appDatalistaFlussoSingoloPagamento";

	public final static String APPDATA_RICERCAFLUSSORIVERSAMENTO_CODE = "appDataricercaFlussoRiversamento";

	public final static String APPDATA_RICERCAFLUSSOSINGOLOPAGAMENTO_CODE = "appDataricercaFlussoSingoloPagamento";

	public final static String APPDATA_SELETTOREFLUSSORIVERSAMENTO_CODE = "appDataselettoreFlussoRiversamento";

	public final static String APPDATA_SELETTOREFLUSSOSINGOLOPAGAMENTO_CODE = "appDataselettoreFlussoSingoloPagamento";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpFlussoRiversamento";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String RICERCA_OUTCOME_CODE__NO_RESULT = "no_result";
		final String RICERCA_OUTCOME_CODE__RESULT = "result";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1271716211) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			ArrayList<FlussoRiversamento> listaFlussoRiversamento = getGestoreMDPNODONAZIONALEBackOffice()
					.getFlussoRiversamentoByParam(
							user,
							null,//id,
							null,//identificativopsp,
							null,//identificativoflusso,
							null,//versioneoggetto,
							theModel.getAppDataricercaFlussoRiversamento()
									.getIdentificativounivocoregolamento(),//identificativounivocoregolamento,
							null,//identificativoistitutomittente,
							theModel.getAppDataricercaFlussoRiversamento()
									.getIdentificativoistitutoricevente(),//identificativoistitutoricevente,
							null,//numerototalepagamenti,
							null,//importototalepagamenti,
							null,//dataoraflusso,
							theModel.getAppDataricercaFlussoRiversamento()
									.getDataregolamentoDA(),//dataregolamentoDa,
							theModel.getAppDataricercaFlussoRiversamento()
									.getDataregolamentoA(),//dataregolamentoA,
							null,//datainserimento,
							null,//datamodifica,
							null,//xmlflusso,
							theModel.getAppDataricercaFlussoRiversamento()
									.getDenominazionemittente(),//denominazionemittente,
							null//denominazionericevente
					);

			theModel.setAppDatalistaFlussoRiversamento(listaFlussoRiversamento);
			log.info("lunghezza lista " + listaFlussoRiversamento.size());

			// impostazione del result code
			//if (listaFlussoRiversamento.size() < 1) {
				//result.setResultCode(RICERCA_OUTCOME_CODE__NO_RESULT);
			//} else {
			result.setResultCode(RICERCA_OUTCOME_CODE__RESULT);
			//}

			result.setModel(theModel);
			return result;

			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::ricerca] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults verificaFlussi(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VERIFICAFLUSSI_OUTCOME_CODE__OK = "ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1307499501) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			theModel.setAppDataisPostBack("N");

			// impostazione del result code 
			result.setResultCode(VERIFICAFLUSSI_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::verificaFlussi] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglio(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VAIALDETTAGLIO_OUTCOME_CODE__OK = "OK";
		final String VAIALDETTAGLIO_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1417166988) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			String id = theModel.getAppDataselettoreFlussoRiversamento();

			if (id == null || id.trim().equalsIgnoreCase("")) {
				result.getGlobalErrors().add(pu.getMessage("SEL-E-0001"));
				log.info("[vaiAlDettaglioRTI] id = " + id);
				// impostazione del result code
				result.setResultCode(VAIALDETTAGLIO_OUTCOME_CODE__KO);
			} else {
				result.setResultCode(VAIALDETTAGLIO_OUTCOME_CODE__OK);

			}
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::vaiAlDettaglio] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "INIZIALE";
		final String REFRESHPANEL_OUTCOME_CODE__RESULT = "RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R425452069) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");
				UserInfoExt user = theModel.getAppDatauserInfoExt();
				log.info("[CPBECpFlussoRiversamento::refreshPanel] REFRESHPANEL_OUTCOME_CODE__INIZIALE");
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);
			} else {
				// impostazione del result code
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RESULT);
				log.info("[CPBECpFlussoRiversamento::refreshPanel] REFRESHPANEL_OUTCOME_CODE__RESULT");
			}

			// impostazione del result code 
			result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);
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

	public static void resetClearStatus_widg_tbRicerca(java.util.Map session) {
		session.put("cpFlussoRiversamento_tbRicerca_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-2129549337) ENABLED START*/
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
