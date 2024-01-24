/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.mdppagopaapi.business.MdpReceiptService;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;
import it.csi.mdp.mdppagopaapi.integration.repository.MdpReceiptRepository;


@Service
public class MdpReceiptServiceImpl implements MdpReceiptService {

    @Autowired
    MdpReceiptRepository mdpReceiptRepository;

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public MdpReceipt findFirstByReceiptIdOrderByIdDesc ( String receiptId ) {
        try {
            return mdpReceiptRepository.findTopByReceiptIdOrderByIdDesc ( receiptId );
        } catch ( NoResultException | NoSuchElementException e ) {
            return null;
        }
    }

    @Override
    @Transactional ( propagation = Propagation.REQUIRED )
    public MdpReceipt inserisciRecord ( MdpReceipt mdpReceipt ) {
        return mdpReceiptRepository.save ( mdpReceipt );
    }

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public MdpReceipt findTopByCreditorReferenceId ( String reqIuv ) {
        try {
            return mdpReceiptRepository.findTopBycreditorReferenceidOrderByIdDesc ( reqIuv );
        } catch ( NoResultException | NoSuchElementException e ) {
            return null;
        }
    }

    @Override
    @Transactional ( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
    public MdpReceipt findTopByCreditorReferenceIdAndIdPA ( String receiptId, String idPa ) {
        try {
            return mdpReceiptRepository.findTopByReceiptIdAndIdPaOrderByIdDesc ( receiptId, idPa );
        } catch ( NoResultException | NoSuchElementException e ) {
            return null;
        }
    }

}
