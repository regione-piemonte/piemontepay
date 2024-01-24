/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeterminaProssimoEndpointResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeterminaProssimoEndpointResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="ConfigurazioneApplicativo" type="{http://www.csi.it/epay/epaywso/businesstypes}ConfigurazioneApplicativoType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeterminaProssimoEndpointResponseType", propOrder = {
    "configurazioneApplicativo"
})
public class DeterminaProssimoEndpointResponseType
    extends ResponseType
{

    @XmlElement(name = "ConfigurazioneApplicativo", required = true)
    protected ConfigurazioneApplicativoType configurazioneApplicativo;

    /**
     * Gets the value of the configurazioneApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurazioneApplicativoType }
     *     
     */
    public ConfigurazioneApplicativoType getConfigurazioneApplicativo() {
        return configurazioneApplicativo;
    }

    /**
     * Sets the value of the configurazioneApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurazioneApplicativoType }
     *     
     */
    public void setConfigurazioneApplicativo(ConfigurazioneApplicativoType value) {
        this.configurazioneApplicativo = value;
    }

}
