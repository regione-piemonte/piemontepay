/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ProtocolloAggiornamentoAzioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ProtocolloAggiornamentoAzioneType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="INSERIMENTO"/&gt;
 *     &lt;enumeration value="MODIFICA"/&gt;
 *     &lt;enumeration value="CANCELLAZIONE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
