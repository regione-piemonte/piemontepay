/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.config;

import it.csi.mdp.mdppagopacheckout.provider.ConstraintViolationExceptionMapper;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.core.Application;


@SuppressWarnings ( "unused" )
public class MdppagopacheckoutApplication extends Application {

	public MdppagopacheckoutApplication () {
		ResteasyProviderFactory.getInstance ().registerProvider ( ConstraintViolationExceptionMapper.class );
	}
}
