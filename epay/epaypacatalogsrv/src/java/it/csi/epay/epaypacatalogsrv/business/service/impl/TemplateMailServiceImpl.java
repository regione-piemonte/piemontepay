/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.TemplateMailService;
import it.csi.epay.epaypacatalogsrv.model.TemplateEmail;
import it.csi.epay.epaypacatalogsrv.repository.TemplateEmailRepository;


@Component
@Transactional
public class TemplateMailServiceImpl implements TemplateMailService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    TemplateEmailRepository templateEmailRepository;

    @Override
    public String popolaTemplate ( String raw, Map<String, String> model ) {
        if ( raw == null ) {
            return null;
        }

        String placeholder;
        for ( Entry<String, String> entry: model.entrySet () ) {
            placeholder = "<" + entry.getKey () + ">";
            if ( raw.contains ( placeholder ) ) {
                raw = raw.replace ( placeholder, entry.getValue () );
            }
        }

        return raw;
    }

    @Override
    public TemplateEmail getTemplate ( String codice ) {

        logger.debug ( "risolvo template " + codice );

        TemplateEmail confTemplate = templateEmailRepository.findOneByCodice ( codice );
        if ( confTemplate == null ) {
            throw new RuntimeException ( "TEMPLATE NON CONFIGURATO" );
        }

        return confTemplate;
    }

}
