/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;


public interface StoricoRiferimentoContabileRepository extends JpaRepository<StoricoRiferimentoContabile, Long> {

    List<StoricoRiferimentoContabile> findByRiferimentoContabileOrderByIdDesc ( RiferimentoContabile riferimentoContabile );

}
