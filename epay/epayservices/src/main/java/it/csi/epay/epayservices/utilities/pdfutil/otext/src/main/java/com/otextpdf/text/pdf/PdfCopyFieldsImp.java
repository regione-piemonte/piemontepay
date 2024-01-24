/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Counter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.CounterFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;


@Deprecated
class PdfCopyFieldsImp extends PdfWriter {

	protected static final HashMap<PdfName, Integer> widgetKeys = new HashMap<> ();

	protected static final HashMap<PdfName, Integer> fieldKeys = new HashMap<> ();

	private static final PdfName oTextTag = new PdfName ( "_oTextTag_" );

	private static final Integer zero = 0;

	static {
		Integer one = 1;
		widgetKeys.put ( PdfName.SUBTYPE, one );
		widgetKeys.put ( PdfName.CONTENTS, one );
		widgetKeys.put ( PdfName.RECT, one );
		widgetKeys.put ( PdfName.NM, one );
		widgetKeys.put ( PdfName.M, one );
		widgetKeys.put ( PdfName.F, one );
		widgetKeys.put ( PdfName.BS, one );
		widgetKeys.put ( PdfName.BORDER, one );
		widgetKeys.put ( PdfName.AP, one );
		widgetKeys.put ( PdfName.AS, one );
		widgetKeys.put ( PdfName.C, one );
		widgetKeys.put ( PdfName.A, one );
		widgetKeys.put ( PdfName.STRUCTPARENT, one );
		widgetKeys.put ( PdfName.OC, one );
		widgetKeys.put ( PdfName.H, one );
		widgetKeys.put ( PdfName.MK, one );
		widgetKeys.put ( PdfName.DA, one );
		widgetKeys.put ( PdfName.Q, one );
		widgetKeys.put ( PdfName.P, one );
		fieldKeys.put ( PdfName.AA, one );
		fieldKeys.put ( PdfName.FT, one );
		fieldKeys.put ( PdfName.TU, one );
		fieldKeys.put ( PdfName.TM, one );
		fieldKeys.put ( PdfName.FF, one );
		fieldKeys.put ( PdfName.V, one );
		fieldKeys.put ( PdfName.DV, one );
		fieldKeys.put ( PdfName.DS, one );
		fieldKeys.put ( PdfName.RV, one );
		fieldKeys.put ( PdfName.OPT, one );
		fieldKeys.put ( PdfName.MAXLEN, one );
		fieldKeys.put ( PdfName.TI, one );
		fieldKeys.put ( PdfName.I, one );
		fieldKeys.put ( PdfName.LOCK, one );
		fieldKeys.put ( PdfName.SV, one );
	}

	private final ArrayList<String> calculationOrder = new ArrayList<> ();

	private final HashSet<Object> mergedRadioButtons = new HashSet<> ();

	protected Counter COUNTER = CounterFactory.getCounter ( PdfCopyFields.class );

	ArrayList<PdfReader> readers = new ArrayList<> ();

	HashMap<PdfReader, IntHashtable> readers2intrefs = new HashMap<> ();

	HashMap<PdfReader, IntHashtable> pages2intrefs = new HashMap<> ();

	HashMap<PdfReader, IntHashtable> visited = new HashMap<> ();

	ArrayList<AcroFields> fields = new ArrayList<> ();

	RandomAccessFileOrArray file;

	HashMap<String, Object> fieldTree = new HashMap<> ();

	ArrayList<PdfIndirectReference> pageRefs = new ArrayList<> ();

	ArrayList<PdfDictionary> pageDics = new ArrayList<> ();

	PdfDictionary resources = new PdfDictionary ();

	PdfDictionary form;

	boolean closing = false;

	Document nd;

	private HashMap<PdfArray, ArrayList<Integer>> tabOrder;

	private ArrayList<Object> calculationOrderRefs;

	private boolean hasSignature;

