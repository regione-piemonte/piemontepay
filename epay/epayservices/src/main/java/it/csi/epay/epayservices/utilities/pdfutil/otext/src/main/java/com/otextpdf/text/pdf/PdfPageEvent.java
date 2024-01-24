/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Document;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Paragraph;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;


interface PdfPageEvent {

	void onOpenDocument ( PdfWriter writer, Document document );

	void onStartPage ( PdfWriter writer, Document document );

	void onEndPage ( PdfWriter writer, Document document );

	void onCloseDocument ( PdfWriter writer, Document document );

	void onParagraph ( PdfWriter writer, Document document, float paragraphPosition );

	void onParagraphEnd ( PdfWriter writer, Document document, float paragraphPosition );

	void onChapter ( PdfWriter writer, Document document, float paragraphPosition, Paragraph title );

	void onChapterEnd ( PdfWriter writer, Document document, float paragraphPosition );

	void onSection ( PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title );

	void onSectionEnd ( PdfWriter writer, Document document, float paragraphPosition );

	void onGenericTag ( PdfWriter writer, Document document, Rectangle rect, String text );
}
