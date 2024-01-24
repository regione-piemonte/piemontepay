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

/*PROTECTED REGION ID(R-350233762) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;

import org.apache.struts2.ServletActionContext;
import it.csi.mdp.mdpboweb.util.Costanti;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/*PROTECTED REGION END*/

public class CPBECpGestioneRPT {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_LISTASTATIRPT_CODE = "appDatalistaStatiRpt";

	public final static String APPDATA_RICERCARPT_CODE = "appDataricercaRPT";

	public final static String APPDATA_SELETTOREIDRPT_CODE = "appDataselettoreIdRPT";

	public final static String APPDATA_STATIRPT_CODE = "appDatastatiRpt";

	public final static String APPDATA_LISTARPT_CODE = "appDatalistaRPT";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpGestioneRPT";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cercaRTI(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String CERCARTI_OUTCOME_CODE__NO_RESULT = "NO_RESULT";
		final String CERCARTI_OUTCOME_CODE__RESULT = "RESULT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1692703076) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();

			String idStatiRpt = theModel.getAppDataricercaRPT().getIdStatiRpt();
			log.info("theModel.getAppDataricercaRPT().getIdStatiRpt() "
					+ theModel.getAppDataricercaRPT().getIdStatiRpt());

			ArrayList<RichiestaPagamentoTelematico> lista = getGestoreMDPNODONAZIONALEBackOffice()
					.getRPTByParam(
							user,
							null,// theModel.getAppDataricercaRPT().getId(),
							theModel.getAppDataricercaRPT().getApplicationId(),
							theModel.getAppDataricercaRPT().getTransactionId(),
							theModel.getAppDataricercaRPT().getLastUpdateDa(),
							theModel.getAppDataricercaRPT().getLastUpdateA(),
							theModel.getAppDataricercaRPT().getInsertDateDa(),
							theModel.getAppDataricercaRPT().getInsertDateA(),
							theModel.getAppDataricercaRPT().getIuv(),
							idStatiRpt,
							theModel.getAppDataricercaRPT().getIdMsgRichiesta());
			theModel.setAppDatalistaRPT(lista);
			log.info("lunghezza lista " + lista.size());

			String rptRicerca = "";
			if (lista.size() > Costanti.MAX_RIS) {
				rptRicerca = "La ricerca ha prodotto un numero di risultati troppo elevato  "
						+ lista.size()
						+ "maggiore di "
						+ Costanti.MAX_RIS
						+ " si prega di effettuare una ricerca con maggiori filtri di ricerca";
			}

