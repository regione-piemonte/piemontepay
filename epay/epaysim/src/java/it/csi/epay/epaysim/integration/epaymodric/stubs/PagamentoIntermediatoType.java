/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.integration.epaymodric.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PagamentoIntermediatoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PagamentoIntermediatoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiSingoliPagamenti" form="qualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DatiSingoloPagamento" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}DatiSingoloPagamentoType" form="qualified"/>
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
@XmlType(name = "PagamentoIntermediatoType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", propOrder = {
    "datiSingoliPagamenti"
})
public class PagamentoIntermediatoType {

    @XmlElement(name = "DatiSingoliPagamenti", required = true)
    protected PagamentoIntermediatoType.DatiSingoliPagamenti datiSingoliPagamenti;

    /**
     * Gets the value of the datiSingoliPagamenti property.
     * 
     * @return
     *     possible object is
     *     {@link PagamentoIntermediatoType.DatiSingoliPagamenti }
     *     
     */
    public PagamentoIntermediatoType.DatiSingoliPagamenti getDatiSingoliPagamenti() {
        return datiSingoliPagamenti;
    }

    /**
     * Sets the value of the datiSingoliPagamenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link PagamentoIntermediatoType.DatiSingoliPagamenti }
     *     
     */
    public void setDatiSingoliPagamenti(PagamentoIntermediatoType.DatiSingoliPagamenti value) {
        this.datiSingoliPagamenti = value;
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
     *         &lt;element name="DatiSingoloPagamento" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}DatiSingoloPagamentoType" form="qualified"/>
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
        "datiSingoloPagamento"
    })
    public static class DatiSingoliPagamenti {

        @XmlElement(name = "DatiSingoloPagamento", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", required = true)
        protected DatiSingoloPagamentoType datiSingoloPagamento;

        /**
         * Gets the value of the datiSingoloPagamento property.
         * 
         * @return
         *     possible object is
         *     {@link DatiSingoloPagamentoType }
         *     
         */
        public DatiSingoloPagamentoType getDatiSingoloPagamento() {
            return datiSingoloPagamento;
        }

        /**
         * Sets the value of the datiSingoloPagamento property.
         * 
         * @param value
         *     allowed object is
         *     {@link DatiSingoloPagamentoType }
         *     
         */
        public void setDatiSingoloPagamento(DatiSingoloPagamentoType value) {
            this.datiSingoloPagamento = value;
        }

    }

}
