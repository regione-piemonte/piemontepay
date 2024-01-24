/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class AggiornaRPTInviataDAO extends BaseDAO{

	public  AggiornaRPTInviataDAO(int id) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(
				id
			));
		
	}
	
	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update rpt set da_inviare = false, data_invio=now(), last_update = now() where id = ?");
		return strSql.toString();
	}
}
