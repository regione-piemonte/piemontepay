/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class ComponentePagamentoEnteSecondaroInput implements Serializable {

	private static final long serialVersionUID = -2875552361800653878L;

	private String codiceFiscaleEnte;

	private String codiceVersamento;

	private Integer annoAccertamento;

	private String causale;

	private String datiSpecificiRiscossione;

	private BigDecimal importo;

	private String numeroAccertamento;

	private Integer progressivo;

	public String getCodiceFiscaleEnte () {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getCodiceVersamento () {
		return codiceVersamento;
	}

	public void setCodiceVersamento ( String codiceVersamento ) {
		this.codiceVersamento = codiceVersamento;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
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

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public Integer getProgressivo () {
		return progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}
}
