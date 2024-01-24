/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamento;


/**
 * Spring data Jpa repository for "ElementoMultiversamentoRepository" <br>
 *
 * @author Silvia.Balsamini
 * @author Marco.Mezzolla
 */

@Repository
public interface ElementoMultiversamentoRepository extends IRepository<ElementoMultiversamento, Integer> {

    ElementoMultiversamento findFirstByTransactionIdAndIuvOrderByIdDesc ( String transactionId, String iuv );

}
