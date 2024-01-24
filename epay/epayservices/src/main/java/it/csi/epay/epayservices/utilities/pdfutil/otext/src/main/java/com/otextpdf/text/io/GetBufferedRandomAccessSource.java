/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;


public class GetBufferedRandomAccessSource implements RandomAccessSource {

	private final RandomAccessSource source;

	private final byte[] getBuffer;

	private long getBufferStart;

	private long getBufferEnd;

	public GetBufferedRandomAccessSource ( RandomAccessSource source ) {
		this.source = source;

		this.getBuffer = new byte[(int) Math.min ( Math.max ( source.length () / 4, 1 ), 4096 )];
		this.getBufferStart = -1;
		this.getBufferEnd = -1;

	}

	public int get ( long position ) throws IOException {
		if ( position < getBufferStart || position > getBufferEnd ) {
			int count = source.get ( position, getBuffer, 0, getBuffer.length );
			if ( count == -1 )
				return -1;
			getBufferStart = position;
			getBufferEnd = position + count - 1;
		}
		int bufPos = (int) ( position - getBufferStart );
		return 0xff & getBuffer[bufPos];
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		return source.get ( position, bytes, off, len );
	}

	public long length () {
		return source.length ();
	}

	public void close () throws IOException {
		source.close ();
		getBufferStart = -1;
		getBufferEnd = -1;
	}

}
