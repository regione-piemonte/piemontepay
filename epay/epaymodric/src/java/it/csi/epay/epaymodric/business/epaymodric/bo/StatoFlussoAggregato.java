/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;


public class StatoFlussoAggregato implements Serializable{


	private static final long serialVersionUID = 3238276942376029202L;

	private Long id;

    private String statoFlusso;

    private String datoAggiuntivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatoFlusso() {
		return statoFlusso;
	}

	public void setStatoFlusso(String statoFlusso) {
		this.statoFlusso = statoFlusso;
	}

	public String getDatoAggiuntivo() {
		return datoAggiuntivo;
	}

	public void setDatoAggiuntivo(String datoAggiuntivo) {
		this.datoAggiuntivo = datoAggiuntivo;
	}
    
    
    
    
    

}
