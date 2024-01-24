/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamentoLight;


/**
 * Spring data Jpa repository for "ElementoMultiversamentoRepository" <br>
 *
 * @author Silvia.Balsamini
 * @author Marco.Mezzolla
 */

@Repository
public interface ElementoMultiversamentoLightRepository extends IRepository<ElementoMultiversamentoLight, Integer> {

    ElementoMultiversamentoLight findFirstByTransactionIdAndIuvOrderByIdDesc ( String transactionId, String iuv );

}
