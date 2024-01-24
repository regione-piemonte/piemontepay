/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.ChiamataEsternaManager;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;

@Stateless(name="ChiamataEsternaFacade", mappedName = "ChiamataEsterna")
public class ChiamataEsternaBean extends _BaseBean implements ChiamataEsternaFacade {
	
	@EJB
	private ChiamataEsternaManager chiamataEsternaManager;

	@Override
	public Long inserisci(TracciabilitaChiamanteEsterno chiamataEsterna) throws IllegalArgumentException {
		return chiamataEsternaManager.inserisci(chiamataEsterna);
	}

	@Override
	public TracciabilitaChiamanteEsterno ricerca(String idTransazione) throws NoDataException, IllegalAccessException {
		return chiamataEsternaManager.ricerca(idTransazione);
	}

	@Override
    public Long ricercaIdentificativoPagamento ( String identificativoPagamento ) throws NoDataException, IllegalAccessException {
        return chiamataEsternaManager.ricercaIdentificativoPagamento ( identificativoPagamento );
    }

    @Override
	public Long aggiorna(TracciabilitaChiamanteEsterno chiamataEsterna) throws NoDataException, IllegalAccessException {
		return chiamataEsternaManager.aggiorna(chiamataEsterna);
	}

	@Override
	public ChiamanteEsterno recuperaChiamanteEsterno(String codiceChiamanteEsterno) throws NoDataException, IllegalAccessException {
		return chiamataEsternaManager.recuperaChiamanteEsterno(codiceChiamanteEsterno);
	}

    @Override
    public Long inserisciChiamataEsternaNonValida ( ChiamataEsternaNonValida chiamataEsternaNonValida ) throws IllegalArgumentException {
        return chiamataEsternaManager.inserisci ( chiamataEsternaNonValida );
    }

    @Override
    public ChiamataEsternaNonValida ricercaChiamataEsternaNonValida ( ChiamataEsternaNonValida chiamataEsternaNonValida )
                    throws NoDataException, IllegalAccessException {
        return chiamataEsternaManager.ricerca ( chiamataEsternaNonValida );
    }
}
