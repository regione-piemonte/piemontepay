/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;

import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoubleResultSetExtractor implements ResultSetExtractor<Double>{

	public Double extractData(ResultSet rs) throws SQLException {
		if(rs.next())
			return new Double(rs.getDouble(1));
		return null;
	}

}
