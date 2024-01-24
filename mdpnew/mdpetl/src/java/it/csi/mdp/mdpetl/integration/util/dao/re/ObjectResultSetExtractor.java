/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.re;


import it.csi.mdp.mdpetl.integration.util.dao.ResultSetExtractor;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;




/**
 * Questa classe è in fase sperimentale!!! supporta solo le PersistenceField annotation dichiarate fino alla prima superclasse.
 * @author 71551
 *
 * @param <T>
 */
public class ObjectResultSetExtractor<T> implements ResultSetExtractor<T> {
	LogUtil log = new LogUtil(ObjectResultSetExtractor.class);
	
	
	private T obj;

	public ObjectResultSetExtractor(T obj) {
		this.obj = obj;
	}

	public T extractData(ResultSet rs) throws SQLException, Exception {
		final String methodName = "extractData";
		
		if(!rs.next()){
			log.debug(methodName, "no data");
			return null;
		}
		if(obj==null){
			log.debug(methodName, "no object");
			return null;			
		}
		
		
		
		Field[] fields = obj.getClass().getDeclaredFields();

		Field[] fieldsSuperclass = obj.getClass().getSuperclass().getSuperclass().getDeclaredFields();

		Field[] allFields = new Field[fields.length + fieldsSuperclass.length];
		System.arraycopy(fields, 0, allFields, 0, fields.length);
		System.arraycopy(fieldsSuperclass, 0, allFields, fields.length, fieldsSuperclass.length);
		
		
		
		for (int i = 0; i < allFields.length; i++) {
			Field f = allFields[i];
			PersistenceField a = f.getAnnotation(PersistenceField.class);
			if(a!=null){
				
				String columnName = a.column();
				int columnIdx = rs.findColumn(columnName);				
				int columnType = rs.getMetaData().getColumnType(columnIdx);
								
				Object  value = getValue(rs, columnIdx, columnType);
				log.debug(methodName, "columnName:"+columnName + " value:"+value);
				
				try {
					Method accessorSet = null;
					
					Class c = obj.getClass();
					while(c != null ){
						try{
							accessorSet = c.getDeclaredMethod("set"+StringUtils.capitalize(f.getName()), f.getType());
							c = null;
						}catch (NoSuchMethodException e) {
							Class superclass = c.getSuperclass();
							if(superclass==null){
								String msg = "Impossibile trovare il setter method corrispondente alla colonna: "+columnName;
								log.error(methodName,msg,e);
								throw new Exception(msg,e);
							} else {
								c = superclass;
							}
						}					
					}
					
					accessorSet.invoke(obj, value /*rs.getObject(a.column())*/);
					
					
			
				}catch (SecurityException e) {
					String msg = "Impossibile accedere al setter method corrispondente alla colonna: "+columnName;
					log.error(methodName,msg,e);
					throw new Exception(msg,e);
				} catch (NoSuchMethodException e) {
					String msg = "Impossibile trovare il setter method corrispondente alla colonna: "+columnName;
					log.error(methodName,msg,e);
					throw new Exception(msg,e);
				} catch (IllegalArgumentException e) {
					String msg = "Argomento [tipo: "+value!=null?value.getClass().getName():"null"
						+"] non valido per il setter method corrispondente alla colonna: "
						+columnName + "[tipo:"+f.getType().getName()+"]";
					log.error(methodName,msg,e);
					throw new Exception(msg,e);
				} catch (IllegalAccessException e) {
					String msg = "Accesso non valido per settare il valore corrispondente alla colonna: "+columnName;
					log.error(methodName,msg,e);
					throw new Exception(msg,e);
				} catch (InvocationTargetException e) {
					String msg = "Impossibile settare il valore corrispondente alla colonna: "+columnName;
					log.error(methodName,msg,e);
					throw new Exception(msg,e);
				}
				
			}
			
		}
		
	
		return obj;
	}

	private Object getValue(ResultSet rs, int columnIdx, int type)
			throws SQLException {
		
		Object val = null;
		
		if(type == Types.DATE ){
			val = rs.getDate(columnIdx);
		}else if(type == Types.TIME
				||type == Types.TIMESTAMP){
			val = rs.getTimestamp(columnIdx);
		} else if(type == Types.VARCHAR
				|| type == Types.CHAR
				|| type == Types.LONGVARCHAR){
			val = rs.getString(columnIdx);
		} else if(type == Types.INTEGER
				|| type == Types.SMALLINT
				|| type == Types.TINYINT){
			val = new Integer(rs.getInt(columnIdx));
		} else if(type == Types.DOUBLE 
				|| type == Types.FLOAT ) {
			val = new BigDecimal(rs.getDouble(columnIdx));
			
		} else if( type == Types.DECIMAL  
				|| type == Types.NUMERIC  ){
			val = rs.getBigDecimal(columnIdx);
			
		} else {
			throw new IllegalArgumentException("Tipo colonna non supportato: "+type);
		}
		return val;
	}

}
