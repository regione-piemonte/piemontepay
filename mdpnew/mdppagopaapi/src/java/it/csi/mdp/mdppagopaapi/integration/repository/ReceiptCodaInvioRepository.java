/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.ReceiptCodaInvio;
import it.csi.mdp.mdppagopaapi.integration.domain.ReceiptCodaInvioPK;


/**
 * Spring data Jpa repository for "ReceiptCodaInvioRepository" <br>
 *
 */

@Repository
public interface ReceiptCodaInvioRepository extends IRepository<ReceiptCodaInvio, ReceiptCodaInvioPK> {

//    List<ReceiptCodaInvio> findByMultiId ( int elementoMultiversamento );

}
