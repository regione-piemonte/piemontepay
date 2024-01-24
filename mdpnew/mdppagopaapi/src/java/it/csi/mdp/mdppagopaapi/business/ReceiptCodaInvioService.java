/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.ReceiptCodaInvio;



public interface ReceiptCodaInvioService {

    
    /**
     * Metodo per registrare un evento.
     *
     * @param ElementoMultiversamento
     * @return
     */
    public ReceiptCodaInvio insert ( ReceiptCodaInvio receiptCodaInvio );


}
