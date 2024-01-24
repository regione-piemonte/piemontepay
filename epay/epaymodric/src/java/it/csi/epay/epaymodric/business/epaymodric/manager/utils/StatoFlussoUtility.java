/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author vsgro
 */
public class StatoFlussoUtility {

    final static Logger logger = LogManager.getLogger(StatoFlussoUtility.class);


    public static StatoFlusso getStatoFlusso(CblDStatoFlusso entity) {
        StatoFlusso statoFlusso = null;
        if (entity==null) {
            logger.warn ( "StatoFlussoUtility.getStatoFlusso: oggetto null" );
        } else {
            statoFlusso = new StatoFlusso();
            statoFlusso.setId ( entity.getId () );
            statoFlusso.setCodiceStatoFlusso ( entity.getCodiceStato ());
            statoFlusso.setDescrizioneStatoFlusso ( entity.getDescrizioneStato () );
            statoFlusso.setPermettiRielaborazione ( entity.getPermettiRielaborazione () );
        }
        return statoFlusso;
    }

    public static CblDStatoFlusso getStatoFlusso(StatoFlusso statoFlusso) {
        CblDStatoFlusso cblDStatoFlusso  = null;
        if (statoFlusso==null) {
            logger.warn ( "StatoFlussoUtility.getStatoFlusso - to entity: oggetto null" );
        } else {
            cblDStatoFlusso = new CblDStatoFlusso();
            cblDStatoFlusso.setId ( statoFlusso.getId () );
            cblDStatoFlusso.setCodiceStato ( statoFlusso.getCodiceStatoFlusso ());
            cblDStatoFlusso.setDescrizioneStato ( statoFlusso.getDescrizioneStatoFlusso () );
            cblDStatoFlusso.setPermettiRielaborazione ( statoFlusso.getPermettiRielaborazione () );
        }
        return cblDStatoFlusso;
    }

    public static List<String> getCodiciStati( boolean isRiesecuzione ) {
        List<String> codiceStati = new ArrayList<> ();
        codiceStati.add ( StatoFlussoEnum.BOZZA.getCodice () );
        codiceStati.add ( StatoFlussoEnum.IN_ATTESA.getCodice () );
        if ( isRiesecuzione ) {
            codiceStati.add ( StatoFlussoEnum.FORZATO.getCodice () );
        }
        return codiceStati;
    }

    public static List<String> getCodiciStatiSintesi ( boolean isRiesecuzione ) {
        List<String> codiceStati = new ArrayList<> ();
        codiceStati.add ( StatoFlussoEnum.BOZZA.getCodice () );
        codiceStati.add ( StatoFlussoEnum.IN_ATTESA.getCodice () );
        if ( isRiesecuzione ) {
            codiceStati.add ( StatoFlussoEnum.FORZATO.getCodice () );
        }
        return codiceStati;
    }

    public static List<String> getStatiFlussoDaElaborare (List<String> statiDaEscludere) {
        List<String> statiFlussoDaElaborare = new ArrayList<> ();
        
        

        if (null ==  statiDaEscludere || statiDaEscludere.isEmpty() || !statiDaEscludere.contains( StatoFlussoEnum.BOZZA.getCodice ())) 
        {
        	 statiFlussoDaElaborare.add ( StatoFlussoEnum.BOZZA.getCodice () );
        }
        else
        {
        	 logger.debug ( "Escluso dall'elaborazione lo stato: "+StatoFlussoEnum.BOZZA.getCodice()+" - " +StatoFlussoEnum.BOZZA.getDescrizione());
        }
        
        if (null ==  statiDaEscludere || statiDaEscludere.isEmpty() || !statiDaEscludere.contains( StatoFlussoEnum.IN_ATTESA.getCodice ())) 
        {
        	 statiFlussoDaElaborare.add ( StatoFlussoEnum.IN_ATTESA.getCodice () );
        }
        else
        {
        	 logger.debug ( "Escluso dall'elaborazione lo stato: "+StatoFlussoEnum.IN_ATTESA.getCodice()+" - " +StatoFlussoEnum.IN_ATTESA.getDescrizione());
        }
        if (null ==  statiDaEscludere || statiDaEscludere.isEmpty() || !statiDaEscludere.contains( StatoFlussoEnum.FORZATO.getCodice ())) 
        {
        	 statiFlussoDaElaborare.add ( StatoFlussoEnum.FORZATO.getCodice () );
        }
        else
        {
        	 logger.debug ( "Escluso dall'elaborazione lo stato: "+StatoFlussoEnum.FORZATO.getCodice()+" - " +StatoFlussoEnum.FORZATO.getDescrizione());
        }

        return statiFlussoDaElaborare;
    }

}
