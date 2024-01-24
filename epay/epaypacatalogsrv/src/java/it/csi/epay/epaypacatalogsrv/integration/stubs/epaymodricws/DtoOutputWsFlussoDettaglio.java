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
 * <p>Classe Java per dtoOutputWsFlussoDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsFlussoDettaglio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datiUtente" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/&gt;
 *         &lt;element name="flussiDettaglio" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoDettaglio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="flussoSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoSintesi" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsFlussoDettaglio", propOrder = {
    "datiUtente",
    "esito",
    "flussiDettaglio",
    "flussoSintesi"
})
public class DtoOutputWsFlussoDettaglio {

    protected DtoUtente datiUtente;
    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoFlussoDettaglio> flussiDettaglio;
    protected DtoFlussoSintesi flussoSintesi;

    /**
     * Recupera il valore della proprieta datiUtente.
     * 
     * @return
     *     possible object is
     *     {@link DtoUtente }
     *     
     */
    public DtoUtente getDatiUtente() {
        return datiUtente;
    }

    /**
     * Imposta il valore della proprieta datiUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoUtente }
     *     
     */
    public void setDatiUtente(DtoUtente value) {
        this.datiUtente = value;
    }

    /**
     * Recupera il valore della proprieta esito.
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
     * Imposta il valore della proprieta esito.
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
     * Gets the value of the flussiDettaglio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flussiDettaglio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlussiDettaglio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoFlussoDettaglio }
     * 
     * 
     */
    public List<DtoFlussoDettaglio> getFlussiDettaglio() {
        if (flussiDettaglio == null) {
            flussiDettaglio = new ArrayList<DtoFlussoDettaglio>();
        }
        return this.flussiDettaglio;
    }

    /**
     * Recupera il valore della proprieta flussoSintesi.
     * 
     * @return
     *     possible object is
     *     {@link DtoFlussoSintesi }
     *     
     */
    public DtoFlussoSintesi getFlussoSintesi() {
        return flussoSintesi;
    }

    /**
     * Imposta il valore della proprieta flussoSintesi.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFlussoSintesi }
     *     
     */
    public void setFlussoSintesi(DtoFlussoSintesi value) {
        this.flussoSintesi = value;
    }

}
