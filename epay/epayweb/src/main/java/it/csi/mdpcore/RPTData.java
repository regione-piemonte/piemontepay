/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per RPTData complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RPTData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autenticazioneSoggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idStazioneRichiedente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soggettoVersante" type="{http://interfacecxf.core.mdp.csi.it/}SoggettoVersante" minOccurs="0"/>
 *         &lt;element name="soggettoPagatore" type="{http://interfacecxf.core.mdp.csi.it/}SoggettoPagatore"/>
 *         &lt;element name="datiVersamento" type="{http://interfacecxf.core.mdp.csi.it/}DatiVersamentoRPT"/>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RPTData", propOrder = {
    "autenticazioneSoggetto",
    "idStazioneRichiedente",
    "soggettoVersante",
    "soggettoPagatore",
    "datiVersamento",
    "applicationId",
    "datiAccertamento"
})
public class RPTData {

    @XmlElement(required = true)
    protected String autenticazioneSoggetto;
    protected String idStazioneRichiedente;
    protected SoggettoVersante soggettoVersante;
    @XmlElement(required = true)
    protected SoggettoPagatore soggettoPagatore;
    @XmlElement(required = true)
    protected DatiVersamentoRPT datiVersamento;
    @XmlElement(required = true)
    protected String applicationId;

    protected DatiAccertamentoRPT datiAccertamento;
    
    /**
     * Recupera il valore della proprieta' autenticazioneSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutenticazioneSoggetto() {
        return autenticazioneSoggetto;
    }

    /**
     * Imposta il valore della proprieta' autenticazioneSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutenticazioneSoggetto(String value) {
        this.autenticazioneSoggetto = value;
    }

    /**
     * Recupera il valore della proprieta' idStazioneRichiedente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStazioneRichiedente() {
        return idStazioneRichiedente;
    }

    /**
     * Imposta il valore della proprieta' idStazioneRichiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStazioneRichiedente(String value) {
        this.idStazioneRichiedente = value;
    }

    /**
     * Recupera il valore della proprieta' soggettoVersante.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoVersante }
     *     
     */
    public SoggettoVersante getSoggettoVersante() {
        return soggettoVersante;
    }

    /**
     * Imposta il valore della proprieta' soggettoVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoVersante }
     *     
     */
    public void setSoggettoVersante(SoggettoVersante value) {
        this.soggettoVersante = value;
    }

    /**
     * Recupera il valore della proprieta' soggettoPagatore.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoPagatore }
     *     
     */
    public SoggettoPagatore getSoggettoPagatore() {
        return soggettoPagatore;
    }

    /**
     * Imposta il valore della proprieta' soggettoPagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoPagatore }
     *     
     */
    public void setSoggettoPagatore(SoggettoPagatore value) {
        this.soggettoPagatore = value;
    }

    /**
     * Recupera il valore della proprieta' datiVersamento.
     * 
     * @return
     *     possible object is
     *     {@link DatiVersamentoRPT }
     *     
     */
    public DatiVersamentoRPT getDatiVersamento() {
        return datiVersamento;
    }

    /**
     * Imposta il valore della proprieta' datiVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiVersamentoRPT }
     *     
     */
    public void setDatiVersamento(DatiVersamentoRPT value) {
        this.datiVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' applicationId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Imposta il valore della proprieta' applicationId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    
    public DatiAccertamentoRPT getDatiAccertamento () {
        return datiAccertamento;
    }

    
    public void setDatiAccertamento ( DatiAccertamentoRPT datiAccertamento ) {
        this.datiAccertamento = datiAccertamento;
    }

    
}
