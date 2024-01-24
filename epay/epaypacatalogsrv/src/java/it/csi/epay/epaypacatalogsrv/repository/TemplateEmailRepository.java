/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.TemplateEmail;


public interface TemplateEmailRepository extends JpaRepository<TemplateEmail, Integer> {

    TemplateEmail findOneByCodice ( String codice );
}