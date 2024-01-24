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
 * <p>Classe Java per dtoOutputWsFlussoSintesi complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsFlussoSintesi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datiUtente" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/&gt;
 *         &lt;element name="flussiSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoSintesi" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="flussoOrigine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoOrigine" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsFlussoSintesi", propOrder = {
    "datiUtente",
    "esito",
    "flussiSintesi",
    "flussoOrigine"
})
public class DtoOutputWsFlussoSintesi {

    protected DtoUtente datiUtente;
    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoFlussoSintesi> flussiSintesi;
    protected DtoFlussoOrigine flussoOrigine;

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
     * Gets the value of the flussiSintesi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flussiSintesi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlussiSintesi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoFlussoSintesi }
     * 
     * 
     */
    public List<DtoFlussoSintesi> getFlussiSintesi() {
        if (flussiSintesi == null) {
            flussiSintesi = new ArrayList<DtoFlussoSintesi>();
        }
        return this.flussiSintesi;
    }

    /**
     * Recupera il valore della proprieta flussoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link DtoFlussoOrigine }
     *     
     */
    public DtoFlussoOrigine getFlussoOrigine() {
        return flussoOrigine;
    }

    /**
     * Imposta il valore della proprieta flussoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFlussoOrigine }
     *     
     */
    public void setFlussoOrigine(DtoFlussoOrigine value) {
        this.flussoOrigine = value;
    }

}
