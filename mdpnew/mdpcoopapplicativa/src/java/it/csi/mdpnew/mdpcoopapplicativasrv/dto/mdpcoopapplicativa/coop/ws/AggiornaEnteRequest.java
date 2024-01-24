/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Ente" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}EnteType"/>
 *         &lt;element name="CodiciVersamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}CodiciVersamentoType"/>
 *         &lt;element name="IdOperazione" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
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
    "ente",
    "codiciVersamento",
    "idOperazione"
})
@XmlRootElement(name = "AggiornaEnteRequest")
public class AggiornaEnteRequest {

    @XmlElement(name = "Ente", required = true)
    protected EnteType ente;
    @XmlElement(name = "CodiciVersamento", required = true)
    protected CodiciVersamentoType codiciVersamento;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;

    /**
     * Gets the value of the ente property.
     * 
     * @return
     *     possible object is
     *     {@link EnteType }
     *     
     */
    public EnteType getEnte() {
        return ente;
    }

    /**
     * Sets the value of the ente property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnteType }
     *     
     */
    public void setEnte(EnteType value) {
        this.ente = value;
    }

    /**
     * Gets the value of the codiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link CodiciVersamentoType }
     *     
     */
    public CodiciVersamentoType getCodiciVersamento() {
        return codiciVersamento;
    }

    /**
     * Sets the value of the codiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiciVersamentoType }
     *     
     */
    public void setCodiciVersamento(CodiciVersamentoType value) {
        this.codiciVersamento = value;
    }

    /**
     * Gets the value of the idOperazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperazione() {
        return idOperazione;
    }

    /**
     * Sets the value of the idOperazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperazione(String value) {
        this.idOperazione = value;
    }

}
