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

/*PROTECTED REGION ID(R1863114502) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

import org.apache.struts2.ServletActionContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*PROTECTED REGION END*/

public class CPBECpGestioneRT {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_LISTART_CODE = "appDatalistaRT";

	public final static String APPDATA_RICERCART_CODE = "appDataricercaRT";

	public final static String APPDATA_SELETTOREIDRT_CODE = "appDataselettoreIdRT";

	public final static String APPDATA_CODICIESITOPAGAMENTO_CODE = "appDatacodiciEsitoPagamento";

	public final static String APPDATA_LISTACODICIESITOPAGAMENTO_CODE = "appDatalistaCodiciEsitoPagamento";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneRT";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricercaRT(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String RICERCART_OUTCOME_CODE__NO_RESULT = "NO_RESULT";
		final String RICERCART_OUTCOME_CODE__RESULT = "RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1714246922) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// setto la lista esitiPagamento
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			log.info("theModel.getAppDataricercaRT().getIdEsitoPagamento() "
					+ theModel.getAppDataricercaRT().getIdEsitoPagamento());
			String idEsitoPagamento = theModel.getAppDataricercaRT()
					.getIdEsitoPagamento();

			ArrayList<RicevuteTelematicheExt> listaRte = getGestoreMDPNODONAZIONALEBackOffice()
					.getRTByParam(
							user,
							null,// theModel.getAppDataricercaRT().getId(),
							theModel.getAppDataricercaRT().getApplicationId(),
							theModel.getAppDataricercaRT().getTransactionId(),
							theModel.getAppDataricercaRT().getLastUpdateDa(),
							theModel.getAppDataricercaRT().getLastUpdateA(),
							theModel.getAppDataricercaRT().getInsertDateDa(),
							theModel.getAppDataricercaRT().getInsertDateA(),
							theModel.getAppDataricercaRT().getIuv(),
							idEsitoPagamento,
							theModel.getAppDataricercaRT().getIdMsgRichiesta());
			ArrayList<RicevuteTelematiche> listaRT = new ArrayList<RicevuteTelematiche>();

			listaRT = estraiListaRt(listaRte);

			theModel.setAppDatalistaRT(listaRT);
			log.info("lunghezza lista " + listaRT.size());

