/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class GetOpzioniOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

	private List<DecodificaOutputDto> opzioni;

	public List<DecodificaOutputDto> getOpzioni() {
		return opzioni;
	}

	public void setOpzioni(List<DecodificaOutputDto> opzioni) {
		this.opzioni = opzioni;
	}
}
