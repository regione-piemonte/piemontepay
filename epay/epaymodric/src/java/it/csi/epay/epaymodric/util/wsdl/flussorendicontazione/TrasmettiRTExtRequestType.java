/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.flussorendicontazione;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrasmettiRTExtRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TrasmettiRTExtRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}TestataFlussoRendicontazioneExtType"/>
 *         &lt;element name="FlussoRT" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="PagamentiIntermediati">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PagamentoIntermediato" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}PagamentoIntermediatoType" maxOccurs="unbounded"/>
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrasmettiRTExtRequestType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", propOrder = {
    "testata",
    "flussoRT",
    "pagamentiIntermediati"
})
public class TrasmettiRTExtRequestType {

    @XmlElement(name = "Testata", required = true)
    protected TestataFlussoRendicontazioneExtType testata;
    @XmlElement(name = "FlussoRT", required = true)
    protected byte[] flussoRT;
    @XmlElement(name = "PagamentiIntermediati", required = true)
    protected TrasmettiRTExtRequestType.PagamentiIntermediati pagamentiIntermediati;

    /**
     * Gets the value of the testata property.
     *
     * @return
     *     possible object is
     *     {@link TestataFlussoRendicontazioneExtType }
     *
     */
    public TestataFlussoRendicontazioneExtType getTestata() {
        return testata;
    }

    /**
     * Sets the value of the testata property.
     *
     * @param value
     *     allowed object is
     *     {@link TestataFlussoRendicontazioneExtType }
     *
     */
    public void setTestata(TestataFlussoRendicontazioneExtType value) {
        this.testata = value;
    }

    /**
     * Gets the value of the flussoRT property.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFlussoRT() {
        return flussoRT;
    }

    /**
     * Sets the value of the flussoRT property.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFlussoRT(byte[] value) {
        this.flussoRT = value;
    }

    /**
     * Gets the value of the pagamentiIntermediati property.
     *
     * @return
     *     possible object is
     *     {@link TrasmettiRTExtRequestType.PagamentiIntermediati }
     *
     */
    public TrasmettiRTExtRequestType.PagamentiIntermediati getPagamentiIntermediati() {
        return pagamentiIntermediati;
    }

    /**
     * Sets the value of the pagamentiIntermediati property.
     *
     * @param value
     *     allowed object is
     *     {@link TrasmettiRTExtRequestType.PagamentiIntermediati }
     *
     */
    public void setPagamentiIntermediati(TrasmettiRTExtRequestType.PagamentiIntermediati value) {
        this.pagamentiIntermediati = value;
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
     *         &lt;element name="PagamentoIntermediato" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}PagamentoIntermediatoType" maxOccurs="unbounded"/>
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
        "pagamentoIntermediato"
    })
    public static class PagamentiIntermediati {

        @XmlElement(name = "PagamentoIntermediato", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", required = true)
        protected List<PagamentoIntermediatoType> pagamentoIntermediato;

        /**
         * Gets the value of the pagamentoIntermediato property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pagamentoIntermediato property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPagamentoIntermediato().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PagamentoIntermediatoType }
         *
         *
         */
        public List<PagamentoIntermediatoType> getPagamentoIntermediato() {
            if (pagamentoIntermediato == null) {
                pagamentoIntermediato = new ArrayList<PagamentoIntermediatoType>();
            }
            return this.pagamentoIntermediato;
        }

    }

}
