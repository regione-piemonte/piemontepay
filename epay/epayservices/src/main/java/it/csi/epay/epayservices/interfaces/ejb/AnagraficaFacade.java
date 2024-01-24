/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.AnagraficaTemp;

@Remote
public interface AnagraficaFacade {

    AnagraficaTemp getAnagrafica ( ) throws NoDataException, IllegalArgumentException;
    
    void inserisciAnagrafica (AnagraficaTemp inserisciAnagrafica );
}
