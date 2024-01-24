/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.security;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.util.Date;


@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter {

	@Inject
	AuthenticationContextImpl authCtx;

	@Context
	SecurityContext securityCtx;

	@Context
	io.vertx.core.http.HttpServerRequest httpRequest;

	@Override
	public void filter ( ContainerRequestContext requestContext ) {
		String name = securityCtx.getUserPrincipal ().getName ();
		User user = new User ( name, httpRequest.remoteAddress ().hostAddress (), new Date () );
		authCtx.setCurrentUser ( user );
	}
}
