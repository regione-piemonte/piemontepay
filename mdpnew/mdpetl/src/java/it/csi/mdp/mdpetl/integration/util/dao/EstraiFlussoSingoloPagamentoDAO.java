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
import it.csi.mdp.mdpetl.util.UtilDate;


public class EstraiFlussoSingoloPagamentoDAO extends BaseDAO<List<FlussoSingoloPagamento>> {

	private final String limiteInferioreDataEsitoSingoloPagamento;

	private final String limiteLetturaPagCodZeroSenzaRT;

	private final String limiteApplication;

	private final String codiceTiplogiaPagCodZeroSenzaRT;

	public EstraiFlussoSingoloPagamentoDAO ( String limiteInferioreDataEsitoSingoloPagamento, String limiteLetturaPagCodZeroSenzaRT, String limiteApplication, String codiceTiplogiaPagCodZeroSenzaRT) {
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
		this.limiteApplication = limiteApplication;
		this.codiceTiplogiaPagCodZeroSenzaRT = codiceTiplogiaPagCodZeroSenzaRT;
		this.setResultSetExtractor ( new FlussoSingoloPagamentoExtractor () );
	}

	@Override
	public String componiQuery () {
	    String sql = "SELECT s.id, s.iuv, s.application_id,s.dataesitosingolopagamento, r.id AS idRPT, null AS idGETPAYMENT, s.id_flusso AS id_flusso, fr.identificativopsp AS identificativopsp, "
	                    + "fr.identificativoistitutomittente AS identificativoistitutomittente, fr.denominazionemittente AS denominazionemittente, "
	                    + "s.identificativounivocoriscossione AS identificativounivocoriscossione, s.singoloimportopagato AS singoloimportopagato, "
	                    + "r.transaction_id AS transaction_id, s.datainserimento AS datainserimento, s.codiceesitosingolopagamento AS codiceesitosingolopagamento "
                        + "FROM flusso_singolo_pagamento s "
                        + "JOIN flusso_riversamento fr ON (s.id_flusso = fr.id) "
                        + "JOIN rpt r ON s.iuv = r.iuv "
                        + "JOIN application a ON a.id = s.application_id "
                        + "WHERE "
                        + "s.dataesitosingolopagamento >= '" + limiteInferioreDataEsitoSingoloPagamento + "' "
                        + "AND  r.id_stati_rpt in (50,60) "
                        + "AND NOT EXISTS (SELECT 1 FROM rt WHERE rt.iuv = s.iuv and id_esito_pagamento = 1)"
                        + "AND s.codiceesitosingolopagamento = '" + codiceTiplogiaPagCodZeroSenzaRT + "' ";
	    if( StringUtils.isNotBlank ( limiteApplication )) {
	        sql = sql.concat ( "AND s.application_id IN (" + limiteApplication + ") " );
	    } 
	    sql = sql.concat ( "LIMIT " + limiteLetturaPagCodZeroSenzaRT );
	    System.out.println ("sql: " + sql);
		return sql;
	}

}


class FlussoSingoloPagamentoExtractor implements ResultSetExtractor<List<FlussoSingoloPagamento>> {

    @Override
	public List<FlussoSingoloPagamento> extractData ( ResultSet rs ) throws Exception {
		List<FlussoSingoloPagamento> elencoFlussi = new ArrayList<FlussoSingoloPagamento> ();
		while ( rs.next () ) {
			FlussoSingoloPagamento f = new FlussoSingoloPagamento ();
			f.setId ( rs.getInt ( "id" ) );
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
			f.setIdGetpayment ( rs.getInt ( "idGETPAYMENT" ) );
			elencoFlussi.add ( f );
		}
		return elencoFlussi;
	}
}
