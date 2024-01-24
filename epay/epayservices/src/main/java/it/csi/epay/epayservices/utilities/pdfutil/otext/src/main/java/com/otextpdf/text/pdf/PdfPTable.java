/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ElementListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.LargeElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.Spaceable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PdfPTable implements LargeElement, Spaceable, IAccessibleElement {

	public static final int BASECANVAS = 0;

	public static final int BACKGROUNDCANVAS = 1;

	public static final int LINECANVAS = 2;

	public static final int TEXTCANVAS = 3;

	private final Logger LOGGER = LoggerFactory.getLogger ( PdfPTable.class );

	protected ArrayList<PdfPRow> rows = new ArrayList<> ();

	protected float totalHeight = 0;

	protected PdfPCell[] currentRow;

	protected int currentColIdx = 0;

	protected PdfPCell defaultCell = new PdfPCell ( (Phrase) null );

	protected float totalWidth = 0;

	protected float[] relativeWidths;

	protected float[] absoluteWidths;

	protected PdfPTableEvent tableEvent;

	protected int headerRows;

	protected float widthPercentage = 80;

	protected boolean isColspan = false;

	protected int runDirection = PdfWriter.RUN_DIRECTION_DEFAULT;

	protected float spacingBefore;

	protected float spacingAfter;

	protected boolean complete = true;

	protected boolean rowCompleted = true;

	protected boolean loopCheck = true;

	protected boolean rowsNotChecked = true;

	protected PdfName role = PdfName.TABLE;

	protected HashMap<PdfName, PdfObject> accessibleAttributes = null;

	protected AccessibleElementId id = new AccessibleElementId ();

	private int horizontalAlignment = Element.ALIGN_CENTER;

	private boolean skipFirstHeader = false;

	private boolean skipLastFooter = false;

	private boolean lockedWidth = false;

	private boolean splitRows = true;

	private boolean[] extendLastRow = { false, false };

	private boolean headersInEvent;

	private boolean splitLate = true;

	private boolean keepTogether;

	private int footerRows;

	private PdfPTableHeader header = null;

	private PdfPTableBody body = null;

	private PdfPTableFooter footer = null;

	protected PdfPTable () {
	}

	public PdfPTable ( final float[] relativeWidths ) {
		if ( relativeWidths == null )
			throw new NullPointerException ( MessageLocalization.getComposedMessage ( "the.widths.array.in.pdfptable.constructor.can.not.be.null" ) );
		if ( relativeWidths.length == 0 )
			throw new IllegalArgumentException (
							MessageLocalization.getComposedMessage ( "the.widths.array.in.pdfptable.constructor.can.not.have.zero.length" ) );
		this.relativeWidths = new float[relativeWidths.length];
		System.arraycopy ( relativeWidths, 0, this.relativeWidths, 0, relativeWidths.length );
		absoluteWidths = new float[relativeWidths.length];
		calculateWidths ();
		currentRow = new PdfPCell[absoluteWidths.length];
		keepTogether = false;
	}

	public PdfPTable ( final int numColumns ) {
		if ( numColumns <= 0 )
			throw new IllegalArgumentException (
							MessageLocalization.getComposedMessage ( "the.number.of.columns.in.pdfptable.constructor.must.be.greater.than.zero" ) );
		relativeWidths = new float[numColumns];
		for ( int k = 0; k < numColumns; ++k )
			relativeWidths[k] = 1;
		absoluteWidths = new float[relativeWidths.length];
		calculateWidths ();
		currentRow = new PdfPCell[absoluteWidths.length];
		keepTogether = false;
	}

	public PdfPTable ( final PdfPTable table ) {
		copyFormat ( table );
		for ( int k = 0; k < currentRow.length; ++k ) {
			if ( table.currentRow[k] == null )
				break;
			currentRow[k] = new PdfPCell ( table.currentRow[k] );
		}
		for ( int k = 0; k < table.rows.size (); ++k ) {
			PdfPRow row = table.rows.get ( k );
			if ( row != null )
				row = new PdfPRow ( row );
			rows.add ( row );
		}
	}

	public static PdfPTable shallowCopy ( final PdfPTable table ) {
		PdfPTable nt = new PdfPTable ();
		nt.copyFormat ( table );
		return nt;
	}

	public static PdfContentByte[] beginWritingRows ( final PdfContentByte canvas ) {
		return new PdfContentByte[] {
						canvas,
						canvas.getDuplicate (),
						canvas.getDuplicate (),
						canvas.getDuplicate (),
		};
	}

	public static void endWritingRows ( final PdfContentByte[] canvases ) {
		PdfContentByte canvas = canvases[BASECANVAS];
		PdfArtifact artifact = new PdfArtifact ();
		canvas.openMCBlock ( artifact );
		canvas.saveState ();
		canvas.add ( canvases[BACKGROUNDCANVAS] );
		canvas.restoreState ();
		canvas.saveState ();
		canvas.setLineCap ( 2 );
		canvas.resetRGBColorStroke ();
		canvas.add ( canvases[LINECANVAS] );
		canvas.restoreState ();
		canvas.closeMCBlock ( artifact );
		canvas.add ( canvases[TEXTCANVAS] );
	}

	protected void copyFormat ( final PdfPTable sourceTable ) {
		relativeWidths = new float[sourceTable.getNumberOfColumns ()];
		absoluteWidths = new float[sourceTable.getNumberOfColumns ()];
		System.arraycopy ( sourceTable.relativeWidths, 0, relativeWidths, 0, getNumberOfColumns () );
		System.arraycopy ( sourceTable.absoluteWidths, 0, absoluteWidths, 0, getNumberOfColumns () );
		totalWidth = sourceTable.totalWidth;
		totalHeight = sourceTable.totalHeight;
		currentColIdx = 0;
		tableEvent = sourceTable.tableEvent;
		runDirection = sourceTable.runDirection;
		if ( sourceTable.defaultCell instanceof PdfPHeaderCell )
			defaultCell = new PdfPHeaderCell ( (PdfPHeaderCell) sourceTable.defaultCell );
		else
			defaultCell = new PdfPCell ( sourceTable.defaultCell );
		currentRow = new PdfPCell[sourceTable.currentRow.length];
		isColspan = sourceTable.isColspan;
		splitRows = sourceTable.splitRows;
		spacingAfter = sourceTable.spacingAfter;
		spacingBefore = sourceTable.spacingBefore;
		headerRows = sourceTable.headerRows;
		footerRows = sourceTable.footerRows;
		lockedWidth = sourceTable.lockedWidth;
		extendLastRow = sourceTable.extendLastRow;
		headersInEvent = sourceTable.headersInEvent;
		widthPercentage = sourceTable.widthPercentage;
		splitLate = sourceTable.splitLate;
		skipFirstHeader = sourceTable.skipFirstHeader;
		skipLastFooter = sourceTable.skipLastFooter;
		horizontalAlignment = sourceTable.horizontalAlignment;
		keepTogether = sourceTable.keepTogether;
		complete = sourceTable.complete;
		loopCheck = sourceTable.loopCheck;
		id = sourceTable.id;
		role = sourceTable.role;
		if ( sourceTable.accessibleAttributes != null )
			accessibleAttributes = new HashMap<> ( sourceTable.accessibleAttributes );
		header = sourceTable.getHeader ();
		body = sourceTable.getBody ();
		footer = sourceTable.getFooter ();
	}

	public void setWidths ( final float[] relativeWidths ) throws DocumentException {
		if ( relativeWidths.length != getNumberOfColumns () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "wrong.number.of.columns" ) );
		this.relativeWidths = new float[relativeWidths.length];
		System.arraycopy ( relativeWidths, 0, this.relativeWidths, 0, relativeWidths.length );
		absoluteWidths = new float[relativeWidths.length];
		totalHeight = 0;
		calculateWidths ();
		calculateHeights ();
	}

	public void setWidths ( final int[] relativeWidths ) throws DocumentException {
		float[] tb = new float[relativeWidths.length];
		for ( int k = 0; k < relativeWidths.length; ++k )
			tb[k] = relativeWidths[k];
		setWidths ( tb );
	}

	protected void calculateWidths () {
		if ( totalWidth <= 0 )
			return;
		float total = 0;
		int numCols = getNumberOfColumns ();
		for ( int k = 0; k < numCols; ++k )
			total += relativeWidths[k];
		for ( int k = 0; k < numCols; ++k )
			absoluteWidths[k] = totalWidth * relativeWidths[k] / total;
	}

	public float getTotalWidth () {
		return totalWidth;
	}

	public void setTotalWidth ( final float totalWidth ) {
		if ( this.totalWidth == totalWidth )
			return;
		this.totalWidth = totalWidth;
		totalHeight = 0;
		calculateWidths ();
		calculateHeights ();
	}

	public void calculateHeights () {
		if ( totalWidth <= 0 )
			return;
		totalHeight = 0;
		for ( int k = 0; k < rows.size (); ++k ) {
			totalHeight += getRowHeight ( k, true );
		}
	}

	public void addCell ( final PdfPCell cell ) {
		rowCompleted = false;
		PdfPCell ncell;
		if ( cell instanceof PdfPHeaderCell )
			ncell = new PdfPHeaderCell ( (PdfPHeaderCell) cell );
		else
			ncell = new PdfPCell ( cell );

		int colspan = ncell.getColspan ();
		colspan = Math.max ( colspan, 1 );
		colspan = Math.min ( colspan, currentRow.length - currentColIdx );
		ncell.setColspan ( colspan );

		if ( colspan != 1 )
			isColspan = true;
		int rdir = ncell.getRunDirection ();
		if ( rdir == PdfWriter.RUN_DIRECTION_DEFAULT )
			ncell.setRunDirection ( runDirection );

		skipColsWithRowspanAbove ();

		boolean cellAdded = false;
		if ( currentColIdx < currentRow.length ) {
			currentRow[currentColIdx] = ncell;
			currentColIdx += colspan;
			cellAdded = true;
		}

		skipColsWithRowspanAbove ();

		while ( currentColIdx >= currentRow.length ) {
			int numCols = getNumberOfColumns ();
			if ( runDirection == PdfWriter.RUN_DIRECTION_RTL ) {
				PdfPCell[] rtlRow = new PdfPCell[numCols];
				int rev = currentRow.length;
				for ( int k = 0; k < currentRow.length; ++k ) {
					PdfPCell rcell = currentRow[k];
					int cspan = rcell.getColspan ();
					rev -= cspan;
					rtlRow[rev] = rcell;
					k += cspan - 1;
				}
				currentRow = rtlRow;
			}
			PdfPRow row = new PdfPRow ( currentRow );
			if ( totalWidth > 0 ) {
				row.setWidths ( absoluteWidths );
				totalHeight += row.getMaxHeights ();
			}
			rows.add ( row );
			currentRow = new PdfPCell[numCols];
			currentColIdx = 0;
			skipColsWithRowspanAbove ();
			rowCompleted = true;
		}

		if ( !cellAdded ) {
			currentRow[currentColIdx] = ncell;
			currentColIdx += colspan;
		}
	}

	private void skipColsWithRowspanAbove () {
		int direction = 1;
		if ( runDirection == PdfWriter.RUN_DIRECTION_RTL )
			direction = -1;
		while ( rowSpanAbove ( rows.size (), currentColIdx ) )
			currentColIdx += direction;
	}

	PdfPCell cellAt ( final int row, final int col ) {
		PdfPCell[] cells = rows.get ( row ).getCells ();
		for ( int i = 0; i < cells.length; i++ ) {
			if ( cells[i] != null ) {
				if ( col >= i && col < ( i + cells[i].getColspan () ) ) {
					return cells[i];
				}
			}
		}
		return null;
	}

	boolean rowSpanAbove ( final int currRow, final int currCol ) {
		if ( currCol >= getNumberOfColumns ()
						|| currCol < 0
						|| currRow < 1 )
			return false;
		int row = currRow - 1;
		PdfPRow aboveRow = rows.get ( row );
		if ( aboveRow == null )
			return false;
		PdfPCell aboveCell = cellAt ( row, currCol );
		while ( aboveCell == null && row > 0 ) {
			aboveRow = rows.get ( --row );
			if ( aboveRow == null )
				return false;
			aboveCell = cellAt ( row, currCol );
		}

		int distance = currRow - row;

		assert aboveCell != null;
		if ( aboveCell.getRowspan () == 1 && distance > 1 ) {
			int col = currCol - 1;
			aboveRow = rows.get ( row + 1 );
			distance--;
			aboveCell = aboveRow.getCells ()[col];
			while ( aboveCell == null && col > 0 )
				aboveCell = aboveRow.getCells ()[--col];
		}

		return aboveCell != null && aboveCell.getRowspan () > distance;
	}

	public void writeSelectedRows ( int colStart, int colEnd, int rowStart, int rowEnd, final float xPos, float yPos, final PdfContentByte[] canvases,
					final boolean reusable ) {
		if ( totalWidth <= 0 )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "the.table.width.must.be.greater.than.zero" ) );

		int totalRows = rows.size ();
		if ( rowStart < 0 )
			rowStart = 0;
		if ( rowEnd < 0 )
			rowEnd = totalRows;
		else
			rowEnd = Math.min ( rowEnd, totalRows );
		if ( rowStart >= rowEnd )
			return;

		int totalCols = getNumberOfColumns ();
		if ( colStart < 0 )
			colStart = 0;
		else
			colStart = Math.min ( colStart, totalCols );
		if ( colEnd < 0 )
			colEnd = totalCols;
		else
			colEnd = Math.min ( colEnd, totalCols );

		LOGGER.info ( String.format ( "Writing row %s to %s; column %s to %s", rowStart, rowEnd, colStart, colEnd ) );

		float yPosStart = yPos;

		PdfPTableBody currentBlock = null;
		if ( rowsNotChecked )
			getFittingRows ( Float.MAX_VALUE, rowStart );
		List<PdfPRow> rows = getRows ( rowStart, rowEnd );
		int k = rowStart;
		for ( PdfPRow row : rows ) {
			if ( getHeader ().rows != null && getHeader ().rows.contains ( row ) && currentBlock == null ) {
				currentBlock = openTableBlock ( getHeader (), canvases[TEXTCANVAS] );
			} else if ( getBody ().rows != null && getBody ().rows.contains ( row ) && currentBlock == null ) {
				currentBlock = openTableBlock ( getBody (), canvases[TEXTCANVAS] );
			} else if ( getFooter ().rows != null && getFooter ().rows.contains ( row ) && currentBlock == null ) {
				currentBlock = openTableBlock ( getFooter (), canvases[TEXTCANVAS] );
			}
			if ( row != null ) {
				row.writeCells ( colStart, colEnd, xPos, yPos, canvases, reusable );
				yPos -= row.getMaxHeights ();
			}
			if ( getHeader ().rows != null && getHeader ().rows.contains ( row ) && ( k == rowEnd - 1 || !getHeader ().rows.contains (
							rows.get ( k + 1 ) ) ) ) {
				currentBlock = closeTableBlock ( getHeader (), canvases[TEXTCANVAS] );
			} else if ( getBody ().rows != null && getBody ().rows.contains ( row ) && ( k == rowEnd - 1 || !getBody ().rows.contains (
							rows.get ( k + 1 ) ) ) ) {
				currentBlock = closeTableBlock ( getBody (), canvases[TEXTCANVAS] );
			} else if ( getFooter ().rows != null && getFooter ().rows.contains ( row ) && ( k == rowEnd - 1 || !getFooter ().rows.contains (
							rows.get ( k + 1 ) ) ) ) {
				currentBlock = closeTableBlock ( getFooter (), canvases[TEXTCANVAS] );
			}
			k++;
		}

		if ( tableEvent != null && colStart == 0 && colEnd == totalCols ) {
			float[] heights = new float[rowEnd - rowStart + 1];
			heights[0] = yPosStart;
			for ( k = rowStart; k < rowEnd; ++k ) {
				PdfPRow row = rows.get ( k );
				float hr = 0;
				if ( row != null )
					hr = row.getMaxHeights ();
				heights[k - rowStart + 1] = heights[k - rowStart] - hr;
			}
			tableEvent.tableLayout ( this, getEventWidths ( xPos, rowStart, rowEnd, headersInEvent ), heights, headersInEvent ? headerRows : 0, rowStart,
							canvases );
		}

	}

	private PdfPTableBody openTableBlock ( PdfPTableBody block, PdfContentByte canvas ) {
		if ( canvas.writer.getStandardStructElems ().contains ( block.getRole () ) ) {
			canvas.openMCBlock ( block );
			return block;
		}
		return null;
	}

	private PdfPTableBody closeTableBlock ( PdfPTableBody block, PdfContentByte canvas ) {
		if ( canvas.writer.getStandardStructElems ().contains ( block.getRole () ) )
			canvas.closeMCBlock ( block );
		return null;
	}

	public void writeSelectedRows ( int colStart, int colEnd, final int rowStart, final int rowEnd, final float xPos, final float yPos,
					final PdfContentByte canvas, final boolean reusable ) {
		int totalCols = getNumberOfColumns ();
		if ( colStart < 0 )
			colStart = 0;
		else
			colStart = Math.min ( colStart, totalCols );

		if ( colEnd < 0 )
			colEnd = totalCols;
		else
			colEnd = Math.min ( colEnd, totalCols );

		boolean clip = colStart != 0 || colEnd != totalCols;

		if ( clip ) {
			float w = 0;
			for ( int k = colStart; k < colEnd; ++k )
				w += absoluteWidths[k];
			canvas.saveState ();
			float lx = colStart == 0 ? 10000 : 0;
			float rx = colEnd == totalCols ? 10000 : 0;
			canvas.rectangle ( xPos - lx, -10000, w + lx + rx, PdfPRow.RIGHT_LIMIT );
			canvas.clip ();
			canvas.newPath ();
		}

		PdfContentByte[] canvases = beginWritingRows ( canvas );
		writeSelectedRows ( colStart, colEnd, rowStart, rowEnd, xPos, yPos, canvases, reusable );
		endWritingRows ( canvases );

		if ( clip )
			canvas.restoreState ();

	}

	public int size () {
		return rows.size ();
	}

	public float getTotalHeight () {
		return totalHeight;
	}

	public float getRowHeight ( final int idx ) {
		return getRowHeight ( idx, false );
	}

	protected float getRowHeight ( final int idx, final boolean firsttime ) {
		if ( totalWidth <= 0 || idx < 0 || idx >= rows.size () )
			return 0;
		PdfPRow row = rows.get ( idx );
		if ( row == null )
			return 0;
		if ( firsttime )
			row.setWidths ( absoluteWidths );
		float height = row.getMaxHeights ();
		PdfPCell cell;
		PdfPRow tmprow;
		for ( int i = 0; i < relativeWidths.length; i++ ) {
			if ( !rowSpanAbove ( idx, i ) )
				continue;
			int rs = 1;
			while ( rowSpanAbove ( idx - rs, i ) ) {
				rs++;
			}
			tmprow = rows.get ( idx - rs );
			cell = tmprow.getCells ()[i];
			float tmp = 0;
			if ( cell != null && cell.getRowspan () == rs + 1 ) {
				tmp = cell.getMaxHeight ();
				while ( rs > 0 ) {
					tmp -= getRowHeight ( idx - rs );
					rs--;
				}
			}
			if ( tmp > height )
				height = tmp;
		}
		row.setMaxHeights ( height );
		return height;
	}

	public boolean hasRowspan ( final int rowIdx ) {
		if ( rowIdx < rows.size () && getRow ( rowIdx ).hasRowspan () ) {
			return true;
		}
		PdfPRow previousRow = rowIdx > 0 ? getRow ( rowIdx - 1 ) : null;
		if ( previousRow != null && previousRow.hasRowspan () ) {
			return true;
		}
		for ( int i = 0; i < getNumberOfColumns (); i++ ) {
			if ( rowSpanAbove ( rowIdx - 1, i ) )
				return true;
		}
		return false;
	}

	public void normalizeHeadersFooters () {
		if ( footerRows > headerRows )
			footerRows = headerRows;
	}

	public float getHeaderHeight () {
		float total = 0;
		int size = Math.min ( rows.size (), headerRows );
		for ( int k = 0; k < size; ++k ) {
			PdfPRow row = rows.get ( k );
			if ( row != null )
				total += row.getMaxHeights ();
		}
		return total;
	}

	public float getFooterHeight () {
		float total = 0;
		int start = Math.max ( 0, headerRows - footerRows );
		int size = Math.min ( rows.size (), headerRows );
		for ( int k = start; k < size; ++k ) {
			PdfPRow row = rows.get ( k );
			if ( row != null )
				total += row.getMaxHeights ();
		}
		return total;
	}

	public void deleteBodyRows () {
		ArrayList<PdfPRow> rows2 = new ArrayList<> ();
		for ( int k = 0; k < headerRows; ++k )
			rows2.add ( rows.get ( k ) );
		rows = rows2;
		totalHeight = 0;
		if ( totalWidth > 0 )
			totalHeight = getHeaderHeight ();
	}

	public int getNumberOfColumns () {
		return relativeWidths.length;
	}

	public int getHeaderRows () {
		return headerRows;
	}

	public void setHeaderRows ( int headerRows ) {
		if ( headerRows < 0 )
			headerRows = 0;
		this.headerRows = headerRows;
	}

	public List<Chunk> getChunks () {
		return new ArrayList<> ();
	}

	public int type () {
		return Element.PTABLE;
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

	public float getWidthPercentage () {
		return widthPercentage;
	}

	public void setWidthPercentage ( final float widthPercentage ) {
		this.widthPercentage = widthPercentage;
	}

	public int getHorizontalAlignment () {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment ( final int horizontalAlignment ) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public PdfPRow getRow ( final int idx ) {
		return rows.get ( idx );
	}

	public ArrayList<PdfPRow> getRows () {
		return rows;
	}

	public ArrayList<PdfPRow> getRows ( final int start, final int end ) {
		ArrayList<PdfPRow> list = new ArrayList<> ();
		if ( start < 0 || end > size () ) {
			return list;
		}
		for ( int i = start; i < end; i++ ) {
			list.add ( adjustCellsInRow ( i, end ) );
		}
		return list;
	}

	protected PdfPRow adjustCellsInRow ( final int start, final int end ) {
		PdfPRow row = getRow ( start );
		if ( row.isAdjusted () )
			return row;
		row = new PdfPRow ( row );
		PdfPCell cell;
		PdfPCell[] cells = row.getCells ();
		for ( int i = 0; i < cells.length; i++ ) {
			cell = cells[i];
			if ( cell == null || cell.getRowspan () == 1 )
				continue;
			int stop = Math.min ( end, start + cell.getRowspan () );
			float extra = 0;
			for ( int k = start + 1; k < stop; k++ ) {
				extra += getRow ( k ).getMaxHeights ();
			}
			row.setExtraHeight ( i, extra );
		}
		row.setAdjusted ( true );
		return row;
	}

	public PdfPTableEvent getTableEvent () {
		return tableEvent;
	}

	float[][] getEventWidths ( final float xPos, int firstRow, int lastRow, final boolean includeHeaders ) {
		if ( includeHeaders ) {
			firstRow = Math.max ( firstRow, headerRows );
			lastRow = Math.max ( lastRow, headerRows );
		}
		float[][] widths = new float[( includeHeaders ? headerRows : 0 ) + lastRow - firstRow][];
		if ( isColspan ) {
			int n = 0;
			if ( includeHeaders ) {
				for ( int k = 0; k < headerRows; ++k ) {
					PdfPRow row = rows.get ( k );
					if ( row == null )
						++n;
					else
						widths[n++] = row.getEventWidth ( xPos, absoluteWidths );
				}
			}
			for ( ; firstRow < lastRow; ++firstRow ) {
				PdfPRow row = rows.get ( firstRow );
				if ( row == null )
					++n;
				else
					widths[n++] = row.getEventWidth ( xPos, absoluteWidths );
			}
		} else {
			int numCols = getNumberOfColumns ();
			float[] width = new float[numCols + 1];
			width[0] = xPos;
			for ( int k = 0; k < numCols; ++k )
				width[k + 1] = width[k] + absoluteWidths[k];
			Arrays.fill ( widths, width );
		}
		return widths;
	}

	public boolean isSkipFirstHeader () {
		return skipFirstHeader;
	}

	public void setSkipFirstHeader ( final boolean skipFirstHeader ) {
		this.skipFirstHeader = skipFirstHeader;
	}

	public boolean isSkipLastFooter () {
		return skipLastFooter;
	}

	public boolean isLockedWidth () {
		return this.lockedWidth;
	}

	public void setLockedWidth ( final boolean lockedWidth ) {
		this.lockedWidth = lockedWidth;
	}

	public boolean isSplitRows () {
		return this.splitRows;
	}

	public void setSplitRows ( final boolean splitRows ) {
		this.splitRows = splitRows;
	}

	public float spacingBefore () {
		return spacingBefore;
	}

	public float spacingAfter () {
		return spacingAfter;
	}

	public boolean isExtendLastRow () {
		return extendLastRow[0];
	}

	public void setExtendLastRow ( final boolean extendLastRows ) {
		extendLastRow[0] = extendLastRows;
		extendLastRow[1] = extendLastRows;
	}

	public boolean isExtendLastRow ( final boolean newPageFollows ) {
		if ( newPageFollows ) {
			return extendLastRow[0];
		}
		return extendLastRow[1];
	}

	public boolean isHeadersInEvent () {
		return headersInEvent;
	}

	public void setHeadersInEvent ( final boolean headersInEvent ) {
		this.headersInEvent = headersInEvent;
	}

	public boolean isSplitLate () {
		return splitLate;
	}

	public void setSplitLate ( final boolean splitLate ) {
		this.splitLate = splitLate;
	}

	public boolean getKeepTogether () {
		return keepTogether;
	}

	public void setKeepTogether ( final boolean keepTogether ) {
		this.keepTogether = keepTogether;
	}

	public int getFooterRows () {
		return this.footerRows;
	}

	public void flushContent () {
		deleteBodyRows ();
		setSkipFirstHeader ( true );
	}

	public boolean isComplete () {
		return complete;
	}

	public void setComplete ( final boolean complete ) {
		this.complete = complete;
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

	public boolean isLoopCheck () {
		return loopCheck;
	}

	public void setLoopCheck ( boolean loopCheck ) {
		this.loopCheck = loopCheck;
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

	public PdfPTableHeader getHeader () {
		if ( header == null )
			header = new PdfPTableHeader ();
		return header;
	}

	public PdfPTableBody getBody () {
		if ( body == null )
			body = new PdfPTableBody ();
		return body;
	}

	public PdfPTableFooter getFooter () {
		if ( footer == null )
			footer = new PdfPTableFooter ();
		return footer;
	}

	public int getCellStartRowIndex ( int rowIdx, int colIdx ) {
		int lastRow = rowIdx;
		while ( getRow ( lastRow ).getCells ()[colIdx] == null && lastRow > 0 ) {
			--lastRow;
		}
		return lastRow;
	}

	public FittingRows getFittingRows ( float availableHeight, int startIdx ) {
		assert ( getRow ( startIdx ).getCells ()[0] != null ); // top left cell of current page may not be null
		int cols = getNumberOfColumns ();
		ColumnMeasurementState[] states = new ColumnMeasurementState[cols];
		for ( int i = 0; i < cols; ++i ) {
			states[i] = new ColumnMeasurementState ();
		}
		float completedRowsHeight = 0; // total height of all rows up to k only counting completed cells (with no open
		float totalHeight = 0; // total height needed to display all rows up to k, respecting rowspans
		Map<Integer, Float> correctedHeightsForLastRow = new HashMap<> ();
		int k;
		for ( k = startIdx; k < size (); ++k ) {
			PdfPRow row = getRow ( k );
			float rowHeight = row.getMaxRowHeightsWithoutCalculating ();
			float maxCompletedRowsHeight = 0;
			int i = 0;
			while ( i < cols ) {
				PdfPCell cell = row.getCells ()[i];
				ColumnMeasurementState state = states[i];
				if ( cell == null ) {
					state.consumeRowspan ();
				} else {
					state.beginCell ( cell, completedRowsHeight, rowHeight );
				}
				if ( state.cellEnds () && state.height > maxCompletedRowsHeight ) {
					maxCompletedRowsHeight = state.height;
				}
				for ( int j = 1; j < state.colspan; ++j ) {
					states[i + j].height = state.height;
				}
				i += state.colspan;
			}
			float maxTotalHeight = 0;
			for ( ColumnMeasurementState state : states ) {
				if ( state.height > maxTotalHeight ) {
					maxTotalHeight = state.height;
				}
			}
			row.setFinalMaxHeights ( maxCompletedRowsHeight - completedRowsHeight );
			float remainingHeight = availableHeight - ( isSplitLate () ? maxTotalHeight : maxCompletedRowsHeight );
			if ( remainingHeight < 0 ) {
				break;
			}
			correctedHeightsForLastRow.put ( k, maxTotalHeight - completedRowsHeight );
			completedRowsHeight = maxCompletedRowsHeight;
			totalHeight = maxTotalHeight;
		}
		rowsNotChecked = false;
		return new FittingRows ( startIdx, k - 1, totalHeight, completedRowsHeight, correctedHeightsForLastRow );
	}

	public static class FittingRows {

		public final int firstRow, lastRow;

		public final float height, completedRowsHeight;

		private final Map<Integer, Float> correctedHeightsForLastRow;

		public FittingRows ( int firstRow, int lastRow, float height, float completedRowsHeight,
						Map<Integer, Float> correctedHeightsForLastRow ) {
			this.firstRow = firstRow;
			this.lastRow = lastRow;
			this.height = height;
			this.completedRowsHeight = completedRowsHeight;
			this.correctedHeightsForLastRow = correctedHeightsForLastRow;
		}

		public void correctLastRowChosen ( PdfPTable table, int k ) {
			PdfPRow row = table.getRow ( k );
			Float value = correctedHeightsForLastRow.get ( k );
			if ( value != null ) {
				row.setFinalMaxHeights ( value );
			}
		}
	}


	public static class ColumnMeasurementState {

		public float height = 0;

		public int rowspan = 1, colspan = 1;

		public void beginCell ( PdfPCell cell, float completedRowsHeight, float rowHeight ) {
			rowspan = cell.getRowspan ();
			colspan = cell.getColspan ();
			height = completedRowsHeight + Math.max ( cell.getMaxHeight (), rowHeight );
		}

		public void consumeRowspan () {
			--rowspan;
		}

		public boolean cellEnds () {
			return rowspan == 1;
		}
	}
}
