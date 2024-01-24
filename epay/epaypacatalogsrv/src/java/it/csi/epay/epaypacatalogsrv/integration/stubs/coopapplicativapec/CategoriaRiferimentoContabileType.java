/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CategoriaRiferimentoContabileType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoriaRiferimentoContabileType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CRC1"/&gt;
 *     &lt;enumeration value="CRC2"/&gt;
 *     &lt;enumeration value="CRC3"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CategoriaRiferimentoContabileType")
@XmlEnum
public enum CategoriaRiferimentoContabileType {

    @XmlEnumValue("CRC1")
    CRC_1("CRC1"),
    @XmlEnumValue("CRC2")
    CRC_2("CRC2"),
    @XmlEnumValue("CRC3")
    CRC_3("CRC3");
    private final String value;

    CategoriaRiferimentoContabileType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoriaRiferimentoContabileType fromValue(String v) {
        for (CategoriaRiferimentoContabileType c: CategoriaRiferimentoContabileType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
