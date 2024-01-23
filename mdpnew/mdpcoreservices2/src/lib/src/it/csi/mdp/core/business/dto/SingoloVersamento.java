/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import it.csi.mdp.generatedvo.pagamenti.CtDatiMarcaBolloDigitale;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SingoloVersamento", propOrder = { "importoSingoloVersamento",
		"commissioneCaricoPA", "ibanAccredito", "bicAccredito", "ibanAppoggio",
		"bicAppoggio", "credenzialiPagatore", "causaleVersamento",
		"datiSpecificiRiscossione", "datiMarcaBolloDigitale" })

public class SingoloVersamento {

	@XmlElement(required = true)
	protected BigDecimal importoSingoloVersamento;
	protected BigDecimal commissioneCaricoPA;
	protected String ibanAccredito;
	protected String bicAccredito;
	protected String ibanAppoggio;
	protected String bicAppoggio;
	protected String credenzialiPagatore;
	@XmlElement(required = true)
	protected String causaleVersamento;
	@XmlElement(required = true)
	protected String datiSpecificiRiscossione;
	
	//Dati appiattiti Marca da bollo
    protected String tipoBollo;
    protected String hashDocumento;
    protected String provinciaResidenza;

	public BigDecimal getImportoSingoloVersamento() {
		return importoSingoloVersamento;
	}

	public void setImportoSingoloVersamento(BigDecimal importoSingoloVersamento) {
		this.importoSingoloVersamento = importoSingoloVersamento;
	}

	public BigDecimal getCommissioneCaricoPA() {
		return commissioneCaricoPA;
	}

	public void setCommissioneCaricoPA(BigDecimal commissioneCaricoPA) {
		this.commissioneCaricoPA = commissioneCaricoPA;
	}

	public String getIbanAccredito() {
		return ibanAccredito;
	}

	public void setIbanAccredito(String ibanAccredito) {
		this.ibanAccredito = ibanAccredito;
	}

	public String getBicAccredito() {
		return bicAccredito;
	}

	public void setBicAccredito(String bicAccredito) {
		this.bicAccredito = bicAccredito;
	}

	public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	public void setIbanAppoggio(String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}

	public String getBicAppoggio() {
		return bicAppoggio;
	}

	public void setBicAppoggio(String bicAppoggio) {
		this.bicAppoggio = bicAppoggio;
	}

	public String getCredenzialiPagatore() {
		return credenzialiPagatore;
	}

	public void setCredenzialiPagatore(String credenzialiPagatore) {
		this.credenzialiPagatore = credenzialiPagatore;
	}

	public String getCausaleVersamento() {
		return causaleVersamento;
	}

	public void setCausaleVersamento(String causaleVersamento) {
		this.causaleVersamento = causaleVersamento;
	}

	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public String getTipoBollo() {
		return tipoBollo;
	}

	public void setTipoBollo(String tipoBollo) {
		this.tipoBollo = tipoBollo;
	}

	public String getHashDocumento() {
		return hashDocumento;
	}

	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
	}

	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}
	

}
