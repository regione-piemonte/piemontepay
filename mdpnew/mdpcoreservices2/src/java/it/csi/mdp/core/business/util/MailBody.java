/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;

import it.csi.mdp.core.util.Constants;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MailBody

{
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
	private static StringWriter sw = new StringWriter();
	private static StringBuffer sb = sw.getBuffer();
	private static PrintWriter pw = new PrintWriter(sw);
	private static String output = "";
	
	
	public static String processBodyMail(PlaceHolder ph, Properties envp, long codStatoTransazione) throws Exception
	{
		log.debug("[MailBody::processBodyMail] BEGIN");
		String _PLACEHOLDER_Esito = "[$ESITO$]", _PLACEHOLDER_MerchantCode = "[$MERCHANTCODE$]", 
			_PLACEHOLDER_TimeStamp = "[$TIMESTAMP$]",_PLACEHOLDER_Ccy = "[$CCY$]", 
			_PLACEHOLDER_Amount = "[$AMOUNT$]",_PLACEHOLDER_IDTransazione = "[$IDTRANSAZIONE$]", 
			_PLACEHOLDER_NumOper = "[$NUMOPER$]",_PLACEHOLDER_NumAutor = "[$NUMAUTOR$]", 
			_PLACEHOLDER_PayMode = "[$PAYMENTMODE$]",_PLACEHOLDER_Provider = "[$PROVIDER$]",
			_PLACEHOLDER_BuyerName = "[$BUYERNAME$]", _PLACEHOLDER_BuyerMail = "[$BUYERMAIL$]", 
			_PLACEHOLDER_NumVerde = "[$NUMVERDE$]", _PLACEHOLDER_IUV = "[$IUV$]" ;
			
		String bodyText = "";
		String passPhrase = "$MDP2004$"; // PASSPHRASE per check Hash MD5. NON MODIFICARE !!!
		String hashMail = "";
		String esitoTrascod = "";
		log.debug("[MailBody::processBodyMail] esito:"+ph.getEsito());
		log.debug("[MailBody::processBodyMail] CODICE STATTRT:"+codStatoTransazione);
		
		

		try
		{
			FileInputStream stream = null;
			if (codStatoTransazione == 4) {
				if (ph.getEsito().equals("CAPTURED")) {
					stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_CAPTURED"));
					hashMail = envp.getProperty("ResponseMailBodyHash_CAPTURED");
					esitoTrascod = "AUTORIZZATA ed ACCREDITATA";
				} else {
					stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_APPROVED"));
					hashMail = envp.getProperty("ResponseMailBodyHash_APPROVED");
					esitoTrascod = "AUTORIZZATA";
				}
				
			} else if (codStatoTransazione == 5) {
				
				stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_NOTAPPROVED"));
				hashMail = envp.getProperty("ResponseMailBodyHash_NOTAPPROVED");
				esitoTrascod = "NON AUTORIZZATA";
				
				if (ph.getEsito().equals("DENIED BY RISK"))
				{
					stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_DENIEDBYRISK"));
					hashMail = envp.getProperty("ResponseMailBodyHash_DENIEDBYRISK");
					esitoTrascod = "NEGATA PER SUPERAMENTO LIMITI IMPOSTI DALLA BANCA";
				}
				else if (ph.getEsito().equals("HOST TIMEOUT"))
				{
					stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_HOSTTIMEOUT"));
					hashMail = envp.getProperty("ResponseMailBodyHash_HOSTTIMEOUT");
					esitoTrascod = "NON AUTORIZZATA PER MANCATO COLLEGAMENTO CON HOST";
				}
				else if (ph.getEsito().equals("INCASSOKO"))
				{
					stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_INCASSOKO"));
					hashMail = envp.getProperty("ResponseMailBodyHash_INCASSOKO");
					esitoTrascod = "MANCATO INCASSO DI UNO O PIU' BOLLETTINI POSTALI";
				}
				else if (ph.getEsito().equals("MDPRECEIPTKO"))
				{
					stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_MDPRECEIPTKO"));
					hashMail = envp.getProperty("ResponseMailBodyHash_MDPRECEIPTKO");
					esitoTrascod = "TRANSAZIONE NON ELABORATA DAL GATEWAY PER PROBLEMI TECNICI - NOTIFICATION MESSAGE FALLITO";
				}
				else if (ph.getEsito().equals("DA VERIFICARE"))
				{
					stream = new FileInputStream(envp.getProperty("ErrorMerchantMailBodyFileName"));
					hashMail = envp.getProperty("ErrorMerchantMailBodyHash");
					esitoTrascod = "SI E' VERIFICATO UN ERRORE DURANTE L'ELABORAZIONE. LO STATO DELLA TRANSAZIONE E' DA VERIFICARE";

				}
			}
			

			if (codStatoTransazione == 6) {
				stream = new FileInputStream(envp.getProperty("ResponseMailBodyFileName_NOTAPPROVED"));
				hashMail = envp.getProperty("ResponseMailBodyHash_NOTAPPROVED");
				esitoTrascod = "ANNULLATA";
			}
			
			byte [] b = new byte [stream.available()]; 
			stream.read(b);
			String body = new String(b);
			log.debug("[MailBody::processBodyMail] body:"+body);
			String StringToHash = passPhrase + body;
			log.debug("[MailBody::processBodyMail] passPhrase:"+passPhrase);

			String otherValue = mD5Encode(StringToHash);
			log.debug("[MailBody::processBodyMail] otherValue:"+otherValue);	
			log.debug("[MailBody::processBodyMail] hashMail:"+hashMail);
			// verifica che il corpo della mail caricata da file non sia stato alterato.
			if (otherValue.equals(hashMail))
			{
				// OK verifica Hash
				// sostituzione dei PLACEHOLDER con i valori specifici dell'utente che ha eseguito la transazione
				StringToHash = StringToHash.replace(passPhrase,"");
				StringToHash = StringToHash.replace(_PLACEHOLDER_MerchantCode,StringUtils.trimToEmpty(ph.getMerchantID()));
				StringToHash = StringToHash.replace(_PLACEHOLDER_Amount,ph.getAmount());
				StringToHash = StringToHash.replace(_PLACEHOLDER_BuyerMail, (ph.getBuyerEmail()!=null?ph.getBuyerEmail():""));
				StringToHash = StringToHash.replace(_PLACEHOLDER_BuyerName, (ph.getBuyerName()!=null?ph.getBuyerName():""));
				StringToHash = StringToHash.replace(_PLACEHOLDER_Ccy, ph.getCcy());
				StringToHash = StringToHash.replace(_PLACEHOLDER_Esito,esitoTrascod);
				StringToHash = StringToHash.replace(_PLACEHOLDER_TimeStamp, ph.getDataOp());
				StringToHash = StringToHash.replace(_PLACEHOLDER_IDTransazione, ph.getTransId());
				//					StringToHash = StringToHash.Replace(_PLACEHOLDER_MerchantCode, "");
				StringToHash = StringToHash.replace(_PLACEHOLDER_NumAutor, (ph.getNumAutor()==null?"":ph.getNumAutor()));
				StringToHash = StringToHash.replace(_PLACEHOLDER_NumOper, (ph.getNumOper()==null?"":ph.getNumOper()));
				StringToHash = StringToHash.replace(_PLACEHOLDER_Provider,(ph.getProvider()==null?"":ph.getProvider()));
				StringToHash = StringToHash.replace(_PLACEHOLDER_PayMode,(ph.getPayMode()==null?"":ph.getPayMode()));
				StringToHash = StringToHash.replace(_PLACEHOLDER_NumVerde, (ph.getNumVerde()==null?"":ph.getNumVerde()));
				StringToHash = StringToHash.replace(_PLACEHOLDER_IUV, (ph.getIuv()==null?"": "IUV: " + ph.getIuv()));
				bodyText = StringToHash;
			}
			else
			{
				throw new Exception("Errore in verifica hascode del corpo email") ;
			}
		} 
		catch(Exception ex)
		{
			ex.printStackTrace(pw);
			sb = sw.getBuffer();
			output = sb.toString();
			log.error("[MailBody::processBodyMail] error:"+output);	
			throw new Exception(ex.getMessage());
		}
		finally
		{
			log.debug("[MailBody::processBodyMail] END");	
		}

		return bodyText;
	}	
	private static String mD5Encode(String sInputString) throws NoSuchAlgorithmException ,UnsupportedEncodingException
	{
		//log.debug("["+this.getClass().getName()+"::encodeUrl] start");
		/*
		 System.Security.Cryptography.MD5 hasher = System.Security.Cryptography.MD5.Create(); //hasher = new System.Security.Cryptography.MD5
            
			char[] arrch = _strMessage.ToCharArray();
			byte[] arrStr;
			arrStr = System.Text.Encoding.Unicode.GetBytes(arrch);
			byte[] arrMD5 = hasher.ComputeHash(arrStr);
			return System.Convert.ToBase64String(arrMD5);
		 */
		
		
		
		
		log.debug("[MailBody::mD5Encode] BEGIN");
		log.debug("[MailBody::mD5Encode] sInputString:"+sInputString);
		MessageDigest hasher = MessageDigest.getInstance("MD5");
		//BASE64Encoder encoder = new BASE64Encoder();
		Base64 encoder = new Base64();
		String sStringToDigest = sInputString ;
		hasher.update(sStringToDigest.getBytes("UTF-16LE"));
		byte[] bMac = hasher.digest();
		String sMacEncoded = encoder.encodeAsString(bMac);
		log.debug("[MailBody::mD5Encode] sMacEncoded:"+sMacEncoded);
		sMacEncoded = sMacEncoded.substring(0,24);
		log.debug("[MailBody::mD5Encode] end");
		return sMacEncoded;
	}

}
