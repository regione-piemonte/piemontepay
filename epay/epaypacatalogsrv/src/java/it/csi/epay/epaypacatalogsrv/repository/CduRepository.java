/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.csi.epay.epaypacatalogsrv.model.CategoriaCdu;
import it.csi.epay.epaypacatalogsrv.model.Cdu;

public interface CduRepository extends JpaRepository<Cdu, Long>, JpaSpecificationExecutor<Cdu> {

    List<Cdu> findByCodiceIn ( List<String> codiciRuolo );

    Cdu findOneByCodice ( String codiceRuolo );

    List<Cdu> findByCategoria ( CategoriaCdu categoria, Sort sortClause );

}
