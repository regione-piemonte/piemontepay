/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTEnti;


/**
 * Spring data Jpa repository for "EpayTEnti" <br>
 *
 * @author EII
 */

@Repository
public interface EpayTEntiRepository extends IRepository<EpayTEnti, Long> {

    @Query ( "SELECT e FROM EpayTEnti e WHERE e.codiceFiscale = :cf" )
    EpayTEnti findByCodiceFiscale ( @Param ( "cf" ) String codiceFiscale );

    EpayTEnti findByListOfEpayTTipoPagamentoIdTipoPagamento ( Long idTipoPagamento );
}
