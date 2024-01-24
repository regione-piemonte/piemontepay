/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Document implements DocListener, IAccessibleElement {

	public static boolean compress = true;

	public static boolean plainRandomAccess = false;

	public static float wmfFontCorrection = 0.86f;

	protected ArrayList<DocListener> listeners = new ArrayList<> ();

	protected boolean open;

	protected boolean close;

	protected Rectangle pageSize;

	protected float marginLeft;

	protected float marginRight;

	protected float marginTop;

	protected float marginBottom;

	protected boolean marginMirroring = false;

	protected boolean marginMirroringTopBottom = false;

	protected String javaScript_onLoad = null;

	protected String javaScript_onUnLoad = null;

	protected String htmlStyleClass = null;

	protected int pageN = 0;

	protected int chapternumber = 0;

	protected PdfName role = PdfName.DOCUMENT;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected AccessibleElementId id = new AccessibleElementId ();

	public Document () {
		this ( PageSize.A4 );
	}

	public Document ( Rectangle pageSize ) {
		this ( pageSize, 36, 36, 36, 36 );
	}

	public Document ( Rectangle pageSize, float marginLeft, float marginRight,
					float marginTop, float marginBottom ) {
		this.pageSize = pageSize;
		this.marginLeft = marginLeft;
		this.marginRight = marginRight;
		this.marginTop = marginTop;
		this.marginBottom = marginBottom;
	}

	public void addDocListener ( DocListener listener ) {
		listeners.add ( listener );
		if ( listener instanceof IAccessibleElement ) {
			IAccessibleElement ae = (IAccessibleElement) listener;
			ae.setRole ( this.role );
			ae.setId ( this.id );
			if ( this.accessibleAttributes != null ) {
				for ( PdfName key : this.accessibleAttributes.keySet () )
					ae.setAccessibleAttribute ( key, this.accessibleAttributes.get ( key ) );
			}
		}
	}

	public boolean add ( Element element ) throws DocumentException {
		if ( close ) {
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.document.has.been.closed.you.can.t.add.any.elements" ) );
		}
		if ( !open && element.isContent () ) {
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.document.is.not.open.yet.you.can.only.add.meta.information" ) );
		}
		boolean success = false;
		if ( element instanceof ChapterAutoNumber ) {
			chapternumber = ( (ChapterAutoNumber) element ).setAutomaticNumber ( chapternumber );
		}
		for ( DocListener listener : listeners ) {
			success |= listener.add ( element );
		}
		if ( element instanceof LargeElement ) {
			LargeElement e = (LargeElement) element;
			if ( !e.isComplete () )
				e.flushContent ();
		}
		return success;
	}

	public void open () {
		if ( !close ) {
			open = true;
		}
		for ( DocListener listener : listeners ) {
			listener.setPageSize ( pageSize );
			listener.setMargins ( marginLeft, marginRight, marginTop,
							marginBottom );
			listener.open ();
		}
	}

	public void setMargins ( float marginLeft, float marginRight,
					float marginTop, float marginBottom ) {
		this.marginLeft = marginLeft;
		this.marginRight = marginRight;
		this.marginTop = marginTop;
		this.marginBottom = marginBottom;
		for ( DocListener listener : listeners ) {
			listener.setMargins ( marginLeft, marginRight, marginTop,
							marginBottom );
		}
	}

	public void newPage () {
		if ( !open || close ) {
			return;
		}
		for ( DocListener listener : listeners ) {
			listener.newPage ();
		}
	}

	public void resetPageCount () {
		pageN = 0;
		for ( DocListener listener : listeners ) {
			listener.resetPageCount ();
		}
	}

	public void setPageCount ( int pageN ) {
		this.pageN = pageN;
		for ( DocListener listener : listeners ) {
			listener.setPageCount ( pageN );
		}
	}

	public int getPageNumber () {
		return this.pageN;
	}

	public void close () {
		if ( !close ) {
			open = false;
			close = true;
		}
		for ( DocListener listener : listeners ) {
			listener.close ();
		}
	}

	public void addProducer () {
		try {
			add ( new Meta ( Element.PRODUCER, Version.getInstance ().getVersion () ) );
		} catch ( DocumentException de ) {
			throw new ExceptionConverter ( de );
		}
	}

	public void addCreationDate () {
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat (
							"EEE MMM dd HH:mm:ss zzz yyyy" );
			add ( new Meta ( Element.CREATIONDATE, sdf.format ( new Date () ) ) );
		} catch ( DocumentException de ) {
			throw new ExceptionConverter ( de );
		}
	}

	public float left () {
		return pageSize.getLeft ( marginLeft );
	}

	public float right () {
		return pageSize.getRight ( marginRight );
	}

	public float top () {
		return pageSize.getTop ( marginTop );
	}

	public float bottom () {
		return pageSize.getBottom ( marginBottom );
	}

	public float left ( float margin ) {
		return pageSize.getLeft ( marginLeft + margin );
	}

	public float right ( float margin ) {
		return pageSize.getRight ( marginRight + margin );
	}

	public float top ( float margin ) {
		return pageSize.getTop ( marginTop + margin );
	}

	public float bottom ( float margin ) {
		return pageSize.getBottom ( marginBottom + margin );
	}

	public void setPageSize ( Rectangle pageSize ) {
		this.pageSize = pageSize;
		for ( DocListener listener : listeners ) {
			listener.setPageSize ( pageSize );
		}
	}

	public boolean isOpen () {
		return open;
	}

	public boolean setMarginMirroring ( boolean marginMirroring ) {
		this.marginMirroring = marginMirroring;
		DocListener listener;
		for ( DocListener element : listeners ) {
			listener = element;
			listener.setMarginMirroring ( marginMirroring );
		}
		return true;
	}

	public boolean setMarginMirroringTopBottom ( boolean marginMirroringTopBottom ) {
		this.marginMirroringTopBottom = marginMirroringTopBottom;
		DocListener listener;
		for ( DocListener element : listeners ) {
			listener = element;
			listener.setMarginMirroringTopBottom ( marginMirroringTopBottom );
		}
		return true;
	}

	public PdfObject getAccessibleAttribute ( final PdfName key ) {
		if ( accessibleAttributes != null )
			return accessibleAttributes.get ( key );
		else
			return null;
	}

	public void setAccessibleAttribute ( final PdfName key, final PdfObject value ) {
		if ( accessibleAttributes == null )
			accessibleAttributes = new HashMap<> ();
		accessibleAttributes.put ( key, value );
	}

	public HashMap<PdfName, PdfObject> getAccessibleAttributes () {
		return accessibleAttributes;
	}

	public PdfName getRole () {
		return role;
	}

	public void setRole ( final PdfName role ) {
		this.role = role;
	}

	public AccessibleElementId getId () {
		return id;
	}

	public void setId ( final AccessibleElementId id ) {
		this.id = id;
	}

	public boolean isInline () {
		return false;
	}
}
