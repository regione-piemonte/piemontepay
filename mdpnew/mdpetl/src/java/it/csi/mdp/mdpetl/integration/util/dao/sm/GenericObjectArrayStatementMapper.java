/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.sm;

import it.csi.mdp.mdpetl.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

//import oracle.sql.CLOB;

public class GenericObjectArrayStatementMapper extends StatementMapperUtils {
	
	LogUtil log = new LogUtil(GenericObjectArrayStatementMapper.class);
	
	private final Object[] objs;

	public GenericObjectArrayStatementMapper(Object... objs){
		this.objs = objs;
	}

	

	@Override
	public String mapStatementParameters(PreparedStatement stmt) throws SQLException {
		StringBuilder parametri= new StringBuilder();

		for (Object o : objs) {
			setObj(stmt, o);

			parametri.append(o).append(",");
		}
		return parametri.toString();
	}

	private void setObj(PreparedStatement stmt, Object o) throws SQLException {
		if(o instanceof Integer){
			setInt(stmt, (Integer)o);
		} else if(o instanceof Double){
			setDouble(stmt, (Double)o);
		} else if(o instanceof String){
			setString(stmt, (String)o);
		} else if(o instanceof java.sql.Timestamp){
			setTimestamp(stmt, (Date)o);
		} else if(o instanceof Date){
			setDate(stmt, (Date)o);	
		} else if(o instanceof BigDecimal){
			setBigDecimal(stmt, (BigDecimal)o);	
		} 
		else if(o instanceof byte[]){
			setBinaryStream(stmt, new ByteArrayInputStream((byte[])o), ((byte[])o).length);	
		} 
		else if(o instanceof Boolean){
			setBoolean(stmt, (Boolean)o);
		} 
		else if(o instanceof Long){
		    setLong(stmt, (Long)o);
		} 
		/*
		else if(o instanceof char[]){
			//valido solo per Oracle
			CLOB c = CLOB.createTemporary(stmt.getConnection(), true, CLOB.DURATION_SESSION);
			Writer out = c.setCharacterStream(0);
			
			try {
				out.write((char[])o);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new SQLException("IOException while writing Clob:"+e.getMessage());
			} 
							
			setClob(stmt,c);
		} 
		*/
		else if(o == null){	
			setNull(stmt);
		} else {
			throw new IllegalArgumentException("GenericObjectArrayStatementMapper: Tipo oggetto non supportato ["+ o.getClass() +"]");
		}
		
	}
}
