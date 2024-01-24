/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringEscapeUtils;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import freemarker.template.Version;

public class MailHelper {
	private InternetAddress[] toRecipients;
	private InternetAddress[] ccRecipients;
	private String subject;
	private Address sender;
	private String contentType;
	private Session mailSession;
	private String templateName;
	private Map<String, Object> templateContext;
	private Configuration cfg;

	public MailHelper(Session mailSession) {
		this.mailSession = mailSession;

		cfg = new Configuration(new Version(2, 3, 20));
		cfg.setClassForTemplateLoading(MailHelper.class, "/META-INF/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.getDefault());
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setSharedVariable("escapeHtmlEntity", new FreemarkerEscapeDirective());
	}

	public MailHelper setSender(String sender) throws AddressException {
		this.sender = (sender != null) ? new InternetAddress(sender) : null;
		return this;
	}

	public MailHelper setToRecipients(String toRecipients) throws AddressException {
		this.toRecipients = (toRecipients != null) ? InternetAddress.parse(toRecipients) : null;
		return this;
	}

	public MailHelper setCcRecipients(String ccRecipients) throws AddressException {
		this.ccRecipients = (ccRecipients != null) ? InternetAddress.parse(ccRecipients) : null;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public MailHelper setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public MailHelper setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public MailHelper setTemplateName(String templateName) {
		this.templateName = templateName;
		return this;
	}

	public MailHelper setTemplateContext(Map<String, Object> templateContext) {
		this.templateContext = templateContext;
		return this;
	}

	public void sendMail() throws MessagingException, IOException, TemplateException {
		MimeMessage m = new MimeMessage(mailSession);

		if (sender == null) {
			throw new MessagingException("Sender is null");
		}
		if (toRecipients == null) {
			throw new MessagingException("To Recipient is null");
		}
		m.setFrom(sender);
		m.setRecipients(Message.RecipientType.TO, toRecipients);
		if (ccRecipients != null) {
			m.setRecipients(Message.RecipientType.CC, ccRecipients);
		}
		m.setSubject(subject);
		m.setContent(generateContent(), contentType);
		m.setSentDate(new java.util.Date());

		Transport.send(m);
	}

	private String generateContent() throws IOException, TemplateException {
		Template t = cfg.getTemplate(templateName);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Writer res = new BufferedWriter(new OutputStreamWriter(baos, "UTF-8"));
		t.process(templateContext, res);
		String content = baos.toString("UTF-8");
		baos.close();
		res.close();

		return content;
	}

	private class FreemarkerEscapeDirective implements TemplateDirectiveModel {

		@Override
		@SuppressWarnings("rawtypes")
		public void execute(Environment env, Map map, TemplateModel[] atemplatemodel, TemplateDirectiveBody body) throws TemplateException, IOException {
			if (body != null) {
				body.render(new EscapeEntities(env.getOut()));
			}
		}

		private class EscapeEntities extends Writer {
			private final Writer out;

			EscapeEntities(Writer out) {
				this.out = out;
			}

			public void write(char[] cbuf, int off, int len) throws IOException {
				StringEscapeUtils.escapeHtml(out, new String(cbuf, off, len));
			}

			public void flush() throws IOException {
				out.flush();
			}

			public void close() throws IOException {
				out.close();
			}
		}
	}

}
