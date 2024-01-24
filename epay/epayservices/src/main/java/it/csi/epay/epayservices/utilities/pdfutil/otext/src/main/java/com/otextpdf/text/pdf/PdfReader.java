/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.PageSize;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.BadPasswordException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.InvalidPdfException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.UnsupportedPdfException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSource;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.WindowRandomAccessSource;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Counter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.CounterFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Level;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Logger;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.LoggerFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfViewerPreferences;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfViewerPreferencesImp;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.security.ExternalDecryptionProcess;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.zip.InflaterInputStream;


public class PdfReader implements PdfViewerPreferences {

	static final PdfName[] pageInhCandidates = {
					PdfName.MEDIABOX, PdfName.ROTATE, PdfName.RESOURCES, PdfName.CROPBOX
	};

	static final byte[] endstream = PdfEncodings.convertToBytes ( "endstream", null );

	static final byte[] endobj = PdfEncodings.convertToBytes ( "endobj", null );

	private static final Logger LOGGER = LoggerFactory.getLogger ( PdfReader.class );

	public static boolean unethicalreading = false;

	public static boolean debugmode = false;

	protected static Counter COUNTER = CounterFactory.getCounter ( PdfReader.class );

	private final boolean partial;

	private final PdfViewerPreferencesImp viewerPreferences = new PdfViewerPreferencesImp ();

	protected PRTokeniser tokens;

	protected long[] xref;

	protected HashMap<Integer, IntHashtable> objStmMark;

	protected LongHashtable objStmToOffset;

	protected boolean newXrefType;

	protected ArrayList<PdfObject> xrefObj;

	protected PdfDictionary trailer;

	protected PdfDictionary catalog;

	protected PageRefs pageRefs;

	protected PRAcroForm acroForm = null;

	protected boolean acroFormParsed = false;

	protected boolean encrypted = false;

	protected boolean rebuilt = false;

	protected int freeXref;

	protected boolean tampered = false;

	protected long lastXref;

	protected long eofPos;

	protected char pdfVersion;

	protected PdfEncryption decrypt;

	protected byte[] password;

	protected Key certificateKey = null;

	protected Certificate certificate = null;

	protected String certificateKeyProvider = null;

	protected ExternalDecryptionProcess externalDecryptionProcess = null;

	protected ArrayList<PdfString> strings = new ArrayList<> ();

	protected boolean sharedStreams = true;

	protected boolean consolidateNamedDestinations = false;

	protected boolean remoteToLocalNamedDestinations = false;

	protected int rValue;

	protected int pValue;

	PdfDictionary rootPages;

	private boolean ownerPasswordUsed;

	private int objNum;

	private int objGen;

	private long fileLength;

	private boolean hybridXref;

	private int lastXrefPartial = -1;

	private PRIndirectReference cryptoRef;

	private boolean encryptionError;

	private boolean appendable;

	private int readDepth = 0;

	private PdfReader ( RandomAccessSource byteSource, boolean partialRead, byte[] ownerPassword, Certificate certificate, Key certificateKey,
					String certificateKeyProvider, ExternalDecryptionProcess externalDecryptionProcess, boolean closeSourceOnConstructorError )
					throws IOException {
		this.certificate = certificate;
		this.certificateKey = certificateKey;
		this.certificateKeyProvider = certificateKeyProvider;
		this.externalDecryptionProcess = externalDecryptionProcess;
		this.password = ownerPassword;
		this.partial = partialRead;
		try {

			tokens = getOffsetTokeniser ( byteSource );

			if ( partialRead ) {
				readPdfPartial ();
			} else {
				readPdf ();
			}
		} catch ( IOException e ) {
			if ( closeSourceOnConstructorError )
				byteSource.close ();
			throw e;
		}
		getCounter ().read ( fileLength );
	}

	public PdfReader ( final String filename ) throws IOException {
		this ( filename, (byte[]) null );
	}

	public PdfReader ( final String filename, final byte[] ownerPassword ) throws IOException {
		this ( filename, ownerPassword, false );
	}

	public PdfReader ( final String filename, final byte[] ownerPassword, boolean partial ) throws IOException {
		this (
						new RandomAccessSourceFactory ()
										.setForceRead ( false )
										.setUsePlainRandomAccess ( Document.plainRandomAccess )
										.createBestSource ( filename ),
						partial,
						ownerPassword,
						null,
						null,
						null,
						null,
						true
		);
	}

	public PdfReader ( final byte[] pdfIn ) throws IOException {
		this ( pdfIn, null );
	}

	public PdfReader ( final byte[] pdfIn, final byte[] ownerPassword ) throws IOException {
		this (
						new RandomAccessSourceFactory ().createSource ( pdfIn ),
						false,
						ownerPassword,
						null,
						null,
						null,
						null,
						true
		);

	}

	public PdfReader ( final String filename, final Certificate certificate, final Key certificateKey, final String certificateKeyProvider )
					throws IOException {
		this (
						new RandomAccessSourceFactory ()
										.setForceRead ( false )
										.setUsePlainRandomAccess ( Document.plainRandomAccess )
										.createBestSource ( filename ),
						false,
						null,
						certificate,
						certificateKey,
						certificateKeyProvider,
						null,
						true

		);

	}

	public PdfReader ( final String filename, final ExternalDecryptionProcess externalDecryptionProcess ) throws IOException {
		this (
						new RandomAccessSourceFactory ()
										.setForceRead ( false )
										.setUsePlainRandomAccess ( Document.plainRandomAccess )
										.createBestSource ( filename ),
						false,
						null,
						null,
						null,
						null,
						externalDecryptionProcess,
						true
		);

	}

	public PdfReader ( final URL url ) throws IOException {
		this ( url, null );
	}

	public PdfReader ( final URL url, final byte[] ownerPassword ) throws IOException {
		this (
						new RandomAccessSourceFactory ().createSource ( url ),
						false,
						ownerPassword,
						null,
						null,
						null,
						null,
						true
		);

	}

	public PdfReader ( final InputStream is, final byte[] ownerPassword ) throws IOException {
		this (
						new RandomAccessSourceFactory ().createSource ( is ),
						false,
						ownerPassword,
						null,
						null,
						null,
						null,
						false
		);

	}

	public PdfReader ( final InputStream is ) throws IOException {
		this ( is, null );
	}

	public PdfReader ( final RandomAccessFileOrArray raf, final byte[] ownerPassword ) throws IOException {
		this (
						raf.getByteSource (),
						true,
						ownerPassword,
						null,
						null,
						null,
						null,
						false
		);
	}

	public PdfReader ( final PdfReader reader ) {
		this.appendable = reader.appendable;
		this.consolidateNamedDestinations = reader.consolidateNamedDestinations;
		this.encrypted = reader.encrypted;
		this.rebuilt = reader.rebuilt;
		this.sharedStreams = reader.sharedStreams;
		this.tampered = reader.tampered;
		this.password = reader.password;
		this.pdfVersion = reader.pdfVersion;
		this.eofPos = reader.eofPos;
		this.freeXref = reader.freeXref;
		this.lastXref = reader.lastXref;
		this.newXrefType = reader.newXrefType;
		this.tokens = new PRTokeniser ( reader.tokens.getSafeFile () );
		if ( reader.decrypt != null )
			this.decrypt = new PdfEncryption ( reader.decrypt );
		this.pValue = reader.pValue;
		this.rValue = reader.rValue;
		this.xrefObj = new ArrayList<> ( reader.xrefObj );
		for ( int k = 0; k < reader.xrefObj.size (); ++k ) {
			this.xrefObj.set ( k, duplicatePdfObject ( reader.xrefObj.get ( k ), this ) );
		}
		this.pageRefs = new PageRefs ( reader.pageRefs, this );
		this.trailer = (PdfDictionary) duplicatePdfObject ( reader.trailer, this );
		this.catalog = trailer.getAsDict ( PdfName.ROOT );
		this.rootPages = catalog.getAsDict ( PdfName.PAGES );
		this.fileLength = reader.fileLength;
		this.partial = reader.partial;
		this.hybridXref = reader.hybridXref;
		this.objStmToOffset = reader.objStmToOffset;
		this.xref = reader.xref;
		this.cryptoRef = (PRIndirectReference) duplicatePdfObject ( reader.cryptoRef, this );
		this.ownerPasswordUsed = reader.ownerPasswordUsed;
	}

	private static PRTokeniser getOffsetTokeniser ( RandomAccessSource byteSource ) throws IOException {
		PRTokeniser tok = new PRTokeniser ( new RandomAccessFileOrArray ( byteSource ) );
		int offset = tok.getHeaderOffset ();
		if ( offset != 0 ) {
			RandomAccessSource offsetSource = new WindowRandomAccessSource ( byteSource, offset );
			tok = new PRTokeniser ( new RandomAccessFileOrArray ( offsetSource ) );
		}
		return tok;
	}

	public static Rectangle getNormalizedRectangle ( final PdfArray box ) {
		float llx = ( (PdfNumber) getPdfObjectRelease ( box.getPdfObject ( 0 ) ) ).floatValue ();
		float lly = ( (PdfNumber) getPdfObjectRelease ( box.getPdfObject ( 1 ) ) ).floatValue ();
		float urx = ( (PdfNumber) getPdfObjectRelease ( box.getPdfObject ( 2 ) ) ).floatValue ();
		float ury = ( (PdfNumber) getPdfObjectRelease ( box.getPdfObject ( 3 ) ) ).floatValue ();
		return new Rectangle ( Math.min ( llx, urx ), Math.min ( lly, ury ),
						Math.max ( llx, urx ), Math.max ( lly, ury ) );
	}

	public static PdfObject getPdfObjectRelease ( final PdfObject obj ) {
		PdfObject obj2 = getPdfObject ( obj );
		releaseLastXrefPartial ( obj );
		return obj2;
	}

