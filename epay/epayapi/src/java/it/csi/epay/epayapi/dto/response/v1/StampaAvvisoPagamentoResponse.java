/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.dto.response.v1;

import java.io.Serializable;

import javax.annotation.Generated;

import it.csi.epay.epayservices.model.v1.AbstractOutput;
import it.csi.epay.epayservices.model.v1.Esito;


public class StampaAvvisoPagamentoResponse extends AbstractOutput implements Serializable {

    private static final long serialVersionUID = -78980346720797118L;

    private byte [] paymentnotice;

    @Generated ( "SparkTools" )
    private StampaAvvisoPagamentoResponse ( Builder builder ) {
        setResult ( builder.result );
        this.paymentnotice = builder.paymentnotice;
    }

    /**
     * @return the paymentnotice
     */
    public byte [] getPaymentnotice () {
        return paymentnotice;
    }

    /**
     * Creates builder to build {@link StampaAvvisoPagamentoResponse}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link StampaAvvisoPagamentoResponse}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Esito result;

        private byte [] paymentnotice;

        private Builder () {
        }

        public Builder withResult ( Esito result ) {
            this.result = result;
            return this;
        }

        public Builder withPaymentnotice ( byte [] paymentnotice ) {
            this.paymentnotice = paymentnotice;
            return this;
        }

        public StampaAvvisoPagamentoResponse build () {
            return new StampaAvvisoPagamentoResponse ( this );
        }
    }

}
