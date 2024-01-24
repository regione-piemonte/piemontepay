/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.SingoloPagamentoMultiVersamentoDTO;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

public class EstraiMultiversamentoDAO extends BaseDAO<List<SingoloPagamentoMultiVersamentoDTO>> {
	 LogUtil log = new LogUtil(EstraiMultiversamentoDAO.class);
	
	public  EstraiMultiversamentoDAO(String iuv) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new MultiversamentoExtractor());
	}

	@Override
	public String componiQuery() {
	//AO: la posizione c'e' su entrambe le tabelle (sulla multiversamento e' valorizzata con 0 e genera errore
	//return "select * from elemento_multiversamento e, dati_singolo_versamento v where e.id = v.multi_id and e.iuv = ? order by v.posizione";
	return "select application_id,causaleversamento,commissionecaricopa,credenzialipagatore, datispecificiriscossione,importosingoloversamento, "+
		   "iuv,modellopagamento,multi_id, v.posizione, transaction_id, annoaccertamento, numeroaccertamento "+
		   "from elemento_multiversamento e, dati_singolo_versamento v "+ 
		   "where e.id = v.multi_id and e.iuv = ? order by v.posizione";
	}

}

class MultiversamentoExtractor implements ResultSetExtractor<List<SingoloPagamentoMultiVersamentoDTO>> {
	
	public List<SingoloPagamentoMultiVersamentoDTO> extractData(ResultSet rs) throws SQLException {
		List<SingoloPagamentoMultiVersamentoDTO> elenco = new ArrayList<SingoloPagamentoMultiVersamentoDTO>();
	
		while(rs.next()){
			SingoloPagamentoMultiVersamentoDTO elem = null;
			elem = new SingoloPagamentoMultiVersamentoDTO();
			elem.setApplicationId(rs.getString("application_id"));
			elem.setCausale(rs.getString("causaleversamento"));
			elem.setCommissione(rs.getBigDecimal("commissionecaricopa"));
			elem.setCredenzialiPagatore(rs.getString("credenzialipagatore"));
			elem.setDatiSpecificiRiscossione(rs.getString("datispecificiriscossione"));
			elem.setImporto(rs.getBigDecimal("importosingoloversamento"));
			elem.setIuv(rs.getString("iuv"));
			elem.setModelloPagamento(rs.getString("modellopagamento"));
			elem.setMultiId(rs.getInt("multi_id"));
			elem.setPosizione(rs.getInt("posizione"));
			elem.setTransactionId(rs.getString("transaction_id"));
			//LF 04/01/2019 - Recupero anno e numero accertamento
			elem.setAnnoAccertamento(rs.getInt("annoaccertamento"));
			elem.setNumeroAccertamento(rs.getInt("numeroaccertamento"));
			elenco.add(elem);
		}
		
		return elenco;
	}
	
}

