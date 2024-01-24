/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.mdp.mdpetl.dto.DatiRichiestaReceipt;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

public class EstraiReceiptPerIuvSconosciutoDAO extends BaseDAO<DatiRichiestaReceipt> {
	 LogUtil log = new LogUtil(EstraiReceiptPerIuvSconosciutoDAO.class);
	 
	public  EstraiReceiptPerIuvSconosciutoDAO(String iuv) {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new DatiRichiestaReceiptExtractor());
		System.out.println("iuv in EstraiReceiptPerIuvSconosciutoDAO"+iuv);

	}

	@Override
	public String componiQuery() {
		
		return "select id,rae.applicationid as application_id,transaction_id, data_ora_insert as insert_date, "
		+" data_ora_update as last_update, payment_method  as auth_soggetto, payment_datetime as data_msg_richiesta, psp_comany_name as id_canale, "		 
		+ " id_psp as id_interm_psp, receipt_id as id_msg_richiesta, id_psp,"
		+ "	id_pa as identificativo_dominio, id_broker_pa as identificativo_intermediario_pa, "
		+ "	id_station as identificativo_stazione_intermediario_pa, xml_receipt as rpt_xml, creditor_referenceid as iuv, "
		+ " 3 as id_stati_rpt, receipt_id as codice_contesto_pagamento, null as da_inviare, "
		+ " data_invio_fruitore data_invio,"
		+ " true as secondario, false as intermediato"
		+ " from mdp_receipt, r_application_ente_primario_secondario rae"
		+ " where creditor_referenceid = ? "
		+ " and id_pa = rae.codicefiscale_secondario" 
		+ " and iuv_sconosciuto = true "
		+ " limit 1";
	}
}

class DatiRichiestaReceiptExtractor implements ResultSetExtractor<DatiRichiestaReceipt> {
    
    public DatiRichiestaReceipt extractData(ResultSet rs) throws SQLException {
        
        DatiRichiestaReceipt risultato = null;
        
        if(rs.next()){
            risultato = new DatiRichiestaReceipt();
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
