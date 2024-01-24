/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.epayfruitore;


import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtDatiSingolaRevoca;
import it.csi.epay.epayservices.ws.ricevirichiestarevoca.CtRichiestaRevoca;


@Singleton ( name = "RiceviRichiestaRevocaOperation", mappedName = "RiceviRichiestaRevocaOperation" )
public class RiceviRichiestaRevocaOperation {

	protected LogUtil log = new LogUtil(this.getClass());

//    @EJB
//    EnteTempManager enteTempManager;
//
//    @EJB
//    EnteManager enteManager;
//
//    @EJB
//    AggiornaEnteConfermaOperation aggiornaEnteConfermaOperation;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CodiciEsito execute ( CtRichiestaRevoca parameters ) {
		for (int i = 0; i < parameters.getDatiRevoca().getDatiSingolaRevoca().size(); i++) {
			CtDatiSingolaRevoca listaRevoca = parameters.getDatiRevoca().getDatiSingolaRevoca().get(i);
			log.info("CodiciEsito.execute con causale revoca ", " sono in CodiciEsito.execute listaRevoca.getCausaleRevoca()) " + listaRevoca.getCausaleRevoca());
		}
		
     

        return CodiciEsito.ESECUZIONE_OK;
	}

}
