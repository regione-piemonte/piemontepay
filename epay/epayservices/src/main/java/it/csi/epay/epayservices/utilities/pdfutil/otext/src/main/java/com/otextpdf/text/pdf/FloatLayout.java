/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Spaceable;

import java.util.ArrayList;
import java.util.List;


public class FloatLayout {

	protected final ColumnText compositeColumn;

	protected final List<Element> content;

	protected final boolean useAscender;

	protected float maxY;

	protected float minY;

	protected float leftX;

	protected float rightX;

	protected float yLine;

	protected float floatLeftX;

	protected float floatRightX;

	protected float filledWidth;

	public FloatLayout ( List<Element> elements, boolean useAscender ) {
		compositeColumn = new ColumnText ( null );
		compositeColumn.setUseAscender ( useAscender );
		this.useAscender = useAscender;
		content = elements;
	}

	public float getYLine () {
		return yLine;
	}

	public float getFilledWidth () {
		return filledWidth;
	}

	public void setSimpleColumn ( final float llx, final float lly, final float urx, final float ury ) {
		leftX = Math.min ( llx, urx );
		maxY = Math.max ( lly, ury );
		minY = Math.min ( lly, ury );
		rightX = Math.max ( llx, urx );
		floatLeftX = leftX;
		floatRightX = rightX;
		yLine = maxY;
		filledWidth = 0;
	}

	public int layout ( PdfContentByte canvas, boolean simulate ) throws DocumentException {
		compositeColumn.setCanvas ( canvas );
		int status = ColumnText.NO_MORE_TEXT;

		ArrayList<Element> floatingElements = new ArrayList<> ();
		List<Element> content = simulate ? new ArrayList<> ( this.content ) : this.content;

		while ( !content.isEmpty () ) {
			if ( content.get ( 0 ) instanceof PdfDiv ) {
				PdfDiv floatingElement = (PdfDiv) content.get ( 0 );
				if ( floatingElement.getFloatType () == PdfDiv.FloatType.LEFT || floatingElement.getFloatType () == PdfDiv.FloatType.RIGHT ) {
					floatingElements.add ( floatingElement );
					content.remove ( 0 );
				} else {
					if ( !floatingElements.isEmpty () ) {
						status = floatingLayout ( floatingElements, simulate );
						if ( ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
							break;
						}
					}

					content.remove ( 0 );

					status = floatingElement.layout ( canvas, useAscender, true, floatLeftX, minY, floatRightX, yLine );

					if ( !simulate ) {
						canvas.openMCBlock ( floatingElement );
						status = floatingElement.layout ( canvas, useAscender, false, floatLeftX, minY, floatRightX, yLine );
						canvas.closeMCBlock ( floatingElement );
					}

					if ( floatingElement.getActualWidth () > filledWidth ) {
						filledWidth = floatingElement.getActualWidth ();
					}
					if ( ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
						content.add ( 0, floatingElement );
						yLine = floatingElement.getYLine ();
						break;
					} else {
						yLine -= floatingElement.getActualHeight ();
					}
				}
			} else {
				floatingElements.add ( content.get ( 0 ) );
				content.remove ( 0 );
			}
		}

		if ( ( status & ColumnText.NO_MORE_TEXT ) != 0 && !floatingElements.isEmpty () ) {
			status = floatingLayout ( floatingElements, simulate );
		}

		content.addAll ( 0, floatingElements );

		return status;
	}

