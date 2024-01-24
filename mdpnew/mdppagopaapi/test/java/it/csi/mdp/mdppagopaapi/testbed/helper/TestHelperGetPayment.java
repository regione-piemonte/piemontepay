/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.testbed.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import it.csi.mdp.mdppagopaapi.pa.pafornode.CtQrCode;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StTransferType;


/**
 *
 */

public class TestHelperGetPayment {

    public static PaGetPaymentReq creaRequestOK () {
        PaGetPaymentReq req = new PaGetPaymentReq ();
        req.setIdPA ( "00000000170" );
        req.setIdBrokerPA ( "01995120019" );
        req.setIdStation ( "77777777777_01" );
        CtQrCode qc = new CtQrCode ();
        qc.setFiscalCode ( "77777777777" );
        qc.setNoticeNumber ( "322192140496592225" );
        req.setQrCode ( qc );
        GregorianCalendar gc = new GregorianCalendar ();
        gc.setTime ( new Date () );
        try {
            req.setDueDate ( DatatypeFactory.newInstance ()
                .newXMLGregorianCalendar ( gc ) );
        } catch ( DatatypeConfigurationException e ) {

        }
        req.setAmount ( new BigDecimal ( 15.00 ) );
        req.setPaymentNote ( "paymentNote" );
        //req.setTransferType ( "transferType" );
        req.setTransferType ( StTransferType.POSTAL );
        return req;
    }

    public static PaGetPaymentReq creaRequestKO () {
        PaGetPaymentReq req = new PaGetPaymentReq ();
        req.setIdPA ( "80087670016" );
        req.setIdBrokerPA ( "80087670016" );
        req.setIdStation ( "80087670016_01" );
        CtQrCode qc = new CtQrCode ();
        qc.setFiscalCode ( "80087670016" );
        qc.setNoticeNumber ( "8200221210055701" );
        req.setQrCode ( qc );
        GregorianCalendar gc = new GregorianCalendar ();
        gc.setTime ( new Date () );
        try {
            req.setDueDate ( DatatypeFactory.newInstance ()
                .newXMLGregorianCalendar ( gc ) );
        } catch ( DatatypeConfigurationException e ) {

        }
        return req;
    }
}
