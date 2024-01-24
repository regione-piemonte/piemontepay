/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class AggiornaDatoSpecificoRiscossioneInput extends ParentInput {

	private static final long serialVersionUID = -6406460321868262074L;

	private Long idTassonomia;

	private String tassonomiaEsistente;

	private String tassonomiaNuova;

	public Long getIdTassonomia () {
		return idTassonomia;
	}

	public void setIdTassonomia ( Long idTassonomia ) {
		this.idTassonomia = idTassonomia;
	}

	public String getTassonomiaEsistente () {
		return tassonomiaEsistente;
	}

	public void setTassonomiaEsistente ( String tassonomiaEsistente ) {
		this.tassonomiaEsistente = tassonomiaEsistente;
	}

	public String getTassonomiaNuova () {
		return tassonomiaNuova;
	}

	public void setTassonomiaNuova ( String tassonomiaNuova ) {
		this.tassonomiaNuova = tassonomiaNuova;
	}
}
