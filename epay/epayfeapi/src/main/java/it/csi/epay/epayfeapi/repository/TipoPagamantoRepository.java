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
import java.util.Arrays;
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
					String[] sortableFields,
					String inputSortString,
					int pageIndex, // N.B zero-based
					int pageSize ) {

		var methodName = "[findByEnteAndTipologiaPagamentoAndLikeDescrizione] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "ente:%s", enteEntity );
		Log.infof ( "tipologiaPagamento:%s", tipologiaPagamentoEntity );
		Log.infof ( "sortableFields:%s", Arrays.toString ( sortableFields ) );
		Log.infof ( "inputSortString:%s", inputSortString );
		Log.infof ( "pageIndex:%d", pageIndex );
		Log.infof ( "pageSize:%d", pageSize );

		var now = new Date ();

		Log.infof ( "%senteEntity:%d", methodName, enteEntity );
		Log.infof ( "%stipologiaPagamentoEntity:%s", methodName, tipologiaPagamentoEntity );
		Log.infof ( "%s now:%s", methodName, now );

		// query base
		var query = "epayTEnti = ?1 "
						+ "and flagVisualizzaDaSportello = true "
						+ "and (tipologiaPagamento = ?2 or pagamentoSpontaneo = true) "
						+ "and (inizioValidita is null or inizioValidita <= ?3) and (fineValidita is null or ?3 <= fineValidita) ";

		// order by
		Map<String, String> fieldColumnMap = new HashMap<> ();
		fieldColumnMap.put ( SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO, "codiceVersamento" );
		fieldColumnMap.put ( SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO, "descrizionePortale" );
		var orderBy = FieldsUtil.buildOrderBy ( sortableFields, inputSortString, fieldColumnMap );
		if ( orderBy.isEmpty () ) {
			orderBy = "order by fineValidita asc ";// default
		}

		PanacheQuery<EpayTTipoPagamento> panacheQuery;
		if ( likeDescrizione != null ) {
			// estende la query base
			query += "and upper(descrizionePortale) like upper(?4) " + orderBy;
			Log.infof ( "%slikeDescrizione:%s", methodName, likeDescrizione );
			Log.infof ( "%squery (1): select * from EpayTTipoPagamento where %s", methodName, query );
			panacheQuery = find ( query, enteEntity, tipologiaPagamentoEntity, now, "%" + likeDescrizione + "%" );
		} else {
			query += orderBy;
			Log.infof ( "%squery (2): select * from EpayTTipoPagamento where %s", methodName, query );
			panacheQuery = find ( query, enteEntity, tipologiaPagamentoEntity, now );
		}
		Log.infof ( "%spageIndex:%d", methodName, pageIndex );
		Log.infof ( "%spageSize:%d", methodName, pageSize );

		// applica la paginazione
		PagedListResultDTO<EpayTTipoPagamento> result = new PagedListResultDTO<> ();
		RepositoryUtil.applyPagination ( result, panacheQuery, pageIndex, pageSize );

		Log.infof ( "%sresult:%s", methodName, result );
		Log.infof ( "%sEND", methodName );
		return result;
	}

	public EpayTTipoPagamento findByTipoPagamento ( EpayTTipoPagamento tipoPagamento ) {
		return find ( "idTipoPagamento", tipoPagamento.getIdTipoPagamento () ).firstResult ();
	}

	public List<EpayTTipoPagamento> findByEnte ( EpayTEnti ente ) {
		return find ( "epayTEnti", ente ).list ();
	}
}
