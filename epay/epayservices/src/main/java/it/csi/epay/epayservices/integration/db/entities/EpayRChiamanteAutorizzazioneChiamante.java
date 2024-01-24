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
@Table ( name = "epay_r_chiamante_autorizzazione_chiamante" )
public class EpayRChiamanteAutorizzazioneChiamante implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpayRChiamanteAutorizzazioneChiamantePK id;

    public EpayRChiamanteAutorizzazioneChiamante () {
        id = new EpayRChiamanteAutorizzazioneChiamantePK ();
    }

    public EpayRChiamanteAutorizzazioneChiamantePK getId () {
        return id;
    }

    public void setId ( EpayRChiamanteAutorizzazioneChiamantePK id ) {
        this.id = id;
    }

    public String getCodiceChiamante () {
        return id != null ? id.getCodiceChiamante () : null;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        id.setCodiceChiamante ( codiceChiamante );
    }

    public String getCodiceAutorizzazioneChiamante () {
        return id != null ? id.getCodiceAutorizzazioneChiamante () : null;
    }

    public void codiceAutorizzazioneChiamante ( String codiceAutorizzazioneChiamante ) {
        id.setCodiceAutorizzazioneChiamante ( codiceAutorizzazioneChiamante );
    }
}
