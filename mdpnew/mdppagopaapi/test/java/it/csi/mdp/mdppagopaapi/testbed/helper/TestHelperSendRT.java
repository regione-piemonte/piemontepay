/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.testbed.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import it.csi.mdp.mdppagopaapi.pa.pafornode.CtEntityUniqueIdentifier;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtMapEntry;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtMetadata;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtReceipt;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtSubject;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtTransferListPA;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtTransferPA;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StEntityUniqueIdentifierType;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StOutcome;


/**
 *
 */

public class TestHelperSendRT {

    public static PaSendRTReq crea () {
        // setto parametri che mi piacciono
        return new PaSendRTReq ();
    }

    public static PaSendRTReq creaConParametri () {
        // setto parametri
        PaSendRTReq request = new PaSendRTReq ();
        request.setIdPA ( "00000000170" );
        request.setIdBrokerPA ( "01995120019" );
        request.setIdStation ( "77777777777_01" );
        //request.setIdPSP ( "00000000170" );
        CtReceipt receipt = new CtReceipt ();
        receipt.setIdPSP ( "00000000170" );
        receipt.setReceiptId ( "c110729d258c4ab1b765fe902aae41d6" );
        receipt.setNoticeNumber ( "22192140496592225" );
        receipt.setFiscalCode ( "77777777777" );
        receipt.setOutcome ( StOutcome.OK );
        receipt.setDescription ( "provadescr" );
        receipt.setCreditorReferenceId ( "22192140496592225" );
        BigDecimal d = new BigDecimal ( 10.00 );
        receipt.setPaymentAmount ( d );
        receipt.setCompanyName ( "company" );
        receipt.setOfficeName ( "officeName" );
        CtSubject debtor = new CtSubject ();
        debtor.setFullName ( "Marco" );
        receipt.setDebtor ( debtor );
        CtTransferListPA transferList = new CtTransferListPA ();
        List<CtTransferPA> theTransfer = transferList.getTransfer ();
        CtTransferPA t = new CtTransferPA ();
        t.setIdTransfer ( 999 );
        t.setFiscalCodePA ( "77777777777" );
        t.setIBAN ( "00000000170" );
        t.setRemittanceInformation ( "remittanceInformation" );
        t.setTransferAmount ( d );
        t.setTransferCategory ( "1045" );
        theTransfer.add ( t );
        receipt.setTransferList ( transferList );
        receipt.setPspPartitaIVA ( "aaa" );
        receipt.setChannelDescription ( "channelDescription" );
        receipt.setCompanyName ( "companyName" );
        receipt.setIdChannel ( "idChannel" );
        CtSubject payer = new CtSubject ();
        payer.setFullName ( "fullName" );
        CtEntityUniqueIdentifier u = new CtEntityUniqueIdentifier ();
        u.setEntityUniqueIdentifierType ( StEntityUniqueIdentifierType.F );
        u.setEntityUniqueIdentifierValue ( "entityUniqueIdentifierValue" );
        payer.setUniqueIdentifier ( u );
        receipt.setPayer ( payer );
        receipt.setPaymentMethod ( "card" );
        receipt.setFee ( d );
        GregorianCalendar gc = new GregorianCalendar ();
        gc.setTime ( new Date () );
        try {
            receipt.setPaymentDateTime ( DatatypeFactory.newInstance ()
                .newXMLGregorianCalendar ( gc ) );
            receipt.setApplicationDate ( DatatypeFactory.newInstance ()
                .newXMLGregorianCalendar ( gc ) );
            receipt.setTransferDate ( DatatypeFactory.newInstance ()
                .newXMLGregorianCalendar ( gc ) );
        } catch ( DatatypeConfigurationException e ) {

        }
        //        Map<String, String> metadata = new HashMap<> ();
        CtMetadata metadata = new CtMetadata ();
        List<CtMapEntry> theMapEntry = new ArrayList<CtMapEntry> ();
        CtMapEntry me = new CtMapEntry ();
        me.setKey ( "transaction_id" );
        me.setValue ( "TST000000000004021" );
        //        metadata.put ( "transaction_id", "TST000000000004021" );
        theMapEntry.add ( me );
        receipt.setMetadata ( metadata );
        request.setReceipt ( receipt );

        return request;
    }

}
