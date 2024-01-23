/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table ( name = "epay_t_registro_versamenti" )
@SuppressWarnings ( "unused" )
public class EpayTRegistroVersamenti implements Serializable {

	private static final long serialVersionUID = -4667660030760236025L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_REGISTRO_VERSAMENTI_IDREGISTRO_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_REGISTRO_VERSAMENTI_ID_REGISTRO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_REGISTRO_VERSAMENTI_IDREGISTRO_GENERATOR" )
	@Column ( name = "id_registro", unique = true, nullable = false )
	private Long idRegistro;

	@Column ( name = "data_operazione", nullable = false )
	private Timestamp dataOperazione;

	@Column ( name = "desc_esito_pagamento" )
	private String descEsitoPagamento;

	@Column ( length = 25 )
	private String iuv;

	@Column ( name = "origine_inserimento" )
	private String origineInserimento;

	//bi-directional many-to-one association to EpayDOrigineChiamata
	@ManyToOne
	@JoinColumn(name="id_origine_chiamata")
	private EpayDOrigineChiamata epayDOrigineChiamata;

	@OneToMany ( mappedBy = "epayTRegistroVersamenti", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	private List<EpayTEsitiRicevuti> epayTEsitiRicevutis;

	@ManyToOne
	@JoinColumn ( name = "id_stato", nullable = false )
	private EpayDStatoPagamento epayDStatoPagamento;

	@ManyToOne
	@JoinColumn ( name = "id_anagrafica_versante" )
	private EpayTAnagrafica epayTAnagrafica;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento", nullable = false )
	private EpayTPagamento epayTPagamento;

	@ManyToOne
	@JoinColumn ( name = "id_transazione" )
	private EpayTTransazioneMdp epayTTransazioneMdp;

	@OneToMany ( mappedBy = "epayTRegistroVersamenti", cascade = CascadeType.ALL )
	private List<EpayTRt> epayTRts;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento_secondario" )
	private EpayTPagamentoSecondario epayTPagamentoSecondario;

	public EpayTRegistroVersamenti () {
	}

	public Long getIdRegistro () {
		return idRegistro;
	}

	public void setIdRegistro ( Long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public Timestamp getDataOperazione () {
		return dataOperazione;
	}

	public void setDataOperazione ( Timestamp dataOperazione ) {
		this.dataOperazione = dataOperazione;
	}

	public String getDescEsitoPagamento () {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento ( String descEsitoPagamento ) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getOrigineInserimento () {
		return origineInserimento;
	}

	public void setOrigineInserimento ( String origineInserimento ) {
		this.origineInserimento = origineInserimento;
	}

	public EpayDOrigineChiamata getEpayDOrigineChiamata() {
		return this.epayDOrigineChiamata;
	}

	public void setEpayDOrigineChiamata(EpayDOrigineChiamata epayDOrigineChiamata) {
		this.epayDOrigineChiamata = epayDOrigineChiamata;
	}

	public List<EpayTEsitiRicevuti> getEpayTEsitiRicevutis () {
		return epayTEsitiRicevutis;
	}

	public void setEpayTEsitiRicevutis ( List<EpayTEsitiRicevuti> epayTEsitiRicevutis ) {
		this.epayTEsitiRicevutis = epayTEsitiRicevutis;
	}

	public EpayDStatoPagamento getEpayDStatoPagamento () {
		return epayDStatoPagamento;
	}

	public void setEpayDStatoPagamento ( EpayDStatoPagamento epayDStatoPagamento ) {
		this.epayDStatoPagamento = epayDStatoPagamento;
	}

	public EpayTAnagrafica getEpayTAnagrafica () {
		return epayTAnagrafica;
	}

	public void setEpayTAnagrafica ( EpayTAnagrafica epayTAnagrafica ) {
		this.epayTAnagrafica = epayTAnagrafica;
	}

	public EpayTPagamento getEpayTPagamento () {
		return epayTPagamento;
	}

	public void setEpayTPagamento ( EpayTPagamento epayTPagamento ) {
		this.epayTPagamento = epayTPagamento;
	}

	public EpayTTransazioneMdp getEpayTTransazioneMdp () {
		return epayTTransazioneMdp;
	}

	public void setEpayTTransazioneMdp ( EpayTTransazioneMdp epayTTransazioneMdp ) {
		this.epayTTransazioneMdp = epayTTransazioneMdp;
	}

	public List<EpayTRt> getEpayTRts () {
		return epayTRts;
	}

	public void setEpayTRts ( List<EpayTRt> epayTRts ) {
		this.epayTRts = epayTRts;
	}

	public EpayTPagamentoSecondario getEpayTPagamentoSecondario () {
		return epayTPagamentoSecondario;
	}

	public void setEpayTPagamentoSecondario ( EpayTPagamentoSecondario epayTPagamentoSecondario ) {
		this.epayTPagamentoSecondario = epayTPagamentoSecondario;
	}

	@Override
	public String toString () {
		return "{ " +
			"idRegistro:" + idRegistro +
			", dataOperazione:" + dataOperazione +
			", descEsitoPagamento:" + descEsitoPagamento +
			", iuv:" + iuv +
			", origineInserimento:" + origineInserimento +
			// non esporre epayTEsitiRicevutis
			// non esporre epayDStatoPagamento
			// non esporre epayTAnagrafica
			// non esporre epayTPagamento
			// non esporre epayTTransazioneMdp
			// non esporre epayTRts
			// non esporre  epayTPagamentoSecondario
			" }";
	}
}
