/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.util.Map;


public class PdfStructTreeController {

	private final PdfCopy writer;

	private final PdfStructureTreeRoot structureTreeRoot;

	protected PdfReader reader;

	private PdfDictionary structTreeRoot;

	private PdfDictionary roleMap = null;

	private PdfDictionary sourceRoleMap = null;

	private PdfDictionary sourceClassMap = null;

	protected PdfStructTreeController ( PdfReader reader, PdfCopy writer ) throws BadPdfFormatException {
		if ( !writer.isTagged () )
			throw new BadPdfFormatException ( MessageLocalization.getComposedMessage ( "no.structtreeroot.found" ) );
		this.writer = writer;
		structureTreeRoot = writer.getStructureTreeRoot ();
		structureTreeRoot.put ( PdfName.PARENTTREE, new PdfDictionary ( PdfName.STRUCTELEM ) );
		setReader ( reader );
	}

	public static PdfObject getDirectObject ( PdfObject object ) {
		if ( object == null )
			return null;
		while ( object.isIndirect () )
			object = PdfReader.getPdfObjectRelease ( object );
		return object;
	}

	static PdfDictionary getKDict ( PdfDictionary obj ) {
		PdfDictionary k = obj.getAsDict ( PdfName.K );
		if ( k != null ) {
			if ( PdfName.OBJR.equals ( k.getAsName ( PdfName.TYPE ) ) ) {
				return k;
			}
		} else {
			PdfArray k1 = obj.getAsArray ( PdfName.K );
			if ( k1 == null )
				return null;
			for ( int i = 0; i < k1.size (); i++ ) {
				k = k1.getAsDict ( i );
				if ( k != null ) {
					if ( PdfName.OBJR.equals ( k.getAsName ( PdfName.TYPE ) ) ) {
						return k;
					}
				}
			}
		}
		return null;
	}

	private static PdfArray getDirectArray ( PdfArray in ) {
		PdfArray out = new PdfArray ();
		for ( int i = 0; i < in.size (); ++i ) {
			PdfObject value = getDirectObject ( in.getPdfObject ( i ) );
			if ( value == null )
				continue;
			if ( value.isArray () ) {
				out.add ( getDirectArray ( (PdfArray) value ) );
			} else if ( value.isDictionary () ) {
				out.add ( getDirectDict ( (PdfDictionary) value ) );
			} else {
				out.add ( value );
			}
		}
		return out;
	}

	private static PdfDictionary getDirectDict ( PdfDictionary in ) {
		PdfDictionary out = new PdfDictionary ();
		for ( Map.Entry<PdfName, PdfObject> entry : in.hashMap.entrySet () ) {
			PdfObject value = getDirectObject ( entry.getValue () );
			if ( value == null )
				continue;
			if ( value.isArray () ) {
				out.put ( entry.getKey (), getDirectArray ( (PdfArray) value ) );
			} else if ( value.isDictionary () ) {
				out.put ( entry.getKey (), getDirectDict ( (PdfDictionary) value ) );
			} else {
				out.put ( entry.getKey (), value );
			}
		}
		return out;
	}

	public static boolean compareObjects ( PdfObject value1, PdfObject value2 ) {
		value2 = getDirectObject ( value2 );
		if ( value2 == null )
			return true;
		if ( value1.type () != value2.type () )
			return true;

		if ( value1.isBoolean () ) {
			if ( value1 == value2 )
				return false;
			if ( value2 instanceof PdfBoolean ) {
				return ( (PdfBoolean) value1 ).booleanValue () != ( (PdfBoolean) value2 ).booleanValue ();
			}
			return true;
		} else if ( value1.isName () ) {
			return !value1.equals ( value2 );
		} else if ( value1.isNumber () ) {
			if ( value1 == value2 )
				return false;
			if ( value2 instanceof PdfNumber ) {
				return ( (PdfNumber) value1 ).doubleValue () != ( (PdfNumber) value2 ).doubleValue ();
			}
			return true;
		} else if ( value1.isNull () ) {
			if ( value1 == value2 )
				return false;
			return !( value2 instanceof PdfNull );
		} else if ( value1.isString () ) {
			if ( value1 == value2 )
				return false;
			if ( value2 instanceof PdfString ) {
				return ( ( ( (PdfString) value2 ).value != null || ( (PdfString) value1 ).value != null )
								&& ( ( (PdfString) value1 ).value == null || !( (PdfString) value1 ).value.equals ( ( (PdfString) value2 ).value ) ) );
			}
			return true;
		}
		if ( value1.isArray () ) {
			PdfArray array1 = (PdfArray) value1;
			PdfArray array2 = (PdfArray) value2;
			if ( array1.size () != array2.size () )
				return true;
			for ( int i = 0; i < array1.size (); ++i )
				if ( compareObjects ( array1.getPdfObject ( i ), array2.getPdfObject ( i ) ) )
					return true;
			return false;
		}
		if ( value1.isDictionary () ) {
			PdfDictionary first = (PdfDictionary) value1;
			PdfDictionary second = (PdfDictionary) value2;
			if ( first.size () != second.size () )
				return true;
			for ( PdfName name : first.hashMap.keySet () ) {
				if ( compareObjects ( first.get ( name ), second.get ( name ) ) )
					return true;
			}
			return false;
		}
		return true;
	}

