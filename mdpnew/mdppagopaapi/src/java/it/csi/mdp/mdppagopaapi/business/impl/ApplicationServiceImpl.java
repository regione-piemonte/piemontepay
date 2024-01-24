/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.ApplicationService;
import it.csi.mdp.mdppagopaapi.integration.domain.Application;
import it.csi.mdp.mdppagopaapi.integration.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application findById ( String applicationId ) {

        return applicationRepository.findById ( applicationId );
    }

    @Override
    public Application isExists ( String applicationId ) {

        return applicationRepository.findTopById ( applicationId );
    }

    @Override
    public List<Application> findAllById ( List<String> applicationId ) {
        return applicationRepository.findAllByIdIn ( applicationId );
    }
}
