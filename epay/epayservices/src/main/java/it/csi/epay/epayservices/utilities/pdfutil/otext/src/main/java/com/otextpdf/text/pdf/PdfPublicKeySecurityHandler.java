/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import org.bouncycastle.asn1.DEROutputStream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;


public class PdfPublicKeySecurityHandler {

	static final int SEED_LENGTH = 20;

	private final ArrayList<PdfPublicKeyRecipient> recipients;

	private byte[] seed = new byte[SEED_LENGTH];

	public PdfPublicKeySecurityHandler () {
		KeyGenerator key;
		try {
			key = KeyGenerator.getInstance ( "AES" );
			key.init ( 192, new SecureRandom () );
			SecretKey sk = key.generateKey ();
			System.arraycopy ( sk.getEncoded (), 0, seed, 0, SEED_LENGTH );
		} catch ( NoSuchAlgorithmException e ) {
			seed = SecureRandom.getSeed ( SEED_LENGTH );
		}

		recipients = new ArrayList<> ();
	}

	public void addRecipient ( PdfPublicKeyRecipient recipient ) {
		recipients.add ( recipient );
	}

	protected byte[] getSeed () {
		return seed.clone ();
	}

	public int getRecipientsSize () {
		return recipients.size ();
	}

	public byte[] getEncodedRecipient ( int index ) throws IOException, GeneralSecurityException {
		PdfPublicKeyRecipient recipient = recipients.get ( index );
		byte[] cms = recipient.getCms ();

		if ( cms != null )
			return cms;

		int permission = recipient.getPermission ();

		permission |= 0xfffff0c0;
		permission &= 0xfffffffc;
		permission += 1;

		byte[] pkcs7input = new byte[24];

		byte one = (byte) permission;
		byte two = (byte) ( permission >> 8 );
		byte three = (byte) ( permission >> 16 );
		byte four = (byte) ( permission >> 24 );

		System.arraycopy ( seed, 0, pkcs7input, 0, 20 ); // put this seed in the pkcs7 input

		pkcs7input[20] = four;
		pkcs7input[21] = three;
		pkcs7input[22] = two;
		pkcs7input[23] = one;

		//SC        ASN1Primitive obj = createDERForRecipient(pkcs7input, (X509Certificate)certificate);

		ByteArrayOutputStream baos = new ByteArrayOutputStream ();

		DEROutputStream k = new DEROutputStream ( baos );

		//SC        k.writeObject(obj);

		cms = baos.toByteArray ();

		recipient.setCms ( cms );

		return cms;
	}

	public PdfArray getEncodedRecipients () {
		PdfArray EncodedRecipients = new PdfArray ();
		byte[] cms;
		for ( int i = 0; i < recipients.size (); i++ )
			try {
				cms = getEncodedRecipient ( i );
				EncodedRecipients.add ( new PdfLiteral ( PdfContentByte.escapeString ( cms ) ) );
			} catch ( GeneralSecurityException | IOException e ) {
				EncodedRecipients = null;
			}

		return EncodedRecipients;
	}

	//SC    private ASN1Primitive createDERForRecipient(byte[] in, X509Certificate cert)
	//        throws IOException,
	//               GeneralSecurityException
	//    {
	//
	//        String s = "1.2.840.113549.3.2";
	//
	//        AlgorithmParameterGenerator algorithmparametergenerator = AlgorithmParameterGenerator.getInstance(s);
	//        AlgorithmParameters algorithmparameters = algorithmparametergenerator.generateParameters();
	//        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(algorithmparameters.getEncoded("ASN.1"));
	//        ASN1InputStream asn1inputstream = new ASN1InputStream(bytearrayinputstream);
	//        ASN1Primitive derobject = asn1inputstream.readObject();
	//        KeyGenerator keygenerator = KeyGenerator.getInstance(s);
	//        keygenerator.init(128);
	//        SecretKey secretkey = keygenerator.generateKey();
	//        Cipher cipher = Cipher.getInstance(s);
	//        cipher.init(1, secretkey, algorithmparameters);
	//        byte[] abyte1 = cipher.doFinal(in);
	//        DEROctetString deroctetstring = new DEROctetString(abyte1);
	//        KeyTransRecipientInfo keytransrecipientinfo = computeRecipientInfo(cert, secretkey.getEncoded());
	//        DERSet derset = new DERSet(new RecipientInfo(keytransrecipientinfo));
	//        AlgorithmIdentifier algorithmidentifier = new AlgorithmIdentifier(new ASN1ObjectIdentifier(s), derobject);
	//        EncryptedContentInfo encryptedcontentinfo =
	//            new EncryptedContentInfo(PKCSObjectIdentifiers.data, algorithmidentifier, deroctetstring);
	//        ASN1Set set = null;
	//        EnvelopedData env = new EnvelopedData(null, derset, encryptedcontentinfo, set);
	//        ContentInfo contentinfo =
	//            new ContentInfo(PKCSObjectIdentifiers.envelopedData, env);
	//        return contentinfo.toASN1Primitive();
	//    }
	//    private KeyTransRecipientInfo computeRecipientInfo(X509Certificate x509certificate, byte[] abyte0)
	//        throws GeneralSecurityException, IOException
	//    {
	//        ASN1InputStream asn1inputstream =
	//            new ASN1InputStream(new ByteArrayInputStream(x509certificate.getTBSCertificate()));
	//        TBSCertificateStructure tbscertificatestructure =
	//            TBSCertificateStructure.getInstance(asn1inputstream.readObject());
	//        AlgorithmIdentifier algorithmidentifier = tbscertificatestructure.getSubjectPublicKeyInfo().getAlgorithm();
	//        IssuerAndSerialNumber issuerandserialnumber =
	//            new IssuerAndSerialNumber(
	//                tbscertificatestructure.getIssuer(),
	//                tbscertificatestructure.getSerialNumber().getValue());
	//        Cipher cipher = Cipher.getInstance(algorithmidentifier.getAlgorithm().getId());
	//        try{
	//        cipher.init(1, x509certificate);
	//        }catch(InvalidKeyException e){
	//        	cipher.init(1,x509certificate.getPublicKey());
	//        }
	//        DEROctetString deroctetstring = new DEROctetString(cipher.doFinal(abyte0));
	//        RecipientIdentifier recipId = new RecipientIdentifier(issuerandserialnumber);
	//        return new KeyTransRecipientInfo( recipId, algorithmidentifier, deroctetstring);
	//    }
}
