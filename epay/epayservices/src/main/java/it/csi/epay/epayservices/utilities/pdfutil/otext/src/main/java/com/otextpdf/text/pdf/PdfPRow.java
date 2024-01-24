/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.Arrays;
import java.util.HashMap;


public class PdfPRow implements IAccessibleElement {

	public static final float BOTTOM_LIMIT = -( 1 << 30 );

	public static final float RIGHT_LIMIT = 20000;

	private final Logger LOGGER = LoggerFactory.getLogger ( PdfPRow.class );

	public boolean mayNotBreak = false;

	protected PdfPCell[] cells;

	protected float[] widths;

	protected float[] extraHeights;

	protected float maxHeight = 0;

	protected boolean calculated = false;

	protected boolean adjusted = false;

	protected PdfName role = PdfName.TR;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected AccessibleElementId id = new AccessibleElementId ();

	private int[] canvasesPos;

	public PdfPRow ( PdfPCell[] cells ) {
		this ( cells, null );
	}

	public PdfPRow ( PdfPCell[] cells, PdfPRow source ) {
		this.cells = cells;
		widths = new float[cells.length];
		initExtraHeights ();
		if ( source != null ) {
			this.id = source.id;
			this.role = source.role;
			if ( source.accessibleAttributes != null )
				this.accessibleAttributes = new HashMap<> ( source.accessibleAttributes );
		}
	}

	public PdfPRow ( PdfPRow row ) {
		mayNotBreak = row.mayNotBreak;
		maxHeight = row.maxHeight;
		calculated = row.calculated;
		cells = new PdfPCell[row.cells.length];
		for ( int k = 0; k < cells.length; ++k ) {
			if ( row.cells[k] != null ) {
				if ( row.cells[k] instanceof PdfPHeaderCell )
					cells[k] = new PdfPHeaderCell ( (PdfPHeaderCell) row.cells[k] );
				else
					cells[k] = new PdfPCell ( row.cells[k] );
			}
		}
		widths = new float[cells.length];
		System.arraycopy ( row.widths, 0, widths, 0, cells.length );
		initExtraHeights ();
		this.id = row.id;
		this.role = row.role;
		if ( row.accessibleAttributes != null )
			this.accessibleAttributes = new HashMap<> ( row.accessibleAttributes );
	}

	public static float setColumn ( ColumnText ct, float left, float bottom, float right, float top ) {
		if ( left > right )
			right = left;
		if ( bottom > top )
			top = bottom;
		ct.setSimpleColumn ( left, bottom, right, top );
		return top;
	}

	static private boolean isTagged ( PdfContentByte canvas ) {
		return canvas != null && canvas.writer != null && canvas.writer.isTagged ();
	}

	public void setWidths ( float[] widths ) {
		if ( widths.length != cells.length )
			return;
		System.arraycopy ( widths, 0, this.widths, 0, cells.length );
		float total = 0;
		calculated = false;
		for ( int k = 0; k < widths.length; ++k ) {
			PdfPCell cell = cells[k];

			if ( cell == null ) {
				total += widths[k];
				continue;
			}

			cell.setLeft ( total );
			int last = k + cell.getColspan ();
			for ( ; k < last; ++k )
				total += widths[k];
			--k;
			cell.setRight ( total );
			cell.setTop ( 0 );
		}
	}

	protected void initExtraHeights () {
		extraHeights = new float[cells.length];
		Arrays.fill ( extraHeights, 0 );
	}

	public void setExtraHeight ( int cell, float height ) {
		if ( cell < 0 || cell >= cells.length )
			return;
		extraHeights[cell] = height;
	}

	protected void calculateHeights () {
		maxHeight = 0;
		for ( PdfPCell cell : cells ) {
			float height;
			if ( cell == null ) {

			} else {
				height = cell.getMaxHeight ();
				if ( ( height > maxHeight ) && ( cell.getRowspan () == 1 ) )
					maxHeight = height;
			}
		}
		calculated = true;
	}

