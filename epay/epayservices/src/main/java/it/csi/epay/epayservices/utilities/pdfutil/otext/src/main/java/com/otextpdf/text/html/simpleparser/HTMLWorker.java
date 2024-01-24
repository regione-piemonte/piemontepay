/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.simpleparser;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.FontProvider;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ListItem;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.TextElementArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlTags;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.html.HtmlUtilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPCell;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPTable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw.LineSeparator;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLDocHandler;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLParser;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class HTMLWorker implements SimpleXMLDocHandler, DocListener {

	public static final String IMG_PROVIDER = "img_provider";

	public static final String IMG_PROCESSOR = "img_interface";

	public static final String IMG_STORE = "img_static";

	public static final String IMG_BASEURL = "img_baseurl";

	public static final String FONT_PROVIDER = "font_factory";

	public static final String LINK_PROVIDER = "alink_interface";

	private static final Logger LOGGER = LoggerFactory.getLogger ( HTMLWorker.class );

	private final ChainedProperties chain = new ChainedProperties ();

	private final ElementFactory factory = new ElementFactory ();

	private final Stack<boolean[]> tableState = new Stack<> ();

	protected DocListener document;

	protected Map<String, HTMLTagProcessor> tags;

	protected Stack<Element> stack = new Stack<> ();

	protected Paragraph currentParagraph;

	protected boolean skipText = false;

	protected List<Element> objectList;

	private StyleSheet style = new StyleSheet ();

	private Map<String, Object> providers = new HashMap<> ();

	private boolean pendingTR = false;

	private boolean pendingTD = false;

	private boolean pendingLI = false;

	private boolean insidePRE = false;

	public HTMLWorker ( final DocListener document, final Map<String, HTMLTagProcessor> tags, final StyleSheet style ) {
		this.document = document;
		setSupportedTags ( tags );
		setStyleSheet ( style );
	}

	public void setSupportedTags ( Map<String, HTMLTagProcessor> tags ) {
		if ( tags == null )
			tags = new HTMLTagProcessors ();
		this.tags = tags;
	}

	public void setStyleSheet ( StyleSheet style ) {
		if ( style == null )
			style = new StyleSheet ();
		this.style = style;
	}

	public void parse ( final Reader reader ) throws IOException {
		LOGGER.info ( "Please note, there is a more extended version of the HTMLWorker available in the oText XMLWorker" );
		SimpleXMLParser.parse ( this, null, reader, true );
	}

	public void startDocument () {
		HashMap<String, String> attrs = new HashMap<> ();
		style.applyStyle ( HtmlTags.BODY, attrs );
		chain.addToChain ( HtmlTags.BODY, attrs );
	}

	public void startElement ( final String tag, final Map<String, String> attrs ) {
		HTMLTagProcessor htmlTag = tags.get ( tag );
		if ( htmlTag == null ) {
			return;
		}
		style.applyStyle ( tag, attrs );
		StyleSheet.resolveStyleAttribute ( attrs, chain );
		try {
			htmlTag.startElement ( this, tag, attrs );
		} catch ( DocumentException | IOException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public void text ( String content ) {
		if ( skipText )
			return;
		if ( currentParagraph == null ) {
			currentParagraph = createParagraph ();
		}
		if ( !insidePRE ) {
			if ( content.trim ().isEmpty () && content.indexOf ( ' ' ) < 0 ) {
				return;
			}
			content = HtmlUtilities.eliminateWhiteSpace ( content );
		}
		Chunk chunk = createChunk ( content );
		currentParagraph.add ( chunk );
	}

	public void endElement ( final String tag ) {
		HTMLTagProcessor htmlTag = tags.get ( tag );
		if ( htmlTag == null ) {
			return;
		}
		try {
			htmlTag.endElement ( this, tag );
		} catch ( DocumentException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public void endDocument () {
		try {
			for ( int k = 0; k < stack.size (); ++k )
				document.add ( stack.elementAt ( k ) );
			if ( currentParagraph != null )
				document.add ( currentParagraph );
			currentParagraph = null;
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public void newLine () {
		if ( currentParagraph == null ) {
			currentParagraph = new Paragraph ();
		}
		currentParagraph.add ( createChunk ( "\n" ) );
	}

	public void carriageReturn () throws DocumentException {
		if ( currentParagraph == null )
			return;
		if ( stack.empty () )
			document.add ( currentParagraph );
		else {
			Element obj = stack.pop ();
			if ( obj instanceof TextElementArray ) {
				TextElementArray current = (TextElementArray) obj;
				current.add ( currentParagraph );
			}
			stack.push ( obj );
		}
		currentParagraph = null;
	}

	public void flushContent () {
		pushToStack ( currentParagraph );
		currentParagraph = new Paragraph ();
	}

	public void pushToStack ( final Element element ) {
		if ( element != null )
			stack.push ( element );
	}

	public void updateChain ( final String tag, final Map<String, String> attrs ) {
		chain.addToChain ( tag, attrs );
	}

	public void updateChain ( final String tag ) {
		chain.removeChain ( tag );
	}

	public void setProviders ( final Map<String, Object> providers ) {
		if ( providers == null )
			return;
		this.providers = providers;
		FontProvider ff;
		ff = (FontProvider) providers.get ( FONT_PROVIDER );
		if ( ff != null )
			factory.setFontProvider ( ff );
	}

	public Chunk createChunk ( final String content ) {
		return factory.createChunk ( content, chain );
	}

	public Paragraph createParagraph () {
		return factory.createParagraph ( chain );
	}

	public it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List createList ( final String tag ) {
		return factory.createList ( tag, chain );
	}

	public ListItem createListItem () {
		return factory.createListItem ( chain );
	}

	public LineSeparator createLineSeparator ( final Map<String, String> attrs ) {
		return factory.createLineSeparator ( attrs, currentParagraph.getLeading () / 2 );
	}

	public Image createImage ( final Map<String, String> attrs ) throws DocumentException, IOException {
		String src = attrs.get ( HtmlTags.SRC );
		if ( src == null )
			return null;
		return factory.createImage (
						src, attrs, chain, document,
						(ImageProvider) providers.get ( IMG_PROVIDER ),
						(ImageStore) providers.get ( IMG_STORE ),
						(String) providers.get ( IMG_BASEURL ) );
	}

	public CellWrapper createCell ( final String tag ) {
		return new CellWrapper ( tag, chain );
	}

	public void processLink () {
		if ( currentParagraph == null ) {
			currentParagraph = new Paragraph ();
		}
		LinkProcessor i = (LinkProcessor) providers.get ( HTMLWorker.LINK_PROVIDER );
		if ( i == null || !i.process ( currentParagraph, chain ) ) {
			String href = chain.getProperty ( HtmlTags.HREF );
			if ( href != null ) {
				for ( Chunk ck : currentParagraph.getChunks () ) {
					ck.setAnchor ( href );
				}
			}
		}
		Paragraph tmp;
		if ( stack.isEmpty () ) {
			tmp = new Paragraph ( new Phrase ( currentParagraph ) );
		} else {
			tmp = (Paragraph) stack.pop ();
			tmp.add ( new Phrase ( currentParagraph ) );
		}
		currentParagraph = tmp;
	}

	public void processList () throws DocumentException {
		if ( stack.empty () )
			return;
		Element obj = stack.pop ();
		if ( !( obj instanceof it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List ) ) {
			stack.push ( obj );
			return;
		}
		if ( stack.empty () )
			document.add ( obj );
		else
			( (TextElementArray) stack.peek () ).add ( obj );
	}

	public void processListItem () throws DocumentException {
		if ( stack.empty () )
			return;
		Element obj = stack.pop ();
		if ( !( obj instanceof ListItem ) ) {
			stack.push ( obj );
			return;
		}
		if ( stack.empty () ) {
			document.add ( obj );
			return;
		}
		ListItem item = (ListItem) obj;
		Element list = stack.pop ();
		if ( !( list instanceof it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List ) ) {
			stack.push ( list );
			return;
		}
		( (it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.List) list ).add ( item );
		item.adjustListSymbolFont ();
		stack.push ( list );
	}

	public void processImage ( final Image img, final Map<String, String> attrs ) throws DocumentException {
		ImageProcessor processor = (ImageProcessor) providers.get ( HTMLWorker.IMG_PROCESSOR );
		if ( processor == null || !processor.process ( img, attrs, chain, document ) ) {
			String align = attrs.get ( HtmlTags.ALIGN );
			if ( align != null ) {
				carriageReturn ();
			}
			if ( currentParagraph == null ) {
				currentParagraph = createParagraph ();
			}
			currentParagraph.add ( new Chunk ( img, 0, 0, true ) );
			currentParagraph.setAlignment ( HtmlUtilities.alignmentValue ( align ) );
			if ( align != null ) {
				carriageReturn ();
			}
		}
	}

	public void processTable () throws DocumentException {
		TableWrapper table = (TableWrapper) stack.pop ();
		PdfPTable tb = table.createTable ();
		tb.setSplitRows ( true );
		if ( stack.empty () )
			document.add ( tb );
		else
			( (TextElementArray) stack.peek () ).add ( tb );
	}

	public void processRow () {
		ArrayList<PdfPCell> row = new ArrayList<> ();
		ArrayList<Float> cellWidths = new ArrayList<> ();
		boolean percentage = false;
		float width;
		float totalWidth = 0;
		int zeroWidth = 0;
		TableWrapper table;
		while ( true ) {
			Element obj = stack.pop ();
			if ( obj instanceof CellWrapper ) {
				CellWrapper cell = (CellWrapper) obj;
				width = cell.getWidth ();
				cellWidths.add ( width );
				percentage |= cell.isPercentage ();
				if ( width == 0 ) {
					zeroWidth++;
				} else {
					totalWidth += width;
				}
				row.add ( cell.getCell () );
			}
			if ( obj instanceof TableWrapper ) {
				table = (TableWrapper) obj;
				break;
			}
		}
		table.addRow ( row );
		if ( !cellWidths.isEmpty () ) {
			totalWidth = 100 - totalWidth;
			Collections.reverse ( cellWidths );
			float[] widths = new float[cellWidths.size ()];
			boolean hasZero = false;
			for ( int i = 0; i < widths.length; i++ ) {
				widths[i] = cellWidths.get ( i );
				if ( widths[i] == 0 && percentage && zeroWidth > 0 ) {
					widths[i] = totalWidth / zeroWidth;
				}
				if ( widths[i] == 0 ) {
					hasZero = true;
					break;
				}
			}
			if ( !hasZero )
				table.setColWidths ( widths );
		}
		stack.push ( table );
	}

	public void pushTableState () {
		tableState.push ( new boolean[] { pendingTR, pendingTD } );
	}

	public void popTableState () {
		boolean[] state = tableState.pop ();
		pendingTR = state[0];
		pendingTD = state[1];
	}

	public boolean isPendingTR () {
		return pendingTR;
	}

	public void setPendingTR ( final boolean pendingTR ) {
		this.pendingTR = pendingTR;
	}

	public boolean isPendingTD () {
		return pendingTD;
	}

	public void setPendingTD ( final boolean pendingTD ) {
		this.pendingTD = pendingTD;
	}

	public boolean isPendingLI () {
		return pendingLI;
	}

	public void setPendingLI ( final boolean pendingLI ) {
		this.pendingLI = pendingLI;
	}

	public void setInsidePRE ( final boolean insidePRE ) {
		this.insidePRE = insidePRE;
	}

	public void setSkipText ( final boolean skipText ) {
		this.skipText = skipText;
	}

	public boolean add ( final Element element ) throws DocumentException {
		objectList.add ( element );
		return true;
	}

	public void close () {
	}

	public void newPage () {
	}

	public void open () {
	}

	public void resetPageCount () {
	}

	public boolean setMarginMirroring ( final boolean marginMirroring ) {
		return false;
	}

	public boolean setMarginMirroringTopBottom ( final boolean marginMirroring ) {
		return false;
	}

	public void setMargins ( final float marginLeft, final float marginRight,
					final float marginTop, final float marginBottom ) {
	}

	public void setPageCount ( final int pageN ) {
	}

	public void setPageSize ( final Rectangle pageSize ) {
	}

	@Deprecated
	public Map<String, Object> getInterfaceProps () {
		return providers;
	}

	@Deprecated
	public void setInterfaceProps ( final HashMap<String, Object> providers ) {
		setProviders ( providers );
	}

}
