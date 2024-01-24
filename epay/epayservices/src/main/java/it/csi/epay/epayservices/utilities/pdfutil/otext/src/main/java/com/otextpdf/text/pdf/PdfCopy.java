/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.Counter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log.CounterFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;


public class PdfCopy extends PdfWriter {

	protected static final HashSet<PdfName> widgetKeys = new HashSet<> ();

	protected static final HashSet<PdfName> fieldKeys = new HashSet<> ();

	static private final PdfName annotId = new PdfName ( "oTextAnnotId" );

	static private final int annotIdCnt = 0;

	private static final PdfName oTextTag = new PdfName ( "_oTextTag_" );

	private static final Integer zero = 0;

	protected static Counter COUNTER = CounterFactory.getCounter ( PdfCopy.class );

	static {
		widgetKeys.add ( PdfName.SUBTYPE );
		widgetKeys.add ( PdfName.CONTENTS );
		widgetKeys.add ( PdfName.RECT );
		widgetKeys.add ( PdfName.NM );
		widgetKeys.add ( PdfName.M );
		widgetKeys.add ( PdfName.F );
		widgetKeys.add ( PdfName.BS );
		widgetKeys.add ( PdfName.BORDER );
		widgetKeys.add ( PdfName.AP );
		widgetKeys.add ( PdfName.AS );
		widgetKeys.add ( PdfName.C );
		widgetKeys.add ( PdfName.A );
		widgetKeys.add ( PdfName.STRUCTPARENT );
		widgetKeys.add ( PdfName.OC );
		widgetKeys.add ( PdfName.H );
		widgetKeys.add ( PdfName.MK );
		widgetKeys.add ( PdfName.DA );
		widgetKeys.add ( PdfName.Q );
		widgetKeys.add ( PdfName.P );
		widgetKeys.add ( PdfName.TYPE );
		widgetKeys.add ( annotId );
		fieldKeys.add ( PdfName.AA );
		fieldKeys.add ( PdfName.FT );
		fieldKeys.add ( PdfName.TU );
		fieldKeys.add ( PdfName.TM );
		fieldKeys.add ( PdfName.FF );
		fieldKeys.add ( PdfName.V );
		fieldKeys.add ( PdfName.DV );
		fieldKeys.add ( PdfName.DS );
		fieldKeys.add ( PdfName.RV );
		fieldKeys.add ( PdfName.OPT );
		fieldKeys.add ( PdfName.MAXLEN );
		fieldKeys.add ( PdfName.TI );
		fieldKeys.add ( PdfName.I );
		fieldKeys.add ( PdfName.LOCK );
		fieldKeys.add ( PdfName.SV );
	}

	private final boolean rotateContents = true;

	private final PdfStructTreeController structTreeController = null;

	private final int currentStructArrayNumber = 0;

	private final HashSet<Object> mergedRadioButtons = new HashSet<> ();

	private final HashMap<Object, PdfObject> mergedTextFields = new HashMap<> ();

	protected HashMap<RefKey, IndirectReferences> indirects;

	protected HashMap<PdfReader, HashMap<RefKey, IndirectReferences>> indirectMap;

	protected HashMap<PdfObject, PdfObject> parentObjects;

	protected HashSet<PdfObject> disableIndirects;

	protected PdfReader reader;

	protected int[] namePtr = { 0 };

	protected PdfArray fieldArray;

	protected HashSet<PdfTemplate> fieldTemplates;

	protected PRIndirectReference structTreeRootReference;

	protected HashMap<RefKey, PdfIndirectObject> indirectObjects;

	protected ArrayList<PdfIndirectObject> savedObjects;

	protected ArrayList<ImportedPage> importedPages;

	protected boolean updateRootKids = false;

	protected boolean mergeFields = false;

	protected ArrayList<AcroFields> fields;

	private boolean hasSignature;

	private PdfIndirectReference acroForm;

	private HashMap<PdfArray, ArrayList<Integer>> tabOrder;

	private ArrayList<Object> calculationOrderRefs;

	private PdfDictionary resources;

	private ArrayList<String> calculationOrder;

	private HashMap<String, Object> fieldTree;

	private HashMap<Integer, PdfIndirectObject> unmergedMap;

	private HashSet<PdfIndirectObject> unmergedSet;

	private HashMap<Integer, PdfIndirectObject> mergedMap;

	private HashSet<PdfIndirectObject> mergedSet;

	public PdfCopy ( Document document, OutputStream os ) throws DocumentException {
		super ( new PdfDocument (), os );
		document.addDocListener ( pdf );
		pdf.addWriter ( this );
		indirectMap = new HashMap<> ();
		parentObjects = new HashMap<> ();
		disableIndirects = new HashSet<> ();

		indirectObjects = new HashMap<> ();
		savedObjects = new ArrayList<> ();
		importedPages = new ArrayList<> ();
	}

	static Integer getFlags ( PdfDictionary field ) {
		PdfName type = field.getAsName ( PdfName.FT );
		if ( !PdfName.BTN.equals ( type ) )
			return null;
		PdfNumber flags = field.getAsNumber ( PdfName.FF );
		if ( flags == null )
			return null;
		return flags.intValue ();
	}

