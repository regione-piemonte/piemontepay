/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.business.impl;

import it.csi.epay.epaybeapi.business.LoggingService;
import it.csi.epay.epaybeapi.dto.common.LoggerDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 */
@SuppressWarnings ( "deprecation" )
@Service
public class LoggingServiceImpl implements LoggingService, InitializingBean {

    private static final String ROOT_LOGGER_IDENTIFIER = "ROOT";

    private final static Logger logger = LogManager.getLogger ( LoggingService.class );

    private volatile static boolean isSupported;

    static {
//        try {
//            org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger ();
//            logger4j.setLevel ( logger4j.getLevel () );
//            isSupported = true;
//            logger.debug ( "Enabling log4j service" );
//        } catch ( Throwable t ) {
//            logger.error ( "Deactivating log4j service because of initialization error: " + t.getMessage (), t );
//            isSupported = false;
//        }

        if ( isSupported ) {
//            Map<String, String> merged = ConfigurazioneServiceImpl.getMergedConfiguration ();
//            for ( String key: merged.keySet () ) {
//                if ( key.startsWith ( "logging.logger.level." ) ) {
//                    String loggerName = key.substring ( "logging.logger.level.".length () );
//                    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger ( loggerName );
//                    setLevel ( logger, merged.get ( key ) );
//                } else if ( key.startsWith ( "logging.category.level." ) ) {
//                    String loggerName = key.substring ( "logging.category.level.".length () );
//                    Category category = org.apache.log4j.Category.getInstance ( loggerName );
//                    category.setLevel ( org.apache.log4j.Level.toLevel ( merged.get ( key ) ) );
//                }
//            }
        }
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        logger.debug ( "initializating LoggingService" );
    }

    @Override
    public List<LoggerDTO> getLoggers () {
//
//        @SuppressWarnings ( "unchecked" )
//        Enumeration<Logger> loggers = Logger.getRootLogger ()
//            .getLoggerRepository ()
//            .getCurrentLoggers ();

        List<LoggerDTO> loggersList = new LinkedList<> ();

//        while ( loggers.hasMoreElements () ) {
//            final Logger c = loggers.nextElement ();
//            LoggerDTO dto = new LoggerDTO (
//                c.getName () != null && !c.getName ().isEmpty () ? c.getName () : "ROOT",
//                c.getLevel () != null ? c.getLevel ().toString () : c.getEffectiveLevel () != null ? c.getEffectiveLevel ().toString () : null );
//            loggersList.add ( dto );
//        }

        return loggersList;
    }

    @Override
    public void setLevel ( LoggerDTO logger ) {
//        Assert.notNull ( logger );
//        Assert.hasText ( logger.getName () );
//        Assert.hasText ( logger.getLevel () );
//
//        if ( ROOT_LOGGER_IDENTIFIER.equalsIgnoreCase ( logger.getName () ) ) {
//            setRootLevel ( logger.getLevel () );
//        } else {
//            setLevel ( logger.getName (), logger.getLevel () );
//            setCategoryLevel ( logger.getName (), logger.getLevel () );
//        }
    }

    public void setRootLevel ( String level ) {
//        if ( !isSupported ) {
//            return;
//        }
//
//        org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger ();
//        setLevel ( rootLogger, level );
    }

    public void setLevel ( String resource, String level ) {
//        if ( !isSupported ) {
//            return;
//        }
//
//        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger ( resource );
//        setLevel ( logger, level );
    }

    public void setLevel ( Class<?> resource, String level ) {
//        if ( !isSupported ) {
//            return;
//        }
//
//        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger ( resource );
//        setLevel ( logger, level );
    }

    public void setCategoryLevel ( Class<?> resource, String level ) {
//        if ( !isSupported ) {
//            return;
//        }
//
//        Category category = org.apache.log4j.Category.getInstance ( resource );
//        setLevel ( category, level );
    }

    public void setCategoryLevel ( String name, String level ) {
//        if ( !isSupported ) {
//            return;
//        }

//        Category category = org.apache.log4j.Category.getInstance ( name );
//        setLevel ( category, level );
    }

    private static void setLevel ( Logger logger, String level ) {
//        LoggingServiceImpl.logger.debug ( "Updating log level for logger " + logger.getName () + " to " + level );
//        logger.setLevel ( org.apache.log4j.Level.toLevel ( level ) );
    }

//    private static void setLevel ( Category category, String level ) {
//        LoggingServiceImpl.logger.debug ( "Updating log level for category " + category.getName () + " to " + level );
//        category.setLevel ( org.apache.log4j.Level.toLevel ( level ) );
//    }
}
