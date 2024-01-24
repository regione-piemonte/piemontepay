/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaypa_t_report database table.
 *
 */
@Entity
@Table ( name = "epaypa_t_report" )
@NamedQueries ( {
    @NamedQuery ( name = "EpaypaTReport.findAll", query = "SELECT e FROM EpaypaTReport e" ),
    @NamedQuery ( name = "EpaypaTReport.findAllByIdEnteAndIdUtente",
    query = "SELECT e FROM EpaypaTReport e WHERE e.idEnte = :idEnte AND e.idUtente = :idUtente" ),
    @NamedQuery ( name = "EpaypaTReport.findByStatusCode",
    query = "SELECT e FROM EpaypaTReport e WHERE e.epaypaDStatoReport.codice = :codiceStato ORDER BY e.dataInserimento"),

    @NamedQuery ( name = "EpaypaTReport.findAllByIdEnteAndIdUtenteAndCodiceTipoReport",
    query = "SELECT e FROM EpaypaTReport e WHERE e.idEnte = :idEnte AND e.idUtente = :idUtente AND e.epaypaDTipoReport.codice = :codiceTipoReport" ),

    @NamedQuery ( name = "EpaypaTReport.findByStateCodesAndDataModificaBefore",
    query = "SELECT e FROM EpaypaTReport e WHERE e.epaypaDStatoReport.codice in :stateCodesList AND e.dataModifica < :thresholdDate")
} )
public class EpaypaTReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "EPAYPA_T_REPORT_ID_GENERATOR", sequenceName = "EPAYPA_T_REPORT_ID_SEQ", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAYPA_T_REPORT_ID_GENERATOR" )
    private Long id;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data_ora_inserimento" )
    private Date dataInserimento;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data_ora_modifica" )
    private Date dataModifica;

    @Column ( name = "id_ente" )
    private Integer idEnte;

    @Column ( name = "id_utente" )
    private Long idUtente;

    @Column ( name = "nominativo_export" )
    private String nominativoExport;

    @Column ( name = "codice_fiscale_ente" )
    private String codiceFiscaleEnte;

    @Column ( name = "codice_fiscale_utente" )
    private String codiceFiscaleUtente;

    //bi-directional many-to-one association to EpaypaDStatoReport
    @ManyToOne
    @JoinColumn ( name = "id_stato" )
    private EpaypaDStatoReport epaypaDStatoReport;

    //bi-directional many-to-one association to EpaypaDTipoFileReport
    @ManyToOne
    @JoinColumn ( name = "id_tipo_file" )
    private EpaypaDTipoFileReport epaypaDTipoFileReport;

    //bi-directional many-to-one association to EpaypaTFileReport
    @ManyToOne
    @JoinColumn ( name = "id_file" )
    private EpaypaTFileReport epaypaTFileReport;

    //bi-directional many-to-one association to EpaypaDTipoReport
    @ManyToOne
    @JoinColumn ( name = "id_tipo_report" )
    private EpaypaDTipoReport epaypaDTipoReport;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="epaypaTReport", cascade={CascadeType.ALL})
    private List<EpaypaTDatiFiltroReport> epaypaTDatiFiltroReports;

    /**
     *
     */
    public EpaypaTReport () {
        super ();
    }

    @Generated ( "SparkTools" )
    private EpaypaTReport ( Builder builder ) {
        this.id = builder.id;
        this.dataInserimento = builder.dataInserimento;
        this.dataModifica = builder.dataModifica;
        this.idEnte = builder.idEnte;
        this.idUtente = builder.idUtente;
        this.nominativoExport = builder.nominativoExport;
        this.codiceFiscaleEnte = builder.codiceFiscaleEnte;
        this.codiceFiscaleUtente = builder.codiceFiscaleUtente;
        this.epaypaDStatoReport = builder.epaypaDStatoReport;
        this.epaypaDTipoFileReport = builder.epaypaDTipoFileReport;
        this.epaypaTFileReport = builder.epaypaTFileReport;
        this.epaypaDTipoReport = builder.epaypaDTipoReport;
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Date getDataInserimento () {
        return this.dataInserimento;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataModifica () {
        return this.dataModifica;
    }

    public void setDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public Integer getIdEnte () {
        return this.idEnte;
    }

    public void setIdEnte ( Integer idEnte ) {
        this.idEnte = idEnte;
    }

    public Long getIdUtente () {
        return this.idUtente;
    }

    public void setIdUtente ( Long idUtente ) {
        this.idUtente = idUtente;
    }

    public String getNominativoExport () {
        return this.nominativoExport;
    }

    public void setNominativoExport ( String nominativoExport ) {
        this.nominativoExport = nominativoExport;
    }

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getCodiceFiscaleUtente () {
        return codiceFiscaleUtente;
    }

    public void setCodiceFiscaleUtente ( String codiceFiscaleUtente ) {
        this.codiceFiscaleUtente = codiceFiscaleUtente;
    }

    public EpaypaDStatoReport getEpaypaDStatoReport () {
        return this.epaypaDStatoReport;
    }

    public void setEpaypaDStatoReport ( EpaypaDStatoReport epaypaDStatoReport ) {
        this.epaypaDStatoReport = epaypaDStatoReport;
    }

    public EpaypaDTipoFileReport getEpaypaDTipoFileReport () {
        return this.epaypaDTipoFileReport;
    }

    public void setEpaypaDTipoFileReport ( EpaypaDTipoFileReport epaypaDTipoFileReport ) {
        this.epaypaDTipoFileReport = epaypaDTipoFileReport;
    }

    public EpaypaTFileReport getEpaypaTFileReport () {
        return this.epaypaTFileReport;
    }

    public void setEpaypaTFileReport ( EpaypaTFileReport epaypaTFileReport ) {
        this.epaypaTFileReport = epaypaTFileReport;
    }

    public EpaypaDTipoReport getEpaypaDTipoReport() {
        return epaypaDTipoReport;
    }

    public void setEpaypaDTipoReport(EpaypaDTipoReport epaypaDTipoReport) {
        this.epaypaDTipoReport = epaypaDTipoReport;
    }

    public List<EpaypaTDatiFiltroReport> getEpaypaTDatiFiltroReports() {
        return epaypaTDatiFiltroReports;
    }

    public void setEpaypaTDatiFiltroReports(List<EpaypaTDatiFiltroReport> epaypaTDatiFiltroReports) {
        this.epaypaTDatiFiltroReports = epaypaTDatiFiltroReports;
    }

    /**
     * Creates builder to build {@link EpaypaTReport}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link EpaypaTReport}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private Date dataInserimento;

        private Date dataModifica;

        private Integer idEnte;

        private Long idUtente;

        private String nominativoExport;

        private String codiceFiscaleEnte;

        private String codiceFiscaleUtente;

        private EpaypaDStatoReport epaypaDStatoReport;

        private EpaypaDTipoFileReport epaypaDTipoFileReport;

        private EpaypaTFileReport epaypaTFileReport;

        private EpaypaDTipoReport epaypaDTipoReport;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withDataInserimento ( Date dataInserimento ) {
            this.dataInserimento = dataInserimento;
            return this;
        }

        public Builder withDataModifica ( Date dataModifica ) {
            this.dataModifica = dataModifica;
            return this;
        }

        public Builder withIdEnte ( Integer idEnte ) {
            this.idEnte = idEnte;
            return this;
        }

        public Builder withIdUtente ( Long idUtente ) {
            this.idUtente = idUtente;
            return this;
        }

        public Builder withNominativoExport ( String nominativoExport ) {
            this.nominativoExport = nominativoExport;
            return this;
        }

        public Builder withCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
            this.codiceFiscaleEnte = codiceFiscaleEnte;
            return this;
        }

        public Builder withCodiceFiscaleUtente ( String codiceFiscaleUtente ) {
            this.codiceFiscaleUtente = codiceFiscaleUtente;
            return this;
        }

        public Builder withEpaypaDStatoReport ( EpaypaDStatoReport epaypaDStatoReport ) {
            this.epaypaDStatoReport = epaypaDStatoReport;
            return this;
        }

        public Builder withEpaypaDTipoFileReport ( EpaypaDTipoFileReport epaypaDTipoFileReport ) {
            this.epaypaDTipoFileReport = epaypaDTipoFileReport;
            return this;
        }

        public Builder withEpaypaTFileReport ( EpaypaTFileReport epaypaTFileReport ) {
            this.epaypaTFileReport = epaypaTFileReport;
            return this;
        }

        public Builder withEpaypaDTipoReport(EpaypaDTipoReport epaypaDTipoReport) {
            this.epaypaDTipoReport = epaypaDTipoReport;
            return this;
        }

        public EpaypaTReport build () {
            return new EpaypaTReport ( this );
        }
    }

}
