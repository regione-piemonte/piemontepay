/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model.notify;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class IoDefaultAddresses implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
