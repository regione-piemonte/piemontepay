/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.dto.ReportTransazioni;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManager;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManagerFactory;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.Timekeeper;

public class ReportTransazioniDAO {
	static private LogUtil log = new LogUtil(ReportTransazioni.class);
	
	private String query = "SELECT a.applicationname, g.gateway_description, "
			+ " CASE WHEN t.cod_stato=4 THEN '1' WHEN t.cod_stato=5 THEN '2' WHEN t.cod_stato=6 THEN '3' ELSE '4' END as cod_stato,"
			+ " count(*) as conteggio"
			+ " FROM transazione t"
			+ " join gateway g on g.gateway_id = t.gateway_id"
			+ " INNER JOIN application a on a.id = t.application_id"
			+ " where t.init_date >= ?"
			+ " and t.init_date <= ?"
			+ " and t.cod_stato in (4,5,6,9)"
			+ " and t.application_id not in (<excludeApplicationId>)"
			+ " group by a.applicationname, g.gateway_description, t.cod_stato" 
			+ " order by a.applicationname, g.gateway_description, t.cod_stato";

	public List<ReportTransazioni> estraidati(final java.util.Date dataRifStart, final java.util.Date dataRifEnd, final String excludeApplicationId) throws Exception {
		String methodName = "estraidati";
		
		log.startMethod(methodName);
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ReportTransazioni> dataList = new ArrayList<ReportTransazioni>();
		
		try {
			
			log.debug(methodName, "Ottengo la connessione");
			Timekeeper tk = new Timekeeper("Connect db");
			conn = ConnectionManagerFactory.getInstance().getConnection();
			log.info(methodName, tk.stopAndPrintTime());
				
			log.debug(methodName, "Connessione ottenuta, creo lo statement applicazioni");
			query = StringUtils.replace(query, "<excludeApplicationId>", excludeApplicationId);
			statement = conn.prepareStatement(query);
			statement.setDate(1, new Date(dataRifStart.getTime()));
			statement.setDate(2, new Date(dataRifEnd.getTime()));
			log.debug(methodName, statement.toString());
			log.info(methodName, "Statement ottenuto, eseguo la query applicazioni");
			tk.renameAndRestart("eseguzione query");
			resultSet = statement.executeQuery();
			log.info(methodName, tk.stopAndPrintTime());
				
			while (resultSet.next()) {
				ReportTransazioni data = new ReportTransazioni();
				data.setApplicationname(resultSet.getString(ReportTransazioni.APPLICATIONNAME));
				data.setGatewayDescription(resultSet.getString(ReportTransazioni.GATEWAY_DESCRIPTION));
				data.setCodStato(resultSet.getInt(ReportTransazioni.COD_STATO));
				data.setConteggio(resultSet.getLong(ReportTransazioni.CONTEGGIO));
				dataList.add(data);
			}
			
			return dataList;
		
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
}
