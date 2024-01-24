/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.application.ReportNodoSpcExec;
import it.csi.mdp.mdpetl.integration.util.dao.ReportNodoSpcDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ReportParametriDAO;
import it.csi.mdp.mdpetl.util.CommonCliUtil;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.Timekeeper;


public class MainReportNodoSpc {        
	private static LogUtil log = new LogUtil(MainReportNodoSpc.class);
	
	private static final int NUMERO_THREAD = 8;
	
	private static Boolean flagCumulativo;
	private static Date dataRifStart;
	private static Date dataRifEnd;
	private static String applicationId;
	private static String nomeReport;
	
	private static String[] destinatariEmail;
	private static String excludeApplicationId;
	
	public static void main(String[] args) {
		final String methodName = "MAIN"; 
		Timekeeper tk = new Timekeeper("main");
		log.startMethod(methodName);
				
		try {
			impostaParametri(args);
			
			List<String> applicationIds = cercaApplicationId();
			log.info(methodName, "numero applicationId : " + applicationIds.size());
			
			ExecutorService executorReport = Executors.newFixedThreadPool(NUMERO_THREAD);
			for (String id : applicationIds) {
				executorReport.execute(new ReportNodoSpcExec(id, nomeReport, dataRifStart, dataRifEnd, destinatariEmail));
			}
			executorReport.shutdown();
			executorReport.awaitTermination(30 * 24 * 60 * 60, TimeUnit.SECONDS); //un mese!
		} catch (Exception e) {
			log.error(methodName, ExceptionUtils.getStackTrace(e));
		} finally {
			log.info(methodName, tk.stopAndPrintTime());
		}
		log.stopMethod(methodName);
		System.exit(0);
	}

	private static void impostaParametri(String[] args) throws Exception {
		final String methodName = "impostaParametri"; 
		
		CommonCliUtil cli = new CommonCliUtil(CommonCliUtil.Report.NODO_SPC, args);
		if (cli.hasFlagCumulativo()) {
			log.info(methodName, "Report Nodo SPC cumulativo da inizio anno");
			flagCumulativo = Boolean.TRUE;
		} else {
			log.info(methodName, "Report Nodo SPC mensile");
			flagCumulativo = Boolean.FALSE;
		}
		
		{
			Calendar calendar = Calendar.getInstance();
			if (cli.hasDataRif()) {
				calendar.set(Integer.parseInt(cli.getDataRifAnno()), Integer.parseInt(cli.getDataRifMese())-1, 1);
			} else {
				calendar.setTime(new Date());
				calendar.add(Calendar.MONTH, -1);
			}
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
			dataRifEnd = calendar.getTime();

			nomeReport = String.format("nodospc_<applicationId>_%s%04d%02d.csv", 
					flagCumulativo?"cumulativo_":"",  
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH)+1);
			
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
			if (flagCumulativo) {
				calendar.set(Calendar.MONTH, Calendar.JANUARY);
			}
			dataRifStart = calendar.getTime();

			log.info(methodName, "Inizio periodo di riferimento : " + dataRifStart);
			log.info(methodName, "Fnizio periodo di riferimento : " + dataRifEnd);
		}
		
		if (cli.hasApplicationId()) {
			applicationId = cli.getApplicationId();
		}
		
		ReportParametriDAO rpDAO = new ReportParametriDAO();
		destinatariEmail = rpDAO.getDestinatariMailNodospc();
		excludeApplicationId = rpDAO.getExcludeApplicationid();
	}
	
	private static List<String> cercaApplicationId() throws Exception {
		return new ReportNodoSpcDAO().trovaApplicationId(applicationId, excludeApplicationId);
	}

}	
