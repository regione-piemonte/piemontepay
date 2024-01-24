/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_notifica_pagamento database table.
 *
 */
@Entity
@Table ( name = "epaypa_t_notifica_pagamento" )
public class EpaypaTNotificaPagamentoMedium implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id_notifica_pagamento" )
    private Long idNotificaPagamento;

    @Column ( name = "id_flusso" )
    private Long idFlusso;

    @Column ( name = "id_posizione_debitoria" )
    private String idPosizioneDebitoria;

    private String iuv;

    @Column ( name = "importo_pagato" )
    private BigDecimal importoPagato;

    @Column ( name = "des_causale_versamento" )
    private String desCausaleVersamento;

    @Column ( name = "dt_esito_pagamento" )
    private Timestamp dtEsitoPagamento;

	@Column(name="dt_scadenza")
	private Timestamp dtScadenza;

	@Column(name="anno_riferimento")
	private Integer annoRiferimento;
	
	@Column(name="note")
	private String note;
	
    //uni-directional many-to-one association to EpaypaTSoggetto
    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn ( name = "id_soggetto_debitore" )
    private EpaypaTSoggetto epaypaTSoggettoDebitore;

    //bi-directional many-to-one association to EpaypaTFlusso
    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private EpaypaTFlusso epaypaTFlusso;

    @OneToOne ( fetch = FetchType.LAZY, optional = true )
    @JoinColumn ( name = "iuv", referencedColumnName = "iuv", insertable = false, updatable = false )
    private EpaypaTPagamenti pagamento;

    public EpaypaTNotificaPagamentoMedium () {
    }

    public EpaypaTPagamenti getPagamento () {
        return pagamento;
    }

    public void setPagamento ( EpaypaTPagamenti pagamento ) {
        this.pagamento = pagamento;
    }

    public Long getIdNotificaPagamento () {
        return idNotificaPagamento;
    }

    public void setIdNotificaPagamento ( Long idNotificaPagamento ) {
        this.idNotificaPagamento = idNotificaPagamento;
    }

    public String getIdPosizioneDebitoria () {
        return idPosizioneDebitoria;
    }

    public Long getIdFlusso () {
        return idFlusso;
    }

    public void setIdFlusso ( Long idFlusso ) {
        this.idFlusso = idFlusso;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public void setIdPosizioneDebitoria ( String idPosizioneDebitoria ) {
        this.idPosizioneDebitoria = idPosizioneDebitoria;
    }

    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    public String getDesCausaleVersamento () {
        return desCausaleVersamento;
    }

    public void setDesCausaleVersamento ( String desCausaleVersamento ) {
        this.desCausaleVersamento = desCausaleVersamento;
    }

    public Timestamp getDtEsitoPagamento () {
        return dtEsitoPagamento;
    }

    public void setDtEsitoPagamento ( Timestamp dtEsitoPagamento ) {
        this.dtEsitoPagamento = dtEsitoPagamento;
    }

    public Timestamp getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(Timestamp dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public Integer getAnnoRiferimento() {
		return annoRiferimento;
	}

	public void setAnnoRiferimento(Integer annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public EpaypaTSoggetto getEpaypaTSoggettoDebitore () {
        return epaypaTSoggettoDebitore;
    }

    public void setEpaypaTSoggettoDebitore ( EpaypaTSoggetto epaypaTSoggettoDebitore ) {
        this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
    }

    public EpaypaTFlusso getEpaypaTFlusso () {
        return epaypaTFlusso;
    }

    public void setEpaypaTFlusso ( EpaypaTFlusso epaypaTFlusso ) {
        this.epaypaTFlusso = epaypaTFlusso;
    }

}
