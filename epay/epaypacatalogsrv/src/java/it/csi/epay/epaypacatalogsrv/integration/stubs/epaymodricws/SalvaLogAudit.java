/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per salvaLogAudit complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="salvaLogAudit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="logAudit" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsLogAudit" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salvaLogAudit", propOrder = {
    "logAudit"
})
public class SalvaLogAudit {

    protected DtoInputWsLogAudit logAudit;

    /**
     * Recupera il valore della proprieta logAudit.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsLogAudit }
     *     
     */
    public DtoInputWsLogAudit getLogAudit() {
        return logAudit;
    }

    /**
     * Imposta il valore della proprieta logAudit.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsLogAudit }
     *     
     */
    public void setLogAudit(DtoInputWsLogAudit value) {
        this.logAudit = value;
    }

}
