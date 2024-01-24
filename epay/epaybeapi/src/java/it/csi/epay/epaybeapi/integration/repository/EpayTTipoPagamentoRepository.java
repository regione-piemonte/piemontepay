/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTTipoPagamento;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.csi.epay.epaybeapi.integration.repository.IRepository;
/**
 * Spring data Jpa repository for "EpayTTipoPagamento" <br>
 *
 * @author EII
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayTTipoPagamentoRepository extends IRepository<EpayTTipoPagamento, Long> {
	
	@Query("SELECT tp FROM EpayTTipoPagamento tp WHERE tp.epayTEnti.idEnte = :ide and tp.codiceVersamento = :cv")
	EpayTTipoPagamento findByEpayTEntiIdEnteAndCodiceVersamento(@Param("ide")Long idEnte, @Param("cv")String codiceVersamento);
}
