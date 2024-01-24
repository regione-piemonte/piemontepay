/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProtocolloAggiornamentoAzioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProtocolloAggiornamentoAzioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INSERIMENTO"/>
 *     &lt;enumeration value="MODIFICA"/>
 *     &lt;enumeration value="CANCELLAZIONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType ( name = "ProtocolloAggiornamentoAzioneType", namespace = "http://www.csi.it/epay/epaywso/types" )
@XmlEnum
public enum ProtocolloAggiornamentoAzioneType {

    INSERIMENTO,
    MODIFICA,
    CANCELLAZIONE;

    public String value() {
        return name();
    }

    public static ProtocolloAggiornamentoAzioneType fromValue(String v) {
        return valueOf(v);
    }

}
