/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity.cooppec;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table ( name = "epaywso_t_ente_temp", schema = "epaywso" )
@NamedQueries ( {
    @NamedQuery (
                    name = "EPaywsoTEnteTemp.findOneByIdOperazione",
                    query = "SELECT e "
                        + "FROM EPaywsoTEnteTemp e "
                        + "WHERE e.idOperazione = :idOperazione" ),

    @NamedQuery (
                    name = "EPaywsoTEnteTemp.findAll",
                    query = "SELECT e FROM EPaywsoTEnteTemp e" )
} )

public class EPaywsoTEnteTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAYWSO_T_ENTE_TEMP_ID_GENERATOR", sequenceName="epaywso.EPAYWSO_T_ENTE_TEMP_SEQ" ,schema="epaywso", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYWSO_T_ENTE_TEMP_ID_GENERATOR")
    
    @Column ( name = "id_ente" )
    private Integer idEnte;

    @Column ( name = "cod_fiscale_ente" )
    private String codFiscaleEnte;

    private String denominazione;

    @Column(name="dt_fine_validita")
    private Timestamp dtFineValidita;

    @Column(name="dt_inizio_validita")
    private Timestamp dtInizioValidita;

    @Column ( name = "id_operazione" )
    private String idOperazione;

    public EPaywsoTEnteTemp () {
    }

    public Integer getIdEnte () {
        return this.idEnte;
    }

    public void setIdEnte ( Integer idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodFiscaleEnte () {
        return this.codFiscaleEnte;
    }

    public void setCodFiscaleEnte ( String codFiscaleEnte ) {
        this.codFiscaleEnte = codFiscaleEnte;
    }

    public String getDenominazione () {
        return this.denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    
    public Timestamp getDtFineValidita () {
        return dtFineValidita;
    }

    
    public void setDtFineValidita ( Timestamp dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }

    
    public Timestamp getDtInizioValidita () {
        return dtInizioValidita;
    }

    
    public void setDtInizioValidita ( Timestamp dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
    }

}
