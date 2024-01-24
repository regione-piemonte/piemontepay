/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class LongHashtable implements Cloneable {

	private final float loadFactor;

	private transient Entry[] table;

	private transient int count;

	private int threshold;

	public LongHashtable () {
		this ( 150, 0.75f );
	}

	public LongHashtable ( int initialCapacity, float loadFactor ) {
		super ();
		if ( initialCapacity < 0 ) {
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "illegal.capacity.1", initialCapacity ) );
		}
		if ( loadFactor <= 0 ) {
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "illegal.load.1", String.valueOf ( loadFactor ) ) );
		}
		if ( initialCapacity == 0 ) {
			initialCapacity = 1;
		}
		this.loadFactor = loadFactor;
		table = new Entry[initialCapacity];
		threshold = (int) ( initialCapacity * loadFactor );
	}

	public int size () {
		return count;
	}

	public boolean isEmpty () {
		return count == 0;
	}

	public boolean contains ( long value ) {

		Entry[] tab = table;
		for ( int i = tab.length; i-- > 0; ) {
			for ( Entry e = tab[i]; e != null; e = e.next ) {
				if ( e.value == value ) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsKey ( long key ) {
		Entry[] tab = table;
		int hash = (int) ( key ^ ( key >>> 32 ) );
		int index = ( hash & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index]; e != null; e = e.next ) {
			if ( e.hash == hash && e.key == key ) {
				return true;
			}
		}
		return false;
	}

	public long get ( long key ) {
		Entry[] tab = table;
		int hash = (int) ( key ^ ( key >>> 32 ) );
		int index = ( hash & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index]; e != null; e = e.next ) {
			if ( e.hash == hash && e.key == key ) {
				return e.value;
			}
		}
		return 0;
	}

	protected void rehash () {
		int oldCapacity = table.length;
		Entry[] oldMap = table;

		int newCapacity = oldCapacity * 2 + 1;
		Entry[] newMap = new Entry[newCapacity];

		threshold = (int) ( newCapacity * loadFactor );
		table = newMap;

		for ( int i = oldCapacity; i-- > 0; ) {
			for ( Entry old = oldMap[i]; old != null; ) {
				Entry e = old;
				old = old.next;

				int index = ( e.hash & 0x7FFFFFFF ) % newCapacity;
				e.next = newMap[index];
				newMap[index] = e;
			}
		}
	}

	public long put ( long key, long value ) {
		Entry[] tab = table;
		int hash = (int) ( key ^ ( key >>> 32 ) );
		int index = ( hash & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index]; e != null; e = e.next ) {
			if ( e.hash == hash && e.key == key ) {
				long old = e.value;
				e.value = value;
				return old;
			}
		}

		if ( count >= threshold ) {
			rehash ();

			tab = table;
			index = ( hash & 0x7FFFFFFF ) % tab.length;
		}

		Entry e = new Entry ( hash, key, value, tab[index] );
		tab[index] = e;
		count++;
		return 0;
	}

	public long remove ( long key ) {
		Entry[] tab = table;
		int hash = (int) ( key ^ ( key >>> 32 ) );
		int index = ( hash & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index], prev = null; e != null; prev = e, e = e.next ) {
			if ( e.hash == hash && e.key == key ) {
				if ( prev != null ) {
					prev.next = e.next;
				} else {
					tab[index] = e.next;
				}
				count--;
				long oldValue = e.value;
				e.value = 0;
				return oldValue;
			}
		}
		return 0;
	}

	public void clear () {
		Entry[] tab = table;
		for ( int index = tab.length; --index >= 0; ) {
			tab[index] = null;
		}
		count = 0;
	}

	public long[] getKeys () {
		long[] res = new long[count];
		int ptr = 0;
		int index = table.length;
		Entry entry = null;
		while ( true ) {
			if ( entry == null )
				while ( index-- > 0 && ( entry = table[index] ) == null )
					;
			if ( entry == null )
				break;
			Entry e = entry;
			entry = e.next;
			res[ptr++] = e.key;
		}
		return res;
	}

	@Override
	public Object clone () {
		try {
			LongHashtable t = (LongHashtable) super.clone ();
			t.table = new Entry[table.length];
			for ( int i = table.length; i-- > 0; ) {
				t.table[i] = table[i] != null
								? (Entry) table[i].clone () : null;
			}
			return t;
		} catch ( CloneNotSupportedException e ) {
			throw new InternalError ();
		}
	}

	static class Entry {

		int hash;

		long key;

		long value;

		Entry next;

		protected Entry ( int hash, long key, long value, Entry next ) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public long getKey () {
			return key;
		}

		public long getValue () {
			return value;
		}

		@Override
		protected Object clone () throws CloneNotSupportedException {
			return new Entry ( hash, key, value, next != null ? (Entry) next.clone () : null );
		}
	}
}
