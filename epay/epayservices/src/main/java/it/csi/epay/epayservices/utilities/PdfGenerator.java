/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities;

import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BadElementException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Image;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.PageSize;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.RectangleReadOnly;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Utilities;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.Barcode;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.Barcode128;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BarcodeQRCode;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfAction;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPCell;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfPTable;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode.EncodeHintType;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode.ErrorCorrectionLevel;

import javax.ejb.Singleton;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class PdfGenerator {

	private static final Font FONTN = new Font ( Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK );

	private static final Font FONTB = new Font ( Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK );

	private static final Font FONTI = new Font ( Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.BLACK );

	private static final Font FONTTRI = new Font ( Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, BaseColor.BLACK );

	private static final float MARGIN20 = PdfGenerator.mmToPt ( 20f );

	private static final float MARGIN05 = PdfGenerator.mmToPt ( 5f );

	private static final Rectangle SIZELOGOENTE = new RectangleReadOnly ( ( PageSize.A4.getWidth () / 2 - MARGIN20 ), 50 );

	private static final Rectangle SIZELOGOPAGOPA = new RectangleReadOnly ( 110, 80 );

	private static final String BARCODE_GLN_CODE = "(415)";

	private static final String BARCODE_REFERENCE_CODE = "(8020)"; // Payment

	// slip
	// reference
	// number
	private static final String BARCODE_AMOUNT_CODE = "(3902)"; // Amount
	// payable
	// (local
	// currency)

	private static final String QRCODE_SEPARATOR = "|";

	private static final String QRCODE_IDENTIFICATIVO = "PAGOPA";

	private static final String QRCODE_VERSIONE = "002";

	private static final LogUtil log = new LogUtil ( PdfGenerator.class );

	private static final String ND = "n.d.";

	public byte[] creaRicevutaPdf ( Map<ParamNameXPdf, Object> param ) {
		final String methodName = "creaRicevutaPdf";

		param.put ( ParamNameXPdf.B1_TITOLO, "Ricevuta di pagamento" );

		return helperCreaPdf ( param, true, methodName );
	}

	public byte[] creaAvvisoPdf ( Map<ParamNameXPdf, Object> param ) {
		final String methodName = "creaAvvisoPdf";

		param.put ( ParamNameXPdf.B1_TITOLO, "Avviso di pagamento" );

		return helperCreaPdf ( param, false, methodName );
	}

	private byte[] helperCreaPdf ( Map<ParamNameXPdf, Object> param, boolean ricPag, String methodName ) {
		try ( ByteArrayOutputStream baos = new ByteArrayOutputStream () ) {
			Document document = new Document ( PageSize.A4 );
			document.setMargins ( MARGIN20, MARGIN20, MARGIN20, MARGIN20 );

			PdfWriter writer = PdfWriter.getInstance ( document, baos );
			param.put ( ParamNameXPdf.Z1_PDFWRITER, writer );

			document.open ();

			add ( document, logoEnte ( param ) );
			add ( document, logoPagoPA ( param ) );
			add ( document, titolo ( param ) );
			add ( document, informazioniDovuto ( param ) );
			add ( document, informazioniPagatore ( param ) );

			if ( ricPag ) {
				add ( document, informazioniTransazione ( param, false ) );
				add ( document, informazioniAggiuntive ( param, false ) );
				add ( document, esito ( param ) );
			} else {
				add ( document, informazioniMetodoPagamento ( param ) );
				add ( document, barcode ( param ) );
				add ( document, qrcode ( param ) );
			}
			document.close ();

			return baos.toByteArray ();
		} catch ( DocumentException | IOException e ) {
			log.debug ( methodName, e.getStackTrace () );
			return null;
		}
	}

	private Element logoEnte ( Map<ParamNameXPdf, Object> param ) throws BadElementException, IOException {
		Image image = null;

		if ( param.containsKey ( ParamNameXPdf.A1_LOGO_ENTE ) ) {
			image = Image.getInstance ( (byte[]) param.get ( ParamNameXPdf.A1_LOGO_ENTE ) );
			image.setAlignment ( Image.LEFT | Image.TEXTWRAP );
//			image.setScaleToFitHeight ( true );
			image.scaleToFit ( SIZELOGOENTE );
			image.setAbsolutePosition ( MARGIN20, PageSize.A4.getHeight () - MARGIN20 - 80 );
		}
		return image;
	}

	private Element logoPagoPA ( Map<ParamNameXPdf, Object> param ) throws BadElementException, IOException {
		Image image = null;

		if ( param.containsKey ( ParamNameXPdf.A2_LOGO_PAGOPA ) ) {
			image = Image.getInstance ( (byte[]) param.get ( ParamNameXPdf.A2_LOGO_PAGOPA ) );
			image.setAlignment ( Image.RIGHT | Image.TEXTWRAP );
			image.setScaleToFitHeight ( true );
			image.scaleToFit ( SIZELOGOPAGOPA );
			image.setAbsolutePosition ( PageSize.A4.getWidth () - MARGIN20 - image.getScaledWidth (),
							PageSize.A4.getHeight () - MARGIN20 - 80 );
		}
		return image;
	}

	private Element titolo ( Map<ParamNameXPdf, Object> param ) {
		return titoloHelper ( param, false );
	}

	private Element esito ( Map<ParamNameXPdf, Object> param ) {
		Font font = new Font ( Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK );

		Paragraph paragraph = new Paragraph ();
		paragraph.setFont ( font );
		paragraph.setLeading ( 20 );
		paragraph.setAlignment ( Paragraph.ALIGN_CENTER );
		paragraph.add ( Chunk.NEWLINE );
		paragraph.add ( new Phrase ( (String) param.get ( ParamNameXPdf.B3_STATO_TRANSAZIONE ) ) );

		return paragraph;
	}

	private Element titoloEsito ( Map<ParamNameXPdf, Object> param ) {
		return titoloHelper ( param, true );
	}

	private Element titoloHelper ( Map<ParamNameXPdf, Object> param, boolean esito ) {
		Font font = new Font ( Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK );

		Paragraph paragraph = new Paragraph ();
		paragraph.setSpacingBefore ( 100 );
		paragraph.setFont ( font );
		paragraph.setLeading ( 20 );
		paragraph.setAlignment ( Paragraph.ALIGN_CENTER );
		paragraph.add ( new Phrase ( (String) param.get ( ParamNameXPdf.B1_TITOLO ) ) );
		paragraph.add ( Chunk.NEWLINE );
		paragraph.add ( new Phrase ( (String) param.get ( ParamNameXPdf.B2_DESCRIZIONE_TASSA ) ) );

		if ( esito ) {
			paragraph.add ( Chunk.NEWLINE );
			paragraph.add ( new Phrase ( (String) param.get ( ParamNameXPdf.B3_STATO_TRANSAZIONE ) ) );
		}

		return paragraph;
	}

	private Element informazioniDovuto ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = helperInformazioniDovuto ( param );
		table.addCell ( new PdfPCell ( new Phrase ( "Importo dovuto (Euro)", FONTB ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( getImportoFormattato ( param ), FONTB ) ) );
		isQuietanzaHelper ( table, param );

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( new Phrase ( "Informazioni sul dovuto", FONTI ) );
		paragraph.add ( table );

		return paragraph;
	}

	private void isQuietanzaHelper ( PdfPTable table, Map<ParamNameXPdf, Object> param ) {
		if ( param.get ( ParamNameXPdf.C4_CODICE_AVVISO ) != null ) {
			table.addCell ( new PdfPCell ( new Phrase ( "CODICE AVVISO", FONTN ) ) );
			table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C4_CODICE_AVVISO ), FONTN ) ) );
		}
		table.addCell ( new PdfPCell ( new Phrase ( "Identificativo Univoco Versamento (IUV)", FONTB ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C5_IUV ), FONTB ) ) );

	}

	private Element importoTotale ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = new PdfPTable ( 2 );
		table.setHorizontalAlignment ( PdfPTable.ALIGN_LEFT );
		table.setWidthPercentage ( 100 );
		table.addCell ( new PdfPCell ( new Phrase ( "Importo Totale pagato (Euro)", FONTB ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( getImportoFormattato ( param ), FONTB ) ) );

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( table );

		return paragraph;
	}

	private Element informazioniDovutoQuietanza ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = helperInformazioniDovuto ( param );
		table.addCell ( new PdfPCell ( new Phrase ( "Tipologia versamento", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.B2_DESCRIZIONE_TASSA ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( "Importo pagato (Euro)", FONTB ) ) );
		if ( param.get ( ParamNameXPdf.F1_DESCRIZIONE_ENTE_SECONDARIO ) != null ) {
			table.addCell ( new PdfPCell ( new Phrase ( getImportoPrincipaleFormattato ( param ), FONTN ) ) );
		} else {
			table.addCell ( new PdfPCell ( new Phrase ( getImportoFormattato ( param ), FONTB ) ) );
		}

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( new Phrase ( "Informazioni sul dovuto", FONTTRI ) );
		paragraph.add ( table );

		if ( param.get ( ParamNameXPdf.F1_DESCRIZIONE_ENTE_SECONDARIO ) != null ) {
			PdfPTable tablesec = new PdfPTable ( 2 );
			tablesec.setHorizontalAlignment ( PdfPTable.ALIGN_LEFT );
			tablesec.setWidthPercentage ( 100 );
			tablesec.setSpacingBefore ( PdfGenerator.mmToPt ( 5f ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( "Ente beneficiario", FONTN ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.F1_DESCRIZIONE_ENTE_SECONDARIO ), FONTN ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( "Codice fiscale ente beneficiario", FONTN ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.F2_CF_ENTE_SECONDARIO ), FONTN ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( "Tipologia versamento", FONTN ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.F3_DESCRIZIONE_TASSA_SECONDARIO ), FONTN ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( "Importo pagato (Euro)", FONTB ) ) );
			tablesec.addCell ( new PdfPCell ( new Phrase ( getImportoSecondarioFormattato ( param ), FONTN ) ) );
			paragraph.add ( tablesec );
		}

		return paragraph;
	}

	private PdfPTable helperInformazioniDovuto ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = new PdfPTable ( 2 );
		table.setHorizontalAlignment ( PdfPTable.ALIGN_LEFT );
		table.setWidthPercentage ( 100 );
		table.addCell ( new PdfPCell ( new Phrase ( "Ente beneficiario", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C1_DESCRIZIONE_ENTE ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( "Codice fiscale ente beneficiario", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C2_CF_ENTE ), FONTN ) ) );

		return table;
	}

	private Element informazioniPagatore ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = informazioniPagatoreHelper ( param );
		if ( param.get ( ParamNameXPdf.C6_NOTE ) != null ) {
			table.addCell ( new PdfPCell ( new Phrase ( "Note", FONTN ) ) );
			table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C6_NOTE ), FONTN ) ) );
		}

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( new Phrase ( "Informazioni sul pagatore", FONTI ) );
		paragraph.add ( table );

		return paragraph;
	}

	private PdfPTable informazioniPagatoreHelper ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = new PdfPTable ( 2 );
		table.setHorizontalAlignment ( PdfPTable.ALIGN_LEFT );
		table.setWidthPercentage ( 100 );
		table.addCell ( new PdfPCell ( new Phrase ( "Nome e Cognome / Ragione Sociale", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.D1_NOME ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( "Codice fiscale / Partita IVA", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.D2_CF ), FONTN ) ) );
		return table;
	}

	private Element informazioniPagatoreQuietanza ( Map<ParamNameXPdf, Object> param ) {
		PdfPTable table = informazioniPagatoreHelper ( param );

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( new Phrase ( "Informazioni sul pagatore", FONTTRI ) );
		paragraph.add ( table );

		return paragraph;
	}

	private Element informazioniTransazione ( Map<ParamNameXPdf, Object> param, boolean isQuietanza ) {
		PdfPTable table = new PdfPTable ( 2 );
		table.setHorizontalAlignment ( PdfPTable.ALIGN_LEFT );
		table.setWidthPercentage ( 100 );
		if ( isQuietanza ) {
			isQuietanzaHelper ( table, param );
		}
		table.addCell ( new PdfPCell ( new Phrase ( "Numero transazione", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.E1_ID_TRANSAZIONE ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( "Prestatore di Servizi di Pagamento", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( "Data e ora dell' operazione", FONTN ) ) );
		if ( null == param.get ( ParamNameXPdf.E3_DATA_ORA ) ) {
			table.addCell ( new PdfPCell ( new Phrase ( ND, FONTN ) ) );
		} else {
			table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.E3_DATA_ORA ), FONTN ) ) );
		}
		table.addCell ( new PdfPCell ( new Phrase ( "Data applicativa", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( "Identificativo Univoco Riscossione", FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.E4_IDENTIFICATIVO_UNIVOCO_RISCOSSIONE ), FONTN ) ) );

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( new Phrase ( "Informazioni sulla transazione", isQuietanza ? FONTTRI : FONTI ) );
		paragraph.add ( table );

		return paragraph;
	}

	private Element informazioniAggiuntive ( Map<ParamNameXPdf, Object> param, boolean isQuietanza ) {
		PdfPTable table = new PdfPTable ( 1 );
		table.setHorizontalAlignment ( PdfPTable.ALIGN_LEFT );
		table.setWidthPercentage ( 100 );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C6_CAUSALE ), FONTN ) ) );
		table.addCell ( new PdfPCell ( new Phrase ( (String) param.get ( ParamNameXPdf.C6_NOTE_PAGAMENTO ), FONTN ) ) );

		Paragraph paragraph = new Paragraph ();
		paragraph.add ( new Phrase ( "Informazioni aggiuntive - Eventuali Note dell'ente e del pagatore", isQuietanza ? FONTTRI : FONTI ) );
		paragraph.add ( table );

		return paragraph;
	}

	private Element informazioniMetodoPagamento ( Map<ParamNameXPdf, Object> param ) {
		final String methodName = "informazioniMetodoPagamento";
		Paragraph paragraph = new Paragraph ();
		paragraph.setFont ( new Font ( Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK ) );

		try {
			Phrase phrase;
			Chunk chunk;
			Font fontLink = new Font ( Font.FontFamily.HELVETICA, 9, Font.UNDERLINE, BaseColor.BLACK );
			fontLink.setColor ( BaseColor.BLUE );
			String urlEpay = "https://pay.sistemapiemonte.it/epayweb/";
			Chunk chunkWWW = new Chunk ( urlEpay );
			chunkWWW.setAction ( new PdfAction ( new URL ( urlEpay ) ) );
			chunkWWW.setFont ( fontLink );
			String urtAgid = "http://www.agid.gov.it/agenda-digitale/pubblica-amministrazione/pagamenti-elettronici/psp-aderenti-elenco";
			Chunk chunkAgid = new Chunk ( urtAgid );
			chunkAgid.setAction ( new PdfAction ( new URL ( urtAgid ) ) );
			chunkAgid.setFont ( fontLink );

			phrase = new Phrase ();
			chunk = new Chunk ( "E' possibile effettuare il pagamento:" );
			phrase.add ( chunk );
			phrase.add ( Chunk.NEWLINE );
			phrase.add ( Chunk.NEWLINE );
			paragraph.add ( phrase );

			phrase = new Phrase ( "- Dal sito " );
			phrase.add ( chunkWWW );
			phrase.add ( new Chunk ( " indicando lo IUV: " ) );
			chunk = new Chunk ( (String) param.get ( ParamNameXPdf.C5_IUV ) );
			chunk.setFont ( new Font ( Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK ) );
			phrase.add ( chunk );
			phrase.add ( new Chunk ( " e il Codice Fiscale/Partita IVA: " ) );
			chunk = new Chunk ( (String) param.get ( ParamNameXPdf.D2_CF ) );
			chunk.setFont ( new Font ( Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK ) );
			phrase.add ( chunk );
			phrase.add ( Chunk.NEWLINE );
			paragraph.add ( phrase );

			phrase = new Phrase (
							"- Presso le banche e altri prestatori di servizio di pagamento aderenti all'iniziativa tramite i canali messi a disposizione (ad esempio: home banking, sportello, ATM, APP da smartphone, ecc)." );
			phrase.add ( Chunk.NEWLINE );
			paragraph.add ( phrase );

			phrase = new Phrase ( "Per effettuare il pagamento utilizzare il Codice Avviso " );
			phrase.add ( new Chunk ( (String) param.get ( ParamNameXPdf.C4_CODICE_AVVISO ) ) );
			phrase.add ( new Chunk ( " oppure il codice a barre o il QR Code presenti nella sintesi." ) );
			phrase.add ( Chunk.NEWLINE );
			paragraph.add ( phrase );

			phrase = new Phrase ( "L'elenco dei punti abilitati a ricevere il pagamento e' disponibile sul sito " );
			phrase.add ( Chunk.NEWLINE );
			phrase.add ( chunkAgid );
			phrase.add ( Chunk.NEWLINE );
			paragraph.add ( phrase );

			paragraph.add ( Chunk.NEWLINE );

			phrase =
							new Phrase ( "A conclusione della transazione di pagamento il contribuente potra' scaricare la ricevuta telematica collegandosi al sito " );
			phrase.add ( chunkWWW );
			phrase.add ( new Chunk ( " nella sezione Verifica fornendo i dati richiesti tra cui l'Identificativo Univoco di Versamento (IUV) " ) );
			phrase.add ( new Chunk ( (String) param.get ( ParamNameXPdf.C5_IUV ) ) );
			phrase.add ( new Chunk ( " e il codice fiscale/partita IVA." ) );
			phrase.add ( Chunk.NEWLINE );
			paragraph.add ( phrase );

		} catch ( MalformedURLException e ) {
			log.debug ( methodName, e.getStackTrace () );
			return null;
		}
		return paragraph;
	}

	private Element barcode ( Map<ParamNameXPdf, Object> param ) {
		StringBuilder sb = new StringBuilder ();
		sb.append ( BARCODE_GLN_CODE );
		BigDecimal gln_code_bd = (BigDecimal) param.get ( ParamNameXPdf.F1_GLN_CODE );
		String gln_code_s = gln_code_bd.toPlainString ();
		sb.append ( gln_code_s );
		sb.append ( BARCODE_REFERENCE_CODE );
		sb.append ( (String) param.get ( ParamNameXPdf.C4_CODICE_AVVISO ) );
		sb.append ( BARCODE_AMOUNT_CODE );
		String amount_s = getImportoPiatto ( param );
		sb.append ( amount_s );

		Barcode128 code128ext = new Barcode128 ();
		code128ext.setCodeType ( Barcode.CODE128_UCC );
		code128ext.setStartStopText ( false );
		code128ext.setChecksumText ( true );
		code128ext.setExtended ( true );
		code128ext.setSize ( 9 );
		code128ext.setBarHeight ( mmToPt ( 14f ) );
		code128ext.setCode ( sb.toString () );
		Image barCode = code128ext
						.createImageWithBarcode ( ( (PdfWriter) param.get ( ParamNameXPdf.Z1_PDFWRITER ) ).getDirectContent (), null, null );
		barCode.setAbsolutePosition ( MARGIN20, MARGIN20 );
		barCode.setScaleToFitHeight ( false );
		barCode.scaleToFit ( mmToPt ( 80f ), barCode.getHeight () );

		return barCode;
	}

	private Element qrcode ( Map<ParamNameXPdf, Object> param ) throws BadElementException {
		String sb = QRCODE_IDENTIFICATIVO +
						QRCODE_SEPARATOR +
						QRCODE_VERSIONE +
						QRCODE_SEPARATOR +
						param.get ( ParamNameXPdf.C4_CODICE_AVVISO ) +
						QRCODE_SEPARATOR +
						param.get ( ParamNameXPdf.C2_CF_ENTE ) +
						QRCODE_SEPARATOR +
						getImportoPiatto ( param );

		Map<EncodeHintType, Object> qrParam = new HashMap<> ();
		qrParam.put ( EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M );
		qrParam.put ( EncodeHintType.CHARACTER_SET, "UTF-8" );

		BarcodeQRCode qrcode = new BarcodeQRCode ( sb, (int) mmToPt ( 30f ), (int) mmToPt ( 30f ), qrParam );
		Image qrcodeImage = qrcode.getImage ();
		qrcodeImage.setAbsolutePosition ( PageSize.A4.getWidth () - MARGIN20 - qrcodeImage.getWidth (), MARGIN20 );

		return qrcodeImage;
	}

	private void add ( Document document, Element element ) throws DocumentException {
		if ( element == null ) {
			return;
		}
		document.add ( element );
		if ( element instanceof Paragraph ) {
			document.add ( Chunk.NEWLINE );
		}
	}

	private static float mmToPt ( final float value ) {
		return Utilities.millimetersToPoints ( value );
	}

	private String getImportoFormattato ( Map<ParamNameXPdf, Object> param ) {
		return getImportoHelper ( param, ParamNameXPdf.C3_IMPORTO );
	}

	private String getImportoSecondarioFormattato ( Map<ParamNameXPdf, Object> param ) {
		return getImportoHelper ( param, ParamNameXPdf.F4_IMPORTO_SECONDARIO );
	}

	private String getImportoPrincipaleFormattato ( Map<ParamNameXPdf, Object> param ) {
		return getImportoHelper ( param, ParamNameXPdf.F4_IMPORTO_PRINCIPALE );
	}

	private String getImportoHelper ( Map<ParamNameXPdf, Object> param, ParamNameXPdf paramNameXPdf ) {
		BigDecimal importo = (BigDecimal) param.get ( paramNameXPdf );
		importo = importo.setScale ( 2, RoundingMode.DOWN );
		DecimalFormat df = new DecimalFormat ();
		df.setMaximumFractionDigits ( 2 );
		df.setMinimumFractionDigits ( 2 );
		df.setGroupingUsed ( true );
		DecimalFormatSymbols dfs = new DecimalFormatSymbols ();
		dfs.setDecimalSeparator ( ',' );
		dfs.setGroupingSeparator ( '.' );
		df.setDecimalFormatSymbols ( dfs );
		return df.format ( importo );
	}

	private String getImportoPiatto ( Map<ParamNameXPdf, Object> param ) {
		BigDecimal importo = (BigDecimal) param.get ( ParamNameXPdf.C3_IMPORTO );
		importo = importo.setScale ( 2, RoundingMode.DOWN ).multiply ( new BigDecimal ( 100 ) );
		DecimalFormat df = new DecimalFormat ();
		df.setMaximumFractionDigits ( 0 );
		df.setMinimumFractionDigits ( 0 );
		df.setGroupingUsed ( false );
		DecimalFormatSymbols dfs = new DecimalFormatSymbols ();
		dfs.setDecimalSeparator ( ',' );
		dfs.setGroupingSeparator ( '.' );
		df.setDecimalFormatSymbols ( dfs );
		return df.format ( importo );
	}

	public byte[] creaQuietanzaPdf ( Map<ParamNameXPdf, Object> param ) {
		final String methodName = "creaQuietanzaPdf";

		param.put ( ParamNameXPdf.B1_TITOLO, "Ricevuta di pagamento" );

		try ( ByteArrayOutputStream baos = new ByteArrayOutputStream () ) {
			Document document = new Document ( PageSize.A4, MARGIN20, MARGIN20, MARGIN20, MARGIN05 ); // sinistra , destra , alto, sinsitra
			PdfWriter writer = PdfWriter.getInstance ( document, baos );
			param.put ( ParamNameXPdf.Z1_PDFWRITER, writer );

			document.open ();

			add ( document, logoEnte ( param ) );
			add ( document, logoPagoPA ( param ) );
			add ( document, titoloEsito ( param ) );
			add ( document, importoTotale ( param ) );
			add ( document, informazioniDovutoQuietanza ( param ) );
			add ( document, informazioniPagatoreQuietanza ( param ) );
			add ( document, informazioniTransazione ( param, true ) );
			add ( document, informazioniAggiuntive ( param, true ) );
			document.close ();

			return baos.toByteArray ();
		} catch ( DocumentException | IOException e ) {
			log.debug ( methodName, e.getStackTrace () );
			return null;
		}
	}
}
