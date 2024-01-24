/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.ConfigurazioneServiceElementsDTO;
import it.csi.epay.epayfeapi.entity.EpayTConfigurazione;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.repository.ConfigurazioneRepository;
import it.csi.epay.epayfeapi.security.User;
import org.openapitools.model.Error;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import static it.csi.epay.epayfeapi.util.Constants.CONFIG_MAX_ELEMENTS_PER_PAGE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_VALORE_DI_CONFIGURAZIONE_NOT_DEFINED;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;


@ApplicationScoped
@Transactional
public class ConfigurazioneService {

	@Inject
	ConfigurazioneRepository configurazioneRepository;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	public EpayTConfigurazione findByCodiceAndCodiceAndEnte ( String codice, EpayTEnti ente ) {
		return configurazioneRepository.findByCodiceAndEnte ( codice, ente );
	}

	// ottiene il numero massimo di elementi per pagina di default se non specificato il parametro di input elements
	public ConfigurazioneServiceElementsDTO getMaxElementPerPage ( String serviceName, User user, String organizationFiscalCode ) {
		EpayTConfigurazione configurazioneEntity = configurazioneRepository.findByCodice ( CONFIG_MAX_ELEMENTS_PER_PAGE );
		if ( configurazioneEntity == null ) {
			Response response
							= generateBusinessErrorResponse ( serviceName, ERROR_VALORE_DI_CONFIGURAZIONE_NOT_DEFINED,
							CONFIG_MAX_ELEMENTS_PER_PAGE );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return ConfigurazioneServiceElementsDTO.KO ( response );
		}
		int elements = Integer.parseInt ( configurazioneEntity.getValore () );
		Log.info ( "Impostato elements al valore di default:" + elements );
		return ConfigurazioneServiceElementsDTO.OK ( elements );
	}

}
