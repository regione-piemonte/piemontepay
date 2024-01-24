/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.util.ArrayList;
import java.util.List;


public class Rectangle implements Element {

	public static final int UNDEFINED = -1;

	public static final int TOP = 1;

	public static final int BOTTOM = 2;

	public static final int LEFT = 4;

	public static final int RIGHT = 8;

	public static final int NO_BORDER = 0;

	public static final int BOX = TOP + BOTTOM + LEFT + RIGHT;

	protected float llx;

	protected float lly;

	protected float urx;

	protected float ury;

	protected int rotation = 0;

	protected BaseColor backgroundColor = null;

	protected int border = UNDEFINED;

	protected boolean useVariableBorders = false;

	protected float borderWidth = UNDEFINED;

	protected float borderWidthLeft = UNDEFINED;

	protected float borderWidthRight = UNDEFINED;

	protected float borderWidthTop = UNDEFINED;

	protected float borderWidthBottom = UNDEFINED;

	protected BaseColor borderColor = null;

	protected BaseColor borderColorLeft = null;

	protected BaseColor borderColorRight = null;

	protected BaseColor borderColorTop = null;

	protected BaseColor borderColorBottom = null;

	public Rectangle ( final float llx, final float lly, final float urx, final float ury ) {
		this.llx = llx;
		this.lly = lly;
		this.urx = urx;
		this.ury = ury;
	}

	public Rectangle ( final float llx, final float lly, final float urx, final float ury, final int rotation ) {
		this ( llx, lly, urx, ury );
		setRotation ( rotation );
	}

	public Rectangle ( final float urx, final float ury ) {
		this ( 0, 0, urx, ury );
	}

	public Rectangle ( final float urx, final float ury, final int rotation ) {
		this ( 0, 0, urx, ury, rotation );
	}

	public Rectangle ( final Rectangle rect ) {
		this ( rect.llx, rect.lly, rect.urx, rect.ury );
		cloneNonPositionParameters ( rect );
	}

	public Rectangle ( it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.awt.geom.Rectangle rect ) {
		this ( (float) rect.getX (), (float) rect.getY (), (float) ( rect.getX () + rect.getWidth () ), (float) ( rect.getY () + rect.getHeight () ) );
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( this );
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return Element.RECTANGLE;
	}

	public List<Chunk> getChunks () {
		return new ArrayList<> ();
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return false;
	}

	public float getLeft () {
		return llx;
	}

	public void setLeft ( final float llx ) {
		this.llx = llx;
	}

	public float getLeft ( final float margin ) {
		return llx + margin;
	}

	public float getRight () {
		return urx;
	}

	public void setRight ( final float urx ) {
		this.urx = urx;
	}

	public float getRight ( final float margin ) {
		return urx - margin;
	}

	public float getWidth () {
		return urx - llx;
	}

	public float getTop () {
		return ury;
	}

	public void setTop ( final float ury ) {
		this.ury = ury;
	}

	public float getTop ( final float margin ) {
		return ury - margin;
	}

	public float getBottom () {
		return lly;
	}

	public void setBottom ( final float lly ) {
		this.lly = lly;
	}

	public float getBottom ( final float margin ) {
		return lly + margin;
	}

	public float getHeight () {
		return ury - lly;
	}

	public void normalize () {
		if ( llx > urx ) {
			float a = llx;
			llx = urx;
			urx = a;
		}
		if ( lly > ury ) {
			float a = lly;
			lly = ury;
			ury = a;
		}
	}

	public int getRotation () {
		return rotation;
	}

	public void setRotation ( final int rotation ) {
		this.rotation = rotation % 360;
		switch ( this.rotation ) {
		case 90:
		case 180:
		case 270:
			break;
		default:
			this.rotation = 0;
		}
	}

	public Rectangle rotate () {
		Rectangle rect = new Rectangle ( lly, llx, ury, urx );
		rect.setRotation ( rotation + 90 );
		return rect;
	}

	public BaseColor getBackgroundColor () {
		return backgroundColor;
	}

	public void setBackgroundColor ( final BaseColor backgroundColor ) {
		this.backgroundColor = backgroundColor;
	}

