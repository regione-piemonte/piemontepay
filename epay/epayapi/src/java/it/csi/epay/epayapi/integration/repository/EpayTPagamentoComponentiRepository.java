/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.repository;

import it.csi.epay.epayapi.integration.domain.EpayTPagamentoComponenti;
import org.springframework.stereotype.Repository;
import it.csi.epay.epayapi.integration.repository.IRepository;
/**
 * Spring data Jpa repository for "EpayTPagamentoComponenti" <br>
 *
 * @author fabio.fenoglio
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayTPagamentoComponentiRepository extends IRepository<EpayTPagamentoComponenti, Long> {
}
