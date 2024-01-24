/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.AccessibleElementId;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Anchor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Annotation;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListItem;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListLabel;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.MarkedObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.MarkedSection;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Meta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Section;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TabSettings;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TabStop;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Version;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.api.WriterOperation;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.collection.PdfCollection;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw.DrawInterface;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfAnnotationsImp;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfViewerPreferencesImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;


public class PdfDocument extends Document {

	static final String hangingPunctuation = ".,;:'";

	private final Stack<Float> leadingStack = new Stack<> ();

	protected PdfWriter writer;

	protected HashMap<AccessibleElementId, PdfStructureElement> structElements = new HashMap<> ();

	protected boolean openMCDocument = false;

	protected HashMap<Object, int[]> structParentIndices = new HashMap<> ();

	protected HashMap<Object, Integer> markPoints = new HashMap<> ();

	protected PdfContentByte text;

	protected PdfContentByte graphics;

	protected float leading = 0;

	protected int alignment = Element.ALIGN_LEFT;

	protected float currentHeight = 0;

	protected boolean isSectionTitle = false;

	protected PdfAction anchorAction = null;

	protected TabSettings tabSettings;

	protected int textEmptySize;

	protected float nextMarginLeft;

	protected float nextMarginRight;

	protected float nextMarginTop;

	protected float nextMarginBottom;

	protected boolean firstPageEvent = true;

	protected PdfLine line = null;

	protected ArrayList<PdfLine> lines = new ArrayList<> ();

	protected int lastElementType = -1;

	protected Indentation indentation = new Indentation ();

	protected PdfInfo info = new PdfInfo ();

	protected PdfOutline rootOutline;

	protected PdfOutline currentOutline;

	protected PdfViewerPreferencesImp viewerPreferences = new PdfViewerPreferencesImp ();

	protected PdfPageLabels pageLabels;

	protected TreeMap<String, Destination> localDestinations = new TreeMap<> ();

	protected HashMap<String, PdfObject> documentLevelJS = new HashMap<> ();

	protected HashMap<String, PdfObject> documentFileAttachment = new HashMap<> ();

	protected String openActionName;

	protected PdfAction openActionAction;

	protected PdfDictionary additionalActions;

	protected PdfCollection collection;

	protected PdfString language;

	protected Rectangle nextPageSize = null;

	protected HashMap<String, PdfRectangle> thisBoxSize = new HashMap<> ();

	protected HashMap<String, PdfRectangle> boxSize = new HashMap<> ();

	protected PdfDictionary pageAA = null;

	protected PageResources pageResources;

	protected boolean strictImageSequence = false;

	protected float imageEnd = -1;

	protected Image imageWait = null;

	PdfAnnotationsImp annotationsImp;

	private boolean pageEmpty = true;

	private ArrayList<Element> floatingElements = new ArrayList<> ();

	public PdfDocument () {
		super ();
		addProducer ();
		addCreationDate ();
	}

	private static boolean isTagged ( final PdfWriter writer ) {
		return ( writer != null ) && writer.isTagged ();
	}

	public void addWriter ( final PdfWriter writer ) throws DocumentException {
		if ( this.writer == null ) {
			this.writer = writer;
			annotationsImp = new PdfAnnotationsImp ( writer );
			return;
		}
		throw new DocumentException ( MessageLocalization.getComposedMessage ( "you.can.only.add.a.writer.to.a.pdfdocument.once" ) );
	}

	public float getLeading () {
		return leading;
	}

	protected void pushLeading () {
		leadingStack.push ( leading );
	}

	protected void popLeading () {
		leading = leadingStack.pop ();
		if ( !leadingStack.isEmpty () )
			leading = leadingStack.peek ();
	}

