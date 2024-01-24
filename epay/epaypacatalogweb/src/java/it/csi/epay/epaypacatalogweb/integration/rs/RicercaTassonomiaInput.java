/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class RicercaTassonomiaInput extends ParentInput {

	private static final long serialVersionUID = 4627462082826393648L;
	
	@JsonProperty("codiceEnteCreditore")
    private String codiceEnteCreditore;
	 
	@JsonProperty("tipoServizio")
	 private String tipoServizio;
	 
	@JsonProperty("datiSpecificiIncasso")
	 private String datiSpecificiIncasso;
	 
	@JsonProperty("dataInizioValidita")
	 private Date dataInizioValidita;
	 
	 
	@JsonProperty("dataFineValidita")
	 private Date dataFineValidita;


	/**
	 * @return the codiceEnteCreditore
	 */
	public String getCodiceEnteCreditore() {
		return codiceEnteCreditore;
	}


	/**
	 * @param codiceEnteCreditore the codiceEnteCreditore to set
	 */
	public void setCodiceEnteCreditore(String codiceEnteCreditore) {
		this.codiceEnteCreditore = codiceEnteCreditore;
	}


	/**
	 * @return the tipoServizio
	 */
	public String getTipoServizio() {
		return tipoServizio;
	}


	/**
	 * @param tipoServizio the tipoServizio to set
	 */
	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}


	/**
	 * @return the datiSpecificiIncasso
	 */
	public String getDatiSpecificiIncasso() {
		return datiSpecificiIncasso;
	}


	/**
	 * @param datiSpecificiIncasso the datiSpecificiIncasso to set
	 */
	public void setDatiSpecificiIncasso(String datiSpecificiIncasso) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}


	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}


	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}


	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}


	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

    

}
