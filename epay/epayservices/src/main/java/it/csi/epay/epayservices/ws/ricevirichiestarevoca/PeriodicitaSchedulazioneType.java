/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PeriodicitaSchedulazioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PeriodicitaSchedulazioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SGF"/>
 *     &lt;enumeration value="GIO"/>
 *     &lt;enumeration value="SET"/>
 *     &lt;enumeration value="MEN"/>
 *     &lt;enumeration value="BIM"/>
 *     &lt;enumeration value="TRI"/>
 *     &lt;enumeration value="QUA"/>
 *     &lt;enumeration value="SEM"/>
 *     &lt;enumeration value="ANN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PeriodicitaSchedulazioneType")
@XmlEnum
public enum PeriodicitaSchedulazioneType {

    SGF,
    GIO,
    SET,
    MEN,
    BIM,
    TRI,
    QUA,
    SEM,
    ANN;

    public String value() {
        return name();
    }

    public static PeriodicitaSchedulazioneType fromValue(String v) {
        return valueOf(v);
    }

}
