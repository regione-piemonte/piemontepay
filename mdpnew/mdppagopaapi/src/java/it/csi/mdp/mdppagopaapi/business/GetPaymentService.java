/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;

public interface GetPaymentService {

    /**
     * Metodo per ricercare il record nella tabella Mdp_Getpayment in join con i 2 campi in ingresso della Mdp_Receipt.
     *
     */
    public MdpGetpayment findByCreditorReferenceidAndTransactionId ( String creditorId, String transactionId );

    /**
     * Metodo per registrare un record nella tabella mdp_getpayment.
     *
     * @param mdpGetpayment
     * @return
     */
    MdpGetpayment inserisciRecord ( MdpGetpayment mdpGetpayment );

}
