/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.codec;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.RandomAccessFileOrArray;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class TIFFDirectory implements Serializable {

	private static final long serialVersionUID = -168636766193675380L;

	private static final int[] sizeOfType = {
					0, //  0 = n/a
					1, //  1 = byte
					1, //  2 = ascii
					2, //  3 = short
					4, //  4 = long
					8, //  5 = rational
					1, //  6 = sbyte
					1, //  7 = undefined
					2, //  8 = sshort
					4, //  9 = slong
					8, // 10 = srational
					4, // 11 = float
					8  // 12 = double
	};

	boolean isBigEndian;

	int numEntries;

	TIFFField[] fields;

	Hashtable<Integer, Integer> fieldIndex = new Hashtable<> ();

	long IFDOffset = 8;

	long nextIFDOffset = 0;

	public TIFFDirectory ( RandomAccessFileOrArray stream, int directory )
					throws IOException {

		long global_save_offset = stream.getFilePointer ();
		long ifd_offset;

		// Read the TIFF header
		stream.seek ( 0L );
		int endian = stream.readUnsignedShort ();
		if ( !isValidEndianTag ( endian ) ) {
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "bad.endianness.tag.not.0x4949.or.0x4d4d" ) );
		}
		isBigEndian = endian == 0x4d4d;

		int magic = readUnsignedShort ( stream );
		if ( magic != 42 ) {
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "bad.magic.number.should.be.42" ) );
		}

		// Get the initial ifd offset as an unsigned int (using a long)
		ifd_offset = readUnsignedInt ( stream );

		for ( int i = 0; i < directory; i++ ) {
			if ( ifd_offset == 0L ) {
				throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "directory.number.too.large" ) );
			}

			stream.seek ( ifd_offset );
			int entries = readUnsignedShort ( stream );
			stream.skip ( 12L * entries );

			ifd_offset = readUnsignedInt ( stream );
		}

		stream.seek ( ifd_offset );
		initialize ( stream );
		stream.seek ( global_save_offset );
	}

	private static boolean isValidEndianTag ( int endian ) {
		return endian == 0x4949 || endian == 0x4d4d;
	}

	private void initialize ( RandomAccessFileOrArray stream ) throws IOException {
		long nextTagOffset = 0L;
		long maxOffset = stream.length ();
		int i, j;

		IFDOffset = stream.getFilePointer ();

		numEntries = readUnsignedShort ( stream );
		fields = new TIFFField[numEntries];

		for ( i = 0; i < numEntries && nextTagOffset < maxOffset; i++ ) {
			int tag = readUnsignedShort ( stream );
			int type = readUnsignedShort ( stream );
			int count = (int) readUnsignedInt ( stream );
			boolean processTag = true;

			// The place to return to to read the next tag
			nextTagOffset = stream.getFilePointer () + 4;

			try {
				// If the tag data can't fit in 4 bytes, the next 4 bytes
				// contain the starting offset of the data
				if ( count * sizeOfType[type] > 4 ) {
					long valueOffset = readUnsignedInt ( stream );

					// bounds check offset for EOF
					if ( valueOffset < maxOffset ) {
						stream.seek ( valueOffset );
					} else {
						// bad offset pointer .. skip tag
						processTag = false;
					}
				}
			} catch ( ArrayIndexOutOfBoundsException ae ) {
				// if the data type is unknown we should skip this TIFF Field
				processTag = false;
			}

			if ( processTag ) {
				fieldIndex.put ( tag, i );
				Object obj = null;

				switch ( type ) {
				case TIFFField.TIFF_BYTE:
				case TIFFField.TIFF_SBYTE:
				case TIFFField.TIFF_UNDEFINED:
				case TIFFField.TIFF_ASCII:
					byte[] bvalues = new byte[count];
					stream.readFully ( bvalues, 0, count );

					if ( type == TIFFField.TIFF_ASCII ) {

						// Can be multiple strings
						int index = 0, prevIndex = 0;
						ArrayList<String> v = new ArrayList<> ();

						while ( index < count ) {

							while ( index < count && bvalues[index++] != 0 )
								;

							// When we encountered zero, means one string has ended
							v.add ( new String ( bvalues, prevIndex,
											( index - prevIndex ) ) );
							prevIndex = index;
						}

						count = v.size ();
						String[] strings = new String[count];
						for ( int c = 0; c < count; c++ ) {
							strings[c] = v.get ( c );
						}

						obj = strings;
					} else {
						obj = bvalues;
					}

					break;

				case TIFFField.TIFF_SHORT:
					char[] cvalues = new char[count];
					for ( j = 0; j < count; j++ ) {
						cvalues[j] = (char) readUnsignedShort ( stream );
					}
					obj = cvalues;
					break;

				case TIFFField.TIFF_LONG:
					long[] lvalues = new long[count];
					for ( j = 0; j < count; j++ ) {
						lvalues[j] = readUnsignedInt ( stream );
					}
					obj = lvalues;
					break;

				case TIFFField.TIFF_RATIONAL:
					long[][] llvalues = new long[count][2];
					for ( j = 0; j < count; j++ ) {
						llvalues[j][0] = readUnsignedInt ( stream );
						llvalues[j][1] = readUnsignedInt ( stream );
					}
					obj = llvalues;
					break;

				case TIFFField.TIFF_SSHORT:
					short[] svalues = new short[count];
					for ( j = 0; j < count; j++ ) {
						svalues[j] = readShort ( stream );
					}
					obj = svalues;
					break;

				case TIFFField.TIFF_SLONG:
					int[] ivalues = new int[count];
					for ( j = 0; j < count; j++ ) {
						ivalues[j] = readInt ( stream );
					}
					obj = ivalues;
					break;

				case TIFFField.TIFF_SRATIONAL:
					int[][] iivalues = new int[count][2];
					for ( j = 0; j < count; j++ ) {
						iivalues[j][0] = readInt ( stream );
						iivalues[j][1] = readInt ( stream );
					}
					obj = iivalues;
					break;

				case TIFFField.TIFF_FLOAT:
					float[] fvalues = new float[count];
					for ( j = 0; j < count; j++ ) {
						fvalues[j] = readFloat ( stream );
					}
					obj = fvalues;
					break;

				case TIFFField.TIFF_DOUBLE:
					double[] dvalues = new double[count];
					for ( j = 0; j < count; j++ ) {
						dvalues[j] = readDouble ( stream );
					}
					obj = dvalues;
					break;

				default:
					break;
				}

				fields[i] = new TIFFField ( tag, type, count, obj );
			}

			stream.seek ( nextTagOffset );
		}

		// Read the offset of the next IFD.
		try {
			nextIFDOffset = readUnsignedInt ( stream );
		} catch ( Exception e ) {
			nextIFDOffset = 0;
		}
	}

	public TIFFField getField ( int tag ) {
		Integer i = fieldIndex.get ( tag );
		if ( i == null ) {
			return null;
		} else {
			return fields[i];
		}
	}

	public boolean isTagPresent ( int tag ) {
		return fieldIndex.containsKey ( tag );
	}

	public int[] getTags () {
		int[] tags = new int[fieldIndex.size ()];
		Enumeration<Integer> e = fieldIndex.keys ();
		int i = 0;

		while ( e.hasMoreElements () ) {
			tags[i++] = e.nextElement ();
		}

		return tags;
	}

	public TIFFField[] getFields () {
		return fields;
	}

	public long getFieldAsLong ( int tag, int index ) {
		Integer i = fieldIndex.get ( tag );
		return fields[i].getAsLong ( index );
	}

	public long getFieldAsLong ( int tag ) {
		return getFieldAsLong ( tag, 0 );
	}

	private short readShort ( RandomAccessFileOrArray stream )
					throws IOException {
		if ( isBigEndian ) {
			return stream.readShort ();
		} else {
			return stream.readShortLE ();
		}
	}

	private int readUnsignedShort ( RandomAccessFileOrArray stream )
					throws IOException {
		if ( isBigEndian ) {
			return stream.readUnsignedShort ();
		} else {
			return stream.readUnsignedShortLE ();
		}
	}

	private int readInt ( RandomAccessFileOrArray stream )
					throws IOException {
		if ( isBigEndian ) {
			return stream.readInt ();
		} else {
			return stream.readIntLE ();
		}
	}

	private long readUnsignedInt ( RandomAccessFileOrArray stream )
					throws IOException {
		if ( isBigEndian ) {
			return stream.readUnsignedInt ();
		} else {
			return stream.readUnsignedIntLE ();
		}
	}

	private float readFloat ( RandomAccessFileOrArray stream )
					throws IOException {
		if ( isBigEndian ) {
			return stream.readFloat ();
		} else {
			return stream.readFloatLE ();
		}
	}

	// Utilities

	private double readDouble ( RandomAccessFileOrArray stream )
					throws IOException {
		if ( isBigEndian ) {
			return stream.readDouble ();
		} else {
			return stream.readDoubleLE ();
		}
	}

}
