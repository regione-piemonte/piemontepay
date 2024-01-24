/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule.jobimpl;

import freemarker.template.TemplateException;
import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapper;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapperFactory;
import it.csi.epay.epaypaweb.enumeration.JobTypeEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.SchedulerException;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestioneFlussiDad;
import it.csi.epay.epaypaweb.persistence.dad.interf.InvioMailDad;
import it.csi.epay.epaypaweb.schedule.JobTypes;
import it.csi.epay.epaypaweb.util.Util;
import it.csi.epay.epaypaweb.util.mail.MailHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

@JobTypes(JobTypeEnum.MAIL_SENDER)
public class MailSenderJob implements Job {
	static private final String CLASSNAME = MailSenderJob.class.getName();
	static private final String CONTENT_TYPE_TEXT_HTML = "text/html";

	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".business");

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	@EJB
	private TemplateBusiness templateBusiness;

	@Inject
	private InvioMailDad invioMailDad;

	@Inject
	private GestioneFlussiDad gestioneFlussiDad;

	@Override
	public JobDto execute(JobDto jobDto) throws SchedulerException {
		String methodName = "execute";

		String mailSender = jobDto.getParams().getProperty("mail.sender");
		String mailSenderCC = jobDto.getParams().getProperty("mail.sender.cc");
		String mailSenderCCN = jobDto.getParams().getProperty("mail.sender.ccn");
		String tempoLatenza = jobDto.getParams().getProperty("tempo.latenza.errore.invio");

		InvioMailDto invioMail;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			invioMail = invioMailDad.findProssimoInvioMail(Integer.parseInt(tempoLatenza));
		} catch (NumberFormatException e) {
			log.error(e);
			throw new SchedulerException("Formato errato nel parametro tempo latenza", e);

		} catch (PersistenceException e) {
			log.error(e);
			throw new SchedulerException("Errore nella lettura dei dati dal DB", e);
		}
		try {
			if (invioMail != null && invioMail.getIdFlusso() != null) {
				Long idFlusso = invioMail.getIdFlusso();
				log.info("Trovato invio mail con id:" + invioMail.getId() + " e idFlusso:" + idFlusso);

				invioMail.setDataUltimoTentativo(new Date());

				FlussoCompletoDto<? extends ElementoFlussoDto> flussoCompleto;
				try {
					switch (invioMail.getTipoMailEnum()) {
					case TRASMISSIONE_NOTIFICHE_PAGAMENTO:
						flussoCompleto = gestioneFlussiDad.findFlussoNotifichePagamento(idFlusso);
						break;
					case TRASMISSIONE_AVVISI_SCADUTI:
						flussoCompleto = gestioneFlussiDad.findFlussoAvvisiScaduti(idFlusso);
						break;
					case TRASMISSIONE_FLUSSO_RENDICONTAZIONE:
						flussoCompleto = gestioneFlussiDad.findFlussoRendicontazione(idFlusso);
						break;
					default:
						flussoCompleto = null;
						break;
					}
				} catch (PersistenceException e) {
					invioMail.setEsitoUltimoTentativo(Util.formatException("Errore nella lettura delle notifiche dal DB: ", e));
					invioMailDad.updateInvioMail(invioMail);
					flussoCompleto = null;
				}

				if (flussoCompleto != null) {
					FlussoDto flusso = flussoCompleto.getFlusso();

					String subject;
					String templateName;
					switch (invioMail.getTipoMailEnum()) {
					case TRASMISSIONE_NOTIFICHE_PAGAMENTO:
						subject = "EPAY - Flusso notifiche di pagamento - " + flusso.getCodVersamento();
						templateName = "template-invio-flusso-pagamenti.ftl";
						break;
					case TRASMISSIONE_AVVISI_SCADUTI:
						subject = "EPAY - Flusso avvisi scaduti - " + flusso.getCodVersamento();
						templateName = "template-invio-flusso-avvisiscaduti.ftl";
						break;
					case TRASMISSIONE_FLUSSO_RENDICONTAZIONE:
						subject = "EPAY - Flusso di rendicontazione";
						templateName = "template-invio-flusso-rendicontazione.ftl";
						break;
					default:
						subject = null;
						templateName = null;
						break;
					}

					TemplateDto template = templateBusiness.getTemplate(flusso.getIdEnte(), flusso.getTipoFlusso().getId());

					flusso.setDataInoltro(new Date());
					if (template != null) {
						try {
							if (mailSenderCC != null && mailSenderCC.length() > 0) {
								if (invioMail.getCc() != null && invioMail.getCc().length() > 0)
									invioMail.setCc(invioMail.getCc() + "," + mailSenderCC);
								else
									invioMail.setCc(mailSenderCC);
							}
							if (mailSenderCCN != null && mailSenderCCN.length() > 0) {
								if (invioMail.getBcc() != null && invioMail.getCc().length() > 0)
									invioMail.setBcc(invioMail.getBcc() + "," + mailSenderCCN);
								else
									invioMail.setBcc(mailSenderCCN);
							}
							sendMail(flussoCompleto, subject, template, templateName, invioMail, mailSender);
							invioMailDad.deleteInvioMail(invioMail.getId());
							flusso.setStatoFlusso(StatoFlussoEnum.INOLTRATO);

						} catch (MessagingException | IOException | TemplateException e) {
							flusso.setStatoFlusso(StatoFlussoEnum.ERRORE_IN_FASE_DI_INOLTRO);
							invioMail.setErroreInvio(true);
							invioMail.setEsitoUltimoTentativo(Util.formatException(e));
							invioMailDad.updateInvioMail(invioMail);
						}
					} else {
						flusso.setStatoFlusso(StatoFlussoEnum.ERRORE_IN_FASE_DI_INOLTRO);
						invioMail.setErroreInvio(true);
						invioMail.setEsitoUltimoTentativo("Template non trovato per ente:" + flusso.getCodFiscaleEnte() + " e tipo flusso:" + flusso.getTipoFlusso().name());
						invioMailDad.updateInvioMail(invioMail);
					}

					gestioneFlussiDad.updateFlusso(flusso);
				}
			}
		} catch (PersistenceException e) {
			log.error(e);
			throw new SchedulerException("Errore nell'accesso al database", e);

		} catch (BusinessException e) {
			log.error(e);
			throw new SchedulerException("Errore nell'accesso ai dati", e);
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - START" );
		}

		return jobDto;
	}

	//@formatter:off
	@SuppressWarnings("unchecked")
	private <T extends ElementoFlussoDto> void sendMail(FlussoCompletoDto<T> flussoCompleto, String subject, TemplateDto template, String templateName, InvioMailDto invioMail, String mailSender)
	throws MessagingException, IOException, TemplateException {
		FlussoDto flusso = flussoCompleto.getFlusso();

		byte[] data;
		TemplateMapper<T> mapper = (TemplateMapper<T>) TemplateMapperFactory.getMapper(flusso.getTipoFlusso());
		TipoFormatoFileEnum tipoFormato = template.getTipoFormato();
		data = templateBusiness.generate(flussoCompleto, template, mapper, tipoFormato).getData();
		String filename = templateBusiness.buildFilename(flusso, tipoFormato);

		Map<String, Object> mailTemplateCtx = new HashMap<String, Object>();
		mailTemplateCtx.put("flusso", flusso);
		mailTemplateCtx.put("filename", filename);

		MailHelper mailHelper = new MailHelper(mailSession);
        mailHelper.setSender(mailSender)
            .setToRecipients(invioMail.getTo())
            .setCcRecipients(invioMail.getCc())
            .setBccRecipients(invioMail.getBcc())
            .setContentType(CONTENT_TYPE_TEXT_HTML)
            .setSubject(subject)
            .setTemplateName(templateName)
            .setTemplateContext(mailTemplateCtx);
        if (data != null) {
        	mailHelper.addAttachment(filename, "application/octet-stream", data);
        }

		mailHelper.sendMail();
	}
	//@formatter:on

}