	public boolean isMayNotBreak () {
		return mayNotBreak;
	}

	public void writeBorderAndBackground ( float xPos, float yPos, float currentMaxHeight, PdfPCell cell, PdfContentByte[] canvases ) {
		BaseColor background = cell.getBackgroundColor ();
		if ( background != null || cell.hasBorders () ) {
			float right = cell.getRight () + xPos;
			float top = cell.getTop () + yPos;
			float left = cell.getLeft () + xPos;
			float bottom = top - currentMaxHeight;

			if ( background != null ) {
				PdfContentByte backgr = canvases[PdfPTable.BACKGROUNDCANVAS];
				backgr.setColorFill ( background );
				backgr.rectangle ( left, bottom, right - left, top - bottom );
				backgr.fill ();
			}
			if ( cell.hasBorders () ) {
				Rectangle newRect = new Rectangle ( left, bottom, right, top );
				newRect.cloneNonPositionParameters ( cell );
				newRect.setBackgroundColor ( null );
				PdfContentByte lineCanvas = canvases[PdfPTable.LINECANVAS];
				lineCanvas.rectangle ( newRect );
			}
		}
	}

	protected void saveAndRotateCanvases ( PdfContentByte[] canvases, float a, float b, float c, float d, float e, float f ) {
		int last = PdfPTable.TEXTCANVAS + 1;
		if ( canvasesPos == null )
			canvasesPos = new int[last * 2];
		for ( int k = 0; k < last; ++k ) {
			ByteBuffer bb = canvases[k].getInternalBuffer ();
			canvasesPos[k * 2] = bb.size ();
			canvases[k].saveState ();
			canvases[k].concatCTM ( a, b, c, d, e, f );
			canvasesPos[k * 2 + 1] = bb.size ();
		}
	}

	protected void restoreCanvases ( PdfContentByte[] canvases ) {
		int last = PdfPTable.TEXTCANVAS + 1;
		for ( int k = 0; k < last; ++k ) {
			ByteBuffer bb = canvases[k].getInternalBuffer ();
			int p1 = bb.size ();
			canvases[k].restoreState ();
			if ( p1 == canvasesPos[k * 2 + 1] )
				bb.setSize ( canvasesPos[k * 2] );
		}
	}

	public void writeCells ( int colStart, int colEnd, float xPos, float yPos, PdfContentByte[] canvases, boolean reusable ) {
		if ( !calculated )
			calculateHeights ();
		if ( colEnd < 0 )
			colEnd = cells.length;
		else
			colEnd = Math.min ( colEnd, cells.length );
		if ( colStart < 0 )
			colStart = 0;
		if ( colStart >= colEnd )
			return;

		int newStart;
		for ( newStart = colStart; newStart >= 0; --newStart ) {
			if ( cells[newStart] != null )
				break;
			if ( newStart > 0 )
				xPos -= widths[newStart - 1];
		}

		if ( newStart < 0 )
			newStart = 0;
		if ( cells[newStart] != null )
			xPos -= cells[newStart].getLeft ();

		if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
			canvases[PdfPTable.TEXTCANVAS].openMCBlock ( this );
		}
		for ( int k = newStart; k < colEnd; ++k ) {
			PdfPCell cell = cells[k];
			if ( cell == null )
				continue;
			if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
				canvases[PdfPTable.TEXTCANVAS].openMCBlock ( cell );
			}
			float currentMaxHeight = maxHeight + extraHeights[k];

			writeBorderAndBackground ( xPos, yPos, currentMaxHeight, cell, canvases );

			Image img = cell.getImage ();

