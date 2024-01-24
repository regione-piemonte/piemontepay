/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.ApplicationdetailService;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationdetail;
import it.csi.mdp.mdppagopaapi.integration.repository.ApplicationdetailRepository;


@Service
public class ApplicationdetailServiceImpl implements ApplicationdetailService {

    @Autowired
    private ApplicationdetailRepository applicationdetailRepository;

    @Override
    public Applicationdetail findTopByApplicationid ( String applicationId ) {

        try {
            return applicationdetailRepository.findTopById_Applicationid ( applicationId );
        } catch ( NoResultException e ) {
            return null;
        }
    }




}
