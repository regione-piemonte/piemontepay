/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.ejb.mock.epayservices;

import org.springframework.stereotype.Service;

import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;

@Service
public class ComponentiImportoFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.ComponentiImportoFacade{

    @Override
    public ComponentiImportoOutput estraiComponentiImporto ( ComponentiImportoInput arg0 ) {
        return new ComponentiImportoOutput ();
    }

}
