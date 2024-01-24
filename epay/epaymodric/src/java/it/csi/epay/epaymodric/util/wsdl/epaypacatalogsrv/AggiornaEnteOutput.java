/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaEnteOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaEnteOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="codiceRisultatoSincronizzazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneRisultatoSincronizzazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaEnteOutput", propOrder = {
    "codiceRisultatoSincronizzazione",
    "descrizioneRisultatoSincronizzazione"
})
public class AggiornaEnteOutput
    extends ParentOutput
{

    protected String codiceRisultatoSincronizzazione;
    protected String descrizioneRisultatoSincronizzazione;

    /**
     * Gets the value of the codiceRisultatoSincronizzazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRisultatoSincronizzazione() {
        return codiceRisultatoSincronizzazione;
    }

    /**
     * Sets the value of the codiceRisultatoSincronizzazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRisultatoSincronizzazione(String value) {
        this.codiceRisultatoSincronizzazione = value;
    }

    /**
     * Gets the value of the descrizioneRisultatoSincronizzazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneRisultatoSincronizzazione() {
        return descrizioneRisultatoSincronizzazione;
    }

    /**
     * Sets the value of the descrizioneRisultatoSincronizzazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneRisultatoSincronizzazione(String value) {
        this.descrizioneRisultatoSincronizzazione = value;
    }

}
