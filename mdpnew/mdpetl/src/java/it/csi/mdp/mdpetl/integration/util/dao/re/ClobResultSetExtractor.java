/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;
import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobResultSetExtractor implements ResultSetExtractor {

	public String extractData(ResultSet rs) throws SQLException {
		if(rs.next()){
			Clob clob = rs.getClob(1);
			
			StringBuilder strOut = new StringBuilder();
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			
			try {
				String buf;
				while ((buf=br.readLine())!=null){
					strOut.append(buf);
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new SQLException("IOException durante la lettura del clob.");
			}
			
			return strOut.toString();
		}
		return null;
	}

}