	static boolean isCheckButton ( PdfDictionary field ) {
		Integer flags = getFlags ( field );
		return flags == null || ( ( flags & PdfFormField.FF_PUSHBUTTON ) == 0 && ( flags & PdfFormField.FF_RADIO ) == 0 );
	}

	static boolean isRadioButton ( PdfDictionary field ) {
		Integer flags = getFlags ( field );
		return flags != null && ( flags & PdfFormField.FF_PUSHBUTTON ) == 0 && ( flags & PdfFormField.FF_RADIO ) != 0;
	}

	static boolean isTextField ( PdfDictionary field ) {
		PdfName type = field.getAsName ( PdfName.FT );
		return PdfName.TX.equals ( type );
	}

	protected Counter getCounter () {
		return COUNTER;
	}

	protected void fixStructureTreeRoot ( HashSet<RefKey> activeKeys, HashSet<PdfName> activeClassMaps ) {
		HashMap<PdfName, PdfObject> newClassMap = new HashMap<> ( activeClassMaps.size () );
		for ( PdfName key : activeClassMaps ) {
			PdfObject cm = structureTreeRoot.classes.get ( key );
			if ( cm != null )
				newClassMap.put ( key, cm );
		}

		structureTreeRoot.classes = newClassMap;

		PdfArray kids = structureTreeRoot.getAsArray ( PdfName.K );
		if ( kids != null ) {
			for ( int i = 0; i < kids.size (); ++i ) {
				PdfIndirectReference iref = (PdfIndirectReference) kids.getPdfObject ( i );
				RefKey key = new RefKey ( iref );
				if ( !activeKeys.contains ( key ) )
					kids.remove ( i-- );
			}
		}
	}

	protected PdfIndirectReference copyIndirect ( PRIndirectReference in, boolean keepStructure, boolean directRootKids )
					throws IOException, BadPdfFormatException {
		PdfIndirectReference theRef;
		RefKey key = new RefKey ( in );
		IndirectReferences iRef = indirects.get ( key );
		PdfObject obj = PdfReader.getPdfObjectRelease ( in );
		if ( ( keepStructure ) && ( directRootKids ) )
			if ( obj instanceof PdfDictionary ) {
				PdfDictionary dict = (PdfDictionary) obj;
				if ( dict.contains ( PdfName.PG ) )
					return null;
			}

		if ( iRef != null ) {
			theRef = iRef.getRef ();
			if ( iRef.getCopied () ) {
				return theRef;
			}
		} else {
			theRef = body.getPdfIndirectReference ();
			iRef = new IndirectReferences ( theRef );
			indirects.put ( key, iRef );
		}

		if ( obj != null && obj.isDictionary () ) {
			assert obj instanceof PdfDictionary;
			PdfObject type = PdfReader.getPdfObjectRelease ( ( (PdfDictionary) obj ).get ( PdfName.TYPE ) );
			if ( PdfName.PAGE.equals ( type ) ) {
				return theRef;
			}
		}
		iRef.setCopied ();
		if ( obj != null )
			parentObjects.put ( obj, in );
		PdfObject res = copyObject ( obj, keepStructure, directRootKids );
		if ( disableIndirects.contains ( obj ) )
			iRef.setNotCopied ();
		if ( res != null ) {
			addToBody ( res, theRef );
			return theRef;
		} else {
			indirects.remove ( key );
			return null;
		}

	}

	protected PdfIndirectReference copyIndirect ( PRIndirectReference in ) throws IOException, BadPdfFormatException {
		return copyIndirect ( in, false, false );
	}

	protected PdfDictionary copyDictionary ( PdfDictionary in, boolean keepStruct, boolean directRootKids )
					throws IOException, BadPdfFormatException {
		PdfDictionary out = new PdfDictionary ();
		PdfObject type = PdfReader.getPdfObjectRelease ( in.get ( PdfName.TYPE ) );

		if ( keepStruct ) {
			if ( ( directRootKids ) && ( in.contains ( PdfName.PG ) ) ) {
				PdfObject curr = in;
				disableIndirects.add ( curr );
				while ( parentObjects.containsKey ( curr ) && !( disableIndirects.contains ( curr ) ) ) {
					curr = parentObjects.get ( curr );
					disableIndirects.add ( curr );
				}
				return null;
			}

			PdfName structType = in.getAsName ( PdfName.S );
			structTreeController.addRole ( structType );
			structTreeController.addClass ( in );
		}
		for ( PdfName element : in.getKeys () ) {
			PdfObject value = in.get ( element );
			if ( PdfName.PAGE.equals ( type ) ) {
				if ( !element.equals ( PdfName.B ) && !element.equals ( PdfName.PARENT ) ) {
					parentObjects.put ( value, in );
					PdfObject res = copyObject ( value, keepStruct, directRootKids );
					if ( res != null )
						out.put ( element, res );
				}
			} else {
				PdfObject res;
				if ( tagged && value.isIndirect () && isStructTreeRootReference ( (PRIndirectReference) value ) ) {
					res = structureTreeRoot.getReference ();
				} else {
					res = copyObject ( value, keepStruct, directRootKids );
				}
				if ( res != null )
					out.put ( element, res );
			}
		}

		return out;
	}

