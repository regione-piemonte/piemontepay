/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;


public abstract class Options {

	private int options = 0;

	public Options () {
		// EMTPY
	}

	public Options ( int options ) throws XMPException {
		assertOptionsValid ( options );
		setOptions ( options );
	}

	public void clear () {
		options = 0;
	}

	public boolean containsOneOf ( int optionBits ) {
		return ( ( getOptions () ) & optionBits ) != 0;
	}

	protected boolean getOption ( int optionBit ) {
		return ( options & optionBit ) != 0;
	}

	public void setOption ( int optionBits, boolean value ) {
		options = value ? options | optionBits : options & ~optionBits;
	}

	public int getOptions () {
		return options;
	}

	public void setOptions ( int options ) throws XMPException {
		assertOptionsValid ( options );
		this.options = options;
	}

	public boolean equals ( Object obj ) {
		return getOptions () == ( (Options) obj ).getOptions ();
	}

	public int hashCode () {
		return getOptions ();
	}

	public String toString () {
		return "0x" + Integer.toHexString ( options );
	}

	protected abstract int getValidOptions ();

	protected void assertConsistency ( int options ) throws XMPException {
		// empty, no checks
	}

	private void assertOptionsValid ( int options ) throws XMPException {
		int invalidOptions = options & ~getValidOptions ();
		if ( invalidOptions == 0 ) {
			assertConsistency ( options );
		} else {
			throw new XMPException ( "The option bit(s) 0x" + Integer.toHexString ( invalidOptions )
							+ " are invalid!", XMPError.BADOPTIONS );
		}
	}

}
