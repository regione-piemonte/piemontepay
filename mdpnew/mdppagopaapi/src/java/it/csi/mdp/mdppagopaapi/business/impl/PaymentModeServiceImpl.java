/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.PaymentModeService;
import it.csi.mdp.mdppagopaapi.integration.domain.Paymentmode;
import it.csi.mdp.mdppagopaapi.integration.repository.PaymentModeRepository;

@Service
public class PaymentModeServiceImpl implements PaymentModeService {

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Override
    public Paymentmode findByIdPaymentmode ( String idPaymentmode ) {
        return paymentModeRepository.findOne ( idPaymentmode );
    }

}
