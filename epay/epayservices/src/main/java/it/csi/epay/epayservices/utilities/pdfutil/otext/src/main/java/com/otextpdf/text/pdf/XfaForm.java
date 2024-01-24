/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.XmlDomWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;


public class XfaForm {

	public static final String XFA_DATA_SCHEMA = "http://www.xfa.org/schema/xfa-data/1.0/";

	private final boolean xfaPresent;

	private Node templateNode;

	private Xml2SomDatasets datasetsSom;

	private Node datasetsNode;

	private AcroFieldsSearch acroFieldsSom;

	private PdfReader reader;

	private Document domDocument;

	private boolean changed;

	public XfaForm ( PdfReader reader ) throws IOException, ParserConfigurationException, SAXException {
		this.reader = reader;
		PdfObject xfa = getXfaObject ( reader );
		if ( xfa == null ) {
			xfaPresent = false;
			return;
		}
		xfaPresent = true;
		ByteArrayOutputStream bout = new ByteArrayOutputStream ();
		if ( xfa.isArray () ) {
			PdfArray ar = (PdfArray) xfa;
			for ( int k = 1; k < ar.size (); k += 2 ) {
				PdfObject ob = ar.getDirectObject ( k );
				if ( ob instanceof PRStream ) {
					byte[] b = PdfReader.getStreamBytes ( (PRStream) ob );
					bout.write ( b );
				}
			}
		} else if ( xfa instanceof PRStream ) {
			byte[] b = PdfReader.getStreamBytes ( (PRStream) xfa );
			bout.write ( b );
		}
		bout.close ();
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance ();
		fact.setNamespaceAware ( true );
		DocumentBuilder db = fact.newDocumentBuilder ();
		domDocument = db.parse ( new ByteArrayInputStream ( bout.toByteArray () ) );
		extractNodes ();
	}

	public static PdfObject getXfaObject ( PdfReader reader ) {
		PdfDictionary af = (PdfDictionary) PdfReader.getPdfObjectRelease ( reader.getCatalog ().get ( PdfName.ACROFORM ) );
		if ( af == null ) {
			return null;
		}
		return PdfReader.getPdfObjectRelease ( af.get ( PdfName.XFA ) );
	}

	public static Map<String, Node> extractXFANodes ( Document domDocument ) {
		Map<String, Node> xfaNodes = new HashMap<> ();
		Node n = domDocument.getFirstChild ();
		while ( n.getChildNodes ().getLength () == 0 ) {
			n = n.getNextSibling ();
		}
		n = n.getFirstChild ();
		while ( n != null ) {
			if ( n.getNodeType () == Node.ELEMENT_NODE ) {
				String s = n.getLocalName ();
				xfaNodes.put ( s, n );
			}
			n = n.getNextSibling ();
		}

		return xfaNodes;
	}

	public static void setXfa ( XfaForm form, PdfReader reader, PdfWriter writer ) throws IOException {
		PdfDictionary af = (PdfDictionary) PdfReader.getPdfObjectRelease ( reader.getCatalog ().get ( PdfName.ACROFORM ) );
		if ( af == null ) {
			return;
		}
		PdfObject xfa = getXfaObject ( reader );
		assert xfa != null;
		if ( xfa.isArray () ) {
			PdfArray ar = (PdfArray) xfa;
			int t = -1;
			int d = -1;
			for ( int k = 0; k < ar.size (); k += 2 ) {
				PdfString s = ar.getAsString ( k );
				if ( "template".equals ( s.toString () ) ) {
					t = k + 1;
				}
				if ( "datasets".equals ( s.toString () ) ) {
					d = k + 1;
				}
			}
			if ( t > -1 && d > -1 ) {
				reader.killXref ( ar.getAsIndirectObject ( t ) );
				reader.killXref ( ar.getAsIndirectObject ( d ) );
				PdfStream tStream = new PdfStream ( serializeDoc ( form.templateNode ) );
				tStream.flateCompress ( writer.getCompressionLevel () );
				ar.set ( t, writer.addToBody ( tStream ).getIndirectReference () );
				PdfStream dStream = new PdfStream ( serializeDoc ( form.datasetsNode ) );
				dStream.flateCompress ( writer.getCompressionLevel () );
				ar.set ( d, writer.addToBody ( dStream ).getIndirectReference () );
				af.put ( PdfName.XFA, new PdfArray ( ar ) );
				return;
			}
		}
		reader.killXref ( af.get ( PdfName.XFA ) );
		PdfStream str = new PdfStream ( serializeDoc ( form.domDocument ) );
		str.flateCompress ( writer.getCompressionLevel () );
		PdfIndirectReference ref = writer.addToBody ( str ).getIndirectReference ();
		af.put ( PdfName.XFA, ref );
	}

