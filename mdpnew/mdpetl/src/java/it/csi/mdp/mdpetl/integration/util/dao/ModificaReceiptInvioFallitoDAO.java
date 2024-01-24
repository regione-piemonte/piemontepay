/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class ModificaReceiptInvioFallitoDAO extends BaseDAO{

	public  ModificaReceiptInvioFallitoDAO(Integer id,String  message) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(message, id));
		
	}
	
	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update mdp_receipt "
		    + "set data_ora_invio_fallito=now(), "
		    + "data_ora_update = now(), "
		    + "data_ora_retry = now(), "
            + "msg_invio_fallito = ?,"
            + "stato_invio_fruitore ='KO' "
		    + "WHERE id = ?");
		return strSql.toString();
	}
}
