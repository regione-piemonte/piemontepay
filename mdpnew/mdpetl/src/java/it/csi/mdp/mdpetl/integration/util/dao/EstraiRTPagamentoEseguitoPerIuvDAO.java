/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class EstraiRTPagamentoEseguitoPerIuvDAO extends BaseDAO<RT> {
	
	public EstraiRTPagamentoEseguitoPerIuvDAO(String iuv) {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new RTExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from rt where rt.id_esito_pagamento = 1 and iuv = ? order by id desc";
	}

	class RTExtractor implements ResultSetExtractor<RT> {

		public RT extractData(ResultSet rs) throws SQLException, Exception {
			RT rt = null;
			if(rs.next()) {
				rt = new RT();
				
				rt.setApplicationId(rs.getString("application_id"));
				rt.setCommissioniApplicatePSP(rs.getDouble("commissioniapplicatepsp"));
				rt.setDataInvioFruitore(rs.getTimestamp("data_invio_fruitore"));
				rt.setDataMsgRicevuta(rs.getTimestamp("data_msg_ricevuta"));
				rt.setId(rs.getInt("id"));
				rt.setIdEsitoPagamento(rs.getInt("id_esito_pagamento"));
				rt.setIdMsgRicevuta(rs.getString("id_msg_ricevuta"));
				rt.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
				rt.setInsertDate(rs.getTimestamp("insert_date"));
				rt.setIuv(rs.getString("iuv"));
				rt.setLastUpdate(rs.getTimestamp("last_update"));
				rt.setRtData(rs.getBytes("rt_data"));
				rt.setSogenteInvioFruitore(rs.getString("sorgente_invio_fruitore"));
				rt.setStatoInvioFruitore(rs.getString("stato_invio_fruitore"));
				rt.setTransactionId(rs.getString("transaction_id"));
				rt.setSogenteInvioFruitore(rs.getString("sorgente_invio_fruitore"));
			}
			
			return rt;
		}
	}
}
