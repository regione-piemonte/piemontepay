/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static it.csi.epay.epayfeapi.util.Constants.REGEX_FIELD_SEPARATOR;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GENERAL__CURRENT_PAGE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GENERAL__ELEMENTS;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GENERAL__FIELDS;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GENERAL__SORT;
import static it.csi.epay.epayfeapi.util.Constants.SORT_ASCENDENT_PREFIX;
import static it.csi.epay.epayfeapi.util.Constants.SORT_DESCENDENT_PREFIX;


public class FieldsUtil {

	/**
	 * Verifica l'inclusione in <code>validFields</code> di tutti i nomi di campi separati da virgola in <code>inputFieldsString</code>.
	 *
	 * @param validFields       elenco di nomi di campi validi
	 * @param inputFieldsString stringa di nomi di campi da analizzare separati da virgola
	 * @param admitsRepetitions <code>true</code> per ammettere nomi di campi ripetuti nella stringa di input
	 * @return <code>true</code> se la validazione ha esito positivo, <code>false</code> se ha esito negativo
	 */
	public static boolean validateFields ( String[] validFields, String inputFieldsString, boolean admitsRepetitions ) {
		if ( inputFieldsString != null ) {
			var inputFieldList = Arrays.asList ( inputFieldsString.trim ().split ( REGEX_FIELD_SEPARATOR ) );
			if ( validFields != null ) {
				var validFieldSet = new HashSet<> ( Arrays.asList ( validFields ) );
				var inputFieldSet = new HashSet<> ( inputFieldList );
				if ( validFieldSet.containsAll ( inputFieldSet ) )
					if ( admitsRepetitions ) {
						return true;
					} else {
						return inputFieldSet.size () == inputFieldList.size ();
					}
				else {
					return false;
				}
			} else {
				// in assenza di valori validi definiti, qualunque valore nella stringa di input non puo essere valido, quindi ritorna false
				return false;
			}
		} else {
			// in assenza della stringa, essendo opzionale, ritorna esito della validazione true
			return true;
		}
	}

	public static boolean validateFields ( String[] validFields, String inputFieldsString ) {
		return validateFields ( validFields, inputFieldsString, false );
	}

	/**
	 * Verifica l'inclusione in <code>sortableFields</code> di tutti i valori separati da virgola in <code>inputSortString</code>.
	 * <p>
	 * Prima di verificare l'inclusione, i valori in <code>sortableFields</code> vengonmo raddoppiati:
	 * <ul>
	 * <li>da 0 a n-1, i valori vengono prefissati con "-"</li>
	 * <li>da n a 2*n - 1, i valori vengono prefissati con "+"</li>
	 * </ul>
	 *
	 * @param sortableFields    elenco di nomi di campi per i quali risulta applicabile l'ordinamento
	 * @param inputSortString   stringa di nomi di campi, con "+" o "-" come prefisso, separati da virgola, di cui si richiede l'ordinamento
	 * @param admitsRepetitions <code>true</code> per ammettere nomi di campi ripetuti ripetuti nella stringa di input
	 * @return <code>true</code> se la validazione ha esito positivo, <code>false</code> se ha esito negativo
	 */
	public static boolean validateSort ( String[] sortableFields, String inputSortString, boolean admitsRepetitions ) {
		if ( inputSortString != null ) {
			if ( sortableFields != null ) {
				// costruisce l'insieme di tutti i valori validi premettendo "+" e "-" ai valori in sortableFields
				return validateFields ( sortableHelper ( sortableFields ), inputSortString, admitsRepetitions );
			} else {
				// in assenza di valori ordinabili definiti, qualunque valore nella stringa di input non va bene, quindi ritorna false
				return false;
			}
		} else {
			// in assenza della stringa, essendo opzionale, ritorna esito della valdiazione true
			return true;
		}
	}

	private static String[] sortableHelper ( String[] sortableFields ) {
		var n = sortableFields.length;
		var prefixedSortableFields = new String[n * 2];
		for ( var i = 0; i < n; i++ ) {
			prefixedSortableFields[i] = SORT_DESCENDENT_PREFIX + sortableFields[i];
		}
		for ( int i = n, j = 0; i < 2 * n; i++, j++ ) {
			prefixedSortableFields[i] = SORT_ASCENDENT_PREFIX + sortableFields[j];
		}
		return prefixedSortableFields;
	}

	public static boolean validateSort ( String[] sortableFields, String inputSortString ) {
		return validateSort ( sortableFields, inputSortString, false );
	}

