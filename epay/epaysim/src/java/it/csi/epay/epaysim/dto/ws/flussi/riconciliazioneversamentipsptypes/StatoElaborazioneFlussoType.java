/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatoElaborazioneFlussoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoElaborazioneFlussoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DA_ELABORARE"/>
 *     &lt;enumeration value="ELABORATO"/>
 *     &lt;enumeration value="IN_ERRORE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatoElaborazioneFlussoType", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
@XmlEnum
public enum StatoElaborazioneFlussoType {

    DA_ELABORARE,
    ELABORATO,
    IN_ERRORE;

    public String value() {
        return name();
    }

    public static StatoElaborazioneFlussoType fromValue(String v) {
        return valueOf(v);
    }

}
