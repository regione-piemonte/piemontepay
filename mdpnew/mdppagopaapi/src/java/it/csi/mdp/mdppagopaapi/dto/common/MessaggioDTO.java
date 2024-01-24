/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.common;

import javax.annotation.Generated;


/**
 *
 */

public class MessaggioDTO {

    private String codice;

    private String messaggio;

    @Generated ( "SparkTools" )
    private MessaggioDTO ( Builder builder ) {
        codice = builder.codice;
        messaggio = builder.messaggio;
    }

    public String getCodice () {
        return codice;
    }

    public String getMessaggio () {
        return messaggio;
    }

    /**
     * Creates builder to build {@link MessaggioDTO}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link MessaggioDTO}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String codice;

        private String messaggio;

        private Builder () {
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public Builder withMessaggio ( String messaggio ) {
            this.messaggio = messaggio;
            return this;
        }

        public MessaggioDTO build () {
            return new MessaggioDTO ( this );
        }
    }

}
