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

import java.util.List;


public class EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO extends BaseDAO<List<DatiReceiptNonInviata>>{

	private LogUtil log = new LogUtil(EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO.class);

	private Integer limite;
	private String  idApplication;
	private String dataPioritariaInvio;

	public EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO ( String limiteInvio, String dataPioritariaInvio, String idApplication, Integer limiteNumGiorniReinvioReceipt ) {
		if ( StringUtils.isEmpty ( idApplication ) ) {
			throw new IllegalArgumentException ( "Assenza di id application" );
		}

		if ( StringUtils.isEmpty ( dataPioritariaInvio ) ) {
			throw new IllegalArgumentException ( "Assenza di data prioritaria invio" );
		}
		if ( !UtilDate.checkData ( dataPioritariaInvio ) ) {
			throw new IllegalArgumentException ( " data prioritaria invio non formattata correttamente" );
		}

		this.idApplication = idApplication;

		this.dataPioritariaInvio = dataPioritariaInvio;
		if ( StringUtils.isEmpty ( limiteInvio ) ) {
			setStatementMapper (
							new it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper ( limiteNumGiorniReinvioReceipt, dataPioritariaInvio ) );
		} else {
			this.limite = new Integer ( limiteInvio );
			setStatementMapper ( new GenericObjectArrayStatementMapper ( limiteNumGiorniReinvioReceipt, dataPioritariaInvio, limite ) );
		}

		setResultSetExtractor ( new DatiReceiptNonInviataExtractor () );
	}

	public EstraiRicevuteNonInviateDataPrioritariaIdApplicationReceiptDAO ( String limiteInvio, String idApplication, Integer limiteNumGiorniReinvioReceipt ) {
		if ( StringUtils.isEmpty ( idApplication ) ) {
			throw new IllegalArgumentException ( "Assenza di  id application" );
		}
		this.idApplication = idApplication;

		if ( !StringUtils.isEmpty ( limiteInvio ) ) {
			this.limite = new Integer ( limiteInvio );
			setStatementMapper ( new GenericObjectArrayStatementMapper ( limiteNumGiorniReinvioReceipt, limite ) );
		} else {
			setStatementMapper ( new GenericObjectArrayStatementMapper ( limiteNumGiorniReinvioReceipt ) );
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
						"enti e " +
						"where coda.iuv = rice.creditor_referenceid " +
						"and rice.transaction_id = coda.transaction_id " +
						"and rice.transaction_id = t.transaction_id " +
						"and rae.application_id = coda.application_id " +
						"and e.ente_id =rae.ente_id " +
						"and e.partita_iva =rice.id_pa " +
						"and (coda.data_inizio_tentativi is null " +
						"or coda.num_giorni_tentativi_ko<= ? ) ";

		query = query + "and coda.application_id in (" + idApplication + ") ";

		if ( !StringUtils.isEmpty ( dataPioritariaInvio ) ) {
			query = query + "and date(coda.data_inserimento) >= to_date(?, 'YYYY-MM-DD') ";
		}
		query = query + "order by application_id, id ";
		if ( null != limite ) {
			query = query + " limit ?";
		}
		return query;
	}
}
