/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoInputWsAggiornaProvvisori complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsAggiornaProvvisori"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dtoProvvisorioList" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoProvvisorio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dtoUtente" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *         &lt;element name="identificativoEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsAggiornaProvvisori", propOrder = {
    "dtoProvvisorioList",
    "dtoUtente",
    "identificativoEnte"
})
public class DtoInputWsAggiornaProvvisori {

    @XmlElement(nillable = true)
    protected List<DtoProvvisorio> dtoProvvisorioList;
    protected DtoUtente dtoUtente;
    protected String identificativoEnte;

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

    /**
     * Recupera il valore della proprieta dtoUtente.
     * 
     * @return
     *     possible object is
     *     {@link DtoUtente }
     *     
     */
    public DtoUtente getDtoUtente() {
        return dtoUtente;
    }

    /**
     * Imposta il valore della proprieta dtoUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoUtente }
     *     
     */
    public void setDtoUtente(DtoUtente value) {
        this.dtoUtente = value;
    }

    /**
     * Recupera il valore della proprieta identificativoEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoEnte() {
        return identificativoEnte;
    }

    /**
     * Imposta il valore della proprieta identificativoEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoEnte(String value) {
        this.identificativoEnte = value;
    }

}
