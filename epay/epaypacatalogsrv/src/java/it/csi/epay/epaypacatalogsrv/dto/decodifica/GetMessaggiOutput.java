/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class GetMessaggiOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

	private List<GetMessaggiOutputDto> messaggi;

	public List<GetMessaggiOutputDto> getMessaggi() {
		return messaggi;
	}

	public void setMessaggi(List<GetMessaggiOutputDto> messaggi) {
		this.messaggi = messaggi;
	}

}
