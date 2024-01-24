/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;


public class OutputStreamCounter extends OutputStream {

	protected OutputStream out;

	protected long counter = 0;

	public OutputStreamCounter ( OutputStream out ) {
		this.out = out;
	}

	public void close () throws IOException {
		out.close ();
	}

	public void flush () throws IOException {
		out.flush ();
	}

	public void write ( byte[] b ) throws IOException {
		counter += b.length;
		out.write ( b );
	}

	public void write ( int b ) throws IOException {
		++counter;
		out.write ( b );
	}

	public void write ( byte[] b, int off, int len ) throws IOException {
		counter += len;
		out.write ( b, off, len );
	}

	public long getCounter () {
		return counter;
	}

}
