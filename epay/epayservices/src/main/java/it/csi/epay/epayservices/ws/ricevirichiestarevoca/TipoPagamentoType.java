/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Classe Java per TipoPagamentoType.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="TipoPagamentoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LDS"/>
 *     &lt;enumeration value="PS"/>
 *     &lt;enumeration value="REDS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "TipoPagamentoType")
@XmlEnum
public enum TipoPagamentoType {

    LDS,
        PS,
        REDS,
        MABL,
        PABL;

    public String value() {
        return name();
    }

    public static TipoPagamentoType fromValue(String v) {
        return valueOf(v);
    }

}
