/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import it.csi.mdp.mdppagopacheckout.entity.Applicationcustomfield;
import it.csi.mdp.mdppagopacheckout.repository.ApplicationCustomFieldRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional
@SuppressWarnings ( "unused" )
public class ApplicationCustomFieldService {

	@Inject
	ApplicationCustomFieldRepository applicationCustomFieldRepository;

	@Inject
	EncryptionDecryptionService encryptionDecryptionService;

	public Applicationcustomfield getByApplicationIdAndFieldName ( String applicationId, String fieldName ) {
		return applicationCustomFieldRepository.getByApplicationIdAndFieldName ( applicationId, fieldName );
	}

	public String decrypt ( Applicationcustomfield applicationcustomfield ) {
		return encryptionDecryptionService.decrypt ( applicationcustomfield.getFieldvalue () );
	}
}
