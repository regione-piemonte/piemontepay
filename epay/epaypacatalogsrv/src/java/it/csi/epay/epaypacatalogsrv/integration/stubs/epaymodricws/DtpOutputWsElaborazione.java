/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtpOutputWsElaborazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtpOutputWsElaborazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoBaseContabilizzatore"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="risultatoOutputEstemporanea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtpOutputWsElaborazione", propOrder = {
    "idElaborazione",
    "idEnte",
    "risultatoOutputEstemporanea"
})
public class DtpOutputWsElaborazione
    extends DtoBaseContabilizzatore
{

    protected Long idElaborazione;
    protected String idEnte;
    protected String risultatoOutputEstemporanea;

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
     * Recupera il valore della proprieta risultatoOutputEstemporanea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisultatoOutputEstemporanea() {
        return risultatoOutputEstemporanea;
    }

    /**
     * Imposta il valore della proprieta risultatoOutputEstemporanea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisultatoOutputEstemporanea(String value) {
        this.risultatoOutputEstemporanea = value;
    }

}
