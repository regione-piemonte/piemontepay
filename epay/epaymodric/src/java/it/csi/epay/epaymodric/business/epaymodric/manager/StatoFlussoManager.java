/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.ArrayList;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoFlusso;

public interface StatoFlussoManager {
    
    public ArrayList<DTOStatoFlusso> ricercaStatiFlussi ();

}
