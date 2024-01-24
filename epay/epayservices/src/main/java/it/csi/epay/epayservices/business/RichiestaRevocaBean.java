/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.RrManager;
import it.csi.epay.epayservices.interfaces.ejb.RichiestaRevocaFacade;
import it.csi.epay.epayservices.model.DatiSingolaRevoca;
import it.csi.epay.epayservices.model.Rr;

@Stateless(name="RichiestaRevocaFacade", mappedName = "RichiestaRevoca")
public class RichiestaRevocaBean extends _BaseBean implements RichiestaRevocaFacade{


	@EJB
	private RrManager rrManager;

	@Override
	public Long inserisci(Rr richiestaRevoca) throws IllegalArgumentException, Exception {
		Integer idRevoca = rrManager.inserisci(richiestaRevoca);
		rrManager.ricercaRtByIuvAndUpdateIdRr(richiestaRevoca.getIuv(), idRevoca);
		return idRevoca.longValue();
	}
	

    @Override
    public Integer inserisciSingolaRevoca(DatiSingolaRevoca singolaRevoca, Integer rrID) throws IllegalArgumentException {
        Integer idRevoca = rrManager.inserisciSingolaRevoca ( singolaRevoca, rrID);
        return idRevoca;
    }
    
}
