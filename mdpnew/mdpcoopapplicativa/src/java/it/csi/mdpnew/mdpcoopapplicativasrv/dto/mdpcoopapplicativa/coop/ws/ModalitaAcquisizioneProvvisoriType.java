/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModalitaAcquisizioneProvvisoriType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ModalitaAcquisizioneProvvisoriType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CAM"/>
 *     &lt;enumeration value="CON"/>
 *     &lt;enumeration value="NES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ModalitaAcquisizioneProvvisoriType")
@XmlEnum
public enum ModalitaAcquisizioneProvvisoriType {

    CAM,
    CON,
    NES;

    public String value() {
        return name();
    }

    public static ModalitaAcquisizioneProvvisoriType fromValue(String v) {
        return valueOf(v);
    }

}
