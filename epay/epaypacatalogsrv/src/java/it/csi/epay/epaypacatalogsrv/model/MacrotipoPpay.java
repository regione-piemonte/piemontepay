/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_d_macrotipo_ppay database table.
 *
 */
@Entity
@Table(name="epaycat_d_macrotipo_ppay")
//CSI_PAG_AM-5380 aggiunto ordinamento decrescente.
@NamedQuery ( name = "MacrotipoPpay.findAll", query = "SELECT m FROM MacrotipoPpay m order by codice desc" )
public class MacrotipoPpay extends AbstractCSILogAuditedParentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    private Integer id;

    private String codice;

    private String descrizione;

    public MacrotipoPpay() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

	@Override
	public String toString() {
		return "MacrotipoPpay [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
