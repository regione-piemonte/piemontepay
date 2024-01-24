/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.Mail;

public class InviaMailSincronizzazionePagamentiPEC {

	private static LogUtil log= new LogUtil(InviaMailSincronizzazionePagamentiPEC.class);
	private static final String CONFIG_PROPERTIES = "config.properties";

	private Properties properties;
	private MailFacade mailFacade;

	public InviaMailSincronizzazionePagamentiPEC(MailFacade mailFacade) {
        final String methodName = "InviaMailSincronizzazionePagamentiPEC";
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

    public void inviaMailGenericError ( Throwable e ) {
        final String methodName = "inviaMailGenericError";
        log.infoStart ( methodName );

		try {
            StringBuilder sb = new StringBuilder ();
            sb.append ( getTxt ( "mail.sincronizzapagamentipec.genericerror.testo.01" ) );
            sb.append ( getTxt ( "mail.sincronizzapagamentipec.genericerror.testo.02", ( new Date () ).toString () ) );
            sb.append ( getTxt ( "mail.sincronizzapagamentipec.genericerror.testo.03", e.getMessage () ) );
            sb.append ( getTxt ( "mail.sincronizzapagamentipec.genericerror.testo.04", ExceptionUtils.getStackTrace ( e ) ) );

            Mail mail = new Mail ();
            String to = getTxt ( "mail.sincronizzapagamentipec.to" );
            String subject = getTxt ( "mail.sincronizzapagamentipec.genericerror.oggetto" );
            String text = sb.toString ();
            mail.setTo ( to );
            mail.setSubject ( subject );
            mail.setText ( text );

            log.info ( methodName, "Invio mail: A " + to );
            log.info ( methodName, "Invio mail: SUBJECT: " + subject );
            log.info ( methodName, "Invio mail: TEXT: " + text );

            mailFacade.inviaMail ( mail );
        } catch ( Exception e2 ) {
            log.error ( "Errore nell'invio della mail", e2 );
        } finally {
            log.infoEnd ( methodName );
		}

	}

	private String getTxt(String propertyKey, String ... parametri) {
		String riga = properties.getProperty(propertyKey);
		try {
			int i = 0;
			for (String parametro : parametri) {
                log.debug ( "getTxt", "riga:" + riga );
				riga = riga.replace("$" + i++, parametro);
			}
			return riga + "\n";
		} catch (Exception e) {
			throw new RuntimeException("Errore preparazione riga della mail (" + propertyKey + ")", e.getCause());
		}

	}

}
