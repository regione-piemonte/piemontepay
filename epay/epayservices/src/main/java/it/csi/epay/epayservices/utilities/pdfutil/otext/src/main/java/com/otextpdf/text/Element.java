/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.util.List;


public interface Element {

	int HEADER = 0;

	int TITLE = 1;

	int SUBJECT = 2;

	int KEYWORDS = 3;

	int AUTHOR = 4;

	int PRODUCER = 5;

	int CREATIONDATE = 6;

	int CREATOR = 7;

	int LANGUAGE = 8;

	int CHUNK = 10;

	int PHRASE = 11;

	int PARAGRAPH = 12;

	int SECTION = 13;

	int LIST = 14;

	int LISTITEM = 15;

	int CHAPTER = 16;

	int ANCHOR = 17;

	int PTABLE = 23;

	int ANNOTATION = 29;

	int RECTANGLE = 30;

	int JPEG = 32;

	int JPEG2000 = 33;

	int IMGRAW = 34;

	int IMGTEMPLATE = 35;

	int JBIG2 = 36;

	int DIV = 37;

	int MARKED = 50;

	int YMARK = 55;

	int WRITABLE_DIRECT = 666;

	int ALIGN_UNDEFINED = -1;

	int ALIGN_LEFT = 0;

	int ALIGN_CENTER = 1;

	int ALIGN_RIGHT = 2;

	int ALIGN_JUSTIFIED = 3;

	int ALIGN_TOP = 4;

	int ALIGN_MIDDLE = 5;

	int ALIGN_BOTTOM = 6;

	int ALIGN_BASELINE = 7;

	int ALIGN_JUSTIFIED_ALL = 8;

	int CCITTG4 = 0x100;

	int CCITTG3_1D = 0x101;

	int CCITTG3_2D = 0x102;

	int CCITT_BLACKIS1 = 1;

	int CCITT_ENCODEDBYTEALIGN = 2;

	int CCITT_ENDOFLINE = 4;

	int CCITT_ENDOFBLOCK = 8;

	boolean process ( ElementListener listener );

	int type ();

	boolean isContent ();

	boolean isNestable ();

	List<Chunk> getChunks ();

	String toString ();
}
