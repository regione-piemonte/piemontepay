/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_rr database table.
 * 
 */
@Entity
@Table(name="epay_t_rr")
@NamedQueries ( {
    @NamedQuery(name="EpayTRr.findAll",    query="SELECT e FROM EpayTRr e"),
    @NamedQuery(name="EpayTRr.findToSend", query="SELECT e FROM EpayTRr e WHERE e.idDominio = :idDominio and e.dataOraInvioWso2 IS NULL" )
  } )
public class EpayTRr implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idRr;
	private String applicationId;
	private String codiceContestoPagamento;
	private String codiceIdentificativoUnivocoAttestante;
	private Timestamp dataOraMessaggioRevoca;
	private String denominazioneIstitutoAttestante;
	private String idDominio;
	private String identificativoMessaggioRevoca;
	private BigDecimal importoTotaleRevocato;
	private String iuv;
	private BigDecimal tipoRevoca;
	private byte[] xmlRr;
	private List<EpayTDatiSingolaRevoca> epayTDatiSingolaRevocas;
	private List<EpayTDatiSingoloEsito> epayTDatiSingoloEsitos;
    private Timestamp dataOraInvioWso2;

	public EpayTRr() {
	}


	@Id
	@SequenceGenerator(name="EPAY_T_RR_IDRR_GENERATOR", allocationSize = 1, sequenceName="EPAY_T_RR_ID_RR_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_RR_IDRR_GENERATOR")
	@Column(name="id_rr", unique=true, nullable=false)
	public Integer getIdRr() {
		return this.idRr;
	}

	public void setIdRr(Integer idRr) {
		this.idRr = idRr;
	}


	@Column(name="application_id", nullable=false, length=50)
	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}


	@Column(name="codice_contesto_pagamento", nullable=false, length=35)
	public String getCodiceContestoPagamento() {
		return this.codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento(String codiceContestoPagamento) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}


	@Column(name="codice_identificativo_univoco_attestante", nullable=false, length=35)
	public String getCodiceIdentificativoUnivocoAttestante() {
		return this.codiceIdentificativoUnivocoAttestante;
	}

	public void setCodiceIdentificativoUnivocoAttestante(String codiceIdentificativoUnivocoAttestante) {
		this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
	}


	@Column(name="data_ora_messaggio_revoca")
	public Timestamp getDataOraMessaggioRevoca() {
		return this.dataOraMessaggioRevoca;
	}

	public void setDataOraMessaggioRevoca(Timestamp dataOraMessaggioRevoca) {
		this.dataOraMessaggioRevoca = dataOraMessaggioRevoca;
	}


	@Column(name="denominazione_istituto_attestante", length=35)
	public String getDenominazioneIstitutoAttestante() {
		return this.denominazioneIstitutoAttestante;
	}

	public void setDenominazioneIstitutoAttestante(String denominazioneIstitutoAttestante) {
		this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
	}


	@Column(name="id_dominio", nullable=false, length=35)
	public String getIdDominio() {
		return this.idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}


	@Column(name="identificativo_messaggio_revoca", nullable=false, length=35)
	public String getIdentificativoMessaggioRevoca() {
		return this.identificativoMessaggioRevoca;
	}

	public void setIdentificativoMessaggioRevoca(String identificativoMessaggioRevoca) {
		this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
	}


	@Column(name="importo_totale_revocato", nullable=false, precision=13, scale=2)
	public BigDecimal getImportoTotaleRevocato() {
		return this.importoTotaleRevocato;
	}

	public void setImportoTotaleRevocato(BigDecimal importoTotaleRevocato) {
		this.importoTotaleRevocato = importoTotaleRevocato;
	}


	@Column(nullable=false, length=35)
	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}


	@Column(name="tipo_revoca", nullable=false, precision=131089)
	public BigDecimal getTipoRevoca() {
		return this.tipoRevoca;
	}

	public void setTipoRevoca(BigDecimal tipoRevoca) {
		this.tipoRevoca = tipoRevoca;
	}


	@Column(name="xml_rr")
	public byte[] getXmlRr() {
		return this.xmlRr;
	}

	public void setXmlRr(byte[] xmlRr) {
		this.xmlRr = xmlRr;
	}


	//bi-directional many-to-one association to EpayTDatiSingolaRevoca
//	@OneToMany(mappedBy="epayTRr")
//	public List<EpayTDatiSingolaRevoca> getEpayTDatiSingolaRevocas() {
//		return this.epayTDatiSingolaRevocas;
//	}
//
//	public void setEpayTDatiSingolaRevocas(List<EpayTDatiSingolaRevoca> epayTDatiSingolaRevocas) {
//		this.epayTDatiSingolaRevocas = epayTDatiSingolaRevocas;
//	}

//	public EpayTDatiSingolaRevoca addEpayTDatiSingolaRevoca(EpayTDatiSingolaRevoca epayTDatiSingolaRevoca) {
//		getEpayTDatiSingolaRevocas().add(epayTDatiSingolaRevoca);
//		epayTDatiSingolaRevoca.setEpayTRr(this);
//
//		return epayTDatiSingolaRevoca;
//	}
//
//	public EpayTDatiSingolaRevoca removeEpayTDatiSingolaRevoca(EpayTDatiSingolaRevoca epayTDatiSingolaRevoca) {
//		getEpayTDatiSingolaRevocas().remove(epayTDatiSingolaRevoca);
//		epayTDatiSingolaRevoca.setEpayTRr(null);
//
//		return epayTDatiSingolaRevoca;
//	}


	//bi-directional many-to-one association to EpayTDatiSingoloEsito
//	@OneToMany(mappedBy="epayTRr")
//	public List<EpayTDatiSingoloEsito> getEpayTDatiSingoloEsitos() {
//		return this.epayTDatiSingoloEsitos;
//	}

	public void setEpayTDatiSingoloEsitos(List<EpayTDatiSingoloEsito> epayTDatiSingoloEsitos) {
		this.epayTDatiSingoloEsitos = epayTDatiSingoloEsitos;
	}


    @Column(name="data_ora_invio_wso2")    
    public Timestamp getDataOraInvioWso2 () {
        return dataOraInvioWso2;
    }


    
    public void setDataOraInvioWso2 ( Timestamp dataOraInvioWso2 ) {
        this.dataOraInvioWso2 = dataOraInvioWso2;
    }

//	public EpayTDatiSingoloEsito addEpayTDatiSingoloEsito(EpayTDatiSingoloEsito epayTDatiSingoloEsito) {
//		getEpayTDatiSingoloEsitos().add(epayTDatiSingoloEsito);
//		epayTDatiSingoloEsito.setEpayTRr(this);
//
//		return epayTDatiSingoloEsito;
//	}

//	public EpayTDatiSingoloEsito removeEpayTDatiSingoloEsito(EpayTDatiSingoloEsito epayTDatiSingoloEsito) {
//		getEpayTDatiSingoloEsitos().remove(epayTDatiSingoloEsito);
//		epayTDatiSingoloEsito.setEpayTRr(null);
//
//		return epayTDatiSingoloEsito;
//	}

}
