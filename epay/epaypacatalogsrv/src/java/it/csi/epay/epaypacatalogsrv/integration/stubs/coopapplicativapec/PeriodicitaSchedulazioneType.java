/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PeriodicitaSchedulazioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PeriodicitaSchedulazioneType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SGF"/&gt;
 *     &lt;enumeration value="GIO"/&gt;
 *     &lt;enumeration value="SET"/&gt;
 *     &lt;enumeration value="MEN"/&gt;
 *     &lt;enumeration value="BIM"/&gt;
 *     &lt;enumeration value="TRI"/&gt;
 *     &lt;enumeration value="QUA"/&gt;
 *     &lt;enumeration value="SEM"/&gt;
 *     &lt;enumeration value="ANN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
