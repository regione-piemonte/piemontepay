/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.DatiRichiesta;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class EstraiRichiestePagamentoPerIuvDAO extends BaseDAO<DatiRichiesta> {
	 LogUtil log = new LogUtil(EstraiRichiestePagamentoPerIuvDAO.class);
	
	public  EstraiRichiestePagamentoPerIuvDAO(String iuv, String identificativoDominio) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv, identificativoDominio));
		setResultSetExtractor(new DatiRichiestaExtractor());
	}

	@Override
	public String componiQuery() {
		//#AC20230830 Inserito join con dataesitosingolopagamento di flusso_singolo_pagamento per correggere estrazione ed impostare un controllo piu stringente
		return "select rpt.*, true as intermediato, false as secondario "
        		+ "from rpt "
        		+ "join flusso_singolo_pagamento on rpt.iuv = flusso_singolo_pagamento.iuv "
        		+ "where rpt.iuv = ? and identificativo_dominio = ? "
        		+ "and trunc(rpt.insert_date) = trunc(flusso_singolo_pagamento.dataesitosingolopagamento) "
        		+ "order by id_stati_rpt desc, insert_date desc";
	}
}

class DatiRichiestaExtractor implements ResultSetExtractor<DatiRichiesta> {
	
	public DatiRichiesta extractData(ResultSet rs) throws SQLException {
		
		DatiRichiesta risultato = null;
		
		if(rs.next()){
			risultato = new DatiRichiesta();
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
	        risultato.setIntermediato ( rs.getBoolean("intermediato") );
	        risultato.setSecondario ( rs.getBoolean("secondario"));
		}
		return risultato;
	}
}
