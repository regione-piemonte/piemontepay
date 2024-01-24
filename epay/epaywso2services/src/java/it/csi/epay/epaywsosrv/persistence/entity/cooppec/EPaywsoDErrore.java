/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity.cooppec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table ( name = "epaywso_d_errore", schema = "epaywso" )

@NamedQueries ( {
    @NamedQuery (
                    name = "EPaywsoDErrore.findAll",
                    query = "SELECT c FROM EPaywsoDErrore c" ),

    @NamedQuery (
                    name = "EPaywsoDErrore.findByCodiceErrore",
                    query = "SELECT e "
                        + "FROM EPaywsoDErrore e "
                        + "WHERE e.codiceErrore = :codiceErrore "
                        + "ORDER BY e.codiceErrore" )
} )

public class EPaywsoDErrore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "EPAYWSO_D_ERRORE_ID_GENERATOR", sequenceName = "epaywso.EPAYWSO_D_ERRORE_ID_SEQ", schema = "epaywso", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAYWSO_D_ERRORE_ID_GENERATOR" )
    private Long id;

    @Column ( name = "codice_errore" )
    private String codiceErrore;

    @Column ( name = "descrizione_errore" )
    private String descrizioneErrore;

    @Column ( name = "flag_mail" )
    private Boolean flagMail;

    @Column ( name = "flag_rielaborazione" )
    private Boolean flagRielaborazione;

    private String tipologia;

    public EPaywsoDErrore () {
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceErrore () {
        return this.codiceErrore;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public String getDescrizioneErrore () {
        return this.descrizioneErrore;
    }

    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public Boolean getFlagMail () {
        return this.flagMail;
    }

    public void setFlagMail ( Boolean flagMail ) {
        this.flagMail = flagMail;
    }

    public Boolean getFlagRielaborazione () {
        return this.flagRielaborazione;
    }

    public void setFlagRielaborazione ( Boolean flagRielaborazione ) {
        this.flagRielaborazione = flagRielaborazione;
    }

    public String getTipologia () {
        return this.tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

}
