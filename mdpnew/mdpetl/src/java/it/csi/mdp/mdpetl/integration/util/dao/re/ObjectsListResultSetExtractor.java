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

public class ObjectsListResultSetExtractor<T> implements ResultSetExtractor<List<T>> {
	
	
	private ResultSetExtractor<T> rse;

	public ObjectsListResultSetExtractor(ResultSetExtractor<T> rse) {
		this.rse = rse;
	}

	public List<T> extractData(ResultSet rs) throws SQLException, Exception {
		
		List<T> list = new ArrayList<T>();
				
		T o = rse.extractData(rs);
		
		while (o!=null){
			list.add(o);
			
			o = rse.extractData(rs);
		}
		
		return list;
	}

}
