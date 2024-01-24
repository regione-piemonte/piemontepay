/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;




import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="epay_r_tipo_pagamento_collegato", schema="epay" )
public class EpayRTipoPagamentoCollegato implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpayRTipoPagamentoCollegatoKey id;

    //CSI_PAG-1888 Commentata perche' non esiste nel db
    //@Column ( name = "data_fine_validita" )
    //private String dataFineValidita;

    public EpayRTipoPagamentoCollegato () {
        id = new EpayRTipoPagamentoCollegatoKey ();
    }

    public EpayRTipoPagamentoCollegatoKey getId () {
        return id;
    }

    public void setId ( EpayRTipoPagamentoCollegatoKey id ) {
        this.id = id;
    }

    public Long getIdTipoPagamentoPrincipale() {
        return id.getIdTipoPagamentoPrincipale();
    }

    public void setIdTipoPagamentoPrincipale(Long idTipoPagamentoPrincipale) {
        this.id.setIdTipoPagamentoPrincipale(idTipoPagamentoPrincipale) ;
    }

    public Long getIdTipoPagamentoSecondario() {
        return id.getIdTipoPagamentoSecondario();
    }

    public void setIdTipoPagamentoSecondario(Long idTipoPagamentoSecondario) {
        this.id.setIdTipoPagamentoSecondario(idTipoPagamentoSecondario) ;
    }



}
