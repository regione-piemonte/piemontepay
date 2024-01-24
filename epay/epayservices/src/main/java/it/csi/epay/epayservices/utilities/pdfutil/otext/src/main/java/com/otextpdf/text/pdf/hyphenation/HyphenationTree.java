/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class HyphenationTree extends TernaryTree
				implements PatternConsumer {

	private static final long serialVersionUID = -7763254239309429432L;

	protected ByteVector vspace;

	protected HashMap<String, ArrayList<Object>> stoplist;

	protected TernaryTree classmap;

	private transient TernaryTree ivalues;

	public HyphenationTree () {
		stoplist = new HashMap<> ( 23 );    // usually a small table
		classmap = new TernaryTree ();
		vspace = new ByteVector ();
		vspace.alloc ( 1 );    // this reserves index 0, which we don't use
	}

	protected int packValues ( String values ) {
		int i, n = values.length ();
		int m = ( n & 1 ) == 1 ? ( n >> 1 ) + 2 : ( n >> 1 ) + 1;
		int offset = vspace.alloc ( m );
		byte[] va = vspace.getArray ();
		for ( i = 0; i < n; i++ ) {
			int j = i >> 1;
			byte v = (byte) ( values.charAt ( i ) - '0' + 1 & 0x0f );
			if ( ( i & 1 ) == 1 ) {
				va[j + offset] = (byte) ( va[j + offset] | v );
			} else {
				va[j + offset] = (byte) ( v << 4 );    // big endian
			}
		}
		va[m - 1 + offset] = 0;    // terminator
		return offset;
	}

	public void loadSimplePatterns ( InputStream stream ) {
		SimplePatternParser pp = new SimplePatternParser ();
		ivalues = new TernaryTree ();

		pp.parse ( stream, this );

		// patterns/values should be now in the tree
		// let's optimize a bit
		trimToSize ();
		vspace.trimToSize ();
		classmap.trimToSize ();

		// get rid of the auxiliary map
		ivalues = null;
	}

	protected int hstrcmp ( char[] s, int si, char[] t, int ti ) {
		for ( ; s[si] == t[ti]; si++, ti++ ) {
			if ( s[si] == 0 ) {
				return 0;
			}
		}
		if ( t[ti] == 0 ) {
			return 0;
		}
		return s[si] - t[ti];
	}

	protected byte[] getValues ( int k ) {
		StringBuilder buf = new StringBuilder ();
		byte v = vspace.get ( k++ );
		while ( v != 0 ) {
			char c = (char) ( ( v >>> 4 ) - 1 );
			buf.append ( c );
			c = (char) ( v & 0x0f );
			if ( c == 0 ) {
				break;
			}
			c = (char) ( c - 1 );
			buf.append ( c );
			v = vspace.get ( k++ );
		}
		byte[] res = new byte[buf.length ()];
		for ( int i = 0; i < res.length; i++ ) {
			res[i] = (byte) buf.charAt ( i );
		}
		return res;
	}

	protected void searchPatterns ( char[] word, int index, byte[] il ) {
		byte[] values;
		int i = index;
		char p, q;
		char sp = word[i];
		p = root;

		while ( p > 0 && p < sc.length ) {
			if ( sc[p] == 0xFFFF ) {
				if ( hstrcmp ( word, i, kv.getArray (), lo[p] ) == 0 ) {
					values = getValues ( eq[p] );    // data pointer is in eq[]
					int j = index;
					for ( byte value : values ) {
						if ( j < il.length && value > il[j] ) {
							il[j] = value;
						}
						j++;
					}
				}
				return;
			}
			int d = sp - sc[p];
			if ( d == 0 ) {
				if ( sp == 0 ) {
					break;
				}
				sp = word[++i];
				p = eq[p];
				q = p;

				// look for a pattern ending at this position by searching for
				// the null char ( splitchar == 0 )
				while ( q > 0 && q < sc.length ) {
					if ( sc[q] == 0xFFFF ) {        // stop at compressed branch
						break;
					}
					if ( sc[q] == 0 ) {
						values = getValues ( eq[q] );
						int j = index;
						for ( byte value : values ) {
							if ( j < il.length && value > il[j] ) {
								il[j] = value;
							}
							j++;
						}
						break;
					} else {
						q = lo[q];

					}
				}
			} else {
				p = d < 0 ? lo[p] : hi[p];
			}
		}
	}

	public Hyphenation hyphenate ( String word, int remainCharCount,
					int pushCharCount ) {
		char[] w = word.toCharArray ();
		return hyphenate ( w, 0, w.length, remainCharCount, pushCharCount );
	}

	public Hyphenation hyphenate ( char[] w, int offset, int len,
					int remainCharCount, int pushCharCount ) {
		int i;
		char[] word = new char[len + 3];

		// normalize word
		char[] c = new char[2];
		int iIgnoreAtBeginning = 0;
		int iLength = len;
		boolean bEndOfLetters = false;
		for ( i = 1; i <= len; i++ ) {
			c[0] = w[offset + i - 1];
			int nc = classmap.find ( c, 0 );
			if ( nc < 0 ) {    // found a non-letter character ...
				if ( i == 1 + iIgnoreAtBeginning ) {
					// ... before any letter character
					iIgnoreAtBeginning++;
				} else {
					// ... after a letter character
					bEndOfLetters = true;
				}
				iLength--;
			} else {
				if ( !bEndOfLetters ) {
					word[i - iIgnoreAtBeginning] = (char) nc;
				} else {
					return null;
				}
			}
		}
		int origlen = len;
		len = iLength;
		if ( len < remainCharCount + pushCharCount ) {
			// word is too short to be hyphenated
			return null;
		}
		int[] result = new int[len + 1];
		int k = 0;

		// check exception list first
		String sw = new String ( word, 1, len );
		if ( stoplist.containsKey ( sw ) ) {
			// assume only simple hyphens (Hyphen.pre="-", Hyphen.post = Hyphen.no = null)
			ArrayList<Object> hw = stoplist.get ( sw );
			int j = 0;
			for ( i = 0; i < hw.size (); i++ ) {
				Object o = hw.get ( i );
				// j = index(sw) = letterindex(word)?
				// result[k] = corresponding index(w)
				if ( o instanceof String ) {
					j += ( (String) o ).length ();
					if ( j >= remainCharCount && j < len - pushCharCount ) {
						result[k++] = j + iIgnoreAtBeginning;
					}
				}
			}
		} else {
			word[0] = '.';                    // word start marker
			word[len + 1] = '.';              // word end marker
			word[len + 2] = 0;                // null terminated
			byte[] il = new byte[len + 3];    // initialized to zero
			for ( i = 0; i < len + 1; i++ ) {
				searchPatterns ( word, i, il );
			}

			for ( i = 0; i < len; i++ ) {
				if ( ( il[i + 1] & 1 ) == 1 && i >= remainCharCount
								&& i <= len - pushCharCount ) {
					result[k++] = i + iIgnoreAtBeginning;
				}
			}
		}

		if ( k > 0 ) {
			// trim result array
			int[] res = new int[k];
			System.arraycopy ( result, 0, res, 0, k );
			return new Hyphenation ( new String ( w, offset, origlen ), res );
		} else {
			return null;
		}
	}

	public void addClass ( String chargroup ) {
		if ( !chargroup.isEmpty () ) {
			char equivChar = chargroup.charAt ( 0 );
			char[] key = new char[2];
			for ( int i = 0; i < chargroup.length (); i++ ) {
				key[0] = chargroup.charAt ( i );
				classmap.insert ( key, 0, equivChar );
			}
		}
	}

	public void addException ( String word, ArrayList<Object> hyphenatedword ) {
		stoplist.put ( word, hyphenatedword );
	}

	public void addPattern ( String pattern, String ivalue ) {
		int k = ivalues.find ( ivalue );
		if ( k <= 0 ) {
			k = packValues ( ivalue );
			ivalues.insert ( ivalue, (char) k );
		}
		insert ( pattern, (char) k );
	}

	@Override
	public void printStats () {
		System.out.println ( "Value space size = "
						+ vspace.length () );
		super.printStats ();
	}
}
