/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ComponenteImportoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal importo;
	private String causale;
	private String datiSpecificiRiscossione;
	private Integer annoAccertamento;
	private Integer numeroAccertamento;

	private Boolean flagComponenteSecondario;

	public ComponenteImportoDto() {
	}

	public ComponenteImportoDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

	public Integer getNumeroAccertamento() {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento(Integer numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public Boolean getFlagComponenteSecondario () {
		return flagComponenteSecondario;
	}

	public void setFlagComponenteSecondario ( Boolean flagComponenteSecondario ) {
		this.flagComponenteSecondario = flagComponenteSecondario;
	}

	@Override
	public String toString () {
		return "ComponenteImportoDto [id=" + id + ", importo=" + importo + ", causale=" + causale + ", datiSpecificiRiscossione=" + datiSpecificiRiscossione
						+ ", annoAccertamento=" + annoAccertamento + ", numeroAccertamento=" + numeroAccertamento + ", flagComponenteSecondario=" + flagComponenteSecondario
						+ "]";
	}

}
