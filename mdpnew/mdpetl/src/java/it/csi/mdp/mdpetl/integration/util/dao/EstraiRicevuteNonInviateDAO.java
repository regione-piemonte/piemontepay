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

import it.csi.mdp.mdpetl.dto.CodaInvioRT;
import it.csi.mdp.mdpetl.dto.DatiRicevutaNonInviata;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;

public class EstraiRicevuteNonInviateDAO extends BaseDAO<List<DatiRicevutaNonInviata>> {
	 LogUtil log = new LogUtil(EstraiRicevuteNonInviateDAO.class);
	 Integer limite;
	 String  idApplication;
	 
//	 public  EstraiRicevuteNonInviateDAO(Integer numGiorniKo,byte[] sKey) throws SerialException, SQLException {
//		   String flagRetry= StringUtils.encodeStringValue("true", sKey);
//			{
//				 setStatementMapper(new GenericObjectArrayStatementMapper(numGiorniKo));
//				 setStatementMapper(new GenericObjectArrayStatementMapper(flagRetry));
//				 
//				setResultSetExtractor(new DatiRicevutaNonInviataExtractor());
//			}
//		}
	 
	 public  EstraiRicevuteNonInviateDAO(String limiteRecord,Integer numGiorniKo,byte[] sKey) throws SerialException, SQLException {
		 if (null== numGiorniKo) 
		 {
			 throw new IllegalArgumentException("numGiorniKo  null");
		 }
		 if (null== sKey ) 
		 {
			 throw new IllegalArgumentException("Assenza di chiave di decodifica");
		 }
		
		   String flagRetry= StringUtils.encodeStringValue("true", sKey);
			{
				
				
				 if (StringUtils.isEmpty(limiteRecord) ) 
				 {
					 
					 setStatementMapper(new GenericObjectArrayStatementMapper(flagRetry, numGiorniKo ));
				 }
				 else
				 {
					 this.limite = new Integer(limiteRecord);
					 setStatementMapper(new GenericObjectArrayStatementMapper(flagRetry, numGiorniKo ,limite ));
				 }
				setResultSetExtractor(new DatiRicevutaNonInviataExtractor());
			}
		}
	

	@Override
	public String componiQuery() {
//		return "select rice.*, t.amount from rt_coda_invio coda, rt rice, transazione t where coda.iuv = rice.iuv and rice.transaction_id = t.transaction_id order by application_id, id";
		String query=  "select distinct rice.*, t.amount, coda.contatore_tentativi, "
				+ "coda.transaction_id as coda_transaction_id,  "
				+ "coda.application_id as coda_application_id, "
				+ "coda.data_tentativi, coda.data_inizio_tentativi, "
				+ "coda.num_giorni_tentativi_ko, coda.ultimo_esito_fruitore, "
				+ "coda.data_ultima_modifica, coda.data_inserimento  "
				+ " from rt_coda_invio coda, "
				+ "rt rice, "
				+ "transazione t,"
				+ "applicationcustomfields custom "
				+ "where coda.iuv = rice.iuv "
				+ "and rice.transaction_id =coda.transaction_id "
				+ "and rice.transaction_id = t.transaction_id "
				
				
				
				+ "and custom.applicationid = coda.application_id "
				+ "and custom.fieldname = 'flag_retry_rt' "
				+ "and custom.fieldvalue = ? "
				+ "and (coda.data_inizio_tentativi is null  or coda.num_giorni_tentativi_ko<= ? )";
//				+ "and coda.contatore_tentativi <> -1 ";
		
		        query= query+ getWhereContition();
		        
				
//				if (null != idApplication)
//				{
//					query= query+ "and  coda.application_id in (" + idApplication + ") "
//					+ "and date(coda.data_inserimento) = to_date(?, 'YYYY-MM-DD')";
//				}
//				
				query= query+  "order by application_id, id ";
//		
				if (null != limite)
				{
					query= query+ " limit ?";
				}
		return query;
				
		//		return "select rice.*, t.amount from rt_coda_invio coda, rt rice, transazione t where coda.iuv = rice.iuv and rice.transaction_id = t.transaction_id and (rice.stato_invio_fruitore <> 'OK' or rice.stato_invio_fruitore is null) order by application_id, id";
	}

	private String  getWhereContition() {
		return "";
		
	}
}

class DatiRicevutaNonInviataExtractor implements ResultSetExtractor<List<DatiRicevutaNonInviata>> {
	
	public List<DatiRicevutaNonInviata> extractData(ResultSet rs) throws SQLException {
		List<DatiRicevutaNonInviata> elenco = new ArrayList<DatiRicevutaNonInviata>();
		while(rs.next()){
			DatiRicevutaNonInviata risultato = new DatiRicevutaNonInviata();
			risultato.setId(rs.getInt("id"));
			risultato.setApplicationId(rs.getString("application_id"));
			risultato.setTransactionId(rs.getString("transaction_id"));
			risultato.setInsertDate(rs.getTimestamp("insert_date"));
			risultato.setLastUpdate(rs.getTimestamp("last_update"));
			risultato.setDataMsgRicevuta(rs.getTimestamp("data_msg_ricevuta"));
			risultato.setIdMsgRicevuta(rs.getString("id_msg_ricevuta"));
			risultato.setRtData(rs.getBytes("rt_data"));
			risultato.setTipoFirma(rs.getString("tipo_firma"));
			risultato.setIuv(rs.getString("iuv"));
			risultato.setIdEsitoPagamento(rs.getInt("id_esito_pagamento"));
			risultato.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
			risultato.setDataInvioFallito(rs.getDate("data_invio_fallito"));
			
			CodaInvioRT coda= new CodaInvioRT();
			
			coda.setContatoreTentativi(rs.getInt("contatore_tentativi"));
			coda.setApplicationId(rs.getString("application_id"));
			coda.setTransactionId(rs.getString("transaction_id"));
			coda.setDataTentativi(rs.getTimestamp("data_tentativi"));
			coda.setDataInizioTentativi(rs.getTimestamp("data_inizio_tentativi"));
			coda.setNumGiorniTentativiKo(rs.getInt("num_giorni_tentativi_ko"));
			coda.setUltimoEsitoFruitore(rs.getString("ultimo_esito_fruitore"));
			coda.setDataUltimaModifica(rs.getTimestamp("data_ultima_modifica"));
			coda.setDataInserimento(rs.getTimestamp("data_inserimento"));
			risultato.setCoda(coda);
			elenco.add(risultato);
		}
		return elenco;
	}
	
}

