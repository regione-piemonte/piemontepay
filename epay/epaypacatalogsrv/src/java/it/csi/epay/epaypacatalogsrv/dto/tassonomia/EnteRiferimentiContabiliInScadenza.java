/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;
import java.io.Serializable;


public class EnteRiferimentiContabiliInScadenza implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idEnte;

    private String codiceFiscale;
    
	private String denominazione;
	
	
	private String emailAddress;

    
    /**
     * @return the idEnte
     */
    public String getIdEnte() {
		return idEnte;
	}

    /**
     * @param idEnte the idEnte to set
     */
	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}
	
	 /**
     * @return the codiceFiscale
     */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	
	 /**
     * @param codiceFiscale the codiceFiscale to set
     */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

    
    

    

}
