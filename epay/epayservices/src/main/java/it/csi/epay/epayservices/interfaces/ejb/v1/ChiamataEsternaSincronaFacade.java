/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb.v1;

import javax.ejb.Remote;

import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoOutput;



@Remote
public interface ChiamataEsternaSincronaFacade {

    public AccessoChiamanteEsternoSincronoOutput accessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input );


}
