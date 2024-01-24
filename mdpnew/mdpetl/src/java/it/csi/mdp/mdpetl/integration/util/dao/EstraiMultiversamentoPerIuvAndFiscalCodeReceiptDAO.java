/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.util.List;

import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO extends BaseDAO<List<SingoloPagamentoMultiVersamentoDTO>> {
	
	public EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO(String iuv, String idDominio) {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv,idDominio));
		setResultSetExtractor(new MultiversamentoExtractor());
		System.out.println("iuv in EstraiMultiversamentoPerIuvAndFiscalCodeReceiptDAO"+iuv);

	}

	@Override
	public String componiQuery() {
		return "select e.application_id,"
				+ " v.causaleversamento, v.commissionecaricopa,v.credenzialipagatore, v.datispecificiriscossione,importosingoloversamento, "
				+ " iuv, 3 as modellopagamento,multi_id, v.posizione, e.transaction_id, v.annoaccertamento, v.numeroaccertamento "
				+ " from elemento_multiversamento e, dati_singolo_versamento v,  mdp_receipt mg, mdp_singolo_transfer mst "
				+ " where e.iuv = ? "
				+ " and e.id = v.multi_id "
				+ " and mg.creditor_referenceid = e.iuv "
				+ " and mst.id_receipt = mg.id "
				+ "and mst.fiscal_codepa = ? "
//				+ "and mst.transfer_category = v.datispecificiriscossione " Da eliminare in quanto non restituisce nessun risutato. Chiedere a Osorio.
				+ " order by v.posizione";
	
	}
	
	/*class MultiversamentoExtractor implements ResultSetExtractor<List<SingoloPagamentoMultiVersamentoDTO>> {
		
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
	}*/
}
