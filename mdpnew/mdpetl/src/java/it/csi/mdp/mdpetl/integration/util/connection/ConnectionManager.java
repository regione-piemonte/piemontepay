/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionManager {

	public abstract Connection getConnection() throws SQLException;	

	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs){
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conn);	
	}
	
	public static void closeConnection(Connection conn, Statement stmt){
		closeStatement(stmt);
		closeConnection(conn);	
	}
	
	public static void closeConnection(Connection conn){
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
			conn = null;
		}
	}
	
	public static void closeStatement(Statement stmt){
		if (stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
			stmt = null;
		}
	}
	
	public static void closeResultSet(ResultSet rs){
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
			rs = null;
		}
	}
}
