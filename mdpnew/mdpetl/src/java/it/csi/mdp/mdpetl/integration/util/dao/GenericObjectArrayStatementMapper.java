/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

//import oracle.sql.CLOB;

public class GenericObjectArrayStatementMapper extends StatementMapperUtils {
	
	LogUtil log = new LogUtil(GenericObjectArrayStatementMapper.class);
	
	private Object[] objs;



	/*public GenericObjectArrayStatementMapper(Object a){
		this(new Object[]{a});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b){
		this(new Object[]{a,b});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b,Object c){
		this(new Object[]{a,b,c});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b,Object c,Object d){		
		this(new Object[]{a,b,c,d});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b,Object c,Object d, Object e){
		this(new Object[]{a,b,c,d,e});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b,Object c,Object d, Object e,Object f){
		this(new Object[]{a,b,c,d,e,f});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b,Object c,Object d, Object e,Object f,Object g){
		this(new Object[]{a,b,c,d,e,f,g});
	}
	
	public GenericObjectArrayStatementMapper(Object a, Object b,Object c,Object d, Object e,Object f,Object g,Object h){
		this(new Object[]{a,b,c,d,e,f,g,h});
	}

	public GenericObjectArrayStatementMapper(Object a, Object b,Object c,Object d, Object e,Object f,Object g,Object h,Object i){
		this(new Object[]{a,b,c,d,e,f,g,h,i});
	}*/
	
	public GenericObjectArrayStatementMapper(Object... objs){
		this.objs = objs;
	}

	

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
		} else if(o instanceof byte[]){
			setBinaryStream(stmt, new ByteArrayInputStream((byte[])o), ((byte[])o).length);	
		} else if(o instanceof Long){
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
