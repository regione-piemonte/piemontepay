/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.MdpGetpaymentService;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;
import it.csi.mdp.mdppagopaapi.integration.repository.MdpGetpaymentRepository;

@Service
public class MdpGetpaymentServiceImpl implements MdpGetpaymentService {

    @Autowired
    private MdpGetpaymentRepository mdpGetpaymentRepository;


    @Override
    public MdpGetpayment insert ( MdpGetpayment mdpGetpayment ) {
        return mdpGetpaymentRepository.save ( mdpGetpayment );
    }

}
