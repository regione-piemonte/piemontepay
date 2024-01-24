/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import it.csi.epay.epaybeapi.integration.domain.EpayDModalitaPagamento;
import org.springframework.stereotype.Repository;
import it.csi.epay.epaybeapi.integration.repository.IRepository;
/**
 * Spring data Jpa repository for "EpayDModalitaPagamento" <br>
 *
 * @author EII
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayDModalitaPagamentoRepository extends IRepository<EpayDModalitaPagamento, Integer> {
}
