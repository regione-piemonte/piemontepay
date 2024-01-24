/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.utilities;

import org.apache.commons.codec.binary.Base64;

public class CifraturaIdPagamento {

    final static String chiave = "IT33K4006216660783440758225";

    public static String codifica ( Long id ) {
        String encrypted = AES.encrypt ( "" + id, chiave );
        byte[] encoded = Base64.encodeBase64URLSafe (encrypted.getBytes ());
        return new String(encoded);
    }

    public static String codifica ( String id ) {
        String encrypted = AES.encrypt ( "" + id, chiave );
        byte[] encoded = Base64.encodeBase64URLSafe (encrypted.getBytes ());
        return new String(encoded);
    }

    public static Long decodifica ( String idCifrato ) throws Exception {
        Long id = 0L;

        try {
            byte [] decodedB = Base64.decodeBase64( idCifrato.getBytes () );
            String decrypted = AES.decrypt ( new String ( decodedB ), chiave );
        
            id = Long.parseLong ( decrypted );
        } catch(Exception e) {
            e.printStackTrace ();
            throw new Exception ("DECODE DELL'ID AVVISO FALLITO");
        }
        return id;
    }
    
    public static void main(String[] a) throws Exception {
        String encodedOK = codifica(999999767868878688L);
        System.out.println ( encodedOK );
        System.out.println ( decodifica(encodedOK));
    }
}