	public static byte[] serializeDoc ( Node n ) throws IOException {
		XmlDomWriter xw = new XmlDomWriter ();
		ByteArrayOutputStream fout = new ByteArrayOutputStream ();
		xw.setOutput ( fout, null );
		xw.setCanonical ( false );
		xw.write ( n );
		fout.close ();
		return fout.toByteArray ();
	}

	public static String getNodeText ( Node n ) {
		if ( n == null )
			return "";
		return getNodeText ( n, "" );

	}

	private static String getNodeText ( Node n, String name ) {
		Node n2 = n.getFirstChild ();
		while ( n2 != null ) {
			if ( n2.getNodeType () == Node.ELEMENT_NODE ) {
				name = getNodeText ( n2, name );
			} else if ( n2.getNodeType () == Node.TEXT_NODE ) {
				name += n2.getNodeValue ();
			}
			n2 = n2.getNextSibling ();
		}
		return name;
	}

	private void extractNodes () {
		Map<String, Node> xfaNodes = extractXFANodes ( domDocument );

		if ( xfaNodes.containsKey ( "template" ) ) {
			templateNode = xfaNodes.get ( "template" );
			new Xml2SomTemplate ( templateNode );
		}
		if ( xfaNodes.containsKey ( "datasets" ) ) {
			datasetsNode = xfaNodes.get ( "datasets" );
			datasetsSom = new Xml2SomDatasets ( datasetsNode.getFirstChild () );
		}
		if ( datasetsNode == null )
			createDatasetsNode ( domDocument.getFirstChild () );
	}

	private void createDatasetsNode ( Node n ) {
		while ( n.getChildNodes ().getLength () == 0 ) {
			n = n.getNextSibling ();
		}
		Element e = n.getOwnerDocument ().createElement ( "xfa:datasets" );
		e.setAttribute ( "xmlns:xfa", XFA_DATA_SCHEMA );
		datasetsNode = e;
		n.appendChild ( datasetsNode );
	}

	public void setXfa ( PdfWriter writer ) throws IOException {
		setXfa ( this, reader, writer );
	}

	public boolean isXfaPresent () {
		return xfaPresent;
	}

	public String findFieldName ( String name, AcroFields af ) {
		Map<String, AcroFields.Item> items = af.getFields ();
		if ( items.containsKey ( name ) )
			return name;
		if ( acroFieldsSom == null ) {
			if ( items.isEmpty () && xfaPresent )
				acroFieldsSom = new AcroFieldsSearch ( datasetsSom.getName2Node ().keySet () );
			else
				acroFieldsSom = new AcroFieldsSearch ( items.keySet () );
		}
		if ( acroFieldsSom.getAcroShort2LongName ().containsKey ( name ) )
			return acroFieldsSom.getAcroShort2LongName ().get ( name );
		return acroFieldsSom.inverseSearchGlobal ( Xml2Som.splitParts ( name ) );
	}

	public String findDatasetsName ( String name ) {
		if ( datasetsSom.getName2Node ().containsKey ( name ) )
			return name;
		return datasetsSom.inverseSearchGlobal ( Xml2Som.splitParts ( name ) );
	}

	public Node findDatasetsNode ( String name ) {
		if ( name == null )
			return null;
		name = findDatasetsName ( name );
		if ( name == null )
			return null;
		return datasetsSom.getName2Node ().get ( name );
	}

	public void setNodeText ( Node n, String text ) {
		if ( n == null )
			return;
		Node nc;
		while ( ( nc = n.getFirstChild () ) != null ) {
			n.removeChild ( nc );
		}
		if ( n.getAttributes ().getNamedItemNS ( XFA_DATA_SCHEMA, "dataNode" ) != null )
			n.getAttributes ().removeNamedItemNS ( XFA_DATA_SCHEMA, "dataNode" );
		n.appendChild ( domDocument.createTextNode ( text ) );
		changed = true;
	}

	public PdfReader getReader () {
		return reader;
	}

