/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.interfacews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for PaVerifyPaymentNoticeResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="mapEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType ( name = "mapEntry", namespace = "http://ws.pagamenti.telematici.gov/", propOrder = {
    "key",
    "value"
})

public class MapEntry {

    @XmlElement ( required = true )
    protected String key;

    @XmlElement ( required = true )
    protected String value;

    public String getKey () {
        return key;
    }

    public void setKey ( String key ) {
        this.key = key;
    }

    public String getValue () {
        return value;
    }

    public void setValue ( String value ) {
        this.value = value;
    }




}
