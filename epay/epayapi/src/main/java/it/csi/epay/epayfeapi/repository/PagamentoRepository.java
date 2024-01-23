/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.dto.PaymentStatus;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.util.FieldsUtil;


@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<EpayTPagamento> {

	public EpayTPagamento findByiuvNumeroAvviso ( String iuv ) {
		return find ( "iuvNumeroAvviso", iuv ).firstResult ();
	}

	public EpayTPagamento findByIUVAndIsActiveAndPayable ( String iuv ) {
		Date now = new Date ();
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
		String [] sortableFields,
		String inputSortString,
		int pageIndex, // N.B zero-based
		int pageSize ) {

		String methodName = "[search] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( "ente:" + enteEntity );
		Log.info ( "citizenFiscalCode:" + citizenFiscalCode );
		Log.info ( "PaymentStatus:" + status.name () );
		Log.info ( "iuv:" + iuv );
		Log.info ( "inputSortString:" + inputSortString );
		Log.info ( "pageIndex:" + pageIndex );
		Log.info ( "pageSize:" + pageSize );

		List<Integer> inStatus;
		switch ( status ) {
		default :
		case ALL :
			inStatus = Arrays.asList ( 0, 3, 5, 4, 6, 7, 11, 9 ); // sono tutti gli stati PAYED, NOT_PAYED + 9 (stato annullato dall'ente)
			break;
		case PAYED :
			inStatus = Arrays.asList ( 4, 6, 7, 11 );
			break;
		case NOT_PAYED :
			inStatus = Arrays.asList ( 0, 3, 5 );
			break;
		}

		Log.info ( methodName + "?1 (citizenFiscalCode):" + citizenFiscalCode );
		Log.info ( methodName + "?2 (ente):" + enteEntity );
		Log.info ( methodName + "?3 (inStatus):" + Arrays.toString ( inStatus.toArray () ) );

		// query base
		String query = "epayTAnagrafica.codiceFiscale = ?1 "
			+ "and epayTTipoPagamento.epayTEnti = ?2 "
			+ "and epayDStatoPagamento.idStato in (?3) "
			+ "and (inizioValidita is null or ?4 >= inizioValidita) "
			+ "and (fineValidita is null or ?4 <= fineValidita) ";

		// order by
		Map<String, String> fieldColumnMap = new HashMap<> ();
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IUV, "iuvNumeroAvviso" );
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__CODICE_VERSAMENTO, "epayTTipoPagamento.codiceVersamento" );
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__DATA_SCADENZA, "dataScadenza" );
		fieldColumnMap.put ( SERVICE_FIELDS_FIND_DEBT_POSITIONS__IMPORTO, "importo" );
		String orderBy = FieldsUtil.buildOrderBy ( sortableFields, inputSortString, fieldColumnMap );
		if ( orderBy.isEmpty () ) {
			orderBy = "order by dataScadenza asc "; // default
		}

		Date now = new Date ();
		PanacheQuery<EpayTPagamento> panacheQuery;
		if ( iuv != null ) {
			query += "and iuvNumeroAvviso = ?5 " + orderBy;

			Log.info ( methodName + "?4 (iuv):" + iuv );
			Log.info ( methodName + "query (1): select * from EpayTPagamento where " + query );

			panacheQuery = find ( query, citizenFiscalCode, enteEntity, inStatus, now, iuv );

		} else {
			query += orderBy;

			Log.info ( methodName + "query (2): select * from EpayTPagamento where " + query );

			panacheQuery = find ( query, citizenFiscalCode, enteEntity, inStatus, now );
		}

		Log.info ( methodName + "pageIndex:" + pageIndex );
		Log.info ( methodName + "pageSize:" + pageSize );

		// applica la paginazione
		PagedListResultDTO<EpayTPagamento> result = new PagedListResultDTO<> ();
		result.setList ( panacheQuery.page ( pageIndex, pageSize ).list () );
		result.setCurrentPage ( pageIndex + 1 );
		result.setPageSize ( pageSize );
		result.setTotalPages ( panacheQuery.pageCount () );
		result.setTotalElements ( panacheQuery.count () );

		Log.info ( methodName + "result:" + result );
		Log.info ( methodName + "END" );
		return result;
	}
}
