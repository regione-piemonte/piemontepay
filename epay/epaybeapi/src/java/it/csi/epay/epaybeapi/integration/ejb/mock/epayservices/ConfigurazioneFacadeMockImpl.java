/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.ejb.mock.epayservices;

import org.springframework.stereotype.Service;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Configurazione;

@Service
public class ConfigurazioneFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade{

	@Override
	public Configurazione ricerca(String codice, String codiceFiscale)
			throws NoDataException, IllegalArgumentException {
		Configurazione conf = new Configurazione();
		conf.setDescrizione("responce mock");
		conf.setCodice("responce mock");
		conf.setValore("responce mock");
		
		return conf;
	}

}
