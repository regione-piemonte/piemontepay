/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.intnodospc2.util;

import it.csi.mdp.adapters.business.PersistenzaDatiNodoUtility;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.paymentmanager.Payment;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {

	public static long getStatoTransazioneMdp(String statoBank) {
		long statoMdp = 5;
		if (statoBank.equals("00")) {
			statoMdp = 4;
		} else {
			statoMdp = 5;
		}

		return statoMdp;
	}

	public static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	public static String randomString(int length) {
		
		Random rand = new Random();
		StringBuffer tempStr = new StringBuffer();
		tempStr.append("");
		for (int i = 0; i < length; i++) {
			int c = rand.nextInt(122 - 48) + 48;
			if ((c >= 58 && c <= 64) || (c >= 91 && c <= 96)) {
				i--;
				continue;
			}
			tempStr.append((char) c);

		}
		return tempStr.toString();
	}
	
	public static Map<String, String> convertiUrlInMapParametri (String urlStr) throws MalformedURLException, UnsupportedEncodingException {
		Map<String, String> mappaParametri = new HashMap<String, String>();
		URL url = new URL(urlStr);
	    String query = url.getQuery();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        mappaParametri.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return mappaParametri;
	}
	
	/**
	 * Registra l'evento di giornale sul DB tramite invocazione all'EJB della core.
	 * @param applicationId
	 * @param transactionId
	 * @param gatewayId
	 * @param esito
	 * @param tipoEvento
	 * @param iuv
	 * @param tipoVersamento
	 * @param identificativoStazioneIntermediarioPA
	 * @param identificativoDominio
	 * @param idPsp
	 * @param idIntermediarioPsp
	 * @param parametriSpecificiInterfaccia
	 */
	public static void registraEventoGiornale(String applicationId, String transactionId, String gatewayId, String esito, String tipoEvento, 
			String iuv, String tipoVersamento, String identificativoStazioneIntermediarioPA, String identificativoDominio, 
			String idPsp, String idIntermediarioPsp, String parametriSpecificiInterfaccia) {

		try {
			PersistenzaDatiNodoUtility.registraEventoGiornale(applicationId, transactionId, gatewayId, esito, tipoEvento, iuv, tipoVersamento, identificativoStazioneIntermediarioPA, identificativoDominio, idPsp, idIntermediarioPsp, parametriSpecificiInterfaccia, "mdpintnodospc2", "n/a");
		} catch (Exception e) {
			LoggerUtil.error("IMPOSSIBILE REGISTRARE EVENTO GIORNALE", e);
		}
	}
	
	/**
	 * Trova la causa radice di un'eccezione rilanciata
	 * @param e
	 * @return
	 */
	public static Throwable trovaCausa(Throwable e) {
		if (e == null || e.getCause() == null) return e;
		return trovaCausa(e.getCause());
	}
	
	/**
	 * Concatena un messaggio con i messaggi di tutte le eccezioni della catena in modo da dare più dettagli possibile
	 * @param e
	 * @return
	 */
	public static StringBuilder concatenaMessaggiCausa (Throwable e) {
		StringBuilder messaggio = new StringBuilder();
		
		if (e == null) return messaggio;
		
		messaggio.append(e.getMessage());
		Throwable cause = e.getCause();
		while (cause != null) {
			messaggio.append("; ").append(cause.getMessage());
			cause = cause.getCause();
		}
		return messaggio;
	}
	
	public static void inviaEmailErrore(String iuv, String codiceContestoPagamento, Payment p, Transazione t, Throwable e, String metodo) {
		LoggerUtil.begin();
		StringBuilder testoEmail = new StringBuilder("SI E' VERIFICATO UN ERRORE NON GESTITO IN ");
		try {
			testoEmail.append(metodo);
			testoEmail.append("\n\nIUV= ").append(iuv);
			testoEmail.append("\n\nCodice contesto pagamento= ").append(codiceContestoPagamento);
			testoEmail.append("\n\n").append("SEGUONO DETTAGLI ERRORE\n\n");
			testoEmail.append(concatenaMessaggiCausa(e)).append("\n\n");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			Throwable trueCause = trovaCausa(e);
			trueCause.printStackTrace(pw);
			testoEmail.append(sw.getBuffer());
			p.errorMail(t == null ? "N/A" : t.getApplicationId() , t, testoEmail.toString());
		} catch (Exception eInvio) {
			LoggerUtil.error("IMPOSSIBILE INVIARE EMAIL PER ERRORE", eInvio);
		}
		LoggerUtil.end();
	}

}
