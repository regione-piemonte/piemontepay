/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpGetpayment;



public interface MdpGetpaymentService {

    /**
     * Metodo per registrare un evento.
     *
     * @param gde
     * @return
     */
    MdpGetpayment insert ( MdpGetpayment mdpGetpayment );



}
