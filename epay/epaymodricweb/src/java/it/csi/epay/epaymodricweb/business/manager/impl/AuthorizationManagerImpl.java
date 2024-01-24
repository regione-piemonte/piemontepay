/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import java.security.InvalidParameterException;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.business.manager.AuthorizationManager;
import it.csi.epay.epaymodricweb.security.EntityAction;

@Service
public class AuthorizationManagerImpl implements AuthorizationManager {

	private Logger logger = LoggerFactory.getLogger(AuthorizationManager.class);

	public AuthorizationManagerImpl() {

	}

	@Override
	public boolean authorize(Class<?> entity, EntityAction action) {
		
		return authorize(entity, null, action);
	}
	
	@Override
	public boolean authorize(Class<?> entity, Long id, EntityAction action) {
		if (entity == null) {
			throw new InvalidParameterException();
		}
		
		// TODO Auto-generated method stub
		logger.warn("NOT_IMPLEMENTED");
		throw new NotImplementedException();
	}

	@Override
	public boolean authorize(Object vo, EntityAction action) {
		if (vo == null) {
			throw new InvalidParameterException();
		}
		
		// TODO Auto-generated method stub
		logger.warn("NOT_IMPLEMENTED");
		return false;
	}

}
