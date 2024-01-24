/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Configurazione;

@Remote
public interface ConfigurazioneFacade {
	
	/**
     * Cerca la configurazione per codice e codice fiscale dell'ente
     */
    public Configurazione ricerca ( String codice, String codiceFiscale ) throws NoDataException, IllegalArgumentException;
	
}