	protected PdfStream copyStream ( PRStream in ) throws IOException, BadPdfFormatException {
		PRStream out = new PRStream ( in, null );

		for ( PdfName element : in.getKeys () ) {
			PdfObject value = in.get ( element );
			parentObjects.put ( value, in );
			PdfObject res = copyObject ( value );
			if ( res != null )
				out.put ( element, res );
		}

		return out;
	}

	protected PdfArray copyArray ( PdfArray in, boolean keepStruct, boolean directRootKids ) throws IOException, BadPdfFormatException {
		PdfArray out = new PdfArray ();

		for ( PdfObject value : in ) {
			parentObjects.put ( value, in );
			PdfObject res = copyObject ( value, keepStruct, directRootKids );
			if ( res != null )
				out.add ( res );
		}
		return out;
	}

	protected PdfObject copyObject ( PdfObject in, boolean keepStruct, boolean directRootKids ) throws IOException, BadPdfFormatException {
		if ( in == null )
			return PdfNull.PDFNULL;
		switch ( in.type ) {
		case PdfObject.DICTIONARY:
			return copyDictionary ( (PdfDictionary) in, keepStruct, directRootKids );
		case PdfObject.INDIRECT:
			if ( !keepStruct && !directRootKids )
				return copyIndirect ( (PRIndirectReference) in );
			else
				return copyIndirect ( (PRIndirectReference) in, keepStruct, directRootKids );
		case PdfObject.ARRAY:
			return copyArray ( (PdfArray) in, keepStruct, directRootKids );
		case PdfObject.NUMBER:
		case PdfObject.NAME:
		case PdfObject.STRING:
		case PdfObject.NULL:
		case PdfObject.BOOLEAN:
		case 0:
			return in;
		case PdfObject.STREAM:
			return copyStream ( (PRStream) in );

		default:
			if ( in.type < 0 ) {
				String lit = in.toString ();
				if ( lit.equals ( "true" ) || lit.equals ( "false" ) ) {
					return new PdfBoolean ( lit );
				}
				return new PdfLiteral ( lit );
			}
			System.out.println ( "CANNOT COPY type " + in.type );
			return null;
		}
	}

	protected PdfObject copyObject ( PdfObject in ) throws IOException, BadPdfFormatException {
		return copyObject ( in, false, false );
	}

	@Override
	public PdfIndirectObject addToBody ( final PdfObject object, final PdfIndirectReference ref ) throws IOException {
		return this.addToBody ( object, ref, false );
	}

	@Override
	public PdfIndirectObject addToBody ( final PdfObject object, final PdfIndirectReference ref, boolean formBranching ) throws IOException {
		if ( formBranching ) {
			updateReferences ( object );
		}
		PdfIndirectObject iobj;
		if ( ( tagged || mergeFields ) && indirectObjects != null && ( object.isArray () || object.isDictionary () || object.isStream () || object.isNull () ) ) {
			RefKey key = new RefKey ( ref );
			PdfIndirectObject obj = indirectObjects.get ( key );
			if ( obj == null ) {
				obj = new PdfIndirectObject ( ref, object, this );
				indirectObjects.put ( key, obj );
			}
			iobj = obj;
		} else {
			iobj = super.addToBody ( object, ref );
		}
		if ( mergeFields && object.isDictionary () ) {
			PdfNumber annotId = ( (PdfDictionary) object ).getAsNumber ( PdfCopy.annotId );
			if ( annotId != null ) {
				if ( formBranching ) {
					mergedMap.put ( annotId.intValue (), iobj );
					mergedSet.add ( iobj );
				} else {
					unmergedMap.put ( annotId.intValue (), iobj );
					unmergedSet.add ( iobj );
				}
			}
		}
		return iobj;
	}

	@Override
	public PdfIndirectObject addToBody ( final PdfObject object ) throws IOException {
		PdfIndirectObject iobj = super.addToBody ( object );
		if ( ( tagged || mergeFields ) && indirectObjects != null ) {
			savedObjects.add ( iobj );
			RefKey key = new RefKey ( iobj.number, iobj.generation );
			if ( !indirectObjects.containsKey ( key ) )
				indirectObjects.put ( key, iobj );
		}
		return iobj;
	}

	@Override
	protected void flushTaggedObjects () throws IOException {
		try {
			fixTaggedStructure ();
		} catch ( ClassCastException ignored ) {
		} finally {
			flushIndirectObjects ();
		}
	}

	@Override
	protected void flushAcroFields () throws IOException, BadPdfFormatException {
		if ( mergeFields ) {
			try {
				for ( PdfReader reader : indirectMap.keySet () ) {
					reader.removeFields ();
				}
				mergeFields ();
				createAcroForms ();
			} catch ( ClassCastException ignored ) {
			} finally {
				if ( !tagged )
					flushIndirectObjects ();
			}
		}
	}

