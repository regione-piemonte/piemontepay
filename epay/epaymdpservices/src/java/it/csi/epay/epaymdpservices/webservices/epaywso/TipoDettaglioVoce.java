/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.epaywso;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoDettaglioVoce.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoDettaglioVoce">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IMPORTO_TRANSATO"/>
 *     &lt;enumeration value="IMPORTO_AUTORIZZATO"/>
 *     &lt;enumeration value="IMPORTO_COMMISSIONI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoDettaglioVoce")
@XmlEnum
public enum TipoDettaglioVoce {

    IMPORTO_TRANSATO,
    IMPORTO_AUTORIZZATO,
    IMPORTO_COMMISSIONI;

    public String value() {
        return name();
    }

    public static TipoDettaglioVoce fromValue(String v) {
        return valueOf(v);
    }

}
