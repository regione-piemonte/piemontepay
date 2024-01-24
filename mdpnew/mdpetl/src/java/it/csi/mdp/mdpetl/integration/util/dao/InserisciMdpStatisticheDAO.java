/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.lang.exception.ExceptionUtils;

import it.csi.mdp.mdpetl.dto.MdpStatistiche;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManager;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManagerFactory;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.Timekeeper;

public class InserisciMdpStatisticheDAO {
	static private LogUtil log = new LogUtil(InserisciMdpStatisticheDAO.class);
	
	private static String query = "INSERT INTO mdp_statistiche (application_id, nome_report, periodo_report, csv) VALUES (?,?,?,?)";

	public static void salva(final String applicationId, final String nomeReport, final String periodoReport, final String csv) throws Exception {
		MdpStatistiche mdpStatistiche = new MdpStatistiche();
		mdpStatistiche.setApplicationId(applicationId);
		mdpStatistiche.setNomeReport(nomeReport);
		mdpStatistiche.setPeriodoReport(periodoReport);
		mdpStatistiche.setCsv(csv);
		
		InserisciMdpStatisticheDAO.salva(mdpStatistiche);
	}
	
	public static void salva(final MdpStatistiche dati) throws Exception {
		String methodName = "salva";
		
		log.startMethod(methodName);
		Timekeeper tk = new Timekeeper("salva db");
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			log.debug(methodName, "Ottengo la connessione");
			conn = ConnectionManagerFactory.getInstance().getConnection();
			statement = conn.prepareStatement(query);
			statement.setString(1, dati.getApplicationId());
			statement.setString(2, dati.getNomeReport());
			statement.setString(3, dati.getPeriodoReport());
			statement.setString(4, dati.getCsv());
			log.debug(methodName, statement.toString());
			statement.executeUpdate();
		} catch (Exception e) {
			log.error(methodName, ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			ConnectionManager.closeStatement(statement);
			ConnectionManager.closeConnection(conn);
			log.info(methodName, tk.stopAndPrintTime());
			log.stopMethod(methodName);
		}
	 }
}
