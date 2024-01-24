/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

public class EstraiRichiestePagamentoDaInviareDAO extends BaseDAO<List<DatiRichiesta>> {
	 LogUtil log = new LogUtil(EstraiRichiestePagamentoDaInviareDAO.class);
	
	public  EstraiRichiestePagamentoDaInviareDAO() throws SerialException, SQLException {
		setResultSetExtractor(new DatiRichiestaNonInviataExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from rpt where da_inviare = true order by application_id";
	}
}

class DatiRichiestaNonInviataExtractor implements ResultSetExtractor<List<DatiRichiesta>> {
	
	public List<DatiRichiesta> extractData(ResultSet rs) throws SQLException {
		
		List<DatiRichiesta> elenco = new ArrayList<DatiRichiesta>();
		
		while(rs.next()){
			
			DatiRichiesta risultato = new DatiRichiesta();
			risultato.setId(rs.getInt("id"));
			risultato.setApplicationId(rs.getString("application_id"));
			risultato.setTransactionId(rs.getString("transaction_id"));
			risultato.setInsertDate(rs.getTimestamp("insert_date"));
			risultato.setLastUpdate(rs.getTimestamp("last_update"));
			risultato.setAuthSoggetto(rs.getString("auth_soggetto"));
			risultato.setDataMsgRichiesta(rs.getTimestamp("data_msg_richiesta"));
			risultato.setIdCanale(rs.getString("id_canale"));
			risultato.setIdIntermPsp(rs.getString("id_interm_psp"));
			risultato.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
			risultato.setIdPsp(rs.getString("id_psp"));
			risultato.setIdentificativoDominio(rs.getString("identificativo_dominio"));
			risultato.setIdentificativoIntermediarioPa(rs.getString("identificativo_intermediario_pa"));
			risultato.setIdentificativoStazioneIntermediarioPa(rs.getString("identificativo_stazione_intermediario_pa"));
			risultato.setRptXml(rs.getString("rpt_xml"));
			risultato.setIuv(rs.getString("iuv"));
			risultato.setIdStatiRpt(rs.getInt("id_stati_rpt"));
			risultato.setCodiceContestoPagamento(rs.getString("codice_contesto_pagamento"));
			risultato.setDaInviare(rs.getBoolean("da_inviare"));
			risultato.setDataInvio(rs.getTimestamp("data_invio"));
			
			elenco.add(risultato);
		}
		return elenco;
	}
	
}

