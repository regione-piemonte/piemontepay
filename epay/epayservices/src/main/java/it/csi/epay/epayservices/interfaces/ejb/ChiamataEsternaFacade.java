/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;

@Remote
public interface ChiamataEsternaFacade {
	
	public Long inserisci(TracciabilitaChiamanteEsterno chiamataEsterna) throws IllegalArgumentException;
	
	public TracciabilitaChiamanteEsterno ricerca(String idTransazione) throws NoDataException, IllegalAccessException;
	
    public Long ricercaIdentificativoPagamento ( String identificativoPagamento ) throws NoDataException, IllegalAccessException;

	public Long aggiorna(TracciabilitaChiamanteEsterno chiamataEsterna) throws NoDataException, IllegalAccessException;
	
	public ChiamanteEsterno recuperaChiamanteEsterno(String codiceChiamanteEsterno) throws NoDataException, IllegalAccessException;

    public Long inserisciChiamataEsternaNonValida ( ChiamataEsternaNonValida chiamataEsternaNonValida ) throws IllegalArgumentException;

    public ChiamataEsternaNonValida ricercaChiamataEsternaNonValida ( ChiamataEsternaNonValida chiamataEsternaNonValida )
                    throws NoDataException, IllegalAccessException;
}
