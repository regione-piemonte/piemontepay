/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.25 alle 11:56:43 AM CEST 
//


package it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per EndpointType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EndpointType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Protocol"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="http"/&gt;
 *               &lt;enumeration value="https"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Host" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Context" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Port" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="CredenzialiAutenticazione" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="NomeUtente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndpointType", propOrder = {
    "protocol",
    "host",
    "context",
    "port",
    "credenzialiAutenticazione"
})
public class EndpointType {

    @XmlElement(name = "Protocol", required = true, defaultValue = "http")
    protected String protocol;
    @XmlElement(name = "Host", required = true)
    protected String host;
    @XmlElement(name = "Context", required = true)
    protected String context;
    @XmlElement(name = "Port")
    protected Integer port;
    @XmlElement(name = "CredenzialiAutenticazione")
    protected EndpointType.CredenzialiAutenticazione credenzialiAutenticazione;

    /**
     * Recupera il valore della propriet protocol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Imposta il valore della propriet protocol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    /**
     * Recupera il valore della propriet host.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Imposta il valore della propriet host.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Recupera il valore della propriet context.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContext() {
        return context;
    }

    /**
     * Imposta il valore della propriet context.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContext(String value) {
        this.context = value;
    }

    /**
     * Recupera il valore della propriet port.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Imposta il valore della propriet port.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPort(Integer value) {
        this.port = value;
    }

    /**
     * Recupera il valore della propriet credenzialiAutenticazione.
     * 
     * @return
     *     possible object is
     *     {@link EndpointType.CredenzialiAutenticazione }
     *     
     */
    public EndpointType.CredenzialiAutenticazione getCredenzialiAutenticazione() {
        return credenzialiAutenticazione;
    }

    /**
     * Imposta il valore della propriet credenzialiAutenticazione.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointType.CredenzialiAutenticazione }
     *     
     */
    public void setCredenzialiAutenticazione(EndpointType.CredenzialiAutenticazione value) {
        this.credenzialiAutenticazione = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="NomeUtente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nomeUtente",
        "password"
    })
    public static class CredenzialiAutenticazione {

        @XmlElement(name = "NomeUtente", required = true)
        protected String nomeUtente;
        @XmlElement(name = "Password", required = true)
        protected String password;

        /**
         * Recupera il valore della propriet nomeUtente.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNomeUtente() {
            return nomeUtente;
        }

        /**
         * Imposta il valore della propriet nomeUtente.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNomeUtente(String value) {
            this.nomeUtente = value;
        }

        /**
         * Recupera il valore della propriet password.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPassword() {
            return password;
        }

        /**
         * Imposta il valore della propriet password.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPassword(String value) {
            this.password = value;
        }

    }

}
