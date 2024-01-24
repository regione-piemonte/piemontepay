/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;

import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountResultSetExtractor implements ResultSetExtractor<Integer> {

	public Integer extractData(ResultSet rs) throws SQLException {
		if(rs.next()){
			return new Integer(rs.getInt(1));
		}
		return null;
	}

}
