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

/*PROTECTED REGION ID(R-1718370845) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

/*PROTECTED REGION END*/

public class CPBECpVerificaFlussi {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_FLUSSORIVERSAMENTO_CODE = "appDataflussoRiversamento";

	public final static String APPDATA_LISTAFLUSSORIVERSAMENTO_CODE = "appDatalistaFlussoRiversamento";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpVerificaFlussi";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goToFlussi(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String GOTOFLUSSI_OUTCOME_CODE__OK = "ok";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1922614011) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code 
			result.setResultCode(GOTOFLUSSI_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::goToFlussi] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "INIZIALE";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-694435281) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {

				ArrayList<FlussoRiversamento> listaFlussoDB = getGestoreMDPNODONAZIONALEBackOffice()
						.getFlussoRiversamentoByParam(user, null,//id,
								null,//identificativopsp,
								null,//identificativoflusso,
								null,//versioneoggetto,
								null,//identificativounivocoregolamento,
								null,//identificativoistitutomittente,
								null,//identificativoistitutoricevente,
								null,//numerototalepagamenti,
								null,//importototalepagamenti,
								null,//dataoraflusso,
								null,//dataregolamentoDa,
								null,//dataregolamentoA,
								null,//datainserimento,
								null,//datamodifica,
								null,//xmlflusso,
								null,//denominazionemittente,
								null//denominazionericevente
						);

				// TODO da usare 
				ArrayList<FlussoRiversamento> listaFlussoServizio = getGestoreMDPNODONAZIONALEBackOffice()
						.estraiFlussiDaServizio(user);

				ArrayList<FlussoRiversamento> listaFlussi = reportFlussiPresenti(
						listaFlussoServizio, listaFlussoDB);

				theModel.setAppDatalistaFlussoRiversamento(listaFlussi);
			}
			//Collection<FlussoRiversamento> colDB = listaFlussoDB;
			//Collection<FlussoRiversamento> colServ = listaFlussoServizio;

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

	public static void resetClearStatus_widg_tbRiepilogo(java.util.Map session) {
		session.put("cpVerificaFlussi_tbRiepilogo_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-1448422543) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice = null;

	public GestoreMDPNODONAZIONALEBackOffice getGestoreMDPNODONAZIONALEBackOffice() {
		return gestoreMDPNODONAZIONALEBackOffice;
	}

	public void setGestoreMDPNODONAZIONALEBackOffice(
			GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice) {
		this.gestoreMDPNODONAZIONALEBackOffice = gestoreMDPNODONAZIONALEBackOffice;
	}

	public ArrayList<FlussoRiversamento> reportFlussiPresenti(
			List<FlussoRiversamento> listServ, List<FlussoRiversamento> listDB) {

		ArrayList<FlussoRiversamento> listaFlussiRis = new ArrayList<FlussoRiversamento>();

		for (FlussoRiversamento elServ : listServ) {
			FlussoRiversamento fr = new FlussoRiversamento();
			elServ.setFlussoAcquisito("NO");

			boolean presentesulDB = false;
			for (FlussoRiversamento elDB : listDB) {
				if (elServ.getIdentificativoflusso().equalsIgnoreCase(
						elDB.getIdentificativoflusso())) {
					presentesulDB = true;
					elServ.setFlussoAcquisito("SI");
					break;
				}
			}

			listaFlussiRis.add(elServ);
		}

		return listaFlussiRis;
	}
	/*PROTECTED REGION END*/
}
