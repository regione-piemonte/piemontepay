/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table ( name = "epay_t_esiti_ricevuti" )
@SuppressWarnings ( "unused" )
public class EpayTEsitiRicevuti implements Serializable {

	private static final long serialVersionUID = -8780018453217796216L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_ESITI_RICEVUTI_ID_ESITO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR" )
	@Column ( name = "id_esito", unique = true, nullable = false )
	private Long idEsito;

	@Column ( name = "cod_esito_pagamento" )
	private Integer codEsitoPagamento;

	@Column ( name = "data_pagamento" )
	private Timestamp dataPagamento;

	@Column ( name = "desc_esito_pagamento" )
	private String descEsitoPagamento;

	@Column ( name = "id_applicazione", length = 30 )
	private String idApplicazione;

	@Column ( name = "id_modalita_ricezione", nullable = false )
	private Integer idModalitaRicezione;

	@Column ( name = "id_transazione", length = 50 )
	private String idTransazione;

	@Column ( name = "identificativo_psp", length = 250 )
	private String identificativoPsp;

	@Column ( precision = 10, scale = 2 )
	private BigDecimal importo;

	@Column ( length = 35 )
	private String iur;

	@Column ( name = "ragione_sociale_psp", length = 250 )
	private String ragioneSocialePsp;

	@Column ( name = "iuv" )
	private String iuv;

	@Column ( name = "data_ora_operazione" )
	private Date dataOraOperazione;

	@ManyToOne
	@JoinColumn ( name = "id_modalita_pagamento" )
	private EpayDModalitaPagamento epayDModalitaPagamento;

	@ManyToOne
	@JoinColumn ( name = "id_registro", nullable = false )
	private EpayTRegistroVersamenti epayTRegistroVersamenti;

	@ManyToOne
	@JoinColumn ( name = "id_quietanza_esito" )
	private EpayTQuietanzaEsito epayTQuietanzaEsito;

	public EpayTEsitiRicevuti () {
	}

	public Long getIdEsito () {
		return idEsito;
	}

	public void setIdEsito ( Long idEsito ) {
		this.idEsito = idEsito;
	}

	public Integer getCodEsitoPagamento () {
		return codEsitoPagamento;
	}

	public void setCodEsitoPagamento ( Integer codEsitoPagamento ) {
		this.codEsitoPagamento = codEsitoPagamento;
	}

	public Timestamp getDataPagamento () {
		return dataPagamento;
	}

	public void setDataPagamento ( Timestamp dataPagamento ) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescEsitoPagamento () {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento ( String descEsitoPagamento ) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIdApplicazione () {
		return idApplicazione;
	}

	public void setIdApplicazione ( String idApplicazione ) {
		this.idApplicazione = idApplicazione;
	}

	public Integer getIdModalitaRicezione () {
		return idModalitaRicezione;
	}

	public void setIdModalitaRicezione ( Integer idModalitaRicezione ) {
		this.idModalitaRicezione = idModalitaRicezione;
	}

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIdentificativoPsp () {
		return identificativoPsp;
	}

	public void setIdentificativoPsp ( String identificativoPsp ) {
		this.identificativoPsp = identificativoPsp;
	}

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getIur () {
		return iur;
	}

	public void setIur ( String iur ) {
		this.iur = iur;
	}

	public String getRagioneSocialePsp () {
		return ragioneSocialePsp;
	}

	public void setRagioneSocialePsp ( String ragioneSocialePsp ) {
		this.ragioneSocialePsp = ragioneSocialePsp;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Date getDataOraOperazione () {
		return dataOraOperazione;
	}

	public void setDataOraOperazione ( Date dataOraOperazione ) {
		this.dataOraOperazione = dataOraOperazione;
	}

	public EpayDModalitaPagamento getEpayDModalitaPagamento () {
		return epayDModalitaPagamento;
	}

	public void setEpayDModalitaPagamento ( EpayDModalitaPagamento epayDModalitaPagamento ) {
		this.epayDModalitaPagamento = epayDModalitaPagamento;
	}

	public EpayTRegistroVersamenti getEpayTRegistroVersamenti () {
		return epayTRegistroVersamenti;
	}

	public void setEpayTRegistroVersamenti ( EpayTRegistroVersamenti epayTRegistroVersamenti ) {
		this.epayTRegistroVersamenti = epayTRegistroVersamenti;
	}

	public EpayTQuietanzaEsito getEpayTQuietanzaEsito () {
		return epayTQuietanzaEsito;
	}

	public void setEpayTQuietanzaEsito ( EpayTQuietanzaEsito epayTQuietanzaEsito ) {
		this.epayTQuietanzaEsito = epayTQuietanzaEsito;
	}

	@Override
	public String toString () {
		return "{ " +
			"idEsito:" + idEsito +
			", codEsitoPagamento:" + codEsitoPagamento +
			", dataPagamento:" + dataPagamento +
			", descEsitoPagamento:" + descEsitoPagamento +
			", idApplicazione:" + idApplicazione +
			", idModalitaRicezione:" + idModalitaRicezione +
			", idTransazione:" + idTransazione +
			", identificativoPsp:" + identificativoPsp +
			", importo:" + importo +
			", iur:" + iur +
			", ragioneSocialePsp:" + ragioneSocialePsp +
			", iuv:" + iuv +
			", dataOraOperazione:" + dataOraOperazione +
			", epayDModalitaPagamento:" + epayDModalitaPagamento +
			// non esporre epayTRegistroVersamenti
			// non esporre epayTQuietanzaEsito
			" }";
	}
}
