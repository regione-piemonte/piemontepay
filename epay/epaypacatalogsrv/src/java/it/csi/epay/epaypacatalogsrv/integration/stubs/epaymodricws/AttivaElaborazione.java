/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per attivaElaborazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="attivaElaborazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsAttivaElaborazione" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attivaElaborazione", propOrder = {
    "arg0"
})
public class AttivaElaborazione {

    protected DtoInputWsAttivaElaborazione arg0;

    /**
     * Recupera il valore della proprieta arg0.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsAttivaElaborazione }
     *     
     */
    public DtoInputWsAttivaElaborazione getArg0() {
        return arg0;
    }

    /**
     * Imposta il valore della proprieta arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsAttivaElaborazione }
     *     
     */
    public void setArg0(DtoInputWsAttivaElaborazione value) {
        this.arg0 = value;
    }

}
