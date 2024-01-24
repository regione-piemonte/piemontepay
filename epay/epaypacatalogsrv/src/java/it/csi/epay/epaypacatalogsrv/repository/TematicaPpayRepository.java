/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.TematicaPpay;

public interface TematicaPpayRepository extends JpaRepository<TematicaPpay, Long> {

    TematicaPpay findOneByCodice ( String codice );
    
    List<TematicaPpay> findByCodiceIn ( Collection<String> codice );
}
