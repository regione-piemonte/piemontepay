/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

public class CMapSequence {

	public byte[] seq;

	public int off;

	public int len;

	public CMapSequence ( byte[] seq, int off, int len ) {
		this.seq = seq;
		this.off = off;
		this.len = len;
	}
}
