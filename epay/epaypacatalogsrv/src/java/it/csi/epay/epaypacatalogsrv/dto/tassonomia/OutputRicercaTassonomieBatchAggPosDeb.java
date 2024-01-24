/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

import java.util.List;


public class OutputRicercaTassonomieBatchAggPosDeb extends ParentOutput {

	private static final long serialVersionUID = -7436083426152117597L;

	private Long id;

	private String tipoServizio;

	private String datiSpecificiIncasso;

	public static OutputRicercaTassonomieBatchAggPosDeb ok ( List<OutputRicercaTassonomieBatchAggPosDeb> output ) {
		return ok ( OutputRicercaTassonomieBatchAggPosDeb.class );
	}

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getTipoServizio () {
		return tipoServizio;
	}

	public void setTipoServizio ( String tipoServizio ) {
		this.tipoServizio = tipoServizio;
	}

	public String getDatiSpecificiIncasso () {
		return datiSpecificiIncasso;
	}

	public void setDatiSpecificiIncasso ( String datiSpecificiIncasso ) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}
}
