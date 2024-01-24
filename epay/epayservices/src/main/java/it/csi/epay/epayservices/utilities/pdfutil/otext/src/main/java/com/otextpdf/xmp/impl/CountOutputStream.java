/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import java.io.IOException;
import java.io.OutputStream;


public final class CountOutputStream extends OutputStream {

	private final OutputStream out;

	private int bytesWritten = 0;

	CountOutputStream ( OutputStream out ) {
		this.out = out;
	}

	public void write ( byte[] buf, int off, int len ) throws IOException {
		out.write ( buf, off, len );
		bytesWritten += len;
	}

	public void write ( byte[] buf ) throws IOException {
		out.write ( buf );
		bytesWritten += buf.length;
	}

	public void write ( int b ) throws IOException {
		out.write ( b );
		bytesWritten++;
	}

	public int getBytesWritten () {
		return bytesWritten;
	}
}
