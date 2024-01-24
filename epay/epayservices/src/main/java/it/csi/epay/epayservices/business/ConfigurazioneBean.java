/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.ConfigurazioneManager;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Configurazione;


@Stateless ( name = "ConfigurazioneFacade", mappedName = "Configurazione" )
public class ConfigurazioneBean extends _BaseBean implements ConfigurazioneFacade {
	
	@EJB
    private ConfigurazioneManager configurazioneManager;
	
	
	@Override
    public Configurazione ricerca ( String codice, String codiceFiscale ) throws NoDataException, IllegalArgumentException {
        Configurazione configurazione = configurazioneManager.getConfigurazione ( codice, codiceFiscale );
        return configurazione;
	}
}
