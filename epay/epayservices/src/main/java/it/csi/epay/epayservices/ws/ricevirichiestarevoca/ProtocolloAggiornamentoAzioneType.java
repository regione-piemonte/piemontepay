/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ProtocolloAggiornamentoAzioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
@XmlType(name = "ProtocolloAggiornamentoAzioneType", namespace = "http://www.csi.it/epay/epaywso/types")
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
