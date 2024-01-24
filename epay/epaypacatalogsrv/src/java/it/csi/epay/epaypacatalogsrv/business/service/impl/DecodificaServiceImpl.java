/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.DecodificaService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.MessageDto;
import it.csi.epay.epaypacatalogsrv.model.Errore;
import it.csi.epay.epaypacatalogsrv.repository.ErroreRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Component
@Transactional
public class DecodificaServiceImpl implements DecodificaService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private ErroreRepository epaypacatDMessaggioRepository;

    @Override
    public Map<String, String> getMessaggi ( String codiceLingua ) {

        Map<String, String> output = new HashMap<> ();

        for ( Errore entry: epaypacatDMessaggioRepository.findByCodiceLinguaAndCodiceApplicativo (
            Constants.DEFAULT_LANGUAGE, Constants.APPLICATION_CODE ) ) {
            output.put ( entry.getCodice (), entry.getMessaggio () );
        }
        return output;
    }

    @Override
    public String getMessaggio ( String codice ) {
        Errore dto = epaypacatDMessaggioRepository.findOneByCodiceApplicativoAndCodiceLinguaAndCodice (
            Constants.APPLICATION_CODE, Constants.DEFAULT_LANGUAGE, codice );

        if ( dto == null ) {
            logger.warn ( "messaggio non trovato: " + codice );
            return Constants.DEFAULT_LANGUAGE + "." + Constants.APPLICATION_CODE + "." + codice;
        } else {
            return dto.getMessaggio ();
        }
    }

    @Override
    public MessageDto getMessaggio ( String codice, Object... parametri ) {
        String raw = getMessaggio ( codice );
        String formattato = EntityUtils.formatMessage ( raw, parametri );

        return new MessageDto ( codice, formattato );
    }

}