	@Override
	public boolean add ( final Element element ) throws DocumentException {
		if ( writer != null && writer.isPaused () ) {
			return false;
		}
		try {
			if ( element.type () != Element.DIV ) {
				flushFloatingElements ();
			}
			switch ( element.type () ) {
			case Element.HEADER:
				info.addkey ( ( (Meta) element ).getName (), ( (Meta) element ).getContent () );
				break;
			case Element.TITLE:
				info.addTitle ( ( (Meta) element ).getContent () );
				break;
			case Element.SUBJECT:
				info.addSubject ( ( (Meta) element ).getContent () );
				break;
			case Element.KEYWORDS:
				info.addKeywords ( ( (Meta) element ).getContent () );
				break;
			case Element.AUTHOR:
				info.addAuthor ( ( (Meta) element ).getContent () );
				break;
			case Element.CREATOR:
				info.addCreator ( ( (Meta) element ).getContent () );
				break;
			case Element.LANGUAGE:
				setLanguage ( ( (Meta) element ).getContent () );
				break;
			case Element.PRODUCER:
				info.addProducer ();
				break;
			case Element.CREATIONDATE:
				info.addCreationDate ();
				break;
			case Element.CHUNK: {
				if ( line == null ) {
					carriageReturn ();
				}

				PdfChunk chunk = new PdfChunk ( (Chunk) element, anchorAction, tabSettings );
				{
					PdfChunk overflow;
					while ( ( overflow = line.add ( chunk ) ) != null ) {
						carriageReturn ();
						boolean newlineSplit = chunk.isNewlineSplit ();
						chunk = overflow;
						if ( !newlineSplit )
							chunk.trimFirstSpace ();
					}
				}

				pageEmpty = false;
				if ( chunk.isAttribute ( Chunk.NEWPAGE ) ) {
					newPage ();
				}
				break;
			}
			case Element.ANCHOR: {
				Anchor anchor = (Anchor) element;
				String url = anchor.getReference ();
				leading = anchor.getLeading ();
				pushLeading ();
				if ( url != null ) {
					anchorAction = new PdfAction ( url );
				}
				element.process ( this );
				anchorAction = null;
				popLeading ();
				break;
			}
			case Element.ANNOTATION: {
				if ( line == null ) {
					carriageReturn ();
				}
				Annotation annot = (Annotation) element;
				Rectangle rect = new Rectangle ( 0, 0 );
				if ( line != null )
					rect = new Rectangle ( annot.llx ( indentRight () - line.widthLeft () ), annot.ury ( indentTop () - currentHeight - 20 ),
									annot.urx ( indentRight () - line.widthLeft () + 20 ), annot.lly ( indentTop () - currentHeight ) );
				PdfAnnotation an = PdfAnnotationsImp.convertAnnotation ( writer, annot, rect );
				annotationsImp.addPlainAnnotation ( an );
				pageEmpty = false;
				break;
			}
			case Element.PHRASE: {
				TabSettings backupTabSettings = tabSettings;
				if ( ( (Phrase) element ).getTabSettings () != null )
					tabSettings = ( (Phrase) element ).getTabSettings ();
				leading = ( (Phrase) element ).getTotalLeading ();
				pushLeading ();
				element.process ( this );
				tabSettings = backupTabSettings;
				popLeading ();
				break;
			}
			case Element.PARAGRAPH: {
				TabSettings backupTabSettings = tabSettings;
				if ( ( (Phrase) element ).getTabSettings () != null )
					tabSettings = ( (Phrase) element ).getTabSettings ();
				// we cast the element to a paragraph
				Paragraph paragraph = (Paragraph) element;
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.openMCBlock ( paragraph );
				}
				addSpacing ( paragraph.getSpacingBefore (), leading, paragraph.getFont () );

				// we adjust the parameters of the document
				alignment = paragraph.getAlignment ();
				leading = paragraph.getTotalLeading ();
				pushLeading ();
				carriageReturn ();

				// we don't want to make orphans/widows
				if ( currentHeight + line.height () + leading > indentTop () - indentBottom () ) {
					newPage ();
				}
				indentation.indentLeft += paragraph.getIndentationLeft ();
				indentation.indentRight += paragraph.getIndentationRight ();
				carriageReturn ();

				PdfPageEvent pageEvent = writer.getPageEvent ();
				if ( pageEvent != null && !isSectionTitle )
					pageEvent.onParagraph ( writer, this, indentTop () - currentHeight );

				// if a paragraph has to be kept together, we wrap it in a table object
				if ( paragraph.getKeepTogether () ) {
					carriageReturn ();
					PdfPTable table = new PdfPTable ( 1 );
					table.setKeepTogether ( paragraph.getKeepTogether () );
					table.setWidthPercentage ( 100f );
					PdfPCell cell = new PdfPCell ();
					cell.addElement ( paragraph );
					cell.setBorder ( Rectangle.NO_BORDER );
					cell.setPadding ( 0 );
					table.addCell ( cell );
					indentation.indentLeft -= paragraph.getIndentationLeft ();
					indentation.indentRight -= paragraph.getIndentationRight ();
					this.add ( table );
					indentation.indentLeft += paragraph.getIndentationLeft ();
					indentation.indentRight += paragraph.getIndentationRight ();
				} else {
					line.setExtraIndent ( paragraph.getFirstLineIndent () );
					element.process ( this );
					carriageReturn ();
					addSpacing ( paragraph.getSpacingAfter (), paragraph.getTotalLeading (), paragraph.getFont () );
				}

				if ( pageEvent != null && !isSectionTitle )
					pageEvent.onParagraphEnd ( writer, this, indentTop () - currentHeight );

				alignment = Element.ALIGN_LEFT;
				indentation.indentLeft -= paragraph.getIndentationLeft ();
				indentation.indentRight -= paragraph.getIndentationRight ();
				carriageReturn ();
				tabSettings = backupTabSettings;
				popLeading ();
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.closeMCBlock ( paragraph );
				}
				break;
			}
			case Element.SECTION:
			case Element.CHAPTER: {
				// Chapters and Sections only differ in their constructor
				// so we cast both to a Section
				Section section = (Section) element;
				PdfPageEvent pageEvent = writer.getPageEvent ();

				boolean hasTitle = section.isNotAddedYet ()
								&& section.getTitle () != null;

				// if the section is a chapter, we begin a new page
				if ( section.isTriggerNewPage () ) {
					newPage ();
				}

				if ( hasTitle ) {
					float fith = indentTop () - currentHeight;
					int rotation = pageSize.getRotation ();
					if ( rotation == 90 || rotation == 180 )
						fith = pageSize.getHeight () - fith;
					PdfDestination destination = new PdfDestination ( PdfDestination.FITH, fith );
					while ( currentOutline.level () >= section.getDepth () ) {
						currentOutline = currentOutline.parent ();
					}
					currentOutline = new PdfOutline ( currentOutline, destination, section.getBookmarkTitle (), section.isBookmarkOpen () );
				}

				// some values are set
				carriageReturn ();
				indentation.sectionIndentLeft += section.getIndentationLeft ();
				indentation.sectionIndentRight += section.getIndentationRight ();

				if ( section.isNotAddedYet () && pageEvent != null )
					if ( element.type () == Element.CHAPTER )
						pageEvent.onChapter ( writer, this, indentTop () - currentHeight, section.getTitle () );
					else
						pageEvent.onSection ( writer, this, indentTop () - currentHeight, section.getDepth (), section.getTitle () );

				// the title of the section (if any has to be printed)
				if ( hasTitle ) {
					isSectionTitle = true;
					add ( section.getTitle () );
					isSectionTitle = false;
				}
				indentation.sectionIndentLeft += section.getIndentation ();
				// we process the section
				element.process ( this );
				flushLines ();
				// some parameters are set back to normal again
				indentation.sectionIndentLeft -= section.getIndentationLeft () + section.getIndentation ();
				indentation.sectionIndentRight -= section.getIndentationRight ();

				if ( section.isComplete () && pageEvent != null )
					if ( element.type () == Element.CHAPTER )
						pageEvent.onChapterEnd ( writer, this, indentTop () - currentHeight );
					else
						pageEvent.onSectionEnd ( writer, this, indentTop () - currentHeight );

				break;
			}
			case Element.LIST: {
				// we cast the element to a List
				List list = (List) element;
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.openMCBlock ( list );
				}
				if ( list.isAlignindent () ) {
					list.normalizeIndentation ();
				}
				// we adjust the document
				indentation.listIndentLeft += list.getIndentationLeft ();
				indentation.indentRight += list.getIndentationRight ();
				// we process the items in the list
				element.process ( this );

				// some parameters are set back to normal again
				indentation.listIndentLeft -= list.getIndentationLeft ();
				indentation.indentRight -= list.getIndentationRight ();
				carriageReturn ();
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.closeMCBlock ( list );
				}
				break;
			}
			case Element.LISTITEM: {
				// we cast the element to a ListItem
				ListItem listItem = (ListItem) element;
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.openMCBlock ( listItem );
				}

				addSpacing ( listItem.getSpacingBefore (), leading, listItem.getFont () );

				// we adjust the document
				alignment = listItem.getAlignment ();
				indentation.listIndentLeft += listItem.getIndentationLeft ();
				indentation.indentRight += listItem.getIndentationRight ();
				leading = listItem.getTotalLeading ();
				pushLeading ();
				carriageReturn ();

				// we prepare the current line to be able to show us the listsymbol
				line.setListItem ( listItem );
				// we process the item
				element.process ( this );
				addSpacing ( listItem.getSpacingAfter (), listItem.getTotalLeading (), listItem.getFont () );

				// if the last line is justified, it should be aligned to the left
				if ( line.hasToBeJustified () ) {
					line.resetAlignment ();
				}
				// some parameters are set back to normal again
				carriageReturn ();
				indentation.listIndentLeft -= listItem.getIndentationLeft ();
				indentation.indentRight -= listItem.getIndentationRight ();
				popLeading ();
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.closeMCBlock ( listItem.getListBody () );
					text.closeMCBlock ( listItem );
				}
				break;
			}
			case Element.RECTANGLE: {
				Rectangle rectangle = (Rectangle) element;
				graphics.rectangle ( rectangle );
				pageEmpty = false;
				break;
			}
			case Element.PTABLE: {
				PdfPTable ptable = (PdfPTable) element;
				if ( ptable.size () <= ptable.getHeaderRows () )
					break; //nothing to do

				// before every table, we add a new line and flush all lines
				ensureNewLine ();
				flushLines ();

				addPTable ( ptable );
				pageEmpty = false;
				newLine ();
				break;
			}
			case Element.JPEG:
			case Element.JPEG2000:
			case Element.JBIG2:
			case Element.IMGRAW:
			case Element.IMGTEMPLATE: {
				//carriageReturn(); suggestion by Marc Campforts
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.openMCBlock ( (Image) element );
				}
				add ( (Image) element );
				if ( isTagged ( writer ) ) {
					flushLines ();
					text.closeMCBlock ( (Image) element );
				}
				break;
			}
			case Element.YMARK: {
				DrawInterface zh = (DrawInterface) element;
				zh.draw ( graphics, indentLeft (), indentBottom (), indentRight (), indentTop (),
								indentTop () - currentHeight - ( !leadingStack.isEmpty () ? leading : 0 ) );
				pageEmpty = false;
				break;
			}
			case Element.MARKED: {
				MarkedObject mo;
				if ( element instanceof MarkedSection ) {
					mo = ( (MarkedSection) element ).getTitle ();
					if ( mo != null ) {
						mo.process ( this );
					}
				}
				mo = (MarkedObject) element;
				mo.process ( this );
				break;
			}
			case Element.WRITABLE_DIRECT:
				if ( null != writer ) {
					( (WriterOperation) element ).write ( writer, this );
				}
				break;
			case Element.DIV:
				ensureNewLine ();
				flushLines ();
				addDiv ( (PdfDiv) element );
				pageEmpty = false;
				//newLine();
				break;
			default:
				return false;
			}
			lastElementType = element.type ();
			return true;
		} catch ( Exception e ) {
			throw new DocumentException ( e );
		}
	}

	@Override
	public void open () {
		if ( !open ) {
			super.open ();
			writer.open ();
			rootOutline = new PdfOutline ( writer );
			currentOutline = rootOutline;
		}
		try {
			initPage ();
			if ( isTagged ( writer ) ) {
				openMCDocument = true;
			}
		} catch ( DocumentException de ) {
			throw new ExceptionConverter ( de );
		}
	}

	@Override
	public void close () {
		if ( close ) {
			return;
		}
		try {
			if ( isTagged ( writer ) ) {
				flushFloatingElements ();
				flushLines ();
				writer.getDirectContent ().closeMCBlock ( this );
				writer.flushAcroFields ();
				writer.flushTaggedObjects ();
				if ( isPageEmpty () ) {
					int pageReferenceCount = writer.pageReferences.size ();
					if ( pageReferenceCount > 0 && writer.currentPageNumber == pageReferenceCount ) {
						writer.pageReferences.remove ( pageReferenceCount - 1 );
					}
				}
			} else
				writer.flushAcroFields ();
			boolean wasImage = imageWait != null;
			newPage ();
			if ( imageWait != null || wasImage )
				newPage ();
			if ( annotationsImp.hasUnusedAnnotations () )
				throw new RuntimeException ( MessageLocalization.getComposedMessage (
								"not.all.annotations.could.be.added.to.the.document.the.document.doesn.t.have.enough.pages" ) );
			PdfPageEvent pageEvent = writer.getPageEvent ();
			if ( pageEvent != null )
				pageEvent.onCloseDocument ( writer, this );
			super.close ();

			writer.addLocalDestinations ( localDestinations );
			calculateOutlineCount ();
			writeOutlines ();
		} catch ( Exception e ) {
			throw ExceptionConverter.convertException ( e );
		}

		writer.close ();
	}

	@Override
	public void newPage () {
		try {
			flushFloatingElements ();
		} catch ( DocumentException de ) {
			// maybe this never happens, but it's better to check.
			throw new ExceptionConverter ( de );
		}
		lastElementType = -1;
		if ( isPageEmpty () ) {
			setNewPageSizeAndMargins ();
			return;
		}
		if ( !open || close ) {
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "the.document.is.not.open" ) );
		}
		PdfPageEvent pageEvent = writer.getPageEvent ();
		if ( pageEvent != null )
			pageEvent.onEndPage ( writer, this );

		super.newPage ();

		indentation.imageIndentLeft = 0;
		indentation.imageIndentRight = 0;

		try {
			flushLines ();

			int rotation = pageSize.getRotation ();

			if ( writer.isPdfIso () ) {
				if ( thisBoxSize.containsKey ( "art" ) && thisBoxSize.containsKey ( "trim" ) )
					throw new PdfXConformanceException ( MessageLocalization.getComposedMessage ( "only.one.of.artbox.or.trimbox.can.exist.in.the.page" ) );
				if ( !thisBoxSize.containsKey ( "art" ) && !thisBoxSize.containsKey ( "trim" ) ) {
					if ( thisBoxSize.containsKey ( "crop" ) )
						thisBoxSize.put ( "trim", thisBoxSize.get ( "crop" ) );
					else
						thisBoxSize.put ( "trim", new PdfRectangle ( pageSize, pageSize.getRotation () ) );
				}
			}

			// [M1]
			pageResources.addDefaultColorDiff ( writer.getDefaultColorspace () );
			if ( writer.isRgbTransparencyBlending () ) {
				PdfDictionary dcs = new PdfDictionary ();
				dcs.put ( PdfName.CS, PdfName.DEVICERGB );
				pageResources.addDefaultColorDiff ( dcs );
			}
			PdfDictionary resources = pageResources.getResources ();

			// we create the page dictionary

			PdfPage page = new PdfPage ( new PdfRectangle ( pageSize, rotation ), thisBoxSize, resources, rotation );
			if ( isTagged ( writer ) ) {
				page.put ( PdfName.TABS, PdfName.S );
			} else {
				page.put ( PdfName.TABS, writer.getTabs () );
			}
			page.putAll ( writer.getPageDictEntries () );
			writer.resetPageDictEntries ();

			// we complete the page dictionary

			// [U3] page actions: additional actions
			if ( pageAA != null ) {
				page.put ( PdfName.AA, writer.addToBody ( pageAA ).getIndirectReference () );
				pageAA = null;
			}

			if ( annotationsImp.hasUnusedAnnotations () ) {
				PdfArray array = annotationsImp.rotateAnnotations ( writer, pageSize );
				if ( !array.isEmpty () )
					page.put ( PdfName.ANNOTS, array );
			}

			// [F12] we add tag info
			if ( isTagged ( writer ) )
				page.put ( PdfName.STRUCTPARENTS, new PdfNumber ( getStructParentIndex ( writer.getCurrentPage () ) ) );

			if ( text.size () > textEmptySize || isTagged ( writer ) )
				text.endText ();
			else
				text = null;

			ArrayList<IAccessibleElement> mcBlocks = null;
			if ( isTagged ( writer ) ) {
				mcBlocks = writer.getDirectContent ().saveMCBlocks ();
			}
			writer.add ( page, new PdfContents ( writer.getDirectContentUnder (), graphics, !isTagged ( writer ) ? text : null, writer.getDirectContent (),
							pageSize ) );
			// we initialize the new page
			initPage ();

			if ( isTagged ( writer ) ) {
				writer.getDirectContentUnder ().restoreMCBlocks ( mcBlocks );
			}

		} catch ( DocumentException | IOException de ) {
			throw new ExceptionConverter ( de );
		}
	}

	@Override
	public void setPageSize ( final Rectangle pageSize ) {
		if ( writer != null && writer.isPaused () ) {
			return;
		}
		nextPageSize = new Rectangle ( pageSize );
	}

	@Override
	public void setMargins ( final float marginLeft, final float marginRight, final float marginTop, final float marginBottom ) {
		if ( writer != null && writer.isPaused () ) {
			return;
		}
		nextMarginLeft = marginLeft;
		nextMarginRight = marginRight;
		nextMarginTop = marginTop;
		nextMarginBottom = marginBottom;
	}

	@Override
	public boolean setMarginMirroring ( final boolean MarginMirroring ) {
		if ( writer != null && writer.isPaused () ) {
			return false;
		}
		return super.setMarginMirroring ( MarginMirroring );
	}

	@Override
	public boolean setMarginMirroringTopBottom ( final boolean MarginMirroringTopBottom ) {
		if ( writer != null && writer.isPaused () ) {
			return false;
		}
		return super.setMarginMirroringTopBottom ( MarginMirroringTopBottom );
	}

	@Override
	public void setPageCount ( final int pageN ) {
		if ( writer != null && writer.isPaused () ) {
			return;
		}
		super.setPageCount ( pageN );
	}

	@Override
	public void resetPageCount () {
		if ( writer != null && writer.isPaused () ) {
			return;
		}
		super.resetPageCount ();
	}

	protected void initPage () throws DocumentException {
		pageN++;

		annotationsImp.resetAnnotations ();
		pageResources = new PageResources ();

		writer.resetContent ();
		if ( isTagged ( writer ) ) {
			graphics = writer.getDirectContentUnder ().getDuplicate ();
			writer.getDirectContent ().duplicatedFrom = graphics;
		} else {
			graphics = new PdfContentByte ( writer );
		}

		setNewPageSizeAndMargins ();
		imageEnd = -1;
		indentation.imageIndentRight = 0;
		indentation.imageIndentLeft = 0;
		indentation.indentBottom = 0;
		indentation.indentTop = 0;
		currentHeight = 0;

		// backgroundcolors, etc...
		thisBoxSize = new HashMap<> ( boxSize );
		if ( pageSize.getBackgroundColor () != null
						|| pageSize.hasBorders ()
						|| pageSize.getBorderColor () != null ) {
			add ( pageSize );
		}

		float oldleading = leading;
		int oldAlignment = alignment;
		pageEmpty = true;
		// if there is an image waiting to be drawn, draw it
		try {
			if ( imageWait != null ) {
				add ( imageWait );
				imageWait = null;
			}
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
		leading = oldleading;
		alignment = oldAlignment;
		carriageReturn ();

		PdfPageEvent pageEvent = writer.getPageEvent ();
		if ( pageEvent != null ) {
			if ( firstPageEvent ) {
				pageEvent.onOpenDocument ( writer, this );
			}
			pageEvent.onStartPage ( writer, this );
		}
		firstPageEvent = false;
	}

	protected void newLine () throws DocumentException {
		lastElementType = -1;
		carriageReturn ();
		if ( lines != null && !lines.isEmpty () ) {
			lines.add ( line );
			currentHeight += line.height ();
		}
		line = new PdfLine ( indentLeft (), indentRight (), alignment, leading );
	}

	protected void carriageReturn () {
		// the arraylist with lines may not be null
		if ( lines == null ) {
			lines = new ArrayList<> ();
		}
		// If the current line is not null or empty
		if ( line != null && line.size () > 0 ) {
			// we check if the end of the page is reached (bugfix by Francois Gravel)
			if ( currentHeight + line.height () + leading > indentTop () - indentBottom () ) {
				PdfLine overflowLine = line;
				line = null;
				newPage ();
				line = overflowLine;
				//update left indent because of mirror margins.
				overflowLine.left = indentLeft ();
			}
			currentHeight += line.height ();
			lines.add ( line );
			pageEmpty = false;
		}
		if ( imageEnd > -1 && currentHeight > imageEnd ) {
			imageEnd = -1;
			indentation.imageIndentRight = 0;
			indentation.imageIndentLeft = 0;
		}
		// a new current line is constructed
		line = new PdfLine ( indentLeft (), indentRight (), alignment, leading );
	}

	protected void ensureNewLine () {
		try {
			if ( lastElementType == Element.PHRASE ||
							lastElementType == Element.CHUNK ) {
				newLine ();
				flushLines ();
			}
		} catch ( DocumentException ex ) {
			throw new ExceptionConverter ( ex );
		}
	}

	protected void flushLines () throws DocumentException {
		if ( lines == null ) {
			return;
		}
		if ( line != null && line.size () > 0 ) {
			lines.add ( line );
			line = new PdfLine ( indentLeft (), indentRight (), alignment, leading );
		}

		if ( lines.isEmpty () ) {
			return;
		}

		Object[] currentValues = new Object[2];
		PdfFont currentFont = null;
		float lastBaseFactor = (float) 0;
		currentValues[1] = lastBaseFactor;
		for ( PdfLine l : lines ) {
			float moveTextX = l.indentLeft () - indentLeft () + indentation.indentLeft + indentation.listIndentLeft + indentation.sectionIndentLeft;
			text.moveText ( moveTextX, -l.height () );
			l.flush ();

			if ( l.listSymbol () != null ) {
				ListLabel lbl = null;
				Chunk symbol = l.listSymbol ();
				if ( isTagged ( writer ) ) {
					lbl = l.listItem ().getListLabel ();
					graphics.openMCBlock ( lbl );
					symbol = new Chunk ( symbol );
					symbol.setRole ( null );
				}
				ColumnText.showTextAligned ( graphics, Element.ALIGN_LEFT, new Phrase ( symbol ), text.getXTLM () - l.listIndent (), text.getYTLM (), 0 );
				if ( lbl != null ) {
					graphics.closeMCBlock ( lbl );
				}
			}

			currentValues[0] = currentFont;

			if ( isTagged ( writer ) && l.listItem () != null ) {
				text.openMCBlock ( l.listItem ().getListBody () );
			}
			writeLineToContent ( l, text, graphics, currentValues, writer.getSpaceCharRatio () );

			currentFont = (PdfFont) currentValues[0];
			l.height ();
			text.moveText ( -moveTextX, 0 );

		}
		lines = new ArrayList<> ();
	}

	float writeLineToContent ( final PdfLine line, final PdfContentByte text, final PdfContentByte graphics, final Object[] currentValues, final float ratio )
					throws DocumentException {
		PdfFont currentFont = (PdfFont) currentValues[0];
		float lastBaseFactor = (Float) currentValues[1];
		PdfChunk chunk;
		int numberOfSpaces;
		int lineLen;
		boolean isJustified;
		float hangingCorrection = 0;
		float hScale;
		float lastHScale = Float.NaN;
		float baseWordSpacing = 0;
		float baseCharacterSpacing = 0;
		float glueWidth = 0;
		float lastX = text.getXTLM () + line.getOriginalWidth ();
		numberOfSpaces = line.numberOfSpaces ();
		lineLen = line.getLineLengthUtf32 ();
		isJustified = line.hasToBeJustified () && ( numberOfSpaces != 0 || lineLen > 1 );
		int separatorCount = line.getSeparatorCount ();
		if ( separatorCount > 0 ) {
			glueWidth = line.widthLeft () / separatorCount;
		} else if ( isJustified && separatorCount == 0 ) {
			float v = lastBaseFactor * ( ratio * numberOfSpaces + lineLen - 1 );
			if ( line.isNewlineSplit () && line.widthLeft () >= v ) {
				if ( line.isRTL () ) {
					text.moveText ( line.widthLeft () - v, 0 );
				}
				baseWordSpacing = ratio * lastBaseFactor;
				baseCharacterSpacing = lastBaseFactor;
			} else {
				float width = line.widthLeft ();
				PdfChunk last = line.getChunk ( line.size () - 1 );
				if ( last != null ) {
					String s = last.toString ();
					char c;
					if ( !s.isEmpty () && hangingPunctuation.indexOf ( ( c = s.charAt ( s.length () - 1 ) ) ) >= 0 ) {
						float oldWidth = width;
						width += last.font ().width ( c ) * 0.4f;
						hangingCorrection = width - oldWidth;
					}
				}
				float baseFactor = width / ( ratio * numberOfSpaces + lineLen - 1 );
				baseWordSpacing = ratio * baseFactor;
				baseCharacterSpacing = baseFactor;
				lastBaseFactor = baseFactor;
			}
		} else if ( line.alignment == Element.ALIGN_LEFT || line.alignment == Element.ALIGN_UNDEFINED ) {
			lastX -= line.widthLeft ();
		}

		int lastChunkStroke = line.getLastStrokeChunk ();
		int chunkStrokeIdx = 0;
		float xMarker = text.getXTLM ();
		float baseXMarker = xMarker;
		float yMarker = text.getYTLM ();
		boolean adjustMatrix = false;
		float tabPosition = 0;

		for ( Iterator<PdfChunk> j = line.iterator (); j.hasNext (); ) {
			chunk = j.next ();
			if ( isTagged ( writer ) && chunk.accessibleElement != null ) {
				text.openMCBlock ( chunk.accessibleElement );
			}
			BaseColor color = chunk.color ();
			float fontSize = chunk.font ().size ();
			float ascender;
			float descender;
			if ( chunk.isImage () ) {
				ascender = chunk.height ();
				descender = 0;
			} else {
				ascender = chunk.font ().getFont ().getFontDescriptor ( BaseFont.ASCENT, fontSize );
				descender = chunk.font ().getFont ().getFontDescriptor ( BaseFont.DESCENT, fontSize );
			}
			hScale = 1;

			if ( chunkStrokeIdx <= lastChunkStroke ) {
				float width;
				if ( isJustified ) {
					width = chunk.getWidthCorrected ( baseCharacterSpacing, baseWordSpacing );
				} else {
					width = chunk.width ();
				}
				if ( chunk.isStroked () ) {
					PdfChunk nextChunk = line.getChunk ( chunkStrokeIdx + 1 );
					if ( chunk.isSeparator () ) {
						width = glueWidth;
						Object[] sep = (Object[]) chunk.getAttribute ( Chunk.SEPARATOR );
						DrawInterface di = (DrawInterface) sep[0];
						Boolean vertical = (Boolean) sep[1];
						if ( vertical ) {
							di.draw ( graphics, baseXMarker, yMarker + descender, baseXMarker + line.getOriginalWidth (), ascender - descender, yMarker );
						} else {
							di.draw ( graphics, xMarker, yMarker + descender, xMarker + width, ascender - descender, yMarker );
						}
					}
					if ( chunk.isTab () ) {
						if ( chunk.isAttribute ( Chunk.TABSETTINGS ) ) {
							TabStop tabStop = chunk.getTabStop ();
							if ( tabStop != null ) {
								tabPosition = tabStop.getPosition () + baseXMarker;
								if ( tabStop.getLeader () != null )
									tabStop.getLeader ().draw ( graphics, xMarker, yMarker + descender, tabPosition, ascender - descender, yMarker );
							} else {
								tabPosition = xMarker;
							}
						} else {
							//Keep deprecated tab logic for backward compatibility...
							Object[] tab = (Object[]) chunk.getAttribute ( Chunk.TAB );
							DrawInterface di = (DrawInterface) tab[0];
							tabPosition = (Float) tab[1] + (Float) tab[3];
							if ( tabPosition > xMarker ) {
								di.draw ( graphics, xMarker, yMarker + descender, tabPosition, ascender - descender, yMarker );
							}
						}
						float tmp = xMarker;
						xMarker = tabPosition;
						tabPosition = tmp;
					}
					if ( chunk.isAttribute ( Chunk.BACKGROUND ) ) {
						boolean inText = graphics.getInText ();
						if ( inText && isTagged ( writer ) ) {
							graphics.endText ();
						}
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.BACKGROUND ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						Object[] bgr = (Object[]) chunk.getAttribute ( Chunk.BACKGROUND );
						graphics.setColorFill ( (BaseColor) bgr[0] );
						float[] extra = (float[]) bgr[1];
						graphics.rectangle ( xMarker - extra[0],
										yMarker + descender - extra[1] + chunk.getTextRise (),
										width - subtract + extra[0] + extra[2],
										ascender - descender + extra[1] + extra[3] );
						graphics.fill ();
						graphics.setGrayFill ( 0 );
						if ( inText && isTagged ( writer ) ) {
							graphics.beginText ( true );
						}
					}
					if ( chunk.isAttribute ( Chunk.UNDERLINE ) && !chunk.isNewlineSplit () ) {
						boolean inText = graphics.getInText ();
						if ( inText && isTagged ( writer ) ) {
							graphics.endText ();
						}
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.UNDERLINE ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						Object[][] unders = (Object[][]) chunk.getAttribute ( Chunk.UNDERLINE );
						BaseColor scolor;
						for ( Object[] obj : unders ) {
							scolor = (BaseColor) obj[0];
							float[] ps = (float[]) obj[1];
							if ( scolor == null )
								scolor = color;
							if ( scolor != null )
								graphics.setColorStroke ( scolor );
							graphics.setLineWidth ( ps[0] + fontSize * ps[1] );
							float shift = ps[2] + fontSize * ps[3];
							int cap2 = (int) ps[4];
							if ( cap2 != 0 )
								graphics.setLineCap ( cap2 );
							graphics.moveTo ( xMarker, yMarker + shift );
							graphics.lineTo ( xMarker + width - subtract, yMarker + shift );
							graphics.stroke ();
							if ( scolor != null )
								graphics.resetGrayStroke ();
							if ( cap2 != 0 )
								graphics.setLineCap ( 0 );
						}
						graphics.setLineWidth ( 1 );
						if ( inText && isTagged ( writer ) ) {
							graphics.beginText ( true );
						}
					}
					if ( chunk.isAttribute ( Chunk.ACTION ) ) {
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.ACTION ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						PdfAnnotation annot;
						if ( chunk.isImage () ) {
							annot = new PdfAnnotation ( writer, xMarker, yMarker + chunk.getImageOffsetY (), xMarker + width - subtract,
											yMarker + chunk.getImageHeight () + chunk.getImageOffsetY (), (PdfAction) chunk.getAttribute ( Chunk.ACTION ) );
						} else {
							annot = new PdfAnnotation ( writer, xMarker, yMarker + descender + chunk.getTextRise (), xMarker + width - subtract,
											yMarker + ascender + chunk.getTextRise (), (PdfAction) chunk.getAttribute ( Chunk.ACTION ) );
						}
						text.addAnnotation ( annot, true );
						if ( isTagged ( writer ) && chunk.accessibleElement != null ) {
							int structParent = getStructParentIndex ( annot );
							annot.put ( PdfName.STRUCTPARENT, new PdfNumber ( structParent ) );
							PdfStructureElement strucElem = structElements.get ( chunk.accessibleElement.getId () );
							if ( strucElem != null ) {
								PdfArray kArray = strucElem.getAsArray ( PdfName.K );
								if ( kArray == null ) {
									kArray = new PdfArray ();
									PdfObject k = strucElem.get ( PdfName.K );
									if ( k != null ) {
										kArray.add ( k );
									}
									strucElem.put ( PdfName.K, kArray );
								}
								PdfDictionary dict = new PdfDictionary ();
								dict.put ( PdfName.TYPE, PdfName.OBJR );
								dict.put ( PdfName.OBJ, annot.getIndirectReference () );
								kArray.add ( dict );
								writer.getStructureTreeRoot ().setAnnotationMark ( structParent, strucElem.getReference () );
							}

						}
					}
					if ( chunk.isAttribute ( Chunk.REMOTEGOTO ) ) {
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.REMOTEGOTO ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						Object[] obj = (Object[]) chunk.getAttribute ( Chunk.REMOTEGOTO );
						String filename = (String) obj[0];
						if ( obj[1] instanceof String )
							remoteGoto ( filename, (String) obj[1], xMarker, yMarker + descender + chunk.getTextRise (), xMarker + width - subtract,
											yMarker + ascender + chunk.getTextRise () );
						else
							remoteGoto ( filename, (Integer) obj[1], xMarker, yMarker + descender + chunk.getTextRise (),
											xMarker + width - subtract, yMarker + ascender + chunk.getTextRise () );
					}
					if ( chunk.isAttribute ( Chunk.LOCALGOTO ) ) {
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.LOCALGOTO ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						localGoto ( (String) chunk.getAttribute ( Chunk.LOCALGOTO ), xMarker, yMarker, xMarker + width - subtract, yMarker + fontSize );
					}
					if ( chunk.isAttribute ( Chunk.LOCALDESTINATION ) ) {
						localDestination ( (String) chunk.getAttribute ( Chunk.LOCALDESTINATION ), new PdfDestination ( xMarker, yMarker + fontSize, 0 ) );
					}
					if ( chunk.isAttribute ( Chunk.GENERICTAG ) ) {
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.GENERICTAG ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						Rectangle rect = new Rectangle ( xMarker, yMarker, xMarker + width - subtract, yMarker + fontSize );
						PdfPageEvent pev = writer.getPageEvent ();
						if ( pev != null )
							pev.onGenericTag ( writer, this, rect, (String) chunk.getAttribute ( Chunk.GENERICTAG ) );
					}
					if ( chunk.isAttribute ( Chunk.PDFANNOTATION ) ) {
						float subtract = lastBaseFactor;
						if ( nextChunk != null && nextChunk.isAttribute ( Chunk.PDFANNOTATION ) )
							subtract = 0;
						if ( nextChunk == null )
							subtract += hangingCorrection;
						PdfAnnotation annot = PdfFormField.shallowDuplicate ( (PdfAnnotation) chunk.getAttribute ( Chunk.PDFANNOTATION ) );
						annot.put ( PdfName.RECT, new PdfRectangle ( xMarker, yMarker + descender, xMarker + width - subtract, yMarker + ascender ) );
						text.addAnnotation ( annot, true );
					}
					float[] params = (float[]) chunk.getAttribute ( Chunk.SKEW );
					Float hs = (Float) chunk.getAttribute ( Chunk.HSCALE );
					if ( params != null || hs != null ) {
						float b = 0, c = 0;
						if ( params != null ) {
							b = params[0];
							c = params[1];
						}
						if ( hs != null )
							hScale = hs;
						text.setTextMatrix ( hScale, b, c, 1, xMarker, yMarker );
					}
					if ( !isJustified ) {
						if ( chunk.isAttribute ( Chunk.WORD_SPACING ) ) {
							Float ws = (Float) chunk.getAttribute ( Chunk.WORD_SPACING );
							text.setWordSpacing ( ws );
						}

						if ( chunk.isAttribute ( Chunk.CHAR_SPACING ) ) {
							Float cs = (Float) chunk.getAttribute ( Chunk.CHAR_SPACING );
							text.setCharacterSpacing ( cs );
						}
					}
					if ( chunk.isImage () ) {
						Image image = chunk.getImage ();
						width = chunk.getImageWidth ();
						float[] matrix = image.matrix ( chunk.getImageScalePercentage () );
						matrix[Image.CX] = xMarker + chunk.getImageOffsetX () - matrix[Image.CX];
						matrix[Image.CY] = yMarker + chunk.getImageOffsetY () - matrix[Image.CY];
						graphics.addImage ( image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5] );
						text.moveText ( xMarker + lastBaseFactor + chunk.getImageWidth () - text.getXTLM (), 0 );
					}
				}

				xMarker += width;
				++chunkStrokeIdx;
			}

			if ( !chunk.isImage () && chunk.font ().compareTo ( currentFont ) != 0 ) {
				currentFont = chunk.font ();
				text.setFontAndSize ( currentFont.getFont (), currentFont.size () );
			}
			float rise = 0;
			Object[] textRender = (Object[]) chunk.getAttribute ( Chunk.TEXTRENDERMODE );
			int tr = 0;
			float strokeWidth = 1;
			BaseColor strokeColor = null;
			Float fr = (Float) chunk.getAttribute ( Chunk.SUBSUPSCRIPT );
			if ( textRender != null ) {
				tr = (Integer) textRender[0] & 3;
				if ( tr != PdfContentByte.TEXT_RENDER_MODE_FILL )
					text.setTextRenderingMode ( tr );
				if ( tr == PdfContentByte.TEXT_RENDER_MODE_STROKE || tr == PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE ) {
					strokeWidth = (Float) textRender[1];
					if ( strokeWidth != 1 )
						text.setLineWidth ( strokeWidth );
					strokeColor = (BaseColor) textRender[2];
					if ( strokeColor == null )
						strokeColor = color;
					if ( strokeColor != null )
						text.setColorStroke ( strokeColor );
				}
			}
			if ( fr != null )
				rise = fr;
			if ( color != null )
				text.setColorFill ( color );
			if ( rise != 0 )
				text.setTextRise ( rise );
			if ( chunk.isImage () ) {
				adjustMatrix = true;
			} else if ( chunk.isHorizontalSeparator () ) {
				PdfTextArray array = new PdfTextArray ();
				array.add ( -glueWidth * 1000f / chunk.font.size () / hScale );
				text.showText ( array );
			} else if ( chunk.isTab () && tabPosition != xMarker ) {
				PdfTextArray array = new PdfTextArray ();
				array.add ( ( tabPosition - xMarker ) * 1000f / chunk.font.size () / hScale );
				text.showText ( array );
			}
			// If it is a CJK chunk or Unicode TTF we will have to simulate the
			// space adjustment.
			else if ( isJustified && numberOfSpaces > 0 && chunk.isSpecialEncoding () ) {
				if ( hScale != lastHScale ) {
					lastHScale = hScale;
					text.setWordSpacing ( baseWordSpacing / hScale );
					text.setCharacterSpacing ( baseCharacterSpacing / hScale + text.getCharacterSpacing () );
				}
				String s = chunk.toString ();
				int idx = s.indexOf ( ' ' );
				if ( idx < 0 )
					text.showText ( s );
				else {
					float spaceCorrection = -baseWordSpacing * 1000f / chunk.font.size () / hScale;
					PdfTextArray textArray = new PdfTextArray ( s.substring ( 0, idx ) );
					int lastIdx = idx;
					while ( ( idx = s.indexOf ( ' ', lastIdx + 1 ) ) >= 0 ) {
						textArray.add ( spaceCorrection );
						textArray.add ( s.substring ( lastIdx, idx ) );
						lastIdx = idx;
					}
					textArray.add ( spaceCorrection );
					textArray.add ( s.substring ( lastIdx ) );
					text.showText ( textArray );
				}
			} else {
				if ( isJustified && hScale != lastHScale ) {
					lastHScale = hScale;
					text.setWordSpacing ( baseWordSpacing / hScale );
					text.setCharacterSpacing ( baseCharacterSpacing / hScale + text.getCharacterSpacing () );
				}
				text.showText ( chunk.toString () );
			}

			if ( rise != 0 )
				text.setTextRise ( 0 );
			if ( color != null )
				text.resetRGBColorFill ();
			if ( tr != PdfContentByte.TEXT_RENDER_MODE_FILL )
				text.setTextRenderingMode ( PdfContentByte.TEXT_RENDER_MODE_FILL );
			if ( strokeColor != null )
				text.resetRGBColorStroke ();
			if ( strokeWidth != 1 )
				text.setLineWidth ( 1 );
			if ( chunk.isAttribute ( Chunk.SKEW ) || chunk.isAttribute ( Chunk.HSCALE ) ) {
				adjustMatrix = true;
				text.setTextMatrix ( xMarker, yMarker );
			}
			if ( !isJustified ) {
				if ( chunk.isAttribute ( Chunk.CHAR_SPACING ) ) {
					text.setCharacterSpacing ( baseCharacterSpacing );
				}
				if ( chunk.isAttribute ( Chunk.WORD_SPACING ) ) {
					text.setWordSpacing ( baseWordSpacing );
				}
			}
			if ( isTagged ( writer ) && chunk.accessibleElement != null ) {
				text.closeMCBlock ( chunk.accessibleElement );
			}

		}
		if ( isJustified ) {
			text.setWordSpacing ( 0 );
			text.setCharacterSpacing ( 0 );
			if ( line.isNewlineSplit () )
				lastBaseFactor = 0;
		}
		if ( adjustMatrix )
			text.moveText ( baseXMarker - text.getXTLM (), 0 );
		currentValues[0] = currentFont;
		currentValues[1] = lastBaseFactor;
		return lastX;
	}

	protected float indentLeft () {
		return left ( indentation.indentLeft + indentation.listIndentLeft + indentation.imageIndentLeft + indentation.sectionIndentLeft );
	}

	protected float indentRight () {
		return right ( indentation.indentRight + indentation.sectionIndentRight + indentation.imageIndentRight );
	}

	protected float indentTop () {
		return top ( indentation.indentTop );
	}

	float indentBottom () {
		return bottom ( indentation.indentBottom );
	}

	protected void addSpacing ( final float extraspace, final float oldleading, Font f ) {
		if ( extraspace == 0 )
			return;
		if ( pageEmpty )
			return;
		if ( currentHeight + line.height () + leading > indentTop () - indentBottom () )
			return;
		leading = extraspace;
		carriageReturn ();
		if ( f.isUnderlined () || f.isStrikethru () ) {
			f = new Font ( f );
			int style = f.getStyle ();
			style &= ~Font.UNDERLINE;
			style &= ~Font.STRIKETHRU;
			f.setStyle ( style );
		}
		Chunk space = new Chunk ( " ", f );
		space.process ( this );
		carriageReturn ();
		leading = oldleading;
	}

	PdfInfo getInfo () {
		return info;
	}

	PdfCatalog getCatalog ( final PdfIndirectReference pages ) {
		PdfCatalog catalog = new PdfCatalog ( pages, writer );

		if ( !rootOutline.getKids ().isEmpty () ) {
			catalog.put ( PdfName.PAGEMODE, PdfName.USEOUTLINES );
			catalog.put ( PdfName.OUTLINES, rootOutline.indirectReference () );
		}

		// [C2] version
		writer.getPdfVersion ().addToCatalog ( catalog );

		// [C3] preferences
		viewerPreferences.addToCatalog ( catalog );

		// [C4] pagelabels
		if ( pageLabels != null ) {
			catalog.put ( PdfName.PAGELABELS, pageLabels.getDictionary ( writer ) );
		}

		// [C5] named objects
		catalog.addNames ( localDestinations, getDocumentLevelJS (), documentFileAttachment, writer );

		// [C6] actions
		if ( openActionName != null ) {
			PdfAction action = getLocalGotoAction ( openActionName );
			catalog.setOpenAction ( action );
		} else if ( openActionAction != null )
			catalog.setOpenAction ( openActionAction );
		if ( additionalActions != null ) {
			catalog.setAdditionalActions ( additionalActions );
		}

		// [C7] portable collections
		if ( collection != null ) {
			catalog.put ( PdfName.COLLECTION, collection );
		}

		// [C8] AcroForm
		if ( annotationsImp.hasValidAcroForm () ) {
			try {
				catalog.put ( PdfName.ACROFORM, writer.addToBody ( annotationsImp.getAcroForm () ).getIndirectReference () );
			} catch ( IOException e ) {
				throw new ExceptionConverter ( e );
			}
		}

		if ( language != null ) {
			catalog.put ( PdfName.LANG, language );
		}

		return catalog;
	}

	void calculateOutlineCount () {
		if ( rootOutline.getKids ().isEmpty () )
			return;
		traverseOutlineCount ( rootOutline );
	}

	void traverseOutlineCount ( final PdfOutline outline ) {
		ArrayList<PdfOutline> kids = outline.getKids ();
		PdfOutline parent = outline.parent ();
		if ( kids.isEmpty () ) {
			if ( parent != null ) {
				parent.setCount ( parent.getCount () + 1 );
			}
		} else {
			for ( PdfOutline kid : kids ) {
				traverseOutlineCount ( kid );
			}
			if ( parent != null ) {
				if ( outline.isOpen () ) {
					parent.setCount ( outline.getCount () + parent.getCount () + 1 );
				} else {
					parent.setCount ( parent.getCount () + 1 );
					outline.setCount ( -outline.getCount () );
				}
			}
		}
	}

	void writeOutlines () throws IOException {
		if ( rootOutline.getKids ().isEmpty () )
			return;
		outlineTree ( rootOutline );
		writer.addToBody ( rootOutline, rootOutline.indirectReference () );
	}

	void outlineTree ( final PdfOutline outline ) throws IOException {
		outline.setIndirectReference ( writer.getPdfIndirectReference () );
		if ( outline.parent () != null )
			outline.put ( PdfName.PARENT, outline.parent ().indirectReference () );
		ArrayList<PdfOutline> kids = outline.getKids ();
		int size = kids.size ();
		for ( PdfOutline pdfOutline : kids )
			outlineTree ( pdfOutline );
		for ( int k = 0; k < size; ++k ) {
			if ( k > 0 )
				kids.get ( k ).put ( PdfName.PREV, kids.get ( k - 1 ).indirectReference () );
			if ( k < size - 1 )
				kids.get ( k ).put ( PdfName.NEXT, kids.get ( k + 1 ).indirectReference () );
		}
		if ( size > 0 ) {
			outline.put ( PdfName.FIRST, kids.get ( 0 ).indirectReference () );
			outline.put ( PdfName.LAST, kids.get ( size - 1 ).indirectReference () );
		}
		for ( int k = 0; k < size; ++k ) {
			PdfOutline kid = kids.get ( k );
			writer.addToBody ( kid, kid.indirectReference () );
		}
	}

	void setViewerPreferences ( final int preferences ) {
		this.viewerPreferences.setViewerPreferences ( preferences );
	}

	void addViewerPreference ( final PdfName key, final PdfObject value ) {
		this.viewerPreferences.addViewerPreference ( key, value );
	}

	void localGoto ( final String name, final float llx, final float lly, final float urx, final float ury ) {
		PdfAction action = getLocalGotoAction ( name );
		annotationsImp.addPlainAnnotation ( new PdfAnnotation ( writer, llx, lly, urx, ury, action ) );
	}

	void remoteGoto ( final String filename, final String name, final float llx, final float lly, final float urx, final float ury ) {
		annotationsImp.addPlainAnnotation ( new PdfAnnotation ( writer, llx, lly, urx, ury, new PdfAction ( filename, name ) ) );
	}

	void remoteGoto ( final String filename, final int page, final float llx, final float lly, final float urx, final float ury ) {
		addAnnotation ( new PdfAnnotation ( writer, llx, lly, urx, ury, new PdfAction ( filename, page ) ) );
	}

	void setAction ( final PdfAction action, final float llx, final float lly, final float urx, final float ury ) {
		addAnnotation ( new PdfAnnotation ( writer, llx, lly, urx, ury, action ) );
	}

	PdfAction getLocalGotoAction ( final String name ) {
		PdfAction action;
		Destination dest = localDestinations.get ( name );
		if ( dest == null )
			dest = new Destination ();
		if ( dest.action == null ) {
			if ( dest.reference == null ) {
				dest.reference = writer.getPdfIndirectReference ();
			}
			action = new PdfAction ( dest.reference );
			dest.action = action;
			localDestinations.put ( name, dest );
		} else {
			action = dest.action;
		}
		return action;
	}

	void localDestination ( final String name, final PdfDestination destination ) {
		Destination dest = localDestinations.get ( name );
		if ( dest == null )
			dest = new Destination ();
		if ( dest.destination != null )
			return;
		dest.destination = destination;
		localDestinations.put ( name, dest );
		if ( !destination.hasPage () )
			destination.addPage ( writer.getCurrentPage () );
	}

	HashMap<String, PdfObject> getDocumentLevelJS () {
		return documentLevelJS;
	}

	HashMap<String, PdfObject> getDocumentFileAttachment () {
		return documentFileAttachment;
	}

	public void setCollection ( final PdfCollection collection ) {
		this.collection = collection;
	}

	void addAnnotation ( final PdfAnnotation annot ) {
		pageEmpty = false;
		annotationsImp.addAnnotation ( annot );
	}

	void setLanguage ( final String language ) {
		this.language = new PdfString ( language );
	}

	protected void setNewPageSizeAndMargins () {
		pageSize = nextPageSize;
		if ( marginMirroring && ( getPageNumber () & 1 ) == 0 ) {
			marginRight = nextMarginLeft;
			marginLeft = nextMarginRight;
		} else {
			marginLeft = nextMarginLeft;
			marginRight = nextMarginRight;
		}
		if ( marginMirroringTopBottom && ( getPageNumber () & 1 ) == 0 ) {
			marginTop = nextMarginBottom;
			marginBottom = nextMarginTop;
		} else {
			marginTop = nextMarginTop;
			marginBottom = nextMarginBottom;
		}
		if ( !isTagged ( writer ) ) {
			text = new PdfContentByte ( writer );
			text.reset ();
		} else {
			text = graphics;
		}
		text.beginText ();
		text.moveText ( left (), top () );
		if ( isTagged ( writer ) )
			textEmptySize = text.size ();
	}

	boolean isPageEmpty () {
		if ( isTagged ( writer ) ) {
			return writer == null || writer.getDirectContent ().size ( false ) == 0 && writer.getDirectContentUnder ().size ( false ) == 0 && text.size (
							false ) - textEmptySize == 0 && ( pageEmpty || writer.isPaused () );
		} else
			return writer == null || writer.getDirectContent ().size () == 0 && writer.getDirectContentUnder ()
							.size () == 0 && ( pageEmpty || writer.isPaused () );
	}

	void setDuration ( final int seconds ) {
		if ( seconds > 0 )
			writer.addPageDictEntry ( PdfName.DUR, new PdfNumber ( seconds ) );
	}

	void setTransition ( final PdfTransition transition ) {
		writer.addPageDictEntry ( PdfName.TRANS, transition.getTransitionDictionary () );
	}

	void setThumbnail ( final Image image ) throws DocumentException {
		writer.addPageDictEntry ( PdfName.THUMB, writer.getImageReference ( writer.addDirectImageSimple ( image ) ) );
	}

	PageResources getPageResources () {
		return pageResources;
	}

	public int getStructParentIndex ( Object obj ) {
		int[] i = structParentIndices.computeIfAbsent ( obj, k -> new int[] { structParentIndices.size (), 0 } );
		return i[0];
	}

	public int[] getStructParentIndexAndNextMarkPoint ( Object obj ) {
		int[] i = structParentIndices.computeIfAbsent ( obj, k -> new int[] { structParentIndices.size (), 0 } );
		int markPoint = i[1];
		i[1]++;
		return new int[] { i[0], markPoint };
	}

	protected void add ( final Image image ) throws DocumentException {

		if ( image.hasAbsoluteY () ) {
			graphics.addImage ( image );
			pageEmpty = false;
			return;
		}

		if ( currentHeight != 0 && indentTop () - currentHeight - image.getScaledHeight () < indentBottom () ) {
			if ( !strictImageSequence && imageWait == null ) {
				imageWait = image;
				return;
			}
			newPage ();
			if ( currentHeight != 0 && indentTop () - currentHeight - image.getScaledHeight () < indentBottom () ) {
				imageWait = image;
				return;
			}
		}
		pageEmpty = false;
		if ( image == imageWait )
			imageWait = null;
		boolean textwrap = ( image.getAlignment () & Image.TEXTWRAP ) == Image.TEXTWRAP
						&& !( ( image.getAlignment () & Image.MIDDLE ) == Image.MIDDLE );
		boolean underlying = ( image.getAlignment () & Image.UNDERLYING ) == Image.UNDERLYING;
		float diff = leading / 2;
		if ( textwrap ) {
			diff += leading;
		}
		float lowerleft = indentTop () - currentHeight - image.getScaledHeight () - diff;
		float[] mt = image.matrix ();
		float startPosition = indentLeft () - mt[4];
		if ( ( image.getAlignment () & Image.RIGHT ) == Image.RIGHT )
			startPosition = indentRight () - image.getScaledWidth () - mt[4];
		if ( ( image.getAlignment () & Image.MIDDLE ) == Image.MIDDLE )
			startPosition = indentLeft () + ( indentRight () - indentLeft () - image.getScaledWidth () ) / 2 - mt[4];
		if ( image.hasAbsoluteX () )
			startPosition = image.getAbsoluteX ();
		if ( textwrap ) {
			if ( imageEnd < 0 || imageEnd < currentHeight + image.getScaledHeight () + diff ) {
				imageEnd = currentHeight + image.getScaledHeight () + diff;
			}
			if ( ( image.getAlignment () & Image.RIGHT ) == Image.RIGHT ) {
				indentation.imageIndentRight += image.getScaledWidth () + image.getIndentationLeft ();
			} else {
				indentation.imageIndentLeft += image.getScaledWidth () + image.getIndentationRight ();
			}
		} else {
			if ( ( image.getAlignment () & Image.RIGHT ) == Image.RIGHT )
				startPosition -= image.getIndentationRight ();
			else if ( ( image.getAlignment () & Image.MIDDLE ) == Image.MIDDLE )
				startPosition += image.getIndentationLeft () - image.getIndentationRight ();
			else
				startPosition += image.getIndentationLeft ();
		}
		graphics.addImage ( image, mt[0], mt[1], mt[2], mt[3], startPosition, lowerleft - mt[5] );
		if ( !( textwrap || underlying ) ) {
			currentHeight += image.getScaledHeight () + diff;
			flushLines ();
			text.moveText ( 0, -( image.getScaledHeight () + diff ) );
			newLine ();
		}
	}

	void addPTable ( final PdfPTable ptable ) throws DocumentException {
		ColumnText ct = new ColumnText ( isTagged ( writer ) ? text : writer.getDirectContent () );
		if ( ptable.getKeepTogether () && !fitsPage ( ptable ) && currentHeight > 0 ) {
			newPage ();
		}
		if ( currentHeight == 0 ) {
			ct.setAdjustFirstLine ( false );
		}
		ct.addElement ( ptable );
		boolean he = ptable.isHeadersInEvent ();
		ptable.setHeadersInEvent ( true );
		int loop = 0;
		while ( true ) {
			ct.setSimpleColumn ( indentLeft (), indentBottom (), indentRight (), indentTop () - currentHeight );
			int status = ct.go ();
			if ( ( status & ColumnText.NO_MORE_TEXT ) != 0 ) {
				if ( isTagged ( writer ) ) {
					text.setTextMatrix ( indentLeft (), ct.getYLine () );
				} else {
					text.moveText ( 0, ct.getYLine () - indentTop () + currentHeight );
				}
				currentHeight = indentTop () - ct.getYLine ();
				break;
			}
			if ( indentTop () - currentHeight == ct.getYLine () )
				++loop;
			else
				loop = 0;
			if ( loop == 3 ) {
				throw new DocumentException ( MessageLocalization.getComposedMessage ( "infinite.table.loop" ) );
			}
			newPage ();
			if ( isTagged ( writer ) ) {
				ct.setCanvas ( text );
			}
		}
		ptable.setHeadersInEvent ( he );
	}

	private void addDiv ( final PdfDiv div ) {
		if ( floatingElements == null ) {
			floatingElements = new ArrayList<> ();
		}
		floatingElements.add ( div );
	}

	private void flushFloatingElements () throws DocumentException {
		if ( floatingElements != null && !floatingElements.isEmpty () ) {
			ArrayList<Element> cachedFloatingElements = floatingElements;
			floatingElements = null;
			FloatLayout fl = new FloatLayout ( cachedFloatingElements, false );
			int loop = 0;
			while ( true ) {
				fl.setSimpleColumn ( indentLeft (), indentBottom (), indentRight (), indentTop () - currentHeight );
				try {
					int status = fl.layout ( isTagged ( writer ) ? text : writer.getDirectContent (), false );
					if ( ( status & ColumnText.NO_MORE_TEXT ) != 0 ) {
						if ( isTagged ( writer ) ) {
							text.setTextMatrix ( indentLeft (), fl.getYLine () );
						} else {
							text.moveText ( 0, fl.getYLine () - indentTop () + currentHeight );
						}
						currentHeight = indentTop () - fl.getYLine ();
						break;
					}
				} catch ( Exception exc ) {
					return;
				}
				if ( indentTop () - currentHeight == fl.getYLine () || isPageEmpty () )
					++loop;
				else {
					loop = 0;
				}
				if ( loop == 2 ) {
					return;
				}
				newPage ();
			}
		}
	}

	boolean fitsPage ( final PdfPTable table ) {
		if ( !table.isLockedWidth () ) {
			float totalWidth = ( indentRight () - indentLeft () ) * table.getWidthPercentage () / 100;
			table.setTotalWidth ( totalWidth );
		}
		ensureNewLine ();
		float spaceNeeded = table.isSkipFirstHeader () ? table.getTotalHeight () - table.getHeaderHeight () : table.getTotalHeight ();
		return spaceNeeded + ( currentHeight > 0 ? table.spacingBefore () : 0f ) <= indentTop () - currentHeight - indentBottom () - (float) 0.0;
	}

	public static class PdfInfo extends PdfDictionary {

		PdfInfo () {
			super ();
			addProducer ();
			addCreationDate ();
		}

		void addTitle ( final String title ) {
			put ( PdfName.TITLE, new PdfString ( title, PdfObject.TEXT_UNICODE ) );
		}

		void addSubject ( final String subject ) {
			put ( PdfName.SUBJECT, new PdfString ( subject, PdfObject.TEXT_UNICODE ) );
		}

		void addKeywords ( final String keywords ) {
			put ( PdfName.KEYWORDS, new PdfString ( keywords, PdfObject.TEXT_UNICODE ) );
		}

		void addAuthor ( final String author ) {
			put ( PdfName.AUTHOR, new PdfString ( author, PdfObject.TEXT_UNICODE ) );
		}

		void addCreator ( final String creator ) {
			put ( PdfName.CREATOR, new PdfString ( creator, PdfObject.TEXT_UNICODE ) );
		}

		void addProducer () {
			put ( PdfName.PRODUCER, new PdfString ( Version.getInstance ().getVersion () ) );
		}

		void addCreationDate () {
			PdfString date = new PdfDate ();
			put ( PdfName.CREATIONDATE, date );
			put ( PdfName.MODDATE, date );
		}

		void addkey ( final String key, final String value ) {
			if ( key.equals ( "Producer" ) || key.equals ( "CreationDate" ) )
				return;
			put ( new PdfName ( key ), new PdfString ( value, PdfObject.TEXT_UNICODE ) );
		}
	}


	static class PdfCatalog extends PdfDictionary {

		PdfWriter writer;

		PdfCatalog ( final PdfIndirectReference pages, final PdfWriter writer ) {
			super ( CATALOG );
			this.writer = writer;
			put ( PdfName.PAGES, pages );
		}

		void addNames ( final TreeMap<String, Destination> localDestinations, final HashMap<String, PdfObject> documentLevelJS,
						final HashMap<String, PdfObject> documentFileAttachment, final PdfWriter writer ) {
			if ( localDestinations.isEmpty () && documentLevelJS.isEmpty () && documentFileAttachment.isEmpty () )
				return;
			try {
				PdfDictionary names = new PdfDictionary ();
				if ( !localDestinations.isEmpty () ) {
					PdfArray ar = new PdfArray ();
					for ( Map.Entry<String, Destination> entry : localDestinations.entrySet () ) {
						String name = entry.getKey ();
						Destination dest = entry.getValue ();
						if ( dest.destination == null ) //no destination
							continue;
						PdfIndirectReference ref = dest.reference;
						ar.add ( new PdfString ( name, null ) );
						ar.add ( ref );
					}
					if ( !ar.isEmpty () ) {
						PdfDictionary dests = new PdfDictionary ();
						dests.put ( PdfName.NAMES, ar );
						names.put ( PdfName.DESTS, writer.addToBody ( dests ).getIndirectReference () );
					}
				}
				if ( !documentLevelJS.isEmpty () ) {
					PdfDictionary tree = PdfNameTree.writeTree ( documentLevelJS, writer );
					names.put ( PdfName.JAVASCRIPT, writer.addToBody ( tree ).getIndirectReference () );
				}
				if ( !documentFileAttachment.isEmpty () ) {
					names.put ( PdfName.EMBEDDEDFILES, writer.addToBody ( PdfNameTree.writeTree ( documentFileAttachment, writer ) ).getIndirectReference () );
				}
				if ( names.size () > 0 )
					put ( PdfName.NAMES, writer.addToBody ( names ).getIndirectReference () );
			} catch ( IOException e ) {
				throw new ExceptionConverter ( e );
			}
		}

		void setOpenAction ( final PdfAction action ) {
			put ( PdfName.OPENACTION, action );
		}

		void setAdditionalActions ( final PdfDictionary actions ) {
			try {
				put ( PdfName.AA, writer.addToBody ( actions ).getIndirectReference () );
			} catch ( Exception e ) {
				throw new ExceptionConverter ( e );
			}
		}
	}


	public static class Indentation {

		float indentLeft = 0;

		float sectionIndentLeft = 0;

		float listIndentLeft = 0;

		float imageIndentLeft = 0;

		float indentRight = 0;

		float sectionIndentRight = 0;

		float imageIndentRight = 0;

		float indentTop = 0;

		float indentBottom = 0;
	}


	public static class Destination {

		public PdfAction action;

		public PdfIndirectReference reference;

		public PdfDestination destination;
	}
}
