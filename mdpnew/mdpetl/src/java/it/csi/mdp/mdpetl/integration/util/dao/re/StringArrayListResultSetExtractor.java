/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;

import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StringArrayListResultSetExtractor implements ResultSetExtractor<List<String>> {

	public List<String> extractData(ResultSet rs) throws SQLException {
		List<String> a = new ArrayList<String>();
		
		while(rs.next()){
			a.add(rs.getString(1));			
		}		
		
		return a;
	}

}
