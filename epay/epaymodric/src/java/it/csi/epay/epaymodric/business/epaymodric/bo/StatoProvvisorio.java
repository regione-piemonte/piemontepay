/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

public enum StatoProvvisorio {

    ANNULLATO,
    VALIDO;

    public String value() {
        return name();
    }

    public static StatoProvvisorio fromValue(String v) {
        return valueOf(v);
    }

}