	public static PdfObject getPdfObject ( PdfObject obj ) {
		if ( obj == null )
			return null;
		if ( !obj.isIndirect () )
			return obj;
		try {
			PRIndirectReference ref = (PRIndirectReference) obj;
			int idx = ref.getNumber ();
			boolean appendable = ref.getReader ().appendable;
			obj = ref.getReader ().getPdfObject ( idx );
			if ( obj == null ) {
				return null;
			} else {
				if ( appendable ) {
					switch ( obj.type () ) {
					case PdfObject.NULL:
						obj = new PdfNull ();
						break;
					case PdfObject.BOOLEAN:
						obj = new PdfBoolean ( ( (PdfBoolean) obj ).booleanValue () );
						break;
					case PdfObject.NAME:
						obj = new PdfName ( obj.getBytes () );
						break;
					}
					obj.setIndRef ( ref );
				}
				return obj;
			}
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public static PdfObject getPdfObjectRelease ( final PdfObject obj, final PdfObject parent ) {
		PdfObject obj2 = getPdfObject ( obj, parent );
		releaseLastXrefPartial ( obj );
		return obj2;
	}

	public static PdfObject getPdfObject ( PdfObject obj, final PdfObject parent ) {
		if ( obj == null )
			return null;
		if ( !obj.isIndirect () ) {
			PRIndirectReference ref;
			if ( parent != null && ( ref = parent.getIndRef () ) != null && ref.getReader ().isAppendable () ) {
				switch ( obj.type () ) {
				case PdfObject.NULL:
					obj = new PdfNull ();
					break;
				case PdfObject.BOOLEAN:
					obj = new PdfBoolean ( ( (PdfBoolean) obj ).booleanValue () );
					break;
				case PdfObject.NAME:
					obj = new PdfName ( obj.getBytes () );
					break;
				}
				obj.setIndRef ( ref );
			}
			return obj;
		}
		return getPdfObject ( obj );
	}

	public static void releaseLastXrefPartial ( final PdfObject obj ) {
		if ( obj == null )
			return;
		if ( !obj.isIndirect () )
			return;
		if ( !( obj instanceof PRIndirectReference ) )
			return;

		PRIndirectReference ref = (PRIndirectReference) obj;
		PdfReader reader = ref.getReader ();
		if ( reader.partial && reader.lastXrefPartial != -1 && reader.lastXrefPartial == ref.getNumber () ) {
			reader.xrefObj.set ( reader.lastXrefPartial, null );
		}
		reader.lastXrefPartial = -1;
	}

	public static PdfObject killIndirect ( final PdfObject obj ) {
		if ( obj == null || obj.isNull () )
			return null;
		PdfObject ret = getPdfObjectRelease ( obj );
		if ( obj.isIndirect () ) {
			PRIndirectReference ref = (PRIndirectReference) obj;
			PdfReader reader = ref.getReader ();
			int n = ref.getNumber ();
			reader.xrefObj.set ( n, null );
			if ( reader.partial )
				reader.xref[n * 2] = -1;
		}
		return ret;
	}

	public static byte[] FlateDecode ( final byte[] in ) {
		byte[] b = FlateDecode ( in, true );
		if ( b == null )
			return FlateDecode ( in, false );
		return b;
	}

	public static byte[] decodePredictor ( final byte[] in, final PdfObject dicPar ) {
		if ( dicPar == null || !dicPar.isDictionary () )
			return in;
		PdfDictionary dic = (PdfDictionary) dicPar;
		PdfObject obj = getPdfObject ( dic.get ( PdfName.PREDICTOR ) );
		if ( obj == null || !obj.isNumber () )
			return in;
		int predictor = ( (PdfNumber) obj ).intValue ();
		if ( predictor < 10 && predictor != 2 )
			return in;
		int width = 1;
		obj = getPdfObject ( dic.get ( PdfName.COLUMNS ) );
		if ( obj != null && obj.isNumber () )
			width = ( (PdfNumber) obj ).intValue ();
		int colors = 1;
		obj = getPdfObject ( dic.get ( PdfName.COLORS ) );
		if ( obj != null && obj.isNumber () )
			colors = ( (PdfNumber) obj ).intValue ();
		int bpc = 8;
		obj = getPdfObject ( dic.get ( PdfName.BITSPERCOMPONENT ) );
		if ( obj != null && obj.isNumber () )
			bpc = ( (PdfNumber) obj ).intValue ();
		DataInputStream dataStream = new DataInputStream ( new ByteArrayInputStream ( in ) );
		ByteArrayOutputStream fout = new ByteArrayOutputStream ( in.length );
		int bytesPerPixel = colors * bpc / 8;
		int bytesPerRow = ( colors * width * bpc + 7 ) / 8;
		byte[] curr = new byte[bytesPerRow];
		byte[] prior = new byte[bytesPerRow];
		if ( predictor == 2 ) {
			if ( bpc == 8 ) {
				int numRows = in.length / bytesPerRow;
				for ( int row = 0; row < numRows; row++ ) {
					int rowStart = row * bytesPerRow;
					for ( int col = bytesPerPixel; col < bytesPerRow; col++ ) {
						in[rowStart + col] = (byte) ( in[rowStart + col] + in[rowStart + col - bytesPerPixel] );
					}
				}
			}
			return in;
		}
		while ( true ) {
			int filter;
			try {
				filter = dataStream.read ();
				if ( filter < 0 ) {
					return fout.toByteArray ();
				}
				dataStream.readFully ( curr, 0, bytesPerRow );
			} catch ( Exception e ) {
				return fout.toByteArray ();
			}

			switch ( filter ) {
			case 0:
				break;
			case 1:
				for ( int i = bytesPerPixel; i < bytesPerRow; i++ ) {
					curr[i] += curr[i - bytesPerPixel];
				}
				break;
			case 2:
				for ( int i = 0; i < bytesPerRow; i++ ) {
					curr[i] += prior[i];
				}
				break;
			case 3:
				for ( int i = 0; i < bytesPerPixel; i++ ) {
					curr[i] += (byte) ( prior[i] / 2 );
				}
				for ( int i = bytesPerPixel; i < bytesPerRow; i++ ) {
					curr[i] += (byte) ( ( ( curr[i - bytesPerPixel] & 0xff ) + ( prior[i] & 0xff ) ) / 2 );
				}
				break;
			case 4:
				for ( int i = 0; i < bytesPerPixel; i++ ) {
					curr[i] += prior[i];
				}

				for ( int i = bytesPerPixel; i < bytesPerRow; i++ ) {
					int a = curr[i - bytesPerPixel] & 0xff;
					int b = prior[i] & 0xff;
					int c = prior[i - bytesPerPixel] & 0xff;

					int p = a + b - c;
					int pa = Math.abs ( p - a );
					int pb = Math.abs ( p - b );
					int pc = Math.abs ( p - c );

					int ret;

					if ( pa <= pb && pa <= pc ) {
						ret = a;
					} else if ( pb <= pc ) {
						ret = b;
					} else {
						ret = c;
					}
					curr[i] += (byte) ret;
				}
				break;
			default:
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "png.filter.unknown" ) );
			}
			try {
				fout.write ( curr );
			} catch ( IOException ignored ) {
			}

			byte[] tmp = prior;
			prior = curr;
			curr = tmp;
		}
	}

	public static byte[] FlateDecode ( final byte[] in, final boolean strict ) {
		ByteArrayInputStream stream = new ByteArrayInputStream ( in );
		InflaterInputStream zip = new InflaterInputStream ( stream );
		ByteArrayOutputStream out = new ByteArrayOutputStream ();
		byte[] b = new byte[strict ? 4092 : 1];
		try {
			int n;
			while ( ( n = zip.read ( b ) ) >= 0 ) {
				out.write ( b, 0, n );
			}
			zip.close ();
			out.close ();
			return out.toByteArray ();
		} catch ( Exception e ) {
			if ( strict )
				return null;
			return out.toByteArray ();
		}
	}

