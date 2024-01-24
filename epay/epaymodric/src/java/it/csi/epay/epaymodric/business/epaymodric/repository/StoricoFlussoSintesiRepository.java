/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTStoricoFlussoSintesi;

/*
 * 
 * Nuru
 */

public interface StoricoFlussoSintesiRepository extends JpaRepository<CblTStoricoFlussoSintesi, Long> {

}
