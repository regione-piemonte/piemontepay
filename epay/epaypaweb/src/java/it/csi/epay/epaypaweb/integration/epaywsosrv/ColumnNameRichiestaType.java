/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ColumnNameRichiestaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ColumnNameRichiestaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ID_RICHIESTA"/>
 *     &lt;enumeration value="CODICE_FISCALE_ENTE"/>
 *     &lt;enumeration value="ID_TIPO_RICHIESTA"/>
 *     &lt;enumeration value="ID_STATO_RICHIESTA"/>
 *     &lt;enumeration value="PAGAMENTO_SPONTANEO"/>
 *     &lt;enumeration value="CODICE_VERSAMENTO"/>
 *     &lt;enumeration value="DATA_INSERIMENTO_RICHIESTA"/>
 *     &lt;enumeration value="DATA_INVIO_AL_DESTINATARIO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ColumnNameRichiestaType")
@XmlEnum
public enum ColumnNameRichiestaType {

    ID_RICHIESTA,
    CODICE_FISCALE_ENTE,
    ID_TIPO_RICHIESTA,
    ID_STATO_RICHIESTA,
    PAGAMENTO_SPONTANEO,
    CODICE_VERSAMENTO,
    DATA_INSERIMENTO_RICHIESTA,
    DATA_INVIO_AL_DESTINATARIO;

    public String value() {
        return name();
    }

    public static ColumnNameRichiestaType fromValue(String v) {
        return valueOf(v);
    }

}
