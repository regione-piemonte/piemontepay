/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.HyphenationEvent;

import java.util.ArrayList;
import java.util.Collection;


public class Phrase extends ArrayList<Element> implements TextElementArray {

	private static final long serialVersionUID = 2643594602455068231L;

	protected float leading = Float.NaN;

	protected float multipliedLeading = 0;

	protected Font font;

	protected HyphenationEvent hyphenation = null;

	protected TabSettings tabSettings = null;

	public Phrase () {
		this ( 16 );
	}

	public Phrase ( final Phrase phrase ) {
		super ();
		this.addAll ( phrase );
		setLeading ( phrase.getLeading (), phrase.getMultipliedLeading () );
		font = phrase.getFont ();
		tabSettings = phrase.getTabSettings ();
		setHyphenation ( phrase.getHyphenation () );
	}

	public Phrase ( final float leading ) {
		this.leading = leading;
		font = new Font ();
	}

	public Phrase ( final Chunk chunk ) {
		super.add ( chunk );
		font = chunk.getFont ();
		setHyphenation ( chunk.getHyphenation () );
	}

	public Phrase ( final float leading, final Chunk chunk ) {
		this.leading = leading;
		super.add ( chunk );
		font = chunk.getFont ();
		setHyphenation ( chunk.getHyphenation () );
	}

	public Phrase ( final String string ) {
		this ( Float.NaN, string, new Font () );
	}

	public Phrase ( final String string, final Font font ) {
		this ( Float.NaN, string, font );
	}

	public Phrase ( final float leading, final String string ) {
		this ( leading, string, new Font () );
	}

	public Phrase ( final float leading, final String string, final Font font ) {
		this.leading = leading;
		this.font = font;
		if ( string != null && !string.isEmpty () ) {
			super.add ( new Chunk ( string, font ) );
		}
	}

	private Phrase ( final boolean dummy ) {
	}

	public static Phrase getInstance ( final String string ) {
		return getInstance ( 16, string, new Font () );
	}

	public static Phrase getInstance ( final int leading, final String string ) {
		return getInstance ( leading, string, new Font () );
	}

	public static Phrase getInstance ( final int leading, String string, final Font font ) {
		Phrase p = new Phrase ( true );
		p.setLeading ( leading );
		p.font = font;
		if ( font.getFamily () != Font.FontFamily.SYMBOL && font.getFamily () != Font.FontFamily.ZAPFDINGBATS && font.getBaseFont () == null ) {
			int index;
			while ( ( index = SpecialSymbol.index ( string ) ) > -1 ) {
				if ( index > 0 ) {
					String firstPart = string.substring ( 0, index );
					p.add ( new Chunk ( firstPart, font ) );
					string = string.substring ( index );
				}
				Font symbol = new Font ( Font.FontFamily.SYMBOL, font.getSize (), font.getStyle (), font.getColor () );
				StringBuilder buf = new StringBuilder ();
				buf.append ( SpecialSymbol.getCorrespondingSymbol ( string.charAt ( 0 ) ) );
				string = string.substring ( 1 );
				while ( SpecialSymbol.index ( string ) == 0 ) {
					buf.append ( SpecialSymbol.getCorrespondingSymbol ( string.charAt ( 0 ) ) );
					string = string.substring ( 1 );
				}
				p.add ( new Chunk ( buf.toString (), symbol ) );
			}
		}
		if ( string != null && !string.isEmpty () ) {
			p.add ( new Chunk ( string, font ) );
		}
		return p;
	}

