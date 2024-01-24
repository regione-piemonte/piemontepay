/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Anchor extends Phrase {

	private static final long serialVersionUID = -852278536049236911L;

	protected String name = null;

	protected String reference = null;

	public Anchor ( final Phrase phrase ) {
		super ( phrase );
		if ( phrase instanceof Anchor ) {
			Anchor a = (Anchor) phrase;
			setName ( a.name );
			setReference ( a.reference );
		}
	}

	@Override
	public boolean process ( final ElementListener listener ) {
		try {
			Chunk chunk;
			Iterator<Chunk> i = getChunks ().iterator ();
			boolean localDestination = reference != null && reference.startsWith ( "#" );
			boolean notGotoOK = true;
			while ( i.hasNext () ) {
				chunk = i.next ();
				if ( name != null && notGotoOK && !chunk.isEmpty () ) {
					chunk.setLocalDestination ( name );
					notGotoOK = false;
				}
				if ( localDestination ) {
					chunk.setLocalGoto ( reference.substring ( 1 ) );
				}
				listener.add ( chunk );
			}
			return true;
		} catch ( DocumentException de ) {
			return false;
		}
	}

	@Override
	public List<Chunk> getChunks () {
		boolean localDestination = reference != null && reference.startsWith ( "#" );
		boolean notGotoOK = true;
		List<Chunk> tmp = new ArrayList<> ();
		Iterator<Element> i = iterator ();
		Element element;
		while ( i.hasNext () ) {
			element = i.next ();
			if ( element instanceof Chunk ) {
				Chunk chunk = (Chunk) element;
				notGotoOK = applyAnchor ( chunk, notGotoOK, localDestination );
				tmp.add ( chunk );
			} else {
				for ( Chunk c : element.getChunks () ) {
					notGotoOK = applyAnchor ( c, notGotoOK, localDestination );
					tmp.add ( c );
				}
			}
		}
		return tmp;
	}

	protected boolean applyAnchor ( Chunk chunk, boolean notGotoOK, boolean localDestination ) {
		if ( name != null && notGotoOK && !chunk.isEmpty () ) {
			chunk.setLocalDestination ( name );
			notGotoOK = false;
		}
		if ( localDestination ) {
			chunk.setLocalGoto ( reference.substring ( 1 ) );
		} else if ( reference != null )
			chunk.setAnchor ( reference );
		return notGotoOK;
	}

	@Override
	public int type () {
		return Element.ANCHOR;
	}

	public String getName () {
		return name;
	}

	public void setName ( final String name ) {
		this.name = name;
	}

	public String getReference () {
		return reference;
	}

	public void setReference ( final String reference ) {
		this.reference = reference;
	}

	public URL getUrl () {
		try {
			return new URL ( reference );
		} catch ( MalformedURLException mue ) {
			return null;
		}
	}

}
