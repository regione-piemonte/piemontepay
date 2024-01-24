/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.mail;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class MailHelper {
	static private final String TEMPLATE_RELATIVEPATH = "/META-INF/email-templates";

	private InternetAddress[] toRecipients;
	private InternetAddress[] ccRecipients;
	private InternetAddress[] bccRecipients;
	private String subject;
	private Address sender;
	private String contentType;
	private Session mailSession;
	private String templateName;
	private List<Attachment> attachments;
	private Map<String, Object> templateContext;
	private Configuration cfg;

	public MailHelper(Session mailSession) {
		this.mailSession = mailSession;

		attachments = new ArrayList<Attachment>();

		cfg = new Configuration(new Version(2, 3, 20));
		cfg.setClassForTemplateLoading(MailHelper.class, TEMPLATE_RELATIVEPATH);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.getDefault());
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setSharedVariable("escapeHtmlEntity", new FreemarkerEscapeDirective());
	}

	public MailHelper setSender(String sender) throws AddressException {
		if (sender != null)
			this.sender = new InternetAddress(sender);
		else
			this.sender = null;
		return this;
	}

	public MailHelper setToRecipients(String toRecipients) throws AddressException {
		if (toRecipients != null)
			this.toRecipients = InternetAddress.parse(toRecipients);
		else
			this.toRecipients = null;
		return this;
	}

	public MailHelper setCcRecipients(String ccRecipients) throws AddressException {
		if (ccRecipients != null)
			this.ccRecipients = InternetAddress.parse(ccRecipients);
		else
			this.ccRecipients = null;
		return this;
	}

	public MailHelper setBccRecipients(String bccRecipients) throws AddressException {
		if (bccRecipients != null)
			this.bccRecipients = InternetAddress.parse(bccRecipients);
		else
			this.bccRecipients = null;
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

	public MailHelper addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
		return this;
	}

	public MailHelper addAttachment(String filename, String mimeType, byte[] content) {
		return addAttachment(new Attachment(filename, mimeType, content));
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
		if (bccRecipients != null) {
			m.setRecipients(Message.RecipientType.BCC, bccRecipients);
		}
		m.setSubject(subject);
		m.setSentDate(new java.util.Date());

		// Message body part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(generateContent(), contentType);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		if (attachments.size() > 0) {
			for (Attachment attachment : attachments) {
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				attachmentBodyPart.setDataHandler(new DataHandler(attachment.getContent(), attachment.getMimeType()));
				attachmentBodyPart.setFileName(attachment.getFilename());

				multipart.addBodyPart(attachmentBodyPart);
			}
		}

		m.setContent(multipart);

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
}
