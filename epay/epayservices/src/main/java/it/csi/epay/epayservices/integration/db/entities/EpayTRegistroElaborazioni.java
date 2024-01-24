/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_registro_elaborazioni database table.
 * 
 */
@Entity
@Table(name="epay_t_registro_elaborazioni")
public class EpayTRegistroElaborazioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAY_T_REGISTRO_ELABORAZIONI_ID_GENERATOR", allocationSize=1, sequenceName="EPAY_T_REGISTRO_ELABORAZIONI_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_REGISTRO_ELABORAZIONI_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="data_fine")
	private Timestamp dataFine;

	@Column(name="data_inizio", nullable=false)
	private Timestamp dataInizio;

	@Column(nullable=false, length=250)
	private String esito;

	@Column(name="id_messaggio", length=250)
	private String idMessaggio;

	@Column(name="importo_tot_pagamenti", precision=10, scale=2)
	private BigDecimal importoTotPagamenti;

	@Column(name="message_fault", length=2000)
	private String messageFault;

	@Column(name="num_pagamenti")
	private Integer numPagamenti;

	@Column(length=250)
	private String operazione;

	@Column(name="pagamenti_spontanei")
	private Boolean pagamentiSpontanei;
	
	@Column(name="id_ente")
	private Long idEnte;
	
	@Column(name="id_tipo_pagamento")
	private Long idTipoPagamento;

	//bi-directional many-to-many association to EpayTPagamento
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
		name="epay_r_pagamento_registro_elaborazioni"
		, joinColumns={
			@JoinColumn(name="id_registro_elaborazioni", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_pagamento", nullable=false)
			}
		)
	private List<EpayTPagamento> epayTPagamentos;

	//bi-directional many-to-one association to EpayTRegistroElaborazioniFault
	@OneToMany(mappedBy="epayTRegistroElaborazioni", cascade=CascadeType.ALL)
	private List<EpayTRegistroElaborazioniFault> epayTRegistroElaborazioniFaults;
		
	public EpayTRegistroElaborazioni() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getEsito() {
		return this.esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getIdMessaggio() {
		return this.idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public BigDecimal getImportoTotPagamenti() {
		return this.importoTotPagamenti;
	}

	public void setImportoTotPagamenti(BigDecimal importoTotPagamenti) {
		this.importoTotPagamenti = importoTotPagamenti;
	}

	public String getMessageFault() {
		return this.messageFault;
	}

	public void setMessageFault(String messageFault) {
		this.messageFault = messageFault;
	}

	public Integer getNumPagamenti() {
		return this.numPagamenti;
	}

	public void setNumPagamenti(Integer numPagamenti) {
		this.numPagamenti = numPagamenti;
	}

	public String getOperazione() {
		return this.operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public Boolean getPagamentiSpontanei() {
		return this.pagamentiSpontanei;
	}

	public void setPagamentiSpontanei(Boolean pagamentiSpontanei) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public List<EpayTPagamento> getEpayTPagamentos() {
		return this.epayTPagamentos;
	}

	public void setEpayTPagamentos(List<EpayTPagamento> epayTPagamentos) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public Long getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}

	public Long getIdTipoPagamento() {
		return this.idTipoPagamento;
	}

	public void setIdTipoPagamento(Long idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}
	
	public List<EpayTRegistroElaborazioniFault> getEpayTRegistroElaborazioniFaults() {
		return this.epayTRegistroElaborazioniFaults;
	}

	public void setEpayTRegistroElaborazioniFaults(List<EpayTRegistroElaborazioniFault> epayTRegistroElaborazioniFaults) {
		this.epayTRegistroElaborazioniFaults = epayTRegistroElaborazioniFaults;
	}

	public EpayTRegistroElaborazioniFault addEpayTRegistroElaborazioniFault(EpayTRegistroElaborazioniFault epayTRegistroElaborazioniFault) {
		getEpayTRegistroElaborazioniFaults().add(epayTRegistroElaborazioniFault);
		epayTRegistroElaborazioniFault.setEpayTRegistroElaborazioni(this);

		return epayTRegistroElaborazioniFault;
	}

	public EpayTRegistroElaborazioniFault removeEpayTRegistroElaborazioniFault(EpayTRegistroElaborazioniFault epayTRegistroElaborazioniFault) {
		getEpayTRegistroElaborazioniFaults().remove(epayTRegistroElaborazioniFault);
		epayTRegistroElaborazioniFault.setEpayTRegistroElaborazioni(null);

		return epayTRegistroElaborazioniFault;
	}
}
