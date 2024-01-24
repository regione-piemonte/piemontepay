/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.epay.epaypacatalogsrv.model.EpaypaRRuoloCdu;
import it.csi.epay.epaypacatalogsrv.model.EpaypaRRuoloCduPK;


/**
* Generated by Spring Data Generator on 26/10/2018
*/
@Repository
public interface EpaypaRRuoloCduRepository extends JpaRepository<EpaypaRRuoloCdu, EpaypaRRuoloCduPK>, JpaSpecificationExecutor<EpaypaRRuoloCdu> {

    @Query ( "select associazione from EpaypaRRuoloCdu associazione where id.codRuolo = :codRuolo" )
    List<EpaypaRRuoloCdu> findByCodRuolo ( @Param ( "codRuolo" ) String codRuolo );

}
