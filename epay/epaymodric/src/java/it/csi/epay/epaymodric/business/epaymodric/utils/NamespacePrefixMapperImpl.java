/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.utils;

import java.io.OutputStream;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.dom.DOMResult;

import org.w3c.dom.Node;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


/**
 *
 */

/**
 * Implemented to determine URI -> prefix mapping.
 */
public class NamespacePrefixMapperImpl extends NamespacePrefixMapper {

    private static final String [] EMPTY_STRING = new String [0];

    /**
     * Returns a preferred prefix for the given namespace URI.
     *
     * This method is intended to be overrided by a derived class.
     *
     * @param namespaceUri The namespace URI for which the prefix needs to be found. Never be null. "" is used to denote the default namespace.
     * @param suggestion When the content tree has a suggestion for the prefix to the given namespaceUri, that suggestion is passed as a parameter. Typicall
     *            this value comes from the QName.getPrefix to show the preference of the content tree. This parameter may be null, and this parameter may
     *            represent an already occupied prefix.
     * @param requirePrefix If this method is expected to return non-empty prefix. When this flag is true, it means that the given namespace URI cannot be set
     *            as the default namespace.
     *
     * @return null if there's no prefered prefix for the namespace URI. In this case, the system will generate a prefix for you.
     *
     *         Otherwise the system will try to use the returned prefix, but generally there's no guarantee if the prefix will be actually used or not.
     *
     *         return "" to map this namespace URI to the default namespace. Again, there's no guarantee that this preference will be honored.
     *
     *         If this method returns "" when requirePrefix=true, the return value will be ignored and the system will generate one.
     *
     * @since JAXB 1.0.1
     */
    @Override
    public String getPreferredPrefix ( String namespaceUri, String suggestion, boolean requirePrefix ) {
        if ( namespaceUri == null || namespaceUri.equals ( "" ) ) {
            return "";
        }
        if ( namespaceUri.contains ( "rendicontazione" ) ) {
            return "";
        }
        if ( namespaceUri.contains ( "types" ) ) {
            return "ns2";
        }
        return suggestion;
    }

    /**
     * Returns a list of namespace URIs that should be declared at the root element.
     *
     * <p>
     * By default, the JAXB RI 1.0.x produces namespace declarations only when they are necessary, only at where they are used. Because of this lack of
     * look-ahead, sometimes the marshaller produces a lot of namespace declarations that look redundant to human eyes. For example,
     *
     * <pre>
     * <xmp>
     * <?xml version="1.0"?>
     * <root>
     *   <ns1:child xmlns:ns1="urn:foo"> ... </ns1:child>
     *   <ns2:child xmlns:ns2="urn:foo"> ... </ns2:child>
     *   <ns3:child xmlns:ns3="urn:foo"> ... </ns3:child>
     *   ...
     * </root>
     * <xmp>
     * </pre>
     *
     * <p>
     * The JAXB RI 2.x mostly doesn't exhibit this behavior any more, as it declares all statically known namespace URIs (those URIs that are used as
     * element/attribute names in JAXB annotations), but it may still declare additional namespaces in the middle of a document, for example when (i) a QName as
     * an attribute/element value requires a new namespace URI, or (ii) DOM nodes as a portion of an object tree requires a new namespace URI.
     *
     * <p>
     * If you know in advance that you are going to use a certain set of namespace URIs, you can override this method and have the marshaller declare those
     * namespace URIs at the root element.
     *
     * <p>
     * For example, by returning <code>new String[]{"urn:foo"}</code>, the marshaller will produce:
     *
     * <pre>
     * <xmp>
     * <?xml version="1.0"?>
     * <root xmlns:ns1="urn:foo">
     *   <ns1:child> ... </ns1:child>
     *   <ns1:child> ... </ns1:child>
     *   <ns1:child> ... </ns1:child>
     *   ...
     * </root>
     * <xmp>
     * </pre>
     * <p>
     * To control prefixes assigned to those namespace URIs, use the {@link #getPreferredPrefix(String, String, boolean)} method.
     *
     * @return A list of namespace URIs as an array of {@link String}s. This method can return a length-zero array but not null. None of the array component can
     *         be null. To represent the empty namespace, use the empty string <code>""</code>.
     *
     * @since JAXB RI 1.0.2
     */
    @Override
    public String [] getPreDeclaredNamespaceUris () {
        return EMPTY_STRING;
    }

    /**
     * Similar to {@link #getPreDeclaredNamespaceUris()} but allows the (prefix,nsUri) pairs to be returned.
     *
     * <p>
     * With {@link #getPreDeclaredNamespaceUris()}, applications who wish to control the prefixes as well as the namespaces needed to implement both
     * {@link #getPreDeclaredNamespaceUris()} and {@link #getPreferredPrefix(String, String, boolean)}.
     *
     * <p>
     * This version eliminates the needs by returning an array of pairs.
     *
     * @return always return a non-null (but possibly empty) array. The array stores data like (prefix1,nsUri1,prefix2,nsUri2,...) Use an empty string to
     *         represent the empty namespace URI and the default prefix. Null is not allowed as a value in the array.
     *
     * @since JAXB RI 2.0 beta
     */
    @Override
    public String [] getPreDeclaredNamespaceUris2 () {
        return EMPTY_STRING;
    }

    /**
     * Returns a list of (prefix,namespace URI) pairs that represents namespace bindings available on ancestor elements (that need not be repeated by the JAXB
     * RI.)
     *
     * <p>
     * Sometimes JAXB is used to marshal an XML document, which will be used as a subtree of a bigger document. When this happens, it's nice for a JAXB
     * marshaller to be able to use in-scope namespace bindings of the larger document and avoid declaring redundant namespace URIs.
     *
     * <p>
     * This is automatically done when you are marshalling to {@link XMLStreamWriter}, {@link XMLEventWriter}, {@link DOMResult}, or {@link Node}, because those
     * output format allows us to inspect what's currently available as in-scope namespace binding. However, with other output format, such as
     * {@link OutputStream}, the JAXB RI cannot do this automatically. That's when this method comes into play.
     *
     * <p>
     * Namespace bindings returned by this method will be used by the JAXB RI, but will not be re-declared. They are assumed to be available when you insert
     * this subtree into a bigger document.
     *
     * <p>
     * It is <b>NOT</b> OK to return the same binding, or give the receiver a conflicting binding information. It's a responsibility of the caller to make sure
     * that this doesn't happen even if the ancestor elements look like:
     *
     * <pre>
     * <xmp>
     *   <foo:abc xmlns:foo="abc">
     *     <foo:abc xmlns:foo="def">
     *       <foo:abc xmlns:foo="abc">
     *         ... JAXB marshalling into here.
     *       </foo:abc>
     *     </foo:abc>
     *   </foo:abc>
     * </xmp>
     * </pre>
     *
     * <!-- TODO: shall we relax this constraint? -->
     *
     * @return always return a non-null (but possibly empty) array. The array stores data like (prefix1,nsUri1,prefix2,nsUri2,...) Use an empty string to
     *         represent the empty namespace URI and the default prefix. Null is not allowed as a value in the array.
     *
     * @since JAXB RI 2.0 beta
     */
    @Override
    public String [] getContextualNamespaceDecls () {
        return EMPTY_STRING;
    }

}
