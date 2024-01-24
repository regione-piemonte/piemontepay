/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business.mail;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayjob.model.RtData;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.Attached;
import it.csi.epay.epayservices.model.Mail;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.Rt;

public class InviaMailRicezioneRt {
	
	private static LogUtil log;
	
	private Properties properties;
	private JobFacade jobFacade;
	private MailFacade mailFacade;
	
	public InviaMailRicezioneRt(JobFacade jobFacade, MailFacade mailFacade) {
		log = new LogUtil(this.getClass());
		this.jobFacade = jobFacade;
		this.mailFacade = mailFacade;
		
		List<Parametro> listaParametri = this.jobFacade.elencoParametriPerGruppo("job_generaPdfRt");
		
		for (Parametro parametro : listaParametri) {
			properties.put(parametro.getCodice(), parametro.getValore());
		}
	}
	
	public void execute(RtData rtData) throws NamingException {
		final String methodName = "execute";
		log.info(methodName, "Executed  at " + new Date());
		
		Map<ParamNameXPdf, Object> param = rtData.getParam();
		Rt rt = rtData.getRt();
		
		try {
			String iuv = (String)param.get(ParamNameXPdf.C5_IUV);
			String esitoPagamento = rt.getDescEsitoPagamento();
			String ente = (String)param.get(ParamNameXPdf.C1_DESCRIZIONE_ENTE);
			String cfEnte = (String)param.get(ParamNameXPdf.C2_CF_ENTE);

			String tipoPagamento = (String)param.get(ParamNameXPdf.B2_DESCRIZIONE_TASSA);
			String idTRansazione = (String)param.get(ParamNameXPdf.E1_ID_TRANSAZIONE);
			String codiceAvviso = (String)param.get(ParamNameXPdf.C4_CODICE_AVVISO);
			String iuvTransazione = rt.getIuv();
			String ragioneSocialePSP = (String)param.get(ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE);
			String dataOra = (String)param.get(ParamNameXPdf.E3_DATA_ORA);
			String importo = ((BigDecimal)param.get(ParamNameXPdf.C3_IMPORTO)).toPlainString();


			StringBuilder sb = new StringBuilder();
			sb.append(getMessaggio("riga1"));
			sb.append(getMessaggio("riga2", iuv));
			sb.append("\n");
			sb.append(getMessaggio("esito", esitoPagamento));
			sb.append("\n");
			sb.append(getMessaggio("dati"));
			sb.append("\n");
			sb.append(getMessaggio("dati01", ente));
			sb.append(getMessaggio("dati02", cfEnte));
			sb.append("\n");
			sb.append(getMessaggio("dati03", tipoPagamento));
			sb.append("\n");
			sb.append(getMessaggio("dati04", idTRansazione));
			if (StringUtils.isNotBlank(codiceAvviso)) {
				sb.append(getMessaggio("dati05", codiceAvviso));
			}
			sb.append(getMessaggio("dati06", iuvTransazione));
			sb.append(getMessaggio("dati07", ragioneSocialePSP));
			sb.append("\n");
			sb.append(getMessaggio("dati08", dataOra));
			sb.append("\n");
			sb.append(getMessaggio("dati09", importo));
			sb.append("\n");
			sb.append(getMessaggio("saluti"));
			sb.append("\n");
			sb.append(getMessaggio("firma"));	
			
			Attached mailAttachedPdf = new Attached();
			mailAttachedPdf.setName("rt_" + rt.getIuv() + ".pdf");
			mailAttachedPdf.setType(Attached.MimeType.PDF);
			mailAttachedPdf.setData(rt.getRicevutaPdf());

			Attached mailAttachedXml = new Attached();
			mailAttachedXml.setName("rt_" + rt.getIuv() + ".xml");
			mailAttachedXml.setType(Attached.MimeType.XML);
			mailAttachedXml.setData(rt.getRtXml());

			Mail mail = new Mail();
			mail.setTo(rtData.getPagamento().getPagatore().getEmail());
			mail.setSubject(getMessaggio("oggetto",iuv));
			mail.setText(sb.toString());
			mail.getAttachedFiles().add(mailAttachedPdf);
			mail.getAttachedFiles().add(mailAttachedXml);

			mailFacade.inviaMail(mail);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private String getMessaggio(String riga, String ... parametri) {
		try {
			String row = properties.getProperty(riga);
			int i = 0;
			for (String parametro : parametri) {
				row = row.replace("$" + i++, parametro);
			}
			return row + "\n";
		} catch (Exception e) {
			throw new RuntimeException("Errore preparazione riga della mail con RT (row = " + riga + ")", e.getCause());
		}
			
	}
}
