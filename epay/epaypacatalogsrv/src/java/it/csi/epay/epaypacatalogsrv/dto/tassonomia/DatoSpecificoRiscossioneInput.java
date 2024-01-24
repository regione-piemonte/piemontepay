/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.io.Serializable;
import java.util.List;


public class DatoSpecificoRiscossioneInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String codiceFiscaleEnte;
	
	private String tipoPagamento;
	
	private List<ComponentiAccertamento> componentiAccertamento;

		
	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<ComponentiAccertamento> getComponentiAccertamento() {
		return componentiAccertamento;
	}

	public void setComponentiAccertamento(List<ComponentiAccertamento> componentiAccertamento) {
		this.componentiAccertamento = componentiAccertamento;
	}


		
	


}
