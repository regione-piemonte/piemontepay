/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

public class EstraiDataOraFlussoDAO extends BaseDAO< List<Timestamp>> {
	 LogUtil log = new LogUtil(EstraiDataOraFlussoDAO.class);
	
	public  EstraiDataOraFlussoDAO(String identificativoflusso) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(identificativoflusso));
		setResultSetExtractor(new DateExtractor());
	}

	@Override
	public String componiQuery() {
		return "select dataoraflusso from flusso_riversamento  where identificativoflusso = ?";
	}

}

class DateExtractor implements ResultSetExtractor< List<Timestamp>> {
	
	public List<Timestamp> extractData(ResultSet rs) throws SQLException {
	    List<Timestamp> dataOraFlusso = new LinkedList<Timestamp> ();
		    while (rs.next()) {
		        dataOraFlusso.add ( rs.getTimestamp ( 1 ) );
            }
		return dataOraFlusso;
	}
}

