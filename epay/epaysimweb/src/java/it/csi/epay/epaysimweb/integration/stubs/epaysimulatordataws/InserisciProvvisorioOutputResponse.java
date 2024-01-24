/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciProvvisorioOutputResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciProvvisorioOutputResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inserisciProvvisorioOutputDTOList" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}inserisciProvvisorioOutputDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciProvvisorioOutputResponse", propOrder = {
    "inserisciProvvisorioOutputDTOList"
})
public class InserisciProvvisorioOutputResponse
    extends ParentOutput
{

    @XmlElement(nillable = true)
    protected List<InserisciProvvisorioOutputDTO> inserisciProvvisorioOutputDTOList;

    /**
     * Gets the value of the inserisciProvvisorioOutputDTOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inserisciProvvisorioOutputDTOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInserisciProvvisorioOutputDTOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InserisciProvvisorioOutputDTO }
     * 
     * 
     */
    public List<InserisciProvvisorioOutputDTO> getInserisciProvvisorioOutputDTOList() {
        if (inserisciProvvisorioOutputDTOList == null) {
            inserisciProvvisorioOutputDTOList = new ArrayList<InserisciProvvisorioOutputDTO>();
        }
        return this.inserisciProvvisorioOutputDTOList;
    }

}