	public static byte[] ASCIIHexDecode ( final byte[] in ) {
		ByteArrayOutputStream out = new ByteArrayOutputStream ();
		boolean first = true;
		int n1 = 0;
		for ( byte b : in ) {
			int ch = b & 0xff;
			if ( ch == '>' )
				break;
			if ( PRTokeniser.isWhitespace ( ch ) )
				continue;
			int n = PRTokeniser.getHex ( ch );
			if ( n == -1 )
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "illegal.character.in.asciihexdecode" ) );
			if ( first )
				n1 = n;
			else
				out.write ( (byte) ( ( n1 << 4 ) + n ) );
			first = !first;
		}
		if ( !first )
			out.write ( (byte) ( n1 << 4 ) );
		return out.toByteArray ();
	}

	public static byte[] ASCII85Decode ( final byte[] in ) {
		ByteArrayOutputStream out = new ByteArrayOutputStream ();
		int state = 0;
		int[] chn = new int[5];
		for ( byte b : in ) {
			int ch = b & 0xff;
			if ( ch == '~' )
				break;
			if ( PRTokeniser.isWhitespace ( ch ) )
				continue;
			if ( ch == 'z' && state == 0 ) {
				out.write ( 0 );
				out.write ( 0 );
				out.write ( 0 );
				out.write ( 0 );
				continue;
			}
			if ( ch < '!' || ch > 'u' )
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "illegal.character.in.ascii85decode" ) );
			chn[state] = ch - '!';
			++state;
			if ( state == 5 ) {
				state = 0;
				int r = 0;
				for ( int j = 0; j < 5; ++j )
					r = r * 85 + chn[j];
				out.write ( (byte) ( r >> 24 ) );
				out.write ( (byte) ( r >> 16 ) );
				out.write ( (byte) ( r >> 8 ) );
				out.write ( (byte) r );
			}
		}
		int r;
		if ( state == 2 ) {
			r = chn[0] * 85 * 85 * 85 * 85 + chn[1] * 85 * 85 * 85 + 85 * 85 * 85 + 85 * 85 + 85;
			out.write ( (byte) ( r >> 24 ) );
		} else if ( state == 3 ) {
			r = chn[0] * 85 * 85 * 85 * 85 + chn[1] * 85 * 85 * 85 + chn[2] * 85 * 85 + 85 * 85 + 85;
			out.write ( (byte) ( r >> 24 ) );
			out.write ( (byte) ( r >> 16 ) );
		} else if ( state == 4 ) {
			r = chn[0] * 85 * 85 * 85 * 85 + chn[1] * 85 * 85 * 85 + chn[2] * 85 * 85 + chn[3] * 85 + 85;
			out.write ( (byte) ( r >> 24 ) );
			out.write ( (byte) ( r >> 16 ) );
			out.write ( (byte) ( r >> 8 ) );
		}
		return out.toByteArray ();
	}

	public static byte[] LZWDecode ( final byte[] in ) {
		ByteArrayOutputStream out = new ByteArrayOutputStream ();
		LZWDecoder lzw = new LZWDecoder ();
		lzw.decode ( in, out );
		return out.toByteArray ();
	}

	public static byte[] decodeBytes ( byte[] b, final PdfDictionary streamDictionary ) throws IOException {
		return decodeBytes ( b, streamDictionary, FilterHandlers.getDefaultFilterHandlers () );
	}

	public static byte[] decodeBytes ( byte[] b, final PdfDictionary streamDictionary, Map<PdfName, FilterHandlers.FilterHandler> filterHandlers )
					throws IOException {
		PdfObject filter = getPdfObjectRelease ( streamDictionary.get ( PdfName.FILTER ) );

		ArrayList<PdfObject> filters = new ArrayList<> ();
		if ( filter != null ) {
			if ( filter.isName () )
				filters.add ( filter );
			else if ( filter.isArray () )
				filters = ( (PdfArray) filter ).getArrayList ();
		}
		ArrayList<PdfObject> dp = new ArrayList<> ();
		PdfObject dpo = getPdfObjectRelease ( streamDictionary.get ( PdfName.DECODEPARMS ) );
		if ( dpo == null || !dpo.isDictionary () && !dpo.isArray () )
			dpo = getPdfObjectRelease ( streamDictionary.get ( PdfName.DP ) );
		if ( dpo != null ) {
			if ( dpo.isDictionary () )
				dp.add ( dpo );
			else if ( dpo.isArray () )
				dp = ( (PdfArray) dpo ).getArrayList ();
		}
		for ( int j = 0; j < filters.size (); ++j ) {
			PdfName filterName = (PdfName) filters.get ( j );
			FilterHandlers.FilterHandler filterHandler = filterHandlers.get ( filterName );
			if ( filterHandler == null )
				throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "the.filter.1.is.not.supported", filterName ) );

			PdfDictionary decodeParams;
			if ( j < dp.size () ) {
				PdfObject dpEntry = getPdfObject ( dp.get ( j ) );
				if ( dpEntry instanceof PdfDictionary ) {
					decodeParams = (PdfDictionary) dpEntry;
				} else if ( dpEntry == null || dpEntry instanceof PdfNull ) {
					decodeParams = null;
				} else {
					throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "the.decode.parameter.type.1.is.not.supported",
									dpEntry.getClass ().toString () ) );
				}

			} else {
				decodeParams = null;
			}
			b = filterHandler.decode ( b, filterName, decodeParams, streamDictionary );
		}
		return b;
	}

	public static byte[] getStreamBytes ( final PRStream stream, final RandomAccessFileOrArray file ) throws IOException {
		byte[] b = getStreamBytesRaw ( stream, file );
		return decodeBytes ( b, stream );
	}

	public static byte[] getStreamBytes ( final PRStream stream ) throws IOException {
		RandomAccessFileOrArray rf = stream.getReader ().getSafeFile ();
		try {
			rf.reOpen ();
			return getStreamBytes ( stream, rf );
		} finally {
			try {
				rf.close ();
			} catch ( Exception ignored ) {
			}
		}
	}

	public static byte[] getStreamBytesRaw ( final PRStream stream, final RandomAccessFileOrArray file ) throws IOException {
		PdfReader reader = stream.getReader ();
		byte[] b;
		if ( stream.getOffset () < 0 )
			b = stream.getBytes ();
		else {
			b = new byte[stream.getLength ()];
			file.seek ( stream.getOffset () );
			file.readFully ( b );
			PdfEncryption decrypt = reader.getDecrypt ();
			if ( decrypt != null ) {
				PdfObject filter = getPdfObjectRelease ( stream.get ( PdfName.FILTER ) );
				ArrayList<PdfObject> filters = new ArrayList<> ();
				if ( filter != null ) {
					if ( filter.isName () )
						filters.add ( filter );
					else if ( filter.isArray () )
						filters = ( (PdfArray) filter ).getArrayList ();
				}
				boolean skip = false;
				for ( PdfObject pdfObject : filters ) {
					PdfObject obj = getPdfObjectRelease ( pdfObject );
					if ( obj != null && obj.toString ().equals ( "/Crypt" ) ) {
						skip = true;
						break;
					}
				}
				if ( !skip ) {
					decrypt.setHashKey ( stream.getObjNum (), stream.getObjGen () );
					b = decrypt.decryptByteArray ( b );
				}
			}
		}
		return b;
	}

	public static byte[] getStreamBytesRaw ( final PRStream stream ) throws IOException {
		RandomAccessFileOrArray rf = stream.getReader ().getSafeFile ();
		try {
			rf.reOpen ();
			return getStreamBytesRaw ( stream, rf );
		} finally {
			try {
				rf.close ();
			} catch ( Exception ignored ) {
			}
		}
	}

	static boolean equalsn ( final byte[] a1, final byte[] a2 ) {
		int length = a2.length;
		for ( int k = 0; k < length; ++k ) {
			if ( a1[k] != a2[k] )
				return false;
		}
		return true;
	}

	protected static PdfDictionary duplicatePdfDictionary ( final PdfDictionary original, PdfDictionary copy, final PdfReader newReader ) {
		if ( copy == null )
			copy = new PdfDictionary ();
		for ( PdfName element : original.getKeys () ) {
			copy.put ( element, duplicatePdfObject ( original.get ( element ), newReader ) );
		}
		return copy;
	}

	protected static PdfObject duplicatePdfObject ( final PdfObject original, final PdfReader newReader ) {
		if ( original == null )
			return null;
		switch ( original.type () ) {
		case PdfObject.DICTIONARY: {
			return duplicatePdfDictionary ( (PdfDictionary) original, null, newReader );
		}
		case PdfObject.STREAM: {
			PRStream org = (PRStream) original;
			PRStream stream = new PRStream ( org, null, newReader );
			duplicatePdfDictionary ( org, stream, newReader );
			return stream;
		}
		case PdfObject.ARRAY: {
			PdfArray arr = new PdfArray ();
			for ( PdfObject pdfObject : (PdfArray) original ) {
				arr.add ( duplicatePdfObject ( pdfObject, newReader ) );
			}
			return arr;
		}
		case PdfObject.INDIRECT: {
			PRIndirectReference org = (PRIndirectReference) original;
			return new PRIndirectReference ( newReader, org.getNumber (), org.getGeneration () );
		}
		default:
			return original;
		}
	}

	protected Counter getCounter () {
		return COUNTER;
	}

	public RandomAccessFileOrArray getSafeFile () {
		return tokens.getSafeFile ();
	}

	protected PdfReaderInstance getPdfReaderInstance ( final PdfWriter writer ) {
		return new PdfReaderInstance ( this, writer );
	}

	public int getNumberOfPages () {
		return pageRefs.size ();
	}

	public PdfDictionary getCatalog () {
		return catalog;
	}

	public int getPageRotation ( final int index ) {
		return getPageRotation ( pageRefs.getPageNRelease ( index ) );
	}

	int getPageRotation ( final PdfDictionary page ) {
		PdfNumber rotate = page.getAsNumber ( PdfName.ROTATE );
		if ( rotate == null )
			return 0;
		else {
			int n = rotate.intValue ();
			n %= 360;
			return n < 0 ? n + 360 : n;
		}
	}

	public Rectangle getPageSizeWithRotation ( final PdfDictionary page ) {
		Rectangle rect = getPageSize ( page );
		int rotation = getPageRotation ( page );
		while ( rotation > 0 ) {
			rect = rect.rotate ();
			rotation -= 90;
		}
		return rect;
	}

	public Rectangle getPageSize ( final int index ) {
		return getPageSize ( pageRefs.getPageNRelease ( index ) );
	}

	public Rectangle getPageSize ( final PdfDictionary page ) {
		PdfArray mediaBox = page.getAsArray ( PdfName.MEDIABOX );
		return getNormalizedRectangle ( mediaBox );
	}

	public HashMap<String, String> getInfo () {
		HashMap<String, String> map = new HashMap<> ();
		PdfDictionary info = trailer.getAsDict ( PdfName.INFO );
		if ( info == null )
			return map;
		for ( PdfName element : info.getKeys () ) {
			PdfObject obj = getPdfObject ( info.get ( element ) );
			if ( obj == null )
				continue;
			String value = obj.toString ();
			switch ( obj.type () ) {
			case PdfObject.STRING: {
				value = ( (PdfString) obj ).toUnicodeString ();
				break;
			}
			case PdfObject.NAME: {
				value = PdfName.decodeName ( value );
				break;
			}
			}
			map.put ( PdfName.decodeName ( element.toString () ), value );
		}
		return map;
	}

	protected void readPdf () throws IOException {
		fileLength = tokens.getFile ().length ();
		pdfVersion = tokens.checkPdfHeader ();
		try {
			readXref ();
		} catch ( Exception e ) {
			try {
				rebuilt = true;
				rebuildXref ();
				lastXref = -1;
			} catch ( Exception ne ) {
				throw new InvalidPdfException (
								MessageLocalization.getComposedMessage ( "rebuild.failed.1.original.message.2", ne.getMessage (), e.getMessage () ) );
			}
		}
		try {
			readDocObj ();
		} catch ( Exception e ) {
			if ( e instanceof BadPasswordException )
				throw new BadPasswordException ( e.getMessage () );
			if ( rebuilt || encryptionError )
				throw new InvalidPdfException ( e.getMessage () );
			rebuilt = true;
			encrypted = false;
			try {
				rebuildXref ();
				lastXref = -1;
				readDocObj ();
			} catch ( Exception ne ) {
				throw new InvalidPdfException (
								MessageLocalization.getComposedMessage ( "rebuild.failed.1.original.message.2", ne.getMessage (), e.getMessage () ) );
			}
		}
		strings.clear ();
		readPages ();
		removeUnusedObjects ();

	}

	protected void readPdfPartial () throws IOException {
		fileLength = tokens.getFile ().length ();
		pdfVersion = tokens.checkPdfHeader ();
		try {
			readXref ();
		} catch ( Exception e ) {
			try {
				rebuilt = true;
				rebuildXref ();
				lastXref = -1;
			} catch ( Exception ne ) {
				throw new InvalidPdfException (
								MessageLocalization.getComposedMessage (
												"rebuild.failed.1.original.message.2",
												ne.getMessage (), e.getMessage () ), ne );
			}
		}
		readDocObjPartial ();
		readPages ();
	}

	private boolean equalsArray ( final byte[] ar1, final byte[] ar2, final int size ) {
		for ( int k = 0; k < size; ++k ) {
			if ( ar1[k] != ar2[k] )
				return true;
		}
		return false;
	}

	private void readDecryptedDocObj () throws IOException {
		if ( encrypted )
			return;
		PdfObject encDic = trailer.get ( PdfName.ENCRYPT );
		if ( encDic == null || encDic.toString ().equals ( "null" ) )
			return;
		encryptionError = true;
		byte[] encryptionKey = null;
		encrypted = true;
		PdfDictionary enc = (PdfDictionary) getPdfObject ( encDic );

		String s;
		PdfObject o;

		PdfArray documentIDs = trailer.getAsArray ( PdfName.ID );
		byte[] documentID = null;
		if ( documentIDs != null ) {
			o = documentIDs.getPdfObject ( 0 );
			strings.remove ( o );
			s = o.toString ();
			documentID = it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter.getISOBytes ( s );
			if ( documentIDs.size () > 1 )
				strings.remove ( documentIDs.getPdfObject ( 1 ) );
		}
		if ( documentID == null )
			documentID = new byte[0];
		byte[] uValue = null;
		byte[] oValue = null;
		int cryptoMode = PdfWriter.STANDARD_ENCRYPTION_40;
		int lengthValue = 0;

		PdfObject filter = getPdfObjectRelease ( enc.get ( PdfName.FILTER ) );

		if ( filter.equals ( PdfName.STANDARD ) ) {
			s = enc.get ( PdfName.U ).toString ();
			strings.remove ( enc.get ( PdfName.U ) );
			uValue = it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter.getISOBytes ( s );
			s = enc.get ( PdfName.O ).toString ();
			strings.remove ( enc.get ( PdfName.O ) );
			oValue = it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocWriter.getISOBytes ( s );
			if ( enc.contains ( PdfName.OE ) )
				strings.remove ( enc.get ( PdfName.OE ) );
			if ( enc.contains ( PdfName.UE ) )
				strings.remove ( enc.get ( PdfName.UE ) );
			if ( enc.contains ( PdfName.PERMS ) )
				strings.remove ( enc.get ( PdfName.PERMS ) );

			o = enc.get ( PdfName.P );
			if ( !o.isNumber () )
				throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.p.value" ) );
			pValue = ( (PdfNumber) o ).intValue ();

			o = enc.get ( PdfName.R );
			if ( !o.isNumber () )
				throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.r.value" ) );
			rValue = ( (PdfNumber) o ).intValue ();

			switch ( rValue ) {
			case 2:
				break;
			case 3:
				o = enc.get ( PdfName.LENGTH );
				if ( !o.isNumber () )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.length.value" ) );
				lengthValue = ( (PdfNumber) o ).intValue ();
				if ( lengthValue > 128 || lengthValue < 40 || lengthValue % 8 != 0 )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.length.value" ) );
				cryptoMode = PdfWriter.STANDARD_ENCRYPTION_128;
				break;
			case 4:
				PdfDictionary dic = (PdfDictionary) enc.get ( PdfName.CF );
				if ( dic == null )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "cf.not.found.encryption" ) );
				dic = (PdfDictionary) dic.get ( PdfName.STDCF );
				if ( dic == null )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "stdcf.not.found.encryption" ) );
				if ( PdfName.V2.equals ( dic.get ( PdfName.CFM ) ) )
					cryptoMode = PdfWriter.STANDARD_ENCRYPTION_128;
				else if ( PdfName.AESV2.equals ( dic.get ( PdfName.CFM ) ) )
					cryptoMode = PdfWriter.ENCRYPTION_AES_128;
				else
					throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "no.compatible.encryption.found" ) );
				PdfObject em = enc.get ( PdfName.ENCRYPTMETADATA );
				if ( em != null && em.toString ().equals ( "false" ) )
					cryptoMode |= PdfWriter.DO_NOT_ENCRYPT_METADATA;
				break;
			case 5:
				cryptoMode = PdfWriter.ENCRYPTION_AES_256;
				PdfObject em5 = enc.get ( PdfName.ENCRYPTMETADATA );
				if ( em5 != null && em5.toString ().equals ( "false" ) )
					cryptoMode |= PdfWriter.DO_NOT_ENCRYPT_METADATA;
				break;
			default:
				throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "unknown.encryption.type.r.eq.1", rValue ) );
			}
		} else if ( filter.equals ( PdfName.PUBSEC ) ) {
			PdfArray recipients;

			o = enc.get ( PdfName.V );
			if ( !o.isNumber () )
				throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.v.value" ) );
			int vValue = ( (PdfNumber) o ).intValue ();
			switch ( vValue ) {
			case 1:
				recipients = (PdfArray) enc.get ( PdfName.RECIPIENTS );
				break;
			case 2:
				o = enc.get ( PdfName.LENGTH );
				if ( !o.isNumber () )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.length.value" ) );
				lengthValue = ( (PdfNumber) o ).intValue ();
				if ( lengthValue > 128 || lengthValue < 40 || lengthValue % 8 != 0 )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "illegal.length.value" ) );
				recipients = (PdfArray) enc.get ( PdfName.RECIPIENTS );
				break;
			case 4:
			case 5:
				PdfDictionary dic = (PdfDictionary) enc.get ( PdfName.CF );
				if ( dic == null )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "cf.not.found.encryption" ) );
				dic = (PdfDictionary) dic.get ( PdfName.DEFAULTCRYPTFILTER );
				if ( dic == null )
					throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "defaultcryptfilter.not.found.encryption" ) );
				if ( PdfName.V2.equals ( dic.get ( PdfName.CFM ) ) ) {
				} else if ( PdfName.AESV2.equals ( dic.get ( PdfName.CFM ) ) ) {
				} else if ( PdfName.AESV3.equals ( dic.get ( PdfName.CFM ) ) ) {
				} else
					throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "no.compatible.encryption.found" ) );
				PdfObject em = dic.get ( PdfName.ENCRYPTMETADATA );
				if ( em != null && em.toString ().equals ( "false" ) ) {
				}

				recipients = (PdfArray) dic.get ( PdfName.RECIPIENTS );
				break;
			default:
				throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "unknown.encryption.type.v.eq.1", vValue ) );
			}
			//SC			X509CertificateHolder certHolder;
			//			try {
			//				certHolder = new X509CertificateHolder ( certificate.getEncoded () );
			//			} catch ( Exception f ) {
			//				throw new ExceptionConverter ( f );
			//			}
			if ( externalDecryptionProcess == null ) {
				for ( int i = 0; i < recipients.size (); i++ ) {
					PdfObject recipient = recipients.getPdfObject ( i );
					strings.remove ( recipient );

					//SC					CMSEnvelopedData data = null;
					//					try {
					//						data = new CMSEnvelopedData ( recipient.getBytes () );
					//
					//						Iterator<RecipientInformation> recipientCertificatesIt = data.getRecipientInfos ().getRecipients ().iterator ();
					//
					//						while ( recipientCertificatesIt.hasNext () ) {
					//							RecipientInformation recipientInfo = recipientCertificatesIt.next ();
					//
					//							if ( recipientInfo.getRID ().match ( certHolder ) && !foundRecipient ) {
					//								envelopedData = PdfEncryptor.getContent ( recipientInfo, (PrivateKey) certificateKey, certificateKeyProvider );
					//								foundRecipient = true;
					//							}
					//						}
					//
					//					} catch ( Exception f ) {
					//						throw new ExceptionConverter ( f );
					//					}
				}
			} else {
				for ( int i = 0; i < recipients.size (); i++ ) {
					PdfObject recipient = recipients.getPdfObject ( i );
					strings.remove ( recipient );

					//SC					CMSEnvelopedData data = null;
					//					try {
					//						data = new CMSEnvelopedData ( recipient.getBytes () );
					//
					//						RecipientInformation recipientInfo =
					//										data.getRecipientInfos ().get ( externalDecryptionProcess.getCmsRecipientId () );
					//
					//						if ( recipientInfo != null ) {
					//							envelopedData =
					//											recipientInfo.getContent ( externalDecryptionProcess.getCmsRecipient () );
					//							foundRecipient = true;
					//						}
					//					} catch ( Exception f ) {
					//						throw new ExceptionConverter ( f );
					//					}
				}
			}

			throw new UnsupportedPdfException ( MessageLocalization.getComposedMessage ( "bad.certificate.and.key" ) );

		}

		decrypt = new PdfEncryption ();
		decrypt.setCryptoMode ( cryptoMode, lengthValue );

		if ( filter.equals ( PdfName.STANDARD ) ) {
			if ( rValue == 5 ) {
				ownerPasswordUsed = decrypt.readKey ( enc, password );
				pValue = decrypt.getPermissions ();
			} else {
				decrypt.setupByOwnerPassword ( documentID, password, oValue, pValue );
				if ( equalsArray ( uValue, decrypt.userKey, rValue == 3 || rValue == 4 ? 16 : 32 ) ) {
					decrypt.setupByUserPassword ( documentID, password, oValue, pValue );
					if ( equalsArray ( uValue, decrypt.userKey, rValue == 3 || rValue == 4 ? 16 : 32 ) ) {
						throw new BadPasswordException ( MessageLocalization.getComposedMessage ( "bad.user.password" ) );
					}
				} else
					ownerPasswordUsed = true;
			}
		} else if ( filter.equals ( PdfName.PUBSEC ) ) {
			if ( ( cryptoMode & PdfWriter.ENCRYPTION_MASK ) == PdfWriter.ENCRYPTION_AES_256 )
				decrypt.setKey ( encryptionKey );
			else
				decrypt.setupByEncryptionKey ( encryptionKey, lengthValue );
			ownerPasswordUsed = true;
		}

		for ( PdfString str : strings ) {
			str.decrypt ( this );
		}

		if ( encDic.isIndirect () ) {
			cryptoRef = (PRIndirectReference) encDic;
			xrefObj.set ( cryptoRef.getNumber (), null );
		}
		encryptionError = false;
	}

	public PdfObject getPdfObjectRelease ( final int idx ) {
		PdfObject obj = getPdfObject ( idx );
		releaseLastXrefPartial ();
		return obj;
	}

	public PdfObject getPdfObject ( final int idx ) {
		try {
			lastXrefPartial = -1;
			if ( idx < 0 || idx >= xrefObj.size () )
				return null;
			PdfObject obj = xrefObj.get ( idx );
			if ( !partial || obj != null )
				return obj;
			if ( idx * 2 >= xref.length )
				return null;
			obj = readSingleObject ( idx );
			lastXrefPartial = -1;
			if ( obj != null )
				lastXrefPartial = idx;
			return obj;
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public void releaseLastXrefPartial () {
		if ( partial && lastXrefPartial != -1 ) {
			xrefObj.set ( lastXrefPartial, null );
			lastXrefPartial = -1;
		}
	}

	protected void readPages () throws IOException {
		catalog = trailer.getAsDict ( PdfName.ROOT );
		if ( catalog == null )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "the.document.has.no.catalog.object" ) );
		rootPages = catalog.getAsDict ( PdfName.PAGES );
		if ( rootPages == null )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "the.document.has.no.page.root" ) );
		pageRefs = new PageRefs ( this );
	}

	protected void readDocObjPartial () throws IOException {
		xrefObj = new ArrayList<> ( xref.length / 2 );
		xrefObj.addAll ( Collections.nCopies ( xref.length / 2, null ) );
		readDecryptedDocObj ();
		if ( objStmToOffset != null ) {
			long[] keys = objStmToOffset.getKeys ();
			for ( long n : keys ) {
				objStmToOffset.put ( n, xref[(int) ( n * 2 )] );
				xref[(int) ( n * 2 )] = -1;
			}
		}
	}

	protected PdfObject readSingleObject ( final int k ) throws IOException {
		strings.clear ();
		int k2 = k * 2;
		long pos = xref[k2];
		if ( pos < 0 )
			return null;
		if ( xref[k2 + 1] > 0 )
			pos = objStmToOffset.get ( xref[k2 + 1] );
		if ( pos == 0 )
			return null;
		tokens.seek ( pos );
		tokens.nextValidToken ();
		if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
			tokens.throwError ( MessageLocalization.getComposedMessage ( "invalid.object.number" ) );
		objNum = tokens.intValue ();
		tokens.nextValidToken ();
		if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
			tokens.throwError ( MessageLocalization.getComposedMessage ( "invalid.generation.number" ) );
		objGen = tokens.intValue ();
		tokens.nextValidToken ();
		if ( !tokens.getStringValue ().equals ( "obj" ) )
			tokens.throwError ( MessageLocalization.getComposedMessage ( "token.obj.expected" ) );
		PdfObject obj;
		try {
			obj = readPRObject ();
			for ( PdfString str : strings ) {
				str.decrypt ( this );
			}
			if ( obj.isStream () ) {
				checkPRStreamLength ( (PRStream) obj );
			}
		} catch ( IOException e ) {
			if ( debugmode ) {
				if ( LOGGER.isLogging ( Level.ERROR ) )
					LOGGER.error ( e.getMessage (), e );
				obj = null;
			} else
				throw e;
		}
		if ( xref[k2 + 1] > 0 ) {
			obj = readOneObjStm ( (PRStream) obj, (int) xref[k2] );
		}
		xrefObj.set ( k, obj );
		return obj;
	}

	protected PdfObject readOneObjStm ( final PRStream stream, int idx ) throws IOException {
		int first = stream.getAsNumber ( PdfName.FIRST ).intValue ();
		byte[] b = getStreamBytes ( stream, tokens.getFile () );
		PRTokeniser saveTokens = tokens;
		tokens = new PRTokeniser ( new RandomAccessFileOrArray ( new RandomAccessSourceFactory ().createSource ( b ) ) );
		try {
			int address = 0;
			boolean ok = true;
			++idx;
			for ( int k = 0; k < idx; ++k ) {
				ok = tokens.nextToken ();
				if ( !ok )
					break;
				if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER ) {
					ok = false;
					break;
				}
				ok = tokens.nextToken ();
				if ( !ok )
					break;
				if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER ) {
					ok = false;
					break;
				}
				address = tokens.intValue () + first;
			}
			if ( !ok )
				throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "error.reading.objstm" ) );
			tokens.seek ( address );
			tokens.nextToken ();
			PdfObject obj;
			if ( tokens.getTokenType () == PRTokeniser.TokenType.NUMBER ) {
				obj = new PdfNumber ( tokens.getStringValue () );
			} else {
				tokens.seek ( address );
				obj = readPRObject ();
			}
			return obj;
		} finally {
			tokens = saveTokens;
		}
	}

	protected void readDocObj () throws IOException {
		ArrayList<PRStream> streams = new ArrayList<> ();
		xrefObj = new ArrayList<> ( xref.length / 2 );
		xrefObj.addAll ( Collections.nCopies ( xref.length / 2, null ) );
		for ( int k = 2; k < xref.length; k += 2 ) {
			long pos = xref[k];
			if ( pos <= 0 || xref[k + 1] > 0 )
				continue;
			tokens.seek ( pos );
			tokens.nextValidToken ();
			if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "invalid.object.number" ) );
			objNum = tokens.intValue ();
			tokens.nextValidToken ();
			if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "invalid.generation.number" ) );
			objGen = tokens.intValue ();
			tokens.nextValidToken ();
			if ( !tokens.getStringValue ().equals ( "obj" ) )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "token.obj.expected" ) );
			PdfObject obj;
			try {
				obj = readPRObject ();
				if ( obj.isStream () ) {
					streams.add ( (PRStream) obj );
				}
			} catch ( IOException e ) {
				if ( debugmode ) {
					if ( LOGGER.isLogging ( Level.ERROR ) )
						LOGGER.error ( e.getMessage (), e );
					obj = null;
				} else
					throw e;
			}
			xrefObj.set ( k / 2, obj );
		}
		for ( PRStream stream : streams ) {
			checkPRStreamLength ( stream );
		}
		readDecryptedDocObj ();
		if ( objStmMark != null ) {
			for ( Map.Entry<Integer, IntHashtable> entry : objStmMark.entrySet () ) {
				int n = entry.getKey ();
				IntHashtable h = entry.getValue ();
				readObjStm ( (PRStream) xrefObj.get ( n ), h );
				xrefObj.set ( n, null );
			}
			objStmMark = null;
		}
		xref = null;
	}

	private void checkPRStreamLength ( final PRStream stream ) throws IOException {
		long fileLength = tokens.length ();
		long start = stream.getOffset ();
		boolean calc = false;
		long streamLength = 0;
		PdfObject obj = getPdfObjectRelease ( stream.get ( PdfName.LENGTH ) );
		if ( obj != null && obj.type () == PdfObject.NUMBER ) {
			streamLength = ( (PdfNumber) obj ).intValue ();
			if ( streamLength + start > fileLength - 20 )
				calc = true;
			else {
				tokens.seek ( start + streamLength );
				String line = tokens.readString ( 20 );
				if ( !line.startsWith ( "\nendstream" ) &&
								!line.startsWith ( "\r\nendstream" ) &&
								!line.startsWith ( "\rendstream" ) &&
								!line.startsWith ( "endstream" ) )
					calc = true;
			}
		} else
			calc = true;
		if ( calc ) {
			byte[] tline = new byte[16];
			tokens.seek ( start );
			long pos;
			while ( true ) {
				pos = tokens.getFilePointer ();
				if ( tokens.readLineSegment ( tline ) )
					break;
				if ( equalsn ( tline, endstream ) ) {
					streamLength = pos - start;
					break;
				}
				if ( equalsn ( tline, endobj ) ) {
					tokens.seek ( pos - 16 );
					String s = tokens.readString ( 16 );
					int index = s.indexOf ( "endstream" );
					if ( index >= 0 )
						pos = pos - 16 + index;
					streamLength = pos - start;
					break;
				}
			}
			tokens.seek ( pos - 2 );
			if ( tokens.read () == 13 )
				streamLength--;
			tokens.seek ( pos - 1 );
			if ( tokens.read () == 10 )
				streamLength--;
		}
		stream.setLength ( (int) streamLength );
	}

	protected void readObjStm ( final PRStream stream, final IntHashtable map ) throws IOException {
		int first = stream.getAsNumber ( PdfName.FIRST ).intValue ();
		int n = stream.getAsNumber ( PdfName.N ).intValue ();
		byte[] b = getStreamBytes ( stream, tokens.getFile () );
		PRTokeniser saveTokens = tokens;
		tokens = new PRTokeniser ( new RandomAccessFileOrArray ( new RandomAccessSourceFactory ().createSource ( b ) ) );
		try {
			int[] address = new int[n];
			int[] objNumber = new int[n];
			boolean ok = true;
			for ( int k = 0; k < n; ++k ) {
				ok = tokens.nextToken ();
				if ( !ok )
					break;
				if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER ) {
					ok = false;
					break;
				}
				objNumber[k] = tokens.intValue ();
				ok = tokens.nextToken ();
				if ( !ok )
					break;
				if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER ) {
					ok = false;
					break;
				}
				address[k] = tokens.intValue () + first;
			}
			if ( !ok )
				throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "error.reading.objstm" ) );
			for ( int k = 0; k < n; ++k ) {
				if ( map.containsKey ( k ) ) {
					tokens.seek ( address[k] );
					tokens.nextToken ();
					PdfObject obj;
					if ( tokens.getTokenType () == PRTokeniser.TokenType.NUMBER ) {
						obj = new PdfNumber ( tokens.getStringValue () );
					} else {
						tokens.seek ( address[k] );
						obj = readPRObject ();
					}
					xrefObj.set ( objNumber[k], obj );
				}
			}
		} finally {
			tokens = saveTokens;
		}
	}

	private void ensureXrefSize ( final int size ) {
		if ( size == 0 )
			return;
		if ( xref == null )
			xref = new long[size];
		else {
			if ( xref.length < size ) {
				long[] xref2 = new long[size];
				System.arraycopy ( xref, 0, xref2, 0, xref.length );
				xref = xref2;
			}
		}
	}

	protected void readXref () throws IOException {
		hybridXref = false;
		newXrefType = false;
		tokens.seek ( tokens.getStartxref () );
		tokens.nextToken ();
		if ( !tokens.getStringValue ().equals ( "startxref" ) )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "startxref.not.found" ) );
		tokens.nextToken ();
		if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "startxref.is.not.followed.by.a.number" ) );
		long startxref = tokens.longValue ();
		lastXref = startxref;
		eofPos = tokens.getFilePointer ();
		try {
			if ( readXRefStream ( startxref ) ) {
				newXrefType = true;
				return;
			}
		} catch ( Exception ignored ) {
		}
		xref = null;
		tokens.seek ( startxref );
		trailer = readXrefSection ();
		PdfDictionary trailer2 = trailer;
		while ( true ) {
			PdfNumber prev = (PdfNumber) trailer2.get ( PdfName.PREV );
			if ( prev == null )
				break;
			tokens.seek ( prev.longValue () );
			trailer2 = readXrefSection ();
		}
	}

	protected PdfDictionary readXrefSection () throws IOException {
		tokens.nextValidToken ();
		if ( !tokens.getStringValue ().equals ( "xref" ) )
			tokens.throwError ( MessageLocalization.getComposedMessage ( "xref.subsection.not.found" ) );
		int start;
		int end;
		long pos;
		int gen;
		while ( true ) {
			tokens.nextValidToken ();
			if ( tokens.getStringValue ().equals ( "trailer" ) )
				break;
			if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "object.number.of.the.first.object.in.this.xref.subsection.not.found" ) );
			start = tokens.intValue ();
			tokens.nextValidToken ();
			if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "number.of.entries.in.this.xref.subsection.not.found" ) );
			end = tokens.intValue () + start;
			if ( start == 1 ) {
				long back = tokens.getFilePointer ();
				tokens.nextValidToken ();
				pos = tokens.longValue ();
				tokens.nextValidToken ();
				gen = tokens.intValue ();
				if ( pos == 0 && gen == PdfWriter.GENERATION_MAX ) {
					--start;
					--end;
				}
				tokens.seek ( back );
			}
			ensureXrefSize ( end * 2 );
			for ( int k = start; k < end; ++k ) {
				tokens.nextValidToken ();
				pos = tokens.longValue ();
				tokens.nextValidToken ();
				tokens.intValue ();
				tokens.nextValidToken ();
				int p = k * 2;
				if ( tokens.getStringValue ().equals ( "n" ) ) {
					if ( xref[p] == 0 && xref[p + 1] == 0 ) {
						xref[p] = pos;
					}
				} else if ( tokens.getStringValue ().equals ( "f" ) ) {
					if ( xref[p] == 0 && xref[p + 1] == 0 )
						xref[p] = -1;
				} else
					tokens.throwError ( MessageLocalization.getComposedMessage ( "invalid.cross.reference.entry.in.this.xref.subsection" ) );
			}
		}
		PdfDictionary trailer = (PdfDictionary) readPRObject ();
		PdfNumber xrefSize = (PdfNumber) trailer.get ( PdfName.SIZE );
		ensureXrefSize ( xrefSize.intValue () * 2 );
		PdfObject xrs = trailer.get ( PdfName.XREFSTM );
		if ( xrs != null && xrs.isNumber () ) {
			int loc = ( (PdfNumber) xrs ).intValue ();
			try {
				readXRefStream ( loc );
				newXrefType = true;
				hybridXref = true;
			} catch ( IOException e ) {
				xref = null;
				throw e;
			}
		}
		return trailer;
	}

	protected boolean readXRefStream ( final long ptr ) throws IOException {
		tokens.seek ( ptr );
		int thisStream = 0;
		if ( !tokens.nextToken () )
			return false;
		if ( tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
			return false;
		thisStream = tokens.intValue ();
		if ( !tokens.nextToken () || tokens.getTokenType () != PRTokeniser.TokenType.NUMBER )
			return false;
		if ( !tokens.nextToken () || !tokens.getStringValue ().equals ( "obj" ) )
			return false;
		PdfObject object = readPRObject ();
		PRStream stm = null;
		if ( object.isStream () ) {
			stm = (PRStream) object;
			if ( !PdfName.XREF.equals ( stm.get ( PdfName.TYPE ) ) )
				return false;
		} else
			return false;
		if ( trailer == null ) {
			trailer = new PdfDictionary ();
			trailer.putAll ( stm );
		}
		stm.setLength ( ( (PdfNumber) stm.get ( PdfName.LENGTH ) ).intValue () );
		int size = ( (PdfNumber) stm.get ( PdfName.SIZE ) ).intValue ();
		PdfArray index;
		PdfObject obj = stm.get ( PdfName.INDEX );
		if ( obj == null ) {
			index = new PdfArray ();
			index.add ( new int[] { 0, size } );
		} else
			index = (PdfArray) obj;
		PdfArray w = (PdfArray) stm.get ( PdfName.W );
		long prev = -1;
		obj = stm.get ( PdfName.PREV );
		if ( obj != null )
			prev = ( (PdfNumber) obj ).longValue ();
		ensureXrefSize ( size * 2 );
		if ( objStmMark == null && !partial )
			objStmMark = new HashMap<> ();
		if ( objStmToOffset == null && partial )
			objStmToOffset = new LongHashtable ();
		byte[] b = getStreamBytes ( stm, tokens.getFile () );
		int bptr = 0;
		int[] wc = new int[3];
		for ( int k = 0; k < 3; ++k )
			wc[k] = w.getAsNumber ( k ).intValue ();
		for ( int idx = 0; idx < index.size (); idx += 2 ) {
			int start = index.getAsNumber ( idx ).intValue ();
			int length = index.getAsNumber ( idx + 1 ).intValue ();
			ensureXrefSize ( ( start + length ) * 2 );
			while ( length-- > 0 ) {
				int type = 1;
				if ( wc[0] > 0 ) {
					type = 0;
					for ( int k = 0; k < wc[0]; ++k )
						type = ( type << 8 ) + ( b[bptr++] & 0xff );
				}
				long field2 = 0;
				for ( int k = 0; k < wc[1]; ++k )
					field2 = ( field2 << 8 ) + ( b[bptr++] & 0xff );
				int field3 = 0;
				for ( int k = 0; k < wc[2]; ++k )
					field3 = ( field3 << 8 ) + ( b[bptr++] & 0xff );
				int base = start * 2;
				if ( xref[base] == 0 && xref[base + 1] == 0 ) {
					switch ( type ) {
					case 0:
						xref[base] = -1;
						break;
					case 1:
						xref[base] = field2;
						break;
					case 2:
						xref[base] = field3;
						xref[base + 1] = field2;
						if ( partial ) {
							objStmToOffset.put ( field2, 0 );
						} else {
							Integer on = (int) field2;
							IntHashtable seq = objStmMark.get ( on );
							if ( seq == null ) {
								seq = new IntHashtable ();
								seq.put ( field3, 1 );
								objStmMark.put ( on, seq );
							} else
								seq.put ( field3, 1 );
						}
						break;
					}
				}
				++start;
			}
		}
		thisStream *= 2;
		if ( thisStream + 1 < xref.length && xref[thisStream] == 0 && xref[thisStream + 1] == 0 )
			xref[thisStream] = -1;

		if ( prev == -1 )
			return true;
		return readXRefStream ( prev );
	}

	protected void rebuildXref () throws IOException {
		hybridXref = false;
		newXrefType = false;
		tokens.seek ( 0 );
		long[][] xr = new long[1024][];
		long top = 0;
		trailer = null;
		byte[] line = new byte[64];
		for ( ; ; ) {
			long pos = tokens.getFilePointer ();
			if ( tokens.readLineSegment ( line ) )
				break;
			if ( line[0] == 't' ) {
				if ( !PdfEncodings.convertToString ( line, null ).startsWith ( "trailer" ) )
					continue;
				tokens.seek ( pos );
				tokens.nextToken ();
				pos = tokens.getFilePointer ();
				try {
					PdfDictionary dic = (PdfDictionary) readPRObject ();
					if ( dic.get ( PdfName.ROOT ) != null )
						trailer = dic;
					else
						tokens.seek ( pos );
				} catch ( Exception e ) {
					tokens.seek ( pos );
				}
			} else if ( line[0] >= '0' && line[0] <= '9' ) {
				long[] obj = PRTokeniser.checkObjectStart ( line );
				if ( obj == null )
					continue;
				long num = obj[0];
				long gen = obj[1];
				if ( num >= xr.length ) {
					long newLength = num * 2;
					long[][] xr2 = new long[(int) newLength][];
					System.arraycopy ( xr, 0, xr2, 0, (int) top );
					xr = xr2;
				}
				if ( num >= top )
					top = num + 1;
				if ( xr[(int) num] == null || gen >= xr[(int) num][1] ) {
					obj[0] = pos;
					xr[(int) num] = obj;
				}
			}
		}
		if ( trailer == null )
			throw new InvalidPdfException ( MessageLocalization.getComposedMessage ( "trailer.not.found" ) );
		xref = new long[(int) ( top * 2 )];
		for ( int k = 0; k < top; ++k ) {
			long[] obj = xr[k];
			if ( obj != null )
				xref[k * 2] = obj[0];
		}
	}

	protected PdfDictionary readDictionary () throws IOException {
		PdfDictionary dic = new PdfDictionary ();
		while ( true ) {
			tokens.nextValidToken ();
			if ( tokens.getTokenType () == PRTokeniser.TokenType.END_DIC )
				break;
			if ( tokens.getTokenType () != PRTokeniser.TokenType.NAME )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "dictionary.key.1.is.not.a.name", tokens.getStringValue () ) );
			PdfName name = new PdfName ( tokens.getStringValue (), false );
			PdfObject obj = readPRObject ();
			int type = obj.type ();
			if ( -type == PRTokeniser.TokenType.END_DIC.ordinal () )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "unexpected.gt.gt" ) );
			if ( -type == PRTokeniser.TokenType.END_ARRAY.ordinal () )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "unexpected.close.bracket" ) );
			dic.put ( name, obj );
		}
		return dic;
	}

	protected PdfArray readArray () throws IOException {
		PdfArray array = new PdfArray ();
		while ( true ) {
			PdfObject obj = readPRObject ();
			int type = obj.type ();
			if ( -type == PRTokeniser.TokenType.END_ARRAY.ordinal () )
				break;
			if ( -type == PRTokeniser.TokenType.END_DIC.ordinal () )
				tokens.throwError ( MessageLocalization.getComposedMessage ( "unexpected.gt.gt" ) );
			array.add ( obj );
		}
		return array;
	}

	protected PdfObject readPRObject () throws IOException {
		tokens.nextValidToken ();
		PRTokeniser.TokenType type = tokens.getTokenType ();
		switch ( type ) {
		case START_DIC: {
			++readDepth;
			PdfDictionary dic = readDictionary ();
			--readDepth;
			long pos = tokens.getFilePointer ();
			boolean hasNext;
			do {
				hasNext = tokens.nextToken ();
			} while ( hasNext && tokens.getTokenType () == PRTokeniser.TokenType.COMMENT );

			if ( hasNext && tokens.getStringValue ().equals ( "stream" ) ) {
				int ch;
				do {
					ch = tokens.read ();
				} while ( ch == 32 || ch == 9 || ch == 0 || ch == 12 );
				if ( ch != '\n' )
					ch = tokens.read ();
				if ( ch != '\n' )
					tokens.backOnePosition ( ch );
				PRStream stream = new PRStream ( this, tokens.getFilePointer () );
				stream.putAll ( dic );
				// crypto handling
				stream.setObjNum ( objNum, objGen );

				return stream;
			} else {
				tokens.seek ( pos );
				return dic;
			}
		}
		case START_ARRAY: {
			++readDepth;
			PdfArray arr = readArray ();
			--readDepth;
			return arr;
		}
		case NUMBER:
			return new PdfNumber ( tokens.getStringValue () );
		case STRING:
			PdfString str = new PdfString ( tokens.getStringValue (), null ).setHexWriting ( tokens.isHexString () );
			str.setObjNum ( objNum, objGen );
			if ( strings != null )
				strings.add ( str );

			return str;
		case NAME: {
			PdfName cachedName = PdfName.staticNames.get ( tokens.getStringValue () );
			if ( readDepth > 0 && cachedName != null ) {
				return cachedName;
			} else {
				return new PdfName ( tokens.getStringValue (), false );
			}
		}
		case REF:
			int num = tokens.getReference ();
			return new PRIndirectReference ( this, num, tokens.getGeneration () );
		case ENDOFFILE:
			throw new IOException ( MessageLocalization.getComposedMessage ( "unexpected.end.of.file" ) );
		default:
			String sv = tokens.getStringValue ();
			if ( "null".equals ( sv ) ) {
				if ( readDepth == 0 ) {
					return new PdfNull ();
				}
				return PdfNull.PDFNULL;
			} else if ( "true".equals ( sv ) ) {
				if ( readDepth == 0 ) {
					return new PdfBoolean ( true );
				}
				return PdfBoolean.PDFTRUE;
			} else if ( "false".equals ( sv ) ) {
				if ( readDepth == 0 ) {
					return new PdfBoolean ( false );
				}
				return PdfBoolean.PDFFALSE;
			}
			return new PdfLiteral ( -type.ordinal (), tokens.getStringValue () );
		}
	}

	public boolean isRebuilt () {
		return this.rebuilt;
	}

	public PdfDictionary getPageN ( final int pageNum ) {
		PdfDictionary dic = pageRefs.getPageN ( pageNum );
		if ( dic == null )
			return null;
		if ( appendable )
			dic.setIndRef ( pageRefs.getPageOrigRef ( pageNum ) );
		return dic;
	}

	public PdfDictionary getPageNRelease ( final int pageNum ) {
		PdfDictionary dic = getPageN ( pageNum );
		pageRefs.releasePage ( pageNum );
		return dic;
	}

	public PRIndirectReference getPageOrigRef ( final int pageNum ) {
		return pageRefs.getPageOrigRef ( pageNum );
	}

	public byte[] getPageContent ( final int pageNum, final RandomAccessFileOrArray file ) throws IOException {
		PdfDictionary page = getPageNRelease ( pageNum );
		if ( page == null )
			return null;
		PdfObject contents = getPdfObjectRelease ( page.get ( PdfName.CONTENTS ) );
		if ( contents == null )
			return new byte[0];
		ByteArrayOutputStream bout;
		if ( contents.isStream () ) {
			return getStreamBytes ( (PRStream) contents, file );
		} else if ( contents.isArray () ) {
			PdfArray array = (PdfArray) contents;
			bout = new ByteArrayOutputStream ();
			for ( int k = 0; k < array.size (); ++k ) {
				PdfObject item = getPdfObjectRelease ( array.getPdfObject ( k ) );
				if ( item == null || !item.isStream () )
					continue;
				byte[] b = getStreamBytes ( (PRStream) item, file );
				bout.write ( b );
				if ( k != array.size () - 1 )
					bout.write ( '\n' );
			}
			return bout.toByteArray ();
		} else
			return new byte[0];
	}

	protected void killXref ( PdfObject obj ) {
		if ( obj == null )
			return;
		if ( obj instanceof PdfIndirectReference && !obj.isIndirect () )
			return;
		switch ( obj.type () ) {
		case PdfObject.INDIRECT: {
			int xr = ( (PRIndirectReference) obj ).getNumber ();
			obj = xrefObj.get ( xr );
			xrefObj.set ( xr, null );
			freeXref = xr;
			killXref ( obj );
			break;
		}
		case PdfObject.ARRAY: {
			PdfArray t = (PdfArray) obj;
			for ( int i = 0; i < t.size (); ++i )
				killXref ( t.getPdfObject ( i ) );
			break;
		}
		case PdfObject.STREAM:
		case PdfObject.DICTIONARY: {
			PdfDictionary dic = (PdfDictionary) obj;
			for ( PdfName element : dic.getKeys () ) {
				killXref ( dic.get ( element ) );
			}
			break;
		}
		}
	}

	public boolean isTampered () {
		return tampered;
	}

	public void setTampered ( final boolean tampered ) {
		this.tampered = tampered;
		pageRefs.keepPages ();
	}

	public byte[] getMetadata () throws IOException {
		PdfObject obj = getPdfObject ( catalog.get ( PdfName.METADATA ) );
		if ( !( obj instanceof PRStream ) )
			return null;
		RandomAccessFileOrArray rf = getSafeFile ();
		byte[] b;
		try {
			rf.reOpen ();
			b = getStreamBytes ( (PRStream) obj, rf );
		} finally {
			try {
				rf.close ();
			} catch ( Exception ignored ) {
			}
		}
		return b;
	}

	public long getLastXref () {
		return lastXref;
	}

	public int getXrefSize () {
		return xrefObj.size ();
	}

	public char getPdfVersion () {
		return pdfVersion;
	}

	public boolean isEncrypted () {
		return encrypted;
	}

	public int getPermissions () {
		return pValue;
	}

	public PdfDictionary getTrailer () {
		return trailer;
	}

	PdfEncryption getDecrypt () {
		return decrypt;
	}

	public void removeFields () {
		pageRefs.resetReleasePage ();
		for ( int k = 1; k <= pageRefs.size (); ++k ) {
			PdfDictionary page = pageRefs.getPageN ( k );
			PdfArray annots = page.getAsArray ( PdfName.ANNOTS );
			if ( annots == null ) {
				pageRefs.releasePage ( k );
				continue;
			}
			for ( int j = 0; j < annots.size (); ++j ) {
				PdfObject obj = getPdfObjectRelease ( annots.getPdfObject ( j ) );
				if ( obj == null || !obj.isDictionary () )
					continue;
				PdfDictionary annot = (PdfDictionary) obj;
				if ( PdfName.WIDGET.equals ( annot.get ( PdfName.SUBTYPE ) ) )
					annots.remove ( j-- );
			}
			if ( annots.isEmpty () )
				page.remove ( PdfName.ANNOTS );
			else
				pageRefs.releasePage ( k );
		}
		catalog.remove ( PdfName.ACROFORM );
		pageRefs.resetReleasePage ();
	}

	public void close () {
		try {
			tokens.close ();
		} catch ( IOException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	@SuppressWarnings ( "unchecked" )
	protected void removeUnusedNode ( PdfObject obj, final boolean[] hits ) {
		Stack<Object> state = new Stack<> ();
		state.push ( obj );
		while ( !state.empty () ) {
			Object current = state.pop ();
			if ( current == null )
				continue;
			ArrayList<PdfObject> ar = null;
			PdfDictionary dic = null;
			PdfName[] keys = null;
			Object[] objs = null;
			int idx = 0;
			if ( current instanceof PdfObject ) {
				obj = (PdfObject) current;
				switch ( obj.type () ) {
				case PdfObject.DICTIONARY:
				case PdfObject.STREAM:
					dic = (PdfDictionary) obj;
					keys = new PdfName[dic.size ()];
					dic.getKeys ().toArray ( keys );
					break;
				case PdfObject.ARRAY:
					ar = ( (PdfArray) obj ).getArrayList ();
					break;
				case PdfObject.INDIRECT:
					PRIndirectReference ref = (PRIndirectReference) obj;
					int num = ref.getNumber ();
					if ( !hits[num] ) {
						hits[num] = true;
						state.push ( getPdfObjectRelease ( ref ) );
					}
					continue;
				default:
					continue;
				}
			} else {
				objs = (Object[]) current;
				if ( objs[0] instanceof ArrayList ) {
					ar = (ArrayList<PdfObject>) objs[0];
					idx = (Integer) objs[1];
				} else {
					keys = (PdfName[]) objs[0];
					dic = (PdfDictionary) objs[1];
					idx = (Integer) objs[2];
				}
			}
			if ( ar != null ) {
				for ( int k = idx; k < ar.size (); ++k ) {
					PdfObject v = ar.get ( k );
					if ( v.isIndirect () ) {
						int num = ( (PRIndirectReference) v ).getNumber ();
						if ( num >= xrefObj.size () || !partial && xrefObj.get ( num ) == null ) {
							ar.set ( k, PdfNull.PDFNULL );
							continue;
						}
					}
					if ( objs == null )
						state.push ( new Object[] { ar, k + 1 } );
					else {
						objs[1] = k + 1;
						state.push ( objs );
					}
					state.push ( v );
					break;
				}
			} else {
				for ( int k = idx; k < Objects.requireNonNull ( keys ).length; ++k ) {
					PdfName key = keys[k];
					PdfObject v = dic.get ( key );
					if ( v.isIndirect () ) {
						int num = ( (PRIndirectReference) v ).getNumber ();
						if ( num < 0 || num >= xrefObj.size () || !partial && xrefObj.get ( num ) == null ) {
							dic.put ( key, PdfNull.PDFNULL );
							continue;
						}
					}
					if ( objs == null )
						state.push ( new Object[] { keys, dic, k + 1 } );
					else {
						objs[2] = k + 1;
						state.push ( objs );
					}
					state.push ( v );
					break;
				}
			}
		}
	}

	public void removeUnusedObjects () {
		boolean[] hits = new boolean[xrefObj.size ()];
		removeUnusedNode ( trailer, hits );
		if ( partial ) {
			for ( int k = 1; k < hits.length; ++k ) {
				if ( !hits[k] ) {
					xref[k * 2] = -1;
					xref[k * 2 + 1] = 0;
					xrefObj.set ( k, null );
				}
			}
		} else {
			for ( int k = 1; k < hits.length; ++k ) {
				if ( !hits[k] ) {
					xrefObj.set ( k, null );
				}
			}
		}
	}

	public void setViewerPreferences ( final int preferences ) {
		this.viewerPreferences.setViewerPreferences ( preferences );
		setViewerPreferences ( this.viewerPreferences );
	}

	public void addViewerPreference ( final PdfName key, final PdfObject value ) {
		this.viewerPreferences.addViewerPreference ( key, value );
		setViewerPreferences ( this.viewerPreferences );
	}

	public void setViewerPreferences ( final PdfViewerPreferencesImp vp ) {
		vp.addToCatalog ( catalog );
	}

	public boolean isAppendable () {
		return this.appendable;
	}

	public void setAppendable ( final boolean appendable ) {
		this.appendable = appendable;
		if ( appendable )
			getPdfObject ( trailer.get ( PdfName.ROOT ) );
	}

	public boolean isNewXrefType () {
		return newXrefType;
	}

	public boolean isHybridXref () {
		return hybridXref;
	}

	PdfIndirectReference getCryptoRef () {
		if ( cryptoRef == null )
			return null;
		return new PdfIndirectReference ( 0, cryptoRef.getNumber (), cryptoRef.getGeneration () );
	}

	public final boolean isOpenedWithFullPermissions () {
		return encrypted && !ownerPasswordUsed && !unethicalreading;
	}

	static class PageRefs {

		private final PdfReader reader;

		private ArrayList<PRIndirectReference> refsn;

		private int sizep;

		private IntHashtable refsp;

		private int lastPageRead = -1;

		private ArrayList<PdfDictionary> pageInh;

		private boolean keepPages;

		private PageRefs ( final PdfReader reader ) {
			this.reader = reader;
			if ( reader.partial ) {
				refsp = new IntHashtable ();
				PdfNumber npages = (PdfNumber) PdfReader.getPdfObjectRelease ( reader.rootPages.get ( PdfName.COUNT ) );
				sizep = npages.intValue ();
			} else {
				readPages ();
			}
		}

		PageRefs ( final PageRefs other, final PdfReader reader ) {
			this.reader = reader;
			this.sizep = other.sizep;
			if ( other.refsn != null ) {
				refsn = new ArrayList<> ( other.refsn );
				refsn.replaceAll ( original -> (PRIndirectReference) duplicatePdfObject ( original, reader ) );
			} else
				this.refsp = (IntHashtable) other.refsp.clone ();
		}

		int size () {
			if ( refsn != null )
				return refsn.size ();
			else
				return sizep;
		}

		void readPages () {
			if ( refsn != null )
				return;
			refsp = null;
			refsn = new ArrayList<> ();
			pageInh = new ArrayList<> ();
			iteratePages ( (PRIndirectReference) reader.catalog.get ( PdfName.PAGES ) );
			pageInh = null;
			reader.rootPages.put ( PdfName.COUNT, new PdfNumber ( refsn.size () ) );
		}

		public PdfDictionary getPageN ( final int pageNum ) {
			PRIndirectReference ref = getPageOrigRef ( pageNum );
			return (PdfDictionary) PdfReader.getPdfObject ( ref );
		}

		public PdfDictionary getPageNRelease ( final int pageNum ) {
			PdfDictionary page = getPageN ( pageNum );
			releasePage ( pageNum );
			return page;
		}

		public PRIndirectReference getPageOrigRef ( int pageNum ) {
			try {
				--pageNum;
				if ( pageNum < 0 || pageNum >= size () )
					return null;
				if ( refsn != null )
					return refsn.get ( pageNum );
				else {
					int n = refsp.get ( pageNum );
					if ( n == 0 ) {
						PRIndirectReference ref = getSinglePage ( pageNum );
						if ( reader.lastXrefPartial == -1 )
							lastPageRead = -1;
						else
							lastPageRead = pageNum;
						reader.lastXrefPartial = -1;
						refsp.put ( pageNum, ref.getNumber () );
						if ( keepPages )
							lastPageRead = -1;
						return ref;
					} else {
						if ( lastPageRead != pageNum )
							lastPageRead = -1;
						if ( keepPages )
							lastPageRead = -1;
						return new PRIndirectReference ( reader, n );
					}
				}
			} catch ( Exception e ) {
				throw new ExceptionConverter ( e );
			}
		}

		void keepPages () {
			if ( refsp == null || keepPages )
				return;
			keepPages = true;
			refsp.clear ();
		}

		public void releasePage ( int pageNum ) {
			if ( refsp == null )
				return;
			--pageNum;
			if ( pageNum < 0 || pageNum >= size () )
				return;
			if ( pageNum != lastPageRead )
				return;
			lastPageRead = -1;
			reader.lastXrefPartial = refsp.get ( pageNum );
			reader.releaseLastXrefPartial ();
			refsp.remove ( pageNum );
		}

		public void resetReleasePage () {
			if ( refsp == null )
				return;
			lastPageRead = -1;
		}

		private void pushPageAttributes ( final PdfDictionary nodePages ) {
			PdfDictionary dic = new PdfDictionary ();
			if ( !pageInh.isEmpty () ) {
				dic.putAll ( pageInh.get ( pageInh.size () - 1 ) );
			}
			for ( PdfName pageInhCandidate : pageInhCandidates ) {
				PdfObject obj = nodePages.get ( pageInhCandidate );
				if ( obj != null )
					dic.put ( pageInhCandidate, obj );
			}
			pageInh.add ( dic );
		}

		private void popPageAttributes () {
			pageInh.remove ( pageInh.size () - 1 );
		}

		private void iteratePages ( final PRIndirectReference rpage ) {
			PdfDictionary page = (PdfDictionary) getPdfObject ( rpage );
			if ( page == null )
				return;
			PdfArray kidsPR = page.getAsArray ( PdfName.KIDS );
			if ( kidsPR == null ) {
				page.put ( PdfName.TYPE, PdfName.PAGE );
				PdfDictionary dic = pageInh.get ( pageInh.size () - 1 );
				PdfName key;
				for ( PdfName element : dic.getKeys () ) {
					key = element;
					if ( page.get ( key ) == null )
						page.put ( key, dic.get ( key ) );
				}
				if ( page.get ( PdfName.MEDIABOX ) == null ) {
					PdfArray arr = new PdfArray ( new float[] { 0, 0, PageSize.LETTER.getRight (), PageSize.LETTER.getTop () } );
					page.put ( PdfName.MEDIABOX, arr );
				}
				refsn.add ( rpage );
			} else {
				page.put ( PdfName.TYPE, PdfName.PAGES );
				pushPageAttributes ( page );
				for ( int k = 0; k < kidsPR.size (); ++k ) {
					PdfObject obj = kidsPR.getPdfObject ( k );
					if ( !obj.isIndirect () ) {
						while ( k < kidsPR.size () )
							kidsPR.remove ( k );
						break;
					}
					iteratePages ( (PRIndirectReference) obj );
				}
				popPageAttributes ();
			}
		}

		protected PRIndirectReference getSinglePage ( final int n ) {
			PdfDictionary acc = new PdfDictionary ();
			PdfDictionary top = reader.rootPages;
			int base = 0;
			while ( true ) {
				for ( PdfName pageInhCandidate : pageInhCandidates ) {
					PdfObject obj = top.get ( pageInhCandidate );
					if ( obj != null )
						acc.put ( pageInhCandidate, obj );
				}
				PdfArray kids = (PdfArray) PdfReader.getPdfObjectRelease ( top.get ( PdfName.KIDS ) );
				for ( PdfObject kid : kids ) {
					PRIndirectReference ref = (PRIndirectReference) kid;
					PdfDictionary dic = (PdfDictionary) getPdfObject ( ref );
					int last = reader.lastXrefPartial;
					PdfObject count = getPdfObjectRelease ( dic.get ( PdfName.COUNT ) );
					reader.lastXrefPartial = last;
					int acn = 1;
					if ( count != null && count.type () == PdfObject.NUMBER )
						acn = ( (PdfNumber) count ).intValue ();
					if ( n < base + acn ) {
						if ( count == null ) {
							dic.mergeDifferent ( acc );
							return ref;
						}
						reader.releaseLastXrefPartial ();
						top = dic;
						break;
					}
					reader.releaseLastXrefPartial ();
					base += acn;
				}
			}
		}

	}

}
