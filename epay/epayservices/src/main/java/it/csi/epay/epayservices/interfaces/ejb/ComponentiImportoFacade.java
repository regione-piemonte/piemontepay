/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;


@Remote
public interface ComponentiImportoFacade {

    public ComponentiImportoOutput estraiComponentiImporto(ComponentiImportoInput componentiImportoInput);
}
