/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public interface DocListener extends ElementListener {

	void open ();

	void close ();

	void newPage ();

	void setPageSize ( Rectangle pageSize );

	void setMargins ( float marginLeft, float marginRight, float marginTop, float marginBottom );

	boolean setMarginMirroring ( boolean marginMirroring );

	boolean setMarginMirroringTopBottom ( boolean marginMirroringTopBottom );

	void setPageCount ( int pageN );

	void resetPageCount ();

}
