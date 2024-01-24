/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;


import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManager;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManagerFactory;
import it.csi.mdp.mdpetl.integration.util.dao.re.EmptyStatementMapper;
import it.csi.mdp.mdpetl.integration.util.dao.re.ObjectResultSetExtractor;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import javax.naming.NamingException;
import java.sql.*;

/**
 * Alternativa a BasicDAO in greado di fornire supporto alle bind variable di Oracle
 * e una pi semplice usabilità orientata alla mapping immediato di un oggetto a partire dal risultato di 
 * una query. Rispetto al BasicDAO annulla il concetto di reader preservandone però l'efficienza della
 * riusabilità per il remapping degli oggetti.
 * @author 71551
 *
 */


public abstract class BaseDAO<T> implements BaseDAOI<T> {

	protected LogUtil log = new LogUtil(this.getClass());
	protected ResultSetExtractor<T> resultSetExtractor = null;
	protected StatementMapper statementMapper = new EmptyStatementMapper();
	
	
	
	public abstract String componiQuery();

	public void setResultSetExtractor(ResultSetExtractor<T> resultSetExtractor) {
		this.resultSetExtractor = resultSetExtractor;
	}
	
	public void setResultSetExtractor(T o) {
		this.setResultSetExtractor(new ObjectResultSetExtractor<T>(o));
	}
	
	
	
	
	public void setStatementMapper(StatementMapper statementMapper) {
		this.statementMapper = statementMapper;
	}
	
	public void setStatementParams(Object... params) {
		this.statementMapper = new GenericObjectArrayStatementMapper(params);
	}
		
	
	public T executeQuery() throws Exception {
		final String METHOD_NAME = "executeQuery";
//		log.info(METHOD_NAME, "dao: "+super.getClass().getSimpleName());
		
		T returnObject;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManagerFactory.getInstance().getConnection();
			String sql = componiQuery();
			stmt = conn.prepareStatement(sql);
//			log.debug(METHOD_NAME, "map statement params...");
			statementMapper.mapStatementParameters(stmt);	
			
			
			log.info(METHOD_NAME, "start execute query: "+sql);
			rs = stmt.executeQuery();
//			log.debug(METHOD_NAME, "extract data...");
			returnObject = resultSetExtractor.extractData(rs);
//			log.debug(METHOD_NAME, "returning object.");
			return returnObject;

		}
		catch (SQLException e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "SQLException", e);
			throw e;
		} 
		catch (NamingException e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "NamingException", e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "Exception", e);
			throw e;
		}
		finally {
			ConnectionManager.closeConnection(conn,stmt,rs);
		}

		
		
		
	}
	
	public int executeUpdate() throws Exception {
		return executeUpdate(false);
	}
	
	//public int executeUpdate() throws SQLException, NamingException, Exception {
	public int executeUpdate(boolean generateKey) throws Exception {
		String METHOD_NAME = "executeUpdate";
		log.debug(METHOD_NAME, "dao: "+super.getClass().getSimpleName());
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManagerFactory.getInstance().getConnection();
			String sql = componiQuery();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			log.debug(METHOD_NAME, "map statement params...");
			log.info(METHOD_NAME, "parametri "+ statementMapper.mapStatementParameters(stmt));

			
			log.info(METHOD_NAME, "start execute query: "+sql);
			
			int result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (generateKey && rs.next() ) {
			    // Retrieve the auto generated key(s).
			    result = rs.getInt(1);
			}
			log.debug(METHOD_NAME, "returning result: "+result);
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "SQLException " , e);
			return -1;
		} catch (NamingException e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "NamingException", e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "Exception", e);
			throw e;
		} finally {
			ConnectionManager.closeConnection(conn, stmt);
		}
	}
	
	
	

}
