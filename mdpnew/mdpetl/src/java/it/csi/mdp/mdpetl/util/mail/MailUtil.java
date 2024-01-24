/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.util.LogUtil;

public class MailUtil {
	
	private static LogUtil log = new LogUtil(MailUtil.class);
	
	private static final String MAIL_PROPERTIES = "mail.properties";
	private static final String TEST_FLAG = "mail.test.flag";
	private static final String TEST_DESTINATARIO = "mail.test.destinatario";
	private static final String DEFAULT_DESTINATARIO = "mail.default.destinatario";
	private static final String MITTENTE_INDIRIZZO = "mail.mittente.indirizzo";
	private static final String MITTENTE_ALIAS = "mail.mittente.alias";
	private static final String DESTINATARIO_NOTIFICA_FALLITO_INVIO_RT_FRUITORE = "mail.destinatario.notifica.fallito.invio.rt.fruitore";
				
	private static Properties properties = new Properties();
	static {
		final String methodName = "initialization";
		
		InputStream inputStream = null;
		try {
			inputStream = ClassLoader.getSystemResourceAsStream(MAIL_PROPERTIES);
			properties.load(inputStream);
		} catch (IOException e) {
			log.error(methodName, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(methodName, ExceptionUtils.getStackFrames(e));
				}
			}
		}
		
		for (Entry<Object, Object> property : properties.entrySet()) {
			String message = "Property " + property.getKey() + " values '" + property.getValue() + "'";
			log.info(methodName, message);
		}
	}
	
	public static void inviaMail(MailData mail) throws MessagingException, UnsupportedEncodingException {
		final String methodName = "inviaMail";
		
		if (StringUtils.isBlank(mail.getFrom())) {
			mail.setFrom(properties.getProperty(MITTENTE_INDIRIZZO));
		}
		if (StringUtils.isBlank(mail.getAlias())) {
			mail.setAlias(properties.getProperty(MITTENTE_ALIAS));
		}
				
		Message message = new MimeMessage(Session.getInstance(properties));
		message.setSentDate(new Date());
		message.setFrom(mail.getFromAndAlias());
		
		if (Boolean.valueOf(properties.getProperty(TEST_FLAG))) {
			log.info(methodName, "mail di test");
			InternetAddress[] ia = {new InternetAddress(properties.getProperty(TEST_DESTINATARIO))};
			message.setRecipients(javax.mail.Message.RecipientType.TO, ia);
		} else {
			if (!mail.IsEmptyTo()) {
				message.setRecipients(javax.mail.Message.RecipientType.TO, mail.getTo());
			} else {
				log.info(methodName, "destinatario di default");
				InternetAddress[] ia = {new InternetAddress(properties.getProperty(DEFAULT_DESTINATARIO))};
				message.setRecipients(javax.mail.Message.RecipientType.TO, ia);
			}	
			if (!mail.IsEmptyCc()) {
				message.setRecipients(javax.mail.Message.RecipientType.CC, mail.getCc());
			}
			if (!mail.IsEmptyBcc()) {
				message.setRecipients(javax.mail.Message.RecipientType.BCC, mail.getBcc());
			}
		}
		
		if (Boolean.valueOf(properties.getProperty(TEST_FLAG))) {
			message.setSubject("TEST - " + mail.getSubject());
		} else {
			message.setSubject(mail.getSubject());
		}
		
		if (mail.hasAttachedFiles()) {
			Multipart multipart = new MimeMultipart();
			
			/*testo*/
			BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(mail.getText());
	        multipart.addBodyPart(messageBodyPart);

	        /* attachments */
	        for (Attached attached : mail.getAttachedFiles()) {
		        messageBodyPart = new MimeBodyPart();
		        messageBodyPart.setFileName(attached.getName());
		        DataSource source = new ByteArrayDataSource(attached.getData(), attached.getType());
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        multipart.addBodyPart(messageBodyPart);
			}

	        message.setContent(multipart);
		} else {
			message.setText(mail.getText());
		}
		Transport.send(message);
	}
	
	
	public static void main(String[] args){
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "mailfarm.csi.it");
		properties.setProperty("mail.smtp.port", "25");
		properties.setProperty(MITTENTE_INDIRIZZO, "servizio.mdp@csi.it");
		properties.setProperty(MITTENTE_ALIAS, "servizio.mdp");
		properties.setProperty(TEST_FLAG, "true");
		properties.setProperty(TEST_DESTINATARIO, "massimo.venesia@csi.it");
		
		MailData mail = new MailData();
		mail.setSubject("mail di prova");
		mail.setText("mail per testare la classe che compone il messaggio");
				
		try {
			MailUtil.inviaMail(mail);
			System.out.println("fine invio   ");
		} catch (Exception e) {
			System.out.println("errore   " + e);
			e.printStackTrace();
		}
	}
	
	public static String getDestinatarioNotificaFallitoInvioRTFruitore() {
		return properties.getProperty(DESTINATARIO_NOTIFICA_FALLITO_INVIO_RT_FRUITORE);
	}
		
}
