/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.HashMap;


public class PdfPCell extends Rectangle implements IAccessibleElement {

	protected Phrase phrase;

	protected PdfName role = PdfName.TD;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected AccessibleElementId id = new AccessibleElementId ();

	protected ArrayList<PdfPHeaderCell> headers = null;

	private ColumnText column = new ColumnText ( null );

	private int verticalAlignment = Element.ALIGN_TOP;

	private float paddingLeft = 2;

	private float paddingRight = 2;

	private float paddingTop = 2;

	private float paddingBottom = 2;

	private float fixedHeight = 0;

	private float minimumHeight;

	private boolean noWrap = false;

	private PdfPTable table;

	private int colspan = 1;

	private int rowspan = 1;

	private Image image;

	private PdfPCellEvent cellEvent;

	private boolean useDescender = false;

	private boolean useBorderPadding = false;

	private int rotation;

	public PdfPCell () {
		super ( 0, 0, 0, 0 );
		borderWidth = 0.5f;
		border = BOX;
		column.setLeading ( 0, 1 );
	}

	public PdfPCell ( Phrase phrase ) {
		super ( 0, 0, 0, 0 );
		borderWidth = 0.5f;
		border = BOX;
		column.addText ( this.phrase = phrase );
		column.setLeading ( 0, 1 );
	}

	public PdfPCell ( Image image ) {
		this ( image, false );
	}

	public PdfPCell ( Image image, boolean fit ) {
		super ( 0, 0, 0, 0 );
		borderWidth = 0.5f;
		border = BOX;
		column.setLeading ( 0, 1 );
		if ( fit ) {
			this.image = image;
			setPadding ( borderWidth / 2 );
		} else {
			image.setScaleToFitLineWhenOverflow ( false );
			column.addText ( this.phrase = new Phrase ( new Chunk ( image, 0, 0, true ) ) );
			setPadding ( 0 );
		}
	}

	public PdfPCell ( PdfPTable table ) {
		this ( table, null );
	}

	public PdfPCell ( PdfPTable table, PdfPCell style ) {
		super ( 0, 0, 0, 0 );
		borderWidth = 0.5f;
		border = BOX;
		column.setLeading ( 0, 1 );
		this.table = table;
		table.setWidthPercentage ( 100 );
		table.setExtendLastRow ( true );
		column.addElement ( table );
		if ( style != null ) {
			cloneNonPositionParameters ( style );
			verticalAlignment = style.verticalAlignment;
			paddingLeft = style.paddingLeft;
			paddingRight = style.paddingRight;
			paddingTop = style.paddingTop;
			paddingBottom = style.paddingBottom;
			colspan = style.colspan;
			rowspan = style.rowspan;
			cellEvent = style.cellEvent;
			useDescender = style.useDescender;
			useBorderPadding = style.useBorderPadding;
			rotation = style.rotation;
		} else
			setPadding ( 0 );
	}

	public PdfPCell ( PdfPCell cell ) {
		super ( cell.llx, cell.lly, cell.urx, cell.ury );
		cloneNonPositionParameters ( cell );
		verticalAlignment = cell.verticalAlignment;
		paddingLeft = cell.paddingLeft;
		paddingRight = cell.paddingRight;
		paddingTop = cell.paddingTop;
		paddingBottom = cell.paddingBottom;
		phrase = cell.phrase;
		fixedHeight = cell.fixedHeight;
		minimumHeight = cell.minimumHeight;
		noWrap = cell.noWrap;
		colspan = cell.colspan;
		rowspan = cell.rowspan;
		if ( cell.table != null )
			table = new PdfPTable ( cell.table );
		image = Image.getInstance ( cell.image );
		cellEvent = cell.cellEvent;
		useDescender = cell.useDescender;
		column = ColumnText.duplicate ( cell.column );
		useBorderPadding = cell.useBorderPadding;
		rotation = cell.rotation;
		id = cell.id;
		role = cell.role;
		if ( cell.accessibleAttributes != null )
			accessibleAttributes = new HashMap<> ( cell.accessibleAttributes );
		headers = cell.headers;
	}

