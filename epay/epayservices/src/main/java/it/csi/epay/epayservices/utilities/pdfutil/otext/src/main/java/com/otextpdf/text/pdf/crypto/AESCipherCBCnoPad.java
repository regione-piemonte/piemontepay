/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;


public class AESCipherCBCnoPad {

	private final BlockCipher cbc;

	public AESCipherCBCnoPad ( boolean forEncryption, byte[] key ) {
		BlockCipher aes = new AESFastEngine ();
		cbc = new CBCBlockCipher ( aes );
		KeyParameter kp = new KeyParameter ( key );
		cbc.init ( forEncryption, kp );
	}

	public byte[] processBlock ( byte[] inp, int inpOff, int inpLen ) {
		if ( ( inpLen % cbc.getBlockSize () ) != 0 )
			throw new IllegalArgumentException ( "Not multiple of block: " + inpLen );
		byte[] outp = new byte[inpLen];
		int baseOffset = 0;
		while ( inpLen > 0 ) {
			cbc.processBlock ( inp, inpOff, outp, baseOffset );
			inpLen -= cbc.getBlockSize ();
			baseOffset += cbc.getBlockSize ();
			inpOff += cbc.getBlockSize ();
		}
		return outp;
	}
}
