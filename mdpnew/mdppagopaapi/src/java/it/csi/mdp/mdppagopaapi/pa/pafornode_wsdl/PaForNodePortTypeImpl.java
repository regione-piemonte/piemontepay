/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode_wsdl;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.mdp.mdppagopaapi.business.PaGetPaymentBuisnessService;
import it.csi.mdp.mdppagopaapi.business.PaSendRTBusinessService;
import it.csi.mdp.mdppagopaapi.business.PaVerifyPaymentNoticeBuisnessService;
import it.csi.mdp.mdppagopaapi.dto.exception.PaymentException;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtFaultBean;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentRes;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTRes;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaVerifyPaymentNoticeReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaVerifyPaymentNoticeRes;
import it.csi.mdp.mdppagopaapi.pa.pafornode.StOutcome;
import it.csi.mdp.mdppagopaapi.util.FaultBeanEnum;


/**
 *
 */

@WebService ( serviceName = "Mdpapisrv", portName = "PPTPort", endpointInterface = "it.csi.mdp.mdppagopaapi.pa.pafornode_wsdl.PaForNodePortType" )
public class PaForNodePortTypeImpl implements PaForNodePortType {

    private static final Logger LOGGER = LoggerFactory.getLogger ( PaForNodePortTypeImpl.class );
    
    @Autowired
    private PaVerifyPaymentNoticeBuisnessService paVerifyPaymentNoticeBuisnessService;

    @Autowired
    private PaSendRTBusinessService paSendRTBusinessService;

    @Autowired
    private PaGetPaymentBuisnessService pPaGetPaymentBuisnessService;

    protected static final Logger logger = LoggerFactory.getLogger ( PaForNodePortTypeImpl.class );

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    @Override
    public PaSendRTRes paSendRT ( PaSendRTReq requestBody ) {
        try {
            return paSendRTBusinessService.paSendRT ( requestBody );
        } catch ( PaymentException e ) {
            LOGGER.error ( "Errore PaymentException ", e );
            PaSendRTRes response = new PaSendRTRes ();
            response.setFault ( e.getCtFault () );
            response.setOutcome ( StOutcome.KO );
            return response;
        } catch ( IllegalArgumentException e ) {
            LOGGER.error ( "Errore Exception", e );
            PaSendRTRes response = new PaSendRTRes ();
            response.setFault ( CtFaultBean.builder ()
                .withFaultCode ( FaultBeanEnum.PAA_SINTASSI_XSD.name () )
                .withDescription ( e.getMessage () )
                .withId ( requestBody.getIdPA () )
                .withFaultString ( FaultBeanEnum.PAA_SINTASSI_XSD.getDescription () )
                .build () );
            response.setOutcome ( StOutcome.KO );
            return response;
        } catch ( Exception e ) {
            LOGGER.error ( "Errore Exception", e );
            PaSendRTRes response = new PaSendRTRes ();
            response.setFault ( CtFaultBean.builder ()
                .withFaultCode ( FaultBeanEnum.PAA_SYSTEM_ERROR.name () )
                .withDescription ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
                .withId ( requestBody.getIdPA () )
                .withFaultString ( FaultBeanEnum.PAA_SYSTEM_ERROR.getDescription () )
                .build () );
            response.setOutcome ( StOutcome.KO );
            return response;
        }
    }

    @Override
    public PaVerifyPaymentNoticeRes paVerifyPaymentNotice ( PaVerifyPaymentNoticeReq requestBody ) {
        return paVerifyPaymentNoticeBuisnessService.paVerifyPaymentNotice ( requestBody );
    }

    @Override
    public PaGetPaymentRes paGetPayment ( PaGetPaymentReq requestBody ) {
        return pPaGetPaymentBuisnessService.paGetPayment ( requestBody );
    }

}
