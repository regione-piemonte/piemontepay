/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;


public class FileChannelRandomAccessSource implements RandomAccessSource {

	private final FileChannel channel;

	private final MappedChannelRandomAccessSource source;

	public FileChannelRandomAccessSource ( FileChannel channel ) throws IOException {
		this.channel = channel;
		source = new MappedChannelRandomAccessSource ( channel, 0, channel.size () );
		source.open ();
	}

	public void close () throws IOException {
		source.close ();
		channel.close ();
	}

	public int get ( long position ) throws IOException {
		return source.get ( position );
	}

	public int get ( long position, byte[] bytes, int off, int len ) throws IOException {
		return source.get ( position, bytes, off, len );
	}

	public long length () {
		return source.length ();
	}

}
