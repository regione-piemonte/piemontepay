/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

public interface PdfPTableEventAfterSplit extends PdfPTableEventSplit {

	void afterSplitTable ( PdfPTable table, PdfPRow startRow, int startIdx );

}
