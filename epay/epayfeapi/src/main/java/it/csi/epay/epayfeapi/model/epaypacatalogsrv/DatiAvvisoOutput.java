/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.model.epaypacatalogsrv;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


@SuppressWarnings ( "unused" )
public class DatiAvvisoOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty ( "codiceEsito" )
    private String codiceEsito;

    @JsonProperty ( "descrizioneEsito" )
    private String descrizioneEsito;

    @JsonProperty ( "numeroAutorizzazione" )
    private String numeroAutorizzazione;
    
    @JsonProperty ( "ibanAppoggio" )
    private String ibanAppoggio;
    
    @JsonProperty ( "ibanTesoreria" )
    private String ibanTesoreria;
    
    @JsonProperty ( "intestatarioPostale" )
    private String intestatarioPostale;
    
    
    @JsonProperty ( "settore" )
    private String settore;
    
    @JsonProperty ( "indirizzo" )
    private String indirizzo;
    
    @JsonProperty ( "localita" )
    private String localita;
    
    @JsonProperty ( "cap" )
    private String cap;
    
    @JsonProperty ( "siglaProvincia" )
    private String siglaProvincia;
    
    @JsonProperty ( "email" )
    private String email;
    
    @JsonProperty ( "infoEnte" )
    private String infoEnte;
    
    
    

	public String getNumeroAutorizzazione() {
		return numeroAutorizzazione;
	}

	public String getCodiceEsito() {
		return codiceEsito;
	}

	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}

	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}

	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}

	public void setNumeroAutorizzazione(String numeroAutorizzazione) {
		this.numeroAutorizzazione = numeroAutorizzazione;
	}

	public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	public void setIbanAppoggio(String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}

	public String getIbanTesoreria() {
		return ibanTesoreria;
	}

	public void setIbanTesoreria(String ibanTesoreria) {
		this.ibanTesoreria = ibanTesoreria;
	}

	public String getIntestatarioPostale() {
		return intestatarioPostale;
	}

	public void setIntestatarioPostale(String intestatarioPostale) {
		this.intestatarioPostale = intestatarioPostale;
	}

	public String getSettore() {
		return settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfoEnte() {
		return infoEnte;
	}

	public void setInfoEnte(String infoEnte) {
		this.infoEnte = infoEnte;
	}
    
  

}
