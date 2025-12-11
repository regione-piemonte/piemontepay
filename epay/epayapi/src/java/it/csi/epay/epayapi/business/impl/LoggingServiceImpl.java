/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.business.impl;

import java.util.*;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import it.csi.epay.epayapi.business.LoggingService;
import it.csi.epay.epayapi.dto.common.LoggerDTO;
import org.apache.logging.log4j.LogManager;


/**
 *
 */
@Service
public class LoggingServiceImpl implements LoggingService, InitializingBean {

    private static final String ROOT_LOGGER_IDENTIFIER = "ROOT";
	private static final Logger logger = LogManager.getLogger ( LoggingService.class );

	@Override
    public void afterPropertiesSet () {
        logger.debug ( "initializating LoggingService" );
    }

    @Override
    public List<LoggerDTO> getLoggers () {
		LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
		Collection<org.apache.logging.log4j.core.Logger> collection = logContext.getLoggers();

        List<LoggerDTO> loggersList = new LinkedList<> ();

		for ( org.apache.logging.log4j.core.Logger c : collection ) {
			LoggerDTO dto = new LoggerDTO (
							c.getName () != null && !c.getName ().isEmpty () ? c.getName () : "ROOT",
							c.getLevel () != null ? c.getLevel ().toString () : c.getLevel () != null ? c.getLevel ().name () : null );
			loggersList.add ( dto );
		}

        return loggersList;
    }

    @Override
    public void setLevel ( LoggerDTO logger ) {
        Assert.notNull ( logger );
        Assert.hasText ( logger.getName () );
        Assert.hasText ( logger.getLevel () );

        if ( ROOT_LOGGER_IDENTIFIER.equalsIgnoreCase ( logger.getName () ) ) {
            setRootLevel ( logger.getLevel () );
        } else {
            setLevel ( logger.getName (), logger.getLevel () );
            setCategoryLevel ( logger.getName (), logger.getLevel () );
        }
    }

    public void setRootLevel ( String level ) {
	}

    public void setLevel ( String resource, String level ) {
	}

	public void setCategoryLevel ( String name, String level ) {
    }

}
