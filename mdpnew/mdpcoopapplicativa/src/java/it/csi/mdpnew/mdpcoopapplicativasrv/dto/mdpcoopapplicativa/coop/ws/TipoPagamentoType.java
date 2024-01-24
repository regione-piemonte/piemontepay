/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoPagamentoType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoPagamentoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LDC"/>
 *     &lt;enumeration value="PS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "TipoPagamentoType")
@XmlEnum
public enum TipoPagamentoType {

    LDC,
        PS,
        REDS,
        PABL,
        MABL;

    public String value() {
        return name();
    }

    public static TipoPagamentoType fromValue(String v) {
        return valueOf(v);
    }

}
