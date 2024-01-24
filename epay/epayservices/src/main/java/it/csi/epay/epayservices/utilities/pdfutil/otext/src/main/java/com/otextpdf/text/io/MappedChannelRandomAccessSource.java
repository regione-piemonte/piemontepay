/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;


class MappedChannelRandomAccessSource implements RandomAccessSource {

	private final FileChannel channel;

	private final long offset;

	private final long length;

	private ByteBufferRandomAccessSource source;

	public MappedChannelRandomAccessSource ( FileChannel channel, long offset, long length ) {
		if ( offset < 0 )
			throw new IllegalArgumentException ( offset + " is negative" );
		if ( length <= 0 )
			throw new IllegalArgumentException ( length + " is zero or negative" );

		this.channel = channel;
		this.offset = offset;
		this.length = length;
		this.source = null;
	}

	private static boolean exceptionIsMapFailureException ( IOException e ) {
		return e.getMessage () != null && e.getMessage ().contains ( "Map failed" );
	}

	void open () throws IOException {
		if ( source != null )
			return;

		if ( !channel.isOpen () )
			throw new IllegalStateException ( "Channel is closed" );

		try {
			source = new ByteBufferRandomAccessSource ( channel.map ( FileChannel.MapMode.READ_ONLY, offset, length ) );
		} catch ( IOException e ) {
			if ( exceptionIsMapFailureException ( e ) )
				throw new MapFailedException ( e );
			throw e;
		}
	}

	public int get ( long position ) throws IOException {
		if ( source == null )
			throw new IOException ( "RandomAccessSource not opened" );
		return source.get ( position );
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		if ( source == null )
			throw new IOException ( "RandomAccessSource not opened" );
		return source.get ( position, bytes, off, len );
	}

	public long length () {
		return length;
	}

	public void close () throws IOException {
		if ( source == null )
			return;
		source.close ();
		source = null;
	}

	@Override
	public String toString () {
		return getClass ().getName () + " (" + offset + ", " + length + ")";
	}
}