	public boolean process ( final ElementListener listener ) {
		try {
			for ( Element element : this ) {
				listener.add ( element );
			}
			return true;
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return Element.PHRASE;
	}

	public java.util.List<Chunk> getChunks () {
		java.util.List<Chunk> tmp = new ArrayList<> ();
		for ( Element element : this ) {
			tmp.addAll ( element.getChunks () );
		}
		return tmp;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return true;
	}

	@Override
	public void add ( final int index, final Element element ) {
		if ( element == null )
			return;
		switch ( element.type () ) {
		case Element.CHUNK:
			Chunk chunk = (Chunk) element;
			if ( !font.isStandardFont () ) {
				chunk.setFont ( font.difference ( chunk.getFont () ) );
			}
			if ( hyphenation != null && chunk.getHyphenation () == null && !chunk.isEmpty () ) {
				chunk.setHyphenation ( hyphenation );
			}
			super.add ( index, chunk );
			return;
		case Element.PHRASE:
		case Element.PARAGRAPH:
		case Element.MARKED:
		case Element.DIV:
		case Element.ANCHOR:
		case Element.ANNOTATION:
		case Element.PTABLE:
		case Element.LIST:
		case Element.YMARK:
		case Element.WRITABLE_DIRECT:
			super.add ( index, element );
			return;
		default:
			throw new ClassCastException ( MessageLocalization.getComposedMessage ( "insertion.of.illegal.element.1", element.getClass ().getName () ) );
		}
	}

	public boolean add ( final String s ) {
		if ( s == null ) {
			return false;
		}
		return super.add ( new Chunk ( s, font ) );
	}

	@Override
	public boolean add ( final Element element ) {
		if ( element == null )
			return false;
		try {
			switch ( element.type () ) {
			case Element.CHUNK:
				return addChunk ( (Chunk) element );
			case Element.PHRASE:
			case Element.PARAGRAPH:
				Phrase phrase = (Phrase) element;
				boolean success = true;
				Element e;
				for ( Element element2 : phrase ) {
					e = element2;
					if ( e instanceof Chunk ) {
						success &= addChunk ( (Chunk) e );
					} else {
						success &= this.add ( e );
					}
				}
				return success;
			case Element.MARKED:
			case Element.DIV:
			case Element.ANCHOR:
			case Element.ANNOTATION:
			case Element.PTABLE:
			case Element.LIST:
			case Element.YMARK:
			case Element.WRITABLE_DIRECT:
				return super.add ( element );
			default:
				throw new ClassCastException ( String.valueOf ( element.type () ) );
			}
		} catch ( ClassCastException cce ) {
			throw new ClassCastException ( MessageLocalization.getComposedMessage ( "insertion.of.illegal.element.1", cce.getMessage () ) );
		}
	}

	@Override
	public boolean addAll ( final Collection<? extends Element> collection ) {
		for ( Element e : collection ) {
			this.add ( e );
		}
		return true;
	}

	protected boolean addChunk ( final Chunk chunk ) {
		Font f = chunk.getFont ();
		String c = chunk.getContent ();
		if ( font != null && !font.isStandardFont () ) {
			f = font.difference ( chunk.getFont () );
		}
		if ( size () > 0 && chunk.hasAttributes () ) {
			try {
				Chunk previous = (Chunk) get ( size () - 1 );
				if ( previous.hasAttributes ()
								&& ( f == null
								|| f.compareTo ( previous.getFont () ) == 0 )
								&& !previous.getContent ().trim ().isEmpty ()
								&& !c.trim ().isEmpty () ) {
					previous.append ( c );
					return true;
				}
			} catch ( ClassCastException ignored ) {
			}
		}
		Chunk newChunk = new Chunk ( c, f );
		newChunk.setAttributes ( chunk.getAttributes () );
		newChunk.role = chunk.getRole ();
		newChunk.accessibleAttributes = chunk.getAccessibleAttributes ();
		if ( hyphenation != null && newChunk.getHyphenation () == null && !newChunk.isEmpty () ) {
			newChunk.setHyphenation ( hyphenation );
		}
		return super.add ( newChunk );
	}

	protected void addSpecial ( final Element object ) {
		super.add ( object );
	}

	public void setLeading ( final float fixedLeading, final float multipliedLeading ) {
		this.leading = fixedLeading;
		this.multipliedLeading = multipliedLeading;
	}

	public float getLeading () {
		if ( Float.isNaN ( leading ) && font != null ) {
			return font.getCalculatedLeading ( 1.5f );
		}
		return leading;
	}

	public void setLeading ( final float fixedLeading ) {
		this.leading = fixedLeading;
		this.multipliedLeading = 0;
	}

	public float getMultipliedLeading () {
		return multipliedLeading;
	}

	public float getTotalLeading () {
		float m = font == null ?
						Font.DEFAULTSIZE * multipliedLeading : font.getCalculatedLeading ( multipliedLeading );
		if ( m > 0 && !hasLeading () ) {
			return m;
		}
		return getLeading () + m;
	}

	public boolean hasLeading () {
		return !Float.isNaN ( leading );
	}

	public Font getFont () {
		return font;
	}

	public void setFont ( final Font font ) {
		this.font = font;
	}

	public String getContent () {
		StringBuilder buf = new StringBuilder ();
		for ( Chunk c : getChunks () ) {
			buf.append ( c.toString () );
		}
		return buf.toString ();
	}

	@Override
	public boolean isEmpty () {
		switch ( size () ) {
		case 0:
			return true;
		case 1:
			Element element = get ( 0 );
			return element.type () == Element.CHUNK && ( (Chunk) element ).isEmpty ();
		default:
			return false;
		}
	}

	public HyphenationEvent getHyphenation () {
		return hyphenation;
	}

	public void setHyphenation ( final HyphenationEvent hyphenation ) {
		this.hyphenation = hyphenation;
	}

	public TabSettings getTabSettings () {
		return tabSettings;
	}

	public void setTabSettings ( TabSettings tabSettings ) {
		this.tabSettings = tabSettings;
	}

	public boolean trim () {
		while ( this.size () > 0 ) {
			Element firstChunk = this.get ( 0 );
			if ( firstChunk instanceof Chunk && ( (Chunk) firstChunk ).isWhitespace () ) {
				this.remove ( firstChunk );
			} else {
				break;
			}
		}
		while ( this.size () > 0 ) {
			Element lastChunk = this.get ( this.size () - 1 );
			if ( lastChunk instanceof Chunk && ( (Chunk) lastChunk ).isWhitespace () ) {
				this.remove ( lastChunk );
			} else {
				break;
			}
		}
		return size () > 0;
	}

}
