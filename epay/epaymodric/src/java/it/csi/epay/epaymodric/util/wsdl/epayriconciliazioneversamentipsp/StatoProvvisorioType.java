/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatoProvvisorioType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoProvvisorioType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ANNULLATO"/>
 *     &lt;enumeration value="VALIDO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatoProvvisorioType", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
@XmlEnum
public enum StatoProvvisorioType {

    ANNULLATO,
    VALIDO;

    public String value() {
        return name();
    }

    public static StatoProvvisorioType fromValue(String v) {
        return valueOf(v);
    }

}
