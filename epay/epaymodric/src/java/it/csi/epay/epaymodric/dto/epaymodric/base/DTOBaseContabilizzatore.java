/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @author Gueye Classe contenitore base per le risposte ws contabilizzatore
 */

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoBaseContabilizzatore", propOrder = {"risposta"})
public class DTOBaseContabilizzatore implements Serializable {

    private static final long serialVersionUID = 1L;

    private DTOBaseResponse risposta = new DTOBaseResponse ();

    public DTOBaseContabilizzatore () {
        super ();
        risposta = new DTOBaseResponse ();
    }

    public DTOBaseResponse getRisposta () {
        return risposta;
    }

    public void setRisposta ( DTOBaseResponse risposta ) {
        this.risposta = risposta;
    }

}
