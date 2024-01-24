/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamento;
import it.csi.mdp.mdppagopaapi.integration.domain.ElementoMultiversamentoLight;


public interface ElementoMultiversamentoService {

    /**
     * Metodo per ricercare il primo receipt_id nella tabella Mdp_Receipt.
     *
     * @param stringa contenente transactionId
     * @param stringa contenente iuv
     * @return lista di receipt_id uguali al receipt_id passato in ingresso.
     */
    ElementoMultiversamento findByTransactionIdAndIuv ( String transactionId, String iuv );

    /**
     * Metodo per ricercare il primo receipt_id nella tabella Mdp_Receipt.
     *
     * @param stringa contenente transactionId
     * @param stringa contenente iuv
     * @return lista di receipt_id uguali al receipt_id passato in ingresso.
     */
    ElementoMultiversamentoLight findByTransactionIdAndIuvLight ( String transactionId, String iuv );

    /**
     * Metodo per registrare un evento.
     *
     * @param ElementoMultiversamento
     * @return
     */
    ElementoMultiversamento insert ( ElementoMultiversamento elementoMultiversamento );

    ElementoMultiversamento findById ( int id );

}
