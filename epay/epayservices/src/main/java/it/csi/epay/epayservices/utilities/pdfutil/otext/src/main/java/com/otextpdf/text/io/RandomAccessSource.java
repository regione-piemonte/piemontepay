/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;


public interface RandomAccessSource {

	int get ( long position ) throws IOException;

	int get ( long position, byte[] bytes, int off, int len ) throws IOException;

	long length ();

	void close () throws IOException;

}
