/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings ( "unused" )
public class QuietanzaDaElaborareDto implements Serializable {

	private static final long serialVersionUID = -8535714992156538293L;

	private Long idQuietanzaDaElaborare;

	private String fruitoreEsterno;

	private String tipoElaborazione;

	private Date dataPrimaChiamata;

	private Date dataInserimento;

	private String identificativoChiamata;

	private String codEsito;

	private Boolean inviare;

	private Integer nrInvii;

	private Date invioFallitoGiornaliero;

	private Integer nrGiorni;

	private byte[] ricevutaPdf;

	private Long idQuietanzaEsito;

	private Long idRt;

	private String iuv;

	private Boolean terminiScaduti;

	private String causalePagamento;

	private Date dataInizioPagamento;

	private Date dataFinePagamento;

	private String cfCittadino;

	private String codiceIpa;

	public Long getIdQuietanzaDaElaborare () {
		return idQuietanzaDaElaborare;
	}

	public void setIdQuietanzaDaElaborare ( Long idQuietanzaDaElaborare ) {
		this.idQuietanzaDaElaborare = idQuietanzaDaElaborare;
	}

	public String getFruitoreEsterno () {
		return fruitoreEsterno;
	}

	public void setFruitoreEsterno ( String fruitoreEsterno ) {
		this.fruitoreEsterno = fruitoreEsterno;
	}

	public String getTipoElaborazione () {
		return tipoElaborazione;
	}

	public void setTipoElaborazione ( String tipoElaborazione ) {
		this.tipoElaborazione = tipoElaborazione;
	}

	public Date getDataPrimaChiamata () {
		return dataPrimaChiamata;
	}

	public void setDataPrimaChiamata ( Date dataPrimaChiamata ) {
		this.dataPrimaChiamata = dataPrimaChiamata;
	}

	public Date getDataInserimento () {
		return dataInserimento;
	}

	public void setDataInserimento ( Date dataInserimento ) {
		this.dataInserimento = dataInserimento;
	}

	public String getIdentificativoChiamata () {
		return identificativoChiamata;
	}

	public void setIdentificativoChiamata ( String identificativoChiamata ) {
		this.identificativoChiamata = identificativoChiamata;
	}

	public String getCodEsito () {
		return codEsito;
	}

	public void setCodEsito ( String codEsito ) {
		this.codEsito = codEsito;
	}

	public Boolean getInviare () {
		return inviare;
	}

	public void setInviare ( Boolean inviare ) {
		this.inviare = inviare;
	}

	public Integer getNrInvii () {
		return nrInvii;
	}

	public void setNrInvii ( Integer nrInvii ) {
		this.nrInvii = nrInvii;
	}

	public Date getInvioFallitoGiornaliero () {
		return invioFallitoGiornaliero;
	}

	public void setInvioFallitoGiornaliero ( Date invioFallitoGiornaliero ) {
		this.invioFallitoGiornaliero = invioFallitoGiornaliero;
	}

	public Integer getNrGiorni () {
		return nrGiorni;
	}

	public void setNrGiorni ( Integer nrGiorni ) {
		this.nrGiorni = nrGiorni;
	}

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public Long getIdQuietanzaEsito () {
		return idQuietanzaEsito;
	}

	public void setIdQuietanzaEsito ( Long idQuietanzaEsito ) {
		this.idQuietanzaEsito = idQuietanzaEsito;
	}

	public Long getIdRt () {
		return idRt;
	}

	public void setIdRt ( Long idRt ) {
		this.idRt = idRt;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Boolean getTerminiScaduti () {
		return terminiScaduti;
	}

	public void setTerminiScaduti ( Boolean terminiScaduti ) {
		this.terminiScaduti = terminiScaduti;
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

	@Override
	public String toString () {
		return "{ " +
			"idQuietanzaDaElaborare:" + idQuietanzaDaElaborare +
			", fruitoreEsterno:" + fruitoreEsterno +
			", tipoElaborazione:" + tipoElaborazione +
			", dataPrimaChiamata:" + dataPrimaChiamata +
			", dataInserimento:" + dataInserimento +
			", identificativoChiamata:" + identificativoChiamata +
			", codEsito:" + codEsito +
			", inviare:" + inviare +
			", nrInvii:" + nrInvii +
			", invioFallitoGiornaliero:" + invioFallitoGiornaliero +
			", nrGiorni:" + nrGiorni +
			", ricevutaPdf" + ( ricevutaPdf != null ? " di" + ricevutaPdf.length + " bytes" : ":NULL" ) +
			", idQuietanzaEsito:" + idQuietanzaEsito +
			", idRt:" + idRt +
			", iuv:" + iuv +
			", terminiScaduti:" + terminiScaduti +
			", causalePagamento:" + causalePagamento +
			", dataInizioPagamento:" + dataInizioPagamento +
			", dataFinePagamento:" + dataFinePagamento +
			", cfCittadino:" + cfCittadino +
			", codiceIpa:" + codiceIpa +
			" }";
	}
}
