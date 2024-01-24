/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import it.csi.epay.epayservices.integration.db.manager.RtManager;
import it.csi.epay.epayservices.model.Rt;

@Singleton
@Startup
public class TimerServiceBean extends _BaseBean {

	private static final String TIMER_PROPERTIES = "META-INF/timer.properties";
	private static final String MAILRT_PROPERTIES = "META-INF/mailRT.properties";

	@EJB
	private RtManager rtManager;

	private static Properties properties;
	private static Boolean flagGenerateRtPdf;
	private static Boolean flagInviaMailRicezioneRt;

	private static Map<String, XPathExpression> xPathExpressions = new HashMap<>();

	private static Properties mailRows;

	@PostConstruct
	private void init() {
		final String methodName = "init";

		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(TIMER_PROPERTIES)) {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Entry<Object, Object> property : properties.entrySet()) {
			String message = "Property " + property.getKey() + " values " + property.getValue();
			log.info(methodName, message);
		}

		flagGenerateRtPdf = Boolean.parseBoolean((String) properties.get("generateRtPdf"));
		flagInviaMailRicezioneRt = Boolean.parseBoolean((String) properties.get("inviaMailRicezioneRt"));

		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			xPathExpressions.put("msgRichiesta", xPath.compile("/RT/riferimentoMessaggioRichiesta"));
			xPathExpressions.put("cfEnte", xPath.compile("/RT/enteBeneficiario/identificativoUnivocoBeneficiario/codiceIdentificativoUnivoco"));
			xPathExpressions.put("nomeEnte", xPath.compile("/RT/enteBeneficiario/denominazioneBeneficiario"));
			xPathExpressions.put("nomePSP", xPath.compile("/RT/istitutoAttestante/denominazioneAttestante"));
			xPathExpressions.put("dataOra", xPath.compile("/RT/dataOraMessaggioRicevuta"));
			xPathExpressions.put("cfPagatore", xPath.compile("/RT/soggettoPagatore/identificativoUnivocoPagatore/codiceIdentificativoUnivoco"));
			xPathExpressions.put("nomePagatore", xPath.compile("/RT/soggettoPagatore/anagraficaPagatore"));
			xPathExpressions.put("importo", xPath.compile("/RT/datiPagamento/importoTotalePagato"));
			xPathExpressions.put("iuv", xPath.compile("/RT/datiPagamento/identificativoUnivocoVersamento"));
			xPathExpressions.put("codiceEsitoPagamento", xPath.compile("/RT/datiPagamento/codiceEsitoPagamento"));
			xPathExpressions.put("iur", xPath.compile("/RT/datiPagamento/datiSingoloPagamento/identificativoUnivocoRiscossione"));
			xPathExpressions.put("esitoSingoloPagamento", xPath.compile("/RT/datiPagamento/datiSingoloPagamento/esitoSingoloPagamento"));
			xPathExpressions.put("dataEsitoSingoloPagamento", xPath.compile("/RT/datiPagamento/datiSingoloPagamento/dataEsitoSingoloPagamento"));
		} catch (XPathExpressionException e) {
			log.error("init", e.getStackTrace());
		}

		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(MAILRT_PROPERTIES)) {
			mailRows = new Properties();
			mailRows.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Schedule(minute = "*/1", hour = "3-23,00-1", persistent = false)// not supported
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void generatePdfRt() {
		final String methodName = "generatePdfRt";

		if (!flagGenerateRtPdf) {
			return;
		}

		synchronized (this) {
            log.debug ( methodName, "Executed  at " + new Date () );
            List<Rt> rtList = rtManager.elencoRtSenzaRicevutaPdf();
			for (Rt rt : rtList) {
                rtManager.generatePdfRt(rt, xPathExpressions, flagInviaMailRicezioneRt, mailRows);
			}
		}
	}

}
