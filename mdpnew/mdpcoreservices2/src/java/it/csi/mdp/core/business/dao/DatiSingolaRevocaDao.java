/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.DatiSingolaRevoca;
import it.csi.mdp.core.business.exceptions.DaoException;

public interface DatiSingolaRevocaDao {
    /**
     * Method 'insert'
     * 
     * @param dto
     */
    public void insert(DatiSingolaRevoca dto) throws DaoException;
    
    /**
     * Method 'update'
     * 
     * @param dto
     */
    public void update(DatiSingolaRevoca dto) throws DaoException;
    
    /**
     * Method 'delete'
     * 
     * @param dto
     */
    public void delete(DatiSingolaRevoca dto) throws DaoException;
}
