/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CriterioOrdinamentoRichiestaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CriterioOrdinamentoRichiestaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ColumnNameRichiesta" type="{http://www.csi.it/epay/epaywso/businesstypes}ColumnNameRichiestaType"/>
 *         &lt;element name="SortType" type="{http://www.csi.it/epay/epaywso/businesstypes}SortTypeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CriterioOrdinamentoRichiestaType", propOrder = {
    "columnNameRichiesta",
    "sortType"
})
public class CriterioOrdinamentoRichiestaType {

    @XmlElement(name = "ColumnNameRichiesta", required = true)
    protected ColumnNameRichiestaType columnNameRichiesta;
    @XmlElement(name = "SortType", required = true)
    protected SortTypeType sortType;

    /**
     * Gets the value of the columnNameRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link ColumnNameRichiestaType }
     *     
     */
    public ColumnNameRichiestaType getColumnNameRichiesta() {
        return columnNameRichiesta;
    }

    /**
     * Sets the value of the columnNameRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColumnNameRichiestaType }
     *     
     */
    public void setColumnNameRichiesta(ColumnNameRichiestaType value) {
        this.columnNameRichiesta = value;
    }

    /**
     * Gets the value of the sortType property.
     * 
     * @return
     *     possible object is
     *     {@link SortTypeType }
     *     
     */
    public SortTypeType getSortType() {
        return sortType;
    }

    /**
     * Sets the value of the sortType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortTypeType }
     *     
     */
    public void setSortType(SortTypeType value) {
        this.sortType = value;
    }

}
