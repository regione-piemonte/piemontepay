/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EpayRAutorizzazioneChiamanteTipoPagamentoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column ( name = "codice_chiamante" )
    private String codiceChiamante;

    @Column ( name = "id_tipo_pagamento" )
    private Long idTipoPagamento;

    @Column ( name = "data_inizio_validita" )
    private String dataInizioValidita;

    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

    public Long getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( String dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceChiamante == null ) ? 0 : codiceChiamante.hashCode () );
        result = prime * result + ( ( dataInizioValidita == null ) ? 0 : dataInizioValidita.hashCode () );
        result = prime * result + ( ( idTipoPagamento == null ) ? 0 : idTipoPagamento.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass () != obj.getClass () ) {
            return false;
        }
        EpayRAutorizzazioneChiamanteTipoPagamentoPK other = (EpayRAutorizzazioneChiamanteTipoPagamentoPK) obj;
        if ( codiceChiamante == null ) {
            if ( other.codiceChiamante != null ) {
                return false;
            }
        } else if ( !codiceChiamante.equals ( other.codiceChiamante ) ) {
            return false;
        }
        if ( dataInizioValidita == null ) {
            if ( other.dataInizioValidita != null ) {
                return false;
            }
        } else if ( !dataInizioValidita.equals ( other.dataInizioValidita ) ) {
            return false;
        }
        if ( idTipoPagamento == null ) {
            if ( other.idTipoPagamento != null ) {
                return false;
            }
        } else if ( !idTipoPagamento.equals ( other.idTipoPagamento ) ) {
            return false;
        }
        return true;
    }

}
