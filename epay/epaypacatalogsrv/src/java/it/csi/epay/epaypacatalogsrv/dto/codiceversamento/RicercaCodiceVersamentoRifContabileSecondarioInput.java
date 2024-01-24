/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RicercaCodiceVersamentoRifContabileSecondarioInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long idEnte;

	public Long getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}
    
    

}
