/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.util.List;

import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO extends BaseDAO<List<SingoloPagamentoMultiVersamentoDTO>> {
	
	public EstraiMultiversamentoPerIuvAndFiscalCodeGetPaymentDAO(String iuv, String idDominio) {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv,idDominio));
		setResultSetExtractor(new MultiversamentoExtractor());
		
	}

    @Override
    public String componiQuery () {
        return 
            "SELECT e.application_id, " +
            "                   v.causaleversamento, " +
            "                   v.commissionecaricopa, " +
            "                   v.credenzialipagatore, " +
            "                   v.datispecificiriscossione, " +
            "                   importosingoloversamento, " +
            "                   iuv, " +
            "                   modellopagamento, " +
            "                   multi_id, " +
            "                   v.posizione, " +
            "                   e.transaction_id, " +
            "                   v.annoaccertamento, " +
            "                   v.numeroaccertamento " +
            "FROM elemento_multiversamento e " +
            "  JOIN dati_singolo_versamento v ON (e.id = v.multi_id) " +
            "  JOIN mdp_getpayment mg ON (mg.creditor_referenceid = e.iuv) " +
            "  JOIN mdp_singolo_transfer mst ON (mst.id_getpayment = mg.id_getpayment) " +
            "  JOIN r_application_enti rae ON (v.applicationid = rae.application_id ) " +
            "  JOIN enti ent ON (rae.ente_id = ent.ente_id) " +
            "WHERE e.iuv = ? " +
            "AND   mst.fiscal_codepa = ? " +
            "AND   mst.id_receipt IS NULL " +
            "AND   mg.cod_stato_getpayment = '3' " +
            "AND   e.id = mg.id_elemento_multiversamento " +
            "AND   mst.fiscal_codepa = ent.partita_iva " +
            "ORDER BY v.posizione;";

    }
	
	/*
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
	}*/
}
