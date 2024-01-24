/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.business.manager.EnteManager;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.model.GenericVO;


@Service
public class EnteManagerImpl implements EnteManager {

    @Autowired
    private EpaymodricWsFacade service;

    @Override
    public List<GenericVO> getListaOpzioniEnte () throws OperationFailedException {
        return service.ricercaEnte (false);
    }

	@Override
	public List<GenericVO> getListaOpzioniEnteConIdEnte() throws OperationFailedException {
		
		return service.ricercaEnte(true);
	}

}
