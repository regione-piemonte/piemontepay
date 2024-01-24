/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiciversamentoEsclusione;

public interface CodiciVersamentoDaEscludereRepository extends JpaRepository<CblTCodiciversamentoEsclusione, Long> {

    public List<CblTCodiciversamentoEsclusione> findByIdEnte ( String IdEnte );
    
}
