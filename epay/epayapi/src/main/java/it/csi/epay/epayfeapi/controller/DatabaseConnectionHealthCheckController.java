/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.controller;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;


@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheckController implements HealthCheck {

	@Override
	public HealthCheckResponse call () {
		return HealthCheckResponse.up ( "Custom Database connection health check" );
	}
}
