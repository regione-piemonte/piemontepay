/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Stack;


public class TernaryTree implements Cloneable, Serializable {

	protected static final int BLOCK_SIZE = 2048;    // allocation size for arrays

	private static final long serialVersionUID = 5313366505322983510L;

	protected char[] lo;

	protected char[] hi;

	protected char[] eq;

	protected char[] sc;

	protected CharVector kv;

	protected char root;

	protected char freenode;

	protected int length;    // number of items in tree

	TernaryTree () {
		init ();
	}

	public static int strcmp ( char[] a, int startA, char[] b, int startB ) {
		for ( ; a[startA] == b[startB]; startA++, startB++ ) {
			if ( a[startA] == 0 ) {
				return 0;
			}
		}
		return a[startA] - b[startB];
	}

	public static void strcpy ( char[] dst, int di, char[] src, int si ) {
		while ( src[si] != 0 ) {
			dst[di++] = src[si++];
		}
		dst[di] = 0;
	}

	public static int strlen ( char[] a, int start ) {
		int len = 0;
		for ( int i = start; i < a.length && a[i] != 0; i++ ) {
			len++;
		}
		return len;
	}

	public static int strlen ( char[] a ) {
		return strlen ( a, 0 );
	}

	protected void init () {
		root = 0;
		freenode = 1;
		length = 0;
		lo = new char[BLOCK_SIZE];
		hi = new char[BLOCK_SIZE];
		eq = new char[BLOCK_SIZE];
		sc = new char[BLOCK_SIZE];
		kv = new CharVector ();
	}

	public void insert ( String key, char val ) {
		// make sure we have enough room in the arrays
		int len = key.length ()
						+ 1;    // maximum number of nodes that may be generated
		if ( freenode + len > eq.length ) {
			redimNodeArrays ( eq.length + BLOCK_SIZE );
		}
		char[] strkey = new char[len--];
		key.getChars ( 0, len, strkey, 0 );
		strkey[len] = 0;
		root = insert ( root, strkey, 0, val );
	}

	public void insert ( char[] key, int start, char val ) {
		int len = strlen ( key ) + 1;
		if ( freenode + len > eq.length ) {
			redimNodeArrays ( eq.length + BLOCK_SIZE );
		}
		root = insert ( root, key, start, val );
	}

	private char insert ( char p, char[] key, int start, char val ) {
		int len = strlen ( key, start );
		if ( p == 0 ) {
			p = freenode++;
			eq[p] = val;           // holds data
			length++;
			hi[p] = 0;
			if ( len > 0 ) {
				sc[p] = 0xFFFF;    // indicates branch is compressed
				lo[p] = (char) kv.alloc ( len
								+ 1 );    // use 'lo' to hold pointer to key
				strcpy ( kv.getArray (), lo[p], key, start );
			} else {
				sc[p] = 0;
				lo[p] = 0;
			}
			return p;
		}

		if ( sc[p] == 0xFFFF ) {
			char pp = freenode++;
			lo[pp] = lo[p];    // previous pointer to key
			eq[pp] = eq[p];    // previous pointer to data
			lo[p] = 0;
			if ( len > 0 ) {
				sc[p] = kv.get ( lo[pp] );
				eq[p] = pp;
				lo[pp]++;
				if ( kv.get ( lo[pp] ) == 0 ) {
					// key completely decompressed leaving garbage in key array
					lo[pp] = 0;
					sc[pp] = 0;
					hi[pp] = 0;
				} else {
					// we only got first char of key, rest is still there
					sc[pp] = 0xFFFF;
				}
			} else {
				// In this case we can save a node by swapping the new node
				// with the compressed node
				sc[pp] = 0xFFFF;
				hi[p] = pp;
				sc[p] = 0;
				eq[p] = val;
				length++;
				return p;
			}
		}
		char s = key[start];
		if ( s < sc[p] ) {
			lo[p] = insert ( lo[p], key, start, val );
		} else if ( s == sc[p] ) {
			if ( s != 0 ) {
				eq[p] = insert ( eq[p], key, start + 1, val );
			} else {
				// key already in tree, overwrite data
				eq[p] = val;
			}
		} else {
			hi[p] = insert ( hi[p], key, start, val );
		}
		return p;
	}

	public int find ( String key ) {
		int len = key.length ();
		char[] strkey = new char[len + 1];
		key.getChars ( 0, len, strkey, 0 );
		strkey[len] = 0;

		return find ( strkey, 0 );
	}

	public int find ( char[] key, int start ) {
		int d;
		char p = root;
		int i = start;
		char c;

		while ( p != 0 ) {
			if ( sc[p] == 0xFFFF ) {
				if ( strcmp ( key, i, kv.getArray (), lo[p] ) == 0 ) {
					return eq[p];
				} else {
					return -1;
				}
			}
			c = key[i];
			d = c - sc[p];
			if ( d == 0 ) {
				if ( c == 0 ) {
					return eq[p];
				}
				i++;
				p = eq[p];
			} else if ( d < 0 ) {
				p = lo[p];
			} else {
				p = hi[p];
			}
		}
		return -1;
	}

	private void redimNodeArrays ( int newsize ) {
		int len = Math.min ( newsize, lo.length );
		char[] na = new char[newsize];
		System.arraycopy ( lo, 0, na, 0, len );
		lo = na;
		na = new char[newsize];
		System.arraycopy ( hi, 0, na, 0, len );
		hi = na;
		na = new char[newsize];
		System.arraycopy ( eq, 0, na, 0, len );
		eq = na;
		na = new char[newsize];
		System.arraycopy ( sc, 0, na, 0, len );
		sc = na;
	}

