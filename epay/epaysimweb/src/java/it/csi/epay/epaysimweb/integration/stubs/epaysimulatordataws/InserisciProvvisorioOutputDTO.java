/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciProvvisorioOutputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciProvvisorioOutputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="provvisorioDTO" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}provvisorioDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciProvvisorioOutputDTO", propOrder = {
    "provvisorioDTO"
})
public class InserisciProvvisorioOutputDTO
    extends ParentOutput
{

    protected ProvvisorioDTO provvisorioDTO;

    /**
     * Recupera il valore della propriet provvisorioDTO.
     * 
     * @return
     *     possible object is
     *     {@link ProvvisorioDTO }
     *     
     */
    public ProvvisorioDTO getProvvisorioDTO() {
        return provvisorioDTO;
    }

    /**
     * Imposta il valore della propriet provvisorioDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvvisorioDTO }
     *     
     */
    public void setProvvisorioDTO(ProvvisorioDTO value) {
        this.provvisorioDTO = value;
    }

}
