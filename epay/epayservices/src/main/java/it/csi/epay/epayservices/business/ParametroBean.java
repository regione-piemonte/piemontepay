/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.ParametriManager;
import it.csi.epay.epayservices.interfaces.ejb.ParametroFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Parametro;

@Stateless(name="ParametroFacade", mappedName = "Parametro")
public class ParametroBean extends _BaseBean implements ParametroFacade{

	@EJB
	ParametriManager parametriManager;
	
	@Override
	public String test(String value) {
		return "Test PagamentoBean" + value == null ? "" : " - value = " + value;
	}

	@Override
	public Parametro ricerca(String gruppo, String codice) throws NoDataException, IllegalArgumentException {
		Parametro parametro = parametriManager.getParametro(gruppo, codice);		
		return parametro;
	}
	
	@Override
	public List<Parametro> ricercaGruppo(String gruppo) throws IllegalArgumentException {
		List<Parametro> parametri = parametriManager.getGruppoParametri(gruppo);		
		return parametri;
	}
	
}