	public void addElement ( Element element ) {
		if ( table != null ) {
			table = null;
			column.setText ( null );
		}
		if ( element instanceof PdfPTable ) {
			( (PdfPTable) element ).setSplitLate ( false );
		} else if ( element instanceof PdfDiv ) {
			for ( Element divChildElement : ( (PdfDiv) element ).getContent () ) {
				if ( divChildElement instanceof PdfPTable ) {
					( (PdfPTable) divChildElement ).setSplitLate ( false );
				}
			}
		}
		column.addElement ( element );
	}

	public Phrase getPhrase () {
		return phrase;
	}

	public void setPhrase ( Phrase phrase ) {
		table = null;
		image = null;
		column.setText ( this.phrase = phrase );
	}

	public int getHorizontalAlignment () {
		return column.getAlignment ();
	}

	public void setHorizontalAlignment ( int horizontalAlignment ) {
		column.setAlignment ( horizontalAlignment );
	}

	public int getVerticalAlignment () {
		return verticalAlignment;
	}

	public void setVerticalAlignment ( int verticalAlignment ) {
		if ( table != null )
			table.setExtendLastRow ( verticalAlignment == Element.ALIGN_TOP );
		this.verticalAlignment = verticalAlignment;
	}

	public float getEffectivePaddingLeft () {
		if ( isUseBorderPadding () ) {
			float border = getBorderWidthLeft () / ( isUseVariableBorders () ? 1f : 2f );
			return paddingLeft + border;
		}
		return paddingLeft;
	}

	public float getPaddingLeft () {
		return paddingLeft;
	}

	public void setPaddingLeft ( float paddingLeft ) {
		this.paddingLeft = paddingLeft;
	}

	public float getEffectivePaddingRight () {
		if ( isUseBorderPadding () ) {
			float border = getBorderWidthRight () / ( isUseVariableBorders () ? 1f : 2f );
			return paddingRight + border;
		}
		return paddingRight;
	}

	public float getPaddingRight () {
		return paddingRight;
	}

	public void setPaddingRight ( float paddingRight ) {
		this.paddingRight = paddingRight;
	}

	public float getEffectivePaddingTop () {
		if ( isUseBorderPadding () ) {
			float border = getBorderWidthTop () / ( isUseVariableBorders () ? 1f : 2f );
			return paddingTop + border;
		}
		return paddingTop;
	}

	public float getPaddingTop () {
		return paddingTop;
	}

	public void setPaddingTop ( float paddingTop ) {
		this.paddingTop = paddingTop;
	}

	public float getEffectivePaddingBottom () {
		if ( isUseBorderPadding () ) {
			float border = getBorderWidthBottom () / ( isUseVariableBorders () ? 1f : 2f );
			return paddingBottom + border;
		}
		return paddingBottom;
	}

	public float getPaddingBottom () {
		return paddingBottom;
	}

	public void setPaddingBottom ( float paddingBottom ) {
		this.paddingBottom = paddingBottom;
	}

	public void setPadding ( float padding ) {
		paddingBottom = padding;
		paddingTop = padding;
		paddingLeft = padding;
		paddingRight = padding;
	}

	public boolean isUseBorderPadding () {
		return useBorderPadding;
	}

	public float getLeading () {
		return column.getLeading ();
	}

	public float getIndent () {
		return column.getIndent ();
	}

	public void setIndent ( float indent ) {
		column.setIndent ( indent );
	}

	public float getFixedHeight () {
		return fixedHeight;
	}

	public void setFixedHeight ( float fixedHeight ) {
		this.fixedHeight = fixedHeight;
		minimumHeight = 0;
	}

	public boolean hasFixedHeight () {
		return getFixedHeight () > 0;
	}

	public float getMinimumHeight () {
		return minimumHeight;
	}

	public void setMinimumHeight ( float minimumHeight ) {
		this.minimumHeight = minimumHeight;
		fixedHeight = 0;
	}

	public boolean hasMinimumHeight () {
		return getMinimumHeight () > 0;
	}

