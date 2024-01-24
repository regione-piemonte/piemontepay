/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.OutputStreamCounter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public abstract class DocWriter implements DocListener {

	public static final byte NEWLINE = (byte) '\n';

	public static final byte TAB = (byte) '\t';

	public static final byte LT = (byte) '<';

	public static final byte SPACE = (byte) ' ';

	public static final byte EQUALS = (byte) '=';

	public static final byte QUOTE = (byte) '\"';

	public static final byte GT = (byte) '>';

	public static final byte FORWARD = (byte) '/';

	protected Rectangle pageSize;

	protected Document document;

	protected OutputStreamCounter os;

	protected boolean open = false;

	protected boolean pause = false;

	protected boolean closeStream = true;

	protected DocWriter () {
	}

	protected DocWriter ( Document document, OutputStream os ) {
		this.document = document;
		this.os = new OutputStreamCounter ( new BufferedOutputStream ( os ) );
	}

	public static byte[] getISOBytes ( String text ) {
		if ( text == null )
			return null;
		int len = text.length ();
		byte[] b = new byte[len];
		for ( int k = 0; k < len; ++k )
			b[k] = (byte) text.charAt ( k );
		return b;
	}

	public boolean add ( Element element ) throws DocumentException {
		return false;
	}

	public void open () {
		open = true;
	}

	public void setPageSize ( Rectangle pageSize ) {
		this.pageSize = pageSize;
	}

	public void setMargins ( float marginLeft, float marginRight, float marginTop, float marginBottom ) {
	}

	public void newPage () {
	}

	public void resetPageCount () {
	}

	public void setPageCount ( int pageN ) {
	}

	public void close () {
		open = false;
		try {
			os.flush ();
			if ( closeStream )
				os.close ();
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
	}

	public void pause () {
		pause = true;
	}

	public boolean isPaused () {
		return pause;
	}

	public void flush () {
		try {
			os.flush ();
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
	}

	protected void write ( String string ) throws IOException {
		os.write ( getISOBytes ( string ) );
	}

	protected void write ( String key, String value )
					throws IOException {
		os.write ( SPACE );
		write ( key );
		os.write ( EQUALS );
		os.write ( QUOTE );
		write ( value );
		os.write ( QUOTE );
	}

	public boolean isCloseStream () {
		return closeStream;
	}

	public boolean setMarginMirroring ( boolean MarginMirroring ) {
		return false;
	}

	public boolean setMarginMirroringTopBottom ( boolean MarginMirroring ) {
		return false;
	}

}
