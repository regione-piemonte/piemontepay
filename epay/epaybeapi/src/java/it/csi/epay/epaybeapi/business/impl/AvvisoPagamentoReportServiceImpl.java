/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.csi.epay.epaybeapi.business.AvvisoPagamentoReportService;
import it.csi.epay.epaybeapi.integration.domain.EpayTPdfReport;
import it.csi.epay.epaybeapi.integration.dto.EpayTPdfReportDTO;
import it.csi.epay.epaybeapi.integration.mapper.EpayTPdfReportMapper;
import it.csi.epay.epaybeapi.integration.repository.EpayTPdfReportRepository;
import it.csi.epay.epaybeapi.util.LogAndWatch;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

@Service
public class AvvisoPagamentoReportServiceImpl implements AvvisoPagamentoReportService {

	private static final String CLASSNAME = AvvisoPagamentoReportServiceImpl.class.getSimpleName();

	private static final String CODICE_AVVISO_ANALOGICO = "AA01";

	private static final Logger logger = LoggerFactory.getLogger(AvvisoPagamentoReportService.class);

	@Autowired
	private EpayTPdfReportRepository repository;

	@Autowired
	private EpayTPdfReportMapper mapper;

	private EpayTPdfReportDTO epayTPdfReportDTO;

	@PostConstruct
	protected void initialize() throws Exception {
		String methodName = "initialize";
		LogAndWatch lw = new LogAndWatch(logger, CLASSNAME, methodName);
		lw.start();
		try {
			EpayTPdfReport avvisoPagamentoReport = null;
			List<EpayTPdfReport> reports = repository.findByCodice(CODICE_AVVISO_ANALOGICO);
			switch (reports.size()) {
			case 0:
				logger.error("Nessun report trovato per codice: ".concat(CODICE_AVVISO_ANALOGICO));
				throw new RuntimeException("Nessun report trovato per codice:".concat(CODICE_AVVISO_ANALOGICO));
			case 1:
				logger.info("Un solo report trovato per codice: ".concat(CODICE_AVVISO_ANALOGICO));
				avvisoPagamentoReport = reports.get(0);
				break;
			default:
				logger.error("Piu report presenti con codice: ".concat(CODICE_AVVISO_ANALOGICO));
				throw new RuntimeException("Piu report presenti con codice:".concat(CODICE_AVVISO_ANALOGICO));
			}
			if (avvisoPagamentoReport.getTemplateCompilato() == null) {
				logger.info("Avvio della compilazione del report trovato con codice: ".concat(CODICE_AVVISO_ANALOGICO));
				try (InputStream template = new ByteArrayInputStream(
						avvisoPagamentoReport.getTemplate().getBytes(StandardCharsets.UTF_8));
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
					JasperCompileManager.compileReportToStream(template, outputStream);
					avvisoPagamentoReport.setTemplateCompilato(outputStream.toByteArray());
					avvisoPagamentoReport = repository.save(avvisoPagamentoReport);
					logger.info("Compilazione del report terminata con successo");
				} catch (JRException e) {
					logger.error("Errore imprevisto durante la compilazione del template  " + CODICE_AVVISO_ANALOGICO
							+ ": " + CLASSNAME + "::" + methodName, e);
					throw new RuntimeException(
							"Errore imprevisto durante la compilazione del template: " + CLASSNAME + "::" + methodName,
							e);
				}
			}else {
				logger.info("Report trovato per codice: ".concat(CODICE_AVVISO_ANALOGICO) + " gia' compilato");
			}
			epayTPdfReportDTO = mapper.toDTO(avvisoPagamentoReport);
		} finally {
			lw.stop();
		}
	}

	@Override
	public EpayTPdfReportDTO getJasperReport() {
		if (epayTPdfReportDTO == null) {
			logger.error("Il template richiesto non presente  " + CODICE_AVVISO_ANALOGICO + ":" + CLASSNAME
					+ "::getJasperReport");
			throw new RuntimeException("Il template richiesto non presente  " + CODICE_AVVISO_ANALOGICO + ":"
					+ CLASSNAME + "::getJasperReport");
		}
		return epayTPdfReportDTO;
	}
}
