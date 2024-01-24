/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import it.csi.mdp.mdpetl.dto.RT;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.UtilDate;

public class EstraiRTStatoInvioFuitoreNullDAO extends BaseDAO<List<RT>>{
	
	 LogUtil log = new LogUtil(EstraiRTStatoInvioFuitoreNullDAO.class);

    public EstraiRTStatoInvioFuitoreNullDAO () throws SerialException, SQLException {
        Calendar cal = Calendar.getInstance ();
        cal.add ( Calendar.DATE, -1 );
        setStatementMapper ( new GenericObjectArrayStatementMapper ( UtilDate.formatCalendar ( cal, "yyyy-MM-dd" ) ) );
        setResultSetExtractor ( new RTEsitoNullExtractor () );
    }
	
    @Override
    public String componiQuery () {
        StringBuilder strSql = new StringBuilder ();
        strSql.append ( " select id, application_id, iuv, transaction_id " +
            "from rt " +
            "where stato_invio_fruitore is null and (date(data_msg_ricevuta) <= to_date(?, 'YYYY-MM-DD') or data_msg_ricevuta is null)" +
            "order by insert_date desc limit 500" );
        return strSql.toString ();
    }
	
	
}

class RTEsitoNullExtractor implements ResultSetExtractor<List<RT>> {
	
    public List<RT> extractData ( ResultSet rs ) throws SQLException {
        List<RT> elencoRt = new ArrayList<RT> ();
        while ( rs.next () ) {
            RT rt = new RT ();
            rt.setApplicationId ( rs.getString ( "application_id" ) );
            rt.setId ( rs.getInt ( "id" ) );
            rt.setIuv ( rs.getString ( "iuv" ) );
            rt.setTransactionId ( rs.getString ( "transaction_id" ) );
            elencoRt.add ( rt );
        }

        return elencoRt;
    }
}
