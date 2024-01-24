/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import javax.annotation.Generated;
import java.io.Serializable;


/**
 *
 */

public class Stato implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -3174769577893181313L;

    private final String code;

    private final String description;

    @Generated ( "SparkTools" )
    private Stato ( Builder builder ) {
        this.code = builder.code;
        this.description = builder.description;
    }

    /**
     * @return the code
     */
    public String getCode () {
        return code;
    }

    /**
     * @return the description
     */
    public String getDescription () {
        return description;
    }

    /**
     * Creates builder to build {@link Stato}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link Stato}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String code;

        private String description;

        private Builder () {
        }

        public Builder withCode ( String code ) {
            this.code = code;
            return this;
        }

        public Builder withDescription ( String description ) {
            this.description = description;
            return this;
        }

        public Stato build () {
            return new Stato ( this );
        }
    }

}
