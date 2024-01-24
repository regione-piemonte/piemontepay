/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;

import java.math.BigDecimal;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


public class MacUtil {

    /**
     * 
     * @param passphrase
     * @param identificativoUnivocoVersamento
     * @param applicationId
     * @param timestamp
     * @return
     */
    public static String generaMacVerificaDatiPagamento ( String passphrase, String identificativoUnivocoVersamento, String applicationId, String timestamp ) {
        String sToDigest = passphrase + "%%%%" + identificativoUnivocoVersamento + timestamp + "%%%%" + passphrase;
        byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
        String mac = Base64.encodeBase64String ( bMac );
        return mac.substring ( 0, 35 );
    }

    /**
     * 
     * @param passphrase
     * @param identificativoUnivocoVersamento
     * @param importoSingoloVersamento
     * @param timestamp
     * @return
     */
    public static String generaMacChiediDatiPagamento ( String passphrase, String identificativoUnivocoVersamento, BigDecimal importoSingoloVersamento,
        String timestamp ) {
        String sToDigest = passphrase + "%%%%" + identificativoUnivocoVersamento + importoSingoloVersamento.toString () + timestamp + "%%%%" + passphrase;
        byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
        String mac = Base64.encodeBase64String ( bMac );
        return  mac.substring ( 0, 35 );
    }

    /**
     * 
     * @param applicationId
     * @param passphrase
     * @param identificativoUnivocoVersamento
     * @param timestamp
     * @param idMsgRicevuta
     * @return
     */
    public static String generaMacRiceviEsito ( String applicationId, String passphrase, String identificativoUnivocoVersamento, String timestamp,
        String idMsgRicevuta ) {
        String sToDigest = passphrase + "%%%%" + applicationId + identificativoUnivocoVersamento + idMsgRicevuta + timestamp + "%%%%" + passphrase;
        byte [] bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
        String mac = Base64.encodeBase64String ( bMac );
        return mac.substring ( 0, 35 );
        
    }

}
