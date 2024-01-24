/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaywsosrv.facade.cooppec.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipologiaDatoSpecificoRiscossioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipologiaDatoSpecificoRiscossioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SIOPE"/>
 *     &lt;enumeration value="ENTE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipologiaDatoSpecificoRiscossioneType")
@XmlEnum
public enum TipologiaDatoSpecificoRiscossioneType {

    SIOPE,
    ENTE;

    public String value() {
        return name();
    }

    public static TipologiaDatoSpecificoRiscossioneType fromValue(String v) {
        return valueOf(v);
    }

}
