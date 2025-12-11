/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epayapi.api.LoggingApi;
import it.csi.epay.epayapi.business.LoggingService;
import it.csi.epay.epayapi.dto.common.LoggerDTO;
import it.csi.epay.epayapi.api.util.SpringSupportedResource;


@SpringSupportedResource
public class LoggingApiImpl implements LoggingApi {

    @Autowired
    public LoggingService loggingService;

    @Override
    public List<LoggerDTO> get () {
        return loggingService.getLoggers ();
    }

    @Override
    public LoggerDTO update ( LoggerDTO payload ) {
        loggingService.setLevel ( payload );
        return payload;
    }

}
