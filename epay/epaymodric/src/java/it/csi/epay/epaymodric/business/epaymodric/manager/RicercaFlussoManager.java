/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoSintesi;
/**
 * @author vsgro
 * Caso d'uso: 2.2.19
 */
public interface RicercaFlussoManager {
    
    
    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine ( 
        DTOInputWsRicercaFlussoOrigine dtoInputWsRicercaFlussoOrigine );

    public DTOOutputWsFlussoSintesi ricercaFlussiSintesi ( 
        DTOInputWsRicercaFlussoSintesi dtoInputWsRicercaFlussoSintesi );

    public DTOOutputWsFlussoDettaglio ricercaFlussiDettaglio ( 
        DTOInputWsRicercaFlussoDettaglio dtoInputWsRicercaFlussoDettaglio );
 
}
