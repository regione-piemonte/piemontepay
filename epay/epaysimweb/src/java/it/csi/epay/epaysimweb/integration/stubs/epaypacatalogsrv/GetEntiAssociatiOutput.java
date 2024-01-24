/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getEntiAssociatiOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getEntiAssociatiOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entiAssociati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEntiAssociatiOutputDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiAssociatiOutput", propOrder = {
    "entiAssociati"
})
public class GetEntiAssociatiOutput
    extends ParentOutput
{

    @XmlElement(nillable = true)
    protected List<GetEntiAssociatiOutputDto> entiAssociati;

    /**
     * Gets the value of the entiAssociati property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entiAssociati property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntiAssociati().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetEntiAssociatiOutputDto }
     * 
     * 
     */
    public List<GetEntiAssociatiOutputDto> getEntiAssociati() {
        if (entiAssociati == null) {
            entiAssociati = new ArrayList<GetEntiAssociatiOutputDto>();
        }
        return this.entiAssociati;
    }

}