	private int floatingLayout ( List<Element> floatingElements, boolean simulate ) throws DocumentException {
		int status = ColumnText.NO_MORE_TEXT;
		float minYLine = yLine;
		float leftWidth = 0;
		float rightWidth = 0;

		ColumnText currentCompositeColumn = compositeColumn;
		if ( simulate ) {
			currentCompositeColumn = ColumnText.duplicate ( compositeColumn );
		}

		while ( !floatingElements.isEmpty () ) {
			Element nextElement = floatingElements.get ( 0 );
			floatingElements.remove ( 0 );
			if ( nextElement instanceof PdfDiv ) {
				PdfDiv floatingElement = (PdfDiv) nextElement;
				status = floatingElement.layout ( compositeColumn.getCanvas (), useAscender, true, floatLeftX, minY, floatRightX, yLine );
				if ( ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
					yLine = minYLine;
					floatLeftX = leftX;
					floatRightX = rightX;
					status = floatingElement.layout ( compositeColumn.getCanvas (), useAscender, true, floatLeftX, minY, floatRightX, yLine );
					if ( ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
						floatingElements.add ( 0, floatingElement );
						break;
					}
				}
				if ( floatingElement.getFloatType () == PdfDiv.FloatType.LEFT ) {
					status = floatingElement.layout ( compositeColumn.getCanvas (), useAscender, simulate, floatLeftX, minY, floatRightX, yLine );
					floatLeftX += floatingElement.getActualWidth ();
					leftWidth += floatingElement.getActualWidth ();
				} else if ( floatingElement.getFloatType () == PdfDiv.FloatType.RIGHT ) {
					status = floatingElement.layout ( compositeColumn.getCanvas (), useAscender, simulate,
									floatRightX - floatingElement.getActualWidth () - 0.01f, minY, floatRightX, yLine );
					floatRightX -= floatingElement.getActualWidth ();
					rightWidth += floatingElement.getActualWidth ();
				}
				minYLine = Math.min ( minYLine, yLine - floatingElement.getActualHeight () );
			} else {
				if ( nextElement instanceof Spaceable ) {
					yLine -= ( (Spaceable) nextElement ).getSpacingBefore ();
				}
				if ( simulate ) {
					if ( nextElement instanceof PdfPTable )
						currentCompositeColumn.addElement ( new PdfPTable ( (PdfPTable) nextElement ) );
					else
						currentCompositeColumn.addElement ( nextElement );
				} else {
					currentCompositeColumn.addElement ( nextElement );
				}

				if ( yLine > minYLine )
					currentCompositeColumn.setSimpleColumn ( floatLeftX, yLine, floatRightX, minYLine );
				else
					currentCompositeColumn.setSimpleColumn ( floatLeftX, yLine, floatRightX, minY );

				currentCompositeColumn.setFilledWidth ( 0 );

				status = currentCompositeColumn.go ( simulate );
				if ( yLine > minYLine && ( floatLeftX > leftX || floatRightX < rightX ) && ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
					yLine = minYLine;
					floatLeftX = leftX;
					floatRightX = rightX;
					if ( leftWidth != 0 && rightWidth != 0 ) {
						filledWidth = rightX - leftX;
					} else {
						if ( leftWidth > filledWidth ) {
							filledWidth = leftWidth;
						}
						if ( rightWidth > filledWidth ) {
							filledWidth = rightWidth;
						}
					}

					leftWidth = 0;
					rightWidth = 0;
					if ( simulate && nextElement instanceof PdfPTable ) {
						currentCompositeColumn.addElement ( new PdfPTable ( (PdfPTable) nextElement ) );
					}

					currentCompositeColumn.setSimpleColumn ( floatLeftX, yLine, floatRightX, minY );
					status = currentCompositeColumn.go ( simulate );
					minYLine = currentCompositeColumn.getYLine () + currentCompositeColumn.getDescender ();
					yLine = minYLine;
					if ( currentCompositeColumn.getFilledWidth () > filledWidth ) {
						filledWidth = currentCompositeColumn.getFilledWidth ();
					}
				} else {
					if ( rightWidth > 0 ) {
						rightWidth += currentCompositeColumn.getFilledWidth ();
					} else if ( leftWidth > 0 ) {
						leftWidth += currentCompositeColumn.getFilledWidth ();
					} else if ( currentCompositeColumn.getFilledWidth () > filledWidth ) {
						filledWidth = currentCompositeColumn.getFilledWidth ();
					}
					minYLine = Math.min ( currentCompositeColumn.getYLine () + currentCompositeColumn.getDescender (), minYLine );
					yLine = currentCompositeColumn.getYLine () + currentCompositeColumn.getDescender ();
				}

				if ( ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
					if ( !simulate ) {
						floatingElements.addAll ( 0, currentCompositeColumn.getCompositeElements () );
						currentCompositeColumn.getCompositeElements ().clear ();
					} else {
						floatingElements.add ( 0, nextElement );
						currentCompositeColumn.setText ( null );
					}
					break;
				} else {
					currentCompositeColumn.setText ( null );
				}
			}
		}

		if ( leftWidth != 0 && rightWidth != 0 ) {
			filledWidth = rightX - leftX;
		} else {
			if ( leftWidth > filledWidth ) {
				filledWidth = leftWidth;
			}
			if ( rightWidth > filledWidth ) {
				filledWidth = rightWidth;
			}
		}

		yLine = minYLine;
		floatLeftX = leftX;
		floatRightX = rightX;

		return status;
	}
}
