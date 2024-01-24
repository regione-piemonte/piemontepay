/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;


@SuppressWarnings ( "unused" )
public class ComponentiImporto implements Serializable {

	private static final long serialVersionUID = 4772299633029031248L;

	private BigDecimal importo;

	private String causaleDescrittiva;

	private Integer annoAccertamento;

	private String numeroAccertamento;

	private String causale;

	private String datiSpecificiRiscossione;

	private Integer progressivo;

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getCausaleDescrittiva () {
		return causaleDescrittiva;
	}

	public void setCausaleDescrittiva ( String causaleDescrittiva ) {
		this.causaleDescrittiva = causaleDescrittiva;
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

	public Integer getProgressivo () {
		return progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}
}
