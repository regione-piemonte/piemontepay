/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import javax.annotation.Generated;


/**
 *
 */

public class EliminaPosizioneDebitoriaChiamanteEsternoOutput extends AbstractOutput {

    /**
     *
     */
    private static final long serialVersionUID = -8135190068072717738L;

    @Generated ( "SparkTools" )
    private EliminaPosizioneDebitoriaChiamanteEsternoOutput ( Builder builder ) {
        setResult ( builder.result );
    }
    
    /**
     * Creates builder to build {@link EliminaPosizioneDebitoriaChiamanteEsternoOutput}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }
    /**
     * Builder to build {@link EliminaPosizioneDebitoriaChiamanteEsternoOutput}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Esito result;

        private Builder () {
        }

        public Builder withResult ( Esito result ) {
            this.result = result;
            return this;
        }

        public EliminaPosizioneDebitoriaChiamanteEsternoOutput build () {
            return new EliminaPosizioneDebitoriaChiamanteEsternoOutput ( this );
        }
    }
    
}
