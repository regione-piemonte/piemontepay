/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.business.dto.multiversamento;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for PaVerifyPaymentNoticeResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="metadata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mapEntry" type="{www.csi.it/mdp}MapEntry" />
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadata", namespace = "http://ws.pagamenti.telematici.gov/", propOrder = {
    "mapEntry"
})
@XmlRootElement ( name = "Metadata", namespace = "www.csi.it/mdp" )
public class Metadata {

    @XmlElement ( required = true )
    protected List<MapEntry> mapEntry;

    public List<MapEntry> getMapEntry () {
        return mapEntry;
    }

    public void setMapEntry ( List<MapEntry> mapEntry ) {
        this.mapEntry = mapEntry;
    }






}
