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
 * <p>Classe Java per updateEsitoStatoElaborazioneFlussoInputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="updateEsitoStatoElaborazioneFlussoInputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identificativiFlussoOrigineList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nuovoStato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateEsitoStatoElaborazioneFlussoInputDTO", propOrder = {
    "identificativiFlussoOrigineList",
    "nuovoStato"
})
public class UpdateEsitoStatoElaborazioneFlussoInputDTO
    extends ParentInput
{

    @XmlElement(nillable = true)
    protected List<String> identificativiFlussoOrigineList;
    protected Boolean nuovoStato;

    /**
     * Gets the value of the identificativiFlussoOrigineList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificativiFlussoOrigineList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificativiFlussoOrigineList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdentificativiFlussoOrigineList() {
        if (identificativiFlussoOrigineList == null) {
            identificativiFlussoOrigineList = new ArrayList<String>();
        }
        return this.identificativiFlussoOrigineList;
    }

    /**
     * Recupera il valore della propriet nuovoStato.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNuovoStato() {
        return nuovoStato;
    }

    /**
     * Imposta il valore della propriet nuovoStato.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNuovoStato(Boolean value) {
        this.nuovoStato = value;
    }

}
