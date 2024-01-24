/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import javax.annotation.Generated;


/**
 *
 */

public class AggiornaPosizioneDebitoriaChiamanteEsternoOutput extends AbstractOutput {

    /**
     *
     */
    private static final long serialVersionUID = 3629243283892598380L;

    @Generated ( "SparkTools" )
    private AggiornaPosizioneDebitoriaChiamanteEsternoOutput ( Builder builder ) {
        setResult ( builder.result );
    }

    /**
     * Creates builder to build {@link AggiornaPosizioneDebitoriaChiamanteEsternoOutput}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link AggiornaPosizioneDebitoriaChiamanteEsternoOutput}.
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

        public AggiornaPosizioneDebitoriaChiamanteEsternoOutput build () {
            return new AggiornaPosizioneDebitoriaChiamanteEsternoOutput ( this );
        }
    }

}
