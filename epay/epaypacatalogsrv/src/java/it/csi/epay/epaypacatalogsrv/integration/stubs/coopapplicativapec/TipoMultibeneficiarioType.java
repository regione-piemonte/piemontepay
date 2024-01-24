/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoMultibeneficiarioType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoMultibeneficiarioType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ORDINARIO"/>
 *     &lt;enumeration value="PRIMARIO"/>
 *     &lt;enumeration value="SECONDARIO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoMultibeneficiarioType")
@XmlEnum
public enum TipoMultibeneficiarioType {

    ORDINARIO,
    PRIMARIO,
    SECONDARIO;

    public String value() {
        return name();
    }

    public static TipoMultibeneficiarioType fromValue(String v) {
        return valueOf(v);
    }

}
