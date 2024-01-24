/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sim_t_flusso_origine_errore database table.
 * 
 */
@Entity
@Table(name="sim_t_flusso_origine_errore")
@NamedQuery(name="SimTFlussoOrigineErrore.findAll", query="SELECT s FROM SimTFlussoOrigineErrore s")
public class SimTFlussoOrigineErrore extends DatiTecniciParentEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "SIM_T_FLUSSO_ORIGINE_ERRORE_ID_GENERATOR", sequenceName = "SIM_T_FLUSSO_ORIGINE_ERRORE_ID_SEQ", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIM_T_FLUSSO_ORIGINE_ERRORE_ID_GENERATOR")
    private Long id;

    @Column(name="codice_errore")
    private String codiceErrore;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="contatore_tentativi")
    private Integer contatoreTentativi;

    @Temporal(TemporalType.DATE)
    @Column(name="data_regolamento")
    private Date dataRegolamento;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column(name="dataora_flusso")
    private Date dataoraFlusso;

    @Column(name="descrizione_errore")
    private String descrizioneErrore;

    @Column(name="iban_riversamento_flusso")
    private String ibanRiversamentoFlusso;

    @Column(name="id_elaborazione")
    private Long idElaborazione;

    @Column ( name = "cf_ente_ricevente" )
    private String cfEnteRicevente;

    @Column(name="id_messaggio")
    private String idMessaggio;

    @Column(name="id_stato_flusso")
    private Long idStatoFlusso;

    @Column(name="identificativo_flusso")
    private String identificativoFlusso;

    @Column(name="identificativo_psp")
    private String identificativoPsp;

    @Column(name="identificativo_univoco_regolamento")
    private String identificativoUnivocoRegolamento;

    @Column(name="importo_totale_pagamenti")
    private BigDecimal importoTotalePagamenti;

    @Column(name="importo_totale_pagamenti_intermediati")
    private BigDecimal importoTotalePagamentiIntermediati;

    @Column(name="numero_totale_pagamenti")
    private Integer numeroTotalePagamenti;

    @Column(name="numero_totale_pagamenti_intermediati")
    private Integer numeroTotalePagamentiIntermediati;

    @Column(name="xml_flusso")
    private String xmlFlusso;

    public SimTFlussoOrigineErrore() {
    }

    @Override
    public Long getId () {
        return this.id;
    }

    public SimTFlussoOrigineErrore ( Long id, String codiceErrore, String codiceVersamento, Integer contatoreTentativi, Date dataInserimento, Date dataModifica,
        Date dataRegolamento, Date dataoraFlusso, String descrizioneErrore, String ibanRiversamentoFlusso, Long idElaborazione, String cfEnteRicevente,
        String idMessaggio, Long idStatoFlusso, String identificativoFlusso, String identificativoPsp, String identificativoUnivocoRegolamento,
        BigDecimal importoTotalePagamenti, BigDecimal importoTotalePagamentiIntermediati, Integer numeroTotalePagamenti,
        Integer numeroTotalePagamentiIntermediati, String utenteInserimento, String utenteModifica, String xmlFlusso ) {
        super ();
        this.id = id;
        this.codiceErrore = codiceErrore;
        this.codiceVersamento = codiceVersamento;
        this.contatoreTentativi = contatoreTentativi;
        this.setDataInserimento ( dataInserimento );
        this.setDataModifica ( dataModifica );
        this.dataRegolamento = dataRegolamento;
        this.dataoraFlusso = dataoraFlusso;
        this.descrizioneErrore = descrizioneErrore;
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
        this.idElaborazione = idElaborazione;
        this.cfEnteRicevente = cfEnteRicevente;
        this.idMessaggio = idMessaggio;
        this.idStatoFlusso = idStatoFlusso;
        this.identificativoFlusso = identificativoFlusso;
        this.identificativoPsp = identificativoPsp;
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        this.importoTotalePagamenti = importoTotalePagamenti;
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        this.setUtenteInserimento ( utenteInserimento );
        this.setUtenteModifica ( utenteModifica );
        this.xmlFlusso = xmlFlusso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceErrore() {
        return this.codiceErrore;
    }

    public void setCodiceErrore(String codiceErrore) {
        this.codiceErrore = codiceErrore;
    }

    public String getCodiceVersamento() {
        return this.codiceVersamento;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    public Integer getContatoreTentativi() {
        return this.contatoreTentativi;
    }

    public void setContatoreTentativi(Integer contatoreTentativi) {
        this.contatoreTentativi = contatoreTentativi;
    }

    public Date getDataRegolamento() {
        return this.dataRegolamento;
    }

    public void setDataRegolamento(Date dataRegolamento) {
        this.dataRegolamento = dataRegolamento;
    }

    public Date getDataoraFlusso () {
        return this.dataoraFlusso;
    }

    public void setDataoraFlusso ( Date dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public String getDescrizioneErrore() {
        return this.descrizioneErrore;
    }

    public void setDescrizioneErrore(String descrizioneErrore) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public String getIbanRiversamentoFlusso() {
        return this.ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso(String ibanRiversamentoFlusso) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public Long getIdElaborazione() {
        return this.idElaborazione;
    }

    public void setIdElaborazione(Long idElaborazione) {
        this.idElaborazione = idElaborazione;
    }

    public String getCfEnteRicevente () {
        return this.cfEnteRicevente;
    }

    public void setCfEnteRicevente ( String cfEnteRicevente ) {
        this.cfEnteRicevente = cfEnteRicevente;
    }

    public String getIdMessaggio() {
        return this.idMessaggio;
    }

    public void setIdMessaggio(String idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public Long getIdStatoFlusso() {
        return this.idStatoFlusso;
    }

    public void setIdStatoFlusso(Long idStatoFlusso) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getIdentificativoFlusso() {
        return this.identificativoFlusso;
    }

    public void setIdentificativoFlusso(String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoPsp() {
        return this.identificativoPsp;
    }

    public void setIdentificativoPsp(String identificativoPsp) {
        this.identificativoPsp = identificativoPsp;
    }

    public String getIdentificativoUnivocoRegolamento() {
        return this.identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento(String identificativoUnivocoRegolamento) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public BigDecimal getImportoTotalePagamenti() {
        return this.importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti(BigDecimal importoTotalePagamenti) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati() {
        return this.importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati(BigDecimal importoTotalePagamentiIntermediati) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamenti() {
        return this.numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti(Integer numeroTotalePagamenti) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public Integer getNumeroTotalePagamentiIntermediati() {
        return this.numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati(Integer numeroTotalePagamentiIntermediati) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public String getXmlFlusso() {
        return this.xmlFlusso;
    }

    public void setXmlFlusso(String xmlFlusso) {
        this.xmlFlusso = xmlFlusso;
    }

}
