/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business;

import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.StatoPosizioneDebitoriaOutput;

public interface StatoPosizioneDebitoriaService {
    
    StatoPosizioneDebitoriaOutput getStatoPosizioneDebitoria(StatoPosizioneDebitoriaInput request);
    
    
    
    

}
