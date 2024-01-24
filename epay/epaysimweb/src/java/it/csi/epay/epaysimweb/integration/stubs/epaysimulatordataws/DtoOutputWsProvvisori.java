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
 * <p>Classe Java per dtoOutputWsProvvisori complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsProvvisori"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="esito" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/&gt;
 *         &lt;element name="provvisori" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}dtoProvvisorio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsProvvisori", propOrder = {
    "esito",
    "provvisori"
})
public class DtoOutputWsProvvisori {

    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoProvvisorio> provvisori;

    /**
     * Recupera il valore della propriet esito.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public DtoOutputWsEsito getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della propriet esito.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public void setEsito(DtoOutputWsEsito value) {
        this.esito = value;
    }

    /**
     * Gets the value of the provvisori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the provvisori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvvisori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoProvvisorio }
     * 
     * 
     */
    public List<DtoProvvisorio> getProvvisori() {
        if (provvisori == null) {
            provvisori = new ArrayList<DtoProvvisorio>();
        }
        return this.provvisori;
    }

}
