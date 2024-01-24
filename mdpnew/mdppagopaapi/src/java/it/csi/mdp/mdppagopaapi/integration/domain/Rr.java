/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rr database table.
 * 
 */
@Entity
@NamedQuery(name="Rr.findAll", query="SELECT r FROM Rr r")
public class Rr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RR_IDRR_GENERATOR", sequenceName="RR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RR_IDRR_GENERATOR")
	@Column(name="id_rr")
	private Integer idRr;

	@Column(name="application_id")
	private String applicationId;

	@Column(name="codice_contesto_pagamento")
	private String codiceContestoPagamento;

	@Column(name="codice_identificativo_univoco_attestante")
	private String codiceIdentificativoUnivocoAttestante;

	@Column(name="data_ora_messaggio_revoca")
	private Timestamp dataOraMessaggioRevoca;

	@Column(name="denominazione_istituto_attestante")
	private String denominazioneIstitutoAttestante;

	@Column(name="id_dominio")
	private String idDominio;

	@Column(name="identificativo_messaggio_revoca")
	private String identificativoMessaggioRevoca;

	@Column(name="importo_totale_revocato")
	private BigDecimal importoTotaleRevocato;

	private String iuv;

	@Column(name="xml_rr")
	private byte[] xmlRr;

	//bi-directional many-to-one association to DatiSingolaRevoca
	@OneToMany(mappedBy="rr")
	private List<DatiSingolaRevoca> datiSingolaRevocas;

	//bi-directional many-to-one association to TipoRevoca
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_revoca")
	private TipoRevoca tipoRevocaBean;

	public Rr() {
	}

	public Integer getIdRr() {
		return this.idRr;
	}

	public void setIdRr(Integer idRr) {
		this.idRr = idRr;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCodiceContestoPagamento() {
		return this.codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento(String codiceContestoPagamento) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}

	public String getCodiceIdentificativoUnivocoAttestante() {
		return this.codiceIdentificativoUnivocoAttestante;
	}

	public void setCodiceIdentificativoUnivocoAttestante(String codiceIdentificativoUnivocoAttestante) {
		this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
	}

	public Timestamp getDataOraMessaggioRevoca() {
		return this.dataOraMessaggioRevoca;
	}

	public void setDataOraMessaggioRevoca(Timestamp dataOraMessaggioRevoca) {
		this.dataOraMessaggioRevoca = dataOraMessaggioRevoca;
	}

	public String getDenominazioneIstitutoAttestante() {
		return this.denominazioneIstitutoAttestante;
	}

	public void setDenominazioneIstitutoAttestante(String denominazioneIstitutoAttestante) {
		this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
	}

	public String getIdDominio() {
		return this.idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}

	public String getIdentificativoMessaggioRevoca() {
		return this.identificativoMessaggioRevoca;
	}

	public void setIdentificativoMessaggioRevoca(String identificativoMessaggioRevoca) {
		this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
	}

	public BigDecimal getImportoTotaleRevocato() {
		return this.importoTotaleRevocato;
	}

	public void setImportoTotaleRevocato(BigDecimal importoTotaleRevocato) {
		this.importoTotaleRevocato = importoTotaleRevocato;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public byte[] getXmlRr() {
		return this.xmlRr;
	}

	public void setXmlRr(byte[] xmlRr) {
		this.xmlRr = xmlRr;
	}

	public List<DatiSingolaRevoca> getDatiSingolaRevocas() {
		return this.datiSingolaRevocas;
	}

	public void setDatiSingolaRevocas(List<DatiSingolaRevoca> datiSingolaRevocas) {
		this.datiSingolaRevocas = datiSingolaRevocas;
	}

	public DatiSingolaRevoca addDatiSingolaRevoca(DatiSingolaRevoca datiSingolaRevoca) {
		getDatiSingolaRevocas().add(datiSingolaRevoca);
		datiSingolaRevoca.setRr(this);

		return datiSingolaRevoca;
	}

	public DatiSingolaRevoca removeDatiSingolaRevoca(DatiSingolaRevoca datiSingolaRevoca) {
		getDatiSingolaRevocas().remove(datiSingolaRevoca);
		datiSingolaRevoca.setRr(null);

		return datiSingolaRevoca;
	}

	public TipoRevoca getTipoRevocaBean() {
		return this.tipoRevocaBean;
	}

	public void setTipoRevocaBean(TipoRevoca tipoRevocaBean) {
		this.tipoRevocaBean = tipoRevocaBean;
	}

}
