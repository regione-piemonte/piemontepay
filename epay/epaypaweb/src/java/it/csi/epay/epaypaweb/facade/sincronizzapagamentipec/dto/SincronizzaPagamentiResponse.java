/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "esito"
})
@XmlRootElement(name = "SincronizzaPagamentiResponse")
public class SincronizzaPagamentiResponse {

    @XmlElement(required = true)
    protected ResponseType esito;

    public ResponseType getEsito() {
        return esito;
    }

    public void setEsito(ResponseType value) {
        esito = value;
    }

}
