/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaywso_t_applicativo database table.
 * 
 */
@Entity
@Table ( name = "epaywso_t_applicativo" )
@NamedQueries ( {
    @NamedQuery (
                    name = "EPaywsoTApplicativo.findOneById",
                    query = "SELECT e "
                        + "FROM EPaywsoTApplicativo e "
                        + "WHERE e.idApplicativo = :idApplicativo "
                        + "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)" ),
    @NamedQuery (
                    name = "EPaywsoTApplicativo.findAllByIdEnte",
                    query = "SELECT e "
                        + "FROM EPaywsoTApplicativo e "
                        + "WHERE e.ePaywsoTEnte.idEnte = :idEnte "
                        + "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)" ),
    @NamedQuery (
    		name = "EPaywsoTApplicativo.findOneByIdEnteAndCodiceModalitaIntegrazione",
    		query = "SELECT e "
    				+ "FROM EPaywsoTApplicativo e "
    				+ "WHERE e.ePaywsoTEnte.idEnte = :idEnte "
    				+ " AND e.codiceModalitaIntegrazione = :codiceModalitaIntegrazione "
    				+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)" ),
    
    @NamedQuery (
    		name = "EPaywsoTApplicativo.findOneByIdEnteAndDescrizioneLikeAndCodiceModalitaIntegrazione",
    		query = "SELECT e "
    				+ "FROM EPaywsoTApplicativo e "
    				+ "WHERE e.ePaywsoTEnte.idEnte = :idEnte "
                    + " AND e.codiceModalitaIntegrazione = :codiceModalitaIntegrazione "
    				+ " AND e.descrizione like :descrizione "
    				+ " AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)" ),
    @NamedQuery (
                    name = "EPaywsoTApplicativo.findAll",
                    query = "SELECT e FROM EPaywsoTApplicativo e" ),
    
    @NamedQuery(
    	       name="EPaywsoTApplicativo.findIdApplicativoMax",
    	       query = "select u from EPaywsoTApplicativo u order by u.idApplicativo DESC")
} )
public class EPaywsoTApplicativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAYWSO_T_APPLICATIVO_ID_GENERATOR", sequenceName="epaywso.epaywso_t_applicativo_seq" ,schema="epaywso", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYWSO_T_APPLICATIVO_ID_GENERATOR")
    @Column ( name = "id_applicativo" )
    private Integer idApplicativo;

    private String descrizione;

    @Column ( name = "dt_fine_validita" )
    private Timestamp dtFineValidita;

    @Column ( name = "dt_inizio_validita" )
    private Timestamp dtInizioValidita;

    @Column ( name = "ms_inbound" )
    private String msInbound;

    @Column ( name = "ms_outbound" )
    private String msOutbound;

    private String utente;

    @Column ( name = "codice_modalita_integrazione" )
    private String codiceModalitaIntegrazione;

    //uni-directional many-to-one association to EPaywsoTEnte
    @ManyToOne
    @JoinColumn ( name = "id_ente" )
    private EPaywsoTEnte ePaywsoTEnte;

    public EPaywsoTApplicativo () {
    }

    public Integer getIdApplicativo () {
        return this.idApplicativo;
    }

    public void setIdApplicativo ( Integer idApplicativo ) {
        this.idApplicativo = idApplicativo;
    }

    public String getDescrizione () {
        return this.descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Timestamp getDtFineValidita () {
        return this.dtFineValidita;
    }

    public void setDtFineValidita ( Timestamp dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }

    public Timestamp getDtInizioValidita () {
        return this.dtInizioValidita;
    }

    public void setDtInizioValidita ( Timestamp dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
    }

    public String getMsInbound () {
        return this.msInbound;
    }

    public void setMsInbound ( String msInbound ) {
        this.msInbound = msInbound;
    }

    public String getMsOutbound () {
        return this.msOutbound;
    }

    public void setMsOutbound ( String msOutbound ) {
        this.msOutbound = msOutbound;
    }

    public String getUtente () {
        return this.utente;
    }

    public void setUtente ( String utente ) {
        this.utente = utente;
    }

    public EPaywsoTEnte getEPaywsoTEnte () {
        return this.ePaywsoTEnte;
    }

    public void setEPaywsoTEnte ( EPaywsoTEnte ePaywsoTEnte ) {
        this.ePaywsoTEnte = ePaywsoTEnte;
    }

    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    public EPaywsoTEnte getePaywsoTEnte () {
        return ePaywsoTEnte;
    }

    public void setePaywsoTEnte ( EPaywsoTEnte ePaywsoTEnte ) {
        this.ePaywsoTEnte = ePaywsoTEnte;
    }

}
