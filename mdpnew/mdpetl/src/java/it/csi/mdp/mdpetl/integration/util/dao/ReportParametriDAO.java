/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.dto.ReportTransazioni;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManager;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManagerFactory;
import it.csi.mdp.mdpetl.util.LogUtil;

public class ReportParametriDAO {
	static private LogUtil log = new LogUtil(ReportTransazioni.class);
	
	private static String query = "select value from config where key = ?";
	
	private static final String DESTINATARI_MAIL_TRANSAZIONI = "report.transazioni.destinatari.mail";
	private static final String DESTINATARI_MAIL_NODOSPC = "report.nodospc.destinatari.mail";
	private static final String EXCLUDE_APPLICATIONID = "report.exclude.applicationid.list";
	
	private static String[] destinatariMailTransazioni;
	private static String[] destinatariMailNodospc;
	private static String[] excludeApplicationid;
	
	
	public ReportParametriDAO() throws Exception {
		String methodName = "costruttore";
		log.startMethod(methodName);
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = ConnectionManagerFactory.getInstance().getConnection();
			
			statement = conn.prepareStatement(query);
			statement.setString(1, DESTINATARI_MAIL_TRANSAZIONI);
			resultSet = statement.executeQuery();
			resultSet.next();
			destinatariMailTransazioni = parse(resultSet.getString("value"));
			statement.close();
			
			statement = conn.prepareStatement(query);
			statement.setString(1, DESTINATARI_MAIL_NODOSPC);
			resultSet = statement.executeQuery();
			resultSet.next();
			destinatariMailNodospc = parse(resultSet.getString("value"));
			statement.close();
			
			statement = conn.prepareStatement(query);
			statement.setString(1, EXCLUDE_APPLICATIONID);
			resultSet = statement.executeQuery();
			resultSet.next();
			excludeApplicationid = parse(resultSet.getString("value"));
			
			log.info(methodName, "destinatariMailTransazioni : " + StringUtils.join(destinatariMailTransazioni,','));
			log.info(methodName, "destinatariMailNodospc : " + StringUtils.join(destinatariMailNodospc,','));
			log.info(methodName, "excludeApplicationid : " + StringUtils.join(excludeApplicationid,','));
		} catch (Exception e) {
			log.error(methodName, ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			ConnectionManager.closeResultSet(resultSet);
			ConnectionManager.closeStatement(statement);
			ConnectionManager.closeConnection(conn);
			log.stopMethod(methodName);
		}
	}
	
	private String[] parse(String param) {
		if (StringUtils.isBlank(param)) {
			return null;
		}
		
		String[] lista;
		if (StringUtils.contains(param, ';')) {
			lista = StringUtils.split(param, ';');
		} else if (StringUtils.contains(param, ',')) {
			lista = StringUtils.split(param, ',');
		} else if (StringUtils.contains(param, ' ')) {
			lista = StringUtils.split(param, ' ');
		} else {
			lista = new String[1];
			lista[0] = param;
		}
		for (int i = lista.length-1; i >= 0; i--) {
			lista[i] =  StringUtils.trim(lista[i]);
		}
		return lista;
	}

	public String[] getDestinatariMailTransazioni() {
		return destinatariMailTransazioni;
	}
	
	public String[] getDestinatariMailNodospc() {
		return destinatariMailNodospc;
	}

	public String getExcludeApplicationid() {
		if (excludeApplicationid == null || excludeApplicationid.length == 0) {
			return "''";
		}
		
		String[] excludeApplicationid2 = new String[excludeApplicationid.length];
		for(int i = excludeApplicationid.length-1; i >= 0 ; i--) {
			excludeApplicationid2[i] = "'" + excludeApplicationid[i] + "'"; 
		}
		return StringUtils.join(excludeApplicationid2, ',');
	}
}
