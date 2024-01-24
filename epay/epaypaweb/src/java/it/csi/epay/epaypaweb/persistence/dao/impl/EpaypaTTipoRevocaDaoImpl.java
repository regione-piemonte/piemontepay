/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTTipoRevocaDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;

public class EpaypaTTipoRevocaDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTTipoRevoca> implements EpaypaTTipoRevocaDao{

    @Override
    public EpaypaTTipoRevoca findTipoRevocaById ( int id ) throws PersistenceException {
        
 
        
        TypedQuery<EpaypaTTipoRevoca> queryNotificaPagamento = entityManager.createNamedQuery("EpaypaTTipoRevoca.findTipoRevocaById", EpaypaTTipoRevoca.class);
        queryNotificaPagamento.setParameter("id", id);
        EpaypaTTipoRevoca tipoRevoca = queryNotificaPagamento.getSingleResult ();
        
        return tipoRevoca;
    
    
    }

}
