/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sim_t_provvisorio database table.
 * 
 */
@Entity
@Table(name="sim_t_provvisorio")
@NamedQuery(name="SimTProvvisorio.findAll", query="SELECT s FROM SimTProvvisorio s")
public class SimTProvvisorio extends DatiTecniciParentEntity<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "SIM_T_PROVVISORIO_ID_GENERATOR", sequenceName = "SIM_T_PROVVISORIO_ID_SEQ", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIM_T_PROVVISORIO_ID_GENERATOR")
    private Integer id;

    @Column(name="anno_esercizio")
    private Integer annoEsercizio;

    @Column(name="anno_provvisorio")
    private Integer annoProvvisorio;

    private String causale;

    @Column(name="codice_fiscale_ente")
    private String codiceFiscaleEnte;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column(name="data_movimento")
    private Date dataMovimento;

    @Column(name="identificativo_flusso")
    private String identificativoFlusso;

    @Column(name="importo_provvisorio")
    private BigDecimal importoProvvisorio;

    @Column(name="numero_provvisorio")
    private Integer numeroProvvisorio;

    private String stato;

    //bi-directional many-to-one association to SimTGiornaleDiCassa
    @ManyToOne ( fetch = FetchType.LAZY ) //Viene usato lazy per perfomance visto che il giornale di cassa possiede un campo di tipo xml.
    @JoinColumn(name="id_giornale_di_cassa")
    private SimTGiornaleDiCassa simTGiornaleDiCassa;

    public SimTProvvisorio() {
    }

    public SimTProvvisorio ( Integer id, Integer annoEsercizio, Integer annoProvvisorio, String causale, String codiceFiscaleEnte, Date dataInserimento,
        Date dataModifica, Date dataMovimento, String identificativoFlusso, BigDecimal importoProvvisorio, Integer numeroProvvisorio, String stato,
        String utenteInserimento, String utenteModifica, SimTGiornaleDiCassa simTGiornaleDiCassa ) {
        super ();
        this.id = id;
        this.annoEsercizio = annoEsercizio;
        this.annoProvvisorio = annoProvvisorio;
        this.causale = causale;
        this.codiceFiscaleEnte = codiceFiscaleEnte;
        this.setDataInserimento ( dataInserimento );
        this.setDataModifica ( dataModifica );
        this.dataMovimento = dataMovimento;
        this.identificativoFlusso = identificativoFlusso;
        this.importoProvvisorio = importoProvvisorio;
        this.numeroProvvisorio = numeroProvvisorio;
        this.stato = stato;
        this.setUtenteInserimento ( utenteInserimento );
        this.setUtenteModifica ( utenteModifica );
        this.simTGiornaleDiCassa = simTGiornaleDiCassa;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnoEsercizio() {
        return this.annoEsercizio;
    }

    public void setAnnoEsercizio(Integer annoEsercizio) {
        this.annoEsercizio = annoEsercizio;
    }

    public Integer getAnnoProvvisorio() {
        return this.annoProvvisorio;
    }

    public void setAnnoProvvisorio(Integer annoProvvisorio) {
        this.annoProvvisorio = annoProvvisorio;
    }

    public String getCausale() {
        return this.causale;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public String getCodiceFiscaleEnte() {
        return this.codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public Date getDataMovimento () {
        return this.dataMovimento;
    }

    public void setDataMovimento ( Date dataMovimento ) {
        this.dataMovimento = dataMovimento;
    }

    public String getIdentificativoFlusso() {
        return this.identificativoFlusso;
    }

    public void setIdentificativoFlusso(String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public BigDecimal getImportoProvvisorio() {
        return this.importoProvvisorio;
    }

    public void setImportoProvvisorio(BigDecimal importoProvvisorio) {
        this.importoProvvisorio = importoProvvisorio;
    }

    public Integer getNumeroProvvisorio() {
        return this.numeroProvvisorio;
    }

    public void setNumeroProvvisorio(Integer numeroProvvisorio) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    public String getStato() {
        return this.stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public SimTGiornaleDiCassa getSimTGiornaleDiCassa() {
        return this.simTGiornaleDiCassa;
    }

    public void setSimTGiornaleDiCassa(SimTGiornaleDiCassa simTGiornaleDiCassa) {
        this.simTGiornaleDiCassa = simTGiornaleDiCassa;
    }

}
