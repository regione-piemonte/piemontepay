/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.dto.security;

import java.io.Serializable;

import javax.annotation.Generated;


public class ScopeDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1252182472L;

    private String codice;

    private String descrizione;

    public static ScopeDTO fromScope ( Scopes auth ) {
        return ScopeDTO.builder ()
            .withCodice ( auth.name () )
            .withDescrizione ( auth.getDescrizione () )
            .build ();
    }

    @Generated ( "SparkTools" )
    private ScopeDTO ( Builder builder ) {
        codice = builder.codice;
        descrizione = builder.descrizione;
    }

    public String getCodice () {
        return codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    /**
     * Creates builder to build {@link ScopeDTO}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ScopeDTO}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String codice;

        private String descrizione;

        private Builder () {
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public Builder withDescrizione ( String descrizione ) {
            this.descrizione = descrizione;
            return this;
        }

        public ScopeDTO build () {
            return new ScopeDTO ( this );
        }
    }

    @Override
    public String toString () {
        return "ScopeDTO [" + ( codice != null ? "codice=" + codice + ", " : "" ) + ( descrizione != null ? "descrizione=" + descrizione : "" ) + "]";
    }

}
