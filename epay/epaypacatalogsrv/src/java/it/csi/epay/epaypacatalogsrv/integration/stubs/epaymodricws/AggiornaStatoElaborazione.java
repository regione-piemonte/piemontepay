/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaStatoElaborazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaStatoElaborazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nuovaStatoElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utenteModifica" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaStatoElaborazione", propOrder = {
    "idElaborazione",
    "nuovaStatoElaborazione",
    "utenteModifica"
})
public class AggiornaStatoElaborazione {

    protected Long idElaborazione;
    protected String nuovaStatoElaborazione;
    protected DtoUtente utenteModifica;

    /**
     * Recupera il valore della proprieta idElaborazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdElaborazione() {
        return idElaborazione;
    }

    /**
     * Imposta il valore della proprieta idElaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdElaborazione(Long value) {
        this.idElaborazione = value;
    }

    /**
     * Recupera il valore della proprieta nuovaStatoElaborazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuovaStatoElaborazione() {
        return nuovaStatoElaborazione;
    }

    /**
     * Imposta il valore della proprieta nuovaStatoElaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuovaStatoElaborazione(String value) {
        this.nuovaStatoElaborazione = value;
    }

    /**
     * Recupera il valore della proprieta utenteModifica.
     * 
     * @return
     *     possible object is
     *     {@link DtoUtente }
     *     
     */
    public DtoUtente getUtenteModifica() {
        return utenteModifica;
    }

    /**
     * Imposta il valore della proprieta utenteModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoUtente }
     *     
     */
    public void setUtenteModifica(DtoUtente value) {
        this.utenteModifica = value;
    }

}
