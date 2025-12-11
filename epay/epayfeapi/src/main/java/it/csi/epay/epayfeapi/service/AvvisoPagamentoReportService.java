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


@ApplicationScoped
@Transactional
public class AvvisoPagamentoReportService {

	private static final String CODICE_AVVISO_ANALOGICO = "AA01";

	@Inject
	PdfReportRepository pdfReportRepository;

	private EpayTPdfReport epayTPdfReport;

	@PostConstruct
	protected void initialize () {
		var methodName = "initialize";
		try {
			var avvisoPagamentoReport = pdfReportRepository.findByCodice ( CODICE_AVVISO_ANALOGICO );
			if ( null == avvisoPagamentoReport ) {
				Log.error ( "Nessun report trovato per codice: ".concat ( CODICE_AVVISO_ANALOGICO ) );
				throw new RuntimeException ( "Nessun report trovato per codice:".concat ( CODICE_AVVISO_ANALOGICO ) );
			}
			if ( avvisoPagamentoReport.getTemplateCompilato () == null ) {
				Log.info ( "Avvio della compilazione del report trovato con codice: ".concat ( CODICE_AVVISO_ANALOGICO ) );
				try ( var template = new ByteArrayInputStream ( avvisoPagamentoReport.getTemplate () ); var outputStream = new ByteArrayOutputStream () ) {
					JasperCompileManager.compileReportToStream ( template, outputStream );
					avvisoPagamentoReport.setTemplateCompilato ( outputStream.toByteArray () );
					pdfReportRepository.persist ( avvisoPagamentoReport );
					Log.info ( "Compilazione del report terminata con successo" );
				} catch ( Exception e ) {
					Log.errorf ( "Errore imprevisto durante la compilazione del template %s: %s::%s", CODICE_AVVISO_ANALOGICO, this.getClass ().getName (),
									methodName );
					Log.error ( e.getMessage () );
					throw new RuntimeException (
									String.format ( "Errore imprevisto durante la compilazione del template: %s::%s", this.getClass ().getName (), methodName ),
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
			Log.errorf ( "Il template richiesto non presente  %s:%s::getJasperReport", CODICE_AVVISO_ANALOGICO, this.getClass ().getName () );
			throw new RuntimeException ( String.format ( "Il template richiesto non presente %s:%s::getJasperReport", CODICE_AVVISO_ANALOGICO,
							this.getClass ().getName () ) );
		}
		return epayTPdfReport;
	}
}
