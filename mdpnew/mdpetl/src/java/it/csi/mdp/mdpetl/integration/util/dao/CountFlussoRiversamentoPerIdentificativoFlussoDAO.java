/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;


/**
 *
 */

public class CountFlussoRiversamentoPerIdentificativoFlussoDAO extends BaseDAO<Integer> {


	private final String identificativoFlusso;

	public CountFlussoRiversamentoPerIdentificativoFlussoDAO ( String identificativoFlusso) {
		if ( StringUtils.isBlank ( identificativoFlusso ) ) {
			throw new IllegalArgumentException ( "Assenza di identificativoFlusso" );
		}
		this.identificativoFlusso = identificativoFlusso;
		this.setResultSetExtractor (new CountFlussoRendicontazioneExtractor());
	}

	@Override
	public String componiQuery () {
		String sql
			= "SELECT 1 "
				+ "FROM flusso_riversamento fr  "
				+ "WHERE 1=1 and fr.identificativoflusso = '" + identificativoFlusso + "' limit 1";

		System.out.println ( "sql: " + sql );
		return sql;
		
		
	}

	
	class CountFlussoRendicontazioneExtractor implements ResultSetExtractor<Integer> {
	    
	    public Integer extractData(ResultSet rs) throws SQLException {
	        Integer count = 0;
	        if(rs!=null && rs.next())
	            count = rs.getInt(1);
	        
	        return count;
	    }
	}

}
