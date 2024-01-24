/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="epay_c_campi_redirect_async")
@NamedQueries ( {
    @NamedQuery(name="EpayCCampiRedirectAsync.findAll", query="SELECT t FROM EpayCCampiRedirectAsync t")
})
public class EpayCCampiRedirectAsync implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private String id;

    @Column(name="campo_interfaccia")
    private String campoInterfaccia;

    @Column(name="descrizione", nullable=false)
    private String descrizione;

    @Column(name="flg_abilitato", nullable=false)
    private Boolean flgAbilitato;


    public String getId () {
        return id;
    }


    public void setId ( String id ) {
        this.id = id;
    }


    public String getCampoInterfaccia () {
        return campoInterfaccia;
    }


    public void setCampoInterfaccia ( String campoInterfaccia ) {
        this.campoInterfaccia = campoInterfaccia;
    }


    public String getDescrizione () {
        return descrizione;
    }


    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Boolean getFlgAbilitato () {
        return flgAbilitato;
    }

    public void setFlgAbilitato ( Boolean flgAbilitato ) {
        this.flgAbilitato = flgAbilitato;
    }
}
