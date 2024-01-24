/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.epay.epaybeapi.integration.domain.EpayTPagamento;


/**
 * Spring data Jpa repository for "EpayTPagamento" <br>
 *
 * @author EII
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayTPagamentoRepository extends IRepository<EpayTPagamento, Long> {

    @Query ( "SELECT p FROM EpayTPagamento p WHERE p.codicePagamentoRifEnte = :crEnte AND p.epayTTipoPagamento.codiceVersamento = :cv AND p.epayTTipoPagamento.epayTEnti.codiceFiscale = :cfEnte" )
    EpayTPagamento findByCodiceRiferimentoEnteAndCodiceFiscaleEnteAndCodiceVersamento (
        @Param ( "crEnte" ) String codiceRiferimentoEnte,
        @Param ( "cfEnte" ) String codiceFiscaleEnte,
        @Param ( "cv" ) String codiceVersamento );

    @Query ( "SELECT p FROM EpayTPagamento p WHERE p.codicePagamentoRifEnte = :crEnte AND p.epayTTipoPagamento.idTipoPagamento = :idCv" )
    EpayTPagamento findByIdCodiceRiferimentoEnteAndCodiceVersamento (
        @Param ( "crEnte" ) String codiceRiferimentoEnte,
        @Param ( "idCv" ) Long idCodiceVersamento );

    @Query ( "SELECT p FROM EpayTPagamento p WHERE p.iuvNumeroAvviso = :iuv" )
    EpayTPagamento findByIuvNumeroAvviso ( @Param ( "iuv" ) String iuv );

    @Query ( "SELECT p FROM EpayTPagamento p WHERE p.epayDStatoPagamento.idStato = :idStato" )
    Page<EpayTPagamento> findByStatoPagamento ( @Param ( "idStato" ) Short idStato, Pageable pageable );

    @Query ( "SELECT p FROM EpayTPagamento p WHERE p.codicePagamentoRifEnte IS NOT NULL" )
    Page<EpayTPagamento> findByCodiceRiferimentoEnteNotNull ( Pageable pageable );

}
