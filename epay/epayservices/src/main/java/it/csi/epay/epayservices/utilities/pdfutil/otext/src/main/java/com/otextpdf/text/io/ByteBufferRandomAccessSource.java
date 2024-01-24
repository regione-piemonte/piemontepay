/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;


class ByteBufferRandomAccessSource implements RandomAccessSource {

	private final ByteBuffer byteBuffer;

	public ByteBufferRandomAccessSource ( final ByteBuffer byteBuffer ) {
		this.byteBuffer = byteBuffer;
	}

	private static void clean ( final ByteBuffer buffer ) {
		if ( buffer == null || !buffer.isDirect () )
			return;

		AccessController.doPrivileged ( (PrivilegedAction<Boolean>) () -> {
			Boolean success = Boolean.FALSE;
			try {
				Method getCleanerMethod = buffer.getClass ().getMethod ( "cleaner", (Class<?>[]) null );
				getCleanerMethod.setAccessible ( true );
				Object cleaner = getCleanerMethod.invoke ( buffer, (Object[]) null );
				Method clean = cleaner.getClass ().getMethod ( "clean", (Class<?>[]) null );
				clean.invoke ( cleaner, (Object[]) null );
				success = Boolean.TRUE;
			} catch ( Exception ignored ) {
			}
			return success;
		} );

	}

	public int get ( long position ) throws IOException {
		if ( position > Integer.MAX_VALUE )
			throw new IllegalArgumentException ( "Position must be less than Integer.MAX_VALUE" );

		try {

			if ( position >= byteBuffer.limit () )
				return -1;

			byte b = byteBuffer.get ( (int) position );

			return b & 0xff;
		} catch ( BufferUnderflowException e ) {
			return -1;
		}
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		if ( position > Integer.MAX_VALUE )
			throw new IllegalArgumentException ( "Position must be less than Integer.MAX_VALUE" );

		if ( position >= byteBuffer.limit () )
			return -1;

		byteBuffer.position ( (int) position );
		int bytesFromThisBuffer = Math.min ( len, byteBuffer.remaining () );
		byteBuffer.get ( bytes, off, bytesFromThisBuffer );

		return bytesFromThisBuffer;

	}

	public long length () {
		return byteBuffer.limit ();
	}

	public void close () throws IOException {
		clean ( byteBuffer );
	}
}
