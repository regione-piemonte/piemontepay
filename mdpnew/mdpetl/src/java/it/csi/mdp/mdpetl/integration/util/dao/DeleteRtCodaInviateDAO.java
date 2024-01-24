/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class DeleteRtCodaInviateDAO extends BaseDAO {
	
	String inclause;
	public  DeleteRtCodaInviateDAO(String inclause) throws SerialException, SQLException {		
		this.inclause = inclause;
		setStatementMapper(new GenericObjectArrayStatementMapper());	
	}

	@Override
	public String componiQuery() {
		String sql = "DELETE FROM rt_coda_invio WHERE iuv in ( " + inclause + " )";
		return sql;
	}

}

