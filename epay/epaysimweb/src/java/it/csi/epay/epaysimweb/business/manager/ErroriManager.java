/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.business.manager;

import java.util.List;

import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoErroreInputDTO;


public interface ErroriManager {

    List<FlussoOrigineErrorePagopaOutputDTO> ricercaErrori ( RicercaFlussoErroreInputDTO filtroricerca ) throws OperationFailedException;
}
