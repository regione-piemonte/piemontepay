/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.IOException;
import java.util.HashMap;


class EnumerateTTC extends TrueTypeFont {

	protected String[] names;

	EnumerateTTC ( String ttcFile ) throws DocumentException, IOException {
		fileName = ttcFile;
		rf = new RandomAccessFileOrArray ( ttcFile );
		findNames ();
	}

	void findNames () throws DocumentException, IOException {
		tables = new HashMap<> ();

		try {
			String mainTag = readStandardString ( 4 );
			if ( !mainTag.equals ( "ttcf" ) )
				throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.ttc.file", fileName ) );
			rf.skipBytes ( 4 );
			int dirCount = rf.readInt ();
			names = new String[dirCount];
			int dirPos = (int) rf.getFilePointer ();
			for ( int dirIdx = 0; dirIdx < dirCount; ++dirIdx ) {
				tables.clear ();
				rf.seek ( dirPos );
				rf.skipBytes ( dirIdx * 4 );
				directoryOffset = rf.readInt ();
				rf.seek ( directoryOffset );
				if ( rf.readInt () != 0x00010000 )
					throw new DocumentException ( MessageLocalization.getComposedMessage ( "1.is.not.a.valid.ttf.file", fileName ) );
				int num_tables = rf.readUnsignedShort ();
				rf.skipBytes ( 6 );
				for ( int k = 0; k < num_tables; ++k ) {
					String tag = readStandardString ( 4 );
					rf.skipBytes ( 4 );
					int[] table_location = new int[2];
					table_location[0] = rf.readInt ();
					table_location[1] = rf.readInt ();
					tables.put ( tag, table_location );
				}
				names[dirIdx] = getBaseFont ();
			}
		} finally {
			if ( rf != null )
				rf.close ();
		}
	}

	String[] getNames () {
		return names;
	}

}
