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

import it.csi.mdp.mdppagopaapi.business.ElementoMultiversamentoService;
import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamento;
import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamentoLight;
import it.csi.mdp.mdppagopaapi.integration.repository.ElementoMultiversamentoLightRepository;
import it.csi.mdp.mdppagopaapi.integration.repository.ElementoMultiversamentoRepository;

@Service
public class ElementoMultiversamentoServiceImpl implements ElementoMultiversamentoService {

    @Autowired
    private ElementoMultiversamentoRepository elementoMultiversamentoRepository;

    @Autowired
    private ElementoMultiversamentoLightRepository elementoMultiversamentoLightRepository;

    @Override
    public ElementoMultiversamento findByTransactionIdAndIuv ( String transactionId, String iuv ) {
        try {
            return elementoMultiversamentoRepository.findFirstByTransactionIdAndIuvOrderByIdDesc ( transactionId, iuv );
        } catch ( NoResultException e ) {
            return null;
        }
    }

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public ElementoMultiversamentoLight findByTransactionIdAndIuvLight ( String transactionId, String iuv ) {
        try {
            return elementoMultiversamentoLightRepository.findFirstByTransactionIdAndIuvOrderByIdDesc ( transactionId, iuv );
        } catch ( NoResultException e ) {
            return null;
        }
    }

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public ElementoMultiversamento findById ( int id ) {
        try {
            return elementoMultiversamentoRepository.findOne ( id );
        } catch ( NoResultException e ) {
            return null;
        }
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED )
    public ElementoMultiversamento insert ( ElementoMultiversamento elementoMultiversamento ) {
        return elementoMultiversamentoRepository.save ( elementoMultiversamento );
    }
}
