/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.ApplicationCustomFieldService;
import it.csi.mdp.mdppagopaapi.integration.domain.Application;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;
import it.csi.mdp.mdppagopaapi.integration.repository.ApplicationCustomFieldRepository;

@Service
public class ApplicationCustomFieldServiceImpl implements ApplicationCustomFieldService {

    @Autowired
    private ApplicationCustomFieldRepository applicationCustomFieldRepository;

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public List<Applicationcustomfield> findAllByApplicationInAndFieldnameIs ( Collection<Application> applications, String fieldName ) {
        return applicationCustomFieldRepository.findAllByApplicationInAndFieldnameIs ( applications, fieldName );
    }

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public Applicationcustomfield findTopByApplicationAndFieldnameIs ( Application application, String fieldName ) {
        return applicationCustomFieldRepository.findTopByApplicationAndFieldnameIs ( application.getId (), fieldName );
    }

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public Applicationcustomfield findById ( int id ) {
        return applicationCustomFieldRepository.findOne ( id );
    }
    
    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public List<Applicationcustomfield>  findAllByApplicationEnabled ( String applicationId ) {
        return applicationCustomFieldRepository.findAllByApplicationEnabled ( applicationId );
    }



}
