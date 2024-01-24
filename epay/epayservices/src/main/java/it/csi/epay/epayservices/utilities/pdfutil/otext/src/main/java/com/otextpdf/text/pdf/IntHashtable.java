/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.util.Arrays;


public class IntHashtable implements Cloneable {

	private final float loadFactor;

	private transient Entry[] table;

	private transient int count;

	private int threshold;

	public IntHashtable () {
		this ( 150, 0.75f );
	}

	public IntHashtable ( int initialCapacity ) {
		this ( initialCapacity, 0.75f );
	}

	public IntHashtable ( int initialCapacity, float loadFactor ) {
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

	public boolean contains ( int value ) {

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

	public boolean containsKey ( int key ) {
		Entry[] tab = table;
		int index = ( key & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index]; e != null; e = e.next ) {
			if ( e.hash == key && e.key == key ) {
				return true;
			}
		}
		return false;
	}

	public int get ( int key ) {
		Entry[] tab = table;
		int index = ( key & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index]; e != null; e = e.next ) {
			if ( e.hash == key && e.key == key ) {
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

	public int put ( int key, int value ) {
		// Makes sure the key is not already in the hashtable.
		Entry[] tab = table;
		int index = ( key & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index]; e != null; e = e.next ) {
			if ( e.hash == key && e.key == key ) {
				int old = e.value;
				//e.addValue(old);
				e.value = value;
				return old;
			}
		}

		if ( count >= threshold ) {
			// Rehash the table if the threshold is exceeded
			rehash ();

			tab = table;
			index = ( key & 0x7FFFFFFF ) % tab.length;
		}

		// Creates the new entry.
		Entry e = new Entry ( key, key, value, tab[index] );
		tab[index] = e;
		count++;
		return 0;
	}

	public int remove ( int key ) {
		Entry[] tab = table;
		int index = ( key & 0x7FFFFFFF ) % tab.length;
		for ( Entry e = tab[index], prev = null; e != null; prev = e, e = e.next ) {
			if ( e.hash == key && e.key == key ) {
				if ( prev != null ) {
					prev.next = e.next;
				} else {
					tab[index] = e.next;
				}
				count--;
				int oldValue = e.value;
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

	public int[] toOrderedKeys () {
		int[] res = getKeys ();
		Arrays.sort ( res );
		return res;
	}

	public int[] getKeys () {
		int[] res = new int[count];
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
			IntHashtable t = (IntHashtable) super.clone ();
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

		int key;

		int value;

		Entry next;

		protected Entry ( int hash, int key, int value, Entry next ) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public int getKey () {
			return key;
		}

		public int getValue () {
			return value;
		}

		@Override
		protected Object clone () throws CloneNotSupportedException {
			return new Entry ( hash, key, value, next != null ? (Entry) next.clone () : null );
		}
	}

}
