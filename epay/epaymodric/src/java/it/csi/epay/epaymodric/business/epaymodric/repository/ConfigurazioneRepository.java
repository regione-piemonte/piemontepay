/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTConfigurazione;

public interface ConfigurazioneRepository extends JpaRepository<CblTConfigurazione, Long> {

    public List<CblTConfigurazione> findByIdEnte(String idEnte);

    public CblTConfigurazione findByIdEnteAndNomeAttributo(String idEnte, String nomeAttributo);

    public CblTConfigurazione findByNomeAttributo ( String nomeAttributo );

}
