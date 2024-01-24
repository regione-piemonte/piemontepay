/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


public class EstraiRPTPerIuvDAO extends BaseDAO<DatiRichiesta> {

	public  EstraiRPTPerIuvDAO(String iuv ) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new RPTExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from rpt where iuv = ? order by id desc limit 1";
	}
	
	class RPTExtractor implements ResultSetExtractor<DatiRichiesta> {
		
		public DatiRichiesta extractData(ResultSet rs) throws SQLException {
			
			DatiRichiesta rpt = null;
			
			if(rs.next()){
				rpt = new DatiRichiesta();
				rpt.setId(rs.getInt("id"));
				rpt.setApplicationId(rs.getString("application_id"));
//				rpt.setTransactionId(rs.getString("transaction_id"));
//				rpt.setIdCanale(rs.getString("id_canale"));
//				rpt.setIdIntermPsp(rs.getString("id_interm_psp"));
//				rpt.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
//				rpt.setIdPsp(rs.getString("id_psp"));
//				rpt.setIdentificativoDominio(rs.getString("identificativo_dominio"));
//				rpt.setIdentificativoIntermediarioPa(rs.getString("identificativo_intermediario_pa"));
//				rpt.setIdentificativoStazioneIntermediarioPa(rs.getString("identificativo_stazione_intermediario_pa"));
				rpt.setIuv(rs.getString("iuv"));
//				rpt.setIdStatiRpt(rs.getInt("id_stati_rpt"));
//				rpt.setCodiceContestoPagamento(rs.getString("codice_contesto_pagamento"));
//				rpt.setDaInviare(rs.getBoolean("da_inviare"));
//				rpt.setDataInvio(rs.getTimestamp("data_invio"));
			}
			return rpt;
		}
	}
}