	protected void fixTaggedStructure () throws IOException {
		HashMap<Integer, PdfIndirectReference> numTree = structureTreeRoot.getNumTree ();
		HashSet<RefKey> activeKeys = new HashSet<> ();
		ArrayList<PdfIndirectReference> actives = new ArrayList<> ();
		int pageRefIndex = 0;

		if ( mergeFields && acroForm != null ) {
			actives.add ( acroForm );
			activeKeys.add ( new RefKey ( acroForm ) );
		}
		for ( PdfIndirectReference page : pageReferences ) {
			actives.add ( page );
			activeKeys.add ( new RefKey ( page ) );
		}

		for ( int i = numTree.size () - 1; i >= 0; --i ) {
			PdfIndirectReference currNum = numTree.get ( i );
			RefKey numKey = new RefKey ( currNum );
			PdfObject obj = indirectObjects.get ( numKey ).object;
			if ( obj.isDictionary () ) {
				boolean addActiveKeys = false;
				if ( pageReferences.contains ( ( (PdfDictionary) obj ).get ( PdfName.PG ) ) ) {
					addActiveKeys = true;
				} else {
					PdfDictionary k = PdfStructTreeController.getKDict ( (PdfDictionary) obj );
					if ( k != null && pageReferences.contains ( k.get ( PdfName.PG ) ) ) {
						addActiveKeys = true;
					}
				}
				if ( addActiveKeys ) {
					activeKeys.add ( numKey );
					actives.add ( currNum );
				} else {
					numTree.remove ( i );
				}
			} else if ( obj.isArray () ) {
				activeKeys.add ( numKey );
				actives.add ( currNum );
				PdfArray currNums = (PdfArray) obj;
				PdfIndirectReference currPage = pageReferences.get ( pageRefIndex++ );
				actives.add ( currPage );
				activeKeys.add ( new RefKey ( currPage ) );
				PdfIndirectReference prevKid = null;
				for ( int j = 0; j < currNums.size (); j++ ) {
					PdfIndirectReference currKid = (PdfIndirectReference) currNums.getDirectObject ( j );
					if ( currKid.equals ( prevKid ) )
						continue;
					RefKey kidKey = new RefKey ( currKid );
					activeKeys.add ( kidKey );
					actives.add ( currKid );

					PdfIndirectObject iobj = indirectObjects.get ( kidKey );
					if ( iobj.object.isDictionary () ) {
						PdfDictionary dict = (PdfDictionary) iobj.object;
						PdfIndirectReference pg = (PdfIndirectReference) dict.get ( PdfName.PG );
						if ( pg != null && !pageReferences.contains ( pg ) && !pg.equals ( currPage ) ) {
							dict.put ( PdfName.PG, currPage );
							PdfArray kids = dict.getAsArray ( PdfName.K );
							if ( kids != null ) {
								PdfObject firstKid = kids.getDirectObject ( 0 );
								if ( firstKid.isNumber () )
									kids.remove ( 0 );
							}
						}
					}
					prevKid = currKid;
				}
			}
		}

		HashSet<PdfName> activeClassMaps = new HashSet<> ();
		findActives ( actives, activeKeys, activeClassMaps );
		ArrayList<PdfIndirectReference> newRefs = findActiveParents ( activeKeys );
		fixPgKey ( newRefs, activeKeys );
		fixStructureTreeRoot ( activeKeys, activeClassMaps );

		for ( Map.Entry<RefKey, PdfIndirectObject> entry : indirectObjects.entrySet () ) {
			if ( !activeKeys.contains ( entry.getKey () ) ) {
				entry.setValue ( null );
			} else {
				if ( entry.getValue ().object.isArray () ) {
					removeInactiveReferences ( (PdfArray) entry.getValue ().object, activeKeys );
				} else if ( entry.getValue ().object.isDictionary () ) {
					PdfObject kids = ( (PdfDictionary) entry.getValue ().object ).get ( PdfName.K );
					if ( kids != null && kids.isArray () )
						removeInactiveReferences ( (PdfArray) kids, activeKeys );
				}
			}
		}
	}

	private void removeInactiveReferences ( PdfArray array, HashSet<RefKey> activeKeys ) {
		for ( int i = 0; i < array.size (); ++i ) {
			PdfObject obj = array.getPdfObject ( i );
			if ( ( obj.type () == 0 && !activeKeys.contains ( new RefKey ( (PdfIndirectReference) obj ) ) ) ||
							( obj.isDictionary () && containsInactivePg ( (PdfDictionary) obj, activeKeys ) ) )
				array.remove ( i-- );
		}
	}

	private boolean containsInactivePg ( PdfDictionary dict, HashSet<RefKey> activeKeys ) {
		PdfObject pg = dict.get ( PdfName.PG );
		return pg != null && !activeKeys.contains ( new RefKey ( (PdfIndirectReference) pg ) );
	}

	private ArrayList<PdfIndirectReference> findActiveParents ( HashSet<RefKey> activeKeys ) {
		ArrayList<PdfIndirectReference> newRefs = new ArrayList<> ();
		ArrayList<RefKey> tmpActiveKeys = new ArrayList<> ( activeKeys );
		for ( int i = 0; i < tmpActiveKeys.size (); ++i ) {
			PdfIndirectObject iobj = indirectObjects.get ( tmpActiveKeys.get ( i ) );
			if ( iobj == null || !iobj.object.isDictionary () )
				continue;
			PdfObject parent = ( (PdfDictionary) iobj.object ).get ( PdfName.P );
			if ( parent != null && parent.type () == 0 ) {
				RefKey key = new RefKey ( (PdfIndirectReference) parent );
				if ( !activeKeys.contains ( key ) ) {
					activeKeys.add ( key );
					tmpActiveKeys.add ( key );
					newRefs.add ( (PdfIndirectReference) parent );
				}
			}
		}
		return newRefs;
	}

