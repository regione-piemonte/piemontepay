/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import java.io.IOException;
import java.util.ArrayList;


public class PdfLayer extends PdfDictionary implements PdfOCG {

	protected PdfIndirectReference ref;

	protected ArrayList<PdfLayer> children;

	protected PdfLayer parent;

	protected String title;

	private boolean on = true;

	public PdfLayer ( String name, PdfWriter writer ) throws IOException {
		super ( PdfName.OCG );
		setName ( name );
		if ( writer instanceof PdfStamperImp )
			ref = writer.addToBody ( this ).getIndirectReference ();
		else
			ref = writer.getPdfIndirectReference ();
		writer.registerLayer ( this );
	}

	String getTitle () {
		return title;
	}

	public PdfLayer getParent () {
		return parent;
	}

	public ArrayList<PdfLayer> getChildren () {
		return children;
	}

	public PdfIndirectReference getRef () {
		return ref;
	}

	public void setName ( String name ) {
		put ( PdfName.NAME, new PdfString ( name, PdfObject.TEXT_UNICODE ) );
	}

	public PdfObject getPdfObject () {
		return this;
	}

	public boolean isOn () {
		return this.on;
	}

	public void setOn ( boolean on ) {
		this.on = on;
	}

	private PdfDictionary getUsage () {
		PdfDictionary usage = getAsDict ( PdfName.USAGE );
		if ( usage == null ) {
			usage = new PdfDictionary ();
			put ( PdfName.USAGE, usage );
		}
		return usage;
	}

	public void setExport ( boolean export ) {
		PdfDictionary usage = getUsage ();
		PdfDictionary dic = new PdfDictionary ();
		dic.put ( PdfName.EXPORTSTATE, export ? PdfName.ON : PdfName.OFF );
		usage.put ( PdfName.EXPORT, dic );
	}

	public void setView ( boolean view ) {
		PdfDictionary usage = getUsage ();
		PdfDictionary dic = new PdfDictionary ();
		dic.put ( PdfName.VIEWSTATE, view ? PdfName.ON : PdfName.OFF );
		usage.put ( PdfName.VIEW, dic );
	}

	public boolean isOnPanel () {
		return true;
	}

}
