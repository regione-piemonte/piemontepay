/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 * The persistent class for the sim_t_flusso_sintesi_pagopa database table.
 * 
 */
@Entity
@Table ( name = "sim_t_flusso_sintesi_pagopa" )
@NamedQuery ( name = "SimTFlussoSintesiPagopa.findAll", query = "SELECT s FROM SimTFlussoSintesiPagopa s" )
public class SimTFlussoSintesiPagopa extends DatiTecniciParentEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "SIM_T_FLUSSO_SINTESI_PAGOPA_ID_GENERATOR", sequenceName = "SIM_T_FLUSSO_SINTESI_PAGOPA_ID_SEQ", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "SIM_T_FLUSSO_SINTESI_PAGOPA_ID_GENERATOR" )
    private Long id;

    @Column ( name = "accertamento_anno" )
    private Integer accertamentoAnno;

    @Column ( name = "accertamento_numero" )
    private Integer accertamentoNumero;

    private BigDecimal articolo;

    private String capitolo;

    @Column ( name = "codice_versamento" )
    private String codiceVersamento;

    @Column ( name = "dati_specifici_di_riscossione" )
    private String datiSpecificiDiRiscossione;

    @Column ( name = "descrizione_codice_versamento" )
    private String descrizioneCodiceVersamento;

    @Column ( name = "descrizione_dati_specifici" )
    private String descrizioneDatiSpecifici;

    @Column ( name = "importo_quota_aggregazione" )
    private BigDecimal importoQuotaAggregazione;

    private String macrotipo;

    @Column ( name = "numero_pagamenti_aggregazione" )
    private BigDecimal numeroPagamentiAggregazione;

    private String pdc;

    @Column ( name = "progressivo_flusso_sintetico" )
    private Integer progressivoFlussoSintetico;

    private String tematica;

    //bi-directional many-to-one association to SimTFlussoDettaglioPagopa
    @OneToMany ( mappedBy = "simTFlussoSintesiPagopa", cascade = CascadeType.ALL )
    @LazyCollection ( LazyCollectionOption.FALSE )
    private List<SimTFlussoDettaglioPagopa> simTFlussoDettaglioPagopas;

    //bi-directional many-to-one association to SimTFlussoOriginePagopa
    @ManyToOne
    @JoinColumn ( name = "id_flusso_origine" )
    private SimTFlussoOriginePagopa simTFlussoOriginePagopa;

    public SimTFlussoSintesiPagopa () {
    }

    public SimTFlussoSintesiPagopa ( Long id, Integer accertamentoAnno, Integer accertamentoNumero, BigDecimal articolo, String capitolo,
        String codiceVersamento, String datiSpecificiDiRiscossione, String descrizioneCodiceVersamento, String descrizioneDatiSpecifici,
        BigDecimal importoQuotaAggregazione, String macrotipo, BigDecimal numeroPagamentiAggregazione, String pdc,
        Integer progressivoFlussoSintetico, String tematica, List<SimTFlussoDettaglioPagopa> simTFlussoDettaglioPagopas,
        SimTFlussoOriginePagopa simTFlussoOriginePagopa, Date dataInserimento, Date dataModifica,
        String utenteInserimento, String utenteModifica ) {
        super ();
        this.id = id;
        this.accertamentoAnno = accertamentoAnno;
        this.accertamentoNumero = accertamentoNumero;
        this.articolo = articolo;
        this.capitolo = capitolo;
        this.codiceVersamento = codiceVersamento;
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
        this.importoQuotaAggregazione = importoQuotaAggregazione;
        this.macrotipo = macrotipo;
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
        this.pdc = pdc;
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
        this.tematica = tematica;
        this.simTFlussoDettaglioPagopas = simTFlussoDettaglioPagopas;
        this.simTFlussoOriginePagopa = simTFlussoOriginePagopa;
        setDataInserimento ( dataInserimento );
        setDataModifica ( dataModifica );
        setUtenteInserimento ( utenteInserimento );
        setUtenteModifica ( utenteModifica );
    }

    @Override
    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAccertamentoAnno () {
        return this.accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNumero () {
        return this.accertamentoNumero;
    }

    public void setAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
    }

    public BigDecimal getArticolo () {
        return this.articolo;
    }

    public void setArticolo ( BigDecimal articolo ) {
        this.articolo = articolo;
    }

    public String getCapitolo () {
        return this.capitolo;
    }

    public void setCapitolo ( String capitolo ) {
        this.capitolo = capitolo;
    }

    public String getCodiceVersamento () {
        return this.codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return this.datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public String getDescrizioneCodiceVersamento () {
        return this.descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public String getDescrizioneDatiSpecifici () {
        return this.descrizioneDatiSpecifici;
    }

    public void setDescrizioneDatiSpecifici ( String descrizioneDatiSpecifici ) {
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
    }

    public BigDecimal getImportoQuotaAggregazione () {
        return this.importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione ( BigDecimal importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public String getMacrotipo () {
        return this.macrotipo;
    }

    public void setMacrotipo ( String macrotipo ) {
        this.macrotipo = macrotipo;
    }

    public BigDecimal getNumeroPagamentiAggregazione () {
        return this.numeroPagamentiAggregazione;
    }

    public void setNumeroPagamentiAggregazione ( BigDecimal numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
    }

    public String getPdc () {
        return this.pdc;
    }

    public void setPdc ( String pdc ) {
        this.pdc = pdc;
    }

    public Integer getProgressivoFlussoSintetico () {
        return this.progressivoFlussoSintetico;
    }

    public void setProgressivoFlussoSintetico ( Integer progressivoFlussoSintetico ) {
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
    }

    public String getTematica () {
        return this.tematica;
    }

    public void setTematica ( String tematica ) {
        this.tematica = tematica;
    }

    public List<SimTFlussoDettaglioPagopa> getSimTFlussoDettaglioPagopas () {
        return this.simTFlussoDettaglioPagopas;
    }

    public void setSimTFlussoDettaglioPagopas ( List<SimTFlussoDettaglioPagopa> simTFlussoDettaglioPagopas ) {
        this.simTFlussoDettaglioPagopas = simTFlussoDettaglioPagopas;
    }

    public SimTFlussoDettaglioPagopa addSimTFlussoDettaglioPagopa ( SimTFlussoDettaglioPagopa simTFlussoDettaglioPagopa ) {
        getSimTFlussoDettaglioPagopas ().add ( simTFlussoDettaglioPagopa );
        simTFlussoDettaglioPagopa.setSimTFlussoSintesiPagopa ( this );

        return simTFlussoDettaglioPagopa;
    }

    public SimTFlussoDettaglioPagopa removeSimTFlussoDettaglioPagopa ( SimTFlussoDettaglioPagopa simTFlussoDettaglioPagopa ) {
        getSimTFlussoDettaglioPagopas ().remove ( simTFlussoDettaglioPagopa );
        simTFlussoDettaglioPagopa.setSimTFlussoSintesiPagopa ( null );

        return simTFlussoDettaglioPagopa;
    }

    public SimTFlussoOriginePagopa getSimTFlussoOriginePagopa () {
        return this.simTFlussoOriginePagopa;
    }

    public void setSimTFlussoOriginePagopa ( SimTFlussoOriginePagopa simTFlussoOriginePagopa ) {
        this.simTFlussoOriginePagopa = simTFlussoOriginePagopa;
    }

}
