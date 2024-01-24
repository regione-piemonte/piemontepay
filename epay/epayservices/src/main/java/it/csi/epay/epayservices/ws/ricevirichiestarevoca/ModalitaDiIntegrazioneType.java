/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ModalitaDiIntegrazioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ModalitaDiIntegrazioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PEC"/>
 *     &lt;enumeration value="SRV"/>
 *     &lt;enumeration value="ALL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
