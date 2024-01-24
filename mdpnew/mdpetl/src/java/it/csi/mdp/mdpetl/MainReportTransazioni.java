/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.dto.ReportTransazioni;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciMdpStatisticheDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ReportParametriDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ReportTransazioniDAO;
import it.csi.mdp.mdpetl.util.CommonCliUtil;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.Timekeeper;
import it.csi.mdp.mdpetl.util.mail.Attached;
import it.csi.mdp.mdpetl.util.mail.MailData;
import it.csi.mdp.mdpetl.util.mail.MailUtil;
import it.csi.mdp.mdpetl.util.mail.MimeType;


public class MainReportTransazioni {        
	private static LogUtil log = new LogUtil(MainReportTransazioni.class);
	
	private static Boolean flagCumulativo;
	private static Date dataRifStart;
	private static Date dataRifEnd;
	private static String nomeReport;
	
	private static String[] destinatariEmail;
	private static String excludeApplicationId;
	
	private static List<ReportTransazioni> data = new ArrayList<ReportTransazioni>();
	private static String report = "";
	
	public static void main(String[] args) {
		final String methodName = "MAIN"; 
		Timekeeper tk = new Timekeeper("main");
		log.startMethod(methodName);
		
		try {
			impostaParametri(args);
			
			elaborate();
			
			if(data.isEmpty()){
				log.info(methodName, "Fine elaborazione - dati non trovati");
				throw new Exception("dati non trovati");
			}
			
			report = generaReport();
			
			salvaSuDB();
			
			inviaMail();

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
		
		CommonCliUtil cli = new CommonCliUtil(CommonCliUtil.Report.TRANSAZIONI, args);
		if (cli.hasFlagCumulativo()) {
			log.info(methodName, "Report Transazioni cumulativo da inizio anno");
			flagCumulativo = Boolean.TRUE;
		} else {
			log.info(methodName, "Report Transazioni mensile");
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

			nomeReport = String.format("transazioni_%s%04d%02d.csv", 
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
		
		ReportParametriDAO rpDAO = new ReportParametriDAO();
		destinatariEmail = rpDAO.getDestinatariMailTransazioni();
		excludeApplicationId = rpDAO.getExcludeApplicationid();
	}

	private static void elaborate() throws Exception {
		final String methodName = "elaborate";
		log.startMethod(methodName);
		
		data = new ReportTransazioniDAO().estraidati(dataRifStart, dataRifEnd, excludeApplicationId);
		log.info(methodName, "n. righe trovate " + data.size());
		
		log.stopMethod(methodName);
	}

	private static String generaReport() {
		StringBuffer sb = new StringBuffer();
		String appName = "";
		String gateway = "";
		List<String> riga = titoli();
		int codStato = 5;
		for (ReportTransazioni d : data) {
			if (!appName.equals(d.getApplicationname()) || !gateway.equals(d.getGatewayDescription())) {
				for(;codStato <= 4; codStato++) riga.add("0");
				sb.append(stampaRiga(riga));
				appName = d.getApplicationname();
				gateway = d.getGatewayDescription();
				codStato = 1;
				riga = new ArrayList<String>();
				riga.add(d.getApplicationname());
				riga.add(d.getGatewayDescription());
			}
			while (codStato < d.getCodStato()) {
				codStato += 1;
				riga.add("0");
			} 
			riga.add(d.getConteggio().toString());
			codStato += 1;
		}
		sb.append(stampaRiga(riga));
		return sb.toString();
	}
		
	private static List<String> titoli() {
		List<String> l = new ArrayList<String>();
		l.add("Applicazione");
		l.add("Gateway");
		l.add("Successo");
		l.add("Fallito");
		l.add("Cancellata");
		l.add("Attesa RT");
		return l;
	}
	
	private static String stampaRiga(List<String> lista) {
		if (lista == null || lista.isEmpty()) {
			return "";
		}
		String[] a = new String[lista.size()];
		a = lista.toArray(a);
		String b = StringUtils.join(a,'|');
		
		return b + "\n";
	}

	private static void salvaSuDB() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		String periodoReport = formatter.format(dataRifStart) + " - " + formatter.format(dataRifEnd);
		InserisciMdpStatisticheDAO.salva("", nomeReport, periodoReport, report);
	}
	
	private static void inviaMail() {
		final String methodName = "inviaMail";
		log.startMethod(methodName);
		try {
			Attached mailAttachedCsv = new Attached();
			mailAttachedCsv.setName(nomeReport);
			mailAttachedCsv.setType(MimeType.CSV);
			mailAttachedCsv.setData(report.getBytes("UTF-8"));


			MailData mail = new MailData();
			mail.setTo(destinatariEmail);
			mail.setSubject("Statistiche MdP - " + nomeReport);
			mail.setText(nomeReport);
			mail.getAttachedFiles().add(mailAttachedCsv);

			MailUtil.inviaMail(mail);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			log.stopMethod(methodName);
		}
		
	}

}	
