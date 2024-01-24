/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.CategoriaCdu;

public interface CategoriaCduRepository extends JpaRepository<CategoriaCdu, Integer> {

    CategoriaCdu findOneByCodice ( String codice );
    
    List<CategoriaCdu> findByCodiceIn ( Collection<String> codice );

}
