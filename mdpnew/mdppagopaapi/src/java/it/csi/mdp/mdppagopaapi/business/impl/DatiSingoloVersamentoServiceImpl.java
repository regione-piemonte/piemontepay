/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.DatiSingoloVersamentoService;
import it.csi.mdp.mdppagopaapi.integration.domain.DatiSingoloVersamento;
import it.csi.mdp.mdppagopaapi.integration.repository.DatiSingoloVersamentoRepository;

@Service
public class DatiSingoloVersamentoServiceImpl implements DatiSingoloVersamentoService {

    @Autowired
    private DatiSingoloVersamentoRepository datiSingoloVersamentoRepository;

    @Override
    public DatiSingoloVersamento inserisciSingoloVersamento ( DatiSingoloVersamento datiSingoloVersamento ) {
        return datiSingoloVersamentoRepository.save ( datiSingoloVersamento );
    }

    @Override
    public List<DatiSingoloVersamento> findByMultiId ( int elementoMultiversamento ) {

        try {
            return datiSingoloVersamentoRepository.findByMultiId ( elementoMultiversamento );
        } catch ( NoResultException e ) {
            return null;
        }
    }

    @Override
    public DatiSingoloVersamento insert ( DatiSingoloVersamento datiSingoloVersamento ) {
        // TODO Auto-generated method stub
        return datiSingoloVersamentoRepository.save ( datiSingoloVersamento );
    }

}
