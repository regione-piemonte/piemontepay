/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EnteEnum;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoflussi", propOrder = { "idEnte", "identificativiFlusso" } )
public class DTOFlussi implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idEnte = EnteEnum.ENTE_SCONOSCIUTO.getCodice ();

    private List<DTOFlusso> identificativiFlusso = new ArrayList<DTOFlusso> ();

    public DTOFlussi () {
        super ();
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public List<DTOFlusso> getIdentificativiFlusso () {
        return identificativiFlusso;
    }

    public void setIdentificativiFlusso ( List<DTOFlusso> identificativiFlusso ) {
        this.identificativiFlusso = identificativiFlusso;
    }

}
