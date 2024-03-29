/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListBody;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListItem;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListLabel;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IPdfStructureElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class PdfStructureElement extends PdfDictionary implements IPdfStructureElement {

	private PdfStructureElement parent;

	private PdfStructureTreeRoot top;

	private PdfIndirectReference reference;

	protected PdfStructureElement ( PdfDictionary parent, PdfName structureType ) {
		if ( parent instanceof PdfStructureElement ) {
			top = ( (PdfStructureElement) parent ).top;
			init ( parent, structureType );
			this.parent = (PdfStructureElement) parent;
			put ( PdfName.P, ( (PdfStructureElement) parent ).reference );
			put ( PdfName.TYPE, PdfName.STRUCTELEM );
		} else if ( parent instanceof PdfStructureTreeRoot ) {
			top = (PdfStructureTreeRoot) parent;
			init ( parent, structureType );
			put ( PdfName.P, ( (PdfStructureTreeRoot) parent ).getReference () );
			put ( PdfName.TYPE, PdfName.STRUCTELEM );
		}
	}

	private void init ( PdfDictionary parent, PdfName structureType ) {
		if ( !top.getWriter ().getStandardStructElems ().contains ( structureType ) ) {
			PdfDictionary roleMap = top.getAsDict ( PdfName.ROLEMAP );
			if ( roleMap == null || !roleMap.contains ( structureType ) )
				throw new ExceptionConverter ( new DocumentException (
								MessageLocalization.getComposedMessage ( "unknown.structure.element.role.1", structureType.toString () ) ) );
			else
				roleMap.getAsName ( structureType );
		}
		PdfObject kido = parent.get ( PdfName.K );
		PdfArray kids;
		if ( kido == null ) {
			kids = new PdfArray ();
			parent.put ( PdfName.K, kids );
		} else if ( kido instanceof PdfArray ) {
			kids = (PdfArray) kido;
		} else {
			kids = new PdfArray ();
			kids.add ( kido );
			parent.put ( PdfName.K, kids );
		}
		if ( !kids.isEmpty () ) {
			if ( kids.getAsNumber ( 0 ) != null )
				kids.remove ( 0 );
			if ( !kids.isEmpty () ) {
				PdfDictionary mcr = kids.getAsDict ( 0 );
				if ( mcr != null && PdfName.MCR.equals ( mcr.getAsName ( PdfName.TYPE ) ) ) {
					kids.remove ( 0 );
				}
			}
		}
		kids.add ( this );
		put ( PdfName.S, structureType );
		reference = top.getWriter ().getPdfIndirectReference ();
	}

	public PdfDictionary getParent () {
		return getParent ( false );
	}

	public PdfDictionary getParent ( boolean includeStructTreeRoot ) {
		if ( parent == null && includeStructTreeRoot )
			return top;
		else
			return parent;
	}

	void setPageMark ( int page, int mark ) {
		if ( mark >= 0 )
			put ( PdfName.K, new PdfNumber ( mark ) );
		top.setPageMark ( page, reference );
	}

	public PdfIndirectReference getReference () {
		return this.reference;
	}

	public PdfObject getAttribute ( PdfName name ) {
		PdfDictionary attr = getAsDict ( PdfName.A );
		if ( attr != null ) {
			if ( attr.contains ( name ) )
				return attr.get ( name );
		}
		PdfDictionary parent = getParent ();
		if ( parent instanceof PdfStructureElement )
			return ( (PdfStructureElement) parent ).getAttribute ( name );
		if ( parent instanceof PdfStructureTreeRoot )
			return ( (PdfStructureTreeRoot) parent ).getAttribute ( name );

		return new PdfNull ();
	}

	public void setAttribute ( PdfName name, PdfObject obj ) {
		PdfDictionary attr = getAsDict ( PdfName.A );
		if ( attr == null ) {
			attr = new PdfDictionary ();
			put ( PdfName.A, attr );
		}
		attr.put ( name, obj );
	}

	public void writeAttributes ( final IAccessibleElement element ) {
		if ( top.getWriter ().getPdfVersion ().getVersion () < PdfWriter.VERSION_1_7 )
			return;
		if ( element instanceof ListItem ) {
			writeAttributes ( (ListItem) element );
		} else if ( element instanceof Paragraph ) {
			writeAttributes ( (Paragraph) element );
		} else if ( element instanceof Chunk ) {
			writeAttributes ( (Chunk) element );
		} else if ( element instanceof Image ) {
			writeAttributes ( (Image) element );
		} else if ( element instanceof List ) {
			writeAttributes ( (List) element );
		} else if ( element instanceof ListLabel ) {
			writeAttributes ( (ListLabel) element );
		} else if ( element instanceof ListBody ) {
			writeAttributes ();
		} else if ( element instanceof PdfPTable ) {
			writeAttributes ( (PdfPTable) element );
		} else if ( element instanceof PdfPRow ) {
			writeAttributes ( (PdfPRow) element );
		} else if ( element instanceof PdfPHeaderCell ) {
			writeAttributes ( (PdfPHeaderCell) element );
		} else if ( element instanceof PdfPCell ) {
			writeAttributes ( (PdfPCell) element );
		} else if ( element instanceof PdfPTableHeader ) {
			writeAttributes ( (PdfPTableHeader) element );
		} else if ( element instanceof PdfPTableFooter ) {
			writeAttributes ( (PdfPTableFooter) element );
		} else if ( element instanceof PdfPTableBody ) {
			writeAttributes ( (PdfPTableBody) element );
		} else if ( element instanceof PdfDiv ) {
			writeAttributes ( (PdfDiv) element );
		} else if ( element instanceof PdfTemplate ) {
			writeAttributes ( (PdfTemplate) element );
		} else if ( element instanceof Document ) {
			writeAttributes ( (Document) element );
		}
		if ( element.getAccessibleAttributes () != null ) {
			for ( PdfName key : element.getAccessibleAttributes ().keySet () ) {
				if ( key.equals ( PdfName.LANG ) || key.equals ( PdfName.ALT ) || key.equals ( PdfName.ACTUALTEXT ) || key.equals ( PdfName.E ) ) {
					put ( key, element.getAccessibleAttribute ( key ) );
				} else {
					setAttribute ( key, element.getAccessibleAttribute ( key ) );
				}
			}
		}
	}

	private void writeAttributes ( final Chunk chunk ) {
		if ( chunk != null ) {
			if ( chunk.getImage () != null ) {
				writeAttributes ( chunk.getImage () );
			} else {
				HashMap<String, Object> attr = chunk.getAttributes ();
				if ( attr != null ) {
					this.setAttribute ( PdfName.O, PdfName.LAYOUT );
					// Setting non-inheritable attributes
					if ( attr.containsKey ( Chunk.UNDERLINE ) ) {
						this.setAttribute ( PdfName.TEXTDECORATIONTYPE, PdfName.UNDERLINE );
					}
					if ( attr.containsKey ( Chunk.BACKGROUND ) ) {
						Object[] back = (Object[]) attr.get ( Chunk.BACKGROUND );
						BaseColor color = (BaseColor) back[0];
						this.setAttribute ( PdfName.BACKGROUNDCOLOR,
										new PdfArray ( new float[] { color.getRed () / 255f, color.getGreen () / 255f, color.getBlue () / 255f } ) );
					}

					// Setting inheritable attributes
					IPdfStructureElement parent = (IPdfStructureElement) this.getParent ( true );
					PdfObject obj = parent.getAttribute ( PdfName.COLOR );
					if ( ( chunk.getFont () != null ) && ( chunk.getFont ().getColor () != null ) ) {
						BaseColor c = chunk.getFont ().getColor ();
						setColorAttribute ( c, obj, PdfName.COLOR );
					}
					PdfObject decorThickness = parent.getAttribute ( PdfName.TEXTDECORATIONTHICKNESS );
					PdfObject decorColor = parent.getAttribute ( PdfName.TEXTDECORATIONCOLOR );
					if ( attr.containsKey ( Chunk.UNDERLINE ) ) {
						Object[][] unders = (Object[][]) attr.get ( Chunk.UNDERLINE );
						Object[] arr = unders[unders.length - 1];
						BaseColor color = (BaseColor) arr[0];
						float[] floats = (float[]) arr[1];
						float thickness = floats[0];
						if ( decorThickness instanceof PdfNumber ) {
							float t = ( (PdfNumber) decorThickness ).floatValue ();
							if ( Float.compare ( thickness, t ) != 0 ) {
								this.setAttribute ( PdfName.TEXTDECORATIONTHICKNESS, new PdfNumber ( thickness ) );
							}
						} else
							this.setAttribute ( PdfName.TEXTDECORATIONTHICKNESS, new PdfNumber ( thickness ) );

						if ( color != null ) {
							setColorAttribute ( color, decorColor, PdfName.TEXTDECORATIONCOLOR );
						}
					}

					if ( attr.containsKey ( Chunk.LINEHEIGHT ) ) {
						float height = (Float) attr.get ( Chunk.LINEHEIGHT );
						PdfObject parentLH = parent.getAttribute ( PdfName.LINEHEIGHT );
						if ( parentLH instanceof PdfNumber ) {
							float pLH = ( (PdfNumber) parentLH ).floatValue ();
							if ( Float.compare ( pLH, height ) != 0 ) {
								this.setAttribute ( PdfName.LINEHEIGHT, new PdfNumber ( height ) );
							}
						} else
							this.setAttribute ( PdfName.LINEHEIGHT, new PdfNumber ( height ) );
					}
				}
			}
		}
	}

	private void writeAttributes ( final Image image ) {
		if ( image != null ) {
			this.setAttribute ( PdfName.O, PdfName.LAYOUT );
			if ( image.getWidth () > 0 ) {
				this.setAttribute ( PdfName.WIDTH, new PdfNumber ( image.getWidth () ) );
			}
			if ( image.getHeight () > 0 ) {
				this.setAttribute ( PdfName.HEIGHT, new PdfNumber ( image.getHeight () ) );
			}
			PdfRectangle rect = new PdfRectangle ( image, image.getRotation () );
			this.setAttribute ( PdfName.BBOX, rect );
			if ( image.getBackgroundColor () != null ) {
				BaseColor color = image.getBackgroundColor ();
				this.setAttribute ( PdfName.BACKGROUNDCOLOR,
								new PdfArray ( new float[] { color.getRed () / 255f, color.getGreen () / 255f, color.getBlue () / 255f } ) );
			}
		}
	}

	private void writeAttributes ( final PdfTemplate template ) {
		if ( template != null ) {
			this.setAttribute ( PdfName.O, PdfName.LAYOUT );
			if ( template.getWidth () > 0 ) {
				this.setAttribute ( PdfName.WIDTH, new PdfNumber ( template.getWidth () ) );
			}
			if ( template.getHeight () > 0 ) {
				this.setAttribute ( PdfName.HEIGHT, new PdfNumber ( template.getHeight () ) );
			}
			PdfRectangle rect = new PdfRectangle ( template.getBoundingBox () );
			this.setAttribute ( PdfName.BBOX, rect );
		}
	}

	private void writeAttributes ( final Paragraph paragraph ) {
		if ( paragraph != null ) {
			this.setAttribute ( PdfName.O, PdfName.LAYOUT );
			if ( Float.compare ( paragraph.getSpacingBefore (), 0f ) != 0 )
				this.setAttribute ( PdfName.SPACEBEFORE, new PdfNumber ( paragraph.getSpacingBefore () ) );
			if ( Float.compare ( paragraph.getSpacingAfter (), 0f ) != 0 )
				this.setAttribute ( PdfName.SPACEAFTER, new PdfNumber ( paragraph.getSpacingAfter () ) );

			IPdfStructureElement parent = (IPdfStructureElement) this.getParent ( true );
			PdfObject obj = parent.getAttribute ( PdfName.COLOR );
			if ( ( paragraph.getFont () != null ) && ( paragraph.getFont ().getColor () != null ) ) {
				BaseColor c = paragraph.getFont ().getColor ();
				setColorAttribute ( c, obj, PdfName.COLOR );
			}
			obj = parent.getAttribute ( PdfName.TEXTINDENT );
			if ( Float.compare ( paragraph.getFirstLineIndent (), 0f ) != 0 ) {
				boolean writeIndent = true;
				if ( obj instanceof PdfNumber ) {
					if ( Float.compare ( ( (PdfNumber) obj ).floatValue (), paragraph.getFirstLineIndent () ) == 0 )
						writeIndent = false;
				}
				if ( writeIndent )
					this.setAttribute ( PdfName.TEXTINDENT, new PdfNumber ( paragraph.getFirstLineIndent () ) );
			}
			obj = parent.getAttribute ( PdfName.STARTINDENT );
			if ( obj instanceof PdfNumber ) {
				float startIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( startIndent, paragraph.getIndentationLeft () ) != 0 )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( paragraph.getIndentationLeft () ) );
			} else {
				if ( Math.abs ( paragraph.getIndentationLeft () ) > Float.MIN_VALUE )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( paragraph.getIndentationLeft () ) );
			}

			obj = parent.getAttribute ( PdfName.ENDINDENT );
			if ( obj instanceof PdfNumber ) {
				float endIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( endIndent, paragraph.getIndentationRight () ) != 0 )
					this.setAttribute ( PdfName.ENDINDENT, new PdfNumber ( paragraph.getIndentationRight () ) );
			} else {
				if ( Float.compare ( paragraph.getIndentationRight (), 0 ) != 0 )
					this.setAttribute ( PdfName.ENDINDENT, new PdfNumber ( paragraph.getIndentationRight () ) );
			}

			setTextAlignAttribute ( paragraph.getAlignment () );
		}
	}

	private void writeAttributes ( final List list ) {
		if ( list != null ) {
			this.setAttribute ( PdfName.O, PdfName.LIST );
			if ( list.isAutoindent () ) {
				if ( list.isNumbered () ) {
					if ( list.isLettered () ) {
						if ( list.isLowercase () )
							this.setAttribute ( PdfName.LISTNUMBERING, PdfName.LOWERROMAN );
						else
							this.setAttribute ( PdfName.LISTNUMBERING, PdfName.UPPERROMAN );
					} else {
						this.setAttribute ( PdfName.LISTNUMBERING, PdfName.DECIMAL );
					}
				} else if ( list.isLettered () ) {
					if ( list.isLowercase () )
						this.setAttribute ( PdfName.LISTNUMBERING, PdfName.LOWERALPHA );
					else
						this.setAttribute ( PdfName.LISTNUMBERING, PdfName.UPPERALPHA );
				}
			}
			PdfObject obj = parent.getAttribute ( PdfName.STARTINDENT );
			if ( obj instanceof PdfNumber ) {
				float startIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( startIndent, list.getIndentationLeft () ) != 0 )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( list.getIndentationLeft () ) );
			} else {
				if ( Math.abs ( list.getIndentationLeft () ) > Float.MIN_VALUE )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( list.getIndentationLeft () ) );
			}

			obj = parent.getAttribute ( PdfName.ENDINDENT );
			if ( obj instanceof PdfNumber ) {
				float endIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( endIndent, list.getIndentationRight () ) != 0 )
					this.setAttribute ( PdfName.ENDINDENT, new PdfNumber ( list.getIndentationRight () ) );
			} else {
				if ( Float.compare ( list.getIndentationRight (), 0 ) != 0 )
					this.setAttribute ( PdfName.ENDINDENT, new PdfNumber ( list.getIndentationRight () ) );
			}
		}
	}

	private void writeAttributes ( final ListItem listItem ) {
		if ( listItem != null ) {
			PdfObject obj = parent.getAttribute ( PdfName.STARTINDENT );
			if ( obj instanceof PdfNumber ) {
				float startIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( startIndent, listItem.getIndentationLeft () ) != 0 )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( listItem.getIndentationLeft () ) );
			} else {
				if ( Math.abs ( listItem.getIndentationLeft () ) > Float.MIN_VALUE )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( listItem.getIndentationLeft () ) );
			}

			obj = parent.getAttribute ( PdfName.ENDINDENT );
			if ( obj instanceof PdfNumber ) {
				float endIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( endIndent, listItem.getIndentationRight () ) != 0 )
					this.setAttribute ( PdfName.ENDINDENT, new PdfNumber ( listItem.getIndentationRight () ) );
			} else {
				if ( Float.compare ( listItem.getIndentationRight (), 0 ) != 0 )
					this.setAttribute ( PdfName.ENDINDENT, new PdfNumber ( listItem.getIndentationRight () ) );
			}
		}
	}

	private void writeAttributes () {
	}

	private void writeAttributes ( final ListLabel listLabel ) {
		if ( listLabel != null ) {
			PdfObject obj = parent.getAttribute ( PdfName.STARTINDENT );
			if ( obj instanceof PdfNumber ) {
				float startIndent = ( (PdfNumber) obj ).floatValue ();
				if ( Float.compare ( startIndent, listLabel.getIndentation () ) != 0 )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( listLabel.getIndentation () ) );
			} else {
				if ( Math.abs ( listLabel.getIndentation () ) > Float.MIN_VALUE )
					this.setAttribute ( PdfName.STARTINDENT, new PdfNumber ( listLabel.getIndentation () ) );
			}
		}
	}

	private void writeAttributes ( final PdfPTable table ) {
		if ( table != null ) {
			if ( Float.compare ( table.getSpacingBefore (), 0f ) != 0 )
				this.setAttribute ( PdfName.SPACEBEFORE, new PdfNumber ( table.getSpacingBefore () ) );

			if ( Float.compare ( table.getSpacingAfter (), 0f ) != 0 )
				this.setAttribute ( PdfName.SPACEAFTER, new PdfNumber ( table.getSpacingAfter () ) );

			if ( table.getTotalHeight () > 0 ) {
				this.setAttribute ( PdfName.HEIGHT, new PdfNumber ( table.getTotalHeight () ) );
			}
			if ( table.getTotalWidth () > 0 ) {
				this.setAttribute ( PdfName.WIDTH, new PdfNumber ( table.getTotalWidth () ) );
			}
		}
	}

	private void writeAttributes ( final PdfPRow row ) {
		if ( row != null ) {
			this.setAttribute ( PdfName.O, PdfName.TABLE );
		}
	}

	private void writeAttributes ( final PdfPCell cell ) {
		if ( cell != null ) {
			this.setAttribute ( PdfName.O, PdfName.TABLE );
			if ( cell.getColspan () != 1 ) {
				this.setAttribute ( PdfName.COLSPAN, new PdfNumber ( cell.getColspan () ) );
			}
			if ( cell.getRowspan () != 1 ) {
				this.setAttribute ( PdfName.ROWSPAN, new PdfNumber ( cell.getRowspan () ) );
			}
			if ( cell.getHeaders () != null ) {
				PdfArray headers = new PdfArray ();
				ArrayList<PdfPHeaderCell> list = cell.getHeaders ();
				for ( PdfPHeaderCell header : list ) {
					if ( header.getName () != null )
						headers.add ( new PdfString ( header.getName () ) );
				}
				if ( !headers.isEmpty () )
					this.setAttribute ( PdfName.HEADERS, headers );
			}

			if ( cell.getFixedHeight () > 0 ) {
				this.setAttribute ( PdfName.HEIGHT, new PdfNumber ( cell.getFixedHeight () ) );
			}

			if ( cell.getWidth () > 0 ) {
				this.setAttribute ( PdfName.WIDTH, new PdfNumber ( cell.getWidth () ) );
			}

			if ( cell.getBackgroundColor () != null ) {
				BaseColor color = cell.getBackgroundColor ();
				this.setAttribute ( PdfName.BACKGROUNDCOLOR,
								new PdfArray ( new float[] { color.getRed () / 255f, color.getGreen () / 255f, color.getBlue () / 255f } ) );
			}
		}
	}

	private void writeAttributes ( final PdfPHeaderCell headerCell ) {
		if ( headerCell != null ) {
			if ( headerCell.getScope () != PdfPHeaderCell.NONE ) {
				switch ( headerCell.getScope () ) {
				case PdfPHeaderCell.ROW:
					this.setAttribute ( PdfName.SCOPE, PdfName.ROW );
					break;
				case PdfPHeaderCell.COLUMN:
					this.setAttribute ( PdfName.SCOPE, PdfName.COLUMN );
					break;
				case PdfPHeaderCell.BOTH:
					this.setAttribute ( PdfName.SCOPE, PdfName.BOTH );
					break;
				}
			}
			if ( headerCell.getName () != null )
				this.setAttribute ( PdfName.NAME, new PdfName ( headerCell.getName () ) );
			writeAttributes ( (PdfPCell) headerCell );
		}
	}

	private void writeAttributes ( final PdfPTableHeader header ) {
		if ( header != null ) {
			this.setAttribute ( PdfName.O, PdfName.TABLE );
		}
	}

	private void writeAttributes ( final PdfPTableBody body ) {
	}

	private void writeAttributes ( final PdfPTableFooter footer ) {
	}

	private void writeAttributes ( final PdfDiv div ) {
		if ( div != null ) {
			if ( div.getBackgroundColor () != null )
				setColorAttribute ( div.getBackgroundColor (), null, PdfName.BACKGROUNDCOLOR );

			setTextAlignAttribute ( div.getTextAlignment () );
		}
	}

	private void writeAttributes ( final Document document ) {
	}

	private boolean colorsEqual ( PdfArray parentColor, float[] color ) {
		if ( Float.compare ( color[0], parentColor.getAsNumber ( 0 ).floatValue () ) != 0 ) {
			return false;
		}
		if ( Float.compare ( color[1], parentColor.getAsNumber ( 1 ).floatValue () ) != 0 ) {
			return false;
		}
		return Float.compare ( color[2], parentColor.getAsNumber ( 2 ).floatValue () ) == 0;
	}

	private void setColorAttribute ( BaseColor newColor, PdfObject oldColor, PdfName attributeName ) {
		float[] colorArr = new float[] { newColor.getRed () / 255f, newColor.getGreen () / 255f, newColor.getBlue () / 255f };
		if ( ( oldColor instanceof PdfArray ) ) {
			PdfArray oldC = (PdfArray) oldColor;
			if ( colorsEqual ( oldC, colorArr ) ) {
				this.setAttribute ( attributeName, new PdfArray ( colorArr ) );
			} else
				this.setAttribute ( attributeName, new PdfArray ( colorArr ) );
		} else
			this.setAttribute ( attributeName, new PdfArray ( colorArr ) );
	}

	private void setTextAlignAttribute ( final int elementAlign ) {
		PdfName align = null;
		switch ( elementAlign ) {
		case Element.ALIGN_LEFT:
			align = PdfName.START;
			break;
		case Element.ALIGN_CENTER:
			align = PdfName.CENTER;
			break;
		case Element.ALIGN_RIGHT:
			align = PdfName.END;
			break;
		case Element.ALIGN_JUSTIFIED:
			align = PdfName.JUSTIFY;
			break;
		}
		PdfObject obj = parent.getAttribute ( PdfName.TEXTALIGN );
		if ( obj instanceof PdfName ) {
			PdfName textAlign = ( (PdfName) obj );
			if ( align != null && !textAlign.equals ( align ) )
				this.setAttribute ( PdfName.TEXTALIGN, align );
		} else {
			if ( align != null && !PdfName.START.equals ( align ) )
				this.setAttribute ( PdfName.TEXTALIGN, align );
		}
	}

	@Override
	public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
		PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_STRUCTELEM, this );
		super.toPdf ( writer, os );
	}

}
