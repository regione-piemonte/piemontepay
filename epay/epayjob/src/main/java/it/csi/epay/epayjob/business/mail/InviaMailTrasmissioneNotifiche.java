/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.MessagingException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.Mail;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.sportello2epaywso.ResultType;


public class InviaMailTrasmissioneNotifiche {
	
	private static LogUtil log= new LogUtil(InviaMailTrasmissioneNotifiche.class);

	private static final String CONFIG_PROPERTIES = "config.properties";
	
	private Properties properties;

	private MailFacade mailFacade;
			
	public InviaMailTrasmissioneNotifiche(MailFacade mailFacade) {
		final String methodName = "InviaMailTrasmissioneNotifiche";
		this.mailFacade = mailFacade;
		
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			log.debug(methodName, "errore lettura parametri. " + e.getMessage());
			throw new RuntimeException("Errore lettura parametri: " + e.getMessage());
		}
		
		if (properties.isEmpty()) {
            log.debug ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
		}
	}
	
	public void inviaMailResponceWarning(RegistroElaborazioni registroElaborazioni, ResultType result) {
		//final String methodName = "inviaMailResponceWarning";
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(getTxt("mail.warning.testo.01"));
			sb.append(getTxt("mail.warning.testo.02", registroElaborazioni.getId().toString()));
			sb.append(getTxt("mail.warning.testo.03", registroElaborazioni.getDataInizio().toString()));
			sb.append(getTxt("mail.warning.testo.04", registroElaborazioni.getIdEnte().toString()));
            sb.append ( getTxt ( "mail.warning.testo.05",
                ( registroElaborazioni.getIdTipoPagamento () != null ? registroElaborazioni.getIdTipoPagamento ().toString () : "--" ) ) );
            sb.append ( getTxt ( "mail.warning.testo.06",
                registroElaborazioni.getPagamentiSpontanei () != null && registroElaborazioni.getPagamentiSpontanei () ? "spontanei" : "attesi" ) );
			sb.append(getTxt("mail.warning.testo.07", result.getCodice()));
			sb.append(getTxt("mail.warning.testo.08", result.getMessaggio()));
						
			Mail mail = new Mail();
			mail.setTo(getTxt("mail.trasmissioneNotifiche.to"));
			mail.setSubject(getTxt("mail.warning.oggetto"));
			mail.setText(sb.toString());
			
			mailFacade.inviaMail(mail);
		} catch (MessagingException|UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void inviaMailResponceError(RegistroElaborazioni registroElaborazioni, ResultType result) {
		//final String methodName = "inviaMailResponceError";
		try {
			StringBuilder sb = new StringBuilder();
            try {
			sb.append(getTxt("mail.error.testo.01"));
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
			sb.append(getTxt("mail.error.testo.02", registroElaborazioni.getId().toString()));
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
			sb.append(getTxt("mail.error.testo.03", registroElaborazioni.getDataInizio().toString()));
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
			sb.append(getTxt("mail.error.testo.04", registroElaborazioni.getIdEnte().toString()));
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
                sb.append ( getTxt ( "mail.error.testo.05",
                    ( registroElaborazioni.getIdTipoPagamento () != null ? registroElaborazioni.getIdTipoPagamento ().toString () : "--" ) ) );
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
                sb.append ( getTxt ( "mail.error.testo.06",
                    registroElaborazioni.getPagamentiSpontanei () != null && registroElaborazioni.getPagamentiSpontanei () ? "spontanei" : "attesi" ) );
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
			sb.append(getTxt("mail.error.testo.07", result.getCodice()));
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
            try {
			sb.append(getTxt("mail.error.testo.08", result.getMessaggio()));
            } catch ( Exception e ) {
                sb.append ( "[ERROR: " + e.getMessage () + "]" );
            }
						
            try {
				Mail mail = new Mail();
				mail.setTo(getTxt("mail.trasmissioneNotifiche.to"));
				mail.setSubject(getTxt("mail.error.oggetto"));
				mail.setText(sb.toString());
                mailFacade.inviaMail ( mail );
            } catch ( Exception e ) {
                System.out.println ( "Errore nell'invio della mail: " + e.getMessage () );
                e.printStackTrace ();
                throw e;
            }
			
		} catch (MessagingException|UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void inviaMailGenericError(Exception e) {
		//final String methodName = "inviaMailResponceError";
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(getTxt("mail.genericerror.testo.01"));
			sb.append(getTxt("mail.genericerror.testo.02", (new Date()).toString()));
			sb.append(getTxt("mail.genericerror.testo.03",  e.getMessage()));
			sb.append(getTxt("mail.genericerror.testo.04", ExceptionUtils.getStackTrace(e)));
						
			Mail mail = new Mail();
			mail.setTo(getTxt("mail.trasmissioneNotifiche.to"));
			mail.setSubject(getTxt("mail.genericerror.oggetto"));
			mail.setText(sb.toString());
			
			mailFacade.inviaMail(mail);
		} catch (MessagingException|UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
	}
	
	private String getTxt(String propertyKey, String ... parametri) {
		String riga = properties.getProperty(propertyKey);
		try {
			int i = 0;
			for (String parametro : parametri) {
				System.out.println("riga:"+riga);
				riga = riga.replace("$" + i++, parametro);
			}
			return riga + "\n";
		} catch (Exception e) {
			throw new RuntimeException("Errore preparazione riga della mail (" + propertyKey + ")", e.getCause());
		}
			
	}
	
	public InviaMailTrasmissioneNotifiche() {
		File file = new File("D:/Sviluppo/prodotti/csi/workspace_ePay/epayjob/conf/config.properties");
		properties = new Properties();
		
		try (InputStream inputStream = new FileInputStream(file)) {
			properties.load(inputStream);
			for (Entry<Object, Object> e : properties.entrySet()) {
				System.out.println((String)e.getKey() + " : " + (String)e.getValue());
			}
		} catch (IOException e) {
			log.debug("main", "errore lettura parametri. " + e.getMessage());
			throw new RuntimeException("Errore lettura parametri: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		InviaMailTrasmissioneNotifiche imtn = new InviaMailTrasmissioneNotifiche();
		
		System.out.println("\n\n" + imtn.getTxt("mail.error.testo.02", "pippo-pluto-paperino"));
	}

}
