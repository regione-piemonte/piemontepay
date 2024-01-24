/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;


class GroupedRandomAccessSource implements RandomAccessSource {

	private final SourceEntry[] sources;

	private final long size;

	private SourceEntry currentSourceEntry;

	public GroupedRandomAccessSource ( RandomAccessSource[] sources ) throws IOException {
		this.sources = new SourceEntry[sources.length];

		long totalSize = 0;
		for ( int i = 0; i < sources.length; i++ ) {
			this.sources[i] = new SourceEntry ( i, sources[i], totalSize );
			totalSize += sources[i].length ();
		}
		size = totalSize;
		currentSourceEntry = this.sources[sources.length - 1];
		sourceInUse ( currentSourceEntry.source );
	}

	protected int getStartingSourceIndex ( long offset ) {
		if ( offset >= currentSourceEntry.firstByte )
			return currentSourceEntry.index;

		return 0;
	}

	private SourceEntry getSourceEntryForOffset ( long offset ) throws IOException {
		if ( offset >= size )
			return null;

		if ( offset >= currentSourceEntry.firstByte && offset <= currentSourceEntry.lastByte )
			return currentSourceEntry;

		sourceReleased ( currentSourceEntry.source );

		int startAt = getStartingSourceIndex ( offset );

		for ( int i = startAt; i < sources.length; i++ ) {
			if ( offset >= sources[i].firstByte && offset <= sources[i].lastByte ) {
				currentSourceEntry = sources[i];
				sourceInUse ( currentSourceEntry.source );
				return currentSourceEntry;
			}
		}

		return null;

	}

	protected void sourceReleased ( RandomAccessSource source ) throws IOException {
	}

	protected void sourceInUse ( RandomAccessSource source ) throws IOException {
	}

	public int get ( long position ) throws IOException {
		SourceEntry entry = getSourceEntryForOffset ( position );

		if ( entry == null )
			return -1;

		return entry.source.get ( entry.offsetN ( position ) );

	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		SourceEntry entry = getSourceEntryForOffset ( position );

		if ( entry == null )
			return -1;

		long offN = entry.offsetN ( position );

		int remaining = len;

		while ( remaining > 0 ) {
			if ( entry == null ) // we have run out of data to read from
				break;
			if ( offN > entry.source.length () )
				break;

			int count = entry.source.get ( offN, bytes, off, remaining );
			if ( count == -1 )
				break;

			off += count;
			position += count;
			remaining -= count;

			offN = 0;
			entry = getSourceEntryForOffset ( position );
		}
		return remaining == len ? -1 : len - remaining;
	}

	public long length () {
		return size;
	}

	public void close () throws IOException {
		for ( SourceEntry entry : sources ) {
			entry.source.close ();
		}
	}

	private static class SourceEntry {

		final RandomAccessSource source;

		final long firstByte;

		final long lastByte;

		final int index;

		public SourceEntry ( int index, RandomAccessSource source, long offset ) {
			this.index = index;
			this.source = source;
			this.firstByte = offset;
			this.lastByte = offset + source.length () - 1;
		}

		public long offsetN ( long absoluteOffset ) {
			return absoluteOffset - firstByte;
		}
	}
}
