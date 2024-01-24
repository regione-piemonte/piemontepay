/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoDettaglio;

/**
 * @author vsgro
 */
public interface FlussoDettaglioManager {

    public List<FlussoDettaglio> leggi ( ArrayList<Long> ids );

    public DTOOutputWsFlussoDettaglio ricercaFlussiDettaglio (Long idFlussoSintesi);

    public boolean controllaDettagliPerRiconciliazione ( FlussoSintesi flussoSintesi );

    public List<FlussoDettaglio> cercaDettagliDaSintesi ( Long idFlussoSintesi );
    
    void aggiornaDettaglio ( FlussoDettaglio  dettaglio );
    
    void inserisciDettaglioDaDettaglio ( FlussoDettaglio  dettaglio );
    
}
