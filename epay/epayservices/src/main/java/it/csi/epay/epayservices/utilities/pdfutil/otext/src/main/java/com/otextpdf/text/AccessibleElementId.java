/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public class AccessibleElementId implements Comparable<AccessibleElementId> {

	private static int id_counter = 0;

	private final int id;

	public AccessibleElementId () {
		id = ++id_counter;
	}

	public String toString () {
		return Integer.toString ( id );
	}

	public int hashCode () {
		return id;
	}

	public boolean equals ( Object o ) {
		return ( o instanceof AccessibleElementId ) && ( id == ( (AccessibleElementId) o ).id );
	}

	public int compareTo ( AccessibleElementId elementId ) {
		return Integer.compare ( id, elementId.id );
	}

}
