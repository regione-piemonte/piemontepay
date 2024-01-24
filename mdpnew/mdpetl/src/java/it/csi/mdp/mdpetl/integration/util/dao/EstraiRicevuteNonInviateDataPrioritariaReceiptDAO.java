/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.DatiReceiptNonInviata;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;
import it.csi.mdp.mdpetl.util.UtilDate;

import java.sql.SQLException;
import java.util.List;


public class EstraiRicevuteNonInviateDataPrioritariaReceiptDAO extends BaseDAO<List<DatiReceiptNonInviata>>{

	private LogUtil log = new LogUtil(EstraiRicevuteNonInviateDataPrioritariaReceiptDAO.class);
	private Integer limite;
	public EstraiRicevuteNonInviateDataPrioritariaReceiptDAO ( String limiteLetturaRecordReinvioReceipt, String dataPrioritariaReinvio, Integer limiteNumGiorniReinvioReceipt, byte[] sKey )
					throws SQLException {
		if ( StringUtils.isEmpty ( dataPrioritariaReinvio ) ) {
			throw new IllegalArgumentException ( "Assenza di data prioritaria" );
		}

		if ( null == sKey ) {
			throw new IllegalArgumentException ( "Assenza di chiave di decodifica" );
		}

		if ( !UtilDate.checkData ( dataPrioritariaReinvio ) ) {
			throw new IllegalArgumentException ( "dataPrioritariaReinvio  non formattata correttamente" );
		}
		String flagRetry = StringUtils.encodeStringValue ( "true", sKey );

		if ( StringUtils.isEmpty ( limiteLetturaRecordReinvioReceipt ) ) {
			setStatementMapper ( new it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper ( limiteNumGiorniReinvioReceipt, flagRetry,
							dataPrioritariaReinvio ) );
		} else {
			this.limite = new Integer ( limiteLetturaRecordReinvioReceipt );
			setStatementMapper ( new GenericObjectArrayStatementMapper ( limiteNumGiorniReinvioReceipt, flagRetry, dataPrioritariaReinvio, limite ) );
		}

		setResultSetExtractor ( new DatiReceiptNonInviataExtractor () );
	}

	@Override public String componiQuery () {
        String query = "select distinct rice.*, t.amount, coda.contatore_tentativi," +
                        "coda.transaction_id as coda_transaction_id," +
                        "coda.application_id as coda_application_id," +
                        "coda.data_tentativi, coda.data_inizio_tentativi," +
                        "coda.num_giorni_tentativi_ko, coda.ultimo_esito_fruitore," +
                        "coda.data_ultima_modifica, coda.data_inserimento " +
                        "from receipt_coda_invio coda," +
                        "mdp_receipt rice," +
                        "transazione t, " +
                        "r_application_enti rae," +
                        "applicationcustomfields custom, "+
                        "enti e " +
                        "where coda.iuv = rice.creditor_referenceid " +
                        "and rice.transaction_id = coda.transaction_id " +
                        "and rice.transaction_id = t.transaction_id " +
                        "and rae.application_id = coda.application_id " +
                        "and custom.applicationid = coda.application_id "+
                        "and custom.fieldname = 'flag_retry_rt' " +
                        "and e.ente_id =rae.ente_id " +
                        "and e.partita_iva =rice.id_pa " +
                        "and (coda.data_inizio_tentativi is null " +
                        "or coda.num_giorni_tentativi_ko<= ? ) " +
                        "and custom.fieldvalue = ? ";


            query = query + "and date(coda.data_inserimento) = to_date(?, 'YYYY-MM-DD') ";
        query = query + "order by application_id, id ";
        if ( null != limite ) {
            query = query + " limit ?";
        }
        return query;
    }
}
