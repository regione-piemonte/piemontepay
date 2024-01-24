/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.io.IOException;
import java.util.HashMap;


public class PdfTemplate extends PdfContentByte implements IAccessibleElement {

	public static final int TYPE_TEMPLATE = 1;

	public static final int TYPE_IMPORTED = 2;

	public static final int TYPE_PATTERN = 3;

	protected int type;

	protected PdfIndirectReference thisReference;

	protected PageResources pageResources;

	protected Rectangle bBox = new Rectangle ( 0, 0 );

	protected PdfArray matrix;

	protected PdfTransparencyGroup group;

	protected PdfOCG layer;

	protected PdfIndirectReference pageReference;

	protected boolean contentTagged = false;

	protected PdfName role = PdfName.FIGURE;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	private PdfDictionary additional = null;

	private AccessibleElementId id = null;

	protected PdfTemplate () {
		super ( null );
		type = TYPE_TEMPLATE;
	}

	PdfTemplate ( PdfWriter wr ) {
		super ( wr );
		type = TYPE_TEMPLATE;
		pageResources = new PageResources ();
		pageResources.addDefaultColor ( wr.getDefaultColorspace () );
		thisReference = writer.getPdfIndirectReference ();
	}

	public static PdfTemplate createTemplate ( PdfWriter writer, float width, float height ) {
		return createTemplate ( writer, width, height, null );
	}

	static PdfTemplate createTemplate ( PdfWriter writer, float width, float height, PdfName forcedName ) {
		PdfTemplate template = new PdfTemplate ( writer );
		template.setWidth ( width );
		template.setHeight ( height );
		writer.addDirectTemplateSimple ( template, forcedName );
		return template;
	}

	public boolean isTagged () {
		return super.isTagged () && contentTagged;
	}

	public float getWidth () {
		return bBox.getWidth ();
	}

	public void setWidth ( float width ) {
		bBox.setLeft ( 0 );
		bBox.setRight ( width );
	}

	public float getHeight () {
		return bBox.getHeight ();
	}

	public void setHeight ( float height ) {
		bBox.setBottom ( 0 );
		bBox.setTop ( height );
	}

	public Rectangle getBoundingBox () {
		return bBox;
	}

	public void setBoundingBox ( Rectangle bBox ) {
		this.bBox = bBox;
	}

	public PdfOCG getLayer () {
		return layer;
	}

	public void setLayer ( PdfOCG layer ) {
		this.layer = layer;
	}

	public void setMatrix ( float a, float b, float c, float d, float e, float f ) {
		matrix = new PdfArray ();
		matrix.add ( new PdfNumber ( a ) );
		matrix.add ( new PdfNumber ( b ) );
		matrix.add ( new PdfNumber ( c ) );
		matrix.add ( new PdfNumber ( d ) );
		matrix.add ( new PdfNumber ( e ) );
		matrix.add ( new PdfNumber ( f ) );
	}

	PdfArray getMatrix () {
		return matrix;
	}

	public PdfIndirectReference getIndirectReference () {
		if ( thisReference == null /* && writer != null */ ) {
			thisReference = writer.getPdfIndirectReference ();
		}
		return thisReference;
	}

	public void beginVariableText () {
		content.append ( "/Tx BMC " );
	}

	public void endVariableText () {
		content.append ( "EMC " );
	}

	PdfObject getResources () {
		return getPageResources ().getResources ();
	}

	public PdfStream getFormXObject ( int compressionLevel ) throws IOException {
		return new PdfFormXObject ( this, compressionLevel );
	}

	public PdfContentByte getDuplicate () {
		PdfTemplate tpl = new PdfTemplate ();
		tpl.writer = writer;
		tpl.pdf = pdf;
		tpl.thisReference = thisReference;
		tpl.pageResources = pageResources;
		tpl.bBox = new Rectangle ( bBox );
		tpl.group = group;
		tpl.layer = layer;
		if ( matrix != null ) {
			tpl.matrix = new PdfArray ( matrix );
		}
		tpl.separator = separator;
		tpl.additional = additional;
		return tpl;
	}

	public int getType () {
		return type;
	}

	PageResources getPageResources () {
		return pageResources;
	}

	public PdfTransparencyGroup getGroup () {
		return this.group;
	}

	public void setGroup ( PdfTransparencyGroup group ) {
		this.group = group;
	}

	public PdfDictionary getAdditional () {
		return this.additional;
	}

	public void setAdditional ( PdfDictionary additional ) {
		this.additional = additional;
	}

	public PdfIndirectReference getCurrentPage () {
		return pageReference == null ? writer.getCurrentPage () : pageReference;
	}

	public PdfIndirectReference getPageReference () {
		return pageReference;
	}

	public void setPageReference ( PdfIndirectReference pageReference ) {
		this.pageReference = pageReference;
	}

	public boolean isContentTagged () {
		return contentTagged;
	}

	public void setContentTagged ( boolean contentTagged ) {
		this.contentTagged = contentTagged;
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