	public void setReader ( PdfReader reader ) {
		this.reader = reader;
	}

	public boolean isChanged () {
		return changed;
	}

	public void setChanged ( boolean changed ) {
		this.changed = changed;
	}

	public Xml2SomDatasets getDatasetsSom () {
		return datasetsSom;
	}

	public Node getDatasetsNode () {
		return datasetsNode;
	}

	public static class InverseStore {

		protected ArrayList<String> part = new ArrayList<> ();

		protected ArrayList<Object> follow = new ArrayList<> ();

		public String getDefaultName () {
			InverseStore store = this;
			while ( true ) {
				Object obj = store.follow.get ( 0 );
				if ( obj instanceof String )
					return (String) obj;
				store = (InverseStore) obj;
			}
		}

		public boolean isSimilar ( String name ) {
			int idx = name.indexOf ( '[' );
			name = name.substring ( 0, idx + 1 );
			for ( String s : part ) {
				if ( s.startsWith ( name ) )
					return true;
			}
			return false;
		}
	}


	public static class Stack2<T> extends ArrayList<T> {

		private static final long serialVersionUID = -7451476576174095212L;

		public T peek () {
			if ( size () == 0 )
				throw new EmptyStackException ();
			return get ( size () - 1 );
		}

		public T pop () {
			if ( size () == 0 )
				throw new EmptyStackException ();
			T ret = get ( size () - 1 );
			remove ( size () - 1 );
			return ret;
		}

		public T push ( T item ) {
			add ( item );
			return item;
		}

		public boolean empty () {
			return size () == 0;
		}
	}


	public static class Xml2Som {

		protected ArrayList<String> order;

		protected HashMap<String, Node> name2Node;

		protected HashMap<String, InverseStore> inverseSearch;

		protected Stack2<String> stack;

		protected int anform;

		public static String escapeSom ( String s ) {
			if ( s == null )
				return "";
			int idx = s.indexOf ( '.' );
			if ( idx < 0 )
				return s;
			StringBuilder sb = new StringBuilder ();
			int last = 0;
			while ( idx >= 0 ) {
				sb.append ( s, last, idx );
				sb.append ( '\\' );
				last = idx;
				idx = s.indexOf ( '.', idx + 1 );
			}
			sb.append ( s.substring ( last ) );
			return sb.toString ();
		}

		public static String getShortName ( String s ) {
			int idx = s.indexOf ( ".#subform[" );
			if ( idx < 0 )
				return s;
			int last = 0;
			StringBuilder sb = new StringBuilder ();
			while ( idx >= 0 ) {
				sb.append ( s, last, idx );
				idx = s.indexOf ( "]", idx + 10 );
				if ( idx < 0 )
					return sb.toString ();
				last = idx + 1;
				idx = s.indexOf ( ".#subform[", last );
			}
			sb.append ( s.substring ( last ) );
			return sb.toString ();
		}

		public static void inverseSearchAdd ( HashMap<String, InverseStore> inverseSearch, Stack2<String> stack, String unstack ) {
			String last = stack.peek ();
			InverseStore store = inverseSearch.get ( last );
			if ( store == null ) {
				store = new InverseStore ();
				inverseSearch.put ( last, store );
			}
			for ( int k = stack.size () - 2; k >= 0; --k ) {
				last = stack.get ( k );
				InverseStore store2;
				int idx = store.part.indexOf ( last );
				if ( idx < 0 ) {
					store.part.add ( last );
					store2 = new InverseStore ();
					store.follow.add ( store2 );
				} else
					store2 = (InverseStore) store.follow.get ( idx );
				store = store2;
			}
			store.part.add ( "" );
			store.follow.add ( unstack );
		}

		public static Stack2<String> splitParts ( String name ) {
			while ( name.startsWith ( "." ) )
				name = name.substring ( 1 );
			Stack2<String> parts = new Stack2<> ();
			int last = 0;
			int pos;
			String part;
			while ( true ) {
				pos = last;
				while ( true ) {
					pos = name.indexOf ( '.', pos );
					if ( pos < 0 )
						break;
					if ( name.charAt ( pos - 1 ) == '\\' )
						++pos;
					else
						break;
				}
				if ( pos < 0 )
					break;
				part = name.substring ( last, pos );
				if ( !part.endsWith ( "]" ) )
					part += "[0]";
				parts.add ( part );
				last = pos + 1;
			}
			part = name.substring ( last );
			if ( !part.endsWith ( "]" ) )
				part += "[0]";
			parts.add ( part );
			return parts;
		}

