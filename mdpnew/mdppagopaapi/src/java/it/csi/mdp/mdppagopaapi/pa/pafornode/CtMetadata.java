/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Its a _key/value_ store fields for the exclusive use of the PA. 
 * The data will return in the receipt (`paSendRT`)
 * 
 * <p>Java class for ctMetadata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctMetadata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mapEntry" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctMapEntry" maxOccurs="10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctMetadata", propOrder = {
    "mapEntry"
})
public class CtMetadata {

    @XmlElement(required = true)
    protected List<CtMapEntry> mapEntry;

    /**
     * Gets the value of the mapEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mapEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMapEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CtMapEntry }
     * 
     * 
     */
    public List<CtMapEntry> getMapEntry() {
        if (mapEntry == null) {
            mapEntry = new ArrayList<CtMapEntry>();
        }
        return this.mapEntry;
    }

}
