/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.dto.response;

import javax.annotation.Generated;


/**
 *
 */

public class ClientRegistrationResponse {

    private String passphrase;

    public ClientRegistrationResponse () {
        // NOP
    }

    @Generated ( "SparkTools" )
    private ClientRegistrationResponse ( Builder builder ) {
        passphrase = builder.passphrase;
    }

    public String getPassphrase () {
        return passphrase;
    }

    /**
     * Creates builder to build {@link ClientRegistrationResponse}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static IPassphraseStage builder () {
        return new Builder ();
    }

    @Generated ( "SparkTools" )
    public interface IPassphraseStage {

        public IBuildStage withPassphrase ( String passphrase );
    }

    @Generated ( "SparkTools" )
    public interface IBuildStage {

        public ClientRegistrationResponse build ();
    }

    /**
     * Builder to build {@link ClientRegistrationResponse}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder implements IPassphraseStage, IBuildStage {

        private String passphrase;

        private Builder () {
        }

        @Override
        public IBuildStage withPassphrase ( String passphrase ) {
            this.passphrase = passphrase;
            return this;
        }

        @Override
        public ClientRegistrationResponse build () {
            return new ClientRegistrationResponse ( this );
        }
    }

}
