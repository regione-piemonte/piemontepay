/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.dto.exception.PaymentException;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTReq;
import it.csi.mdp.mdppagopaapi.pa.pafornode.PaSendRTRes;


public interface PaSendRTBusinessService extends IServicesRT {

    /**
     * Metodo che serve a centralizzare la logica della primitiva sendRT
     * @param paSendRTReq request di pagopa
     * @return PaSendRTResponse risposta a pagopa
     * @throws PaymentException 
     */
    PaSendRTRes paSendRT ( PaSendRTReq paSendRTReq ) throws PaymentException;
}
