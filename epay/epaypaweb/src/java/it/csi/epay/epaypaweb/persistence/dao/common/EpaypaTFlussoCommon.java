/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.common;

import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTFlussoFilter;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.CODICE_ESITO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.COD_ID_UNIVOCO_MITTENTE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.C_FISC;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.DATA_INSERIMENTO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.DATA_ULTIMA_VARIAZIONE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.DENOMINAZIONE_MITTENTE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.DETTAGLIO_ESITO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.ID_CODICE_VERSAMENTO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.ID_ENTE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.ID_MESSAGGIO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.POSIZIONE_DEBITORIA_ESTERNA;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.IUV;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.IUV_AGGIORNAMENTO_POSIZIONI_DEBITORIE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.PAGAMENTI_SPONTANEI;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.STATO_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.TIPO_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.UTENTE_ULTIMA_VARIAZIONE;
import static it.csi.epay.epaypaweb.util.Util.getLaterDate;

public class EpaypaTFlussoCommon {
	@SuppressWarnings("unchecked")
	static public List<Predicate> buildPredicates(FlussoLightFilterDto filter, CriteriaBuilder cbuilder, Root<EpaypaTFlussoLight> root) {
		List<Predicate> predicates = null;

		if (filter != null) {
			predicates = new ArrayList<> ();
			if ( null != filter.getIuv () ) {
				predicates.add ( cbuilder.equal ( getColumnPath ( root, IUV ), filter.getIuv () ) );
			}
			if ( null != filter.getCfisc ()) {
				predicates.add ( cbuilder.equal ( getColumnPath ( root, C_FISC ), filter.getCfisc () ) );
			}
			if ( null != filter.getIdPosizioneDebitoriaEsterna () ) {
				predicates.add ( cbuilder.equal ( getColumnPath ( root, POSIZIONE_DEBITORIA_ESTERNA ), filter.getIdPosizioneDebitoriaEsterna () ) );
			}
			if (filter.getIdMessaggio() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, ID_MESSAGGIO), filter.getIdMessaggio()));
			}
			if (filter.getTipoFlusso() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, TIPO_FLUSSO), filter.getTipoFlusso().getId()));
			}
			if (filter.getStatoFlusso() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, STATO_FLUSSO), filter.getStatoFlusso().getId()));
			}
			if (filter.getIdEnte() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, ID_ENTE), filter.getIdEnte()));
			}
			if (filter.getIdCodVersamentoList() != null) {
				predicates.add(getColumnPath(root, ID_CODICE_VERSAMENTO).in(filter.getIdCodVersamentoList()));
			}
			if (filter.getPagamentiSpontanei() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, PAGAMENTI_SPONTANEI), filter.getPagamentiSpontanei()));
			}
			if (filter.getDataInserimentoDa() != null) {
				predicates.add(cbuilder.greaterThanOrEqualTo(getColumnPath(root, DATA_INSERIMENTO), filter.getDataInserimentoDa()));
			}
			if (filter.getDataInserimentoA() != null) {
				predicates.add(cbuilder.lessThan(getColumnPath(root, DATA_INSERIMENTO), getLaterDate(filter.getDataInserimentoA())));
			}
			if (filter.getDataUltimaVariazioneDa() != null) {
				predicates.add(cbuilder.greaterThanOrEqualTo(getColumnPath(root, DATA_ULTIMA_VARIAZIONE), filter.getDataUltimaVariazioneDa()));
			}
			if (filter.getDataUltimaVariazioneA() != null) {
				predicates.add(cbuilder.lessThan(getColumnPath(root, DATA_ULTIMA_VARIAZIONE), getLaterDate(filter.getDataUltimaVariazioneA())));
			}
			if (filter.getUtenteUltimaVariazione() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, UTENTE_ULTIMA_VARIAZIONE), filter.getUtenteUltimaVariazione()));
			}
			if (filter.getCodEsito() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, CODICE_ESITO), filter.getCodEsito()));
			}
			if (filter.getDetEsito() != null) {
				predicates.add(cbuilder.equal(getColumnPath(root, DETTAGLIO_ESITO), filter.getDetEsito()));
			}
			if (filter.getCodiceDenominazioneMittente() != null) {
				Predicate prdCodMittente = cbuilder.like(cbuilder.lower( getColumnPath(root, COD_ID_UNIVOCO_MITTENTE) ), "%" + filter.getCodiceDenominazioneMittente().toLowerCase() + "%");
				Predicate prdDenominazioneMittente = cbuilder.like(cbuilder.lower( getColumnPath(root, DENOMINAZIONE_MITTENTE) ), "%" + filter.getCodiceDenominazioneMittente().toLowerCase() + "%");
				predicates.add(cbuilder.or(prdCodMittente,prdDenominazioneMittente));
			}
		}

		return predicates;
	}

	static public Predicate buildUserRestrictions(String codUtente, CriteriaBuilder cbuilder, Root<EpaypaTFlussoLight> root) {
		if (codUtente != null) {
			List<Predicate> orPredicates = new ArrayList<> ();
			orPredicates.add(cbuilder.equal(getColumnPath(root, UTENTE_ULTIMA_VARIAZIONE), codUtente));
			orPredicates.add(cbuilder.notEqual(getColumnPath(root, STATO_FLUSSO), StatoFlussoEnum.BOZZA.getId()));
			return cbuilder.or(orPredicates.toArray( new Predicate[0] ));
		}
		return null;
	}

	static public Predicate buildUserRestrictionsF ( String codUtente, CriteriaBuilder cbuilder, Root<EpaypaTFlussoFilter> root ) {
		if ( codUtente != null ) {
			List<Predicate> orPredicates = new ArrayList<> ();
			orPredicates.add ( cbuilder.equal ( getColumnPathF ( root, UTENTE_ULTIMA_VARIAZIONE ), codUtente ) );
			orPredicates.add ( cbuilder.notEqual ( getColumnPathF ( root, STATO_FLUSSO ), StatoFlussoEnum.BOZZA.getId () ) );
			return cbuilder.or ( orPredicates.toArray ( new Predicate[0] ) );
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	static public Path getColumnPath(Root<EpaypaTFlussoLight> root, ColumnNameFlussoEnum columnEnum) {
		Path columnPath = null;
		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_FLUSSO:
				columnPath = root.get("idFlusso");
				break;
			case ID_MESSAGGIO:
				columnPath = root.get("idMessaggio");
				break;
			case TIPO_FLUSSO:
				columnPath = root.get("epaypaDTipoFlusso").get("idTipoFlusso");
				break;
			case DESCRIZIONE_TIPO_FLUSSO:
				columnPath = root.get("epaypaDTipoFlusso").get("descrizione");
				break;
			case STATO_FLUSSO:
				columnPath = root.get("epaypaDStatoFlusso").get("idStatoFlusso");
				break;
			case DESCRIZIONE_STATO_FLUSSO:
				columnPath = root.get("epaypaDStatoFlusso").get("descrizione");
				break;
			case ID_ENTE:
				columnPath = root.get("epaypaTEnte").get("idEnte");
				break;
			case ID_CODICE_VERSAMENTO:
				columnPath = root.get("epaypaTCodiceVersamento").get("idCodiceVersamento");
				break;
			case NUMERO_ELEMENTI:
				columnPath = root.get("numeroElementi");
				break;
			case IMPORTO_TOTALE:
				columnPath = root.get("importoTotale");
				break;
			case PAGAMENTI_SPONTANEI:
				columnPath = root.get("pagamentiSpontanei");
				break;
			case DATA_INSERIMENTO:
				columnPath = root.get("dtInserimento");
				break;
			case DATA_ULTIMA_VARIAZIONE:
				columnPath = root.get("dtUltimaVariazione");
				break;
			case UTENTE_ULTIMA_VARIAZIONE:
				columnPath = root.get("utenteUltimaVariazione");
				break;
			case CODICE_ESITO:
				columnPath = root.get("codEsito");
				break;
			case DETTAGLIO_ESITO:
				columnPath = root.get("dettaglioEsito");
				break;
			case COD_ID_UNIVOCO_MITTENTE:
				columnPath = root.get("epaypaTRendicontazione").get("codIdUnivocoMittente");
				break;
			case DATA_REGOLAMENTO:
				columnPath = root.get("epaypaTRendicontazione").get("dtRegolamento");
				break;
			case DENOMINAZIONE_MITTENTE:
				columnPath = root.get("epaypaTRendicontazione").get("denominazioneMittente");
				break;
			case TIPO_ID_MITTENTE:
				columnPath = root.get("epaypaTRendicontazione").get("tipoIdMittente");
				break;
			case IUV:
				columnPath = root.join ( "epaypaTPosizioneDebitoriaLight" ).get ( "iuv" );
				break;
			case C_FISC:
				columnPath = root.join ( "epaypaTPosizioneDebitoriaLight" ).get ( "epaypaTSoggettoDebitore" ).get ( "idUnivocoFiscale" );
				break;
			case POSIZIONE_DEBITORIA_ESTERNA:
				columnPath = root.join ( "epaypaTPosizioneDebitoriaLight" ).get ( "idPosizioneDebitoriaEst" );
				break;
			default:
				break;
			}
		}
		return columnPath;
	}

	@SuppressWarnings ( "unchecked" )
	public static List<Predicate> buildPredicatesF ( FlussoLightFilterDto filter, CriteriaBuilder cbuilder, Root<EpaypaTFlussoFilter> root ) {
		List<Predicate> predicates = null;

		if ( filter != null ) {
			predicates = new ArrayList<> ();
			if ( null != filter.getIuv () ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, IUV_AGGIORNAMENTO_POSIZIONI_DEBITORIE ), filter.getIuv () ) );
			}
			if ( null != filter.getCfisc () ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, C_FISC ), filter.getCfisc () ) );
			}
			if (null != filter.getIdPosizioneDebitoriaEsterna ()) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, POSIZIONE_DEBITORIA_ESTERNA ), filter.getIdPosizioneDebitoriaEsterna () ) );
			}
			if ( filter.getIdMessaggio () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, ID_MESSAGGIO ), filter.getIdMessaggio () ) );
			}
			if ( filter.getTipoFlusso () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, TIPO_FLUSSO ), filter.getTipoFlusso ().getId () ) );
			}
			if ( filter.getStatoFlusso () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, STATO_FLUSSO ), filter.getStatoFlusso ().getId () ) );
			}
			if ( filter.getIdEnte () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, ID_ENTE ), filter.getIdEnte () ) );
			}
			if ( filter.getIdCodVersamentoList () != null ) {
				predicates.add ( getColumnPathF ( root, ID_CODICE_VERSAMENTO ).in ( filter.getIdCodVersamentoList () ) );
			}
			if ( filter.getPagamentiSpontanei () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, PAGAMENTI_SPONTANEI ), filter.getPagamentiSpontanei () ) );
			}
			if ( filter.getDataInserimentoDa () != null ) {
				predicates.add ( cbuilder.greaterThanOrEqualTo ( getColumnPathF ( root, DATA_INSERIMENTO ), filter.getDataInserimentoDa () ) );
			}
			if ( filter.getDataInserimentoA () != null ) {
				predicates.add ( cbuilder.lessThan ( getColumnPathF ( root, DATA_INSERIMENTO ), getLaterDate ( filter.getDataInserimentoA () ) ) );
			}
			if ( filter.getDataUltimaVariazioneDa () != null ) {
				predicates.add ( cbuilder.greaterThanOrEqualTo ( getColumnPathF ( root, DATA_ULTIMA_VARIAZIONE ), filter.getDataUltimaVariazioneDa () ) );
			}
			if ( filter.getDataUltimaVariazioneA () != null ) {
				predicates.add ( cbuilder.lessThan ( getColumnPathF ( root, DATA_ULTIMA_VARIAZIONE ), getLaterDate ( filter.getDataUltimaVariazioneA () ) ) );
			}
			if ( filter.getUtenteUltimaVariazione () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, UTENTE_ULTIMA_VARIAZIONE ), filter.getUtenteUltimaVariazione () ) );
			}
			if ( filter.getCodEsito () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, CODICE_ESITO ), filter.getCodEsito () ) );
			}
			if ( filter.getDetEsito () != null ) {
				predicates.add ( cbuilder.equal ( getColumnPathF ( root, DETTAGLIO_ESITO ), filter.getDetEsito () ) );
			}
			if ( filter.getCodiceDenominazioneMittente () != null ) {
				Predicate prdCodMittente = cbuilder.like ( cbuilder.lower ( getColumnPathF ( root, COD_ID_UNIVOCO_MITTENTE ) ),
								"%" + filter.getCodiceDenominazioneMittente ().toLowerCase () + "%" );
				Predicate prdDenominazioneMittente = cbuilder.like ( cbuilder.lower ( getColumnPathF ( root, DENOMINAZIONE_MITTENTE ) ),
								"%" + filter.getCodiceDenominazioneMittente ().toLowerCase () + "%" );
				predicates.add ( cbuilder.or ( prdCodMittente, prdDenominazioneMittente ) );
			}
		}
		return predicates;
	}

	@SuppressWarnings ( "rawtypes" )
	static public Path getColumnPathF ( Root<EpaypaTFlussoFilter> root, ColumnNameFlussoEnum columnEnum ) {
		Path columnPath = null;
		if ( columnEnum != null ) {
			switch ( columnEnum ) {
			case ID_FLUSSO:
				columnPath = root.get ( "idFlusso" );
				break;
			case ID_MESSAGGIO:
				columnPath = root.get ( "idMessaggio" );
				break;
			case TIPO_FLUSSO:
				columnPath = root.get ( "epaypaDTipoFlusso" ).get ( "idTipoFlusso" );
				break;
			case DESCRIZIONE_TIPO_FLUSSO:
				columnPath = root.get ( "epaypaDTipoFlusso" ).get ( "descrizione" );
				break;
			case STATO_FLUSSO:
				columnPath = root.get ( "epaypaDStatoFlusso" ).get ( "idStatoFlusso" );
				break;
			case DESCRIZIONE_STATO_FLUSSO:
				columnPath = root.get ( "epaypaDStatoFlusso" ).get ( "descrizione" );
				break;
			case ID_ENTE:
				columnPath = root.get ( "epaypaTEnte" ).get ( "idEnte" );
				break;
			case ID_CODICE_VERSAMENTO:
				columnPath = root.get ( "epaypaTCodiceVersamento" ).get ( "idCodiceVersamento" );
				break;
			case NUMERO_ELEMENTI:
				columnPath = root.get ( "numeroElementi" );
				break;
			case IMPORTO_TOTALE:
				columnPath = root.get ( "importoTotale" );
				break;
			case PAGAMENTI_SPONTANEI:
				columnPath = root.get ( "pagamentiSpontanei" );
				break;
			case DATA_INSERIMENTO:
				columnPath = root.get ( "dtInserimento" );
				break;
			case DATA_ULTIMA_VARIAZIONE:
				columnPath = root.get ( "dtUltimaVariazione" );
				break;
			case UTENTE_ULTIMA_VARIAZIONE:
				columnPath = root.get ( "utenteUltimaVariazione" );
				break;
			case CODICE_ESITO:
				columnPath = root.get ( "codEsito" );
				break;
			case DETTAGLIO_ESITO:
				columnPath = root.get ( "dettaglioEsito" );
				break;
			case COD_ID_UNIVOCO_MITTENTE:
				columnPath = root.get ( "epaypaTRendicontazione" ).get ( "codIdUnivocoMittente" );
				break;
			case DATA_REGOLAMENTO:
				columnPath = root.get ( "epaypaTRendicontazione" ).get ( "dtRegolamento" );
				break;
			case DENOMINAZIONE_MITTENTE:
				columnPath = root.get ( "epaypaTRendicontazione" ).get ( "denominazioneMittente" );
				break;
			case TIPO_ID_MITTENTE:
				columnPath = root.get ( "epaypaTRendicontazione" ).get ( "tipoIdMittente" );
				break;
			case IUV:
				columnPath = root.join ( "epaypaTPosizioneDebitoriaLight" ).get ( "iuv" );
				break;
			case IUV_AGGIORNAMENTO_POSIZIONI_DEBITORIE:
				columnPath = root
								.join ( "epaypaTAggPosizioneDebitoriaFilter" )
								.join ( "epaypaTPosizioneDebitoriaFilter" )
								.join ( "epaypaTSoggettoDebitore" )
								.getParentPath ().get ( "iuv" );
				break;
			case C_FISC:
				columnPath = root
								.join ( "epaypaTAggPosizioneDebitoriaFilter" )
								.join ( "epaypaTPosizioneDebitoriaFilter" )
								.join ( "epaypaTSoggettoDebitore" )
								.get ( "idUnivocoFiscale" );
				break;
			case POSIZIONE_DEBITORIA_ESTERNA:
				columnPath = root
								.join ( "epaypaTAggPosizioneDebitoriaFilter" )
								.join ( "epaypaTPosizioneDebitoriaFilter" )
								.join ( "epaypaTSoggettoDebitore" )
								.getParentPath ().get ( "idPosizioneDebitoriaEst" );
				break;
			default:
				break;
			}
		}
		return columnPath;
	}
}
