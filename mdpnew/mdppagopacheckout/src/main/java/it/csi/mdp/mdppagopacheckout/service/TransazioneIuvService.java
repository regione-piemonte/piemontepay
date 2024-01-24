/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import it.csi.mdp.mdppagopacheckout.repository.TransazioneIuvRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@SuppressWarnings ( "unused" )
@ApplicationScoped
@Transactional
public class TransazioneIuvService {

	@Inject
	TransazioneIuvRepository transazioneIuvRepository;

	public void saveTransazioneIuv ( String transactionId, String iuv ) {
		transazioneIuvRepository.save ( transactionId, iuv );
	}
}
