/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log;

public class NoOpCounter implements Counter {

	public Counter getCounter ( Class<?> klass ) {
		return this;
	}

	public void read ( long l ) {
	}

	public void written ( long l ) {
	}

}
