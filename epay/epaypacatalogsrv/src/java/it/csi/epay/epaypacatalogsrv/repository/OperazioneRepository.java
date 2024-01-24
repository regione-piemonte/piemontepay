/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.csi.epay.epaypacatalogsrv.model.Operazione;


/**
 * Repository class for {@link Operazione}.
 */
public interface OperazioneRepository extends JpaRepository<Operazione, Long>, JpaSpecificationExecutor<Operazione> {

    List<Operazione> findByCodice ( String codice );

    Operazione findOneByCodice ( String codice );

}
