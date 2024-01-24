/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.monitoring;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Generated;


/**
 *
 */

public class ServiceStatusDTO {

    public static ServiceStatusDTO.Builder of ( Boolean bool ) {
        if ( bool == null ) {
            return unknown ();
        } else if ( bool ) {
            return up ();
        } else {
            return down ();
        }
    }

    public static ServiceStatusDTO.Builder up () {
        return ServiceStatusDTO.builder ()
            .withMessage ( "Service is UP" )
            .withStatus ( ServiceStatus.UP );
    }

    public static ServiceStatusDTO.Builder down () {
        return ServiceStatusDTO.builder ()
            .withMessage ( "Service is DOWN" )
            .withStatus ( ServiceStatus.DOWN );
    }

    public static ServiceStatusDTO.Builder unknown () {
        return ServiceStatusDTO.builder ()
            .withMessage ( "Service is UNKNOWN" )
            .withStatus ( ServiceStatus.UNKNOWN );
    }

    public static ServiceStatusDTO.Builder warning () {
        return ServiceStatusDTO.builder ()
            .withMessage ( "Service requires attention" )
            .withStatus ( ServiceStatus.WARNING );
    }

    private ServiceStatus status;

    private Long responseTime;

    private String name;

    private String message;

    private Map<String, Object> details;

    @Generated ( "SparkTools" )
    private ServiceStatusDTO ( Builder builder ) {
        status = builder.status;
        message = builder.message;
        details = builder.details;
        name = builder.name;
        responseTime = builder.responseTime;
    }

    public Long getResponseTime () {
        return responseTime;
    }

    public void setResponseTime ( Long responseTime ) {
        this.responseTime = responseTime;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public ServiceStatus getStatus () {
        return status;
    }

    public void setStatus ( ServiceStatus status ) {
        this.status = status;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public Map<String, Object> getDetails () {
        return details;
    }

    public void setDetails ( Map<String, Object> details ) {
        this.details = details;
    }

    /**
     * Creates builder to build {@link ServiceStatusDTO}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ServiceStatusDTO}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private ServiceStatus status;

        private String name;

        private String message;

        private Long responseTime;

        private Map<String, Object> details = new TreeMap<> ();

        private Builder () {
        }

        public Builder withStatus ( ServiceStatus status ) {
            this.status = status;
            return this;
        }

        public Builder withResponseTime ( Long responseTime ) {
            this.responseTime = responseTime;
            return this;
        }

        public Builder withName ( String name ) {
            this.name = name;
            return this;
        }

        public Builder withMessage ( String message ) {
            this.message = message;
            return this;
        }

        public Builder withDetails ( Map<String, Object> details ) {
            this.details = details;
            return this;
        }

        public Builder withDetail ( String key, Object value ) {
            details.put ( key, value );
            return this;
        }

        public ServiceStatusDTO build () {
            return new ServiceStatusDTO ( this );
        }
    }

}
