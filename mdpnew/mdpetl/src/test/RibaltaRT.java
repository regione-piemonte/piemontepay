/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class RibaltaRT {

	private static String DRIVER = "org.postgresql.Driver";
	private static String TEST_URL = "jdbc:postgresql://tst-domdb35.csi.it:5432/PGTST01";
	private static String USER = "mdpnew_rw";
	private static String PASSWORD = "mypass";
	private static String COLL_URL = "jdbc:postgresql://pgcol01.csi.it:5432/PGCOL01";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Connection connTest = null;
		Connection connColl = null;
		PreparedStatement stmtTest = null;
		ResultSet rsTest = null;
		PreparedStatement stmtColl = null;

		try {

			Class.forName(DRIVER);
			connTest = DriverManager.getConnection(TEST_URL, USER, PASSWORD);
			connColl = DriverManager.getConnection(COLL_URL, USER, PASSWORD);

			stmtTest = connTest
					.prepareStatement("select * from rt where iuv = ?");
			stmtTest.setString(1, args[0]);// IUV SU TEST DA RIBALTARE
			rsTest = stmtTest.executeQuery();
			if (rsTest.next()) {
				
				String applicationid = rsTest.getString("application_id");
				String transactionid = rsTest.getString("transaction_id");
				Timestamp datamsgricevuta = rsTest.getTimestamp("data_msg_ricevuta");
				String idmsgricevuta = rsTest.getString("id_msg_ricevuta");
				String tipofirma = rsTest.getString("tipo_firma");
				String iuv = rsTest.getString("iuv");
				Integer idesitopagamento = rsTest.getInt("id_esito_pagamento");
				String idmsgrichiesta = rsTest.getString("id_msg_richiesta");
				byte[] b = rsTest.getBytes("rt_data");
				
				stmtColl = connColl.prepareStatement("INSERT INTO rt(application_id, transaction_id, insert_date, last_update, data_msg_ricevuta, id_msg_ricevuta, tipo_firma, iuv, id_esito_pagamento, id_msg_richiesta, rt_data) VALUES (?, ?, now(), now(), ?, ?, ?, ?, ?, ?, ?);");
				
				stmtColl.setString(1, applicationid);
				stmtColl.setString(2, transactionid);
				stmtColl.setTimestamp(3, datamsgricevuta);
				stmtColl.setString(4, idmsgricevuta);
				stmtColl.setString(5, tipofirma);
				stmtColl.setString(6, iuv);
				stmtColl.setInt(7, idesitopagamento);
				stmtColl.setString(8, idmsgrichiesta);
				stmtColl.setBytes(9, b);
				
				int resiult = stmtColl.executeUpdate();
				
				connColl.commit();

			}
		} finally {
			connTest.close();
			connColl.close();
		}

	}

}
