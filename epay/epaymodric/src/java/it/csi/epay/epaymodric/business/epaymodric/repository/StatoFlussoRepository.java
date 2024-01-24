/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;

public interface StatoFlussoRepository extends JpaRepository<CblDStatoFlusso, Long> {

    public CblDStatoFlusso findByCodiceStato(String codiceStato);
    
    public List<CblDStatoFlusso> findByCodiceStatoIn(List<String> codiciStato);
    
    @Query("select s.id from CblDStatoFlusso s where s.codiceStato in ?1 ")
    public List<Long> findIdsCodiceStatoIn(List<String> codiciStato);
}
