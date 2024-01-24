/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListItem;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TabStop;

import java.util.ArrayList;
import java.util.Iterator;


public class PdfLine {

	protected ArrayList<PdfChunk> line;

	protected float left;

	protected float width;

	protected int alignment;

	protected float height;

	protected boolean newlineSplit = false;

	protected float originalWidth;

	protected boolean isRTL = false;

	protected ListItem listItem = null;

	protected TabStop tabStop = null;

	protected float tabStopAnchorPosition = Float.NaN;

	protected float tabPosition = Float.NaN;

	PdfLine ( float left, float right, int alignment, float height ) {
		this.left = left;
		this.width = right - left;
		this.originalWidth = this.width;
		this.alignment = alignment;
		this.height = height;
		this.line = new ArrayList<> ();
	}

	PdfLine ( float left, float originalWidth, float remainingWidth, int alignment, boolean newlineSplit, ArrayList<PdfChunk> line, boolean isRTL ) {
		this.left = left;
		this.originalWidth = originalWidth;
		this.width = remainingWidth;
		this.alignment = alignment;
		this.line = line;
		this.newlineSplit = newlineSplit;
		this.isRTL = isRTL;
	}

	PdfChunk add ( PdfChunk chunk ) {
		if ( chunk == null || chunk.toString ().isEmpty () ) {
			return null;
		}

		PdfChunk overflow = chunk.split ( width );
		newlineSplit = chunk.isNewlineSplit () || overflow == null;
		if ( chunk.isTab () ) {
			Object[] tab = (Object[]) chunk.getAttribute ( Chunk.TAB );
			if ( chunk.isAttribute ( Chunk.TABSETTINGS ) ) {
				boolean isWhiteSpace = (Boolean) tab[1];
				if ( !isWhiteSpace || !line.isEmpty () ) {
					flush ();
					tabStopAnchorPosition = Float.NaN;
					tabStop = PdfChunk.getTabStop ( chunk, originalWidth - width );
					if ( tabStop.getPosition () > originalWidth ) {
						width = 0;
						if ( isWhiteSpace )
							return null;
						else
							return chunk;
					}
					tabStop.setPosition ( tabStop.getPosition () );
					chunk.setTabStop ( tabStop );
					if ( tabStop.getAlignment () == TabStop.Alignment.LEFT ) {
						width = originalWidth - tabStop.getPosition ();
						tabStop = null;
						tabPosition = Float.NaN;
					} else
						tabPosition = originalWidth - width;
				} else
					return null;
			} else {
				Float tabStopPosition = (Float) tab[1];
				boolean newline = (Boolean) tab[2];
				if ( newline && tabPosition < originalWidth - width ) {
					return chunk;
				}
				chunk.adjustLeft ( left );
				width = originalWidth - tabStopPosition;
			}
			addToLine ( chunk );
		} else if ( chunk.length () > 0 || chunk.isImage () ) {
			if ( overflow != null )
				chunk.trimLastSpace ();
			width -= chunk.width ();
			addToLine ( chunk );
		} else if ( line.isEmpty () ) {
			chunk = overflow;
			overflow = chunk.truncate ( width );
			width -= chunk.width ();
			if ( chunk.length () > 0 ) {
				addToLine ( chunk );
				return overflow;
			} else {
				if ( overflow != null )
					addToLine ( overflow );
				return null;
			}
		} else {
			width += ( line.get ( line.size () - 1 ) ).trimLastSpace ();
		}
		return overflow;
	}

	private void addToLine ( PdfChunk chunk ) {
		if ( chunk.changeLeading ) {
			float f;
			if ( chunk.isImage () ) {
				Image img = chunk.getImage ();
				f = chunk.getImageHeight () + chunk.getImageOffsetY ()
								+ img.getBorderWidthTop () + img.getSpacingBefore ();
			} else {
				f = chunk.getLeading ();
			}
			if ( f > height )
				height = f;
		}
		if ( tabStop != null && tabStop.getAlignment () == TabStop.Alignment.ANCHOR && Float.isNaN ( tabStopAnchorPosition ) ) {
			String value = chunk.toString ();
			int anchorIndex = value.indexOf ( tabStop.getAnchorChar () );
			if ( anchorIndex != -1 ) {
				float subWidth = chunk.width ( value.substring ( anchorIndex ) );
				tabStopAnchorPosition = originalWidth - width - subWidth;
			}
		}
		line.add ( chunk );
	}

	public int size () {
		return line.size ();
	}

	public Iterator<PdfChunk> iterator () {
		return line.iterator ();
	}

	float height () {
		return height;
	}

	float indentLeft () {
		if ( isRTL ) {
			switch ( alignment ) {
			case Element.ALIGN_LEFT:
				return left + width;
			case Element.ALIGN_CENTER:
				return left + width / 2f;
			default:
				return left;
			}
		} else if ( this.getSeparatorCount () <= 0 ) {
			switch ( alignment ) {
			case Element.ALIGN_RIGHT:
				return left + width;
			case Element.ALIGN_CENTER:
				return left + width / 2f;
			}
		}
		return left;
	}

	public boolean hasToBeJustified () {
		return ( ( alignment == Element.ALIGN_JUSTIFIED && !newlineSplit ) || alignment == Element.ALIGN_JUSTIFIED_ALL ) && width != 0;
	}

	public void resetAlignment () {
		if ( alignment == Element.ALIGN_JUSTIFIED ) {
			alignment = Element.ALIGN_LEFT;
		}
	}

