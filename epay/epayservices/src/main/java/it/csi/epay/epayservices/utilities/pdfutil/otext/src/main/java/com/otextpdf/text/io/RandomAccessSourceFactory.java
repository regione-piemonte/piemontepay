/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;


public final class RandomAccessSourceFactory {

	private boolean forceRead = false;

	private boolean usePlainRandomAccess = false;

	public RandomAccessSourceFactory () {
	}

	public RandomAccessSourceFactory setForceRead ( boolean forceRead ) {
		this.forceRead = forceRead;
		return this;
	}

	public RandomAccessSourceFactory setUsePlainRandomAccess ( boolean usePlainRandomAccess ) {
		this.usePlainRandomAccess = usePlainRandomAccess;
		return this;
	}

	public RandomAccessSource createSource ( byte[] data ) {
		return new ArrayRandomAccessSource ( data );
	}

	public RandomAccessSource createSource ( URL url ) throws IOException {
		try ( InputStream is = url.openStream () ) {
			return createSource ( is );
		}
	}

	public RandomAccessSource createSource ( InputStream is ) throws IOException {
		try {
			return createSource ( StreamUtil.inputStreamToArray ( is ) );
		} finally {
			try {
				is.close ();
			} catch ( IOException ignored ) {
			}
		}
	}

	public RandomAccessSource createBestSource ( String filename ) throws IOException {
		File file = new File ( filename );
		if ( !file.canRead () ) {
			if ( filename.startsWith ( "file:/" )
							|| filename.startsWith ( "http://" )
							|| filename.startsWith ( "https://" )
							|| filename.startsWith ( "jar:" )
							|| filename.startsWith ( "wsjar:" )
							|| filename.startsWith ( "vfszip:" ) ) {
				return createSource ( new URL ( filename ) );
			} else {
				return createByReadingToMemory ( filename );
			}
		}

		if ( forceRead ) {
			return createByReadingToMemory ( Files.newInputStream ( Paths.get ( filename ) ) );
		}

		String openMode = "r";

		RandomAccessFile raf = new RandomAccessFile ( file, openMode );

		if ( usePlainRandomAccess ) {
			return new RAFRandomAccessSource ( raf );
		}

		try {
			if ( raf.length () <= 0 ) // files with zero length can't be mapped and will throw an IllegalArgumentException.  Just open using a simple RAF source.
				return new RAFRandomAccessSource ( raf );

			try {
				return createBestSource ( raf.getChannel () );
			} catch ( MapFailedException e ) {
				return new RAFRandomAccessSource ( raf );
			}
		} catch ( IOException e ) { // If RAFRandomAccessSource constructor or createBestSource throws, then we must close the RAF we created.
			try {
				raf.close ();
			} catch ( IOException ignore ) {
			}
			throw e;
		} catch ( RuntimeException e ) { // if we get a runtime exception during opening, we must close the RAF we created
			try {
				raf.close ();
			} catch ( IOException ignore ) {
			}
			throw e;
		}
	}

	public RandomAccessSource createBestSource ( FileChannel channel ) throws IOException {
		if ( channel.size () <= PagedChannelRandomAccessSource.DEFAULT_TOTAL_BUFSIZE ) { // if less than the fully mapped usage of PagedFileChannelRandomAccessSource, just map the whole thing and be done with it
			return new GetBufferedRandomAccessSource ( new FileChannelRandomAccessSource ( channel ) );
		} else {
			return new GetBufferedRandomAccessSource ( new PagedChannelRandomAccessSource ( channel ) );
		}
	}

	private RandomAccessSource createByReadingToMemory ( String filename ) throws IOException {
		InputStream is = StreamUtil.getResourceStream ( filename );
		if ( is == null )
			throw new IOException ( MessageLocalization.getComposedMessage ( "1.not.found.as.file.or.resource", filename ) );
		return createByReadingToMemory ( is );
	}

	private RandomAccessSource createByReadingToMemory ( InputStream is ) throws IOException {
		try {
			return new ArrayRandomAccessSource ( StreamUtil.inputStreamToArray ( is ) );
		} finally {
			try {
				is.close ();
			} catch ( IOException ignored ) {
			}
		}
	}

}
