/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.IuvOtticiService;
import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;
import it.csi.mdp.mdppagopaapi.integration.repository.IuvOtticiRepository;

@Service
public class IuvOtticiServiceImpl implements IuvOtticiService {

    @Autowired
    private IuvOtticiRepository iuvOtticiRepository;

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public IuvOttici findTopByIuvOttico ( String iuv ) {
        try {
            return iuvOtticiRepository.findTopByIuvOttico ( iuv );
        } catch ( NoResultException e ) {
            return null;
        }
    }


}
