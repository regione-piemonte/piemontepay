/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Version;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions.BadPasswordException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Counter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.CounterFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfIsoKeys;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal.PdfViewerPreferencesImp;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp.PdfProperties;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp.XmpBasicProperties;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp.XmpWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMetaFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.SerializeOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;


class PdfStamperImp extends PdfWriter {

	protected RandomAccessFileOrArray file;

	protected boolean closed = false;

	protected AcroFields acroFields;

	protected boolean flat = false;

	protected boolean flatFreeText = false;

	protected int[] namePtr = { 0 };

	protected HashSet<String> partialFlattening = new HashSet<> ();

	protected boolean useVp = false;

	protected PdfViewerPreferencesImp viewerPreferences = new PdfViewerPreferencesImp ();

	protected HashSet<PdfTemplate> fieldTemplates = new HashSet<> ();

	protected boolean fieldsAdded = false;

	protected int sigFlags = 0;

	protected boolean append;

	protected IntHashtable marked;

	protected int initialXrefSize;

	protected PdfAction openAction;

	protected Counter COUNTER = CounterFactory.getCounter ( PdfStamper.class );

	HashMap<PdfReader, IntHashtable> readers2intrefs = new HashMap<> ();

	HashMap<PdfReader, RandomAccessFileOrArray> readers2file = new HashMap<> ();

	PdfReader reader;

	IntHashtable myXref = new IntHashtable ();

	HashMap<PdfDictionary, PageStamp> pagesToContent = new HashMap<> ();

	protected PdfStamperImp ( PdfReader reader, OutputStream os, char pdfVersion, boolean append ) throws DocumentException, IOException {
		super ( new PdfDocument (), os );
		if ( reader.isOpenedWithFullPermissions () )
			throw new BadPasswordException ( MessageLocalization.getComposedMessage ( "pdfreader.not.opened.with.owner.password" ) );
		if ( reader.isTampered () )
			throw new DocumentException ( MessageLocalization.getComposedMessage ( "the.original.document.was.reused.read.it.again.from.file" ) );
		reader.setTampered ( true );
		this.reader = reader;
		file = reader.getSafeFile ();
		this.append = append;
		if ( reader.isEncrypted () && ( append || PdfReader.unethicalreading ) ) {
			crypto = new PdfEncryption ( reader.getDecrypt () );
		}
		if ( append ) {
			if ( reader.isRebuilt () )
				throw new DocumentException (
								MessageLocalization.getComposedMessage ( "append.mode.requires.a.document.without.errors.even.if.recovery.was.possible" ) );
			pdf_version.setAppendmode ( true );
			byte[] buf = new byte[8192];
			int n;
			while ( ( n = file.read ( buf ) ) > 0 )
				this.os.write ( buf, 0, n );
			prevxref = reader.getLastXref ();
			reader.setAppendable ( true );
		} else {
			if ( pdfVersion == 0 )
				super.setPdfVersion ( reader.getPdfVersion () );
			else
				super.setPdfVersion ( pdfVersion );
		}
		super.open ();
		pdf.addWriter ( this );
		if ( append ) {
			body.setRefnum ( reader.getXrefSize () );
			marked = new IntHashtable ();
			if ( reader.isNewXrefType () )
				fullCompression = true;
			if ( reader.isHybridXref () )
				fullCompression = false;
		}
		initialXrefSize = reader.getXrefSize ();
		readColorProfile ();
	}

	protected Counter getCounter () {
		return COUNTER;
	}

	protected void readColorProfile () {
		PdfArray outputIntents = reader.getCatalog ().getAsArray ( PdfName.OUTPUTINTENTS );
		if ( outputIntents != null && !outputIntents.isEmpty () ) {
			PdfStream iccProfileStream = null;
			for ( int i = 0; i < outputIntents.size (); i++ ) {
				PdfDictionary outputIntentDictionary = outputIntents.getAsDict ( i );
				if ( outputIntentDictionary != null ) {
					iccProfileStream = outputIntentDictionary.getAsStream ( PdfName.DESTOUTPUTPROFILE );
					if ( iccProfileStream != null )
						break;
				}
			}

			if ( iccProfileStream instanceof PRStream ) {
				try {
					colorProfile = ICC_Profile.getInstance ( PdfReader.getStreamBytes ( (PRStream) iccProfileStream ) );
				} catch ( IOException exc ) {
					throw new ExceptionConverter ( exc );
				}
			}
		}
	}

	protected void setViewerPreferences () {
		reader.setViewerPreferences ( viewerPreferences );
		markUsed ( reader.getTrailer ().get ( PdfName.ROOT ) );
	}

