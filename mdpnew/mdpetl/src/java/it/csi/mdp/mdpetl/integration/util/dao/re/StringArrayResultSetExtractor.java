/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;

import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StringArrayResultSetExtractor implements ResultSetExtractor<String[]> {

	public String[] extractData(ResultSet rs) throws SQLException {
		Vector a = new Vector();
		
		while(rs.next()){
			a.add(rs.getString(1));			
		}		
		
		return (String[])a.toArray(new String[a.size()]);
	}

}
