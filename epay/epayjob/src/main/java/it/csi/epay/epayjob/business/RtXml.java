/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class RtXml {
	
	Document doc;
	
	protected enum XmlField {
		msgRichiesta,
		cfEnte,
		nomeEnte,
		nomePSP,
		dataOra,
		cfPagatore,
		nomePagatore,
		importo,
		iuv,
		codiceEsitoPagamento,
		
		iur,
		esitoSingoloPagamento,
		dataEsitoSingoloPagamento,
		commissioniApplicate
	}


	private final Map<XmlField, XPathExpression> xPathExpressions = new HashMap<> ();
	
	public RtXml() {
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			xPathExpressions.put(XmlField.msgRichiesta, xPath.compile("/RT/riferimentoMessaggioRichiesta"));
			xPathExpressions.put(XmlField.cfEnte, xPath.compile("/RT/enteBeneficiario/identificativoUnivocoBeneficiario/codiceIdentificativoUnivoco"));
			xPathExpressions.put(XmlField.nomeEnte, xPath.compile("/RT/enteBeneficiario/denominazioneBeneficiario"));
			xPathExpressions.put(XmlField.nomePSP, xPath.compile("/RT/istitutoAttestante/denominazioneAttestante"));
			xPathExpressions.put(XmlField.dataOra, xPath.compile("/RT/dataOraMessaggioRicevuta"));
			xPathExpressions.put(XmlField.cfPagatore, xPath.compile("/RT/soggettoPagatore/identificativoUnivocoPagatore/codiceIdentificativoUnivoco"));
			xPathExpressions.put(XmlField.nomePagatore, xPath.compile("/RT/soggettoPagatore/anagraficaPagatore"));
			xPathExpressions.put(XmlField.importo, xPath.compile("/RT/datiPagamento/importoTotalePagato"));
			xPathExpressions.put(XmlField.iuv, xPath.compile("/RT/datiPagamento/identificativoUnivocoVersamento"));
			xPathExpressions.put(XmlField.codiceEsitoPagamento, xPath.compile("/RT/datiPagamento/codiceEsitoPagamento"));
			xPathExpressions.put(XmlField.iur, xPath.compile("/RT/datiPagamento/datiSingoloPagamento/identificativoUnivocoRiscossione"));
			xPathExpressions.put(XmlField.esitoSingoloPagamento, xPath.compile("/RT/datiPagamento/datiSingoloPagamento/esitoSingoloPagamento"));
			xPathExpressions.put(XmlField.dataEsitoSingoloPagamento, xPath.compile("/RT/datiPagamento/datiSingoloPagamento/dataEsitoSingoloPagamento"));
			xPathExpressions.put(XmlField.commissioniApplicate, xPath.compile("/RT/datiPagamento/datiSingoloPagamento/commissioniApplicatePSP"));
		} catch (XPathExpressionException e) {
			System.out.println( Arrays.toString ( e.getStackTrace () ) );
			System.exit(-2);
		}
	}

	protected void setDoc(byte[] rt_xml) throws SAXException, IOException, ParserConfigurationException {
		this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(rt_xml));
	}
	
	protected String getNode(XmlField nome) throws XPathExpressionException {
		if (doc == null) return null;
		Element element = (Element) xPathExpressions.get(nome).evaluate(doc, XPathConstants.NODE);
		return element.getTextContent();
	}
	
	protected String getNodeOrNull() throws XPathExpressionException {
        if (doc == null) return null;
        Element element = (Element) xPathExpressions.get( XmlField.dataEsitoSingoloPagamento ).evaluate(doc, XPathConstants.NODE);
        if (element != null)
        {
            return element.getTextContent();
        }
         return  null ;
    }
	
	protected String getNodes(XmlField nome) throws XPathExpressionException {
		if (doc == null) return null;
		NodeList  nodes = (NodeList) xPathExpressions.get(nome).evaluate(doc, XPathConstants.NODESET);
		StringBuilder sb = new StringBuilder ();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (i != 0) {
				sb.append(" - ");
			}
			sb.append( nodes.item(i).getTextContent());
		}
		return sb.toString();
	}
	
	protected BigDecimal getNodesBigDecimal() throws XPathExpressionException {
		if (doc == null) return null;
		NodeList  nodes = (NodeList) xPathExpressions.get( XmlField.commissioniApplicate ).evaluate(doc, XPathConstants.NODESET);
		BigDecimal bd = new BigDecimal(0);
		for (int i = 0; i < nodes.getLength(); i++) {
			String text = nodes.item(i).getTextContent();
			bd = bd.add(new BigDecimal(text));
		}
		return bd;
	}
	
	protected String getFirstNode() throws XPathExpressionException {
		if (doc == null) return null;
		NodeList  nodes = (NodeList) xPathExpressions.get( XmlField.dataEsitoSingoloPagamento ).evaluate(doc, XPathConstants.NODESET);
		if (nodes.getLength() == 0) {
			return null;
		} else {
			return nodes.item(0).getTextContent();
		}
	}
}
