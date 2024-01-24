/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class RectangleReadOnly extends Rectangle {

	public RectangleReadOnly ( final float llx, final float lly, final float urx, final float ury ) {
		super ( llx, lly, urx, ury );
	}

	public RectangleReadOnly ( final float llx, final float lly, final float urx, final float ury, final int rotation ) {
		super ( llx, lly, urx, ury );
		super.setRotation ( rotation );
	}

	public RectangleReadOnly ( final float urx, final float ury ) {
		super ( 0, 0, urx, ury );
	}

	public RectangleReadOnly ( final float urx, final float ury, final int rotation ) {
		super ( 0, 0, urx, ury );
		super.setRotation ( rotation );
	}

	public RectangleReadOnly ( final Rectangle rect ) {
		super ( rect.llx, rect.lly, rect.urx, rect.ury );
		super.cloneNonPositionParameters ( rect );
	}

	private void throwReadOnlyError () {
		throw new UnsupportedOperationException ( MessageLocalization.getComposedMessage ( "rectanglereadonly.this.rectangle.is.read.only" ) );
	}

	@Override
	public void setRotation ( final int rotation ) {
		throwReadOnlyError ();
	}

	@Override
	public void setLeft ( final float llx ) {
		throwReadOnlyError ();
	}

	@Override
	public void setRight ( final float urx ) {
		throwReadOnlyError ();
	}

	@Override
	public void setTop ( final float ury ) {
		throwReadOnlyError ();
	}

	@Override
	public void setBottom ( final float lly ) {
		throwReadOnlyError ();
	}

	@Override
	public void normalize () {
		throwReadOnlyError ();
	}

	@Override
	public void setBackgroundColor ( final BaseColor value ) {
		throwReadOnlyError ();
	}

	@Override
	public void setBorder ( final int border ) {
		throwReadOnlyError ();
	}

	@Override
	public void disableBorderSide ( final int side ) {
		throwReadOnlyError ();
	}

	@Override
	public void setBorderWidth ( final float borderWidth ) {
		throwReadOnlyError ();
	}

	@Override
	public void setBorderColor ( final BaseColor borderColor ) {
		throwReadOnlyError ();
	}

	@Override
	public void cloneNonPositionParameters ( final Rectangle rect ) {
		throwReadOnlyError ();
	}

	@Override
	public String toString () {
		return "RectangleReadOnly: " + getWidth () +
						'x' +
						getHeight () +
						" (rot: " +
						rotation +
						" degrees)";
	}
}
