/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;


import javax.ejb.EJB;
import javax.ejb.Singleton;

import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.interfaces.ejb.AnagraficaFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.AnagraficaTemp;

//
@Singleton(name = "AnagraficaFacade", mappedName = "Anagrafica")
public class AnagraficaBean extends _BaseBean implements AnagraficaFacade {

    @EJB
    private AnagraficaManager anagraficaManager;

	@Override
	public AnagraficaTemp getAnagrafica() throws NoDataException, IllegalArgumentException {
		return anagraficaManager.getAnagraficaTemp();
	}

	@Override
	public void inserisciAnagrafica(AnagraficaTemp inserisciAnagrafica) {
		anagraficaManager.aggiornaInserisciAnagraficaTemp(inserisciAnagrafica);
		
	}

}
