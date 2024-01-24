/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;


/**
 * @author vsgro use case 2.2.13
 */	
public interface StoricoManager {


    public void storicizzaDatiFlusso ( Long idOrigine );

    public void storicizzaDatiFlusso(List<CblTFlussoOrigine> flussiOrigineEntityList);
    
    public void storicizzaElaborazione(Elaborazione elaborazione);

}
