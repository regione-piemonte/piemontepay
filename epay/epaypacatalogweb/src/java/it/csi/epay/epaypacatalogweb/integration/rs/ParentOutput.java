/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import org.codehaus.jackson.annotate.JsonProperty;

public class ParentOutput {

	@JsonProperty("codiceEsito")
    protected String codiceEsito;
	
	@JsonProperty("codiceMessaggio")
    protected String codiceMessaggio;
	
	@JsonProperty("codiceStato")
    protected Integer codiceStato;
	
	@JsonProperty("descrizioneEsito")
    protected String descrizioneEsito;

    /**
     * Recupera il valore della propriet codiceEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Imposta il valore della propriet codiceEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsito(String value) {
        this.codiceEsito = value;
    }

    /**
     * Recupera il valore della propriet codiceMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceMessaggio() {
        return codiceMessaggio;
    }

    /**
     * Imposta il valore della propriet codiceMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceMessaggio(String value) {
        this.codiceMessaggio = value;
    }

    /**
     * Recupera il valore della propriet codiceStato.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodiceStato() {
        return codiceStato;
    }

    /**
     * Imposta il valore della propriet codiceStato.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodiceStato(Integer value) {
        this.codiceStato = value;
    }

    /**
     * Recupera il valore della propriet descrizioneEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneEsito() {
        return descrizioneEsito;
    }

    /**
     * Imposta il valore della propriet descrizioneEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneEsito(String value) {
        this.descrizioneEsito = value;
    }

}
