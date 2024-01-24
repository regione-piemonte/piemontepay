/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per parentOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="parentOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceMessaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceStato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parentOutput", propOrder = {
    "codiceEsito",
    "codiceMessaggio",
    "codiceStato",
    "descrizioneEsito"
})
@XmlSeeAlso({
    InserisciProvvisorioOutputResponse.class,
    InserisciProvvisorioOutputDTO.class,
    UpdateEsitoStatoElaborazioneFlussoOutputDTO.class,
    RicercaFlussoOutputResponse.class,
    FlussoOriginePagopaOutputDTO.class,
    FlussoSintesiPagopaOutputDTO.class,
    FlussoDettaglioPagopaOutputDTO.class
})
public class ParentOutput {

    protected String codiceEsito;
    protected String codiceMessaggio;
    protected Integer codiceStato;
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
