/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class ModificaRTDataInvioFallitoDAO extends BaseDAO{

	public  ModificaRTDataInvioFallitoDAO(Integer id) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(id));
		
	}
	
	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update rt set data_invio_fallito=now(), last_update = now() WHERE id = ?");
		return strSql.toString();
	}
}
