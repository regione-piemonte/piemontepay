/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule.jobimpl;

import it.csi.epay.epaypaweb.util.mail.MailHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.mail.Session;
import java.util.Map;
import java.util.Properties;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class JobBase {
	static private final String CLASSNAME = JobBase.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".scheduler");

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	protected void sendEmail(String templateName, Map<String, Object> templateCtx, Properties params) {
		String methodName = "sendEmail";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			String mailSender = params.getProperty("mail.sender");
			String mailRecipientsTo = params.getProperty("mail.recipients.to");
			String mailRecipientsCC = params.getProperty("mail.recipients.cc");
			String mailSubject = params.getProperty("mail.subject");

			log.debug("mailSender:" + mailSender);
			log.debug("mailRecipientsTo:" + mailRecipientsTo);
			log.debug("mailRecipientsCC:" + mailRecipientsCC);
			log.debug("mailSubject:" + mailSubject);

			//@formatter:off
			MailHelper mailHelper = new MailHelper(mailSession);
			mailHelper.setSender(mailSender)
				.setToRecipients(mailRecipientsTo)
				.setCcRecipients(mailRecipientsCC)
				.setSubject(mailSubject)
				.setTemplateName(templateName)
				.setTemplateContext(templateCtx)
				.setContentType("text/html").sendMail();
			//@formatter:on

			log.info("alert mail sent");

		} catch (Exception e) {
			log.error("cannot send mail", e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

}
