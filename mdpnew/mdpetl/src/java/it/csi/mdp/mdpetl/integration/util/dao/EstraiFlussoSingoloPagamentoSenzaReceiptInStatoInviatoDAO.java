/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.util.UtilDate;
import org.apache.commons.lang.StringUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EstraiFlussoSingoloPagamentoSenzaReceiptInStatoInviatoDAO extends BaseDAO<List<FlussoSingoloPagamento>> {

	private final String limiteInferioreDataEsitoSingoloPagamento;

	private final String limiteLetturaPagCodZeroSenzaRT;

	private final String limiteApplicazionePagCodZeroSenzaRT;

	public EstraiFlussoSingoloPagamentoSenzaReceiptInStatoInviatoDAO ( String limiteInferioreDataEsitoSingoloPagamento, String limiteLetturaPagCodZeroSenzaRT,
					String limiteApplicazionePagCodZeroSenzaRT ) {
		if ( StringUtils.isBlank ( limiteInferioreDataEsitoSingoloPagamento ) ) {
			throw new IllegalArgumentException ( "Assenza di limiteInferioreDataEsitoSingoloPagamento" );
		}
		if ( !UtilDate.checkData ( limiteInferioreDataEsitoSingoloPagamento ) ) {
			throw new IllegalArgumentException ( " data dataesitosingolopagamento invio non formattata correttamente" );
		}
		if ( StringUtils.isBlank ( limiteLetturaPagCodZeroSenzaRT ) ) {
			throw new IllegalArgumentException ( "Assenza di limiteLetturaPagCodZeroSenzaRT" );
		}
		this.limiteInferioreDataEsitoSingoloPagamento = limiteInferioreDataEsitoSingoloPagamento;
		this.limiteLetturaPagCodZeroSenzaRT = limiteLetturaPagCodZeroSenzaRT;
		this.limiteApplicazionePagCodZeroSenzaRT = limiteApplicazionePagCodZeroSenzaRT;
		this.setResultSetExtractor ( new FlussoSingoloPagamentoExtractorSenzaReceipt () );
	}

	@Override public String componiQuery () {
		String sql = "SELECT s.id, s.iuv, s.application_id, s.dataesitosingolopagamento, null AS idRPT, gp.id_getpayment AS idGETPAYMENT, s.id_flusso AS id_flusso, " +
						"fr.identificativopsp AS identificativopsp, fr.identificativoistitutomittente AS identificativoistitutomittente, " +
						"fr.denominazionemittente AS denominazionemittente, s.identificativounivocoriscossione AS identificativounivocoriscossione, " +
						"s.singoloimportopagato AS singoloimportopagato, gp.transaction_id AS transaction_id, s.datainserimento AS datainserimento, " +
						"s.codiceesitosingolopagamento AS codiceesitosingolopagamento " +
						"FROM flusso_singolo_pagamento s " +
						"JOIN flusso_riversamento fr ON (s.id_flusso = fr.id) " +
						"JOIN application a ON a.id = s.application_id " +
						"JOIN mdp_getpayment gp ON s.iuv=gp.creditor_referenceid " +
						"WHERE s.dataesitosingolopagamento >= '" + limiteInferioreDataEsitoSingoloPagamento + "'" +
						"AND NOT EXISTS (SELECT 1 FROM mdp_receipt rec WHERE rec.creditor_referenceid = s.iuv and rec.pagamento_inviato = true)";

		if ( StringUtils.isNotBlank ( limiteApplicazionePagCodZeroSenzaRT ) ) {
			sql = sql.concat ( "AND s.application_id IN (" + limiteApplicazionePagCodZeroSenzaRT + ") " );
		}
		sql = sql.concat ( "LIMIT " + limiteLetturaPagCodZeroSenzaRT );
		System.out.println ( "sql: " + sql );
		return sql;
	}
}


class FlussoSingoloPagamentoExtractorSenzaReceipt implements ResultSetExtractor<List<FlussoSingoloPagamento>> {

	@Override public List<FlussoSingoloPagamento> extractData ( ResultSet rs ) throws Exception {
		List<FlussoSingoloPagamento> elencoFlussi = new ArrayList<FlussoSingoloPagamento> ();
		while ( rs.next () ) {
			FlussoSingoloPagamento f = new FlussoSingoloPagamento ();
			f.setIdFlusso ( rs.getInt ( "id" ) );
			f.setIuv ( rs.getString ( "iuv" ) );
			f.setApplicationId ( rs.getString ( "application_id" ) );
			f.setDataEsitoSingoloPagamento ( rs.getTimestamp ( "dataesitosingolopagamento" ) );
			f.setIdRPT ( rs.getInt ( "idRPT" ) );
			f.setIdGetpayment ( rs.getInt ( "idGETPAYMENT" ) );
			f.setIdFlusso ( rs.getInt ( "id_flusso" ) );
			f.setIdentificativoPsp ( rs.getString ( "identificativopsp" ) );
			f.setIdentificativoIstitutoMittente ( rs.getString ( "identificativoistitutomittente" ) );
			f.setDenominazioneMittente ( rs.getString ( "denominazionemittente" ) );
			f.setIdentificativoUnivocoRiscossione ( rs.getString ( "identificativounivocoriscossione" ) );
			f.setSingoloImportoPagato ( rs.getBigDecimal ( "singoloimportopagato" ) );
			f.setTransactionId ( rs.getString ( "transaction_id" ) );
			f.setDataInserimento ( rs.getTimestamp ( "datainserimento" ) );
			f.setCodiceEsitoSingoloPagamento ( rs.getString ( "codiceesitosingolopagamento" ) );
			elencoFlussi.add ( f );
		}
		return elencoFlussi;
	}
}
