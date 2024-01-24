/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_provvisorio database table.
 *
 */
@Entity
@Table(name="cbl_t_provvisorio",schema="epaymodric")
@NamedQuery(name="CblTProvvisorio.findAll", query="SELECT c FROM CblTProvvisorio c")
public class CblTProvvisorio extends DatiTecniciParentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    @Id
    @SequenceGenerator(name="CBL_T_PROVVISORIO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_PROVVISORIO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_PROVVISORIO_ID_GENERATOR")
    private Long id;

    @Column(name="anno_esercizio")
    private Integer annoEsercizio;

    @Column(name="anno_provvisorio")
    private Integer annoProvvisorio;

    private String causale;

    @Column(name="data_fine")
    private Timestamp dataFine;

    @Column(name="data_movimento")
    private Timestamp dataMovimento;

    private String descrizione;

    @Column(name="id_ente")
    private String idEnte;

    @Column(name="identificativo_flusso")
    private String identificativoFlusso;

    @Column(name="importo_disponibilita")
    private BigDecimal importoDisponibilita;

    @Column(name="importo_provvisorio")
    private BigDecimal importoProvvisorio;

    @Column(name="numero_provvisorio")
    private Integer numeroProvvisorio;

    private String stato;

    @Column(name="tipo_movimento")
    private String tipoMovimento;

    public CblTProvvisorio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    public void setAnnoEsercizio(Integer annoEsercizio) {
        this.annoEsercizio = annoEsercizio;
    }

    public Integer getAnnoProvvisorio() {
        return annoProvvisorio;
    }

    public void setAnnoProvvisorio(Integer annoProvvisorio) {
        this.annoProvvisorio = annoProvvisorio;
    }

    public String getCausale() {
        return causale;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public Timestamp getDataFine() {
        return dataFine;
    }

    public void setDataFine(Timestamp dataFine) {
        this.dataFine = dataFine;
    }

    public Timestamp getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Timestamp dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIdEnte() {
        return idEnte;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso(String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public BigDecimal getImportoDisponibilita() {
        return importoDisponibilita;
    }

    public void setImportoDisponibilita(BigDecimal importoDisponibilita) {
        this.importoDisponibilita = importoDisponibilita;
    }

    public BigDecimal getImportoProvvisorio() {
        return importoProvvisorio;
    }

    public void setImportoProvvisorio(BigDecimal importoProvvisorio) {
        this.importoProvvisorio = importoProvvisorio;
    }

    public Integer getNumeroProvvisorio() {
        return numeroProvvisorio;
    }

    public void setNumeroProvvisorio(Integer numeroProvvisorio) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

}