	private void fixPgKey ( ArrayList<PdfIndirectReference> newRefs, HashSet<RefKey> activeKeys ) {
		for ( PdfIndirectReference iref : newRefs ) {
			PdfIndirectObject iobj = indirectObjects.get ( new RefKey ( iref ) );
			if ( iobj == null || !iobj.object.isDictionary () )
				continue;
			PdfDictionary dict = (PdfDictionary) iobj.object;
			PdfObject pg = dict.get ( PdfName.PG );
			if ( pg == null || activeKeys.contains ( new RefKey ( (PdfIndirectReference) pg ) ) )
				continue;
			PdfArray kids = dict.getAsArray ( PdfName.K );
			if ( kids == null )
				continue;
			for ( int i = 0; i < kids.size (); ++i ) {
				PdfObject obj = kids.getPdfObject ( i );
				if ( obj.type () != 0 ) {
					kids.remove ( i-- );
				} else {
					PdfIndirectObject kid = indirectObjects.get ( new RefKey ( (PdfIndirectReference) obj ) );
					if ( kid != null && kid.object.isDictionary () ) {
						PdfObject kidPg = ( (PdfDictionary) kid.object ).get ( PdfName.PG );
						if ( kidPg != null && activeKeys.contains ( new RefKey ( (PdfIndirectReference) kidPg ) ) ) {
							dict.put ( PdfName.PG, kidPg );
							break;
						}
					}
				}
			}
		}
	}

	private void findActives ( ArrayList<PdfIndirectReference> actives, HashSet<RefKey> activeKeys, HashSet<PdfName> activeClassMaps ) {
		for ( int i = 0; i < actives.size (); ++i ) {
			RefKey key = new RefKey ( actives.get ( i ) );
			PdfIndirectObject iobj = indirectObjects.get ( key );
			if ( iobj == null || iobj.object == null )
				continue;
			switch ( iobj.object.type () ) {
			case 0:
				findActivesFromReference ( (PdfIndirectReference) iobj.object, actives, activeKeys );
				break;
			case PdfObject.ARRAY:
				findActivesFromArray ( (PdfArray) iobj.object, actives, activeKeys, activeClassMaps );
				break;
			case PdfObject.DICTIONARY:
			case PdfObject.STREAM:
				findActivesFromDict ( (PdfDictionary) iobj.object, actives, activeKeys, activeClassMaps );
				break;
			}
		}
	}

	private void findActivesFromReference ( PdfIndirectReference iref, ArrayList<PdfIndirectReference> actives, HashSet<RefKey> activeKeys ) {
		RefKey key = new RefKey ( iref );
		PdfIndirectObject iobj = indirectObjects.get ( key );
		if ( iobj != null && iobj.object.isDictionary () && containsInactivePg ( (PdfDictionary) iobj.object, activeKeys ) )
			return;

		if ( !activeKeys.contains ( key ) ) {
			activeKeys.add ( key );
			actives.add ( iref );
		}
	}

	private void findActivesFromArray ( PdfArray array, ArrayList<PdfIndirectReference> actives, HashSet<RefKey> activeKeys,
					HashSet<PdfName> activeClassMaps ) {
		for ( PdfObject obj : array ) {
			switch ( obj.type () ) {
			case 0:
				findActivesFromReference ( (PdfIndirectReference) obj, actives, activeKeys );
				break;
			case PdfObject.ARRAY:
				findActivesFromArray ( (PdfArray) obj, actives, activeKeys, activeClassMaps );
				break;
			case PdfObject.DICTIONARY:
			case PdfObject.STREAM:
				findActivesFromDict ( (PdfDictionary) obj, actives, activeKeys, activeClassMaps );
				break;
			}
		}
	}

	private void findActivesFromDict ( PdfDictionary dict, ArrayList<PdfIndirectReference> actives, HashSet<RefKey> activeKeys,
					HashSet<PdfName> activeClassMaps ) {
		if ( containsInactivePg ( dict, activeKeys ) )
			return;
		for ( PdfName key : dict.getKeys () ) {
			PdfObject obj = dict.get ( key );
			if ( key.equals ( PdfName.P ) )
				continue;
			else if ( key.equals ( PdfName.C ) ) {
				if ( obj.isArray () ) {
					for ( PdfObject cm : (PdfArray) obj ) {
						if ( cm.isName () )
							activeClassMaps.add ( (PdfName) cm );
					}
				} else if ( obj.isName () )
					activeClassMaps.add ( (PdfName) obj );
				continue;
			}
			switch ( obj.type () ) {
			case 0:
				findActivesFromReference ( (PdfIndirectReference) obj, actives, activeKeys );
				break;
			case PdfObject.ARRAY:
				findActivesFromArray ( (PdfArray) obj, actives, activeKeys, activeClassMaps );
				break;
			case PdfObject.DICTIONARY:
			case PdfObject.STREAM:
				findActivesFromDict ( (PdfDictionary) obj, actives, activeKeys, activeClassMaps );
				break;
			}
		}
	}

