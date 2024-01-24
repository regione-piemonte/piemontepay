/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.common;

import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamenti;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class EpaypaTPagamentoCommon {
	@SuppressWarnings("rawtypes")
	public static Path getColumnPath ( Root<EpaypaTPagamenti> root, ColumnNamePagamentiEnum columnEnum, Boolean isPagamentoSpontaneo ) {
		if ( isPagamentoSpontaneo ) {
			return getColumnPathPagamentoSpontaneo ( root, columnEnum );
		} else {
			return getColumnPathPagamentoDovuto ( root, columnEnum );
		}
	}

	@SuppressWarnings ( "rawtypes" )
	private static Path getColumnPathPagamentoSpontaneo ( Root<EpaypaTPagamenti> root, ColumnNamePagamentiEnum columnEnum ) {
		Path columnPath = null;
		if ( columnEnum != null ) {
			switch ( columnEnum ) {
			case IUV:
			case IMPORTO_DOVUTO:
				columnPath = root.get ( "iuv" );
				break;
			case DESCRIZIONE_CAUSALE_VERSAMENTO:
				columnPath = root.get ( "notificaPagamento" ).get ( "desCausaleVersamento" );
				break;
			case COD_VERSAMENTO:
				columnPath = root.get ( "notificaPagamento" ).get ( "epaypaTFlusso" ).get ( "epaypaTCodiceVersamento" ).get ( "codVersamento" );
				break;
			case IMPORTO_PAGATO:
				columnPath = root.get ( "notificaPagamento" ).get ( "importoPagato" );
				break;
			case DATA_SCADENZA:
				columnPath = root.get ( "notificaPagamento" ).get ( "dtScadenza" );
				break;
			case DATA_PAGAMENTO:
				columnPath = root.get ( "dataPagamento" );
				break;
			case STATO_PAGAMENTO:
				columnPath = root.get ( "descStato" );
				break;
			case CAUSALE:
				columnPath = root.get ( "causale" );
			}
		}
		return columnPath;
	}

	@SuppressWarnings ( "rawtypes" )
	static private Path getColumnPathPagamentoDovuto ( Root<EpaypaTPagamenti> root, ColumnNamePagamentiEnum columnEnum ) {
		Path columnPath = null;
		if ( columnEnum != null ) {
			switch ( columnEnum ) {
			case IUV:
				columnPath = root.get ( "iuv" );
				break;
			case DESCRIZIONE_CAUSALE_VERSAMENTO:
				columnPath = root.get ( "posizioneDebitoria" ).get ( "desCausaleVersamento" );
				break;
			case IMPORTO_DOVUTO:
				columnPath = root.get ( "posizioneDebitoria" ).get ( "importoTotale" );
				break;
			case COD_VERSAMENTO:
				columnPath = root.get ( "codVersamento" );
				break;
			case IMPORTO_PAGATO:
				columnPath = root.get ( "importoPagato" );
				break;
			case DATA_SCADENZA:
				columnPath = root.get ( "posizioneDebitoria" ).get ( "dtScadenza" );
				break;
			case DATA_PAGAMENTO:
				columnPath = root.get ( "dataPagamento" );
				break;
			case STATO_PAGAMENTO:
				columnPath = root.get ( "descStato" );
				break;
			case CAUSALE:
				columnPath = root.get ( "causale" );
				break;
			}
		}
		return columnPath;
	}

}
