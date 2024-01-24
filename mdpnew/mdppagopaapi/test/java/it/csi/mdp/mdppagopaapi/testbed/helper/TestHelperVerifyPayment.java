/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.testbed.helper;

import it.csi.mdp.mdppagopaapi.pa.pafornode.CtQrCode;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaVerifyPaymentNoticeReq;

/**
 *
 */

public class TestHelperVerifyPayment {

    public static PaVerifyPaymentNoticeReq crea() {
        // setto parametri che mi piacciono
        return new PaVerifyPaymentNoticeReq ();
    }

    public static PaVerifyPaymentNoticeReq creaConParametri () {
        // setto parametri che mi piacciono
        PaVerifyPaymentNoticeReq request = new PaVerifyPaymentNoticeReq ();
        request.setIdPA ( "00000000170" );
        request.setIdBrokerPA ( "01995120019" );
        request.setIdStation ( "77777777777_01" );
        CtQrCode qc = new CtQrCode ();
        qc.setFiscalCode ( "77777777777" );
        qc.setNoticeNumber ( "322192140496592225" );
        request.setQrCode ( qc );
        return request;
    }
}
