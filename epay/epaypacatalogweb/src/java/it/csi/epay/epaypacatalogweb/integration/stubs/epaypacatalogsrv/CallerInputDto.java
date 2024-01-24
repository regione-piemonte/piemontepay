/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per callerInputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="callerInputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="principal" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}principalInputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callerInputDto", propOrder = {
    "codiceApplicativo",
    "ip",
    "principal"
})
public class CallerInputDto {

    protected String codiceApplicativo;
    protected String ip;
    protected PrincipalInputDto principal;

    /**
     * Recupera il valore della propriet codiceApplicativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceApplicativo() {
        return codiceApplicativo;
    }

    /**
     * Imposta il valore della propriet codiceApplicativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceApplicativo(String value) {
        this.codiceApplicativo = value;
    }

    /**
     * Recupera il valore della propriet ip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Imposta il valore della propriet ip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Recupera il valore della propriet principal.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalInputDto }
     *     
     */
    public PrincipalInputDto getPrincipal() {
        return principal;
    }

    /**
     * Imposta il valore della propriet principal.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalInputDto }
     *     
     */
    public void setPrincipal(PrincipalInputDto value) {
        this.principal = value;
    }

}
