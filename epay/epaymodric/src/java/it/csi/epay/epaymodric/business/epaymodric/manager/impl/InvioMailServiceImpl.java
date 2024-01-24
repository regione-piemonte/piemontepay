/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
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

import it.csi.epay.epaymodric.business.epaymodric.bo.Email;
import it.csi.epay.epaymodric.business.epaymodric.manager.InvioMailManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.InvioMailUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTConfigurazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEmail;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.repository.ConfigurazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EmailRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailStatoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;


@Component
@Transactional
public class InvioMailServiceImpl implements InvioMailManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    private static final String EMAIL_ACTIVE = "mail.active";

    private static final String DEFAULT_EMAIL_ADDRESS = "DEFAULT_EMAIL_ADDRESS";

    private static final String DEFAULT_EMAIL_ASSISTENZA = "DEFAULT_EMAIL_ASSISTENZA_ADDRESS";

    @Autowired
    ConfigurazioneRepository configurazioneRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    ErroreRepository erroreRepository;

    @Autowired
    EnteRepository enteRepository;

    private String ambiente = "PRODUZIONE";

    private static java.util.ResourceBundle res;

    static {
        try {
            res = java.util.ResourceBundle.getBundle ( "config" );
        } catch ( MissingResourceException e ) {
            System.out.println ( e );
        }
    }

    public static java.util.ResourceBundle getRes () {
        return res;
    }

    @Override
    @Async
    public void emailDequeue () {
        //Cerco tutte le mail con stato in attesa

        List<String> stati = new ArrayList<String> ();
        stati.add ( EmailStatoEnum.ATTESA.getCodice () );

        List<CblTEmail> coda = emailRepository.findByStatoIn ( stati );

        for ( Iterator<CblTEmail> iterator = coda.iterator (); iterator.hasNext (); ) {
            CblTEmail cblTEmail = iterator.next ();

            Email email = InvioMailUtility.toEmail ( cblTEmail );

            emailInit ( email );

            try {
                sendMail ( email, cblTEmail.getIdEnte () );
            } catch (EpaymodricException e) {
                if (EpaymodricException.EMAIL_DEACTIVATED == e.getErrorCode()) {
                    logger.warn("Invio mail disattivato", e);
                } else {
                    logger.error("Errore durante l'invio mail", e);
                }
            }
        }
    }

    private void emailInit ( Email email ) {
        String port = getRes ().getString ( "smtp.port" );
        String retryTimesMax = getRes ().getString ( "smtp.retry" );
        String delay = getRes ().getString ( "smtp.retrydelay" );//UNUSED TILL NOW

		if ( retryTimesMax.isEmpty () )
            retryTimesMax = "0";

		if ( delay.isEmpty () )
            delay = "0";

		if ( port.isEmpty () )
            port = "0";

        email.setTransportProtocol ( getRes ().getString ( "mail.transport.protocol" ) );
        email.setSmtpAuth ( getRes ().getString ( "mail.smtp.auth" ) );
        email.setStarTTLsEnable ( getRes ().getString ( "mail.smtp.starTTLS.enable" ) );
        email.setServer ( getRes ().getString ( "smtp.server" ) );
        email.setUser ( getRes ().getString ( "smtp.user" ) );
        email.setPassword ( getRes ().getString ( "smtp.password" ) );
        email.setSender ( getRes ().getString ( "smtp.sender" ) );
        email.setEmergencyRecipient ( getRes ().getString ( "smtp.emergency" ) );

        email.setPort ( Long.parseLong ( port ) );
        email.setRetryTimesMax ( Long.parseLong ( retryTimesMax ) );
        email.setRetryDelaySeconds ( Long.parseLong ( delay ) );
        email.setRetryTimes(0L);
    }

    @Override
    @Async
    public void invioMail ( EmailEnum tipo, String errorEnum, String idEnte, String idFlusso, String descrizioneAggiuntiva ) {
        logger.info ( "InvioMailServiceImpl.invioMail: INIZIO" );

        //        Properties prop = new Properties ();
        //        InputStream input = null;
        //
        //        try {
        //            input = new FileInputStream ( "C:\\Users\\Betacom\\Desktop\\ESL040Torino\\Workspace\\ppu\\tech-poc-1\\epaymodric\\buildfiles\\config.properties" );
        //            prop.load ( input );
        //        } catch ( Exception ex ) {
        //            logger.error("Impossibile caricare il file di properties per email",ex);
        //        } finally {
        //            if ( input != null ) {
        //                try {
        //                    input.close ();
        //                } catch ( IOException e ) {
        //                    e.printStackTrace ();
        //                }
        //            }
        //        }

        //Ovviamente sara' in cache il tutto
        //CblTConfigurazione confServer = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.server" );
        //CblTConfigurazione confPort = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.port" );
        //CblTConfigurazione confUser = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.user" );
        //CblTConfigurazione confPassword = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.password" );
        //CblTConfigurazione confSender = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.sender" );
        //CblTConfigurazione confRetry = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.retry" );
        //CblTConfigurazione confEmergency = configurazioneRepository.findByIdEnteAndNomeAttributo ( null, "smtp.emergency" );

        CblTConfigurazione confTemplate = null;

        Email email = new Email ();

        emailInit ( email );

        ambiente = getRes ().getString ( "ambiente" );

        email.setSubject ( "[" + ambiente + "] - " );

        CblTEnte ente = enteRepository.findByIdEnte ( idEnte );

        CblTConfigurazione defaultEmailAddress = configurazioneRepository.findByNomeAttributo ( DEFAULT_EMAIL_ADDRESS );

        CblTConfigurazione mailAssistenza = configurazioneRepository.findByNomeAttributo ( DEFAULT_EMAIL_ASSISTENZA );

        if ( tipo.getCodice ().equalsIgnoreCase ( EmailEnum.ERRORE_ACQUISIZIONE.getCodice () ) ) {
            email.setRecipient ( defaultEmailAddress.getValore () );
            email.setSubject ( email.getSubject () + "Errore acquisizione flusso - CONTABILIZZATORE" );
            confTemplate = configurazioneRepository.findByNomeAttributo ( "email_template_acquisizione" );
        } else if ( tipo.getCodice ().equalsIgnoreCase ( EmailEnum.ERRORE_BUSINESS.getCodice () ) ) {
            email.setRecipient ( ente.getEmailEnte () );
            email.setSubject ( email.getSubject () + "Errore in fase di elaborazione - CONTABILIZZATORE" );
            email.setCarbonCopy ( mailAssistenza.getValore () );
            confTemplate = configurazioneRepository.findByNomeAttributo ( "email_template_business" );
        } else if ( tipo.getCodice ().equalsIgnoreCase ( EmailEnum.ERRORE_FLUSSO.getCodice () ) ) {
            email.setRecipient ( ente.getEmailEnte () );
            email.setSubject ( email.getSubject () + "Errore in fase di trasmissione flussi in errore / non elaborabili - CONTABILIZZATORE" );
            email.setCarbonCopy ( mailAssistenza.getValore () );
            confTemplate = configurazioneRepository.findByNomeAttributo ( "email_template_trasmissione_flussi" );
        } else if ( tipo.getCodice ().equalsIgnoreCase ( EmailEnum.ERRORE_DI_SISTEMA.getCodice () ) ) {
            email.setRecipient ( defaultEmailAddress.getValore () );
            email.setSubject ( email.getSubject () + "Errore di sistema - CONTABILIZZATORE" );
            confTemplate = configurazioneRepository.findByNomeAttributo ( "email_template_sistema" );
        } else if ( tipo.getCodice ().equalsIgnoreCase ( EmailEnum.ERRORE_ELABORAZIONE.getCodice () ) ) {
            email.setRecipient ( defaultEmailAddress.getValore () );
            email.setSubject ( email.getSubject () + "Errore di sistema - CONTABILIZZATORE - ATTIVA ELABORAZIONE" );
            confTemplate = configurazioneRepository.findByNomeAttributo ( "email_template_sistema" );
        }

        CblDErrore error = erroreRepository.findByCodiceErrore ( errorEnum );

        if (null != confTemplate) {
            String corpo = confTemplate.getValore ();

            if ( !StringUtils.isEmpty ( descrizioneAggiuntiva ) ) {
                corpo += System.lineSeparator () + descrizioneAggiuntiva;
            }

            email.setBody ( corpo );
        }

        if ( error != null ) {
            email.setBody ( email.getBody ().replaceAll ( "<cod_errore>", error.getCodiceErrore () ) );
            email.setBody ( email.getBody ().replaceAll ( "<des_errore>", error.getDescrizioneErrore () ) );
        }

        if ( idFlusso != null ) {
            email.setBody ( email.getBody ().replaceAll ( "<id_flusso>", idFlusso ) );
            email.setBody ( email.getBody ().replaceAll ( "<identificativo_flusso>", idFlusso ) );
        }

        email.setBody ( email.getBody ().replaceAll ( "<data_elaborazione>", new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" ).format ( new Date () ) ) );

        if ( ente != null ) {
            email.setBody ( email.getBody ().replaceAll ( "<denominazione_ente>", ente.getDenominazione () ) );
        }

        //L'ultimo step del ciclo invia una mail di emergenza
        //for ( int i = 0; i < ( email.getRetry () + 1 ); i++ ) {
        try {
            sendMail ( email, idEnte );
        } catch (EpaymodricException e) {
            if (EpaymodricException.EMAIL_DEACTIVATED == e.getErrorCode()) {
                logger.warn("Invio mail disattivato", e);
            } else {
                logger.error("Errore durante l'invio mail", e);
            }
        }

        //      break;
        //  try {
        //      Thread.currentThread ().sleep ( 1000 * email.getRetryDelay () );
        //  } catch ( InterruptedException e ) {
        //      logger.error ( e );
        //  }
        //}

        logger.info ( "InvioMailServiceImpl.invioMail: FINE" );

    }

    private boolean sendMail ( final Email email, String idEnte ) throws EpaymodricException {

        if (!(getRes ().getString(EMAIL_ACTIVE) != null && new Boolean ( getRes().getString(EMAIL_ACTIVE)))) {
            throw new EpaymodricException(EpaymodricException.EMAIL_DEACTIVATED, "Invio delle e-mail disattivato");
        }

        //Get the session object
        Properties properties = System.getProperties ();

        properties.put ( "mail.transport.protocol", email.getTransportProtocol () );
        properties.put ( "mail.smtp.auth", email.getSmtpAuth () );
        properties.put ( "mail.smtp.starttls.enable", email.getStarTTLsEnable () );
        properties.put ( "mail.smtp.host", email.getServer () );
        properties.put ( "mail.smtp.port", email.getPort () );

        if ( "locale".equals ( getRes ().getString ( "ambiente" ) ) ) {
            properties.put ( "mail.smtp.ssl.trust", "*" );
        }

        CblTEmail emailTable = new CblTEmail ();

        emailTable.setError ( "" );

        Session session = null;

        if ( email.getUser ().isEmpty () == false ) {
            properties.put ( "mail.smtp.auth", "true" );
            EpaymodricAuthenticator authenticator = new EpaymodricAuthenticator ( email.getUser (), email.getPassword () );
            session = Session.getInstance ( properties, authenticator );
        } else {
            session = Session.getInstance ( properties );
        }

        try {
            MimeMessage message = new MimeMessage ( session );
            message.setFrom ( new InternetAddress ( email.getSender () ) );

            if ( email.getRetryTimes () > email.getRetryTimesMax () ) {

                message.setSubject ( "NOTIFICA - " + email.getSubject () );
                message.addRecipient ( Message.RecipientType.TO, new InternetAddress ( email.getEmergencyRecipient () ) );
            } else {
                message.setSubject ( email.getSubject () );
                message.addRecipient ( Message.RecipientType.TO, new InternetAddress ( email.getRecipient () ) );
            }

            if ( !StringUtils.isEmpty ( email.getCarbonCopy () ) ) {
                message.addRecipient ( Message.RecipientType.CC, new InternetAddress ( email.getCarbonCopy () ) );
            }

            message.setText ( email.getBody () );
            message.setContent ( email.getBody (), "text/html; charset=utf-8" );

            // Send message
            Transport.send ( message );

            emailTable.setStato ( EmailStatoEnum.INVIATA.getCodice () );

        } catch ( Exception reason ) {
            emailTable.setError ( ExceptionUtils.getStackTrace ( reason ) );
            emailTable.setStato ( EmailStatoEnum.ATTESA.getCodice () );
        }

        if ( email.getRetryTimes () > email.getRetryTimesMax () ) {
            emailTable.setStato ( EmailStatoEnum.ERRORE.getCodice () );
        }

        emailTable.setSubject ( email.getSubject () );
        emailTable.setBody ( email.getBody () );
        emailTable.setRecipient ( email.getRecipient () );
        emailTable.setIdEnte ( idEnte );
        emailTable.setData ( new Timestamp ( System.currentTimeMillis () ) );
        emailTable.setRetry ( email.getRetryTimes () + 1 );
        emailTable.setId ( email.getId () != null && email.getId () > 0 ? email.getId () : null );

        emailRepository.save ( emailTable );

        if ( email.getRetryTimes () > email.getRetryTimesMax () )
            return false;

        logger.info ( String.format ( "Email inviata correttamente all'indirizzo %s e salvata su base dati", email.getRecipient () ) );

        return emailTable.getError ().isEmpty ();
    }

    private class EpaymodricAuthenticator extends Authenticator {

        private String user;

        private String password;

        public EpaymodricAuthenticator ( String user, String password ) {
            this.user = user;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication () {
            return new PasswordAuthentication ( this.user, this.password );
        }
    }
}
