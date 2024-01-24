/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class FlussoNextvalDAO extends BaseDAO<Integer> {
	 LogUtil log = new LogUtil(FlussoNextvalDAO.class);
	
	public  FlussoNextvalDAO() throws SerialException, SQLException {
		setResultSetExtractor(new NextValFlussoExtractor());
	}

	@Override
	public String componiQuery() {
		return "select nextval('flusso_keyid_seq') nv";
	}

}

class NextValFlussoExtractor implements ResultSetExtractor<Integer> {
	
	public Integer extractData(ResultSet rs) throws SQLException {
		Integer nextval = null;
		if(rs.next()){
			nextval = rs.getInt("nv");
		}
		
		return nextval;
	}
	
}

