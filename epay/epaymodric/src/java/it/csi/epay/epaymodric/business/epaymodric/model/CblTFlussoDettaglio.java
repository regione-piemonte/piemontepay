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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_flusso_dettaglio database table.
 *
 */
@Entity
@Table(name="cbl_t_flusso_dettaglio",schema="epaymodric")
@NamedQuery(name="CblTFlussoDettaglio.findAll", query="SELECT c FROM CblTFlussoDettaglio c")
public class CblTFlussoDettaglio extends DatiTecniciParentEntity implements Serializable {
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CblTFlussoDettaglio other = (CblTFlussoDettaglio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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
    @SequenceGenerator(name="CBL_T_FLUSSO_DETTAGLIO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_FLUSSO_DETTAGLIO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_FLUSSO_DETTAGLIO_ID_GENERATOR")
    private Long id;

    @Column(name="anagrafica_pagatore")
    private String anagraficaPagatore;

    @Column(name="descrizione_causale_versamento")
    private String descrizioneCausaleVersamento;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="codicefiscale_pagatore")
    private String codicefiscalePagatore;

    @Column(name="data_pagamento")
    private Timestamp dataPagamento;

    @Column(name="dati_specifici_di_riscossione")
    private String datiSpecificiDiRiscossione;

    @Column(name="esito_pagamento")
    private String esitoPagamento;

    @Column(name="identificativo_unico_riscossione")
    private String identificativoUnicoRiscossione;

    @Column(name="identificativo_unico_versamento")
    private String identificativoUnicoVersamento;

    @Column(name="importo_singolo_versamento")
    private BigDecimal importoSingoloVersamento;

    @Column(name="indice_singolo_versamento")
    private Integer indiceSingoloVersamento;

    @Column(name="stato_invio_fruitore")
    private String statoInvioFruitore;

    private String transactionid;

    //bi-directional many-to-one association to CblTFlussoSintesi
    @ManyToOne
    @JoinColumn(name="id_flusso_sintesi")
    private CblTFlussoSintesi cblTFlussoSintesi;

    @ManyToOne
    @JoinColumn(name="categoria_iuv", referencedColumnName="id")
    private CblDCategoriaIuv cblDCategoriaIuv;
    
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

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDatiSpecificiDiRiscossione() {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione(String datiSpecificiDiRiscossione) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public String getEsitoPagamento() {
        return esitoPagamento;
    }

    public void setEsitoPagamento(String esitoPagamento) {
        this.esitoPagamento = esitoPagamento;
    }

    public String getIdentificativoUnicoRiscossione() {
        return identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione(String identificativoUnicoRiscossione) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getIdentificativoUnicoVersamento() {
        return identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento(String identificativoUnicoVersamento) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento(BigDecimal importoSingoloVersamento) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public Integer getIndiceSingoloVersamento() {
        return indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento(Integer indiceSingoloVersamento) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    public String getStatoInvioFruitore() {
        return statoInvioFruitore;
    }

    public void setStatoInvioFruitore(String statoInvioFruitore) {
        this.statoInvioFruitore = statoInvioFruitore;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public CblTFlussoSintesi getCblTFlussoSintesi() {
        return cblTFlussoSintesi;
    }

    public void setCblTFlussoSintesi(CblTFlussoSintesi cblTFlussoSintesi) {
        this.cblTFlussoSintesi = cblTFlussoSintesi;
    }

    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    /**
     * @return the cblDCategoriaIuv
     */
    public CblDCategoriaIuv getCblDCategoriaIuv () {
        return cblDCategoriaIuv;
    }

    /**
     * @param cblDCategoriaIuv the cblDCategoriaIuv to set
     */
    public void setCblDCategoriaIuv ( CblDCategoriaIuv cblDCategoriaIuv ) {
        this.cblDCategoriaIuv = cblDCategoriaIuv;
    }

}
