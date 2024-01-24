/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class ModificaStatoInformativePSPDAO extends BaseDAO{

	public  ModificaStatoInformativePSPDAO(String statoNew,String statoOld) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(
				statoNew,
				statoOld
			));
		
	}
	
	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update informativa_psp set ");
		strSql.append(" statoinserimento=? WHERE statoinserimento = ?");
		return strSql.toString();
	}
}
