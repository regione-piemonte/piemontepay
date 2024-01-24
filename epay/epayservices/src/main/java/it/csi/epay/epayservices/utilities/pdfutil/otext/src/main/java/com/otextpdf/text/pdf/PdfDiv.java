/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.AffineTransform;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ElementListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Spaceable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PdfDiv implements Element, Spaceable, IAccessibleElement {

	private final Float percentageHeight = null;

	private final Float percentageWidth = null;

	private final FloatType floatType = FloatType.NONE;

	protected PdfName role = PdfName.DIV;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected AccessibleElementId id = new AccessibleElementId ();

	protected float spacingBefore;

	protected float spacingAfter;

	private ArrayList<Element> content;

	private Float left = null;

	private Float top = null;

	private Float right = null;

	private Float bottom = null;

	private Float width = null;

	private Float height = null;

	private float contentWidth = 0;

	private float contentHeight = 0;

	private float paddingLeft = 0;

	private float paddingRight = 0;

	private float paddingTop = 0;

	private float paddingBottom = 0;

	private PositionType position = PositionType.STATIC;

	private FloatLayout floatLayout = null;

	private float yLine;

	private BaseColor backgroundColor = null;

	public PdfDiv () {
		content = new ArrayList<> ();
	}

	public float getActualHeight () {
		return height != null && height >= contentHeight ? height : contentHeight;
	}

	public float getActualWidth () {
		return width != null && width >= contentWidth ? width : contentWidth;
	}

	public BaseColor getBackgroundColor () {
		return backgroundColor;
	}

	public void setBackgroundColor ( BaseColor backgroundColor ) {
		this.backgroundColor = backgroundColor;
	}

	public float getYLine () {
		return yLine;
	}

	public List<Chunk> getChunks () {
		return new ArrayList<> ();
	}

	public int type () {
		return Element.DIV;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return true;
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( this );
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public float getSpacingBefore () {
		return spacingBefore;
	}

	public void setSpacingBefore ( final float spacing ) {
		this.spacingBefore = spacing;
	}

	public float getSpacingAfter () {
		return spacingAfter;
	}

	public void setSpacingAfter ( final float spacing ) {
		this.spacingAfter = spacing;
	}

	public int getTextAlignment () {
		return Element.ALIGN_UNDEFINED;
	}

	public Float getLeft () {
		return this.left;
	}

	public void setLeft ( Float left ) {
		this.left = left;
	}

	public Float getRight () {
		return this.right;
	}

	public void setRight ( Float right ) {
		this.right = right;
	}

	public Float getTop () {
		return this.top;
	}

	public void setTop ( Float top ) {
		this.top = top;
	}

	public Float getBottom () {
		return this.bottom;
	}

	public void setBottom ( Float bottom ) {
		this.bottom = bottom;
	}

	public Float getWidth () {
		return this.width;
	}

	public void setWidth ( Float width ) {
		this.width = width;
	}

	public Float getHeight () {
		return this.height;
	}

	public void setHeight ( Float height ) {
		this.height = height;
	}

	public float getPaddingLeft () {
		return paddingLeft;
	}

	public void setPaddingLeft ( float paddingLeft ) {
		this.paddingLeft = paddingLeft;
	}

	public float getPaddingRight () {
		return paddingRight;
	}

	public void setPaddingRight ( float paddingRight ) {
		this.paddingRight = paddingRight;
	}

	public float getPaddingTop () {
		return paddingTop;
	}

	public void setPaddingTop ( float paddingTop ) {
		this.paddingTop = paddingTop;
	}

	public float getPaddingBottom () {
		return paddingBottom;
	}

	public void setPaddingBottom ( float paddingBottom ) {
		this.paddingBottom = paddingBottom;
	}

	public FloatType getFloatType () {
		return floatType;
	}

	public PositionType getPosition () {
		return position;
	}

	public void setPosition ( PositionType position ) {
		this.position = position;
	}

	public ArrayList<Element> getContent () {
		return content;
	}

	public void setContent ( ArrayList<Element> content ) {
		this.content = content;
	}

	public int layout ( final PdfContentByte canvas, boolean useAscender, boolean simulate, final float llx, final float lly, final float urx, final float ury )
					throws DocumentException {

		float leftX = Math.min ( llx, urx );
		float maxY = Math.max ( lly, ury );
		float minY = Math.min ( lly, ury );
		float rightX = Math.max ( llx, urx );
		yLine = maxY;
		boolean contentCutByFixedHeight = false;

		if ( width != null && width > 0 ) {
			if ( width < rightX - leftX ) {
				rightX = leftX + width;
			} else if ( width > rightX - leftX ) {
				return ColumnText.NO_MORE_COLUMN;
			}
		}

		if ( height != null && height > 0 ) {
			if ( height < maxY - minY ) {
				minY = maxY - height;
				contentCutByFixedHeight = true;
			} else if ( height > maxY - minY ) {
				return ColumnText.NO_MORE_COLUMN;
			}
		}

		if ( !simulate && position == PositionType.RELATIVE ) {
			float translationX;
			if ( left != null ) {
				translationX = left;
			} else if ( right != null ) {
				translationX = -right;
			} else {
				translationX = 0f;
			}

			float translationY;
			if ( top != null ) {
				translationY = -top;
			} else if ( bottom != null ) {
				translationY = bottom;
			} else {
				translationY = 0f;
			}
			canvas.saveState ();
			canvas.transform ( new AffineTransform ( 1f, 0, 0, 1f, translationX, translationY ) );
		}

		if ( !simulate ) {
			if ( backgroundColor != null && getActualWidth () > 0 && getActualHeight () > 0 ) {
				float backgroundWidth = getActualWidth ();
				float backgroundHeight = getActualHeight ();
				if ( width != null ) {
					backgroundWidth = width > 0 ? width : 0;
				}

				if ( height != null ) {
					backgroundHeight = height > 0 ? height : 0;
				}
				if ( backgroundWidth > 0 && backgroundHeight > 0 ) {
					Rectangle background = new Rectangle ( leftX, maxY - backgroundHeight, leftX + backgroundWidth, maxY );
					background.setBackgroundColor ( backgroundColor );
					PdfArtifact artifact = new PdfArtifact ();
					canvas.openMCBlock ( artifact );
					canvas.rectangle ( background );
					canvas.closeMCBlock ( artifact );
				}
			}
		}

		contentWidth = 0;
		contentHeight = 0;

		minY += paddingBottom;
		leftX += paddingLeft;
		rightX -= paddingRight;

		yLine -= paddingTop;

		int status = ColumnText.NO_MORE_TEXT;

		if ( !content.isEmpty () ) {
			if ( this.floatLayout == null ) {
				ArrayList<Element> floatingElements = new ArrayList<> ( content );
				floatLayout = new FloatLayout ( floatingElements, useAscender );
			}

			floatLayout.setSimpleColumn ( leftX, minY, rightX, yLine );
			status = floatLayout.layout ( canvas, simulate );
			yLine = floatLayout.getYLine ();
			if ( contentWidth < floatLayout.getFilledWidth () ) {
				contentWidth = floatLayout.getFilledWidth ();
			}
		}

		if ( !simulate && position == PositionType.RELATIVE ) {
			canvas.restoreState ();
		}

		yLine -= paddingBottom;
		contentHeight = maxY - yLine;

		contentWidth += paddingLeft + paddingRight;

		return contentCutByFixedHeight ? ColumnText.NO_MORE_TEXT : status;
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

	public enum FloatType {NONE, LEFT, RIGHT}


	public enum PositionType {STATIC, ABSOLUTE, FIXED, RELATIVE}
}
