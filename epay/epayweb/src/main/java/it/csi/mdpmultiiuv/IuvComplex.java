/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpmultiiuv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IuvComplex complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="IuvComplex">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auxDigit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iuvOttico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IuvComplex", propOrder = {
    "auxDigit",
    "applicationCode",
    "iuvOttico",
    "iuv"
})
public class IuvComplex {

    protected String auxDigit;
    protected String applicationCode;
    protected String iuvOttico;
    protected String iuv;

    /**
     * Recupera il valore della proprieta' auxDigit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuxDigit() {
        return auxDigit;
    }

    /**
     * Imposta il valore della proprieta' auxDigit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuxDigit(String value) {
        this.auxDigit = value;
    }

    /**
     * Recupera il valore della proprieta' applicationCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationCode() {
        return applicationCode;
    }

    /**
     * Imposta il valore della proprieta' applicationCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationCode(String value) {
        this.applicationCode = value;
    }

    /**
     * Recupera il valore della proprieta' iuvOttico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIuvOttico() {
        return iuvOttico;
    }

    /**
     * Imposta il valore della proprieta' iuvOttico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIuvOttico(String value) {
        this.iuvOttico = value;
    }

    /**
     * Recupera il valore della proprieta' iuv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIuv() {
        return iuv;
    }

    /**
     * Imposta il valore della proprieta' iuv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIuv(String value) {
        this.iuv = value;
    }

}
