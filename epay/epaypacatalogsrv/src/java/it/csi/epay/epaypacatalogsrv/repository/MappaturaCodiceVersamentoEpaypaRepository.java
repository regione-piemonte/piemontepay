/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.MappaturaCodiceVersamentoEpaypa;


public interface MappaturaCodiceVersamentoEpaypaRepository extends JpaRepository<MappaturaCodiceVersamentoEpaypa, Integer> {

    MappaturaCodiceVersamentoEpaypa findByIdCodiceVersamentoOld ( Integer codiceVersamentoOld );
    MappaturaCodiceVersamentoEpaypa findByCodice ( String codiceVersamento );
}