	public int getBorder () {
		return border;
	}

	public void setBorder ( final int border ) {
		this.border = border;
	}

	public boolean hasBorders () {
		switch ( border ) {
		case UNDEFINED:
		case NO_BORDER:
			return false;
		default:
			return borderWidth > 0 || borderWidthLeft > 0
							|| borderWidthRight > 0 || borderWidthTop > 0 || borderWidthBottom > 0;
		}
	}

	public boolean hasBorder ( final int type ) {
		if ( border == UNDEFINED )
			return false;
		return ( border & type ) == type;
	}

	public boolean isUseVariableBorders () {
		return useVariableBorders;
	}

	public void disableBorderSide ( final int side ) {
		if ( border == UNDEFINED )
			border = 0;
		border &= ~side;
	}

	public float getBorderWidth () {
		return borderWidth;
	}

	public void setBorderWidth ( final float borderWidth ) {
		this.borderWidth = borderWidth;
	}

	private float getVariableBorderWidth ( final float variableWidthValue, final int side ) {
		if ( ( border & side ) != 0 )
			return variableWidthValue != UNDEFINED ? variableWidthValue : borderWidth;
		return 0;
	}

	public float getBorderWidthLeft () {
		return getVariableBorderWidth ( borderWidthLeft, LEFT );
	}

	public float getBorderWidthRight () {
		return getVariableBorderWidth ( borderWidthRight, RIGHT );
	}

	public float getBorderWidthTop () {
		return getVariableBorderWidth ( borderWidthTop, TOP );
	}

	public float getBorderWidthBottom () {
		return getVariableBorderWidth ( borderWidthBottom, BOTTOM );
	}

	public BaseColor getBorderColor () {
		return borderColor;
	}

	public void setBorderColor ( final BaseColor borderColor ) {
		this.borderColor = borderColor;
	}

	public BaseColor getBorderColorLeft () {
		if ( borderColorLeft == null )
			return borderColor;
		return borderColorLeft;
	}

	public BaseColor getBorderColorRight () {
		if ( borderColorRight == null )
			return borderColor;
		return borderColorRight;
	}

	public BaseColor getBorderColorTop () {
		if ( borderColorTop == null )
			return borderColor;
		return borderColorTop;
	}

	public BaseColor getBorderColorBottom () {
		if ( borderColorBottom == null )
			return borderColor;
		return borderColorBottom;
	}

	public Rectangle rectangle ( final float top, final float bottom ) {
		Rectangle tmp = new Rectangle ( this );
		if ( getTop () > top ) {
			tmp.setTop ( top );
			tmp.disableBorderSide ( TOP );
		}
		if ( getBottom () < bottom ) {
			tmp.setBottom ( bottom );
			tmp.disableBorderSide ( BOTTOM );
		}
		return tmp;
	}

	public void cloneNonPositionParameters ( final Rectangle rect ) {
		this.rotation = rect.rotation;
		this.backgroundColor = rect.backgroundColor;
		this.border = rect.border;
		this.useVariableBorders = rect.useVariableBorders;
		this.borderWidth = rect.borderWidth;
		this.borderWidthLeft = rect.borderWidthLeft;
		this.borderWidthRight = rect.borderWidthRight;
		this.borderWidthTop = rect.borderWidthTop;
		this.borderWidthBottom = rect.borderWidthBottom;
		this.borderColor = rect.borderColor;
		this.borderColorLeft = rect.borderColorLeft;
		this.borderColorRight = rect.borderColorRight;
		this.borderColorTop = rect.borderColorTop;
		this.borderColorBottom = rect.borderColorBottom;
	}

	@Override
	public String toString () {
		return "Rectangle: " + getWidth () +
						'x' +
						getHeight () +
						" (rot: " +
						rotation +
						" degrees)";
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( obj instanceof Rectangle ) {
			Rectangle other = (Rectangle) obj;
			return other.llx == this.llx && other.lly == this.lly && other.urx == this.urx && other.ury == this.ury && other.rotation == this.rotation;
		} else {
			return false;
		}
	}
}
