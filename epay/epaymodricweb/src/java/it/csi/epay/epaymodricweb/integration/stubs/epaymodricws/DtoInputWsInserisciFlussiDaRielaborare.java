/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsInserisciFlussiDaRielaborare complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsInserisciFlussiDaRielaborare">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="identificativiFlussoDaRielaborare" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsInserisciFlussiDaRielaborare", propOrder = {
    "identificativiFlussoDaRielaborare"
})
public class DtoInputWsInserisciFlussiDaRielaborare
    extends DtoInputBase
{

    @XmlElement(nillable = true)
    protected List<String> identificativiFlussoDaRielaborare;

    /**
     * Gets the value of the identificativiFlussoDaRielaborare property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificativiFlussoDaRielaborare property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificativiFlussoDaRielaborare().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdentificativiFlussoDaRielaborare() {
        if (identificativiFlussoDaRielaborare == null) {
            identificativiFlussoDaRielaborare = new ArrayList<String>();
        }
        return this.identificativiFlussoDaRielaborare;
    }

}
