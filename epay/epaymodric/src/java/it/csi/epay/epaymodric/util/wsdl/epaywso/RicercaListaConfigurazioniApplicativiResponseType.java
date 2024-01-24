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
 * <p>Java class for RicercaListaConfigurazioniApplicativiResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RicercaListaConfigurazioniApplicativiResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="ConfigurazioniApplicativi" type="{http://www.csi.it/epay/epaywso/businesstypes}ConfigurazioneApplicativoTypeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RicercaListaConfigurazioniApplicativiResponseType", propOrder = {
    "configurazioniApplicativi"
})
public class RicercaListaConfigurazioniApplicativiResponseType
    extends ResponseType
{

    @XmlElement(name = "ConfigurazioniApplicativi")
    protected ConfigurazioneApplicativoTypeList configurazioniApplicativi;

    /**
     * Gets the value of the configurazioniApplicativi property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurazioneApplicativoTypeList }
     *     
     */
    public ConfigurazioneApplicativoTypeList getConfigurazioniApplicativi() {
        return configurazioniApplicativi;
    }

    /**
     * Sets the value of the configurazioniApplicativi property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurazioneApplicativoTypeList }
     *     
     */
    public void setConfigurazioniApplicativi(ConfigurazioneApplicativoTypeList value) {
        this.configurazioniApplicativi = value;
    }

}
