/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaTematicheUtenteTematicaInputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaTematicheUtenteTematicaInputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagVisibilitaTotale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idCodiciVersamento" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaTematicheUtenteTematicaInputDto", propOrder = {
    "codice",
    "flagVisibilitaTotale",
    "idCodiciVersamento"
})
public class AggiornaTematicheUtenteTematicaInputDto {

    protected String codice;
    protected Boolean flagVisibilitaTotale;
    @XmlElement(nillable = true)
    protected List<Long> idCodiciVersamento;

    /**
     * Gets the value of the codice property.
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
     * Sets the value of the codice property.
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
     * Gets the value of the flagVisibilitaTotale property.
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
     * Sets the value of the flagVisibilitaTotale property.
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
     * Gets the value of the idCodiciVersamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idCodiciVersamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdCodiciVersamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getIdCodiciVersamento() {
        if (idCodiciVersamento == null) {
            idCodiciVersamento = new ArrayList<Long>();
        }
        return this.idCodiciVersamento;
    }

}
