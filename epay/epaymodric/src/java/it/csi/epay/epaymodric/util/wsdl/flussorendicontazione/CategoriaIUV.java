/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.flussorendicontazione;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for categoriaIUV.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="categoriaIUV">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INTERM_PPAY"/>
 *     &lt;enumeration value="NON_INTERM_PPAY"/>
 *     &lt;enumeration value="SCONOSCIUT_PPAY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "categoriaIUV")
@XmlEnum
public enum CategoriaIUV {

    INTERM_PPAY,
    NON_INTERM_PPAY,
    SCONOSCIUT_PPAY;

    public String value() {
        return name();
    }

    public static CategoriaIUV fromValue(String v) {
        return valueOf(v);
    }

}
