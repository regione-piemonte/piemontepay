/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.util;

import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import com.lowagie.text.pdf.BarcodeDatamatrix;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.AvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.DestinatarioAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.EnteCreditoreAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.InfoPagamentoAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTPdfReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AvvisoPagamentoPdfGenerator {

	public byte[] creaPdf ( EnteCreditoreAvvisoPagamentoDTO ente, DestinatarioAvvisoPagamentoDTO destinatarioAvviso,
					InfoPagamentoAvvisoPagamentoDTO infoPagamento, EpayTPdfReport pdfReport ) throws IOException, JRException {

		ArrayList<AvvisoPagamentoDTO> avvisoAnalogicoList = createAvvisoPagamento ( ente, destinatarioAvviso, infoPagamento );

		byte[] output;
		try ( InputStream inputStream = new ByteArrayInputStream ( pdfReport.getTemplateCompilato () ) ) {

			java.util.Map<String, Object> parameters = new HashMap<> ();
			JasperPrint jasperPrint = JasperFillManager.fillReport ( inputStream, parameters, new JRBeanCollectionDataSource ( avvisoAnalogicoList ) );

			output = JasperExportManager.exportReportToPdf ( jasperPrint );
		}
		return output;
	}

	private ArrayList<AvvisoPagamentoDTO> createAvvisoPagamento ( EnteCreditoreAvvisoPagamentoDTO ente,
					DestinatarioAvvisoPagamentoDTO destinatarioAvviso, InfoPagamentoAvvisoPagamentoDTO infoPagamento ) throws IOException {

		AvvisoPagamentoDTO avvisoPagamentoDto = new AvvisoPagamentoDTO ();
		ArrayList<AvvisoPagamentoDTO> avvisoPagamentoDtos = new ArrayList<> ();

		// ente creditore
		if ( ente != null ) {
			if ( ente.getEcLogo () != null ) {
				BufferedImage image = ImageIO.read ( new ByteArrayInputStream ( ente.getEcLogo () ) );
				BufferedImage result = new BufferedImage ( image.getWidth (), image.getHeight (), BufferedImage.TYPE_BYTE_BINARY );

				Graphics2D graphic = result.createGraphics ();
				graphic.drawImage ( image, 0, 0, Color.WHITE, null );
				graphic.dispose ();
				avvisoPagamentoDto.setEcLogo ( result );
			}
			avvisoPagamentoDto.setCfEnte ( ente.getCfEnte ().toUpperCase () );

			avvisoPagamentoDto.setEnteCreditore ( ente.getEnteCreditore () );
			avvisoPagamentoDto.setSettoreEnte ( ente.getSettoreEnte () );
			avvisoPagamentoDto.setInfoEnte ( ente.getInfoEnte () );
			avvisoPagamentoDto.setAutorizzazione ( ente.getAutorizzazione () );
			if ( StringUtils.isNotBlank ( ente.getNumeroCCPostale () ) ) {
				StringBuilder numeroCCPostale = new StringBuilder ();
				if ( ente.getNumeroCCPostale ().length () < Constants.DATAMATRIX_LUNGHEZZA_CONTO ) {
					numeroCCPostale.append ( "0".repeat ( Math.max ( 0, Constants.DATAMATRIX_LUNGHEZZA_CONTO - ente.getNumeroCCPostale ().length () ) ) );
					numeroCCPostale.append ( ente.getNumeroCCPostale ().toUpperCase () );
				} else {
					numeroCCPostale.append ( ente.getNumeroCCPostale ().toUpperCase (), 0, Constants.DATAMATRIX_LUNGHEZZA_CONTO );
				}
				avvisoPagamentoDto.setNumeroCCPostale ( numeroCCPostale.toString () );
			}
			avvisoPagamentoDto.setIntestatarioNumeroCCPostale ( ente.getIntestatarioCCPostale () );
		}

		// destinatario avviso
		if ( destinatarioAvviso != null ) {
			avvisoPagamentoDto.setCfDestinatario ( destinatarioAvviso.getIdUnivocoDestinatario ().toUpperCase () );
			avvisoPagamentoDto.setNomeCognomeDestinatario ( destinatarioAvviso.getAnagraficaDestinatario () );
			avvisoPagamentoDto.setIndirizzoDestinatario ( destinatarioAvviso.getIndirizzoDestinatario () );
			avvisoPagamentoDto.setDatiDestinatario ( destinatarioAvviso.getDatiDestinatario () );
		}

		if ( infoPagamento != null ) {
			// oggetto del pagamento
			avvisoPagamentoDto.setTitle ( infoPagamento.getOggettoDelPagamento () );
			// pagamento
			avvisoPagamentoDto.setPagamentoRateale ( infoPagamento.getPagamentoReteale () );
			avvisoPagamentoDto.setImporto ( infoPagamento.getImporto () );
			avvisoPagamentoDto.setScadenza ( infoPagamento.getData () );
			avvisoPagamentoDto.setRate ( infoPagamento.getRate () );
			avvisoPagamentoDto.setAllaRata ( infoPagamento.getAllaRata () );
			avvisoPagamentoDto.setOggettoPagamento ( infoPagamento.getOggettoDelPagamento () );
			avvisoPagamentoDto.setModalitaPagamentoEnteCreditore ( infoPagamento.getModalitaPagamentoEnteCreditore () );
			avvisoPagamentoDto.setUrlPagamentoEnteCreditore ( infoPagamento.getUrlPagamentoEnteCreditore () );
			avvisoPagamentoDto.setModalitaPagamento ( infoPagamento.getModalitaPagamento () );
			avvisoPagamentoDto.setCodiceAvviso ( infoPagamento.getCodiceAvviso () );
		}

		if ( ente != null && infoPagamento != null && destinatarioAvviso != null ) {
			// dati per il pagamento (cbill, qrcode e datamatrix)
			if ( ente.getCbill () != null ) {
				avvisoPagamentoDto.setCbill ( ente.getCbill ().toUpperCase () );
			}
			Image qrCode = qrCode ( infoPagamento.getCodiceAvviso (), ente.getCfEnte (), infoPagamento.getImporto () );
			avvisoPagamentoDto.setQrCodeRataUnica ( qrCode );
			if ( StringUtils.isNotBlank ( ente.getAutorizzazione () ) && StringUtils.isNotBlank ( ente.getNumeroCCPostale () ) ) {
				avvisoPagamentoDto.setDataMatrixRataUnica ( dataMatrix ( ente.getCfEnte (),
								destinatarioAvviso,
								infoPagamento.getOggettoDelPagamento (), infoPagamento.getCodiceAvviso ().replace ( " ", "" ),
								infoPagamento.getImporto (), ente.getNumeroCCPostale () ) );
				avvisoPagamentoDto.setShowBollettino ( true );
			} else {
				avvisoPagamentoDto.setDataMatrixRataUnica ( null );
				avvisoPagamentoDto.setShowBollettino ( false );
			}
		}

		avvisoPagamentoDto.setPagoPA ( getImage ( Constants.PATH_PAGOPA ) );
		avvisoPagamentoDto.setAvvisoDiPagamento ( getImage ( Constants.PATH_AVVISO_DI_PAGAMENTO ) );
		avvisoPagamentoDto.setCanaliDigitali ( getImage ( Constants.PATH_CANALI_DIGITALI ) );
		avvisoPagamentoDto.setCanaliFisici ( getImage ( Constants.PATH_CANALI_FISICI ) );
		avvisoPagamentoDto.setPosteItaliane ( getImage ( Constants.PATH_POSTE_ITALIANE ) );
		avvisoPagamentoDto.setBollettinoPostale ( getImage ( Constants.PATH_BOLLETTINO ) );
		avvisoPagamentoDto.setEuro ( getImage ( Constants.PATH_EURO ) );
		avvisoPagamentoDto.setForbici ( getImage ( Constants.PATH_FORBICI ) );

		avvisoPagamentoDtos.add ( avvisoPagamentoDto );
		return avvisoPagamentoDtos;
	}

	private Image dataMatrix ( String codiceFiscaleEnte, DestinatarioAvvisoPagamentoDTO pagatore, String oggettoPagamento, String codiceAvviso,
					Double importo, String numeroContoCorrentePostale ) throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder ();
		sb.append ( Constants.DATAMATRIX_INDIRIZZAMENTO_FASE );
		sb.append ( Constants.DATAMATRIX_CODICE_FASE_ACCETTAZIONE );
		sb.append ( Constants.DATAMATRIX_SEPARATORE );

		StringBuilder codeline = new StringBuilder ();
		codeline.append ( Constants.DATAMATRIX_LUNGHEZZA_CODICE_AVVISO );
		if ( codiceAvviso.length () != 18 ) {
			Log.error ( "Lunghezza codice avviso errata" );
			throw new RuntimeException ( "Lunghezza codice avviso errata" );
		}

		codeline.append ( codiceAvviso );
		codeline.append ( Constants.DATAMATRIX_LUNGHEZZA_CONTO );
		if ( numeroContoCorrentePostale.length () < Constants.DATAMATRIX_LUNGHEZZA_CONTO ) {
			codeline.append ( "0".repeat ( Constants.DATAMATRIX_LUNGHEZZA_CONTO - numeroContoCorrentePostale.length () ) );
			codeline.append ( numeroContoCorrentePostale.toUpperCase () );
		} else {
			codeline.append ( numeroContoCorrentePostale.toUpperCase () );
		}

		codeline.append ( Constants.DATAMATRIX_LUNGHEZZA_IMPORTO );
		codeline.append ( PDFUtil.doubleWithTenZeros ( importo ) );
		codeline.append ( Constants.DATAMATRIX_LUNGHEZZA_TIPO_DOCUMENTO );
		codeline.append ( Constants.DATAMATRIX_TIPO_DOCUMENTO );
		if ( codeline.toString ().length () != Constants.CODELINE_LENGTH ) {
			Log.error ( "Lunghezza codeline errata" );
			throw new RuntimeException ( "Lunghezza codeline errata" );
		}

		sb.append ( codeline );
		sb.append ( Constants.DATAMATRIX_ID_DATAMATRIX );
		sb.append ( Constants.DATAMATRIX_FASE_PAGAMENTO );
		sb.append ( codiceFiscaleEnte.toUpperCase () );
		if ( pagatore.getIdUnivocoDestinatario ().length () < Constants.DATAMATRIX_ID_UNIVOCO_DIM ) {
			sb.append ( pagatore.getIdUnivocoDestinatario ().toUpperCase () );
			sb.append ( " ".repeat ( Math.max ( 0, Constants.DATAMATRIX_ID_UNIVOCO_DIM - pagatore.getIdUnivocoDestinatario ().length () ) ) );
		} else {
			sb.append ( pagatore.getIdUnivocoDestinatario ().toUpperCase (), 0, Constants.DATAMATRIX_ID_UNIVOCO_DIM );
		}

		if ( pagatore.getDatiDestinatario ().length () < Constants.DATAMATRIX_ANAGRAFICA_DIM ) {
			sb.append ( pagatore.getDatiDestinatario ().toUpperCase () );
			sb.append ( " ".repeat ( Math.max ( 0, Constants.DATAMATRIX_ANAGRAFICA_DIM - pagatore.getDatiDestinatario ().length () ) ) );
		} else {
			sb.append ( pagatore.getDatiDestinatario ().toUpperCase (), 0, Constants.DATAMATRIX_ANAGRAFICA_DIM );
		}

		if ( oggettoPagamento.length () < Constants.DATAMATRIX_CAUSALE_DIM ) {
			sb.append ( oggettoPagamento.toUpperCase () );
			sb.append ( " ".repeat ( Constants.DATAMATRIX_CAUSALE_DIM - oggettoPagamento.length () ) );
		} else {
			sb.append ( oggettoPagamento.toUpperCase (), 0, Constants.DATAMATRIX_CAUSALE_DIM );
		}
		sb.append ( " ".repeat ( Constants.DATAMATRIX_FILLER ) );
		sb.append ( Constants.DATAMATRIX_VALORE_FINALE_DATAMATRIX );

		if ( sb.toString ().length () != Constants.DATAMATRIX_LENGTH ) {
			Log.error ( "Lunghezza datamatrix errata" );
			throw new RuntimeException ( "Lunghezza datamatrix errata" );
		}

		BarcodeDatamatrix dm = new BarcodeDatamatrix ();
		// data matrix con correzione errore ECC200, di dimensione 52x52, senza quiet zone
		dm.setHeight ( 52 );
		dm.setWidth ( 52 );
		dm.setWs ( 0 );
		dm.generate ( sb.toString () );
		Image image = dm.createAwtImage ( Color.BLACK, Color.WHITE );
		return generateAwtImage ( image );
	}

	private Image qrCode ( String numeroAvviso, String identificativoEnte, double importo ) {
		String sb = Constants.QRCODE_IDENTIFICATIVO +
						Constants.QRCODE_SEPARATOR +
						Constants.QRCODE_VERSIONE +
						Constants.QRCODE_SEPARATOR +
						numeroAvviso.replace ( " ", "" ) +
						Constants.QRCODE_SEPARATOR +
						identificativoEnte +
						Constants.QRCODE_SEPARATOR +
						Math.round ( importo * 100.0 );

		Map<EncodeHintType, Object> qrParam = new HashMap<> ();
		qrParam.put ( EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M );
		qrParam.put ( EncodeHintType.CHARACTER_SET, "UTF-8" );
		// Qrcode con modules 33x33 (quindi di 4 versione), con codifica carattere UTF-8
		// e correzione dell'errore di livello M
		BarcodeQRCode qrcode = new BarcodeQRCode ( sb, Constants.QRCODE_MODULES, Constants.QRCODE_MODULES, qrParam );
		Image image = qrcode.createAwtImage ( Color.BLACK, Color.WHITE );
		return generateAwtImage ( image );
	}

	private BufferedImage generateAwtImage ( Image image ) {
		BufferedImage outputImage = new BufferedImage ( Constants.SCALED_DIMENSION, Constants.SCALED_DIMENSION, BufferedImage.TYPE_BYTE_BINARY );
		Graphics2D g2d = outputImage.createGraphics ();
		g2d.drawImage ( image, 0, 0, Color.WHITE, null );
		g2d.drawImage ( image, 0, 0, Constants.SCALED_DIMENSION, Constants.SCALED_DIMENSION, null );
		g2d.dispose ();

		return outputImage;
	}

	private Image getImage ( String path ) throws IOException {
		Image image;
		InputStream inputStream = null;
		try {
			ClassLoader classLoader = getClass ().getClassLoader ();
			inputStream = classLoader.getResourceAsStream ( path );
			assert inputStream != null;
			image = ImageIO.read ( inputStream );
		} finally {
			if ( inputStream != null ) {
				inputStream.close ();
			}
		}
		return image;
	}
}
