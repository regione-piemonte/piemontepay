/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.Email;


public interface EmailRepository extends JpaRepository<Email, Long> {

    public List<Email> findByCodiceStatoIn ( List<String> stato );

    public List<Email> findByCodiceStato ( String codiceStato );

}
