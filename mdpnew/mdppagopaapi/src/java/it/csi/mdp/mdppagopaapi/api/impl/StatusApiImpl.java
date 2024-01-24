/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.api.impl;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.mdp.mdppagopaapi.api.StatusApi;
import it.csi.mdp.mdppagopaapi.api.util.SpringSupportedResource;
import it.csi.mdp.mdppagopaapi.business.ConfigurazioneService;
import it.csi.mdp.mdppagopaapi.business.MonitoringService;
import it.csi.mdp.mdppagopaapi.dto.monitoring.ServiceStatus;
import it.csi.mdp.mdppagopaapi.dto.monitoring.ServiceStatusDTO;


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
