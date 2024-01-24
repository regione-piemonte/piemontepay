/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import javax.annotation.Generated;
import java.io.Serializable;


public class GetStatoPosizioneDebitoriaOutput extends AbstractOutput implements Serializable {

    private static final long serialVersionUID = 216317436222374718L;

//    private final Stato status;
    
    private final String code;

    private final String description;

    


    @Generated ( "SparkTools" )
    private GetStatoPosizioneDebitoriaOutput ( Builder builder ) {
        this.code = builder.code;
        this.description = builder.description;
        setResult ( builder.result );
    }

    /**
     * Creates builder to build {@link GetStatoPosizioneDebitoriaOutput}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link GetStatoPosizioneDebitoriaOutput}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private  String code;

        private  String description;

        private Esito result;

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

        public Builder withResult ( Esito result ) {
            this.result = result;
            return this;
        }

        public GetStatoPosizioneDebitoriaOutput build () {
            return new GetStatoPosizioneDebitoriaOutput ( this );
        }
    }

    
    public String getCode () {
        return code;
    }

    
    public String getDescription () {
        return description;
    }
    
    

}
