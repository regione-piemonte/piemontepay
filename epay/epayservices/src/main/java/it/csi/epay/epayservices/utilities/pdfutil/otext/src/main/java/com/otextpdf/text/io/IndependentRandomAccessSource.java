/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;


public class IndependentRandomAccessSource implements RandomAccessSource {

	private final RandomAccessSource source;

	public IndependentRandomAccessSource ( RandomAccessSource source ) {
		this.source = source;
	}

	public int get ( long position ) throws IOException {
		return source.get ( position );
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		return source.get ( position, bytes, off, len );
	}

	public long length () {
		return source.length ();
	}

	public void close () throws IOException {
	}

}
