/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import it.csi.mdp.mdpetl.dto.DatiRicevutaNonInviata;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;
import it.csi.mdp.mdpetl.util.UtilDate;

public class EstraiRicevuteNonInviateDataPrioritariaDAO extends BaseDAO<List<DatiRicevutaNonInviata>> {
	 LogUtil log = new LogUtil(EstraiRicevuteNonInviateDataPrioritariaDAO.class);
	 Integer limite;
//	 String  idApplication;
	 String dataLimite;
	 
	 
	 public  EstraiRicevuteNonInviateDataPrioritariaDAO(String limiteInvio, String dataLimite, Integer limiteNumGiorniReinvioRt,  byte[] key) throws SerialException, SQLException {
		 if (StringUtils.isEmpty(dataLimite) ) 
		 {
			 throw new IllegalArgumentException("Assenza di data prioritaria");
		 }
		 
		 if (null== key ) 
		 {
			 throw new IllegalArgumentException("Assenza di chiave di decodifica");
		 }

		 if (!UtilDate.checkData(dataLimite))
		 {
			 throw new IllegalArgumentException("dataPrioritariaReinvio  non formattata correttamente");
		 }
		 String flagRetry= StringUtils.encodeStringValue("true", key);
		 
		 if (StringUtils.isEmpty(limiteInvio) ) 
		 {
			 
			 setStatementMapper(new GenericObjectArrayStatementMapper(limiteNumGiorniReinvioRt, flagRetry, dataLimite));
		 }
		 else
		 {
			 this.limite = new Integer(limiteInvio);
			 setStatementMapper(new GenericObjectArrayStatementMapper(limiteNumGiorniReinvioRt,flagRetry, dataLimite,limite ));
		 }



		 setResultSetExtractor(new DatiRicevutaNonInviataExtractor());
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
				+ "and rice.transaction_id = coda.transaction_id "
				+ "and rice.transaction_id = t.transaction_id "
				+ "and (coda.data_inizio_tentativi is null  or coda.num_giorni_tentativi_ko<= ? ) "
		
				+ "and custom.applicationid = coda.application_id "
				+ "and custom.fieldname = 'flag_retry_rt' "
				+ "and custom.fieldvalue = ? ";
				
				
				
					query= query+ "and date(coda.data_inserimento) = to_date(?, 'YYYY-MM-DD')";
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

}


