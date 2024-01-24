/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.cooppec.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CodiciVersamentoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CodiciVersamentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElencoCodiciVersamento">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodiciVersamentoType", propOrder = {
    "elencoCodiciVersamento"
})
public class CodiciVersamentoType {

    @XmlElement(name = "ElencoCodiciVersamento", required = true)
    protected CodiciVersamentoType.ElencoCodiciVersamento elencoCodiciVersamento;

    /**
     * Gets the value of the elencoCodiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link CodiciVersamentoType.ElencoCodiciVersamento }
     *     
     */
    public CodiciVersamentoType.ElencoCodiciVersamento getElencoCodiciVersamento() {
        return elencoCodiciVersamento;
    }

    /**
     * Sets the value of the elencoCodiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiciVersamentoType.ElencoCodiciVersamento }
     *     
     */
    public void setElencoCodiciVersamento(CodiciVersamentoType.ElencoCodiciVersamento value) {
        this.elencoCodiciVersamento = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "codiceVersamento"
    })
    public static class ElencoCodiciVersamento {

        @XmlElement(name = "CodiceVersamento")
        protected List<String> codiceVersamento;

        /**
         * Gets the value of the codiceVersamento property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the codiceVersamento property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCodiceVersamento().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCodiceVersamento() {
            if (codiceVersamento == null) {
                codiceVersamento = new ArrayList<String>();
            }
            return this.codiceVersamento;
        }

    }

}
