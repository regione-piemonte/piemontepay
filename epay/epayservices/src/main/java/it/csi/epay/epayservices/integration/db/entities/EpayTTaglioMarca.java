/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_taglio_marca database table.
 *
 */
@Entity
@Table ( name = "epay_t_taglio_marca" )
@NamedQuery ( name = "EpayTTaglioMarca.findAll", query = "SELECT e FROM EpayTTaglioMarca e" )
public class EpayTTaglioMarca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "epay_t_taglio_marca_GENERATOR", allocationSize = 1, sequenceName = "epay_t_taglio_marca_id_taglio_marca_seq" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "epay_t_taglio_marca_GENERATOR" )
    @Column ( name = "id_taglio_marca" )
    private Long idTaglioMarca;

    @Column ( name = "codice_marca" )
    private String codiceMarca;

    private BigDecimal importo;

    private Integer limite;

    @Column ( name = "controllo_hash" )
    private Boolean controlloHash;

    @Column ( name = "lunghezza_hash" )
    private Integer lunghezzaHash;

    public EpayTTaglioMarca () {
    }

    public Long getIdTaglioMarca () {
        return this.idTaglioMarca;
    }

    public void setIdTaglioMarca ( Long idTaglioMarca ) {
        this.idTaglioMarca = idTaglioMarca;
    }

    public String getCodiceMarca () {
        return this.codiceMarca;
    }

    public void setCodiceMarca ( String codiceMarca ) {
        this.codiceMarca = codiceMarca;
    }

    public BigDecimal getImporto () {
        return this.importo;
    }

    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    public Integer getLimite () {
        return this.limite;
    }

    public void setLimite ( Integer limite ) {
        this.limite = limite;
    }

    public Boolean getControlloHash () {
        return controlloHash;
    }

    public void setControlloHash ( Boolean controlloHash ) {
        this.controlloHash = controlloHash;
    }

    public Integer getLunghezzaHash () {
        return lunghezzaHash;
    }

    public void setLunghezzaHash ( Integer lunghezzaHash ) {
        this.lunghezzaHash = lunghezzaHash;
    }

}
