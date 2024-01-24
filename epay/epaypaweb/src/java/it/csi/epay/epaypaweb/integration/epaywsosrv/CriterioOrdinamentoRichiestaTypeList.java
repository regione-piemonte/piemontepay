/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CriterioOrdinamentoRichiestaTypeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CriterioOrdinamentoRichiestaTypeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CriterioOrdinamentoRichiesta" type="{http://www.csi.it/epay/epaywso/businesstypes}CriterioOrdinamentoRichiestaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CriterioOrdinamentoRichiestaTypeList", propOrder = {
    "criterioOrdinamentoRichiesta"
})
public class CriterioOrdinamentoRichiestaTypeList {

    @XmlElement(name = "CriterioOrdinamentoRichiesta", required = true)
    protected List<CriterioOrdinamentoRichiestaType> criterioOrdinamentoRichiesta;

    /**
     * Gets the value of the criterioOrdinamentoRichiesta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the criterioOrdinamentoRichiesta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCriterioOrdinamentoRichiesta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CriterioOrdinamentoRichiestaType }
     * 
     * 
     */
    public List<CriterioOrdinamentoRichiestaType> getCriterioOrdinamentoRichiesta() {
        if (criterioOrdinamentoRichiesta == null) {
            criterioOrdinamentoRichiesta = new ArrayList<CriterioOrdinamentoRichiestaType>();
        }
        return this.criterioOrdinamentoRichiesta;
    }

}
