/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stAmountOption.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stAmountOption">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EQ"/>
 *     &lt;enumeration value="LS"/>
 *     &lt;enumeration value="GT"/>
 *     &lt;enumeration value="ANY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stAmountOption")
@XmlEnum
public enum StAmountOption {

    EQ,
    LS,
    GT,
    ANY;

    public String value() {
        return name();
    }

    public static StAmountOption fromValue(String v) {
        return valueOf(v);
    }

}
