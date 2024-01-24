/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import it.csi.mdp.mdpetl.dto.DatiRichiestaGetPayment;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

public class EstraiGetPaymentPerIuvDominioDAO extends BaseDAO<DatiRichiestaGetPayment> {
	 LogUtil log = new LogUtil(EstraiGetPaymentPerIuvDominioDAO.class);
	 
	public  EstraiGetPaymentPerIuvDominioDAO(String iuv, String identificativoDominio) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(identificativoDominio, iuv ));
		setResultSetExtractor(new DatiRichiestaGetPaymentExtractor());
		System.out.println("id_dominio in EstraiGetPaymentPerIuvDominioDAO: "+identificativoDominio+ "- iuv:"+iuv);
	}

	@Override
	public String componiQuery() {
		return "select id_getpayment as id,mg.application_id, transaction_id, data_ora_insert as insert_date, "
				+ "data_ora_update as last_update, null as auth_soggetto, "
				+ "data_ora_invio as data_msg_richiesta, null as id_canale, null as id_interm_psp, "
				+ " transaction_id as id_msg_richiesta, null as id_psp,"
				+ "e.partita_iva as identificativo_dominio, null as identificativo_intermediario_pa, "
				+ "null as identificativo_stazione_intermediario_pa, xml_getpayment as rpt_xml, creditor_referenceid as iuv, "
				+ "cod_stato_getpayment as id_stati_rpt, null as codice_contesto_pagamento, null as da_inviare, "
				+ "null as data_invio,"
				+ " (case when (e.partita_iva = ? ) then false else true end) as secondario, "
				+ " true as intermediato "
				+ " from mdp_getpayment mg, r_application_enti rae, enti e "
				+ "where creditor_referenceid = ? "
				+ "and rae.application_id = mg.application_id "
				+ "and mg.cod_stato_getpayment ='3' "
				+ "and rae.ente_id = e.ente_id "				
				+" order by data_ora_insert desc";
	}
}

class DatiRichiestaGetPaymentExtractor implements ResultSetExtractor<DatiRichiestaGetPayment> {
    
    public DatiRichiestaGetPayment extractData(ResultSet rs) throws SQLException {
        
        DatiRichiestaGetPayment risultato = null;
        
        if(rs.next()){
            risultato = new DatiRichiestaGetPayment();
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
            risultato.setRptXml(rs.getBytes ( "rpt_xml"));
            risultato.setIuv(rs.getString("iuv"));
            risultato.setIdStatiRpt(rs.getInt("id_stati_rpt"));
            risultato.setCodiceContestoPagamento(rs.getString("codice_contesto_pagamento"));
            risultato.setDaInviare(rs.getBoolean("da_inviare"));
            risultato.setDataInvio(rs.getTimestamp("data_invio"));
            risultato.setIntermediato ( rs.getBoolean("intermediato") );
            risultato.setSecondario ( rs.getBoolean("secondario"));
        }
        return risultato;
    }
}
