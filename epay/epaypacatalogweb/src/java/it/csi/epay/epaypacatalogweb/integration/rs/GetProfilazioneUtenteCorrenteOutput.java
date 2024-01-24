/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;



public class GetProfilazioneUtenteCorrenteOutput
    extends ParentOutput
{

	@JsonProperty("ente")
    protected GetProfilazioneUtenteCorrenteEnteOutputDto ente;
	
	@JsonProperty("tematiche")
    protected List<GetProfilazioneUtenteCorrenteTematicaOutputDto> tematiche;//not null
	
	@JsonProperty("utente")
    protected GetProfilazioneUtenteCorrenteOutputDto utente;

    /**
     * Recupera il valore della propriet ente.
     * 
     * @return
     *     possible object is
     *     {@link GetProfilazioneUtenteCorrenteEnteOutputDto }
     *     
     */
    public GetProfilazioneUtenteCorrenteEnteOutputDto getEnte() {
        return ente;
    }

    /**
     * Imposta il valore della propriet ente.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProfilazioneUtenteCorrenteEnteOutputDto }
     *     
     */
    public void setEnte(GetProfilazioneUtenteCorrenteEnteOutputDto value) {
        this.ente = value;
    }

    /**
     * Gets the value of the tematiche property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tematiche property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTematiche().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetProfilazioneUtenteCorrenteTematicaOutputDto }
     * 
     * 
     */
    public List<GetProfilazioneUtenteCorrenteTematicaOutputDto> getTematiche() {
        if (tematiche == null) {
            tematiche = new ArrayList<GetProfilazioneUtenteCorrenteTematicaOutputDto>();
        }
        return this.tematiche;
    }

    /**
     * Recupera il valore della propriet utente.
     * 
     * @return
     *     possible object is
     *     {@link GetProfilazioneUtenteCorrenteOutputDto }
     *     
     */
    public GetProfilazioneUtenteCorrenteOutputDto getUtente() {
        return utente;
    }

    /**
     * Imposta il valore della propriet utente.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProfilazioneUtenteCorrenteOutputDto }
     *     
     */
    public void setUtente(GetProfilazioneUtenteCorrenteOutputDto value) {
        this.utente = value;
    }

}
