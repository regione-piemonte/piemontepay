/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaProvvisori">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaProvvisoriDaCercare" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaProvvisori" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaProvvisori", propOrder = {
    "listaProvvisoriDaCercare"
})
public class RicercaProvvisori {

    protected DtoInputWsRicercaProvvisori listaProvvisoriDaCercare;

    /**
     * Gets the value of the listaProvvisoriDaCercare property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaProvvisori }
     *     
     */
    public DtoInputWsRicercaProvvisori getListaProvvisoriDaCercare() {
        return listaProvvisoriDaCercare;
    }

    /**
     * Sets the value of the listaProvvisoriDaCercare property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaProvvisori }
     *     
     */
    public void setListaProvvisoriDaCercare(DtoInputWsRicercaProvvisori value) {
        this.listaProvvisoriDaCercare = value;
    }

}
