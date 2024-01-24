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
@XmlType ( name = "dtoOutputWsTrasmettiFlussiPagoPA",
                propOrder = { "esito", "trasmettiFlussoRiconciliazioneRequest" } )
public class DTOOutputWsTrasmettiFlussiPagoPA implements Serializable {

    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private String trasmettiFlussoRiconciliazioneRequest;

    public DTOOutputWsTrasmettiFlussiPagoPA () {
        super ();
    }

    public String getTrasmettiFlussoRiconciliazioneRequest () {
        return trasmettiFlussoRiconciliazioneRequest;
    }

    public void setTrasmettiFlussoRiconciliazioneRequest ( String trasmettiFlussoRiconciliazioneRequest ) {
        this.trasmettiFlussoRiconciliazioneRequest = trasmettiFlussoRiconciliazioneRequest;
    }

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }

}
