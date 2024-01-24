/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ModalitaAcquisizioneProvvisoriType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ModalitaAcquisizioneProvvisoriType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CAM"/&gt;
 *     &lt;enumeration value="CON"/&gt;
 *     &lt;enumeration value="NES"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
