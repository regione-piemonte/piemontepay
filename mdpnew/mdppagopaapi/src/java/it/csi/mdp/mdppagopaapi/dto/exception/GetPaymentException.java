/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.exception;

import it.csi.mdp.mdppagopaapi.pa.pafornode.CtFaultBean;

/**
 *
 */

public class GetPaymentException extends PaymentException {

    /**
     *
     */
    private static final long serialVersionUID = 3215645938406902214L;

    public GetPaymentException ( CtFaultBean ctFault ) {
        super ( ctFault );
    }
}
