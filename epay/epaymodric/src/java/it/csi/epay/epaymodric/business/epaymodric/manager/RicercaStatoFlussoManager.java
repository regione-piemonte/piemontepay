/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsStatoFlusso;
/**
 * @author vsgro
 * Caso d'uso: 2.2.19
 */
public interface RicercaStatoFlussoManager {
    
    
    public DTOOutputWsStatoFlusso ricercaStatoFlusso ( DTOInputWsRicercaStatoFlusso dtoInputWsRicercaStatoFlusso );
 
}
