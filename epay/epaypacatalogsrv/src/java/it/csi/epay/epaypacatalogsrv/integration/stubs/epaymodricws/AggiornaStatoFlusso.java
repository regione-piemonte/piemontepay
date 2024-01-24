/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaStatoFlusso complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaStatoFlusso"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nuovoStatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utente" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaStatoFlusso", propOrder = {
    "identificativoFlusso",
    "idEnte",
    "nuovoStatoFlusso",
    "utente"
})
public class AggiornaStatoFlusso {

    protected List<String> identificativoFlusso;
    protected String idEnte;
    protected String nuovoStatoFlusso;
    protected DtoUtente utente;

    /**
     * Gets the value of the identificativoFlusso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificativoFlusso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificativoFlusso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdentificativoFlusso() {
        if (identificativoFlusso == null) {
            identificativoFlusso = new ArrayList<String>();
        }
        return this.identificativoFlusso;
    }

    /**
     * Recupera il valore della proprieta idEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEnte() {
        return idEnte;
    }

    /**
     * Imposta il valore della proprieta idEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEnte(String value) {
        this.idEnte = value;
    }

    /**
     * Recupera il valore della proprieta nuovoStatoFlusso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuovoStatoFlusso() {
        return nuovoStatoFlusso;
    }

    /**
     * Imposta il valore della proprieta nuovoStatoFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuovoStatoFlusso(String value) {
        this.nuovoStatoFlusso = value;
    }

    /**
     * Recupera il valore della proprieta utente.
     * 
     * @return
     *     possible object is
     *     {@link DtoUtente }
     *     
     */
    public DtoUtente getUtente() {
        return utente;
    }

    /**
     * Imposta il valore della proprieta utente.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoUtente }
     *     
     */
    public void setUtente(DtoUtente value) {
        this.utente = value;
    }

}
