/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.custom.EnteRepositoryCustom;

public interface EnteRepository extends
	JpaRepository<Ente, Long>,
	JpaSpecificationExecutor<Ente>,
	EnteRepositoryCustom {

	Ente findOneByCodiceFiscale(String codiceFiscale);

    List<Ente> findByUtenteModifica ( String utenteModifica );

    Ente findOneById(Long id);
}
