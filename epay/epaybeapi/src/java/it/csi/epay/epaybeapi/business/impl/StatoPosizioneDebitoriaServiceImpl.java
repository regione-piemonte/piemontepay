/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.business.StatoPosizioneDebitoriaService;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaOutput;


@Service
public class StatoPosizioneDebitoriaServiceImpl implements StatoPosizioneDebitoriaService {
	
    private static final String CLASSNAME = StatoPosizioneDebitoriaServiceImpl.class.getSimpleName ();

    
    @Autowired
    private PagamentoFacade pagamentoFacade;
    

    @Override
    public StatoPosizioneDebitoriaOutput getStatoPosizioneDebitoria ( StatoPosizioneDebitoriaInput request ) {
        return pagamentoFacade.getStatoPosizioneDebitoria ( request );
    }


}
