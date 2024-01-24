/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTDatiSingolaRevocaDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiSingolaRevoca;

public class EpaypaTDatiSingolaRevocaDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTDatiSingolaRevoca> implements EpaypaTDatiSingolaRevocaDao {

    @Override
    public List<EpaypaTDatiSingolaRevoca> findAllByIdRr ( Long idRr ) throws PersistenceException {                    

        TypedQuery<EpaypaTDatiSingolaRevoca> queryRevoca = entityManager.createNamedQuery("EpaypaTDatiSingolaRevoca.findRevocaByIdRr", EpaypaTDatiSingolaRevoca.class);
        queryRevoca.setParameter("idRr", idRr);
        //
        List<EpaypaTDatiSingolaRevoca> entityListRevoca = new ArrayList<EpaypaTDatiSingolaRevoca>();
        entityListRevoca = queryRevoca.getResultList();
        return entityListRevoca;
        
    }
    
}
