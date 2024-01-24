/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.cooppec.dto;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipologiaRiferimentoContabileType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipologiaRiferimentoContabileType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TRC1"/&gt;
 *     &lt;enumeration value="TRC2"/&gt;
 *     &lt;enumeration value="TRC3"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TipologiaRiferimentoContabileType")
@XmlEnum
public enum TipologiaRiferimentoContabileType {

    @XmlEnumValue("TRC1")
    TRC_1("TRC1"),
    @XmlEnumValue("TRC2")
    TRC_2("TRC2"),
    @XmlEnumValue("TRC3")
    TRC_3("TRC3");
    private final String value;

    TipologiaRiferimentoContabileType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipologiaRiferimentoContabileType fromValue(String v) {
        for (TipologiaRiferimentoContabileType c: TipologiaRiferimentoContabileType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
