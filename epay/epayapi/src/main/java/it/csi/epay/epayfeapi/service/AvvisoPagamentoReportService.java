/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTPdfReport;
import it.csi.epay.epayfeapi.repository.PdfReportRepository;
import net.sf.jasperreports.engine.JasperCompileManager;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


@ApplicationScoped
@Transactional
public class AvvisoPagamentoReportService {

	private static final String CODICE_AVVISO_ANALOGICO = "AA01";

	private EpayTPdfReport epayTPdfReport;

	@Inject
	PdfReportRepository pdfReportRepository;

	@PostConstruct
	protected void initialize () {
		String methodName = "initialize";
		try {
			EpayTPdfReport avvisoPagamentoReport = pdfReportRepository.findByCodice ( CODICE_AVVISO_ANALOGICO );
			if ( null == avvisoPagamentoReport ) {
				Log.error ( "Nessun report trovato per codice: ".concat ( CODICE_AVVISO_ANALOGICO ) );
				throw new RuntimeException ( "Nessun report trovato per codice:".concat ( CODICE_AVVISO_ANALOGICO ) );
			}
			if ( avvisoPagamentoReport.getTemplateCompilato () == null ) {
				Log.info ( "Avvio della compilazione del report trovato con codice: ".concat ( CODICE_AVVISO_ANALOGICO ) );
				try ( InputStream template = new ByteArrayInputStream ( avvisoPagamentoReport.getTemplate () );
								ByteArrayOutputStream outputStream = new ByteArrayOutputStream () ) {
					JasperCompileManager.compileReportToStream ( template, outputStream );
					avvisoPagamentoReport.setTemplateCompilato ( outputStream.toByteArray () );
					pdfReportRepository.persist ( avvisoPagamentoReport );
					Log.info ( "Compilazione del report terminata con successo" );
				} catch ( Exception e ) {
					Log.error ( "Errore imprevisto durante la compilazione del template  " + CODICE_AVVISO_ANALOGICO
									+ ": " + this.getClass ().getName () + "::" + methodName, e );
					throw new RuntimeException (
									"Errore imprevisto durante la compilazione del template: " + this.getClass ().getName () + "::" + methodName,
									e );
				}
			} else {
				Log.info ( "Report trovato per codice: ".concat ( CODICE_AVVISO_ANALOGICO ) + " gia' compilato" );
			}
			epayTPdfReport = avvisoPagamentoReport;
		} catch ( RuntimeException e ) {
			throw new RuntimeException ( e );
		}
	}

	public EpayTPdfReport getJasperReport () {
		if ( epayTPdfReport == null ) {
			Log.error ( "Il template richiesto non presente  " + CODICE_AVVISO_ANALOGICO + ":" + this.getClass ().getName ()
							+ "::getJasperReport" );
			throw new RuntimeException ( "Il template richiesto non presente  " + CODICE_AVVISO_ANALOGICO + ":"
							+ this.getClass ().getName () + "::getJasperReport" );
		}
		return epayTPdfReport;
	}
}
