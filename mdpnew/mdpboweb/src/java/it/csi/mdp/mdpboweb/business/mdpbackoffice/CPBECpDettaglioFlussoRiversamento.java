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

/*PROTECTED REGION ID(R-1302968810) ENABLED START*/
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPNODONAZIONALEBackOffice;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
/*PROTECTED REGION END*/

public class CPBECpDettaglioFlussoRiversamento {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_FLUSSORIVERSAMENTO_CODE = "appDataflussoRiversamento";

	public final static String APPDATA_SELETTOREFLUSSORIVERSAMENTO_CODE = "appDataselettoreFlussoRiversamento";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpDettaglioFlussoRiversamento";

	/** 
	 * Restituisce il tab correntemente visibile in un determinato tab set
	 */
	private String getCurrentTab(Map session, String tabSetName) {
		String value = (String) session.get(CPNAME + "_" + tabSetName
				+ "_selectedMultiPanel");
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esporta(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String ESPORTA_OUTCOME_CODE__ESPORTA = "ESPORTA";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R1961134785) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			FlussoRiversamento fr = theModel.getAppDataflussoRiversamento();

			String xml = fr.getXmlflusso();
			String nomeFile = fr.getIdentificativoflusso() + ".xml";
			String contentType = "text/xml";
			// impostazione del result code
			estraiXml(xml, nomeFile, contentType);

			// impostazione del result code 
			result.setResultCode(ESPORTA_OUTCOME_CODE__ESPORTA);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::esporta] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__INIZIALE = "INIZIALE";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-120050660) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			PropertiesUtil pu = new PropertiesUtil();
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			String isPostBack = theModel.getAppDataisPostBack();
			//			if (isPostBack == null || isPostBack.equals("")|| isPostBack.equals("N")) {
			//				theModel.setAppDataisPostBack("S");

			if (theModel.getAppDataselettoreFlussoRiversamento() != null) {
				ArrayList<FlussoRiversamento> listaFlussoRiversamento = getGestoreMDPNODONAZIONALEBackOffice()
						.getFlussoRiversamentoByParam(
								user,
								Integer.parseInt(theModel
										.getAppDataselettoreFlussoRiversamento()),//id,
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

				theModel.setAppDataflussoRiversamento(listaFlussoRiversamento
						.get(0));
				log.info("lunghezza lista " + listaFlussoRiversamento.size());
			}
			//}
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

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-1618721896) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
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
