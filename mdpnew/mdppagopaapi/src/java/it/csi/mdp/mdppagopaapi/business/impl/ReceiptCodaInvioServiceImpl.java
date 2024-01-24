/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.ReceiptCodaInvioService;
import it.csi.mdp.mdppagopaapi.integration.domain.ReceiptCodaInvio;
import it.csi.mdp.mdppagopaapi.integration.repository.ReceiptCodaInvioRepository;

@Service
public class ReceiptCodaInvioServiceImpl implements ReceiptCodaInvioService {

    @Autowired
    private ReceiptCodaInvioRepository receiptCodaInvioRepository;

    @Override
    @Transactional ( propagation = Propagation.REQUIRED )
    public ReceiptCodaInvio insert ( ReceiptCodaInvio receiptCodaInvio ) {
        return receiptCodaInvioRepository.save ( receiptCodaInvio );
    }



}
