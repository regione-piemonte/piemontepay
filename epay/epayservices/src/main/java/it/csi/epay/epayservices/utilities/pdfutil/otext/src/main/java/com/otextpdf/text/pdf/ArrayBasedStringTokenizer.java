/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArrayBasedStringTokenizer {

	private final Pattern regex;

	public ArrayBasedStringTokenizer ( String[] tokens ) {
		this.regex = Pattern.compile ( getRegexFromTokens ( tokens ) );
	}

	public String[] tokenize ( String text ) {

		List<String> tokens = new ArrayList<> ();

		Matcher matcher = regex.matcher ( text );

		int endIndexOfpreviousMatch = 0;

		while ( matcher.find () ) {

			int startIndexOfMatch = matcher.start ();

			String previousToken = text.substring ( endIndexOfpreviousMatch, startIndexOfMatch );

			if ( !previousToken.isEmpty () ) {
				tokens.add ( previousToken );
			}

			String currentMatch = matcher.group ();

			tokens.add ( currentMatch );

			endIndexOfpreviousMatch = matcher.end ();

		}

		String tail = text.substring ( endIndexOfpreviousMatch );

		if ( !tail.isEmpty () ) {
			tokens.add ( tail );
		}

		return tokens.toArray ( new String[0] );

	}

	private String getRegexFromTokens ( String[] tokens ) {
		StringBuilder regexBuilder = new StringBuilder ( 100 );

		for ( String token : tokens ) {
			regexBuilder.append ( "(" ).append ( token ).append ( ")|" );
		}

		regexBuilder.setLength ( regexBuilder.length () - 1 );

		return regexBuilder.toString ();
	}

}
