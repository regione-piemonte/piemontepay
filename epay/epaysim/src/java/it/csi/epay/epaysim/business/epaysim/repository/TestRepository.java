/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaysim.business.epaysim.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

}
