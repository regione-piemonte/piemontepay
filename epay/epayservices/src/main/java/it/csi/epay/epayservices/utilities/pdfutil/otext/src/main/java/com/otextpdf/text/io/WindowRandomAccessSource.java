/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;


public class WindowRandomAccessSource implements RandomAccessSource {

	private final RandomAccessSource source;

	private final long offset;

	private final long length;

	public WindowRandomAccessSource ( RandomAccessSource source, long offset ) {
		this ( source, offset, source.length () - offset );
	}

	public WindowRandomAccessSource ( RandomAccessSource source, long offset, long length ) {
		this.source = source;
		this.offset = offset;
		this.length = length;
	}

	public int get ( long position ) throws IOException {
		if ( position >= length )
			return -1;
		return source.get ( offset + position );
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		if ( position >= length )
			return -1;

		long toRead = Math.min ( len, length - position );
		return source.get ( offset + position, bytes, off, (int) toRead );
	}

	public long length () {
		return length;
	}

	public void close () throws IOException {
		source.close ();
	}

}
