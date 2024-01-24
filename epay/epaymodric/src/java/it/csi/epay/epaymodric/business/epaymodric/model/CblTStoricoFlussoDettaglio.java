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
 * The persistent class for the cbl_t_storico_flusso_dettaglio database table.
 *
 */
@Entity
@Table(name="cbl_t_storico_flusso_dettaglio",schema="epaymodric")
@NamedQuery(name="CblTStoricoFlussoDettaglio.findAll", query="SELECT c FROM CblTStoricoFlussoDettaglio c")
public class CblTStoricoFlussoDettaglio extends DatiTecniciParentEntity implements Serializable {
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
    @SequenceGenerator(name="CBL_T_STORICO_FLUSSO_DETTAGLIO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_STORICO_FLUSSO_DETTAGLIO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_STORICO_FLUSSO_DETTAGLIO_ID_GENERATOR")
    private Long id;

    @Column(name="anagrafica_pagatore")
    private String anagraficaPagatore;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="codicefiscale_pagatore")
    private String codicefiscalePagatore;

    @Column ( name = "data_pagamento" )
    private Timestamp dataPagamento;

    @Column ( name = "dati_specifici_di_riscossione" )
    private String datiSpecificiDiRiscossione;

    @Column ( name = "descrizione_causale_versamento" )
    private String descrizioneCausaleVersamento;

    @Column ( name = "esito_pagamento" )
    private String esitoPagamento;

    @Column ( name = "id_flusso_sintesi" )
    private Long idFlussoSintesi;

    @Column ( name = "identificativo_unico_riscossione" )
    private String identificativoUnicoRiscossione;

    @Column ( name = "identificativo_unico_versamento" )
    private String identificativoUnicoVersamento;

    @Column(name="importo_singolo_versamento")
    private BigDecimal importoSingoloVersamento;

    @Column ( name = "indice_singolo_versamento" )
    private Integer indiceSingoloVersamento;

    @Column ( name = "stato_invio_fruitore" )
    private String statoInvioFruitore;

    private String transactionid;

    public CblTStoricoFlussoDettaglio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnagraficaPagatore() {
        return anagraficaPagatore;
    }

    public void setAnagraficaPagatore(String anagraficaPagatore) {
        this.anagraficaPagatore = anagraficaPagatore;
    }

    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getCodicefiscalePagatore() {
        return codicefiscalePagatore;
    }

    public void setCodicefiscalePagatore(String codicefiscalePagatore) {
        this.codicefiscalePagatore = codicefiscalePagatore;
    }

    @Override
    public Timestamp getDataInserimento () {
        return dataInserimento;
    }

    @Override
    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    @Override
    public Timestamp getDataModifica () {
        return dataModifica;
    }

    @Override
    public void setDataModifica ( Timestamp dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public Timestamp getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    public String getEsitoPagamento () {
        return esitoPagamento;
    }

    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    public Long getIdFlussoSintesi () {
        return idFlussoSintesi;
    }

    public void setIdFlussoSintesi ( Long idFlussoSintesi ) {
        this.idFlussoSintesi = idFlussoSintesi;
    }

    public String getIdentificativoUnicoRiscossione () {
        return identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getIdentificativoUnicoVersamento () {
        return identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento(BigDecimal importoSingoloVersamento) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public Integer getIndiceSingoloVersamento () {
        return indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    public String getStatoInvioFruitore () {
        return statoInvioFruitore;
    }

    public void setStatoInvioFruitore ( String statoInvioFruitore ) {
        this.statoInvioFruitore = statoInvioFruitore;
    }

    public String getTransactionid () {
        return transactionid;
    }

    public void setTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
    }

    @Override
    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    @Override
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    @Override
    public String getUtenteModifica() {
        return utenteModifica;
    }

    @Override
    public void setUtenteModifica(String utenteModifica) {
        this.utenteModifica = utenteModifica;
    }


}