	/**
	 * Ottiene da una stringa di nomi di campi separati da virgola, l'insieme dei nomi di quei campi.
	 *
	 * @param validFields       elenco di nomi di campi validi
	 * @param inputFieldsString stringa di nomi di campi da analizzare separati da virgola
	 * @return il <code>Set<String></code> dei nomi di campi validi estratto da <code>inputFieldsString</code>, se tutti i nomi di campi separati da virgola in
	 * <code>inputFieldsString</code> appartengono all'insieme dei nomi di campi validi <code>validFields</code>, insieme vuoto diversamente
	 */
	public static Set<String> getInputFieldSet ( String[] validFields, String inputFieldsString ) {
		Set<String> emptyFieldSet = new HashSet<> ();
		var validFieldSet = new HashSet<> ( Arrays.asList ( validFields ) );
		if ( inputFieldsString != null ) {
			var inputFieldList = Arrays.asList ( inputFieldsString.trim ().split ( REGEX_FIELD_SEPARATOR ) );
			var inputFieldSet = new HashSet<> ( inputFieldList );
			if ( validFieldSet.containsAll ( inputFieldSet ) ) {
				return inputFieldSet;
			} else {
				return emptyFieldSet;
			}
		} else {
			// in assenza della stringa, essendo opzionale, ritorna l'insieme di tutti i nomi di campi validi
			return validFieldSet;
		}
	}

	private static List<String> getInputFieldList ( String[] validFields, String inputFieldsString ) {
		List<String> emptyFieldList = new ArrayList<> ();
		var validFieldList = Arrays.asList ( validFields );
		if ( inputFieldsString != null ) {
			var inputFieldList = Arrays.asList ( inputFieldsString.trim ().split ( REGEX_FIELD_SEPARATOR ) );
			var validFieldSet = new HashSet<> ( validFieldList );
			var inputFieldSet = new HashSet<> ( inputFieldList );
			if ( validFieldSet.containsAll ( inputFieldSet ) ) {
				return inputFieldList;
			} else {
				return emptyFieldList;
			}
		} else {
			// in assenza della stringa, essendo opzionale, ritorna l'insieme di tutti i nomi di campi validi
			return validFieldList;
		}
	}

	/**
	 * Costruisce la stringa che rappresenta l'ORDER BY sql
	 *
	 * @param sortableFields  elenco di nomi di campi per i quali risulta applicabile l'ordinamento
	 * @param inputSortString stringa di nomi di campi, con "+" o "-" come prefisso, separati da virgola, di cui si richiede l'ordinamento
	 * @param fieldColumnMap  mapping tra i nomi dei campi del servizio e i nomi delle property dell'entity
	 * @return la stringa ORDER BY campo1 ASC, campo2 DESC, ecc.
	 */
	public static String buildOrderBy ( String[] sortableFields, String inputSortString, Map<String, String> fieldColumnMap ) {
		// trasforma la stringa di campi ordinabili in lista di campi ordinabili
		List<String> inputSortList = new ArrayList<> ();
		if ( inputSortString != null ) {
			if ( sortableFields != null ) {
				inputSortList = getInputFieldList ( sortableHelper ( sortableFields ), inputSortString );
			}
		}

		// costruisce l'sql dell'ORDER BY
		StringBuilder orderBy = new StringBuilder ();
		if ( !inputSortList.isEmpty () ) {
			orderBy.append ( "order by " );
			for ( int i = 0, n = inputSortList.size (); i < n; i++ ) {
				var sort = inputSortList.get ( i );
				orderBy.append ( fieldColumnMap.get ( sort.substring ( 1 ) ) );
				switch ( sort.charAt ( 0 ) ) {
				case SORT_ASCENDENT_PREFIX:
					orderBy.append ( " asc" );
					break;
				case SORT_DESCENDENT_PREFIX:
					orderBy.append ( " desc" );
					break;
				}
				if ( i < n - 1 ) {
					orderBy.append ( ", " );
				}
			}
			orderBy.append ( " " );
		}
		return orderBy.toString ();
	}

	public static List<String> validateCommonFields ( List<String> notValids, Integer currentPage, Integer elements, String sort, String fields,
					String[] sortableFields, String[] validFields ) {
		if ( currentPage != null && currentPage < 1 ) {
			notValids.add ( SERVICE_FIELDS_GENERAL__CURRENT_PAGE );
		}
		if ( elements != null && elements < 1 ) {
			notValids.add ( SERVICE_FIELDS_GENERAL__ELEMENTS );
		}
		if ( !FieldsUtil.validateSort ( sortableFields, sort ) ) {
			notValids.add ( SERVICE_FIELDS_GENERAL__SORT );
		}
		if ( !FieldsUtil.validateFields ( validFields, fields ) ) {
			notValids.add ( SERVICE_FIELDS_GENERAL__FIELDS );
		}
		return notValids;
	}
}
