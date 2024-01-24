/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class VerificaFlussoGiaPresenteDAO extends BaseDAO<Integer> {
	 LogUtil log = new LogUtil(VerificaFlussoGiaPresenteDAO.class);
	
	public  VerificaFlussoGiaPresenteDAO(String identificativoflusso) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(identificativoflusso));
		setResultSetExtractor(new CountExtractor());
	}

	@Override
	public String componiQuery() {
		return "select count(*) from flusso_riversamento  where identificativoflusso = ?";
	}

}

class CountExtractor implements ResultSetExtractor<Integer> {
	
	public Integer extractData(ResultSet rs) throws SQLException {
		Integer count = 0;
		if(rs.next())
			count = rs.getInt(1);
		
		return count;
	}
}

