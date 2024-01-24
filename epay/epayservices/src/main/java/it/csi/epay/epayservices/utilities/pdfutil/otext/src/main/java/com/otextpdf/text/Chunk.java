/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.HyphenationEvent;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAction;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAnnotation;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw.DrawInterface;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Chunk implements Element, IAccessibleElement {

	public static final String OBJECT_REPLACEMENT_CHARACTER = "?";

	public static final Chunk NEWLINE = new Chunk ( "\n" );

	public static final Chunk NEXTPAGE = new Chunk ( "" );

	public static final Chunk TABBING = new Chunk ( Float.NaN, false );

	public static final Chunk SPACETABBING = new Chunk ( Float.NaN, true );

	public static final String SEPARATOR = "SEPARATOR";

	public static final String TAB = "TAB";

	public static final String TABSETTINGS = "TABSETTINGS";

	public static final String HSCALE = "HSCALE";

	public static final String UNDERLINE = "UNDERLINE";

	public static final String SUBSUPSCRIPT = "SUBSUPSCRIPT";

	public static final String SKEW = "SKEW";

	public static final String BACKGROUND = "BACKGROUND";

	public static final String TEXTRENDERMODE = "TEXTRENDERMODE";

	public static final String SPLITCHARACTER = "SPLITCHARACTER";

	public static final String HYPHENATION = "HYPHENATION";

	public static final String REMOTEGOTO = "REMOTEGOTO";

	public static final String LOCALGOTO = "LOCALGOTO";

	public static final String LOCALDESTINATION = "LOCALDESTINATION";

	public static final String GENERICTAG = "GENERICTAG";

	public static final String LINEHEIGHT = "LINEHEIGHT";

	public static final String IMAGE = "IMAGE";

	public static final String ACTION = "ACTION";

	public static final String NEWPAGE = "NEWPAGE";

	public static final String PDFANNOTATION = "PDFANNOTATION";

	public static final String COLOR = "COLOR";

	public static final String ENCODING = "ENCODING";

	public static final String CHAR_SPACING = "CHAR_SPACING";

	public static final String WORD_SPACING = "WORD_SPACING";

	public static final String WHITESPACE = "WHITESPACE";

	static {
		NEWLINE.setRole ( PdfName.P );
	}

	static {
		NEXTPAGE.setNewPage ();
	}

	protected StringBuffer content = null;

	protected Font font = null;

	protected HashMap<String, Object> attributes = null;

	protected PdfName role;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	private AccessibleElementId id = null;

	private String contentWithNoTabs = null;

	public Chunk () {
		this.content = new StringBuffer ();
		this.font = new Font ();
		this.role = PdfName.SPAN;
	}

	public Chunk ( final Chunk ck ) {
		if ( ck.content != null ) {
			content = new StringBuffer ( ck.content.toString () );
		}
		if ( ck.font != null ) {
			font = new Font ( ck.font );
		}
		if ( ck.attributes != null ) {
			attributes = new HashMap<> ( ck.attributes );
		}
		role = ck.role;
		if ( ck.accessibleAttributes != null ) {
			accessibleAttributes = new HashMap<> ( ck.accessibleAttributes );
		}
		id = ck.getId ();
	}

	public Chunk ( final String content, final Font font ) {
		this.content = new StringBuffer ( content );
		this.font = font;
		this.role = PdfName.SPAN;
	}

	public Chunk ( final String content ) {
		this ( content, new Font () );
	}

	public Chunk ( final char c, final Font font ) {
		this.content = new StringBuffer ();
		this.content.append ( c );
		this.font = font;
		this.role = PdfName.SPAN;
	}

	public Chunk ( final char c ) {
		this ( c, new Font () );
	}

	public Chunk ( final Image image, final float offsetX, final float offsetY ) {
		this ( OBJECT_REPLACEMENT_CHARACTER, new Font () );
		Image copyImage = Image.getInstance ( image );
		copyImage.setAbsolutePosition ( Float.NaN, Float.NaN );
		setAttribute ( IMAGE, new Object[] { copyImage, offsetX, offsetY, Boolean.FALSE } );
		this.role = null;
	}

	public Chunk ( final DrawInterface separator ) {
		this ( separator, false );
	}

	public Chunk ( final DrawInterface separator, final boolean vertical ) {
		this ( OBJECT_REPLACEMENT_CHARACTER, new Font () );
		setAttribute ( SEPARATOR, new Object[] { separator, vertical } );
		this.role = null;
	}

	@Deprecated
	public Chunk ( final DrawInterface separator, final float tabPosition ) {
		this ( separator, tabPosition, false );
	}

	@Deprecated
	public Chunk ( final DrawInterface separator, final float tabPosition, final boolean newline ) {
		this ( OBJECT_REPLACEMENT_CHARACTER, new Font () );
		if ( tabPosition < 0 ) {
			throw new IllegalArgumentException (
							MessageLocalization.getComposedMessage ( "a.tab.position.may.not.be.lower.than.0.yours.is.1", String.valueOf ( tabPosition ) ) );
		}
		setAttribute ( TAB, new Object[] { separator, tabPosition, newline, (float) 0 } );
		this.role = PdfName.ARTIFACT;
	}

	private Chunk ( final Float tabInterval, final boolean isWhitespace ) {
		this ( OBJECT_REPLACEMENT_CHARACTER, new Font () );
		if ( tabInterval < 0 ) {
			throw new IllegalArgumentException (
							MessageLocalization.getComposedMessage ( "a.tab.position.may.not.be.lower.than.0.yours.is.1", String.valueOf ( tabInterval ) ) );
		}
		setAttribute ( TAB, new Object[] { tabInterval, isWhitespace } );
		setAttribute ( SPLITCHARACTER, TabSplitCharacter.TAB );

		setAttribute ( TABSETTINGS, null );
		this.role = PdfName.ARTIFACT;
	}

	public Chunk ( final Image image, final float offsetX, final float offsetY,
					final boolean changeLeading ) {
		this ( OBJECT_REPLACEMENT_CHARACTER, new Font () );
		setAttribute ( IMAGE, new Object[] { image, offsetX, offsetY, changeLeading } );
		this.role = PdfName.ARTIFACT;
	}

	@Deprecated
	public static Chunk createTabspace () {
		return createTabspace ( 60 );
	}

	@Deprecated
	public static Chunk createTabspace ( float spacing ) {
		return new Chunk ( spacing, true );
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( this );
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return Element.CHUNK;
	}

	public List<Chunk> getChunks () {
		List<Chunk> tmp = new ArrayList<> ();
		tmp.add ( this );
		return tmp;
	}

	public StringBuffer append ( final String string ) {
		contentWithNoTabs = null;
		return content.append ( string );
	}

	public Font getFont () {
		return font;
	}

	public void setFont ( final Font font ) {
		this.font = font;
	}

	public String getContent () {
		if ( contentWithNoTabs == null )
			contentWithNoTabs = content.toString ().replaceAll ( "\t", "" );
		return contentWithNoTabs;
	}

	@Override
	public String toString () {
		return getContent ();
	}

	public boolean isEmpty () {
		return content.toString ().trim ().isEmpty ()
						&& !content.toString ().contains ( "\n" )
						&& attributes == null;
	}

	public float getWidthPoint () {
		if ( getImage () != null ) {
			return getImage ().getScaledWidth ();
		}
		return font.getCalculatedBaseFont ( true ).getWidthPoint ( getContent (),
						font.getCalculatedSize () )
						* getHorizontalScaling ();
	}

	public boolean hasAttributes () {
		return attributes == null;
	}

	public HashMap<String, Object> getAttributes () {
		return attributes;
	}

	public void setAttributes ( final HashMap<String, Object> attributes ) {
		this.attributes = attributes;
	}

	private Chunk setAttribute ( final String name, final Object obj ) {
		if ( attributes == null )
			attributes = new HashMap<> ();
		attributes.put ( name, obj );
		return this;
	}

	public float getHorizontalScaling () {
		if ( attributes == null )
			return 1f;
		Float f = (Float) attributes.get ( HSCALE );
		if ( f == null )
			return 1f;
		return f;
	}

	public void setTextRise ( final float rise ) {
		setAttribute ( SUBSUPSCRIPT, rise );
	}

	public Chunk setBackground ( final BaseColor color ) {
		return setBackground ( color, 0, 0, 0, 0 );
	}

	public Chunk setBackground ( final BaseColor color, final float extraLeft, final float extraBottom,
					final float extraRight, final float extraTop ) {
		return setAttribute ( BACKGROUND, new Object[] { color,
						new float[] { extraLeft, extraBottom, extraRight, extraTop } } );
	}

	public void setLocalGoto ( final String name ) {
		setAttribute ( LOCALGOTO, name );
	}

	public void setLocalDestination ( final String name ) {
		setAttribute ( LOCALDESTINATION, name );
	}

	public Chunk setLineHeight ( float lineheight ) {
		return setAttribute ( LINEHEIGHT, lineheight );
	}

	public Image getImage () {
		if ( attributes == null )
			return null;
		Object[] obj = (Object[]) attributes.get ( Chunk.IMAGE );
		if ( obj == null )
			return null;
		else {
			return (Image) obj[0];
		}
	}

	public Chunk setAction ( final PdfAction action ) {
		setRole ( PdfName.LINK );
		return setAttribute ( ACTION, action );
	}

	public Chunk setAnchor ( final URL url ) {
		setRole ( PdfName.LINK );
		return setAttribute ( ACTION, new PdfAction ( url.toExternalForm () ) );
	}

	public Chunk setAnchor ( final String url ) {
		setRole ( PdfName.LINK );
		return setAttribute ( ACTION, new PdfAction ( url ) );
	}

	public void setNewPage () {
		setAttribute ( NEWPAGE, null );
	}

	public Chunk setAnnotation ( final PdfAnnotation annotation ) {
		return setAttribute ( PDFANNOTATION, annotation );
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return true;
	}

	public HyphenationEvent getHyphenation () {
		if ( attributes == null )
			return null;
		return (HyphenationEvent) attributes.get ( Chunk.HYPHENATION );
	}

	public Chunk setHyphenation ( final HyphenationEvent hyphenation ) {
		return setAttribute ( HYPHENATION, hyphenation );
	}

	public boolean isWhitespace () {
		return attributes != null && attributes.containsKey ( WHITESPACE );
	}

	@Deprecated
	public boolean isTabspace () {
		return attributes != null && attributes.containsKey ( TAB );
	}

	public PdfObject getAccessibleAttribute ( final PdfName key ) {
		if ( getImage () != null ) {
			return getImage ().getAccessibleAttribute ( key );
		} else if ( accessibleAttributes != null )
			return accessibleAttributes.get ( key );
		else
			return null;
	}

	public void setAccessibleAttribute ( final PdfName key, final PdfObject value ) {
		if ( getImage () != null ) {
			getImage ().setAccessibleAttribute ( key, value );
		} else {
			if ( accessibleAttributes == null )
				accessibleAttributes = new HashMap<> ();
			accessibleAttributes.put ( key, value );
		}
	}

	public HashMap<PdfName, PdfObject> getAccessibleAttributes () {
		if ( getImage () != null )
			return getImage ().getAccessibleAttributes ();
		else
			return accessibleAttributes;
	}

	public PdfName getRole () {
		if ( getImage () != null )
			return getImage ().getRole ();
		else
			return role;
	}

	public void setRole ( final PdfName role ) {
		if ( getImage () != null )
			getImage ().setRole ( role );
		else
			this.role = role;
	}

	public AccessibleElementId getId () {
		if ( id == null )
			id = new AccessibleElementId ();
		return id;
	}

	public void setId ( final AccessibleElementId id ) {
		this.id = id;
	}

	public boolean isInline () {
		return true;
	}

}
