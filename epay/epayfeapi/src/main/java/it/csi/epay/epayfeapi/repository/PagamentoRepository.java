/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.enumeration.PaymentStatus;
import it.csi.epay.epayfeapi.util.FieldsUtil;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV;


@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<EpayTPagamento> {

	public EpayTPagamento findByiuvNumeroAvviso ( String iuv ) {
		return find ( "iuvNumeroAvviso", iuv ).firstResult ();
	}

	public EpayTPagamento findByIUVAndIsActiveAndPayable ( String iuv ) {
		var now = new Date ();
		return find ( "iuvNumeroAvviso = ?1 and (epayTTipoPagamento.inizioValidita is null or epayTTipoPagamento.inizioValidita <= ?2) " +
										"and (epayTTipoPagamento.fineValidita is null or epayTTipoPagamento.fineValidita >= ?2) " +
										"and (inizioValidita is null or inizioValidita <= ?2) " +
										"and (fineValidita is null or fineValidita >= ?2) and epayDStatoPagamento.pagabile = true",
						iuv, now ).firstResult ();
	}

	public PagedListResultDTO<EpayTPagamento> search (
					EpayTEnti enteEntity,
					String citizenFiscalCode,
					PaymentStatus status,
					String iuv,
					String[] sortableFields,
					String inputSortString,
					int pageIndex, // N.B zero-based
					int pageSize ) {

		var methodName = "[search] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "ente:%s", enteEntity );
		Log.infof ( "citizenFiscalCode:%s", citizenFiscalCode );
		Log.infof ( "PaymentStatus:%s", status.name () );
		Log.infof ( "iuv:%s", iuv );
		Log.infof ( "inputSortString:%s", inputSortString );
		Log.infof ( "pageIndex:%d", pageIndex );
		Log.infof ( "pageSize:%d", pageSize );

		List<Integer> inStatus;
		switch ( status ) {
		default:
		case ALL:
			inStatus = Arrays.asList ( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ); // sono tutti gli stati PAYED, NOT_PAYED + 9 (stato annullato dall'ente) 09092024 inserito 2 in attesa su richiesta della Varesio
			break;
		case PAYED:
			inStatus = Arrays.asList ( 4, 11 );
			break;
		case NOT_PAYED:
			inStatus = Arrays.asList ( 0, 1, 2, 3, 5, 6 ,7, 8, 10 );
			break;
		}

		Log.infof ( "%scitizenFiscalCode):%s", methodName, citizenFiscalCode );
		Log.infof ( "%sente):%s", methodName, enteEntity );
		Log.infof ( "%sinStatus):%s", methodName, Arrays.toString ( inStatus.toArray () ) );

		// query base
		var query = "epayTAnagrafica.codiceFiscale = ?1 "
						+ "and epayTTipoPagamento.epayTEnti = ?2 "
						+ "and epayDStatoPagamento.idStato in (?3) "
						+ "and (epayDStatoPagamento.idStato in( 4, 9 ) or ( "
						+ " (inizioValidita is null or ?4 >= inizioValidita) "
						+ "and (fineValidita is null or ?4 <= fineValidita))) ";

		// order by
		Map<String, String> fieldColumnMap = new HashMap<> ();
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV, "iuvNumeroAvviso" );
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO, "epayTTipoPagamento.codiceVersamento" );
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA, "dataScadenza" );
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO, "importo" );
		var orderBy = FieldsUtil.buildOrderBy ( sortableFields, inputSortString, fieldColumnMap );
		if ( orderBy.isEmpty () ) {
			orderBy = "order by dataScadenza asc "; // default
		}

		var now = new Date ();
		PanacheQuery<EpayTPagamento> panacheQuery;
		if ( iuv != null ) {
			query += "and iuvNumeroAvviso = ?5 " + orderBy;

			Log.infof ( "%siuv:%s", methodName, iuv );
			Log.infof ( "%squery (1): select * from EpayTPagamento where %s", methodName, query );

			panacheQuery = find ( query, citizenFiscalCode, enteEntity, inStatus, now, iuv );

		} else {
			query += orderBy;

			Log.infof ( "%squery (2): select * from EpayTPagamento where %s", methodName, query );

			panacheQuery = find ( query, citizenFiscalCode, enteEntity, inStatus, now );
		}

		Log.infof ( "%spageIndex:%s", methodName, pageIndex );
		Log.infof ( "%spageSize:%s", methodName, pageSize );

		// applica la paginazione
		PagedListResultDTO<EpayTPagamento> result = new PagedListResultDTO<> ();
		RepositoryUtil.applyPagination ( result, panacheQuery, pageIndex, pageSize );

		Log.infof ( "%sresult:%s", methodName, result );
		Log.infof ( "%sEND", methodName );
		return result;
	}
}
