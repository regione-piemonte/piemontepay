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


public class EstraiRPTPerIuvAndTransactionIdDAO extends BaseDAO<DatiRichiesta> {

	public  EstraiRPTPerIuvAndTransactionIdDAO(String iuv, String transactionId) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv,transactionId));
		setResultSetExtractor(new RPTExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from rpt where iuv = ? and transaction_id = ? order by id desc";
	}
	
	class RPTExtractor implements ResultSetExtractor<DatiRichiesta> {
		
		public DatiRichiesta extractData(ResultSet rs) throws SQLException {
			
			DatiRichiesta rpt = null;
			
			if(rs.next()){
				rpt = new DatiRichiesta();
				rpt.setId(rs.getInt("id"));
				rpt.setApplicationId(rs.getString("application_id"));
				rpt.setTransactionId(rs.getString("transaction_id"));
				rpt.setInsertDate(rs.getTimestamp("insert_date"));
				rpt.setLastUpdate(rs.getTimestamp("last_update"));
				rpt.setAuthSoggetto(rs.getString("auth_soggetto"));
				rpt.setDataMsgRichiesta(rs.getTimestamp("data_msg_richiesta"));
				rpt.setIdCanale(rs.getString("id_canale"));
				rpt.setIdIntermPsp(rs.getString("id_interm_psp"));
				rpt.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
				rpt.setIdPsp(rs.getString("id_psp"));
				rpt.setIdentificativoDominio(rs.getString("identificativo_dominio"));
				rpt.setIdentificativoIntermediarioPa(rs.getString("identificativo_intermediario_pa"));
				rpt.setIdentificativoStazioneIntermediarioPa(rs.getString("identificativo_stazione_intermediario_pa"));
				System.out.println(rs.getString("rpt_xml"));
				rpt.setRptXml(rs.getString("rpt_xml"));
				rpt.setIuv(rs.getString("iuv"));
				rpt.setIdStatiRpt(rs.getInt("id_stati_rpt"));
				rpt.setCodiceContestoPagamento(rs.getString("codice_contesto_pagamento"));
				rpt.setDaInviare(rs.getBoolean("da_inviare"));
				rpt.setDataInvio(rs.getTimestamp("data_invio"));
			}
			return rpt;
		}
	}
}
