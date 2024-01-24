/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class ArchiviaFasceCostoServizioOldDAO extends BaseDAO{

	public  ArchiviaFasceCostoServizioOldDAO() throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(
				false
			));
		
	}
	
	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update fascia_costo_servizio set ");
		strSql.append(" valido=? ");
		return strSql.toString();
	}
}
