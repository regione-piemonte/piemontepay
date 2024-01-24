/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the epay_t_quietanza_da_elaborare database table.
 */
@Entity
@Table ( name = "epay_t_quietanza_da_elaborare" )
@NamedQuery ( name = "EpayTQuietanzaDaElaborare.findAll", query = "SELECT e FROM EpayTQuietanzaDaElaborare e" )
@SuppressWarnings ( "unused" )
public class EpayTQuietanzaDaElaborare implements Serializable {

	private static final long serialVersionUID = -4232729821626054689L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_QUIETANZA_DA_ELABORARE_IDQUIETANZADAELABORARE_GENERATOR", allocationSize=1, sequenceName = "EPAY_T_QUIETANZA_DA_ELABORARE_ID_QUIETANZA_DA_ELABORARE_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_QUIETANZA_DA_ELABORARE_IDQUIETANZADAELABORARE_GENERATOR" )
	@Column ( name = "id_quietanza_da_elaborare", unique = true, nullable = false )
	private Long idQuietanzaDaElaborare;

	@Column ( name = "cod_esito" )
	private String codEsito;

	@Temporal ( TemporalType.TIMESTAMP )
	@Column ( name = "data_inserimento" )
	private Date dataInserimento;

	@Temporal ( TemporalType.TIMESTAMP )
	@Column ( name = "data_prima_chiamata" )
	private Date dataPrimaChiamata;

	@Column ( name = "fruitore_esterno" )
	private String fruitoreEsterno;

	@Column ( name = "identificativo_chiamata" )
	private String identificativoChiamata;

	private Boolean inviare;

	@Temporal ( TemporalType.TIMESTAMP )
	@Column ( name = "invio_fallito_giornaliero" )
	private Date invioFallitoGiornaliero;

	private String iuv;

	@Column ( name = "nr_giorni" )
	private Integer nrGiorni;

	@Column ( name = "nr_invii" )
	private Integer nrInvii;

	@Column ( name = "ricevuta_pdf" )
	private byte[] ricevutaPdf;

	@Column ( name = "termini_scaduti" )
	private Boolean terminiScaduti;

	@Column ( name = "tipo_elaborazione" )
	private String tipoElaborazione;

	//bi-directional many-to-many association to EpayTElaborazione
	@ManyToMany
	@JoinTable (
					name = "epay_r_quietanza_elaborazione"
					, joinColumns = {
					@JoinColumn ( name = "id_quietanza_da_elaborare" )
	}
					, inverseJoinColumns = {
					@JoinColumn ( name = "id_elaborazione" )
	}
	)
	private List<EpayTElaborazione> epayTElaboraziones;

	//bi-directional many-to-one association to EpayTQuietanzaEsito
	@ManyToOne
	@JoinColumn ( name = "id_quietanza_esito" )
	private EpayTQuietanzaEsito epayTQuietanzaEsito;

	//bi-directional many-to-one association to EpayTRt
	@ManyToOne
	@JoinColumn ( name = "id_rt" )
	private EpayTRt epayTRt;

	@Column ( name = "causale_pagamento" )
	private String causalePagamento;

	@Column ( name = "data_inizio_pagamento" )
	@Temporal ( TemporalType.TIMESTAMP )
	private Date dataInizioPagamento;

	@Column ( name = "data_fine_pagamento" )
	@Temporal ( TemporalType.TIMESTAMP )
	private Date dataFinePagamento;

	@Column ( name = "cf_cittadino", length = 24 )
	private String cfCittadino;

	@Column ( name = "codice_ipa", length = 10 )
	private String codiceIpa;

	public EpayTQuietanzaDaElaborare () {
	}

	public Long getIdQuietanzaDaElaborare () {
		return this.idQuietanzaDaElaborare;
	}

	public void setIdQuietanzaDaElaborare ( Long idQuietanzaDaElaborare ) {
		this.idQuietanzaDaElaborare = idQuietanzaDaElaborare;
	}

	public String getCodEsito () {
		return this.codEsito;
	}