	protected void setReader ( PdfReader reader ) throws BadPdfFormatException {
		this.reader = reader;
		PdfObject obj = reader.getCatalog ().get ( PdfName.STRUCTTREEROOT );
		obj = getDirectObject ( obj );
		if ( ( obj == null ) || ( !obj.isDictionary () ) )
			throw new BadPdfFormatException ( MessageLocalization.getComposedMessage ( "no.structtreeroot.found" ) );
		structTreeRoot = (PdfDictionary) obj;
		obj = PdfStructTreeController.getDirectObject ( structTreeRoot.get ( PdfName.PARENTTREE ) );
		if ( obj == null || !obj.isDictionary () )
			throw new BadPdfFormatException ( MessageLocalization.getComposedMessage ( "the.document.does.not.contain.parenttree" ) );
		sourceRoleMap = null;
		sourceClassMap = null;
	}

	protected void addClass ( PdfObject object ) throws BadPdfFormatException {
		object = getDirectObject ( object );
		if ( object.isDictionary () ) {
			PdfObject curClass = ( (PdfDictionary) object ).get ( PdfName.C );
			if ( curClass == null )
				return;
			if ( curClass.isArray () ) {
				PdfArray array = (PdfArray) curClass;
				for ( int i = 0; i < array.size (); ++i ) {
					addClass ( array.getPdfObject ( i ) );
				}
			} else if ( curClass.isName () )
				addClass ( curClass );
		} else if ( object.isName () ) {
			PdfName name = (PdfName) object;
			if ( sourceClassMap == null ) {
				object = getDirectObject ( structTreeRoot.get ( PdfName.CLASSMAP ) );
				if ( object == null || !object.isDictionary () ) {
					return;
				}
				sourceClassMap = (PdfDictionary) object;
			}
			object = getDirectObject ( sourceClassMap.get ( name ) );
			if ( object == null ) {
				return;
			}
			PdfObject put = structureTreeRoot.getMappedClass ( name );
			if ( put != null ) {
				if ( compareObjects ( put, object ) ) {
					throw new BadPdfFormatException ( MessageLocalization.getComposedMessage ( "conflict.in.classmap", name ) );
				}
			} else {
				if ( object.isDictionary () )
					structureTreeRoot.mapClass ( name, getDirectDict ( (PdfDictionary) object ) );
				else if ( object.isArray () ) {
					structureTreeRoot.mapClass ( name, getDirectArray ( (PdfArray) object ) );
				}
			}
		}
	}

	protected void addRole ( PdfName structType ) throws BadPdfFormatException {
		if ( structType == null ) {
			return;
		}
		for ( PdfName name : writer.getStandardStructElems () ) {
			if ( name.equals ( structType ) )
				return;
		}
		if ( sourceRoleMap == null ) {
			PdfObject object = getDirectObject ( structTreeRoot.get ( PdfName.ROLEMAP ) );
			if ( object == null || !object.isDictionary () ) {
				return;
			}
			sourceRoleMap = (PdfDictionary) object;
		}
		PdfObject object = sourceRoleMap.get ( structType );
		if ( object == null || !object.isName () ) {
			return;
		}
		PdfObject currentRole;
		if ( roleMap == null ) {
			roleMap = new PdfDictionary ();
			structureTreeRoot.put ( PdfName.ROLEMAP, roleMap );
			roleMap.put ( structType, object );
		} else if ( ( currentRole = roleMap.get ( structType ) ) != null ) {
			if ( !currentRole.equals ( object ) ) {
				throw new BadPdfFormatException ( MessageLocalization.getComposedMessage ( "conflict.in.rolemap", object ) );
			}
		} else {
			roleMap.put ( structType, object );
		}
	}

}
