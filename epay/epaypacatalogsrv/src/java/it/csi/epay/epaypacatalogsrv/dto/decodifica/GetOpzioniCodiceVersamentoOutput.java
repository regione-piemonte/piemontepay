/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class GetOpzioniCodiceVersamentoOutput extends  ParentOutput {

	private static final long serialVersionUID = 1L;
	
	private List<DecodificaCodiciVersamentoOutputDto> opzioni;

	public List<DecodificaCodiciVersamentoOutputDto> getOpzioni() {
		return opzioni;
	}

	public void setOpzioni(List<DecodificaCodiciVersamentoOutputDto> opzioni) {
		this.opzioni = opzioni;
	}
	
	
}
