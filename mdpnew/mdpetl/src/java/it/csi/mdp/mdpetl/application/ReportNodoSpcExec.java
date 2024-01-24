/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.dto.ReportNodoSpc;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciMdpStatisticheDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ReportNodoSpcDAO;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.mail.Attached;
import it.csi.mdp.mdpetl.util.mail.MailData;
import it.csi.mdp.mdpetl.util.mail.MailUtil;
import it.csi.mdp.mdpetl.util.mail.MimeType;

public class ReportNodoSpcExec implements Runnable {
	private static LogUtil log = new LogUtil(ReportNodoSpcExec.class);
	
	private String applicationId;
	private String nomeReport;
	private Date dataRifStart;
	private Date dataRifEnd;
	private String[] destinatariEmail;

	public ReportNodoSpcExec(String applicationId, String nomeReport, Date dataRifStart, Date dataRifEnd, String[] destinatariEmail) {
		  this.applicationId = applicationId;
		  this.nomeReport = nomeReport;
		  this.dataRifStart = dataRifStart;
		  this.dataRifEnd = dataRifEnd;
		  this.destinatariEmail = destinatariEmail;
	}
	
	public void run() {
		final String methodName = "run";
		
		try {
			String nomeReportConId = StringUtils.replace(nomeReport, "<applicationId>", applicationId); 
			
			List<ReportNodoSpc> data = elaborate(applicationId);
			if(data == null || data.isEmpty()){
				log.info(methodName, "dati non trovati per applicationId " + applicationId);
				return;
			}
			
			String report = generaReport(data);
			
			salvaSuDB(applicationId, nomeReportConId, report);
			
			inviaMail(nomeReportConId, report);
		} catch (Exception e) {
			log.error(methodName, ExceptionUtils.getStackTrace(e));
		}
	}


	private List<ReportNodoSpc> elaborate(final String applicationId) throws Exception {
		final String methodName = "elaborate";
		log.startMethod(methodName);
		
		List<ReportNodoSpc> data = new ReportNodoSpcDAO().estraidati(applicationId, dataRifStart, dataRifEnd);
		log.info(methodName, "n. righe trovate " + data.size());
		
		log.stopMethod(methodName);
		return data;
	}
	
	private String generaReport(List<ReportNodoSpc> data) {
		StringBuffer sb = new StringBuffer();
		sb.append(stampaRiga(titoli()));
		for (ReportNodoSpc d : data) {
			List<String> riga = new ArrayList<String>();
			riga.add(d.getIdPsp());
			riga.add(d.getIdCanale());
			riga.add(d.getRagioneSociale());
			riga.add("\"" + d.getDescrizioneServizio().replaceAll("[\n\r]", "") + "\"");
			riga.add(d.getTipoVersamento());
			riga.add("\"" +d.getCondizioniEconomicheMassime().replaceAll("[\n\r]", "")+ "\"");
			riga.add(d.getNumTransazioni().toString());
			riga.add(d.getTransato().toPlainString());
			sb.append(stampaRiga(riga));
		}
		return sb.toString();
	}
	
	private List<String> titoli() {
		List<String> l = new ArrayList<String>();
		l.add("Id psp");
		l.add("Id canale");
		l.add("Ragione sociale");
		l.add("Descrizione servizio");
		l.add("Tipo versamento");
		l.add("Condizioni economiche massime");
		l.add("Numero transazioni");
		l.add("Totale transato");
		return l;
	}
	
	private String stampaRiga(List<String> lista) {
		if (lista == null || lista.isEmpty()) {
			return "";
		}
		String[] a = new String[lista.size()];
		a = lista.toArray(a);
		String b = StringUtils.join(a,'|');
		
		return b + "\n";
	}
	
	private void salvaSuDB(final String applicationId, final String nome, final String report) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		String periodoReport = formatter.format(dataRifStart) + " - " + formatter.format(dataRifEnd);
		InserisciMdpStatisticheDAO.salva(applicationId, nome, periodoReport, report);
	}
	
	private void inviaMail(final String nome, final String report) {
		final String methodName = "inviaMail";
		log.startMethod(methodName);
		try {
			Attached mailAttachedCsv = new Attached();
			mailAttachedCsv.setName(nome + ".csv");
			mailAttachedCsv.setType(MimeType.CSV);
			mailAttachedCsv.setData(report.getBytes("UTF-8"));


			MailData mail = new MailData();
			mail.setTo(destinatariEmail);
			mail.setSubject("Statistiche MdP - " + nome);
			mail.setText(nome);
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


	 

 
 
 
