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

/*PROTECTED REGION ID(R-2001965272) ENABLED START*/
import org.apache.struts2.ServletActionContext;
import it.csi.mdp.mdpboweb.business.mgr.GestoreMDPBackOffice;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;

/*PROTECTED REGION END*/

public class CPBECpStatisticheTranzazioni {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_LISTASTATISTICAAPPLICAZIONETRANSAZIONE_CODE = "appDatalistaStatisticaApplicazioneTransazione";

	public final static String APPDATA_STATISTICAAPPLICAZIONETRANSAZIONE_CODE = "appDatastatisticaApplicazioneTransazione";

	public final static String APPDATA_FILTRORICERCASTATISTICAAPPLICAZIONETRANSAZIONE_CODE = "appDatafiltroRicercaStatisticaApplicazioneTransazione";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	//////////////////////////////////////////////////////////////////////////////

	// nome del content panel associato al bean
	public final static String CPNAME = "cpStatisticheTranzazioni";

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

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String RICERCA_OUTCOME_CODE__OK = "OK";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R667780270) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			String dataDa = theModel
					.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
					.getDataDa();
			String dataA = theModel
					.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
					.getDataA();

			if (dataDa == null || dataA == null || dataDa.equals("")
					|| dataA.equals("")) {
				PropertiesUtil pu = new PropertiesUtil();
				result.getGlobalErrors().add(pu.getMessage("SAT_E-0001"));
				result.setResultCode(RICERCA_OUTCOME_CODE__OK);
				result.setModel(theModel);
				return result;
			}

			UserInfoExt user = theModel.getAppDatauserInfoExt();
			ArrayList<StatisticaApplicazioneTransazione> lista = getGestoreMDPBackOffice()
					.getStatisticaApplicazioneTransazione(
							user,
							theModel.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
									.getApplicationId(),
							theModel.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
									.getDataDa(),
							theModel.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
									.getDataA());

			theModel.setAppDatalistaStatisticaApplicazioneTransazione(lista);
			// impostazione del result code 
			result.setResultCode(RICERCA_OUTCOME_CODE__OK);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

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

	public ExecResults exportCsv(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String EXPORTCSV_OUTCOME_CODE__EXPORT = "EXPORT";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R244100723) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:

			//String csvText = componiCsv(theModel.getAppDatalistaStatisticaApplicazioneTransazione()); // TODO da fare

			String nomeFile = "Statistica Transazioni"
					+ theModel
							.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
							.getDataDa()
					+ theModel
							.getAppDatafiltroRicercaStatisticaApplicazioneTransazione()
							.getDataA() + ".csv";
			String contentType = "application/CSV";
			// impostazione del result code

			estrai(theModel.getAppDatalistaStatisticaApplicazioneTransazione(),
					nomeFile, contentType);

			// impostazione del result code 
			result.setResultCode(EXPORTCSV_OUTCOME_CODE__EXPORT);
			// modifica degli attributi del model (che verranno propagati allo strato
			// di presentation). si puo' agire anche direttamente sull'attributo "session".

			result.setModel(theModel);
			return result;
			/*PROTECTED REGION END*/
		} catch (Exception e) {
			log.error(
					"[BackEndFacade::exportCsv] Errore occorso nell'esecuzione del metodo:"
							+ e, e);
			throw new BEException("Errore occorso nell'esecuzione del metodo:"
					+ e, e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel theModel

	) throws BEException {
		/// definizione costanti di outcome
		final String REFRESHPANEL_OUTCOME_CODE__VIEW_INIZIALE = "VIEW_INIZIALE";
		final String REFRESHPANEL_OUTCOME_CODE__VIEW_RICERCA = "VIEW_RICERCA";
		///
		try {
			ExecResults result = new ExecResults();
			/*PROTECTED REGION ID(R-289668150) ENABLED START*/
			// inserire qui la logica applicativa da eseguire:
			UserInfoExt user = theModel.getAppDatauserInfoExt();
			// impostazione del result code
			String isPostBack = theModel.getAppDataisPostBack();
			if (isPostBack == null || isPostBack.equals("")
					|| isPostBack.equals("N")) {
				theModel.setAppDataisPostBack("S");
				// impostazione del result code 
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__VIEW_INIZIALE);
			} else {
				// impostazione del result code 
				result.setResultCode(REFRESHPANEL_OUTCOME_CODE__VIEW_RICERCA);
			}
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

	public static void resetClearStatus_widg_tbRisultati(java.util.Map session) {
		session.put("cpStatisticheTranzazioni_tbRisultati_clearStatus",
				Boolean.TRUE);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R2082299148) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	GestoreMDPBackOffice gestoreMDPBackOffice = null;

	public GestoreMDPBackOffice getGestoreMDPBackOffice() {
		return gestoreMDPBackOffice;
	}

	public void setGestoreMDPBackOffice(
			GestoreMDPBackOffice gestoreMDPBackOffice) {
		this.gestoreMDPBackOffice = gestoreMDPBackOffice;
	}

	private void estrai(ArrayList<StatisticaApplicazioneTransazione> lista,
			String nomeFile, String contentType) throws Exception, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-Disposition", String.format(
				"%s; filename=%s", "attachment", Utility
						.encodeFilenameForDownload(
								nomeFile,
								ServletActionContext.getRequest().getHeader(
										"user-agent"))));

		response.setContentType(contentType);
		ServletOutputStream outputStream = response.getOutputStream();
		generaCsV(lista, outputStream);

	}

	private void generaCsV(ArrayList<StatisticaApplicazioneTransazione> lista,
			ServletOutputStream outputStream) {
		log.info("generaCsVSTART");

		PrintStream out = new PrintStream(outputStream);
		//private static final String A_CAPO = "\r\n";
		final String SEPARATORE = "\",\"";

		out.print("Application ID" + SEPARATORE);
		out.print("NOT Initialized" + SEPARATORE);
		out.print("Initialized" + SEPARATORE);
		out.print("Configured" + SEPARATORE);
		out.print("Started" + SEPARATORE);
		out.print("Successful" + SEPARATORE);
		out.print("Unsuccessful" + SEPARATORE);
		out.print("Canceled" + SEPARATORE);
		out.print("Refunded" + SEPARATORE);
		out.print("ToBeConfirmed" + SEPARATORE);
		out.print("AttesaRT" + SEPARATORE);
		out.print("Tot");
		out.println("\"");
		out.flush();
		for (StatisticaApplicazioneTransazione sat : lista) {
			out.print(sat.getApplicationId() + SEPARATORE);
			out.print(sat.getNotInitialized() + SEPARATORE);
			out.print(sat.getInitialized() + SEPARATORE);
			out.print(sat.getConfigured() + SEPARATORE);
			out.print(sat.getStarted() + SEPARATORE);
			out.print(sat.getSuccessful() + SEPARATORE);
			out.print(sat.getUnsuccessful() + SEPARATORE);
			out.print(sat.getCanceled() + SEPARATORE);
			out.print(sat.getRefunded() + SEPARATORE);
			out.print(sat.getToBeConfirmed() + SEPARATORE);
			out.print(sat.getAttesaRt() + SEPARATORE);
			out.print(sat.getTotForAppId() + SEPARATORE);
			out.println("\"");
			//Flush the output to the file
			out.flush();
		}
		out.close();

		log.info("generaCsV STOP");
	}

	//	private void estrai(String xml, String nomeFile, String contentType)
	//			throws Exception, IOException {
	//				
	//		HttpServletResponse response = ServletActionContext.getResponse();
	//		response.setHeader("Content-Disposition", String.format(
	//				"%s; filename=%s", "attachment", Utility
	//						.encodeFilenameForDownload(
	//								nomeFile,
	//								ServletActionContext.getRequest().getHeader(
	//										"user-agent"))));
	//		response.setContentType(contentType);
	//		ServletOutputStream outputStream = response.getOutputStream();
	//		PrintStream out = new PrintStream(outputStream);
	//		out.print(xml);
	//		out.flush();
	//		out.close();
	//	}
	//
	//	private static final String A_CAPO = "\r\n";
	//	private static final String SEPARATORE = ",";
	//	private String componiCsv(
	//			ArrayList<StatisticaApplicazioneTransazione> appDatalistaStatisticaApplicazioneTransazione) {
	//
	//		StringBuffer buffer = new StringBuffer();
	//		buffer.append("Application ID" + SEPARATORE);
	//		buffer.append("NOT Initialized" + SEPARATORE);
	//		buffer.append("Initialized" + SEPARATORE);
	//		buffer.append("Configured" + SEPARATORE);
	//		buffer.append("Started" + SEPARATORE);
	//		buffer.append("Successful" + SEPARATORE);
	//		buffer.append("Unsuccessful" + SEPARATORE);
	//		buffer.append("Canceled" + SEPARATORE);
	//		buffer.append("Refunded" + SEPARATORE);
	//		buffer.append("ToBeConfirmed" + SEPARATORE);
	//		buffer.append("AttesaRT" + SEPARATORE);
	//		buffer.append("Tot");
	//		buffer.append(A_CAPO);
	//
	//		for (StatisticaApplicazioneTransazione sat : appDatalistaStatisticaApplicazioneTransazione) {
	//			buffer.append(sat.getApplicationId() + SEPARATORE);
	//			buffer.append(sat.getNotInitialized() + SEPARATORE);
	//			buffer.append(sat.getInitialized() + SEPARATORE);
	//			buffer.append(sat.getConfigured() + SEPARATORE);
	//			buffer.append(sat.getStarted() + SEPARATORE);
	//			buffer.append(sat.getSuccessful() + SEPARATORE);
	//			buffer.append(sat.getUnsuccessful() + SEPARATORE);
	//			buffer.append(sat.getCanceled() + SEPARATORE);
	//			buffer.append(sat.getRefunded() + SEPARATORE);
	//			buffer.append(sat.getToBeConfirmed() + SEPARATORE);
	//			buffer.append(sat.getAttesaRt() + SEPARATORE);
	//			buffer.append("Tot");
	//			buffer.append(A_CAPO);
	//		}
	//		return buffer.toString();
	//	}
	/*PROTECTED REGION END*/
}
