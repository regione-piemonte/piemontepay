/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.csi.epay.epaypacatalogsrv.model.LogTransazione;


/**
 * Repository class for {@link LogTransazione}
 */
public interface LogTransazioneRepository extends JpaRepository<LogTransazione, Long>, JpaSpecificationExecutor<LogTransazione> {

}
