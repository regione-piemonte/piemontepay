/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiSingolaRevoca;

public interface EpaypaTDatiSingolaRevocaDao extends EpaypaDaoBase<Long, EpaypaTDatiSingolaRevoca>{

    public List<EpaypaTDatiSingolaRevoca> findAllByIdRr(Long idRr) throws PersistenceException;
    
}
