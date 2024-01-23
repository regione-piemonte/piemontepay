/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

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
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table ( name = "epay_t_registro_elaborazioni" )
@SuppressWarnings ( "unused" )
public class EpayTRegistroElaborazioni implements Serializable {

	private static final long serialVersionUID = -8119913620504985381L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_REGISTRO_ELABORAZIONI_ID_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_REGISTRO_ELABORAZIONI_ID_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_REGISTRO_ELABORAZIONI_ID_GENERATOR" )
	@Column ( unique = true, nullable = false )
	private Long id;

	@Column ( name = "data_inizio", nullable = false )
	private Timestamp dataInizio;

	@Column ( name = "data_fine" )
	private Timestamp dataFine;

	@Column ( nullable = false, length = 250 )
	private String esito;

	@Column ( name = "id_messaggio", length = 250 )
	private String idMessaggio;

	@Column ( name = "importo_tot_pagamenti", precision = 10, scale = 2 )
	private BigDecimal importoTotPagamenti;

	@Column ( name = "message_fault", length = 2000 )
	private String messageFault;

	@Column ( name = "num_pagamenti" )
	private Integer numPagamenti;

	@Column ( length = 250 )
	private String operazione;

	@Column ( name = "pagamenti_spontanei" )
	private Boolean pagamentiSpontanei;

	@Column ( name = "id_ente" )
	private Long idEnte;

	@Column ( name = "id_tipo_pagamento" )
	private Long idTipoPagamento;

	@ManyToMany ( cascade = CascadeType.ALL )
	@JoinTable (
					name = "epay_r_pagamento_registro_elaborazioni", joinColumns = {
						@JoinColumn ( name = "id_registro_elaborazioni", nullable = false )
					},
					inverseJoinColumns = {
						@JoinColumn ( name = "id_pagamento", nullable = false )
					} )
	private List<EpayTPagamento> epayTPagamentos;

	@OneToMany ( mappedBy = "epayTRegistroElaborazioni", cascade = CascadeType.ALL )
	private List<EpayTRegistroElaborazioniFault> epayTRegistroElaborazioniFaults;

	public EpayTRegistroElaborazioni () {
	}

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Timestamp getDataInizio () {
		return dataInizio;
	}

	public void setDataInizio ( Timestamp dataInizio ) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine () {
		return dataFine;
	}

	public void setDataFine ( Timestamp dataFine ) {
		this.dataFine = dataFine;
	}

	public String getEsito () {
		return esito;
	}

	public void setEsito ( String esito ) {
		this.esito = esito;
	}

	public String getIdMessaggio () {
		return idMessaggio;
	}

	public void setIdMessaggio ( String idMessaggio ) {
		this.idMessaggio = idMessaggio;
	}

	public BigDecimal getImportoTotPagamenti () {
		return importoTotPagamenti;
	}

	public void setImportoTotPagamenti ( BigDecimal importoTotPagamenti ) {
		this.importoTotPagamenti = importoTotPagamenti;
	}

	public String getMessageFault () {
		return messageFault;
	}

	public void setMessageFault ( String messageFault ) {
		this.messageFault = messageFault;
	}

	public Integer getNumPagamenti () {
		return numPagamenti;
	}

	public void setNumPagamenti ( Integer numPagamenti ) {
		this.numPagamenti = numPagamenti;
	}

	public String getOperazione () {
		return operazione;
	}

	public void setOperazione ( String operazione ) {
		this.operazione = operazione;
	}

	public Boolean getPagamentiSpontanei () {
		return pagamentiSpontanei;
	}

	public void setPagamentiSpontanei ( Boolean pagamentiSpontanei ) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public Long getIdEnte () {
		return idEnte;
	}

	public void setIdEnte ( Long idEnte ) {
		this.idEnte = idEnte;
	}

	public Long getIdTipoPagamento () {
		return idTipoPagamento;
	}

	public void setIdTipoPagamento ( Long idTipoPagamento ) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public List<EpayTPagamento> getEpayTPagamentos () {
		return epayTPagamentos;
	}

	public void setEpayTPagamentos ( List<EpayTPagamento> epayTPagamentos ) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public List<EpayTRegistroElaborazioniFault> getEpayTRegistroElaborazioniFaults () {
		return epayTRegistroElaborazioniFaults;
	}

	public void setEpayTRegistroElaborazioniFaults ( List<EpayTRegistroElaborazioniFault> epayTRegistroElaborazioniFaults ) {
		this.epayTRegistroElaborazioniFaults = epayTRegistroElaborazioniFaults;
	}

	@Override
	public String toString () {
		return "{ " +
			"id:" + id +
			", dataInizio:" + dataInizio +
			", dataFine:" + dataFine +
			", esito:" + esito +
			", idMessaggio:" + idMessaggio +
			", importoTotPagamenti:" + importoTotPagamenti +
			", messageFault:" + messageFault +
			", numPagamenti:" + numPagamenti +
			", operazione:" + operazione +
			", pagamentiSpontanei:" + pagamentiSpontanei +
			", idEnte:" + idEnte +
			", idTipoPagamento:" + idTipoPagamento +
			// non esporre epayTPagamentos
			// non esporre epayTRegistroElaborazioniFaults
			" }";
	}
}