			// impostazione del result code
			if (lista.size() < 1) {
				result.setResultCode(CERCARTI_OUTCOME_CODE__NO_RESULT);
				result.getGlobalMessages().add(
						"La ricerca non ha prodotto alcun risultato");
			}
			if (!rptRicerca.equals("")) {
				result.setResultCode(CERCARTI_OUTCOME_CODE__NO_RESULT);
				result.getGlobalMessages().add(rptRicerca);
			} else {
				result.setResultCode(CERCARTI_OUTCOME_CODE__RESULT);
			}
			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::cercaRTI] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglioRTI(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String VAIALDETTAGLIORTI_OUTCOME_CODE__OK = "OK";
		final String VAIALDETTAGLIORTI_OUTCOME_CODE__KO = "KO";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-287233834) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String id = theModel.getAppDataselettoreIdRPT();

			if (id == null || id.trim().equalsIgnoreCase("")) {
				result.getGlobalErrors().add(pu.getMessage("RPT-E-0001"));
				log.info("[BackEndFacade::vaiAlDettaglioRTI] id = " + id);
				// impostazione del result code
				result.setResultCode(VAIALDETTAGLIORTI_OUTCOME_CODE__KO);
			} else {
				result.setResultCode(VAIALDETTAGLIORTI_OUTCOME_CODE__OK);

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
					"[BackEndFacade::vaiAlDettaglioRTI] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRptXml(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ESPORTARPTXML_OUTCOME_CODE__KO = "KO";
		final String ESPORTARPTXML_OUTCOME_CODE__OK = "OK";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R639630984) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String id = theModel.getAppDataselettoreIdRPT();
			if (id == null || id.trim().equalsIgnoreCase("")) {
				result.getGlobalErrors().add(pu.getMessage("RPT-E-0001"));
				// impostazione del result code
				result.setResultCode(ESPORTARPTXML_OUTCOME_CODE__KO);
			} else {
				ArrayList<RichiestaPagamentoTelematico> rpt = getGestoreMDPNODONAZIONALEBackOffice()
						.getRPTByParam(user, Integer.parseInt(id), null, null,
								null, null, null, null, null, null, null);
				String xml = rpt.get(0).getRptXml();
				String nomeFile = "RPT_" + rpt.get(0).getIuv() + ".xml";
				String contentType = "text/xml";
				// impostazione del result code
				estraiXml(xml, nomeFile, contentType);
				result.setResultCode(ESPORTARPTXML_OUTCOME_CODE__OK);
			}
			// modifica degli attributi del model (che verranno propagati allo
			// strato
			// di presentation). si puo' agire anche direttamente sull'attributo
			// "session".

			result.setModel(theModel);
			return result; /*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::esportaRptXml] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRTAssociata(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ESPORTARTASSOCIATA_OUTCOME_CODE__OK = "OK";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1462130551) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String id = theModel.getAppDataselettoreIdRPT();

			if (id == null || id.trim().equalsIgnoreCase("")) {
				result.getGlobalErrors().add(pu.getMessage("RPT-E-0001"));
				result.setResultCode(ESPORTARTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			RichiestaPagamentoTelematico rpt = getGestoreMDPNODONAZIONALEBackOffice()
					.getRPTByParam(user, Integer.parseInt(id), null, null,
							null, null, null, null, null, null, null).get(0);

			String iuv = rpt.getIuv();
			int nRt = 0;
			ArrayList<RicevuteTelematicheExt> listaRt = new ArrayList<RicevuteTelematicheExt>();

			if (iuv == null) {
				iuv = "";
				String idRichiesta = rpt.getIdMsgRichiesta();
				log.warn("[CPBECpGestioneRPT::esportaRPTassociata] iuv non presente VERIFICARE DATI SU DB provo ad effettuare la ricerca per id richiesta = "
						+ idRichiesta);

				listaRt = getGestoreMDPNODONAZIONALEBackOffice().getRTByParam(
						user, null, null, null, null, null, null, null, null,
						null, idRichiesta);
			} else {

				listaRt = getGestoreMDPNODONAZIONALEBackOffice().getRTByParam(
						user, null, null, null, null, null, null, null, iuv,
						null, null);
			}

			if (listaRt != null) {
				nRt = listaRt.size();
			}

			if (nRt == 0) {
				result.getGlobalErrors().add(pu.getMessage("RPT-E-0002"));
				result.setResultCode(ESPORTARTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			if (nRt == 1) {
				//				String xml = listaRt.get(0).getRtData();
				//				String nomeFile = "RT_" + iuv + ".xml";
				//				String contentType = "text/xml";
				//				estraiXml(xml, nomeFile, contentType);

				log.info("[BackEndFacade::esportaRTAssociata] IUV " + iuv);

				byte[] xml = listaRt.get(0).getRtDataBin();
				log.info("[BackEndFacade::esportaRTAssociata] len xml "
						+ xml.length);

				String nomeFile = "RT_"
						+ listaRt.get(0).getRicevuteTelematiche().getIuv()
						+ ".xml";

				//StringUtil.estraiXml(xml, nomeFile);
				String risDownloadFile = StringUtil.estraiXml(xml, nomeFile,
						"text/xml");
				log.info("[CPBECpGestioneRRT::esportaRtXml] risDownloadFile --> "
						+ risDownloadFile);

				result.setResultCode(ESPORTARTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			if (nRt > 1) {
				result.getGlobalErrors().add(pu.getMessage("RPT-E-0003"));
				result.setResultCode(ESPORTARTASSOCIATA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}
			result.setResultCode(ESPORTARTASSOCIATA_OUTCOME_CODE__OK);
			result.setModel(theModel);
			return result;

			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::esportaRTAssociata] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults dettStatiRPT(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String DETTSTATIRPT_OUTCOME_CODE__DETT = "DETT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-1135553957) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			// impostazione del result code 
			result.setResultCode(DETTSTATIRPT_OUTCOME_CODE__DETT);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::dettStatiRPT] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "Iniziale";
		final String REFRESHPANEL_OUTCOME_CODE__RESULT = "Result";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R665188820) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");

				// setto la lista esitiPagamento
				UserInfoExt user = theModel.getAppDatauserInfoExt();
				ArrayList<StatiRpt> lista = getGestoreMDPNODONAZIONALEBackOffice()
						.getStatiRptAll(user);
				theModel.setAppDatalistaStatiRpt(lista);
				// impostazione del result code
				log.info("[CPBECpGestioneRRT::refreshPanel] REFRESHPANEL_OUTCOME_CODE__INIZIALE");
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__INIZIALE);

			} else {
				// impostazione del result code
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__RESULT);
				log.info("[CPBECpGestioneRRT::refreshPanel] REFRESHPANEL_OUTCOME_CODE__RESULT");
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

	public static void resetClearStatus_widg_tbRicerca(java.util.Map session) {
		session.put("cpGestioneRPT_tbRicerca_clearStatus", Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R2076377936) ENABLED START*/
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

	private void estraiXml(String xml, String nomeFile, String contentType)
			throws Exception, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-Disposition", String.format(
				"%s; filename=%s", "attachment", Utility
						.encodeFilenameForDownload(
								nomeFile,
								ServletActionContext.getRequest().getHeader(
										"user-agent"))));
		response.setContentType(contentType);
		ServletOutputStream outputStream = response.getOutputStream();
		PrintStream out = new PrintStream(outputStream);
		out.print(xml);
		out.flush();
		out.close();
	}

	/*PROTECTED REGION END*/
}
