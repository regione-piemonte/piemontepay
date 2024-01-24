/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailStatoEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.DecodificaService;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.TemplateMailService;
import it.csi.epay.epaypacatalogsrv.business.util.InvioMailUtils;
import it.csi.epay.epaypacatalogsrv.model.Email;
import it.csi.epay.epaypacatalogsrv.model.TemplateEmail;
import it.csi.epay.epaypacatalogsrv.repository.ConfigurazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.EmailRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.ErroreRepository;
import it.csi.epay.epaypacatalogsrv.repository.TemplateEmailRepository;
import it.csi.epay.epaypacatalogsrv.vo.email.EmailVO;


@Component
@Transactional
public class InvioMailServiceImpl implements InvioMailService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    public final static String DEFAULT_DESTINATARIO = null;

    private static final String DEFAULT_SUBJECT = "EPAYCATALOG - Notifica di sistema";

    private static final String ENVIRONMENT_LOCAL = "local";

    private static final String MAIL_CONTENT_TYPE = "text/html; charset=utf-8";

    private static final String PROPERTY_MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";

    private static final String PROPERTY_MAIL_SMTP_PORT = "mail.smtp.port";

    private static final String PROPERTY_MAIL_SMTP_HOST = "mail.smtp.host";

    private static final String PROPERTY_MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

    private static final String PROPERTY_MAIL_SMTP_AUTH = "mail.smtp.auth";

    private static final String PROPERTY_MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";

    @Autowired
    ConfigurazioneRepository configurazioneRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    TemplateEmailRepository templateEmailRepository;

    @Autowired
    ErroreRepository erroreRepository;

    @Autowired
    EnteRepository enteRepository;

    @Autowired
    DecodificaService decodificaService;

    @Autowired
    ConfigurazioneService configurazioneService;

    @Autowired
    TemplateMailService templateMailService;

    private boolean isMailEnabled () {
        return configurazioneService.getParametro ( ParametriConfigurazione.MAIL_ACTIVE ).asBoolean ( false );
    }

    @Override
    @Async
    public Map<EmailStatoEnum, Integer> elaboraCodaEmail () {

        if ( !isMailEnabled () ) {
            logger.warn ( "Invio mail disattivato" );
            return null;
        }

        List<Email> coda = emailRepository.findByCodiceStato ( EmailStatoEnum.ATTESA.getCodice () );
        Map<EmailStatoEnum, Integer> risultati = new HashMap<> ();

        for ( Email emailEntity: coda ) {
            EmailStatoEnum risultato = reinviaMail ( emailEntity );

            risultati.put ( risultato, risultati.getOrDefault ( risultato, 0 ) + 1 );
        }

        return risultati;
    }

    @Override
    @Async
    public void inviaMail ( EmailEnum template, Long idEnte ) {
        inviaMail ( template, DEFAULT_DESTINATARIO, idEnte, null );
    }

    @Override
    @Async
    public void inviaMail ( EmailEnum template, String destinatario, Long idEnte ) {
        inviaMail ( template, destinatario, idEnte, null );
    }

    @Override
    @Async
    public void inviaMail ( EmailEnum codTemplate, String destinatario, Long idEnte, Map<String, String> model ) {
        if ( !isMailEnabled () ) {
            logger.warn ( "Invio mail disattivato" );
            return;
        }

        if ( model == null ) {
            model = new HashMap<> ();
        }

        EmailVO email = null;

        try {
            TemplateEmail template = templateMailService.getTemplate ( codTemplate.getCodice () );

            email = new EmailVO ();

            emailInit ( email );

            // comparison strana perche' la costante potrebbe essere NULL
            if ( String.valueOf ( destinatario ).equals ( String.valueOf ( DEFAULT_DESTINATARIO ) ) ) {
                email.setRecipient ( configurazioneService.richiediParametro ( ParametriConfigurazione.DEFAULT_EMAIL_ADDRESS, idEnte ).asString () );
            } else {
                email.setRecipient ( destinatario );
                email
                    .setCarbonCopy ( configurazioneService.richiediParametro ( ParametriConfigurazione.DEFAULT_EMAIL_ASSISTENZA_ADDRESS, idEnte ).asString () );
            }

            email.setSubject ( popolaTemplate ( !StringUtils.isEmpty ( template.getOggetto () ) ? template.getOggetto () : DEFAULT_SUBJECT, model ) );
            email.setBody ( popolaTemplate ( template.getTemplate (), model ) );

        } catch ( Exception e ) {

            logger.error ( "Errore critico nella preparazione della email da inviare", e );
            throw e;
        }

        try {
            tentaInvioMail ( email, idEnte, false );
        } catch ( Exception e ) {
            logger.error ( "Errore durante l'invio mail", e );
        }
    }

    private EmailStatoEnum reinviaMail ( Email emailEntity ) {
        Long idEnte = emailEntity.getIdEnte ().longValue ();

        EmailVO email = InvioMailUtils.toEmail ( emailEntity );

        emailInit ( email );

        email.setCarbonCopy ( configurazioneService.richiediParametro ( ParametriConfigurazione.DEFAULT_EMAIL_ASSISTENZA_ADDRESS, idEnte ).asString () );

        return tentaInvioMail ( email, idEnte, true );
    }

    private EmailStatoEnum tentaInvioMail ( final EmailVO email, Long idEnte, boolean reinvio ) {
        EmailStatoEnum returnValue;

        Email emailTable = new Email ();

        boolean invioRiuscito;

        try {
            MimeMessage message = getMessageFromEmail ( email );

            message.setFrom ( new InternetAddress ( email.getSender () ) );
            message.setSubject ( email.getSubject () );
            message.addRecipient ( Message.RecipientType.TO, new InternetAddress ( email.getRecipient () ) );
            message.setText ( email.getBody () );
            message.setContent ( email.getBody (), MAIL_CONTENT_TYPE );

            if ( !StringUtils.isEmpty ( email.getCarbonCopy () ) ) {
                message.addRecipient ( Message.RecipientType.CC, new InternetAddress ( email.getCarbonCopy () ) );
            }

            Transport.send ( message );

            invioRiuscito = true;

        } catch ( Exception reason ) {
            logger.error ( "errore nell'invio della mail", reason );
            emailTable.setError ( ExceptionUtils.getStackTrace ( reason ) );
            invioRiuscito = false;
        }

        if ( invioRiuscito ) {
            emailTable.setCodiceStato ( EmailStatoEnum.INVIATA.getCodice () );
            returnValue = EmailStatoEnum.INVIATA;

        } else {
            if ( email.getRetryTimes () >= email.getRetryTimesMax () ) {
                emailTable.setCodiceStato ( EmailStatoEnum.ERRORE.getCodice () );
                returnValue = EmailStatoEnum.ERRORE;

                // invio email di emergenza
                notificaInvioFallito ( email );

            } else {
                emailTable.setCodiceStato ( EmailStatoEnum.ATTESA.getCodice () );
                returnValue = EmailStatoEnum.ATTESA;
            }
        }

        emailTable.setId ( email.getId () );
        emailTable.setSubject ( email.getSubject () );
        emailTable.setBody ( email.getBody () );
        emailTable.setRecipient ( email.getRecipient () );
        emailTable.setIdEnte ( idEnte.intValue () );
        emailTable.setData ( new Timestamp ( System.currentTimeMillis () ) );
        emailTable.setNumeroTentativi ( email.getRetryTimes ().intValue () + 1 );

        emailRepository.saveAndFlush ( emailTable );
        return returnValue;
    }

    private void notificaInvioFallito ( final EmailVO email ) {

        String newBody = "<p> L'invio della mail al destinatario e' fallito. <br/> " +
            "Si riporta di seguito il contenuto della mail originale. </p>" +
            email.getBody ();

        try {
            MimeMessage message = getMessageFromEmail ( email );

            message.setFrom ( new InternetAddress ( email.getSender () ) );
            message.setSubject ( "NOTIFICA INVIO FALLITO - " + email.getSubject () );
            message.addRecipient ( Message.RecipientType.TO, new InternetAddress ( email.getEmergencyRecipient () ) );
            message.setText ( newBody );
            message.setContent ( newBody, MAIL_CONTENT_TYPE );

            if ( !StringUtils.isEmpty ( email.getCarbonCopy () ) ) {
                message.addRecipient ( Message.RecipientType.CC, new InternetAddress ( email.getCarbonCopy () ) );
            }

            Transport.send ( message );

        } catch ( Exception e ) {
            logger.error ( "Errore nell'invio della mail di emergenza", e );
        }
    }

    private String popolaTemplate ( String raw, Map<String, String> model ) {
        if ( raw == null ) {
            return null;
        }

        if ( model == null ) {
            model = new HashMap<> ();
        }

        return templateMailService.popolaTemplate ( raw, model );
    }

    private void emailInit ( EmailVO email ) {

        email.setTransportProtocol ( configurazioneService.richiediParametro ( ParametriConfigurazione.MAIL_TRANSPORT_PROTOCOL ).asString () );
        email.setSmtpAuth ( configurazioneService.richiediParametro ( ParametriConfigurazione.MAIL_SMTP_AUTH ).asString () );
        email.setStarTTLsEnable ( configurazioneService.richiediParametro ( ParametriConfigurazione.MAIL_SMTP_STARTTLS_ENABLE ).asString () );
        email.setServer ( configurazioneService.richiediParametro ( ParametriConfigurazione.SMTP_SERVER ).asString () );
        email.setUser ( configurazioneService.getParametro ( ParametriConfigurazione.SMTP_USER ).asString ( null ) );
        email.setPassword ( configurazioneService.getParametro ( ParametriConfigurazione.SMTP_PASSWORD ).asString ( null ) );
        email.setSender ( configurazioneService.richiediParametro ( ParametriConfigurazione.SMTP_SENDER ).asString () );
        email.setEmergencyRecipient ( configurazioneService.richiediParametro ( ParametriConfigurazione.SMTP_EMERGENCY ).asString () );
        email.setPort ( configurazioneService.richiediParametro ( ParametriConfigurazione.SMTP_PORT ).asLong ( 0L ) );
        email.setRetryTimesMax ( configurazioneService.richiediParametro ( ParametriConfigurazione.SMTP_RETRY ).asLong ( 0L ) );
        email.setRetryDelaySeconds ( configurazioneService.richiediParametro ( ParametriConfigurazione.SMTP_RETRYDELAY ).asLong ( 0L ) );

        if ( email.getId () == null ) {
            email.setRetryTimes ( 0L );
        }
    }

    private MimeMessage getMessageFromEmail ( final EmailVO email ) {

        Properties properties = System.getProperties ();

        properties.put ( PROPERTY_MAIL_TRANSPORT_PROTOCOL, email.getTransportProtocol () );
        properties.put ( PROPERTY_MAIL_SMTP_AUTH, email.getSmtpAuth () );
        properties.put ( PROPERTY_MAIL_SMTP_STARTTLS_ENABLE, email.getStarTTLsEnable () );
        properties.put ( PROPERTY_MAIL_SMTP_HOST, email.getServer () );
        properties.put ( PROPERTY_MAIL_SMTP_PORT, email.getPort () != null ? email.getPort ().toString () : null );

        if ( ENVIRONMENT_LOCAL.equals ( configurazioneService.getParametro ( ParametriConfigurazione.SPRING_PROFILES_ACTIVE ).getValore () ) ) {
            properties.put ( PROPERTY_MAIL_SMTP_SSL_TRUST, "*" );
        }

        Session session = null;

        if ( email.getUser () != null && email.getUser ().isEmpty () == false ) {
            properties.put ( PROPERTY_MAIL_SMTP_AUTH, "true" );
            EpaycatalogAuthenticator authenticator = new EpaycatalogAuthenticator ( email.getUser (), email.getPassword () );
            session = Session.getInstance ( properties, authenticator );
        } else {
            session = Session.getInstance ( properties );
        }

        MimeMessage message = new MimeMessage ( session );
        return message;
    }

    private class EpaycatalogAuthenticator extends Authenticator {

        private final String user;

        private final String password;

        public EpaycatalogAuthenticator ( String user, String password ) {
            this.user = user;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication () {
            return new PasswordAuthentication ( user, password );
        }
    }

}