	protected void close ( Map<String, String> moreInfo ) throws IOException {
		if ( closed )
			return;
		if ( useVp ) {
			setViewerPreferences ();
		}
		if ( flat )
			flatFields ();
		if ( flatFreeText )
			flatFreeTextFields ();
		addFieldResources ();
		PdfDictionary catalog = reader.getCatalog ();
		getPdfVersion ().addToCatalog ( catalog );
		PdfDictionary acroForm = (PdfDictionary) PdfReader.getPdfObject ( catalog.get ( PdfName.ACROFORM ), reader.getCatalog () );
		if ( acroFields != null && acroFields.getXfa ().isChanged () ) {
			markUsed ( acroForm );
			if ( !flat )
				acroFields.getXfa ().setXfa ( this );
		}
		if ( sigFlags != 0 ) {
			if ( acroForm != null ) {
				acroForm.put ( PdfName.SIGFLAGS, new PdfNumber ( sigFlags ) );
				markUsed ( acroForm );
				markUsed ( catalog );
			}
		}
		closed = true;
		addSharedObjectsToBody ();
		setOutlines ();
		setJavaScript ();
		addFileAttachments ();
		// [C11] Output Intents
		if ( extraCatalog != null ) {
			catalog.mergeDifferent ( extraCatalog );
		}
		if ( openAction != null ) {
			catalog.put ( PdfName.OPENACTION, openAction );
		}
		if ( pdf.pageLabels != null ) {
			catalog.put ( PdfName.PAGELABELS, pdf.pageLabels.getDictionary ( this ) );
		}
		// OCG
		if ( !documentOCG.isEmpty () ) {
			fillOCProperties ();
			PdfDictionary ocdict = catalog.getAsDict ( PdfName.OCPROPERTIES );
			if ( ocdict == null ) {
				reader.getCatalog ().put ( PdfName.OCPROPERTIES, OCProperties );
			} else {
				ocdict.put ( PdfName.OCGS, OCProperties.get ( PdfName.OCGS ) );
				PdfDictionary ddict = ocdict.getAsDict ( PdfName.D );
				if ( ddict == null ) {
					ddict = new PdfDictionary ();
					ocdict.put ( PdfName.D, ddict );
				}
				ddict.put ( PdfName.ORDER, OCProperties.getAsDict ( PdfName.D ).get ( PdfName.ORDER ) );
				ddict.put ( PdfName.RBGROUPS, OCProperties.getAsDict ( PdfName.D ).get ( PdfName.RBGROUPS ) );
				ddict.put ( PdfName.OFF, OCProperties.getAsDict ( PdfName.D ).get ( PdfName.OFF ) );
				ddict.put ( PdfName.AS, OCProperties.getAsDict ( PdfName.D ).get ( PdfName.AS ) );
			}
			PdfWriter.checkPdfIsoConformance ( this, PdfIsoKeys.PDFISOKEY_LAYER, OCProperties );
		}
		// metadata
		int skipInfo = -1;
		PdfIndirectReference iInfo = reader.getTrailer ().getAsIndirectObject ( PdfName.INFO );
		if ( iInfo != null ) {
			skipInfo = iInfo.getNumber ();
		}
		PdfDictionary oldInfo = reader.getTrailer ().getAsDict ( PdfName.INFO );
		String producer = null;
		if ( oldInfo != null && oldInfo.get ( PdfName.PRODUCER ) != null ) {
			producer = oldInfo.getAsString ( PdfName.PRODUCER ).toUnicodeString ();
		}
		Version version = Version.getInstance ();
		if ( producer == null || !version.getVersion ().contains ( version.getProduct () ) ) {
			producer = version.getVersion ();
		} else {
			int idx = producer.indexOf ( "; modified using" );
			StringBuilder buf;
			if ( idx == -1 )
				buf = new StringBuilder ( producer );
			else
				buf = new StringBuilder ( producer.substring ( 0, idx ) );
			buf.append ( "; modified using " );
			buf.append ( version.getVersion () );
			producer = buf.toString ();
		}
		PdfIndirectReference info;
		PdfDictionary newInfo = new PdfDictionary ();
		if ( oldInfo != null ) {
			for ( PdfName element : oldInfo.getKeys () ) {
				PdfObject value = PdfReader.getPdfObject ( oldInfo.get ( element ) );
				newInfo.put ( element, value );
			}
		}
		if ( moreInfo != null ) {
			for ( Map.Entry<String, String> entry : moreInfo.entrySet () ) {
				String key = entry.getKey ();
				PdfName keyName = new PdfName ( key );
				String value = entry.getValue ();
				if ( value == null )
					newInfo.remove ( keyName );
				else
					newInfo.put ( keyName, new PdfString ( value, PdfObject.TEXT_UNICODE ) );
			}
		}
		PdfDate date = new PdfDate ();
		newInfo.put ( PdfName.MODDATE, date );
		newInfo.put ( PdfName.PRODUCER, new PdfString ( producer, PdfObject.TEXT_UNICODE ) );
		if ( append ) {
			if ( iInfo == null ) {
				info = addToBody ( newInfo, false ).getIndirectReference ();
			} else {
				info = addToBody ( newInfo, iInfo.getNumber (), false ).getIndirectReference ();
			}
		} else {
			info = addToBody ( newInfo, false ).getIndirectReference ();
		}
		// XMP
		byte[] altMetadata = null;
		PdfObject xmpo = PdfReader.getPdfObject ( catalog.get ( PdfName.METADATA ) );
		if ( xmpo != null && xmpo.isStream () ) {
			altMetadata = PdfReader.getStreamBytesRaw ( (PRStream) xmpo );
			PdfReader.killIndirect ( catalog.get ( PdfName.METADATA ) );
		}
		PdfStream xmp = null;
		if ( xmpMetadata != null ) {
			altMetadata = xmpMetadata;
		} else if ( xmpWriter != null ) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream ();
				PdfProperties.setProducer ( xmpWriter.getXmpMeta (), producer );
				XmpBasicProperties.setModDate ( xmpWriter.getXmpMeta (), date.getW3CDate () );
				XmpBasicProperties.setMetaDataDate ( xmpWriter.getXmpMeta (), date.getW3CDate () );
				xmpWriter.serialize ( baos );
				xmpWriter.close ();
				xmp = new PdfStream ( baos.toByteArray () );
			} catch ( XMPException exc ) {
				xmpWriter = null;
			}
		}
		if ( xmp == null && altMetadata != null ) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream ();
				if ( moreInfo == null || xmpMetadata != null ) {
					XMPMeta xmpMeta = XMPMetaFactory.parseFromBuffer ( altMetadata );

					PdfProperties.setProducer ( xmpMeta, producer );
					XmpBasicProperties.setModDate ( xmpMeta, date.getW3CDate () );
					XmpBasicProperties.setMetaDataDate ( xmpMeta, date.getW3CDate () );

					SerializeOptions serializeOptions = new SerializeOptions ();
					serializeOptions.setPadding ( 2000 );
					XMPMetaFactory.serialize ( xmpMeta, baos, serializeOptions );
				} else {
					XmpWriter xmpw = createXmpWriter ( baos, newInfo );
					xmpw.close ();
				}
				xmp = new PdfStream ( baos.toByteArray () );
			} catch ( XMPException | IOException e ) {
				xmp = new PdfStream ( altMetadata );
			}
		}
		if ( xmp != null ) {
			xmp.put ( PdfName.TYPE, PdfName.METADATA );
			xmp.put ( PdfName.SUBTYPE, PdfName.XML );
			if ( crypto != null && crypto.isMetadataEncrypted () ) {
				PdfArray ar = new PdfArray ();
				ar.add ( PdfName.CRYPT );
				xmp.put ( PdfName.FILTER, ar );
			}
			if ( append && xmpo != null ) {
				body.add ( xmp, xmpo.getIndRef () );
			} else {
				catalog.put ( PdfName.METADATA, body.add ( xmp ).getIndirectReference () );
				markUsed ( catalog );
			}
		}
		close ( info, skipInfo );
	}

	protected void close ( PdfIndirectReference info, int skipInfo ) throws IOException {
		alterContents ();
		int rootN = ( (PRIndirectReference) reader.trailer.get ( PdfName.ROOT ) ).getNumber ();
		if ( append ) {
			int[] keys = marked.getKeys ();
			for ( int j : keys ) {
				PdfObject obj = reader.getPdfObjectRelease ( j );
				if ( obj != null && skipInfo != j && j < initialXrefSize ) {
					addToBody ( obj, obj.getIndRef (), j != rootN );
				}
			}
			for ( int k = initialXrefSize; k < reader.getXrefSize (); ++k ) {
				PdfObject obj = reader.getPdfObject ( k );
				if ( obj != null ) {
					addToBody ( obj, getNewObjectNumber ( reader, k, 0 ) );
				}
			}
		} else {
			for ( int k = 1; k < reader.getXrefSize (); ++k ) {
				PdfObject obj = reader.getPdfObjectRelease ( k );
				if ( obj != null && skipInfo != k ) {
					addToBody ( obj, getNewObjectNumber ( reader, k, 0 ), k != rootN );
				}
			}
		}

		PdfIndirectReference encryption = null;
		PdfObject fileID;
		if ( crypto != null ) {
			if ( append ) {
				encryption = reader.getCryptoRef ();
			} else {
				PdfIndirectObject encryptionObject = addToBody ( crypto.getEncryptionDictionary (), false );
				encryption = encryptionObject.getIndirectReference ();
			}
			fileID = crypto.getFileID ( true );
		} else {
			PdfArray IDs = reader.trailer.getAsArray ( PdfName.ID );
			if ( IDs != null && IDs.getAsString ( 0 ) != null ) {
				fileID = PdfEncryption.createInfoId ( IDs.getAsString ( 0 ).getBytes (), true );
			} else {
				fileID = PdfEncryption.createInfoId ( PdfEncryption.createDocumentId (), true );
			}
		}
		PRIndirectReference iRoot = (PRIndirectReference) reader.trailer.get ( PdfName.ROOT );
		PdfIndirectReference root = new PdfIndirectReference ( 0, getNewObjectNumber ( reader, iRoot.getNumber (), 0 ) );
		// write the cross-reference table of the body
		body.writeCrossReferenceTable ( os, root, info, encryption, fileID, prevxref );
		if ( fullCompression ) {
			writeKeyInfo ( os );
			os.write ( getISOBytes ( "startxref\n" ) );
			os.write ( getISOBytes ( String.valueOf ( body.offset () ) ) );
			os.write ( getISOBytes ( "\n%%EOF\n" ) );
		} else {
			PdfTrailer trailer = new PdfTrailer ( body.size (),
							body.offset (),
							root,
							info,
							encryption,
							fileID, prevxref );
			trailer.toPdf ( this, os );
		}
		os.flush ();
		if ( isCloseStream () )
			os.close ();
		getCounter ().written ( os.getCounter () );
	}

	void applyRotation ( PdfDictionary pageN, ByteBuffer out ) {
		boolean rotateContents = true;
		if ( !rotateContents )
			return;
		Rectangle page = reader.getPageSizeWithRotation ( pageN );
		int rotation = page.getRotation ();
		switch ( rotation ) {
		case 90:
			out.append ( PdfContents.ROTATE90 );
			out.append ( page.getTop () );
			out.append ( ' ' ).append ( '0' ).append ( PdfContents.ROTATEFINAL );
			break;
		case 180:
			out.append ( PdfContents.ROTATE180 );
			out.append ( page.getRight () );
			out.append ( ' ' );
			out.append ( page.getTop () );
			out.append ( PdfContents.ROTATEFINAL );
			break;
		case 270:
			out.append ( PdfContents.ROTATE270 );
			out.append ( '0' ).append ( ' ' );
			out.append ( page.getRight () );
			out.append ( PdfContents.ROTATEFINAL );
			break;
		}
	}

	protected void alterContents () throws IOException {
		for ( PageStamp element : pagesToContent.values () ) {
			PdfDictionary pageN = element.pageN;
			markUsed ( pageN );
			PdfArray ar;
			PdfObject content = PdfReader.getPdfObject ( pageN.get ( PdfName.CONTENTS ), pageN );
			if ( content == null ) {
				ar = new PdfArray ();
				pageN.put ( PdfName.CONTENTS, ar );
			} else if ( content.isArray () ) {
				ar = (PdfArray) content;
				markUsed ( ar );
			} else if ( content.isStream () ) {
				ar = new PdfArray ();
				ar.add ( pageN.get ( PdfName.CONTENTS ) );
				pageN.put ( PdfName.CONTENTS, ar );
			} else {
				ar = new PdfArray ();
				pageN.put ( PdfName.CONTENTS, ar );
			}
			ByteBuffer out = new ByteBuffer ();
			if ( element.under != null ) {
				out.append ( PdfContents.SAVESTATE );
				applyRotation ( pageN, out );
				out.append ( element.under.getInternalBuffer () );
				out.append ( PdfContents.RESTORESTATE );
			}
			if ( element.over != null )
				out.append ( PdfContents.SAVESTATE );
			PdfStream stream = new PdfStream ( out.toByteArray () );
			stream.flateCompress ( compressionLevel );
			ar.addFirst ( addToBody ( stream ).getIndirectReference () );
			out.reset ();
			if ( element.over != null ) {
				out.append ( ' ' );
				out.append ( PdfContents.RESTORESTATE );
				ByteBuffer buf = element.over.getInternalBuffer ();
				out.append ( buf.getBuffer (), 0, element.replacePoint );
				out.append ( PdfContents.SAVESTATE );
				applyRotation ( pageN, out );
				out.append ( buf.getBuffer (), element.replacePoint, buf.size () - element.replacePoint );
				out.append ( PdfContents.RESTORESTATE );
				stream = new PdfStream ( out.toByteArray () );
				stream.flateCompress ( compressionLevel );
				ar.add ( addToBody ( stream ).getIndirectReference () );
			}
			alterResources ( element );
		}
	}

	void alterResources ( PageStamp ps ) {
		ps.pageN.put ( PdfName.RESOURCES, ps.pageResources.getResources () );
	}

	@Override
	protected int getNewObjectNumber ( PdfReader reader, int number, int generation ) {
		IntHashtable ref = readers2intrefs.get ( reader );
		if ( ref != null ) {
			int n = ref.get ( number );
			if ( n == 0 ) {
				n = getIndirectReferenceNumber ();
				ref.put ( number, n );
			}
			return n;
		}
		if ( currentPdfReaderInstance == null ) {
			if ( append && number < initialXrefSize )
				return number;
			int n = myXref.get ( number );
			if ( n == 0 ) {
				n = getIndirectReferenceNumber ();
				myXref.put ( number, n );
			}
			return n;
		} else
			return currentPdfReaderInstance.getNewObjectNumber ( number );
	}

	PageStamp getPageStamp ( int pageNum ) {
		PdfDictionary pageN = reader.getPageN ( pageNum );
		PageStamp ps = pagesToContent.get ( pageN );
		if ( ps == null ) {
			ps = new PageStamp ( this, pageN );
			pagesToContent.put ( pageN, ps );
		}
		return ps;
	}

	PdfContentByte getOverContent ( int pageNum ) {
		if ( pageNum < 1 || pageNum > reader.getNumberOfPages () )
			return null;
		PageStamp ps = getPageStamp ( pageNum );
		if ( ps.over == null )
			ps.over = new StampContent ( this, ps );
		return ps.over;
	}

	boolean isContentWritten () {
		return body.size () > 1;
	}

	void getAcroFields () {
		if ( acroFields == null ) {
			acroFields = new AcroFields ( reader, this );
			try {
				for ( String key : acroFields.getFields ().keySet () ) {
					if ( AcroFields.FIELD_TYPE_TEXT != acroFields.getFieldType ( key ) )
						continue;
					String value = acroFields.getField ( key ).trim ();
					if ( !value.isEmpty () ) {
						acroFields.setField ( key, value, value );
					}
				}
			} catch ( DocumentException | IOException de ) {
				// do nothing
			}
		}
	}

	protected void flatFields () {
		if ( append )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "field.flattening.is.not.supported.in.append.mode" ) );
		getAcroFields ();
		Map<String, AcroFields.Item> fields = acroFields.getFields ();
		if ( fieldsAdded && partialFlattening.isEmpty () ) {
			partialFlattening.addAll ( fields.keySet () );
		}
		PdfDictionary acroForm = reader.getCatalog ().getAsDict ( PdfName.ACROFORM );
		PdfArray acroFds = null;
		if ( acroForm != null ) {
			acroFds = (PdfArray) PdfReader.getPdfObject ( acroForm.get ( PdfName.FIELDS ), acroForm );
		}
		for ( Map.Entry<String, AcroFields.Item> entry : fields.entrySet () ) {
			String name = entry.getKey ();
			if ( !partialFlattening.isEmpty () && !partialFlattening.contains ( name ) )
				continue;
			AcroFields.Item item = entry.getValue ();
			for ( int k = 0; k < item.size (); ++k ) {
				PdfDictionary merged = item.getMerged ( k );
				PdfNumber ff = merged.getAsNumber ( PdfName.F );
				int flags = 0;
				if ( ff != null )
					flags = ff.intValue ();
				int page = item.getPage ( k );
				if ( page < 1 )
					continue;
				PdfDictionary appDic = merged.getAsDict ( PdfName.AP );
				if ( appDic != null && ( flags & PdfFormField.FLAGS_PRINT ) != 0 && ( flags & PdfFormField.FLAGS_HIDDEN ) == 0 ) {
					PdfObject obj = appDic.get ( PdfName.N );
					PdfAppearance app = null;
					if ( obj != null ) {
						PdfObject objReal = PdfReader.getPdfObject ( obj );
						if ( obj instanceof PdfIndirectReference && !obj.isIndirect () )
							app = new PdfAppearance ( (PdfIndirectReference) obj );
						else if ( objReal instanceof PdfStream ) {
							( (PdfDictionary) objReal ).put ( PdfName.SUBTYPE, PdfName.FORM );
							assert obj instanceof PdfIndirectReference;
							app = new PdfAppearance ( (PdfIndirectReference) obj );
						} else {
							if ( objReal != null && objReal.isDictionary () ) {
								PdfName as = merged.getAsName ( PdfName.AS );
								if ( as != null ) {
									PdfIndirectReference iref = (PdfIndirectReference) ( (PdfDictionary) objReal ).get ( as );
									if ( iref != null ) {
										app = new PdfAppearance ( iref );
										if ( iref.isIndirect () ) {
											objReal = PdfReader.getPdfObject ( iref );
											( (PdfDictionary) objReal ).put ( PdfName.SUBTYPE, PdfName.FORM );
										}
									}
								}
							}
						}
					}
					if ( app != null ) {
						Rectangle box = PdfReader.getNormalizedRectangle ( merged.getAsArray ( PdfName.RECT ) );
						PdfContentByte cb = getOverContent ( page );
						cb.setLiteral ( "Q " );
						cb.addTemplate ( app, box.getLeft (), box.getBottom () );
						cb.setLiteral ( "q " );
					}
				}
				if ( partialFlattening.isEmpty () )
					continue;
				PdfDictionary pageDic = reader.getPageN ( page );
				PdfArray annots = pageDic.getAsArray ( PdfName.ANNOTS );
				if ( annots == null )
					continue;
				for ( int idx = 0; idx < annots.size (); ++idx ) {
					PdfObject ran = annots.getPdfObject ( idx );
					if ( !ran.isIndirect () )
						continue;
					PdfObject ran2 = item.getWidgetRef ( k );
					if ( !ran2.isIndirect () )
						continue;
					if ( ( (PRIndirectReference) ran ).getNumber () == ( (PRIndirectReference) ran2 ).getNumber () ) {
						annots.remove ( idx-- );
						PRIndirectReference wdref = (PRIndirectReference) ran2;
						while ( true ) {
							PdfDictionary wd = (PdfDictionary) PdfReader.getPdfObject ( wdref );
							PRIndirectReference parentRef = (PRIndirectReference) wd.get ( PdfName.PARENT );
							PdfReader.killIndirect ( wdref );
							if ( parentRef == null ) { // reached AcroForm
								for ( int fr = 0; fr < Objects.requireNonNull ( acroFds ).size (); ++fr ) {
									PdfObject h = acroFds.getPdfObject ( fr );
									if ( h.isIndirect () && ( (PRIndirectReference) h ).getNumber () == wdref.getNumber () ) {
										acroFds.remove ( fr );
										--fr;
									}
								}
								break;
							}
							PdfDictionary parent = (PdfDictionary) PdfReader.getPdfObject ( parentRef );
							PdfArray kids = parent.getAsArray ( PdfName.KIDS );
							for ( int fr = 0; fr < kids.size (); ++fr ) {
								PdfObject h = kids.getPdfObject ( fr );
								if ( h.isIndirect () && ( (PRIndirectReference) h ).getNumber () == wdref.getNumber () ) {
									kids.remove ( fr );
									--fr;
								}
							}
							if ( !kids.isEmpty () )
								break;
							wdref = parentRef;
						}
					}
				}
				if ( annots.isEmpty () ) {
					PdfReader.killIndirect ( pageDic.get ( PdfName.ANNOTS ) );
					pageDic.remove ( PdfName.ANNOTS );
				}
			}
		}
		if ( !fieldsAdded && partialFlattening.isEmpty () ) {
			for ( int page = 1; page <= reader.getNumberOfPages (); ++page ) {
				PdfDictionary pageDic = reader.getPageN ( page );
				PdfArray annots = pageDic.getAsArray ( PdfName.ANNOTS );
				if ( annots == null )
					continue;
				for ( int idx = 0; idx < annots.size (); ++idx ) {
					PdfObject annoto = annots.getDirectObject ( idx );
					if ( annoto instanceof PdfIndirectReference && !annoto.isIndirect () )
						continue;
					if ( !annoto.isDictionary () || PdfName.WIDGET.equals ( ( (PdfDictionary) annoto ).get ( PdfName.SUBTYPE ) ) ) {
						annots.remove ( idx );
						--idx;
					}
				}
				if ( annots.isEmpty () ) {
					PdfReader.killIndirect ( pageDic.get ( PdfName.ANNOTS ) );
					pageDic.remove ( PdfName.ANNOTS );
				}
			}
			eliminateAcroformObjects ();
		}
	}

	void eliminateAcroformObjects () {
		PdfObject acro = reader.getCatalog ().get ( PdfName.ACROFORM );
		if ( acro == null )
			return;
		PdfDictionary acrodic = (PdfDictionary) PdfReader.getPdfObject ( acro );
		reader.killXref ( acrodic.get ( PdfName.XFA ) );
		acrodic.remove ( PdfName.XFA );
		PdfObject iFields = acrodic.get ( PdfName.FIELDS );
		if ( iFields != null ) {
			PdfDictionary kids = new PdfDictionary ();
			kids.put ( PdfName.KIDS, iFields );
			sweepKids ( kids );
			PdfReader.killIndirect ( iFields );
			acrodic.put ( PdfName.FIELDS, new PdfArray () );
		}
		acrodic.remove ( PdfName.SIGFLAGS );
		acrodic.remove ( PdfName.NEEDAPPEARANCES );
		acrodic.remove ( PdfName.DR );
	}

	void sweepKids ( PdfObject obj ) {
		PdfObject oo = PdfReader.killIndirect ( obj );
		if ( oo == null || !oo.isDictionary () )
			return;
		PdfDictionary dic = (PdfDictionary) oo;
		PdfArray kids = (PdfArray) PdfReader.killIndirect ( dic.get ( PdfName.KIDS ) );
		if ( kids == null )
			return;
		for ( int k = 0; k < kids.size (); ++k ) {
			sweepKids ( kids.getPdfObject ( k ) );
		}
	}

	protected void flatFreeTextFields () {
		if ( append )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "freetext.flattening.is.not.supported.in.append.mode" ) );

		for ( int page = 1; page <= reader.getNumberOfPages (); ++page ) {
			PdfDictionary pageDic = reader.getPageN ( page );
			PdfArray annots = pageDic.getAsArray ( PdfName.ANNOTS );
			if ( annots == null )
				continue;
			for ( int idx = 0; idx < annots.size (); ++idx ) {
				PdfObject annoto = annots.getDirectObject ( idx );
				if ( annoto instanceof PdfIndirectReference && !annoto.isIndirect () )
					continue;

				assert annoto instanceof PdfDictionary;
				PdfDictionary annDic = (PdfDictionary) annoto;
				if ( !annDic.get ( PdfName.SUBTYPE ).equals ( PdfName.FREETEXT ) )
					continue;
				PdfNumber ff = annDic.getAsNumber ( PdfName.F );
				int flags = ff != null ? ff.intValue () : 0;

				if ( ( flags & PdfFormField.FLAGS_PRINT ) != 0 && ( flags & PdfFormField.FLAGS_HIDDEN ) == 0 ) {
					PdfObject obj1 = annDic.get ( PdfName.AP );
					if ( obj1 == null )
						continue;
					PdfDictionary appDic = obj1 instanceof PdfIndirectReference ?
									(PdfDictionary) PdfReader.getPdfObject ( obj1 ) : (PdfDictionary) obj1;
					PdfObject obj = appDic.get ( PdfName.N );
					PdfAppearance app = null;
					PdfObject objReal = PdfReader.getPdfObject ( obj );

					if ( obj instanceof PdfIndirectReference && !obj.isIndirect () )
						app = new PdfAppearance ( (PdfIndirectReference) obj );
					else if ( objReal instanceof PdfStream ) {
						( (PdfDictionary) objReal ).put ( PdfName.SUBTYPE, PdfName.FORM );
						assert obj instanceof PdfIndirectReference;
						app = new PdfAppearance ( (PdfIndirectReference) obj );
					} else {
						if ( objReal.isDictionary () ) {
							PdfName as_p = appDic.getAsName ( PdfName.AS );
							if ( as_p != null ) {
								PdfIndirectReference iref = (PdfIndirectReference) ( (PdfDictionary) objReal ).get ( as_p );
								if ( iref != null ) {
									app = new PdfAppearance ( iref );
									if ( iref.isIndirect () ) {
										objReal = PdfReader.getPdfObject ( iref );
										( (PdfDictionary) objReal ).put ( PdfName.SUBTYPE, PdfName.FORM );
									}
								}
							}
						}
					}
					if ( app != null ) {
						Rectangle box = PdfReader.getNormalizedRectangle ( annDic.getAsArray ( PdfName.RECT ) );
						PdfContentByte cb = getOverContent ( page );
						cb.setLiteral ( "Q " );
						cb.addTemplate ( app, box.getLeft (), box.getBottom () );
						cb.setLiteral ( "q " );
					}
				}
			}
			for ( int idx = 0; idx < annots.size (); ++idx ) {
				PdfDictionary annot = annots.getAsDict ( idx );
				if ( annot != null ) {
					if ( PdfName.FREETEXT.equals ( annot.get ( PdfName.SUBTYPE ) ) ) {
						annots.remove ( idx );
						--idx;
					}
				}
			}
			if ( annots.isEmpty () ) {
				PdfReader.killIndirect ( pageDic.get ( PdfName.ANNOTS ) );
				pageDic.remove ( PdfName.ANNOTS );
			}
		}
	}

	@Override
	public PdfIndirectReference getPageReference ( int page ) {
		PdfIndirectReference ref = reader.getPageOrigRef ( page );
		if ( ref == null )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "invalid.page.number.1", page ) );
		return ref;
	}

	@Override
	public void addAnnotation ( PdfAnnotation annot ) {
		throw new RuntimeException ( MessageLocalization.getComposedMessage ( "unsupported.in.this.context.use.pdfstamper.addannotation" ) );
	}

	void addDocumentField ( PdfIndirectReference ref ) {
		PdfDictionary catalog = reader.getCatalog ();
		PdfDictionary acroForm = (PdfDictionary) PdfReader.getPdfObject ( catalog.get ( PdfName.ACROFORM ), catalog );
		if ( acroForm == null ) {
			acroForm = new PdfDictionary ();
			catalog.put ( PdfName.ACROFORM, acroForm );
			markUsed ( catalog );
		}
		PdfArray fields = (PdfArray) PdfReader.getPdfObject ( acroForm.get ( PdfName.FIELDS ), acroForm );
		if ( fields == null ) {
			fields = new PdfArray ();
			acroForm.put ( PdfName.FIELDS, fields );
			markUsed ( acroForm );
		}
		if ( !acroForm.contains ( PdfName.DA ) ) {
			acroForm.put ( PdfName.DA, new PdfString ( "/Helv 0 Tf 0 g " ) );
			markUsed ( acroForm );
		}
		fields.add ( ref );
		markUsed ( fields );
	}

	protected void addFieldResources () throws IOException {
		if ( fieldTemplates.isEmpty () )
			return;
		PdfDictionary catalog = reader.getCatalog ();
		PdfDictionary acroForm = (PdfDictionary) PdfReader.getPdfObject ( catalog.get ( PdfName.ACROFORM ), catalog );
		if ( acroForm == null ) {
			acroForm = new PdfDictionary ();
			catalog.put ( PdfName.ACROFORM, acroForm );
			markUsed ( catalog );
		}
		PdfDictionary dr = (PdfDictionary) PdfReader.getPdfObject ( acroForm.get ( PdfName.DR ), acroForm );
		if ( dr == null ) {
			dr = new PdfDictionary ();
			acroForm.put ( PdfName.DR, dr );
			markUsed ( acroForm );
		}
		markUsed ( dr );
		for ( PdfTemplate template : fieldTemplates ) {
			PdfFormField.mergeResources ( dr, (PdfDictionary) template.getResources (), this );
		}
		// if (dr.get(PdfName.ENCODING) == null) dr.put(PdfName.ENCODING, PdfName.WIN_ANSI_ENCODING);
		PdfDictionary fonts = dr.getAsDict ( PdfName.FONT );
		if ( fonts == null ) {
			fonts = new PdfDictionary ();
			dr.put ( PdfName.FONT, fonts );
		}
		if ( !fonts.contains ( PdfName.HELV ) ) {
			PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
			dic.put ( PdfName.BASEFONT, PdfName.HELVETICA );
			dic.put ( PdfName.ENCODING, PdfName.WIN_ANSI_ENCODING );
			dic.put ( PdfName.NAME, PdfName.HELV );
			dic.put ( PdfName.SUBTYPE, PdfName.TYPE1 );
			fonts.put ( PdfName.HELV, addToBody ( dic ).getIndirectReference () );
		}
		if ( !fonts.contains ( PdfName.ZADB ) ) {
			PdfDictionary dic = new PdfDictionary ( PdfName.FONT );
			dic.put ( PdfName.BASEFONT, PdfName.ZAPFDINGBATS );
			dic.put ( PdfName.NAME, PdfName.ZADB );
			dic.put ( PdfName.SUBTYPE, PdfName.TYPE1 );
			fonts.put ( PdfName.ZADB, addToBody ( dic ).getIndirectReference () );
		}
		if ( acroForm.get ( PdfName.DA ) == null ) {
			acroForm.put ( PdfName.DA, new PdfString ( "/Helv 0 Tf 0 g " ) );
			markUsed ( acroForm );
		}
	}

	void expandFields ( PdfFormField field, ArrayList<PdfAnnotation> allAnnots ) {
		allAnnots.add ( field );
		ArrayList<PdfFormField> kids = field.getKids ();
		if ( kids != null ) {
			for ( PdfFormField kid : kids )
				expandFields ( kid, allAnnots );
		}
	}

	void addAnnotation ( PdfAnnotation annot, PdfDictionary pageN ) {
		try {
			ArrayList<PdfAnnotation> allAnnots = new ArrayList<> ();
			if ( annot.isForm () ) {
				fieldsAdded = true;
				getAcroFields ();
				PdfFormField field = (PdfFormField) annot;
				if ( field.getParent () != null )
					return;
				expandFields ( field, allAnnots );
			} else
				allAnnots.add ( annot );
			for ( PdfAnnotation allAnnot : allAnnots ) {
				annot = allAnnot;
				if ( annot.getPlaceInPage () > 0 )
					pageN = reader.getPageN ( annot.getPlaceInPage () );
				if ( annot.isForm () ) {
					if ( !annot.isUsed () ) {
						HashSet<PdfTemplate> templates = annot.getTemplates ();
						if ( templates != null )
							fieldTemplates.addAll ( templates );
					}
					PdfFormField field = (PdfFormField) annot;
					if ( field.getParent () == null )
						addDocumentField ( field.getIndirectReference () );
				}
				if ( annot.isAnnotation () ) {
					PdfObject pdfobj = PdfReader.getPdfObject ( pageN.get ( PdfName.ANNOTS ), pageN );
					PdfArray annots;
					if ( pdfobj == null || !pdfobj.isArray () ) {
						annots = new PdfArray ();
						pageN.put ( PdfName.ANNOTS, annots );
						markUsed ( pageN );
					} else
						annots = (PdfArray) pdfobj;
					annots.add ( annot.getIndirectReference () );
					markUsed ( annots );
					if ( !annot.isUsed () ) {
						PdfRectangle rect = (PdfRectangle) annot.get ( PdfName.RECT );
						if ( rect != null && ( rect.left () != 0 || rect.right () != 0 || rect.top () != 0 || rect.bottom () != 0 ) ) {
							int rotation = reader.getPageRotation ( pageN );
							Rectangle pageSize = reader.getPageSizeWithRotation ( pageN );
							switch ( rotation ) {
							case 90:
								annot.put ( PdfName.RECT, new PdfRectangle (
												pageSize.getTop () - rect.top (),
												rect.right (),
												pageSize.getTop () - rect.bottom (),
												rect.left () ) );
								break;
							case 180:
								annot.put ( PdfName.RECT, new PdfRectangle (
												pageSize.getRight () - rect.left (),
												pageSize.getTop () - rect.bottom (),
												pageSize.getRight () - rect.right (),
												pageSize.getTop () - rect.top () ) );
								break;
							case 270:
								annot.put ( PdfName.RECT, new PdfRectangle (
												rect.bottom (),
												pageSize.getRight () - rect.left (),
												rect.top (),
												pageSize.getRight () - rect.right () ) );
								break;
							}
						}
					}
				}
				if ( !annot.isUsed () ) {
					annot.setUsed ();
					addToBody ( annot, annot.getIndirectReference () );
				}
			}
		} catch ( IOException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	@Override
	void addAnnotation ( PdfAnnotation annot, int page ) {
		annot.setPage ( page );
		addAnnotation ( annot, reader.getPageN ( page ) );
	}

	private void outlineTravel ( PRIndirectReference outline ) {
		while ( outline != null ) {
			PdfDictionary outlineR = (PdfDictionary) PdfReader.getPdfObjectRelease ( outline );
			PRIndirectReference first = (PRIndirectReference) outlineR.get ( PdfName.FIRST );
			if ( first != null ) {
				outlineTravel ( first );
			}
			PdfReader.killIndirect ( outlineR.get ( PdfName.DEST ) );
			PdfReader.killIndirect ( outlineR.get ( PdfName.A ) );
			PdfReader.killIndirect ( outline );
			outline = (PRIndirectReference) outlineR.get ( PdfName.NEXT );
		}
	}

	void deleteOutlines () {
		PdfDictionary catalog = reader.getCatalog ();
		PdfObject obj = catalog.get ( PdfName.OUTLINES );
		if ( obj == null )
			return;
		if ( obj instanceof PRIndirectReference ) {
			PRIndirectReference outlines = (PRIndirectReference) obj;
			outlineTravel ( outlines );
			PdfReader.killIndirect ( outlines );
		}
		catalog.remove ( PdfName.OUTLINES );
		markUsed ( catalog );
	}

	protected void setJavaScript () throws IOException {
		HashMap<String, PdfObject> djs = pdf.getDocumentLevelJS ();
		if ( djs.isEmpty () )
			return;
		PdfDictionary catalog = reader.getCatalog ();
		PdfDictionary names = (PdfDictionary) PdfReader.getPdfObject ( catalog.get ( PdfName.NAMES ), catalog );
		if ( names == null ) {
			names = new PdfDictionary ();
			catalog.put ( PdfName.NAMES, names );
			markUsed ( catalog );
		}
		markUsed ( names );
		PdfDictionary tree = PdfNameTree.writeTree ( djs, this );
		names.put ( PdfName.JAVASCRIPT, addToBody ( tree ).getIndirectReference () );
	}

	protected void addFileAttachments () throws IOException {
		HashMap<String, PdfObject> fs = pdf.getDocumentFileAttachment ();
		if ( fs.isEmpty () )
			return;
		PdfDictionary catalog = reader.getCatalog ();
		PdfDictionary names = (PdfDictionary) PdfReader.getPdfObject ( catalog.get ( PdfName.NAMES ), catalog );
		if ( names == null ) {
			names = new PdfDictionary ();
			catalog.put ( PdfName.NAMES, names );
			markUsed ( catalog );
		}
		markUsed ( names );
		HashMap<String, PdfObject> old = PdfNameTree.readTree ( (PdfDictionary) PdfReader.getPdfObjectRelease ( names.get ( PdfName.EMBEDDEDFILES ) ) );
		for ( Map.Entry<String, PdfObject> entry : fs.entrySet () ) {
			String name = entry.getKey ();
			int k = 0;
			StringBuilder nn = new StringBuilder ( name );
			while ( old.containsKey ( nn.toString () ) ) {
				++k;
				nn.append ( " " ).append ( k );
			}
			old.put ( nn.toString (), entry.getValue () );
		}
		PdfDictionary tree = PdfNameTree.writeTree ( old, this );
		// Remove old EmbeddedFiles object if preset
		PdfObject oldEmbeddedFiles = names.get ( PdfName.EMBEDDEDFILES );
		if ( oldEmbeddedFiles != null ) {
			PdfReader.killIndirect ( oldEmbeddedFiles );
		}

		names.put ( PdfName.EMBEDDEDFILES, addToBody ( tree ).getIndirectReference () );
	}

	protected void setOutlines () throws IOException {
		if ( newBookmarks == null )
			return;
		deleteOutlines ();
		if ( newBookmarks.isEmpty () )
			return;
		PdfDictionary catalog = reader.getCatalog ();
		boolean namedAsNames = catalog.get ( PdfName.DESTS ) != null;
		writeOutlines ( catalog, namedAsNames );
		markUsed ( catalog );
	}

	@Override
	public void setViewerPreferences ( int preferences ) {
		useVp = true;
		this.viewerPreferences.setViewerPreferences ( preferences );
	}

	@Override
	public void addViewerPreference ( PdfName key, PdfObject value ) {
		useVp = true;
		this.viewerPreferences.addViewerPreference ( key, value );
	}

	@Override
	public void setDuration ( int seconds ) {
		throw new UnsupportedOperationException ( MessageLocalization.getComposedMessage ( "use.setpageaction.pdfname.actiontype.pdfaction.action.int.page" ) );
	}

	@Override
	public void setTransition ( PdfTransition transition ) {
		throw new UnsupportedOperationException ( MessageLocalization.getComposedMessage ( "use.setpageaction.pdfname.actiontype.pdfaction.action.int.page" ) );
	}

	protected void markUsed ( PdfObject obj ) {
		if ( append && obj != null ) {
			PRIndirectReference ref;
			if ( obj.type () == PdfObject.INDIRECT )
				ref = (PRIndirectReference) obj;
			else
				ref = obj.getIndRef ();
			if ( ref != null )
				marked.put ( ref.getNumber (), 1 );
		}
	}

	protected void markUsed ( int num ) {
		if ( append )
			marked.put ( num, 1 );
	}

	boolean isAppend () {
		return append;
	}

	@Override
	public void setThumbnail ( Image image ) {
		throw new UnsupportedOperationException ( MessageLocalization.getComposedMessage ( "use.pdfstamper.setthumbnail" ) );
	}

	@Override
	public PdfContentByte getDirectContentUnder () {
		throw new UnsupportedOperationException ( MessageLocalization.getComposedMessage ( "use.pdfstamper.getundercontent.or.pdfstamper.getovercontent" ) );
	}

	@Override
	public PdfContentByte getDirectContent () {
		throw new UnsupportedOperationException ( MessageLocalization.getComposedMessage ( "use.pdfstamper.getundercontent.or.pdfstamper.getovercontent" ) );
	}

	static class PageStamp {

		PdfDictionary pageN;

		StampContent under;

		StampContent over;

		PageResources pageResources;

		int replacePoint = 0;

		PageStamp ( PdfStamperImp stamper, PdfDictionary pageN ) {
			this.pageN = pageN;
			pageResources = new PageResources ();
			PdfDictionary resources = pageN.getAsDict ( PdfName.RESOURCES );
			pageResources.setOriginalResources ( resources, stamper.namePtr );
		}
	}
}
