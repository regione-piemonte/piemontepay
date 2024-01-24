/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the epaypa_t_notifica_pagamento database table.
 * 
 */
@Entity
@Table(name="epaypa_t_notifica_pagamento")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTNotificaPagamento.findAllByIdFlusso",
			query = "SELECT e FROM EpaypaTNotificaPagamento e WHERE epaypaTFlusso.idFlusso = :idFlusso"),
	@NamedQuery(
			name = "EpaypaTNotificaPagamento.findAll",
			query = "SELECT e FROM EpaypaTNotificaPagamento e"),
	@NamedQuery(//<!-- CSI_PAG-184 -->
	        name = "EpaypaTNotificaPagamento.findAllRevoca",
	        query ="select n "+
	        "from EpaypaTSoggetto s,EpaypaTNotificaPagamento n "+
	        "where n.idRR= :idRr "
	        /* "SELECT a,case when (a.idRR is not null and b.iuv=a.iuv)then b.descrizione " + 
	            "end AS revoca " + 
	            "FROM epaypa_t_notifica_pagamento a, ( select t.descrizione,a.idRR,a.iuv,a.idNotificaPagamento "+
            "FROM  EpaypaTRr p,EpaypaTTipoRevoca t ,EpaypaTNotificaPagamento a"+ 
            " where a.idRR = p.idRr"+ 
            " and p.tipoRevoca=t.id )b"*/
	    )
})
public class EpaypaTNotificaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_notifica_pagamento")
	private Long idNotificaPagamento;

	@Column(name="anno_riferimento")
	private Integer annoRiferimento;

	@Column(name="codice_avviso")
	private String codiceAvviso;

	@Column(name="des_causale_versamento")
	private String desCausaleVersamento;

	@Column(name="dt_esito_pagamento")
	private Timestamp dtEsitoPagamento;

	@Column(name="dt_scadenza")
	private Timestamp dtScadenza;

	@Column(name="id_posizione_debitoria")
	private String idPosizioneDebitoria;

	@Column(name="importo_pagato")
	private BigDecimal importoPagato;

	@Column(name="id_rr")//<!-- CSI_PAG-184 -->
	private Long idRR;
	   

	private String iuv;

	private String note;

	@Transient//<!-- CSI_PAG-184 -->
	private String revoca;

	



	//bi-directional many-to-one association to EpaypaTFlusso
	@ManyToOne
	@JoinColumn(name="id_flusso", updatable=false, insertable=false, nullable=false)
	private EpaypaTFlusso epaypaTFlusso;

	//bi-directional one-to-one association to EpaypaTTransazionePsp
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JoinColumn(name="id_notifica_pagamento", updatable=false, insertable=false, nullable=false)
	private EpaypaTTransazionePsp epaypaTTransazionePsp;

	//uni-directional many-to-one association to EpaypaTSoggetto
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_soggetto_debitore")
	private EpaypaTSoggetto epaypaTSoggettoDebitore;

	//uni-directional many-to-one association to EpaypaTSoggetto
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_soggetto_versante")
	private EpaypaTSoggetto epaypaTSoggettoVersante;

	public EpaypaTNotificaPagamento() {
	}

	public Long getIdNotificaPagamento() {
		return this.idNotificaPagamento;
	}

	public void setIdNotificaPagamento(Long idNotificaPagamento) {
		this.idNotificaPagamento = idNotificaPagamento;
	}

	public Integer getAnnoRiferimento() {
		return this.annoRiferimento;
	}

	public void setAnnoRiferimento(Integer annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getCodiceAvviso() {
		return this.codiceAvviso;
	}

	public void setCodiceAvviso(String codiceAvviso) {
		this.codiceAvviso = codiceAvviso;
	}

	public String getDesCausaleVersamento() {
		return this.desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Timestamp getDtEsitoPagamento() {
		return this.dtEsitoPagamento;
	}

	public void setDtEsitoPagamento(Timestamp dtEsitoPagamento) {
		this.dtEsitoPagamento = dtEsitoPagamento;
	}

	public Timestamp getDtScadenza() {
		return this.dtScadenza;
	}

	public void setDtScadenza(Timestamp dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public String getIdPosizioneDebitoria() {
		return this.idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(String idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public BigDecimal getImportoPagato() {
		return this.importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public EpaypaTFlusso getEpaypaTFlusso() {
		return this.epaypaTFlusso;
	}

	public void setEpaypaTFlusso(EpaypaTFlusso epaypaTFlusso) {
		this.epaypaTFlusso = epaypaTFlusso;
	}

	public EpaypaTTransazionePsp getEpaypaTTransazionePsp() {
		return this.epaypaTTransazionePsp;
	}

	public void setEpaypaTTransazionePsp(EpaypaTTransazionePsp epaypaTTransazionePsp) {
		this.epaypaTTransazionePsp = epaypaTTransazionePsp;
	}

	public EpaypaTSoggetto getEpaypaTSoggettoDebitore() {
		return this.epaypaTSoggettoDebitore;
	}

	public void setEpaypaTSoggettoDebitore(EpaypaTSoggetto epaypaTSoggettoDebitore) {
		this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
	}

	public EpaypaTSoggetto getEpaypaTSoggettoVersante() {
		return this.epaypaTSoggettoVersante;
	}

	public void setEpaypaTSoggettoVersante(EpaypaTSoggetto epaypaTSoggettoVersante) {
		this.epaypaTSoggettoVersante = epaypaTSoggettoVersante;
	}

    public Long getIdRR () {
        return idRR;
    }

    
    public void setIdRR ( Long idRR ) {
        this.idRR = idRR;
    }
    
    public String getRevoca () {
        return revoca;
    }

    
    public void setRevoca ( String revoca ) {
        this.revoca = revoca;
    }
}
