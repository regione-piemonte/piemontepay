/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.LinkedList;


class PagedChannelRandomAccessSource extends GroupedRandomAccessSource implements RandomAccessSource {

	public static final int DEFAULT_TOTAL_BUFSIZE = 1 << 26;

	public static final int DEFAULT_MAX_OPEN_BUFFERS = 16;

	private final int bufferSize;

	private final FileChannel channel;

	private final MRU<RandomAccessSource> mru;

	public PagedChannelRandomAccessSource ( FileChannel channel ) throws IOException {
		this ( channel, DEFAULT_TOTAL_BUFSIZE, DEFAULT_MAX_OPEN_BUFFERS );
	}

	public PagedChannelRandomAccessSource ( final FileChannel channel, final int totalBufferSize, final int maxOpenBuffers ) throws IOException {
		super ( buildSources ( channel, totalBufferSize / maxOpenBuffers ) );
		this.channel = channel;
		this.bufferSize = totalBufferSize / maxOpenBuffers;
		this.mru = new MRU<> ( maxOpenBuffers );
	}

	private static RandomAccessSource[] buildSources ( final FileChannel channel, final int bufferSize ) throws IOException {
		long size = channel.size ();
		if ( size <= 0 )
			throw new IOException ( "File size must be greater than zero" );

		int bufferCount = (int) ( size / bufferSize ) + ( size % bufferSize == 0 ? 0 : 1 );

		MappedChannelRandomAccessSource[] sources = new MappedChannelRandomAccessSource[bufferCount];
		for ( int i = 0; i < bufferCount; i++ ) {
			long pageOffset = (long) i * bufferSize;
			long pageLength = Math.min ( size - pageOffset, bufferSize );
			sources[i] = new MappedChannelRandomAccessSource ( channel, pageOffset, pageLength );
		}
		return sources;

	}

	@Override

	protected int getStartingSourceIndex ( long offset ) {
		return (int) ( offset / bufferSize );
	}

	@Override

	protected void sourceReleased ( RandomAccessSource source ) throws IOException {
		RandomAccessSource old = mru.enqueue ( source );
		if ( old != null )
			old.close ();
	}

	@Override

	protected void sourceInUse ( RandomAccessSource source ) throws IOException {
		( (MappedChannelRandomAccessSource) source ).open ();
	}

	@Override
	public void close () throws IOException {
		super.close ();
		channel.close ();
	}

	private static class MRU<E> {

		private final int limit;

		private final LinkedList<E> queue = new LinkedList<> ();

		public MRU ( int limit ) {
			this.limit = limit;
		}

		public E enqueue ( E newElement ) {
			if ( !queue.isEmpty () && queue.getFirst () == newElement )
				return null;

			for ( Iterator<E> it = queue.iterator (); it.hasNext (); ) {
				E element = it.next ();
				if ( newElement == element ) {
					it.remove ();
					queue.addFirst ( newElement );
					return null;
				}
			}
			queue.addFirst ( newElement );

			if ( queue.size () > limit )
				return queue.removeLast ();

			return null;
		}
	}
}
