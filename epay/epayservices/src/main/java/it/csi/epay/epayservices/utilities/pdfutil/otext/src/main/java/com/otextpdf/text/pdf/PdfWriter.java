/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocListener;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ImgJBIG2;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ImgWMF;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Version;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Counter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.CounterFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.collection.PdfCollection;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.IAccessibleElement;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfAnnotations;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfEncryptionSettings;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfIsoConformance;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfPageActions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfVersion;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfViewerPreferences;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfXConformance;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfVersionImp;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfXConformanceImp;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp.XmpWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


public class PdfWriter extends DocWriter implements
										 PdfViewerPreferences,
										 PdfEncryptionSettings,
										 PdfVersion,
										 PdfPageActions,
										 PdfAnnotations {

	public static final int GENERATION_MAX = 65535;

	public static final char VERSION_1_2 = '2';

	public static final char VERSION_1_3 = '3';

	public static final char VERSION_1_4 = '4';

	public static final char VERSION_1_5 = '5';

	public static final char VERSION_1_6 = '6';

	public static final char VERSION_1_7 = '7';

	public static final PdfName PDF_VERSION_1_2 = new PdfName ( "1.2" );

	public static final PdfName PDF_VERSION_1_3 = new PdfName ( "1.3" );

	public static final PdfName PDF_VERSION_1_4 = new PdfName ( "1.4" );

	public static final PdfName PDF_VERSION_1_5 = new PdfName ( "1.5" );

	public static final PdfName PDF_VERSION_1_6 = new PdfName ( "1.6" );

	public static final PdfName PDF_VERSION_1_7 = new PdfName ( "1.7" );

	public static final int PageLayoutSinglePage = 1;

	public static final int PageLayoutOneColumn = 2;

	public static final int PageLayoutTwoColumnLeft = 4;

	public static final int PageLayoutTwoColumnRight = 8;

	public static final int PageLayoutTwoPageLeft = 16;

	public static final int PageLayoutTwoPageRight = 32;

	public static final int PageModeUseNone = 64;

	public static final int PageModeUseOutlines = 128;

	public static final int PageModeUseThumbs = 256;

	public static final int PageModeFullScreen = 512;

	public static final int PageModeUseOC = 1024;

	public static final int PageModeUseAttachments = 2048;

	public static final int HideToolbar = 1 << 12;

	public static final int HideMenubar = 1 << 13;

	public static final int HideWindowUI = 1 << 14;

	public static final int FitWindow = 1 << 15;

	public static final int CenterWindow = 1 << 16;

	public static final int DisplayDocTitle = 1 << 17;

	public static final int NonFullScreenPageModeUseNone = 1 << 18;

	public static final int NonFullScreenPageModeUseOutlines = 1 << 19;

	public static final int NonFullScreenPageModeUseThumbs = 1 << 20;

	public static final int NonFullScreenPageModeUseOC = 1 << 21;

	public static final int DirectionL2R = 1 << 22;

	public static final int DirectionR2L = 1 << 23;

	public static final int PrintScalingNone = 1 << 24;

	public static final PdfName DOCUMENT_CLOSE = PdfName.WC;

	public static final PdfName WILL_SAVE = PdfName.WS;

	public static final PdfName DID_SAVE = PdfName.DS;

	public static final PdfName WILL_PRINT = PdfName.WP;

	public static final PdfName DID_PRINT = PdfName.DP;

	public static final int SIGNATURE_EXISTS = 1;

	public static final int SIGNATURE_APPEND_ONLY = 2;

	public static final int PDFXNONE = 0;

	public static final int PDFX1A2001 = 1;

	public static final int PDFX32002 = 2;

	public static final int STANDARD_ENCRYPTION_40 = 0;

	public static final int STANDARD_ENCRYPTION_128 = 1;

	public static final int ENCRYPTION_AES_128 = 2;

	public static final int ENCRYPTION_AES_256 = 3;

	public static final int DO_NOT_ENCRYPT_METADATA = 8;

	public static final int EMBEDDED_FILES_ONLY = 24;

	public static final int ALLOW_PRINTING = 4 + 2048;

	public static final int ALLOW_MODIFY_CONTENTS = 8;

	public static final int ALLOW_COPY = 16;

	public static final int ALLOW_MODIFY_ANNOTATIONS = 32;

	public static final int ALLOW_FILL_IN = 256;

	public static final int ALLOW_SCREENREADERS = 512;

	public static final int ALLOW_ASSEMBLY = 1024;

	public static final int ALLOW_DEGRADED_PRINTING = 4;

	@Deprecated
	public static final int AllowPrinting = ALLOW_PRINTING;

	@Deprecated
	public static final int AllowModifyContents = ALLOW_MODIFY_CONTENTS;

	@Deprecated
	public static final int AllowCopy = ALLOW_COPY;

	@Deprecated
	public static final int AllowModifyAnnotations = ALLOW_MODIFY_ANNOTATIONS;

	@Deprecated
	public static final int AllowFillIn = ALLOW_FILL_IN;

	@Deprecated
	public static final int AllowScreenReaders = ALLOW_SCREENREADERS;

	@Deprecated
	public static final int AllowAssembly = ALLOW_ASSEMBLY;

	@Deprecated
	public static final int AllowDegradedPrinting = ALLOW_DEGRADED_PRINTING;

	@Deprecated
	public static final boolean STRENGTH40BITS = false;

	@Deprecated
	public static final boolean STRENGTH128BITS = true;

	static public final int markAll = 0x00;

	static public final int markInlineElementsOnly = 0x01;

	public static final PdfName PAGE_OPEN = PdfName.O;

	public static final PdfName PAGE_CLOSE = PdfName.C;

	public static final float SPACE_CHAR_RATIO_DEFAULT = 2.5f;

	public static final float NO_SPACE_CHAR_RATIO = 10000000f;

	public static final int RUN_DIRECTION_DEFAULT = 0;

	public static final int RUN_DIRECTION_NO_BIDI = 1;

	public static final int RUN_DIRECTION_LTR = 2;

	public static final int RUN_DIRECTION_RTL = 3;

	static final int ENCRYPTION_MASK = 7;

	static private final List<PdfName> standardStructElems_1_4 = Arrays.asList ( PdfName.DOCUMENT, PdfName.PART, PdfName.ART,
					PdfName.SECT, PdfName.DIV, PdfName.BLOCKQUOTE, PdfName.CAPTION, PdfName.TOC, PdfName.TOCI, PdfName.INDEX,
					PdfName.NONSTRUCT, PdfName.PRIVATE, PdfName.P, PdfName.H, PdfName.H1, PdfName.H2, PdfName.H3, PdfName.H4,
					PdfName.H5, PdfName.H6, PdfName.L, PdfName.LBL, PdfName.LI, PdfName.LBODY, PdfName.TABLE, PdfName.TR,
					PdfName.TH, PdfName.TD, PdfName.SPAN, PdfName.QUOTE, PdfName.NOTE, PdfName.REFERENCE, PdfName.BIBENTRY,
					PdfName.CODE, PdfName.LINK, PdfName.FIGURE, PdfName.FORMULA, PdfName.FORM );

	static private final List<PdfName> standardStructElems_1_7 = Arrays.asList ( PdfName.DOCUMENT, PdfName.PART, PdfName.ART,
					PdfName.SECT, PdfName.DIV, PdfName.BLOCKQUOTE, PdfName.CAPTION, PdfName.TOC, PdfName.TOCI, PdfName.INDEX,
					PdfName.NONSTRUCT, PdfName.PRIVATE, PdfName.P, PdfName.H, PdfName.H1, PdfName.H2, PdfName.H3, PdfName.H4,
					PdfName.H5, PdfName.H6, PdfName.L, PdfName.LBL, PdfName.LI, PdfName.LBODY, PdfName.TABLE, PdfName.TR,
					PdfName.TH, PdfName.TD, PdfName.THEAD, PdfName.TBODY, PdfName.TFOOT, PdfName.SPAN, PdfName.QUOTE, PdfName.NOTE,
					PdfName.REFERENCE, PdfName.BIBENTRY, PdfName.CODE, PdfName.LINK, PdfName.ANNOT, PdfName.RUBY, PdfName.RB, PdfName.RT,
					PdfName.RP, PdfName.WARICHU, PdfName.WT, PdfName.WP, PdfName.FIGURE, PdfName.FORMULA, PdfName.FORM );

	protected static Counter COUNTER = CounterFactory.getCounter ( PdfWriter.class );

	private final HashMap<Long, PdfName> images = new HashMap<> ();

	protected PdfDocument pdf;

	protected PdfContentByte directContent;

	protected PdfContentByte directContentUnder;

	protected PdfBody body;

	protected ICC_Profile colorProfile;

	protected PdfDictionary extraCatalog;

	protected PdfPages root = new PdfPages ( this );

	protected ArrayList<PdfIndirectReference> pageReferences = new ArrayList<> ();

	protected int currentPageNumber = 1;

	protected PdfName tabs = null;

	protected PdfDictionary pageDictEntries = new PdfDictionary ();

	protected long prevxref = 0;

	protected byte[] originalFileID = null;

	protected List<HashMap<String, Object>> newBookmarks;

	protected PdfVersionImp pdf_version = new PdfVersionImp ();

	protected byte[] xmpMetadata = null;

	protected XmpWriter xmpWriter = null;

	protected PdfIsoConformance pdfIsoConformance = initPdfIsoConformance ();

	protected PdfEncryption crypto;

	protected boolean fullCompression = false;

	protected int compressionLevel = PdfStream.DEFAULT_COMPRESSION;

	protected LinkedHashMap<BaseFont, FontDetails> documentFonts = new LinkedHashMap<> ();

	protected int fontNumber = 1;

	protected HashMap<PdfIndirectReference, Object[]> formXObjects = new HashMap<> ();

	protected int formXObjectsCounter = 1;

	protected HashMap<PdfReader, PdfReaderInstance> readerInstances = new HashMap<> ();

	protected PdfReaderInstance currentPdfReaderInstance;

	protected HashMap<PdfSpotColor, ColorDetails> documentColors = new HashMap<> ();

	protected int colorNumber = 1;

	protected HashMap<PdfPatternPainter, PdfName> documentPatterns = new HashMap<> ();

	protected int patternNumber = 1;

	protected HashSet<PdfShadingPattern> documentShadingPatterns = new HashSet<> ();

	protected HashSet<PdfShading> documentShadings = new HashSet<> ();

	protected HashMap<PdfDictionary, PdfObject[]> documentExtGState = new HashMap<> ();

	protected HashMap<Object, PdfObject[]> documentProperties = new HashMap<> ();

	protected boolean tagged = false;

	protected int taggingMode = markInlineElementsOnly;

	protected PdfStructureTreeRoot structureTreeRoot;

	protected HashSet<PdfOCG> documentOCG = new HashSet<> ();

	protected ArrayList<PdfOCG> documentOCGorder = new ArrayList<> ();

	protected PdfOCProperties OCProperties;

	protected PdfArray OCGRadioGroup = new PdfArray ();

	protected PdfArray OCGLocked = new PdfArray ();

	protected PdfDictionary group;

	protected int runDirection = RUN_DIRECTION_NO_BIDI;

	protected PdfDictionary defaultColorspace = new PdfDictionary ();

	protected HashMap<ColorDetails, ColorDetails> documentSpotPatterns = new HashMap<> ();

	protected ColorDetails patternColorspaceRGB;

	protected ColorDetails patternColorspaceGRAY;

	protected ColorDetails patternColorspaceCMYK;

	protected PdfDictionary imageDictionary = new PdfDictionary ();

	protected HashMap<PdfStream, PdfIndirectReference> JBIG2Globals = new HashMap<> ();

	protected TtfUnicodeWriter ttfUnicodeWriter = null;

	private PdfPageEvent pageEvent;

	private boolean userProperties;

	private boolean rgbTransparencyBlending;

	protected PdfWriter () {
	}

	protected PdfWriter ( final PdfDocument document, final OutputStream os ) {
		super ( document, os );
		pdf = document;
		directContentUnder = new PdfContentByte ( this );
		directContent = directContentUnder.getDuplicate ();
	}

	public static PdfWriter getInstance ( final Document document, final OutputStream os )
					throws DocumentException {
		PdfDocument pdf = new PdfDocument ();
		document.addDocListener ( pdf );
		PdfWriter writer = new PdfWriter ( pdf, os );
		pdf.addWriter ( writer );
		return writer;
	}

	public static PdfWriter getInstance ( final Document document, final OutputStream os, final DocListener listener )
					throws DocumentException {
		PdfDocument pdf = new PdfDocument ();
		pdf.addDocListener ( listener );
		document.addDocListener ( pdf );
		PdfWriter writer = new PdfWriter ( pdf, os );
		pdf.addWriter ( writer );
		return writer;
	}

	private static void getOCGOrder ( final PdfArray order, final PdfLayer layer ) {
		if ( !layer.isOnPanel () )
			return;
		if ( layer.getTitle () == null )
			order.add ( layer.getRef () );
		ArrayList<PdfLayer> children = layer.getChildren ();
		if ( children == null )
			return;
		PdfArray kids = new PdfArray ();
		if ( layer.getTitle () != null )
			kids.add ( new PdfString ( layer.getTitle (), PdfObject.TEXT_UNICODE ) );
		for ( PdfLayer child : children ) {
			getOCGOrder ( kids, child );
		}
		if ( !kids.isEmpty () )
			order.add ( kids );
	}

	protected static void writeKeyInfo ( OutputStream os ) throws IOException {
		Version version = Version.getInstance ();
		String k = version.getKey ();
		if ( k == null ) {
			k = "oText";
		}
		os.write ( getISOBytes ( String.format ( "%%%s-%s\n", k, version.getRelease () ) ) );

	}

	public static void checkPdfIsoConformance ( PdfWriter writer, int key, Object obj1 ) {
		if ( writer != null )
			writer.checkPdfIsoConformance ( key, obj1 );
	}

	protected Counter getCounter () {
		return COUNTER;
	}

	PdfDocument getPdfDocument () {
		return pdf;
	}

	public PdfDictionary getInfo () {
		return pdf.getInfo ();
	}

	public PdfContentByte getDirectContent () {
		if ( !open )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "the.document.is.not.open" ) );
		return directContent;
	}

	public PdfContentByte getDirectContentUnder () {
		if ( !open )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "the.document.is.not.open" ) );
		return directContentUnder;
	}

	void resetContent () {
		directContent.reset ();
		directContentUnder.reset ();
	}

	void addLocalDestinations ( final TreeMap<String, PdfDocument.Destination> desto ) throws IOException {
		for ( Map.Entry<String, PdfDocument.Destination> entry : desto.entrySet () ) {
			String name = entry.getKey ();
			PdfDocument.Destination dest = entry.getValue ();
			PdfDestination destination = dest.destination;
			if ( dest.reference == null )
				dest.reference = getPdfIndirectReference ();
			if ( destination == null )
				addToBody ( new PdfString ( "invalid_" + name ), dest.reference );
			else
				addToBody ( destination, dest.reference );
		}
	}

	public PdfIndirectObject addToBody ( final PdfObject object ) throws IOException {
		return body.add ( object );
	}

	public PdfIndirectObject addToBody ( final PdfObject object, final boolean inObjStm ) throws IOException {
		return body.add ( object, inObjStm );
	}

	public PdfIndirectObject addToBody ( final PdfObject object, final PdfIndirectReference ref ) throws IOException {
		return body.add ( object, ref );
	}

	public PdfIndirectObject addToBody ( final PdfObject object, final PdfIndirectReference ref, final boolean inObjStm ) throws IOException {
		return body.add ( object, ref, inObjStm );
	}

	public PdfIndirectObject addToBody ( final PdfObject object, final int refNumber ) throws IOException {
		return body.add ( object, refNumber );
	}

	public PdfIndirectObject addToBody ( final PdfObject object, final int refNumber, final boolean inObjStm ) throws IOException {
		return body.add ( object, refNumber, 0, inObjStm );
	}

	public PdfIndirectReference getPdfIndirectReference () {
		return body.getPdfIndirectReference ();
	}

	protected int getIndirectReferenceNumber () {
		return body.getIndirectReferenceNumber ();
	}

	public OutputStreamCounter getOs () {
		return os;
	}

	protected PdfDictionary getCatalog ( final PdfIndirectReference rootObj ) {
		PdfDictionary catalog = pdf.getCatalog ( rootObj );
		buildStructTreeRootForTagged ( catalog );
		if ( !documentOCG.isEmpty () ) {
			fillOCProperties ();
			catalog.put ( PdfName.OCPROPERTIES, OCProperties );
		}
		return catalog;
	}

	protected void buildStructTreeRootForTagged ( PdfDictionary catalog ) {
		if ( tagged ) {
			try {
				getStructureTreeRoot ().buildTree ();
			} catch ( Exception e ) {
				throw new ExceptionConverter ( e );
			}
			catalog.put ( PdfName.STRUCTTREEROOT, structureTreeRoot.getReference () );
			PdfDictionary mi = new PdfDictionary ();
			mi.put ( PdfName.MARKED, PdfBoolean.PDFTRUE );
			if ( userProperties )
				mi.put ( PdfName.USERPROPERTIES, PdfBoolean.PDFTRUE );
			catalog.put ( PdfName.MARKINFO, mi );
		}
	}

	public PdfDictionary getExtraCatalog () {
		if ( extraCatalog == null )
			extraCatalog = new PdfDictionary ();
		return this.extraCatalog;
	}

	public void addPageDictEntry ( final PdfName key, final PdfObject object ) {
		pageDictEntries.put ( key, object );
	}

	public PdfDictionary getPageDictEntries () {
		return pageDictEntries;
	}

	public void resetPageDictEntries () {
		pageDictEntries = new PdfDictionary ();
	}

	public PdfIndirectReference getPageReference ( int page ) {
		--page;
		if ( page < 0 )
			throw new IndexOutOfBoundsException ( MessageLocalization.getComposedMessage ( "the.page.number.must.be.gt.eq.1" ) );
		PdfIndirectReference ref;
		if ( page < pageReferences.size () ) {
			ref = pageReferences.get ( page );
			if ( ref == null ) {
				ref = body.getPdfIndirectReference ();
				pageReferences.set ( page, ref );
			}
		} else {
			int empty = page - pageReferences.size ();
			for ( int k = 0; k < empty; ++k )
				pageReferences.add ( null );
			ref = body.getPdfIndirectReference ();
			pageReferences.add ( ref );
		}
		return ref;
	}

	PdfIndirectReference getCurrentPage () {
		return getPageReference ( currentPageNumber );
	}

	public int getCurrentPageNumber () {
		return currentPageNumber;
	}

	public PdfName getTabs () {
		return tabs;
	}

	public void setTabs ( final PdfName tabs ) {
		this.tabs = tabs;
	}

	void add ( final PdfPage page, final PdfContents contents ) throws PdfException {
		if ( !open ) {
			throw new PdfException ( MessageLocalization.getComposedMessage ( "the.document.is.not.open" ) );
		}
		PdfIndirectObject object;
		try {
			object = addToBody ( contents );
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
		page.add ( object.getIndirectReference () );
		// [U5]
		if ( group != null ) {
			page.put ( PdfName.GROUP, group );
			group = null;
		} else if ( rgbTransparencyBlending ) {
			PdfDictionary pp = new PdfDictionary ();
			pp.put ( PdfName.TYPE, PdfName.GROUP );
			pp.put ( PdfName.S, PdfName.TRANSPARENCY );
			pp.put ( PdfName.CS, PdfName.DEVICERGB );
			page.put ( PdfName.GROUP, pp );
		}
		root.addPage ( page );
		currentPageNumber++;
	}

	public PdfPageEvent getPageEvent () {
		return pageEvent;
	}

	@Override
	public void open () {
		super.open ();
		try {
			pdf_version.writeHeader ( os );
			body = new PdfBody ( this );
			if ( isPdfX () && ( (PdfXConformanceImp) pdfIsoConformance ).isPdfX32002 () ) {
				PdfDictionary sec = new PdfDictionary ();
				sec.put ( PdfName.GAMMA, new PdfArray ( new float[] { 2.2f, 2.2f, 2.2f } ) );
				sec.put ( PdfName.MATRIX, new PdfArray ( new float[] { 0.4124f, 0.2126f, 0.0193f, 0.3576f, 0.7152f, 0.1192f, 0.1805f, 0.0722f, 0.9505f } ) );
				sec.put ( PdfName.WHITEPOINT, new PdfArray ( new float[] { 0.9505f, 1f, 1.089f } ) );
				PdfArray arr = new PdfArray ( PdfName.CALRGB );
				arr.add ( sec );
				setDefaultColorspace ( PdfName.DEFAULTRGB, addToBody ( arr ).getIndirectReference () );
			}
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
	}

	@Override
	public void close () {
		if ( open ) {
			if ( currentPageNumber - 1 != pageReferences.size () )
				throw new RuntimeException ( "The page " + pageReferences.size () +
								" was requested but the document has only " + ( currentPageNumber - 1 ) + " pages." );
			pdf.close ();
			try {
				addSharedObjectsToBody ();
				for ( PdfOCG layer : documentOCG ) {
					addToBody ( layer.getPdfObject (), layer.getRef () );
				}
				PdfIndirectReference rootRef = root.writePageTree ();
				PdfDictionary catalog = getCatalog ( rootRef );
				if ( !documentOCG.isEmpty () )
					PdfWriter.checkPdfIsoConformance ( this, PdfIsoKeys.PDFISOKEY_LAYER, OCProperties );
				if ( xmpMetadata == null && xmpWriter != null ) {
					try {
						ByteArrayOutputStream baos = new ByteArrayOutputStream ();
						xmpWriter.serialize ( baos );
						xmpWriter.close ();
						xmpMetadata = baos.toByteArray ();
					} catch ( IOException | XMPException exc ) {
						xmpWriter = null;
					}
				}
				if ( xmpMetadata != null ) {
					PdfStream xmp = new PdfStream ( xmpMetadata );
					xmp.put ( PdfName.TYPE, PdfName.METADATA );
					xmp.put ( PdfName.SUBTYPE, PdfName.XML );
					if ( crypto != null && crypto.isMetadataEncrypted () ) {
						PdfArray ar = new PdfArray ();
						ar.add ( PdfName.CRYPT );
						xmp.put ( PdfName.FILTER, ar );
					}
					catalog.put ( PdfName.METADATA, body.add ( xmp ).getIndirectReference () );
				}
				if ( isPdfX () ) {
					completeInfoDictionary ( getInfo () );
					completeExtraCatalog ( getExtraCatalog () );
				}
				if ( extraCatalog != null ) {
					catalog.mergeDifferent ( extraCatalog );
				}

				writeOutlines ( catalog, false );

				PdfIndirectObject indirectCatalog = addToBody ( catalog, false );
				PdfIndirectObject infoObj = addToBody ( getInfo (), false );

				PdfIndirectReference encryption = null;
				PdfObject fileID;
				body.flushObjStm ();
				boolean isModified = ( originalFileID != null );
				if ( crypto != null ) {
					PdfIndirectObject encryptionObject = addToBody ( crypto.getEncryptionDictionary (), false );
					encryption = encryptionObject.getIndirectReference ();
					fileID = crypto.getFileID ( isModified );
				} else {
					fileID = PdfEncryption.createInfoId ( isModified ? originalFileID : PdfEncryption.createDocumentId (), isModified );
				}

				body.writeCrossReferenceTable ( os, indirectCatalog.getIndirectReference (),
								infoObj.getIndirectReference (), encryption, fileID, prevxref );

				if ( fullCompression ) {
					writeKeyInfo ( os );
					os.write ( getISOBytes ( "startxref\n" ) );
					os.write ( getISOBytes ( String.valueOf ( body.offset () ) ) );
					os.write ( getISOBytes ( "\n%%EOF\n" ) );
				} else {
					PdfTrailer trailer = new PdfTrailer ( body.size (),
									body.offset (),
									indirectCatalog.getIndirectReference (),
									infoObj.getIndirectReference (),
									encryption,
									fileID, prevxref );
					trailer.toPdf ( this, os );
				}
				super.close ();
			} catch ( IOException ioe ) {
				throw new ExceptionConverter ( ioe );
			}
		}
		getCounter ().written ( os.getCounter () );
	}

	protected void addXFormsToBody () throws IOException {
		for ( Object[] objs : formXObjects.values () ) {
			PdfTemplate template = (PdfTemplate) objs[1];
			if ( template != null && template.getIndirectReference () instanceof PRIndirectReference )
				continue;
			if ( template != null && template.getType () == PdfTemplate.TYPE_TEMPLATE ) {
				addToBody ( template.getFormXObject ( compressionLevel ), template.getIndirectReference () );
			}
		}
	}

	protected void addSharedObjectsToBody () throws IOException {
		for ( FontDetails details : documentFonts.values () ) {
			details.writeFont ( this );
		}
		addXFormsToBody ();
		for ( PdfReaderInstance element : readerInstances.values () ) {
			currentPdfReaderInstance = element;
			currentPdfReaderInstance.writeAllPages ();
		}
		currentPdfReaderInstance = null;
		for ( ColorDetails color : documentColors.values () ) {
			addToBody ( color.getSpotColor ( this ), color.getIndirectReference () );
		}
		for ( PdfPatternPainter pat : documentPatterns.keySet () ) {
			addToBody ( pat.getPattern ( compressionLevel ), pat.getIndirectReference () );
		}
		for ( PdfShadingPattern shadingPattern : documentShadingPatterns ) {
			shadingPattern.addToBody ();
		}
		for ( PdfShading shading : documentShadings ) {
			shading.addToBody ();
		}
		for ( Map.Entry<PdfDictionary, PdfObject[]> entry : documentExtGState.entrySet () ) {
			PdfDictionary gstate = entry.getKey ();
			PdfObject[] obj = entry.getValue ();
			addToBody ( gstate, (PdfIndirectReference) obj[1] );
		}
		for ( Map.Entry<Object, PdfObject[]> entry : documentProperties.entrySet () ) {
			Object prop = entry.getKey ();
			PdfObject[] obj = entry.getValue ();
			if ( prop instanceof PdfLayerMembership ) {
				PdfLayerMembership layer = (PdfLayerMembership) prop;
				addToBody ( layer.getPdfObject (), layer.getRef () );
			} else if ( prop instanceof PdfDictionary && !( prop instanceof PdfLayer ) ) {
				addToBody ( (PdfDictionary) prop, (PdfIndirectReference) obj[1] );
			}
		}
	}

	protected void writeOutlines ( final PdfDictionary catalog, final boolean namedAsNames ) throws IOException {
		if ( newBookmarks == null || newBookmarks.isEmpty () )
			return;
		PdfDictionary top = new PdfDictionary ();
		PdfIndirectReference topRef = getPdfIndirectReference ();
		Object[] kids = SimpleBookmark.iterateOutlines ( this, topRef, newBookmarks, namedAsNames );
		top.put ( PdfName.FIRST, (PdfIndirectReference) kids[0] );
		top.put ( PdfName.LAST, (PdfIndirectReference) kids[1] );
		top.put ( PdfName.COUNT, new PdfNumber ( (Integer) kids[2] ) );
		addToBody ( top, topRef );
		catalog.put ( PdfName.OUTLINES, topRef );
	}

	public void setAtLeastPdfVersion ( final char version ) {
		pdf_version.setAtLeastPdfVersion ( version );
	}

	public void addDeveloperExtension ( final PdfDeveloperExtension de ) {
		pdf_version.addDeveloperExtension ( de );
	}

	PdfVersionImp getPdfVersion () {
		return pdf_version;
	}

	public void setPdfVersion ( final char version ) {
		pdf_version.setPdfVersion ( version );
	}

	public void setPdfVersion ( final PdfName version ) {
		pdf_version.setPdfVersion ( version );
	}

	public void setViewerPreferences ( final int preferences ) {
		pdf.setViewerPreferences ( preferences );
	}

	public void addViewerPreference ( final PdfName key, final PdfObject value ) {
		pdf.addViewerPreference ( key, value );
	}

	public void setCollection ( final PdfCollection collection ) {
		setAtLeastPdfVersion ( VERSION_1_7 );
		pdf.setCollection ( collection );
	}

	public void addAnnotation ( final PdfAnnotation annot ) {
		pdf.addAnnotation ( annot );
	}

	void addAnnotation ( final PdfAnnotation annot, final int page ) {
		addAnnotation ( annot );
	}

	public void setLanguage ( final String language ) {
		pdf.setLanguage ( language );
	}

	protected PdfIsoConformance initPdfIsoConformance () {
		return new PdfXConformanceImp ( this );
	}

	public int getPDFXConformance () {
		if ( pdfIsoConformance instanceof PdfXConformanceImp )
			return ( (PdfXConformance) pdfIsoConformance ).getPDFXConformance ();
		else
			return PDFXNONE;
	}

	public boolean isPdfX () {
		if ( pdfIsoConformance instanceof PdfXConformanceImp )
			return ( (PdfXConformance) pdfIsoConformance ).isPdfX ();
		else
			return false;
	}

	public boolean isPdfIso () {
		return pdfIsoConformance.isPdfIso ();
	}

	PdfEncryption getEncryption () {
		return crypto;
	}

	public void setEncryption ( final byte[] userPassword, final byte[] ownerPassword, final int permissions, final int encryptionType )
					throws DocumentException {
		if ( pdf.isOpen () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "encryption.can.only.be.added.before.opening.the.document" ) );
		crypto = new PdfEncryption ();
		crypto.setCryptoMode ( encryptionType, 0 );
		crypto.setupAllKeys ( userPassword, ownerPassword, permissions );
	}

	public void setEncryption ( final Certificate[] certs, final int[] permissions, final int encryptionType ) throws DocumentException {
		if ( pdf.isOpen () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "encryption.can.only.be.added.before.opening.the.document" ) );
		crypto = new PdfEncryption ();
		if ( certs != null ) {
			for ( int i = 0; i < certs.length; i++ ) {
				crypto.addRecipient ( certs[i], permissions[i] );
			}
		}
		crypto.setCryptoMode ( encryptionType, 0 );
		crypto.getEncryptionDictionary ();
	}

	@Deprecated
	public void setEncryption ( final byte[] userPassword, final byte[] ownerPassword, final int permissions, final boolean strength128Bits )
					throws DocumentException {
		setEncryption ( userPassword, ownerPassword, permissions, strength128Bits ? STANDARD_ENCRYPTION_128 : STANDARD_ENCRYPTION_40 );
	}

	@Deprecated
	public void setEncryption ( final boolean strength, final String userPassword, final String ownerPassword, final int permissions )
					throws DocumentException {
		setEncryption ( getISOBytes ( userPassword ), getISOBytes ( ownerPassword ), permissions, strength ? STANDARD_ENCRYPTION_128 : STANDARD_ENCRYPTION_40 );
	}

	@Deprecated
	public void setEncryption ( final int encryptionType, final String userPassword, final String ownerPassword, final int permissions )
					throws DocumentException {
		setEncryption ( getISOBytes ( userPassword ), getISOBytes ( ownerPassword ), permissions, encryptionType );
	}

	public boolean isFullCompression () {
		return this.fullCompression;
	}

	public int getCompressionLevel () {
		return compressionLevel;
	}

	public void setCompressionLevel ( final int compressionLevel ) {
		if ( compressionLevel < PdfStream.NO_COMPRESSION || compressionLevel > PdfStream.BEST_COMPRESSION )
			this.compressionLevel = PdfStream.DEFAULT_COMPRESSION;
		else
			this.compressionLevel = compressionLevel;
	}

	FontDetails addSimple ( final BaseFont bf ) {
		FontDetails ret = documentFonts.get ( bf );
		if ( ret == null ) {
			PdfWriter.checkPdfIsoConformance ( this, PdfIsoKeys.PDFISOKEY_FONT, bf );
			if ( bf.getFontType () == BaseFont.FONT_TYPE_DOCUMENT ) {
				ret = new FontDetails ( new PdfName ( "F" + fontNumber++ ), ( (DocumentFont) bf ).getIndirectReference (), bf );
			} else {
				ret = new FontDetails ( new PdfName ( "F" + fontNumber++ ), body.getPdfIndirectReference (), bf );
			}
			documentFonts.put ( bf, ret );
		}
		return ret;
	}

	void eliminateFontSubset ( final PdfDictionary fonts ) {
		for ( FontDetails element : documentFonts.values () ) {
			if ( fonts.get ( element.getFontName () ) != null )
				element.setSubset ( false );
		}
	}

	PdfName addDirectTemplateSimple ( PdfTemplate template, final PdfName forcedName ) {
		PdfIndirectReference ref = template.getIndirectReference ();
		Object[] obj = formXObjects.get ( ref );
		PdfName name;
		try {
			if ( obj == null ) {
				if ( forcedName == null ) {
					name = new PdfName ( "Xf" + formXObjectsCounter );
					++formXObjectsCounter;
				} else
					name = forcedName;
				if ( template.getType () == PdfTemplate.TYPE_IMPORTED ) {
					PdfImportedPage ip = (PdfImportedPage) template;
					PdfReader r = ip.getPdfReaderInstance ().getReader ();
					if ( !readerInstances.containsKey ( r ) ) {
						readerInstances.put ( r, ip.getPdfReaderInstance () );
					}
					template = null;
				}
				formXObjects.put ( ref, new Object[] { name, template } );
			} else
				name = (PdfName) obj[0];
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
		return name;
	}

	public void releaseTemplate ( final PdfTemplate tp ) throws IOException {
		PdfIndirectReference ref = tp.getIndirectReference ();
		Object[] objs = formXObjects.get ( ref );
		if ( objs == null || objs[1] == null )
			return;
		PdfTemplate template = (PdfTemplate) objs[1];
		if ( template.getIndirectReference () instanceof PRIndirectReference )
			return;
		if ( template.getType () == PdfTemplate.TYPE_TEMPLATE ) {
			addToBody ( template.getFormXObject ( compressionLevel ), template.getIndirectReference () );
			objs[1] = null;
		}
	}

	protected PdfReaderInstance getPdfReaderInstance ( final PdfReader reader ) {
		PdfReaderInstance inst = readerInstances.get ( reader );
		if ( inst == null ) {
			inst = reader.getPdfReaderInstance ( this );
			readerInstances.put ( reader, inst );
		}
		return inst;
	}

	public void freeReader ( final PdfReader reader ) throws IOException {
		currentPdfReaderInstance = readerInstances.get ( reader );
		if ( currentPdfReaderInstance == null )
			return;
		currentPdfReaderInstance.writeAllPages ();
		currentPdfReaderInstance = null;
		readerInstances.remove ( reader );
	}

	protected int getNewObjectNumber ( final PdfReader reader, final int number, final int generation ) {
		if ( currentPdfReaderInstance == null ) {
			currentPdfReaderInstance = getPdfReaderInstance ( reader );
		}
		return currentPdfReaderInstance.getNewObjectNumber ( number );
	}

	PdfName getColorspaceName () {
		return new PdfName ( "CS" + colorNumber++ );
	}

	ColorDetails addSimple ( final PdfSpotColor spc ) {
		ColorDetails ret = documentColors.get ( spc );
		if ( ret == null ) {
			ret = new ColorDetails ( getColorspaceName (), body.getPdfIndirectReference (), spc );
			documentColors.put ( spc, ret );
		}
		return ret;
	}

	PdfName addSimplePattern ( final PdfPatternPainter painter ) {
		PdfName name = documentPatterns.get ( painter );
		try {
			if ( name == null ) {
				name = new PdfName ( "P" + patternNumber );
				++patternNumber;
				documentPatterns.put ( painter, name );
			}
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
		return name;
	}

	void addSimpleShadingPattern ( final PdfShadingPattern shading ) {
		if ( !documentShadingPatterns.contains ( shading ) ) {
			shading.setName ( patternNumber );
			++patternNumber;
			documentShadingPatterns.add ( shading );
			addSimpleShading ( shading.getShading () );
		}
	}

	void addSimpleShading ( final PdfShading shading ) {
		if ( !documentShadings.contains ( shading ) ) {
			documentShadings.add ( shading );
			shading.setName ( documentShadings.size () );
		}
	}

	PdfObject[] addSimpleExtGState ( final PdfDictionary gstate ) {
		if ( !documentExtGState.containsKey ( gstate ) ) {
			documentExtGState.put ( gstate, new PdfObject[] { new PdfName ( "GS" + ( documentExtGState.size () + 1 ) ), getPdfIndirectReference () } );
		}
		return documentExtGState.get ( gstate );
	}

	PdfObject[] addSimpleProperty ( final Object prop, final PdfIndirectReference refi ) {
		if ( !documentProperties.containsKey ( prop ) ) {
			if ( prop instanceof PdfOCG )
				PdfWriter.checkPdfIsoConformance ( this, PdfIsoKeys.PDFISOKEY_LAYER, prop );
			documentProperties.put ( prop, new PdfObject[] { new PdfName ( "Pr" + ( documentProperties.size () + 1 ) ), refi } );
		}
		return documentProperties.get ( prop );
	}

	boolean propertyExists ( final Object prop ) {
		return documentProperties.containsKey ( prop );
	}

	public boolean needToBeMarkedInContent ( IAccessibleElement element ) {
		if ( ( taggingMode & markInlineElementsOnly ) != 0 ) {
			return element.isInline () || PdfName.ARTIFACT.equals ( element.getRole () );
		}
		return true;
	}

	public void checkElementRole ( IAccessibleElement element, IAccessibleElement parent ) {
		if ( parent != null && ( parent.getRole () == null || PdfName.ARTIFACT.equals ( parent.getRole () ) ) )
			element.setRole ( null );
		else if ( ( taggingMode & markInlineElementsOnly ) != 0 ) {
			if ( element.isInline () && element.getRole () == null && ( parent == null || !parent.isInline () ) )
				throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "inline.elements.with.role.null.are.not.allowed" ) );
		}
	}

	public boolean isTagged () {
		return tagged;
	}

	protected void flushTaggedObjects () throws IOException {
	}

	protected void flushAcroFields () throws IOException, BadPdfFormatException {
	}

	public PdfStructureTreeRoot getStructureTreeRoot () {
		if ( tagged && structureTreeRoot == null )
			structureTreeRoot = new PdfStructureTreeRoot ( this );
		return structureTreeRoot;
	}

	private void addASEvent ( final PdfName event, final PdfName category ) {
		PdfArray arr = new PdfArray ();
		for ( Object element : documentOCG ) {
			PdfLayer layer = (PdfLayer) element;
			PdfDictionary usage = layer.getAsDict ( PdfName.USAGE );
			if ( usage != null && usage.get ( category ) != null )
				arr.add ( layer.getRef () );
		}
		if ( arr.isEmpty () )
			return;
		PdfDictionary d = OCProperties.getAsDict ( PdfName.D );
		PdfArray arras = d.getAsArray ( PdfName.AS );
		if ( arras == null ) {
			arras = new PdfArray ();
			d.put ( PdfName.AS, arras );
		}
		PdfDictionary as = new PdfDictionary ();
		as.put ( PdfName.EVENT, event );
		as.put ( PdfName.CATEGORY, new PdfArray ( category ) );
		as.put ( PdfName.OCGS, arr );
		arras.add ( as );
	}

	protected void fillOCProperties () {
		if ( OCProperties == null )
			OCProperties = new PdfOCProperties ();
		if ( OCProperties.get ( PdfName.OCGS ) == null ) {
			PdfArray gr = new PdfArray ();
			for ( Object element : documentOCG ) {
				PdfLayer layer = (PdfLayer) element;
				gr.add ( layer.getRef () );
			}
			OCProperties.put ( PdfName.OCGS, gr );
		}
		if ( OCProperties.get ( PdfName.D ) != null )
			return;
		ArrayList<PdfOCG> docOrder = new ArrayList<> ( documentOCGorder );
		for ( Iterator<PdfOCG> it = docOrder.iterator (); it.hasNext (); ) {
			PdfLayer layer = (PdfLayer) it.next ();
			if ( layer.getParent () != null )
				it.remove ();
		}
		PdfArray order = new PdfArray ();
		for ( Object element : docOrder ) {
			PdfLayer layer = (PdfLayer) element;
			getOCGOrder ( order, layer );
		}
		PdfDictionary d = new PdfDictionary ();
		OCProperties.put ( PdfName.D, d );
		d.put ( PdfName.ORDER, order );
		if ( !docOrder.isEmpty () && ( docOrder.get ( 0 ) instanceof PdfLayer ) ) {
			PdfLayer l = (PdfLayer) docOrder.get ( 0 );
			PdfString name = l.getAsString ( PdfName.NAME );
			if ( name != null ) {
				d.put ( PdfName.NAME, name );
			}
		}
		PdfArray gr = new PdfArray ();
		for ( Object element : documentOCG ) {
			PdfLayer layer = (PdfLayer) element;
			if ( !layer.isOn () )
				gr.add ( layer.getRef () );
		}
		if ( !gr.isEmpty () )
			d.put ( PdfName.OFF, gr );
		if ( !OCGRadioGroup.isEmpty () )
			d.put ( PdfName.RBGROUPS, OCGRadioGroup );
		if ( !OCGLocked.isEmpty () )
			d.put ( PdfName.LOCKED, OCGLocked );
		addASEvent ( PdfName.VIEW, PdfName.ZOOM );
		addASEvent ( PdfName.VIEW, PdfName.VIEW );
		addASEvent ( PdfName.PRINT, PdfName.PRINT );
		addASEvent ( PdfName.EXPORT, PdfName.EXPORT );
		d.put ( PdfName.LISTMODE, PdfName.VISIBLEPAGES );
	}

	void registerLayer ( final PdfOCG layer ) {
		PdfWriter.checkPdfIsoConformance ( this, PdfIsoKeys.PDFISOKEY_LAYER, layer );
		if ( layer instanceof PdfLayer ) {
			PdfLayer la = (PdfLayer) layer;
			if ( la.getTitle () == null ) {
				if ( !documentOCG.contains ( layer ) ) {
					documentOCG.add ( layer );
					documentOCGorder.add ( layer );
				}
			} else {
				documentOCGorder.add ( layer );
			}
		} else
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "only.pdflayer.is.accepted" ) );
	}

	public void setDuration ( final int seconds ) {
		pdf.setDuration ( seconds );
	}

	public void setTransition ( final PdfTransition transition ) {
		pdf.setTransition ( transition );
	}

	public void setThumbnail ( final Image image ) throws DocumentException {
		pdf.setThumbnail ( image );
	}

	public PdfDictionary getGroup () {
		return this.group;
	}

	public void setGroup ( final PdfDictionary group ) {
		this.group = group;
	}

	public float getSpaceCharRatio () {
		return SPACE_CHAR_RATIO_DEFAULT;
	}

	public PdfDictionary getDefaultColorspace () {
		return defaultColorspace;
	}

	public void setDefaultColorspace ( final PdfName key, final PdfObject cs ) {
		if ( cs == null || cs.isNull () )
			defaultColorspace.remove ( key );
		defaultColorspace.put ( key, cs );
	}

	ColorDetails addSimplePatternColorspace ( final BaseColor color ) {
		int type = ExtendedColor.getType ( color );
		if ( type == ExtendedColor.TYPE_PATTERN || type == ExtendedColor.TYPE_SHADING )
			throw new RuntimeException (
							MessageLocalization.getComposedMessage ( "an.uncolored.tile.pattern.can.not.have.another.pattern.or.shading.as.color" ) );
		try {
			switch ( type ) {
			case ExtendedColor.TYPE_RGB:
				if ( patternColorspaceRGB == null ) {
					patternColorspaceRGB = new ColorDetails ( getColorspaceName (), body.getPdfIndirectReference (), null );
					PdfArray array = new PdfArray ( PdfName.PATTERN );
					array.add ( PdfName.DEVICERGB );
					addToBody ( array, patternColorspaceRGB.getIndirectReference () );
				}
				return patternColorspaceRGB;
			case ExtendedColor.TYPE_CMYK:
				if ( patternColorspaceCMYK == null ) {
					patternColorspaceCMYK = new ColorDetails ( getColorspaceName (), body.getPdfIndirectReference (), null );
					PdfArray array = new PdfArray ( PdfName.PATTERN );
					array.add ( PdfName.DEVICECMYK );
					addToBody ( array, patternColorspaceCMYK.getIndirectReference () );
				}
				return patternColorspaceCMYK;
			case ExtendedColor.TYPE_GRAY:
				if ( patternColorspaceGRAY == null ) {
					patternColorspaceGRAY = new ColorDetails ( getColorspaceName (), body.getPdfIndirectReference (), null );
					PdfArray array = new PdfArray ( PdfName.PATTERN );
					array.add ( PdfName.DEVICEGRAY );
					addToBody ( array, patternColorspaceGRAY.getIndirectReference () );
				}
				return patternColorspaceGRAY;
			case ExtendedColor.TYPE_SEPARATION: {
				ColorDetails details = addSimple ( ( (SpotColor) color ).getPdfSpotColor () );
				ColorDetails patternDetails = documentSpotPatterns.get ( details );
				if ( patternDetails == null ) {
					patternDetails = new ColorDetails ( getColorspaceName (), body.getPdfIndirectReference (), null );
					PdfArray array = new PdfArray ( PdfName.PATTERN );
					array.add ( details.getIndirectReference () );
					addToBody ( array, patternDetails.getIndirectReference () );
					documentSpotPatterns.put ( details, patternDetails );
				}
				return patternDetails;
			}
			default:
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.color.type" ) );
			}
		} catch ( Exception e ) {
			throw new RuntimeException ( e.getMessage () );
		}
	}

	public PdfName addDirectImageSimple ( final Image image ) throws DocumentException {
		return addDirectImageSimple ( image, null );
	}

	public PdfName addDirectImageSimple ( final Image image, final PdfIndirectReference fixedRef ) throws DocumentException {
		PdfName name;
		if ( images.containsKey ( image.getMySerialId () ) ) {
			name = images.get ( image.getMySerialId () );
		} else {
			if ( image.isImgTemplate () ) {
				name = new PdfName ( "img" + images.size () );
				if ( image instanceof ImgWMF ) {
					try {
						ImgWMF wmf = (ImgWMF) image;
						wmf.readWMF ( PdfTemplate.createTemplate ( this, 0, 0 ) );
					} catch ( Exception e ) {
						throw new DocumentException ( e );
					}
				}
			} else {
				PdfIndirectReference dref = image.getDirectReference ();
				if ( dref != null ) {
					PdfName rname = new PdfName ( "img" + images.size () );
					images.put ( image.getMySerialId (), rname );
					imageDictionary.put ( rname, dref );
					return rname;
				}
				Image maskImage = image.getImageMask ();
				PdfIndirectReference maskRef = null;
				if ( maskImage != null ) {
					PdfName mname = images.get ( maskImage.getMySerialId () );
					maskRef = getImageReference ( mname );
				}
				PdfImage i = new PdfImage ( image, "img" + images.size (), maskRef );
				if ( image instanceof ImgJBIG2 ) {
					byte[] globals = ( (ImgJBIG2) image ).getGlobalBytes ();
					if ( globals != null ) {
						PdfDictionary decodeparms = new PdfDictionary ();
						decodeparms.put ( PdfName.JBIG2GLOBALS, getReferenceJBIG2Globals ( globals ) );
						i.put ( PdfName.DECODEPARMS, decodeparms );
					}
				}
				if ( image.hasICCProfile () ) {
					PdfICCBased icc = new PdfICCBased ( image.getICCProfile (), image.getCompressionLevel () );
					PdfIndirectReference iccRef = add ( icc );
					PdfArray iccArray = new PdfArray ();
					iccArray.add ( PdfName.ICCBASED );
					iccArray.add ( iccRef );
					PdfArray colorspace = i.getAsArray ( PdfName.COLORSPACE );
					if ( colorspace != null ) {
						if ( colorspace.size () > 1 && PdfName.INDEXED.equals ( colorspace.getPdfObject ( 0 ) ) )
							colorspace.set ( 1, iccArray );
						else
							i.put ( PdfName.COLORSPACE, iccArray );
					} else
						i.put ( PdfName.COLORSPACE, iccArray );
				}
				add ( i, fixedRef );
				name = i.name ();
			}
			images.put ( image.getMySerialId (), name );
		}
		return name;
	}

	void add ( final PdfImage pdfImage, PdfIndirectReference fixedRef ) throws PdfException {
		if ( !imageDictionary.contains ( pdfImage.name () ) ) {
			PdfWriter.checkPdfIsoConformance ( this, PdfIsoKeys.PDFISOKEY_IMAGE, pdfImage );
			if ( fixedRef instanceof PRIndirectReference ) {
				PRIndirectReference r2 = (PRIndirectReference) fixedRef;
				fixedRef = new PdfIndirectReference ( 0, getNewObjectNumber ( r2.getReader (), r2.getNumber (), r2.getGeneration () ) );
			}
			try {
				if ( fixedRef == null )
					fixedRef = addToBody ( pdfImage ).getIndirectReference ();
				else
					addToBody ( pdfImage, fixedRef );
			} catch ( IOException ioe ) {
				throw new ExceptionConverter ( ioe );
			}
			imageDictionary.put ( pdfImage.name (), fixedRef );
			return;
		}
		imageDictionary.get ( pdfImage.name () );
	}

	PdfIndirectReference getImageReference ( final PdfName name ) {
		return (PdfIndirectReference) imageDictionary.get ( name );
	}

	protected PdfIndirectReference add ( final PdfICCBased icc ) {
		PdfIndirectObject object;
		try {
			object = addToBody ( icc );
		} catch ( IOException ioe ) {
			throw new ExceptionConverter ( ioe );
		}
		return object.getIndirectReference ();
	}

	protected PdfIndirectReference getReferenceJBIG2Globals ( final byte[] content ) {
		if ( content == null )
			return null;
		for ( PdfStream stream : JBIG2Globals.keySet () ) {
			if ( Arrays.equals ( content, stream.getBytes () ) ) {
				return JBIG2Globals.get ( stream );
			}
		}
		PdfStream stream = new PdfStream ( content );
		PdfIndirectObject ref;
		try {
			ref = addToBody ( stream );
		} catch ( IOException e ) {
			return null;
		}
		JBIG2Globals.put ( stream, ref.getIndirectReference () );
		return ref.getIndirectReference ();
	}

	public boolean isRgbTransparencyBlending () {
		return this.rgbTransparencyBlending;
	}

	protected TtfUnicodeWriter getTtfUnicodeWriter () {
		if ( ttfUnicodeWriter == null )
			ttfUnicodeWriter = new TtfUnicodeWriter ( this );
		return ttfUnicodeWriter;
	}

	protected XmpWriter createXmpWriter ( ByteArrayOutputStream baos, PdfDictionary info ) throws IOException {
		return new XmpWriter ( baos, info );
	}

	public void checkPdfIsoConformance ( int key, Object obj1 ) {
		pdfIsoConformance.checkPdfIsoConformance ( key, obj1 );
	}

	private void completeInfoDictionary ( PdfDictionary info ) {
		if ( isPdfX () ) {
			if ( info.get ( PdfName.GTS_PDFXVERSION ) == null ) {
				if ( ( (PdfXConformanceImp) pdfIsoConformance ).isPdfX1A2001 () ) {
					info.put ( PdfName.GTS_PDFXVERSION, new PdfString ( "PDF/X-1:2001" ) );
					info.put ( new PdfName ( "GTS_PDFXConformance" ), new PdfString ( "PDF/X-1a:2001" ) );
				} else if ( ( (PdfXConformanceImp) pdfIsoConformance ).isPdfX32002 () )
					info.put ( PdfName.GTS_PDFXVERSION, new PdfString ( "PDF/X-3:2002" ) );
			}
			if ( info.get ( PdfName.TITLE ) == null ) {
				info.put ( PdfName.TITLE, new PdfString ( "Pdf document" ) );
			}
			if ( info.get ( PdfName.CREATOR ) == null ) {
				info.put ( PdfName.CREATOR, new PdfString ( "Unknown" ) );
			}
			if ( info.get ( PdfName.TRAPPED ) == null ) {
				info.put ( PdfName.TRAPPED, new PdfName ( "False" ) );
			}
		}
	}

	private void completeExtraCatalog ( PdfDictionary extraCatalog ) {
		if ( isPdfX () ) {
			if ( extraCatalog.get ( PdfName.OUTPUTINTENTS ) == null ) {
				PdfDictionary out = new PdfDictionary ( PdfName.OUTPUTINTENT );
				out.put ( PdfName.OUTPUTCONDITION, new PdfString ( "SWOP CGATS TR 001-1995" ) );
				out.put ( PdfName.OUTPUTCONDITIONIDENTIFIER, new PdfString ( "CGATS TR 001" ) );
				out.put ( PdfName.REGISTRYNAME, new PdfString ( "http://www.color.org" ) );
				out.put ( PdfName.INFO, new PdfString ( "" ) );
				out.put ( PdfName.S, PdfName.GTS_PDFX );
				extraCatalog.put ( PdfName.OUTPUTINTENTS, new PdfArray ( out ) );
			}
		}
	}

	public List<PdfName> getStandardStructElems () {
		if ( pdf_version.getVersion () < VERSION_1_7 ) {
			return standardStructElems_1_4;
		} else {
			return standardStructElems_1_7;
		}
	}

	public static class PdfBody {

		private static final int OBJSINSTREAM = 200;

		protected final TreeSet<PdfCrossReference> xrefs;

		protected final PdfWriter writer;

		protected int refnum;

		protected long position;

		protected ByteBuffer index;

		protected ByteBuffer streamObjects;

		protected int currentObjNum;

		protected int numObj = 0;

		protected PdfBody ( final PdfWriter writer ) {
			xrefs = new TreeSet<> ();
			xrefs.add ( new PdfCrossReference ( 0, 0, GENERATION_MAX ) );
			position = writer.getOs ().getCounter ();
			refnum = 1;
			this.writer = writer;
		}

		void setRefnum ( final int refnum ) {
			this.refnum = refnum;
		}

		protected PdfCrossReference addToObjStm ( final PdfObject obj, final int nObj ) throws IOException {
			if ( numObj >= OBJSINSTREAM )
				flushObjStm ();
			if ( index == null ) {
				index = new ByteBuffer ();
				streamObjects = new ByteBuffer ();
				currentObjNum = getIndirectReferenceNumber ();
				numObj = 0;
			}
			int p = streamObjects.size ();
			int idx = numObj++;
			PdfEncryption enc = writer.crypto;
			writer.crypto = null;
			obj.toPdf ( writer, streamObjects );
			writer.crypto = enc;
			streamObjects.append ( ' ' );
			index.append ( nObj ).append ( ' ' ).append ( p ).append ( ' ' );
			return new PdfCrossReference ( 2, nObj, currentObjNum, idx );
		}

		public void flushObjStm () throws IOException {
			if ( numObj == 0 )
				return;
			int first = index.size ();
			index.append ( streamObjects );
			PdfStream stream = new PdfStream ( index.toByteArray () );
			stream.flateCompress ( writer.getCompressionLevel () );
			stream.put ( PdfName.TYPE, PdfName.OBJSTM );
			stream.put ( PdfName.N, new PdfNumber ( numObj ) );
			stream.put ( PdfName.FIRST, new PdfNumber ( first ) );
			add ( stream, currentObjNum );
			index = null;
			streamObjects = null;
			numObj = 0;
		}

		PdfIndirectObject add ( final PdfObject object ) throws IOException {
			return add ( object, getIndirectReferenceNumber () );
		}

		PdfIndirectObject add ( final PdfObject object, final boolean inObjStm ) throws IOException {
			return add ( object, getIndirectReferenceNumber (), 0, inObjStm );
		}

		public PdfIndirectReference getPdfIndirectReference () {
			return new PdfIndirectReference ( 0, getIndirectReferenceNumber () );
		}

		protected int getIndirectReferenceNumber () {
			int n = refnum++;
			xrefs.add ( new PdfCrossReference ( n, 0, GENERATION_MAX ) );
			return n;
		}

		PdfIndirectObject add ( final PdfObject object, final PdfIndirectReference ref ) throws IOException {
			return add ( object, ref, true );
		}

		PdfIndirectObject add ( final PdfObject object, final PdfIndirectReference ref, final boolean inObjStm ) throws IOException {
			return add ( object, ref.getNumber (), ref.getGeneration (), inObjStm );
		}

		PdfIndirectObject add ( final PdfObject object, final int refNumber ) throws IOException {
			return add ( object, refNumber, 0, true ); // to false
		}

		protected PdfIndirectObject add ( final PdfObject object, final int refNumber, final int generation, final boolean inObjStm ) throws IOException {
			if ( inObjStm && object.canBeInObjStm () && writer.isFullCompression () ) {
				PdfCrossReference pxref = addToObjStm ( object, refNumber );
				PdfIndirectObject indirect = new PdfIndirectObject ( refNumber, object, writer );
				if ( !xrefs.add ( pxref ) ) {
					xrefs.remove ( pxref );
					xrefs.add ( pxref );
				}
				return indirect;
			} else {
				PdfIndirectObject indirect;
				if ( writer.isFullCompression () ) {
					indirect = new PdfIndirectObject ( refNumber, object, writer );
					write ( indirect, refNumber );
				} else {
					indirect = new PdfIndirectObject ( refNumber, generation, object, writer );
					write ( indirect, refNumber, generation );
				}
				return indirect;
			}
		}

		protected void write ( final PdfIndirectObject indirect, final int refNumber ) throws IOException {
			PdfCrossReference pxref = new PdfCrossReference ( refNumber, position );
			if ( !xrefs.add ( pxref ) ) {
				xrefs.remove ( pxref );
				xrefs.add ( pxref );
			}
			indirect.writeTo ( writer.getOs () );
			position = writer.getOs ().getCounter ();
		}

		protected void write ( final PdfIndirectObject indirect, final int refNumber, final int generation ) throws IOException {
			PdfCrossReference pxref = new PdfCrossReference ( refNumber, position, generation );
			if ( !xrefs.add ( pxref ) ) {
				xrefs.remove ( pxref );
				xrefs.add ( pxref );
			}
			indirect.writeTo ( writer.getOs () );
			position = writer.getOs ().getCounter ();
		}

		public long offset () {
			return position;
		}

		public int size () {
			return Math.max ( xrefs.last ().getRefnum () + 1, refnum );
		}

		public void writeCrossReferenceTable ( final OutputStream os, final PdfIndirectReference root, final PdfIndirectReference info,
						final PdfIndirectReference encryption, final PdfObject fileID, final long prevxref ) throws IOException {
			int refNumber = 0;
			if ( writer.isFullCompression () ) {
				flushObjStm ();
				refNumber = getIndirectReferenceNumber ();
				xrefs.add ( new PdfCrossReference ( refNumber, position ) );
			}
			PdfCrossReference entry = xrefs.first ();
			int first = entry.getRefnum ();
			int len = 0;
			ArrayList<Integer> sections = new ArrayList<> ();
			for ( PdfCrossReference pdfCrossReference : xrefs ) {
				entry = pdfCrossReference;
				if ( first + len == entry.getRefnum () )
					++len;
				else {
					sections.add ( first );
					sections.add ( len );
					first = entry.getRefnum ();
					len = 1;
				}
			}
			sections.add ( first );
			sections.add ( len );
			if ( writer.isFullCompression () ) {
				int mid = 5;
				long mask = 0xff00000000L;
				for ( ; mid > 1; --mid ) {
					if ( ( mask & position ) != 0 )
						break;
					mask >>>= 8;
				}
				ByteBuffer buf = new ByteBuffer ();

				for ( PdfCrossReference element : xrefs ) {
					entry = element;
					entry.toPdf ( mid, buf );
				}
				PdfStream xr = new PdfStream ( buf.toByteArray () );
				xr.flateCompress ( writer.getCompressionLevel () );
				xr.put ( PdfName.SIZE, new PdfNumber ( size () ) );
				xr.put ( PdfName.ROOT, root );
				if ( info != null ) {
					xr.put ( PdfName.INFO, info );
				}
				if ( encryption != null )
					xr.put ( PdfName.ENCRYPT, encryption );
				if ( fileID != null )
					xr.put ( PdfName.ID, fileID );
				xr.put ( PdfName.W, new PdfArray ( new int[] { 1, mid, 2 } ) );
				xr.put ( PdfName.TYPE, PdfName.XREF );
				PdfArray idx = new PdfArray ();
				for ( Integer section : sections )
					idx.add ( new PdfNumber ( section ) );
				xr.put ( PdfName.INDEX, idx );
				if ( prevxref > 0 )
					xr.put ( PdfName.PREV, new PdfNumber ( prevxref ) );
				PdfEncryption enc = writer.crypto;
				writer.crypto = null;
				PdfIndirectObject indirect = new PdfIndirectObject ( refNumber, xr, writer );
				indirect.writeTo ( writer.getOs () );
				writer.crypto = enc;
			} else {
				os.write ( getISOBytes ( "xref\n" ) );
				Iterator<PdfCrossReference> i = xrefs.iterator ();
				for ( int k = 0; k < sections.size (); k += 2 ) {
					first = sections.get ( k );
					len = sections.get ( k + 1 );
					os.write ( getISOBytes ( String.valueOf ( first ) ) );
					os.write ( getISOBytes ( " " ) );
					os.write ( getISOBytes ( String.valueOf ( len ) ) );
					os.write ( '\n' );
					while ( len-- > 0 ) {
						entry = i.next ();
						entry.toPdf ( os );
					}
				}
			}
		}

		static public class PdfCrossReference implements Comparable<PdfCrossReference> {

			private final int type;

			private final long offset;

			private final int refnum;

			private final int generation;

			public PdfCrossReference ( final int refnum, final long offset, final int generation ) {
				type = 0;
				this.offset = offset;
				this.refnum = refnum;
				this.generation = generation;
			}

			public PdfCrossReference ( final int refnum, final long offset ) {
				type = 1;
				this.offset = offset;
				this.refnum = refnum;
				this.generation = 0;
			}

			public PdfCrossReference ( final int type, final int refnum, final long offset, final int generation ) {
				this.type = type;
				this.offset = offset;
				this.refnum = refnum;
				this.generation = generation;
			}

			public int getRefnum () {
				return refnum;
			}

			public void toPdf ( final OutputStream os ) throws IOException {
				StringBuilder off = new StringBuilder ( "0000000000" ).append ( offset );
				off.delete ( 0, off.length () - 10 );
				StringBuilder gen = new StringBuilder ( "00000" ).append ( generation );
				gen.delete ( 0, gen.length () - 5 );

				off.append ( ' ' ).append ( gen ).append ( generation == GENERATION_MAX ? " f \n" : " n \n" );
				os.write ( getISOBytes ( off.toString () ) );
			}

			public void toPdf ( int midSize, final OutputStream os ) throws IOException {
				os.write ( (byte) type );
				while ( --midSize >= 0 )
					os.write ( (byte) ( offset >>> 8 * midSize & 0xff ) );
				os.write ( (byte) ( generation >>> 8 & 0xff ) );
				os.write ( (byte) ( generation & 0xff ) );
			}

			public int compareTo ( final PdfCrossReference other ) {
				return Integer.compare ( refnum, other.refnum );
			}

			@Override
			public boolean equals ( final Object obj ) {
				if ( obj instanceof PdfCrossReference ) {
					PdfCrossReference other = (PdfCrossReference) obj;
					return refnum == other.refnum;
				} else
					return false;
			}

			@Override
			public int hashCode () {
				return refnum;
			}

		}
	}


	static public class PdfTrailer extends PdfDictionary {

		long offset;

		public PdfTrailer ( final int size, final long offset, final PdfIndirectReference root, final PdfIndirectReference info,
						final PdfIndirectReference encryption, final PdfObject fileID, final long prevxref ) {
			this.offset = offset;
			put ( PdfName.SIZE, new PdfNumber ( size ) );
			put ( PdfName.ROOT, root );
			if ( info != null ) {
				put ( PdfName.INFO, info );
			}
			if ( encryption != null )
				put ( PdfName.ENCRYPT, encryption );
			if ( fileID != null )
				put ( PdfName.ID, fileID );
			if ( prevxref > 0 )
				put ( PdfName.PREV, new PdfNumber ( prevxref ) );
		}

		@Override
		public void toPdf ( final PdfWriter writer, final OutputStream os ) throws IOException {
			PdfWriter.checkPdfIsoConformance ( writer, PdfIsoKeys.PDFISOKEY_TRAILER, this );
			os.write ( getISOBytes ( "trailer\n" ) );
			super.toPdf ( null, os );
			os.write ( '\n' );
			writeKeyInfo ( os );
			os.write ( getISOBytes ( "startxref\n" ) );
			os.write ( getISOBytes ( String.valueOf ( offset ) ) );
			os.write ( getISOBytes ( "\n%%EOF\n" ) );
		}
	}

}
