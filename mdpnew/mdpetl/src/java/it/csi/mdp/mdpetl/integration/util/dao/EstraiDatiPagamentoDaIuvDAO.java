/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.DatiPagamentoDaIuv;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class EstraiDatiPagamentoDaIuvDAO extends BaseDAO<DatiPagamentoDaIuv> {
	 LogUtil log = new LogUtil(EstraiDatiPagamentoDaIuvDAO.class);
	
	public  EstraiDatiPagamentoDaIuvDAO(String iuv) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new DatiPagamentoDaIuvExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from transazione_iuv ti, transazione t, rpt r where ti.transaction_id = t.transaction_id and ti.iuv = r.iuv and ti.iuv = ?";
	}
}

class DatiPagamentoDaIuvExtractor implements ResultSetExtractor<DatiPagamentoDaIuv> {
	
	public DatiPagamentoDaIuv extractData(ResultSet rs) throws SQLException {
		DatiPagamentoDaIuv risultato = new DatiPagamentoDaIuv();
		if(rs.next()){
			risultato.setApplicationId(rs.getString("t.applicationid"));
			risultato.setDescrizionePSP(rs.getString("i.ragionesociale"));
			risultato.setTransactionId(rs.getString("t.transaction_id"));
		}
		return risultato;
	}
	
}

