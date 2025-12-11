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


@Entity
@Table ( name = "epay_t_rt" )
@SuppressWarnings ( "unused" )
public class EpayTRt implements Serializable {

	private static final long serialVersionUID = 8824439627038361717L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_RT_IDRT_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_RT_ID_RT_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_RT_IDRT_GENERATOR" )
	@Column ( name = "id_rt", unique = true, nullable = false )
	private Long idRt;

	@Column ( name = "cod_esito_pagamento" )
	private Integer codEsitoPagamento;

	@Column ( name = "dataora_msg_ricevuta" )
	private Timestamp dataoraMsgRicevuta;

	@Column ( name = "desc_esito_pagamento" )
	private String descEsitoPagamento;

	@Column ( name = "id_applicazione", length = 50 )
	private String idApplicazione;

	@Column ( name = "id_msg_ricevuta", length = 35 )
	private String idMsgRicevuta;

	@Column ( name = "id_msg_richiesta", length = 35 )
	private String idMsgRichiesta;

	@Column ( name = "id_transazione", length = 30 )
	private String idTransazione;

	@Column ( name = "iuv", length = 35 )
	private String iuv;

	@Column ( name = "ricevuta_pdf" )
	private byte[] ricevutaPdf;

	@Column ( name = "rt_xml", nullable = false )
	private byte[] rtXml;

	@Column ( name = "tipo_firma", length = 10 )
	private String tipoFirma;

	@Column ( name = "id_rr" )
	private Integer idRr;

	@Column ( name = "codice_contesto_pagamento" )
	private String codiceContestoPagamento;

	@Column ( name = "identificativo_dominio" )
	private String identificativoDominio;

	@ManyToOne
	@JoinColumn ( name = "id_registro", nullable = false )
	private EpayTRegistroVersamenti epayTRegistroVersamenti;

	@Column ( name = "importo_totale_pagato", precision = 10, scale = 2 )
	private BigDecimal importoTotalePagato;

	public EpayTRt () {
	}

	public Long getIdRt () {
		return idRt;
	}

	public void setIdRt ( Long idRt ) {
		this.idRt = idRt;
	}

	public Integer getCodEsitoPagamento () {
		return codEsitoPagamento;
	}

	public void setCodEsitoPagamento ( Integer codEsitoPagamento ) {
		this.codEsitoPagamento = codEsitoPagamento;
	}

	public Timestamp getDataoraMsgRicevuta () {
		return dataoraMsgRicevuta;
	}

	public void setDataoraMsgRicevuta ( Timestamp dataoraMsgRicevuta ) {
		this.dataoraMsgRicevuta = dataoraMsgRicevuta;
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

	public String getIdMsgRicevuta () {
		return idMsgRicevuta;
	}

	public void setIdMsgRicevuta ( String idMsgRicevuta ) {
		this.idMsgRicevuta = idMsgRicevuta;
	}

	public String getIdMsgRichiesta () {
		return idMsgRichiesta;
	}

	public void setIdMsgRichiesta ( String idMsgRichiesta ) {
		this.idMsgRichiesta = idMsgRichiesta;
	}

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public byte[] getRtXml () {
		return rtXml;
	}

	public void setRtXml ( byte[] rtXml ) {
		this.rtXml = rtXml;
	}

	public String getTipoFirma () {
		return tipoFirma;
	}

	public void setTipoFirma ( String tipoFirma ) {
		this.tipoFirma = tipoFirma;
	}

	public Integer getIdRr () {
		return idRr;
	}

	public void setIdRr ( Integer idRr ) {
		this.idRr = idRr;
	}

	public String getCodiceContestoPagamento () {
		return codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}

	public String getIdentificativoDominio () {
		return identificativoDominio;
	}

	public void setIdentificativoDominio ( String identificativoDominio ) {
		this.identificativoDominio = identificativoDominio;
	}

	public EpayTRegistroVersamenti getEpayTRegistroVersamenti () {
		return epayTRegistroVersamenti;
	}

	public void setEpayTRegistroVersamenti ( EpayTRegistroVersamenti epayTRegistroVersamenti ) {
		this.epayTRegistroVersamenti = epayTRegistroVersamenti;
	}

	public BigDecimal getImportoTotalePagato () {
		return importoTotalePagato;
	}

	public void setImportoTotalePagato ( BigDecimal importoTotalePagato ) {
		this.importoTotalePagato = importoTotalePagato;
	}

	@Override
	public String toString () {
		return "{ " +
						"idRt:" + idRt +
						", codEsitoPagamento:" + codEsitoPagamento +
						", dataoraMsgRicevuta:" + dataoraMsgRicevuta +
						", descEsitoPagamento:" + descEsitoPagamento +
						", idApplicazione:" + idApplicazione +
						", idMsgRicevuta:" + idMsgRicevuta +
						", idMsgRichiesta:" + idMsgRichiesta +
						", idTransazione:" + idTransazione +
						", iuv:" + iuv +
						// non esporre ricevutaPdf
						// non esporre rtXml
						", tipoFirma:" + tipoFirma +
						", idRr:" + idRr +
						", codiceContestoPagamento:" + codiceContestoPagamento +
						", identificativoDominio:" + identificativoDominio +
						// non esporre epayTRegistroVersamenti
						", importoTotalePagato:" + importoTotalePagato +
						" }";
	}
}
