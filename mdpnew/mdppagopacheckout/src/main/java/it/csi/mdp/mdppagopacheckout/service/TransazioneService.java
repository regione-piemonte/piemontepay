/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import it.csi.mdp.mdppagopacheckout.entity.Transazione;
import it.csi.mdp.mdppagopacheckout.repository.StatoTransazioneEnum;
import it.csi.mdp.mdppagopacheckout.repository.StatoTransazioneRepository;
import it.csi.mdp.mdppagopacheckout.repository.TransazioneRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.Date;

import static it.csi.mdp.mdppagopacheckout.util.Constants.LANGUAGE_TRANSACTION;


@ApplicationScoped
@Transactional
@SuppressWarnings ( "unused" )
public class TransazioneService {

	@ConfigProperty ( name = "env.transaction" )
	String ENV_TRANSACTION;

	@Inject
	TransazioneRepository transazioneRepository;

	@Inject
	StatoTransazioneRepository statoTransazioneRepository;

	public String initTransazione ( String appId ) {
		var decimalFormat = new DecimalFormat ( "000000000000000" );
		var newPrimaryKey = ENV_TRANSACTION + decimalFormat.format ( transazioneRepository.getNextSequenceValue () );

		transazioneRepository.persist ( Transazione.builder ()
						.withApplicationId ( appId )
						.withTransactionId ( newPrimaryKey )
						.withLanguage ( LANGUAGE_TRANSACTION )
						.withInitDate ( new Date ( System.currentTimeMillis () ) )
						.withStatoTransazione ( statoTransazioneRepository.findById ( StatoTransazioneEnum.INITIALIZED.getCodice () ) )
						.build () );
		return newPrimaryKey;
	}

	public Transazione getByTransactionId ( String transactionId ) {
		return transazioneRepository.findByTransactionId ( transactionId );
	}
}
