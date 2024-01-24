/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per lockFind complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="lockFind"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="param" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsLock" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lockFind", propOrder = {
    "param"
})
public class LockFind {

    protected DtoInputWsLock param;

    /**
     * Recupera il valore della proprieta param.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsLock }
     *     
     */
    public DtoInputWsLock getParam() {
        return param;
    }

    /**
     * Imposta il valore della proprieta param.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsLock }
     *     
     */
    public void setParam(DtoInputWsLock value) {
        this.param = value;
    }

}
