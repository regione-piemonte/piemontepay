/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoPagamentoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoPagamentoType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="LDC"/&gt;
 *     &lt;enumeration value="PS"/&gt;
 *     &lt;enumeration value="REDS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TipoPagamentoType")
@XmlEnum
public enum TipoPagamentoType {

    LDC,
        PS,
        REDS,
        MABL,
        PABL;

    public String value() {
        return name();
    }

    public static TipoPagamentoType fromValue(String v) {
        return valueOf(v);
    }

}
