/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec.wmf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;

import java.io.IOException;
import java.io.InputStream;


public class InputMeta {

	InputStream in;

	int length;

	public InputMeta ( InputStream in ) {
		this.in = in;
	}

	public int readWord () throws IOException {
		length += 2;
		int k1 = in.read ();
		if ( k1 < 0 )
			return 0;
		return ( k1 + ( in.read () << 8 ) ) & 0xffff;
	}

	public int readShort () throws IOException {
		int k = readWord ();
		if ( k > 0x7fff )
			k -= 0x10000;
		return k;
	}

	public int readInt () throws IOException {
		length += 4;
		int k1 = in.read ();
		if ( k1 < 0 )
			return 0;
		int k2 = in.read () << 8;
		int k3 = in.read () << 16;
		return k1 + k2 + k3 + ( in.read () << 24 );
	}

	public int readByte () throws IOException {
		++length;
		return in.read () & 0xff;
	}

	public void skip ( int len ) throws IOException {
		length += len;
		Utilities.skip ( in, len );
	}

	public int getLength () {
		return length;
	}

	public BaseColor readColor () throws IOException {
		int red = readByte ();
		int green = readByte ();
		int blue = readByte ();
		readByte ();
		return new BaseColor ( red, green, blue );
	}
}
