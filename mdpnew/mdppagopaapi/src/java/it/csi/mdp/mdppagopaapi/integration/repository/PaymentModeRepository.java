/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.Paymentmode;


/**
 * Spring data Jpa repository for "GatewayRepository" <br>
 *
 */
@Repository
public interface PaymentModeRepository extends IRepository<Paymentmode, String> {

}
