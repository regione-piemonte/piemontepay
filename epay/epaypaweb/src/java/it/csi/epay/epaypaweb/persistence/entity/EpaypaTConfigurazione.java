/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_configurazione database table.
 *
 */
@Entity
@Table ( name = "epaypa_t_configurazione" )
@NamedQueries ( {
    @NamedQuery ( name = "EpaypaTConfigurazione.findAll",
                    query = "SELECT e FROM EpaypaTConfigurazione e" ),
    @NamedQuery (
                    name = "EpaypaTConfigurazione.findConfigurazioneByCodiceAndCodFiscaleEnte",
                    query = "SELECT c "
                        + "FROM EpaypaTConfigurazione c "
                        + "WHERE c.epaypaTEnte.codFiscaleEnte = :codFiscaleEnte "
                        + "AND c.codice = :codice" )
} )

public class EpaypaTConfigurazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id" )
    private Integer id;

    @ManyToOne
    @JoinColumn ( name = "id_ente" )
    private EpaypaTEnte epaypaTEnte;
    
    private String codice;

    private String valore;

    private String descrizione;

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public EpaypaTEnte getEpaypaTEnte () {
        return epaypaTEnte;
    }

    public void setEpaypaTEnte ( EpaypaTEnte epaypaTEnte ) {
        this.epaypaTEnte = epaypaTEnte;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

}
