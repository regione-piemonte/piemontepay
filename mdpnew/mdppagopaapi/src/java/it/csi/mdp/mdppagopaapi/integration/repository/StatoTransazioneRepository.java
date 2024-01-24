/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.StatoTransazione;


/**
 * Spring data Jpa repository for "StatoTransazioneRepository" <br>
 *
 */
@Repository
public interface StatoTransazioneRepository extends IRepository<StatoTransazione, Long> {

//    StatoTransazione findTopByCodStato ( Long codStato );
}
