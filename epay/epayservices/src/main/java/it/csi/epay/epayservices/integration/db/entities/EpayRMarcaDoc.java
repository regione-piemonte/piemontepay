/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_r_marca_doc database table.
 *
 */
@Entity
@Table ( name = "epay_r_marca_doc" )
public class EpayRMarcaDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "epay_r_marca_doc_GENERATOR", allocationSize = 1, sequenceName = "epay_r_marca_doc_id_marca_doc_seq" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "epay_r_marca_doc_GENERATOR" )
    @Column ( name = "id_marca_doc" )
    private Long idMarcaDoc;

    @Column ( name = "iuv_istanza" )
    private String iuvIstanza;

    @Column ( name = "iuv_marca" )
    private String iuvMarca;

    public EpayRMarcaDoc () {
    }

    public Long getIdMarcaDoc () {
        return this.idMarcaDoc;
    }

    public void setIdMarcaDoc ( Long idMarcaDoc ) {
        this.idMarcaDoc = idMarcaDoc;
    }

    public String getIuvIstanza () {
        return this.iuvIstanza;
    }

    public void setIuvIstanza ( String iuvIstanza ) {
        this.iuvIstanza = iuvIstanza;
    }

    public String getIuvMarca () {
        return this.iuvMarca;
    }

    public void setIuvMarca ( String iuvMarca ) {
        this.iuvMarca = iuvMarca;
    }

}
