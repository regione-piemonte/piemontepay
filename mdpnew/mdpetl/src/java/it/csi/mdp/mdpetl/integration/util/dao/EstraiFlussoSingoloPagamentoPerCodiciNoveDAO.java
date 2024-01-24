/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;


/**
 *
 */

public class EstraiFlussoSingoloPagamentoPerCodiciNoveDAO extends BaseDAO<List<FlussoSingoloPagamento>> {

	private final String codApplicazioniPagCod9;

	private final String codiceTipologiaPagCod9;

	private final String limiteInferioreDataEsitoCod9;

	private final String limiteLetturaPagCod9;

	public EstraiFlussoSingoloPagamentoPerCodiciNoveDAO ( String codApplicazioniPagCod9, String codiceTipologiaPagCod9, String limiteInferioreDataEsitoCod9,
		String limiteLetturaPagCod9 ) {
		if ( StringUtils.isBlank ( codiceTipologiaPagCod9 ) ) {
			throw new IllegalArgumentException ( "Assenza di codiceTipologiaPagCod9" );
		}
		if ( StringUtils.isBlank ( limiteInferioreDataEsitoCod9 ) ) {
			throw new IllegalArgumentException ( "Assenza di limiteInferioreDataEsitoCod9" );
		}
		if ( StringUtils.isBlank ( limiteLetturaPagCod9 ) ) {
			throw new IllegalArgumentException ( "Assenza di limiteLetturaPagCod9" );
		}
		this.codApplicazioniPagCod9 = codApplicazioniPagCod9;
		this.codiceTipologiaPagCod9 = codiceTipologiaPagCod9;
		this.limiteInferioreDataEsitoCod9 = limiteInferioreDataEsitoCod9;
		this.limiteLetturaPagCod9 = limiteLetturaPagCod9;
		this.setResultSetExtractor ( new FlussoSingoloPagamentoExtractorPerCodNove () );
	}

	@Override
	public String componiQuery () {
		String sql
			= "SELECT s.iuv, s.application_id,s.dataesitosingolopagamento, coalesce('RPT_'|| r.id, 'RECEIPT_' || r1.id) AS tipoRPT, coalesce(r.id, r1.id) AS idRPT,"
				+ "s.id_flusso AS id_flusso, fr.identificativopsp AS identificativopsp,"
				+ "fr.identificativoistitutomittente AS identificativoistitutomittente,fr.denominazionemittente AS denominazionemittente," 
				+ "s.identificativounivocoriscossione AS identificativounivocoriscossione, s.singoloimportopagato AS singoloimportopagato,"
				+ "null AS transaction_id, s.datainserimento AS datainserimento, s.codiceesitosingolopagamento AS codiceesitosingolopagamento "
				+ "FROM flusso_singolo_pagamento s JOIN flusso_riversamento fr ON (s.id_flusso = fr.id) JOIN application a ON a.id = s.application_id "
				+ "left join rpt r on s.iuv = r.iuv "
				+ "left join mdp_receipt r1 on s.iuv || fr.identificativoistitutoricevente  = r1.creditor_referenceid  || r1.id_pa "
				+ "WHERE s.dataesitosingolopagamento >= '" + limiteInferioreDataEsitoCod9 + "' "
				+ "AND (s.esito_ultimo_invio_a_fruitore = 'KO' OR s.esito_ultimo_invio_a_fruitore IS NULL ) "
				+ "AND s.codiceesitosingolopagamento = '" + codiceTipologiaPagCod9 + "' ";

		if ( StringUtils.isNotBlank ( codApplicazioniPagCod9 ) ) {
			sql = sql.concat ( "AND s.application_id in (" + codApplicazioniPagCod9 + ") " );
		}
		sql = sql.concat ( "LIMIT " + limiteLetturaPagCod9 );
		System.out.println ( "sql: " + sql );
		return sql;
	}

}

class FlussoSingoloPagamentoExtractorPerCodNove implements ResultSetExtractor<List<FlussoSingoloPagamento>> {

	@Override
	public List<FlussoSingoloPagamento> extractData ( ResultSet rs ) throws Exception {
		List<FlussoSingoloPagamento> elencoFlussi = new ArrayList<FlussoSingoloPagamento> ();
		while ( rs.next () ) {
			FlussoSingoloPagamento f = new FlussoSingoloPagamento ();
			f.setIuv ( rs.getString ( "iuv" ) );
			f.setApplicationId ( rs.getString ( "application_id" ) );
			f.setDataEsitoSingoloPagamento ( rs.getTimestamp ( "dataesitosingolopagamento" ) );
			f.setIdRPT ( rs.getInt ( "idRPT" ) );
			f.setIdFlusso ( rs.getInt ( "id_flusso" ) );
			f.setIdentificativoUnivocoRiscossione ( rs.getString ( "identificativounivocoriscossione" ) );
			f.setSingoloImportoPagato ( rs.getBigDecimal ( "singoloimportopagato" ) );
			f.setCodiceEsitoSingoloPagamento ( rs.getString ( "codiceesitosingolopagamento" ) );
			f.setDenominazioneMittente ( rs.getString ( "denominazionemittente" ) );
			f.setIdentificativoIstitutoMittente ( rs.getString ( "identificativoistitutomittente" ) );
			f.setIdentificativoPsp ( rs.getString ( "identificativopsp" ) );
			f.setTransactionId ( rs.getString ( "transaction_id" ) );
			f.setDataInserimento ( rs.getTimestamp ( "datainserimento" ) );
			elencoFlussi.add ( f );
		}
		return elencoFlussi;
	}
}
