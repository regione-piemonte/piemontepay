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
 * <p>Java class for dtoInputWsCancellaProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsCancellaProvvisori">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="dtoProvvisorioList" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoProvvisorio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsCancellaProvvisori", propOrder = {
    "dtoProvvisorioList"
})
public class DtoInputWsCancellaProvvisori
    extends DtoInputBase
{

    @XmlElement(nillable = true)
    protected List<DtoProvvisorio> dtoProvvisorioList;

    /**
     * Gets the value of the dtoProvvisorioList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtoProvvisorioList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtoProvvisorioList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoProvvisorio }
     * 
     * 
     */
    public List<DtoProvvisorio> getDtoProvvisorioList() {
        if (dtoProvvisorioList == null) {
            dtoProvvisorioList = new ArrayList<DtoProvvisorio>();
        }
        return this.dtoProvvisorioList;
    }

}
