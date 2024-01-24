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
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.dto.ReportNodoSpc;
import it.csi.mdp.mdpetl.dto.ReportTransazioni;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManager;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManagerFactory;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.Timekeeper;

public class ReportNodoSpcDAO {
	static private LogUtil log = new LogUtil(ReportTransazioni.class);
	
	private String query = 
			"SELECT r.id_psp, r.id_canale,"
			+ " i.ragionesociale, i.descrizioneservizio, i.tipoversamento, i.condizionieconomichemassime,"
			+ " count(*) as num_transazioni, sum(t.amount) as transato"
			+ " FROM (select idinformativapsp, ragionesociale, descrizioneservizio, tipoversamento, condizionieconomichemassime"
			+ "         from informativa_psp"
			+ "       union"
			+ "       select idinformativapsp, ragionesociale, descrizioneservizio, tipoversamento, condizionieconomichemassime"
			+ "         from informativa_psp_bck) i,"
			+ "      rpt r join transazione t on t.transaction_id = r.transaction_id"
			+ " where r.transaction_id in (SELECT transaction_id"
			+ "                              FROM rt where insert_date >= ?"
			+ "                               and insert_date <= ?"
			+ "                               and application_id = ?"
			+ "                               and id_esito_pagamento=1"
			+ "                           )"
			+ "   and i.idinformativapsp = (select max(i2.idinformativapsp)"
			+ "                               from (select idinformativapsp, identificativocanale, identificativopsp, tipoversamento, datainserimento"
			+ "                                       from informativa_psp"
			+ "                                     union"
			+ "                                     select idinformativapsp, identificativocanale, identificativopsp, tipoversamento, datainserimento"
			+ "                                       from informativa_psp_bck ipb"
			+ "                                      where ipb.datainserimento >= ? ) i2"
			+ "        						 where i2.identificativocanale = r.id_canale"
			+ "      				           and i2.identificativopsp = r.id_psp"
			+ "        				           and r.rpt_xml like '%<tipoVersamento>'||i2.tipoversamento||'</tipoVersamento>%'"
			+ "                                and date_trunc('day', i2.datainserimento) <= date_trunc('day', r.insert_date)"
			+ "                            )"
			+ " group by r.id_psp, r.id_canale, i.ragionesociale, i.descrizioneservizio, i.tipoversamento, i.condizionieconomichemassime"
			+ " order by num_transazioni desc";
	
	private String query2 = "select distinct id from application "
			+ " where id = COALESCE(?, id)"
			+ " and id not in (<excludeApplicationId>)";

	public List<String> trovaApplicationId(final String applicationId, final String excludeApplicationId) throws Exception {
		String methodName = "trovaApplicationId";
		
		log.startMethod(methodName);
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> dataList = new ArrayList<String>();
		
		try {
			conn = ConnectionManagerFactory.getInstance().getConnection();
			query2 = StringUtils.replace(query2, "<excludeApplicationId>", excludeApplicationId);
			statement = conn.prepareStatement(query2);
			statement.setString(1, applicationId);
			log.debug(methodName, statement.toString());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				dataList.add(resultSet.getString("id"));
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
	
	public List<ReportNodoSpc> estraidati(final String applicationId, final java.util.Date dataRifStart, final java.util.Date dataRifEnd) throws Exception {
		String methodName = "estraidati";
		
		log.startMethod(methodName);
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ReportNodoSpc> dataList = new ArrayList<ReportNodoSpc>();
		
		try {
			
			log.debug(methodName, "Ottengo la connessione");
			Timekeeper tk = new Timekeeper("Connect db");
			conn = ConnectionManagerFactory.getInstance().getConnection();
			log.info(methodName, tk.stopAndPrintTime());
				
			log.debug(methodName, "Connessione ottenuta, creo lo statement applicazioni");
			statement = conn.prepareStatement(query);
			statement.setDate(1, new Date(dataRifStart.getTime()));
			statement.setDate(2, new Date(dataRifEnd.getTime()));
			statement.setString(3, applicationId);
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(dataRifStart);
				cal.add(Calendar.MONTH, -1);
				statement.setDate(4, new Date(cal.getTimeInMillis()));
			}
			
			log.debug(methodName, statement.toString());
			log.info(methodName, "Statement ottenuto, eseguo la query applicazioni");
			tk.renameAndRestart("eseguzione query");
			resultSet = statement.executeQuery();
			log.info(methodName, tk.stopAndPrintTime());
			
			while (resultSet.next()) {
				ReportNodoSpc data = new ReportNodoSpc();
				data.setIdPsp(resultSet.getString(ReportNodoSpc.ID_PSP));
				data.setIdCanale(resultSet.getString(ReportNodoSpc.ID_CANALE));
				data.setRagioneSociale(resultSet.getString(ReportNodoSpc.RAGIONE_SOCIALE));
				data.setDescrizioneServizio(resultSet.getString(ReportNodoSpc.DESCRIZIONE_SERVIZIO));
				data.setTipoVersamento(resultSet.getString(ReportNodoSpc.TIPO_VERSAMENTO));
				data.setCondizioniEconomicheMassime(resultSet.getString(ReportNodoSpc.CONDIZIONI_ECONOMICHE_MASSIME));
				data.setNumTransazioni(Long.valueOf(resultSet.getLong(ReportNodoSpc.NUM_TRANSAZIONI)));
				data.setTransato(resultSet.getBigDecimal(ReportNodoSpc.TRANSATO));
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
