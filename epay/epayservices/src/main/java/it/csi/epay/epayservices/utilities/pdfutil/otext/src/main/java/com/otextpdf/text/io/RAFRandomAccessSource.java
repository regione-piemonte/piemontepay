/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;
import java.io.RandomAccessFile;


class RAFRandomAccessSource implements RandomAccessSource {

	private final RandomAccessFile raf;

	private final long length;

	public RAFRandomAccessSource ( RandomAccessFile raf ) throws IOException {
		this.raf = raf;
		length = raf.length ();
	}

	public int get ( long position ) throws IOException {
		if ( position > raf.length () )
			return -1;

		raf.seek ( position );

		return raf.read ();
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		if ( position > length )
			return -1;
		raf.seek ( position );

		return raf.read ( bytes, off, len );
	}

	public long length () {
		return length;
	}

	public void close () throws IOException {
		raf.close ();
	}

}
