/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.DatiSingoloEsito;
import it.csi.mdp.core.business.exceptions.DaoException;

public interface DatiSingoloEsitoDao {
    /**
     * Method 'insert'
     * 
     * @param dto
     */
    public void insert(DatiSingoloEsito dto) throws DaoException;
    
    /**
     * Method 'update'
     * 
     * @param dto
     */
    public void update(DatiSingoloEsito dto) throws DaoException;
    
    /**
     * Method 'delete'
     * 
     * @param dto
     */
    public void delete(DatiSingoloEsito dto) throws DaoException;
}
