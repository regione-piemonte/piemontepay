/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per parentInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="parentInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="caller" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}callerInputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parentInput", propOrder = {
    "caller"
})
@XmlSeeAlso({
    DtoInputWsRicercaProvvisori.class,
    InserisciProvvisorioInputDTO.class,
    UpdateEsitoStatoElaborazioneFlussoInputDTO.class,
    RicercaFlussoInputDTO.class,
    FlussoSintesiPagopaDTO.class,
    FlussoDettaglioPagopaDTO.class,
    FlussoOriginePagopaDTO.class,
    RicercaFlussoErroreInputDTO.class
})
public class ParentInput {

    protected CallerInputDTO caller;

    /**
     * Recupera il valore della propriet caller.
     * 
     * @return
     *     possible object is
     *     {@link CallerInputDTO }
     *     
     */
    public CallerInputDTO getCaller() {
        return caller;
    }

    /**
     * Imposta il valore della propriet caller.
     * 
     * @param value
     *     allowed object is
     *     {@link CallerInputDTO }
     *     
     */
    public void setCaller(CallerInputDTO value) {
        this.caller = value;
    }

}
