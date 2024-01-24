/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatoRichiestaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoRichiestaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IN_CORSO_DI_ACQUISIZIONE"/>
 *     &lt;enumeration value="ERRORE_IN_FASE_DI_ACQUISIZIONE"/>
 *     &lt;enumeration value="DA_ELABORARE"/>
 *     &lt;enumeration value="ERRORE_IN_FASE_DI_ELABORAZIONE"/>
 *     &lt;enumeration value="ELABORATA"/>
 *     &lt;enumeration value="IN_CORSO_DI_ELABORAZIONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatoRichiestaType", namespace = "http://www.csi.it/epay/epaywso/types")
@XmlEnum
public enum StatoRichiestaType {

    IN_CORSO_DI_ACQUISIZIONE,
    ERRORE_IN_FASE_DI_ACQUISIZIONE,
    DA_ELABORARE,
    ERRORE_IN_FASE_DI_ELABORAZIONE,
    ELABORATA,
    IN_CORSO_DI_ELABORAZIONE;

    public String value() {
        return name();
    }

    public static StatoRichiestaType fromValue(String v) {
        return valueOf(v);
    }

}
