/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;

import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BigDecimalZeroResultSetExtractor implements ResultSetExtractor<BigDecimal> {
	public BigDecimal extractData(ResultSet rs) throws SQLException {
		
		BigDecimal result = null;
		
		if(rs.next()){
			result = rs.getBigDecimal(1);						
		}
		
		return result!=null?result:new BigDecimal(0);
	}
}
