/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.api.impl;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epaybeapi.api.StatusApi;
import it.csi.epay.epaybeapi.api.util.SpringSupportedResource;
import it.csi.epay.epaybeapi.business.ConfigurazioneService;
import it.csi.epay.epaybeapi.business.MonitoringService;
import it.csi.epay.epaybeapi.dto.monitoring.ServiceStatus;
import it.csi.epay.epaybeapi.dto.monitoring.ServiceStatusDTO;


@SpringSupportedResource
public class StatusApiImpl implements StatusApi {

    @Autowired
    public ConfigurazioneService configurazioneService;

    @Autowired
    public MonitoringService monitoringService;

    @Override
    public Response getStatus () {
        Map<String, Object> map = configurazioneService.getBuildProperties ();

        ServiceStatusDTO status = monitoringService.getServiceStatus ();

        map.put ( "name", status.getName () );
        map.put ( "status", status.getStatus () );
        map.put ( "message", status.getMessage () );
        map.put ( "details", status.getDetails () );
        map.put ( "responseTime", status.getResponseTime () );

        if ( status.getStatus () == ServiceStatus.UP ) {
            return Response.ok ( map ).build ();
        } else {
            return Response.status ( Status.SERVICE_UNAVAILABLE ).entity ( map ).build ();
        }
    }

    @Override
    public Response getProfileInfo () {
        Map<String, Object> map = configurazioneService.getBuildProperties ();
        return Response.ok ( map ).build ();
    }

}
