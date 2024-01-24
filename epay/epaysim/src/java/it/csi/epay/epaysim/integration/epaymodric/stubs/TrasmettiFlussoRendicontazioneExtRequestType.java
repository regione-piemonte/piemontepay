/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.integration.epaymodric.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrasmettiFlussoRendicontazioneExtRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrasmettiFlussoRendicontazioneExtRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}TestataFlussoRendicontazioneExtType" form="qualified"/>
 *         &lt;element name="FlussoRiversamento" type="{http://www.w3.org/2001/XMLSchema}base64Binary" form="qualified"/>
 *         &lt;element name="PagamentiIntermediati" form="qualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PagamentoIntermediato" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}PagamentoIntermediatoType" maxOccurs="unbounded" form="qualified"/>
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
@XmlType(name = "TrasmettiFlussoRendicontazioneExtRequestType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", propOrder = {
    "testata",
    "flussoRiversamento",
    "pagamentiIntermediati"
})
public class TrasmettiFlussoRendicontazioneExtRequestType {

    @XmlElement(name = "Testata", required = true)
    protected TestataFlussoRendicontazioneExtType testata;
    @XmlElement(name = "FlussoRiversamento", required = true)
    protected byte[] flussoRiversamento;
    @XmlElement(name = "PagamentiIntermediati", required = true)
    protected TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati pagamentiIntermediati;

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
     * Gets the value of the flussoRiversamento property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFlussoRiversamento() {
        return flussoRiversamento;
    }

    /**
     * Sets the value of the flussoRiversamento property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFlussoRiversamento(byte[] value) {
        this.flussoRiversamento = value;
    }

    /**
     * Gets the value of the pagamentiIntermediati property.
     * 
     * @return
     *     possible object is
     *     {@link TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati }
     *     
     */
    public TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati getPagamentiIntermediati() {
        return pagamentiIntermediati;
    }

    /**
     * Sets the value of the pagamentiIntermediati property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati }
     *     
     */
    public void setPagamentiIntermediati(TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati value) {
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
     *         &lt;element name="PagamentoIntermediato" type="{http://www.csi.it/epay/epaywso/rendicontazione-ext}PagamentoIntermediatoType" maxOccurs="unbounded" form="qualified"/>
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
