/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.ejb.mock.epayservices;

import org.springframework.stereotype.Service;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;

@Service
public class ChiamataEsternaFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaFacade{

	@Override
	public Long inserisci(TracciabilitaChiamanteEsterno chiamataEsterna) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TracciabilitaChiamanteEsterno ricerca(String idTransazione) throws NoDataException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long ricercaIdentificativoPagamento(String identificativoPagamento)
			throws NoDataException, IllegalAccessException {
		return (long)100;
	}

	@Override
	public Long aggiorna(TracciabilitaChiamanteEsterno chiamataEsterna) throws NoDataException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChiamanteEsterno recuperaChiamanteEsterno(String codiceChiamanteEsterno)
			throws NoDataException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long inserisciChiamataEsternaNonValida(ChiamataEsternaNonValida chiamataEsternaNonValida)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChiamataEsternaNonValida ricercaChiamataEsternaNonValida(ChiamataEsternaNonValida chiamataEsternaNonValida)
			throws NoDataException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
