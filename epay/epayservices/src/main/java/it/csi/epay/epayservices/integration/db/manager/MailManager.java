/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.business.MailBean;
import it.csi.epay.epayservices.model.Attached;
import it.csi.epay.epayservices.model.JobContextDto;
import it.csi.epay.epayservices.model.Mail;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;


@Stateless ( name = "MailManager", mappedName = "MailManager" )
@Startup
public class MailManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String MAIL_PROPERTIES = "META-INF/mail.properties";

    private static final String TEST_FLAG = "mail.test.flag";

    private static final String TEST_DESTINATARIO = "mail.test.destinatario";

    private static final String DEFAULT_DESTINATARIO = "mail.default.destinatario";

    private static final String MITTENTE_INDIRIZZO = "mail.mittente.indirizzo";

    private static final String MITTENTE_ALIAS = "mail.mittente.alias";
    
    private Properties properties;

    @PostConstruct
    private void init () {
        final String methodName = "init";

        try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( MAIL_PROPERTIES ) ) {
            properties = new Properties ();
            properties.load ( inputStream );
        } catch ( IOException e ) {
            e.printStackTrace ();
        }

        for ( Entry<Object, Object> property: properties.entrySet () ) {
            String message = "Property " + property.getKey () + " values >" + property.getValue () + "<";
            log.info ( methodName, message );
        }
    }

    public void inviaMail ( Mail mail ) throws MessagingException, UnsupportedEncodingException {
      
        String methodName = "inviaMail";
        log.infoStart ( methodName );

        try {
            doInviaMail ( mail );

        } catch ( Throwable t ) {
            log.error ( methodName, "Errore nell'invio della mail", t );
            throw t;
        } finally {
            log.infoEnd ( methodName );
        }
    }

    private void doInviaMail ( Mail mail ) throws MessagingException, UnsupportedEncodingException {
        final String methodName = "inviaMail";
        
        if ( StringUtils.isBlank ( mail.getFrom () ) ) {
            mail.setFrom ( properties.getProperty ( MITTENTE_INDIRIZZO ) );
        }
        if ( StringUtils.isBlank ( mail.getAlias () ) ) {
            mail.setAlias ( properties.getProperty ( MITTENTE_ALIAS ) );
        }

        Message message = new MimeMessage ( Session.getInstance ( properties ) );
        message.setSentDate ( new Date () );
        message.setFrom ( mail.getFromAndAlias () );

        if ( Boolean.parseBoolean( properties.getProperty ( TEST_FLAG ) ) ) {
            log.info ( methodName, "mail di test" );
            InternetAddress [] ia = { new InternetAddress ( properties.getProperty ( TEST_DESTINATARIO ) ) };
            message.setRecipients ( javax.mail.Message.RecipientType.TO, ia );
        } else {
            if ( !mail.IsEmptyTo () ) {
                message.setRecipients ( javax.mail.Message.RecipientType.TO, mail.getTo () );
            } else {
                log.info ( methodName, "destinatario di default" );
                InternetAddress [] ia = { new InternetAddress ( properties.getProperty ( DEFAULT_DESTINATARIO ) ) };
                message.setRecipients ( javax.mail.Message.RecipientType.TO, ia );
            }
            if ( !mail.IsEmptyCc () ) {
                message.setRecipients ( javax.mail.Message.RecipientType.CC, mail.getCc () );
            }
            if ( !mail.IsEmptyBcc () ) {
                message.setRecipients ( javax.mail.Message.RecipientType.BCC, mail.getBcc () );
            }
        }

        if ( Boolean.parseBoolean( properties.getProperty ( TEST_FLAG ) ) ) {
            message.setSubject ( "Messaggio di test: " + mail.getSubject () );
        } else {
            message.setSubject ( mail.getSubject () );
        }

        if ( mail.hasAttachedFiles () ) {
            Multipart multipart = new MimeMultipart ();

            /* testo */
            BodyPart messageBodyPart = new MimeBodyPart ();
            messageBodyPart.setText ( mail.getText () );
            multipart.addBodyPart ( messageBodyPart );

            /* attachments */
            for ( Attached attached: mail.getAttachedFiles () ) {
                messageBodyPart = new MimeBodyPart ();
                messageBodyPart.setFileName ( attached.getName () );
                DataSource source = new ByteArrayDataSource ( attached.getData (), attached.getType () );
                messageBodyPart.setDataHandler ( new DataHandler ( source ) );
                multipart.addBodyPart ( messageBodyPart );
            }

            message.setContent ( multipart );
        } else {
            message.setText ( mail.getText () );
        }
        Transport.send ( message );
    }

    public void inviaMailAssistenza ( JobContextDto context ) {
        String methodName = "inviaMail";       
        log.infoStart ( methodName );
        try {
            String ambiente = getAmbiente();
            context.setAmbiente ( ambiente );
           
            String prefix = "[" + context.getUuid () + "] ";
            log.info ( methodName,
                prefix + "Si sono verificati dei problemi durante l'esecuzione del job. Invio segnalazione all'assistenza con UUID " + context.getUuid ()
                    + "." );

            StringBuilder mailText = new StringBuilder("Si sono verificati i seguenti problemi durante l'esecuzione del job " + context.getJobName() + " avviato alle " +
		            context.getStart().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ": \r\n\r\n");
            for ( String problema: context.getProblemi () ) {
                mailText.append(" - ").append(problema).append("\r\n");
            }

            mailText.append("\r\nConsultare i log per avere maggiori dettagli. E' possibile ricercare nei log l'identificativo ").append(context.getUuid()).append(".");

            log.info ( methodName, prefix + mailText );
            
            Mail mail = new Mail ();
            mail.setSubject ( context.getComponente ().toUpperCase () + "("+context.getAmbiente ().toUpperCase ()+") Problemi durante l'esecuzione del job " + context.getJobName () );
            mail.setText (mailText.toString());
            doInviaMail ( mail );

        } catch ( Throwable t ) {
            log.error ( methodName, "Errore nell'invio della mail all'assistenza", t );

        } finally {
            log.infoEnd ( methodName );
        }
    }
    
    private String getAmbiente() {
       String methodName = "getAmbiente";
       log.infoStart ( methodName );
       String ambiente = "";
        try {
            ambiente = properties.getProperty ( "ambiente" );
        } catch ( NullPointerException e ) {
            log.error ( methodName, "Errore durante la lettura dell'ambiente", e );
            e.printStackTrace ();
        }
        log.infoEnd ( methodName );
        return ambiente;
        
    }
    
    public static void main ( String [] args ) {
        Properties properties = new Properties ();
        properties.setProperty ( "mail.smtp.host", "mailfarm.csi.it" );
        properties.setProperty ( "mail.smtp.port", "25" );
        properties.setProperty ( MITTENTE_INDIRIZZO, "servizio.mdp@csi.it" );
        properties.setProperty ( MITTENTE_ALIAS, "PIEMONTE PAY" );
        properties.setProperty ( TEST_FLAG, "true" );
        properties.setProperty ( TEST_DESTINATARIO, "fabrizio.naro@eng.it" );

        Mail mail = new Mail ();
        mail.setSubject ( "mail di prova" );
        mail.setText ( "mail per testare la classe che compone il messaggio" );

        try {
            new MailBean ().inviaMail ( mail );
            System.out.println ( "fine invio   " );
        } catch ( Exception e ) {
            System.out.println ( "errore   " + e );
            e.printStackTrace ();
        }
    }

}
