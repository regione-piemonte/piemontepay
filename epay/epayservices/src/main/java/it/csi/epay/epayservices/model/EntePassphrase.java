/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import javax.annotation.Generated;


public class EntePassphrase implements Serializable {

	private static final long serialVersionUID = 7754268419356687050L;

	private Long idEnte;
	
	private byte [] passphrase;


    @Generated ( "SparkTools" )
    private EntePassphrase ( Builder builder ) {
        this.idEnte = builder.idEnte;
        this.passphrase = builder.passphrase;
    }


    public Long getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( Long idEnte ) {
        this.idEnte = idEnte;
    }

	public byte[] getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(byte[] passphrase) {
		this.passphrase = passphrase;
	}

    /**
     * Creates builder to build {@link EntePassphrase}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link EntePassphrase}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long idEnte;

        private byte [] passphrase;

        private Builder () {
        }

        public Builder withIdEnte ( Long idEnte ) {
            this.idEnte = idEnte;
            return this;
        }

        public Builder withPassphrase ( byte [] passphrase ) {
            this.passphrase = passphrase;
            return this;
        }

        public EntePassphrase build () {
            return new EntePassphrase ( this );
        }
    }

}
