/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Rr;
@Remote
public interface RichiestaRevocaFacade {
	

	/**
	 * Inserisce revoca
	 */
	public Long inserisci(Rr richiestaRevoca) throws IllegalArgumentException, Exception;
	
    public Integer inserisciSingolaRevoca(DatiSingolaRevoca richiestaRevoca, Integer rrID) throws IllegalArgumentException;

}
