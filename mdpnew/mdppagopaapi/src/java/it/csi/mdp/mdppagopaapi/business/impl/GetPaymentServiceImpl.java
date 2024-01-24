/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.GetPaymentService;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;
import it.csi.mdp.mdppagopaapi.integration.repository.MdpGetpaymentRepository;

@Service
public class GetPaymentServiceImpl implements GetPaymentService {

    @Autowired
    MdpGetpaymentRepository mdpGetpaymentRepository;

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public MdpGetpayment findByCreditorReferenceidAndTransactionId ( String creditorId, String transactionId ) {
        return mdpGetpaymentRepository.findByCreditorReferenceidAndTransactionId ( creditorId, transactionId );
    }

    @Override
    @Transactional ( propagation = Propagation.REQUIRED )
    public MdpGetpayment inserisciRecord ( MdpGetpayment mdpGetpayment ) {
        return mdpGetpaymentRepository.save ( mdpGetpayment );
    }



}
