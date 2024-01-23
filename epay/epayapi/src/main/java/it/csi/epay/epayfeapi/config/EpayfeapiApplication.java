/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.config;

import it.csi.epay.epayfeapi.interceptor.ValidationExceptionMapper;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.core.Application;


@SuppressWarnings ( "unused" )
public class EpayfeapiApplication extends Application {

	public EpayfeapiApplication () {
		// Override principale dell'app, per aggiungere l'interceptor per le validazioni
		ResteasyProviderFactory.getInstance ().registerProvider ( ValidationExceptionMapper.class );
	}
}
