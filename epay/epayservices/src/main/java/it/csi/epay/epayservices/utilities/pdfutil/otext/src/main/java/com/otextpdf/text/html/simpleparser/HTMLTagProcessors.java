/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class HTMLTagProcessors extends HashMap<String, HTMLTagProcessor> {

	public static final HTMLTagProcessor EM_STRONG_STRIKE_SUP_SUP = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) {
			tag = mapTag ( tag );
			attrs.put ( tag, null );
			worker.updateChain ( tag, attrs );
		}

		public void endElement ( HTMLWorker worker, String tag ) {
			tag = mapTag ( tag );
			worker.updateChain ( tag );
		}

		private String mapTag ( String tag ) {
			if ( HtmlTags.EM.equalsIgnoreCase ( tag ) )
				return HtmlTags.I;
			if ( HtmlTags.STRONG.equalsIgnoreCase ( tag ) )
				return HtmlTags.B;
			if ( HtmlTags.STRIKE.equalsIgnoreCase ( tag ) )
				return HtmlTags.S;
			return tag;
		}

	};

	public static final HTMLTagProcessor A = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) {
			worker.updateChain ( tag, attrs );
			worker.flushContent ();
		}

		public void endElement ( HTMLWorker worker, String tag ) {
			worker.processLink ();
			worker.updateChain ( tag );
		}
	};

	public static final HTMLTagProcessor BR = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) {
			worker.newLine ();
		}

		public void endElement ( HTMLWorker worker, String tag ) {
		}

	};

	public static final HTMLTagProcessor UL_OL = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingLI () )
				worker.endElement ( HtmlTags.LI );
			worker.setSkipText ( true );
			worker.updateChain ( tag, attrs );
			worker.pushToStack ( worker.createList ( tag ) );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingLI () )
				worker.endElement ( HtmlTags.LI );
			worker.setSkipText ( false );
			worker.updateChain ( tag );
			worker.processList ();
		}

	};

	public static final HTMLTagProcessor HR = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			worker.pushToStack ( worker.createLineSeparator ( attrs ) );
		}

		public void endElement ( HTMLWorker worker, String tag ) {
		}

	};

	public static final HTMLTagProcessor SPAN = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) {
			worker.updateChain ( tag, attrs );
		}

		public void endElement ( HTMLWorker worker, String tag ) {
			worker.updateChain ( tag );
		}

	};

	public static final HTMLTagProcessor H = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			if ( !attrs.containsKey ( HtmlTags.SIZE ) ) {
				int v = 7 - Integer.parseInt ( tag.substring ( 1 ) );
				attrs.put ( HtmlTags.SIZE, Integer.toString ( v ) );
			}
			worker.updateChain ( tag, attrs );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			worker.updateChain ( tag );
		}

	};

	public static final HTMLTagProcessor LI = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingLI () )
				worker.endElement ( tag );
			worker.setSkipText ( false );
			worker.setPendingLI ( true );
			worker.updateChain ( tag, attrs );
			worker.pushToStack ( worker.createListItem () );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			worker.setPendingLI ( false );
			worker.setSkipText ( true );
			worker.updateChain ( tag );
			worker.processListItem ();
		}

	};

	public static final HTMLTagProcessor PRE = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			if ( !attrs.containsKey ( HtmlTags.FACE ) ) {
				attrs.put ( HtmlTags.FACE, "Courier" );
			}
			worker.updateChain ( tag, attrs );
			worker.setInsidePRE ( true );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			worker.updateChain ( tag );
			worker.setInsidePRE ( false );
		}

	};

	public static final HTMLTagProcessor DIV = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			worker.updateChain ( tag, attrs );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			worker.updateChain ( tag );
		}

	};

	public static final HTMLTagProcessor TABLE = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			TableWrapper table = new TableWrapper ( attrs );
			worker.pushToStack ( table );
			worker.pushTableState ();
			worker.setPendingTD ( false );
			worker.setPendingTR ( false );
			worker.setSkipText ( true );
			// Table alignment should not affect children elements, thus remove
			attrs.remove ( HtmlTags.ALIGN );
			// In case this is a nested table reset colspan and rowspan
			attrs.put ( HtmlTags.COLSPAN, "1" );
			attrs.put ( HtmlTags.ROWSPAN, "1" );
			worker.updateChain ( tag, attrs );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingTR () )
				worker.endElement ( HtmlTags.TR );
			worker.updateChain ( tag );
			worker.processTable ();
			worker.popTableState ();
			worker.setSkipText ( false );
		}

	};

	public static final HTMLTagProcessor TR = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingTR () )
				worker.endElement ( tag );
			worker.setSkipText ( true );
			worker.setPendingTR ( true );
			worker.updateChain ( tag, attrs );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingTD () )
				worker.endElement ( HtmlTags.TD );
			worker.setPendingTR ( false );
			worker.updateChain ( tag );
			worker.processRow ();
			worker.setSkipText ( true );
		}

	};

	public static final HTMLTagProcessor TD = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException {
			worker.carriageReturn ();
			if ( worker.isPendingTD () )
				worker.endElement ( tag );
			worker.setSkipText ( false );
			worker.setPendingTD ( true );
			worker.updateChain ( HtmlTags.TD, attrs );
			worker.pushToStack ( worker.createCell ( tag ) );
		}

		public void endElement ( HTMLWorker worker, String tag ) throws DocumentException {
			worker.carriageReturn ();
			worker.setPendingTD ( false );
			worker.updateChain ( HtmlTags.TD );
			worker.setSkipText ( true );
		}

	};

	public static final HTMLTagProcessor IMG = new HTMLTagProcessor () {

		public void startElement ( HTMLWorker worker, String tag, Map<String, String> attrs ) throws DocumentException, IOException {
			worker.updateChain ( tag, attrs );
			worker.processImage ( worker.createImage ( attrs ), attrs );
			worker.updateChain ( tag );
		}

		public void endElement ( HTMLWorker worker, String tag ) {
		}

	};

	private static final long serialVersionUID = -959260811961222824L;

	public HTMLTagProcessors () {
		super ();
		put ( HtmlTags.A, A );
		put ( HtmlTags.B, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.BODY, DIV );
		put ( HtmlTags.BR, BR );
		put ( HtmlTags.DIV, DIV );
		put ( HtmlTags.EM, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.FONT, SPAN );
		put ( HtmlTags.H1, H );
		put ( HtmlTags.H2, H );
		put ( HtmlTags.H3, H );
		put ( HtmlTags.H4, H );
		put ( HtmlTags.H5, H );
		put ( HtmlTags.H6, H );
		put ( HtmlTags.HR, HR );
		put ( HtmlTags.I, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.IMG, IMG );
		put ( HtmlTags.LI, LI );
		put ( HtmlTags.OL, UL_OL );
		put ( HtmlTags.P, DIV );
		put ( HtmlTags.PRE, PRE );
		put ( HtmlTags.S, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.SPAN, SPAN );
		put ( HtmlTags.STRIKE, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.STRONG, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.SUB, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.SUP, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.TABLE, TABLE );
		put ( HtmlTags.TD, TD );
		put ( HtmlTags.TH, TD );
		put ( HtmlTags.TR, TR );
		put ( HtmlTags.U, EM_STRONG_STRIKE_SUP_SUP );
		put ( HtmlTags.UL, UL_OL );
	}
}
