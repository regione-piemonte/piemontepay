/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpSingoloTransfer;


/**
 * Spring data Jpa repository for "MdpSingoloTransferRepository" <br>
 *
 * @author Silvia.Balsamini
 */
@SuppressWarnings ( "unused" )
@Repository
public interface MdpSingoloTransferRepository extends IRepository<MdpSingoloTransfer, Integer> {
}
