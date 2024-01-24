/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.sportello2epaywso;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DatiEsitoSingolaRevocaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiEsitoSingolaRevocaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SingoloImportoRevocato" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/&gt;
 *         &lt;element name="IUR" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="CausaleEsito" type="{http://www.csi.it/epay/epaywso/types}String140Type"/&gt;
 *         &lt;element name="DatiAggiuntiviEsito" type="{http://www.csi.it/epay/epaywso/types}String140Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiEsitoSingolaRevocaType", propOrder = {
    "singoloImportoRevocato",
    "iur",
    "causaleEsito",
    "datiAggiuntiviEsito"
})
public class DatiEsitoSingolaRevocaType {

    @XmlElement(name = "SingoloImportoRevocato", required = true)
    protected BigDecimal singoloImportoRevocato;
    @XmlElement(name = "IUR", required = true)
    protected String iur;
    @XmlElement(name = "CausaleEsito", required = true)
    protected String causaleEsito;
    @XmlElement(name = "DatiAggiuntiviEsito", required = true)
    protected String datiAggiuntiviEsito;

    /**
     * Recupera il valore della proprieta singoloImportoRevocato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSingoloImportoRevocato() {
        return singoloImportoRevocato;
    }

    /**
     * Imposta il valore della proprieta singoloImportoRevocato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSingoloImportoRevocato(BigDecimal value) {
        this.singoloImportoRevocato = value;
    }

    /**
     * Recupera il valore della proprieta iur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIUR() {
        return iur;
    }

    /**
     * Imposta il valore della proprieta iur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIUR(String value) {
        this.iur = value;
    }

    /**
     * Recupera il valore della proprieta causaleEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaleEsito() {
        return causaleEsito;
    }

    /**
     * Imposta il valore della proprieta causaleEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaleEsito(String value) {
        this.causaleEsito = value;
    }

    /**
     * Recupera il valore della proprieta datiAggiuntiviEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiAggiuntiviEsito() {
        return datiAggiuntiviEsito;
    }

    /**
     * Imposta il valore della proprieta datiAggiuntiviEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiAggiuntiviEsito(String value) {
        this.datiAggiuntiviEsito = value;
    }

}
