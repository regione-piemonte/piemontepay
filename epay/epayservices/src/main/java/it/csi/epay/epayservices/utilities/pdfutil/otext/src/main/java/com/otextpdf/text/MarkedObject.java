/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.util.List;
import java.util.Properties;


public class MarkedObject implements Element {

	protected Element element;

	protected Properties markupAttributes = new Properties ();

	protected MarkedObject () {
		element = null;
	}

	public MarkedObject ( final Element element ) {
		this.element = element;
	}

	public List<Chunk> getChunks () {
		return element.getChunks ();
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( element );
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return MARKED;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return true;
	}

}
