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

public class EstraiRicevuteNonInviateDataPrioritariaIdApplicationDAO extends BaseDAO<List<DatiRicevutaNonInviata>> {
	 LogUtil log = new LogUtil(EstraiRicevuteNonInviateDataPrioritariaIdApplicationDAO.class);
	 Integer limite;
	 String  idApplication;
	 String dataPioritariaInvio;
	 
	 
	 public  EstraiRicevuteNonInviateDataPrioritariaIdApplicationDAO(String limiteInvio, String idApplication,Integer limiteNumGiorniReinvioRt) throws SerialException, SQLException {
		 if (StringUtils.isEmpty(idApplication)) 
		 {
			 throw new IllegalArgumentException("Assenza di  id application");
		 }

		 this.idApplication= idApplication;
		

		 if (!StringUtils.isEmpty(limiteInvio) ) 
		 {
			 this.limite= new Integer(limiteInvio);

			 setStatementMapper(new GenericObjectArrayStatementMapper(limiteNumGiorniReinvioRt, limite));
		 }
		 else
		 {
			 setStatementMapper(new GenericObjectArrayStatementMapper( limiteNumGiorniReinvioRt));
		 }


		 setResultSetExtractor(new DatiRicevutaNonInviataExtractor());
	 }
	 
		public  EstraiRicevuteNonInviateDataPrioritariaIdApplicationDAO( String limiteInvio, String dataPioritariaInvio, String idApplication, Integer limiteNumGiorniReinvioRt) throws SerialException, SQLException {
		 if (StringUtils.isEmpty(idApplication)) 
		 {
			 throw new IllegalArgumentException("Assenza di  id application");
		 }
		 
		 if (StringUtils.isEmpty(dataPioritariaInvio)) 
		 {
			 throw new IllegalArgumentException("Assenza di  data prioritaria invio");
		 }
		 if (!UtilDate.checkData(dataPioritariaInvio))
		 {
			 throw new IllegalArgumentException(" data prioritaria invio  non formattata correttamente");
		 }
		 
		 
		 this.idApplication= idApplication;
		
		 this.dataPioritariaInvio= dataPioritariaInvio;
		 if (StringUtils.isEmpty(limiteInvio) ) 
		 {
			 setStatementMapper(new GenericObjectArrayStatementMapper( limiteNumGiorniReinvioRt, dataPioritariaInvio));
		 }
		 else {
			 this.limite= new Integer(limiteInvio);
			 setStatementMapper(new GenericObjectArrayStatementMapper(limiteNumGiorniReinvioRt, dataPioritariaInvio , limite));
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
				+ "transazione t "
				+ "where coda.iuv = rice.iuv "
				+ "and rice.transaction_id = coda.transaction_id "
				+ "and rice.transaction_id = t.transaction_id "
				+ "and (coda.data_inizio_tentativi is null  or coda.num_giorni_tentativi_ko<= ? ) ";
				
		        query= query+ "and  coda.application_id in (" + idApplication + ") ";
				
				
				if (!StringUtils.isEmpty(dataPioritariaInvio))
				{
					query= query+ "and date(coda.data_inserimento) = to_date(?, 'YYYY-MM-DD') ";
				}
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


