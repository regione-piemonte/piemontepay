/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class EstraiMultiversamentoPerIuvAndTransactionIdDAO extends BaseDAO<List<SingoloPagamentoMultiVersamentoDTO>> {
	
	public EstraiMultiversamentoPerIuvAndTransactionIdDAO(String iuv, String transactionId, String applicationId) {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv,transactionId, applicationId));
		setResultSetExtractor(new MultiversamentoExtractor());
		
	}

	@Override
	public String componiQuery() {
		return "select application_id,causaleversamento,commissionecaricopa,credenzialipagatore, datispecificiriscossione,importosingoloversamento, "+
				   "iuv,modellopagamento,multi_id, v.posizione, transaction_id, annoaccertamento, numeroaccertamento "+
				   "from elemento_multiversamento e, dati_singolo_versamento v "+ 
				   "where e.id = v.multi_id and e.iuv = ? and e.transaction_id = ? "
//				   + "and v.applicationid = ? "
				   + "and coalesce (v.applicationid ,e.application_id ) = ? "
				   + "order by v.posizione";
		
	
	}
	
	class MultiversamentoExtractor implements ResultSetExtractor<List<SingoloPagamentoMultiVersamentoDTO>> {
		
		public List<SingoloPagamentoMultiVersamentoDTO> extractData(ResultSet rs) throws SQLException {
			List<SingoloPagamentoMultiVersamentoDTO> spmvList = new ArrayList<SingoloPagamentoMultiVersamentoDTO>();
		
			while(rs.next()){
				SingoloPagamentoMultiVersamentoDTO spmv = new SingoloPagamentoMultiVersamentoDTO();
				spmv.setApplicationId(rs.getString("application_id"));
				spmv.setCausale(rs.getString("causaleversamento"));
				spmv.setCommissione(rs.getBigDecimal("commissionecaricopa"));
				spmv.setCredenzialiPagatore(rs.getString("credenzialipagatore"));
				spmv.setDatiSpecificiRiscossione(rs.getString("datispecificiriscossione"));
				spmv.setImporto(rs.getBigDecimal("importosingoloversamento"));
				spmv.setIuv(rs.getString("iuv"));
				spmv.setModelloPagamento(rs.getString("modellopagamento"));
				spmv.setMultiId(rs.getInt("multi_id"));
				spmv.setPosizione(rs.getInt("posizione"));
				spmv.setTransactionId(rs.getString("transaction_id"));
				spmv.setAnnoAccertamento(rs.getInt("annoaccertamento"));
				spmv.setNumeroAccertamento(rs.getInt("numeroaccertamento"));
				
				spmvList.add(spmv);
			}
			
			return spmvList;
		}
	}
}
