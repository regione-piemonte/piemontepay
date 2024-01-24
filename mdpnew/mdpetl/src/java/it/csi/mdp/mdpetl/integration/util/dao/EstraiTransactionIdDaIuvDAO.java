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

public class EstraiTransactionIdDaIuvDAO extends BaseDAO<String> {
	 LogUtil log = new LogUtil(EstraiTransactionIdDaIuvDAO.class);
	
	public  EstraiTransactionIdDaIuvDAO(String iuv) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new TransactionIdExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from transazione_iuv where iuv = ? ";
	}

}

class TransactionIdExtractor implements ResultSetExtractor<String> {
	
	public String extractData(ResultSet rs) throws SQLException {
		String id = null;
		while(rs.next()){
			id = rs.getString("transaction_id");
		}
		
		return id;
	}
	
}