			float tly = cell.getTop () + yPos - cell.getEffectivePaddingTop ();
			if ( cell.getHeight () <= currentMaxHeight ) {
				switch ( cell.getVerticalAlignment () ) {
				case Element.ALIGN_BOTTOM:
					tly = cell.getTop () + yPos - currentMaxHeight + cell.getHeight ()
									- cell.getEffectivePaddingTop ();
					break;
				case Element.ALIGN_MIDDLE:
					tly = cell.getTop () + yPos + ( cell.getHeight () - currentMaxHeight ) / 2
									- cell.getEffectivePaddingTop ();
					break;
				default:
					break;
				}
			}
			if ( img != null ) {
				if ( cell.getRotation () != 0 ) {
					img = Image.getInstance ( img );
					img.setRotation ( img.getImageRotation () + (float) ( cell.getRotation () * Math.PI / 180.0 ) );
				}
				boolean vf = false;
				if ( cell.getHeight () > currentMaxHeight ) {
					if ( !img.isScaleToFitHeight () ) {
						continue;
					}
					img.scalePercent ( 100 );
					float scale = ( currentMaxHeight - cell.getEffectivePaddingTop () - cell
									.getEffectivePaddingBottom () )
									/ img.getScaledHeight ();
					img.scalePercent ( scale * 100 );
					vf = true;
				}
				float left = cell.getLeft () + xPos
								+ cell.getEffectivePaddingLeft ();
				if ( vf ) {
					switch ( cell.getHorizontalAlignment () ) {
					case Element.ALIGN_CENTER:
						left = xPos
										+ ( cell.getLeft () + cell.getEffectivePaddingLeft ()
										+ cell.getRight ()
										- cell.getEffectivePaddingRight () - img
										.getScaledWidth () ) / 2;
						break;
					case Element.ALIGN_RIGHT:
						left = xPos + cell.getRight ()
										- cell.getEffectivePaddingRight ()
										- img.getScaledWidth ();
						break;
					default:
						break;
					}
					tly = cell.getTop () + yPos - cell.getEffectivePaddingTop ();
				}
				img.setAbsolutePosition ( left, tly - img.getScaledHeight () );
				try {
					if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
						canvases[PdfPTable.TEXTCANVAS].openMCBlock ( img );
					}
					canvases[PdfPTable.TEXTCANVAS].addImage ( img );
					if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
						canvases[PdfPTable.TEXTCANVAS].closeMCBlock ( img );
					}
				} catch ( DocumentException e ) {
					throw new ExceptionConverter ( e );
				}
			} else {
				if ( cell.getRotation () == 90 || cell.getRotation () == 270 ) {
					float netWidth = currentMaxHeight - cell.getEffectivePaddingTop () - cell.getEffectivePaddingBottom ();
					float netHeight = cell.getWidth () - cell.getEffectivePaddingLeft () - cell.getEffectivePaddingRight ();
					ColumnText ct = ColumnText.duplicate ( cell.getColumn () );
					ct.setCanvases ( canvases );
					ct.setSimpleColumn ( 0, 0, netWidth + 0.001f, -netHeight );
					try {
						ct.go ( true );
					} catch ( DocumentException e ) {
						throw new ExceptionConverter ( e );
					}
					float calcHeight = -ct.getYLine ();
					if ( netWidth <= 0 || netHeight <= 0 )
						calcHeight = 0;
					if ( calcHeight > 0 ) {
						if ( cell.isUseDescender () )
							calcHeight -= ct.getDescender ();
						if ( reusable )
							ct = ColumnText.duplicate ( cell.getColumn () );
						else
							ct = cell.getColumn ();
						ct.setCanvases ( canvases );
						ct.setSimpleColumn ( -0.003f, -0.001f, netWidth + 0.003f, calcHeight );
						float pivotX;
						float pivotY;
						if ( cell.getRotation () == 90 ) {
							pivotY = cell.getTop () + yPos - currentMaxHeight + cell.getEffectivePaddingBottom ();
							switch ( cell.getVerticalAlignment () ) {
							case Element.ALIGN_BOTTOM:
								pivotX = cell.getLeft () + xPos + cell.getWidth () - cell.getEffectivePaddingRight ();
								break;
							case Element.ALIGN_MIDDLE:
								pivotX =
												cell.getLeft () + xPos + ( cell.getWidth () + cell.getEffectivePaddingLeft () - cell.getEffectivePaddingRight () + calcHeight ) / 2;
								break;
							default: //top
								pivotX = cell.getLeft () + xPos + cell.getEffectivePaddingLeft () + calcHeight;
								break;
							}
							saveAndRotateCanvases ( canvases, 0, 1, -1, 0, pivotX, pivotY );
						} else {
							pivotY = cell.getTop () + yPos - cell.getEffectivePaddingTop ();
							switch ( cell.getVerticalAlignment () ) {
							case Element.ALIGN_BOTTOM:
								pivotX = cell.getLeft () + xPos + cell.getEffectivePaddingLeft ();
								break;
							case Element.ALIGN_MIDDLE:
								pivotX =
												cell.getLeft () + xPos + ( cell.getWidth () + cell.getEffectivePaddingLeft () - cell.getEffectivePaddingRight () - calcHeight ) / 2;
								break;
							default: //top
								pivotX = cell.getLeft () + xPos + cell.getWidth () - cell.getEffectivePaddingRight () - calcHeight;
								break;
							}
							saveAndRotateCanvases ( canvases, 0, -1, 1, 0, pivotX, pivotY );
						}
						try {
							ct.go ();
						} catch ( DocumentException e ) {
							throw new ExceptionConverter ( e );
						} finally {
							restoreCanvases ( canvases );
						}
					}
				} else {
					float fixedHeight = cell.getFixedHeight ();
					float rightLimit = cell.getRight () + xPos
									- cell.getEffectivePaddingRight ();
					float leftLimit = cell.getLeft () + xPos
									+ cell.getEffectivePaddingLeft ();
					if ( cell.isNoWrap () ) {
						switch ( cell.getHorizontalAlignment () ) {
						case Element.ALIGN_CENTER:
							rightLimit += 10000;
							leftLimit -= 10000;
							break;
						case Element.ALIGN_RIGHT:
							if ( cell.getRotation () == 180 ) {
								rightLimit += RIGHT_LIMIT;
							} else {
								leftLimit -= RIGHT_LIMIT;
							}
							break;
						default:
							if ( cell.getRotation () == 180 ) {
								leftLimit -= RIGHT_LIMIT;
							} else {
								rightLimit += RIGHT_LIMIT;
							}
							break;
						}
					}
					ColumnText ct;
					if ( reusable )
						ct = ColumnText.duplicate ( cell.getColumn () );
					else
						ct = cell.getColumn ();
					ct.setCanvases ( canvases );
					float bry = tly
									- ( currentMaxHeight
									- cell.getEffectivePaddingTop () - cell.getEffectivePaddingBottom () );
					if ( fixedHeight > 0 ) {
						if ( cell.getHeight () > currentMaxHeight ) {
							tly = cell.getTop () + yPos - cell.getEffectivePaddingTop ();
							bry = cell.getTop () + yPos - currentMaxHeight + cell.getEffectivePaddingBottom ();
						}
					}
					if ( ( tly > bry || ct.zeroHeightElement () ) && leftLimit < rightLimit ) {
						ct.setSimpleColumn ( leftLimit, bry - 0.001f, rightLimit, tly );
						if ( cell.getRotation () == 180 ) {
							float shx = leftLimit + rightLimit;
							float shy = yPos + yPos - currentMaxHeight + cell.getEffectivePaddingBottom () - cell.getEffectivePaddingTop ();
							saveAndRotateCanvases ( canvases, -1, 0, 0, -1, shx, shy );
						}
						try {
							ct.go ();
						} catch ( DocumentException e ) {
							throw new ExceptionConverter ( e );
						} finally {
							if ( cell.getRotation () == 180 ) {
								restoreCanvases ( canvases );
							}
						}
					}
				}
			}
			PdfPCellEvent evt = cell.getCellEvent ();
			if ( evt != null ) {
				Rectangle rect = new Rectangle ( cell.getLeft () + xPos, cell.getTop ()
								+ yPos - currentMaxHeight, cell.getRight () + xPos, cell.getTop ()
								+ yPos );
				evt.cellLayout ( cell, rect, canvases );
			}
			if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
				canvases[PdfPTable.TEXTCANVAS].closeMCBlock ( cell );
			}
		}
		if ( isTagged ( canvases[PdfPTable.TEXTCANVAS] ) ) {
			canvases[PdfPTable.TEXTCANVAS].closeMCBlock ( this );
		}
	}

	public boolean isCalculated () {
		return calculated;
	}

	public float getMaxHeights () {
		if ( !calculated )
			calculateHeights ();
		return maxHeight;
	}

	//end add

	public void setMaxHeights ( float maxHeight ) {
		this.maxHeight = maxHeight;
	}

	float[] getEventWidth ( float xPos, float[] absoluteWidths ) {
		int n = 1;
		for ( int k = 0; k < cells.length; ) {
			if ( cells[k] != null ) {
				n++;
				k += cells[k].getColspan ();
			} else {
				while ( k < cells.length && cells[k] == null ) {
					n++;
					k++;
				}
			}
		}
		float[] width = new float[n];
		width[0] = xPos;
		n = 1;
		for ( int k = 0; k < cells.length && n < width.length; ) {
			if ( cells[k] != null ) {
				int colspan = cells[k].getColspan ();
				width[n] = width[n - 1];
				for ( int i = 0; i < colspan && k < absoluteWidths.length; i++ ) {
					width[n] += absoluteWidths[k++];
				}
			} else {
				width[n] = width[n - 1];
				while ( k < cells.length && cells[k] == null ) {
					width[n] += absoluteWidths[k++];
				}
			}
			n++;
		}
		return width;
	}

	public void copyRowContent ( PdfPTable table, int idx ) {
		if ( table == null ) {
			return;
		}
		PdfPCell copy;
		for ( int i = 0; i < cells.length; ++i ) {
			int lastRow = idx;
			copy = table.getRow ( lastRow ).getCells ()[i];
			while ( copy == null && lastRow > 0 ) {
				copy = table.getRow ( --lastRow ).getCells ()[i];
			}
			if ( cells[i] != null && copy != null ) {
				cells[i].setColumn ( copy.getColumn () );
				this.calculated = false;
			}
		}
	}

	public PdfPRow splitRow ( PdfPTable table, int rowIndex, float new_height ) {
		LOGGER.info ( "Splitting " + rowIndex + " " + new_height );
		// second part of the row
		PdfPCell[] newCells = new PdfPCell[cells.length];
		float[] fixHs = new float[cells.length];
		float[] minHs = new float[cells.length];
		boolean allEmpty = true;
		// loop over all the cells
		for ( int k = 0; k < cells.length; ++k ) {
			float newHeight = new_height;
			PdfPCell cell = cells[k];
			if ( cell == null ) {
				int index = rowIndex;
				if ( table.rowSpanAbove ( index, k ) ) {
					while ( table.rowSpanAbove ( --index, k ) ) {
						newHeight += table.getRow ( index ).getMaxHeights ();
					}
					PdfPRow row = table.getRow ( index );
					if ( row != null && row.getCells ()[k] != null ) {
						newCells[k] = new PdfPCell ( row.getCells ()[k] );
						newCells[k].setColumn ( null );
						newCells[k].setRowspan ( row.getCells ()[k].getRowspan () - rowIndex + index );
						allEmpty = false;
					}
				}
				continue;
			}
			fixHs[k] = cell.getFixedHeight ();
			minHs[k] = cell.getMinimumHeight ();
			Image img = cell.getImage ();
			PdfPCell newCell = new PdfPCell ( cell );
			if ( img != null ) {
				float padding = cell.getEffectivePaddingBottom () + cell.getEffectivePaddingTop () + 2;
				if ( ( img.isScaleToFitHeight () || img.getScaledHeight () + padding < newHeight )
								&& newHeight > padding ) {
					newCell.setPhrase ( null );
					allEmpty = false;
				}
			} else {
				float y;
				ColumnText ct = ColumnText.duplicate ( cell.getColumn () );
				float left = cell.getLeft () + cell.getEffectivePaddingLeft ();
				float bottom = cell.getTop () + cell.getEffectivePaddingBottom () - newHeight;
				float right = cell.getRight () - cell.getEffectivePaddingRight ();
				float top = cell.getTop () - cell.getEffectivePaddingTop ();
				switch ( cell.getRotation () ) {
				case 90:
				case 270:
					y = setColumn ( ct, bottom, left, top, right );
					break;
				default:
					y = setColumn ( ct, left, bottom + 0.00001f, cell.isNoWrap () ? RIGHT_LIMIT : right, top );
					break;
				}
				int status;
				try {
					status = ct.go ( true );
				} catch ( DocumentException e ) {
					throw new ExceptionConverter ( e );
				}
				boolean thisEmpty = ( ct.getYLine () == y );
				if ( thisEmpty ) {
					newCell.setColumn ( ColumnText.duplicate ( cell.getColumn () ) );
					ct.setFilledWidth ( 0 );
				} else if ( ( status & ColumnText.NO_MORE_TEXT ) == 0 ) {
					newCell.setColumn ( ct );
					ct.setFilledWidth ( 0 );
				} else
					newCell.setPhrase ( null );
				allEmpty = ( allEmpty && thisEmpty );
			}
			newCells[k] = newCell;
			cell.setFixedHeight ( newHeight );
		}
		if ( allEmpty ) {
			for ( int k = 0; k < cells.length; ++k ) {
				PdfPCell cell = cells[k];
				if ( cell == null )
					continue;
				if ( fixHs[k] > 0 )
					cell.setFixedHeight ( fixHs[k] );
				else
					cell.setMinimumHeight ( minHs[k] );
			}
			return null;
		}
		calculateHeights ();
		PdfPRow split = new PdfPRow ( newCells, this );
		split.widths = widths.clone ();
		return split;
	}

	public float getMaxRowHeightsWithoutCalculating () {
		return maxHeight;
	}

	public void setFinalMaxHeights ( float maxHeight ) {
		setMaxHeights ( maxHeight );
		calculated = true;
	}

	public void splitRowspans ( PdfPTable original, int originalIdx, PdfPTable part, int partIdx ) {
		if ( original == null || part == null ) {
			return;
		}
		int i = 0;
		while ( i < cells.length ) {
			if ( cells[i] == null ) {
				int splittedRowIdx = original.getCellStartRowIndex ( originalIdx, i );
				int copyRowIdx = part.getCellStartRowIndex ( partIdx, i );
				PdfPCell splitted = original.getRow ( splittedRowIdx )
								.getCells ()[i]; // need this to reduce its rowspan
				PdfPCell copy = part.getRow ( copyRowIdx )
								.getCells ()[i]; // need this for (partially) consumed ColumnText
				if ( splitted != null ) {
					assert ( copy != null ); // both null or none
					cells[i] = new PdfPCell ( copy );
					int rowspanOnPreviousPage = partIdx - copyRowIdx + 1;
					cells[i].setRowspan ( copy.getRowspan () - rowspanOnPreviousPage );
					splitted.setRowspan ( rowspanOnPreviousPage );
					this.calculated = false;
				}
				++i;
			} else {
				i += cells[i].getColspan ();
			}
		}
	}

	public PdfPCell[] getCells () {
		return cells;
	}

	public boolean hasRowspan () {
		for ( PdfPCell cell : cells ) {
			if ( cell != null && cell.getRowspan () > 1 )
				return true;
		}
		return false;
	}

	public boolean isAdjusted () {
		return adjusted;
	}

	public void setAdjusted ( boolean adjusted ) {
		this.adjusted = adjusted;
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
