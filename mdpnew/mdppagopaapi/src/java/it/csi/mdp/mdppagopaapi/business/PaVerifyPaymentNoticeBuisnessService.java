/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.pa.pafornode.PaVerifyPaymentNoticeReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaVerifyPaymentNoticeRes;

public interface PaVerifyPaymentNoticeBuisnessService extends IServices {

    /**
     * Servizio contenente la logica di business della primitiva di verifica di una pagamento.
     * @param paVerifyPaymentNoticeReq parametro di input
     * @return PaVerifyPaymentNoticeReq
     */
    PaVerifyPaymentNoticeRes paVerifyPaymentNotice ( PaVerifyPaymentNoticeReq paVerifyPaymentNoticeReq );

}