	public void setCodEsito ( String codEsito ) {
		this.codEsito = codEsito;
	}

	public Date getDataInserimento () {
		return this.dataInserimento;
	}

	public void setDataInserimento ( Date dataInserimento ) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataPrimaChiamata () {
		return this.dataPrimaChiamata;
	}

	public void setDataPrimaChiamata ( Date dataPrimaChiamata ) {
		this.dataPrimaChiamata = dataPrimaChiamata;
	}

	public String getFruitoreEsterno () {
		return this.fruitoreEsterno;
	}

	public void setFruitoreEsterno ( String fruitoreEsterno ) {
		this.fruitoreEsterno = fruitoreEsterno;
	}

	public String getIdentificativoChiamata () {
		return this.identificativoChiamata;
	}

	public void setIdentificativoChiamata ( String identificativoChiamata ) {
		this.identificativoChiamata = identificativoChiamata;
	}

	public Boolean getInviare () {
		return this.inviare;
	}

	public void setInviare ( Boolean inviare ) {
		this.inviare = inviare;
	}

	public Date getInvioFallitoGiornaliero () {
		return this.invioFallitoGiornaliero;
	}

	public void setInvioFallitoGiornaliero ( Date invioFallitoGiornaliero ) {
		this.invioFallitoGiornaliero = invioFallitoGiornaliero;
	}

	public String getIuv () {
		return this.iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Integer getNrGiorni () {
		return this.nrGiorni;
	}

	public void setNrGiorni ( Integer nrGiorni ) {
		this.nrGiorni = nrGiorni;
	}

	public Integer getNrInvii () {
		return this.nrInvii;
	}

	public void setNrInvii ( Integer nrInvii ) {
		this.nrInvii = nrInvii;
	}

	public byte[] getRicevutaPdf () {
		return this.ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public Boolean getTerminiScaduti () {
		return this.terminiScaduti;
	}

	public void setTerminiScaduti ( Boolean terminiScaduti ) {
		this.terminiScaduti = terminiScaduti;
	}

	public String getTipoElaborazione () {
		return this.tipoElaborazione;
	}

	public void setTipoElaborazione ( String tipoElaborazione ) {
		this.tipoElaborazione = tipoElaborazione;
	}

	public List<EpayTElaborazione> getEpayTElaboraziones () {
		return this.epayTElaboraziones;
	}

	public void setEpayTElaboraziones ( List<EpayTElaborazione> epayTElaboraziones ) {
		this.epayTElaboraziones = epayTElaboraziones;
	}

	public EpayTQuietanzaEsito getEpayTQuietanzaEsito () {
		return this.epayTQuietanzaEsito;
	}

	public void setEpayTQuietanzaEsito ( EpayTQuietanzaEsito epayTQuietanzaEsito ) {
		this.epayTQuietanzaEsito = epayTQuietanzaEsito;
	}

	public EpayTRt getEpayTRt () {
		return this.epayTRt;
	}

	public void setEpayTRt ( EpayTRt epayTRt ) {
		this.epayTRt = epayTRt;
	}

	public String getCausalePagamento () {
		return causalePagamento;
	}

	public void setCausalePagamento ( String causalePagamento ) {
		this.causalePagamento = causalePagamento;
	}

	public Date getDataInizioPagamento () {
		return dataInizioPagamento;
	}

	public void setDataInizioPagamento ( Date dataInizioPagamento ) {
		this.dataInizioPagamento = dataInizioPagamento;
	}

	public Date getDataFinePagamento () {
		return dataFinePagamento;
	}

	public void setDataFinePagamento ( Date dataFinePagamento ) {
		this.dataFinePagamento = dataFinePagamento;
	}

	public String getCfCittadino () {
		return cfCittadino;
	}

	public void setCfCittadino ( String cfCittadino ) {
		this.cfCittadino = cfCittadino;
	}

	public String getCodiceIpa () {
		return codiceIpa;
	}

	public void setCodiceIpa ( String codiceIpa ) {
		this.codiceIpa = codiceIpa;
	}
}
