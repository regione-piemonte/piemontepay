/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoInputWsAttivaElaborazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsAttivaElaborazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="daFrontend" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="idElaborazionePrecedente" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsAttivaElaborazione", propOrder = {
    "codiceFiscaleEnte",
    "daFrontend",
    "idElaborazionePrecedente"
})
public class DtoInputWsAttivaElaborazione {

    protected String codiceFiscaleEnte;
    protected Boolean daFrontend;
    protected Long idElaborazionePrecedente;

    /**
     * Recupera il valore della proprieta codiceFiscaleEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    /**
     * Imposta il valore della proprieta codiceFiscaleEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEnte(String value) {
        this.codiceFiscaleEnte = value;
    }

    /**
     * Recupera il valore della proprieta daFrontend.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDaFrontend() {
        return daFrontend;
    }

    /**
     * Imposta il valore della proprieta daFrontend.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDaFrontend(Boolean value) {
        this.daFrontend = value;
    }

    /**
     * Recupera il valore della proprieta idElaborazionePrecedente.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdElaborazionePrecedente() {
        return idElaborazionePrecedente;
    }

    /**
     * Imposta il valore della proprieta idElaborazionePrecedente.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdElaborazionePrecedente(Long value) {
        this.idElaborazionePrecedente = value;
    }

}
