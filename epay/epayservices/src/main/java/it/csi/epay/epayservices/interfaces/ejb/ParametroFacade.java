/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import java.util.List;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Parametro;

@Remote
public interface ParametroFacade {
	
	/**
	 * Testa il bean
	 * @param value
	 * @return
	 */
	public String test(String value); 

	/**
	 * Cerca un Parametro per nome
	 */
	public Parametro ricerca(String gruppo, String codice) throws NoDataException, IllegalArgumentException;

	/**
	 * Cerca un intero gruppo di parametri 
	 */
	public List<Parametro> ricercaGruppo(String gruppo) throws IllegalArgumentException;	
}
