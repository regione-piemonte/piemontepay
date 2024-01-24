/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import it.csi.mdp.mdppagopaapi.business.MdpSingoloTransferService;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpSingoloTransfer;
import it.csi.mdp.mdppagopaapi.integration.repository.DatiSingoloTransferRepository;
import it.csi.mdp.mdppagopaapi.integration.repository.MdpSingoloTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;


@Service
public class MdpSingoloTransferServiceImpl implements MdpSingoloTransferService {

    @Autowired
    private MdpSingoloTransferRepository mdpSingoloTransferRepository;

    @Autowired
    private DatiSingoloTransferRepository datiSingoloTransferRepository;

    @Override
    @Transactional ( propagation = Propagation.REQUIRED )
    public void inserisciSingoloTransfer ( MdpSingoloTransfer datiSingoloTransfer ) {
		mdpSingoloTransferRepository.save ( datiSingoloTransfer );
	}

    @Override
    public List<MdpSingoloTransfer> findByMdpReceipt ( MdpReceipt mdpReceipt ) {
        try {
            return datiSingoloTransferRepository.findByMdpReceipt ( mdpReceipt );
        } catch ( NoResultException e ) {
            return null;
        }
    }

    @Override
    public MdpSingoloTransfer insert ( MdpSingoloTransfer mdpSingoloTransfer ) {
        return mdpSingoloTransferRepository.save ( mdpSingoloTransfer );
    }
}
