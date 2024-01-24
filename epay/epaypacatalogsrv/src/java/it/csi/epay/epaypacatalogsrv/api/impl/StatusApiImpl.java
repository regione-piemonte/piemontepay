/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import it.csi.epay.epaypacatalogsrv.api.StatusApi;
import it.csi.epay.epaypacatalogsrv.api.util.SpringSupportedResource;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;



@SpringSupportedResource
public class StatusApiImpl implements StatusApi {

    @Autowired
    public ConfigurazioneService configurazioneService;

    @Override
    public Response getStatus () {
        Map<String, Object> map = new HashMap<> ();

        Instant start = Instant.now ();

        ConfigurazioneVO status = configurazioneService.getParametro ( ParametriConfigurazione.MIGRATION_ENABLE );

        map.put ( "name", "Epaypacatlogsrv" );
        map.put ( "status", "UP" );
        map.put ( "message", "Servizio attivo, database attivo" );
        map.put ( "details", "Servizio attivo" );
        map.put ( "responseTime", ChronoUnit.MILLIS.between ( start, Instant.now () ) );

        if ( null != status && StringUtils.hasText ( status.getValore () ) ) {
            return Response.ok ( map ).build ();
        } else {
            return Response.status ( Status.SERVICE_UNAVAILABLE ).entity ( map ).build ();
        }
    }

    @Override
    public Response getProfileInfo () {
        // TODO Auto-generated method stub
        return null;
    }

}
