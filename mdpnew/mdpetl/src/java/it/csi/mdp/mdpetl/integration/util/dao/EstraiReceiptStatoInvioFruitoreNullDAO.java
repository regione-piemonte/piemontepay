/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.Receipt;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.UtilDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class EstraiReceiptStatoInvioFruitoreNullDAO extends BaseDAO<List<Receipt>> {

	public EstraiReceiptStatoInvioFruitoreNullDAO () {
		Calendar cal = Calendar.getInstance ();
		cal.add ( Calendar.DATE, -1 );
		setStatementMapper ( new GenericObjectArrayStatementMapper ( UtilDate.formatCalendar ( cal, "yyyy-MM-dd" ) ) );
		setResultSetExtractor ( new ReceiptEsitoNullExtractor () );
	}

	@Override public String componiQuery () {
		return "TODO";
	}
}


class ReceiptEsitoNullExtractor implements ResultSetExtractor<List<Receipt>> {

	@Override public List<Receipt> extractData ( ResultSet rs ) throws SQLException, Exception {
		List<Receipt> elencoReceipt = new ArrayList<Receipt> ();
		while ( rs.next () ) {
			Receipt receipt = new Receipt ();
			receipt.setApplicationId ( rs.getString ( "application_id" ) );
			receipt.setId ( rs.getInt ( "id" ) );
			receipt.setIuv ( rs.getString ( "iuv" ) );
			receipt.setTransactionId ( rs.getString ( "transaction_id" ) );
			elencoReceipt.add ( receipt );
		}
		return elencoReceipt;
	}
}
