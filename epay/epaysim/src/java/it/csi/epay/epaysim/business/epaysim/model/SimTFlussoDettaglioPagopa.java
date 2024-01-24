/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the sim_t_flusso_dettaglio_pagopa database table.
 * 
 */
@Entity
@Table ( name = "sim_t_flusso_dettaglio_pagopa" )
@NamedQuery ( name = "SimTFlussoDettaglioPagopa.findAll", query = "SELECT s FROM SimTFlussoDettaglioPagopa s" )
public class SimTFlussoDettaglioPagopa extends DatiTecniciParentEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "SIM_T_FLUSSO_DETTAGLIO_PAGOPA_ID_GENERATOR", sequenceName = "SIM_T_FLUSSO_DETTAGLIO_PAGOPA_ID_SEQ", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "SIM_T_FLUSSO_DETTAGLIO_PAGOPA_ID_GENERATOR" )
    private Long id;

    @Column ( name = "anagrafica_pagatore" )
    private String anagraficaPagatore;

    private String causale;

    @Column ( name = "data_pagamento" )
    private Timestamp dataPagamento;

    @Column ( name = "descrizione_causale_versamento" )
    private String descrizioneCausaleVersamento;

    @Column ( name = "esito_pagamento" )
    private String esitoPagamento;

    @Column ( name = "identificativo_unico_riscossione" )
    private String identificativoUnicoRiscossione;

    @Column ( name = "identificativo_unico_versamento" )
    private String identificativoUnicoVersamento;

    @Column ( name = "importo_singolo_versamento" )
    private BigDecimal importoSingoloVersamento;

    @Column ( name = "indice_singolo_versamento" )
    private Integer indiceSingoloVersamento;

    private String transactionid;

    //bi-directional many-to-one association to SimTFlussoSintesiPagopa
    @ManyToOne
    @JoinColumn ( name = "id_flusso_sintesi" )
    private SimTFlussoSintesiPagopa simTFlussoSintesiPagopa;

    public SimTFlussoDettaglioPagopa () {
    }

    public SimTFlussoDettaglioPagopa ( Long id, String anagraficaPagatore, String causale, Timestamp dataPagamento, String descrizioneCausaleVersamento,
        String esitoPagamento, String identificativoUnicoRiscossione, String identificativoUnicoVersamento, BigDecimal importoSingoloVersamento,
        Integer indiceSingoloVersamento, String transactionid, SimTFlussoSintesiPagopa simTFlussoSintesiPagopa, Date dataInserimento, Date dataModifica,
        String utenteInserimento, String utenteModifica ) {
        super ();
        this.id = id;
        this.anagraficaPagatore = anagraficaPagatore;
        this.causale = causale;
        this.dataPagamento = dataPagamento;
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        this.esitoPagamento = esitoPagamento;
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
        this.importoSingoloVersamento = importoSingoloVersamento;
        this.indiceSingoloVersamento = indiceSingoloVersamento;
        this.transactionid = transactionid;
        this.simTFlussoSintesiPagopa = simTFlussoSintesiPagopa;
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

    public String getAnagraficaPagatore () {
        return this.anagraficaPagatore;
    }

    public void setAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
    }

    public String getCausale () {
        return this.causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public Timestamp getDataPagamento () {
        return this.dataPagamento;
    }

    public void setDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescrizioneCausaleVersamento () {
        return this.descrizioneCausaleVersamento;
    }

    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    public String getEsitoPagamento () {
        return this.esitoPagamento;
    }

    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    public String getIdentificativoUnicoRiscossione () {
        return this.identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getIdentificativoUnicoVersamento () {
        return this.identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public BigDecimal getImportoSingoloVersamento () {
        return this.importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public Integer getIndiceSingoloVersamento () {
        return this.indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    public String getTransactionid () {
        return this.transactionid;
    }

    public void setTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
    }

    public SimTFlussoSintesiPagopa getSimTFlussoSintesiPagopa () {
        return this.simTFlussoSintesiPagopa;
    }

    public void setSimTFlussoSintesiPagopa ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa ) {
        this.simTFlussoSintesiPagopa = simTFlussoSintesiPagopa;
    }

}
