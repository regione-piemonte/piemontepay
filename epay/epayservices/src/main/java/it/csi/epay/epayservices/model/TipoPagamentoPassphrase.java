/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import javax.annotation.Generated;


public class TipoPagamentoPassphrase implements Serializable {

	private static final long serialVersionUID = 7754268419356687050L;

	private Long idTipoPagamento;
	
	private byte [] passphrase;


    @Generated ( "SparkTools" )
    private TipoPagamentoPassphrase ( Builder builder ) {
        this.idTipoPagamento = builder.idTipoPagamento;
        this.passphrase = builder.passphrase;
    }


   
	
    /**
     * @return the idTipoPagamento
     */
    public Long getIdTipoPagamento () {
        return idTipoPagamento;
    }



    
    /**
     * @param idTipoPagamento the idTipoPagamento to set
     */
    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }



    public byte[] getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(byte[] passphrase) {
		this.passphrase = passphrase;
	}

    /**
     * Creates builder to build {@link TipoPagamentoPassphrase}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link TipoPagamentoPassphrase}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long idTipoPagamento;

        private byte [] passphrase;

        private Builder () {
        }

        public Builder withIdTipoPagamento ( Long idTipoPagamento ) {
            this.idTipoPagamento = idTipoPagamento;
            return this;
        }

        public Builder withPassphrase ( byte [] passphrase ) {
            this.passphrase = passphrase;
            return this;
        }

        public TipoPagamentoPassphrase build () {
            return new TipoPagamentoPassphrase ( this );
        }
    }

}
