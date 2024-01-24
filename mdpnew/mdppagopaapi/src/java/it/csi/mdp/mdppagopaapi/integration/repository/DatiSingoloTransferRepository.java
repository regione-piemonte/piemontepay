/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpSingoloTransfer;


/**
 * Spring data Jpa repository for "DatiSingoloTransferRepository" <br>
 *
 * @author Marco.Mezzolla
 */

@Repository
public interface DatiSingoloTransferRepository extends IRepository<MdpSingoloTransfer, Integer> {

    List<MdpSingoloTransfer> findByMdpReceipt ( MdpReceipt mdpReceipt );

}
