/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpzioniAssociazioneUtenteTematicaOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniAssociazioneUtenteTematicaOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="tematiche" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniAssociazioneUtenteTematicaTematicaOutputDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniAssociazioneUtenteTematicaOutput", propOrder = {
    "tematiche"
})
public class GetOpzioniAssociazioneUtenteTematicaOutput
    extends ParentOutput
{

    @XmlElement(nillable = true)
    protected List<GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto> tematiche;

    /**
     * Gets the value of the tematiche property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tematiche property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTematiche().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto }
     * 
     * 
     */
    public List<GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto> getTematiche() {
        if (tematiche == null) {
            tematiche = new ArrayList<GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto>();
        }
        return this.tematiche;
    }

}