	PdfCopyFieldsImp ( OutputStream os, char pdfVersion ) throws DocumentException {
		super ( new PdfDocument (), os );
		pdf.addWriter ( this );
		if ( pdfVersion != 0 )
			super.setPdfVersion ( pdfVersion );
		nd = new Document ();
		nd.addDocListener ( pdf );
	}

	protected Counter getCounter () {
		return COUNTER;
	}

	void propagate ( PdfObject obj, boolean restricted ) {
		if ( obj == null )
			return;

		if ( obj instanceof PdfIndirectReference )
			return;
		switch ( obj.type () ) {
		case PdfObject.DICTIONARY:
		case PdfObject.STREAM: {
			PdfDictionary dic = (PdfDictionary) obj;
			for ( PdfName key : dic.getKeys () ) {
				if ( restricted && ( key.equals ( PdfName.PARENT ) || key.equals ( PdfName.KIDS ) ) )
					continue;
				PdfObject ob = dic.get ( key );
				if ( ob != null && ob.isIndirect () ) {
					PRIndirectReference ind = (PRIndirectReference) ob;
					if ( !setVisited ( ind ) && isPage ( ind ) ) {
						getNewReference ( ind );
						propagate ( PdfReader.getPdfObjectRelease ( ind ), restricted );
					}
				} else
					propagate ( ob, restricted );
			}
			break;
		}
		case PdfObject.ARRAY: {
			for ( PdfObject ob : (PdfArray) obj ) {
				if ( ob != null && ob.isIndirect () ) {
					PRIndirectReference ind = (PRIndirectReference) ob;
					if ( !isVisited ( ind ) && isPage ( ind ) ) {
						getNewReference ( ind );
						propagate ( PdfReader.getPdfObjectRelease ( ind ), restricted );
					}
				} else
					propagate ( ob, restricted );
			}
			break;
		}
		case PdfObject.INDIRECT: {
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "reference.pointing.to.reference" ) );
		}
		}
	}

	private void adjustTabOrder ( PdfArray annots, PdfIndirectReference ind, PdfNumber nn ) {
		int v = nn.intValue ();
		ArrayList<Integer> t = tabOrder.get ( annots );
		if ( t == null ) {
			t = new ArrayList<> ();
			int size = annots.size () - 1;
			for ( int k = 0; k < size; ++k ) {
				t.add ( zero );
			}
			t.add ( v );
			tabOrder.put ( annots, t );
			annots.add ( ind );
		} else {
			int size = t.size () - 1;
			for ( int k = size; k >= 0; --k ) {
				if ( t.get ( k ) <= v ) {
					t.add ( k + 1, v );
					annots.add ( k + 1, ind );
					size = -2;
					break;
				}
			}
			if ( size != -2 ) {
				t.add ( 0, v );
				annots.add ( 0, ind );
			}
		}
	}

	@SuppressWarnings ( "unchecked" )
	protected PdfArray branchForm ( HashMap<String, Object> level, PdfIndirectReference parent, String fname ) throws IOException {
		PdfArray arr = new PdfArray ();
		for ( Map.Entry<String, Object> entry : level.entrySet () ) {
			String name = entry.getKey ();
			Object obj = entry.getValue ();
			PdfIndirectReference ind = getPdfIndirectReference ();
			PdfDictionary dic = new PdfDictionary ();
			if ( parent != null )
				dic.put ( PdfName.PARENT, parent );
			dic.put ( PdfName.T, new PdfString ( name, PdfObject.TEXT_UNICODE ) );
			String fname2 = fname + "." + name;
			int coidx = calculationOrder.indexOf ( fname2 );
			if ( coidx >= 0 )
				calculationOrderRefs.set ( coidx, ind );
			if ( obj instanceof HashMap ) {
				dic.put ( PdfName.KIDS, branchForm ( (HashMap<String, Object>) obj, ind, fname2 ) );
				arr.add ( ind );
				addToBody ( dic, ind );
			} else {
				ArrayList<Object> list = (ArrayList<Object>) obj;
				dic.mergeDifferent ( (PdfDictionary) list.get ( 0 ) );
				if ( list.size () == 3 ) {
					dic.mergeDifferent ( (PdfDictionary) list.get ( 2 ) );
					int page = (Integer) list.get ( 1 );
					PdfDictionary pageDic = pageDics.get ( page - 1 );
					PdfArray annots = pageDic.getAsArray ( PdfName.ANNOTS );
					if ( annots == null ) {
						annots = new PdfArray ();
						pageDic.put ( PdfName.ANNOTS, annots );
					}
					PdfNumber nn = (PdfNumber) dic.get ( oTextTag );
					dic.remove ( oTextTag );
					adjustTabOrder ( annots, ind, nn );
				} else {
					PdfDictionary field = (PdfDictionary) list.get ( 0 );
					PdfName v = field.getAsName ( PdfName.V );
					PdfArray kids = new PdfArray ();
					for ( int k = 1; k < list.size (); k += 2 ) {
						int page = (Integer) list.get ( k );
						PdfDictionary pageDic = pageDics.get ( page - 1 );
						PdfArray annots = pageDic.getAsArray ( PdfName.ANNOTS );
						if ( annots == null ) {
							annots = new PdfArray ();
							pageDic.put ( PdfName.ANNOTS, annots );
						}
						PdfDictionary widget = new PdfDictionary ();
						widget.merge ( (PdfDictionary) list.get ( k + 1 ) );
						widget.put ( PdfName.PARENT, ind );
						PdfNumber nn = (PdfNumber) widget.get ( oTextTag );
						widget.remove ( oTextTag );
						if ( PdfCopy.isCheckButton ( field ) ) {
							PdfName as = widget.getAsName ( PdfName.AS );
							if ( v != null && as != null )
								widget.put ( PdfName.AS, v );
						} else if ( PdfCopy.isRadioButton ( field ) ) {
							PdfName as = widget.getAsName ( PdfName.AS );
							if ( v != null && as != null && !as.equals ( getOffStateName () ) ) {
								if ( !mergedRadioButtons.contains ( list ) ) {
									mergedRadioButtons.add ( list );
									widget.put ( PdfName.AS, v );
								} else {
									widget.put ( PdfName.AS, getOffStateName () );
								}
							}
						}
						PdfIndirectReference wref = addToBody ( widget ).getIndirectReference ();
						adjustTabOrder ( annots, wref, nn );
						kids.add ( wref );
						propagate ( widget, false );
					}
					dic.put ( PdfName.KIDS, kids );
				}
				arr.add ( ind );
				addToBody ( dic, ind );
				propagate ( dic, false );
			}
		}
		return arr;
	}

	protected PdfName getOffStateName ( ) {
		return PdfName.Off;
	}

	protected void createAcroForms () throws IOException {
		if ( fieldTree.isEmpty () )
			return;
		form = new PdfDictionary ();
		form.put ( PdfName.DR, resources );
		propagate ( resources, false );
		form.put ( PdfName.DA, new PdfString ( "/Helv 0 Tf 0 g " ) );
		tabOrder = new HashMap<> ();
		calculationOrderRefs = new ArrayList<> ( calculationOrder );
		form.put ( PdfName.FIELDS, branchForm ( fieldTree, null, "" ) );
		if ( hasSignature )
			form.put ( PdfName.SIGFLAGS, new PdfNumber ( 3 ) );
		PdfArray co = new PdfArray ();
		for ( Object obj : calculationOrderRefs ) {
			if ( obj instanceof PdfIndirectReference )
				co.add ( (PdfIndirectReference) obj );
		}
		if ( !co.isEmpty () )
			form.put ( PdfName.CO, co );
	}

	@Override
	public void close () {
		if ( closing ) {
			super.close ();
			return;
		}
		closing = true;
		try {
			closeIt ();
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}

	protected void closeIt () throws IOException {
		for ( PdfReader pdfReader : readers ) {
			pdfReader.removeFields ();
		}
		for ( PdfReader reader : readers ) {
			for ( int page = 1; page <= reader.getNumberOfPages (); ++page ) {
				pageRefs.add ( getNewReference ( reader.getPageOrigRef ( page ) ) );
				pageDics.add ( reader.getPageN ( page ) );
			}
		}
		mergeFields ();
		createAcroForms ();
		for ( PdfReader reader : readers ) {
			for ( int page = 1; page <= reader.getNumberOfPages (); ++page ) {
				PdfDictionary dic = reader.getPageN ( page );
				PdfIndirectReference pageRef = getNewReference ( reader.getPageOrigRef ( page ) );
				PdfIndirectReference parent = root.addPageRef ( pageRef );
				dic.put ( PdfName.PARENT, parent );
				propagate ( dic, false );
			}
		}
		for ( Map.Entry<PdfReader, IntHashtable> entry : readers2intrefs.entrySet () ) {
			PdfReader reader = entry.getKey ();
			try {
				file = reader.getSafeFile ();
				file.reOpen ();
				IntHashtable t = entry.getValue ();
				int[] keys = t.toOrderedKeys ();
				for ( int key : keys ) {
					PRIndirectReference ref = new PRIndirectReference ( reader, key );
					addToBody ( PdfReader.getPdfObjectRelease ( ref ), t.get ( key ) );
				}
			} finally {
				try {
					file.close ();
					// TODO: Removed - the user should be responsible for closing all PdfReaders.  But, this could cause a lot of memory leaks in code out there that hasn't been properly closing things - maybe add a finalizer to PdfReader that calls PdfReader#close() ??
					//                    reader.close();
				} catch ( Exception e ) {
					// empty on purpose
				}
			}
		}
		pdf.close ();
	}

	void addPageOffsetToField ( Map<String, AcroFields.Item> fd, int pageOffset ) {
		if ( pageOffset == 0 )
			return;
		for ( AcroFields.Item item : fd.values () ) {
			for ( int k = 0; k < item.size (); ++k ) {
				int p = item.getPage ( k );
				item.forcePage ( k, p + pageOffset );
			}
		}
	}

	void createWidgets ( ArrayList<Object> list, AcroFields.Item item ) {
		for ( int k = 0; k < item.size (); ++k ) {
			list.add ( item.getPage ( k ) );
			PdfDictionary merged = item.getMerged ( k );
			PdfObject dr = merged.get ( PdfName.DR );
			if ( dr != null )
				PdfFormField.mergeResources ( resources, (PdfDictionary) PdfReader.getPdfObject ( dr ) );
			PdfDictionary widget = new PdfDictionary ();
			for ( PdfName element : merged.getKeys () ) {
				if ( widgetKeys.containsKey ( element ) )
					widget.put ( element, merged.get ( element ) );
			}
			widget.put ( oTextTag, new PdfNumber ( item.getTabOrder ( k ) + 1 ) );
			list.add ( widget );
		}
	}

	@SuppressWarnings ( "unchecked" )
	void mergeField ( String name, AcroFields.Item item ) {
		HashMap<String, Object> map = fieldTree;
		StringTokenizer tk = new StringTokenizer ( name, "." );
		if ( !tk.hasMoreTokens () )
			return;
		while ( true ) {
			String s = tk.nextToken ();
			Object obj = map.get ( s );
			if ( tk.hasMoreTokens () ) {
				if ( obj == null ) {
					obj = new HashMap ();
					map.put ( s, obj );
					map = (HashMap<String, Object>) obj;
				} else if ( obj instanceof HashMap )
					map = (HashMap<String, Object>) obj;
				else
					return;
			} else {
				if ( obj instanceof HashMap )
					return;
				PdfDictionary merged = item.getMerged ( 0 );
				if ( obj == null ) {
					PdfDictionary field = new PdfDictionary ();
					if ( PdfName.SIG.equals ( merged.get ( PdfName.FT ) ) )
						hasSignature = true;
					for ( PdfName element : merged.getKeys () ) {
						if ( fieldKeys.containsKey ( element ) )
							field.put ( element, merged.get ( element ) );
					}
					ArrayList<Object> list = new ArrayList<> ();
					list.add ( field );
					createWidgets ( list, item );
					map.put ( s, list );
				} else {
					ArrayList<Object> list = (ArrayList<Object>) obj;
					PdfDictionary field = (PdfDictionary) list.get ( 0 );
					PdfName type1 = (PdfName) field.get ( PdfName.FT );
					PdfName type2 = (PdfName) merged.get ( PdfName.FT );
					if ( type1 == null || !type1.equals ( type2 ) )
						return;
					int flag1 = 0;
					PdfObject f1 = field.get ( PdfName.FF );
					if ( f1 != null && f1.isNumber () )
						flag1 = ( (PdfNumber) f1 ).intValue ();
					int flag2 = 0;
					PdfObject f2 = merged.get ( PdfName.FF );
					if ( f2 != null && f2.isNumber () )
						flag2 = ( (PdfNumber) f2 ).intValue ();
					if ( type1.equals ( PdfName.BTN ) ) {
						if ( ( ( flag1 ^ flag2 ) & PdfFormField.FF_PUSHBUTTON ) != 0 )
							return;
						if ( ( flag1 & PdfFormField.FF_PUSHBUTTON ) == 0 && ( ( flag1 ^ flag2 ) & PdfFormField.FF_RADIO ) != 0 )
							return;
					} else if ( type1.equals ( PdfName.CH ) ) {
						if ( ( ( flag1 ^ flag2 ) & PdfFormField.FF_COMBO ) != 0 )
							return;
					}
					createWidgets ( list, item );
				}
				return;
			}
		}
	}

	void mergeWithMaster ( Map<String, AcroFields.Item> fd ) {
		for ( Map.Entry<String, AcroFields.Item> entry : fd.entrySet () ) {
			String name = entry.getKey ();
			mergeField ( name, entry.getValue () );
		}
	}

	void mergeFields () {
		int pageOffset = 0;
		for ( int k = 0; k < fields.size (); ++k ) {
			Map<String, AcroFields.Item> fd = fields.get ( k ).getFields ();
			addPageOffsetToField ( fd, pageOffset );
			mergeWithMaster ( fd );
			pageOffset += readers.get ( k ).getNumberOfPages ();
		}
	}

	@Override
	public PdfIndirectReference getPageReference ( int page ) {
		return pageRefs.get ( page - 1 );
	}

	@Override
	protected PdfDictionary getCatalog ( PdfIndirectReference rootObj ) {
		try {
			PdfDictionary cat = pdf.getCatalog ( rootObj );
			if ( form != null ) {
				PdfIndirectReference ref = addToBody ( form ).getIndirectReference ();
				cat.put ( PdfName.ACROFORM, ref );
			}
			return cat;
		} catch ( IOException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	protected PdfIndirectReference getNewReference ( PRIndirectReference ref ) {
		return new PdfIndirectReference ( 0, getNewObjectNumber ( ref.getReader (), ref.getNumber (), 0 ) );
	}

	@Override
	protected int getNewObjectNumber ( PdfReader reader, int number, int generation ) {
		IntHashtable refs = readers2intrefs.get ( reader );
		int n = refs.get ( number );
		if ( n == 0 ) {
			n = getIndirectReferenceNumber ();
			refs.put ( number, n );
		}
		return n;
	}

	protected boolean setVisited ( PRIndirectReference ref ) {
		IntHashtable refs = visited.get ( ref.getReader () );
		if ( refs != null )
			return refs.put ( ref.getNumber (), 1 ) != 0;
		else
			return false;
	}

	protected boolean isVisited ( PRIndirectReference ref ) {
		IntHashtable refs = visited.get ( ref.getReader () );
		if ( refs != null )
			return refs.containsKey ( ref.getNumber () );
		else
			return false;
	}

	protected boolean isPage ( PRIndirectReference ref ) {
		IntHashtable refs = pages2intrefs.get ( ref.getReader () );
		if ( refs != null )
			return !refs.containsKey ( ref.getNumber () );
		else
			return true;
	}

	public void openDoc () {
		if ( !nd.isOpen () )
			nd.open ();
	}
}
