/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.business.ClientService;
import it.csi.epay.epaybeapi.business.MessageService;
import it.csi.epay.epaybeapi.dto.common.MessaggioDTO;
import it.csi.epay.epaybeapi.integration.domain.EpayDMessaggio;
import it.csi.epay.epaybeapi.integration.repository.EpayDMessaggioRepository;
import it.csi.epay.epaybeapi.util.Constants;
import it.csi.epay.epaybeapi.util.Messages;


/**
 * Implementazione del servizio per la gestione dei messaggi dinamici
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger ( ClientService.class );

    @Autowired
    private EpayDMessaggioRepository messaggioRepository;

    @Override
    @Transactional ( readOnly = false )
    @Cacheable ( value = Constants.CACHES.MESSAGES, key = "{#message, args}" )
    public MessaggioDTO getMessaggio ( Messages message, Object... args ) {

        String resolved = resolveFromDB ( message.name (), message.getMessage () );

        if ( !StringUtils.isBlank ( resolved ) ) {
            return MessaggioDTO.builder ()
                .withCodice ( message.name () )
                .withMessaggio ( args != null && args.length > 0 ? String.format ( resolved, args ) : resolved )
                .build ();
        }

        return MessaggioDTO.builder ()
            .withCodice ( message.name () )
            .withMessaggio ( "Si e' verificato un errore imprevisto." )
            .build ();
    }

    private String resolveFromDB ( String code, String defaultValue ) {

        logger.debug ( "resolving message from DB with code " + code );
        EpayDMessaggio record = messaggioRepository.findOne ( code );
        if ( record != null ) {
            logger.debug ( "resolved message on DB" );
            return record.getValore ();
        } else {
            logger.debug ( "missing message on DB" );
            // attempt insertion
            try {
                EpayDMessaggio newRecord = new EpayDMessaggio ();
                newRecord.setCodice ( code );
                newRecord.setValore ( defaultValue );
                newRecord.setDescrizione ( "Inserito automaticamente dal sistema " + LocalDateTime.now ().format ( DateTimeFormatter.ISO_DATE_TIME ) );
                messaggioRepository.save ( newRecord );
                logger.debug ( "missing message " + code + " added on DB" );
            } catch ( Exception e ) {
                logger.warn ( "error inserting message on DB with code " + code, e );
            }

            return defaultValue;
        }
    }
}
