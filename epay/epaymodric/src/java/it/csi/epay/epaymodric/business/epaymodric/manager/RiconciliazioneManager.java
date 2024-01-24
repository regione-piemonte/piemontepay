/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;
import java.util.Map;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.bo.Provvisori;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOErroreFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.ElaborazioneException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.FlussoOrigineException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.FlussoSintesiException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.InputNotValidException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RiconciliazioneException;

public interface RiconciliazioneManager {

    void controllaValiditaParametriInput ( DTOInputWsMotoreDiRiconciliazione input ) throws InputNotValidException;

    Ente recuperaEnte(String identificativoEnte) throws ElaborazioneException;

    List<FlussoOrigine> inizializzaFlussiOrigineDaElaborare ( Ente ente, Elaborazione elaborazione, List<String> flussiDaElaborare, int pageNumber,
        int pageSize, List<String> statiDaEscludere ) throws ElaborazioneException;

    void verificaRiconciliabilitaEnte ( Ente ente, List<FlussoOrigine> flussiOrigineDaElaborare, List<DTOErroreFlusso> errori ) throws ElaborazioneException;

    //Chiamata in transazione dei precedenti due metodi
    public List<FlussoOrigine> recuperaFlussiOrigineDaElaborareEVerificaRiconciliabilita ( Ente ente, Elaborazione elaborazione,
        List<String> flussiDaElaborare, List<DTOErroreFlusso> errori, int pageNumber, int pageSize, List<String> statiDaEscludere ) throws ElaborazioneException;

    List<FlussoOrigine> recuperaProvvisori ( Configurazione conf, Ente ente, List<FlussoOrigine> flussiOrigineDaElaborare, Elaborazione elaborazione )
                    throws ElaborazioneException;

    Configurazione recuperaConfigurazione ( String identificativoEnte );

    void verificaNumeroMassimoTentativi(FlussoOrigine flussoOrigine, Configurazione configurazione)  throws FlussoOrigineException;

    void verificaPsp(FlussoOrigine flussoOrigine)  throws FlussoOrigineException;

    public List<FlussoSintesi> recuperaFlussiSintesi ( Ente ente, FlussoOrigine flussoOrigine ) throws FlussoOrigineException;

    void verificaCatalogoEAggiornaFlussoSintesi ( Provvisori provvisorio, Ente ente, FlussoOrigine flussoOrigine, FlussoSintesi flussoSintesi ) throws RiconciliazioneException;

    void controllaCodiciEsclusione ( Ente ente, FlussoSintesi flussoSintesi ) throws FlussoSintesiException;

    void controllaDatiSpecificiRiscossione ( FlussoSintesi flussoSintesi, Ente ente ) throws FlussoSintesiException;

    void controllaImportiProvvisorio ( FlussoOrigine flussoOrigine, Provvisori provvisorio ) throws FlussoOrigineException;

    void elaboraFlussoSintesi ( Provvisori provvisorio, FlussoOrigine flussoOrigine, FlussoSintesi flussoSintesi, Ente ente ) throws  RiconciliazioneException;

    void aggiornaStatoFlussoOrigine(FlussoOrigine flussoOrigine, StatoFlussoEnum stato) throws FlussoOrigineException;

    void aggiornaStatoElaborazione ( Elaborazione elaborazione, StatoElaborazioneEnum stato, List<FlussoOrigine> flussiOrigine ) throws ElaborazioneException;

    DTOOutputWsMotoreDiRiconciliazione elaborazioneEsegui (Map<String, Configurazione> configurazioneEnte, Elaborazione elaborazione, List<String> statiDaEscludere);
    
    DTOOutputWsMotoreDiRiconciliazione eseguiRieseguiElaborazione ( Map<String, Configurazione> configurazioneEnte, Elaborazione elaborazione, List<String> statiDaEscludere, 
    																int pageIdx, Integer pageSize,  boolean firstPage, boolean lastPage, 
    																List<FlussoOrigine> flussiOrigineOriginali, 
    																List<FlussoOrigine> flussiElaborati);

    DTOErroreFlusso salvaErroreFlussoSintesi ( FlussoSintesi flussoSintesi, String codiceErrore, List<String> infoAggiuntive );

    DTOErroreFlusso salvaErroreFlussoOrigine ( FlussoOrigine flussoOrigine, String codiceErrore, List<String> infoAggiuntive, StatoFlussoEnum statoFlusso );

    boolean salvaErroreElaborazione ( Elaborazione elaborazione, String codiceErrore, StatoElaborazioneEnum statoElaborazione, String messaggioErrore,
        List<String> infoAggiuntive, List<FlussoOrigine> flussiOrigine );

}
