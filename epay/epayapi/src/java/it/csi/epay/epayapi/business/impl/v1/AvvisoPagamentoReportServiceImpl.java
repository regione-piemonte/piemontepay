/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.impl.v1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epayapi.business.v1.AvvisoPagamentoReportService;
import it.csi.epay.epayapi.integration.dto.EpayTPdfReportDTO;
import it.csi.epay.epayservices.interfaces.ejb.v1.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.model.DatiPDFReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;


@Service
public class AvvisoPagamentoReportServiceImpl implements AvvisoPagamentoReportService {

	private static final Logger logger = LogManager.getLogger ( AvvisoPagamentoReportService.class );

	private static final String CLASSNAME = AvvisoPagamentoReportServiceImpl.class.getSimpleName ();

	private static final String CODICE_AVVISO_ANALOGICO = "AA01";

	private EpayTPdfReportDTO epayTPdfReportDTO;

    @Autowired
    private ChiamataEsternaSincronaSplitFacade chiamataEsternaSincronaSplitV1;
    
	@PostConstruct
	protected void initialize () throws Exception {
		String methodName = "initialize";
		try {
			DatiPDFReport avvisoPagamentoReport = chiamataEsternaSincronaSplitV1.ricercaTemplatePDFStampaAvviso ( CODICE_AVVISO_ANALOGICO );
			if(null == avvisoPagamentoReport) {
				logger.error ( "Nessun report trovato per codice: ".concat ( CODICE_AVVISO_ANALOGICO ) );
				throw new RuntimeException ( "Nessun report trovato per codice:".concat ( CODICE_AVVISO_ANALOGICO ) );
			   }
			if ( avvisoPagamentoReport.getTemplateCompilato () == null ) {
				logger.info ( "Avvio della compilazione del report trovato con codice: ".concat ( CODICE_AVVISO_ANALOGICO ) );
				try ( InputStream template = new ByteArrayInputStream ( avvisoPagamentoReport.getTemplate () );
								ByteArrayOutputStream outputStream = new ByteArrayOutputStream () ) {
					JasperCompileManager.compileReportToStream ( template, outputStream );
					avvisoPagamentoReport.setTemplateCompilato ( outputStream.toByteArray () );
					chiamataEsternaSincronaSplitV1.aggiornaTemplateCompilato ( avvisoPagamentoReport.getId (), outputStream.toByteArray () );
					logger.info ( "Compilazione del report terminata con successo" );
				} catch ( JRException e ) {
					logger.error ( "Errore imprevisto durante la compilazione del template  " + CODICE_AVVISO_ANALOGICO
									+ ": " + CLASSNAME + "::" + methodName, e );
					throw new RuntimeException (
									"Errore imprevisto durante la compilazione del template: " + CLASSNAME + "::" + methodName,
									e );
				}
			} else {
				logger.info ( "Report trovato per codice: ".concat ( CODICE_AVVISO_ANALOGICO ) + " gia' compilato" );
			}
			epayTPdfReportDTO = new EpayTPdfReportDTO ();
			epayTPdfReportDTO.setCodice ( avvisoPagamentoReport.getCodice () );
			epayTPdfReportDTO.setId ( avvisoPagamentoReport.getId () );
			epayTPdfReportDTO.setNomeTemplate ( avvisoPagamentoReport.getNomeTemplate () );
			epayTPdfReportDTO.setTemplateCompilato ( avvisoPagamentoReport.getTemplateCompilato () );
			epayTPdfReportDTO.setTemplate ( new String(avvisoPagamentoReport.getTemplate (), StandardCharsets.UTF_8 ) );
			
		} catch ( RuntimeException | IOException e ) {
			throw new RuntimeException ( e );
		}
	}

	@Override public EpayTPdfReportDTO getJasperReport () {
		if ( epayTPdfReportDTO == null ) {
			logger.error ( "Il template richiesto non presente  " + CODICE_AVVISO_ANALOGICO + ":" + CLASSNAME
							+ "::getJasperReport" );
			throw new RuntimeException ( "Il template richiesto non presente  " + CODICE_AVVISO_ANALOGICO + ":"
							+ CLASSNAME + "::getJasperReport" );
		}
		return epayTPdfReportDTO;
	}
}
