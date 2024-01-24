/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.util.Date;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RicercaTassonomiaInput extends ParentInput {
	
	private static final long serialVersionUID = -3116620848974475439L;
	
	
	 private String nomeMacroArea;
	 
	 private String tipoServizio;
	 
	 private String datiSpecificiIncasso;
	 
	 private Date dataInizioValidita;
	 
	 private Date dataFineValidita;



	
    /**
	 * @return the nomeMacroArea
	 */
	public String getNomeMacroArea() {
		return nomeMacroArea;
	}




	/**
	 * @param nomeMacroArea the nomeMacroArea to set
	 */
	public void setNomeMacroArea(String nomeMacroArea) {
		this.nomeMacroArea = nomeMacroArea;
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




	@Override
    public String toString () {
        return "RicercaTassonomiaInput ["+ "]";
    }

   
}
