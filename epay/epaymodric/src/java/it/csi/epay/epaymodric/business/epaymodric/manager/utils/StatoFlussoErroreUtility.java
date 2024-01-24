/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.*;
import it.csi.epay.epaymodric.business.epaymodric.model.CblRStatoFlussoErrore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;


/**
 *
 * @author vsgro
 */
public class StatoFlussoErroreUtility {

    final static Logger logger = LogManager.getLogger(StatoFlussoErroreUtility.class);


    public static StatoFlussoErrore getStatoFlussoErrore(CblRStatoFlussoErrore entity) {
        StatoFlussoErrore statoFlussoErrore = new StatoFlussoErrore();
        if (entity==null) {
            logger.warn ( "StatoFlussoErroreUtility.getStatoFlussoErrore: oggetto null" );
        } else {
            statoFlussoErrore.setId ( entity.getId () );
            if ( entity.getCblTFlussoOrigine () != null ) {
                statoFlussoErrore.setFlussoOrigine (
                    FlussiUtility.getFlussoOrigine ( entity.getCblTFlussoOrigine () ) );
            }
            statoFlussoErrore.setErrore ( ErroreUtility.getErrore ( entity.getCblDErrore () ) );
            statoFlussoErrore.setDataAggiornamentoStato ( entity.getDataAggiornamentoStato () );
            statoFlussoErrore.setDescrizioneAggiuntivaErrore ( entity.getDescrizioneAggiuntivaErrore () );
        }
        return statoFlussoErrore;
    }

    public static CblRStatoFlussoErrore getStatoFlussoErrore(StatoFlussoErrore statoFlussoErrore) {
        CblRStatoFlussoErrore cblRStatoFlussoErrore = new CblRStatoFlussoErrore();
        if (statoFlussoErrore==null) {
            logger.warn ( "StatoFlussoErroreUtility.getStatoFlussoErrore - to entity: oggetto null" );
        } else {
            cblRStatoFlussoErrore.setId ( statoFlussoErrore.getId () );
            if ( null != statoFlussoErrore.getFlussoOrigine () ) {
                cblRStatoFlussoErrore.setCblTFlussoOrigine (
                    FlussiUtility.getFlussoOrigine ( statoFlussoErrore.getFlussoOrigine () ) );
            }
            cblRStatoFlussoErrore.setDataAggiornamentoStato ( statoFlussoErrore.getDataAggiornamentoStato () );
            cblRStatoFlussoErrore.setCblDErrore ( ErroreUtility.getErrore ( statoFlussoErrore.getErrore () ) );
            cblRStatoFlussoErrore.setDescrizioneAggiuntivaErrore (statoFlussoErrore.getDescrizioneAggiuntivaErrore () );
        }
        return cblRStatoFlussoErrore;
    }

    public static StatoFlussoErrore caricaStatoFlussoErrore(
        Flusso flusso, Errore errore
                    ) {
        StatoFlussoErrore statoFlussoErrore = new StatoFlussoErrore();
        if (flusso==null) {
            logger.error ( "StatoFlussoErroreUtility.caricaStatoFlussoErrore: oggetto flusso null" );
        } else {
            if (flusso instanceof FlussoOrigine) {
                statoFlussoErrore.setFlussoOrigine ( (FlussoOrigine)flusso );
            } else if (flusso instanceof FlussoSintesi) {
                statoFlussoErrore.setFlussoOrigine ( ((FlussoSintesi)flusso).getFlussoOrigine () );
            }
        }
        if (errore==null) {
            logger.error ( "StatoFlussoErroreUtility.caricaStatoFlussoErrore: oggetto errore null" );
        } else {
            statoFlussoErrore.setErrore ( errore );
        }
        statoFlussoErrore.setDataAggiornamentoStato ( new Timestamp ( System.currentTimeMillis () ) );
        statoFlussoErrore.setDescrizioneAggiuntivaErrore ( errore.getDescrizioneErrore ()  );
        return statoFlussoErrore;
    }

}
