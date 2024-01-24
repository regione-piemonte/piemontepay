/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

public class ParentInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private CallerInputDto caller;

	public CallerInputDto getCaller() {
		return caller;
	}

	public void setCaller(CallerInputDto caller) {
		this.caller = caller;
	}

}
