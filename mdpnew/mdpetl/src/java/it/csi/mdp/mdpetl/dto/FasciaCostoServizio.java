/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class FasciaCostoServizio extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 8123585306931983223L;
	
	private Integer idinformativapsp;
	private BigDecimal importoMassimoFascia;
	private BigDecimal costoFisso;
	private BigDecimal valoreCommissione;
	
	
	public Integer getIdinformativapsp() {
		return idinformativapsp;
	}
	public void setIdinformativapsp(Integer idinformativapsp) {
		this.idinformativapsp = idinformativapsp;
	}
	public BigDecimal getImportoMassimoFascia() {
		return importoMassimoFascia;
	}
	public void setImportoMassimoFascia(BigDecimal importoMassimoFascia) {
		this.importoMassimoFascia = importoMassimoFascia;
	}
	public BigDecimal getCostoFisso() {
		return costoFisso;
	}
	public void setCostoFisso(BigDecimal costoFisso) {
		this.costoFisso = costoFisso;
	}
	public BigDecimal getValoreCommissione() {
		return valoreCommissione;
	}
	public void setValoreCommissione(BigDecimal valoreCommissione) {
		this.valoreCommissione = valoreCommissione;
	}

}
