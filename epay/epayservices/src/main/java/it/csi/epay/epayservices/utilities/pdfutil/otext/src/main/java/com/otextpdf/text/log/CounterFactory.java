/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log;

public class CounterFactory {

	private static final CounterFactory myself;

	static {
		myself = new CounterFactory ();
	}

	private Counter counter = new NoOpCounter ();

	private CounterFactory () {
	}

	public static CounterFactory getInstance () {
		return myself;
	}

	public static Counter getCounter ( Class<?> klass ) {
		return myself.counter.getCounter ( klass );
	}

	public Counter getCounter () {
		return counter;
	}

	public void setCounter ( Counter counter ) {
		this.counter = counter;
	}
}
