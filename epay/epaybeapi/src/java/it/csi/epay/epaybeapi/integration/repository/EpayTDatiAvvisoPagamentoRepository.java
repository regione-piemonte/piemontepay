/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTDatiAvvisoPagamento;


/**
 * Spring data Jpa repository for "EpayTDatiAvvisoPagamento" <br>
 *
 * @author EII
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayTDatiAvvisoPagamentoRepository extends IRepository<EpayTDatiAvvisoPagamento, Long> {

}
