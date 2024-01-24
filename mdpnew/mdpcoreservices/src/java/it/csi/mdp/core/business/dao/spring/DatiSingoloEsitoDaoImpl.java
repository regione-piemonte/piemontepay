/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import it.csi.mdp.core.business.dao.DatiSingoloEsitoDao;
import it.csi.mdp.core.business.dto.DatiSingoloEsito;
import it.csi.mdp.core.business.exceptions.DaoException;

public class DatiSingoloEsitoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<DatiSingoloEsito>, DatiSingoloEsitoDao {

    public void insert ( DatiSingoloEsito dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public void update ( DatiSingoloEsito dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public void delete ( DatiSingoloEsito dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public DatiSingoloEsito mapRow ( ResultSet arg0, int arg1 ) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
