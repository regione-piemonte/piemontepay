/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.inseriscielaborazione;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaymodricweb.util.SecurityUtils;

public class InserisciElaborazioneFiltroVO {
	
	 private String codiceFiscaleEnte;
	 
	 private String idEnte;
		
	 private List<String> flussiDaElaborare = new ArrayList<String>();
	    
	 private Boolean isRiesecuzione;
	    
	 private Long idElaborazionePrecedente;
	    
	 private String statoDaImpostare;
	 
	 private String utenteElaborazione;

	 public InserisciElaborazioneFiltroVO() {
		 utenteElaborazione = SecurityUtils.getUser().getUtente().getCodiceFiscale();
	
	}

	

	public List<String> getFlussiDaElaborare() {
		return flussiDaElaborare;
	}

	public void setFlussiDaElaborare(List<String> flussiDaElaborare) {
		this.flussiDaElaborare = flussiDaElaborare;
	}

	public Boolean getIsRiesecuzione() {
		return isRiesecuzione;
	}

	public void setIsRiesecuzione(Boolean isRiesecuzione) {
		this.isRiesecuzione = isRiesecuzione;
	}

	public Long getIdElaborazionePrecedente() {
		return idElaborazionePrecedente;
	}

	public void setIdElaborazionePrecedente(Long idElaborazionePrecedente) {
		this.idElaborazionePrecedente = idElaborazionePrecedente;
	}

	public String getStatoDaImpostare() {
		return statoDaImpostare;
	}

	public void setStatoDaImpostare(String statoDaImpostare) {
		this.statoDaImpostare = statoDaImpostare;
	}



	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}



	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}



	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}



	public String getUtenteElaborazione() {
		return utenteElaborazione;
	}



	public void setUtenteElaborazione(String utenteElaborazione) {
		this.utenteElaborazione = utenteElaborazione;
	}
	 
	
	 
	 
	 
		

}
