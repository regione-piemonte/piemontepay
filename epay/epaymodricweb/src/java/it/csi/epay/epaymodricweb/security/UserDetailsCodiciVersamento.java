/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.security;

import javax.annotation.Generated;

public class UserDetailsCodiciVersamento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;
	protected String denominazione;
	protected String codice;

	
    public String label () {
        return codice + " - " + denominazione;
    }

    @Generated ( "SparkTools" )
    private UserDetailsCodiciVersamento ( Builder builder ) {
        this.id = builder.id;
        this.denominazione = builder.denominazione;
        this.codice = builder.codice;
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
     * Creates builder to build {@link UserDetailsCodiciVersamento}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link UserDetailsCodiciVersamento}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private String denominazione;

        private String codice;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withDenominazione ( String denominazione ) {
            this.denominazione = denominazione;
            return this;
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public UserDetailsCodiciVersamento build () {
            return new UserDetailsCodiciVersamento ( this );
        }
    }

}
