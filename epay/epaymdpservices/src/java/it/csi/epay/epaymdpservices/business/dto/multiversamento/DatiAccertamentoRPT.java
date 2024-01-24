/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business.dto.multiversamento;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "DatiAccertamentoRPT", propOrder = {
    "annoAccertamento",
    "numeroAccertamento"
} )
public class DatiAccertamentoRPT implements Serializable {  //TAG_PPU - 2019 - RDI 004 - RDI 005

    private static final long serialVersionUID = 307978343289941379L;
     
    private Integer annoAccertamento;

    protected Integer numeroAccertamento;

    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

}
