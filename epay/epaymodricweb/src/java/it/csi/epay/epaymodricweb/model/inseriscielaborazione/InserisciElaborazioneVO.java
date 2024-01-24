/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.inseriscielaborazione;

import java.io.Serializable;

import it.csi.epay.epaymodricweb.model.DTOBaseResponseVO;

public class InserisciElaborazioneVO extends DTOBaseResponseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String risultatoOutputEstemporanea;
	
	private String idEnte;
	
	private Long idElaborazione;

	public InserisciElaborazioneVO() {
		super();
		
	}

	public String getRisultatoOutputEstemporanea() {
		return risultatoOutputEstemporanea;
	}

	public void setRisultatoOutputEstemporanea(String risultatoOutputEstemporanea) {
		this.risultatoOutputEstemporanea = risultatoOutputEstemporanea;
	}

	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public Long getIdElaborazione() {
		return idElaborazione;
	}

	public void setIdElaborazione(Long idElaborazione) {
		this.idElaborazione = idElaborazione;
	}
	
	
	

}
