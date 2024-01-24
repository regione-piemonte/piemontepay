/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;


public interface MdpReceiptService {

    /**
     * Metodo per ricercare il primo receipt_id nella tabella Mdp_Receipt.
     *
     * @param stringa contenente receipt_id
     * @return lista di receipt_id uguali al receipt_id passato in ingresso.
     */
    public MdpReceipt findFirstByReceiptIdOrderByIdDesc ( String receiptId );

    /**
     * Metodo per registrare un record nella tabella mdp_receipt.
     *
     * @param mdpReceipt
     * @return
     */
    MdpReceipt inserisciRecord ( MdpReceipt mdpReceipt );

    MdpReceipt findTopByCreditorReferenceId ( String reqIuv );

	MdpReceipt findTopByCreditorReferenceIdAndIdPA ( String reqIuv, String idPa );
}
