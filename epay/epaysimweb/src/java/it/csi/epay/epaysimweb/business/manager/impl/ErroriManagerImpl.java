/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaysimweb.business.manager.ErroriManager;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.facade.impl.EpaysimulatorDataWsFacade;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoErroreInputDTO;

@Service
public class ErroriManagerImpl implements ErroriManager {

    @Autowired
    private EpaysimulatorDataWsFacade simulatorFacade;

    public ErroriManagerImpl () {
    }

    @Override
    public List<FlussoOrigineErrorePagopaOutputDTO> ricercaErrori ( RicercaFlussoErroreInputDTO filtroricerca ) throws OperationFailedException {

        List<FlussoOrigineErrorePagopaOutputDTO> foundItems = new ArrayList<FlussoOrigineErrorePagopaOutputDTO>();
        try {
            foundItems = simulatorFacade.ricercaFlussiInErrore ( filtroricerca );
        } catch ( Exception ex ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", ex );
        }
        return foundItems;
    }
}
