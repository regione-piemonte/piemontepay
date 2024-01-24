/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaGetPaymentRes;

public interface PaGetPaymentBuisnessService extends IServices {

    /**
     * Servizio contenente la logica di business della primitiva di richiesta di un pagaemnto.
     *
     * @param PaGetPaymentReq parametro di input
     * @return PaGetPaymentRes
     */
    PaGetPaymentRes paGetPayment ( PaGetPaymentReq paGetPaymentReq );

}
