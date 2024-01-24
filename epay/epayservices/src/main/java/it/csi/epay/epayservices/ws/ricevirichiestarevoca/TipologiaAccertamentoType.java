/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipologiaAccertamentoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipologiaAccertamentoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNI"/>
 *     &lt;enumeration value="PIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipologiaAccertamentoType")
@XmlEnum
public enum TipologiaAccertamentoType {

    UNI,
    PIN;

    public String value() {
        return name();
    }

    public static TipologiaAccertamentoType fromValue(String v) {
        return valueOf(v);
    }

}
