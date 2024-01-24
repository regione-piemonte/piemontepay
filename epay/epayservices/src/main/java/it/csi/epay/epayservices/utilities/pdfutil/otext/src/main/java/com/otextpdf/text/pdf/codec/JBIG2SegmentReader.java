/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.RandomAccessFileOrArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class JBIG2SegmentReader {

	public static final int SYMBOL_DICTIONARY = 0; //see 7.4.2.

	public static final int INTERMEDIATE_TEXT_REGION = 4; //see 7.4.3.

	public static final int IMMEDIATE_TEXT_REGION = 6; //see 7.4.3.

	public static final int IMMEDIATE_LOSSLESS_TEXT_REGION = 7; //see 7.4.3.

	public static final int PATTERN_DICTIONARY = 16; //see 7.4.4.

	public static final int INTERMEDIATE_HALFTONE_REGION = 20; //see 7.4.5.

	public static final int IMMEDIATE_HALFTONE_REGION = 22; //see 7.4.5.

	public static final int IMMEDIATE_LOSSLESS_HALFTONE_REGION = 23; //see 7.4.5.

	public static final int INTERMEDIATE_GENERIC_REGION = 36; //see 7.4.6.

	public static final int IMMEDIATE_GENERIC_REGION = 38; //see 7.4.6.

	public static final int IMMEDIATE_LOSSLESS_GENERIC_REGION = 39; //see 7.4.6.

	public static final int INTERMEDIATE_GENERIC_REFINEMENT_REGION = 40; //see 7.4.7.

	public static final int IMMEDIATE_GENERIC_REFINEMENT_REGION = 42; //see 7.4.7.

	public static final int IMMEDIATE_LOSSLESS_GENERIC_REFINEMENT_REGION = 43; //see 7.4.7.

	public static final int PAGE_INFORMATION = 48; //see 7.4.8.

	public static final int END_OF_PAGE = 49; //see 7.4.9.

	public static final int END_OF_STRIPE = 50; //see 7.4.10.

	public static final int END_OF_FILE = 51; //see 7.4.11.

	public static final int PROFILES = 52; //see 7.4.12.

	public static final int TABLES = 53; //see 7.4.13.

	public static final int EXTENSION = 62; //see 7.4.14.

	private final SortedMap<Integer, JBIG2Segment> segments = new TreeMap<> ();

	private final SortedMap<Integer, JBIG2Page> pages = new TreeMap<> ();

	private final SortedSet<JBIG2Segment> globals = new TreeSet<> ();

	private final RandomAccessFileOrArray ra;

	private boolean sequential;

	private boolean read = false;

	public JBIG2SegmentReader ( RandomAccessFileOrArray ra ) {
		this.ra = ra;
	}

	public static byte[] copyByteArray ( byte[] b ) {
		byte[] bc = new byte[b.length];
		System.arraycopy ( b, 0, bc, 0, b.length );
		return bc;
	}

	public void read () throws IOException {
		if ( this.read ) {
			throw new IllegalStateException ( MessageLocalization.getComposedMessage ( "already.attempted.a.read.on.this.jbig2.file" ) );
		}
		this.read = true;

		readFileHeader ();
		// Annex D
		if ( this.sequential ) {
			// D.1
			do {
				JBIG2Segment tmp = readHeader ();
				readSegment ( tmp );
				segments.put ( tmp.segmentNumber, tmp );
			} while ( this.ra.getFilePointer () < this.ra.length () );
		} else {
			// D.2
			JBIG2Segment tmp;
			do {
				tmp = readHeader ();
				segments.put ( tmp.segmentNumber, tmp );
			} while ( tmp.type != END_OF_FILE );
			for ( Integer integer : segments.keySet () ) {
				readSegment ( segments.get ( integer ) );
			}
		}
	}

	void readSegment ( JBIG2Segment s ) throws IOException {
		int ptr = (int) ra.getFilePointer ();

		if ( s.dataLength == 0xffffffffL ) {
			// TODO figure this bit out, 7.2.7
			return;
		}

		byte[] data = new byte[(int) s.dataLength];
		ra.read ( data );
		s.data = data;

		if ( s.type == PAGE_INFORMATION ) {
			int last = (int) ra.getFilePointer ();
			ra.seek ( ptr );
			int page_bitmap_width = ra.readInt ();
			int page_bitmap_height = ra.readInt ();
			ra.seek ( last );
			JBIG2Page p = pages.get ( s.page );
			if ( p == null ) {
				throw new IllegalStateException ( MessageLocalization.getComposedMessage ( "referring.to.widht.height.of.page.we.havent.seen.yet.1", s.page ) );
			}

			p.pageBitmapWidth = page_bitmap_width;
			p.pageBitmapHeight = page_bitmap_height;
		}
	}

	JBIG2Segment readHeader () throws IOException {
		int ptr = (int) ra.getFilePointer ();
		// 7.2.1
		int segment_number = ra.readInt ();
		JBIG2Segment s = new JBIG2Segment ( segment_number );

		// 7.2.3
		int segment_header_flags = ra.read ();
		s.deferredNonRetain = ( segment_header_flags & 0x80 ) == 0x80;
		boolean page_association_size = ( segment_header_flags & 0x40 ) == 0x40;
		s.type = segment_header_flags & 0x3f;

		//7.2.4
		int referred_to_byte0 = ra.read ();
		int count_of_referred_to_segments = ( referred_to_byte0 & 0xE0 ) >> 5;
		int[] referred_to_segment_numbers;
		boolean[] segment_retention_flags;

		if ( count_of_referred_to_segments == 7 ) {
			// at least five bytes
			ra.seek ( ra.getFilePointer () - 1 );
			count_of_referred_to_segments = ra.readInt () & 0x1fffffff;
			segment_retention_flags = new boolean[count_of_referred_to_segments + 1];
			int i = 0;
			int referred_to_current_byte = 0;
			do {
				int j = i % 8;
				if ( j == 0 ) {
					referred_to_current_byte = ra.read ();
				}
				segment_retention_flags[i] = ( 0x1 << j & referred_to_current_byte ) >> j == 0x1;
				i++;
			} while ( i <= count_of_referred_to_segments );

		} else if ( count_of_referred_to_segments <= 4 ) {
			// only one byte
			segment_retention_flags = new boolean[count_of_referred_to_segments + 1];
			referred_to_byte0 &= 0x1f;
			for ( int i = 0; i <= count_of_referred_to_segments; i++ ) {
				segment_retention_flags[i] = ( 0x1 << i & referred_to_byte0 ) >> i == 0x1;
			}

		} else {
			throw new IllegalStateException (
							MessageLocalization.getComposedMessage ( "count.of.referred.to.segments.had.bad.value.in.header.for.segment.1.starting.at.2",
											String.valueOf ( segment_number ), String.valueOf ( ptr ) ) );
		}
		s.segmentRetentionFlags = segment_retention_flags;
		s.countOfReferredToSegments = count_of_referred_to_segments;

		// 7.2.5
		referred_to_segment_numbers = new int[count_of_referred_to_segments + 1];
		for ( int i = 1; i <= count_of_referred_to_segments; i++ ) {
			if ( segment_number <= 256 ) {
				referred_to_segment_numbers[i] = ra.read ();
			} else if ( segment_number <= 65536 ) {
				referred_to_segment_numbers[i] = ra.readUnsignedShort ();
			} else {
				referred_to_segment_numbers[i] = (int) ra.readUnsignedInt (); // TODO wtf ack
			}
		}
		s.referredToSegmentNumbers = referred_to_segment_numbers;

		// 7.2.6
		int segment_page_association;
		int page_association_offset = (int) ra.getFilePointer () - ptr;
		if ( page_association_size ) {
			segment_page_association = ra.readInt ();
		} else {
			segment_page_association = ra.read ();
		}
		if ( segment_page_association < 0 ) {
			throw new IllegalStateException (
							MessageLocalization.getComposedMessage ( "page.1.invalid.for.segment.2.starting.at.3", String.valueOf ( segment_page_association ),
											String.valueOf ( segment_number ), String.valueOf ( ptr ) ) );
		}
		s.page = segment_page_association;
		s.page_association_size = page_association_size;
		s.page_association_offset = page_association_offset;

		if ( segment_page_association > 0 && !pages.containsKey ( segment_page_association ) ) {
			pages.put ( segment_page_association, new JBIG2Page ( segment_page_association ) );
		}
		if ( segment_page_association > 0 ) {
			pages.get ( segment_page_association ).addSegment ( s );
		} else {
			globals.add ( s );
		}

		s.dataLength = ra.readUnsignedInt ();

		int end_ptr = (int) ra.getFilePointer ();
		ra.seek ( ptr );
		byte[] header_data = new byte[end_ptr - ptr];
		ra.read ( header_data );
		s.headerData = header_data;

		return s;
	}

	void readFileHeader () throws IOException {
		ra.seek ( 0 );
		byte[] idstring = new byte[8];
		ra.read ( idstring );

		byte[] refidstring = { (byte) 0x97, 0x4A, 0x42, 0x32, 0x0D, 0x0A, 0x1A, 0x0A };

		for ( int i = 0; i < idstring.length; i++ ) {
			if ( idstring[i] != refidstring[i] ) {
				throw new IllegalStateException ( MessageLocalization.getComposedMessage ( "file.header.idstring.not.good.at.byte.1", i ) );
			}
		}

		int fileheaderflags = ra.read ();

		this.sequential = ( fileheaderflags & 0x1 ) == 0x1;
		boolean number_of_pages_known = ( fileheaderflags & 0x2 ) == 0x0;

		if ( ( fileheaderflags & 0xfc ) != 0x0 ) {
			throw new IllegalStateException ( MessageLocalization.getComposedMessage ( "file.header.flags.bits.2.7.not.0" ) );
		}

		if ( number_of_pages_known ) {
			ra.readInt ();
		}
	}

	public int numberOfPages () {
		return pages.size ();
	}

	public JBIG2Page getPage ( int page ) {
		return pages.get ( page );
	}

	public byte[] getGlobal ( boolean for_embedding ) {
		ByteArrayOutputStream os = new ByteArrayOutputStream ();
		try {
			for ( JBIG2Segment element : globals ) {
				if ( for_embedding &&
								( element.type == END_OF_FILE || element.type == END_OF_PAGE ) ) {
					continue;
				}
				os.write ( element.headerData );
				os.write ( element.data );
			}
			os.close ();
		} catch ( IOException ignored ) {
		}
		if ( os.size () <= 0 ) {
			return null;
		}
		return os.toByteArray ();
	}

	@Override
	public String toString () {
		if ( this.read ) {
			return "Jbig2SegmentReader: number of pages: " + this.numberOfPages ();
		} else {
			return "Jbig2SegmentReader in indeterminate state.";
		}
	}

	public static class JBIG2Segment implements Comparable<JBIG2Segment> {

		public final int segmentNumber;

		public long dataLength = -1;

		public int page = -1;

		public int[] referredToSegmentNumbers = null;

		public boolean[] segmentRetentionFlags = null;

		public int type = -1;

		public boolean deferredNonRetain = false;

		public int countOfReferredToSegments = -1;

		public byte[] data = null;

		public byte[] headerData = null;

		public boolean page_association_size = false;

		public int page_association_offset = -1;

		public JBIG2Segment ( int segment_number ) {
			this.segmentNumber = segment_number;
		}

		public int compareTo ( JBIG2Segment s ) {
			return this.segmentNumber - s.segmentNumber;
		}

	}


	public static class JBIG2Page {

		public final int page;

		private final SortedMap<Integer, JBIG2Segment> segs = new TreeMap<> ();

		public int pageBitmapWidth = -1;

		public int pageBitmapHeight = -1;

		public JBIG2Page ( int page ) {
			this.page = page;
		}

		public byte[] getData ( boolean for_embedding ) throws IOException {
			ByteArrayOutputStream os = new ByteArrayOutputStream ();
			for ( Integer sn : segs.keySet () ) {
				JBIG2Segment s = segs.get ( sn );

				if ( for_embedding &&
								( s.type == END_OF_FILE || s.type == END_OF_PAGE ) ) {
					continue;
				}

				if ( for_embedding ) {
					// change the page association to page 1
					byte[] headerData_emb = copyByteArray ( s.headerData );
					if ( s.page_association_size ) {
						headerData_emb[s.page_association_offset] = 0x0;
						headerData_emb[s.page_association_offset + 1] = 0x0;
						headerData_emb[s.page_association_offset + 2] = 0x0;
						headerData_emb[s.page_association_offset + 3] = 0x1;
					} else {
						headerData_emb[s.page_association_offset] = 0x1;
					}
					os.write ( headerData_emb );
				} else {
					os.write ( s.headerData );
				}
				os.write ( s.data );
			}
			os.close ();
			return os.toByteArray ();
		}

		public void addSegment ( JBIG2Segment s ) {
			segs.put ( s.segmentNumber, s );
		}

	}
}
