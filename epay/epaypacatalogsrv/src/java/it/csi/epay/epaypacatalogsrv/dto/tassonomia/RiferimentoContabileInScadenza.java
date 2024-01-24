/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;
import java.io.Serializable;


public class RiferimentoContabileInScadenza implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String datoSpecificoRiscossione;
	
	private String annoAccertamento;
	
	private String numAccertamento;
		
	private String dataFineValidita;
	
	
	private CodiceVersamento codiceVersamento;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDatoSpecificoRiscossione() {
		return datoSpecificoRiscossione;
	}


	public void setDatoSpecificoRiscossione(String datoSpecificoRiscossione) {
		this.datoSpecificoRiscossione = datoSpecificoRiscossione;
	}


	public String getAnnoAccertamento() {
		return annoAccertamento;
	}


	public void setAnnoAccertamento(String annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}


	public String getNumAccertamento() {
		return numAccertamento;
	}


	public void setNumAccertamento(String numAccertamento) {
		this.numAccertamento = numAccertamento;
	}


	public String getDataFineValidita() {
		return dataFineValidita;
	}


	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}


	public CodiceVersamento getCodiceVersamento() {
		return codiceVersamento;
	}


	public void setCodiceVersamento(CodiceVersamento codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}
	
	
	

	
    

    

}