			// impostazione del result code
			if (listaRT.size() < 1) {
				result.setResultCode(RICERCART_OUTCOME_CODE__NO_RESULT);
			} else {
				result.setResultCode(RICERCART_OUTCOME_CODE__RESULT);
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
					"[BackEndFacade::ricercaRT] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRtXml(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ESPORTARTXML_OUTCOME_CODE__ESPORTA = "ESPORTA";
		final String ESPORTARTXML_OUTCOME_CODE__ESPORTA_KO = "ESPORTA_KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-821594158) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String id = theModel.getAppDataselettoreIdRT();
			if (id == null || id.trim().equalsIgnoreCase("")) {
				result.getGlobalErrors().add(pu.getMessage("RT-E-0001"));
				// impostazione del result code
				result.setResultCode(ESPORTARTXML_OUTCOME_CODE__ESPORTA_KO);
			} else {
				ArrayList<RicevuteTelematicheExt> listaRte = getGestoreMDPNODONAZIONALEBackOffice()
						.getRTByParam(user, Integer.parseInt(id), null, null,
								null, null, null, null, null, null, null);

				byte[] xml = listaRte.get(0).getRtDataBin();
				String nomeFile = "RT_"
						+ listaRte.get(0).getRicevuteTelematiche().getIuv()
						+ ".xml";

				String risDownloadFile = StringUtil.estraiXml(xml, nomeFile,
						"text/xml");
				log.info("[CPBECpGestioneRT::esportaRtXml] risDownloadFile --> "
						+ risDownloadFile);

				// impostazione del result code
				result.setResultCode(ESPORTARTXML_OUTCOME_CODE__ESPORTA);
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
					"[BackEndFacade::esportaRtXml] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRPTassociata(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ESPORTARPTASSOCIATA_OUTCOME_CODE__OK = "OK";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1193681069) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String id = theModel.getAppDataselettoreIdRT();

			log.info("[CPBECpGestioneRT::esportaRPTassociata] id dal selettore "
					+ id);

			if (id == null || id.trim().equalsIgnoreCase("")) {
				result.getGlobalErrors().add(pu.getMessage("RT-E-0001"));
				result.setResultCode(ESPORTARPTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			ArrayList<RicevuteTelematicheExt> listaRte = getGestoreMDPNODONAZIONALEBackOffice()
					.getRTByParam(user, Integer.parseInt(id), null, null, null,
							null, null, null, null, null, null);

			RicevuteTelematiche rt = estraiListaRt(listaRte).get(0);

			String iuv = rt.getIuv();

			int nlistaRPT = 0;

			ArrayList<RichiestaPagamentoTelematico> listaRPT = new ArrayList<RichiestaPagamentoTelematico>();

			if (iuv == null) {
				String idRichiesta = rt.getIdMsgRichiesta();
				log.warn("[CPBECpGestioneRT::esportaRPTassociata] iuv non presente VERIFICARE DATI SU DB provo ad effettuare la ricerca per id richiesta = "
						+ idRichiesta);
				listaRPT = getGestoreMDPNODONAZIONALEBackOffice()
						.getRPTByParam(user, null, null, null, null, null,
								null, null, null, null, idRichiesta);
			} else {
				listaRPT = getGestoreMDPNODONAZIONALEBackOffice()
						.getRPTByParam(user, null, null, null, null, null,
								null, null, iuv, null, null);
			}

			if (listaRPT != null) {
				nlistaRPT = listaRPT.size();
			}

			if (nlistaRPT == 0) {
				result.getGlobalErrors().add(pu.getMessage("RT-E-0002"));
				// impostazione del result code
				result.setResultCode(ESPORTARPTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}
			if (nlistaRPT == 1) {
				String xml = listaRPT.get(0).getRptXml();
				String nomeFile = "RPT_" + listaRPT.get(0).getIuv() + ".xml";
				log.error("[CPBECpGestioneRT::esportaRPTassociata] nome file "
						+ nomeFile);

				nomeFile = "RPT_" + iuv + ".xml";
				log.error("[CPBECpGestioneRT::esportaRPTassociata] nome file "
						+ nomeFile);

				String contentType = "text/xml";
				StringUtil.estraiXml(xml, nomeFile, contentType);
				result.setResultCode(ESPORTARPTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			if (nlistaRPT > 1) {
				result.getGlobalErrors().add(pu.getMessage("RT-E-0003"));
				result.setResultCode(ESPORTARPTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			result.setResultCode(ESPORTARPTASSOCIATA_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;

			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::esportaRPTassociata] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__RICERCA = "Ricerca";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-14836436) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");

				// setto la lista esitiPagamento
				UserInfoExt user = theModel.getAppDatauserInfoExt();
				ArrayList<CodiciEsitoPagamento> lista = getGestoreMDPNODONAZIONALEBackOffice()
						.getCodiciEsitoPagamentoAll(user);
				theModel.setAppDatalistaCodiciEsitoPagamento(lista);
				// impostazione del result code
				log.info("[CPBECpGestioneRT::refreshPanel] REFRESHPANEL_OUTCOME_CODE__INIZIALE");
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);

			} else {
				// impostazione del result code
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RICERCA);
				log.info("[CPBECpGestioneRT::refreshPanel] REFRESHPANEL_OUTCOME_CODE__RICERCA");
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

	public static void resetClearStatus_widg_tRicerca(java.util.Map session) {
		session.put("cpGestioneRT_tRicerca_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R663379182) ENABLED START*/
	// // inserire qui le property che si vogliono iniettare in questo bean (es.
	// dao, proxy di pd, ...)
	GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice = null;

	public GestoreMDPNODONAZIONALEBackOffice getGestoreMDPNODONAZIONALEBackOffice() {
		return gestoreMDPNODONAZIONALEBackOffice;
	}

	public void setGestoreMDPNODONAZIONALEBackOffice(
			GestoreMDPNODONAZIONALEBackOffice gestoreMDPNODONAZIONALEBackOffice) {
		this.gestoreMDPNODONAZIONALEBackOffice = gestoreMDPNODONAZIONALEBackOffice;
	}

	private ArrayList<RicevuteTelematiche> estraiListaRt(
			ArrayList<RicevuteTelematicheExt> listaRte) {
		ArrayList<RicevuteTelematiche> listaRT = new ArrayList<RicevuteTelematiche>();
		for (RicevuteTelematicheExt rte : listaRte) {
			listaRT.add(rte.getRicevuteTelematiche());
		}
		return listaRT;
	}

	/*PROTECTED REGION END*/
}