	void setExtraIndent ( float extra ) {
		left += extra;
		width -= extra;
		originalWidth -= extra;
	}

	float widthLeft () {
		return width;
	}

	int numberOfSpaces () {
		int numberOfSpaces = 0;
		for ( PdfChunk pdfChunk : line ) {
			String tmp = pdfChunk.toString ();
			int length = tmp.length ();
			for ( int i = 0; i < length; i++ ) {
				if ( tmp.charAt ( i ) == ' ' ) {
					numberOfSpaces++;
				}
			}
		}
		return numberOfSpaces;
	}

	public void setListItem ( ListItem listItem ) {
		this.listItem = listItem;
	}

	public Chunk listSymbol () {
		return listItem != null ? listItem.getListSymbol () : null;
	}

	public float listIndent () {
		return listItem != null ? listItem.getIndentationLeft () : 0;
	}

	public ListItem listItem () {
		return listItem;
	}

	@Override
	public String toString () {
		StringBuilder tmp = new StringBuilder ();
		for ( PdfChunk pdfChunk : line ) {
			tmp.append ( ( pdfChunk ).toString () );
		}
		return tmp.toString ();
	}

	public int getLineLengthUtf32 () {
		int total = 0;
		for ( PdfChunk element : line ) {
			total += element.lengthUtf32 ();
		}
		return total;
	}

	public boolean isNewlineSplit () {
		return newlineSplit && alignment != Element.ALIGN_JUSTIFIED_ALL;
	}

	public int getLastStrokeChunk () {
		int lastIdx = line.size () - 1;
		for ( ; lastIdx >= 0; --lastIdx ) {
			PdfChunk chunk = line.get ( lastIdx );
			if ( chunk.isStroked () )
				break;
		}
		return lastIdx;
	}

	public PdfChunk getChunk ( int idx ) {
		if ( idx < 0 || idx >= line.size () )
			return null;
		return line.get ( idx );
	}

	public float getOriginalWidth () {
		return originalWidth;
	}

    /*
     * Gets the maximum size of all the fonts used in this line
     * including images.
     * @return maximum size of all the fonts used in this line
     float getMaxSizeSimple() {
        float maxSize = 0;
        PdfChunk chunk;
        for (int k = 0; k < line.size(); ++k) {
            chunk = (PdfChunk)line.get(k);
            if (!chunk.isImage()) {
                maxSize = Math.max(chunk.font().size(), maxSize);
            }
            else {
                maxSize = Math.max(chunk.getImage().getScaledHeight() + chunk.getImageOffsetY() , maxSize);
            }
        }
        return maxSize;
    }*/

	float[] getMaxSize ( float fixedLeading, float multipliedLeading ) {
		float normal_leading = 0;
		float image_leading = -10000;
		PdfChunk chunk;
		for ( PdfChunk pdfChunk : line ) {
			chunk = pdfChunk;
			if ( chunk.isImage () ) {
				Image img = chunk.getImage ();
				if ( chunk.changeLeading () ) {
					float height = chunk.getImageHeight () + chunk.getImageOffsetY () + img.getSpacingBefore ();
					image_leading = Math.max ( height, image_leading );
				}
			} else {
				if ( chunk.changeLeading () )
					normal_leading = Math.max ( chunk.getLeading (), normal_leading );
				else
					normal_leading = Math.max ( fixedLeading + multipliedLeading * chunk.font ().size (), normal_leading );
			}
		}
		return new float[] { normal_leading > 0 ? normal_leading : fixedLeading, image_leading };
	}

	boolean isRTL () {
		return isRTL;
	}

	int getSeparatorCount () {
		int s = 0;
		PdfChunk ck;
		for ( PdfChunk element : line ) {
			ck = element;
			if ( ck.isTab () ) {
				if ( ck.isAttribute ( Chunk.TABSETTINGS ) )
					continue;
				//It seems justification was forbidden in the deprecated tab logic!!!
				return -1;
			}
			if ( ck.isHorizontalSeparator () ) {
				s++;
			}
		}
		return s;
	}

	public float getAscender () {
		float ascender = 0;
		for ( PdfChunk ck : line ) {
			if ( ck.isImage () )
				ascender = Math.max ( ascender, ck.getImageHeight () + ck.getImageOffsetY () );
			else {
				PdfFont font = ck.font ();
				float textRise = ck.getTextRise ();
				ascender = Math.max ( ascender, ( textRise > 0 ? textRise : 0 ) + font.getFont ().getFontDescriptor ( BaseFont.ASCENT, font.size () ) );
			}
		}
		return ascender;
	}

	public float getDescender () {
		float descender = 0;
		for ( PdfChunk ck : line ) {
			if ( ck.isImage () )
				descender = Math.min ( descender, ck.getImageOffsetY () );
			else {
				PdfFont font = ck.font ();
				float textRise = ck.getTextRise ();
				descender = Math.min ( descender, ( textRise < 0 ? textRise : 0 ) + font.getFont ().getFontDescriptor ( BaseFont.DESCENT, font.size () ) );
			}
		}
		return descender;
	}

	public void flush () {
		if ( tabStop != null ) {
			float textWidth = originalWidth - width - tabPosition;
			float tabStopPosition = tabStop.getPosition ( tabPosition, originalWidth - width, tabStopAnchorPosition );
			width = originalWidth - tabStopPosition - textWidth;
			if ( width < 0 )
				tabStopPosition += width;
			tabStop.setPosition ( tabStopPosition );
			tabStop = null;
			tabPosition = Float.NaN;
		}
	}
}
