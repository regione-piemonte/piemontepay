/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import javax.annotation.Generated;


public class DTOCodiceVersamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String denominazione;

    @Generated ( "SparkTools" )
    private DTOCodiceVersamento ( Builder builder ) {
        this.id = builder.id;
        this.codice = builder.codice;
        this.denominazione = builder.denominazione;
    }

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the codice
     */
    public String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    /**
     * @return the denominazione
     */
    public String getDenominazione () {
        return denominazione;
    }

    /**
     * @param denominazione the denominazione to set
     */
    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    /**
     * Creates builder to build {@link DTOCodiceVersamento}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link DTOCodiceVersamento}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private String codice;

        private String denominazione;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public Builder withDenominazione ( String denominazione ) {
            this.denominazione = denominazione;
            return this;
        }

        public DTOCodiceVersamento build () {
            return new DTOCodiceVersamento ( this );
        }
    }

}
