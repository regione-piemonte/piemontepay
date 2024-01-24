/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table ( name = "epay_r_chiamante_tipo_pagamento" )
public class EpayRAutorizzazioneChiamanteTipoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpayRAutorizzazioneChiamanteTipoPagamentoPK id;

    @Column ( name = "data_fine_validita" )
    private String dataFineValidita;

    public EpayRAutorizzazioneChiamanteTipoPagamento () {
        id = new EpayRAutorizzazioneChiamanteTipoPagamentoPK ();
    }

    public EpayRAutorizzazioneChiamanteTipoPagamentoPK getId () {
        return id;
    }

    public void setId ( EpayRAutorizzazioneChiamanteTipoPagamentoPK id ) {
        this.id = id;
    }

    public String getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( String dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public String getCodiceChiamante () {
        return id != null ? id.getCodiceChiamante () : null;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        id.setCodiceChiamante ( codiceChiamante );
    }

    public Long getIdTipoPagamento () {
        return id != null ? id.getIdTipoPagamento () : null;
    }

    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        id.setIdTipoPagamento ( idTipoPagamento );
    }

    public String getDataInizioValidita () {
        return id != null ? id.getDataInizioValidita () : null;
    }

    public void setDataInizioValidita ( String dataInizioValidita ) {
        id.setDataInizioValidita ( dataInizioValidita );
    }
}
