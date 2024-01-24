/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


public class ModificaReceiptInvioRiuscitoDAO extends BaseDAO {

	public ModificaReceiptInvioRiuscitoDAO ( Integer id ) {
		setStatementMapper(new GenericObjectArrayStatementMapper (id));
	}

	 @Override
	    public String componiQuery() {
	        StringBuffer strSql = new StringBuffer();
	        strSql.append(" update mdp_receipt set "
	            + "data_invio_fruitore = now(), "
	            + "stato_invio_fruitore = 'OK', "
	            + "data_ora_update = now(), "
	            + "data_ora_retry = now(), "
	            + "msg_invio_fallito = '' "
	            
	            + "WHERE id = ?");
	        return strSql.toString();
	    }
}
