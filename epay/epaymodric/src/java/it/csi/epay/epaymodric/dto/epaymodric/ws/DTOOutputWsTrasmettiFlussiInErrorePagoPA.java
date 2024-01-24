/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

/*
 * Copyright 2001-2018 CSI Piemonte. All Rights Reserved.
 *
 * This software is proprietary information of CSI Piemonte.
 * Use is subject to license terms.
 *
 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author fabio.fenoglio
 *
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsTrasmettiFlussiInErrorePagoPA",
                propOrder = { "esito", "trasmettiFlussiInErroreRequest" } )
public class DTOOutputWsTrasmettiFlussiInErrorePagoPA implements Serializable {

    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private String trasmettiFlussiInErroreRequest;

    public String getTrasmettiFlussiInErroreRequest () {
        return trasmettiFlussiInErroreRequest;
    }

    public void setTrasmettiFlussiInErroreRequest ( String trasmettiFlussiInErroreRequest ) {
        this.trasmettiFlussiInErroreRequest = trasmettiFlussiInErroreRequest;
    }

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

}
