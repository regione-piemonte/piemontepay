/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.entity.EpayDTipologiaPagamento;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.util.FieldsUtil;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO;


@ApplicationScoped
public class TipoPagamantoRepository implements PanacheRepository<EpayTTipoPagamento> {

	public List<EpayTTipoPagamento> findByEnteAndCodiceVersamento ( EpayTEnti ente, String codiceVersamento ) {
		return find ( "epayTEnti = ?1 and codiceVersamento = ?2", ente, codiceVersamento ).list ();
	}

	public PagedListResultDTO<EpayTTipoPagamento> findByEnteAndTipologiaPagamentoAndLikeDescrizione (
		EpayTEnti enteEntity,
		EpayDTipologiaPagamento tipologiaPagamentoEntity,
		String likeDescrizione,
		String [] sortableFields,
		String inputSortString,
		int pageIndex, // N.B zero-based
		int pageSize ) {

		String methodName = "[findByEnteAndTipologiaPagamentoAndLikeDescrizione] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( "ente:" + enteEntity );
		Log.info ( "tipologiaPagamento:" + tipologiaPagamentoEntity );
		Log.info ( "sortableFields:" + sortableFields );
		Log.info ( "inputSortString:" + inputSortString );
		Log.info ( "pageIndex:" + pageIndex );
		Log.info ( "pageSize:" + pageSize );

		Date now = new Date ();

		Log.info ( methodName + "?1 (enteEntity):" + enteEntity );
		Log.info ( methodName + "?2 (tipologiaPagamentoEntity):" + tipologiaPagamentoEntity );
		Log.info ( methodName + "?3 (now):" + now );

		// query base
		String query = "epayTEnti = ?1 "
						+ "and (tipologiaPagamento = ?2 or pagamentoSpontaneo = true) "
						+ "and (inizioValidita is null or inizioValidita <= ?3) and (fineValidita is null or ?3 <= fineValidita) ";

		// order by
		Map<String, String> fieldColumnMap = new HashMap<> ();
		fieldColumnMap.put ( SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO, "codiceVersamento" );
		fieldColumnMap.put ( SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO, "descrizionePortale" );
		String orderBy = FieldsUtil.buildOrderBy ( sortableFields, inputSortString, fieldColumnMap );
		if ( orderBy.isEmpty () ) {
			// default
			orderBy = "order by fineValidita asc ";
		}

		PanacheQuery<EpayTTipoPagamento> panacheQuery;
		if ( likeDescrizione != null ) {
			// estende la query base
			query += "and upper(descrizionePortale) like upper(?4) " + orderBy;

			Log.info ( methodName + "?4 (likeDescrizione):" + likeDescrizione );
			Log.info ( methodName + "query (1): select * from EpayTTipoPagamento where " + query );

			panacheQuery = find ( query, enteEntity, tipologiaPagamentoEntity, now, "%" + likeDescrizione + "%" );

		} else {
			query += orderBy;

			Log.info ( methodName + "query (2): select * from EpayTTipoPagamento where " + query );

			panacheQuery = find ( query, enteEntity, tipologiaPagamentoEntity, now );
		}

		Log.info ( methodName + "pageIndex:" + pageIndex );
		Log.info ( methodName + "pageSize:" + pageSize );

		// applica la paginazione
		PagedListResultDTO<EpayTTipoPagamento> result = new PagedListResultDTO<> ();
		result.setList ( panacheQuery.page ( pageIndex, pageSize ).list () );
		result.setCurrentPage ( pageIndex + 1 );
		result.setPageSize ( pageSize );
		result.setTotalPages ( panacheQuery.pageCount () );
		result.setTotalElements ( panacheQuery.count () );

		Log.info ( methodName + "result:" + result );
		Log.info ( methodName + "END" );
		return result;
	}

	public EpayTTipoPagamento findByTipoPagamento ( EpayTTipoPagamento tipoPagamento ) {
		return find ( "idTipoPagamento", tipoPagamento.getIdTipoPagamento () ).firstResult ();
	}

	public List<EpayTTipoPagamento> findByEnte ( EpayTEnti ente ) {
		return find ( "epayTEnti", ente ).list ();
	}
}
