/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResponseType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="ElencoProvvisori" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Provvisorio" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}ProvvisorioType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "elencoProvvisori"
})
@XmlRootElement(name = "RicercaProvvisoriPagoPAResponse", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
public class RicercaProvvisoriPagoPAResponse
extends ResponseType
{

    @XmlElement(name = "ElencoProvvisori", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected RicercaProvvisoriPagoPAResponse.ElencoProvvisori elencoProvvisori;

    /**
     * Gets the value of the elencoProvvisori property.
     *
     * @return
     *     possible object is
     *     {@link RicercaProvvisoriPagoPAResponse.ElencoProvvisori }
     *
     */
    public RicercaProvvisoriPagoPAResponse.ElencoProvvisori getElencoProvvisori() {
        return elencoProvvisori;
    }

    /**
     * Sets the value of the elencoProvvisori property.
     *
     * @param value
     *     allowed object is
     *     {@link RicercaProvvisoriPagoPAResponse.ElencoProvvisori }
     *
     */
    public void setElencoProvvisori(RicercaProvvisoriPagoPAResponse.ElencoProvvisori value) {
        this.elencoProvvisori = value;
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
     *         &lt;element name="Provvisorio" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}ProvvisorioType" maxOccurs="unbounded"/>
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
        "provvisorio"
    })
    public static class ElencoProvvisori {

        @XmlElement(name = "Provvisorio", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
        protected List<ProvvisorioType> provvisorio;

        /**
         * Gets the value of the provvisorio property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the provvisorio property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProvvisorio().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProvvisorioType }
         *
         *
         */
        public List<ProvvisorioType> getProvvisorio() {
            if (provvisorio == null) {
                provvisorio = new ArrayList<ProvvisorioType>();
            }
            return this.provvisorio;
        }

    }

}
