/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOCodiceVersamento;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsCodiciVersamento" )
public class DTOOutputWsCodiciVersamento implements Serializable {

    private static final long serialVersionUID = -5868705165414045775L;
    
    private DTOOutputWsEsito esito;
    private List<DTOCodiceVersamento> codiciVersamento = new LinkedList<> ();
    
    /**
     * @return the esito
     */
    public DTOOutputWsEsito getEsito() {
		return esito;
	}
    
    /**
     * @param esito the esito to set
     */
	public void setEsito(DTOOutputWsEsito esito) {
		this.esito = esito;
	}

	/**
     * @return the codiciVersamento
     */
    public List<DTOCodiceVersamento> getCodiciVersamento () {
        return codiciVersamento;
    }

    /**
     * @param codiciVersamento the codiciVersamento to set
     */
    public void setCodiciVersamento ( List<DTOCodiceVersamento> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

}