		protected String printStack () {
			if ( stack.empty () )
				return "";
			StringBuilder s = new StringBuilder ();
			for ( String string : stack )
				s.append ( '.' ).append ( string );
			return s.substring ( 1 );
		}

		public void inverseSearchAdd ( String unstack ) {
			inverseSearchAdd ( inverseSearch, stack, unstack );
		}

		public String inverseSearchGlobal ( ArrayList<String> parts ) {
			if ( parts.isEmpty () )
				return null;
			InverseStore store = inverseSearch.get ( parts.get ( parts.size () - 1 ) );
			if ( store == null )
				return null;
			for ( int k = parts.size () - 2; k >= 0; --k ) {
				String part = parts.get ( k );
				int idx = store.part.indexOf ( part );
				if ( idx < 0 ) {
					if ( store.isSimilar ( part ) )
						return null;
					return store.getDefaultName ();
				}
				store = (InverseStore) store.follow.get ( idx );
			}
			return store.getDefaultName ();
		}

		public ArrayList<String> getOrder () {
			return order;
		}

		public void setOrder ( ArrayList<String> order ) {
			this.order = order;
		}

		public HashMap<String, Node> getName2Node () {
			return name2Node;
		}

	}


	public static class Xml2SomDatasets extends Xml2Som {

		public Xml2SomDatasets ( Node n ) {
			order = new ArrayList<> ();
			name2Node = new HashMap<> ();
			stack = new Stack2<> ();
			anform = 0;
			inverseSearch = new HashMap<> ();
			processDatasetsInternal ( n );
		}

		private static boolean hasChildren ( Node n ) {
			Node dataNodeN = n.getAttributes ().getNamedItemNS ( XFA_DATA_SCHEMA, "dataNode" );
			if ( dataNodeN != null ) {
				String dataNode = dataNodeN.getNodeValue ();
				if ( "dataGroup".equals ( dataNode ) )
					return true;
				else if ( "dataValue".equals ( dataNode ) )
					return false;
			}
			if ( !n.hasChildNodes () )
				return false;
			Node n2 = n.getFirstChild ();
			while ( n2 != null ) {
				if ( n2.getNodeType () == Node.ELEMENT_NODE ) {
					return true;
				}
				n2 = n2.getNextSibling ();
			}
			return false;
		}

		public Node insertNode ( Node n, String shortName ) {
			Stack2<String> stack = splitParts ( shortName );
			Document doc = n.getOwnerDocument ();
			Node n2 = null;
			n = n.getFirstChild ();
			while ( n.getNodeType () != Node.ELEMENT_NODE )
				n = n.getNextSibling ();
			for ( String part : stack ) {
				int idx = part.lastIndexOf ( '[' );
				String name = part.substring ( 0, idx );
				idx = Integer.parseInt ( part.substring ( idx + 1, part.length () - 1 ) );
				int found = -1;
				assert n != null;
				for ( n2 = n.getFirstChild (); n2 != null; n2 = n2.getNextSibling () ) {
					if ( n2.getNodeType () == Node.ELEMENT_NODE ) {
						String s = escapeSom ( n2.getLocalName () );
						if ( s.equals ( name ) ) {
							++found;
							if ( found == idx )
								break;
						}
					}
				}
				for ( ; found < idx; ++found ) {
					n2 = doc.createElementNS ( null, name );
					n2 = n.appendChild ( n2 );
					Node attr = doc.createAttributeNS ( XFA_DATA_SCHEMA, "dataNode" );
					attr.setNodeValue ( "dataGroup" );
					n2.getAttributes ().setNamedItemNS ( attr );
				}
				n = n2;
			}
			inverseSearchAdd ( inverseSearch, stack, shortName );
			name2Node.put ( shortName, n2 );
			order.add ( shortName );
			return n2;
		}

