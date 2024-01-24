/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

import javax.annotation.Generated;

import it.csi.epay.epayservices.model.v1.Esito;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput.Builder;



public class StatoPosizioneDebitoriaOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    
    private  String code;

    private  String description;
    
    private Esito result;
    

    


    @Generated ( "SparkTools" )
    private StatoPosizioneDebitoriaOutput ( Builder builder ) {
        this.code = builder.code;
        this.description = builder.description;
        setResult ( builder.result );
    }
    
    /**
     * Creates builder to build {@link StatoPosizioneDebitoriaOutput}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }
    

    

    /**
     * Builder to build {@link StatoPosizioneDebitoriaOutput}.
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

        public StatoPosizioneDebitoriaOutput build () {
            return new StatoPosizioneDebitoriaOutput ( this );
        }
    }


    
    public String getCode () {
        return code;
    }

    
    public void setCode ( String code ) {
        this.code = code;
    }

    
    public String getDescription () {
        return description;
    }

    
    public void setDescription ( String description ) {
        this.description = description;
    }

    public Esito getResult () {
        return result;
    }


    
    public void setResult ( Esito result ) {
        this.result = result;
    }


    @Override
    public String toString () {
        return "StatoPosizioneDebitoriaResponse [code=" + code + ", description=" + description + ", ="
             + "]";
    }
    
    
    

}
