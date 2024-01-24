/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class DeleteInformativePSPDAO extends BaseDAO {
	
	Integer gg;
	public  DeleteInformativePSPDAO(Integer giorniBck) throws SerialException, SQLException {		
		gg = giorniBck;
		setStatementMapper(new GenericObjectArrayStatementMapper());	
	}

	@Override
	public String componiQuery() {
		String sql = "DELETE FROM INFORMATIVA_PSP WHERE datainserimento < CURRENT_TIMESTAMP - INTERVAL '"+gg+" days' ";
		return sql;
	}

}