	public boolean isNoWrap () {
		return noWrap;
	}

	public PdfPTable getTable () {
		return table;
	}

	public int getColspan () {
		return colspan;
	}

	public void setColspan ( int colspan ) {
		this.colspan = colspan;
	}

	public int getRowspan () {
		return rowspan;
	}

	public void setRowspan ( int rowspan ) {
		this.rowspan = rowspan;
	}

	public int getRunDirection () {
		return column.getRunDirection ();
	}

	public void setRunDirection ( int runDirection ) {
		column.setRunDirection ( runDirection );
	}

	public Image getImage () {
		return image;
	}

	public void setImage ( Image image ) {
		column.setText ( null );
		table = null;
		this.image = image;
	}

	public PdfPCellEvent getCellEvent () {
		return cellEvent;
	}

	public boolean isUseDescender () {
		return useDescender;
	}

	public void setUseDescender ( boolean useDescender ) {
		this.useDescender = useDescender;
	}

	public ColumnText getColumn () {
		return column;
	}

	public void setColumn ( ColumnText column ) {
		this.column = column;
	}

	@Override
	public int getRotation () {
		return rotation;
	}

	public void setRotation ( int rotation ) {
		rotation %= 360;
		if ( rotation < 0 )
			rotation += 360;
		if ( rotation % 90 != 0 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "rotation.must.be.a.multiple.of.90" ) );
		this.rotation = rotation;
	}

	public float getMaxHeight () {
		boolean pivoted = getRotation () == 90 || getRotation () == 270;
		Image img = getImage ();
		if ( img != null ) {
			img.scalePercent ( 100 );
			float refWidth = pivoted ? img.getScaledHeight () : img.getScaledWidth ();
			float scale = ( getRight () - getEffectivePaddingRight ()
							- getEffectivePaddingLeft () - getLeft () ) / refWidth;
			img.scalePercent ( scale * 100 );
			float refHeight = pivoted ? img.getScaledWidth () : img.getScaledHeight ();
			setBottom ( getTop () - getEffectivePaddingTop () - getEffectivePaddingBottom () - refHeight );
		} else {
			if ( ( pivoted && hasFixedHeight () ) || getColumn () == null )
				setBottom ( getTop () - getFixedHeight () );
			else {
				ColumnText ct = ColumnText.duplicate ( getColumn () );
				float right, top, left, bottom;
				if ( pivoted ) {
					right = PdfPRow.RIGHT_LIMIT;
					top = getRight () - getEffectivePaddingRight ();
					left = 0;
					bottom = getLeft () + getEffectivePaddingLeft ();
				} else {
					right = isNoWrap () ? PdfPRow.RIGHT_LIMIT : getRight () - getEffectivePaddingRight ();
					top = getTop () - getEffectivePaddingTop ();
					left = getLeft () + getEffectivePaddingLeft ();
					bottom = hasFixedHeight () ? getTop () + getEffectivePaddingBottom () - getFixedHeight () : PdfPRow.BOTTOM_LIMIT;
				}
				PdfPRow.setColumn ( ct, left, bottom, right, top );
				try {
					ct.go ( true );
				} catch ( DocumentException e ) {
					throw new ExceptionConverter ( e );
				}
				if ( pivoted )
					setBottom ( getTop () - getEffectivePaddingTop () - getEffectivePaddingBottom () - ct.getFilledWidth () );
				else {
					float yLine = ct.getYLine ();
					if ( isUseDescender () )
						yLine += ct.getDescender ();
					setBottom ( yLine - getEffectivePaddingBottom () );
				}
			}
		}
		float height = getHeight ();
		if ( height == getEffectivePaddingTop () + getEffectivePaddingBottom () )
			height = 0;
		if ( hasFixedHeight () )
			height = getFixedHeight ();
		else if ( hasMinimumHeight () && height < getMinimumHeight () )
			height = getMinimumHeight ();
		return height;
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

	public ArrayList<PdfPHeaderCell> getHeaders () {
		return headers;
	}
}
