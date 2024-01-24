/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per AzioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="AzioneType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="INSERIMENTO"/&gt;
 *     &lt;enumeration value="MODIFICA"/&gt;
 *     &lt;enumeration value="CANCELLAZIONE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AzioneType", namespace = "http://www.csi.it/epay/epaywso/types")
@XmlEnum
public enum AzioneType {

    INSERIMENTO,
    MODIFICA,
    CANCELLAZIONE;

    public String value() {
        return name();
    }

    public static AzioneType fromValue(String v) {
        return valueOf(v);
    }

}