	protected void flushIndirectObjects () throws IOException {
		for ( PdfIndirectObject iobj : savedObjects )
			indirectObjects.remove ( new RefKey ( iobj.number, iobj.generation ) );
		HashSet<RefKey> inactives = new HashSet<> ();
		for ( Map.Entry<RefKey, PdfIndirectObject> entry : indirectObjects.entrySet () ) {
			if ( entry.getValue () != null )
				writeObjectToBody ( entry.getValue () );
			else
				inactives.add ( entry.getKey () );
		}
		ArrayList<PdfBody.PdfCrossReference> pdfCrossReferences = new ArrayList<> ( body.xrefs );
		for ( PdfBody.PdfCrossReference cr : pdfCrossReferences ) {
			RefKey key = new RefKey ( cr.getRefnum (), 0 );
			if ( inactives.contains ( key ) )
				body.xrefs.remove ( cr );
		}
		indirectObjects = null;
	}

	private void writeObjectToBody ( PdfIndirectObject object ) throws IOException {
		boolean skipWriting = false;
		if ( mergeFields ) {
			updateAnnotationReferences ( object.object );
			if ( object.object.isDictionary () || object.object.isStream () ) {
				PdfDictionary dictionary = (PdfDictionary) object.object;
				if ( unmergedSet.contains ( object ) ) {
					PdfNumber annotId = dictionary.getAsNumber ( PdfCopy.annotId );
					if ( annotId != null && mergedMap.containsKey ( annotId.intValue () ) )
						skipWriting = true;
				}
				if ( mergedSet.contains ( object ) ) {
					PdfNumber annotId = dictionary.getAsNumber ( PdfCopy.annotId );
					if ( annotId != null ) {
						PdfIndirectObject unmerged = unmergedMap.get ( annotId.intValue () );
						if ( unmerged != null && unmerged.object.isDictionary () ) {
							PdfNumber structParent = ( (PdfDictionary) unmerged.object ).getAsNumber ( PdfName.STRUCTPARENT );
							if ( structParent != null ) {
								dictionary.put ( PdfName.STRUCTPARENT, structParent );
							}
						}
					}
				}
			}
		}
		if ( !skipWriting ) {
			PdfDictionary dictionary = null;
			PdfNumber annotId = null;
			if ( mergeFields && object.object.isDictionary () ) {
				dictionary = (PdfDictionary) object.object;
				annotId = dictionary.getAsNumber ( PdfCopy.annotId );
				if ( annotId != null )
					dictionary.remove ( PdfCopy.annotId );
			}
			body.add ( object.object, object.number, object.generation, true );
			if ( annotId != null ) {
				dictionary.put ( PdfCopy.annotId, annotId );
			}
		}
	}

	private void updateAnnotationReferences ( PdfObject obj ) {
		if ( obj.isArray () ) {
			PdfArray array = (PdfArray) obj;
			for ( int i = 0; i < array.size (); i++ ) {
				PdfObject o = array.getPdfObject ( i );
				if ( o instanceof PdfIndirectReference ) {
					for ( PdfIndirectObject entry : unmergedSet ) {
						if ( entry.getIndirectReference ().toString ().equals ( o.toString () ) ) {
							if ( entry.object.isDictionary () ) {
								PdfNumber annotId = ( (PdfDictionary) entry.object ).getAsNumber ( PdfCopy.annotId );
								if ( annotId != null ) {
									PdfIndirectObject merged = mergedMap.get ( annotId.intValue () );
									if ( merged != null ) {
										array.set ( i, merged.getIndirectReference () );
									}
								}
							}
						}
					}
				} else {
					updateAnnotationReferences ( o );
				}
			}
		} else if ( obj.isDictionary () || obj.isStream () ) {
			PdfDictionary dictionary = (PdfDictionary) obj;
			for ( PdfName key : dictionary.getKeys () ) {
				PdfObject o = dictionary.get ( key );
				if ( o instanceof PdfIndirectReference ) {
					for ( PdfIndirectObject entry : unmergedSet ) {
						if ( entry.getIndirectReference ().toString ().equals ( o.toString () ) ) {
							if ( entry.object.isDictionary () ) {
								PdfNumber annotId = ( (PdfDictionary) entry.object ).getAsNumber ( PdfCopy.annotId );
								if ( annotId != null ) {
									PdfIndirectObject merged = mergedMap.get ( annotId.intValue () );
									if ( merged != null ) {
										dictionary.put ( key, merged.getIndirectReference () );
									}
								}
							}
						}
					}
				} else {
					updateAnnotationReferences ( o );
				}
			}
		}
	}

	private void mergeFields () {
		int pageOffset = 0;
		for ( AcroFields af : fields ) {
			Map<String, AcroFields.Item> fd = af.getFields ();
			addPageOffsetToField ( fd, pageOffset );
			mergeWithMaster ( fd );
			pageOffset += af.reader.getNumberOfPages ();
		}
	}

	private void addPageOffsetToField ( Map<String, AcroFields.Item> fd, int pageOffset ) {
		if ( pageOffset == 0 )
			return;
		for ( AcroFields.Item item : fd.values () ) {
			for ( int k = 0; k < item.size (); ++k ) {
				int p = item.getPage ( k );
				item.forcePage ( k, p + pageOffset );
			}
		}
	}

