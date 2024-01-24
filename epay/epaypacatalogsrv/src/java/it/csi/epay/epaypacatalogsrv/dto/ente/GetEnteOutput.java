/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.ente;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class GetEnteOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

	public static GetEnteOutput ok(GetEnteOutputDto risultato) {
		GetEnteOutput output = ok(GetEnteOutput.class);
		output.setEnte(risultato);
		return output;
	}

	private GetEnteOutputDto ente;

	public GetEnteOutputDto getEnte() {
		return ente;
	}

	public void setEnte(GetEnteOutputDto ente) {
		this.ente = ente;
	}

}
