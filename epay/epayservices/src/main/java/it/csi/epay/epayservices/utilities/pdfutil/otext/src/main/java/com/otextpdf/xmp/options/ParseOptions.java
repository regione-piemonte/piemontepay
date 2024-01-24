/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options;

public final class ParseOptions extends Options {

	public static final int REQUIRE_XMP_META = 0x0001;

	public static final int STRICT_ALIASING = 0x0004;

	public static final int FIX_CONTROL_CHARS = 0x0008;

	public static final int ACCEPT_LATIN_1 = 0x0010;

	public static final int OMIT_NORMALIZATION = 0x0020;

	public ParseOptions () {
		setOption ( FIX_CONTROL_CHARS | ACCEPT_LATIN_1, true );
	}

	public boolean getRequireXMPMeta () {
		return getOption ( REQUIRE_XMP_META );
	}

	public boolean getStrictAliasing () {
		return getOption ( STRICT_ALIASING );
	}

	public boolean getFixControlChars () {
		return getOption ( FIX_CONTROL_CHARS );
	}

	public boolean getAcceptLatin1 () {
		return getOption ( ACCEPT_LATIN_1 );
	}

	public boolean getOmitNormalization () {
		return getOption ( OMIT_NORMALIZATION );
	}

	protected int getValidOptions () {
		return
						REQUIRE_XMP_META |
										STRICT_ALIASING |
										FIX_CONTROL_CHARS |
										ACCEPT_LATIN_1 |
										OMIT_NORMALIZATION;
	}
}
