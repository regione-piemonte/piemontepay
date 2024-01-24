/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoOutputWsTrasmettiFlussiPagoPA complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsTrasmettiFlussiPagoPA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/&gt;
 *         &lt;element name="flussoRiconciliazioneXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datiUtente" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsTrasmettiFlussiPagoPA", propOrder = {
    "esito",
    "flussoRiconciliazioneXml",
    "datiUtente"
})
public class DtoOutputWsTrasmettiFlussiPagoPA {

    protected DtoOutputWsEsito esito;
    protected String flussoRiconciliazioneXml;
    protected DtoUtente datiUtente;

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
     * Recupera il valore della proprieta flussoRiconciliazioneXml.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlussoRiconciliazioneXml() {
        return flussoRiconciliazioneXml;
    }

    /**
     * Imposta il valore della proprieta flussoRiconciliazioneXml.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlussoRiconciliazioneXml(String value) {
        this.flussoRiconciliazioneXml = value;
    }

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

}
