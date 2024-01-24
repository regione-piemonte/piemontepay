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

public class EstraiApplicationIdDaRPTDAO extends BaseDAO<String> {
	 LogUtil log = new LogUtil(EstraiApplicationIdDaRPTDAO.class);
	
	public  EstraiApplicationIdDaRPTDAO(String iuv) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new ApplicationIdRPTExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from rpt where iuv = ? ";
	}

}

class ApplicationIdRPTExtractor implements ResultSetExtractor<String> {
	
	public String extractData(ResultSet rs) throws SQLException {
		String id = null;
		while(rs.next()){
			id = rs.getString("application_id");
		}
		
		return id;
	}
	
}

