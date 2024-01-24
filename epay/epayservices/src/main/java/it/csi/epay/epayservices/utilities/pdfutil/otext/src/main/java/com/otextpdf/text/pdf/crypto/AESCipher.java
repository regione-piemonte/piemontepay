/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;


public class AESCipher {

	private final PaddedBufferedBlockCipher bp;

	public AESCipher ( boolean forEncryption, byte[] key, byte[] iv ) {
		BlockCipher aes = new AESFastEngine ();
		BlockCipher cbc = new CBCBlockCipher ( aes );
		bp = new PaddedBufferedBlockCipher ( cbc );
		KeyParameter kp = new KeyParameter ( key );
		ParametersWithIV piv = new ParametersWithIV ( kp, iv );
		bp.init ( forEncryption, piv );
	}

	public byte[] update ( byte[] inp, int inpOff, int inpLen ) {
		int neededLen = bp.getUpdateOutputSize ( inpLen );
		byte[] outp = null;
		if ( neededLen > 0 )
			outp = new byte[neededLen];
		bp.processBytes ( inp, inpOff, inpLen, outp, 0 );
		return outp;
	}

	public byte[] doFinal () {
		int neededLen = bp.getOutputSize ( 0 );
		byte[] outp = new byte[neededLen];
		int n;
		try {
			n = bp.doFinal ( outp, 0 );
		} catch ( Exception ex ) {
			return outp;
		}
		if ( n != outp.length ) {
			byte[] outp2 = new byte[n];
			System.arraycopy ( outp, 0, outp2, 0, n );
			return outp2;
		} else
			return outp;
	}

}