	private void mergeWithMaster ( Map<String, AcroFields.Item> fd ) {
		for ( Map.Entry<String, AcroFields.Item> entry : fd.entrySet () ) {
			String name = entry.getKey ();
			mergeField ( name, entry.getValue () );
		}
	}

	@SuppressWarnings ( "unchecked" )
	private void mergeField ( String name, AcroFields.Item item ) {
		HashMap<String, Object> map = fieldTree;
		StringTokenizer tk = new StringTokenizer ( name, "." );
		if ( !tk.hasMoreTokens () )
			return;
		while ( true ) {
			String s = tk.nextToken ();
			Object obj = map.get ( s );
			if ( tk.hasMoreTokens () ) {
				if ( obj == null ) {
					obj = new HashMap<String, Object> ();
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
						if ( fieldKeys.contains ( element ) )
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

	private void createWidgets ( ArrayList<Object> list, AcroFields.Item item ) {
		for ( int k = 0; k < item.size (); ++k ) {
			list.add ( item.getPage ( k ) );
			PdfDictionary merged = item.getMerged ( k );
			PdfObject dr = merged.get ( PdfName.DR );
			if ( dr != null )
				PdfFormField.mergeResources ( resources, (PdfDictionary) PdfReader.getPdfObject ( dr ) );
			PdfDictionary widget = new PdfDictionary ();
			for ( PdfName element : merged.getKeys () ) {
				if ( widgetKeys.contains ( element ) )
					widget.put ( element, merged.get ( element ) );
			}
			widget.put ( oTextTag, new PdfNumber ( item.getTabOrder ( k ) + 1 ) );
			list.add ( widget );
		}
	}

	private PdfObject propagate ( PdfObject obj ) throws IOException {
		if ( obj == null ) {
			return new PdfNull ();
		} else if ( obj.isArray () ) {
			PdfArray a = (PdfArray) obj;
			for ( int i = 0; i < a.size (); i++ ) {
				a.set ( i, propagate ( a.getPdfObject ( i ) ) );
			}
			return a;
		} else if ( obj.isDictionary () || obj.isStream () ) {
			PdfDictionary d = (PdfDictionary) obj;
			for ( PdfName key : d.getKeys () ) {
				d.put ( key, propagate ( d.get ( key ) ) );
			}
			return d;
		} else if ( obj.isIndirect () ) {
			obj = PdfReader.getPdfObject ( obj );
			return addToBody ( propagate ( obj ) ).getIndirectReference ();
		} else
			return obj;
	}

	private void createAcroForms () throws IOException, BadPdfFormatException {
		if ( fieldTree.isEmpty () )
			return;
		PdfDictionary form = new PdfDictionary ();
		form.put ( PdfName.DR, propagate ( resources ) );

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
		this.acroForm = addToBody ( form ).getIndirectReference ();
	}

	private void updateReferences ( PdfObject obj ) {
		if ( obj.isDictionary () || obj.isStream () ) {
			PdfDictionary dictionary = (PdfDictionary) obj;
			for ( PdfName key : dictionary.getKeys () ) {
				PdfObject o = dictionary.get ( key );
				if ( o.isIndirect () ) {
					PdfReader reader = ( (PRIndirectReference) o ).getReader ();
					HashMap<RefKey, IndirectReferences> indirects = indirectMap.get ( reader );
					IndirectReferences indRef = indirects.get ( new RefKey ( (PRIndirectReference) o ) );
					if ( indRef != null ) {
						dictionary.put ( key, indRef.getRef () );
					}
				} else {
					updateReferences ( o );
				}
			}
		} else if ( obj.isArray () ) {
			PdfArray array = (PdfArray) obj;
			for ( int i = 0; i < array.size (); i++ ) {
				PdfObject o = array.getPdfObject ( i );
				if ( o.isIndirect () ) {
					PdfReader reader = ( (PRIndirectReference) o ).getReader ();
					HashMap<RefKey, IndirectReferences> indirects = indirectMap.get ( reader );
					IndirectReferences indRef = indirects.get ( new RefKey ( (PRIndirectReference) o ) );
					if ( indRef != null ) {
						array.set ( i, indRef.getRef () );
					}
				} else {
					updateReferences ( o );
				}
			}
		}
	}

	@SuppressWarnings ( "unchecked" )
	private PdfArray branchForm ( HashMap<String, Object> level, PdfIndirectReference parent, String fname ) throws IOException, BadPdfFormatException {
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
			} else {
				ArrayList<Object> list = (ArrayList<Object>) obj;
				dic.mergeDifferent ( (PdfDictionary) list.get ( 0 ) );
				if ( list.size () == 3 ) {
					dic.mergeDifferent ( (PdfDictionary) list.get ( 2 ) );
					int page = (Integer) list.get ( 1 );
					PdfArray annots = importedPages.get ( page - 1 ).mergedFields;
					PdfNumber nn = (PdfNumber) dic.get ( oTextTag );
					dic.remove ( oTextTag );
					dic.put ( PdfName.TYPE, PdfName.ANNOT );
					adjustTabOrder ( annots, ind, nn );
				} else {
					PdfDictionary field = (PdfDictionary) list.get ( 0 );
					PdfArray kids = new PdfArray ();
					for ( int k = 1; k < list.size (); k += 2 ) {
						int page = (Integer) list.get ( k );
						PdfArray annots = importedPages.get ( page - 1 ).mergedFields;
						PdfDictionary widget = new PdfDictionary ();
						widget.merge ( (PdfDictionary) list.get ( k + 1 ) );
						widget.put ( PdfName.PARENT, ind );
						PdfNumber nn = (PdfNumber) widget.get ( oTextTag );
						widget.remove ( oTextTag );
						if ( PdfCopy.isTextField ( field ) ) {
							PdfString v = field.getAsString ( PdfName.V );
							PdfObject ap = widget.get ( PdfName.AP );
							if ( v != null && ap != null ) {
								if ( !mergedTextFields.containsKey ( list ) ) {
									mergedTextFields.put ( list, ap );
								} else {
									PdfObject ap1 = mergedTextFields.get ( list );
									widget.put ( PdfName.AP, copyObject ( ap1 ) );
								}
							}
						} else if ( PdfCopy.isCheckButton ( field ) ) {
							PdfName v = field.getAsName ( PdfName.V );
							PdfName as = widget.getAsName ( PdfName.AS );
							if ( v != null && as != null )
								widget.put ( PdfName.AS, v );
						} else if ( PdfCopy.isRadioButton ( field ) ) {
							PdfName v = field.getAsName ( PdfName.V );
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
						widget.put ( PdfName.TYPE, PdfName.ANNOT );
						PdfIndirectReference wref = addToBody ( widget, getPdfIndirectReference (), true ).getIndirectReference ();
						adjustTabOrder ( annots, wref, nn );
						kids.add ( wref );
					}
					dic.put ( PdfName.KIDS, kids );
				}
			}
			arr.add ( ind );
			addToBody ( dic, ind, true );
		}
		return arr;
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

	@Override
	protected PdfDictionary getCatalog ( PdfIndirectReference rootObj ) {
		try {
			PdfDictionary theCat = pdf.getCatalog ( rootObj );
			buildStructTreeRootForTagged ( theCat );
			if ( fieldArray != null ) {
				addFieldResources ( theCat );
			} else if ( mergeFields && acroForm != null ) {
				theCat.put ( PdfName.ACROFORM, acroForm );
			}
			return theCat;
		} catch ( IOException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	protected boolean isStructTreeRootReference ( PdfIndirectReference prRef ) {
		if ( prRef == null || structTreeRootReference == null )
			return false;
		return prRef.number == structTreeRootReference.number && prRef.generation == structTreeRootReference.generation;
	}

	private void addFieldResources ( PdfDictionary catalog ) throws IOException {
		if ( fieldArray == null )
			return;
		PdfDictionary acroForm = new PdfDictionary ();
		catalog.put ( PdfName.ACROFORM, acroForm );
		acroForm.put ( PdfName.FIELDS, fieldArray );
		acroForm.put ( PdfName.DA, new PdfString ( "/Helv 0 Tf 0 g " ) );
		if ( fieldTemplates.isEmpty () )
			return;
		PdfDictionary dr = new PdfDictionary ();
		acroForm.put ( PdfName.DR, dr );
		for ( PdfTemplate template : fieldTemplates ) {
			PdfFormField.mergeResources ( dr, (PdfDictionary) template.getResources () );
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
	}

	@Override
	public void close () {
		if ( open ) {
			pdf.close ();
			super.close ();
		}
	}

	public PdfIndirectReference add () {
		return null;
	}

	@Override
	public void addAnnotation ( PdfAnnotation annot ) {
	}

	@Override
	void add ( PdfPage page, PdfContents contents ) throws PdfException {
	}

	@Override
	public void freeReader ( PdfReader reader ) throws IOException {
		PdfArray array = reader.trailer.getAsArray ( PdfName.ID );
		if ( array != null )
			originalFileID = array.getAsString ( 0 ).getBytes ();
		indirectMap.remove ( reader );
		currentPdfReaderInstance = null;
		super.freeReader ( reader );
	}

	protected PdfName getOffStateName () {
		return PdfName.Off;
	}

	static class IndirectReferences {

		PdfIndirectReference theRef;

		boolean hasCopied;

		IndirectReferences ( PdfIndirectReference ref ) {
			theRef = ref;
			hasCopied = false;
		}

		void setCopied () {
			hasCopied = true;
		}

		void setNotCopied () {
			hasCopied = false;
		}

		boolean getCopied () {
			return hasCopied;
		}

		PdfIndirectReference getRef () {
			return theRef;
		}

		@Override
		public String toString () {
			String ext = "";
			if ( hasCopied )
				ext += " Copied";
			return getRef () + ext;
		}
	}


	protected static class ImportedPage {

		int pageNumber;

		PdfReader reader;

		PdfArray mergedFields;

		ImportedPage ( PdfReader reader, int pageNumber, boolean keepFields ) {
			this.pageNumber = pageNumber;
			this.reader = reader;
			if ( keepFields ) {
				mergedFields = new PdfArray ();
			}
		}

		@Override
		public boolean equals ( Object o ) {
			if ( !( o instanceof ImportedPage ) )
				return false;
			ImportedPage other = (ImportedPage) o;
			return this.pageNumber == other.pageNumber && this.reader.equals ( other.reader );
		}

		@Override
		public String toString () {
			return Integer.toString ( pageNumber );
		}
	}

}
