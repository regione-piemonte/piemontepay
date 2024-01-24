/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.epay.epaybeapi.integration.domain.EpayRTipoPagamentoCollegato;
import it.csi.epay.epaybeapi.integration.domain.EpayRTipoPagamentoCollegatoKey;
/**
 * Spring data Jpa repository for "EpayRTipoPagamentoCollegato" <br>
 *
 * @author fabio.fenoglio
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayRTipoPagamentoCollegatoRepository extends IRepository<EpayRTipoPagamentoCollegato, EpayRTipoPagamentoCollegatoKey> {
}
