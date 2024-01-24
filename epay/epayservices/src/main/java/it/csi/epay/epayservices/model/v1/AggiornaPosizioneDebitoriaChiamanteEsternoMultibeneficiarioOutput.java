/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import javax.annotation.Generated;

/**
 *
 */

public class AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput extends AbstractOutput {

    /**
     *
     */
    private static final long serialVersionUID = -8666078896342612993L;

    @Generated ( "SparkTools" )
    private AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput ( Builder builder ) {
        setResult ( builder.result );
    }

    /**
     * Creates builder to build {@link AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput}.
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

        public AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput build () {
            return new AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput ( this );
        }
    }

}
