/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;

public interface ErroreRepository extends JpaRepository<CblDErrore, Long> {
    
    public CblDErrore findByCodiceErrore(String codiceErrore);
    
    public CblDErrore findByCodiceErroreAndTipologia( String codiceErrore, String tipologia );
    
}
