/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LocationTextExtractionStrategy implements TextExtractionStrategy {

	static boolean DUMP_STATE = false;

	private final List<TextChunk> locationalResult = new ArrayList<> ();

	public LocationTextExtractionStrategy () {
	}

	public void beginTextBlock () {
	}

	public void endTextBlock () {
	}

	private boolean startsWithSpace ( String str ) {
		if ( str.isEmpty () )
			return false;
		return str.charAt ( 0 ) == ' ';
	}

	private boolean endsWithSpace ( String str ) {
		if ( str.isEmpty () )
			return false;
		return str.charAt ( str.length () - 1 ) == ' ';
	}

	private List<TextChunk> filterTextChunks ( List<TextChunk> textChunks, TextChunkFilter filter ) {
		if ( filter == null )
			return textChunks;

		List<TextChunk> filtered = new ArrayList<> ();
		for ( TextChunk textChunk : textChunks ) {
			if ( filter.accept ( textChunk ) )
				filtered.add ( textChunk );
		}
		return filtered;
	}

	protected boolean isChunkAtWordBoundary ( TextChunk chunk, TextChunk previousChunk ) {
		float dist = chunk.distanceFromEndOf ( previousChunk );

		return dist < -chunk.getCharSpaceWidth () || dist > chunk.getCharSpaceWidth () / 2.0f;
	}

	public String getResultantText ( TextChunkFilter chunkFilter ) {
		if ( DUMP_STATE )
			dumpState ();

		List<TextChunk> filteredTextChunks = filterTextChunks ( locationalResult, chunkFilter );
		Collections.sort ( filteredTextChunks );

		StringBuilder sb = new StringBuilder ();
		TextChunk lastChunk = null;
		for ( TextChunk chunk : filteredTextChunks ) {

			if ( lastChunk == null ) {
				sb.append ( chunk.text );
			} else {
				if ( chunk.sameLine ( lastChunk ) ) {
					// we only insert a blank space if the trailing character of the previous string wasn't a space, and the leading character of the current string isn't a space
					if ( isChunkAtWordBoundary ( chunk, lastChunk ) && !startsWithSpace ( chunk.text ) && !endsWithSpace ( lastChunk.text ) )
						sb.append ( ' ' );

					sb.append ( chunk.text );
				} else {
					sb.append ( '\n' );
					sb.append ( chunk.text );
				}
			}
			lastChunk = chunk;
		}

		return sb.toString ();
	}

	public String getResultantText () {

		return getResultantText ( null );

	}

	private void dumpState () {
		for ( TextChunk location : locationalResult ) {
			location.printDiagnostics ();

			System.out.println ();
		}

	}

	public void renderText ( TextRenderInfo renderInfo ) {
		LineSegment segment = renderInfo.getBaseline ();
		if ( renderInfo.getRise () != 0 ) { // remove the rise from the baseline - we do this because the text from a super/subscript render operations should probably be considered as part of the baseline of the text the super/sub is relative to
			Matrix riseOffsetTransform = new Matrix ( 0, -renderInfo.getRise () );
			segment = segment.transformBy ( riseOffsetTransform );
		}
		TextChunk location = new TextChunk ( renderInfo.getText (), segment.getStartPoint (), segment.getEndPoint (), renderInfo.getSingleSpaceWidth () );
		locationalResult.add ( location );
	}

	public void renderImage ( ImageRenderInfo renderInfo ) {
		// do nothing
	}

	public interface TextChunkFilter {

		boolean accept ( TextChunk textChunk );
	}


	public static class TextChunk implements Comparable<TextChunk> {

		private final String text;

		private final Vector startLocation;

		private final Vector endLocation;

		private final int orientationMagnitude;

		private final int distPerpendicular;

		private final float distParallelStart;

		private final float distParallelEnd;

		private final float charSpaceWidth;

		public TextChunk ( String string, Vector startLocation, Vector endLocation, float charSpaceWidth ) {
			this.text = string;
			this.startLocation = startLocation;
			this.endLocation = endLocation;
			this.charSpaceWidth = charSpaceWidth;

			Vector oVector = endLocation.subtract ( startLocation );
			if ( oVector.length () == 0 ) {
				oVector = new Vector ( 1, 0, 0 );
			}
			Vector orientationVector = oVector.normalize ();
			orientationMagnitude = (int) ( Math.atan2 ( orientationVector.get ( Vector.I2 ), orientationVector.get ( Vector.I1 ) ) * 1000 );

			Vector origin = new Vector ( 0, 0, 1 );
			distPerpendicular = (int) ( startLocation.subtract ( origin ) ).cross ( orientationVector ).get ( Vector.I3 );

			distParallelStart = orientationVector.dot ( startLocation );
			distParallelEnd = orientationVector.dot ( endLocation );
		}

		private static int compareInts ( int int1, int int2 ) {
			return Integer.compare ( int1, int2 );
		}

		public String getText () {
			return text;
		}

		public float getCharSpaceWidth () {
			return charSpaceWidth;
		}

		private void printDiagnostics () {
			System.out.println ( "Text (@" + startLocation + " -> " + endLocation + "): " + text );
			System.out.println ( "orientationMagnitude: " + orientationMagnitude );
			System.out.println ( "distPerpendicular: " + distPerpendicular );
			System.out.println ( "distParallel: " + distParallelStart );
		}

		public boolean sameLine ( TextChunk as ) {
			if ( orientationMagnitude != as.orientationMagnitude )
				return false;
			return distPerpendicular == as.distPerpendicular;
		}

		public float distanceFromEndOf ( TextChunk other ) {
			return distParallelStart - other.distParallelEnd;
		}

		public int compareTo ( TextChunk rhs ) {
			if ( this == rhs )
				return 0; // not really needed, but just in case

			int rslt;
			rslt = compareInts ( orientationMagnitude, rhs.orientationMagnitude );
			if ( rslt != 0 )
				return rslt;

			rslt = compareInts ( distPerpendicular, rhs.distPerpendicular );
			if ( rslt != 0 )
				return rslt;

			return Float.compare ( distParallelStart, rhs.distParallelStart );
		}

	}
}
