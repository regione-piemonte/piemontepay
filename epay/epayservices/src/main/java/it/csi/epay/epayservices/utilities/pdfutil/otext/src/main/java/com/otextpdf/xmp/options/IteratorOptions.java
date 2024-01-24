/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options;

public final class IteratorOptions extends Options {

	public static final int JUST_CHILDREN = 0x0100;

	public static final int JUST_LEAFNODES = 0x0200;

	public static final int JUST_LEAFNAME = 0x0400;

	public static final int OMIT_QUALIFIERS = 0x1000;

	public boolean isJustChildren () {
		return getOption ( JUST_CHILDREN );
	}

	public boolean isJustLeafname () {
		return getOption ( JUST_LEAFNAME );
	}

	public boolean isJustLeafnodes () {
		return !getOption ( JUST_LEAFNODES );
	}

	public boolean isOmitQualifiers () {
		return getOption ( OMIT_QUALIFIERS );
	}

	protected int getValidOptions () {
		return
						JUST_CHILDREN |
										JUST_LEAFNODES |
										JUST_LEAFNAME |
										OMIT_QUALIFIERS;
	}
}
