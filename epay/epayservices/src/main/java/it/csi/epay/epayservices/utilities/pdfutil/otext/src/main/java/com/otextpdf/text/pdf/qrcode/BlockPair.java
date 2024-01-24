/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

final class BlockPair {

	private final ByteArray dataBytes;

	private final ByteArray errorCorrectionBytes;

	BlockPair ( ByteArray data, ByteArray errorCorrection ) {
		dataBytes = data;
		errorCorrectionBytes = errorCorrection;
	}

	public ByteArray getDataBytes () {
		return dataBytes;
	}

	public ByteArray getErrorCorrectionBytes () {
		return errorCorrectionBytes;
	}

}
