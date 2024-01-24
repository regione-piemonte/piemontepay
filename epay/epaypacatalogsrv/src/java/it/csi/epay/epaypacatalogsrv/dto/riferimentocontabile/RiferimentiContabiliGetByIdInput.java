/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RiferimentiContabiliGetByIdInput extends ParentInput {

	private static final long serialVersionUID = -9143785935946312664L;

	private long id;

	public long getId () {
		return id;
	}

	public void setId ( long id ) {
		this.id = id;
	}
}
