/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.flussorendicontazione;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoRicevuta.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoRicevuta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RPT"/>
 *     &lt;enumeration value="RT"/>
 *     &lt;enumeration value="GET_PAYMENT"/>
 *     &lt;enumeration value="RECEIPT"/>
 *     &lt;enumeration value="IUV_SENZA_RICEVUTA"/>
 *     &lt;enumeration value="SCONOSCIUTO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoRicevuta")
@XmlEnum
public enum TipoRicevuta {

    RPT,
    RT,
    GET_PAYMENT,
    RECEIPT,
    IUV_SENZA_RICEVUTA,
    SCONOSCIUTO;

    public String value() {
        return name();
    }

    public static TipoRicevuta fromValue(String v) {
        return valueOf(v);
    }

}
