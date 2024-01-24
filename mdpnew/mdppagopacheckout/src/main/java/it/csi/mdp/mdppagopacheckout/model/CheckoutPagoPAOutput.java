/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public abstract class CheckoutPagoPAOutput {

	@JsonIgnore
	private boolean ok;

	public boolean isOk () {
		return ok;
	}

	public void setOk ( boolean ok ) {
		this.ok = ok;
	}
}
