/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.security;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public class AuthenticationContextImpl implements AuthenticationContext {

	private User user;

	@Override
	public User getCurrentUser () {
		return user;
	}

	public void setCurrentUser ( User user ) {
		this.user = user;
	}
}
