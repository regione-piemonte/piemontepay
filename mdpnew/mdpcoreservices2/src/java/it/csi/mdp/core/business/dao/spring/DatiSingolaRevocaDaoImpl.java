/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import it.csi.mdp.core.business.dao.DatiSingolaRevocaDao;
import it.csi.mdp.core.business.dto.DatiSingolaRevoca;
import it.csi.mdp.core.business.exceptions.DaoException;

public class DatiSingolaRevocaDaoImpl extends AbstractDAO implements ParameterizedRowMapper<DatiSingolaRevoca>, DatiSingolaRevocaDao{

    public void insert ( DatiSingolaRevoca dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public void update ( DatiSingolaRevoca dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public void delete ( DatiSingolaRevoca dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public DatiSingolaRevoca mapRow ( ResultSet arg0, int arg1 ) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
