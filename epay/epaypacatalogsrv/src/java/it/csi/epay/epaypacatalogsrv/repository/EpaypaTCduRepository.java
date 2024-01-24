/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.csi.epay.epaypacatalogsrv.model.EpaypaTCdu;


/**
* Generated by Spring Data Generator on 26/10/2018
*/
@Repository
public interface EpaypaTCduRepository extends JpaRepository<EpaypaTCdu, Integer>, JpaSpecificationExecutor<EpaypaTCdu> {

}
