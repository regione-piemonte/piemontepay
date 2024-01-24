/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;

import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

public class EstraiRTDaInviareDAO extends BaseDAO<List<RT>> {
	
	private static final String FALLBACK_GIORNI_SOGLIA = "10";
	
	private static final String FALLBACK_DATA_INIZIO_INVIO = "2021-01-01";
	
    private String giorniSoglia = "";

	 LogUtil log = new LogUtil(EstraiRTDaInviareDAO.class);

    //:TODO PASSARE PARAMETRI RECUPERATI DA DB.
	public  EstraiRTDaInviareDAO(String dataInvio, String giorniSoglia) throws SerialException, SQLException {
		dataInvio = StringUtils.isBlank(dataInvio) ? FALLBACK_DATA_INIZIO_INVIO : dataInvio;
		this.giorniSoglia = StringUtils.isBlank(giorniSoglia) ? FALLBACK_GIORNI_SOGLIA : giorniSoglia;
        setStatementMapper ( new GenericObjectArrayStatementMapper ( dataInvio, dataInvio ) );
		setResultSetExtractor(new RTExtractor());
	}

	@Override
	public String componiQuery() {
		String query = "";
		
		//QUERY ORIGINALE
		query = "select rt.* from rt rt, r_application_enti re, enti e " + 
				"where re.application_id = rt.application_id " + 
				"and re.ente_id = e.ente_id " + 
				"and rt.id_esito_pagamento = 1 " + 
				"and e.flag_invio_flusso_esteso = true " + 
				"and rt.insert_date > to_date(?, 'YYYY-MM-DD') " + 
				"and rt.insert_date < now() - INTERVAL '" + giorniSoglia +" DAYS' " + 
				"and NOT EXISTS (SELECT 1 from flusso_singolo_pagamento fsp where rt.iuv = fsp.iuv AND fsp.dataesitosingolopagamento > to_date(?, 'YYYY-MM-DD')) " +
				"and rt.stato_invio not in " + 
				"(2,3) " +
				"order by rt.data_msg_ricevuta desc, rt.iuv";
		
        //QUERY PROPOSTA DA Artan
//      select rt.* from rt , r_application_enti re, enti e     where re.application_id = rt.application_id
//        and re.ente_id = e.ente_id
//        and rt.id_esito_pagamento = 1
//        and e.flag_invio_flusso_esteso = true
//        and rt.insert_date >  timestamp '2020-11-10 00:00:00'         
//        and rt.insert_date < now() - INTERVAL '1 DAY'
//        and NOT EXISTS (SELECT 1 from flusso_singolo_pagamento fsp where rt.iuv = fsp.iuv AND fsp.dataesitosingolopagamento >=timestamp '2020-11-10 00:00:00')
//        and rt.stato_invio not in   (2,3)
        
		//QUERY SUGGERITA DA CSI (CSI_PAG-502)
//		query = "select rt.* from rt rt, r_application_enti re, enti e " + 
//				"where re.application_id = rt.application_id " + 
//				"and re.ente_id = e.ente_id " + 
//				"and rt.id_esito_pagamento = 1 " + 
//				"and e.flag_invio_flusso_esteso = true " + 
//				"and rt.data_msg_ricevuta > to_date(?, 'YYYY-MM-DD') " +
//				"and rt.insert_date < now() - INTERVAL '" + giorniSoglia +" DAYS' " + 
//				"and rt.iuv not in " + 
//				"(select iuv from flusso_singolo_pagamento where dataesitosingolopagamento >=  to_date(?, 'YYYY-MM-DD')) " + 
//				"and rt.stato_invio not in " + 
//				"(2,3)" +
//				"order by rt.data_msg_ricevuta desc, rt.iuv"
//				"";
		
		//QUERY MODIFICATA DA ENG
//		query = "select rt.* from rt rt left join flusso_singolo_pagamento fsp on rt.iuv=fsp.iuv and fsp.dataesitosingolopagamento >= to_date(?,'YYYY-MM-DD'), r_application_enti re, enti e " +
//				"where re.application_id = rt.application_id " +
//				"and re.ente_id = e.ente_id " +
//				"and rt.id_esito_pagamento = 1 " +
//				"and e.flag_invio_flusso_esteso = true " +
//				"and rt.data_msg_ricevuta > to_date(?,'YYYY-MM-DD') " +
//				"and rt.insert_date < now() - interval '" + giorniSoglia + " DAYS' " +
//				"and rt.stato_invio not in (2,3) " +
//				"and fsp.id is null " +
//				"order by rt.data_msg_ricevuta desc, rt.iuv";
		return query;
	}

}

class RTExtractor implements ResultSetExtractor<List<RT>> {
	
    public List<RT> extractData(ResultSet rs) throws SQLException {
        List<RT> elencoRt = new ArrayList<RT> ();
		while(rs.next()){
			RT rt = new RT();
			rt.setApplicationId(rs.getString("application_id"));
			rt.setCommissioniApplicatePSP(rs.getDouble("commissioniapplicatepsp"));
			rt.setDataInvioFruitore(rs.getTimestamp("data_invio_fruitore"));
			rt.setDataMsgRicevuta(rs.getTimestamp("data_msg_ricevuta"));
			rt.setId(rs.getInt("id"));
			rt.setIdEsitoPagamento(rs.getInt("id_esito_pagamento"));
			rt.setIdMsgRicevuta(rs.getString("id_msg_ricevuta"));
			rt.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
			rt.setInsertDate(rs.getTimestamp("insert_date"));
			rt.setIuv(rs.getString("iuv"));
			rt.setLastUpdate(rs.getTimestamp("last_update"));
			rt.setRtData(rs.getBytes("rt_data"));
			rt.setSogenteInvioFruitore(rs.getString("sorgente_invio_fruitore"));
			rt.setStatoInvioFruitore(rs.getString("stato_invio_fruitore"));
//			rt.setTipoFirma(rs.getString(""));
			rt.setTransactionId(rs.getString("transaction_id"));
			rt.setSogenteInvioFruitore(rs.getString("sorgente_invio_fruitore"));
//			rt.setStatoInvio(rs.getInt("stato_invio"));
			elencoRt.add(rt);
		}
			
		return elencoRt;
	}
	
}