	public int size () {
		return length;
	}

	@Override
	public Object clone () {
		TernaryTree t = new TernaryTree ();
		t.lo = this.lo.clone ();
		t.hi = this.hi.clone ();
		t.eq = this.eq.clone ();
		t.sc = this.sc.clone ();
		t.kv = (CharVector) this.kv.clone ();
		t.root = this.root;
		t.freenode = this.freenode;
		t.length = this.length;

		return t;
	}

	protected void insertBalanced ( String[] k, char[] v, int offset, int n ) {
		int m;
		if ( n < 1 ) {
			return;
		}
		m = n >> 1;

		insert ( k[m + offset], v[m + offset] );
		insertBalanced ( k, v, offset, m );

		insertBalanced ( k, v, offset + m + 1, n - m - 1 );
	}

	public void balance () {

		int i = 0, n = length;
		String[] k = new String[n];
		char[] v = new char[n];
		Iterator iter = new Iterator ();
		while ( iter.hasMoreElements () ) {
			v[i] = iter.getValue ();
			k[i++] = iter.nextElement ();
		}
		init ();
		insertBalanced ( k, v, 0, n );

	}

	public void trimToSize () {
		balance ();

		// redimension the node arrays
		redimNodeArrays ( freenode );

		// ok, compact kv array
		CharVector kx = new CharVector ();
		kx.alloc ( 1 );
		TernaryTree map = new TernaryTree ();
		compact ( kx, map, root );
		kv = kx;
		kv.trimToSize ();
	}

	private void compact ( CharVector kx, TernaryTree map, char p ) {
		int k;
		if ( p == 0 ) {
			return;
		}
		if ( sc[p] == 0xFFFF ) {
			k = map.find ( kv.getArray (), lo[p] );
			if ( k < 0 ) {
				k = kx.alloc ( strlen ( kv.getArray (), lo[p] ) + 1 );
				strcpy ( kx.getArray (), k, kv.getArray (), lo[p] );
				map.insert ( kx.getArray (), k, (char) k );
			}
			lo[p] = (char) k;
		} else {
			compact ( kx, map, lo[p] );
			if ( sc[p] != 0 ) {
				compact ( kx, map, eq[p] );
			}
			compact ( kx, map, hi[p] );
		}
	}

	public Enumeration<String> keys () {
		return new Iterator ();
	}

	public void printStats () {
		System.out.println ( "Number of keys = " + length );
		System.out.println ( "Node count = " + Integer.toString ( freenode ) );
		System.out.println ( "Key Array length = "
						+ kv.length () );

	}

	public class Iterator implements Enumeration<String> {

		int cur;

		String curkey;

		Stack<Item> ns;

		StringBuffer ks;

		public Iterator () {
			cur = -1;
			ns = new Stack<> ();
			ks = new StringBuffer ();
			rewind ();
		}

		public void rewind () {
			ns.removeAllElements ();
			ks.setLength ( 0 );
			cur = root;
			run ();
		}

		public String nextElement () {
			String res = curkey;
			cur = up ();
			run ();
			return res;
		}

		public char getValue () {
			if ( cur >= 0 ) {
				return eq[cur];
			}
			return 0;
		}

		public boolean hasMoreElements () {
			return cur != -1;
		}

		private int up () {
			new Item ();
			Item i;
			int res = 0;

			if ( ns.empty () ) {
				return -1;
			}

			if ( cur != 0 && sc[cur] == 0 ) {
				return lo[cur];
			}

			boolean climb = true;

			while ( climb ) {
				i = ns.pop ();
				i.child++;
				switch ( i.child ) {
				case 1:
					if ( sc[i.parent] != 0 ) {
						res = eq[i.parent];
						ns.push ( i.clone () );
						ks.append ( sc[i.parent] );
					} else {
						i.child++;
						ns.push ( i.clone () );
						res = hi[i.parent];
					}
					climb = false;
					break;

				case 2:
					res = hi[i.parent];
					ns.push ( i.clone () );
					if ( ks.length () > 0 ) {
						ks.setLength ( ks.length () - 1 );    // pop
					}
					climb = false;
					break;

				default:
					if ( ns.empty () ) {
						return -1;
					}
					break;
				}
			}
			return res;
		}

		private void run () {
			if ( cur == -1 ) {
				return;
			}

			boolean leaf = false;
			while ( true ) {
				// first go down on low branch until leaf or compressed branch
				while ( cur != 0 ) {
					if ( sc[cur] == 0xFFFF ) {
						leaf = true;
						break;
					}
					ns.push ( new Item ( (char) cur, '\u0000' ) );
					if ( sc[cur] == 0 ) {
						leaf = true;
						break;
					}
					cur = lo[cur];
				}
				if ( leaf ) {
					break;
				}
				// nothing found, go up one node and try again
				cur = up ();
				if ( cur == -1 ) {
					return;
				}
			}
			StringBuilder buf = new StringBuilder ( ks.toString () );
			if ( sc[cur] == 0xFFFF ) {
				int p = lo[cur];
				while ( kv.get ( p ) != 0 ) {
					buf.append ( kv.get ( p++ ) );
				}
			}
			curkey = buf.toString ();
		}

		private class Item implements Cloneable {

			char parent;

			char child;

			public Item () {
				parent = 0;
				child = 0;
			}

			public Item ( char p, char c ) {
				parent = p;
				child = c;
			}

			@Override
			public Item clone () {
				return new Item ( parent, child );
			}

		}

	}

}

