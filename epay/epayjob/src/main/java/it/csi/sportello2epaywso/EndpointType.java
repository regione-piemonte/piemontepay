/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.sportello2epaywso;

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
@XmlType(name = "EndpointType", namespace = "http://www.csi.it/epay/epaywso/types", propOrder = {
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
     * Recupera il valore della proprieta protocol.
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
     * Imposta il valore della proprieta protocol.
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
     * Recupera il valore della proprieta host.
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
     * Imposta il valore della proprieta host.
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
     * Recupera il valore della proprieta context.
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
     * Imposta il valore della proprieta context.
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
     * Recupera il valore della proprieta port.
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
     * Imposta il valore della proprieta port.
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
     * Recupera il valore della proprieta credenzialiAutenticazione.
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
     * Imposta il valore della proprieta credenzialiAutenticazione.
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

        @XmlElement(name = "NomeUtente", namespace = "http://www.csi.it/epay/epaywso/types", required = true)
        protected String nomeUtente;
        @XmlElement(name = "Password", namespace = "http://www.csi.it/epay/epaywso/types", required = true)
        protected String password;

        /**
         * Recupera il valore della proprieta nomeUtente.
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
         * Imposta il valore della proprieta nomeUtente.
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
         * Recupera il valore della proprieta password.
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
         * Imposta il valore della proprieta password.
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