		private void processDatasetsInternal ( Node n ) {
			if ( n != null ) {
				HashMap<String, Integer> ss = new HashMap<> ();
				Node n2 = n.getFirstChild ();
				while ( n2 != null ) {
					if ( n2.getNodeType () == Node.ELEMENT_NODE ) {
						String s = escapeSom ( n2.getLocalName () );
						Integer i = ss.get ( s );
						if ( i == null )
							i = 0;
						else
							i = i + 1;
						ss.put ( s, i );
						if ( hasChildren ( n2 ) ) {
							stack.push ( s + "[" + i + "]" );
							processDatasetsInternal ( n2 );
							stack.pop ();
						} else {
							stack.push ( s + "[" + i + "]" );
							String unstack = printStack ();
							order.add ( unstack );
							inverseSearchAdd ( unstack );
							name2Node.put ( unstack, n2 );
							stack.pop ();
						}
					}
					n2 = n2.getNextSibling ();
				}
			}
		}
	}


	public static class AcroFieldsSearch extends Xml2Som {

		private final HashMap<String, String> acroShort2LongName;

		public AcroFieldsSearch ( Collection<String> items ) {
			inverseSearch = new HashMap<> ();
			acroShort2LongName = new HashMap<> ();
			for ( String string : items ) {
				String itemShort = getShortName ( string );
				acroShort2LongName.put ( itemShort, string );
				inverseSearchAdd ( inverseSearch, splitParts ( itemShort ), string );
			}
		}

		public HashMap<String, String> getAcroShort2LongName () {
			return acroShort2LongName;
		}

	}


	public static class Xml2SomTemplate extends Xml2Som {

		private boolean dynamicForm;

		private int templateLevel;

		public Xml2SomTemplate ( Node n ) {
			order = new ArrayList<> ();
			name2Node = new HashMap<> ();
			stack = new Stack2<> ();
			anform = 0;
			templateLevel = 0;
			inverseSearch = new HashMap<> ();
			processTemplate ( n, null );
		}

		private void processTemplate ( Node n, HashMap<String, Integer> ff ) {
			if ( ff == null )
				ff = new HashMap<> ();
			HashMap<String, Integer> ss = new HashMap<> ();
			Node n2 = n.getFirstChild ();
			while ( n2 != null ) {
				if ( n2.getNodeType () == Node.ELEMENT_NODE ) {
					String s = n2.getLocalName ();
					if ( "subform".equals ( s ) ) {
						Node name = n2.getAttributes ().getNamedItem ( "name" );
						String nn = "#subform";
						boolean annon = true;
						if ( name != null ) {
							nn = escapeSom ( name.getNodeValue () );
							annon = false;
						}
						Integer i;
						if ( annon ) {
							i = anform;
							++anform;
						} else {
							i = ss.get ( nn );
							if ( i == null )
								i = 0;
							else
								i = i + 1;
							ss.put ( nn, i );
						}
						stack.push ( nn + "[" + i + "]" );
						++templateLevel;
						if ( annon )
							processTemplate ( n2, ff );
						else
							processTemplate ( n2, null );
						--templateLevel;
						stack.pop ();
					} else if ( "field".equals ( s ) || "exclGroup".equals ( s ) ) {
						Node name = n2.getAttributes ().getNamedItem ( "name" );
						if ( name != null ) {
							String nn = escapeSom ( name.getNodeValue () );
							Integer i = ff.get ( nn );
							if ( i == null )
								i = 0;
							else
								i = i + 1;
							ff.put ( nn, i );
							stack.push ( nn + "[" + i + "]" );
							String unstack = printStack ();
							order.add ( unstack );
							inverseSearchAdd ( unstack );
							name2Node.put ( unstack, n2 );
							stack.pop ();
						}
					} else if ( !dynamicForm && templateLevel > 0 && "occur".equals ( s ) ) {
						int initial = 1;
						int min = 1;
						int max = 1;
						Node a = n2.getAttributes ().getNamedItem ( "initial" );
						if ( a != null )
							try {
								initial = Integer.parseInt ( a.getNodeValue ().trim () );
							} catch ( Exception ignored ) {
							}
						a = n2.getAttributes ().getNamedItem ( "min" );
						if ( a != null )
							try {
								min = Integer.parseInt ( a.getNodeValue ().trim () );
							} catch ( Exception ignored ) {
							}
						a = n2.getAttributes ().getNamedItem ( "max" );
						if ( a != null )
							try {
								max = Integer.parseInt ( a.getNodeValue ().trim () );
							} catch ( Exception ignored ) {
							}
						if ( initial != min || min != max )
							dynamicForm = true;
					}
				}
				n2 = n2.getNextSibling ();
			}
		}

	}

}
