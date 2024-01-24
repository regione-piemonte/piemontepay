/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class GetProfilazioneUtenteCorrenteTematicaOutputDto {
	
	@JsonProperty("codice")
    protected String codice;
	
    @JsonProperty("codiciVersamento")
    protected List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto> codiciVersamento;//not null
    
    @JsonProperty("descrizione")
    protected String descrizione;
    
    @JsonProperty("flagVisibilitaTotale")
    protected Boolean flagVisibilitaTotale;
    
    @JsonProperty("id")
    protected Long id;

    /**
     * Recupera il valore della propriet codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della propriet codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the codiciVersamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codiciVersamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodiciVersamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto }
     * 
     * 
     */
    public List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto> getCodiciVersamento() {
        if (codiciVersamento == null) {
            codiciVersamento = new ArrayList<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto>();
        }
        return this.codiciVersamento;
    }

    /**
     * Recupera il valore della propriet descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della propriet descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della propriet flagVisibilitaTotale.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagVisibilitaTotale() {
        return flagVisibilitaTotale;
    }

    /**
     * Imposta il valore della propriet flagVisibilitaTotale.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagVisibilitaTotale(Boolean value) {
        this.flagVisibilitaTotale = value;
    }

    /**
     * Recupera il valore della propriet id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

}
