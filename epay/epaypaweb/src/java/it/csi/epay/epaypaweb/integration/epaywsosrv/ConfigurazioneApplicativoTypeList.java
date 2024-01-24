/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfigurazioneApplicativoTypeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfigurazioneApplicativoTypeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConfigurazioneApplicativo" type="{http://www.csi.it/epay/epaywso/businesstypes}ConfigurazioneApplicativoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigurazioneApplicativoTypeList", propOrder = {
    "configurazioneApplicativo"
})
public class ConfigurazioneApplicativoTypeList {

    @XmlElement(name = "ConfigurazioneApplicativo", required = true)
    protected List<ConfigurazioneApplicativoType> configurazioneApplicativo;

    /**
     * Gets the value of the configurazioneApplicativo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the configurazioneApplicativo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfigurazioneApplicativo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfigurazioneApplicativoType }
     * 
     * 
     */
    public List<ConfigurazioneApplicativoType> getConfigurazioneApplicativo() {
        if (configurazioneApplicativo == null) {
            configurazioneApplicativo = new ArrayList<ConfigurazioneApplicativoType>();
        }
        return this.configurazioneApplicativo;
    }

}
