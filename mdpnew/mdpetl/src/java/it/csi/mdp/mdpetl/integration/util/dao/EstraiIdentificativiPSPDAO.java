/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

public class EstraiIdentificativiPSPDAO extends BaseDAO<List<String>> {
	 LogUtil log = new LogUtil(EstraiIdentificativiPSPDAO.class);
	
	public  EstraiIdentificativiPSPDAO() throws SerialException, SQLException {
		setResultSetExtractor(new ElencoPSP());
	}

	@Override
	public String componiQuery() {
		return "select distinct identificativopsp from informativa_psp ";
	}

}

class ElencoPSP implements ResultSetExtractor<List<String>> {
	
	public List<String> extractData(ResultSet rs) throws SQLException {
		List<String> elencoPSP = new ArrayList<String>();
		while(rs.next()){
			elencoPSP.add(rs.getString("identificativopsp"));
		}
		
		return elencoPSP;
	}
	
}

