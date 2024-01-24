/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.Map;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicecaLimiteEsportazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;

/**
 * @author vsgro
 */
public interface ConfigurazioneManager {

    public Configurazione leggi ( String idEnte, String nomeAttributo );

    public Configurazione leggiAttributoGenerale ( String nomeAttributo );

    public Map<String, Configurazione> recuperaConfigurazionePerElaborazione ( String idEnte ) throws EpaymodricException;
    
    String getConfigFromProperties ( String property );
    
    String getConfigFromDatabase ( String property );
    
    public DTOOutputWsRicecaLimiteEsportazione ricercaLimiteEsportazione();
    
    public DTOOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport();

}
