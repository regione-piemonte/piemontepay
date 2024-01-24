/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.exceptions.DaoException;

public interface RRDao {
    
    /**
     * Method 'insert'
     * 
     * @param dto
     */
    public void insert(RR dto) throws DaoException;
    
    /**
     * Method 'update'
     * 
     * @param dto
     */
    public void update(RR dto) throws DaoException;
    
    /**
     * Method 'delete'
     * 
     * @param dto
     */
    public void delete(RR dto) throws DaoException;
    
    
    /**
     * Method 'createRRAndRTData'
     * 
     * @param dto
     */
    public Integer createRRData(RR dto) throws DaoException;
    

}
