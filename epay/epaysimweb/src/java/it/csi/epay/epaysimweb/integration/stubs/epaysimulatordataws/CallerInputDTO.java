/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per callerInputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="callerInputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="principal" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}principalInputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callerInputDTO", propOrder = {
    "codiceApplicativo",
    "principal"
})
public class CallerInputDTO {

    protected String codiceApplicativo;
    protected PrincipalInputDTO principal;

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
     * Recupera il valore della propriet principal.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalInputDTO }
     *     
     */
    public PrincipalInputDTO getPrincipal() {
        return principal;
    }

    /**
     * Imposta il valore della propriet principal.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalInputDTO }
     *     
     */
    public void setPrincipal(PrincipalInputDTO value) {
        this.principal = value;
    }

}
