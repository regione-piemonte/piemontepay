/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import it.csi.epay.epayservices.integration.db.manager.ConfigurazioniCampiRedirectAsyncManager;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioniCampiRedirectAsyncFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ConfigurazioniCampiRedirectAsync;

/**
 *
 */
@Singleton(name = "ConfigurazioniCampiRedirectAsyncFacade", mappedName = "configurazioniCampiRedirectAsync")
public class ConfigurazioniCampiRedirectAsyncBean extends _BaseBean implements ConfigurazioniCampiRedirectAsyncFacade {

    @EJB
    private ConfigurazioniCampiRedirectAsyncManager campiRedirectAsyncManager;
    
    @Override
    public List<ConfigurazioniCampiRedirectAsync> getConfigurazioneCampi () throws NoDataException {
    	return campiRedirectAsyncManager.getConfigurazioni ();
    }

}
