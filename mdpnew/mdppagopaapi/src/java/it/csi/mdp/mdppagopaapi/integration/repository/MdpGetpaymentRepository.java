/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;


/**
 * Spring data Jpa repository for "MdpGetpaymentRepository" <br>
 *
 * @author Marco.Mezzolla
 * @author Silvia.Balsamini
 */

@Repository
public interface MdpGetpaymentRepository extends IRepository<MdpGetpayment, Integer> {

    MdpGetpayment findByCreditorReferenceidAndTransactionId ( String creditorId, String transactionId );
}
