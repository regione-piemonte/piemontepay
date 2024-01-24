/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;

/**
 * @author fabio.fenoglio
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsTrasmettiFlussiInErrorePagoPA" )
public class DTOInputWsTrasmettiFlussiInErrorePagoPA extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private Long idFlusso;

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}


}
