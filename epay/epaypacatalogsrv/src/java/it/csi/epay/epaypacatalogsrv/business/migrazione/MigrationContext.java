/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneInput;
import it.csi.epay.epaypacatalogsrv.model.LogMigrazione;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.TipologiaLogMigrazione;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 *
 */

public class MigrationContext {

    private final Logger logger = LogManager.getLogger ( "it.csi.epay.epaypacatalogsrv.business.service.DataMigrationService" );

    private final Date startTime;

    private final List<LogMigrazione> log;

    private final String migrationUUID;

    private Boolean success = null;

    private Throwable error = null;

    private final EseguiMigrazioneInput input;

    public MigrationContext ( EseguiMigrazioneInput input ) {
        startTime = new Date ();
        log = new ArrayList<> ();
        migrationUUID = UUID.randomUUID ().toString ();
        this.input = input;
    }

    public MigrationContext success () {
        success = true;
        error = null;
        return this;
    }

    public MigrationContext fail ( Throwable e ) {
        error = e;
        success = false;
        return this;
    }

    public EseguiMigrazioneInput getInput () {
        return input;
    }

    public Boolean getSuccess () {
        return success;
    }

    public Throwable getError () {
        return error;
    }

    public String getMigrationUUID () {
        return migrationUUID;
    }

    private void log ( TipologiaLogMigrazione codiceMessaggio, CodiceEntitaMigrazione codiceEntita, String text ) {
        LogMigrazione voce = new LogMigrazione ();

        voce.setCodiceEntita ( codiceEntita != null ? codiceEntita.name () : null );
        voce.setCodiceMessaggio ( codiceMessaggio != null ? codiceMessaggio.name () : null );
        voce.setDataOra ( Timestamp.valueOf ( LocalDateTime.now () ) );
        voce.setId ( null );
        voce.setIdMigrazione ( migrationUUID );
        voce.setMessaggio ( text );

        log.add ( voce );
    }

    public Date getStartTime () {
        return startTime;
    }

    public Timestamp getStartTimestamp () {
        return startTime != null ? new java.sql.Timestamp ( startTime.getTime () ) : null;
    }

    public List<LogMigrazione> getLog () {
        return log;
    }

    public void debug ( CodiceEntitaMigrazione codiceEntita, String text ) {
        logger.debug ( getLogPrefix () + " [" + codiceEntita + "] - " + text );
        if ( logger.isDebugEnabled () ) {
            log ( TipologiaLogMigrazione.DEBUG, codiceEntita, text );
        }
    }

    public void debug ( String text ) {
        logger.debug ( getLogPrefix () + text );
        if ( logger.isDebugEnabled () ) {
            log ( TipologiaLogMigrazione.DEBUG, null, text );
        }
    }

    public void info ( CodiceEntitaMigrazione codiceEntita, String text ) {
        logger.info ( getLogPrefix () + " [" + codiceEntita + "] - " + text );
        if ( logger.isInfoEnabled () ) {
            log ( TipologiaLogMigrazione.INFO, codiceEntita, text );
        }
    }

    public void info ( String text ) {
        logger.info ( getLogPrefix () + text );
        if ( logger.isInfoEnabled () ) {
            log ( TipologiaLogMigrazione.INFO, null, text );
        }
    }

    public void warn ( CodiceEntitaMigrazione codiceEntita, String text ) {
        logger.warn ( getLogPrefix () + " [" + codiceEntita + "] - " + text );
        log ( TipologiaLogMigrazione.WARNING, codiceEntita, text );
    }

    public void warn ( String text ) {
        logger.warn ( getLogPrefix () + text );
        log ( TipologiaLogMigrazione.WARNING, null, text );
    }

    public void warn ( CodiceEntitaMigrazione codiceEntita, String text, Throwable e ) {
        logger.warn ( getLogPrefix () + " [" + codiceEntita + "] - " + text, e );
        log ( TipologiaLogMigrazione.WARNING, codiceEntita,
            text + " - " + e.getClass ().getSimpleName () + " - " + e.getMessage () );
    }

    public void warn ( String text, Throwable e ) {
        logger.warn ( getLogPrefix () + text, e );
        log ( TipologiaLogMigrazione.WARNING, null,
            text + " - " + e.getClass ().getSimpleName () + " - " + e.getMessage () );
    }

    public void error ( CodiceEntitaMigrazione codiceEntita, String text ) {
        logger.error ( getLogPrefix () + " [" + codiceEntita + "] - " + text );
        log ( TipologiaLogMigrazione.ERRORE, codiceEntita, text );
    }

    public void error ( String text ) {
        logger.error ( getLogPrefix () + text );
        log ( TipologiaLogMigrazione.ERRORE, null, text );
    }

    public void error ( CodiceEntitaMigrazione codiceEntita, String text, Throwable e ) {
        logger.error ( getLogPrefix () + " [" + codiceEntita + "] - " + text, e );
        log ( TipologiaLogMigrazione.ERRORE, codiceEntita,
            text + " - " + e.getClass ().getSimpleName () + " - " + e.getMessage () );
    }

    public void error ( String text, Throwable e ) {
        logger.error ( getLogPrefix () + text, e );
        log ( TipologiaLogMigrazione.ERRORE, null,
            text + " - " + e.getClass ().getSimpleName () + " - " + e.getMessage () );
    }

    private String getLogPrefix () {
        return "[migrazione " + migrationUUID + "] ";
    }
}
