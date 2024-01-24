/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ModalitaDiIntegrazioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ModalitaDiIntegrazioneType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PEC"/&gt;
 *     &lt;enumeration value="SRV"/&gt;
 *     &lt;enumeration value="ALL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ModalitaDiIntegrazioneType")
@XmlEnum
public enum ModalitaDiIntegrazioneType {

    PEC,
    SRV,
    ALL;

    public String value() {
        return name();
    }

    public static ModalitaDiIntegrazioneType fromValue(String v) {
        return valueOf(v);
    }

}
