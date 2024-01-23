/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;


@SuppressWarnings ( "unused" )
public class PagamentoSecondarioComponentiDTO implements Serializable {

	private static final long serialVersionUID = 2046041118339613238L;

	private Long idComponenteSecondaria;

	private Long idPagamentoSecondario;

	private Integer progressivo;

	private String causale;

	private String datiSpecificiRiscossione;

	private Integer annoAccertamento;

	private String numeroAccertamento;

	private BigDecimal importo;

	private String utenteUltimaModifica;

	private String applicationId;

	private String codiceTributo;

	public Long getIdComponenteSecondaria () {
		return idComponenteSecondaria;
	}

	public void setIdComponenteSecondaria ( Long idComponenteSecondaria ) {
		this.idComponenteSecondaria = idComponenteSecondaria;
	}

	public Long getIdPagamentoSecondario () {
		return idPagamentoSecondario;
	}

	public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
		this.idPagamentoSecondario = idPagamentoSecondario;
	}

	public Integer getProgressivo () {
		return progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}

	public String getCausale () {
		return causale;
	}

	public void setCausale ( String causale ) {
		this.causale = causale;
	}

	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getUtenteUltimaModifica () {
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica ( String utenteUltimaModifica ) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}

	public String getApplicationId () {
		return applicationId;
	}

	public void setApplicationId ( String applicationId ) {
		this.applicationId = applicationId;
	}

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

}
